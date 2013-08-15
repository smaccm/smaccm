package com.rockwellcollins.atc.resolute.validation;

public abstract class ResoluteType {
    public abstract boolean subtypeOf(ResoluteType otherType);

    public abstract ResoluteType join(ResoluteType otherType);
}
