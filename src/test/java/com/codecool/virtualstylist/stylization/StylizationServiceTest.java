package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.ClothForStylizationCreationDTO;
import com.codecool.virtualstylist.wardrobe.FakeWardrobeDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StylizationServiceTest {

    private User user;
    private final ModelMapper modelMapper;
    private final FakeWardrobeDataAccess wardrobeDataAccess;
    private final FakeStylizationDataAccess stylizationDataAccess;
    private StylizationService stylizationService;

    @Autowired
    StylizationServiceTest(ModelMapper modelMapper, FakeWardrobeDataAccess wardrobeDataAccess, FakeStylizationDataAccess stylizationDataAccess) {
        this.modelMapper = modelMapper;
        this.wardrobeDataAccess = wardrobeDataAccess;
        this.stylizationDataAccess = stylizationDataAccess;
        user = new User();
    }
    @BeforeEach
    void initUserService(){
        stylizationService = new StylizationService(stylizationDataAccess,modelMapper, wardrobeDataAccess);
        FakeWardrobeDataAccess.clothes.clear();
        FakeStylizationDataAccess.stylizations.clear();
        user.setId(0);
    }

    @Test
    void shouldAddNewStylization() {
        // Arrange
        int expectedCount = 1 ;
        StylizationForCreationDTO stylizationForCreationDTO = new StylizationForCreationDTO();
        stylizationForCreationDTO.setTag("#Test");
        stylizationForCreationDTO.setClothes(Arrays.asList(new ClothForStylizationCreationDTO(), new ClothForStylizationCreationDTO()));
        // Act
        stylizationService.addStylization(user, stylizationForCreationDTO);
        final List<Stylization> result = stylizationDataAccess.findAll();
        // Assert
        assertEquals(expectedCount, result.size());
    }
}