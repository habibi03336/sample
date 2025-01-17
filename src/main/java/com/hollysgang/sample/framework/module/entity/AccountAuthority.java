package com.hollysgang.sample.framework.module.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "accountauthority")
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
