package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.wardrobe.ClothForDisplayWardrobeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class StylizationForCreationDTO {

    @JsonProperty("clothes")
    private List<ClothForDisplayWardrobeDTO> clothes;

    @JsonProperty("tag")
    private String tag;

    public List<ClothForDisplayWardrobeDTO> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothForDisplayWardrobeDTO> clothes) {
        this.clothes = clothes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
