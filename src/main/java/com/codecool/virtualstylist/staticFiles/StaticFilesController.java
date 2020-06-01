package com.codecool.virtualstylist.staticFiles;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping
public class StaticFilesController {

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable("fileName") String fileName) throws IOException {
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream(fileName);
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = "/img")
    public String saveUploadedFile(@RequestBody MultipartFile multipartFile) {
        // TODO check if file is an image
        if (!multipartFile.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                //TODO check if name doesn't exist
                String filename = uuid.toString() + ".jpg";
                byte[] bytes = multipartFile.getBytes();
                File file = new File(filename);
                file.createNewFile();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
                return filename;
            } catch (Exception e) {
                throw new IllegalArgumentException(); //TODO send response code
            }
        } else {
            System.out.println("File is empty");
            throw new IllegalArgumentException(); //TODO send response code
        }
    }
}
