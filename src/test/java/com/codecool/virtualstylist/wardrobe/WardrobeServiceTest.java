package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.*;
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
        clothForUpdateDTO.setClothType("TSHIRT");
        //act
        wardrobeService.editCloth(clothForUpdateDTO, user);
        //assert
        assertEquals(wardrobeDataAccess.findAll().get(1).getBodyPart(), BodyPart.CHEST);
    }

    @Test
    void shouldSaveImageNameWhileEditingCloth(){
        //arrange
        Cloth clothToBeEdited = getCloth();
        wardrobeDataAccess.save(clothToBeEdited);
        //act
        wardrobeService.editCloth(getClothForUpdateDTO(), user);
        //assert
        assertEquals(wardrobeDataAccess.findAll().get(1).getImageName(), clothToBeEdited.getImageName());
    }

    @Test
    void shouldDeleteCloth(){
        //arrange
        wardrobeDataAccess.save(getCloth());
        //act
        wardrobeService.deleteCloth(getCloth().getId(), user.getId());
        //assert
        assertTrue(wardrobeDataAccess.findAll().isEmpty());

    }

    private Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setId(0);
        cloth.setUser(user);
        cloth.setImageName("test");
        cloth.setHasPattern(true);
        cloth.setClothType(ClothType.JEANS);
        cloth.setColor(Color.RED);
        cloth.setStyle(Style.BOHO);
        return cloth;
    }

    private ClothForUpdateDTO getClothForUpdateDTO() {
        ClothForUpdateDTO clothForUpdateDTO = modelMapper.map(getCloth(), ClothForUpdateDTO.class);
        return clothForUpdateDTO;
    }


    private ClothForCreationDTO getClothForCreationDTO() {
        ClothForCreationDTO clothForCreationDTO = modelMapper.map(getCloth(), ClothForCreationDTO.class);
        return clothForCreationDTO;
    }
}