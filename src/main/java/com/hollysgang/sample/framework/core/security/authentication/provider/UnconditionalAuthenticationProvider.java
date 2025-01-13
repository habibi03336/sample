package com.hollysgang.sample.framework.core.security.authentication.provider;

import com.hollysgang.sample.framework.core.security.authentication.token.authenticated.UserToken;
import com.hollysgang.sample.framework.core.security.authentication.token.pre.NullToken;
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
