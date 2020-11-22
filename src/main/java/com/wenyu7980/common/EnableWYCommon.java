package com.wenyu7980.common;

import com.wenyu7980.common.exceptions.EnableException;
import com.wenyu7980.common.gson.EnableGson;
import com.wenyu7980.common.swagger.EnableWYSwagger;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableGson
@EnableException
@EnableWYSwagger
public @interface EnableWYCommon {
    @AliasFor(annotation = EnableWYSwagger.class) String name() default "";

    @AliasFor(annotation = EnableWYSwagger.class) String basePackage() default "";
}
