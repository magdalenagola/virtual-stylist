package com.codecool.virtualstylist.wardrobe;

public class ClothForDisplayStylizationDTO {
    private Integer id;
    private String imageName;
    private ClothesProperties.BodyPart bodyPart;

    public Integer getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public ClothesProperties.BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setBodyPart(ClothesProperties.BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }
}
