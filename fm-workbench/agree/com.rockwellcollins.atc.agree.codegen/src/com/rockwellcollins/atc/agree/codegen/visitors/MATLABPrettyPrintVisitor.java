package com.rockwellcollins.atc.agree.codegen.visitors;

import java.util.Iterator;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarStatement;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABStatement;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUnaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUnaryOp;

public class MATLABPrettyPrintVisitor implements MATLABAstVisitor<Void, Void> {

	private StringBuilder sb = new StringBuilder();
	private boolean endOfPrimaryFunction = false;

	@Override
	public String toString() {
		return sb.toString();
	}

	protected void write(Object o) {
		sb.append(o);
	}

	private static final String seperator = System.getProperty("line.separator");

	private void newline() {
		write(seperator);
	}
	
	@Override
	public Void visit(MATLABPrimaryFunction primaryFunction) {
		//write function header
		write("function "+primaryFunction.name);
		write("(");
		//write inputs, separated by ","
		Iterator<MATLABIdExpr> iterator = primaryFunction.inputs.iterator();
		while (iterator.hasNext()) {
			iterator.next().accept(this);
			if (iterator.hasNext()) {
				write(", ");
			}
		}
		write(")");
		newline();
		newline();
		
		//write preVar declarations and initializations
		for(MATLABPersistentVarDecl persistentVarDecl: primaryFunction.persistentVarDecl){
			persistentVarDecl.accept(this);
		}
		
		//write statements
		for(MATLABStatement statement: primaryFunction.statements){
			statement.accept(this);
		}
		
		newline();
		
		write("end");
		
		newline();
		newline();
		//write function definitions
		for(MATLABFunction function: primaryFunction.functions){
			function.accept(this);
		}
		
		return null;
	}
	
	@Override
	public Void visit(MATLABAssignment assignment) {
		assignment.varToAssign.accept(this);
		write(" = ");
		expr(assignment.expr);
		write(";");
		newline();
		return null;
	}
	
	public void expr(MATLABExpr e) {
		e.accept(this);
	}
	
	@Override
	public Void visit(MATLABBinaryExpr e) {
		write("(");
		expr(e.left);
		write(" ");
		write(e.op);
		write(" ");
		expr(e.right);
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABBinaryFunctionCall e) {
		write(e.name);
		write("(");
		expr(e.leftExpr);
		write(", ");
		expr(e.rightExpr);
		write(")");
		return null;
	}
	
	@Override
	public Void visit(MATLABBoolExpr e) {
		write(Boolean.toString(e.value));
		return null;
	}

	@Override
	public Void visit(MATLABIdExpr e) {
		write(e.id);
		return null;
	}

	@Override
	public Void visit(MATLABIfFunctionCall e) {
		write(e.name);
		write("(");
		expr(e.cond);
		write(", ");
		expr(e.ifExpr);
		write(", ");
		expr(e.elseExpr);
		write(")");
		return null;
	}
	
	@Override
	public Void visit(MATLABIntExpr e) {
		write(e.value);
		return null;
	}

	@Override
	public Void visit(MATLABUnaryExpr e) {
		write("(");
		write(e.op);
		if (e.op != MATLABUnaryOp.NEGATIVE) {
			write(" ");
		}
		expr(e.expr);
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABIntCastExpr e) {
		write("int32");
		write("(");
		expr(e.expr);
		write(")");
		return null;
	}
	
	/**function output = arrowFunction(first_time, left, right)
	 * if isempty(first_time)
	 *    output = left;
	 * else
	 *    output = right;
	 * end
	 */
	@Override
	public Void visit(MATLABArrowFunction function) {
		write("function output = ");
		write(function.name);
		write("(first_time, left, right)");
		newline();
		write("if (first_time)");
		newline();
		write("\toutput = left;");
		newline();
		write("else");
		newline();
		write("\toutput = right;");
		newline();
		write("end");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}

	/**function output = ifFunction(cond, ifBranch, elseBranch)
	 * if(cond)
	 *    output = ifBranch;
	 * else
	 *    output = elseBranch;
	 * end
	 */
	@Override
	public Void visit(MATLABIfFunction function) {
		write("function output = ");
		write(function.name);
		write("(cond, ifBranch, elseBranch)");
		newline();
		write("if (cond)");
		newline();
		write("\toutput = ifBranch;");
		newline();
		write("else");
		newline();
		write("\toutput = elseBranch;");
		newline();
		write("end");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}

	
	/**function output = impliesFunction(left, right)
	 * 		output = (not(left) || right);
	 * end
	 */
	@Override
	public Void visit(MATLABImpliesFunction function) {
		write("function output = ");
		write(function.name);
		write("(left, right)");
		newline();
		write("\toutput = (not(left) || right);");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}

	
	/**
	 * persistent preVar
	 */
	@Override 
	public Void visit(MATLABPersistentVarDecl varDecl) {
		write("persistent ");
		write(varDecl.var);
		newline();
		return null;
	}

	
	/**
	 * if isempty(preVar)
	 *    preVar = coder.nullcopy(var);
	 * end
	 */
	@Override
	public Void visit(MATLABPreVarInit preVarInit) {
		newline();
		write("if isempty(");
		write(preVarInit.preVar);
		write(")");
		newline();
		write("\t");
		write(preVarInit.preVar);
		write(" = coder.nullcopy(");
		write(preVarInit.var);
		write(");");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}

	@Override
	public Void visit(MATLABArrowFunctionCall e) {
		write(e.name);
		write("(");
		write(e.firstTimeVar);
		write(",");
		expr(e.leftExpr);
		write(", ");
		expr(e.rightExpr);
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABPersistentVarStatement statement) {
		if(endOfPrimaryFunction){
			statement.assignment.accept(this);
		}
		else{
			statement.decl.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(MATLABFirstTimeVarInit statement) {
		newline();
		write("if isempty(");
		write(statement.firstTimeVar);
		write(")");
		newline();
		write("\t");
		write(statement.firstTimeVar);
		write(" = true;");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}

	@Override
	public Void visit(MATLABAssumption assertion) {
		newline();
		write("sldv.assume(");
		expr(assertion.expr);
		write(")");
		newline();
		return null;
	}

	@Override
	public Void visit(MATLABProperty property) {
		newline();
		write("sldv.prove(");
		write(property.id);
		write(")");
		newline();
		return null;
	}

	@Override
	public Void visit(MATLABDoubleExpr e) {
		write(e.value);
		return null;
	}

	@Override
	public Void visit(MATLABBusElementExpr e) {
		expr(e.busExpr);
		write(".");
		expr(e.element);
		return null;
	}

	@Override
	public Void visit(MATLABBusExpr e) {
		write(e.id);
		return null;
	}

}
