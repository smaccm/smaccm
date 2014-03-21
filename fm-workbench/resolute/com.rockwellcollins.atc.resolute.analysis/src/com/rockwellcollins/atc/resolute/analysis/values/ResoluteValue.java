package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.SortedSet;

import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.resolute.validation.ResoluteType;

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
    
    public boolean isRange() {
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

    public SortedSet<ResoluteValue> getSet() {
        throw new IllegalArgumentException();
    }

    public NamedElement getNamedElement() {
        throw new IllegalArgumentException();
    }
    
    public RangeValue getRange() {
       throw new IllegalArgumentException();
    }
    
    public abstract ResoluteType getType();

    @Override
    public int compareTo(ResoluteValue other) {
        if (isBool() && other.isBool()) {
            return Boolean.compare(getBool(), other.getBool());
        } else if (isInt() && other.isInt()) {
            return Long.compare(getInt(), other.getInt());
        } else if (isReal() && other.isReal()) {
            return Double.compare(getReal(), other.getReal());
        } else if (isString() && other.isString()) {
            return String.CASE_INSENSITIVE_ORDER.compare(getString(), other.getString());
        } else if (isNamedElement() && other.isNamedElement()) {
            return String.CASE_INSENSITIVE_ORDER.compare(getNamedElement().getName(), other
                    .getNamedElement().getName());
        } else if (isSet() && other.isSet()) {
            return Integer.compare(getSet().hashCode(), other.getSet().hashCode());
        } else if (isRange() && other.isRange()) {
            RangeValue a = getRange();
            RangeValue b = other.getRange();
            int cmp = a.getMin().compareTo(b.getMin());
            if (cmp != 0) {
                return cmp;
            } else {
                return a.getMax().compareTo(b.getMax());
            }
        } else {
            throw new IllegalArgumentException("Unable to compare " + getClass().getName()
                    + " and " + other.getClass().getName());
        }
    }
}
