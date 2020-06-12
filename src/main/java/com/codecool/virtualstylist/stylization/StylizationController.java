package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.user.AuthService;
import com.codecool.virtualstylist.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stylization")
public class StylizationController {

    private final StylizationService stylizationService;
    private final AuthService authService;

    @Autowired
    public StylizationController(StylizationService stylizationService, AuthService authService) {
        this.stylizationService = stylizationService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Page<StylizationForDisplayDTO>> getAllStylizations(@PageableDefault(
            size = 50,
            direction = Sort.Direction.ASC,
            sort = "id"
    ) Pageable pageable){
        User user = authService.findUserByEmail();
        return ResponseEntity.ok(stylizationService.getAllStylizations(pageable, user.getId()));
    }
}
