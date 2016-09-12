// Generated from SendsTo.g4 by ANTLR 4.1

    package edu.umn.cs.crisys.tb.parse.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SendsToParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SendsToVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SendsToParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull SendsToParser.ProgramContext ctx);

	/**
	 * Visit a parse tree produced by {@link SendsToParser#sends_to_tl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSends_to_tl(@NotNull SendsToParser.Sends_to_tlContext ctx);
}