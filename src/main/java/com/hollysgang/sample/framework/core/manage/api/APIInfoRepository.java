package com.hollysgang.sample.framework.core.manage.api;

import java.util.List;

public interface APIInfoRepository {
    List<APIInfo> findAll(String targetProject);

    void sync(List<APIInfo> newApiInfo, List<APIInfo> removedApiInfo, List<APIInfo> updatedApiInfo);
}
