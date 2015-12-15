package com.rockwellcollins.atc.agree.codegen.visitors;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABArrayType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBoolType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABDoubleType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABInt32Type;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;

import jkind.lustre.ArrayType;
import jkind.lustre.EnumType;
import jkind.lustre.NamedType;
import jkind.lustre.RecordType;
import jkind.lustre.SubrangeIntType;
import jkind.lustre.TupleType;
import jkind.lustre.visitors.TypeVisitor;

public class LustreToMATLABTypeVisitor implements TypeVisitor<MATLABType> {
	
	@Override
	public MATLABType visit(ArrayType e) {
		//return new ArrayType(e.location, e.base.accept(this), e.size);
		return new MATLABArrayType();
	}

	@Override
	public MATLABType visit(NamedType e) {
		
		if(e.name.equals("bool"))
			return new MATLABBoolType();
		else if(e.name.equals("int"))
			return new MATLABInt32Type();
		else if(e.name.equals("real"))
			return new MATLABDoubleType();
		else
			return new MATLABBusType(e.name);
	}
	
	@Override
	public MATLABType visit(EnumType e) {
		//return e;
		throw new IllegalArgumentException(); 
	}

	@Override
	public MATLABType visit(RecordType e) {
		//Map<String, Type> fields = new HashMap<>();
		//for (Entry<String, Type> entry : e.fields.entrySet()) {
			//fields.put(entry.getKey(), entry.getValue().accept(this));
		//}
		//return new RecordType(e.location, e.id, fields);
		return new MATLABBusType(e.id);
	}

	@Override
	public MATLABType visit(TupleType e) {
		throw new IllegalArgumentException(); 
	}
	
	@Override
	public MATLABType visit(SubrangeIntType e) {
		//translate to int type
		//TODO add assumptions for the min and max values
		return new MATLABInt32Type();
	}
}
