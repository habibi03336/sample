package com.hollysgang.sample.framework.module.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "APIPermission")
public class APIPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_id")
    private API api;

    @Column(length = 40)
    private String authorizedEntity;
}