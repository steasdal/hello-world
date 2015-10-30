package com.teasdale.config

import com.google.common.base.Predicate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import static com.google.common.base.Predicates.or
import static springfox.documentation.builders.PathSelectors.regex

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build()
    }

    private static Predicate<String> paths() {
        return or(
                regex(""),
                regex("/hello"),
                regex("/person"),
                regex("/person/.*")
        );
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hello World Service")
                .description("A relatively simple service to demonstrate some basic features of a Spring Boot and WebMVC")
                .version("1.0")
                .build();
    }
}
