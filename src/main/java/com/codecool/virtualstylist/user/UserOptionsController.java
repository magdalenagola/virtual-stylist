package com.codecool.virtualstylist.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user/options")
class UserOptionsController {

    @GetMapping("/gender")
    public ResponseEntity<List<Gender>> getClothTypes() {
        return new ResponseEntity<>(Arrays.asList(Gender.values()), HttpStatus.OK);
    }
}
