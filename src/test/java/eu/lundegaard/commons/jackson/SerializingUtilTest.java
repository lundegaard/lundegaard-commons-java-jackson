/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson;

import eu.lundegaard.commons.jackson.testclasses.SerializableTestObject;
import eu.lundegaard.commons.jackson.testclasses.SerializableTestWithLocalDate;
import eu.lundegaard.commons.jackson.testclasses.SerialzableTestInterval;
import eu.lundegaard.commons.util.DateUtil;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static eu.lundegaard.commons.jackson.SerializingUtil.deserializeFromJson;
import static eu.lundegaard.commons.jackson.SerializingUtil.serializeToJson;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 * @author Ales Rybak (ales.rybak@lundegaard.eu)
 */
public class SerializingUtilTest {

    @Test
    public void testSerializeToJsonString_FullfilledObject() {
        // Given
        Object object = TEST_OBJECT_FULLFILLED;

        // When
        String result = serializeToJson(object);

        // Then
        assertThatJson(result)
                .node("count").isEqualTo(TEST_OBJECT_FULLFILLED.getCount())
                .node("title").isEqualTo(TEST_OBJECT_FULLFILLED.getTitle())
                .node("categories").isEqualTo(TEST_OBJECT_FULLFILLED.getCategories())
                .node("interval").isEqualTo(TEST_OBJECT_FULLFILLED.getInterval());
    }

    @Test
    public void testSerializeToJsonString_LocalDateObjects() {
        // Given
        Object object = TEST_OBJECT_LOCAL_DATE;
        SimpleDateFormat sdf = new SimpleDateFormat(SerializableTestWithLocalDate.DATE_PATTERN);

        // When
        String result = serializeToJson(object);

        // Then
        assertThatJson(result)
                .node("date").isEqualTo(sdf.format(TEST_OBJECT_LOCAL_DATE.getDate()))
                .node("localDate").isEqualTo(TEST_OBJECT_LOCAL_DATE.getLocalDate().toString())
                .node("localDateTime").isEqualTo(TEST_OBJECT_LOCAL_DATE.getLocalDateTime().toString());
    }

    @Test
    public void testSerializeToJsonString_PrimitiveFilledObject() {
        // Given
        Object object = TEST_OBJECT_PRIMITIVE;

        // When
        String result = serializeToJson(object);

        // Then
        assertThatJson(result)
                .node("count").isEqualTo(TEST_OBJECT_PRIMITIVE.getCount())
                .node("title").isEqualTo(TEST_OBJECT_PRIMITIVE.getTitle())
                .node("categories").isEqualTo(TEST_OBJECT_PRIMITIVE.getCategories())
                .node("interval").isEqualTo(TEST_OBJECT_PRIMITIVE.getInterval());
    }

    @Test
    public void testSerializeToJsonString_NullObject() {
        // Given
        Object object = null;
        String expResult = JSON_NULL_OBJECT;

        // When
        String result = serializeToJson(object);

        // Then
        assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void testDeserializeFromJsonString_FullfilledObject() {
        // Given
        String jsonString = JSON_FULL_OBJECT;
        Object expResult = TEST_OBJECT_FULLFILLED;

        // When
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);

        // Then
        assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void testDeserializeFromJsonString_PrimitiveFilledObject() {
        // Given
        String jsonString = JSON_PRIMITIVE_OBJECT;
        Object expResult = TEST_OBJECT_PRIMITIVE;

        // When
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);

        // Then
        assertThat(result).isEqualTo(expResult);
    }

    @Test
    public void testDeserializeToJsonString_LocalDateObjects() {
        // Given
        String jsonString = JSON_LOCAL_DATE_OBJECT;

        // When
        SerializableTestWithLocalDate result = deserializeFromJson(jsonString, SerializableTestWithLocalDate.class);

        // Then
        assertThat(result).isEqualTo(TEST_OBJECT_LOCAL_DATE);

    }

    @Test
    public void testDeserializeFromJsonString_NullObject() {
        // Given
        String jsonString = JSON_NULL_OBJECT;
        Object expResult = null;

        // When
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);

        // Then
        assertThat(result).isEqualTo(expResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserializeFromJsonString_NullParam() {
        deserializeFromJson(null, SerializableTestObject.class);
    }

    @Test
    public void testSerDe_FullfilledObject() {
        // Gvien
        SerializableTestObject object = TEST_OBJECT_FULLFILLED;

        // When
        String json = serializeToJson(object);
        SerializableTestObject result = deserializeFromJson(json, SerializableTestObject.class);

        // Then
        assertThat(result).isEqualTo(object);
    }

    @Test
    public void testSerDe_LocalDateObjects() {
        // Gvien
        SerializableTestWithLocalDate object = TEST_OBJECT_LOCAL_DATE;

        // When
        String json = serializeToJson(object);
        SerializableTestWithLocalDate result = deserializeFromJson(json, SerializableTestWithLocalDate.class);

        // Then
        assertThat(result).isEqualTo(object);
    }

    @Test
    public void testSerDe_PrimitiveFilledObject() {
        // Gvien
        SerializableTestObject object = TEST_OBJECT_PRIMITIVE;

        // When
        String json = serializeToJson(object);
        SerializableTestObject result = deserializeFromJson(json, SerializableTestObject.class);

        // Then
        assertThat(result).isEqualTo(object);
    }

    @Test
    public void testSerDe_NullObject() {
        // Gvien
        Object object = null;

        // When
        String json = serializeToJson(object);
        Object result = deserializeFromJson(json, Object.class);

        // Then
        assertThat(result).isNull();
    }


    private static final SerializableTestObject TEST_OBJECT_FULLFILLED =
            new SerializableTestObject(4, "Full object", new SerialzableTestInterval(3, 6), "My category",
                    "Your category");
    private static final SerializableTestObject TEST_OBJECT_PRIMITIVE =
            new SerializableTestObject(4, "Full object", null, (String[]) null);
    private static final SerializableTestWithLocalDate TEST_OBJECT_LOCAL_DATE = new SerializableTestWithLocalDate(
            DateUtil.toDate(LocalDateTime.parse("2019-07-23T15:58:40")),
            LocalDate.parse("2019-07-23"),
            LocalDateTime.parse("2019-07-23T15:58:40.107"));

    private static final String JSON_FULL_OBJECT =
            "{\"categories\":[\"My category\",\"Your category\"],\"class\":\"SerializableTestObject\",\"count\":4,\"interval\":{\"class\":\"SerialzableTestInterval\",\"from\":3,\"to\":6},\"title\":\"Full object\"}";
    private static final String JSON_PRIMITIVE_OBJECT =
            "{\"categories\":null,\"class\":\"SerializableTestObject\",\"count\":4,\"interval\":null,\"title\":\"Full object\"}";
    private static final String JSON_NULL_OBJECT = "null";
    private static final String JSON_LOCAL_DATE_OBJECT =
            "{\"date\":\"23.07.2019 15:58:40\",\"localDate\":\"2019-07-23\",\"localDateTime\":\"2019-07-23T15:58:40.107Z\"}";

}
