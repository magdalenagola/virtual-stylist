package com.codecool.virtualstylist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userPostgresAccess")
public interface UserDataAccess extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
