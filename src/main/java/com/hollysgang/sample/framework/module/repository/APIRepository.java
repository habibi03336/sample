package com.hollysgang.sample.framework.module.repository;

import com.hollysgang.sample.framework.module.entity.API;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APIRepository extends JpaRepository<API, Long> {
}
