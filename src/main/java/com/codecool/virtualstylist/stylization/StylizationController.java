package com.codecool.virtualstylist.stylization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stylization")
public class StylizationController {

    private final StylizationService stylizationService;

    @Autowired
    public StylizationController(StylizationService stylizationService) {
        this.stylizationService = stylizationService;
    }

    @PostMapping
    ResponseEntity adddStylization(@RequestBody StylizationForCreationDTO stylizationForCreation){
        stylizationService.addStylization(stylizationForCreation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
