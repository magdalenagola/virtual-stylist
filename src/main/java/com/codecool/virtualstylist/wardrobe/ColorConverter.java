package com.codecool.virtualstylist.wardrobe;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<ClothesProperties.Color, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClothesProperties.Color attribute) {
        switch (attribute) {
            case RED:
                return 1;
            case BLACK:
                return 2;
            case BLUE:
                return 3;
            case BROWN:
                return 4;
            case GOLD:
                return 5;
            case GREEN:
                return 6;
            case GREY:
                return 7;
            case ORANGE:
                return 8;
            case PINK:
                return 9;
            case PURPLE:
                return 10;
            case SILVER:
                return 11;
            case WHITE:
                return 12;
            case YELLOW:
                return 13;
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public ClothesProperties.Color convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return ClothesProperties.Color.RED;
            case 2:
                return ClothesProperties.Color.BLACK;
            case 3:
                return ClothesProperties.Color.BLUE;
            case 4:
                return ClothesProperties.Color.BROWN;
            case 5:
                return ClothesProperties.Color.GOLD;
            case 6:
                return ClothesProperties.Color.GREEN;
            case 7:
                return ClothesProperties.Color.GREY;
            case 8:
                return ClothesProperties.Color.ORANGE;
            case 9:
                return ClothesProperties.Color.PINK;
            case 10:
                return ClothesProperties.Color.PURPLE;
            case 11:
                return ClothesProperties.Color.SILVER;
            case 12:
                return ClothesProperties.Color.WHITE;
            case 13:
                return ClothesProperties.Color.YELLOW;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}