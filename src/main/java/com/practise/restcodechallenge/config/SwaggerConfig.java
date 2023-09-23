package com.pixelbet.restcodechallenge.config;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(withClassAnnotation(Api.class))
            .paths(PathSelectors.any()).build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
