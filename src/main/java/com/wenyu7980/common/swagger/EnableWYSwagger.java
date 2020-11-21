package com.wenyu7980.common.swagger;

import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.lang.annotation.*;

/**
 * @author wenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableOpenApi
@Import(SwaggerConfig.class)
public @interface EnableWYSwagger {
    String name();

    String basePackage();
}
