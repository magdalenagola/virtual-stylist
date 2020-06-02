package com.codecool.virtualstylist.wardrobe;

import javax.persistence.AttributeConverter;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.BodyPart.*;


class BodyPartConverter implements AttributeConverter<ClothesProperties.BodyPart, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClothesProperties.BodyPart bodyPart) {
        switch (bodyPart){
            case CHEST:
                return 1;
            case LEGS:
                return 2;
            case BODY:
                return 3;
            default:
                throw new IllegalArgumentException("Unknown " + bodyPart);
        }
    }

    @Override
    public ClothesProperties.BodyPart convertToEntityAttribute(Integer dbData) {
        switch (dbData){
            case 1:
                return CHEST;
            case 2:
                return LEGS;
            case 3:
                return BODY;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}