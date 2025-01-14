package com.hollysgang.sample.framework.module.api.entity;

import com.hollysgang.sample.demo.entity.Menu;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
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

    public MenuAPI() {

    }
}