package com.hollysgang.sample.demo.entity;

import com.hollysgang.sample.demo.entity.id.AccountAuthorityId;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "AccountAuthority")
@IdClass(AccountAuthorityId.class)
public class AccountAuthority {

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private LocalDateTime expireDt;
}
