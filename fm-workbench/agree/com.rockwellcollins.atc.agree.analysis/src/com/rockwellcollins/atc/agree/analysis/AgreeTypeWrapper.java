package com.rockwellcollins.atc.agree.analysis;

import java.util.Objects;

import com.rockwellcollins.atc.agree.agree.ArrayType;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.Type;

public class AgreeTypeWrapper {
	protected Type type;

	public AgreeTypeWrapper(Type type) {
		this.type = Objects.requireNonNull(type);
	}

	public Type getType() {
		return type;
	}

	@Override
	public int hashCode() {

		if (type instanceof PrimType) {
			PrimType p = (PrimType)type;
			int hash = Objects.hash(p.getHighNeg(), p.getLowNeg(), p.getName(), p.getRangeHigh(), p.getRangeLow());
			return hash;
		}

		if (type instanceof ArrayType) {
			ArrayType a = (ArrayType) type;
			return Objects.hash(a.getSize(), a.getStem());
		}

		if (type instanceof DoubleDotRef) {
			return Objects.hash(((DoubleDotRef) type).getElm());
		}

		throw new AgreeException("ERROR: unhandled type in hashCode");
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AgreeTypeWrapper)) {
			return false;
		}

		AgreeTypeWrapper other = (AgreeTypeWrapper) obj;
		if (!(Objects.equals(type.getClass(), other.type.getClass()))) {
			return false;
		}

		return equalTypes(type, other.type);
	}

	protected static boolean equalTypes(Type left, Type right) {
		assert (left.getClass().equals(right.getClass()));

		if (left instanceof PrimType) {
			return equalsPrimType((PrimType) left, (PrimType) right);
		}

		if (left instanceof DoubleDotRef) {
			return equalsDoubleDotRef((DoubleDotRef) left, (DoubleDotRef) right);
		}

		if (left instanceof ArrayType) {
			return equalsArrayType((ArrayType) left, (ArrayType) right);
		}

		throw new AgreeException("ERROR: unhandled type in equalTypes");
	}

	private static boolean equalsDoubleDotRef(DoubleDotRef left, DoubleDotRef right) {
		return Objects.equals(left.getElm(), right.getElm());
	}

	private static boolean equalsPrimType(PrimType left, PrimType right) {
		return Objects.equals(left.getHighNeg(), right.getHighNeg())
				&& Objects.equals(left.getLowNeg(), right.getLowNeg())
				&& Objects.equals(left.getName(), right.getName())
				&& Objects.equals(left.getRangeHigh(), right.getRangeHigh())
				&& Objects.equals(left.getRangeLow(), right.getRangeLow());
	}

	public static boolean equalsArrayType(ArrayType left, ArrayType right) {
		return Objects.equals(left.getSize(), right.getSize()) && Objects.equals(left.getStem(), right.getStem());
	}

}
