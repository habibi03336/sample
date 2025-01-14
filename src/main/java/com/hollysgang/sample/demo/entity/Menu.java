package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "up_menu")
    private Menu upMenu;

    @Column
    private Boolean itemYn;

    @OneToMany(mappedBy = "menu")
    private Set<AuthorityMenu> authorityMenus;
}
