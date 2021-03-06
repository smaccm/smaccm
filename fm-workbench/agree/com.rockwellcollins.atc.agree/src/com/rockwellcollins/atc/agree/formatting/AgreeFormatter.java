/*
 * generated by Xtext
 */
package com.rockwellcollins.atc.agree.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;

/**
 * This class contains custom formatting description.
 *
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting on how and
 * when to use it
 *
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an
 * example
 */
public class AgreeFormatter extends AbstractDeclarativeFormatter {

	@Inject
	private AgreeGrammarAccess grammarAccess;

	@Override
	protected void configureFormatting(FormattingConfig c) {
		// It's usually a good idea to activate the following three statements.
		// They will add and preserve newlines around comments
		c.setLinewrap(0, 1, 2).before(grammarAccess.getSL_COMMENTRule());
		// c.setLinewrap(0, 1, 2).before(grammarAccess.getML_COMMENTRule());
		// c.setLinewrap(0, 1, 1).after(grammarAccess.getML_COMMENTRule());

		c.setAutoLinewrap(120);

		// set line wrap after semicolon
		for (Keyword keywd : grammarAccess.findKeywords(";")) {
			c.setLinewrap().after(keywd);
		}

		// set no space before dots, commas and semicolons, right paren
		for (Keyword keywd : grammarAccess.findKeywords(".", ",", ";", ")")) {
			c.setNoSpace().before(keywd);
		}

		// set no space after dots and right paren
		for (Keyword keywd : grammarAccess.findKeywords(".", "(")) {
			c.setNoSpace().after(keywd);
		}

		// set and blank line between specification statements
		// But, it seems to not work for positioning with respect to
		// abstract base classes such as SpecStatement
		c.setLinewrap(2).after(grammarAccess.getSpecStatementRule());
		c.setLinewrap(2).before(grammarAccess.getSynchStatementRule());
		c.setLinewrap(2).before(grammarAccess.getOrderStatementRule());
		c.setLinewrap(2).before(grammarAccess.getPropertyStatementRule());
		c.setLinewrap(2).before(grammarAccess.getConstStatementRule());
		c.setLinewrap(2).before(grammarAccess.getEnumStatementRule());
		c.setLinewrap(2).before(grammarAccess.getEqStatementRule());
		c.setLinewrap(2).before(grammarAccess.getAssignStatementRule());
		c.setLinewrap(2).before(grammarAccess.getLinearizationDefExprRule());
		c.setLinewrap(2).before(grammarAccess.getFnDefExprRule());
		c.setLinewrap(2).before(grammarAccess.getLibraryFnDefExprRule());
		c.setLinewrap(2).before(grammarAccess.getNodeDefExprRule());
		c.setLinewrap(2).before(grammarAccess.getRecordDefExprRule());
		c.setLinewrap(2).before(grammarAccess.getInputStatementRule());

		// set line wrap before node def, let, tel, each local var
		c.setLinewrap().after(grammarAccess.getNodeStmtRule());
		c.setLinewrap().after(grammarAccess.getNodeDefExprAccess().getArgsAssignment_3_1_1());
		c.setLinewrap().after(grammarAccess.getNodeDefExprAccess().getReturnsKeyword_5());
		c.setLinewrap().after(grammarAccess.getNodeBodyExprAccess().getLetKeyword_1());
		c.setLinewrap().after(grammarAccess.getNodeBodyExprAccess().getTelKeyword_3());
		c.setIndentationIncrement().after(grammarAccess.getNodeBodyExprAccess().getLetKeyword_1());
		c.setIndentationDecrement().before(grammarAccess.getNodeBodyExprAccess().getTelKeyword_3());

		// set line indentation inside for ITE
		c.setLinewrap().before(grammarAccess.getIfThenElseExprAccess().getThenKeyword_0_3());
		// c.setIndentationIncrement().before(grammarAccess.getIfThenElseExprAccess().getBExprParserRuleCall_0_4_0());
		// c.setIndentationDecrement().after(grammarAccess.getIfThenElseExprAccess().getBExprParserRuleCall_0_4_0());
		c.setLinewrap().before(grammarAccess.getIfThenElseExprAccess().getElseKeyword_0_5());
		// c.setIndentationIncrement().before(grammarAccess.getIfThenElseExprAccess().getCExprParserRuleCall_0_6_0());
		// c.setIndentationDecrement().after(grammarAccess.getIfThenElseExprAccess().getCExprParserRuleCall_0_6_0());

		// set line indentation inside all parentheses
		// grammarAccess.getRecordUpdateExprRule().g
		for (Pair<Keyword, Keyword> p : grammarAccess.findKeywordPairs("(", ")")) {
			c.setIndentationIncrement().after(p.getFirst());
			c.setIndentationDecrement().before(p.getSecond());
			c.setLinewrap().before(p.getFirst());
			c.setLinewrap().before(p.getSecond());
		}

		// set line indentation inside all curly brackets
		// set line wrap after each left curly bracket
		// set line wrap around each right curly bracket
		for (Pair<Keyword, Keyword> p : grammarAccess.findKeywordPairs("{", "}")) {
			c.setIndentationIncrement().after(p.getFirst());
			c.setIndentationDecrement().before(p.getSecond());
		}
	}
}