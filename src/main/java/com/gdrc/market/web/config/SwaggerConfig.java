package com.gdrc.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/*
@Configuration:
    Es una anotacion de spring que indica que es
    una clase de configuracion
@EnableSwagger2:
    Es una anotacion para habilitar Swagger2
@Bean:
    Indicates that a method produces a bean
    to be managed by the Spring container.
La documentacion abre en {host}:{puerto}/{contexto}/swagger-ui/index.html
 */
@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gdrc.market.web.controller"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}
