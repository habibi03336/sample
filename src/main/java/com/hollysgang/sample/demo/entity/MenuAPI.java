package com.hollysgang.sample.demo.entity;

import com.hollysgang.sample.demo.entity.id.MenuAPIId;
import jakarta.persistence.*;

import java.io.Serializable;

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
