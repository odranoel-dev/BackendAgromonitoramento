package com.example.agromonitoramento.backendagromonitoramento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // todos os endpoints
                .allowedOrigins("http://localhost:4200") // front
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos permitidos
                .allowedHeaders("*") // headers permitidos
                .allowCredentials(true); // se precisar mandar cookies/autenticação
    }
}
