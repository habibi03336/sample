package com.hollysgang.sample.framework.core.security.authorization.evaluator;

import java.util.List;

public interface APIPermissionLoader {
    List<APIPermissionInfo> load();
}
