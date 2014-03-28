package com.rockwellcollins.atc.aadl2sexp.handlers;


public abstract class Sexp {
    protected abstract void appendTo(StringBuilder sb, String indent);
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendTo(sb, "");
        return sb.toString();
    }
}
