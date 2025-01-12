package com.hollysgang.sample.framework.security.authorization.evaluator;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class PermissionEvaluator {
    APIPermissionLoader loader;
    Map<String, APIPermissionInfo[]> apiPermissionInfoMap;

    public PermissionResult evaluate(HttpServletRequest request, String[] authorities){
        return null;
    }

}
