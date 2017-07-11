package edu.uah.rsesc.aadlsimulator.xtext.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import edu.uah.rsesc.aadlsimulator.xtext.services.InputConstraintGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalInputConstraintParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_BIGDECIMAL", "RULE_TRUE", "RULE_FALSE", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'pre'", "'('", "')'", "'.'", "'rand_int()'", "'rand_real()'", "'rand()'", "'::'", "'-'", "'['", "','", "']'", "'{'", "'}'", "'+'", "'*'", "'/'"
    };
    public static final int RULE_BIGDECIMAL=5;
    public static final int RULE_STRING=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int RULE_TRUE=6;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int RULE_ID=4;
    public static final int RULE_WS=12;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_FALSE=7;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalInputConstraintParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalInputConstraintParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalInputConstraintParser.tokenNames; }
    public String getGrammarFileName() { return "InternalInputConstraint.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private InputConstraintGrammarAccess grammarAccess;

        public InternalInputConstraintParser(TokenStream input, InputConstraintGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "InputConstraint";
       	}

       	@Override
       	protected InputConstraintGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleInputConstraint"
    // InternalInputConstraint.g:71:1: entryRuleInputConstraint returns [EObject current=null] : iv_ruleInputConstraint= ruleInputConstraint EOF ;
    public final EObject entryRuleInputConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputConstraint = null;


        try {
            // InternalInputConstraint.g:71:56: (iv_ruleInputConstraint= ruleInputConstraint EOF )
            // InternalInputConstraint.g:72:2: iv_ruleInputConstraint= ruleInputConstraint EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInputConstraintRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInputConstraint=ruleInputConstraint();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInputConstraint; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInputConstraint"


    // $ANTLR start "ruleInputConstraint"
    // InternalInputConstraint.g:78:1: ruleInputConstraint returns [EObject current=null] : this_Expr_0= ruleExpr ;
    public final EObject ruleInputConstraint() throws RecognitionException {
        EObject current = null;

        EObject this_Expr_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:84:2: (this_Expr_0= ruleExpr )
            // InternalInputConstraint.g:85:2: this_Expr_0= ruleExpr
            {
            if ( state.backtracking==0 ) {

              		/* */
              	
            }
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getInputConstraintAccess().getExprParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_Expr_0=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_Expr_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInputConstraint"


    // $ANTLR start "entryRuleExpr"
    // InternalInputConstraint.g:99:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // InternalInputConstraint.g:99:45: (iv_ruleExpr= ruleExpr EOF )
            // InternalInputConstraint.g:100:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpr"


    // $ANTLR start "ruleExpr"
    // InternalInputConstraint.g:106:1: ruleExpr returns [EObject current=null] : (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet ) ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_Scalar_0 = null;

        EObject this_Interval_1 = null;

        EObject this_Set_2 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:112:2: ( (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet ) )
            // InternalInputConstraint.g:113:2: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )
            {
            // InternalInputConstraint.g:113:2: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // InternalInputConstraint.g:114:3: this_Scalar_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getExprAccess().getScalarParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Scalar_0=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Scalar_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:126:3: this_Interval_1= ruleInterval
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getExprAccess().getIntervalParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Interval_1=ruleInterval();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Interval_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalInputConstraint.g:138:3: this_Set_2= ruleSet
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getExprAccess().getSetParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Set_2=ruleSet();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Set_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpr"


    // $ANTLR start "entryRuleScalar"
    // InternalInputConstraint.g:153:1: entryRuleScalar returns [EObject current=null] : iv_ruleScalar= ruleScalar EOF ;
    public final EObject entryRuleScalar() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalar = null;


        try {
            // InternalInputConstraint.g:153:47: (iv_ruleScalar= ruleScalar EOF )
            // InternalInputConstraint.g:154:2: iv_ruleScalar= ruleScalar EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScalarRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScalar=ruleScalar();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScalar; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScalar"


    // $ANTLR start "ruleScalar"
    // InternalInputConstraint.g:160:1: ruleScalar returns [EObject current=null] : this_AddSub_0= ruleAddSub ;
    public final EObject ruleScalar() throws RecognitionException {
        EObject current = null;

        EObject this_AddSub_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:166:2: (this_AddSub_0= ruleAddSub )
            // InternalInputConstraint.g:167:2: this_AddSub_0= ruleAddSub
            {
            if ( state.backtracking==0 ) {

              		/* */
              	
            }
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getScalarAccess().getAddSubParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_AddSub_0=ruleAddSub();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_AddSub_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScalar"


    // $ANTLR start "entryRuleAddSub"
    // InternalInputConstraint.g:181:1: entryRuleAddSub returns [EObject current=null] : iv_ruleAddSub= ruleAddSub EOF ;
    public final EObject entryRuleAddSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddSub = null;


        try {
            // InternalInputConstraint.g:181:47: (iv_ruleAddSub= ruleAddSub EOF )
            // InternalInputConstraint.g:182:2: iv_ruleAddSub= ruleAddSub EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAddSubRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAddSub=ruleAddSub();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAddSub; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAddSub"


    // $ANTLR start "ruleAddSub"
    // InternalInputConstraint.g:188:1: ruleAddSub returns [EObject current=null] : (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* ) ;
    public final EObject ruleAddSub() throws RecognitionException {
        EObject current = null;

        EObject this_MultDiv_0 = null;

        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:194:2: ( (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* ) )
            // InternalInputConstraint.g:195:2: (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* )
            {
            // InternalInputConstraint.g:195:2: (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* )
            // InternalInputConstraint.g:196:3: this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )*
            {
            if ( state.backtracking==0 ) {

              			/* */
              		
            }
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAddSubAccess().getMultDivParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_3);
            this_MultDiv_0=ruleMultDiv();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_MultDiv_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalInputConstraint.g:207:3: ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==28) ) {
                    int LA2_2 = input.LA(2);

                    if ( (synpred3_InternalInputConstraint()) ) {
                        alt2=1;
                    }


                }
                else if ( (LA2_0==22) ) {
                    int LA2_3 = input.LA(2);

                    if ( (synpred3_InternalInputConstraint()) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // InternalInputConstraint.g:208:4: () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) )
            	    {
            	    // InternalInputConstraint.g:208:4: ()
            	    // InternalInputConstraint.g:209:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					/* */
            	      				
            	    }
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getAddSubAccess().getBinaryExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalInputConstraint.g:218:4: ( (lv_op_2_0= ruleAddSubOperator ) )
            	    // InternalInputConstraint.g:219:5: (lv_op_2_0= ruleAddSubOperator )
            	    {
            	    // InternalInputConstraint.g:219:5: (lv_op_2_0= ruleAddSubOperator )
            	    // InternalInputConstraint.g:220:6: lv_op_2_0= ruleAddSubOperator
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAddSubAccess().getOpAddSubOperatorEnumRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_4);
            	    lv_op_2_0=ruleAddSubOperator();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getAddSubRule());
            	      						}
            	      						set(
            	      							current,
            	      							"op",
            	      							lv_op_2_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.AddSubOperator");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalInputConstraint.g:237:4: ( (lv_right_3_0= ruleMultDiv ) )
            	    // InternalInputConstraint.g:238:5: (lv_right_3_0= ruleMultDiv )
            	    {
            	    // InternalInputConstraint.g:238:5: (lv_right_3_0= ruleMultDiv )
            	    // InternalInputConstraint.g:239:6: lv_right_3_0= ruleMultDiv
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAddSubAccess().getRightMultDivParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_3);
            	    lv_right_3_0=ruleMultDiv();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getAddSubRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.MultDiv");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddSub"


    // $ANTLR start "entryRuleMultDiv"
    // InternalInputConstraint.g:261:1: entryRuleMultDiv returns [EObject current=null] : iv_ruleMultDiv= ruleMultDiv EOF ;
    public final EObject entryRuleMultDiv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultDiv = null;


        try {
            // InternalInputConstraint.g:261:48: (iv_ruleMultDiv= ruleMultDiv EOF )
            // InternalInputConstraint.g:262:2: iv_ruleMultDiv= ruleMultDiv EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultDivRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMultDiv=ruleMultDiv();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultDiv; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultDiv"


    // $ANTLR start "ruleMultDiv"
    // InternalInputConstraint.g:268:1: ruleMultDiv returns [EObject current=null] : (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* ) ;
    public final EObject ruleMultDiv() throws RecognitionException {
        EObject current = null;

        EObject this_Function_0 = null;

        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:274:2: ( (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* ) )
            // InternalInputConstraint.g:275:2: (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* )
            {
            // InternalInputConstraint.g:275:2: (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* )
            // InternalInputConstraint.g:276:3: this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )*
            {
            if ( state.backtracking==0 ) {

              			/* */
              		
            }
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getMultDivAccess().getFunctionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_5);
            this_Function_0=ruleFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Function_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalInputConstraint.g:287:3: ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==29) ) {
                    int LA3_2 = input.LA(2);

                    if ( (synpred4_InternalInputConstraint()) ) {
                        alt3=1;
                    }


                }
                else if ( (LA3_0==30) ) {
                    int LA3_3 = input.LA(2);

                    if ( (synpred4_InternalInputConstraint()) ) {
                        alt3=1;
                    }


                }


                switch (alt3) {
            	case 1 :
            	    // InternalInputConstraint.g:288:4: () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) )
            	    {
            	    // InternalInputConstraint.g:288:4: ()
            	    // InternalInputConstraint.g:289:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					/* */
            	      				
            	    }
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getMultDivAccess().getBinaryExpressionLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalInputConstraint.g:298:4: ( (lv_op_2_0= ruleMultDivOperator ) )
            	    // InternalInputConstraint.g:299:5: (lv_op_2_0= ruleMultDivOperator )
            	    {
            	    // InternalInputConstraint.g:299:5: (lv_op_2_0= ruleMultDivOperator )
            	    // InternalInputConstraint.g:300:6: lv_op_2_0= ruleMultDivOperator
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getMultDivAccess().getOpMultDivOperatorEnumRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_4);
            	    lv_op_2_0=ruleMultDivOperator();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getMultDivRule());
            	      						}
            	      						set(
            	      							current,
            	      							"op",
            	      							lv_op_2_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.MultDivOperator");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalInputConstraint.g:317:4: ( (lv_right_3_0= ruleFunction ) )
            	    // InternalInputConstraint.g:318:5: (lv_right_3_0= ruleFunction )
            	    {
            	    // InternalInputConstraint.g:318:5: (lv_right_3_0= ruleFunction )
            	    // InternalInputConstraint.g:319:6: lv_right_3_0= ruleFunction
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getMultDivAccess().getRightFunctionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_5);
            	    lv_right_3_0=ruleFunction();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getMultDivRule());
            	      						}
            	      						set(
            	      							current,
            	      							"right",
            	      							lv_right_3_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Function");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultDiv"


    // $ANTLR start "entryRuleFunction"
    // InternalInputConstraint.g:341:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // InternalInputConstraint.g:341:49: (iv_ruleFunction= ruleFunction EOF )
            // InternalInputConstraint.g:342:2: iv_ruleFunction= ruleFunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFunction=ruleFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunction; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFunction"


    // $ANTLR start "ruleFunction"
    // InternalInputConstraint.g:348:1: ruleFunction returns [EObject current=null] : (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary ) ;
    public final EObject ruleFunction() throws RecognitionException {
        EObject current = null;

        EObject this_Pre_0 = null;

        EObject this_Random_1 = null;

        EObject this_Ref_2 = null;

        EObject this_ConstRef_3 = null;

        EObject this_Negative_4 = null;

        EObject this_Primary_5 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:354:2: ( (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary ) )
            // InternalInputConstraint.g:355:2: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )
            {
            // InternalInputConstraint.g:355:2: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )
            int alt4=6;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // InternalInputConstraint.g:356:3: this_Pre_0= rulePre
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getPreParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Pre_0=rulePre();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Pre_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:368:3: this_Random_1= ruleRandom
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getRandomParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Random_1=ruleRandom();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Random_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalInputConstraint.g:380:3: this_Ref_2= ruleRef
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getRefParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Ref_2=ruleRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Ref_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalInputConstraint.g:392:3: this_ConstRef_3= ruleConstRef
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getConstRefParserRuleCall_3());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_ConstRef_3=ruleConstRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ConstRef_3;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalInputConstraint.g:404:3: this_Negative_4= ruleNegative
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getNegativeParserRuleCall_4());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Negative_4=ruleNegative();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Negative_4;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalInputConstraint.g:416:3: this_Primary_5= rulePrimary
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFunctionAccess().getPrimaryParserRuleCall_5());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_Primary_5=rulePrimary();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Primary_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFunction"


    // $ANTLR start "entryRulePre"
    // InternalInputConstraint.g:431:1: entryRulePre returns [EObject current=null] : iv_rulePre= rulePre EOF ;
    public final EObject entryRulePre() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePre = null;


        try {
            // InternalInputConstraint.g:431:44: (iv_rulePre= rulePre EOF )
            // InternalInputConstraint.g:432:2: iv_rulePre= rulePre EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPreRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePre=rulePre();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePre; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePre"


    // $ANTLR start "rulePre"
    // InternalInputConstraint.g:438:1: rulePre returns [EObject current=null] : ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleElementRef ) )? otherlv_4= ')' ) ;
    public final EObject rulePre() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ref_3_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:444:2: ( ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleElementRef ) )? otherlv_4= ')' ) )
            // InternalInputConstraint.g:445:2: ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleElementRef ) )? otherlv_4= ')' )
            {
            // InternalInputConstraint.g:445:2: ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleElementRef ) )? otherlv_4= ')' )
            // InternalInputConstraint.g:446:3: () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleElementRef ) )? otherlv_4= ')'
            {
            // InternalInputConstraint.g:446:3: ()
            // InternalInputConstraint.g:447:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPreAccess().getPreExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,14,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPreAccess().getPreKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,15,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getPreAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalInputConstraint.g:464:3: ( (lv_ref_3_0= ruleElementRef ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalInputConstraint.g:465:4: (lv_ref_3_0= ruleElementRef )
                    {
                    // InternalInputConstraint.g:465:4: (lv_ref_3_0= ruleElementRef )
                    // InternalInputConstraint.g:466:5: lv_ref_3_0= ruleElementRef
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getPreAccess().getRefElementRefParserRuleCall_3_0());
                      				
                    }
                    pushFollow(FOLLOW_8);
                    lv_ref_3_0=ruleElementRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getPreRule());
                      					}
                      					set(
                      						current,
                      						"ref",
                      						lv_ref_3_0,
                      						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.ElementRef");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getPreAccess().getRightParenthesisKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePre"


    // $ANTLR start "entryRuleRandom"
    // InternalInputConstraint.g:491:1: entryRuleRandom returns [EObject current=null] : iv_ruleRandom= ruleRandom EOF ;
    public final EObject entryRuleRandom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandom = null;


        try {
            // InternalInputConstraint.g:491:47: (iv_ruleRandom= ruleRandom EOF )
            // InternalInputConstraint.g:492:2: iv_ruleRandom= ruleRandom EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRandom=ruleRandom();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandom; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRandom"


    // $ANTLR start "ruleRandom"
    // InternalInputConstraint.g:498:1: ruleRandom returns [EObject current=null] : (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement ) ;
    public final EObject ruleRandom() throws RecognitionException {
        EObject current = null;

        EObject this_RandomInteger_0 = null;

        EObject this_RandomReal_1 = null;

        EObject this_RandomElement_2 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:504:2: ( (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement ) )
            // InternalInputConstraint.g:505:2: (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement )
            {
            // InternalInputConstraint.g:505:2: (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 23:
                {
                int LA6_1 = input.LA(2);

                if ( (synpred11_InternalInputConstraint()) ) {
                    alt6=1;
                }
                else if ( (synpred12_InternalInputConstraint()) ) {
                    alt6=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
                }
                break;
            case 15:
                {
                int LA6_2 = input.LA(2);

                if ( (synpred11_InternalInputConstraint()) ) {
                    alt6=1;
                }
                else if ( (synpred12_InternalInputConstraint()) ) {
                    alt6=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 2, input);

                    throw nvae;
                }
                }
                break;
            case 26:
                {
                alt6=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalInputConstraint.g:506:3: this_RandomInteger_0= ruleRandomInteger
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getRandomAccess().getRandomIntegerParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_RandomInteger_0=ruleRandomInteger();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_RandomInteger_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:518:3: this_RandomReal_1= ruleRandomReal
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getRandomAccess().getRandomRealParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_RandomReal_1=ruleRandomReal();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_RandomReal_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalInputConstraint.g:530:3: this_RandomElement_2= ruleRandomElement
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getRandomAccess().getRandomElementParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_RandomElement_2=ruleRandomElement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_RandomElement_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRandom"


    // $ANTLR start "entryRuleRandomInteger"
    // InternalInputConstraint.g:545:1: entryRuleRandomInteger returns [EObject current=null] : iv_ruleRandomInteger= ruleRandomInteger EOF ;
    public final EObject entryRuleRandomInteger() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomInteger = null;


        try {
            // InternalInputConstraint.g:545:54: (iv_ruleRandomInteger= ruleRandomInteger EOF )
            // InternalInputConstraint.g:546:2: iv_ruleRandomInteger= ruleRandomInteger EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomIntegerRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRandomInteger=ruleRandomInteger();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomInteger; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRandomInteger"


    // $ANTLR start "ruleRandomInteger"
    // InternalInputConstraint.g:552:1: ruleRandomInteger returns [EObject current=null] : ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' ) ;
    public final EObject ruleRandomInteger() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_interval_1_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:558:2: ( ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' ) )
            // InternalInputConstraint.g:559:2: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' )
            {
            // InternalInputConstraint.g:559:2: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' )
            // InternalInputConstraint.g:560:3: () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()'
            {
            // InternalInputConstraint.g:560:3: ()
            // InternalInputConstraint.g:561:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getRandomIntegerAccess().getRandomIntegerExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:570:3: ( (lv_interval_1_0= ruleInterval ) )
            // InternalInputConstraint.g:571:4: (lv_interval_1_0= ruleInterval )
            {
            // InternalInputConstraint.g:571:4: (lv_interval_1_0= ruleInterval )
            // InternalInputConstraint.g:572:5: lv_interval_1_0= ruleInterval
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRandomIntegerAccess().getIntervalIntervalParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_9);
            lv_interval_1_0=ruleInterval();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getRandomIntegerRule());
              					}
              					set(
              						current,
              						"interval",
              						lv_interval_1_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Interval");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getRandomIntegerAccess().getFullStopKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,18,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getRandomIntegerAccess().getRand_intKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRandomInteger"


    // $ANTLR start "entryRuleRandomReal"
    // InternalInputConstraint.g:601:1: entryRuleRandomReal returns [EObject current=null] : iv_ruleRandomReal= ruleRandomReal EOF ;
    public final EObject entryRuleRandomReal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomReal = null;


        try {
            // InternalInputConstraint.g:601:51: (iv_ruleRandomReal= ruleRandomReal EOF )
            // InternalInputConstraint.g:602:2: iv_ruleRandomReal= ruleRandomReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomRealRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRandomReal=ruleRandomReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomReal; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRandomReal"


    // $ANTLR start "ruleRandomReal"
    // InternalInputConstraint.g:608:1: ruleRandomReal returns [EObject current=null] : ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' ) ;
    public final EObject ruleRandomReal() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_interval_1_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:614:2: ( ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' ) )
            // InternalInputConstraint.g:615:2: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' )
            {
            // InternalInputConstraint.g:615:2: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' )
            // InternalInputConstraint.g:616:3: () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()'
            {
            // InternalInputConstraint.g:616:3: ()
            // InternalInputConstraint.g:617:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getRandomRealAccess().getRandomRealExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:626:3: ( (lv_interval_1_0= ruleInterval ) )
            // InternalInputConstraint.g:627:4: (lv_interval_1_0= ruleInterval )
            {
            // InternalInputConstraint.g:627:4: (lv_interval_1_0= ruleInterval )
            // InternalInputConstraint.g:628:5: lv_interval_1_0= ruleInterval
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRandomRealAccess().getIntervalIntervalParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_9);
            lv_interval_1_0=ruleInterval();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getRandomRealRule());
              					}
              					set(
              						current,
              						"interval",
              						lv_interval_1_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Interval");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_11); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getRandomRealAccess().getFullStopKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,19,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getRandomRealAccess().getRand_realKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRandomReal"


    // $ANTLR start "entryRuleRandomElement"
    // InternalInputConstraint.g:657:1: entryRuleRandomElement returns [EObject current=null] : iv_ruleRandomElement= ruleRandomElement EOF ;
    public final EObject entryRuleRandomElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomElement = null;


        try {
            // InternalInputConstraint.g:657:54: (iv_ruleRandomElement= ruleRandomElement EOF )
            // InternalInputConstraint.g:658:2: iv_ruleRandomElement= ruleRandomElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomElementRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRandomElement=ruleRandomElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomElement; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRandomElement"


    // $ANTLR start "ruleRandomElement"
    // InternalInputConstraint.g:664:1: ruleRandomElement returns [EObject current=null] : ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' ) ;
    public final EObject ruleRandomElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:670:2: ( ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' ) )
            // InternalInputConstraint.g:671:2: ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' )
            {
            // InternalInputConstraint.g:671:2: ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' )
            // InternalInputConstraint.g:672:3: () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()'
            {
            // InternalInputConstraint.g:672:3: ()
            // InternalInputConstraint.g:673:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getRandomElementAccess().getRandomElementExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:682:3: ( (lv_set_1_0= ruleSet ) )
            // InternalInputConstraint.g:683:4: (lv_set_1_0= ruleSet )
            {
            // InternalInputConstraint.g:683:4: (lv_set_1_0= ruleSet )
            // InternalInputConstraint.g:684:5: lv_set_1_0= ruleSet
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getRandomElementAccess().getSetSetParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_9);
            lv_set_1_0=ruleSet();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getRandomElementRule());
              					}
              					set(
              						current,
              						"set",
              						lv_set_1_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Set");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getRandomElementAccess().getFullStopKeyword_2());
              		
            }
            otherlv_3=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getRandomElementAccess().getRandKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRandomElement"


    // $ANTLR start "entryRuleRef"
    // InternalInputConstraint.g:713:1: entryRuleRef returns [EObject current=null] : iv_ruleRef= ruleRef EOF ;
    public final EObject entryRuleRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRef = null;


        try {
            // InternalInputConstraint.g:713:44: (iv_ruleRef= ruleRef EOF )
            // InternalInputConstraint.g:714:2: iv_ruleRef= ruleRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRef=ruleRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRef"


    // $ANTLR start "ruleRef"
    // InternalInputConstraint.g:720:1: ruleRef returns [EObject current=null] : (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef ) ;
    public final EObject ruleRef() throws RecognitionException {
        EObject current = null;

        EObject this_ElementRef_0 = null;

        EObject this_ConstRef_1 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:726:2: ( (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef ) )
            // InternalInputConstraint.g:727:2: (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef )
            {
            // InternalInputConstraint.g:727:2: (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==EOF||(LA7_1>=16 && LA7_1<=17)||LA7_1==22||(LA7_1>=24 && LA7_1<=25)||(LA7_1>=27 && LA7_1<=30)) ) {
                    alt7=1;
                }
                else if ( (LA7_1==21) ) {
                    alt7=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalInputConstraint.g:728:3: this_ElementRef_0= ruleElementRef
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getRefAccess().getElementRefParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_ElementRef_0=ruleElementRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ElementRef_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:740:3: this_ConstRef_1= ruleConstRef
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getRefAccess().getConstRefParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_ConstRef_1=ruleConstRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ConstRef_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRef"


    // $ANTLR start "entryRuleElementRef"
    // InternalInputConstraint.g:755:1: entryRuleElementRef returns [EObject current=null] : iv_ruleElementRef= ruleElementRef EOF ;
    public final EObject entryRuleElementRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementRef = null;


        try {
            // InternalInputConstraint.g:755:51: (iv_ruleElementRef= ruleElementRef EOF )
            // InternalInputConstraint.g:756:2: iv_ruleElementRef= ruleElementRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElementRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleElementRef=ruleElementRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElementRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElementRef"


    // $ANTLR start "ruleElementRef"
    // InternalInputConstraint.g:762:1: ruleElementRef returns [EObject current=null] : ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* ) ;
    public final EObject ruleElementRef() throws RecognitionException {
        EObject current = null;

        Token lv_ids_1_0=null;
        Token otherlv_2=null;
        Token lv_ids_3_0=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:768:2: ( ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* ) )
            // InternalInputConstraint.g:769:2: ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* )
            {
            // InternalInputConstraint.g:769:2: ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* )
            // InternalInputConstraint.g:770:3: () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )*
            {
            // InternalInputConstraint.g:770:3: ()
            // InternalInputConstraint.g:771:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getElementRefAccess().getElementRefExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:780:3: ( (lv_ids_1_0= RULE_ID ) )
            // InternalInputConstraint.g:781:4: (lv_ids_1_0= RULE_ID )
            {
            // InternalInputConstraint.g:781:4: (lv_ids_1_0= RULE_ID )
            // InternalInputConstraint.g:782:5: lv_ids_1_0= RULE_ID
            {
            lv_ids_1_0=(Token)match(input,RULE_ID,FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_ids_1_0, grammarAccess.getElementRefAccess().getIdsIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getElementRefRule());
              					}
              					addWithLastConsumed(
              						current,
              						"ids",
              						lv_ids_1_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.ID");
              				
            }

            }


            }

            // InternalInputConstraint.g:798:3: (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalInputConstraint.g:799:4: otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_14); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getElementRefAccess().getFullStopKeyword_2_0());
            	      			
            	    }
            	    // InternalInputConstraint.g:803:4: ( (lv_ids_3_0= RULE_ID ) )
            	    // InternalInputConstraint.g:804:5: (lv_ids_3_0= RULE_ID )
            	    {
            	    // InternalInputConstraint.g:804:5: (lv_ids_3_0= RULE_ID )
            	    // InternalInputConstraint.g:805:6: lv_ids_3_0= RULE_ID
            	    {
            	    lv_ids_3_0=(Token)match(input,RULE_ID,FOLLOW_13); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_ids_3_0, grammarAccess.getElementRefAccess().getIdsIDTerminalRuleCall_2_1_0());
            	      					
            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getElementRefRule());
            	      						}
            	      						addWithLastConsumed(
            	      							current,
            	      							"ids",
            	      							lv_ids_3_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.ID");
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElementRef"


    // $ANTLR start "entryRuleConstRef"
    // InternalInputConstraint.g:826:1: entryRuleConstRef returns [EObject current=null] : iv_ruleConstRef= ruleConstRef EOF ;
    public final EObject entryRuleConstRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstRef = null;


        try {
            // InternalInputConstraint.g:826:49: (iv_ruleConstRef= ruleConstRef EOF )
            // InternalInputConstraint.g:827:2: iv_ruleConstRef= ruleConstRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConstRef=ruleConstRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstRef; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstRef"


    // $ANTLR start "ruleConstRef"
    // InternalInputConstraint.g:833:1: ruleConstRef returns [EObject current=null] : ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) ) ;
    public final EObject ruleConstRef() throws RecognitionException {
        EObject current = null;

        Token lv_packageSegments_1_0=null;
        Token otherlv_2=null;
        Token lv_constantName_3_0=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:839:2: ( ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) ) )
            // InternalInputConstraint.g:840:2: ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) )
            {
            // InternalInputConstraint.g:840:2: ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) )
            // InternalInputConstraint.g:841:3: () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) )
            {
            // InternalInputConstraint.g:841:3: ()
            // InternalInputConstraint.g:842:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getConstRefAccess().getConstRefExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:851:3: ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1==21) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // InternalInputConstraint.g:852:4: ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::'
            	    {
            	    // InternalInputConstraint.g:852:4: ( (lv_packageSegments_1_0= RULE_ID ) )
            	    // InternalInputConstraint.g:853:5: (lv_packageSegments_1_0= RULE_ID )
            	    {
            	    // InternalInputConstraint.g:853:5: (lv_packageSegments_1_0= RULE_ID )
            	    // InternalInputConstraint.g:854:6: lv_packageSegments_1_0= RULE_ID
            	    {
            	    lv_packageSegments_1_0=(Token)match(input,RULE_ID,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_packageSegments_1_0, grammarAccess.getConstRefAccess().getPackageSegmentsIDTerminalRuleCall_1_0_0());
            	      					
            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getConstRefRule());
            	      						}
            	      						addWithLastConsumed(
            	      							current,
            	      							"packageSegments",
            	      							lv_packageSegments_1_0,
            	      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.ID");
            	      					
            	    }

            	    }


            	    }

            	    otherlv_2=(Token)match(input,21,FOLLOW_14); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getConstRefAccess().getColonColonKeyword_1_1());
            	      			
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            // InternalInputConstraint.g:875:3: ( (lv_constantName_3_0= RULE_ID ) )
            // InternalInputConstraint.g:876:4: (lv_constantName_3_0= RULE_ID )
            {
            // InternalInputConstraint.g:876:4: (lv_constantName_3_0= RULE_ID )
            // InternalInputConstraint.g:877:5: lv_constantName_3_0= RULE_ID
            {
            lv_constantName_3_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_constantName_3_0, grammarAccess.getConstRefAccess().getConstantNameIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getConstRefRule());
              					}
              					setWithLastConsumed(
              						current,
              						"constantName",
              						lv_constantName_3_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.ID");
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstRef"


    // $ANTLR start "entryRuleNegative"
    // InternalInputConstraint.g:897:1: entryRuleNegative returns [EObject current=null] : iv_ruleNegative= ruleNegative EOF ;
    public final EObject entryRuleNegative() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNegative = null;


        try {
            // InternalInputConstraint.g:897:49: (iv_ruleNegative= ruleNegative EOF )
            // InternalInputConstraint.g:898:2: iv_ruleNegative= ruleNegative EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNegativeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNegative=ruleNegative();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNegative; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegative"


    // $ANTLR start "ruleNegative"
    // InternalInputConstraint.g:904:1: ruleNegative returns [EObject current=null] : ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) ) ;
    public final EObject ruleNegative() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:910:2: ( ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) ) )
            // InternalInputConstraint.g:911:2: ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) )
            {
            // InternalInputConstraint.g:911:2: ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) )
            // InternalInputConstraint.g:912:3: () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) )
            {
            // InternalInputConstraint.g:912:3: ()
            // InternalInputConstraint.g:913:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getNegativeAccess().getNegativeExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,22,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getNegativeAccess().getHyphenMinusKeyword_1());
              		
            }
            // InternalInputConstraint.g:926:3: ( (lv_value_2_0= ruleScalar ) )
            // InternalInputConstraint.g:927:4: (lv_value_2_0= ruleScalar )
            {
            // InternalInputConstraint.g:927:4: (lv_value_2_0= ruleScalar )
            // InternalInputConstraint.g:928:5: lv_value_2_0= ruleScalar
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getNegativeAccess().getValueScalarParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleScalar();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getNegativeRule());
              					}
              					set(
              						current,
              						"value",
              						lv_value_2_0,
              						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Scalar");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegative"


    // $ANTLR start "entryRulePrimary"
    // InternalInputConstraint.g:949:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalInputConstraint.g:949:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalInputConstraint.g:950:2: iv_rulePrimary= rulePrimary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimary=rulePrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimary; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalInputConstraint.g:956:1: rulePrimary returns [EObject current=null] : (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject this_IntegerLiteral_0 = null;

        EObject this_RealLiteral_1 = null;

        EObject this_BooleanLiteral_2 = null;

        EObject this_Scalar_4 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:962:2: ( (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) ) )
            // InternalInputConstraint.g:963:2: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) )
            {
            // InternalInputConstraint.g:963:2: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) )
            int alt10=4;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt10=1;
                }
                break;
            case RULE_BIGDECIMAL:
                {
                alt10=2;
                }
                break;
            case RULE_TRUE:
            case RULE_FALSE:
                {
                alt10=3;
                }
                break;
            case 15:
                {
                alt10=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalInputConstraint.g:964:3: this_IntegerLiteral_0= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getPrimaryAccess().getIntegerLiteralParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_IntegerLiteral_0=ruleIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_IntegerLiteral_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:976:3: this_RealLiteral_1= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getPrimaryAccess().getRealLiteralParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_RealLiteral_1=ruleRealLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_RealLiteral_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalInputConstraint.g:988:3: this_BooleanLiteral_2= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getPrimaryAccess().getBooleanLiteralParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_BooleanLiteral_2=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_BooleanLiteral_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalInputConstraint.g:1000:3: (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' )
                    {
                    // InternalInputConstraint.g:1000:3: (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' )
                    // InternalInputConstraint.g:1001:4: otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_3_0());
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				/* */
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getPrimaryAccess().getScalarParserRuleCall_3_1());
                      			
                    }
                    pushFollow(FOLLOW_8);
                    this_Scalar_4=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_Scalar_4;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    otherlv_5=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_3_2());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleInterval"
    // InternalInputConstraint.g:1025:1: entryRuleInterval returns [EObject current=null] : iv_ruleInterval= ruleInterval EOF ;
    public final EObject entryRuleInterval() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterval = null;


        try {
            // InternalInputConstraint.g:1025:49: (iv_ruleInterval= ruleInterval EOF )
            // InternalInputConstraint.g:1026:2: iv_ruleInterval= ruleInterval EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntervalRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterval=ruleInterval();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterval; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterval"


    // $ANTLR start "ruleInterval"
    // InternalInputConstraint.g:1032:1: ruleInterval returns [EObject current=null] : ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) ) ;
    public final EObject ruleInterval() throws RecognitionException {
        EObject current = null;

        Token lv_leftClosed_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_rightClosed_6_0=null;
        Token otherlv_7=null;
        EObject lv_left_3_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:1038:2: ( ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) ) )
            // InternalInputConstraint.g:1039:2: ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) )
            {
            // InternalInputConstraint.g:1039:2: ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) )
            // InternalInputConstraint.g:1040:3: () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' )
            {
            // InternalInputConstraint.g:1040:3: ()
            // InternalInputConstraint.g:1041:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getIntervalAccess().getIntervalExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalInputConstraint.g:1050:3: ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            else if ( (LA11_0==15) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalInputConstraint.g:1051:4: ( (lv_leftClosed_1_0= '[' ) )
                    {
                    // InternalInputConstraint.g:1051:4: ( (lv_leftClosed_1_0= '[' ) )
                    // InternalInputConstraint.g:1052:5: (lv_leftClosed_1_0= '[' )
                    {
                    // InternalInputConstraint.g:1052:5: (lv_leftClosed_1_0= '[' )
                    // InternalInputConstraint.g:1053:6: lv_leftClosed_1_0= '['
                    {
                    lv_leftClosed_1_0=(Token)match(input,23,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_leftClosed_1_0, grammarAccess.getIntervalAccess().getLeftClosedLeftSquareBracketKeyword_1_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIntervalRule());
                      						}
                      						setWithLastConsumed(current, "leftClosed", true, "[");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:1066:4: otherlv_2= '('
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getIntervalAccess().getLeftParenthesisKeyword_1_1());
                      			
                    }

                    }
                    break;

            }

            // InternalInputConstraint.g:1071:3: ( (lv_left_3_0= ruleScalar ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_INT)||(LA12_0>=14 && LA12_0<=15)||(LA12_0>=22 && LA12_0<=23)||LA12_0==26) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalInputConstraint.g:1072:4: (lv_left_3_0= ruleScalar )
                    {
                    // InternalInputConstraint.g:1072:4: (lv_left_3_0= ruleScalar )
                    // InternalInputConstraint.g:1073:5: lv_left_3_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getIntervalAccess().getLeftScalarParserRuleCall_2_0());
                      				
                    }
                    pushFollow(FOLLOW_17);
                    lv_left_3_0=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getIntervalRule());
                      					}
                      					set(
                      						current,
                      						"left",
                      						lv_left_3_0,
                      						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Scalar");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,24,FOLLOW_18); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getIntervalAccess().getCommaKeyword_3());
              		
            }
            // InternalInputConstraint.g:1094:3: ( (lv_right_5_0= ruleScalar ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=RULE_ID && LA13_0<=RULE_INT)||(LA13_0>=14 && LA13_0<=15)||(LA13_0>=22 && LA13_0<=23)||LA13_0==26) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalInputConstraint.g:1095:4: (lv_right_5_0= ruleScalar )
                    {
                    // InternalInputConstraint.g:1095:4: (lv_right_5_0= ruleScalar )
                    // InternalInputConstraint.g:1096:5: lv_right_5_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getIntervalAccess().getRightScalarParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_19);
                    lv_right_5_0=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getIntervalRule());
                      					}
                      					set(
                      						current,
                      						"right",
                      						lv_right_5_0,
                      						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Scalar");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalInputConstraint.g:1113:3: ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            else if ( (LA14_0==16) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // InternalInputConstraint.g:1114:4: ( (lv_rightClosed_6_0= ']' ) )
                    {
                    // InternalInputConstraint.g:1114:4: ( (lv_rightClosed_6_0= ']' ) )
                    // InternalInputConstraint.g:1115:5: (lv_rightClosed_6_0= ']' )
                    {
                    // InternalInputConstraint.g:1115:5: (lv_rightClosed_6_0= ']' )
                    // InternalInputConstraint.g:1116:6: lv_rightClosed_6_0= ']'
                    {
                    lv_rightClosed_6_0=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_rightClosed_6_0, grammarAccess.getIntervalAccess().getRightClosedRightSquareBracketKeyword_5_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIntervalRule());
                      						}
                      						setWithLastConsumed(current, "rightClosed", true, "]");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:1129:4: otherlv_7= ')'
                    {
                    otherlv_7=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getIntervalAccess().getRightParenthesisKeyword_5_1());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInterval"


    // $ANTLR start "entryRuleSet"
    // InternalInputConstraint.g:1138:1: entryRuleSet returns [EObject current=null] : iv_ruleSet= ruleSet EOF ;
    public final EObject entryRuleSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSet = null;


        try {
            // InternalInputConstraint.g:1138:44: (iv_ruleSet= ruleSet EOF )
            // InternalInputConstraint.g:1139:2: iv_ruleSet= ruleSet EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSetRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSet=ruleSet();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSet; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSet"


    // $ANTLR start "ruleSet"
    // InternalInputConstraint.g:1145:1: ruleSet returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_members_2_0 = null;

        EObject lv_members_4_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:1151:2: ( ( () otherlv_1= '{' ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )? otherlv_5= '}' ) )
            // InternalInputConstraint.g:1152:2: ( () otherlv_1= '{' ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )? otherlv_5= '}' )
            {
            // InternalInputConstraint.g:1152:2: ( () otherlv_1= '{' ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )? otherlv_5= '}' )
            // InternalInputConstraint.g:1153:3: () otherlv_1= '{' ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )? otherlv_5= '}'
            {
            // InternalInputConstraint.g:1153:3: ()
            // InternalInputConstraint.g:1154:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getSetAccess().getSetExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,26,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getSetAccess().getLeftCurlyBracketKeyword_1());
              		
            }
            // InternalInputConstraint.g:1167:3: ( ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_INT)||(LA16_0>=14 && LA16_0<=15)||(LA16_0>=22 && LA16_0<=23)||LA16_0==26) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalInputConstraint.g:1168:4: ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )*
                    {
                    // InternalInputConstraint.g:1168:4: ( (lv_members_2_0= ruleScalar ) )
                    // InternalInputConstraint.g:1169:5: (lv_members_2_0= ruleScalar )
                    {
                    // InternalInputConstraint.g:1169:5: (lv_members_2_0= ruleScalar )
                    // InternalInputConstraint.g:1170:6: lv_members_2_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getSetAccess().getMembersScalarParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_21);
                    lv_members_2_0=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getSetRule());
                      						}
                      						add(
                      							current,
                      							"members",
                      							lv_members_2_0,
                      							"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Scalar");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalInputConstraint.g:1187:4: (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==24) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalInputConstraint.g:1188:5: otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) )
                    	    {
                    	    otherlv_3=(Token)match(input,24,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getSetAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalInputConstraint.g:1192:5: ( (lv_members_4_0= ruleScalar ) )
                    	    // InternalInputConstraint.g:1193:6: (lv_members_4_0= ruleScalar )
                    	    {
                    	    // InternalInputConstraint.g:1193:6: (lv_members_4_0= ruleScalar )
                    	    // InternalInputConstraint.g:1194:7: lv_members_4_0= ruleScalar
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getSetAccess().getMembersScalarParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_21);
                    	    lv_members_4_0=ruleScalar();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getSetRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"members",
                    	      								lv_members_4_0,
                    	      								"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.Scalar");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,27,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getSetAccess().getRightCurlyBracketKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSet"


    // $ANTLR start "entryRuleIntegerLiteral"
    // InternalInputConstraint.g:1221:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // InternalInputConstraint.g:1221:55: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // InternalInputConstraint.g:1222:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntegerLiteral"


    // $ANTLR start "ruleIntegerLiteral"
    // InternalInputConstraint.g:1228:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_value_0_0= ruleBIG_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalInputConstraint.g:1234:2: ( ( (lv_value_0_0= ruleBIG_INT ) ) )
            // InternalInputConstraint.g:1235:2: ( (lv_value_0_0= ruleBIG_INT ) )
            {
            // InternalInputConstraint.g:1235:2: ( (lv_value_0_0= ruleBIG_INT ) )
            // InternalInputConstraint.g:1236:3: (lv_value_0_0= ruleBIG_INT )
            {
            // InternalInputConstraint.g:1236:3: (lv_value_0_0= ruleBIG_INT )
            // InternalInputConstraint.g:1237:4: lv_value_0_0= ruleBIG_INT
            {
            if ( state.backtracking==0 ) {

              				newCompositeNode(grammarAccess.getIntegerLiteralAccess().getValueBIG_INTParserRuleCall_0());
              			
            }
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleBIG_INT();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElementForParent(grammarAccess.getIntegerLiteralRule());
              				}
              				set(
              					current,
              					"value",
              					lv_value_0_0,
              					"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.BIG_INT");
              				afterParserOrEnumRuleCall();
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntegerLiteral"


    // $ANTLR start "entryRuleRealLiteral"
    // InternalInputConstraint.g:1257:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // InternalInputConstraint.g:1257:52: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // InternalInputConstraint.g:1258:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRealLiteral"


    // $ANTLR start "ruleRealLiteral"
    // InternalInputConstraint.g:1264:1: ruleRealLiteral returns [EObject current=null] : ( (lv_value_0_0= RULE_BIGDECIMAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:1270:2: ( ( (lv_value_0_0= RULE_BIGDECIMAL ) ) )
            // InternalInputConstraint.g:1271:2: ( (lv_value_0_0= RULE_BIGDECIMAL ) )
            {
            // InternalInputConstraint.g:1271:2: ( (lv_value_0_0= RULE_BIGDECIMAL ) )
            // InternalInputConstraint.g:1272:3: (lv_value_0_0= RULE_BIGDECIMAL )
            {
            // InternalInputConstraint.g:1272:3: (lv_value_0_0= RULE_BIGDECIMAL )
            // InternalInputConstraint.g:1273:4: lv_value_0_0= RULE_BIGDECIMAL
            {
            lv_value_0_0=(Token)match(input,RULE_BIGDECIMAL,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_value_0_0, grammarAccess.getRealLiteralAccess().getValueBIGDECIMALTerminalRuleCall_0());
              			
            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getRealLiteralRule());
              				}
              				setWithLastConsumed(
              					current,
              					"value",
              					lv_value_0_0,
              					"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.BIGDECIMAL");
              			
            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRealLiteral"


    // $ANTLR start "entryRuleBooleanLiteral"
    // InternalInputConstraint.g:1292:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // InternalInputConstraint.g:1292:55: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // InternalInputConstraint.g:1293:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBooleanLiteral"


    // $ANTLR start "ruleBooleanLiteral"
    // InternalInputConstraint.g:1299:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;
        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:1305:2: ( ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) ) )
            // InternalInputConstraint.g:1306:2: ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) )
            {
            // InternalInputConstraint.g:1306:2: ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_TRUE) ) {
                alt17=1;
            }
            else if ( (LA17_0==RULE_FALSE) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // InternalInputConstraint.g:1307:3: ( (lv_value_0_0= RULE_TRUE ) )
                    {
                    // InternalInputConstraint.g:1307:3: ( (lv_value_0_0= RULE_TRUE ) )
                    // InternalInputConstraint.g:1308:4: (lv_value_0_0= RULE_TRUE )
                    {
                    // InternalInputConstraint.g:1308:4: (lv_value_0_0= RULE_TRUE )
                    // InternalInputConstraint.g:1309:5: lv_value_0_0= RULE_TRUE
                    {
                    lv_value_0_0=(Token)match(input,RULE_TRUE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_value_0_0, grammarAccess.getBooleanLiteralAccess().getValueTRUETerminalRuleCall_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      					}
                      					setWithLastConsumed(
                      						current,
                      						"value",
                      						lv_value_0_0,
                      						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.TRUE");
                      				
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:1326:3: ( (lv_value_1_0= RULE_FALSE ) )
                    {
                    // InternalInputConstraint.g:1326:3: ( (lv_value_1_0= RULE_FALSE ) )
                    // InternalInputConstraint.g:1327:4: (lv_value_1_0= RULE_FALSE )
                    {
                    // InternalInputConstraint.g:1327:4: (lv_value_1_0= RULE_FALSE )
                    // InternalInputConstraint.g:1328:5: lv_value_1_0= RULE_FALSE
                    {
                    lv_value_1_0=(Token)match(input,RULE_FALSE,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_value_1_0, grammarAccess.getBooleanLiteralAccess().getValueFALSETerminalRuleCall_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      					}
                      					setWithLastConsumed(
                      						current,
                      						"value",
                      						lv_value_1_0,
                      						"edu.uah.rsesc.aadlsimulator.xtext.InputConstraint.FALSE");
                      				
                    }

                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBooleanLiteral"


    // $ANTLR start "entryRuleBIG_INT"
    // InternalInputConstraint.g:1348:1: entryRuleBIG_INT returns [String current=null] : iv_ruleBIG_INT= ruleBIG_INT EOF ;
    public final String entryRuleBIG_INT() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBIG_INT = null;


        try {
            // InternalInputConstraint.g:1348:47: (iv_ruleBIG_INT= ruleBIG_INT EOF )
            // InternalInputConstraint.g:1349:2: iv_ruleBIG_INT= ruleBIG_INT EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBIG_INTRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBIG_INT=ruleBIG_INT();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBIG_INT.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBIG_INT"


    // $ANTLR start "ruleBIG_INT"
    // InternalInputConstraint.g:1355:1: ruleBIG_INT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleBIG_INT() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:1361:2: (this_INT_0= RULE_INT )
            // InternalInputConstraint.g:1362:2: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INT_0);
              	
            }
            if ( state.backtracking==0 ) {

              		newLeafNode(this_INT_0, grammarAccess.getBIG_INTAccess().getINTTerminalRuleCall());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBIG_INT"


    // $ANTLR start "ruleAddSubOperator"
    // InternalInputConstraint.g:1372:1: ruleAddSubOperator returns [Enumerator current=null] : ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) ;
    public final Enumerator ruleAddSubOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:1378:2: ( ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) )
            // InternalInputConstraint.g:1379:2: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            {
            // InternalInputConstraint.g:1379:2: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==28) ) {
                alt18=1;
            }
            else if ( (LA18_0==22) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalInputConstraint.g:1380:3: (enumLiteral_0= '+' )
                    {
                    // InternalInputConstraint.g:1380:3: (enumLiteral_0= '+' )
                    // InternalInputConstraint.g:1381:4: enumLiteral_0= '+'
                    {
                    enumLiteral_0=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAddSubOperatorAccess().getADDITIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getAddSubOperatorAccess().getADDITIONEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:1388:3: (enumLiteral_1= '-' )
                    {
                    // InternalInputConstraint.g:1388:3: (enumLiteral_1= '-' )
                    // InternalInputConstraint.g:1389:4: enumLiteral_1= '-'
                    {
                    enumLiteral_1=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getAddSubOperatorAccess().getSUBTRACTIONEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getAddSubOperatorAccess().getSUBTRACTIONEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddSubOperator"


    // $ANTLR start "ruleMultDivOperator"
    // InternalInputConstraint.g:1399:1: ruleMultDivOperator returns [Enumerator current=null] : ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) ) ;
    public final Enumerator ruleMultDivOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalInputConstraint.g:1405:2: ( ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) ) )
            // InternalInputConstraint.g:1406:2: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) )
            {
            // InternalInputConstraint.g:1406:2: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            else if ( (LA19_0==30) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalInputConstraint.g:1407:3: (enumLiteral_0= '*' )
                    {
                    // InternalInputConstraint.g:1407:3: (enumLiteral_0= '*' )
                    // InternalInputConstraint.g:1408:4: enumLiteral_0= '*'
                    {
                    enumLiteral_0=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getMultDivOperatorAccess().getMULTIPLICATIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getMultDivOperatorAccess().getMULTIPLICATIONEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalInputConstraint.g:1415:3: (enumLiteral_1= '/' )
                    {
                    // InternalInputConstraint.g:1415:3: (enumLiteral_1= '/' )
                    // InternalInputConstraint.g:1416:4: enumLiteral_1= '/'
                    {
                    enumLiteral_1=(Token)match(input,30,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getMultDivOperatorAccess().getDIVISIONEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getMultDivOperatorAccess().getDIVISIONEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultDivOperator"

    // $ANTLR start synpred1_InternalInputConstraint
    public final void synpred1_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Scalar_0 = null;


        // InternalInputConstraint.g:114:3: (this_Scalar_0= ruleScalar )
        // InternalInputConstraint.g:114:3: this_Scalar_0= ruleScalar
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_Scalar_0=ruleScalar();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalInputConstraint

    // $ANTLR start synpred2_InternalInputConstraint
    public final void synpred2_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Interval_1 = null;


        // InternalInputConstraint.g:126:3: (this_Interval_1= ruleInterval )
        // InternalInputConstraint.g:126:3: this_Interval_1= ruleInterval
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_Interval_1=ruleInterval();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalInputConstraint

    // $ANTLR start synpred3_InternalInputConstraint
    public final void synpred3_InternalInputConstraint_fragment() throws RecognitionException {   
        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


        // InternalInputConstraint.g:208:4: ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )
        // InternalInputConstraint.g:208:4: () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) )
        {
        // InternalInputConstraint.g:208:4: ()
        // InternalInputConstraint.g:209:5: 
        {
        if ( state.backtracking==0 ) {

          					/* */
          				
        }

        }

        // InternalInputConstraint.g:218:4: ( (lv_op_2_0= ruleAddSubOperator ) )
        // InternalInputConstraint.g:219:5: (lv_op_2_0= ruleAddSubOperator )
        {
        // InternalInputConstraint.g:219:5: (lv_op_2_0= ruleAddSubOperator )
        // InternalInputConstraint.g:220:6: lv_op_2_0= ruleAddSubOperator
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getAddSubAccess().getOpAddSubOperatorEnumRuleCall_1_1_0());
          					
        }
        pushFollow(FOLLOW_4);
        lv_op_2_0=ruleAddSubOperator();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalInputConstraint.g:237:4: ( (lv_right_3_0= ruleMultDiv ) )
        // InternalInputConstraint.g:238:5: (lv_right_3_0= ruleMultDiv )
        {
        // InternalInputConstraint.g:238:5: (lv_right_3_0= ruleMultDiv )
        // InternalInputConstraint.g:239:6: lv_right_3_0= ruleMultDiv
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getAddSubAccess().getRightMultDivParserRuleCall_1_2_0());
          					
        }
        pushFollow(FOLLOW_2);
        lv_right_3_0=ruleMultDiv();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred3_InternalInputConstraint

    // $ANTLR start synpred4_InternalInputConstraint
    public final void synpred4_InternalInputConstraint_fragment() throws RecognitionException {   
        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


        // InternalInputConstraint.g:288:4: ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )
        // InternalInputConstraint.g:288:4: () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) )
        {
        // InternalInputConstraint.g:288:4: ()
        // InternalInputConstraint.g:289:5: 
        {
        if ( state.backtracking==0 ) {

          					/* */
          				
        }

        }

        // InternalInputConstraint.g:298:4: ( (lv_op_2_0= ruleMultDivOperator ) )
        // InternalInputConstraint.g:299:5: (lv_op_2_0= ruleMultDivOperator )
        {
        // InternalInputConstraint.g:299:5: (lv_op_2_0= ruleMultDivOperator )
        // InternalInputConstraint.g:300:6: lv_op_2_0= ruleMultDivOperator
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getMultDivAccess().getOpMultDivOperatorEnumRuleCall_1_1_0());
          					
        }
        pushFollow(FOLLOW_4);
        lv_op_2_0=ruleMultDivOperator();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalInputConstraint.g:317:4: ( (lv_right_3_0= ruleFunction ) )
        // InternalInputConstraint.g:318:5: (lv_right_3_0= ruleFunction )
        {
        // InternalInputConstraint.g:318:5: (lv_right_3_0= ruleFunction )
        // InternalInputConstraint.g:319:6: lv_right_3_0= ruleFunction
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getMultDivAccess().getRightFunctionParserRuleCall_1_2_0());
          					
        }
        pushFollow(FOLLOW_2);
        lv_right_3_0=ruleFunction();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred4_InternalInputConstraint

    // $ANTLR start synpred6_InternalInputConstraint
    public final void synpred6_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Random_1 = null;


        // InternalInputConstraint.g:368:3: (this_Random_1= ruleRandom )
        // InternalInputConstraint.g:368:3: this_Random_1= ruleRandom
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_Random_1=ruleRandom();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_InternalInputConstraint

    // $ANTLR start synpred7_InternalInputConstraint
    public final void synpred7_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Ref_2 = null;


        // InternalInputConstraint.g:380:3: (this_Ref_2= ruleRef )
        // InternalInputConstraint.g:380:3: this_Ref_2= ruleRef
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_Ref_2=ruleRef();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_InternalInputConstraint

    // $ANTLR start synpred8_InternalInputConstraint
    public final void synpred8_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_ConstRef_3 = null;


        // InternalInputConstraint.g:392:3: (this_ConstRef_3= ruleConstRef )
        // InternalInputConstraint.g:392:3: this_ConstRef_3= ruleConstRef
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_ConstRef_3=ruleConstRef();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_InternalInputConstraint

    // $ANTLR start synpred11_InternalInputConstraint
    public final void synpred11_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_RandomInteger_0 = null;


        // InternalInputConstraint.g:506:3: (this_RandomInteger_0= ruleRandomInteger )
        // InternalInputConstraint.g:506:3: this_RandomInteger_0= ruleRandomInteger
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_RandomInteger_0=ruleRandomInteger();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_InternalInputConstraint

    // $ANTLR start synpred12_InternalInputConstraint
    public final void synpred12_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_RandomReal_1 = null;


        // InternalInputConstraint.g:518:3: (this_RandomReal_1= ruleRandomReal )
        // InternalInputConstraint.g:518:3: this_RandomReal_1= ruleRandomReal
        {
        if ( state.backtracking==0 ) {

          			/* */
          		
        }
        pushFollow(FOLLOW_2);
        this_RandomReal_1=ruleRandomReal();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_InternalInputConstraint

    // Delegated rules

    public final boolean synpred4_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalInputConstraint_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA4 dfa4 = new DFA4(this);
    static final String dfa_1s = "\15\uffff";
    static final String dfa_2s = "\1\4\1\uffff\3\0\10\uffff";
    static final String dfa_3s = "\1\32\1\uffff\3\0\10\uffff";
    static final String dfa_4s = "\1\uffff\1\1\11\uffff\1\2\1\3";
    static final String dfa_5s = "\2\uffff\1\0\1\1\1\2\10\uffff}>";
    static final String[] dfa_6s = {
            "\5\1\5\uffff\1\1\1\3\6\uffff\1\1\1\2\2\uffff\1\4",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "113:2: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA1_2 = input.LA(1);

                         
                        int index1_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalInputConstraint()) ) {s = 1;}

                        else if ( (synpred2_InternalInputConstraint()) ) {s = 11;}

                         
                        input.seek(index1_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA1_3 = input.LA(1);

                         
                        int index1_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalInputConstraint()) ) {s = 1;}

                        else if ( (synpred2_InternalInputConstraint()) ) {s = 11;}

                         
                        input.seek(index1_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA1_4 = input.LA(1);

                         
                        int index1_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalInputConstraint()) ) {s = 1;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index1_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 1, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\1\4\2\uffff\1\0\1\uffff\1\0\7\uffff";
    static final String dfa_8s = "\1\32\2\uffff\1\0\1\uffff\1\0\7\uffff";
    static final String dfa_9s = "\1\uffff\1\1\1\2\3\uffff\1\5\1\6\3\uffff\1\3\1\4";
    static final String dfa_10s = "\3\uffff\1\0\1\uffff\1\1\7\uffff}>";
    static final String[] dfa_11s = {
            "\1\5\4\7\5\uffff\1\1\1\3\6\uffff\1\6\1\2\2\uffff\1\2",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    static final char[] dfa_7 = DFA.unpackEncodedStringToUnsignedChars(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[][] dfa_11 = unpackEncodedStringArray(dfa_11s);

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_7;
            this.max = dfa_8;
            this.accept = dfa_9;
            this.special = dfa_10;
            this.transition = dfa_11;
        }
        public String getDescription() {
            return "355:2: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_3 = input.LA(1);

                         
                        int index4_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_InternalInputConstraint()) ) {s = 2;}

                        else if ( (true) ) {s = 7;}

                         
                        input.seek(index4_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA4_5 = input.LA(1);

                         
                        int index4_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalInputConstraint()) ) {s = 11;}

                        else if ( (synpred8_InternalInputConstraint()) ) {s = 12;}

                         
                        input.seek(index4_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000010400002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000005C0C1F0L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000006C1C1F0L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002010000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000000000CC0C1F0L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000009000000L});

}