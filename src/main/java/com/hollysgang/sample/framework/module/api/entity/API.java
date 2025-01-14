package com.hollysgang.sample.framework.module.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Entity
@Data
@Table(name = "API")
public class API {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String path;

    @Column(length = 10)
    private String method;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "api")
    private Set<MenuAPI> menuApis;

    @Builder
    public API(Long id, String path, String method, String description){
        this.id = id;
        this.path = path;
        this.method = method;
        this.description = description;
    }

    public API() {}
}