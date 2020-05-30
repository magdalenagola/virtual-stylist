package com.codecool.virtualstylist.wardrobe;


import com.codecool.virtualstylist.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.StreamingHttpOutputMessage;
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

    void addCloth(ClothForCreationDTO clothForCreation){
        Cloth cloth = modelMapper.map(clothForCreation, Cloth.class);
        ClothesProperties.BodyPart bodyPart = ClothesProperties.BodyPart.LEGS; //TODO find body part basing on cloth type
        cloth.setBodyPart(bodyPart);
        //TODO set user
        wardrobeDataAccess.save(cloth);
    }

    void editCloth(ClothForUpdateDTO clothToUpdate){
        Cloth cloth = modelMapper.map(clothToUpdate, Cloth.class);
        wardrobeDataAccess.save(cloth);
    }

    void deleteCloth(Integer id, User user){
        wardrobeDataAccess.deleteClothByIdAndUser(id, user);
    }

    ClothForDisplayDTO getClothById(int clothId){
        Optional<Cloth> clothPossibly =  wardrobeDataAccess.findById(clothId);
        if (!clothPossibly.isPresent()){
            throw new IllegalArgumentException(); //TODO send response code
        }
        Cloth cloth = clothPossibly.get();
        return modelMapper.map(cloth, ClothForDisplayDTO.class);

    }

    List<ClothForDisplayWardrobeDTO> getAllClothesByUserId(int userId){
        List<Cloth> clothes =  wardrobeDataAccess.findAllByUser_Id(userId);
        return clothes.stream()
                .map(cloth -> modelMapper.map(cloth,ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
    }
}
