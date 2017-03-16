package com.picanco.menuboard.json;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.jetbrains.annotations.NotNull;

public interface JacksonSerializer<T> {

    @NotNull JsonSerializer<T> getWriter();

    @NotNull JsonDeserializer<T> getReader();

    @NotNull Class<? extends T> getSerializingClass();

}
