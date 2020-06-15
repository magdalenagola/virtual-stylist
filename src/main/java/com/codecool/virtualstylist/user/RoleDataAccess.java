package com.codecool.virtualstylist.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("userRolePostgresAccess")
interface RoleDataAccess extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleOptions name);
}
