package com.codecool.virtualstylist.staticFiles;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
    public Map<String, String> saveUploadedFile(@RequestParam(name = "file") MultipartFile multipartFile) {
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
                throw new IllegalArgumentException(); //TODO send response code
            }
        } else {
            System.out.println("File is empty");
            throw new IllegalArgumentException(); //TODO send response code
        }
    }
}
