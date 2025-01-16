package com.hollysgang.sample.framework.module.repository;

import com.hollysgang.sample.framework.module.entity.MenuAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MenuAPIRepository extends JpaRepository<MenuAPI, Long> {

    @Query("SELECT m FROM MenuAPI m WHERE m.menu.id = :menuId AND m.api.id = :apiId")
    Optional<MenuAPI> findByMenuIdAndApiId(@Param("menuId") Long menuId, @Param("apiId") Long apiId);

    @Query("SELECT m FROM MenuAPI m WHERE m.menu.id = :menuId")
    List<MenuAPI> findAllByMenuId(@Param("menuId") Long menuId);

    @Query("SELECT m FROM MenuAPI m WHERE m.api.id = :apiId")
    List<MenuAPI> findAllByApiId(@Param("apiId") Long apiId);

}
