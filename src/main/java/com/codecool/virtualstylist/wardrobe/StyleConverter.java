package com.codecool.virtualstylist.wardrobe;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.Style.*;


@Converter(autoApply = true)
class StyleConverter implements AttributeConverter<ClothesProperties.Style, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClothesProperties.Style style) {
        switch (style){
            case CASUAL:
                return 1;
            case CLASSIC:
                return 2;
            case BUSINESS:
                return 3;
            case RETRO:
                return 4;
            case SPORTY:
                return 5;
            case ROMANTIC:
                return 6;
            case BOHO:
                return 7;
            case FOLK:
                return 8;
            case ALTERNATIVE:
                return 9;
            default:
                throw new IllegalArgumentException("Unknown " + style);
        }
    }

    @Override
    public ClothesProperties.Style convertToEntityAttribute(Integer dbData) {
        switch (dbData){
            case 1:
                return CASUAL;
            case 2:
                return CLASSIC;
            case 3:
                return BUSINESS;
            case 4:
                return RETRO;
            case 5:
                return SPORTY;
            case 6:
                return ROMANTIC;
            case 7:
                return BOHO;
            case 8:
                return FOLK;
            case 9:
                return ALTERNATIVE;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}
