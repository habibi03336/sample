package com.hollysgang.sample.framework.core.manage.api;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class APIRetriever {

    private final RequestMappingHandlerMapping handlerMapping;

    public APIRetriever(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public List<APIInfo> retrieveAPIs(String targetPackage){
        List<APIInfo> apiInfos = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        for(RequestMappingInfo mappingInfo : handlerMethods.keySet()){
            HandlerMethod handlerMethod = handlerMethods.get(mappingInfo);
            String packageName = handlerMethod.getBeanType().getPackageName();

            if(packageName == null || packageName.indexOf(targetPackage) != 0) continue;

            APIInfo apiInfo = APIInfo.builder()
                    .uri(mappingInfo.getPathPatternsCondition().getFirstPattern().getPatternString())
                    .method(mappingInfo.getMethodsCondition().getMethods().toArray()[0].toString())
                    .build();
            apiInfos.add(apiInfo);
        }

        return apiInfos;
    }
}
