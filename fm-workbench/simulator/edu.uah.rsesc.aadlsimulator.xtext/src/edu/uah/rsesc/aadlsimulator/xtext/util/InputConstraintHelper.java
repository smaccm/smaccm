package edu.uah.rsesc.aadlsimulator.xtext.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.serializer.ISerializer;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintPackage;
import edu.uah.rsesc.aadlsimulator.xtext.validation.InputConstraintValidator;

public class InputConstraintHelper {
	private final IParser parser;
	private final ISerializer serializer;
	private final EValidator validator;
	private final Map<Object, Object> validationContext = new HashMap<>();	

	public static class Result {
		private final String errorMessage;
		private final InputConstraint ic;
		
		public Result(final String errorMessage) {
			this.errorMessage = Objects.requireNonNull(errorMessage, "errorMessage must not be null");
			this.ic = null;
		}
		
		public Result(final InputConstraint ic) {
			this.errorMessage = null;
			this.ic = ic;
		}
		
		public boolean isValid() {
			return errorMessage == null;
		}
		
		public InputConstraint getInputConstraint() {
			return ic;
		}
		
		public String getErrorMessage() {
			return errorMessage;
		}
	}
	
	@Inject
	private InputConstraintHelper(final IParser parser, final ISerializer serializer, final EValidator.Registry validatorRegistry, final IGrammarAccess grammarAccess) {
		this.parser = Objects.requireNonNull(parser, "parser must not be null");
		this.serializer = Objects.requireNonNull(serializer, "serializer must not be null");
		this.validator = Objects.requireNonNull(validatorRegistry, "validatorRegistry must not be null").getEValidator(InputConstraintPackage.eINSTANCE);
		Objects.requireNonNull(grammarAccess, "grammarAccess must not be null");		
		validationContext.put(InputConstraintValidator.CURRENT_LANGUAGE_NAME, grammarAccess.getGrammar().getName());
	}
	
	public Result parseAndValidate(final String str, final ReferenceTypeResolver resolver, final ResultType expectedType, final int numberOfPreviousSteps) {
		final Result parseResult = parse(str);
		if(!parseResult.isValid()) {
			return parseResult;
		} else if(parseResult.getInputConstraint() == null) {
			return parseResult;
		}
		
		return validate(parseResult.ic, resolver, expectedType, numberOfPreviousSteps);
	}
	
	/**
	 * 
	 * @param ic
	 * @param resolver
	 * @param expectedType
	 * @param numberOfPreviousSteps
	 * @return
	 */
	public Result validate(final InputConstraint ic, final ReferenceTypeResolver resolver, final ResultType expectedType, final int numberOfPreviousSteps) {
		try {
			// Don't try to validate a null input constraint
			if(ic == null) {
				return new Result(ic);
			}
			
			validationContext.put(ReferenceTypeResolver.class, resolver);
			validationContext.put(InputConstraintValidator.CONTEXT_KEY_EXPECTED_TYPE, expectedType);
			validationContext.put(InputConstraintValidator.CONTEXT_KEY_NUMBER_OF_PREVIOUS_STEPS, numberOfPreviousSteps);
			
			final BasicDiagnostic diag = new BasicDiagnostic();
			boolean isValid = validator.validate(ic, diag, validationContext);
			final Iterator<EObject> constraintIterator = ic.eAllContents();
			while(constraintIterator.hasNext() && isValid) {
				final EObject content = constraintIterator.next();
				isValid = validator.validate(content, diag, validationContext);
			}
			
			if(!isValid) {
				final String validationErrors = getMessage(diag, "");
				return new Result(validationErrors);
			}
			
			// Return the input constraint
			return new Result(ic);
		} finally {
			validationContext.remove(InputConstraintValidator.CONTEXT_KEY_NUMBER_OF_PREVIOUS_STEPS);
			validationContext.remove(InputConstraintValidator.CONTEXT_KEY_EXPECTED_TYPE);
			validationContext.remove(ReferenceTypeResolver.class);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return the result of parsing the input constraint string. Returns a valid result with a null input constraint if str is null or empty.
	 */
	public Result parse(final String str) {
		if(str == null || str.trim().length() == 0) {
			return new Result((InputConstraint)null);
		}
		
		final IParseResult result = parser.parse(new StringReader(str));
		
		// Check for syntax errors
		if(result.hasSyntaxErrors()) {
			String errors = "Error parsing '" + str + "':";
			for(final INode err : result.getSyntaxErrors()) {
				errors += "\n" + err.getSyntaxErrorMessage().getMessage();
			}

			return new Result(errors);
		}
		
		final InputConstraint ic = (InputConstraint)result.getRootASTElement();
		final Resource r = createResource();
		r.getContents().add(ic);

		return new Result(ic);
	}
	
	// Create a new dummy resource
	private Resource createResource() {
		final URI ignoredUri = URI.createHierarchicalURI("aadl_simulator_ignore",
				null,
				null,
				new String[] { "internal.inputconstraint" },
				null,
				null);
		
		final ResourceSet rs = new ResourceSetImpl();
		return rs.createResource(ignoredUri);
	}
	
	public String unparse(final InputConstraint ic) {
		if(ic == null) {
			return "";
		}

		// If the input constraint is not part of a resource, copy the constraint, add it to a temporary resource, and then unparse.
		if(ic.eResource() == null) {
			final InputConstraint copy = EcoreUtil.copy(ic);
			createResource().getContents().add(copy);
			return unparse(copy);			
		} else {
			return serializer.serialize(ic);
		}
	}	
	
	private static String getMessage(final Diagnostic diagnostic, final String current) {
		String newMsg = current;
		if(diagnostic.getMessage() != null) {
			if(newMsg.length() != 0) {
				newMsg += "\n";
			}
			newMsg += diagnostic.getMessage();
		}
		
		for(final Diagnostic child : diagnostic.getChildren()) {
			newMsg = getMessage(child, newMsg);
		}
		
		return newMsg;
	}
}
