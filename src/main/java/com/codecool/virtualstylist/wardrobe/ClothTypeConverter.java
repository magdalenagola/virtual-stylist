package com.codecool.virtualstylist.wardrobe;

import javax.persistence.AttributeConverter;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.ClothType.*;


public class ClothTypeConverter implements AttributeConverter<ClothesProperties.ClothType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClothesProperties.ClothType clothType) {
        switch (clothType){
            case BLOUSE:
                return 1;
            case TSHIRT:
                return 2;
            case TOP:
                return 3;
            case TROUSERS:
                return 4;
            case DRESS:
                return 5;
            case JEANS:
                return 6;
            default:
                throw new IllegalArgumentException("Unknown " + clothType);
        }
    }

    @Override
    public ClothesProperties.ClothType convertToEntityAttribute(Integer dbData) {
        switch (dbData){
            case 1:
                return BLOUSE;
            case 2:
                return TSHIRT;
            case 3:
                return TOP;
            case 4:
                return TROUSERS;
            case 5:
                return DRESS;
            case 6:
                return JEANS;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}
