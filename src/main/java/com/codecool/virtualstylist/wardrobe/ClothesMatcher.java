package com.codecool.virtualstylist.wardrobe;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public interface ClothesMatcher extends BiFunction<Cloth, Cloth, Boolean> {

    Map<ClothesProperties.Style, List<ClothesProperties.Style>> matchingStyles = ClothesProperties.createMatchingStylesMap();

    static ClothesMatcher isPatternMatching(){
        return (clothToBeMatched, cloth)->{
            if(!clothToBeMatched.isHasPattern()) return true;
            else if (!cloth.isHasPattern()) return true;
            return false;
        };
    }

    static ClothesMatcher isStyleMatching(){
        return (clothToBeMatched, cloth)->{
            if(matchingStyles.get(clothToBeMatched.getStyle()).contains(cloth.getStyle())) return true;
            return false;
        };
    }

    default ClothesMatcher and (ClothesMatcher other){
        return (clothToBeMatched, cloth) -> {
            Boolean result = this.apply(clothToBeMatched, cloth);
            return result ? other.apply(clothToBeMatched, cloth) : false;
        };
    }
}
