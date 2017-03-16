package com.picanco.menuboard.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;

@Component
public class InstantSerializer implements JacksonSerializer<Instant> {

    private static final Collection<Instant> BOUNDLESS_INSTANTS = new HashSet<>(asList(null,
                                                                                       Instant.ofEpochMilli(0),
                                                                                       Instant.ofEpochMilli(Long.MAX_VALUE)));

    @NotNull
    @Override
    public JsonSerializer<Instant> getWriter() {
        return new JsonSerializer<Instant>() {
            @Override
            public void serialize(Instant instant,
                                  JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                if (BOUNDLESS_INSTANTS.contains(instant)) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeNumber(instant.toEpochMilli());
                }

            }
        };
    }

    @NotNull
    @Override
    public JsonDeserializer<Instant> getReader() {
        return new JsonDeserializer<Instant>() {
            @Override
            public Instant deserialize(JsonParser jsonParser,
                                       DeserializationContext deserializationContext) throws IOException {
                return Instant.ofEpochMilli(jsonParser.readValueAs(Long.class));
            }
        };
    }

    @NotNull
    @Override
    public Class<? extends Instant> getSerializingClass() {
        return Instant.class;
    }
}
