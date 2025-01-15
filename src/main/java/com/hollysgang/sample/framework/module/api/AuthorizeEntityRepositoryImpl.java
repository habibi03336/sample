package com.hollysgang.sample.framework.module.api;

import com.hollysgang.sample.framework.core.manage.api.AuthorizeEntity;
import com.hollysgang.sample.framework.core.manage.api.AuthorizeEntityRepository;
import com.hollysgang.sample.framework.module.api.entity.Menu;
import com.hollysgang.sample.framework.module.api.repository.MenuRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthorizeEntityRepositoryImpl implements AuthorizeEntityRepository {
    private final MenuRepository menuRepository;

    public AuthorizeEntityRepositoryImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<AuthorizeEntity> findAll() {
        return menuRepository.findAll().stream().map(this::mapToAuthorizeEntity).collect(Collectors.toList());
    }

    @Override
    public AuthorizeEntity findAllByKey(String key) {
        Optional<Menu> menu = menuRepository.findById(Long.parseLong(key));
        return menu.map(this::mapToAuthorizeEntity).orElse(null);
    }

    private AuthorizeEntity mapToAuthorizeEntity(Menu menu){
        return AuthorizeEntity.builder()
                .key(menu.getId().toString())
                .name(menu.getName())
                .build();
    }
}
