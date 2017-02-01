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

import jkind.results.layout.Layout;
import jkind.lustre.Program;

import org.osate.aadl2.ComponentImplementation;

import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;

public class TestSuiteLinker {
	
    private AgreeProgram agreeProgram;
	private Program program; // Lustre program associated with the result
    private ComponentImplementation component; // AADL component implementation associated with the result
    private AgreeSubclause contract; // AGREE contract associated with the result
    private Layout layout; // Layout (mapping signals to categories)
    // private Map<String, EObject> referenceMap; // Mapping from (TCG) variable names to editor references
    private TcgRenaming renaming; 
    private String log;  // For errors during instantiation.
        
    public AgreeProgram getAgreeProgram() {
		return agreeProgram;
	}

	public void setAgreeProgram(AgreeProgram agreeProgram) {
		this.agreeProgram = agreeProgram;
	}

	public void setProgram(Program program) {
    	this.program = program;
    }

    public Program getProgram() {
    	return program;
    }

    public void setComponent(ComponentImplementation ci) {
        this.component = ci;
    }

    public ComponentImplementation getComponent() {
        return component;
    }

    public void setContract(AgreeSubclause contract) {
        this.contract = contract;
    }

    public AgreeSubclause getContract() {
    	return contract;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }
/*
    public void setReferenceMap(Map<String, EObject> refMap) {
        this.referenceMap = refMap;
    }

    public Map<String, EObject> getReferenceMap() {
        return this.referenceMap;
    }
*/
    public void setLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

	public TcgRenaming getRenaming() {
		return renaming;
	}

	public void setRenaming(TcgRenaming renaming) {
		this.renaming = renaming;
	}
    
    
}
