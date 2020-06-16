package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.wardrobe.Cloth;

import java.util.function.BiFunction;


public interface ClothesMatcher extends BiFunction<Cloth, Cloth, Boolean> {

    static ClothesMatcher isPatternMatching(){
        return (clothToBeMatched, cloth)->{
            if(!clothToBeMatched.isHasPattern()) return true;
            else if (!cloth.isHasPattern()) return true;
            return false;
        };
    }

    //TODO isStyleMatching
    //TODO isColorMatching

    default ClothesMatcher and (ClothesMatcher other){
        return (clothToBeMatched, cloth) -> {
            Boolean result = this.apply(clothToBeMatched, cloth);
            return result ? other.apply(clothToBeMatched, cloth) : false;
        };
    }
}
