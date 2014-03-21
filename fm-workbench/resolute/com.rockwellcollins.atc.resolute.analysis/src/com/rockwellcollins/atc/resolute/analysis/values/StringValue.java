package com.rockwellcollins.atc.resolute.analysis.values;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class StringValue extends ResoluteValue {
    final private String value;

    public StringValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot create null StringValue");
        }

        this.value = value;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public ResoluteType getType() {
        return BaseType.STRING;
    }
    
    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringValue) {
            StringValue sv = (StringValue) obj;
            return sv.value.equalsIgnoreCase(value);
        }

        return false;
    }
}
