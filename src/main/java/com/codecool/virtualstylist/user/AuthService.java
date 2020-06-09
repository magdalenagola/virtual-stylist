package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.security.JwtUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthService {

    private UserDataAccess userRepository;
    private RoleDataAccess roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtility jwtUtility;
    private ModelMapper modelMapper;

    @Autowired
    public AuthService(@Qualifier("userPostgresAccess") UserDataAccess userRepository, @Qualifier("userRolePostgresAccess") RoleDataAccess roleRepository, PasswordEncoder passwordEncoder, JwtUtility jwtUtility, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.modelMapper = modelMapper;
    }


    public boolean addUser(UserForRegistrationDTO userForRegistration) {
        if (userRepository.existsByEmail(userForRegistration.getEmail())) {
            return false;
        }
        userForRegistration.setPassword(passwordEncoder.encode(userForRegistration.getPassword()));
        User user = modelMapper.map(userForRegistration, User.class);

        Set<String> stringRoles = userForRegistration.getRoles();
        Set<Role> roles = new HashSet<>();

        if (stringRoles == null) {
            Role userRole = roleRepository.findByName(RoleOptions.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            stringRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(RoleOptions.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if (role.equals("user")) {
                    Role userRole = roleRepository.findByName(RoleOptions.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    public User findUserByEmail(){
        String token = jwtUtility.getAccessToken();
        String userEmail = jwtUtility.getUserNameFromJwtToken(token);
        return userRepository.findUserByEmail(userEmail)
                             .orElseThrow(() -> new RuntimeException("Error: User is not found."));
    }


}
