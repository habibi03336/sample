package com.hollysgang.sample.framework.core.manage.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIInfo {
    private String uri;
    private String method;
    private String description;
}
