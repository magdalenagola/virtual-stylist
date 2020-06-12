package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.wardrobe.Cloth;
import com.codecool.virtualstylist.wardrobe.ClothForDisplayWardrobeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StylizationForCreationDTO {

    @JsonProperty("clothes")
    private List<ClothForDisplayWardrobeDTO> clothes;

    @JsonProperty("tag")
    private String tag;
}
