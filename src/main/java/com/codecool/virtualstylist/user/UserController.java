package com.codecool.virtualstylist.user;


import com.codecool.virtualstylist.security.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userPostgresAccess")
    @Autowired
    UserDataAccess userRepository;

    @Qualifier("userRolePostgresAccess")
    @Autowired
    RoleDataAccess roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtility jwtUtility;
}
