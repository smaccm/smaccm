package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrowFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFirstTimeVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABIfFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABImpliesFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABLocalBusVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreInputVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPreLocalVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;

public interface MATLABAstVisitor<T, S extends T> extends MATLABExprVisitor<S> {
	public T visit(MATLABArrowFunction matlabArrowFunction);

	public T visit(MATLABPrimaryFunction matlabPrimaryFunction);

	public T visit(MATLABIfFunction matlabIfFunction);

	public T visit(MATLABImpliesFunction matlabImpliesFunction);

	public T visit(MATLABAssignment matlabAssignment);

	public T visit(MATLABPersistentVarDecl matlabPersistentVarDecl);

	public T visit(MATLABPreInputVarInit matlabPreVarInit);

	public T visit(MATLABFirstTimeVarInit matlabFirstTimeVarInit);

	public T visit(MATLABAssumption matlabAssertion);

	public T visit(MATLABProperty matlabProperty);

	public T visit(MATLABPreLocalVarInit matlabPreLocalVarInit);

	public T visit(MATLABLocalBusVarInit matlabLocalBusVarInit);


}