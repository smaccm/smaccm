package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.Set;

import org.osate.aadl2.NamedElement;

public abstract class ResoluteValue implements Comparable<ResoluteValue> {
    public boolean isBool() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isReal() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isSet() {
        return false;
    }

    public boolean isNamedElement() {
        return false;
    }

    public boolean isFail() {
        return false;
    }

    public boolean getBool() {
        throw new IllegalArgumentException();
    }

    public long getInt() {
        throw new IllegalArgumentException();
    }

    public double getReal() {
        throw new IllegalArgumentException();
    }

    public String getString() {
        throw new IllegalArgumentException();
    }

    public Set<ResoluteValue> getSet() {
        throw new IllegalArgumentException();
    }

    public NamedElement getNamedElement() {
        throw new IllegalArgumentException();
    }

    @Override
    public int compareTo(ResoluteValue other) {
        if (isBool() && other.isBool()) {
            return Boolean.compare(getBool(), other.getBool());
        } else if (isInt() && other.isInt()) {
            return Long.compare(getInt(), other.getInt());
        } else if (isReal() && other.isReal()) {
            return Double.compare(getReal(), other.getReal());
        } else {
            throw new IllegalArgumentException("Unable to compare " + getClass().getName()
                    + " and " + other.getClass().getName());
        }
    }
}
