package com.codecool.virtualstylist.stylization;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StylizationDataAccess extends PagingAndSortingRepository<Stylization, Integer> {
    Page<Stylization> findAllByUser_Id(Pageable pageable, int userId);
    int countAllByUser_Id(int userId);
}
