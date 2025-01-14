package com.hollysgang.sample.framework.module.api;

import com.hollysgang.sample.framework.core.manage.api.APIInfo;
import com.hollysgang.sample.framework.core.manage.api.APIInfoRepository;
import com.hollysgang.sample.framework.module.api.entity.API;
import com.hollysgang.sample.framework.module.api.repository.APIRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class APIInfoRepositoryImpl implements APIInfoRepository {

    private final APIRepository apiRepository;

    public APIInfoRepositoryImpl(APIRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public List<APIInfo> findAll(String targetProject) {
        List<API> apis = apiRepository.findAll();
        return apis.stream()
                .map((e) -> APIInfo.builder()
                        .key(Long.toString(e.getId()))
                        .uri(e.getPath())
                        .method(e.getMethod())
                        .description(e.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public APIInfo findByKey(String key) {
        Optional<API> apiOptional = apiRepository.findById(Long.parseLong(key));
        if(apiOptional.isEmpty()) return null;
        API api = apiOptional.get();
        return APIInfo.builder()
                .key(api.getId().toString())
                .uri(api.getPath())
                .method(api.getMethod())
                .description(api.getDescription())
                .build();
    }

    @Override
    @Transactional
    public void sync(List<APIInfo> newApiInfo, List<APIInfo> removedApiInfo, List<APIInfo> updatedApiInfo) {
        List<API> newApis = newApiInfo.stream().map(
                (api) -> API.builder()
                        .path(api.getUri())
                        .method(api.getMethod())
                        .description(api.getDescription())
                        .build()
        ).collect(Collectors.toList());
        List<Long> deleteIds = removedApiInfo.stream().map(
                (api) -> Long.parseLong(api.getKey())
        ).collect(Collectors.toList());

       updatedApiInfo.forEach(
                (api) -> apiRepository.updateStatus(Long.parseLong(api.getKey()), api.getDescription())
        );
        apiRepository.deleteAllById(deleteIds);
        apiRepository.saveAll(newApis);
    }
}
