package com.codecool.virtualstylist.wardrobe;

class ClothForDisplayWardrobeDTO {
    private Integer id;
    private String imageName;

    public ClothForDisplayWardrobeDTO(){}

    public ClothForDisplayWardrobeDTO(Integer id, String imageName) {
        this.id = id;
        this.imageName = imageName;
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
}
