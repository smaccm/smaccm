package com.rockwellcollins.atc.resolute.validation;

import com.rockwellcollins.atc.resolute.resolute.Type;

public class ListType extends ResoluteType {

	final public ResoluteType elementType;

	public ListType(ResoluteType elementType) {
		if (elementType == null) {
			throw new IllegalArgumentException();
		}

		this.elementType = elementType;
	}

	@Override
	public String toString() {
		return "[" + (elementType == BaseType.ANY ? "" : elementType) + "]";
	}

	@Override
	public boolean subtypeOf(ResoluteType otherType) {
		if (this.equals(BaseType.FAIL) || otherType.equals(BaseType.FAIL)) {
			return true;
		}

		if (otherType instanceof ListType) {
			ListType lt = (ListType) otherType;
			return elementType.subtypeOf(lt.elementType);
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

		if (otherType instanceof ListType) {
			ListType lt = (ListType) otherType;
			return elementType.join(lt.elementType);
		}

		return null;
	}

	@Override
	public int hashCode() {
		return (37 * ListType.class.hashCode()) + elementType.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ListType) {
			ListType lt = (ListType) obj;
			return elementType.equals(BaseType.ANY) || lt.elementType.equals(BaseType.ANY)
					|| lt.elementType.equals(elementType);
		}

		return false;
	}

	@Override
	public boolean similar(Type otherType) {
		if (otherType instanceof com.rockwellcollins.atc.resolute.resolute.ListType) {
			return true;
		}
		return false;
	}

}
