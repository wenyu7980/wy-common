package com.wenyu7980.common.feign.config;

import feign.Request;
import org.springframework.context.annotation.Bean;

/**
 * stater的Feign Client config
 * @author wenyu
 */
public class FeignClientStarterConfig {
    @Bean
    public Request.Options options() {
        /** 超时时间设置为10s */
        return new Request.Options(10 * 1000, 10 * 1000);
    }
}
