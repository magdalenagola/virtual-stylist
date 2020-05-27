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
    private BodyPart bodyPart;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothType clothType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    private String code;

    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    private String tag;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(nullable = false)
    private boolean hasPattern;

    private String shopLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "clothes",fetch = FetchType.LAZY)
    private List<Stylization> stylizations;

    public Cloth() {
    }

    public Cloth(String imagePath, BodyPart bodyPart, ClothType clothType, Color color, Style style, boolean hasPattern) {
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

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public ClothType getClothType() {
        return clothType;
    }

    public void setClothType(ClothType clothType) {
        this.clothType = clothType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
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
