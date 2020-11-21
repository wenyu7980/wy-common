package com.wenyu7980.common.json.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 * @author wenyu
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    /** 日期格式器 */
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
        String date = json.getAsJsonPrimitive().getAsString();
        if (date.length() == 0) {
            return null;
        } else if (date.length() > 10) {
            date = date.substring(0, 10);
        }
        return LocalDate.parse(date, FORMAT);
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(FORMAT));
    }
}
