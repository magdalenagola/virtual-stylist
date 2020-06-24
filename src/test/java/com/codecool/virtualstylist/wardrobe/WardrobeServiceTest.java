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
import static org.junit.Assert.assertTrue;


@SpringBootTest
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
    void shouldSaveCloth() {
        //arrange
        ClothForCreationDTO clothForCreationDTO = getClothForCreationDTO();
        //act
        wardrobeService.addCloth(clothForCreationDTO, user);
        //assert
        ClothForCreationDTO savedClothForCreation = modelMapper.map(wardrobeDataAccess.findAll().get(0), ClothForCreationDTO.class);
        assertEquals(savedClothForCreation, clothForCreationDTO);
    }

    @Test
    void shouldEditBodyPartOfCloth(){
        //arrange
        Cloth clothToBeEdited = getCloth();
        wardrobeDataAccess.save(clothToBeEdited);
        ClothForUpdateDTO clothForUpdateDTO = getClothForUpdateDTO();
        //act
        wardrobeService.editCloth(clothForUpdateDTO, user);
        //assert
        assertEquals(wardrobeDataAccess.findAll().get(1).getBodyPart(), ClothesProperties.BodyPart.CHEST);
    }

    @Test
    void shouldSaveImageNameWhileEditingCloth(){
        //arrange
        Cloth clothToBeEdited = getCloth();
        wardrobeDataAccess.save(clothToBeEdited);
        ClothForUpdateDTO clothForUpdateDTO = getClothForUpdateDTO();
        //act
        wardrobeService.editCloth(clothForUpdateDTO, user);
        //assert
        assertEquals(wardrobeDataAccess.findAll().get(1).getImageName(), clothToBeEdited.getImageName());
    }

    @Test
    void shouldDeleteCloth(){
        //arrange
        Cloth clothToBeDeleted = getCloth();
        wardrobeDataAccess.save(clothToBeDeleted);
        //act
        wardrobeService.deleteCloth(clothToBeDeleted.getId(), user.getId());
        //assert
        assertTrue(wardrobeDataAccess.findAll().isEmpty());

    }

    private Cloth getCloth() {
        Cloth cloth = modelMapper.map(getClothForCreationDTO(), Cloth.class);
        cloth.setId(0);
        cloth.setUser(user);
        return cloth;
    }

    private ClothForUpdateDTO getClothForUpdateDTO() {
        ClothForUpdateDTO clothForUpdateDTO = new ClothForUpdateDTO();
        clothForUpdateDTO.setId(0);
        clothForUpdateDTO.setHasPattern(true);
        clothForUpdateDTO.setClothType("TSHIRT");
        clothForUpdateDTO.setColor("RED");
        clothForUpdateDTO.setStyle("BOHO");
        return clothForUpdateDTO;
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