package com.wenyu7980.common;

import com.wenyu7980.common.exceptions.ExceptionConfig;
import com.wenyu7980.common.gson.GsonWebConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author wenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ GsonWebConfig.class, ExceptionConfig.class })
public @interface EnableWYCommon {
}
