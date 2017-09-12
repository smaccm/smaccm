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

import edu.uah.rsesc.aadlsimulator.agree.CarryVariable;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.AstMapVisitor;

public class ReplaceFollowedByOperator extends AstMapVisitor {
	private static String stepVariableId = "__SIM_STEP";
	private static String nextStepVariableId = "__SIM_NEXT_STEP";
	private static int firstStepValue = 0; // The step in which the AGREE model's initial conditions should be true. This corresponds to the initial step of the simulation in the non-inductive case.
	
	private ReplaceFollowedByOperator() {}
	
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		if(lustreProgram.nodes.size() != 1) {
			throw new IllegalArgumentException("Only lustre programs with exactly one node are supported");
		}
		
		final ReplaceFollowedByOperator visitor = new ReplaceFollowedByOperator();
		final NodeBuilder nodeBuilder = new NodeBuilder(visitor.visit(lustreProgram.getMainNode()));
		
		// Create variable for the step number
		nodeBuilder.addInput(new VarDecl(stepVariableId, NamedType.INT));
		
		// Add an output for the next step number
		nodeBuilder.addOutput(new VarDecl(nextStepVariableId, NamedType.INT));
		nodeBuilder.addEquation(new Equation(new IdExpr(nextStepVariableId), new BinaryExpr(new IdExpr(stepVariableId), BinaryOp.PLUS, new IntExpr(1))));
		
		// Create the new lustre program using the new node
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(lustreProgram);
		lustreProgramBuilder.clearNodes();
		lustreProgramBuilder.addNode(nodeBuilder.build());		

		// Create the simulation program
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		final Expr stepVariableExpr = new IdExpr(stepVariableId);
		final int initialStepValue = program.getType().isInductive() ? firstStepValue + 1 : firstStepValue; // Ensure that the initial step is greater than the first step when simulating inductive counterexamples 
		simulationProgramBuilder.addInitialConstraint(new BinaryExpr(stepVariableExpr, BinaryOp.EQUAL, new IntExpr(initialStepValue)));
		simulationProgramBuilder.addCarryVariable(new CarryVariable(stepVariableExpr, new IdExpr(nextStepVariableId)));
		simulationProgramBuilder.setLustreProgram(lustreProgramBuilder.build());
		
		return simulationProgramBuilder.build();
	}
	
	@Override
	public Expr visit(final BinaryExpr e) {
		// Convert any usage of the followed by/arrow operator when an if then else expression involving the step variable
		if(e.op == BinaryOp.ARROW) {
			final Expr ifExpression = new BinaryExpr(new IdExpr(stepVariableId), BinaryOp.EQUAL, new IntExpr(firstStepValue));
			final Expr newExpression = new IfThenElseExpr(ifExpression, e.left.accept(this), e.right.accept(this));
			return newExpression;			
		}

		return super.visit(e);
	}
}