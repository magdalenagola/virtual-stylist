package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.wardrobe.Cloth;
import com.codecool.virtualstylist.wardrobe.ClothesProperties;
import com.codecool.virtualstylist.wardrobe.WardrobeDataAccess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    private UserDataAccess userRepository;
    private WardrobeDataAccess wardrobeDataAccess;
    private PasswordEncoder passwordEncoder;

    public DbInit(@Qualifier("userPostgresAccess") UserDataAccess userRepository, PasswordEncoder passwordEncoder, @Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wardrobeDataAccess = wardrobeDataAccess;
    }

    @Override
    public void run(String... args) {

        User adam = new User("adam@adam.pl", passwordEncoder.encode("adam123"), "adam", Gender.MALE);
        Cloth cloth1 = new Cloth("xd", ClothesProperties.BodyPart.CHEST, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"xd","xd", ClothesProperties.Style.BOHO,"xd", ClothesProperties.Size.S,true,"xd",adam);
        Cloth cloth2 = new Cloth("dx", ClothesProperties.BodyPart.CHEST, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"xd","xd", ClothesProperties.Style.BOHO,"xd", ClothesProperties.Size.S,true,"xd",adam);
        this.userRepository.save(adam);
        this.wardrobeDataAccess.save(cloth1);
        this.wardrobeDataAccess.save(cloth2);
    }
}
