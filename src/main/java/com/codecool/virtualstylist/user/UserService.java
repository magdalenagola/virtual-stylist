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

    @Autowired
    public UserService(UserDataAccess userDataAccess, PasswordEncoder passwordEncoder) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
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
}
