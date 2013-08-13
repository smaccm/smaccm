package com.rockwellcollins.atc.resolute.analysis.values;

import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;

public class NamedElementValue extends ResoluteValue {
	final private NamedElement value;

	public NamedElementValue(NamedElement value) {
		if (value == null) {
			throw new IllegalArgumentException("Cannot create null NamedElementValue");

		}
		this.value = value;
	}

	@Override
	public boolean isNamedElement() {
		return true;
	}

	@Override
	public NamedElement getNamedElement() {
		return value;
	}

	@Override
	public String toString() {
		if (value instanceof ComponentInstance) {
			ComponentInstance ci = (ComponentInstance) value;
			ComponentClassifier cc = ci.getComponentClassifier();
			return value.getName() + " : " + cc.getQualifiedName();
		}

		return value.getQualifiedName();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NamedElementValue) {
			NamedElementValue nev = (NamedElementValue) obj;
			return nev.value.equals(value);
		}

		return false;
	}
}
