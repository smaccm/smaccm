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
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.IdExpr;
import jkind.lustre.Node;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.AstMapVisitor;

// Adjusts the assumption conjunction for subcomponents to always be true. This forces subcomponents to meet guarantees even if assumptions are false.
//
// Currently disabled: 
// Also removes assertions for top level assumptions. This prevents inputs from causing an inconsistent Lustre program but causes the model check to potentially select inappropriate values.
public class RemovePropertySatisficationRequirements {
	private final static String assumptionConjunctionId = "__ASSUME__CONJ";
	//private final static String assumeBaseId = "__ASSUME";
	
	public static SimulationProgram transform(final SimulationProgram program) {
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(program.getLustreProgram());
		lustreProgramBuilder.clearNodes();
		
		for(final Node node : program.getLustreProgram().nodes) {
			final NodeBuilder nodeBuilder = new NodeBuilder(new AstMapVisitor() {
				@Override
				public Node visit(final Node n) {					
					return super.visit(n);
				}
				
				@Override
				public Equation visit(final Equation e) {
					// Force the assumption conjunction to be true
					if(e.lhs.size() == 1 && e.lhs.get(0).id.equals(assumptionConjunctionId)) {
						return new Equation(new IdExpr(assumptionConjunctionId), new BoolExpr(true));
					}

					final Equation result = super.visit(e);
					return result;
				}
				
				// Allows simulation to run when top level assumptions are violated but the model checker will potentially violate those assumptions when picking input values
/*
				@Override
				protected List<Expr> visitAssertions(List<Expr> assertions) {
					final List<Expr> transformedAssertions = new ArrayList<>();
					// Remove assertions for assumptions
					for(final Expr assertion : assertions) {
						if(!(assertion instanceof IdExpr && ((IdExpr) assertion).id.startsWith(assumeBaseId))) {
							transformedAssertions.add(assertion.accept(this));							
						}
					}
					
					return transformedAssertions;
				}
				
*/
			
			}.visit(node));
			
			lustreProgramBuilder.addNode(nodeBuilder.build());
		}
		
		simulationProgramBuilder.setLustreProgram(lustreProgramBuilder.build());		
		return simulationProgramBuilder.build();
	};
}
