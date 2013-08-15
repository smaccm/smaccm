package com.rockwellcollins.atc.resolute.validation;

public class SetType extends ResoluteType {
    final public ResoluteType elementType;

    public SetType(ResoluteType elementType) {
        if (elementType == null) {
            throw new IllegalArgumentException();
        }

        this.elementType = elementType;
    }

    @Override
    public String toString() {
        return "{" + elementType + "}";
    }

    @Override
    public boolean subtypeOf(ResoluteType otherType) {
        if (this.equals(BaseType.FAIL) || otherType.equals(BaseType.FAIL)) {
            return true;
        }

        if (otherType instanceof SetType) {
            SetType st = (SetType) otherType;
            return elementType.subtypeOf(st.elementType);
        }

        return false;
    }

    @Override
    public ResoluteType join(ResoluteType otherType) {
        if (this.equals(BaseType.FAIL) || otherType.equals(BaseType.FAIL)) {
            return BaseType.FAIL;
        }

        if (this.equals(otherType)) {
            return this;
        }

        if (otherType instanceof SetType) {
            SetType st = (SetType) otherType;
            return elementType.join(st.elementType);
        }

        return null;
    }

    @Override
    public int hashCode() {
        return elementType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SetType) {
            SetType st = (SetType) obj;
            return st.elementType.equals(elementType);
        }

        return false;
    }
}
