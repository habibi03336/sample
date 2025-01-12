package com.hollysgang.sample.framework.security.authorization.evaluator;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionEvaluator {
    APIPermissionLoader loader;
    Map<String, APIPermissionInfo>[] apiPermissionInfoMaps;


    public PermissionEvaluator(APIPermissionLoader loader){
        this.loader = loader;
        this.load();
    }

    private void load() {
        if (this.apiPermissionInfoMaps == null) {
            this.apiPermissionInfoMaps = new Map[HttpMethod.values().length];
            for(int i = 0; i < HttpMethod.values().length; i ++){
                this.apiPermissionInfoMaps[i] = new HashMap<>();
            }
        }

        List<APIPermissionInfo> permissions = loader.load();
        for(APIPermissionInfo permission: permissions){
            Map<String, APIPermissionInfo> apiPermissionInfoMap =
                    apiPermissionInfoMaps[permission.getMethod().ordinal()];
            apiPermissionInfoMap.put(permission.getUrl(), permission);
        }
    }


    public PermissionResult evaluate(HttpServletRequest request, String... authorities){
        Map<String, APIPermissionInfo> apiPermissionInfoMap =
                apiPermissionInfoMaps[HttpMethod.valueOf(request.getMethod()).ordinal()];

        APIPermissionInfo apiPermissionInfo = apiPermissionInfoMap.get(request.getRequestURI());
        if(apiPermissionInfo != null){
            for(String authority: authorities){
                if(apiPermissionInfo.isAllowedRole(authority)){
                    return PermissionResult.ACCESS;
                }
            }
        }

        return PermissionResult.DENIED;
    }

}
