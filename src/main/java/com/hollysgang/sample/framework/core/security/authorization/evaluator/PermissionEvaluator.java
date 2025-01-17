package com.hollysgang.sample.framework.core.security.authorization.evaluator;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PermissionEvaluator implements AuthorizationManager<RequestAuthorizationContext> {
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

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext reqContext) {
        Authentication auth = authentication.get();
        if (auth == null || !auth.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }
        PermissionResult evaluate = evaluate(reqContext.getRequest(),
                authentication.get().getAuthorities().stream().map(au -> au.getAuthority()).toArray(String[]::new));
        if(PermissionResult.DENIED.equals(evaluate)){
            return new AuthorizationDecision(false);
        }
        return new AuthorizationDecision(true);
    }
}
