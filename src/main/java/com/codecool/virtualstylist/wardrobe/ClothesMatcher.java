package com.codecool.virtualstylist.wardrobe;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public interface ClothesMatcher extends BiFunction<Cloth, Cloth, Boolean> {

    Map<ClothesProperties.Style, List<ClothesProperties.Style>> matchingStyles = ClothesProperties.createMatchingStylesMap();
    Map<ClothesProperties.Color, List<ClothesProperties.Color>> matchingColors = ClothesProperties.createMatchingColorsMap();

    static ClothesMatcher isPatternMatching(){
        return (clothToBeMatched, cloth)->{
            if(!clothToBeMatched.isHasPattern()) return true;
            else return !cloth.isHasPattern();
        };
    }

    static ClothesMatcher isStyleMatching() {
        return (clothToBeMatched, cloth) -> (matchingStyles.get(clothToBeMatched.getStyle())
                .stream()
                .anyMatch(style -> style.equals(cloth.getStyle())));
    }

    static ClothesMatcher isColorMatching(){
        return (clothToBeMatched, cloth)-> matchingColors.get(clothToBeMatched.getColor())
                .stream()
               .anyMatch(color -> color.equals(cloth.getColor()));
    }

    default ClothesMatcher and (ClothesMatcher other){
        return (clothToBeMatched, cloth) -> {
            Boolean result = this.apply(clothToBeMatched, cloth);
            return result ? other.apply(clothToBeMatched, cloth) : false;
        };
    }
}
