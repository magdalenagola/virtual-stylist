package com.codecool.virtualstylist.wardrobe;


import com.codecool.virtualstylist.exception.ResourceNotFoundException;
import com.codecool.virtualstylist.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class WardrobeService {
    private final WardrobeDataAccess wardrobeDataAccess;
    private final ModelMapper modelMapper;

    @Autowired
    WardrobeService(@Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess, ModelMapper modelMapper) {
        this.wardrobeDataAccess = wardrobeDataAccess;
        this.modelMapper = modelMapper;
    }

    void addCloth(ClothForCreationDTO clothForCreation, User user){
        Cloth cloth = modelMapper.map(clothForCreation, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.findClothesBodyPart(cloth.getClothType());
        cloth.setBodyPart(bodyPart);
        cloth.setUser(user);
        wardrobeDataAccess.save(cloth);
    }

    void editCloth(ClothForUpdateDTO cloth, User user) throws ResourceNotFoundException {
        Optional<Cloth> clothToUpdate = wardrobeDataAccess.findByIdAndUser_Id(cloth.getId(), user.getId());
        String imageName = clothToUpdate.orElseThrow(()->new ResourceNotFoundException("Cloth not found!")).getImageName();
        Cloth newCloth = modelMapper.map(cloth, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.findClothesBodyPart(newCloth.getClothType());
        newCloth.setBodyPart(bodyPart);
        newCloth.setImageName(imageName);
        newCloth.setUser(user);
        wardrobeDataAccess.save(newCloth);
    }

    void deleteCloth(int id, int userId) throws ResourceNotFoundException {
        if(wardrobeDataAccess.existsClothByIdAndUser_Id(id,userId))
            wardrobeDataAccess.deleteClothByIdAndUserId(id, userId);
        else
            throw new ResourceNotFoundException("Cloth not found for given user!");
    }

    ClothForDisplayDTO getClothById(int clothId,int userId) throws ResourceNotFoundException {
        Optional<Cloth> clothPossibly =  wardrobeDataAccess.findByIdAndUser_Id(clothId,userId);
        return modelMapper.map(clothPossibly
                .orElseThrow(()->new ResourceNotFoundException("Cloth not found!")),
                ClothForDisplayDTO.class);

    }

    Page<ClothForDisplayWardrobeDTO> getAllClothesByUserId(int userId, Pageable pageable) throws ResourceNotFoundException {
        Page<Cloth> clothesPagedResult =  wardrobeDataAccess.findAllByUser_Id(userId, pageable);
        Optional<List<Cloth>>clothes = Optional.of(clothesPagedResult.getContent());
        List<ClothForDisplayWardrobeDTO> clothesForDisplay = clothes
                .orElseThrow(()->new ResourceNotFoundException("Page not found!"))
                .stream()
                .map(cloth -> modelMapper.map(cloth,ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(clothesForDisplay, pageable, wardrobeDataAccess.countAllByUserId(userId));
    }

    List<ClothForDisplayWardrobeDTO> getAllClothesByBodyPart(ClothesProperties.BodyPart bodyPart, int userId){
        List<Cloth> clothes = wardrobeDataAccess.findAllByBodyPartAndUserId(bodyPart, userId)
                .orElseThrow(()-> new ResourceNotFoundException("Clothes not found for given body part and user"));
        return clothes.stream()
                .map(cloth-> modelMapper.map(cloth, ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
    }
}
