package com.hollysgang.sample.framework.core.manage.api;

import java.util.List;

public interface APIInfoRepository {
    List<APIInfo> findAll(String targetProject);

    APIInfo findByKey(String key);

    void sync(List<APIInfo> newApiInfo, List<APIInfo> removedApiInfo, List<APIInfo> updatedApiInfo);
}
