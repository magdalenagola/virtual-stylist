package com.codecool.virtualstylist.wardrobe;


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
    private WardrobeDataAccess wardrobeDataAccess;
    private ModelMapper modelMapper;

    @Autowired
    public WardrobeService(@Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess, ModelMapper modelMapper) {
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

    void editCloth(ClothForUpdateDTO cloth, User user){
        Optional<Cloth> clothToUpdate = wardrobeDataAccess.findByIdAndUser_Id(cloth.getId(), user.getId());
        String imageName = clothToUpdate.orElseThrow(IllegalArgumentException::new).getImageName();
        Cloth newCloth = modelMapper.map(cloth, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.findClothesBodyPart(newCloth.getClothType());
        newCloth.setBodyPart(bodyPart);
        newCloth.setImageName(imageName);
        newCloth.setUser(user);
        wardrobeDataAccess.save(newCloth);
    }

    void deleteCloth(int id, int userId){
        if(wardrobeDataAccess.existsClothByIdAndUser_Id(id,userId))
            wardrobeDataAccess.deleteClothByIdAndUserId(id, userId);
        else
            throw new IllegalArgumentException();//TODO send response code
    }

    ClothForDisplayDTO getClothById(int clothId,int userId){
        Optional<Cloth> clothPossibly =  wardrobeDataAccess.findByIdAndUser_Id(clothId,userId);
        return modelMapper.map(clothPossibly
                .orElseThrow(IllegalArgumentException::new),
                ClothForDisplayDTO.class);

    }

    Page<ClothForDisplayWardrobeDTO> getAllClothesByUserId(int userId, Pageable pageable){
        Page<Cloth> clothesPagedResult =  wardrobeDataAccess.findAllByUser_Id(userId, pageable);
        Optional<List<Cloth>>clothes = Optional.of(clothesPagedResult.getContent());
        List<ClothForDisplayWardrobeDTO> clothesForDisplay = clothes
                .orElseThrow(IllegalArgumentException::new)
                .stream()
                .map(cloth -> modelMapper.map(cloth,ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(clothesForDisplay, pageable, wardrobeDataAccess.countAllByUserId(userId));
    }
}
