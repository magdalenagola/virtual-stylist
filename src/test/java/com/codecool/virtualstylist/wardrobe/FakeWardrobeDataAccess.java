package com.codecool.virtualstylist.wardrobe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    default Optional<Cloth> findByIdAndUser_Id(int id, int user_id){
        return clothes.stream()
        .filter(cloth -> cloth.getId() == id && cloth.getUser().getId() == user_id)
        .findFirst();
    }

    @Override
    default boolean existsClothByIdAndUser_Id(int id, int userId){
        if(findByIdAndUser_Id(id, userId).isPresent()) return true;
        return false;
    };

    @Override
    default void deleteClothByIdAndUserId(int clothId, int userId){
        clothes.removeIf(cloth -> cloth.getId() == clothId && cloth.getUser().getId() == userId);
    };

    @Override
    default Page<Cloth> findAllByUser_Id(int userId, Pageable paging){
        List<Cloth> userClothes = clothes.stream()
                .filter(cloth -> cloth.getUser().getId() == userId)
                .collect(Collectors.toList());
        return new PageImpl<>(userClothes,paging,userClothes.size());
    };
}
