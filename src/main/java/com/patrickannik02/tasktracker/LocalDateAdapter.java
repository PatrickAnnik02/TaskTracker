package com.patrickannik02.tasktracker;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // Formato "yyyy-MM-dd"

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        // Java -> JSON: Convertimos la fecha a String (ej: "2025-12-05")
        return new JsonPrimitive(src.format(formatter));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // JSON -> Java: Leemos el String y lo convertimos a objeto LocalDate
        return LocalDate.parse(json.getAsString(), formatter);
    }
}