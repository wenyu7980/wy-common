package com.wenyu7980.common.json.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间转换
 * @author wenyu
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    /** 日期格式器 */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter FORMAT_SHORT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
        String dateTime = json.getAsJsonPrimitive().getAsString();
        if (dateTime.length() == 0) {
            return null;
        } else if (dateTime.length() > 19) {
            dateTime = dateTime.substring(0, 19);
        } else if (dateTime.length() == 10) {
            return LocalDateTime.parse(dateTime, FORMAT_SHORT);
        }
        return LocalDateTime.parse(dateTime, FORMAT);
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(FORMAT));
    }
}
