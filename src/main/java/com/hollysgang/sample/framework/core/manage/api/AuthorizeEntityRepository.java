package com.hollysgang.sample.framework.core.manage.api;

import java.util.List;

public interface AuthorizeEntityRepository {
    List<AuthorizeEntity> findAll();
    AuthorizeEntity findAllByKey(String key);
}
