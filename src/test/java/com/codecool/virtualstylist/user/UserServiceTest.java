package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private final PasswordEncoder passwordEncoder;
    @Mock
    private final RoleDataAccess roleDataAccess;

    private UserService userService;
    private final ModelMapper modelMapper;
    private final FakeUserDataAccess userDataAccess;

    @Autowired
    UserServiceTest(ModelMapper modelMapper, FakeUserDataAccess wardrobeDataAccess, PasswordEncoder passwordEncoder, RoleDataAccess roleDataAccess) {
        this.modelMapper = modelMapper;
        this.userDataAccess = wardrobeDataAccess;
        this.passwordEncoder = passwordEncoder;
        this.roleDataAccess = roleDataAccess;
    }
    @BeforeEach
    void initUserService(){
        userService = new UserService(userDataAccess, passwordEncoder, modelMapper, roleDataAccess);
        FakeUserDataAccess.users.clear();
    }

    @Test
    void shouldReturnAllUsers() {
        // Arrange
        arrangeUsers();
        Pageable pageable = PageRequest.of(0, 2);
        final int expectedUserCount = 2;
        final Optional<Role> rolePossibly = Optional.of(new Role(RoleOptions.ROLE_USER));
        // Act
        when(roleDataAccess.findByName(any(RoleOptions.class))).thenReturn(rolePossibly);
        Page<UserForDisplayAllDTO> actualUsers = userService.getAllUsers(pageable);
        // Assert
        assertEquals(expectedUserCount, actualUsers.getTotalElements());
    }

    private void arrangeUsers() {
        final User testUser = new User();
        final User testUser2 = new User();
        testUser.setId(1);
        testUser2.setId(2);
        userDataAccess.save(testUser);
        userDataAccess.save(testUser2);
    }

    @Test
    void shouldDecreaseAllUsers() {
        // Arrange
        arrangeUsers();
        Pageable pageable = PageRequest.of(0, 2);
        final int expectedUserCount = 1;
        final Optional<Role> rolePossibly = Optional.of(new Role(RoleOptions.ROLE_USER));
        // Act
        userService.deleteUser(2);
        when(roleDataAccess.findByName(any(RoleOptions.class))).thenReturn(rolePossibly);
        Page<UserForDisplayAllDTO> actualUsers = userService.getAllUsers(pageable);
        // Assert
        assertEquals(expectedUserCount, actualUsers.getTotalElements());
        assertEquals(Optional.of(1), Optional.ofNullable(actualUsers.getContent().get(0).getId()));
    }

    @Test
    void shouldEditUser(){
        // Arrange
        User testUser = arrangeUserToEdit();
        UserForUpdateDTO userForUpdateDTO = new UserForUpdateDTO();
        userForUpdateDTO.setGender(Gender.FEMALE);
        userForUpdateDTO.setName("ChangedName");
        // Act
        userService.editUser(userForUpdateDTO, testUser);
        User result = userDataAccess.findUserById(0).orElseThrow(ResourceNotFoundException::new);
        // Assert
        assertEquals("ChangedName", result.getName());
        assertEquals(Gender.FEMALE, result.getGender());
    }

    private User arrangeUserToEdit() {
        User testUser = new User();
        testUser.setId(0);
        testUser.setName("Test");
        testUser.setGender(Gender.MALE);
        userDataAccess.save(testUser);
        return testUser;
    }
}