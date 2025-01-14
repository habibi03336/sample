package com.hollysgang.sample.framework.core.manage.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class APIRetrieverTest {

    @Test
    @DisplayName("API 목록 생성 성공 테스트")
    public void APIListTest(){
        // given
        RequestMappingHandlerMapping requestMappingHandlerMapping = mock(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = new HashMap<>();

        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        Class clazz = Class.class;
        when(handlerMethod.getBeanType()).thenReturn(clazz);
        handlerMethods.put(RequestMappingInfo.paths("/").methods(RequestMethod.GET).build(), handlerMethod);

        when(requestMappingHandlerMapping.getHandlerMethods()).thenReturn(handlerMethods);

        APIRetriever apiRetriever = new APIRetriever(requestMappingHandlerMapping);

        // execute
        List<APIInfo> apiInfos = apiRetriever.retrieveAPIs("java.lang");

        // expect
        assertThat(apiInfos.size()).isEqualTo(1);
        assertThat(apiInfos.get(0).getUri()).isEqualTo("/");
        assertThat(apiInfos.get(0).getMethod()).isEqualTo("GET");
    }
}