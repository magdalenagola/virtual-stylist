package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ClothForDisplayWardrobeDTO> getAllClothes(){
        return wardrobeService.getAllClothesByUserId(1);
    }

    @GetMapping("/{id}")
    public ClothForDisplayDTO getCloth(@PathVariable("id") Integer id){
        return wardrobeService.getClothById(id);
    }

    @PutMapping
    public void editCloth(@RequestBody ClothForUpdateDTO cloth){
        wardrobeService.editCloth(cloth);
    }

    @DeleteMapping("/{id}")
    public void deleteCloth(@PathVariable("id") Integer id){
        //TODO findUser
        wardrobeService.deleteCloth(id, new User());
    }

    @PostMapping
    public void addCloth(@RequestBody ClothForCreationDTO clothForCreation){
        wardrobeService.addCloth(clothForCreation);
    }
}
