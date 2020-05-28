package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;

import javax.persistence.*;

public class ClothDTO {
    private Integer id;

    private String imagePath;

    private BodyPart bodyPart;

    private ClothType clothType;

    private Color color;

    private String code;

    private String brand;

    private Style style;

    private String tag;

    private Size size;

    private boolean hasPattern;

    private String shopLink;

    public ClothDTO(String imagePath, BodyPart bodyPart, ClothType clothType, Color color, Style style, boolean hasPattern) {
        this.imagePath = imagePath;
        this.bodyPart = bodyPart;
        this.clothType = clothType;
        this.color = color;
        this.style = style;
        this.hasPattern = hasPattern;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setShopLink(String shopLink) {
        this.shopLink = shopLink;
    }
}

