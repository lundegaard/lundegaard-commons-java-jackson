/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson.testclasses;

import java.io.Serializable;

/**
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 */
public class SerializableTestWithEnumObject extends SerializableTestObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Color titleColor;

    public SerializableTestWithEnumObject() {}

    public SerializableTestWithEnumObject(Color titleColor, int count, String title, SerialzableTestInterval interval,
            String... categories) {
        super(count, title, interval, categories);
        this.titleColor = titleColor;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public static enum Color {
        RED, GREEN, BLUE;
    }

}
