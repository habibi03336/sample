package com.hollysgang.sample.framework.module.security;

import com.hollysgang.sample.framework.core.security.authorization.evaluator.APIPermissionInfo;
import com.hollysgang.sample.framework.core.security.authorization.evaluator.APIPermissionLoader;
import com.hollysgang.sample.framework.core.security.authorization.evaluator.HttpMethod;
import com.hollysgang.sample.framework.module.entity.API;
import com.hollysgang.sample.framework.module.entity.Authority;
import com.hollysgang.sample.framework.module.entity.Menu;
import com.hollysgang.sample.framework.module.repository.AuthorityRepository;
import com.hollysgang.sample.framework.module.repository.MenuAPIRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class APIPermissionLoaderImpl implements APIPermissionLoader {

    private final AuthorityRepository authorityRepository;
    private final MenuAPIRepository menuAPIRepository;

    public APIPermissionLoaderImpl(AuthorityRepository authorityRepository, MenuAPIRepository menuAPIRepository) {
        this.authorityRepository = authorityRepository;
        this.menuAPIRepository = menuAPIRepository;
    }

    @Override
    public List<APIPermissionInfo> load() {
        Map<String, Map<String, APIPermissionInfo>> urlMethodMapToPermission = new HashMap<>();
        List<Authority> authorities = authorityRepository.findAll();
        for(Authority authority : authorities){
            List<Menu> menus = authority.getAuthorityMenus().stream().map(am -> am.getMenu()).collect(Collectors.toList());
            for(Menu menu : menus){
                List<API>  apis = menuAPIRepository.findAllByMenuId(menu.getId()).stream().map(mi -> mi.getApi()).collect(Collectors.toList());
                for(API api : apis) {
                    if (!urlMethodMapToPermission.containsKey(api.getPath())) {
                        urlMethodMapToPermission.put(api.getPath(), new HashMap<>());
                    }
                    Map<String, APIPermissionInfo> methodMapToPermission = urlMethodMapToPermission.get(api.getPath());
                    if (!methodMapToPermission.containsKey(api.getMethod())) {
                        methodMapToPermission.put(api.getMethod(), APIPermissionInfo.builder().url(api.getPath()).method(HttpMethod.valueOf(api.getMethod())).build());
                    }
                    APIPermissionInfo permissionInfo = methodMapToPermission.get(api.getMethod());
                    permissionInfo.addAllowedRoles(authority.getId().toString());
                }
            }
        }
        List<APIPermissionInfo> permissions = new ArrayList<>();
        for(String urlkey : urlMethodMapToPermission.keySet()){
            Map<String, APIPermissionInfo> methodMapToPermission = urlMethodMapToPermission.get(urlkey);
            for(String methodkey : methodMapToPermission.keySet()){
                permissions.add(methodMapToPermission.get(methodkey));
            }
        }
        return permissions;
    }
}
