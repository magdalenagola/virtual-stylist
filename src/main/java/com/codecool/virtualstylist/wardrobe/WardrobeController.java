package com.codecool.virtualstylist.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wardrobe")
public class WardrobeController {

    private WardrobeService wardrobeService;
   // private UserService userService;

    @Autowired
    public WardrobeController(WardrobeService wardrobeService){//, UserService userService) {
        this.wardrobeService = wardrobeService;
       // this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<ClothForDisplayWardrobeDTO>> getAllClothes(Pageable pageable){
        return ResponseEntity.ok(wardrobeService.getAllClothesByUserId(1, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClothForDisplayDTO> getCloth(@PathVariable("id") Integer id){
        return ResponseEntity.ok(wardrobeService.getClothById(id));
    }

    @PutMapping
    public ResponseEntity editCloth(@RequestBody ClothForUpdateDTO cloth){
        wardrobeService.editCloth(cloth);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCloth(@PathVariable("id") Integer id){
        //TODO findUser
        wardrobeService.deleteCloth(id, 1);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity addCloth(@RequestBody ClothForCreationDTO clothForCreation){
        wardrobeService.addCloth(clothForCreation);
        return ResponseEntity.noContent().build();
    }
}
