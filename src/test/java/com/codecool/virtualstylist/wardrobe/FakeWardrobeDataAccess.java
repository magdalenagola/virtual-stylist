package com.codecool.virtualstylist.wardrobe;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FakeWardrobeDataAccess extends WardrobeDataAccess {

    List<Cloth> clothes = new ArrayList<>();

    @Override
    default <S extends Cloth> S save(S s){
        clothes.add(s);
        return s;
    }

    @Override
    default List<Cloth> findAll(){
        return clothes;
    }
}
