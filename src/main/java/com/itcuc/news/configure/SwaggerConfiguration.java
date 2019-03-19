package com.itcuc.news.configure;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("itcuc-news")
                .description(
                        "新闻服务端")
                .license("").licenseUrl("http://unlicense.org").termsOfServiceUrl("").version("1.0.0")
                .contact(new Contact("", "", "")).build();
    }

    @Bean
    public Docket customImplementation() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        /**
         *  header中的ticket参数非必填，传空也可以
         */
        ticketPar.name("authorization").description("authorization")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.itcuc.news.rest"))).build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }
}
