package com.codecool.virtualstylist.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDataAccess userDataAccess;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDataAccess userDataAccess, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userDataAccess = userDataAccess;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public void editUser(UserForUpdateDTO userForUpdateDTO, User user) {
        user.setName(userForUpdateDTO.getName());
        user.setGender(userForUpdateDTO.getGender());
        user.setPassword(passwordEncoder.encode(userForUpdateDTO.getPassword()));
        userDataAccess.saveAndFlush(user);
    }
}
