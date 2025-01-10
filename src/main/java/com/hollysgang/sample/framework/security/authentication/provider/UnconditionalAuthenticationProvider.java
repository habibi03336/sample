package com.hollysgang.sample.framework.security.authentication.provider;

import com.hollysgang.sample.framework.security.authentication.token.pre.NullToken;
import com.hollysgang.sample.framework.security.authentication.token.authenticated.UserToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UnconditionalAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return new UserToken("user", null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(NullToken.class);
    }
}
