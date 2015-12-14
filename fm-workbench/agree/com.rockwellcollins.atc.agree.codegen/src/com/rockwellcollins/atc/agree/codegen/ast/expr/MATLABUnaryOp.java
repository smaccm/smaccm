package com.rockwellcollins.atc.agree.codegen.ast.expr;

public enum MATLABUnaryOp {
	NEGATIVE ("-"),
	NOT ("not");
	
	private String str;
	
	private MATLABUnaryOp(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return str;
	}
	
	public static MATLABUnaryOp fromName(String name) {
		for (MATLABUnaryOp op : MATLABUnaryOp.values()) {
			if (op.name().equals(name)) {
				return op;
			}
		}
		return null;
	}
}

