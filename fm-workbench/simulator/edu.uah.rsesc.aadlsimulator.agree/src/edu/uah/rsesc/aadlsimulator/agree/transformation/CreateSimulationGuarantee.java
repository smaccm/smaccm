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

import java.util.Collection;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;

public class CreateSimulationGuarantee {
	// Variable name for the conjunction of simulation assertions such as the value of PRE's and user constraints.
	// Declared by this transform but must be assigned during the simulation process
	// Actual assertions are not used because they can result in an inconsistent Lustre program which will prevent
	// the set of support from being generated when using yices.
	public final static String SIMULATION_ASSERTIONS_ID = "__SIM_ASSERTIONS";
	public final static String SIMULATION_GUARANTEE_ID = "__SIM_GUARANTEE";
	
	private final NodeBuilder nodeBuilder;
	private final SimulationProgram transformedProgram;
	
	public static SimulationProgram transform(final SimulationProgram program) {
		final CreateSimulationGuarantee transformation = new CreateSimulationGuarantee(program);
		return transformation.getTransformedProgram();
	}
	
	private CreateSimulationGuarantee(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		if(lustreProgram.nodes.size() != 1) {
			throw new IllegalArgumentException("Only lustre programs with exactly one node are supported");
		}
		
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(lustreProgram);
		lustreProgramBuilder.clearNodes();
		
		final Node node = lustreProgram.getMainNode();
		nodeBuilder = new NodeBuilder(node);	
		
		// Create a local variable for the guarantee
		nodeBuilder.addLocal(new VarDecl(SIMULATION_GUARANTEE_ID, NamedType.BOOL));
		nodeBuilder.addLocal(new VarDecl(SIMULATION_ASSERTIONS_ID, NamedType.BOOL));
		
		// Create a property expression that ensures that variables are made available to the simulator
		int simGuaranteePartNumber = 0;
		simGuaranteePartNumber = createGuaranteePartExpression(simGuaranteePartNumber, new IdExpr(SIMULATION_ASSERTIONS_ID), nodeBuilder);
		simGuaranteePartNumber = addDummyGuaranteeExpressions(simGuaranteePartNumber, node.locals);
		simGuaranteePartNumber = addDummyGuaranteeExpressions(simGuaranteePartNumber, node.inputs);
		simGuaranteePartNumber = addDummyGuaranteeExpressions(simGuaranteePartNumber, node.outputs);
		final Expr guaranteeExpr = new UnaryExpr(UnaryOp.NOT, new IdExpr(buildSimGuaranteeId(simGuaranteePartNumber-1))); // Negate the expression so that the property is false when expression is false with the previously built expression is true.

		// Create the guarantee property
		nodeBuilder.addEquation(new Equation(new IdExpr(SIMULATION_GUARANTEE_ID), guaranteeExpr));
		nodeBuilder.addProperty(SIMULATION_GUARANTEE_ID);
		
		// Add the new node to the new lustre program
		lustreProgramBuilder.addNode(nodeBuilder.build());
		
		simulationProgramBuilder.setLustreProgram(lustreProgramBuilder.build());		
		transformedProgram = simulationProgramBuilder.build();
	}
	
	private static int createGuaranteePartExpression(final int number, final Expr expr, final NodeBuilder nodeBuilder) {
		final String newId = buildSimGuaranteeId(number);
		nodeBuilder.addLocal(new VarDecl(newId, NamedType.BOOL));
		nodeBuilder.addEquation(new Equation(new IdExpr(newId), expr));		
		return number + 1;
	}
	
	private static String buildSimGuaranteeId(final int number) {
		return SIMULATION_GUARANTEE_ID + number;
	}
	
	private SimulationProgram getTransformedProgram() {
		return transformedProgram;
	}
	
	/**
	 * Adds expressions that guarantee the variable declarations equal themselves. This is needed to ensure that JKind provies the values in the counterexample.
	 * @param guaranteePartNum
	 * @param varDecls
	 * @return
	 */
	private int addDummyGuaranteeExpressions(final int guaranteePartNum, final Collection<VarDecl> varDecls) {
		int nextGuaranteeNum = guaranteePartNum;
		for(final VarDecl varDecl : varDecls) {			
			final String prevExprId = buildSimGuaranteeId(nextGuaranteeNum-1);
			nextGuaranteeNum = createGuaranteePartExpression(nextGuaranteeNum, new BinaryExpr(new IdExpr(prevExprId), BinaryOp.AND, new BinaryExpr(new IdExpr(varDecl.id), BinaryOp.EQUAL, new IdExpr(varDecl.id))), nodeBuilder);				
		}
		
		return nextGuaranteeNum;
	}
}
