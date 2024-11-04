package team1.BE.seamless.util.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOriginPatterns("*") // CORS를 허용할 도메인
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드
            .allowedHeaders("*") // 허용할 HTTP 헤더
            .allowCredentials(true) // 자격 증명 허용
            .maxAge(3600); // 캐시 유지 시간(초 단위)
    }
}
