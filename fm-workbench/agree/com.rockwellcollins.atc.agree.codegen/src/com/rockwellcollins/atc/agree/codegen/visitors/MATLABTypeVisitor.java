package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;


public interface MATLABTypeVisitor<T> {

	public T visit(MATLABInt32Type matlabInt32Type);

	public T visit(MATLABDoubleType matlabDoubleType);

	public T visit(MATLABBoolType matlabBoolType);

}
