package edu.uah.rsesc.aadlsimulator.xtext.util;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;

public interface ReferenceTypeResolver {
	/**
	 * 
	 * @param reference
	 * @return the type of the reference. null if the reference is invalid.
	 */
	ResultType getElementReferenceType(final ElementRefExpression reference);
	
	ResultType getConstReferenceType(final ConstRefExpression reference);
}
