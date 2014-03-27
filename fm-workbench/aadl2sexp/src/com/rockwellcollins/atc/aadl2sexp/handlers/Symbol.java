package com.rockwellcollins.atc.aadl2sexp.handlers;


public class Symbol extends Sexp {
	public final String symbol;

	public Symbol(String symbol) {
		this.symbol = symbol;
	}

    @Override
    protected void appendTo(StringBuilder sb, String indent) {
        sb.append(indent + symbol);
    }
}
