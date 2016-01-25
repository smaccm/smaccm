package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt8Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABSingleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt16Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt64Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABUInt8Type;


public interface MATLABTypeVisitor<T> {

	public T visit(MATLABInt32Type matlabInt32Type);

	public T visit(MATLABDoubleType matlabDoubleType);

	public T visit(MATLABBoolType matlabBoolType);

	public T visit(MATLABBusType matlabBusType);

	public T visit(MATLABUInt32Type matlabuInt32Type);

	public T visit(MATLABInt8Type matlabInt8Type);

	public T visit(MATLABUInt8Type matlabuInt8Type);

	public T visit(MATLABInt16Type matlabInt16Type);

	public T visit(MATLABUInt16Type matlabuInt16Type);

	public T visit(MATLABSingleType matlabSingleType);

	public T visit(MATLABInt64Type matlabInt64Type);

	public T visit(MATLABUInt64Type matlabuInt64Type);

}
