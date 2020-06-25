package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class UserService {

    private final UserDataAccess userDataAccess;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleDataAccess roleDataAccess;

    @Autowired
    UserService(UserDataAccess userDataAccess, PasswordEncoder passwordEncoder, ModelMapper modelMapper, RoleDataAccess roleDataAccess) {
        this.userDataAccess = userDataAccess;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleDataAccess = roleDataAccess;
    }


    void editUser(UserForUpdateDTO userForUpdateDTO, User user) {
        user.setName(userForUpdateDTO.getName());
        user.setGender(userForUpdateDTO.getGender());
        Optional<String> newPasswordPossibly = userForUpdateDTO.getPassword();
        newPasswordPossibly.ifPresent(newPassword -> user.setPassword(passwordEncoder.encode(newPassword)));
        userDataAccess.save(user);
    }

    void deleteUser(int id) {
        Optional<User> userToDelete = userDataAccess.findUserById(id);
        userDataAccess.delete(userToDelete.orElseThrow(() -> new ResourceNotFoundException("User not found!")));
    }

    UserForDisplayDTO getUserForDisplay(User user) throws ResourceNotFoundException {
        return modelMapper.map(user,UserForDisplayDTO.class);
    }

    Page<UserForDisplayAllDTO> getAllUsers(Pageable pageable) throws ResourceNotFoundException {
        Optional<Role> rolePossibly = roleDataAccess.findByName(RoleOptions.ROLE_USER);
        Page<User> usersPagedResult =  userDataAccess.findAllByRolesIs(rolePossibly.orElseThrow(() -> new ResourceNotFoundException("Role not found!")), pageable);
        List<User> users = usersPagedResult.getContent();
        if(users.isEmpty())
            throw new ResourceNotFoundException("Page not found!");
        List<UserForDisplayAllDTO> usersForDisplay = users
                .stream()
                .map(user -> modelMapper.map(user, UserForDisplayAllDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(usersForDisplay, pageable, userDataAccess.countAllByRolesIs(rolePossibly.get()));
    }
}
