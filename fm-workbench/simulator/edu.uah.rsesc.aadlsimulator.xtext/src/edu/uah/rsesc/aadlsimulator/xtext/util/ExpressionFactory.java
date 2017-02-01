package edu.uah.rsesc.aadlsimulator.xtext.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintFactory;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.NegativeExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.Operator;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RealLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ScalarExpression;

public class ExpressionFactory {
	public static ScalarExpression createReal(final BigInteger value) {
		final boolean isNegative = value.signum() < 0;
		BigDecimal decValue = new BigDecimal(value);
		
		// Negative values arnen't allowed
		if(isNegative) {
			decValue = decValue.negate();
		}
		
		// Adjust scale. Decimal values with a scale less than 1 do not serialize properly.
		if(decValue.scale() < 1) {
			decValue = decValue.setScale(1);
		}
		
		final RealLiteral realLiteral = InputConstraintFactory.eINSTANCE.createRealLiteral();
		realLiteral.setValue(decValue);
		
		final ScalarExpression scalarExpression;
		if(isNegative) {
			final NegativeExpression negExpr = InputConstraintFactory.eINSTANCE.createNegativeExpression();
			negExpr.setValue(realLiteral);
			scalarExpression = negExpr;
		} else {
			scalarExpression = realLiteral;
		}
		
		return scalarExpression;
	}
	
	public static BinaryExpression createFraction(final BigInteger numerator, final BigInteger denominator) {
		final BinaryExpression ic = InputConstraintFactory.eINSTANCE.createBinaryExpression();
		ic.setOp(Operator.DIVISION);
		
		ic.setLeft(createReal(numerator));
		ic.setRight(createReal(denominator));
		
		return ic;
	}
}
