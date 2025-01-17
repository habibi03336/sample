package com.hollysgang.sample.framework.core.security;

import com.hollysgang.sample.framework.core.security.authentication.filter.UserPassThroughAuthenticationFilter;
import com.hollysgang.sample.framework.core.security.authorization.evaluator.APIPermissionLoader;
import com.hollysgang.sample.framework.core.security.authorization.evaluator.PermissionEvaluator;
import com.hollysgang.sample.framework.core.security.authorization.filter.APIPermissionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PermissionEvaluator permissionEvaluator(APIPermissionLoader apiPermissionLoader){
        return new PermissionEvaluator(apiPermissionLoader);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, APIPermissionLoader apiPermissionLoader) throws Exception {
        UserPassThroughAuthenticationFilter userPassThroughFilter =
                new UserPassThroughAuthenticationFilter(
                        new AntPathRequestMatcher("/user-pass-through", "GET"),
                        authenticationManager
                );
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        userPassThroughFilter.setSecurityContextRepository(httpSessionSecurityContextRepository);

        APIPermissionFilter apiPermissionFilter = new APIPermissionFilter(permissionEvaluator(apiPermissionLoader));

        http
            .authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/**")).permitAll()
                        .anyRequest().authenticated()
            )
            .csrf(csrf->csrf.disable())
            .addFilterBefore(userPassThroughFilter, AnonymousAuthenticationFilter.class)
            .addFilterAt(apiPermissionFilter, AuthorizationFilter.class)
        ;

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
