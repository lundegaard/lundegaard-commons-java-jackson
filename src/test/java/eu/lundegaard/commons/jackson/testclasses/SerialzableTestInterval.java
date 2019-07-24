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
public class SerialzableTestInterval implements Serializable {

    private static final long serialVersionUID = 1L;

    private int from;
    private int to;

    public SerialzableTestInterval() {}

    public SerialzableTestInterval(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SerialzableTestInterval other = (SerialzableTestInterval) obj;
        if (this.from != other.from) {
            return false;
        }
        if (this.to != other.to) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.from;
        hash = 83 * hash + this.to;
        return hash;
    }

}
