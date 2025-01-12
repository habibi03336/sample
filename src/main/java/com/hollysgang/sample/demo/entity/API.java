package com.hollysgang.sample.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "API")
public class API {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String method;

    private String description;

    @OneToMany(mappedBy = "api", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuAPI> menuAPIs;
}
