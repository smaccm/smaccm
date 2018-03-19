package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.rockwellcollins.atc.resolute.validation.ListType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ListValue extends ResoluteValue {

	final private List<ResoluteValue> values;

	public ListValue(List<? extends ResoluteValue> values) {
		if (values == null) {
			throw new IllegalArgumentException("Cannot create null ListValue");
		}

		this.values = Collections.unmodifiableList(new ArrayList<ResoluteValue>(values));
	}

	@Override
	public boolean isList() {
		return true;
	}

	@Override
	public List<ResoluteValue> getListValues() {
		return values;
	}

	@Override
	public ResoluteType getType() {
		if (values.isEmpty()) {
			throw new UnsupportedOperationException();
		} else {
			return new ListType(values.iterator().next().getType());
		}
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append("[");

		Iterator<ResoluteValue> iterator = values.iterator();
		while (iterator.hasNext()) {
			ResoluteValue element = iterator.next();
			text.append(element);
			if (iterator.hasNext()) {
				text.append(", ");
			}
		}

		text.append("]");
		return text.toString();
	}

	@Override
	public int hashCode() {
		return (37 * ListValue.class.hashCode()) + values.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ListValue) {
			ListValue lv = (ListValue) obj;
			return lv.values.equals(values);
		}

		return false;
	}

}
