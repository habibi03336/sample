package com.hollysgang.sample.framework.module.security;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DemoUserDetails implements UserDetails {

    private String name;
    private List<DemoGrantedAuthority> authorities;

    @Builder
    public DemoUserDetails(String name, DemoGrantedAuthority ...demoGrantedAuthorities){
        this.name = name;
        this.authorities = List.of(demoGrantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
