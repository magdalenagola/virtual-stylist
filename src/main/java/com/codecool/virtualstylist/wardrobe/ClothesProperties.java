package com.codecool.virtualstylist.wardrobe;

import java.util.*;

class ClothesProperties {

    BodyPart findClothesBodyPart(ClothType clothType){
        Map<BodyPart, ClothType[]> bodyPartsForClothes = createBodyPartsForClothesMap();
        for (Map.Entry<BodyPart, ClothType[]> entry : bodyPartsForClothes.entrySet()) {
            if (Arrays.stream(entry.getValue()).anyMatch(clothType::equals)){
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException(); //TODO send response code
    }

    private Map<BodyPart, ClothType[]> createBodyPartsForClothesMap() {
        Map<BodyPart, ClothType[]> bodyPartsForClothes = new HashMap<BodyPart, ClothType[]>();
        bodyPartsForClothes.put(BodyPart.CHEST, new ClothType[]{ClothType.BLOUSE, ClothType.TOP, ClothType.TSHIRT});
        bodyPartsForClothes.put(BodyPart.LEGS, new ClothType[]{ClothType.TROUSERS, ClothType.JEANS});
        bodyPartsForClothes.put(BodyPart.BODY, new ClothType[]{ClothType.DRESS});
        return bodyPartsForClothes;
    }


    enum Color {
        WHITE,
        BLACK,
        RED,
        BLUE,
        YELLOW,
        BROWN,
        GREY,
        GREEN,
        ORANGE,
        PINK,
        GOLD,
        SILVER,
        PURPLE
    }

    enum Size {
        XS, S, M, L, XL, XXL, PLUS_SIZE
    }

    enum Style {
        CASUAL, CLASSIC, BUSINESS, RETRO, SPORTY, ROMANTIC, BOHO, FOLK, ALTERNATIVE
    }

    enum BodyPart {
        CHEST,
        LEGS,
        FEET,
        HEAD,
        HANDS,
        BODY
    }

    enum ClothType {
        BLOUSE,
        TSHIRT,
        TOP,
        TROUSERS,
        DRESS,
        JEANS
    }

}
