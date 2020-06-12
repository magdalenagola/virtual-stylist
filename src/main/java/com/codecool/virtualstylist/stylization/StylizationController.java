package com.codecool.virtualstylist.stylization;

import org.springframework.beans.factory.annotation.Autowired;
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
}
