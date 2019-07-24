/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.commons.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static eu.lundegaard.commons.util.ValidateUtil.validateNotNull;

/**
 * Util class for simple serializing and deserializing objects to/from strings.
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 * @author Jakub Kohout (jakub.kohout@lundegaard.eu)
 * @author Ales Rybak (ales.rybak@lundegaard.eu)
 */
public class SerializingUtil {

    private static final ObjectMapper MAPPER = ObjectMapperFactory.createObjectMapper();


    private SerializingUtil() {
        // private constructor for util class
    }


    public static String serializeToJson(Object entity) {
        try {

            return MAPPER.writeValueAsString(entity);

        } catch (JsonProcessingException e) {
            throw new SerializationException("Cannot convert object to JSON.", e);
        }
    }

    public static <T> T deserializeFromJson(String json, Class<T> objectClass) {
        validateNotNull(json);

        try {

            return MAPPER.readValue(json, objectClass);

        } catch (IOException e) {
            throw new SerializationException("Cannot convert JSON string into class: " + objectClass.getName(), e);
        }
    }


    /**
     * Exception is thrown if some error occurs during serialization or
     * deserialization of the object.
     */
    public static class SerializationException extends ApplicationException {

        private static final long serialVersionUID = 1L;

        public SerializationException(String message, Throwable cause) {
            super(message, cause);
        }

        public SerializationException(String message) {
            super(message);
        }
    }
}
