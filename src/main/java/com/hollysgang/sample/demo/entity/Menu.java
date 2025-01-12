package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "up_menu")
    private Menu upMenu;

    private Boolean itemYn;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorityMenu> authorityMenus;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuAPI> menuAPIs;
}
