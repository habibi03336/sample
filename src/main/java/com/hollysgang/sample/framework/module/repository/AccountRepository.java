package com.hollysgang.sample.framework.module.repository;

import com.hollysgang.sample.framework.module.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>  {
    @EntityGraph(attributePaths = {"userAuthorities"})
    Optional<Account> findByName(String name);
}
