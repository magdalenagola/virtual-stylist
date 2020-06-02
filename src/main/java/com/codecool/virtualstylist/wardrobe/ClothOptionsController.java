package com.codecool.virtualstylist.wardrobe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wardrobe/options")
class ClothOptionsController {

    @GetMapping
    public Map<String,List<String>> getAllOptions(){
        return ClothesProperties.createOptionsMap();
    }

}
