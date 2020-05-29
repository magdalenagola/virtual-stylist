package com.codecool.virtualstylist.wardrobe;

import com.codecool.virtualstylist.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wardrobeDataAccess")
interface WardrobeDataAccess extends JpaRepository<Cloth,Integer> {
    List<Cloth> findAllByUser_Id(int userId);
    void deleteClothByIdAndUser(int clothId, User user);
}
