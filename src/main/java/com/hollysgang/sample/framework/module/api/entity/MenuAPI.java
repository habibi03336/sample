package com.hollysgang.sample.framework.module.api.entity;

import com.hollysgang.sample.demo.entity.Menu;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
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

    @Builder
    public MenuAPI(Menu menu, API api) {
        this.menu = menu;
        this.api = api;
    }

    public MenuAPI() {

    }
}