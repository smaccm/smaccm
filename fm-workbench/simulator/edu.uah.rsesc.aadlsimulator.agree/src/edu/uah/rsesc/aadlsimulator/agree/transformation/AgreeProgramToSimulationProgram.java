/*
Copyright (c) 2015, Rockwell Collins.
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
package edu.uah.rsesc.aadlsimulator.agree.transformation;

import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.lustre.visitors.RenamingVisitor;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramType;
import edu.uah.rsesc.aadlsimulator.agree.SimulationVariable;
import edu.uah.rsesc.aadlsimulator.agree.sim.AGREESimulatorException;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;

public class AgreeProgramToSimulationProgram {
	public static SimulationProgram transform(final AgreeProgram agreeProgram, final SimulationProgramType type) {
		Objects.requireNonNull(agreeProgram, "agreeProgram must not be null");

		// Build a Component Instance to AgreeNode map
		final Program lustreProgram = LustreAstBuilder.getAssumeGuaranteeLustreProgram(agreeProgram);
		final AgreeRenaming agreeRenaming = new AgreeRenaming();
		final AgreeLayout layout = new AgreeLayout();
		RenamingVisitor.addRenamings(lustreProgram, agreeRenaming, agreeProgram.topNode.compInst, layout);

		SimulationProgram program;
		try {
			final SimulationProgramBuilder builder = new SimulationProgramBuilder(type, agreeProgram.topNode.compInst, lustreProgram, agreeRenaming);
			populateMetadata(builder, agreeProgram, lustreProgram, agreeRenaming, agreeRenaming.getRefMap());
			program = builder.build();
		} catch(final Exception ex) {
			throw new AGREESimulatorException(lustreProgram, ex);
		}

		try {
			program = CreateLocalVariablesForPropertyExpressions.transform(program);
			program = RemovePropertySatisficationRequirements.transform(program);
			program = RemoveCondacts.transform(program);
			program = InlineNodeCalls.transform(program);
			program = ReplaceFollowedByOperator.transform(program);
			program = ReplacePreOperator.transform(program);
			program = CreateSimulationProperties.transform(program);
			program = RemoveProperties.transform(program);
			program = CreateSimulationGuarantee.transform(program);
		} catch(final Exception ex) {
			throw new AGREESimulatorException(program.getLustreProgram(), ex);
		}

		return program;
	}

	private static void populateMetadata(final SimulationProgramBuilder builder, final AgreeProgram agreeProgram, final Program lustreProgram, final AgreeRenaming agreeRenaming, final Map<String, EObject> refMap) {
		// For each input in the Lustre, determine which component instance it belongs to and create a simulation variable for it.
		// Exclude inputs that are used internally by AGREE.
		for(final VarDecl vd : lustreProgram.getMainNode().inputs) {
			if(vd instanceof AgreeVar) {
				final AgreeVar agreeVar = (AgreeVar)vd;
				if(agreeVar.compInst != null) {
					final FeatureInstance featureInstance = agreeVar.featInst;

					// Use the name as provided by the instance object path unless it is not available
					final String variableName;
					if(featureInstance != null) {
						variableName = featureInstance.getFullName();
					} else {
						final String[] idSegs = vd.id.split("__");
						variableName = idSegs[idSegs.length-1];
					}

					builder.addSimulationVariable(new SimulationVariable(agreeVar.compInst, variableName, vd.id, vd.type, featureInstance, agreeVar.reference));
				}
			}
		}

		// Add mappings from component instances to agree nodes
		builder.addMapping(agreeProgram.topNode.compInst, agreeProgram.topNode);
		for(final AgreeNode n : agreeProgram.agreeNodes) {
			builder.addMapping(n.compInst, n);
		}
	}
}
