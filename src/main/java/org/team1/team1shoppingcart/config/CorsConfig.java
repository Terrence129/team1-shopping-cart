package org.team1.team1shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenyaqi
 * @Description
 * @Date 5/10/25 22:12
 * @Param
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override public void addCorsMappings(CorsRegistry r) {
        r.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
                .allowedMethods("GET","POST","PUT","DELETE").allowCredentials(true)
                .allowCredentials(true).maxAge(3600);
    }
}
