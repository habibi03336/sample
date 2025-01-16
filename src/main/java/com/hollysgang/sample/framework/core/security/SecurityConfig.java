package com.hollysgang.sample.framework.core.security;

import com.hollysgang.sample.framework.core.security.authentication.filter.UserPassThroughAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        UserPassThroughAuthenticationFilter userPassThroughFilter =
                new UserPassThroughAuthenticationFilter(
                        new AntPathRequestMatcher("/user-pass-through", "GET"),
                        authenticationManager
                );
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository =
                new HttpSessionSecurityContextRepository();
        userPassThroughFilter.setSecurityContextRepository(httpSessionSecurityContextRepository);

        http
            .authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/**")).permitAll()
                        .anyRequest().authenticated()
            )
            .csrf(csrf->csrf.disable())
            .addFilterBefore(userPassThroughFilter, AnonymousAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
