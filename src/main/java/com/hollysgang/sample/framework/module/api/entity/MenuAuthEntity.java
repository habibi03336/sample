package com.hollysgang.sample.framework.module.api.entity;

import com.hollysgang.sample.demo.entity.AuthorityMenu;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Entity
@Data
@Immutable
@Table(name = "Menu")
public class MenuAuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private Boolean itemYn;

    @Builder
    public MenuAuthEntity(Long id, String name, Boolean itemYn) {
        this.id = id;
        this.name = name;
        this.itemYn = itemYn;
    }

    public MenuAuthEntity() {

    }
}
