package com.hollysgang.sample.demo.entity;

import com.hollysgang.sample.demo.entity.id.UserAuthorityId;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "UserAuthority")
@IdClass(UserAuthorityId.class)
public class UserAuthority {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private LocalDateTime expireDt;
}
