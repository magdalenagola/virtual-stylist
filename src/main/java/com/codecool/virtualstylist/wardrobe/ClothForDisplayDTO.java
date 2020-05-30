package com.codecool.virtualstylist.wardrobe;


public class ClothForDisplayDTO {

    private Integer id;

    private String imagePath;

    private ClothesProperties.BodyPart bodyPart;

    private ClothesProperties.ClothType clothType;

    private ClothesProperties.Color color;

    private String code;

    private String brand;

    private ClothesProperties.Style style;

    private String tag;

    private ClothesProperties.Size size;

    private boolean hasPattern;

    private String shopLink;

    public ClothForDisplayDTO(Integer id, String imagePath, ClothesProperties.BodyPart bodyPart, ClothesProperties.ClothType clothType, ClothesProperties.Color color, String code, String brand, ClothesProperties.Style style, String tag, ClothesProperties.Size size, boolean hasPattern, String shopLink) {
        this.id = id;
        this.imagePath = imagePath;
        this.bodyPart = bodyPart;
        this.clothType = clothType;
        this.color = color;
        this.code = code;
        this.brand = brand;
        this.style = style;
        this.tag = tag;
        this.size = size;
        this.hasPattern = hasPattern;
        this.shopLink = shopLink;
    }
}
