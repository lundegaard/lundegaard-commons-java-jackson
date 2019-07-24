/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.lundegaard.commons.util.DateUtil;


/**
 * Class provides common creation and configuration of objectMapper.
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 */
public class ObjectMapperFactory {

    public static ObjectMapper createObjectMapper() {
        return withTimeZone(TimeZone.getDefault());
    }

    public static ObjectMapper withTimeZone(TimeZone zone) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(dateSupportModule())
                .setTimeZone(zone);

        return mapper;
    }

    private static SimpleModule dateSupportModule() {
        Jackson8Module module = new Jackson8Module();
        module.addStringSerializer(LocalDateTime.class, ObjectMapperFactory::getLocalDateTimeSerializerFunction);
        module.addStringDeserializer(LocalDateTime.class, ObjectMapperFactory::getLocalDateTimeDeserializerFunction);
        module.addStringSerializer(LocalDate.class, ObjectMapperFactory::getLocalDateSerializerFunction);
        module.addStringDeserializer(LocalDate.class, ObjectMapperFactory::getLocalDateDeserializerFunction);

        return module;
    }

    private static String getLocalDateTimeSerializerFunction(LocalDateTime val) {
        return String.valueOf(DateUtil.toDate(val).getTime());
    }

    private static LocalDateTime getLocalDateTimeDeserializerFunction(String val) {
        return DateUtil.toLocalDateTime(Long.valueOf(val));
    }

    private static String getLocalDateSerializerFunction(LocalDate val) {
        return String.valueOf(DateUtil.toDate(val).getTime());
    }

    private static LocalDate getLocalDateDeserializerFunction(String val) {
        return DateUtil.toLocalDate(Long.valueOf(val));
    }



}
