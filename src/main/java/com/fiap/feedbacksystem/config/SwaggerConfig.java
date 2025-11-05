package com.fiap.feedbacksystem.config;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@Configuration
public class SwaggerConfig {
    @GetMapping("/")
    public void redirectSwagger(HttpServletResponse response) {
        String url = "swagger-ui/index.html";
        response.setHeader("Location", url);
        response.setStatus(302);
    }

    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI().info(new Info().title("FeedBack-System")
                .version("1.0.0")
                .license(new License().name("Código fonte da API")
                        .url("https://github.com/offteuz/FeedBack-System")));
    }
}
