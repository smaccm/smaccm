/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package com.rockwellcollins.atc.tcg.views;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.RenamingVisitor;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;
import com.rockwellcollins.atc.agree.analysis.views.AgreeResultsLinker;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;

import jkind.api.results.AnalysisResult;
import jkind.api.results.CompositeAnalysisResult;
import jkind.api.results.JKindResult;
import jkind.lustre.Node;
import jkind.lustre.Program;

public class TcgLinkerFactory {

	private SystemInstance si;
	private boolean monolithicAnalysis;
	private TestSuiteLinker linker = new TestSuiteLinker();
	private AnalysisResult result;
	protected Queue<JKindResult> queue = new ArrayDeque<>();

	public SystemInstance getSysInstance(ComponentImplementation ci) {
		try {
			return InstantiateModel.buildInstanceModelFile(ci);
		} catch (Exception e) {
			Dialog.showError("Model Instantiate", "Error while re-instantiating the model: " + e.getMessage());
			throw new AgreeException("Error Instantiating model");
		}
	}


	public TcgLinkerFactory(ComponentImplementation ci, boolean monolithicAnalysis, boolean allLayers) {
		this.si = getSysInstance(ci);
		this.monolithicAnalysis = monolithicAnalysis;

		// check for AGREE Annex

		ComponentType sysType = AgreeUtils.getInstanceType(si);
		EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
				AgreePackage.eINSTANCE.getAgreeContractSubclause());

		if (annexSubClauses.size() == 0) {
			throw new AgreeException(
					"There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
		}

		CompositeAnalysisResult wrapper = new CompositeAnalysisResult("");

		if (allLayers) {
			result = buildAnalysisResult(ci.getName(), si);
			wrapper.addChild(result);
			result = wrapper;
		} else {
			wrapVerificationResult(si, wrapper);
			result = wrapper;
		}
	}

	public AnalysisResult getAnalysisResult() { return result; }
	public AgreeResultsLinker getLinker() { return linker; }
	public Queue<JKindResult> getWorkQueue() { return queue; }

	// Routines for actually building the verification results...

	private void wrapVerificationResult(ComponentInstance si, CompositeAnalysisResult wrapper) {
		AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si, monolithicAnalysis);
		Program program;
		program = LustreAstBuilder.getAssumeGuaranteeLustreProgram(agreeProgram);
		wrapper.addChild(
				createVerification("Contract Test Cases", si, program, agreeProgram));
	}

	protected AnalysisResult createVerification(String resultName, ComponentInstance compInst, Program lustreProgram, AgreeProgram agreeProgram) {

		AgreeRenaming agreeRenaming = new AgreeRenaming();
		AgreeLayout layout = new AgreeLayout();
		RenamingVisitor.addRenamings(lustreProgram, agreeRenaming, compInst, layout);
		TcgRenaming renaming = new TcgRenaming(agreeRenaming, agreeRenaming.getRefMap());
		Node mainNode = lustreProgram.getMainNode();
		if (mainNode == null) {
			throw new AgreeException("Could not find main lustre node after translation");
		}

		List<String> properties = new ArrayList<>();

		JKindResult result;
		result = new JKindResult(resultName, properties, renaming);
		queue.add(result);

		ComponentImplementation compImpl = AgreeUtils.getInstanceImplementation(compInst);
		linker.setAgreeProgram(result, agreeProgram);
		linker.setProgram(result, lustreProgram);
		linker.setComponent(result, compImpl);
		linker.setContract(result, getContract(compImpl));
		linker.setLayout(result, layout);
		// linker.setReferenceMap(result, renaming.getRefMap());
		linker.setLog(result, AgreeLogger.getLog());
		linker.setRenaming(result, renaming);

		// System.out.println(program);
		return result;

	}

	private AgreeSubclause getContract(ComponentImplementation ci) {
		ComponentType ct = ci.getOwnedRealization().getImplemented();
		for (AnnexSubclause annex : ct.getOwnedAnnexSubclauses()) {
			if (annex instanceof AgreeSubclause) {
				return (AgreeSubclause) annex;
			}
		}
		return null;
	}


	private AnalysisResult buildAnalysisResult(String name, ComponentInstance ci) {
		CompositeAnalysisResult result = new CompositeAnalysisResult("Test case generation for " + name);

		if (containsAGREEAnnex(ci)) {
			wrapVerificationResult(ci, result);
			ComponentImplementation compImpl = AgreeUtils.getInstanceImplementation(ci);
			for (ComponentInstance subInst : ci.getComponentInstances()) {
				if (AgreeUtils.getInstanceImplementation(subInst) != null) {
					AnalysisResult buildAnalysisResult = buildAnalysisResult(subInst.getName(), subInst);
					if (buildAnalysisResult != null) {
						result.addChild(buildAnalysisResult);
					}
				}
			}

			if (result.getChildren().size() != 0) {
				linker.setComponent(result, compImpl);
				return result;
			}
		}
		return null;
	}

	private boolean containsAGREEAnnex(ComponentInstance ci) {
		ComponentClassifier compClass = ci.getComponentClassifier();
		if (compClass instanceof ComponentImplementation) {
			compClass = ((ComponentImplementation) compClass).getType();
		}
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(compClass,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			if (annex instanceof AgreeContractSubclause) {
				return true;
			}
		}
		return false;
	}


}
