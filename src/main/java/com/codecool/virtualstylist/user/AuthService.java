package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.exception.ResourceAlreadyExistsException;
import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.security.JwtUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthService {

    private final UserDataAccess userRepository;
    private final RoleDataAccess roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtility jwtUtility;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthService(@Qualifier("userPostgresAccess") UserDataAccess userRepository, @Qualifier("userRolePostgresAccess") RoleDataAccess roleRepository, PasswordEncoder passwordEncoder, JwtUtility jwtUtility, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
        this.modelMapper = modelMapper;
    }


    public void addUser(UserForRegistrationDTO userForRegistration) {
        if (userRepository.existsByEmail(userForRegistration.getEmail())) {
            throw new ResourceAlreadyExistsException("The email is already registered");
        }
        userForRegistration.setPassword(passwordEncoder.encode(userForRegistration.getPassword()));
        User user = modelMapper.map(userForRegistration, User.class);
        Set<String> stringRoles = userForRegistration.getRoles();
        user.setRoles(getUserRoles(stringRoles));
        userRepository.save(user);
    }

    private Set<Role> getUserRoles(Set<String> stringRoles) {
        Set<Role> roles = new HashSet<>();
        if (stringRoles == null) {
            roles.add(getRole(RoleOptions.ROLE_USER));
        } else {
            stringRoles.forEach(role -> {
                if (role.equals("admin")) {
                    roles.add(getRole(RoleOptions.ROLE_ADMIN));
                } else if (role.equals("user")) {
                    roles.add(getRole(RoleOptions.ROLE_USER));
                }
            });
        }
        return roles;
    }

    private Role getRole(RoleOptions role) {
        return roleRepository.findByName(role)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
    }

    public User findUserByEmail(){
        String token = jwtUtility.getAccessToken().orElseThrow(() -> new ResourceNotFoundException("Error: Token is not found."));
        String userEmail = jwtUtility.getUserNameFromJwtToken(token);
        return userRepository.findUserByEmail(userEmail)
                             .orElseThrow(() -> new ResourceNotFoundException("Error: User is not found."));
    }
}
