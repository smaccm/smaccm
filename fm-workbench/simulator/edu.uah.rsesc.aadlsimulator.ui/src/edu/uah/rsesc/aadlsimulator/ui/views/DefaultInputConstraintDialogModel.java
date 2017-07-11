package edu.uah.rsesc.aadlsimulator.ui.views;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.InputConstraintDialog.Model;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintFactory;
import edu.uah.rsesc.aadlsimulator.xtext.util.InputConstraintHelper;
import edu.uah.rsesc.aadlsimulator.xtext.util.ReferenceTypeResolver;
import edu.uah.rsesc.aadlsimulator.xtext.util.ResultType;

/**
 * Model for the input constraint editor dialog.
 *
 */
public class DefaultInputConstraintDialogModel implements Model {
	private final InputConstraintHelper inputConstraintHelper;
	private final ReferenceTypeResolver refResolver;
	private final SimulationEngineState engineState;
	
	public DefaultInputConstraintDialogModel(final InputConstraintHelper inputConstraintHelper,
		final ReferenceTypeResolver refResolver,
		final SimulationEngineState engineState) {
		this.inputConstraintHelper = Objects.requireNonNull(inputConstraintHelper, "inputConstraintHelper must not be null");
		this.refResolver = Objects.requireNonNull(refResolver, "refResolver must not be null");
		this.engineState = Objects.requireNonNull(engineState, "engineState must not be null");
	}
	
	@Override
	public String unparse(final InputConstraint ic) {
		return inputConstraintHelper.unparse(ic);
	}
	
	@Override
	public InputConstraintHelper.Result parseAndValidate(final String str, final ResultType expectedType, final int numberOfPreviousSteps) {
		return inputConstraintHelper.parseAndValidate(str, refResolver, expectedType, numberOfPreviousSteps);
	}
	
	@Override 
	public InputConstraintHelper.Result validate(final InputConstraint ic, final ResultType expectedType, final int numberOfPreviousSteps) {
		return inputConstraintHelper.validate(ic, refResolver,  expectedType, numberOfPreviousSteps);
	}
	
	@Override
	public Stream<ElementRefExpression> getVariables() {		
		final Stream.Builder<ElementRefExpression> builder = Stream.builder();
		final Deque<String> elementIds = new ArrayDeque<>();
		buildVariablesStream(builder, engineState.getRootElements(), engineState, elementIds);						
		return builder.build();
	}
	
	private void buildVariablesStream(Stream.Builder<ElementRefExpression> sb, 
			final Collection<?> elements, 
			final SimulationEngineState engineState,
			final Deque<String> elementIds) {
		for(final Object element : elements) {
			if(!engineState.isElementHidden(element)) {
				final String elementName = engineState.getElementName(element);
				elementIds.push(elementName);
				try {
					final ElementRefExpression newExpr = InputConstraintFactory.eINSTANCE.createElementRefExpression();
					final EList<String> exprIds = newExpr.getIds();
					for(final Iterator<String> it = elementIds.descendingIterator(); it.hasNext();) {
						exprIds.add(it.next());
					}
					sb.add(newExpr);
					
					buildVariablesStream(sb, engineState.getChildElements(element), engineState, elementIds);
				} finally {
					elementIds.pop();
				}
			}
		}
	}
	
	@Override
	public ResultType getElementReferenceType(final ElementRefExpression reference) {
		return refResolver.getElementReferenceType(reference);
	}
	
	@Override
	public Stream<ConstRefExpression> getConstants() {						
		return engineState.getConstantIds().map((c) -> createConstRefExpression(c));
	}
	
	private ConstRefExpression createConstRefExpression(final String constantId) {
		final ConstRefExpression result = InputConstraintFactory.eINSTANCE.createConstRefExpression();
		final String[] segments = constantId.split("::");
		if(segments.length < 2) {
			throw new RuntimeException("Invalid constant id: " + constantId);
		}
		
		result.setConstantName(segments[segments.length-1]);
		result.getPackageSegments().addAll(Arrays.asList(segments).subList(0, segments.length-1));
		return result;
	}
	
	@Override
	public ResultType getConstReferenceType(final ConstRefExpression reference) {
		return refResolver.getConstReferenceType(reference);
	}
}
