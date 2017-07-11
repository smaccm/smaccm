package edu.uah.rsesc.aadlsimulator.xtext.util;

import java.util.Objects;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BooleanLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntegerLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntervalExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.NegativeExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.PreExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomElementExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomIntegerExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomRealExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RealLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.SetExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.util.InputConstraintSwitch;

/**
 * Determines the type of an input constraint
 */
public class InputConstraintTypeDeterminer {
	private final ReferenceTypeResolver referenceResolver;
	
	private InputConstraintSwitch<ResultType> typeSwitch = new InputConstraintSwitch<ResultType>() {
		@Override
		public ResultType caseIntervalExpression(final IntervalExpression object) {
			return doSwitch(object.getLeft()); // Types for both the left and the right are guaranteed to be the same
		}
		
		@Override
		public ResultType caseSetExpression(final SetExpression object) {
			// Valid sets are guaranteed to have elements of the same type and to have at least one member
			return object.getMembers().size() > 0 ? doSwitch(object.getMembers().get(0)) : null;
		}

		@Override
		public ResultType caseIntegerLiteral(final IntegerLiteral object) {
			return ResultType.INTEGER;
		}

		@Override
		public ResultType caseRealLiteral(final RealLiteral object) {
			return ResultType.REAL;
		}

		@Override
		public ResultType caseBooleanLiteral(final BooleanLiteral object) {
			return ResultType.BOOLEAN;
		}

		@Override
		public ResultType caseBinaryExpression(final BinaryExpression object) {
			return doSwitch(object.getLeft());
		}

		@Override
		public ResultType casePreExpression(final PreExpression object) {
			return doSwitch(object.getRef());
		}

		@Override
		public ResultType caseRandomElementExpression(final RandomElementExpression object) {
			return doSwitch(object.getSet());
		}
		
		@Override
		public ResultType caseRandomIntegerExpression(final RandomIntegerExpression object) {
			return ResultType.INTEGER;
		}
		
		@Override
		public ResultType caseRandomRealExpression(final RandomRealExpression object) {
			return ResultType.REAL;
		}

		@Override
		public ResultType caseElementRefExpression(final ElementRefExpression object) {
			return referenceResolver.getElementReferenceType(object);
		}
		
		@Override
		public ResultType caseConstRefExpression(final ConstRefExpression object) {
			return referenceResolver.getConstReferenceType(object);
		}

		@Override
		public ResultType caseNegativeExpression(final NegativeExpression object) {
			return doSwitch(object.getValue());
		}
	};
	
	public InputConstraintTypeDeterminer(final ReferenceTypeResolver referenceResolver) {
		this.referenceResolver = Objects.requireNonNull(referenceResolver, "referenceResolver must not be  null");
	}
	
	// Returns null if the type could not be determined.
	public ResultType determineType(final InputConstraint c) {
		return typeSwitch.doSwitch(c);
	}
}
