package com.codecool.virtualstylist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionPostgresAccess")
interface SessionDataAccess extends JpaRepository<Session,Integer> {
}
