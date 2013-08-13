package com.rockwellcollins.atc.resolute.validation;


public class BaseType extends ResoluteType {
	public static final BaseType INT = new BaseType("int");
	public static final BaseType REAL = new BaseType("real");
	public static final BaseType STRING = new BaseType("string");
	public static final BaseType BOOL = new BaseType("bool");
	public static final BaseType FAIL = new BaseType("fail");

	public static final BaseType COMPONENT = new BaseType("component");
	public static final BaseType CONNECTION = new BaseType("connection");
	public static final BaseType PROPERTY = new BaseType("property");
	public static final BaseType AADL = new BaseType("aadl");

	public static final BaseType ABSTRACT = new BaseType("abstract");
	public static final BaseType BUS = new BaseType("bus");
	public static final BaseType DATA = new BaseType("data");
	public static final BaseType DEVICE = new BaseType("device");
	public static final BaseType MEMORY = new BaseType("memory");
	public static final BaseType PROCESSOR = new BaseType("processor");
	public static final BaseType PROCESS = new BaseType("process");
	public static final BaseType SUBPROGRAM_GROUP = new BaseType("subprogram_group");
	public static final BaseType SUBPROGRAM = new BaseType("subprogram");
	public static final BaseType SYSTEM = new BaseType("system");
	public static final BaseType THREAD_GROUP = new BaseType("thread_group");
	public static final BaseType THREAD = new BaseType("thread");
	public static final BaseType VIRTUAL_BUS = new BaseType("virtual_bus");
	public static final BaseType VIRTUAL_PROCESSOR = new BaseType("virtual_processor");

	final public String name;

	public BaseType(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		}

		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean subtypeOf(ResoluteType otherType) {
		if (this.equals(FAIL) || otherType.equals(FAIL)) {
			return true;
		}
		
		if (this.equals(otherType)) {
			return true;
		}

		if (otherType instanceof BaseType) {
			BaseType bt = (BaseType) otherType;
			switch (bt.name) {
			case "component":
				return subtypeOf(ABSTRACT) || subtypeOf(BUS) || subtypeOf(DATA)
						|| subtypeOf(DEVICE) || subtypeOf(MEMORY) || subtypeOf(PROCESSOR)
						|| subtypeOf(PROCESS) || subtypeOf(SUBPROGRAM_GROUP)
						|| subtypeOf(SUBPROGRAM) || subtypeOf(SYSTEM) || subtypeOf(THREAD_GROUP)
						|| subtypeOf(THREAD) || subtypeOf(VIRTUAL_BUS)
						|| subtypeOf(VIRTUAL_PROCESSOR);

			case "aadl":
				return subtypeOf(COMPONENT) || subtypeOf(CONNECTION) || subtypeOf(PROPERTY);

			default:
				return false;
			}
		}

		return false;
	}

	@Override
	public ResoluteType join(ResoluteType otherType) {
		if (this.equals(FAIL) || otherType.equals(FAIL)) {
			return FAIL;
		}
		
		if (this.equals(otherType)) {
			return this;
		}

		if (subtypeOf(COMPONENT) && otherType.subtypeOf(COMPONENT)) {
			return COMPONENT;
		}

		if (subtypeOf(AADL) && otherType.subtypeOf(AADL)) {
			return AADL;
		}

		return null;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BaseType) {
			BaseType bt = (BaseType) obj;
			return bt.name.equals(name);
		}

		return false;
	}
}
