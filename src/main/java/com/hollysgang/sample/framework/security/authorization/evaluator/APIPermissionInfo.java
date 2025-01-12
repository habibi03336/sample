package com.hollysgang.sample.framework.security.authorization.evaluator;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class APIPermissionInfo {
    private final String url;
    private final HttpMethod method;
    private final Set<String> allowedRoles;

    public APIPermissionInfo(String url, HttpMethod method){
        this.url = url;
        this.method = method;
        this.allowedRoles = new HashSet<>();
    }

    public void addAllowedRoles(String... roles){
        for(String role: roles){
            if(role == null) continue;
            allowedRoles.add(role);
        }
    }

    public boolean isAllowedRole(String role){
        return allowedRoles.contains(role);
    }
}
