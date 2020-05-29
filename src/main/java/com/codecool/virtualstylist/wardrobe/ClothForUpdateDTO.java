package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;

class ClothForUpdateDTO {
    private Integer id;
    private String clothType;
    private String color;
    private String code;
    private String brand;
    private String style;
    private String tag;
    private String size;
    private boolean hasPattern;
    private String shopLink;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
