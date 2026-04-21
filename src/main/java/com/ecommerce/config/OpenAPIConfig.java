package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Smart E-Commerce API")
                .description("REST and GraphQL backend APIs for Smart E-Commerce System")
                .version("v1.0")
                .contact(new Contact().name("Smart E-Commerce Team"))
                .license(new License().name("Internal Use")));
    }
}
