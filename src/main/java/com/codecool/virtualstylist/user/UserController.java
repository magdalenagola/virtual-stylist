package com.codecool.virtualstylist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/details")
    ResponseEntity<UserForDisplayDTO> getUserDetails() {
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(userService.getUserForDisplay(user));
    }

    @GetMapping
    ResponseEntity<Page<UserForDisplayAllDTO>> getAllUsers(@PageableDefault(
            size = 50,
            sort = "email",
            direction = Sort.Direction.ASC
    ) Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PutMapping
    ResponseEntity editUser(@RequestBody UserForUpdateDTO userForUpdateDTO){
        User user = authService.findUserByEmail();
        userService.editUser(userForUpdateDTO, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
