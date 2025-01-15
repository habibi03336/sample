package com.hollysgang.sample.framework.module.api.repository;

import com.hollysgang.sample.framework.module.api.entity.MenuAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuAuthEntity, Long> {
}
