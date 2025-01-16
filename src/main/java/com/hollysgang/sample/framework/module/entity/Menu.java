package com.hollysgang.sample.framework.module.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Data
@Immutable
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private Boolean itemYn;

    @Builder
    public Menu(Long id, String name, Boolean itemYn) {
        this.id = id;
        this.name = name;
        this.itemYn = itemYn;
    }

    public Menu() {

    }
}
