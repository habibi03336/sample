package com.hollysgang.sample.framework.module.api.repository;

import com.hollysgang.sample.framework.module.api.entity.API;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APIRepository extends JpaRepository<API, Long> {

    List<API> findAll();
    @Modifying
    @Transactional
    @Query("UPDATE API e SET e.description = :description WHERE e.id = :id")
    int updateStatus(@Param("id") Long id, @Param("description") String description);
}
