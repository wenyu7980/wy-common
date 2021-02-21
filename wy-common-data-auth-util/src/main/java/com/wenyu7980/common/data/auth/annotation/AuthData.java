package com.wenyu7980.common.data.auth.annotation;

import java.lang.annotation.*;

/**
 * 数据权限
 * 在类上：代表改类的资源类型
 * 在属性上：校验比较对象
 * 在方法上：方法返回值数据权限校验
 *
 * @author wenyu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
public @interface AuthData {
    /**
     * 资源名
     * @return
     */
    String resource() default "";
}
