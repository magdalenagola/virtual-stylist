package com.codecool.virtualstylist.stylization;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FakeStylizationDataAccess extends StylizationDataAccess{
    List<Stylization> stylizations = new ArrayList<>();

    @Override
    default <S extends Stylization> S save(S s){
        stylizations.add(s);
        return s;
    }

    @Override
    default List<Stylization> findAll( ){
        return stylizations;
    }
}
