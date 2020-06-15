package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

class ClothesProperties {

    static BodyPart findClothesBodyPart(ClothType clothType){
        Map<BodyPart, ClothType[]> bodyPartsForClothes = createBodyPartsForClothesMap();
        for (Map.Entry<BodyPart, ClothType[]> entry : bodyPartsForClothes.entrySet()) {
            if (Arrays.asList(entry.getValue()).contains(clothType)){
                return entry.getKey();
            }
        }
        throw new ResourceNotFoundException("BodyPart not found for given clothType!");
    }

    private static Map<BodyPart, ClothType[]> createBodyPartsForClothesMap() {
        Map<BodyPart, ClothType[]> bodyPartsForClothes = new HashMap<BodyPart, ClothType[]>();
        bodyPartsForClothes.put(BodyPart.CHEST, new ClothType[]{ClothType.BLOUSE, ClothType.TOP, ClothType.TSHIRT});
        bodyPartsForClothes.put(BodyPart.LEGS, new ClothType[]{ClothType.TROUSERS, ClothType.JEANS});
        bodyPartsForClothes.put(BodyPart.BODY, new ClothType[]{ClothType.DRESS});
        return bodyPartsForClothes;
    }

    static Map<String, List<String>> createOptionsMap() {
        Map<String,List<String>>options = new LinkedHashMap<>();
        options.put(ClothesProperties.ClothType.class.getSimpleName(), Arrays.stream(ClothesProperties.ClothType.values()).map(Enum::toString).collect(Collectors.toList()));
        options.put(ClothesProperties.Style.class.getSimpleName(), Arrays.stream(ClothesProperties.Style.values()).map(Enum::toString).collect(Collectors.toList()));
        options.put(ClothesProperties.Color.class.getSimpleName(), Arrays.stream(ClothesProperties.Color.values()).map(Enum::toString).collect(Collectors.toList()));
        options.put(ClothesProperties.Style.class.getSimpleName(), Arrays.stream(ClothesProperties.Style.values()).map(Enum::toString).collect(Collectors.toList()));
        options.put(ClothesProperties.Size.class.getSimpleName(), Arrays.stream(ClothesProperties.Size.values()).map(Enum::toString).collect(Collectors.toList()));
        return options;
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
