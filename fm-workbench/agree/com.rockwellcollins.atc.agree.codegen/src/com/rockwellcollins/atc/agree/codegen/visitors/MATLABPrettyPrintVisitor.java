package com.rockwellcollins.atc.agree.codegen.visitors;

import java.util.Iterator;
import java.util.Map.Entry;




import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt8Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABLocalBusVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreInputVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreLocalVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABSingleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABStatement;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt8Type;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrayAccessExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABArrowFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBinaryFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBoolExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABBusElementUpdateExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABDoubleExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIfFunctionCall;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIntExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeCastExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABTypeInitExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABUnaryExpr;

public class MATLABPrettyPrintVisitor implements MATLABTypeVisitor<Void>, MATLABAstVisitor<Void, Void>  {

	private StringBuilder sb = new StringBuilder();

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
		
		newline();
		
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
	
	public void type(MATLABType t) {
		t.accept(this);
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
		write(e.op);
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

	
	@Override
	public Void visit(MATLABPreInputVarInit preVarInit) {
		newline();
		write("if isempty(");
		write(preVarInit.preVar);
		write(")");
		newline();
		write("\t");
		write(preVarInit.preVar);
		write (" = ");
		//write(" = coder.nullcopy(");
		write(preVarInit.var);
		//write(");");
		write (";");
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
	public Void visit(MATLABAssumption assumption) {
		newline();
		write("sldv.assume(");
		expr(assumption.expr);
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
	public Void visit(MATLABPreLocalVarInit preVarInit) {
		newline();
		write("if isempty(");
		write(preVarInit.preVar);
		write(")");
		newline();
		write("\t");
		write(preVarInit.preVar);
		write(" = ");
		//write(" = coder.nullcopy(");
		preVarInit.typeInitExpr.accept(this);
		//write(");");
		write(";");
		newline();
		write("end");
		newline();
		newline();
		return null;
	}
	
	@Override
	public Void visit(MATLABUInt32Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABInt8Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABUInt8Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABInt16Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABUInt16Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABInt64Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABUInt64Type type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABInt32Type type) {
		write(type.name);
		return null;
	}
	
	@Override
	public Void visit(MATLABSingleType type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABDoubleType type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABBoolType type) {
		//no need to typecast Boolean 
		//also Boolean() is illegal
		return null;
	}
	
	@Override
	public Void visit(MATLABBusType type) {
		write(type.name);
		return null;
	}

	@Override
	public Void visit(MATLABTypeCastExpr e) {
		type(e.type);
		write("(");
		expr(e.expr);
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABTypeInitExpr e) {
		type(e.type);
		write("(");
		write(e.type.getValueStr());
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABArrayAccessExpr e) {
		expr(e.array);
		write("(");
		expr(e.index);
		write(")");
		return null;
	}

	@Override
	public Void visit(MATLABBusElementUpdateExpr e) {
		expr(e.value);
		return null;
	}

	@Override
	public Void visit(MATLABLocalBusVarInit busVarInit) {
		Iterator<Entry<String, MATLABExpr>> iterator = busVarInit.fields.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, MATLABExpr> entry = iterator.next();
			if(busVarInit.newVar != null){
				write(busVarInit.newVar);
				write("=");
				write(busVarInit.originalVar);
				write(";");
				newline();
				write(busVarInit.newVar);
			}
			else{
				write(busVarInit.originalVar);
			}
			write(".");
			write(entry.getKey());
			write(" = ");
			expr(entry.getValue());
			write(";");
			newline();
		}
		return null;
	}
	
}
