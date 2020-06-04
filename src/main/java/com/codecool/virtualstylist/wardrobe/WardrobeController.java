package com.codecool.virtualstylist.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<ClothForDisplayWardrobeDTO> getAllClothes(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "50")Integer pageSize,
            @RequestParam(defaultValue = "clothType") String sortBy
    ){
        return wardrobeService.getAllClothesByUserId(1, pageNo, pageSize, sortBy);
    }


    @GetMapping("/{id}")
    public ClothForDisplayDTO getCloth(@PathVariable("id") Integer id){
        return wardrobeService.getClothById(id);
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
