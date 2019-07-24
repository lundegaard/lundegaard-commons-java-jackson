/*
 * Copyright (C) Lundegaard a.s. 2018 - All Rights Reserved
 *
 * Proprietary and confidential. Unauthorized copying of this file, via any
 * medium is strictly prohibited.
 */
/**
 * All rights reserved Lundegaard s.r.o TODO license agreement.
 */
package eu.lundegaard.commons.jackson.testclasses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Object for testing serialization of localDates
 * 
 * @author Jiri Kadlec (jiri.kadlec@lundegaard.eu)
 */
public class SerializableTestWithLocalDate {

    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";

    @JsonFormat(pattern = DATE_PATTERN)
    private Date date;
    private LocalDate localDate;
    private LocalDateTime localDateTime;


    public SerializableTestWithLocalDate() {}

    public SerializableTestWithLocalDate(Date date, LocalDate localDate, LocalDateTime localDateTime) {
        this.date = date;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SerializableTestWithLocalDate that = (SerializableTestWithLocalDate) o;

        if (!date.equals(that.date)) {
            return false;
        }

        if (!localDate.equals(that.localDate)) {
            return false;
        }

        return localDateTime.equals(that.localDateTime);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SerializableTestWithLocalDate{" +
            "date=" + date +
            ", localDate=" + localDate +
            ", localDateTime=" + localDateTime +
            '}';
    }
}
