package com.wenyu7980.common.feign.config;

import com.google.gson.Gson;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.wenyu7980.common.exceptions.ErrorResponseBody;
import com.wenyu7980.common.exceptions.ExceptionUtil;
import com.wenyu7980.common.gson.adapter.GsonUtil;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author wenyu
 */
public class FeignClientConfig {
    public Gson GSON = GsonUtil.gson();

    @Bean
    public Encoder encoder() {
        return new SpringEncoder(() -> new HttpMessageConverters(new GsonHttpMessageConverter(GSON)));
    }

    @Bean
    public Decoder decoder() {
        return (response, type) -> {
            if (response.body() == null) {
                return null;
            }
            Reader reader = response.body().asReader();
            return GSON.fromJson(reader, type);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            try {
                Reader reader = response.body().asReader();
                ErrorResponseBody body = GSON.fromJson(reader, ErrorResponseBody.class);
                return new HystrixBadRequestException(body.getMessage(), ExceptionUtil.getThrowable(body));
            } catch (IOException exception) {
                return new HystrixBadRequestException(exception.getMessage(), exception);
            }
        };
    }
}
