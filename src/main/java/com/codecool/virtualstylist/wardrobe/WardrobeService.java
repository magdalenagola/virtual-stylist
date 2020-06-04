package com.codecool.virtualstylist.wardrobe;


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

    Integer countUserClothes(int userId){
        return wardrobeDataAccess.countAllByUserId(userId);
    }

    void addCloth(ClothForCreationDTO clothForCreation){
        Cloth cloth = modelMapper.map(clothForCreation, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.findClothesBodyPart(cloth.getClothType());
        cloth.setBodyPart(bodyPart);
        //TODO set user
        wardrobeDataAccess.save(cloth);
    }

    void editCloth(ClothForUpdateDTO cloth){
        Optional<Cloth> clothToUpdate = wardrobeDataAccess.findById(cloth.getId());
        String imageName;
        if (clothToUpdate.isPresent())
            imageName = clothToUpdate.get().getImageName();
        else
            throw new IllegalArgumentException();
        Cloth newCloth = modelMapper.map(cloth, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.findClothesBodyPart(newCloth.getClothType());
        newCloth.setBodyPart(bodyPart);
        newCloth.setImageName(imageName);
        //TODO find user by newCloth id
        //newCloth.setUser(user);
        wardrobeDataAccess.save(newCloth);
    }

    void deleteCloth(Integer id, int userId){
        if(wardrobeDataAccess.findById(id).isPresent())
            wardrobeDataAccess.deleteClothByIdAndUserId(id, userId);
        else
            throw new IllegalArgumentException();//TODO send response code
    }

    ClothForDisplayDTO getClothById(int clothId){
        Optional<Cloth> clothPossibly =  wardrobeDataAccess.findById(clothId);
        if (!clothPossibly.isPresent()){
            throw new IllegalArgumentException(); //TODO send response code
        }
        Cloth cloth = clothPossibly.get();
        return modelMapper.map(cloth, ClothForDisplayDTO.class);

    }

    Page<ClothForDisplayWardrobeDTO> getAllClothesByUserId(int userId, Pageable pageable){
        List<Cloth> clothes;
        Page<Cloth> clothesPagedResult =  wardrobeDataAccess.findAllByUser_Id(userId, pageable);
        if(clothesPagedResult.hasContent()) {
            clothes = clothesPagedResult.getContent();
        } else {
            throw new IllegalArgumentException();
        }
        List<ClothForDisplayWardrobeDTO> clothesForDisplay = clothes.stream()
                .map(cloth -> modelMapper.map(cloth,ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(clothesForDisplay, pageable, wardrobeDataAccess.countAllByUserId(userId));
    }
}
