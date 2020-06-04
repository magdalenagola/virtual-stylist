package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository("wardrobeDataAccess")
interface WardrobeDataAccess extends PagingAndSortingRepository<Cloth,Integer> {
    //List<Cloth> findAllByUser_Id(int userId);
    Page<Cloth> findAllByUser_Id(int userId, Pageable paging);
    @Transactional
    void deleteClothByIdAndUserId(int clothId, int userId);
    Integer countAllByUserId(int userId);
}
