package com.codecool.virtualstylist.wardrobe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wardrobeDataAccess")
interface WardrobeDataAccess extends JpaRepository<Cloth,Integer> {
    @Query(value = "SELECT * FROM CLOTH WHERE user_id = ?1", nativeQuery = true)
    List<Cloth> findAllByUserId(int userId);
}
