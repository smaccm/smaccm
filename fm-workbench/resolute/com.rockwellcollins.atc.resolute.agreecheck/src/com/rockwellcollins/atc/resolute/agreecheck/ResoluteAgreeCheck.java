package com.rockwellcollins.atc.resolute.agreecheck;

import java.util.List;

import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;

import com.rockwellcollins.atc.agree.analysis.saving.AgreeFileUtil;
import com.rockwellcollins.atc.agree.analysis.saving.AgreeLogResult;
import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

import jkind.api.results.Status;

public class ResoluteAgreeCheck implements ResoluteExternalAnalysis {

	private String modelHashcode = "";
	private List<AgreeLogResult> agreeLogResults;
	private EvaluationContext evalContext;

	/**
	 * The purpose of this plugin is to check that, for a given component or implementation,
	 *   1) AGREE was run on the current version of the model, and
	 *   2) the AGREE Assume contracts of the component are valid and consistent
	 * In order to successfully use this plugin, "Generate property analysis log" must be checked
	 * in the AGREE Analysis preferences, and a log file must be specified.
	 * @param context - The context for which this plugin was called
	 * @param args - Plugin arguments.  It should be an empty list.
	 */
	@Override
	public ResoluteValue run(EvaluationContext context, List<ResoluteValue> args) {

		this.evalContext = context;

		// Get the hash representing the current model
		try {
			NamedElement root = null;
			if (evalContext.getThisInstance() instanceof SystemInstance) {
				SystemInstance si = (SystemInstance) evalContext.getThisInstance();
				root = si.getComponentImplementation();
			} else if (evalContext.getThisInstance() instanceof ComponentInstance) {
				ComponentInstance ci = evalContext.getThisInstance();
				root = ci.getClassifier();
			}
			modelHashcode = AgreeFileUtil.getModelHashcode(root);
		} catch (Exception e) {
			throw new ResoluteFailException(e.getMessage(), evalContext.getThisInstance());
		}

		// Get the AGREE analyses from log
		try {
			agreeLogResults = AgreeFileUtil.readAgreeLog();
			if (agreeLogResults.size() == 0) {
				return new BoolValue(false);
			}
		} catch (Exception e) {
			throw new ResoluteFailException(e.getMessage(), evalContext.getThisInstance());
		}

		// Get the name of the verification to check
		// It will be the name of the implementation that was selected to run Resolute
		String verificationName = "Verification for ";
		if (evalContext.getThisInstance() instanceof SystemInstance) {
			SystemInstance si = (SystemInstance) evalContext.getThisInstance();
			verificationName = verificationName.concat(si.getComponentImplementation().getName());

		} else if (evalContext.getThisInstance() instanceof ComponentInstance) {
			ComponentInstance ci = evalContext.getThisInstance();
			while (ci.eContainer() instanceof ComponentInstance) {
				ci = (ComponentInstance) ci.eContainer();
			}
			SystemInstance si = ((SystemInstance) ci);
			verificationName = verificationName.concat(si.getComponentImplementation().getName());
		} else {
			throw new ResoluteFailException(
					"Resolute AgreeCheck can only be called from a component or component implementation.",
					evalContext.getThisInstance());
		}

		return new BoolValue(checkAgreeVerification(verificationName));

	}

	/**
	 * Checks that AGREE was run on the current version of the model and the contracts are valid.
	 * @param verificationName - The name of the top-level AGREE verification run in the log file.
	 */
	private boolean checkAgreeVerification(String verificationName) {
		boolean agreeCheck = false;
		for (AgreeLogResult logResult : agreeLogResults) {
			if (logResult.getName().contentEquals(verificationName)) {
				// Check status and hashcode
				if (logResult.getResult().equalsIgnoreCase(Status.VALID.toString())
						&& logResult.getHashcode().contentEquals(modelHashcode)) {
					agreeCheck = true;
				}
				break;
			}
		}
		return agreeCheck;
	}

}

