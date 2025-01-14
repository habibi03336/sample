package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountAuthority> userAuthorities;

    // Getters and Setters
}
