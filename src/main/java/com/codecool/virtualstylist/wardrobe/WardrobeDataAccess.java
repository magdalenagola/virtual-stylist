package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository("wardrobeDataAccess")
interface WardrobeDataAccess extends PagingAndSortingRepository<Cloth,Integer> {
    //List<Cloth> findAllByUser_Id(int userId);
    Page<Cloth> findAllByUser_Id(int userId, Pageable paging);
    void deleteClothByIdAndUser(int clothId, User user);
    Integer countAllByUserId(int userId);
}
