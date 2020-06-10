package com.codecool.virtualstylist.user;

import java.util.List;

public class JwtResponse {

    private String token;
    private Integer id;
    private String email;
    private String name;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String email, String name, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }
}
