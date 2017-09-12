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

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;

// Removes all properties from the program
public class RemoveProperties {
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		if(lustreProgram.nodes.size() != 1) {
			throw new IllegalArgumentException("Only lustre programs with exactly one node are supported");
		}
		
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(lustreProgram);
		lustreProgramBuilder.clearNodes();
		
		final Node node = lustreProgram.getMainNode();
		final NodeBuilder nodeBuilder = new NodeBuilder(node);
		nodeBuilder.clearProperties();
		
		// Add the new node to the new lustre program
		lustreProgramBuilder.addNode(nodeBuilder.build());
		
		simulationProgramBuilder.setLustreProgram(lustreProgramBuilder.build());		
		return simulationProgramBuilder.build();
	};
}
