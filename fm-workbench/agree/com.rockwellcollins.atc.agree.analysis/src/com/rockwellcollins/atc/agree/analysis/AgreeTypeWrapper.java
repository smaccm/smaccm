package com.rockwellcollins.atc.agree.analysis;


public class AgreeTypeWrapper {
//	protected TypeDef type;
//
//	public AgreeTypeWrapper(TypeDef type) {
//		this.type = Objects.requireNonNull(type);
//	}
//
//	public TypeDef getTypeDef() {
//		return type;
//	}
//
//	@Override
//	public int hashCode() {
//
//		if (type instanceof AgreeTypeSystem.Prim) {
//			return Objects.hash(type);
//		} else if (type instanceof AgreeTypeSystem.RangeIntTypeDef) {
//			int hash = Objects.hash(((AgreeTypeSystem.RangeIntTypeDef) type).name,
//					((AgreeTypeSystem.RangeIntTypeDef) type).low,
//					((AgreeTypeSystem.RangeIntTypeDef) type).high);
//			return hash;
//		} else if (type instanceof AgreeTypeSystem.RangeRealTypeDef) {
//			return Objects.hash(((AgreeTypeSystem.RangeRealTypeDef) type).name,
//					((AgreeTypeSystem.RangeRealTypeDef) type).low,
//					((AgreeTypeSystem.RangeRealTypeDef) type).high);
//
//		} else if (type instanceof ArrayTypeDef) {
//			ArrayTypeDef a = (ArrayTypeDef) type;
//			return Objects.hash(((ArrayTypeDef) type).name, ((ArrayTypeDef) type).baseType,
//					((ArrayTypeDef) type).dimension);
//		} else if (type instanceof RecordTypeDef) {
//			return Objects.hash(((RecordTypeDef) type).name, ((RecordTypeDef) type).fields);
//		} else if (type instanceof EnumTypeDef) {
//			return Objects.hash(((EnumTypeDef) type).name, ((EnumTypeDef) type).values);
//
//		}
//
//		throw new AgreeException("ERROR: unhandled type in hashCode");
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (!(obj instanceof AgreeTypeWrapper)) {
//			return false;
//		}
//
//		AgreeTypeWrapper other = (AgreeTypeWrapper) obj;
//		if (!(Objects.equals(type.getClass(), other.type.getClass()))) {
//			return false;
//		}
//
//		return AgreeTypeSystem.typesEqual(type, other.type);
//	}
//
//

}
