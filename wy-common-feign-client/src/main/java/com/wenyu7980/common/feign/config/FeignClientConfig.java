package com.wenyu7980.common.feign.config;

import com.google.gson.Gson;
import com.wenyu7980.common.exceptions.ErrorResponseBody;
import com.wenyu7980.common.exceptions.code401.InsufficientException;
import com.wenyu7980.common.exceptions.code403.LoginFailException;
import com.wenyu7980.common.exceptions.code404.NotFoundException;
import com.wenyu7980.common.exceptions.code500.SystemException;
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
                if (response.status() == 404) {
                    if (body.getCode() == 1) {
                        return new NotFoundException(body.getMessage());
                    }
                }
                if (response.status() == 403) {
                    if (body.getCode() == 1) {
                        return new LoginFailException(body.getMessage());
                    }
                }
                if (response.status() == 401) {
                    if (body.getCode() == 1) {
                        return new InsufficientException(body.getMessage());
                    }
                }
                if (response.status() == 500) {
                    if (body.getCode() == 1) {
                        return new SystemException(body.getMessage());
                    }
                }
                throw new SystemException(body.getMessage());
            } catch (IOException exception) {
                return exception;
            }
        };
    }
}
