package com.codecool.virtualstylist.wardrobe;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ClothesProperties {


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
