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
import jkind.api.results.AnalysisResult;
import jkind.lustre.Program;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;

import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;

public class TestSuiteLinker {
    private Map<AnalysisResult, AgreeProgram> agreePrograms = new HashMap<>();
    // Lustre program associated with the result
    private Map<AnalysisResult, Program> programs = new HashMap<>();   
    // AADL component implementation associated with the result
    private Map<AnalysisResult, ComponentImplementation> components = new HashMap<>();
    // AGREE contract associated with the result
    private Map<AnalysisResult, AgreeSubclause> contracts = new HashMap<>();
    // Layout (mapping signals to categories)
    private Map<AnalysisResult, Layout> layouts = new HashMap<>();
    // Mapping from (TCG) variable names to editor references
    private Map<AnalysisResult, Map<String, EObject>> referenceMaps = new HashMap<>();
    // For errors during instantiation.
    private Map<AnalysisResult, String> logs = new HashMap<>();
    // For renaming of variables
    private Map<AnalysisResult, TcgRenaming> renamingMap = new HashMap<>();

    public void setRenaming(AnalysisResult result, TcgRenaming renaming){
        this.renamingMap.put(result, renaming);
    }
    
    public TcgRenaming getRenaming(AnalysisResult result){
        return this.renamingMap.get(result);
    }

    public Map<AnalysisResult, AgreeProgram> getAgreePrograms() { return agreePrograms; }
    
    public void setProgram(AnalysisResult result, Program program) {
        programs.put(result, program);
    }

    public Program getProgram(AnalysisResult result) {
        return programs.get(result);
    }

    public void setComponent(AnalysisResult result, ComponentImplementation ci) {
        components.put(result, ci);
    }

    public ComponentImplementation getComponent(AnalysisResult result) {
        return components.get(result);
    }

    public void setContract(AnalysisResult result, AgreeSubclause contract) {
        contracts.put(result, contract);
    }

    public AgreeSubclause getContract(AnalysisResult result) {
        return contracts.get(result);
    }

    public void setLayout(AnalysisResult result, Layout layout) {
        layouts.put(result, layout);
    }

    public Layout getLayout(AnalysisResult result) {
        return layouts.get(result);
    }

    public void setReferenceMap(AnalysisResult result, Map<String, EObject> refMap) {
        referenceMaps.put(result, refMap);
    }

    public Map<String, EObject> getReferenceMap(AnalysisResult result) {
        return referenceMaps.get(result);
    }

    public void setLog(AnalysisResult result, String log) {
        logs.put(result, log);
    }

    public String getLog(AnalysisResult result) {
        return logs.get(result);
    }
/*    
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
    public void setReferenceMap(Map<String, EObject> refMap) {
        this.referenceMap = refMap;
    }

    public Map<String, EObject> getReferenceMap() {
        return this.referenceMap;
    }
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
    
*/
    
}
