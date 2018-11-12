package com.rockwellcollins.atc.resolute.agreecheck;

import java.util.List;
import java.util.Set;

import org.eclipse.xtext.EcoreUtil2;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.impl.ComponentInstanceImpl;
import org.osate.aadl2.instance.impl.SystemInstanceImpl;

import com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl;
import com.rockwellcollins.atc.agree.saving.AgreeFileUtil;
import com.rockwellcollins.atc.agree.saving.AgreeLogResult;
import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

import jkind.api.results.Status;

public class ResoluteAgreeCheck implements ResoluteExternalAnalysis {

	private String modelHashcode = "";
	private List<AgreeLogResult> agreePropertyResults;
	private EvaluationContext evalContext;

	/**
	 * The purpose of this plugin is to check that, for a given component or implementation,
	 *   1) AGREE was run on the latest version of the model, and
	 *   2) the AGREE Assume contracts of the component are valid and consistent
	 * In order to successfully use this plugin, "Generate property analysis log" must be checked
	 * in the AGREE Analysis preferences, and a log file must be specified.
	 * @param context - The context for which this plugin was called
	 * @param argss - Plugin arguments.  It should be an empty list.
	 */
	@Override
	public ResoluteValue run(EvaluationContext context, List<ResoluteValue> args) {

		this.evalContext = context;

		// Get the hash representing the current model
		try {
			NamedElement root = null;
			if (evalContext.getThisInstance() instanceof SystemInstanceImpl) {
				SystemInstanceImpl si = (SystemInstanceImpl) evalContext.getThisInstance();
				root = si.getComponentImplementation();
			} else if (evalContext.getThisInstance() instanceof ComponentInstanceImpl) {
				ComponentInstanceImpl ci = (ComponentInstanceImpl) evalContext.getThisInstance();
				root = ci.getClassifier();
			}
			modelHashcode = AgreeFileUtil.getModelHashcode(root);
		} catch (Exception e) {
			throw new ResoluteFailException(e.getMessage(), evalContext.getThisInstance());
		}

		// Get the AGREE analyses from log
		try {
			agreePropertyResults = AgreeFileUtil.readAgreeLog();
			if (agreePropertyResults.size() == 0) {
				return new BoolValue(false);
			}
		} catch (Exception e) {
			throw new ResoluteFailException(e.getMessage(), evalContext.getThisInstance());
		}


		// If it is a component implementation, examine all components referred to in
		// that implementation.
		// If this instance is a component, just examine AGREE contracts specified in that component.
		// TODO: single layer / all layers?
		if (evalContext.getThisInstance() instanceof SystemInstanceImpl) {
			Set<NamedElement> components = evalContext.getSet("component");
			for (NamedElement component : components) {
				if (!checkComponentAgreeProperties((ComponentInstanceImpl) component)) {
					return new BoolValue(false);
				}
			}
		} else if (evalContext.getThisInstance() instanceof ComponentInstanceImpl) {
			if (!checkComponentAgreeProperties((ComponentInstanceImpl) evalContext.getThisInstance())) {
				return new BoolValue(false);
			}
		} else {
			throw new ResoluteFailException(
					"Resolute AgreeCheck can only be called from a component or component implementation.",
					evalContext.getThisInstance());
		}

		return new BoolValue(true);

	}

	/**
	 * Extracts the AGREE Assume contracts from a given component.  Checks that AGREE was run on the latest version
	 * of the model and the contracts are valid.
	 * @param component - The component containing the AGREE Assume contracts.  Can also be a system implementation.
	 */
	private boolean checkComponentAgreeProperties(ComponentInstanceImpl component) {

		// Get AGREE properties specified for this component
		// If the component is a component implementation, get the component

		List<AssumeStatementImpl> assumeStatements = EcoreUtil2.getAllContentsOfType(component.getComponentClassifier(),
				AssumeStatementImpl.class);
		String propName;
		for (AssumeStatementImpl assumeStatement : assumeStatements) {
			if (component instanceof SystemInstanceImpl) {
				propName = assumeStatement.getStr();
			} else {
				propName = component.getName().concat(" assume: ").concat(assumeStatement.getStr());
			}
			if (!checkAgreeProperty(propName)) {
				return false;
			}
		}

		// Check component consistency
		if (component instanceof SystemInstanceImpl) {
			propName = "This component consistent";
		} else {
			propName = component.getName().concat(" consistent");
		}
		if (!checkAgreeProperty(propName)) {
			return false;
		}

		return true;
	}

	/**
	 * Checks that AGREE was run on the latest version of the model and the contracts are valid.
	 * @param property - The property to be checked.
	 */
	private boolean checkAgreeProperty(String property) {

		try {
			for (AgreeLogResult propResult : agreePropertyResults) {
				if (propResult.getName().contentEquals(property)) {
					// Check status and timestamp
					if (propResult.getResult().equalsIgnoreCase(Status.VALID.toString())
							&& propResult.getHashcode().contentEquals(modelHashcode)) {
						return true;
					}
					break;
				}
			}
			return false;
		} catch (Exception e) {
			throw new ResoluteFailException(
					"Unknown error checking AGREE property [ " + property + " ] : " + e.getMessage(),
					evalContext.getThisInstance());
		}
	}

}

