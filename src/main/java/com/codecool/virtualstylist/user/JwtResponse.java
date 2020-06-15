package com.codecool.virtualstylist.user;

import java.util.List;

class JwtResponse {

    private final String token;
    private final Integer id;
    private final String email;
    private final String name;
    private final List<String> roles;

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
