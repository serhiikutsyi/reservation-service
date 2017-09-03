package com.serhiikutsyi.reservation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Automatically generate RESTful API documentation with Swagger
 * See https://swagger.io/
 *
 * @author Serhii Kutsyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Adding reservation service endpoints to the documentation
     */
    @Bean
    public Docket reservationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("1. Reservation API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.serhiikutsyi.reservation.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Reservation Service")
                .description("Reservation REST API")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
