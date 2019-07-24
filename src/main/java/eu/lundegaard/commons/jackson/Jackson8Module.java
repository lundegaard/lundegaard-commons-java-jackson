/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson;

import java.io.IOException;
import java.util.function.Function;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * This class allows using lambda constructions for serialize and deserialize
 * functions in jackson.
 *
 * See {@code SpringLiferayResolversAutoconfiguration} in portlet lib for usage.
 */
public class Jackson8Module extends SimpleModule {

    public <T> void addStringSerializer(Class<T> cls, Function<T, String> serializeFunction) {
        JsonSerializer<T> jsonSerializer = new JsonSerializer<T>() {

            @Override
            public void serialize(T t, JsonGenerator jgen, SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException {
                String val = serializeFunction.apply(t);
                jgen.writeString(val);
            }
        };
        addSerializer(cls, jsonSerializer);
    }

    public <T> void addStringDeserializer(Class<T> cls, Function<String, T> deserializeFunction) {

        JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<T>() {

            @Override
            public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                    throws IOException, JsonProcessingException {
                String txt = jsonParser.getText();
                return deserializeFunction.apply(txt);
            }
        };

        addDeserializer(cls, jsonDeserializer);
    }

}
