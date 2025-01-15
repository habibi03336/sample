package com.hollysgang.sample.framework.module.api;

import com.hollysgang.sample.demo.entity.Menu;
import com.hollysgang.sample.framework.core.manage.api.APIPermission;
import com.hollysgang.sample.framework.core.manage.api.APIPermissionRepository;
import com.hollysgang.sample.framework.module.api.entity.API;
import com.hollysgang.sample.framework.module.api.entity.MenuAPI;
import com.hollysgang.sample.framework.module.api.repository.MenuAPIRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class APIPermissionRepositoryImpl implements APIPermissionRepository {

    private final MenuAPIRepository menuAPIRepository;

    public APIPermissionRepositoryImpl(MenuAPIRepository menuAPIRepository) {
        this.menuAPIRepository = menuAPIRepository;
    }

    @Override
    public List<APIPermission> findAllByApikey(String apikey) {
        return menuAPIRepository.findAllByApiId(Long.parseLong(apikey)).stream()
                .map(this::mapToApiPermission)
                .collect(Collectors.toList());
    }


    @Override
    public List<APIPermission> findAllByAuthorizedEntity(String authorizedEntity) {
        return menuAPIRepository.findAllByMenuId(Long.parseLong(authorizedEntity)).stream()
                .map(this::mapToApiPermission)
                .collect(Collectors.toList());
    }

    @Override
    public List<APIPermission> findAll() {
        return menuAPIRepository.findAll().stream().map(this::mapToApiPermission).collect(Collectors.toList());
    }

    @Override
    public APIPermission findByAuthorizedEntityAndApikey(String authorizedEntity, String apiKey) {
        Optional<MenuAPI> menuApi = menuAPIRepository.findByMenuIdAndApiId(Long.parseLong(authorizedEntity),
                Long.parseLong(apiKey));
        if(menuApi.isEmpty()) return null;
        return mapToApiPermission(menuApi.get());
    }

    @Override
    public void addPermission(String authorizedEntity, String apiKey) {
        Menu menu = Menu.builder().id(Long.parseLong(authorizedEntity)).build();
        API api = API.builder().id(Long.parseLong(apiKey)).build();
        MenuAPI menuApi = MenuAPI.builder().menu(menu).api(api).build();
        menuAPIRepository.save(menuApi);
    }

    @Override
    public void deletePermission(String authorizedEntity, String apiKey) {
        Optional<MenuAPI> menuApi = menuAPIRepository.findByMenuIdAndApiId(Long.parseLong(authorizedEntity),
                Long.parseLong(apiKey));
        if(menuApi.isEmpty()) return;
        menuAPIRepository.delete(menuApi.get());
    }

    private APIPermission mapToApiPermission(MenuAPI menuAPI){
        return APIPermission.builder()
                .apiKey(menuAPI.getApi().getId().toString())
                .authorizedEntity(menuAPI.getMenu().getId().toString())
                .build();
    }
}
