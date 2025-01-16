package com.hollysgang.sample.framework.module.repository;

import com.hollysgang.sample.framework.module.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
