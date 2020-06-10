package com.codecool.virtualstylist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PutMapping
    public ResponseEntity editUser(@RequestBody UserForUpdateDTO userForUpdateDTO){
        User user = authService.findUserByEmail();
        userService.editUser(userForUpdateDTO, user);
        return ResponseEntity.noContent().build();
    }
}
