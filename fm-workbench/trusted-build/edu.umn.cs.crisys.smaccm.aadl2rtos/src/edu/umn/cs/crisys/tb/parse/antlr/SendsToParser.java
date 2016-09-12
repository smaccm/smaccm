// Generated from SendsTo.g4 by ANTLR 4.1

    package edu.umn.cs.crisys.tb.parse.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SendsToParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, REAL=4, BOOL=5, INT=6, ID=7, WS=8, SL_COMMENT=9, 
		ML_COMMENT=10, ERROR=11;
	public static final String[] tokenNames = {
		"<INVALID>", "'{'", "','", "'}'", "REAL", "BOOL", "INT", "ID", "WS", "SL_COMMENT", 
		"ML_COMMENT", "ERROR"
	};
	public static final int
		RULE_program = 0, RULE_sends_to_tl = 1;
	public static final String[] ruleNames = {
		"program", "sends_to_tl"
	};

	@Override
	public String getGrammarFileName() { return "SendsTo.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public SendsToParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SendsToParser.EOF, 0); }
		public Sends_to_tlContext sends_to_tl(int i) {
			return getRuleContext(Sends_to_tlContext.class,i);
		}
		public List<Sends_to_tlContext> sends_to_tl() {
			return getRuleContexts(Sends_to_tlContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SendsToListener ) ((SendsToListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SendsToListener ) ((SendsToListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SendsToVisitor ) return ((SendsToVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			setState(19);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(4); match(1);
				setState(5); match(3);
				setState(6); match(EOF);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(7); match(1);
				setState(8); sends_to_tl();
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==2) {
					{
					{
					setState(9); match(2);
					setState(10); sends_to_tl();
					}
					}
					setState(15);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(16); match(3);
				setState(17); match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sends_to_tlContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(SendsToParser.INT); }
		public List<TerminalNode> ID() { return getTokens(SendsToParser.ID); }
		public TerminalNode INT(int i) {
			return getToken(SendsToParser.INT, i);
		}
		public TerminalNode ID(int i) {
			return getToken(SendsToParser.ID, i);
		}
		public Sends_to_tlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sends_to_tl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SendsToListener ) ((SendsToListener)listener).enterSends_to_tl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SendsToListener ) ((SendsToListener)listener).exitSends_to_tl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SendsToVisitor ) return ((SendsToVisitor<? extends T>)visitor).visitSends_to_tl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sends_to_tlContext sends_to_tl() throws RecognitionException {
		Sends_to_tlContext _localctx = new Sends_to_tlContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sends_to_tl);
		int _la;
		try {
			setState(35);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(21); match(1);
				setState(22); match(3);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23); match(1);
				setState(24); match(INT);
				setState(25); match(ID);
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==2) {
					{
					{
					setState(26); match(2);
					setState(27); match(INT);
					setState(28); match(ID);
					}
					}
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(34); match(3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\r(\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\3\2\3\2"+
		"\5\2\26\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3"+
		"\3\5\3&\n\3\3\3\2\4\2\4\2\2)\2\25\3\2\2\2\4%\3\2\2\2\6\7\7\3\2\2\7\b\7"+
		"\5\2\2\b\26\7\2\2\3\t\n\7\3\2\2\n\17\5\4\3\2\13\f\7\4\2\2\f\16\5\4\3\2"+
		"\r\13\3\2\2\2\16\21\3\2\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\22\3\2\2\2\21"+
		"\17\3\2\2\2\22\23\7\5\2\2\23\24\7\2\2\3\24\26\3\2\2\2\25\6\3\2\2\2\25"+
		"\t\3\2\2\2\26\3\3\2\2\2\27\30\7\3\2\2\30&\7\5\2\2\31\32\7\3\2\2\32\33"+
		"\7\b\2\2\33!\7\t\2\2\34\35\7\4\2\2\35\36\7\b\2\2\36 \7\t\2\2\37\34\3\2"+
		"\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$&\7\5\2\2%"+
		"\27\3\2\2\2%\31\3\2\2\2&\5\3\2\2\2\6\17\25!%";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}