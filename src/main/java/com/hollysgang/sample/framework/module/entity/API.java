package com.hollysgang.sample.framework.module.entity;

import jakarta.persistence.*;
import lombok.Builder;


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

    @Builder
    public API(String path, String method, String description) {
        this.path = path;
        this.method = method;
        this.description = description;
    }

    public API() {

    }
}
