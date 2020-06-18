package com.codecool.virtualstylist.user;

import java.util.Optional;

class UserForUpdateDTO {
    private String name;
    private Gender gender;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password).filter(s -> !s.isEmpty());
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
