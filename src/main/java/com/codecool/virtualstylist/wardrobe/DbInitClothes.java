//package com.codecool.virtualstylist.wardrobe;
//
//import com.codecool.virtualstylist.user.User;
//import com.codecool.virtualstylist.user.UserDataAccess;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DbInitClothes implements CommandLineRunner {
//
//    private final UserDataAccess userRepository;
//    private final WardrobeDataAccess wardrobeDataAccess;
//
//    public DbInitClothes(@Qualifier("userPostgresAccess") UserDataAccess userRepository, @Qualifier("wardrobeDataAccess") WardrobeDataAccess wardrobeDataAccess) {
//        this.userRepository = userRepository;
//        this.wardrobeDataAccess = wardrobeDataAccess;
//    }
//
//    @Override
//    public void run(String... args) {
//        User adam = userRepository.findAll().get(0);
//        Cloth cloth1 = new Cloth("/path.jpg", ClothesProperties.BodyPart.CHEST, ClothesProperties.ClothType.BLOUSE, ClothesProperties.Color.RED,"234","Zara", ClothesProperties.Style.BOHO,"#FavBlouse", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth2 = new Cloth("/path2.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth3 = new Cloth("/path3.jpg", ClothesProperties.BodyPart.BODY, ClothesProperties.ClothType.DRESS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth4 = new Cloth("/path4.jpg", ClothesProperties.BodyPart.CHEST, ClothesProperties.ClothType.TOP, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth5 = new Cloth("/path5.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.TROUSERS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth6 = new Cloth("/path6.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth7 = new Cloth("/path7.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth8 = new Cloth("/path8.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth9 = new Cloth("/path9.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth10 = new Cloth("/path10.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        Cloth cloth11 = new Cloth("/path11.jpg", ClothesProperties.BodyPart.LEGS, ClothesProperties.ClothType.JEANS, ClothesProperties.Color.RED,"543","Zara", ClothesProperties.Style.BOHO,"#FavJeans", ClothesProperties.Size.S,true,"zara.com",adam);
//        this.userRepository.save(adam);
//        this.wardrobeDataAccess.save(cloth1);
//        this.wardrobeDataAccess.save(cloth2);
//        this.wardrobeDataAccess.save(cloth3);
//        this.wardrobeDataAccess.save(cloth4);
//        this.wardrobeDataAccess.save(cloth5);
//        this.wardrobeDataAccess.save(cloth6);
//        this.wardrobeDataAccess.save(cloth7);
//        this.wardrobeDataAccess.save(cloth8);
//        this.wardrobeDataAccess.save(cloth9);
//        this.wardrobeDataAccess.save(cloth10);
//        this.wardrobeDataAccess.save(cloth11);
//    }
//}
