package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;
import com.rockwellcollins.atc.resolute.validation.SetType;

public class SetValue extends ResoluteValue {
    // Use a SortedSet to ensure deterministic ordering
    final private SortedSet<ResoluteValue> values;

    public SetValue(Set<? extends ResoluteValue> values) {
        if (values == null) {
            throw new IllegalArgumentException("Cannot create null SetValue");
        }

        this.values = Collections.unmodifiableSortedSet(new TreeSet<ResoluteValue>(values));
    }

    public SetValue(List<? extends ResoluteValue> list) {
        if (list == null) {
            throw new IllegalArgumentException("Cannot create null SetValue");
        }

        this.values = Collections.unmodifiableSortedSet(new TreeSet<ResoluteValue>(list));
    }

    @Override
    public boolean isSet() {
        return true;
    }

    @Override
    public SortedSet<ResoluteValue> getSet() {
        return values;
    }

    @Override
    public ResoluteType getType() {
        if (values.isEmpty()) {
            throw new NotImplementedException();
        } else {
            return new SetType(values.iterator().next().getType());
        }
    }
    
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("{");

        Iterator<ResoluteValue> iterator = values.iterator();
        while (iterator.hasNext()) {
            ResoluteValue element = iterator.next();
            text.append(element);
            if (iterator.hasNext()) {
                text.append(", ");
            }
        }

        text.append("}");
        return text.toString();
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SetValue) {
            SetValue sv = (SetValue) obj;
            return sv.values.equals(values);
        }

        return false;
    }
}