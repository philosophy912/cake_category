package com.sophia.cake.config;

import com.sophia.cake.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lizhe
 * @date 2020/3/11 11:41
 **/
@Configuration
@EnableSwagger2
@Slf4j
public class BeanConfigure {

    @Bean
    public EntityUtils getEntityUtils() {
        return new EntityUtils();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sophia.cake.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo getApiInfo() {
        String url = "https://github.com/philosophy912/cake_category";
        return new ApiInfoBuilder()
                .title("cake")
                .description("cake apis")
                .version("1.0.0")
                .contact(new Contact("totti", url, "totti_912@sina.com"))
                .license("The Apache License")
                .licenseUrl(url)
                .build();
    }

}
