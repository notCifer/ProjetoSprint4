package com.projeto.loja.configs.doc;

import java.util.Arrays;
import com.google.common.base.Predicates;
import com.projeto.loja.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket Api(){
        return new Docket(DocumentationType.SWAGGER_2)
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
    
}