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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import edu.uah.rsesc.aadlsimulator.agree.CarryVariable;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayType;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Program;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.TypeAwareAstMapVisitor;

public class ReplacePreOperator extends TypeAwareAstMapVisitor {
	private static String preVariablePrefix = "__SIM_PRE";
	private static String nextPreVariablePrefix = "__SIM_NEXT_PRE";
	
	private final HashMap<String, Integer> processedExpressionsToVariableNumberMap = new HashMap<String, Integer>();
	private final List<VarDecl> newInputs = new ArrayList<VarDecl>();
	private final List<VarDecl> newOutputs = new ArrayList<VarDecl>();
	private final List<Equation> newEquations = new ArrayList<Equation>();
	private final List<CarryVariable> newCarryVariables = new ArrayList<CarryVariable>();
	private int newVariableCount = 0;
	
	private ReplacePreOperator() {}
	
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		if(lustreProgram.nodes.size() != 1) {
			throw new IllegalArgumentException("Only lustre programs with exactly one node are supported");
		}
		
		final SimulationProgramBuilder simulationProgramBuilder = new SimulationProgramBuilder(program);
		
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(lustreProgram);
		lustreProgramBuilder.clearNodes();
			
		final ReplacePreOperator visitor = new ReplacePreOperator();		
		final NodeBuilder nodeBuilder = new NodeBuilder(visitor.visit(lustreProgram).getMainNode());
		
		// Add additional inputs, returns, expressions, etc
		nodeBuilder.addInputs(visitor.newInputs);
		nodeBuilder.addOutputs(visitor.newOutputs);
		nodeBuilder.addEquations(visitor.newEquations);

		lustreProgramBuilder.addNode(nodeBuilder.build());
		
		simulationProgramBuilder.setLustreProgram(lustreProgramBuilder.build());
		simulationProgramBuilder.addCarryVariables(visitor.newCarryVariables);
		
		return simulationProgramBuilder.build();		
	}
	
	@Override
	public Expr visit(final UnaryExpr e) {
		if(e.op == UnaryOp.PRE) {
			// Visit the expression
			final Expr innerExpression = e.expr.accept(this);
			
			// Determine variable names based on the expression
			final String originalExpressionStr = innerExpression.toString();
			final Integer variableNumberObj = processedExpressionsToVariableNumberMap.get(originalExpressionStr);
			final int variableNumber = variableNumberObj == null ? (newVariableCount++) : variableNumberObj;

			// Get the variable name for the current value of the pre expression
			final String preVarName = getPreVariableName(variableNumber);
			
			// Create the variable if it didn't exist in the map
			if(variableNumberObj == null) {
				final String nextPreVarName = getNextPreVariableName(variableNumber);
				processedExpressionsToVariableNumberMap.put(originalExpressionStr, variableNumber);
								
				// Determine the the type to which the expression evaluates
				final Type expressionType = getType(e.expr);
				
				// Create an input declaration
				final VarDecl newInput = new VarDecl(preVarName, expressionType);
				newInputs.add(newInput);
				
				// Create an output declaration
				final VarDecl newOutput = new VarDecl(nextPreVarName, expressionType);
				newOutputs.add(newOutput);
				
				// Create assignment for the output
				final Equation newEquation = new Equation(new IdExpr(nextPreVarName), innerExpression);
				newEquations.add(newEquation);
				
				createCarryVariables(new IdExpr(preVarName), new IdExpr(nextPreVarName), expressionType);
			}
							
			// Use the variable for the current value of the pre expression instead of the pre expression
			return new IdExpr(preVarName);
		}
		
		return super.visit(e);
	}
	
	// Create carry variables for each scalar variable. Flatten arrays and records.
	private void createCarryVariables(final Expr preVarExpr, final Expr nextPreVarExpr, final Type expressionType) {
		// Create carry variables. If the expression is a record, create one for each field in the record
		if(expressionType instanceof RecordType) {
			final RecordType recordType = (RecordType)expressionType;
			for(final Entry<String, Type> fieldEntry : recordType.fields.entrySet()) {
				createCarryVariables(new RecordAccessExpr(preVarExpr, fieldEntry.getKey()), new RecordAccessExpr(nextPreVarExpr, fieldEntry.getKey()), fieldEntry.getValue());
			}
		} else if(expressionType instanceof ArrayType) {
			final ArrayType arrayType = (ArrayType)expressionType;
			for(int i = 0; i < arrayType.size; i++) {
				createCarryVariables(new ArrayAccessExpr(preVarExpr, i), new ArrayAccessExpr(nextPreVarExpr, i), arrayType.base);
			}
		} else if(expressionType instanceof NamedType) {
			newCarryVariables.add(new CarryVariable(preVarExpr, nextPreVarExpr));
		} 
	
	}
		
	private String getPreVariableName(final int variableNumber) {
		return preVariablePrefix + variableNumber;
	}
	
	private String getNextPreVariableName(final int variableNumber) {
		return nextPreVariablePrefix + variableNumber;
	}
}