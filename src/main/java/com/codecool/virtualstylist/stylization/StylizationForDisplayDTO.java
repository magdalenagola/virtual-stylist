package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.wardrobe.ClothForDisplayStylizationDTO;

import java.util.List;


class StylizationForDisplayDTO {

    private Integer id;
    private List<ClothForDisplayStylizationDTO> clothesForDisplayStylization;

    StylizationForDisplayDTO(){}

    StylizationForDisplayDTO(Integer id, List<ClothForDisplayStylizationDTO> clothesForDisplayStylization) {
        this.id = id;
        this.clothesForDisplayStylization = clothesForDisplayStylization;
    }


    public Integer getId() {
        return id;
    }

    public List<ClothForDisplayStylizationDTO> getClothesForDisplayStylization() {
        return clothesForDisplayStylization;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClothesForDisplayStylization(List<ClothForDisplayStylizationDTO> clothesForDisplayStylization) {
        this.clothesForDisplayStylization = clothesForDisplayStylization;
    }
}
