package com.maryanto.dimas.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerUiConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.application.version}")
    private String applicationVersion;
    @Value("${spring.application.fullname}")
    private String applicationFullName;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping(contextPath)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        applicationName,
                        applicationFullName,
                        applicationVersion,
                        "TERMS OF SERVICE URL",
                        new Contact(
                                "Dimas Maryanto",
                                "http://www.dimas-maryanto.com",
                                "software.dimasm_93@icloud.com"),
                        "LICENSE",
                        "LICENSE URL",
                        new ArrayList<>()
                ));
    }
}
