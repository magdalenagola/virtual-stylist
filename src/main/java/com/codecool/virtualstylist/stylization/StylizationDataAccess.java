package com.codecool.virtualstylist.stylization;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylizationDataAccess extends PagingAndSortingRepository<Stylization, Integer> {
}
