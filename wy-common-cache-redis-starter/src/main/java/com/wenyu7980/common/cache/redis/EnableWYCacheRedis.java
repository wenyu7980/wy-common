package com.wenyu7980.common.cache.redis;

import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * redis cache
 * @author wenyu
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheRedisConfiguration.class)
public @interface EnableWYCacheRedis {
}
