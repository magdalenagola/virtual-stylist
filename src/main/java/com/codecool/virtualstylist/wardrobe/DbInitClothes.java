package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.user.UserDataAccess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInitClothes implements CommandLineRunner {

    private UserDataAccess userRepository;
    private WardrobeDataAccess wardrobeDataAccess;

    public DbInitClothes(@Qualifier("userPostgresAccess") UserDataAccess userRepository, @Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess) {
        this.userRepository = userRepository;
        this.wardrobeDataAccess = wardrobeDataAccess;
    }

    @Override
    public void run(String... args) {
        User adam = userRepository.findAll().get(0);
        Cloth cloth1 = new Cloth("/path.jpg", ClothesProperties.BodyPart.CHEST, ClothesProperties.ClothType.BLOUSE, ClothesProperties.Color.RED,"234","Zara", ClothesProperties.Style.BOHO,"#FavBlouse", ClothesProperties.Size.S,true,"zara.com",adam);
        Cloth cloth2 = new Cloth("/path2.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
        this.userRepository.save(adam);
        this.wardrobeDataAccess.save(cloth1);
        this.wardrobeDataAccess.save(cloth2);
    }
}
