package com.codecool.virtualstylist.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userPostgresAccess")
interface UserDataAccess extends PagingAndSortingRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findUserById(int id);
    Page<User> findAllByRolesIs(Role role, Pageable paging);
    Integer countAllByRolesIs(Role role);
}
