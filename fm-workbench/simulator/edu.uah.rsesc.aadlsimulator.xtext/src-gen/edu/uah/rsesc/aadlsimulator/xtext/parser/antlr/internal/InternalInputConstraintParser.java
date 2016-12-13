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
    public String getGrammarFileName() { return "../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g"; }



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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:74:1: entryRuleInputConstraint returns [EObject current=null] : iv_ruleInputConstraint= ruleInputConstraint EOF ;
    public final EObject entryRuleInputConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputConstraint = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:75:2: (iv_ruleInputConstraint= ruleInputConstraint EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:76:2: iv_ruleInputConstraint= ruleInputConstraint EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInputConstraintRule()); 
            }
            pushFollow(FOLLOW_ruleInputConstraint_in_entryRuleInputConstraint81);
            iv_ruleInputConstraint=ruleInputConstraint();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInputConstraint; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInputConstraint91); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:83:1: ruleInputConstraint returns [EObject current=null] : this_Expr_0= ruleExpr ;
    public final EObject ruleInputConstraint() throws RecognitionException {
        EObject current = null;

        EObject this_Expr_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:86:28: (this_Expr_0= ruleExpr )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:88:2: this_Expr_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInputConstraintAccess().getExprParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleInputConstraint140);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:107:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:108:2: (iv_ruleExpr= ruleExpr EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:109:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_ruleExpr_in_entryRuleExpr174);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpr184); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:116:1: ruleExpr returns [EObject current=null] : (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet ) ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_Scalar_0 = null;

        EObject this_Interval_1 = null;

        EObject this_Set_2 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:119:28: ( (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:120:1: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:120:1: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:121:2: this_Scalar_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExprAccess().getScalarParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleScalar_in_ruleExpr234);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:134:2: this_Interval_1= ruleInterval
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExprAccess().getIntervalParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterval_in_ruleExpr264);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:147:2: this_Set_2= ruleSet
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExprAccess().getSetParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSet_in_ruleExpr294);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:166:1: entryRuleScalar returns [EObject current=null] : iv_ruleScalar= ruleScalar EOF ;
    public final EObject entryRuleScalar() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScalar = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:167:2: (iv_ruleScalar= ruleScalar EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:168:2: iv_ruleScalar= ruleScalar EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScalarRule()); 
            }
            pushFollow(FOLLOW_ruleScalar_in_entryRuleScalar329);
            iv_ruleScalar=ruleScalar();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScalar; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScalar339); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:175:1: ruleScalar returns [EObject current=null] : this_AddSub_0= ruleAddSub ;
    public final EObject ruleScalar() throws RecognitionException {
        EObject current = null;

        EObject this_AddSub_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:178:28: (this_AddSub_0= ruleAddSub )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:180:2: this_AddSub_0= ruleAddSub
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getScalarAccess().getAddSubParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleAddSub_in_ruleScalar388);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:199:1: entryRuleAddSub returns [EObject current=null] : iv_ruleAddSub= ruleAddSub EOF ;
    public final EObject entryRuleAddSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddSub = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:200:2: (iv_ruleAddSub= ruleAddSub EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:201:2: iv_ruleAddSub= ruleAddSub EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAddSubRule()); 
            }
            pushFollow(FOLLOW_ruleAddSub_in_entryRuleAddSub422);
            iv_ruleAddSub=ruleAddSub();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAddSub; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAddSub432); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:208:1: ruleAddSub returns [EObject current=null] : (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* ) ;
    public final EObject ruleAddSub() throws RecognitionException {
        EObject current = null;

        EObject this_MultDiv_0 = null;

        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:211:28: ( (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:212:1: (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:212:1: (this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )* )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:213:2: this_MultDiv_0= ruleMultDiv ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAddSubAccess().getMultDivParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultDiv_in_ruleAddSub482);
            this_MultDiv_0=ruleMultDiv();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultDiv_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:1: ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )*
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
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:2: () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:2: ()
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:225:2: 
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

            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:233:2: ( (lv_op_2_0= ruleAddSubOperator ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:234:1: (lv_op_2_0= ruleAddSubOperator )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:234:1: (lv_op_2_0= ruleAddSubOperator )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:235:3: lv_op_2_0= ruleAddSubOperator
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAddSubAccess().getOpAddSubOperatorEnumRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAddSubOperator_in_ruleAddSub515);
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
            	              		"AddSubOperator");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:251:2: ( (lv_right_3_0= ruleMultDiv ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:252:1: (lv_right_3_0= ruleMultDiv )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:252:1: (lv_right_3_0= ruleMultDiv )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:253:3: lv_right_3_0= ruleMultDiv
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAddSubAccess().getRightMultDivParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultDiv_in_ruleAddSub536);
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
            	              		"MultDiv");
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:277:1: entryRuleMultDiv returns [EObject current=null] : iv_ruleMultDiv= ruleMultDiv EOF ;
    public final EObject entryRuleMultDiv() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultDiv = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:278:2: (iv_ruleMultDiv= ruleMultDiv EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:279:2: iv_ruleMultDiv= ruleMultDiv EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultDivRule()); 
            }
            pushFollow(FOLLOW_ruleMultDiv_in_entryRuleMultDiv574);
            iv_ruleMultDiv=ruleMultDiv();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultDiv; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultDiv584); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:286:1: ruleMultDiv returns [EObject current=null] : (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* ) ;
    public final EObject ruleMultDiv() throws RecognitionException {
        EObject current = null;

        EObject this_Function_0 = null;

        Enumerator lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:289:28: ( (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:290:1: (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:290:1: (this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )* )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:291:2: this_Function_0= ruleFunction ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultDivAccess().getFunctionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleFunction_in_ruleMultDiv634);
            this_Function_0=ruleFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Function_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:1: ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )*
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
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:2: () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:2: ()
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:303:2: 
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

            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:311:2: ( (lv_op_2_0= ruleMultDivOperator ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:312:1: (lv_op_2_0= ruleMultDivOperator )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:312:1: (lv_op_2_0= ruleMultDivOperator )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:313:3: lv_op_2_0= ruleMultDivOperator
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultDivAccess().getOpMultDivOperatorEnumRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultDivOperator_in_ruleMultDiv667);
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
            	              		"MultDivOperator");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:329:2: ( (lv_right_3_0= ruleFunction ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:330:1: (lv_right_3_0= ruleFunction )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:330:1: (lv_right_3_0= ruleFunction )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:331:3: lv_right_3_0= ruleFunction
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultDivAccess().getRightFunctionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleFunction_in_ruleMultDiv688);
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
            	              		"Function");
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:355:1: entryRuleFunction returns [EObject current=null] : iv_ruleFunction= ruleFunction EOF ;
    public final EObject entryRuleFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunction = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:356:2: (iv_ruleFunction= ruleFunction EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:357:2: iv_ruleFunction= ruleFunction EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionRule()); 
            }
            pushFollow(FOLLOW_ruleFunction_in_entryRuleFunction726);
            iv_ruleFunction=ruleFunction();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunction; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunction736); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:364:1: ruleFunction returns [EObject current=null] : (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary ) ;
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
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:367:28: ( (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:368:1: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:368:1: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )
            int alt4=6;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:369:2: this_Pre_0= rulePre
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getPreParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_rulePre_in_ruleFunction786);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:382:2: this_Random_1= ruleRandom
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getRandomParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRandom_in_ruleFunction816);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:395:2: this_Ref_2= ruleRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getRefParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRef_in_ruleFunction846);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:408:2: this_ConstRef_3= ruleConstRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getConstRefParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstRef_in_ruleFunction876);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:421:2: this_Negative_4= ruleNegative
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getNegativeParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNegative_in_ruleFunction906);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:434:2: this_Primary_5= rulePrimary
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFunctionAccess().getPrimaryParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_rulePrimary_in_ruleFunction936);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:453:1: entryRulePre returns [EObject current=null] : iv_rulePre= rulePre EOF ;
    public final EObject entryRulePre() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePre = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:454:2: (iv_rulePre= rulePre EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:455:2: iv_rulePre= rulePre EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPreRule()); 
            }
            pushFollow(FOLLOW_rulePre_in_entryRulePre971);
            iv_rulePre=rulePre();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePre; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePre981); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:462:1: rulePre returns [EObject current=null] : ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleRef ) ) otherlv_4= ')' ) ;
    public final EObject rulePre() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ref_3_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:465:28: ( ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleRef ) ) otherlv_4= ')' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:466:1: ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleRef ) ) otherlv_4= ')' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:466:1: ( () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleRef ) ) otherlv_4= ')' )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:466:2: () otherlv_1= 'pre' otherlv_2= '(' ( (lv_ref_3_0= ruleRef ) ) otherlv_4= ')'
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:466:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:467:2: 
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

            otherlv_1=(Token)match(input,14,FOLLOW_14_in_rulePre1030); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPreAccess().getPreKeyword_1());
                  
            }
            otherlv_2=(Token)match(input,15,FOLLOW_15_in_rulePre1042); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getPreAccess().getLeftParenthesisKeyword_2());
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:483:1: ( (lv_ref_3_0= ruleRef ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:484:1: (lv_ref_3_0= ruleRef )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:484:1: (lv_ref_3_0= ruleRef )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:485:3: lv_ref_3_0= ruleRef
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPreAccess().getRefRefParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleRef_in_rulePre1063);
            lv_ref_3_0=ruleRef();

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
                      		"Ref");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,16,FOLLOW_16_in_rulePre1075); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:513:1: entryRuleRandom returns [EObject current=null] : iv_ruleRandom= ruleRandom EOF ;
    public final EObject entryRuleRandom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandom = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:514:2: (iv_ruleRandom= ruleRandom EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:515:2: iv_ruleRandom= ruleRandom EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomRule()); 
            }
            pushFollow(FOLLOW_ruleRandom_in_entryRuleRandom1111);
            iv_ruleRandom=ruleRandom();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandom; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRandom1121); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:522:1: ruleRandom returns [EObject current=null] : (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement ) ;
    public final EObject ruleRandom() throws RecognitionException {
        EObject current = null;

        EObject this_RandomInteger_0 = null;

        EObject this_RandomReal_1 = null;

        EObject this_RandomElement_2 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:525:28: ( (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:526:1: (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:526:1: (this_RandomInteger_0= ruleRandomInteger | this_RandomReal_1= ruleRandomReal | this_RandomElement_2= ruleRandomElement )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 23:
                {
                int LA5_1 = input.LA(2);

                if ( (synpred10_InternalInputConstraint()) ) {
                    alt5=1;
                }
                else if ( (synpred11_InternalInputConstraint()) ) {
                    alt5=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case 15:
                {
                int LA5_2 = input.LA(2);

                if ( (synpred10_InternalInputConstraint()) ) {
                    alt5=1;
                }
                else if ( (synpred11_InternalInputConstraint()) ) {
                    alt5=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
                }
                break;
            case 26:
                {
                alt5=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:527:2: this_RandomInteger_0= ruleRandomInteger
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRandomAccess().getRandomIntegerParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRandomInteger_in_ruleRandom1171);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:540:2: this_RandomReal_1= ruleRandomReal
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRandomAccess().getRandomRealParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRandomReal_in_ruleRandom1201);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:553:2: this_RandomElement_2= ruleRandomElement
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRandomAccess().getRandomElementParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRandomElement_in_ruleRandom1231);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:572:1: entryRuleRandomInteger returns [EObject current=null] : iv_ruleRandomInteger= ruleRandomInteger EOF ;
    public final EObject entryRuleRandomInteger() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomInteger = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:573:2: (iv_ruleRandomInteger= ruleRandomInteger EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:574:2: iv_ruleRandomInteger= ruleRandomInteger EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomIntegerRule()); 
            }
            pushFollow(FOLLOW_ruleRandomInteger_in_entryRuleRandomInteger1266);
            iv_ruleRandomInteger=ruleRandomInteger();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomInteger; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRandomInteger1276); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:581:1: ruleRandomInteger returns [EObject current=null] : ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' ) ;
    public final EObject ruleRandomInteger() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_interval_1_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:584:28: ( ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:585:1: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:585:1: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()' )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:585:2: () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_int()'
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:585:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:586:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:594:2: ( (lv_interval_1_0= ruleInterval ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:595:1: (lv_interval_1_0= ruleInterval )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:595:1: (lv_interval_1_0= ruleInterval )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:596:3: lv_interval_1_0= ruleInterval
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRandomIntegerAccess().getIntervalIntervalParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInterval_in_ruleRandomInteger1334);
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
                      		"Interval");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleRandomInteger1346); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRandomIntegerAccess().getFullStopKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,18,FOLLOW_18_in_ruleRandomInteger1358); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:628:1: entryRuleRandomReal returns [EObject current=null] : iv_ruleRandomReal= ruleRandomReal EOF ;
    public final EObject entryRuleRandomReal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomReal = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:629:2: (iv_ruleRandomReal= ruleRandomReal EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:630:2: iv_ruleRandomReal= ruleRandomReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomRealRule()); 
            }
            pushFollow(FOLLOW_ruleRandomReal_in_entryRuleRandomReal1394);
            iv_ruleRandomReal=ruleRandomReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomReal; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRandomReal1404); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:637:1: ruleRandomReal returns [EObject current=null] : ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' ) ;
    public final EObject ruleRandomReal() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_interval_1_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:640:28: ( ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:641:1: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:641:1: ( () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()' )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:641:2: () ( (lv_interval_1_0= ruleInterval ) ) otherlv_2= '.' otherlv_3= 'rand_real()'
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:641:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:642:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:650:2: ( (lv_interval_1_0= ruleInterval ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:651:1: (lv_interval_1_0= ruleInterval )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:651:1: (lv_interval_1_0= ruleInterval )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:652:3: lv_interval_1_0= ruleInterval
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRandomRealAccess().getIntervalIntervalParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInterval_in_ruleRandomReal1462);
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
                      		"Interval");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleRandomReal1474); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRandomRealAccess().getFullStopKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleRandomReal1486); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:684:1: entryRuleRandomElement returns [EObject current=null] : iv_ruleRandomElement= ruleRandomElement EOF ;
    public final EObject entryRuleRandomElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandomElement = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:685:2: (iv_ruleRandomElement= ruleRandomElement EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:686:2: iv_ruleRandomElement= ruleRandomElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRandomElementRule()); 
            }
            pushFollow(FOLLOW_ruleRandomElement_in_entryRuleRandomElement1522);
            iv_ruleRandomElement=ruleRandomElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRandomElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRandomElement1532); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:693:1: ruleRandomElement returns [EObject current=null] : ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' ) ;
    public final EObject ruleRandomElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_set_1_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:696:28: ( ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:697:1: ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:697:1: ( () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()' )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:697:2: () ( (lv_set_1_0= ruleSet ) ) otherlv_2= '.' otherlv_3= 'rand()'
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:697:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:698:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:706:2: ( (lv_set_1_0= ruleSet ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:707:1: (lv_set_1_0= ruleSet )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:707:1: (lv_set_1_0= ruleSet )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:708:3: lv_set_1_0= ruleSet
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRandomElementAccess().getSetSetParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSet_in_ruleRandomElement1590);
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
                      		"Set");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleRandomElement1602); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRandomElementAccess().getFullStopKeyword_2());
                  
            }
            otherlv_3=(Token)match(input,20,FOLLOW_20_in_ruleRandomElement1614); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:740:1: entryRuleRef returns [EObject current=null] : iv_ruleRef= ruleRef EOF ;
    public final EObject entryRuleRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRef = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:741:2: (iv_ruleRef= ruleRef EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:742:2: iv_ruleRef= ruleRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRefRule()); 
            }
            pushFollow(FOLLOW_ruleRef_in_entryRuleRef1650);
            iv_ruleRef=ruleRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRef; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRef1660); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:749:1: ruleRef returns [EObject current=null] : (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef ) ;
    public final EObject ruleRef() throws RecognitionException {
        EObject current = null;

        EObject this_ElementRef_0 = null;

        EObject this_ConstRef_1 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:752:28: ( (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:753:1: (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:753:1: (this_ElementRef_0= ruleElementRef | this_ConstRef_1= ruleConstRef )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==EOF||(LA6_1>=16 && LA6_1<=17)||LA6_1==22||(LA6_1>=24 && LA6_1<=25)||(LA6_1>=27 && LA6_1<=30)) ) {
                    alt6=1;
                }
                else if ( (LA6_1==21) ) {
                    alt6=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:754:2: this_ElementRef_0= ruleElementRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRefAccess().getElementRefParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleElementRef_in_ruleRef1710);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:767:2: this_ConstRef_1= ruleConstRef
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getRefAccess().getConstRefParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstRef_in_ruleRef1740);
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:786:1: entryRuleElementRef returns [EObject current=null] : iv_ruleElementRef= ruleElementRef EOF ;
    public final EObject entryRuleElementRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementRef = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:787:2: (iv_ruleElementRef= ruleElementRef EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:788:2: iv_ruleElementRef= ruleElementRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElementRefRule()); 
            }
            pushFollow(FOLLOW_ruleElementRef_in_entryRuleElementRef1775);
            iv_ruleElementRef=ruleElementRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElementRef; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementRef1785); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:795:1: ruleElementRef returns [EObject current=null] : ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* ) ;
    public final EObject ruleElementRef() throws RecognitionException {
        EObject current = null;

        Token lv_ids_1_0=null;
        Token otherlv_2=null;
        Token lv_ids_3_0=null;

         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:798:28: ( ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:799:1: ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:799:1: ( () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )* )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:799:2: () ( (lv_ids_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )*
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:799:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:800:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:808:2: ( (lv_ids_1_0= RULE_ID ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:809:1: (lv_ids_1_0= RULE_ID )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:809:1: (lv_ids_1_0= RULE_ID )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:810:3: lv_ids_1_0= RULE_ID
            {
            lv_ids_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleElementRef1839); if (state.failed) return current;
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
                      		"ID");
              	    
            }

            }


            }

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:826:2: (otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:826:4: otherlv_2= '.' ( (lv_ids_3_0= RULE_ID ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleElementRef1857); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getElementRefAccess().getFullStopKeyword_2_0());
            	          
            	    }
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:830:1: ( (lv_ids_3_0= RULE_ID ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:831:1: (lv_ids_3_0= RULE_ID )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:831:1: (lv_ids_3_0= RULE_ID )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:832:3: lv_ids_3_0= RULE_ID
            	    {
            	    lv_ids_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleElementRef1874); if (state.failed) return current;
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
            	              		"ID");
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:856:1: entryRuleConstRef returns [EObject current=null] : iv_ruleConstRef= ruleConstRef EOF ;
    public final EObject entryRuleConstRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstRef = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:857:2: (iv_ruleConstRef= ruleConstRef EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:858:2: iv_ruleConstRef= ruleConstRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstRefRule()); 
            }
            pushFollow(FOLLOW_ruleConstRef_in_entryRuleConstRef1917);
            iv_ruleConstRef=ruleConstRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstRef; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstRef1927); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:865:1: ruleConstRef returns [EObject current=null] : ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) ) ;
    public final EObject ruleConstRef() throws RecognitionException {
        EObject current = null;

        Token lv_packageSegments_1_0=null;
        Token otherlv_2=null;
        Token lv_constantName_3_0=null;

         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:868:28: ( ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:869:1: ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:869:1: ( () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:869:2: () ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+ ( (lv_constantName_3_0= RULE_ID ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:869:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:870:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:878:2: ( ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==21) ) {
                        alt8=1;
                    }


                }


                switch (alt8) {
            	case 1 :
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:878:3: ( (lv_packageSegments_1_0= RULE_ID ) ) otherlv_2= '::'
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:878:3: ( (lv_packageSegments_1_0= RULE_ID ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:879:1: (lv_packageSegments_1_0= RULE_ID )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:879:1: (lv_packageSegments_1_0= RULE_ID )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:880:3: lv_packageSegments_1_0= RULE_ID
            	    {
            	    lv_packageSegments_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleConstRef1982); if (state.failed) return current;
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
            	              		"ID");
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleConstRef1999); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getConstRefAccess().getColonColonKeyword_1_1());
            	          
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:900:3: ( (lv_constantName_3_0= RULE_ID ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:901:1: (lv_constantName_3_0= RULE_ID )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:901:1: (lv_constantName_3_0= RULE_ID )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:902:3: lv_constantName_3_0= RULE_ID
            {
            lv_constantName_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleConstRef2018); if (state.failed) return current;
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
                      		"ID");
              	    
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:926:1: entryRuleNegative returns [EObject current=null] : iv_ruleNegative= ruleNegative EOF ;
    public final EObject entryRuleNegative() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNegative = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:927:2: (iv_ruleNegative= ruleNegative EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:928:2: iv_ruleNegative= ruleNegative EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNegativeRule()); 
            }
            pushFollow(FOLLOW_ruleNegative_in_entryRuleNegative2059);
            iv_ruleNegative=ruleNegative();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNegative; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNegative2069); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:935:1: ruleNegative returns [EObject current=null] : ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) ) ;
    public final EObject ruleNegative() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:938:28: ( ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:939:1: ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:939:1: ( () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:939:2: () otherlv_1= '-' ( (lv_value_2_0= ruleScalar ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:939:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:940:2: 
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

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleNegative2118); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNegativeAccess().getHyphenMinusKeyword_1());
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:952:1: ( (lv_value_2_0= ruleScalar ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:953:1: (lv_value_2_0= ruleScalar )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:953:1: (lv_value_2_0= ruleScalar )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:954:3: lv_value_2_0= ruleScalar
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNegativeAccess().getValueScalarParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScalar_in_ruleNegative2139);
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
                      		"Scalar");
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:978:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:979:2: (iv_rulePrimary= rulePrimary EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:980:2: iv_rulePrimary= rulePrimary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryRule()); 
            }
            pushFollow(FOLLOW_rulePrimary_in_entryRulePrimary2175);
            iv_rulePrimary=rulePrimary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimary; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimary2185); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:987:1: rulePrimary returns [EObject current=null] : (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) ) ;
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
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:990:28: ( (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:991:1: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:991:1: (this_IntegerLiteral_0= ruleIntegerLiteral | this_RealLiteral_1= ruleRealLiteral | this_BooleanLiteral_2= ruleBooleanLiteral | (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt9=1;
                }
                break;
            case RULE_BIGDECIMAL:
                {
                alt9=2;
                }
                break;
            case RULE_TRUE:
            case RULE_FALSE:
                {
                alt9=3;
                }
                break;
            case 15:
                {
                alt9=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:992:2: this_IntegerLiteral_0= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryAccess().getIntegerLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_rulePrimary2235);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1005:2: this_RealLiteral_1= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryAccess().getRealLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_rulePrimary2265);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1018:2: this_BooleanLiteral_2= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryAccess().getBooleanLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePrimary2295);
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1030:6: (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1030:6: (otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1030:8: otherlv_3= '(' this_Scalar_4= ruleScalar otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_15_in_rulePrimary2313); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryAccess().getScalarParserRuleCall_3_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleScalar_in_rulePrimary2338);
                    this_Scalar_4=ruleScalar();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Scalar_4; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_5=(Token)match(input,16,FOLLOW_16_in_rulePrimary2349); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1058:1: entryRuleInterval returns [EObject current=null] : iv_ruleInterval= ruleInterval EOF ;
    public final EObject entryRuleInterval() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterval = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1059:2: (iv_ruleInterval= ruleInterval EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1060:2: iv_ruleInterval= ruleInterval EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntervalRule()); 
            }
            pushFollow(FOLLOW_ruleInterval_in_entryRuleInterval2386);
            iv_ruleInterval=ruleInterval();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterval; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterval2396); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1067:1: ruleInterval returns [EObject current=null] : ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) ) ;
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
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1070:28: ( ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1071:1: ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1071:1: ( () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1071:2: () ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' ) ( (lv_left_3_0= ruleScalar ) )? otherlv_4= ',' ( (lv_right_5_0= ruleScalar ) )? ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1071:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1072:2: 
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

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1080:2: ( ( (lv_leftClosed_1_0= '[' ) ) | otherlv_2= '(' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            else if ( (LA10_0==15) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1080:3: ( (lv_leftClosed_1_0= '[' ) )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1080:3: ( (lv_leftClosed_1_0= '[' ) )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1081:1: (lv_leftClosed_1_0= '[' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1081:1: (lv_leftClosed_1_0= '[' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1082:3: lv_leftClosed_1_0= '['
                    {
                    lv_leftClosed_1_0=(Token)match(input,23,FOLLOW_23_in_ruleInterval2452); if (state.failed) return current;
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1096:7: otherlv_2= '('
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_15_in_ruleInterval2483); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIntervalAccess().getLeftParenthesisKeyword_1_1());
                          
                    }

                    }
                    break;

            }

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1100:2: ( (lv_left_3_0= ruleScalar ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=RULE_ID && LA11_0<=RULE_INT)||(LA11_0>=14 && LA11_0<=15)||(LA11_0>=22 && LA11_0<=23)||LA11_0==26) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1101:1: (lv_left_3_0= ruleScalar )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1101:1: (lv_left_3_0= ruleScalar )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1102:3: lv_left_3_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIntervalAccess().getLeftScalarParserRuleCall_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleScalar_in_ruleInterval2505);
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
                              		"Scalar");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,24,FOLLOW_24_in_ruleInterval2518); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getIntervalAccess().getCommaKeyword_3());
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1122:1: ( (lv_right_5_0= ruleScalar ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_INT)||(LA12_0>=14 && LA12_0<=15)||(LA12_0>=22 && LA12_0<=23)||LA12_0==26) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1123:1: (lv_right_5_0= ruleScalar )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1123:1: (lv_right_5_0= ruleScalar )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1124:3: lv_right_5_0= ruleScalar
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIntervalAccess().getRightScalarParserRuleCall_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleScalar_in_ruleInterval2539);
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
                              		"Scalar");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1140:3: ( ( (lv_rightClosed_6_0= ']' ) ) | otherlv_7= ')' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==25) ) {
                alt13=1;
            }
            else if ( (LA13_0==16) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1140:4: ( (lv_rightClosed_6_0= ']' ) )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1140:4: ( (lv_rightClosed_6_0= ']' ) )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1141:1: (lv_rightClosed_6_0= ']' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1141:1: (lv_rightClosed_6_0= ']' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1142:3: lv_rightClosed_6_0= ']'
                    {
                    lv_rightClosed_6_0=(Token)match(input,25,FOLLOW_25_in_ruleInterval2559); if (state.failed) return current;
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1156:7: otherlv_7= ')'
                    {
                    otherlv_7=(Token)match(input,16,FOLLOW_16_in_ruleInterval2590); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1168:1: entryRuleSet returns [EObject current=null] : iv_ruleSet= ruleSet EOF ;
    public final EObject entryRuleSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSet = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1169:2: (iv_ruleSet= ruleSet EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1170:2: iv_ruleSet= ruleSet EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSetRule()); 
            }
            pushFollow(FOLLOW_ruleSet_in_entryRuleSet2627);
            iv_ruleSet=ruleSet();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSet; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSet2637); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1177:1: ruleSet returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* otherlv_5= '}' ) ;
    public final EObject ruleSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_members_2_0 = null;

        EObject lv_members_4_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1180:28: ( ( () otherlv_1= '{' ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* otherlv_5= '}' ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1181:1: ( () otherlv_1= '{' ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* otherlv_5= '}' )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1181:1: ( () otherlv_1= '{' ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* otherlv_5= '}' )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1181:2: () otherlv_1= '{' ( (lv_members_2_0= ruleScalar ) ) (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )* otherlv_5= '}'
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1181:2: ()
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1182:2: 
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleSet2686); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getSetAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1194:1: ( (lv_members_2_0= ruleScalar ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1195:1: (lv_members_2_0= ruleScalar )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1195:1: (lv_members_2_0= ruleScalar )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1196:3: lv_members_2_0= ruleScalar
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSetAccess().getMembersScalarParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScalar_in_ruleSet2707);
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
                      		"Scalar");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1212:2: (otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==24) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1212:4: otherlv_3= ',' ( (lv_members_4_0= ruleScalar ) )
            	    {
            	    otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleSet2720); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_3, grammarAccess.getSetAccess().getCommaKeyword_3_0());
            	          
            	    }
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1216:1: ( (lv_members_4_0= ruleScalar ) )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1217:1: (lv_members_4_0= ruleScalar )
            	    {
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1217:1: (lv_members_4_0= ruleScalar )
            	    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1218:3: lv_members_4_0= ruleScalar
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSetAccess().getMembersScalarParserRuleCall_3_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScalar_in_ruleSet2741);
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
            	              		"Scalar");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleSet2755); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getSetAccess().getRightCurlyBracketKeyword_4());
                  
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1246:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1247:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1248:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral2791);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral2801); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1255:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_value_0_0= ruleBIG_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1258:28: ( ( (lv_value_0_0= ruleBIG_INT ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1259:1: ( (lv_value_0_0= ruleBIG_INT ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1259:1: ( (lv_value_0_0= ruleBIG_INT ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1260:1: (lv_value_0_0= ruleBIG_INT )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1260:1: (lv_value_0_0= ruleBIG_INT )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1261:3: lv_value_0_0= ruleBIG_INT
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntegerLiteralAccess().getValueBIG_INTParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleBIG_INT_in_ruleIntegerLiteral2846);
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
                      		"BIG_INT");
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1285:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1286:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1287:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral2881);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral2891); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1294:1: ruleRealLiteral returns [EObject current=null] : ( (lv_value_0_0= RULE_BIGDECIMAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1297:28: ( ( (lv_value_0_0= RULE_BIGDECIMAL ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1298:1: ( (lv_value_0_0= RULE_BIGDECIMAL ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1298:1: ( (lv_value_0_0= RULE_BIGDECIMAL ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1299:1: (lv_value_0_0= RULE_BIGDECIMAL )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1299:1: (lv_value_0_0= RULE_BIGDECIMAL )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1300:3: lv_value_0_0= RULE_BIGDECIMAL
            {
            lv_value_0_0=(Token)match(input,RULE_BIGDECIMAL,FOLLOW_RULE_BIGDECIMAL_in_ruleRealLiteral2932); if (state.failed) return current;
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
                      		"BIGDECIMAL");
              	    
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1324:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1325:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1326:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral2972);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral2982); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1333:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;
        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1336:28: ( ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1337:1: ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1337:1: ( ( (lv_value_0_0= RULE_TRUE ) ) | ( (lv_value_1_0= RULE_FALSE ) ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_TRUE) ) {
                alt15=1;
            }
            else if ( (LA15_0==RULE_FALSE) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1337:2: ( (lv_value_0_0= RULE_TRUE ) )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1337:2: ( (lv_value_0_0= RULE_TRUE ) )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1338:1: (lv_value_0_0= RULE_TRUE )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1338:1: (lv_value_0_0= RULE_TRUE )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1339:3: lv_value_0_0= RULE_TRUE
                    {
                    lv_value_0_0=(Token)match(input,RULE_TRUE,FOLLOW_RULE_TRUE_in_ruleBooleanLiteral3024); if (state.failed) return current;
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
                              		"TRUE");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1356:6: ( (lv_value_1_0= RULE_FALSE ) )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1356:6: ( (lv_value_1_0= RULE_FALSE ) )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1357:1: (lv_value_1_0= RULE_FALSE )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1357:1: (lv_value_1_0= RULE_FALSE )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1358:3: lv_value_1_0= RULE_FALSE
                    {
                    lv_value_1_0=(Token)match(input,RULE_FALSE,FOLLOW_RULE_FALSE_in_ruleBooleanLiteral3052); if (state.failed) return current;
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
                              		"FALSE");
                      	    
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1382:1: entryRuleBIG_INT returns [String current=null] : iv_ruleBIG_INT= ruleBIG_INT EOF ;
    public final String entryRuleBIG_INT() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBIG_INT = null;


        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1383:2: (iv_ruleBIG_INT= ruleBIG_INT EOF )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1384:2: iv_ruleBIG_INT= ruleBIG_INT EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBIG_INTRule()); 
            }
            pushFollow(FOLLOW_ruleBIG_INT_in_entryRuleBIG_INT3094);
            iv_ruleBIG_INT=ruleBIG_INT();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBIG_INT.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBIG_INT3105); if (state.failed) return current;

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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1391:1: ruleBIG_INT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleBIG_INT() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule(); 
            
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1394:28: (this_INT_0= RULE_INT )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1395:5: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleBIG_INT3144); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1412:1: ruleAddSubOperator returns [Enumerator current=null] : ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) ;
    public final Enumerator ruleAddSubOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1414:28: ( ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1415:1: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1415:1: ( (enumLiteral_0= '+' ) | (enumLiteral_1= '-' ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==28) ) {
                alt16=1;
            }
            else if ( (LA16_0==22) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1415:2: (enumLiteral_0= '+' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1415:2: (enumLiteral_0= '+' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1415:4: enumLiteral_0= '+'
                    {
                    enumLiteral_0=(Token)match(input,28,FOLLOW_28_in_ruleAddSubOperator3204); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getAddSubOperatorAccess().getADDITIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getAddSubOperatorAccess().getADDITIONEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1421:6: (enumLiteral_1= '-' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1421:6: (enumLiteral_1= '-' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1421:8: enumLiteral_1= '-'
                    {
                    enumLiteral_1=(Token)match(input,22,FOLLOW_22_in_ruleAddSubOperator3221); if (state.failed) return current;
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
    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1431:1: ruleMultDivOperator returns [Enumerator current=null] : ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) ) ;
    public final Enumerator ruleMultDivOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1433:28: ( ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) ) )
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1434:1: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) )
            {
            // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1434:1: ( (enumLiteral_0= '*' ) | (enumLiteral_1= '/' ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==29) ) {
                alt17=1;
            }
            else if ( (LA17_0==30) ) {
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
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1434:2: (enumLiteral_0= '*' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1434:2: (enumLiteral_0= '*' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1434:4: enumLiteral_0= '*'
                    {
                    enumLiteral_0=(Token)match(input,29,FOLLOW_29_in_ruleMultDivOperator3266); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getMultDivOperatorAccess().getMULTIPLICATIONEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getMultDivOperatorAccess().getMULTIPLICATIONEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1440:6: (enumLiteral_1= '/' )
                    {
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1440:6: (enumLiteral_1= '/' )
                    // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:1440:8: enumLiteral_1= '/'
                    {
                    enumLiteral_1=(Token)match(input,30,FOLLOW_30_in_ruleMultDivOperator3283); if (state.failed) return current;
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


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:121:2: (this_Scalar_0= ruleScalar )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:121:2: this_Scalar_0= ruleScalar
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleScalar_in_synpred1_InternalInputConstraint234);
        this_Scalar_0=ruleScalar();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalInputConstraint

    // $ANTLR start synpred2_InternalInputConstraint
    public final void synpred2_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Interval_1 = null;


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:134:2: (this_Interval_1= ruleInterval )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:134:2: this_Interval_1= ruleInterval
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleInterval_in_synpred2_InternalInputConstraint264);
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


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:2: ( () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:2: () ( (lv_op_2_0= ruleAddSubOperator ) ) ( (lv_right_3_0= ruleMultDiv ) )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:224:2: ()
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:225:2: 
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }

        }

        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:233:2: ( (lv_op_2_0= ruleAddSubOperator ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:234:1: (lv_op_2_0= ruleAddSubOperator )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:234:1: (lv_op_2_0= ruleAddSubOperator )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:235:3: lv_op_2_0= ruleAddSubOperator
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getAddSubAccess().getOpAddSubOperatorEnumRuleCall_1_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleAddSubOperator_in_synpred3_InternalInputConstraint515);
        lv_op_2_0=ruleAddSubOperator();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:251:2: ( (lv_right_3_0= ruleMultDiv ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:252:1: (lv_right_3_0= ruleMultDiv )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:252:1: (lv_right_3_0= ruleMultDiv )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:253:3: lv_right_3_0= ruleMultDiv
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getAddSubAccess().getRightMultDivParserRuleCall_1_2_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleMultDiv_in_synpred3_InternalInputConstraint536);
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


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:2: ( () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:2: () ( (lv_op_2_0= ruleMultDivOperator ) ) ( (lv_right_3_0= ruleFunction ) )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:302:2: ()
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:303:2: 
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }

        }

        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:311:2: ( (lv_op_2_0= ruleMultDivOperator ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:312:1: (lv_op_2_0= ruleMultDivOperator )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:312:1: (lv_op_2_0= ruleMultDivOperator )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:313:3: lv_op_2_0= ruleMultDivOperator
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getMultDivAccess().getOpMultDivOperatorEnumRuleCall_1_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleMultDivOperator_in_synpred4_InternalInputConstraint667);
        lv_op_2_0=ruleMultDivOperator();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:329:2: ( (lv_right_3_0= ruleFunction ) )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:330:1: (lv_right_3_0= ruleFunction )
        {
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:330:1: (lv_right_3_0= ruleFunction )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:331:3: lv_right_3_0= ruleFunction
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getMultDivAccess().getRightFunctionParserRuleCall_1_2_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleFunction_in_synpred4_InternalInputConstraint688);
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


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:382:2: (this_Random_1= ruleRandom )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:382:2: this_Random_1= ruleRandom
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleRandom_in_synpred6_InternalInputConstraint816);
        this_Random_1=ruleRandom();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_InternalInputConstraint

    // $ANTLR start synpred7_InternalInputConstraint
    public final void synpred7_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_Ref_2 = null;


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:395:2: (this_Ref_2= ruleRef )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:395:2: this_Ref_2= ruleRef
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleRef_in_synpred7_InternalInputConstraint846);
        this_Ref_2=ruleRef();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_InternalInputConstraint

    // $ANTLR start synpred8_InternalInputConstraint
    public final void synpred8_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_ConstRef_3 = null;


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:408:2: (this_ConstRef_3= ruleConstRef )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:408:2: this_ConstRef_3= ruleConstRef
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleConstRef_in_synpred8_InternalInputConstraint876);
        this_ConstRef_3=ruleConstRef();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_InternalInputConstraint

    // $ANTLR start synpred10_InternalInputConstraint
    public final void synpred10_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_RandomInteger_0 = null;


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:527:2: (this_RandomInteger_0= ruleRandomInteger )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:527:2: this_RandomInteger_0= ruleRandomInteger
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleRandomInteger_in_synpred10_InternalInputConstraint1171);
        this_RandomInteger_0=ruleRandomInteger();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_InternalInputConstraint

    // $ANTLR start synpred11_InternalInputConstraint
    public final void synpred11_InternalInputConstraint_fragment() throws RecognitionException {   
        EObject this_RandomReal_1 = null;


        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:540:2: (this_RandomReal_1= ruleRandomReal )
        // ../edu.uah.rsesc.aadlsimulator.xtext/src-gen/edu/uah/rsesc/aadlsimulator/xtext/parser/antlr/internal/InternalInputConstraint.g:540:2: this_RandomReal_1= ruleRandomReal
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleRandomReal_in_synpred11_InternalInputConstraint1201);
        this_RandomReal_1=ruleRandomReal();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_InternalInputConstraint

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
    public final boolean synpred10_InternalInputConstraint() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_InternalInputConstraint_fragment(); // can never throw exception
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
    static final String DFA1_eotS =
        "\15\uffff";
    static final String DFA1_eofS =
        "\15\uffff";
    static final String DFA1_minS =
        "\1\4\1\uffff\3\0\10\uffff";
    static final String DFA1_maxS =
        "\1\32\1\uffff\3\0\10\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\11\uffff\1\2\1\3";
    static final String DFA1_specialS =
        "\2\uffff\1\0\1\1\1\2\10\uffff}>";
    static final String[] DFA1_transitionS = {
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

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "120:1: (this_Scalar_0= ruleScalar | this_Interval_1= ruleInterval | this_Set_2= ruleSet )";
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
    static final String DFA4_eotS =
        "\15\uffff";
    static final String DFA4_eofS =
        "\15\uffff";
    static final String DFA4_minS =
        "\1\4\2\uffff\1\0\1\uffff\1\0\7\uffff";
    static final String DFA4_maxS =
        "\1\32\2\uffff\1\0\1\uffff\1\0\7\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\1\1\2\3\uffff\1\5\1\6\3\uffff\1\3\1\4";
    static final String DFA4_specialS =
        "\3\uffff\1\0\1\uffff\1\1\7\uffff}>";
    static final String[] DFA4_transitionS = {
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

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "368:1: (this_Pre_0= rulePre | this_Random_1= ruleRandom | this_Ref_2= ruleRef | this_ConstRef_3= ruleConstRef | this_Negative_4= ruleNegative | this_Primary_5= rulePrimary )";
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
 

    public static final BitSet FOLLOW_ruleInputConstraint_in_entryRuleInputConstraint81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInputConstraint91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleInputConstraint140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleExpr234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterval_in_ruleExpr264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSet_in_ruleExpr294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalar_in_entryRuleScalar329 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScalar339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAddSub_in_ruleScalar388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAddSub_in_entryRuleAddSub422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAddSub432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultDiv_in_ruleAddSub482 = new BitSet(new long[]{0x0000000010400002L});
    public static final BitSet FOLLOW_ruleAddSubOperator_in_ruleAddSub515 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleMultDiv_in_ruleAddSub536 = new BitSet(new long[]{0x0000000010400002L});
    public static final BitSet FOLLOW_ruleMultDiv_in_entryRuleMultDiv574 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultDiv584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunction_in_ruleMultDiv634 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_ruleMultDivOperator_in_ruleMultDiv667 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleFunction_in_ruleMultDiv688 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_ruleFunction_in_entryRuleFunction726 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunction736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePre_in_ruleFunction786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandom_in_ruleFunction816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRef_in_ruleFunction846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstRef_in_ruleFunction876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNegative_in_ruleFunction906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_ruleFunction936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePre_in_entryRulePre971 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePre981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rulePre1030 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_rulePre1042 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleRef_in_rulePre1063 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulePre1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandom_in_entryRuleRandom1111 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRandom1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomInteger_in_ruleRandom1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomReal_in_ruleRandom1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomElement_in_ruleRandom1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomInteger_in_entryRuleRandomInteger1266 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRandomInteger1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterval_in_ruleRandomInteger1334 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleRandomInteger1346 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleRandomInteger1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomReal_in_entryRuleRandomReal1394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRandomReal1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterval_in_ruleRandomReal1462 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleRandomReal1474 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleRandomReal1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomElement_in_entryRuleRandomElement1522 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRandomElement1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSet_in_ruleRandomElement1590 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleRandomElement1602 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleRandomElement1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRef_in_entryRuleRef1650 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRef1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementRef_in_ruleRef1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstRef_in_ruleRef1740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementRef_in_entryRuleElementRef1775 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementRef1785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleElementRef1839 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleElementRef1857 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleElementRef1874 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleConstRef_in_entryRuleConstRef1917 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstRef1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstRef1982 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleConstRef1999 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstRef2018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNegative_in_entryRuleNegative2059 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNegative2069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleNegative2118 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleNegative2139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimary_in_entryRulePrimary2175 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimary2185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_rulePrimary2235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_rulePrimary2265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePrimary2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rulePrimary2313 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_rulePrimary2338 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulePrimary2349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterval_in_entryRuleInterval2386 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterval2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleInterval2452 = new BitSet(new long[]{0x0000000005C0C1F0L});
    public static final BitSet FOLLOW_15_in_ruleInterval2483 = new BitSet(new long[]{0x0000000005C0C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleInterval2505 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleInterval2518 = new BitSet(new long[]{0x0000000006C1C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleInterval2539 = new BitSet(new long[]{0x0000000002010000L});
    public static final BitSet FOLLOW_25_in_ruleInterval2559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleInterval2590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSet_in_entryRuleSet2627 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSet2637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleSet2686 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleSet2707 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_24_in_ruleSet2720 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleScalar_in_ruleSet2741 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_27_in_ruleSet2755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral2791 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral2801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBIG_INT_in_ruleIntegerLiteral2846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral2881 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral2891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BIGDECIMAL_in_ruleRealLiteral2932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral2972 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral2982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TRUE_in_ruleBooleanLiteral3024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_FALSE_in_ruleBooleanLiteral3052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBIG_INT_in_entryRuleBIG_INT3094 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBIG_INT3105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleBIG_INT3144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleAddSubOperator3204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleAddSubOperator3221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleMultDivOperator3266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleMultDivOperator3283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScalar_in_synpred1_InternalInputConstraint234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterval_in_synpred2_InternalInputConstraint264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAddSubOperator_in_synpred3_InternalInputConstraint515 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleMultDiv_in_synpred3_InternalInputConstraint536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultDivOperator_in_synpred4_InternalInputConstraint667 = new BitSet(new long[]{0x0000000004C0C1F0L});
    public static final BitSet FOLLOW_ruleFunction_in_synpred4_InternalInputConstraint688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandom_in_synpred6_InternalInputConstraint816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRef_in_synpred7_InternalInputConstraint846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstRef_in_synpred8_InternalInputConstraint876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomInteger_in_synpred10_InternalInputConstraint1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRandomReal_in_synpred11_InternalInputConstraint1201 = new BitSet(new long[]{0x0000000000000002L});

}