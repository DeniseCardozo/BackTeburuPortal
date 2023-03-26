package com.dcardozo.teburuportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dcardozo.teburuportal.controlador"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(appInfo());
    }

    private ApiInfo appInfo() {
        return new ApiInfo(
                "Teburu Portal API - Bootcamp ASJ",
                "API para subida y analisis de archivos excel. Creación/modificación/eliminación de las tablas de los mismos. Creación de áreas y proyectos de trabajo.",
                "V1",
                "Terminos y servicios",
                new Contact("Denise Carla María Cardozo", "https://www.linkedin.com/in/denise-carla-maria-cardozo/", "dennicardozo96@gmail.com"),
                "Licencia de API",
                "URL licencia",
                Collections.emptyList()
        );
    }


}
