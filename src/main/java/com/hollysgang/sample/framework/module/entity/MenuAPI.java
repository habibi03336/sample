package com.hollysgang.sample.framework.module.entity;

import com.hollysgang.sample.demo.entity.Menu;
import com.hollysgang.sample.demo.entity.id.MenuAPIId;

import jakarta.persistence.*;

@Entity
@Table(name = "MenuAPI")
@IdClass(MenuAPIId.class)
public class MenuAPI {

    @Id
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Id
    @ManyToOne
    @JoinColumn(name = "api_id")
    private API api;
}
