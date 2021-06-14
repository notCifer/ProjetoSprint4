package com.projeto.loja.configs.doc;

import java.util.ArrayList;
import java.util.Arrays;
import com.google.common.base.Predicates;
import com.projeto.loja.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket Api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo (metaInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/**"))
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build()
        .ignoredParameterTypes(Usuario.class)
        .globalOperationParameters(Arrays.asList(
            new ParameterBuilder()
            .name("Authorization")
            .description("Bearer + TOKEN ")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build()
        ));
    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
            "Loja API REST",
            "API REST de loja",
            "1.0",
            "Terms of Service",
            new Contact("Allan Patrick", "https://www.instagram.com/allanpatrickdbp/",
                    "allan.15.patrick@gmail.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
    );
    return apiInfo;
    }
    
}