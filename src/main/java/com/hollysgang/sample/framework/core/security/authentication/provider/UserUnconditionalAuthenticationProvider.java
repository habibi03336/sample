package com.hollysgang.sample.framework.core.security.authentication.provider;

import com.hollysgang.sample.framework.core.security.authentication.token.authenticated.UserToken;
import com.hollysgang.sample.framework.core.security.authentication.token.pre.PreUserToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserUnconditionalAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;

    public UserUnconditionalAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        return new UserToken(userDetails.getUsername(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreUserToken.class);
    }
}
