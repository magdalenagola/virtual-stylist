package com.codecool.virtualstylist.wardrobe;

import com.fasterxml.jackson.annotation.JsonProperty;

class ClothForUpdateDTO {
    @JsonProperty("id")
    private Integer id;
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

    public ClothForUpdateDTO(){}
    public ClothForUpdateDTO(Integer id, String clothType, String color, String code, String brand, String style, String tag, String size, boolean hasPattern, String shopLink) {
        this.id = id;
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
}
