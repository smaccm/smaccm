package com.rockwellcollins.atc.agree.validation;

//public class AgreeType {
//
//	public static class Name extends AgreeType {
//
//		private String name;
//
//		public Name(String name) {
//			this.name = name;
//		}
//
//		@Override
//		public boolean equals(Object o) {
//			if (o instanceof Name) {
//				Name other = (Name) o;
//				return name.equals(other.name);
//			}
//			return false;
//		}
//
//		@Override
//		public int hashCode() {
//			return name.hashCode();
//		}
//
//		@Override
//		public String toString() {
//			return name;
//		}
//
//	}
//
//	public static class Array extends AgreeType {
//		private AgreeType stem;
//		private int size;
//
//		public Array(AgreeType stem, int size) {
//			this.stem = stem;
//			this.size = size;
//		}
//
//		public int getSize() {
//			return size;
//		}
//
//		@Override
//		public boolean equals(Object o) {
//			if (o instanceof AgreeType.Array) {
//				AgreeType.Array other = (AgreeType.Array) o;
//				return this.stem.equals(other.stem) && this.size == other.size;
//			}
//			return false;
//		}
//
//		@Override
//		public int hashCode() {
//			return this.toString().hashCode();
//		}
//
//		@Override
//		public String toString() {
//			return stem.toString() + "[" + size + "]";
//		}
//	}
//
//	public static final AgreeType BOOL = new Name("bool");
//	public static final AgreeType INT = new Name("int");
//	public static final AgreeType REAL = new Name("real");
//	public static final AgreeType ERROR = new Name("<error>");
//
//
//	public boolean isPrimitive() {
//		return equals(BOOL) || equals(INT) || equals(REAL);
//	}
//
//}
