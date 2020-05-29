package com.codecool.virtualstylist.wardrobe;


import com.codecool.virtualstylist.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    void addCloth(Cloth cloth){
        wardrobeDataAccess.save(cloth);
    }

    void editCloth(ClothForUpdateDTO clothToUpdate){
        Cloth cloth = modelMapper.map(clothToUpdate, Cloth.class);
        wardrobeDataAccess.save(cloth);
    }

    void deleteCloth(Integer id, User user){
        wardrobeDataAccess.deleteClothByIdAndUser(id, user);
    }

    Optional<Cloth> getClothById(int clothId){
        return wardrobeDataAccess.findById(clothId);
    }

    List<ClothForDisplayWardrobeDTO> getAllClothesByUserId(int userId){
        List<Cloth> clothes =  wardrobeDataAccess.findAllByUser_Id(userId);
        return clothes.stream()
                .map(cloth -> modelMapper.map(cloth,ClothForDisplayWardrobeDTO.class))
                .collect(Collectors.toList());
    }
}
