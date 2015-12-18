package com.rockwellcollins.atc.agree.codegen.ast.expr;

public enum MATLABBinaryOp {
		PLUS ("+"),
		MINUS ("-"),
		MULTIPLY ("*"),
		DIVIDE ("/"),
		GREATER (">"),
		LESS ("<"),	
		GREATEREQUAL (">="),
		LESSEQUAL ("<="),
		NOTEQUAL ("~="),
		OR ("||"),
		AND ("&&");
		
		private String str;
		
		private MATLABBinaryOp(String str) {
			this.str = str;
		}
		
		@Override
		public String toString() {
			return str;
		}
		
		public static MATLABBinaryOp fromName(String name) {
			for (MATLABBinaryOp op : MATLABBinaryOp.values()) {
				if (op.name().equals(name)) {
					return op;
				}
			}
			return null;
		}
	
}
