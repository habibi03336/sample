package com.hollysgang.sample.framework.core.manage.api;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizeEntity {
    private String key;
    private String name;

}
