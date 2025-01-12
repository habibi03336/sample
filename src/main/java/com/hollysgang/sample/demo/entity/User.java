package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAuthority> userAuthorities;

    // Getters and Setters
}
