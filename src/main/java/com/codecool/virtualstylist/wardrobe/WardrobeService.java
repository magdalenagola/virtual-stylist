package com.codecool.virtualstylist.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class WardrobeService {
    private WardrobeDataAccess wardrobeDataAccess;

    @Autowired
    public WardrobeService(@Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess) {
        this.wardrobeDataAccess = wardrobeDataAccess;
    }

    void deleteCloth(Cloth cloth){
        wardrobeDataAccess.delete(cloth);
    }
    Optional<Cloth> getClothById(int clothId){
        return wardrobeDataAccess.findById(clothId);
    }

    List<Cloth> getAllClothesByUserId(int userId){
        return wardrobeDataAccess.findAllByUser_Id(userId);
    }
}
