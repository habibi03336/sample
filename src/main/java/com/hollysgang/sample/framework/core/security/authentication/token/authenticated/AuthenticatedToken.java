package com.hollysgang.sample.framework.core.security.authentication.token.authenticated;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public abstract class AuthenticatedToken extends AbstractAuthenticationToken {

    public AuthenticatedToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
    }

}
