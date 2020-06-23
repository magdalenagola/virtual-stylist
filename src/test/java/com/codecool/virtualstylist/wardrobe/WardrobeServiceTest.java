package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WardrobeServiceTest {

    private User user;
    private WardrobeService wardrobeService;
    private final ModelMapper modelMapper;
    private FakeWardrobeDataAccess wardrobeDataAccess;

    @Autowired
    public WardrobeServiceTest(ModelMapper modelMapper, FakeWardrobeDataAccess wardrobeDataAccess) {
        this.modelMapper = modelMapper;
        this.wardrobeDataAccess = wardrobeDataAccess;
        this.user = new User();
    }

    @BeforeEach
    void initWardrobeService() {
        wardrobeService = new WardrobeService(wardrobeDataAccess, modelMapper);
        user.setId(0);
        FakeWardrobeDataAccess.clothes.clear();
    }

    @Test
    void shouldSaveUser() {
        //arrange
        ClothForCreationDTO clothForCreationDTO = getClothForCreationDTO();
        //act
        wardrobeService.addCloth(clothForCreationDTO, user);
        //assert
        ClothForCreationDTO savedClothForCreation = modelMapper.map(wardrobeDataAccess.findAll().get(0), ClothForCreationDTO.class);
        assertEquals(savedClothForCreation, clothForCreationDTO);
    }

    private ClothForCreationDTO getClothForCreationDTO() {
        ClothForCreationDTO clothForCreationDTO = new ClothForCreationDTO();
        clothForCreationDTO.setImageName("test");
        clothForCreationDTO.setHasPattern(true);
        clothForCreationDTO.setClothType("JEANS");
        clothForCreationDTO.setColor("RED");
        clothForCreationDTO.setStyle("BOHO");
        return clothForCreationDTO;
    }


}