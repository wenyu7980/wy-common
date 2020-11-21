package com.wenyu7980.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wenyu
 */
public class SwaggerConfig implements WebMvcConfigurer, ImportAware {

    private String basePackage;
    private String name;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).groupName(this.name).select()
          .apis(RequestHandlerSelectors.basePackage(basePackage)).build().pathMapping("/")
          .securityContexts(Arrays.asList(securityContexts()))
          .securitySchemes(Arrays.asList(new ApiKey("token", "token", "header"))).apiInfo(new ApiInfoBuilder().build());
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder().securityReferences(defaultAuth()).operationSelector((value) -> true).build();
    }

    private List<SecurityReference> defaultAuth() {
        return Arrays.asList(new SecurityReference("token", new AuthorizationScope[] {
          new AuthorizationScope("", "描述信息")
        }));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableWYSwagger.class.getName());
        this.basePackage = attributes.get("basePackage").toString();
        this.name = attributes.get("name").toString();
    }
}
