package com.rockwellcollins.atc.aadl2sexp.handlers;

import java.util.Arrays;
import java.util.List;

public class Cons extends Sexp {
    public final String operator;
    public final List<Sexp> args;

    public Cons(String operator, List<Sexp> args) {
        this.operator = operator;
        this.args = args;
    }

    public Cons(String operator, Sexp... args) {
        this.operator = operator;
        this.args = Arrays.asList(args);
    }

    @Override
    protected void appendTo(StringBuilder sb, String indent) {
        sb.append(indent + "(" + operator);
        for (Sexp arg : args) {
            sb.append("\n");
            arg.appendTo(sb, indent + "  ");
        }
        sb.append(")");
    }
}
