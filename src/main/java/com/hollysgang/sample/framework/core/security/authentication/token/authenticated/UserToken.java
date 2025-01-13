package com.hollysgang.sample.framework.core.security.authentication.token.authenticated;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserToken extends AuthenticatedToken {

    private final Object principal;

    public UserToken( Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
