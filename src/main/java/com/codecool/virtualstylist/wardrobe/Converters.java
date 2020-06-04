package com.codecool.virtualstylist.wardrobe;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.codecool.virtualstylist.wardrobe.ClothesProperties.BodyPart.*;
import static com.codecool.virtualstylist.wardrobe.ClothesProperties.ClothType.*;
import static com.codecool.virtualstylist.wardrobe.ClothesProperties.ClothType.JEANS;
import static com.codecool.virtualstylist.wardrobe.ClothesProperties.Style.*;

class Converters {

    @Converter(autoApply = true)
    class ColorConverter implements AttributeConverter<ClothesProperties.Color, Integer> {

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



    @Converter(autoApply = true)
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



    @Converter(autoApply = true)
    class ClothTypeConverter implements AttributeConverter<ClothesProperties.ClothType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ClothesProperties.ClothType clothType) {
            switch (clothType) {
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
            switch (dbData) {
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



    @Converter(autoApply = true)
    class SizeConverter implements AttributeConverter<ClothesProperties.Size, Integer> {

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
}

