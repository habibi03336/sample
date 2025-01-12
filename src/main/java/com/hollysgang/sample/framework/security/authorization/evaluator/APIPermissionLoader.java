package com.hollysgang.sample.framework.security.authorization.evaluator;

import java.util.List;

public interface APIPermissionLoader {
    List<APIPermissionInfo> load();
}
