package com.codecool.virtualstylist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class DbInitUser implements CommandLineRunner {

    private UserDataAccess userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleDataAccess roleRepository;

    public DbInitUser(@Qualifier("userPostgresAccess") UserDataAccess userRepository, PasswordEncoder passwordEncoder, @Qualifier("userRolePostgresAccess") RoleDataAccess roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        User adam = new User("adam@adam.pl", passwordEncoder.encode("adam123"), "adam", Gender.MALE);
        this.userRepository.save(adam);

        Role userRoleInit = new Role(RoleOptions.ROLE_USER);
        Role adminRoleInit = new Role(RoleOptions.ROLE_ADMIN);
        roleRepository.save(userRoleInit);
        roleRepository.save(adminRoleInit);
    }
}
