package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDataAccess userDataAccess;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserDataAccess userDataAccess, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public void editUser(UserForUpdateDTO userForUpdateDTO, User user) {
        user.setName(userForUpdateDTO.getName());
        user.setGender(userForUpdateDTO.getGender());
        user.setPassword(passwordEncoder.encode(userForUpdateDTO.getPassword()));
        userDataAccess.saveAndFlush(user);
    }

    public void deleteUser(int id) {
        Optional<User> userToDelete = userDataAccess.findUserById(id);
        userDataAccess.delete(userToDelete.orElseThrow(() -> new ResourceNotFoundException("User not found!")));
    }

    UserForDisplayDTO getUserById(int userId) throws ResourceNotFoundException {
        Optional<User> userPossibly = userDataAccess.findUserById(userId);
        return modelMapper.map(userPossibly
                        .orElseThrow(() -> new ResourceNotFoundException("User not found!")),
                UserForDisplayDTO.class);
    }
}
