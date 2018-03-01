/*
Copyright (c) 2018, Rockwell Collins.
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

package com.rockwellcollins.atc.tcg.lustre.visitors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.rockwellcollins.atc.tcg.TcgException;
import com.rockwellcollins.atc.tcg.obligations.ufc.Obligation;
import com.rockwellcollins.atc.tcg.obligations.ufc.ObligationSet;
import com.rockwellcollins.atc.tcg.obligations.ufc.TcgRenaming;

import jkind.Assert;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Constant;
import jkind.lustre.Contract;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.Function;
import jkind.lustre.FunctionCallExpr;
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
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.visitors.AstVisitor;
import jkind.lustre.visitors.TypeReconstructor;

/**
 * This class provides a visitor for generating UFC (MCDC) coverage from
 * Lustre programs.  The algorithm is derived from the Whalen ISSTA'06
 * paper on requirements coverage, extended to include:
 * 	Boolean relational operators
 *  Pre and arrow (->) expressions
 *  Boolean expressions in weird places.
 *
 *
 * @author mwwhalen
 *
 * MCDC_obligation:
 *
 *
 */


public class GenerateUfcObligationsVisitor implements AstVisitor<ObligationSet, ObligationSet> {

	public static final String TRAP_PROP_PREFIX = "TCG_TRAP_PROP_";
	public static final String GUARANTEE_PREFIX = "__GUARANTEE";

	private TypeReconstructor typeReconstructor;
	private Node currentNode;
	boolean generateForMainNodeObligationsOnly = true;
	boolean generateGuaranteeObligations = true;
	boolean generateAssumptionObligations = false;
	boolean generateEqObligations = false;
	boolean generatePropertyObligations = false;

	boolean generateBoundaryValueTests = false;
	double realDelta = 1.0;
	int intDelta = 1;

	protected Type getType(Expr e) {
		return e.accept(typeReconstructor);
	}

	protected boolean isBoolExpr(Expr e) {
		return (getType(e) == NamedType.BOOL);
	}

	///////////////////////////////////////////////////////////////////////////
	//
	// Note: this is not really correct; we want a value that is *less than or equal to*
	// the boundary distance, not *exactly* at the boundary distance.  For integers with
	// delta 1, it is the same, but it should be fixed for floating point numbers.  Note
	// also that to write these tests, we need to introduce additional inputs to
	// represent the deltas.
	//
	///////////////////////////////////////////////////////////////////////////

	protected void addBoundaryValueTests(Expr e, ObligationSet s) {
		if (e instanceof BinaryExpr && this.generateBoundaryValueTests) {
			Expr rightDelta = null;
			BinaryExpr be = (BinaryExpr)e;
			if (be.op == BinaryOp.EQUAL ||
					be.op == BinaryOp.GREATER ||
					be.op == BinaryOp.GREATEREQUAL ||
					be.op == BinaryOp.LESS ||
					be.op == BinaryOp.LESSEQUAL) {
				Type t = getType(be.left);
				if (t == NamedType.INT) {
					rightDelta = new IntExpr(BigInteger.valueOf(this.intDelta));
				} else if (t == NamedType.REAL) {
					rightDelta = new RealExpr(BigDecimal.valueOf(this.realDelta));
				}
				// if we are in a numeric relational operator AND
				// generateBoundaryValueTests is true, then we add them to the set.
				// Add tests at the boundary.
				if (rightDelta != null) {
					s.add(new BinaryExpr(be.left, BinaryOp.EQUAL,
							new BinaryExpr(be.right, BinaryOp.PLUS, rightDelta)));
					s.add(new BinaryExpr(be.left, BinaryOp.EQUAL, rightDelta));
					s.add(new BinaryExpr(be.left, BinaryOp.EQUAL,
							new BinaryExpr(be.right, BinaryOp.MINUS, rightDelta)));
				}
			}
		}
	}

	protected Expr notExpr(Expr e) {
		return new UnaryExpr(UnaryOp.NOT, e);
	}

	protected Expr binExpr(Expr left, BinaryOp bin, Expr right) {
		return new BinaryExpr(left, bin, right);
	}


	public Map<Node, ObligationSet> obligations = new HashMap<>();

	private final Program initialProgram;
	private TcgRenaming renaming;

	public GenerateUfcObligationsVisitor(Program initialProgram, TcgRenaming renaming) {
		this.initialProgram = initialProgram;
		this.renaming = renaming;
	}

	public void traverse() {
		visit(initialProgram);
	}

	/**
	 * Method for constructing new programs using the UFC obligations.
	 */

	private Node constructNewNode(Node existing, ObligationSet obs) {
		List<VarDecl> locals = new ArrayList<>(existing.locals);
		List<Equation> equations = new ArrayList<>(existing.equations);
		// List<String> properties = new ArrayList<>(existing.properties);

		// Change: MWW 5/6: Don't really want existing properties here.
		List<String> properties = new ArrayList<>();


		/* writing the TCG obligations back as trap properties by negating them... */
		for (int i = 0; i < obs.positivePolarity.size(); i++) {
			String varName = TRAP_PROP_PREFIX + i;
			Obligation ob = obs.positivePolarity.get(i);
			locals.add(new VarDecl(varName, NamedType.BOOL));
			equations.add(new Equation(new IdExpr(varName),
					new UnaryExpr(UnaryOp.NOT, ob.getObligationExpr())));
			properties.add(varName);

			// renaming information
			if(renaming != null) {
				renaming.addRenaming(varName, ob.getEqAssignId(), ob.getTestedCondition().toString());
			}
			System.out.println("Renaming: mapping " + varName + " --> " + ob.getEqAssignId());
		}

		Node newNode = new Node(existing.id, existing.inputs, existing.outputs, locals, equations, properties, existing.assertions, null, null, new ArrayList<String>());
		return newNode;
	}

	public Program constructNewProgram() {
		List<Node> nodes = new ArrayList<>();
		for (Entry<Node, ObligationSet> nodeElem : obligations.entrySet()) {
			nodes.add(constructNewNode(nodeElem.getKey(), nodeElem.getValue()));
		}

		Program newProgram =
				new Program(initialProgram.location, initialProgram.types,
						initialProgram.constants, Collections.emptyList(), nodes, initialProgram.main);

		return newProgram;
	}



	// under the covers stuff...not meant to be called by 'user'.

	@Override
	public ObligationSet visit(Program program) {
		typeReconstructor = new TypeReconstructor(program);

		Iterator<Node> iterator = program.nodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().accept(this);
		}
		return null;
	}


	@Override
	public ObligationSet visit(Function function) {
		return null;
	}

	@Override
	public ObligationSet visit(Node node) {
		typeReconstructor.setNodeContext(node);
		if (node.id.equals(initialProgram.main) ||
				!this.generateForMainNodeObligationsOnly) {
			typeReconstructor.setNodeContext(node);
			currentNode = node;

			ObligationSet allExprs = new ObligationSet();

			for (Equation equation : node.equations) {
				allExprs.addAll(equation.accept(this));
			}

			for (Expr assertion : node.assertions) {
				allExprs.addAll(assertion(assertion));
			}

			if (!node.properties.isEmpty()) {
				for (String property : node.properties) {
					allExprs.addAll(property(property));
				}
			}

			obligations.put(node, allExprs);
			currentNode = null;
		}
		else {
			obligations.put(node, new ObligationSet());
		}
		return null;
	}


	@Override
	public ObligationSet visit(Equation equation) {
		ObligationSet result = new ObligationSet();

		boolean isGuaranteeEq = equation.lhs.size() == 1 &&
				equation.lhs.get(0).id.startsWith(GenerateUfcObligationsVisitor.GUARANTEE_PREFIX);
		boolean isAssumptionEq = false ; /* how do we determine this? */
		boolean isNormalEq = !isGuaranteeEq ;

		if (this.generateGuaranteeObligations && isGuaranteeEq) {
			if (equation.lhs.size() == 1 &&
					equation.lhs.get(0).id.startsWith(GenerateUfcObligationsVisitor.GUARANTEE_PREFIX)) {
				result = expr(equation.expr);
			}
		} else if (this.generateAssumptionObligations && isAssumptionEq) {
			result = expr(equation.expr);
		} else if (this.generateEqObligations && isNormalEq) {
			result = expr(equation.expr);
		} else if(this.generatePropertyObligations) {
			// Check if the equation is for a property in the current node
			if (equation.lhs.size() == 1 &&
					currentNode != null &&
					currentNode.properties.contains(equation.lhs.get(0).id)) {
				result = expr(equation.expr);
			}
		}

		if (!equation.lhs.isEmpty()) {
			result.setEqId(equation.lhs.get(0).id);
		}
		return result;
	}

	private ObligationSet assertion(Expr assertion) {
		if (this.generateGuaranteeObligations || this.generatePropertyObligations) {
			return new ObligationSet();
		} else {
			return expr(assertion);
		}
	}

	private ObligationSet property(String s) {
		return new ObligationSet();
	}

	public ObligationSet expr(Expr e) {
		return e.accept(this);
	}

	public ObligationSet baseExpr(Expr e, ObligationSet childSet) {
		if (isBoolExpr(e)) {
			childSet.add(e);
			if (this.generateBoundaryValueTests) {
				addBoundaryValueTests(e, childSet);
			}
		}
		return childSet;
	}

	@Override
	public ObligationSet visit(ArrayAccessExpr e) {
		ObligationSet obs1 = e.array.accept(this);
		ObligationSet obs2 = e.index.accept(this);
		obs1.addAll(obs2);
		return baseExpr(e, obs1);
	}

	@Override
	public ObligationSet visit(ArrayExpr e) {
		ObligationSet fullSet = new ObligationSet();
		for (Expr elem : e.elements) {
			fullSet.addAll(elem.accept(this));
		}
		return baseExpr(e, fullSet);
	}

	@Override
	public ObligationSet visit(ArrayUpdateExpr e) {
		ObligationSet obs1 = e.array.accept(this);
		ObligationSet obs2 = e.index.accept(this);
		ObligationSet obs3 = e.value.accept(this);
		obs1.addAll(obs2);
		obs1.addAll(obs3);
		return baseExpr(e, obs1);
	}

	@Override
	public ObligationSet visit(BinaryExpr e) {
		if (e.op == BinaryOp.AND) {
			ObligationSet lhs = e.left.accept(this);
			ObligationSet rhs = e.right.accept(this);
			lhs.extendContext(e.right);
			rhs.extendContext(e.left);
			lhs.addAll(rhs);
			return lhs;
		} else if (e.op == BinaryOp.OR){
			ObligationSet lhs = e.left.accept(this);
			ObligationSet rhs = e.right.accept(this);
			lhs.extendContext(notExpr(e.right));
			rhs.extendContext(notExpr(e.left));
			lhs.addAll(rhs);
			return lhs;
		} else if (e.op == BinaryOp.IMPLIES) {
			Expr equivExpr = binExpr(notExpr(e.left), BinaryOp.OR, e.right);
			return equivExpr.accept(this);
		}
		else if (e.op == BinaryOp.ARROW) {
			ObligationSet lhs = e.left.accept(this);
			ObligationSet rhs = e.right.accept(this);
			lhs.makeInitStepOnly();
			rhs.makeAfter1Only();
			lhs.addAll(rhs);
			return lhs;
		} else if (isBoolExpr(e.left)) {
			if (e.op == BinaryOp.XOR || e.op == BinaryOp.NOTEQUAL) {
				Expr equivExpr = notExpr(binExpr(e.left, BinaryOp.EQUAL, e.right));
				return equivExpr.accept(this);
			} else if (e.op == BinaryOp.EQUAL) {
				Expr equivExpr =
						binExpr(binExpr(e.left, BinaryOp.AND, e.right),
								BinaryOp.OR,
								notExpr(binExpr(e.left, BinaryOp.OR, e.right)));
				return equivExpr.accept(this);
			} else {
				// other relational operator over booleans (this may be dead code)
				ObligationSet lhs = e.left.accept(this);
				ObligationSet rhs = e.right.accept(this);
				lhs.addAll(rhs);
				return baseExpr(e, lhs);
			}
		} else {
			// Other kind of operator (arithmetic?)
			ObligationSet lhs = e.left.accept(this);
			ObligationSet rhs = e.right.accept(this);
			lhs.addAll(rhs);
			return baseExpr(e, lhs);
		}
	}

	@Override
	public ObligationSet visit(BoolExpr e) {
		ObligationSet s = new ObligationSet(e);
		return s;
	}

	@Override
	public ObligationSet visit(CastExpr e) {
		ObligationSet s = e.expr.accept(this);
		return baseExpr(e, s);
	}

	// TODO: double check this for init expression args.
	@Override
	public ObligationSet visit(CondactExpr e) {
		ObligationSet obs1 = e.clock.accept(this);
		ObligationSet obs2 = e.call.accept(this);
		ObligationSet obs3 = new ObligationSet();
		for (Expr arg : e.args) {
			obs3.addAll(arg.accept(this));
		}

		// inputs are only ``effecting'' when clock is true.
		obs2.extendContext(e.clock);

		// initial arguments are only effecting before the first clock, not just instants
		// when the clock is low, but we would need a fresh variable to describe this
		// situation, so we are being somewhat inaccurate.
		obs3.extendContext(notExpr(e.clock));

		obs1.addAll(obs2);
		obs1.addAll(obs3);
		return obs1;
	}

	@Override
	public ObligationSet visit(IdExpr e) {
		return baseExpr(e, new ObligationSet());
	}

	@Override
	public ObligationSet visit(IfThenElseExpr e) {
		ObligationSet obs1 = e.cond.accept(this);
		ObligationSet obs2 = e.thenExpr.accept(this);
		ObligationSet obs3 = e.elseExpr.accept(this);

		obs2.extendContext(e.cond);
		obs3.extendContext(notExpr(e.cond));

		obs1.addAll(obs2);
		obs1.addAll(obs3);
		return obs1;
	}

	@Override
	public ObligationSet visit(IntExpr e) {
		return new ObligationSet();
	}

	@Override
	public ObligationSet visit(FunctionCallExpr e) {
		ObligationSet obs3 = new ObligationSet();
		for (Expr arg : e.args) {
			obs3.addAll(arg.accept(this));
		}
		return baseExpr(e, obs3);
	}

	@Override
	public ObligationSet visit(NodeCallExpr e) {
		ObligationSet obs3 = new ObligationSet();
		for (Expr arg : e.args) {
			obs3.addAll(arg.accept(this));
		}
		return baseExpr(e, obs3);
	}

	@Override
	public ObligationSet visit(RealExpr e) {
		return new ObligationSet();
	}

	@Override
	public ObligationSet visit(RecordAccessExpr e) {
		ObligationSet obs1 = e.record.accept(this);
		return baseExpr(e, obs1);
	}

	@Override
	public ObligationSet visit(RecordExpr e) {
		ObligationSet obs = new ObligationSet();
		for (Entry<String, Expr> elem: e.fields.entrySet()) {
			obs.addAll(elem.getValue().accept(this));
		}
		return obs;
	}

	@Override
	public ObligationSet visit(RecordUpdateExpr e) {
		ObligationSet obs1 = e.record.accept(this);
		ObligationSet obs2 = e.value.accept(this);
		obs1.addAll(obs2);
		return baseExpr(e, obs1);
	}

	@Override
	public ObligationSet visit(TupleExpr e) {
		ObligationSet obs = new ObligationSet();
		for (Expr elem : e.elements) {
			obs.addAll(elem.accept(this));
		}
		return obs;
	}

	@Override
	public ObligationSet visit(UnaryExpr e) {
		ObligationSet obs = e.expr.accept(this);
		if (e.op == UnaryOp.NOT) {
			obs.swapPolarities();
		} else if (e.op == UnaryOp.PRE) {
			obs.extendPre();
		}
		return obs;
	}

	@Override
	public ObligationSet visit(Constant constant) {
		// TODO Auto-generated method stub
		Assert.isTrue(false);
		return null;
	}

	@Override
	public ObligationSet visit(TypeDef typeDef) {
		// TODO Auto-generated method stub
		Assert.isTrue(false);
		return null;
	}

	@Override
	public ObligationSet visit(VarDecl varDecl) {
		// TODO Auto-generated method stub
		Assert.isTrue(false);
		return null;
	}

	@Override
	public ObligationSet visit(Contract contract) {
		throw new TcgException("GenerateUfcObligationsVisitor: syntax for Lustre contracts is currently unsupported.");
	}

	public boolean isGenerateGuaranteeObligations() {
		return generateGuaranteeObligations;
	}

	public void setGenerateGuaranteeObligations(boolean generateGuaranteeObligations) {
		this.generateGuaranteeObligations = generateGuaranteeObligations;
	}

	public boolean isGenerateAssumptionObligations() {
		return generateAssumptionObligations;
	}

	public void setGenerateAssumptionObligations(
			boolean generateAssumptionObligations) {
		this.generateAssumptionObligations = generateAssumptionObligations;
	}

	public boolean isGenerateEqObligations() {
		return generateEqObligations;
	}

	public void setGenerateEqObligations(boolean generateEqObligations) {
		this.generateEqObligations = generateEqObligations;
	}

	public boolean isGenerateBoundaryValueTests() {
		return generateBoundaryValueTests;
	}

	public void setGenerateBoundaryValueTests(boolean generateBoundaryValueTests) {
		this.generateBoundaryValueTests = generateBoundaryValueTests;
	}

	public boolean isGeneratePropertyObligations() {
		return generatePropertyObligations;
	}

	public void setGeneratePropertyObligations(
			boolean generatePropertyObligations) {
		this.generatePropertyObligations = generatePropertyObligations;
	}

}