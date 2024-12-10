package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todas las rutas
                      .allowedOrigins("http://localhost:5173") // Permite el frontend definido en properties
                      .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Permite todos los métodos
                      .allowedHeaders("*") // Permite todas las cabeceras
                      .allowCredentials(true); // Permite cookies si es necesario
            }
        };
    }
}