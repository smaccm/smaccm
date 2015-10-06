package com.rockwellcollins.atc.resolute.validation;

import com.rockwellcollins.atc.resolute.resolute.Type;

public abstract class ResoluteType {
	public abstract boolean subtypeOf(ResoluteType otherType);

	public abstract ResoluteType join(ResoluteType otherType);

	public abstract boolean similar(Type otherType);
}
