package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.ClothForDisplayStylizationDTO;
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
@RequestMapping("/stylization")
@CrossOrigin("*")
class StylizationController {

    private final StylizationService stylizationService;
    private final AuthService authService;

    @Autowired
    StylizationController(StylizationService stylizationService, AuthService authService) {
        this.stylizationService = stylizationService;
        this.authService = authService;
    }

    @PostMapping
    ResponseEntity addStylization(@RequestBody StylizationForCreationDTO stylizationForCreation) {
        User user = authService.findUserByEmail();
        stylizationService.addStylization(user, stylizationForCreation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    ResponseEntity<Page<StylizationForDisplayDTO>> getAllStylizations(@PageableDefault(
            size = 50,
            direction = Sort.Direction.ASC,
            sort = "id"
    ) Pageable pageable){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(stylizationService.getAllStylizations(pageable, user.getId()));
    }

    @GetMapping("/{clothId}")
    ResponseEntity<List<ClothForDisplayStylizationDTO>> getAllStylizationsByClothId(@PathVariable("clothId") int clothId) {
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(stylizationService.getAllStylizationsByClothId(clothId, user.getId()));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteStylizationById(@PathVariable("id") int stylizationId){
        User user = authService.findUserByEmail();
        stylizationService.deleteStylization(stylizationId, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/design/{clothId}")
    ResponseEntity<List<ClothForDisplayStylizationDTO>> getAllMatchingClothes(@PathVariable("clothId") int clothId) {
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(stylizationService.getMatchingClothes(clothId, user.getId()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
