package com.wenyu7980.common.exceptions;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author wenyu
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ExceptionConfig.class)
public @interface EnableException {
}
