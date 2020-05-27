package com.codecool.virtualstylist.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class User {
    @Id
    private Integer id;
    private String email;
    private String password;
    private String name;
    private Gender gender;
    @Transient
    private PasswordEncoder passwordEncoder;
    @OneToMany(mappedBy = "user")
    private  List<Cloth> wardrobe;
    @OneToMany(mappedBy = "user")
    private List<Stylization> stylizations;
    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public User() {}

    public User(String email, String password, String name, Gender gender, PasswordEncoder passwordEncoder) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.passwordEncoder = passwordEncoder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Cloth> getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(List<Cloth> wardrobe) {
        this.wardrobe = wardrobe;
    }

    public List<Stylization> getStylizations() {
        return stylizations;
    }

    public void setStylizations(List<Stylization> stylizations) {
        this.stylizations = stylizations;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
