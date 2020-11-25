package com.wenyu7980.common.feign.config;

import com.google.gson.Gson;
import com.wenyu7980.common.exceptions.ErrorResponseBody;
import com.wenyu7980.common.exceptions.code404.NotFoundException;
import com.wenyu7980.common.exceptions.code500.SystemException;
import com.wenyu7980.common.gson.adapter.GsonUtil;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 *
 * @author wenyu
 */
public class FeignClientConfig {
    public Gson GSON = GsonUtil.gson();

    @Bean
    public Decoder decoder() {
        return new Decoder() {
            @Override
            public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
                if (response.status() == 200) {
                    if (response.body() == null) {
                        return null;
                    }
                    Reader reader = response.body().asReader();
                    return GSON.fromJson(reader, type);
                }
                Reader reader = response.body().asReader();
                ErrorResponseBody body = GSON.fromJson(reader, ErrorResponseBody.class);
                if (response.status() == 404) {
                    if (body.getCode() == 1) {
                        throw new NotFoundException(body.getMessage());
                    }
                }
                if (response.status() == 500) {
                    if (body.getCode() == 1) {
                        throw new SystemException(body.getMessage());
                    }
                }
                throw new SystemException(body.getMessage());
            }
        };
    }
}
