package com.codecool.virtualstylist.user;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("userRolePostgresAccess")
public interface RoleDataAccess extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleOptions name);
}
