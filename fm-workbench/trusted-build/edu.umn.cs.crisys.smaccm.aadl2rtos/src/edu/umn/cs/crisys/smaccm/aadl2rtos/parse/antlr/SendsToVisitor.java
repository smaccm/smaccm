// Generated from SendsTo.g4 by ANTLR 4.0

    package edu.umn.cs.crisys.smaccm.aadl2rtos.parse.antlr;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface SendsToVisitor<T> extends ParseTreeVisitor<T> {
	T visitProgram(SendsToParser.ProgramContext ctx);

	T visitSends_to_tl(SendsToParser.Sends_to_tlContext ctx);
}