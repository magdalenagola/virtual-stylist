package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wardrobe")
@CrossOrigin("*")
class WardrobeController {

    private final WardrobeService wardrobeService;
    private final AuthService authService;

    @Autowired
    WardrobeController(WardrobeService wardrobeService, AuthService authService){
        this.wardrobeService = wardrobeService;
        this.authService = authService;
    }

    @GetMapping
    ResponseEntity<Page<ClothForDisplayWardrobeDTO>> getAllClothes(@PageableDefault(
            size = 50,
            sort = "id",
            direction = Sort.Direction.ASC
    ) Pageable pageable){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(wardrobeService.getAllClothesByUserId(user.getId(), pageable));
    }


    @GetMapping("/{id}")
    ResponseEntity<ClothForDisplayDTO> getCloth(@PathVariable("id") int id){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(wardrobeService.getClothById(id, user.getId()));
    }

    @PutMapping
    ResponseEntity editCloth(@RequestBody ClothForUpdateDTO cloth){
        User user = authService.findUserByEmail();
            wardrobeService.editCloth(cloth, user);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteCloth(@PathVariable("id") int id){
        User user = authService.findUserByEmail();
        wardrobeService.deleteCloth(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity addCloth(@RequestBody ClothForCreationDTO clothForCreation){
        User user = authService.findUserByEmail();
        wardrobeService.addCloth(clothForCreation, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("bodyPart/{bodyPart}")
    ResponseEntity<List<ClothForDisplayWardrobeDTO>> getClothesByBodyPart(@PathVariable("bodyPart") String bodyPart){
        ClothesProperties.BodyPart enumBodyPart = ClothesProperties.BodyPart.valueOf(bodyPart.toUpperCase());
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(wardrobeService.getAllClothesByBodyPart(enumBodyPart, user.getId()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
