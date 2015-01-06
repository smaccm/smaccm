package com.rockwellcollins.atc.resolute.analysis.values;

import org.osate.aadl2.UnitLiteral;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class IntValue extends ResoluteValue {
	final private long value;

	public IntValue(long value) {
		this.value = value;
	}

	@Override
	public boolean isInt() {
		return true;
	}

	@Override
	public long getInt() {
		return value;
	}

	public long getScaledInt(UnitLiteral unit) {
		final double factor = (unit == null) ? 1.0 : unit.getAbsoluteFactor();
		return (long) (value / factor);
	}

	public double getScaledIntAsDouble(UnitLiteral unit) {
		final double factor = (unit == null) ? 1.0 : unit.getAbsoluteFactor();
		return (value / factor);
	}

	@Override
	public ResoluteType getType() {
		return BaseType.INT;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}

	@Override
	public int hashCode() {
		return new Long(value).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntValue) {
			IntValue iv = (IntValue) obj;
			return iv.value == value;
		}

		return false;
	}
}
