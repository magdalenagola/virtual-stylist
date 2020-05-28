package com.codecool.virtualstylist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userPostgresAccess")
public interface UserDataAccess extends JpaRepository<User,Integer> {
    public User findUserByEmail(String email);
}
