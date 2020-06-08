package com.codecool.virtualstylist.user;

import com.codecool.virtualstylist.stylization.Stylization;
import com.codecool.virtualstylist.wardrobe.Cloth;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private  List<Cloth> wardrobe;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Stylization> stylizations;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Session> sessions;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id",referencedColumnName = "id") })
    private Set<Role> roles;

    public User() {}

    User(String email, String password, String name, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
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

    public void setEmail(String email) { this.email = email; }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
