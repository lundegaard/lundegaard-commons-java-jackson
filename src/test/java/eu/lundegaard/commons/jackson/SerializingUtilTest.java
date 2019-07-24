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
import java.util.Date;
import static eu.lundegaard.commons.jackson.SerializingUtil.deserializeFromJson;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 */
public class SerializingUtilTest {

    @Test
    public void testSerializeToJsonString_FullfilledObject() {
        Object object = TEST_OBJECT_FULLFILLED;
        String result = SerializingUtil.serializeToJson(object);

        assertThatJson(result)
                .node("count").isEqualTo(TEST_OBJECT_FULLFILLED.getCount())
                .node("title").isEqualTo(TEST_OBJECT_FULLFILLED.getTitle())
                .node("categories").isEqualTo(TEST_OBJECT_FULLFILLED.getCategories())
                .node("interval").isEqualTo(TEST_OBJECT_FULLFILLED.getInterval());
    }

    @Test
    public void testSerializeToJsonString_LocalDateObjects() {
        Object object = TEST_OBJECT_LOCAL_DATE;
        SimpleDateFormat sdf = new SimpleDateFormat(SerializableTestWithLocalDate.DATE_PATTERN);

        String result = SerializingUtil.serializeToJson(object);

        assertThatJson(result)
                .node("date").isEqualTo(sdf.format(TEST_OBJECT_LOCAL_DATE.getDate()))
                .node("localDate")
                .isEqualTo("\"" + DateUtil.toDate(TEST_OBJECT_LOCAL_DATE.getLocalDate()).getTime() + "\"")
                .node("localDateTime")
                .isEqualTo("\"" + DateUtil.toDate(TEST_OBJECT_LOCAL_DATE.getLocalDateTime()).getTime() + "\"");
    }

    @Test
    public void testSerializeToJsonString_PrimitiveFilledObject() {
        Object object = TEST_OBJECT_PRIMITIVE;
        String result = SerializingUtil.serializeToJson(object);

        assertThatJson(result)
                .node("count").isEqualTo(TEST_OBJECT_PRIMITIVE.getCount())
                .node("title").isEqualTo(TEST_OBJECT_PRIMITIVE.getTitle())
                .node("categories").isEqualTo(TEST_OBJECT_PRIMITIVE.getCategories())
                .node("interval").isEqualTo(TEST_OBJECT_PRIMITIVE.getInterval());
    }

    @Test
    public void testSerializeToJsonString_NullObject() {
        Object object = null;
        String expResult = JSON_NULL_OBJECT;
        String result = SerializingUtil.serializeToJson(object);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeserializeFromJsonString_FullfilledObject() {
        String jsonString = JSON_FULL_OBJECT;
        Object expResult = TEST_OBJECT_FULLFILLED;
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeserializeFromJsonString_PrimitiveFilledObject() {
        String jsonString = JSON_PRIMITIVE_OBJECT;
        Object expResult = TEST_OBJECT_PRIMITIVE;
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeserializeToJsonString_LocalDateObjects() {
        String jsonString = JSON_LOCAL_DATE_OBJECT;

        SerializableTestWithLocalDate result = deserializeFromJson(jsonString, SerializableTestWithLocalDate.class);
        assertEquals(TEST_OBJECT_LOCAL_DATE, result);

    }


    @Test
    public void testDeserializeFromJsonString_NullObject() {
        String jsonString = JSON_NULL_OBJECT;
        Object expResult = null;
        SerializableTestObject result = deserializeFromJson(jsonString, SerializableTestObject.class);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserializeFromJsonString_NullParam() {
        deserializeFromJson(null, SerializableTestObject.class);
    }


    private static final SerializableTestObject TEST_OBJECT_FULLFILLED =
            new SerializableTestObject(4, "Full object", new SerialzableTestInterval(3, 6), "My category",
                    "Your category");
    private static final SerializableTestObject TEST_OBJECT_PRIMITIVE =
            new SerializableTestObject(4, "Full object", null, (String[]) null);
    private static final SerializableTestWithLocalDate TEST_OBJECT_LOCAL_DATE =
            new SerializableTestWithLocalDate(new Date(1469798798000L), DateUtil.toLocalDate(1469743200000L),
                    DateUtil.toLocalDateTime(1469798798108L));

    private static final String JSON_FULL_OBJECT =
            "{\"categories\":[\"My category\",\"Your category\"],\"class\":\"SerializableTestObject\",\"count\":4,\"interval\":{\"class\":\"SerialzableTestInterval\",\"from\":3,\"to\":6},\"title\":\"Full object\"}";
    private static final String JSON_PRIMITIVE_OBJECT =
            "{\"categories\":null,\"class\":\"SerializableTestObject\",\"count\":4,\"interval\":null,\"title\":\"Full object\"}";
    private static final String JSON_NULL_OBJECT = "null";
    private static final String JSON_LOCAL_DATE_OBJECT =
            "{\"date\":\"29.07.2016 15:26:38\",\"localDate\":\"1469743200000\",\"localDateTime\":\"1469798798108\"}";


}
