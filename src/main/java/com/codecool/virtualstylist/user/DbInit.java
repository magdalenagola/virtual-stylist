package com.codecool.virtualstylist.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    private UserDataAccess userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(@Qualifier("userPostgresAccess") UserDataAccess userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        User adam = new User("adam@adam.pl", passwordEncoder.encode("adam123"), "adam", Gender.MALE);

        this.userRepository.save(adam);
    }
}
