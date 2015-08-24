package com.rockwellcollins.atc.agree.serializer;

import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public abstract class AbstractAgreeSyntacticSequencer extends AbstractSyntacticSequencer {

	protected AgreeGrammarAccess grammarAccess;
	protected AbstractElementAlias match_TermExpr_LeftParenthesisKeyword_9_0_a;
	protected AbstractElementAlias match_TermExpr_LeftParenthesisKeyword_9_0_p;
	protected AbstractElementAlias match_Type_INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1;
	protected AbstractElementAlias match_Type_INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1;
	protected AbstractElementAlias match_Type___LeftSquareBracketKeyword_0_2_0___INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1___CommaKeyword_0_2_3___INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1___RightSquareBracketKeyword_0_2_6__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (AgreeGrammarAccess) access;
		match_TermExpr_LeftParenthesisKeyword_9_0_a = new TokenAlias(true, true, grammarAccess.getTermExprAccess().getLeftParenthesisKeyword_9_0());
		match_TermExpr_LeftParenthesisKeyword_9_0_p = new TokenAlias(true, false, grammarAccess.getTermExprAccess().getLeftParenthesisKeyword_9_0());
		match_Type_INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getTypeAccess().getINTEGER_LITTerminalRuleCall_0_2_2_0()), new TokenAlias(false, false, grammarAccess.getTypeAccess().getREAL_LITTerminalRuleCall_0_2_2_1()));
		match_Type_INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getTypeAccess().getINTEGER_LITTerminalRuleCall_0_2_5_0()), new TokenAlias(false, false, grammarAccess.getTypeAccess().getREAL_LITTerminalRuleCall_0_2_5_1()));
		match_Type___LeftSquareBracketKeyword_0_2_0___INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1___CommaKeyword_0_2_3___INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1___RightSquareBracketKeyword_0_2_6__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getTypeAccess().getLeftSquareBracketKeyword_0_2_0()), new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getTypeAccess().getINTEGER_LITTerminalRuleCall_0_2_2_0()), new TokenAlias(false, false, grammarAccess.getTypeAccess().getREAL_LITTerminalRuleCall_0_2_2_1())), new TokenAlias(false, false, grammarAccess.getTypeAccess().getCommaKeyword_0_2_3()), new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getTypeAccess().getINTEGER_LITTerminalRuleCall_0_2_5_0()), new TokenAlias(false, false, grammarAccess.getTypeAccess().getREAL_LITTerminalRuleCall_0_2_5_1())), new TokenAlias(false, false, grammarAccess.getTypeAccess().getRightSquareBracketKeyword_0_2_6()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getINTEGER_LITRule())
			return getINTEGER_LITToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getREAL_LITRule())
			return getREAL_LITToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal INTEGER_LIT : 
	 * 		(DIGIT)+('_' (DIGIT)+)*
	 * 		(( '#' BASED_INTEGER  '#' ( INT_EXPONENT )? )
	 * 			| (INT_EXPONENT)?
	 * 		)
	 *   ;
	 */
	protected String getINTEGER_LITToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "##";
	}
	
	/**
	 * terminal REAL_LIT : 	
	 * 	(DIGIT)+('_' (DIGIT)+)* ( '.' (DIGIT)+('_' (DIGIT)+)* ( EXPONENT )?)
	 *   ;
	 */
	protected String getREAL_LITToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return ".";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_TermExpr_LeftParenthesisKeyword_9_0_a.equals(syntax))
				emit_TermExpr_LeftParenthesisKeyword_9_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_TermExpr_LeftParenthesisKeyword_9_0_p.equals(syntax))
				emit_TermExpr_LeftParenthesisKeyword_9_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Type_INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1.equals(syntax))
				emit_Type_INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Type_INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1.equals(syntax))
				emit_Type_INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Type___LeftSquareBracketKeyword_0_2_0___INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1___CommaKeyword_0_2_3___INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1___RightSquareBracketKeyword_0_2_6__q.equals(syntax))
				emit_Type___LeftSquareBracketKeyword_0_2_0___INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1___CommaKeyword_0_2_3___INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1___RightSquareBracketKeyword_0_2_6__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     '('*
	 */
	protected void emit_TermExpr_LeftParenthesisKeyword_9_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('+
	 */
	protected void emit_TermExpr_LeftParenthesisKeyword_9_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     INTEGER_LIT | REAL_LIT
	 */
	protected void emit_Type_INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     INTEGER_LIT | REAL_LIT
	 */
	protected void emit_Type_INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('[' (INTEGER_LIT | REAL_LIT) ',' (INTEGER_LIT | REAL_LIT) ']')?
	 */
	protected void emit_Type___LeftSquareBracketKeyword_0_2_0___INTEGER_LITTerminalRuleCall_0_2_2_0_or_REAL_LITTerminalRuleCall_0_2_2_1___CommaKeyword_0_2_3___INTEGER_LITTerminalRuleCall_0_2_5_0_or_REAL_LITTerminalRuleCall_0_2_5_1___RightSquareBracketKeyword_0_2_6__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
