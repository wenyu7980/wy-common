package com.wenyu7980.common.gson;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author wenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(GsonWebConfig.class)
public @interface EnableGson {
}
