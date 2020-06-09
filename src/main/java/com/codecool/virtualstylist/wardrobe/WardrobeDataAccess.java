package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository("wardrobeDataAccess")
interface WardrobeDataAccess extends PagingAndSortingRepository<Cloth,Integer> {
    Page<Cloth> findAllByUser_Id(int userId, Pageable paging);
    @Transactional
    void deleteClothByIdAndUserId(int clothId, int userId);
    Integer countAllByUserId(int userId);
    Optional<Cloth> findByIdAndUser_Id(int id, int user_id);
    boolean existsClothByIdAndUser_Id(int id, int userId);
}
