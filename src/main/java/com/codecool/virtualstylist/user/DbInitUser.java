//package com.codecool.virtualstylist.user;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//class DbInitUser implements CommandLineRunner {
//
//    private final UserDataAccess userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RoleDataAccess roleRepository;
//
//    @Autowired
//    public DbInitUser(@Qualifier("userPostgresAccess") UserDataAccess userRepository, PasswordEncoder passwordEncoder, @Qualifier("userRolePostgresAccess") RoleDataAccess roleRepository){
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//
//        User adam = new User("adam@adam.pl", passwordEncoder.encode("adam123"), "adam", Gender.MALE);
//        Role userRoleInit = new Role(RoleOptions.ROLE_USER);
//        Role adminRoleInit = new Role(RoleOptions.ROLE_ADMIN);
//        roleRepository.saveAndFlush(userRoleInit);
//        roleRepository.saveAndFlush(adminRoleInit);
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleRepository.findByName(RoleOptions.ROLE_USER).get());
//        adam.setRoles(roles);
//        this.userRepository.save(adam);
//    }
//}
