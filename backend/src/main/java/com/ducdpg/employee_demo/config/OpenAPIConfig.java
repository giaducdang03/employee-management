package com.ducdpg.employee_demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Employee Demo API")
                .description("This is the API documentation for the Employee Demo application.")
                .version("v1.0")
        );
    }
}
