package com.codecool.virtualstylist.image;

import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/img")
class ImageController {

    private final ImageService imageService;
    private final AuthService authService;

    @Autowired
    ImageController(ImageService imageService, AuthService authService) {
        this.imageService = imageService;
        this.authService = authService;
    }

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody byte[] getImageWithMediaType(@PathVariable("fileName") String fileName) throws IOException {
        User user = authService.findUserByEmail();
        return imageService.downloadFile(user,fileName);
    }

    @PostMapping
    Map<String, String> saveUploadedFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        User user = authService.findUserByEmail();
        return imageService.uploadFile(user, multipartFile);
    }
}
