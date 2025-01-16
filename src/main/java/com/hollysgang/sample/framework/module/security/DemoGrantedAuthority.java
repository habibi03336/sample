package com.hollysgang.sample.framework.module.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class DemoGrantedAuthority implements GrantedAuthority {
    private  String authority;
}
