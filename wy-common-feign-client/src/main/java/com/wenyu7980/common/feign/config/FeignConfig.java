package com.wenyu7980.common.feign.config;

import com.google.gson.Gson;
import com.wenyu7980.common.exceptions.code404.NotFoundException;
import com.wenyu7980.common.gson.adapter.GsonUtil;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 *
 * @author wenyu
 */
public class FeignConfig {
    public Gson GSON = GsonUtil.gson();

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
                if (response.status() == 404) {
                    return new NotFoundException("");
                }
                return null;
            }
        };
    }
}
