package com.rockwellcollins.atc.resolute.analysis.values;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteRecordValue extends ResoluteValue {
	private final Map<String, ? extends ResoluteValue> fields;
	
	public ResoluteRecordValue(Map<String, ? extends ResoluteValue> fields) {
		if (fields == null) {
			throw new IllegalArgumentException("Cannot create null ResoluteRecordValue");
		}
		
		this.fields = Collections.unmodifiableMap(new HashMap<>(fields));
	}
	
	public ResoluteValue getField(String fieldName) {
		return fields.get(fieldName);
	}
	
	@Override
	public ResoluteType getType() {
		return BaseType.RECORD;
	}
	
	@Override
	public String toString() {
		return fields.entrySet().stream().map(entry -> {
			return entry.getKey() + " => " + entry.getValue();
		}).collect(Collectors.joining(";", "[", "]"));
	}
	
	@Override
	public int hashCode() {
		return fields.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ResoluteRecordValue && ((ResoluteRecordValue)obj).fields.equals(fields);
	}
}