/*
 * Copyright (C) 2019 Lundegaard a.s., All Rights Reserved
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 */
package eu.lundegaard.commons.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.TimeZone;


/**
 * Class provides common creation and configuration of objectMapper.
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 * @author Ales Rybak (ales.rybak@lundegaard.eu)
 */
public final class ObjectMapperFactory {

    private ObjectMapperFactory() {
        // private constructor for util class
    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());

        mapper.setTimeZone(TimeZone.getDefault());

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        return mapper;
    }

}
