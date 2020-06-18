package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.wardrobe.ClothForStylizationCreationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class StylizationForCreationDTO {

    @JsonProperty("clothes")
    private List<ClothForStylizationCreationDTO> clothes;

    @JsonProperty("tag")
    private String tag;

    public List<ClothForStylizationCreationDTO> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothForStylizationCreationDTO> clothes) {
        this.clothes = clothes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
