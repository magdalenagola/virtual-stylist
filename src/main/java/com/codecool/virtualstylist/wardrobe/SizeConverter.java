package com.codecool.virtualstylist.wardrobe;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SizeConverter implements AttributeConverter<ClothesProperties.Size, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClothesProperties.Size attribute) {
        switch (attribute) {
            case S:
                return 1;
            case M:
                return 2;
            case L:
                return 3;
            case XS:
                return 4;
            case XL:
                return 5;
            case XXL:
                return 6;
            case PLUS_SIZE:
                return 7;
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public ClothesProperties.Size convertToEntityAttribute(Integer dbData) {
        switch (dbData) {
            case 1:
                return ClothesProperties.Size.S;
            case 2:
                return ClothesProperties.Size.M;
            case 3:
                return ClothesProperties.Size.L;
            case 4:
                return ClothesProperties.Size.XS;
            case 5:
                return ClothesProperties.Size.XL;
            case 6:
                return ClothesProperties.Size.XXL;
            case 7:
                return ClothesProperties.Size.PLUS_SIZE;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}