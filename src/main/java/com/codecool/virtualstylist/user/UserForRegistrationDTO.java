package com.codecool.virtualstylist.user;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;


public class UserForRegistrationDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("roles")
    private Set<String> roles;

    public UserForRegistrationDTO() {
    }

    public UserForRegistrationDTO(String email, String password, String name, String gender, Set<String> roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
