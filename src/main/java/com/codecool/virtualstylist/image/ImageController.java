package com.codecool.virtualstylist.image;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;
import java.util.Optional;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
