/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
package eu.lundegaard.commons.jackson.testclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 */
public class SerializableTestObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int count;
    private String title;
    private List<String> categories;
    private SerialzableTestInterval interval;

    public SerializableTestObject() {}

    public SerializableTestObject(int count, String title, SerialzableTestInterval interval, String... categories) {
        this.count = count;
        this.title = title;
        this.interval = interval;
        if (categories != null) {
            this.categories = new ArrayList<String>(Arrays.asList(categories));
        }
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SerialzableTestInterval getInterval() {
        return interval;
    }

    public void setInterval(SerialzableTestInterval interval) {
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SerializableTestObject other = (SerializableTestObject) obj;
        if (this.count != other.count) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if (this.categories != other.categories
                && (this.categories == null || !this.categories.equals(other.categories))) {
            return false;
        }
        if (this.interval != other.interval && (this.interval == null || !this.interval.equals(other.interval))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.count;
        hash = 13 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 13 * hash + (this.categories != null ? this.categories.hashCode() : 0);
        hash = 13 * hash + (this.interval != null ? this.interval.hashCode() : 0);
        return hash;
    }

}
