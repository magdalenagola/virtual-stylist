package com.codecool.virtualstylist.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    private WardrobeService wardrobeService;
    private UserService userService;

    @Autowired
    public WardrobeController(WardrobeService wardrobeService, UserService userService) {
        this.wardrobeService = wardrobeService;
        this.userService = userService;
    }
}
