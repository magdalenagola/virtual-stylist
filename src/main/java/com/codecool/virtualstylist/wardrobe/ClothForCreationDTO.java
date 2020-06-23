package com.codecool.virtualstylist.wardrobe;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

class ClothForCreationDTO {

    @JsonProperty("imageName")
    private String imageName;

    @JsonProperty("clothType")
    private String clothType;

    @JsonProperty("color")
    private String color;

    @JsonProperty("code")
    private String code;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("style")
    private String style;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("size")
    private String size;

    @JsonProperty("hasPattern")
    private boolean hasPattern;

    @JsonProperty("shopLink")
    private String shopLink;


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getClothType() {
        return clothType;
    }

    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothForCreationDTO that = (ClothForCreationDTO) o;
        return hasPattern == that.hasPattern &&
                imageName.equals(that.imageName) &&
                clothType.equals(that.clothType) &&
                color.equals(that.color) &&
                style.equals(that.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName, clothType, color, style, hasPattern);
    }
}
