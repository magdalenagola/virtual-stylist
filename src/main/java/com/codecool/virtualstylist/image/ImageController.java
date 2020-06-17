package com.codecool.virtualstylist.image;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/img")
class ImageController {

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody byte[] getImageWithMediaType(@PathVariable("fileName") String fileName)
            throws IOException {
        InputStream in = Optional.ofNullable(getClass()
                .getClassLoader()
                .getResourceAsStream(fileName))
                .orElseThrow(()-> new ResourceNotFoundException(fileName + " not found!"));
        return IOUtils.toByteArray(in);
    }

    @PostMapping
    Map<String, String> saveUploadedFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            try {
                UUID uuid = UUID.randomUUID();
                String filename = uuid.toString() + ".jpg";
                byte[] bytes = multipartFile.getBytes();
                File file = new File("src/main/resources",filename);
                file.createNewFile();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
                Map<String, String> fileNameJson = new HashMap<>();
                fileNameJson.put("fileName", filename);
                return fileNameJson;
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        } else {
            System.out.println("File is empty");
            throw new IllegalArgumentException();
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
