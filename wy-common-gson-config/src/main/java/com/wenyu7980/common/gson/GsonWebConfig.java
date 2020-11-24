package com.wenyu7980.common.gson;

import com.google.gson.*;
import com.wenyu7980.common.gson.adapter.GsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author wenyu
 */
@Configuration
public class GsonWebConfig implements WebMvcConfigurer {
    private final Gson GSON;

    public GsonWebConfig() {
        this.GSON = GsonUtil.gsonBuilder()
          // 处理Swagger UI页面异常
          .registerTypeAdapter(Json.class, new JsonSerializer<Json>() {
              @Override
              public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
                  return JsonParser.parseString(src.value());
              }
          }).create();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new GsonHttpMessageConverter(this.GSON));
    }
}
