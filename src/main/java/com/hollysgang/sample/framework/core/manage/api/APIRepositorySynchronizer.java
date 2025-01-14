package com.hollysgang.sample.framework.core.manage.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class APIRepositorySynchronizer {
    private final APIRetriever apiRetriever;
    private final APIInfoRepository apiInfoRepository;

    public APIRepositorySynchronizer(APIRetriever apiRetriever, APIInfoRepository apiInfoRepository) {
        this.apiRetriever = apiRetriever;
        this.apiInfoRepository = apiInfoRepository;
    }

    List<APIInfo>[] getModifiedInfo(String target){
        List<APIInfo> apiInfos = apiRetriever.retrieveAPIs(target);
        List<APIInfo> repoApiInfos = apiInfoRepository.findAll(target);

        apiInfos.sort(APIInfo::compareTo);
        repoApiInfos.sort(APIInfo::compareTo);

        List<APIInfo> newApis = new ArrayList<>();
        List<APIInfo> deletedApis = new ArrayList<>();
        List<APIInfo> updatedApis = new ArrayList<>();

        int p1 = 0;
        int p2 = 0;

        while(p1 < apiInfos.size() && p2 < repoApiInfos.size()){
            APIInfo curInfo = apiInfos.get(p1);
            APIInfo repoInfo = repoApiInfos.get(p2);
            if(curInfo.compareTo(repoInfo) < 0){
                newApis.add(curInfo);
                p1++;
            } else if(curInfo.compareTo(repoInfo) > 0){
                deletedApis.add(repoInfo);
                p2++;
            } else {
                if(!curInfo.compareAllFields(repoInfo)){
                    curInfo.setKey(repoInfo.getKey());
                    updatedApis.add(curInfo);
                }
                p1++;
                p2++;
            }
        }

        for(int i = p1; i < apiInfos.size(); i++){
            newApis.add(apiInfos.get(i));
        }
        for(int i = p2; i < repoApiInfos.size(); i++){
            deletedApis.add(repoApiInfos.get(i));
        }

        return new List[] { newApis, deletedApis, updatedApis };

    }

    public void synchronize(String targetPackage){
        List<APIInfo>[] modified = getModifiedInfo(targetPackage);
        apiInfoRepository.sync(modified[0], modified[1], modified[2]);
    }
}
