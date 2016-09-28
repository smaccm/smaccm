// Generated from SendsTo.g4 by ANTLR 4.1

    package edu.umn.cs.crisys.tb.parse.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SendsToParser}.
 */
public interface SendsToListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SendsToParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull SendsToParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SendsToParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull SendsToParser.ProgramContext ctx);

	/**
	 * Enter a parse tree produced by {@link SendsToParser#sends_to_tl}.
	 * @param ctx the parse tree
	 */
	void enterSends_to_tl(@NotNull SendsToParser.Sends_to_tlContext ctx);
	/**
	 * Exit a parse tree produced by {@link SendsToParser#sends_to_tl}.
	 * @param ctx the parse tree
	 */
	void exitSends_to_tl(@NotNull SendsToParser.Sends_to_tlContext ctx);
}