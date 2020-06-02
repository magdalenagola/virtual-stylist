package com.codecool.virtualstylist.wardrobe;


public class ClothForDisplayDTO {

    private Integer id;

    private String imageName;

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

    public ClothForDisplayDTO() {
    }

    public ClothForDisplayDTO(Integer id, String imageName, ClothesProperties.BodyPart bodyPart, ClothesProperties.ClothType clothType, ClothesProperties.Color color, String code, String brand, ClothesProperties.Style style, String tag, ClothesProperties.Size size, boolean hasPattern, String shopLink) {
        this.id = id;
        this.imageName = imageName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ClothesProperties.BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(ClothesProperties.BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public ClothesProperties.ClothType getClothType() {
        return clothType;
    }

    public void setClothType(ClothesProperties.ClothType clothType) {
        this.clothType = clothType;
    }

    public ClothesProperties.Color getColor() {
        return color;
    }

    public void setColor(ClothesProperties.Color color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ClothesProperties.Style getStyle() {
        return style;
    }

    public void setStyle(ClothesProperties.Style style) {
        this.style = style;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ClothesProperties.Size getSize() {
        return size;
    }

    public void setSize(ClothesProperties.Size size) {
        this.size = size;
    }

    public boolean isHasPattern() {
        return hasPattern;
    }

    public void setHasPattern(boolean hasPattern) {
        this.hasPattern = hasPattern;
    }

    public String getShopLink() {
        return shopLink;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }
}
