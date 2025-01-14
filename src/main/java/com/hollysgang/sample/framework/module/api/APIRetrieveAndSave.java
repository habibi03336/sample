package com.hollysgang.sample.framework.module.api;

import com.hollysgang.sample.framework.core.manage.api.APIRepositorySynchronizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class APIRetrieveAndSave implements CommandLineRunner {

    private final APIRepositorySynchronizer apiRepositorySynchronizer;

    public APIRetrieveAndSave(APIRepositorySynchronizer apiRepositorySynchronizer) {
        this.apiRepositorySynchronizer = apiRepositorySynchronizer;
    }

    @Override
    public void run(String... args) throws Exception {
        String targetPackage = "com.hollysgang.sample";
        apiRepositorySynchronizer.synchronize(targetPackage);
    }
}
