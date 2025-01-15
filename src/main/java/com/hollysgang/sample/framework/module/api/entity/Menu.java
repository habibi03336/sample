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

    @Builder
    public Menu(Long id, String name, Menu upMenu, Boolean itemYn, Set<AuthorityMenu> authorityMenus) {
        this.id = id;
        this.name = name;
        this.upMenu = upMenu;
        this.itemYn = itemYn;
    }

    public Menu() {

    }
}
