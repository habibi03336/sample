package com.hollysgang.sample.framework.module;

import com.hollysgang.sample.framework.core.manage.api.APIInfo;
import com.hollysgang.sample.framework.core.manage.api.APIRetriever;
import com.hollysgang.sample.framework.module.entity.API;
import com.hollysgang.sample.framework.module.repository.APIRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class APIRetrieveAndSave implements CommandLineRunner {

    private final APIRetriever apiRetriever;
    private final APIRepository apiRepository;

    public APIRetrieveAndSave(APIRetriever apiRetriever, APIRepository apiRepository) {
        this.apiRetriever = apiRetriever;
        this.apiRepository = apiRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String targetPackage = "com.hollysgang.sample";
        List<APIInfo> apiInfos = apiRetriever.retrieveAPIs(targetPackage);
        List<API> apiEntities = new ArrayList<>();
        for(APIInfo apiInfo : apiInfos){
            apiEntities.add(
                    API.builder()
                            .path(apiInfo.getUri())
                            .method(apiInfo.getMethod())
                            .description(apiInfo.getDescription())
                            .build()
            );
        }
        List<API> newApiEntities = apiEntities
                .stream()
                .filter(apiEntity -> !apiRepository.existsByPathAndMethod(apiEntity.getPath(), apiEntity.getMethod()))
                .collect(Collectors.toList());
        apiRepository.saveAll(newApiEntities);
    }
}
