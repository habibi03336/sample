package com.hollysgang.sample.framework.core.manage.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIPermission {
    private String authorizedEntity;
    private String apiKey;
}
