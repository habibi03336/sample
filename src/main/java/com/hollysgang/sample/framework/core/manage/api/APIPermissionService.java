package com.hollysgang.sample.framework.core.manage.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class APIPermissionService {

    private final APIPermissionRepository apiPermissionRepository;
    private final AuthorizeEntityRepository authorizeEntityRepository;
    private final APIInfoRepository apiInfoRepository;

    public APIPermissionService(APIPermissionRepository apiPermissionRepository, AuthorizeEntityRepository authorizeEntityRepository, APIInfoRepository apiInfoRepository) {
        this.apiPermissionRepository = apiPermissionRepository;
        this.authorizeEntityRepository = authorizeEntityRepository;
        this.apiInfoRepository = apiInfoRepository;

    }

    public List<AuthorizeEntity> getAuthorizeEntities(){
        return authorizeEntityRepository.findAll();
    }

    public AuthorizeEntity getAuthorizeEntity(String key){
        return authorizeEntityRepository.findAllByKey(key);
    }

    public List<APIInfo> getAPIInfos(String targetProject){
        return apiInfoRepository.findAll(targetProject);
    }

    public List<APIPermission> getAuthorizedAPIInfos(String authorizedEntity){
        return apiPermissionRepository.findAllByAuthorizedEntity(authorizedEntity);
    }

    public void addPermission(String authorizedEntity, String apikey){
        AuthorizeEntity authEntity = authorizeEntityRepository.findAllByKey(authorizedEntity);
        if(authEntity == null) {
            throw new NoSuchElementException(String.format("식별자 %s를 가진 허가 대상이 존재하지 않습니다.", authorizedEntity));
        }
        APIInfo apiInfo = apiInfoRepository.findByKey(apikey);
        if(apiInfo == null) {
            throw new NoSuchElementException(String.format("식별자 %s를 가진 API가 존재하지 않습니다.", apikey));
        }
        APIPermission apiPermission =
                apiPermissionRepository.findByAuthorizedEntityAndApikey(authorizedEntity, apikey);
        if(apiPermission != null){
            throw new IllegalStateException(String.format("%s은(는) path: %s, method: %s 호출에 대한 권한을 이미 가지고 있습니다.", authorizedEntity, apiInfo.getUri(), apiInfo.getMethod()));
        }
        apiPermissionRepository.addPermission(authorizedEntity, apikey);
    }

    public void deletePermission(String authorizedEntity, String apikey){
        AuthorizeEntity authEntity = authorizeEntityRepository.findAllByKey(authorizedEntity);
        if(authEntity == null) {
            throw new NoSuchElementException(String.format("식별자 %s를 가진 허가 대상이 존재하지 않습니다.", authorizedEntity));
        }
        APIInfo apiInfo = apiInfoRepository.findByKey(apikey);
        if(apiInfo == null) {
            throw new NoSuchElementException(String.format("식별자 %s를 가진 API가 존재하지 않습니다.", apikey));
        }
        APIPermission apiPermission =
                apiPermissionRepository.findByAuthorizedEntityAndApikey(authorizedEntity, apikey);
        if(apiPermission == null){
            throw new IllegalStateException(String.format("%s은(는) path: %s, method: %s 호출에 대한 권한을 가지고 있지 않습니다.", authorizedEntity, apiInfo.getUri(), apiInfo.getMethod()));
        }
        apiPermissionRepository.deletePermission(authorizedEntity, apikey);
    }
}
