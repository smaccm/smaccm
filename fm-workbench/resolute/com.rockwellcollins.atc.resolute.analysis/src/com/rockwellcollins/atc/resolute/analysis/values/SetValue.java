package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetValue extends ResoluteValue {
	final private Set<ResoluteValue> values;

	public SetValue(Set<ResoluteValue> values) {
		if (values == null) {
			throw new IllegalArgumentException("Cannot create null SetValue");
		}

		this.values = Collections.unmodifiableSet(values);
	}
	
	public SetValue(List<ResoluteValue> list) {
		if (list == null) {
			throw new IllegalArgumentException("Cannot create null SetValue");
		}

		this.values = Collections.unmodifiableSet(new HashSet<ResoluteValue>(list));
	}

	@Override
	public boolean isSet() {
		return true;
	}

	@Override
	public Set<ResoluteValue> getSet() {
		return values;
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