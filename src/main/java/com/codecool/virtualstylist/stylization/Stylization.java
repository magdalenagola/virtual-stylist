package com.codecool.virtualstylist.stylization;

import com.codecool.virtualstylist.user.User;
import com.codecool.virtualstylist.wardrobe.Cloth;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stylization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "clothes_stylization",
            joinColumns = { @JoinColumn(name = "stylization_id",referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "cloth_id",referencedColumnName = "id") })
    private List<Cloth> clothes;

    private String tag;

    @ManyToOne
    private User user;

    public Stylization() {
    }

    public Stylization(List<Cloth> clothes, String tag, User user) {
        this.clothes = clothes;
        this.tag = tag;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cloth> getClothes() {
        return clothes;
    }

    public void setClothes(List<Cloth> clothes) {
        this.clothes = clothes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
