package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.exception.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.Style.*;
import static com.codecool.virtualstylist.wardrobe.ClothesProperties.Color.*;

public class ClothesProperties {

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

    static Map<Style, List<Style>> createMatchingStylesMap(){
        Map<Style, List<Style>> matchingStyles = new HashMap<>();
        matchingStyles.put(CASUAL, Arrays.asList(CASUAL, CLASSIC, SPORTY, ROMANTIC, BOHO, FOLK, ALTERNATIVE, RETRO));
        matchingStyles.put(CLASSIC,(Arrays.asList(CLASSIC, BUSINESS, CASUAL)));
        matchingStyles.put(BUSINESS,(Arrays.asList(BUSINESS, CLASSIC)));
        matchingStyles.put(RETRO,(Arrays.asList(RETRO, FOLK, CASUAL)));
        matchingStyles.put(SPORTY,(Arrays.asList(SPORTY, CASUAL)));
        matchingStyles.put(ROMANTIC,(Arrays.asList(ROMANTIC, CLASSIC, CASUAL, BOHO)));
        matchingStyles.put(BOHO,(Arrays.asList(ROMANTIC, CASUAL, BOHO)));
        matchingStyles.put(FOLK,(Arrays.asList(FOLK, CLASSIC, CASUAL)));
        matchingStyles.put(ALTERNATIVE,(Arrays.asList(ALTERNATIVE, CLASSIC)));
        return matchingStyles;
    }

    public static BodyPart getMatchingBodyPart(BodyPart bodyPart) {
        if (bodyPart.equals(BodyPart.CHEST)) return BodyPart.LEGS;
        else if (bodyPart.equals(BodyPart.LEGS)) return BodyPart.CHEST;
        else throw new ResourceNotFoundException("No matching body part for the cloth");
    }
    public static Map<Color, List<Color>> generateMatchingColors() {
        Map<Color, List<Color>> matchingColors = new HashMap<>();
        matchingColors.put(WHITE, Arrays.asList(Color.values()));
        matchingColors.put(GREY, Arrays.asList(Color.values()));
        matchingColors.put(BLACK, Arrays.asList(WHITE, BLACK, BLUE, YELLOW, BROWN, GREY, GREEN, ORANGE, PINK, GOLD, SILVER, PURPLE));
        matchingColors.put(PURPLE, Arrays.asList(PURPLE, WHITE, BLACK, BLUE, BROWN, GREY, PINK, SILVER));
        matchingColors.put(GOLD, Arrays.asList(GOLD, WHITE, BLACK, RED, YELLOW, BROWN, GREY, GREEN, ORANGE));
        matchingColors.put(SILVER, Arrays.asList(SILVER, WHITE, BLACK, BLUE, GREY, PINK, PURPLE));
        matchingColors.put(PINK, Arrays.asList(PINK, WHITE, BLACK, BLUE, BROWN, GREY, SILVER, PURPLE));
        matchingColors.put(ORANGE, Arrays.asList(ORANGE, WHITE, BLACK, BLUE, BROWN, GREY, SILVER, PURPLE));
        matchingColors.put(GREEN, Arrays.asList(GREEN, WHITE, BLACK, YELLOW, BROWN, GREY,  GOLD));
        matchingColors.put(BROWN, Arrays.asList(BROWN, YELLOW, GREY, GREEN, ORANGE, GOLD, PURPLE, WHITE, BLACK, PINK));
        matchingColors.put(YELLOW, Arrays.asList(YELLOW, WHITE, BLACK, RED, BROWN, GREY, GREEN, ORANGE, GOLD, PURPLE));
        matchingColors.put(BLUE, Arrays.asList(BLUE, WHITE, RED, BLACK, GREY, PINK, SILVER, PURPLE));
        matchingColors.put(RED, Arrays.asList(RED, WHITE,  YELLOW, BROWN, GREY,BLUE, GOLD, SILVER));
        return matchingColors;
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

    public enum BodyPart {
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
