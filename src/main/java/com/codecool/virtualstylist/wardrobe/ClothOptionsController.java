package com.codecool.virtualstylist.wardrobe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wardrobe/options")
class ClothOptionsController {

    @GetMapping("/cloth-type")
    public ResponseEntity<List<ClothesProperties.ClothType>> getClothTypes() {
        return new ResponseEntity<>(Arrays.asList(ClothesProperties.ClothType.values()), HttpStatus.OK);
    }

    @GetMapping("/color")
    public ResponseEntity<List<ClothesProperties.Color>> getColors() {
        return new ResponseEntity<>(Arrays.asList(ClothesProperties.Color.values()), HttpStatus.OK);
    }

    @GetMapping("/size")
    public ResponseEntity<List<ClothesProperties.Size>> getSizes() {
        return new ResponseEntity<>(Arrays.asList(ClothesProperties.Size.values()), HttpStatus.OK);
    }

    @GetMapping("/style")
    public ResponseEntity<List<ClothesProperties.Style>> getStyles() {
        return new ResponseEntity<>(Arrays.asList(ClothesProperties.Style.values()), HttpStatus.OK);
    }
}
