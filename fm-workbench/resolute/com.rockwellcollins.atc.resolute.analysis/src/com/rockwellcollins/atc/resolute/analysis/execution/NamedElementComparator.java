package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.Comparator;

import org.osate.aadl2.NamedElement;

public class NamedElementComparator implements Comparator<NamedElement> {
	@Override
	public int compare(NamedElement arg0, NamedElement arg1) {
		return arg0.qualifiedName().compareToIgnoreCase(arg1.qualifiedName());
	}
}
