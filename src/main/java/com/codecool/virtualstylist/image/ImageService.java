package com.codecool.virtualstylist.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.user.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

@Service
public class ImageService {

    private final AmazonS3 s3;
    private final static String  BUCKET_NAME = "virtual.stylist.images";

    @Autowired
    public ImageService(AmazonS3 s3) {
        this.s3 = s3;
    }

    public byte[] downloadFile(User user, String fileName) throws IOException {
        String path = String.format("%s/%d", BUCKET_NAME, user.getId());
        try {
            S3Object object = s3.getObject(path, fileName);
            return IOUtils.toByteArray(object.getObjectContent());
        }catch(AmazonS3Exception e){
            throw new ResourceNotFoundException(fileName + " not found!");
        }
    }

    public Map<String, String> uploadFile(User user, MultipartFile multipartFile){
        validateFile(multipartFile);
        Optional<Map<String, String>> metaDataOptional = Optional.of(extractMetadata(multipartFile));
            String path = String.format("%s/%d", BUCKET_NAME, user.getId());
            String filename = UUID.randomUUID().toString() + ".jpg";
            ObjectMetadata metadata = new ObjectMetadata();
            metaDataOptional.ifPresent(map -> {
                if (!map.isEmpty())
                    map.forEach(metadata::addUserMetadata);
            });
            try {
                byte[] bytes = multipartFile.getBytes();
                InputStream inputStream = new ByteArrayInputStream(bytes);
                s3.putObject(path, filename,inputStream,metadata);
                Map<String, String> filePropertiesJson = new HashMap<>();
                filePropertiesJson.put("fileName", filename);
                return filePropertiesJson;
            } catch (IOException |AmazonServiceException e) {
                throw new IllegalArgumentException();
            }
    }

    private void validateFile(MultipartFile multipartFile) {
        validateFileContent(multipartFile);
        validateFileType(multipartFile);
    }

    private void validateFileType(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void validateFileContent(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }
}
