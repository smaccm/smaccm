// Generated from SendsTo.g4 by ANTLR 4.1

    package edu.umn.cs.crisys.tb.parse.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SendsToLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, REAL=4, BOOL=5, INT=6, ID=7, WS=8, SL_COMMENT=9, 
		ML_COMMENT=10, ERROR=11;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'{'", "','", "'}'", "REAL", "BOOL", "INT", "ID", "WS", "SL_COMMENT", 
		"ML_COMMENT", "ERROR"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "REAL", "BOOL", "INT", "ID", "WS", "SL_COMMENT", 
		"ML_COMMENT", "ERROR"
	};


	public SendsToLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SendsTo.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 7: WS_action((RuleContext)_localctx, actionIndex); break;

		case 8: SL_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 9: ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void SL_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\rf\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6-\n\6\3\7\6\7\60\n\7\r\7\16\7\61\3\b\3\b\7\b\66"+
		"\n\b\f\b\16\b9\13\b\3\t\6\t<\n\t\r\t\16\t=\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\7\nG\n\n\f\n\16\nJ\13\n\3\n\5\nM\n\n\3\n\5\nP\n\n\3\n\5\nS\n\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\7\13[\n\13\f\13\16\13^\13\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\\\r\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\2"+
		"\23\13\3\25\f\4\27\r\1\3\2\b\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13"+
		"\f\16\17\"\"\5\2\f\f\17\17\'\'\4\2\f\f\17\17n\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\5\33\3\2\2"+
		"\2\7\35\3\2\2\2\t\37\3\2\2\2\13,\3\2\2\2\r/\3\2\2\2\17\63\3\2\2\2\21;"+
		"\3\2\2\2\23A\3\2\2\2\25V\3\2\2\2\27d\3\2\2\2\31\32\7}\2\2\32\4\3\2\2\2"+
		"\33\34\7.\2\2\34\6\3\2\2\2\35\36\7\177\2\2\36\b\3\2\2\2\37 \5\r\7\2 !"+
		"\7\60\2\2!\"\5\r\7\2\"\n\3\2\2\2#$\7v\2\2$%\7t\2\2%&\7w\2\2&-\7g\2\2\'"+
		"(\7h\2\2()\7c\2\2)*\7n\2\2*+\7u\2\2+-\7g\2\2,#\3\2\2\2,\'\3\2\2\2-\f\3"+
		"\2\2\2.\60\t\2\2\2/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62"+
		"\16\3\2\2\2\63\67\t\3\2\2\64\66\t\4\2\2\65\64\3\2\2\2\669\3\2\2\2\67\65"+
		"\3\2\2\2\678\3\2\2\28\20\3\2\2\29\67\3\2\2\2:<\t\5\2\2;:\3\2\2\2<=\3\2"+
		"\2\2=;\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\b\t\2\2@\22\3\2\2\2AB\7\61\2\2BC"+
		"\7\61\2\2CL\3\2\2\2DH\n\6\2\2EG\n\7\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2"+
		"HI\3\2\2\2IM\3\2\2\2JH\3\2\2\2KM\3\2\2\2LD\3\2\2\2LK\3\2\2\2MR\3\2\2\2"+
		"NP\7\17\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QS\7\f\2\2RO\3\2\2\2RS\3\2\2"+
		"\2ST\3\2\2\2TU\b\n\3\2U\24\3\2\2\2VW\7\61\2\2WX\7,\2\2X\\\3\2\2\2Y[\13"+
		"\2\2\2ZY\3\2\2\2[^\3\2\2\2\\]\3\2\2\2\\Z\3\2\2\2]_\3\2\2\2^\\\3\2\2\2"+
		"_`\7,\2\2`a\7\61\2\2ab\3\2\2\2bc\b\13\4\2c\26\3\2\2\2de\13\2\2\2e\30\3"+
		"\2\2\2\f\2,\61\67=HLOR\\";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}