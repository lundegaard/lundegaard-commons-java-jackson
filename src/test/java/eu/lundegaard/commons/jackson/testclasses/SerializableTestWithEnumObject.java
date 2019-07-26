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
