package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountAuthority> accountAuthorities;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorityMenu> authorityMenus;

}
