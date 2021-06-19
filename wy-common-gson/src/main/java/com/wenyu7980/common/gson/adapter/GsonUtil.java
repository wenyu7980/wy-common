package com.wenyu7980.common.gson.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author wenyu
 */
public class GsonUtil {
    private static final Gson GSON = gsonBuilder().create();

    public static GsonBuilder gsonBuilder() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
          .registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
    }

    public static Gson gson() {
        return GSON;
    }
}
