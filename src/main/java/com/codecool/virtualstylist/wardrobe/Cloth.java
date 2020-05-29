package com.codecool.virtualstylist.wardrobe;

import javax.persistence.*;
import com.codecool.virtualstylist.user.User;

@Entity
class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothesProperties.BodyPart bodyPart;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothesProperties.ClothType clothType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothesProperties.Color color;

    private String code;

    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothesProperties.Style style;

    private String tag;

    @Enumerated(EnumType.STRING)
    private ClothesProperties.Size size;

    @Column(nullable = false)
    private boolean hasPattern;

    private String shopLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "clothes", fetch = FetchType.LAZY)
    private List<Stylization> stylizations;

    public Cloth() {
    }

    public Cloth(String imagePath, ClothesProperties.BodyPart bodyPart, ClothesProperties.ClothType clothType, ClothesProperties.Color color, ClothesProperties.Style style, boolean hasPattern) {
        this.imagePath = imagePath;
        this.bodyPart = bodyPart;
        this.clothType = clothType;
        this.color = color;
        this.style = style;
        this.hasPattern = hasPattern;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Stylization> getStylizations() {
        return stylizations;
    }

    public void setStylizations(List<Stylization> stylizations) {
        this.stylizations = stylizations;
    }
}


