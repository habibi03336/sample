package com.hollysgang.sample.framework.core.security.authentication.filter;

import com.hollysgang.sample.framework.core.security.authentication.token.pre.NullToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


public class PassThroughAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public PassThroughAuthenticationFilter(AntPathRequestMatcher filterTargetUrl,
                                           AuthenticationManager authenticationManager) {
        super(filterTargetUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return this.getAuthenticationManager().authenticate(new NullToken());
    }
}
