package com.hollysgang.sample.framework.module.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authoritymenu")
@Data
@IdClass(AuthorityMenuId.class)
public class AuthorityMenu {

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
