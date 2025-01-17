package com.hollysgang.sample.framework.module.repository;

import com.hollysgang.sample.framework.module.entity.Authority;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @EntityGraph(attributePaths = {"authorityMenus"})
    List<Authority> findAll();
    
}
