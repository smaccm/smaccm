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

import java.util.ArrayList;

import jkind.lustre.Program;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeLogger;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.RenamingVisitor;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;

public class TestSuiteLinkerFactory {

	private ComponentImplementation ci; 
	private boolean monolithicAnalysis;
	
	public TestSuiteLinkerFactory(ComponentImplementation ci, boolean monolithicAnalysis) {
		this.ci = ci;
		this.monolithicAnalysis = monolithicAnalysis;
	}
	
	
	public TestSuiteLinker constructLinker() {
		SystemInstance si; 
		si = null;
		try {
			si = InstantiateModel.buildInstanceModelFile(ci);
		} catch (Exception e) {
			throw new AgreeException("Error while re-instantiating the model: " + e.getMessage());
		}

		ComponentType sysType = AgreeUtils.getInstanceType(si);
		EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
				AgreePackage.eINSTANCE.getAgreeContractSubclause());

		if (annexSubClauses.size() == 0) {
			throw new AgreeException("There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
		}

		AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si);
		System.out.println("Constructing LUSTRE program...");
		Program program = LustreAstBuilder.getAssumeGuaranteeLustreProgram(agreeProgram, monolithicAnalysis);
		AgreeRenaming agreeRenaming = new AgreeRenaming(); 
		AgreeLayout layout = new AgreeLayout();
		RenamingVisitor.addRenamings(program, agreeRenaming, layout);
		TcgRenaming renaming = new TcgRenaming(agreeRenaming, agreeRenaming.getRefMap());

		TestSuiteLinker linker = new TestSuiteLinker(); 
		
		linker.setAgreeProgram(agreeProgram);
		linker.setProgram(program);
		linker.setComponent(ci);
		linker.setContract(getContract(ci));
		linker.setLayout(layout);
		linker.setLog(AgreeLogger.getLog());
		linker.setRenaming(renaming);
		return linker;
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

}
