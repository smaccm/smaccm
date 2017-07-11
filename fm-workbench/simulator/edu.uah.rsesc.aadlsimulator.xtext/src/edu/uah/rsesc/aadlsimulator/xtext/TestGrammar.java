package edu.uah.rsesc.aadlsimulator.xtext;

import java.io.IOException;
import edu.uah.rsesc.aadlsimulator.xtext.util.InputConstraintHelper;
import edu.uah.rsesc.aadlsimulator.xtext.util.ReferenceTypeResolver;
import edu.uah.rsesc.aadlsimulator.xtext.util.ResultType;

public class TestGrammar {
	/**
	 * Parses various example constraints and prints out results.
	 */
	public static void main(String[] args) throws IOException {
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		testConstraint("true", ResultType.BOOLEAN, 0, true);
		testConstraint("false", ResultType.BOOLEAN, 0, true);
		testConstraint("5", ResultType.INTEGER, 0, true);
		testConstraint("5.0", ResultType.REAL, 0, true);
		testConstraint("5.", ResultType.REAL, 0, false);
		testConstraint(".5", ResultType.REAL, 0, true);
		testConstraint("-5", ResultType.INTEGER, 0, true);
		testConstraint("-5 + 5", ResultType.INTEGER, 0, true);
		testConstraint("-5 + 5.0", ResultType.INTEGER, 0, false); // Types do not match
		testConstraint("5.0 / 5.0", ResultType.REAL, 0, true);
		testConstraint("5 / 5", ResultType.INTEGER, 0, false); // Integer division is not supported
		testConstraint("-5.0", ResultType.REAL, 0, true);
		testConstraint("z", ResultType.INTEGER, 0, false); // Invalid reference
		testConstraint("ix", ResultType.INTEGER, 0, true);
		testConstraint("-ix", ResultType.INTEGER, 0, true);
		testConstraint("x.iy", ResultType.INTEGER, 0, true);
		testConstraint("x.y.iz", ResultType.INTEGER, 0, true);
		testConstraint("x[1].y[2].iz[3]", ResultType.INTEGER, 0, true);
		testConstraint("ix+5+2+5+1", ResultType.INTEGER, 0, true);
		testConstraint("ix-5", ResultType.INTEGER, 0, true);
		testConstraint("ix*5", ResultType.INTEGER, 0, true);
		testConstraint("ix/5", ResultType.INTEGER, 0, false); // Integer division is not supported
		testConstraint("pre(ix)", ResultType.INTEGER, 0, false); // pre not allowed on first step
		testConstraint("pre(ix)", ResultType.INTEGER, 1, true);
		testConstraint("pre(iy) + 5", ResultType.INTEGER, 1, true);
		testConstraint("(,10]", ResultType.INTEGER, 0, true);
		testConstraint("(false, true]", ResultType.BOOLEAN, 0, false); // Don't allow boolean values
		testConstraint("[-100, 100.0].rand_int()", ResultType.INTEGER, 0, false); // Both sides of interval must be same type
		testConstraint("[-100, 100].rand_int()", ResultType.INTEGER, 0, true);
		testConstraint("[-100, 100].rand_real()", ResultType.REAL, 0, true);
		testConstraint("[-ix+20, 100].rand_real()", ResultType.REAL, 0, false);
		testConstraint("[0,5]", ResultType.INTEGER, 0, true);
		testConstraint("[0,ix]", ResultType.INTEGER, 0, true);
		testConstraint("[,]", ResultType.REAL, 0, false); // Interval must not be completely unbounded
		testConstraint("{}", ResultType.REAL, 0, false); // Sets must not be empty
		testConstraint("{5.0, 6}", ResultType.REAL, 0, false); // All members of the set must be of the same type 
		testConstraint("[, 100].rand_real()", ResultType.REAL, 0, false);
		testConstraint("{1}", ResultType.INTEGER, 0, true);
		testConstraint("{1}", ResultType.REAL, 0, false); // Not of the expected type
		testConstraint("{1, 2}", ResultType.INTEGER, 0, true);
		testConstraint("{1, 2, 3}.rand()", ResultType.INTEGER, 0, true);
		testConstraint("{ix+1, iy+2, iz+3}.rand()", ResultType.INTEGER, 0, false);
		testConstraint("{ix, iy}.rand()", ResultType.INTEGER, 0, false);
		testConstraint("{pre(ix), pre(iy)}.rand()", ResultType.INTEGER, 1, true);		
		testConstraint("a::ib", ResultType.INTEGER, 1, true);
		testConstraint("a::b::ic", ResultType.INTEGER, 1, true);
		testConstraint("a::b.ic", ResultType.INTEGER, 1, false); // Dot syntax not supported for constants
		testConstraint("pre(a::b::ic)", ResultType.INTEGER, 1, true);
	}
	
	private static void testConstraint(final String constraint, final ResultType expectedType, final int numberOfPreviousSteps, final boolean isConstraintValid) {
		com.google.inject.Injector injector = new InputConstraintStandaloneSetup().createInjectorAndDoEMFRegistration();
		final InputConstraintHelper parserHelper = injector.getInstance(InputConstraintHelper.class);
		final ReferenceTypeResolver refResolver = new TestReferenceTypeResolver();

		final InputConstraintHelper.Result parseAndValidateResult = parserHelper.parseAndValidate(constraint, refResolver, expectedType, numberOfPreviousSteps);
		if(parseAndValidateResult.isValid()) {
			if(isConstraintValid) {
				System.out.println("Success: Constructed AST for constraint: " + constraint + " : " + parseAndValidateResult.getInputConstraint());
				
				// Try to unparse the constraint
				parserHelper.unparse(parseAndValidateResult.getInputConstraint());
			} else{
				System.err.println("Error: Invalid constraint: " + constraint + " passed test.");
			}
		} else {
			if(isConstraintValid) {
				System.err.println("Error: Failed to create AST from constraint: " + constraint + ". " + parseAndValidateResult.getErrorMessage());
			} else {
				System.out.println("Success: Expected error for : " + constraint + " : " + parseAndValidateResult.getErrorMessage());				
			}
		}

	}
}
