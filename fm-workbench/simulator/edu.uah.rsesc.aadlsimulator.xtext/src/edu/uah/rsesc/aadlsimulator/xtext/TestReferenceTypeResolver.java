package edu.uah.rsesc.aadlsimulator.xtext;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.util.ReferenceTypeResolver;
import edu.uah.rsesc.aadlsimulator.xtext.util.ResultType;

// ReferenceResolver design for testing. Use the starting letter of the last ID of the reference to determine type.
public class TestReferenceTypeResolver implements ReferenceTypeResolver {		
	@Override
	public ResultType getElementReferenceType(final ElementRefExpression reference) {
		return getReferenceType(reference.getIds().get(reference.getIds().size()-1));
	}
	
	@Override
	public ResultType getConstReferenceType(final ConstRefExpression reference) {
		return getReferenceType(reference.getConstantName());
	}
	
	private ResultType getReferenceType(final String last) {
		if(last.startsWith("i")) {
			return ResultType.INTEGER;
		} else if(last.startsWith("r")) {
			return ResultType.REAL;
		} else if(last.startsWith("b")) {
			return ResultType.BOOLEAN;
		} else {
			return null;
		}
	}
}
