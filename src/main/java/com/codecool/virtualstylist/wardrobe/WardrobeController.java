package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    private WardrobeService wardrobeService;
    private AuthService authService;

    @Autowired
    public WardrobeController(WardrobeService wardrobeService, AuthService authService){
        this.wardrobeService = wardrobeService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Page<ClothForDisplayWardrobeDTO>> getAllClothes(@PageableDefault(
            size = 50,
            sort = "id",
            direction = Sort.Direction.ASC
    ) Pageable pageable){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(wardrobeService.getAllClothesByUserId(user.getId(), pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClothForDisplayDTO> getCloth(@PathVariable("id") int id){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(wardrobeService.getClothById(id, user.getId()));
    }

    @PutMapping
    public ResponseEntity editCloth(@RequestBody ClothForUpdateDTO cloth){
        User user = authService.findUserByEmail();
        wardrobeService.editCloth(cloth, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCloth(@PathVariable("id") int id){
        User user = authService.findUserByEmail();
        wardrobeService.deleteCloth(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity addCloth(@RequestBody ClothForCreationDTO clothForCreation){
        User user = authService.findUserByEmail();
        wardrobeService.addCloth(clothForCreation, user);
        return ResponseEntity.noContent().build();
    }
}
