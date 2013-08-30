package com.rockwellcollins.atc.resolute.serializer;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class ResoluteSyntacticSequencer extends AbstractSyntacticSequencer {

	protected ResoluteGrammarAccess grammarAccess;
	protected AbstractElementAlias match_AtomicExpr_LeftParenthesisKeyword_12_0_a;
	protected AbstractElementAlias match_AtomicExpr_LeftParenthesisKeyword_12_0_p;
	protected AbstractElementAlias match_DefinitionBody___AsteriskAsteriskKeyword_1_2_0_AsteriskAsteriskKeyword_1_2_2__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (ResoluteGrammarAccess) access;
		match_AtomicExpr_LeftParenthesisKeyword_12_0_a = new TokenAlias(true, true, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_12_0());
		match_AtomicExpr_LeftParenthesisKeyword_12_0_p = new TokenAlias(true, false, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_12_0());
		match_DefinitionBody___AsteriskAsteriskKeyword_1_2_0_AsteriskAsteriskKeyword_1_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_0()), new TokenAlias(false, false, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_2()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_AtomicExpr_LeftParenthesisKeyword_12_0_a.equals(syntax))
				emit_AtomicExpr_LeftParenthesisKeyword_12_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AtomicExpr_LeftParenthesisKeyword_12_0_p.equals(syntax))
				emit_AtomicExpr_LeftParenthesisKeyword_12_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_DefinitionBody___AsteriskAsteriskKeyword_1_2_0_AsteriskAsteriskKeyword_1_2_2__q.equals(syntax))
				emit_DefinitionBody___AsteriskAsteriskKeyword_1_2_0_AsteriskAsteriskKeyword_1_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     '('*
	 */
	protected void emit_AtomicExpr_LeftParenthesisKeyword_12_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('+
	 */
	protected void emit_AtomicExpr_LeftParenthesisKeyword_12_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('**' '**')?
	 */
	protected void emit_DefinitionBody___AsteriskAsteriskKeyword_1_2_0_AsteriskAsteriskKeyword_1_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
