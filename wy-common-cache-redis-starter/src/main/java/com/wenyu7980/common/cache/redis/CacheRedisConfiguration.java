package com.wenyu7980.common.cache.redis;

import com.wenyu7980.common.converter.kryo.KryoConverterUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.nio.ByteBuffer;
import java.time.Duration;

/**
 *
 * @author wenyu
 */
@EnableCaching
@Configuration
public class CacheRedisConfiguration {
    @Value("${application.cache.timeout:300}")
    private Long timeout;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
          .entryTtl(Duration.ofSeconds(timeout))
          .serializeValuesWith(new RedisSerializationContext.SerializationPair<Object>() {
              @Override
              public RedisElementReader<Object> getReader() {
                  return buffer -> KryoConverterUtils.read(buffer.array());

              }

              @Override
              public RedisElementWriter<Object> getWriter() {
                  return element -> ByteBuffer.wrap(KryoConverterUtils.write(element));
              }
          }).disableCachingNullValues();
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(configuration).build();
    }
}
