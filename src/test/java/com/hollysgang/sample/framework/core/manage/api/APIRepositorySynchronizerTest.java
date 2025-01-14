package com.hollysgang.sample.framework.core.manage.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class APIRepositorySynchronizerTest {

    @Test
    @DisplayName("API 변경 내역 판단: 신규 API")
    public void getNewAPITest(){
        APIRetriever apiRetriever = mock(APIRetriever.class);
        APIInfoRepository apiRepository = mock(APIInfoRepository.class);
        when(apiRetriever.retrieveAPIs("test")).thenReturn(new ArrayList<>(List.of(
            APIInfo.builder()
                    .uri("/test1")
                    .method("GET")
                    .build(),
            APIInfo.builder()
                    .uri("/test2")
                    .method("POST")
                    .build(),
            APIInfo.builder()
                    .uri("/test3")
                    .method("GET")
                    .build()
        )));
        when(apiRepository.findAll("test")).thenReturn(new ArrayList<>(List.of()));

        APIRepositorySynchronizer synchronizer = new APIRepositorySynchronizer(apiRetriever,
                apiRepository);

        List<APIInfo>[] modifiedInfos = synchronizer.getModifiedInfo("test");
        List<APIInfo> newApis = modifiedInfos[0];
        List<APIInfo> removedApis = modifiedInfos[1];
        List<APIInfo> updatedApis = modifiedInfos[2];

        assertThat(newApis.size()).isEqualTo(3);
        assertThat(removedApis.size()).isEqualTo(0);
        assertThat(updatedApis.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("API 변경 내역 판단: 삭제 API")
    public void getRemovedAPITest(){
        APIRetriever apiRetriever = mock(APIRetriever.class);
        APIInfoRepository apiRepository = mock(APIInfoRepository.class);
        when(apiRetriever.retrieveAPIs("test")).thenReturn(new ArrayList<>(List.of(
                APIInfo.builder()
                        .uri("/test1")
                        .method("GET")
                        .build()
        )));
        when(apiRepository.findAll("test")).thenReturn(new ArrayList<>(List.of(
                APIInfo.builder()
                        .key("1001")
                        .uri("/test1")
                        .method("GET")
                        .build(),
                APIInfo.builder()
                        .key("1002")
                        .uri("/test2")
                        .method("POST")
                        .build(),
                APIInfo.builder()
                        .key("1003")
                        .uri("/test3")
                        .method("GET")
                        .build()
        )));

        APIRepositorySynchronizer synchronizer = new APIRepositorySynchronizer(apiRetriever,
                apiRepository);

        List<APIInfo>[] modifiedInfos = synchronizer.getModifiedInfo("test");
        List<APIInfo> newApis = modifiedInfos[0];
        List<APIInfo> removedApis = modifiedInfos[1];
        List<APIInfo> updatedApis = modifiedInfos[2];

        assertThat(newApis.size()).isEqualTo(0);
        assertThat(removedApis.size()).isEqualTo(2);
        assertThat(updatedApis.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("API 변경 내역 판단: 업데이트 API")
    public void getUpdatedAPITest(){
        APIRetriever apiRetriever = mock(APIRetriever.class);
        APIInfoRepository apiRepository = mock(APIInfoRepository.class);
        when(apiRetriever.retrieveAPIs("test")).thenReturn(new ArrayList<>(List.of(
                APIInfo.builder()
                        .uri("/test1")
                        .method("GET")
                        .build(),
                APIInfo.builder()
                        .key("1002")
                        .uri("/test2")
                        .description("추가된 설명")
                        .method("POST")
                        .build(),
                APIInfo.builder()
                        .key("1003")
                        .uri("/test3")
                        .method("GET")
                        .build()
        )));
        when(apiRepository.findAll("test")).thenReturn(new ArrayList<>(List.of(
                APIInfo.builder()
                        .key("1001")
                        .uri("/test1")
                        .method("GET")
                        .build(),
                APIInfo.builder()
                        .key("1002")
                        .uri("/test2")
                        .method("POST")
                        .build(),
                APIInfo.builder()
                        .key("1003")
                        .uri("/test3")
                        .method("GET")
                        .build()
        )));

        APIRepositorySynchronizer synchronizer = new APIRepositorySynchronizer(apiRetriever,
                apiRepository);

        List<APIInfo>[] modifiedInfos = synchronizer.getModifiedInfo("test");
        List<APIInfo> newApis = modifiedInfos[0];
        List<APIInfo> removedApis = modifiedInfos[1];
        List<APIInfo> updatedApis = modifiedInfos[2];

        assertThat(newApis.size()).isEqualTo(0);
        assertThat(removedApis.size()).isEqualTo(0);
        assertThat(updatedApis.size()).isEqualTo(1);
        APIInfo apiInfo = updatedApis.get(0);
        assertThat(apiInfo.getUri()).isEqualTo("/test2");
        assertThat(apiInfo.getMethod()).isEqualTo("POST");
        assertThat(apiInfo.getDescription()).isEqualTo("추가된 설명");
        assertThat(apiInfo.getKey()).isEqualTo("1002");
    }

}