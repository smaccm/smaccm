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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.TupleExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.AstMapVisitor;
import jkind.lustre.visitors.ExprMapVisitor;

/**
 * Transforms a simulation program to replace expressions generated from assertions, assumptions, and guarantees with SimulationPropertyVar local variables.
 */
public class CreateLocalVariablesForPropertyExpressions {		
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		final SimulationProgramBuilder builder = new SimulationProgramBuilder(program);

		// Build mappings between Agree Statements, expressions, and Agree Nodes		
		final Map<Expr, AgreeStatement> exprToStatementMap = new HashMap<>();
		final Map<AgreeStatement, AgreeNode> agreeStatementToAgreeNodeMap = new HashMap<>();

		for(final AgreeNode agreeNode : program.getAllAgreeNodes()) {
			for(final AgreeStatement statement : agreeNode.assertions) {
				if(statement.reference instanceof AssertStatement) {
					exprToStatementMap.put(statement.expr, statement);
					agreeStatementToAgreeNodeMap.put(statement, agreeNode);
				}
			}
			
			for(final AgreeStatement statement : agreeNode.assumptions) {
				exprToStatementMap.put(statement.expr, statement);
				agreeStatementToAgreeNodeMap.put(statement, agreeNode);
			}
			
			for(final AgreeStatement statement : agreeNode.guarantees) {
				exprToStatementMap.put(statement.expr, statement);
				agreeStatementToAgreeNodeMap.put(statement, agreeNode);
			}
		}		

		// Create local variables for assert statements, assumptions, and guarantees
		final ProgramBuilder lustreBuilder = new ProgramBuilder(lustreProgram).clearNodes();
		for(final Node lustreNode : lustreProgram.nodes) {
			lustreBuilder.addNode(VariableCreator.transform(lustreNode, exprToStatementMap, agreeStatementToAgreeNodeMap));
		}
		
		builder.setLustreProgram(lustreBuilder.build());
		
		return builder.build();
	}
	
	// Transforms a node to replace expressions found in provided mappings with local variables.
	private static class VariableCreator extends ExprMapVisitor {
		private static interface ExpressionProvider {
			Expr getExpr(); 
		}
		
		private static final String varIdPrefix = "__SIM_VAR";
		private final Map<Expr, AgreeStatement> exprToStatementMap;
		private final Map<AgreeStatement, AgreeNode> agreeStatementToAgreeNodeMap;
		private final Map<Expr, VarDecl> exprToVarDeclMap = new HashMap<>();
		
		public static Node transform(final Node n, final Map<Expr, AgreeStatement> exprToStatementMap, final Map<AgreeStatement, AgreeNode> agreeStatementToAgreeNodeMap) {
			final VariableCreator transform = new VariableCreator(exprToStatementMap, agreeStatementToAgreeNodeMap);
			final NodeBuilder nodeBuilder = new NodeBuilder((Node)n.accept(new AstMapVisitor() {
				@Override
				public Equation visit(final Equation e) {
					if(e.lhs.size() == 1 && e.lhs.get(0).id.equals("__ASSERT")) {
						return new Equation(e.location, e.lhs, e.expr.accept(transform));
					}
					
					return super.visit(e);
				}
				
				@Override
				protected List<Expr> visitAssertions(List<Expr> es) {
					return map(e -> e.accept(transform), es);
				}
			}));
			
			// Add new local variables and equations
			for(final Entry<Expr, VarDecl> exprToVarDeclEntry : transform.exprToVarDeclMap.entrySet()) {
				final VarDecl var = exprToVarDeclEntry.getValue();
				nodeBuilder.addLocal(var);
				nodeBuilder.addEquation(new Equation(new IdExpr(var.id), exprToVarDeclEntry.getKey()));
			}

			return nodeBuilder.build();			
		}
		
		private VariableCreator(final Map<Expr, AgreeStatement> exprToStatementMap, final Map<AgreeStatement, AgreeNode> agreeStatementToAgreeNodeMap) {
			this.exprToStatementMap = exprToStatementMap;
			this.agreeStatementToAgreeNodeMap = agreeStatementToAgreeNodeMap;
		}
		
		@Override
		public Expr visit(ArrayAccessExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}			

		@Override
		public Expr visit(ArrayExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(ArrayUpdateExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(BinaryExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(BoolExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(CastExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(CondactExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(IdExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(IfThenElseExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(IntExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(NodeCallExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(RealExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(RecordAccessExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(RecordExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(RecordUpdateExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(TupleExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}

		@Override
		public Expr visit(UnaryExpr e) {
			return getTransformedExpression(e, () -> super.visit(e));
		}			

		private Expr getTransformedExpression(final Expr e, ExpressionProvider defaultExpressionProvider) {
			final AgreeStatement statement = exprToStatementMap.get(e);
			if(statement == null) {
				return defaultExpressionProvider.getExpr();
			}
			
			VarDecl var = exprToVarDeclMap.get(e);
			if(var == null) {
				final AgreeNode agreeNode = agreeStatementToAgreeNodeMap.get(statement);
				var = new SimulationPropertyVar(varIdPrefix + exprToVarDeclMap.size(), NamedType.BOOL, statement.reference, agreeNode.compInst, null); 
				exprToVarDeclMap.put(e, var);
			}

			return new IdExpr(var.id);
		}
	};
}
