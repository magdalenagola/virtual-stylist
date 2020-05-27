package com.codecool.virtualstylist.wardrobe;

import javax.persistence.*;
import com.codecool.virtualstylist.user.User;

@Entity
class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String imagePath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClothType clothType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    private String code;

    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Style style;

    private String tag;

    @Enumerated(EnumType.STRING)
    private Size size;

    private boolean hasPattern;

    private String shopLink;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "clothes",fetch = FetchType.LAZY)
    private List<Stylization> stylizations;


}
