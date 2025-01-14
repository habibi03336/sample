package com.hollysgang.sample.framework.core.manage.api;


import java.util.List;

public interface APIPermissionRepository {
    List<APIPermission> findAllByApikey(String apikey);
    List<APIPermission> findAllByAuthorizedEntity(String authorizedEntity);
    List<APIPermission> findAll();
    APIPermission findByAuthorizedEntityAndApikey(String authorizedEntity, String apiKey);

    void addPermission(String authorizedEntity, String apiKey);
    void deletePermission(String authorizedEntity, String apiKey);
}
