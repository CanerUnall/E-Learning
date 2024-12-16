package com.cap.MyLearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Tüm yollar için
                .allowedOrigins("http://localhost:4200")  // Angular uygulamasının çalıştığı origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // İzin verilen HTTP metodları
                .allowedHeaders("*")  // Tüm başlıkları kabul et
                .allowCredentials(true);  // Kimlik doğrulama bilgileri (örneğin, Cookie'ler) gönderilebilir
    }
}
