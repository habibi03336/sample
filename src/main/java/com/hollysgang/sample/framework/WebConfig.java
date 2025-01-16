package com.hollysgang.sample.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 설정 적용
                .allowedOrigins("http://localhost:5173") // 허용할 호스트
                .allowedMethods("OPTIONS", "DELETE", "GET", "POST", "PUT") // 허용할 HTTP 메서드
                .allowCredentials(true) // 쿠키 허용 여부
                .allowedHeaders("*") // 허용할 헤더
                .maxAge(3600); // 캐싱 시간
    }
}
