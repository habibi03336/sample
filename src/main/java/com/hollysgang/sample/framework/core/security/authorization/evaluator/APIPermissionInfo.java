package com.hollysgang.sample.framework.core.security.authorization.evaluator;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class APIPermissionInfo {
    private String url;
    private HttpMethod method;
    private Set<String> allowedRoles;

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
