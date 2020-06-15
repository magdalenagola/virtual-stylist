package com.codecool.virtualstylist.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/options")
class UserOptionsController {

    @GetMapping
    Map<String,List<String>> getAllOptions(){
        Map<String, List<String>> options = new LinkedHashMap<>();
        options.put(Gender.class.getSimpleName(), Arrays.stream(Gender.values()).map(Enum::toString).collect(Collectors.toList()));
        return options;
    }
}
