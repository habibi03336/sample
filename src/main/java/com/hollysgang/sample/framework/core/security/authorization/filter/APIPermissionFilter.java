package com.hollysgang.sample.framework.core.security.authorization.filter;

import com.hollysgang.sample.framework.core.security.authorization.evaluator.PermissionEvaluator;
import com.hollysgang.sample.framework.core.security.authorization.evaluator.PermissionResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class APIPermissionFilter extends OncePerRequestFilter {

    private final PermissionEvaluator permissionEvaluator;

    public APIPermissionFilter(PermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PermissionResult evaluate = permissionEvaluator.evaluate(request,
                auth.getAuthorities().stream().map((au) -> au.getAuthority()).toArray(String[]::new));
        if(PermissionResult.DENIED.equals(evaluate)){
            response.setStatus(401);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
