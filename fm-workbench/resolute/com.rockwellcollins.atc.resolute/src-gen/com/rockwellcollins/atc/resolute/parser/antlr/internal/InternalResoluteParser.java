package com.rockwellcollins.atc.resolute.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalResoluteParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_REAL_LIT", "RULE_INTEGER_LIT", "RULE_SL_COMMENT", "RULE_DIGIT", "RULE_EXPONENT", "RULE_INT_EXPONENT", "RULE_BASED_INTEGER", "RULE_EXTENDED_DIGIT", "RULE_ANNEXTEXT", "RULE_WS", "'{'", "'}'", "'data'", "'thread'", "'thread_group'", "'process'", "'subprogram'", "'subprogram_group'", "'processor'", "'virtual_processor'", "'memory'", "'bus'", "'virtual_bus'", "'device'", "'system'", "'abstract'", "'connection'", "'component'", "'string'", "'int'", "'real'", "'bool'", "':'", "'='", "'('", "','", "')'", "'<='", "'**'", "'=>'", "'or'", "'and'", "'<'", "'>'", "'>='", "'<>'", "'in'", "'+'", "'-'", "'*'", "'/'", "'union'", "'intersect'", "'not'", "'this'", "'.'", "'fail'", "'if'", "'then'", "'else'", "'forall'", "'exists'", "'for'", "'|'", "'connected'", "'property_lookup'", "'class_of'", "'type'", "'has_type'", "'bound'", "'contained'", "'conn_source'", "'conn_dest'", "'property_exists'", "'sum'", "'analysis'", "'upper_bound'", "'lower_bound'", "'receive_error'", "'contain_error'", "'propagate_error'", "'error_state_reachable'", "'prove'", "'modes'", "'+=>'", "'constant'", "'applies'", "'to'", "'binding'", "';'", "'true'", "'false'", "'reference'", "'['", "']'", "'compute'", "'classifier'", "'..'", "'delta'", "'::'"
    };
    public static final int RULE_ID=4;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int RULE_REAL_LIT=6;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int RULE_ANNEXTEXT=14;
    public static final int T__93=93;
    public static final int T__19=19;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__16=16;
    public static final int T__90=90;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int RULE_EXTENDED_DIGIT=13;
    public static final int T__95=95;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int RULE_STRING=5;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int RULE_EXPONENT=10;
    public static final int T__76=76;
    public static final int RULE_DIGIT=9;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int RULE_INTEGER_LIT=7;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int RULE_BASED_INTEGER=12;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_INT_EXPONENT=11;
    public static final int RULE_WS=15;

    // delegates
    // delegators


        public InternalResoluteParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalResoluteParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalResoluteParser.tokenNames; }
    public String getGrammarFileName() { return "../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g"; }



     	private ResoluteGrammarAccess grammarAccess;
     	
        public InternalResoluteParser(TokenStream input, ResoluteGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "AnnexLibrary";	
       	}
       	
       	@Override
       	protected ResoluteGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleAnnexLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:67:1: entryRuleAnnexLibrary returns [EObject current=null] : iv_ruleAnnexLibrary= ruleAnnexLibrary EOF ;
    public final EObject entryRuleAnnexLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnexLibrary = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:68:2: (iv_ruleAnnexLibrary= ruleAnnexLibrary EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:69:2: iv_ruleAnnexLibrary= ruleAnnexLibrary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnnexLibraryRule()); 
            }
            pushFollow(FOLLOW_ruleAnnexLibrary_in_entryRuleAnnexLibrary75);
            iv_ruleAnnexLibrary=ruleAnnexLibrary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnnexLibrary; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnnexLibrary85); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAnnexLibrary"


    // $ANTLR start "ruleAnnexLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:76:1: ruleAnnexLibrary returns [EObject current=null] : this_ResoluteLibrary_0= ruleResoluteLibrary ;
    public final EObject ruleAnnexLibrary() throws RecognitionException {
        EObject current = null;

        EObject this_ResoluteLibrary_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:79:28: (this_ResoluteLibrary_0= ruleResoluteLibrary )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:81:5: this_ResoluteLibrary_0= ruleResoluteLibrary
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAnnexLibraryAccess().getResoluteLibraryParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleResoluteLibrary_in_ruleAnnexLibrary131);
            this_ResoluteLibrary_0=ruleResoluteLibrary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ResoluteLibrary_0; 
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
    // $ANTLR end "ruleAnnexLibrary"


    // $ANTLR start "entryRuleResoluteLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:105:1: entryRuleResoluteLibrary returns [EObject current=null] : iv_ruleResoluteLibrary= ruleResoluteLibrary EOF ;
    public final EObject entryRuleResoluteLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteLibrary = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:106:2: (iv_ruleResoluteLibrary= ruleResoluteLibrary EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:107:2: iv_ruleResoluteLibrary= ruleResoluteLibrary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getResoluteLibraryRule()); 
            }
            pushFollow(FOLLOW_ruleResoluteLibrary_in_entryRuleResoluteLibrary173);
            iv_ruleResoluteLibrary=ruleResoluteLibrary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleResoluteLibrary; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleResoluteLibrary183); if (state.failed) return current;

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
    // $ANTLR end "entryRuleResoluteLibrary"


    // $ANTLR start "ruleResoluteLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:114:1: ruleResoluteLibrary returns [EObject current=null] : ( () ( (lv_definitions_1_0= ruleDefinition ) )* ) ;
    public final EObject ruleResoluteLibrary() throws RecognitionException {
        EObject current = null;

        EObject lv_definitions_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:117:28: ( ( () ( (lv_definitions_1_0= ruleDefinition ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:118:1: ( () ( (lv_definitions_1_0= ruleDefinition ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:118:1: ( () ( (lv_definitions_1_0= ruleDefinition ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:118:2: () ( (lv_definitions_1_0= ruleDefinition ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:118:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:119:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getResoluteLibraryAccess().getResoluteLibraryAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:124:2: ( (lv_definitions_1_0= ruleDefinition ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:125:1: (lv_definitions_1_0= ruleDefinition )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:125:1: (lv_definitions_1_0= ruleDefinition )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:126:3: lv_definitions_1_0= ruleDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getResoluteLibraryAccess().getDefinitionsDefinitionParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleDefinition_in_ruleResoluteLibrary238);
            	    lv_definitions_1_0=ruleDefinition();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getResoluteLibraryRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"definitions",
            	              		lv_definitions_1_0, 
            	              		"Definition");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
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
    // $ANTLR end "ruleResoluteLibrary"


    // $ANTLR start "entryRuleDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:150:1: entryRuleDefinition returns [EObject current=null] : iv_ruleDefinition= ruleDefinition EOF ;
    public final EObject entryRuleDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:151:2: (iv_ruleDefinition= ruleDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:152:2: iv_ruleDefinition= ruleDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleDefinition_in_entryRuleDefinition275);
            iv_ruleDefinition=ruleDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefinition285); if (state.failed) return current;

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
    // $ANTLR end "entryRuleDefinition"


    // $ANTLR start "ruleDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:159:1: ruleDefinition returns [EObject current=null] : (this_ConstantDefinition_0= ruleConstantDefinition | this_FunctionDefinition_1= ruleFunctionDefinition ) ;
    public final EObject ruleDefinition() throws RecognitionException {
        EObject current = null;

        EObject this_ConstantDefinition_0 = null;

        EObject this_FunctionDefinition_1 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:162:28: ( (this_ConstantDefinition_0= ruleConstantDefinition | this_FunctionDefinition_1= ruleFunctionDefinition ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:163:1: (this_ConstantDefinition_0= ruleConstantDefinition | this_FunctionDefinition_1= ruleFunctionDefinition )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:163:1: (this_ConstantDefinition_0= ruleConstantDefinition | this_FunctionDefinition_1= ruleFunctionDefinition )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==40) ) {
                    alt2=2;
                }
                else if ( (LA2_1==38) ) {
                    alt2=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:164:5: this_ConstantDefinition_0= ruleConstantDefinition
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDefinitionAccess().getConstantDefinitionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstantDefinition_in_ruleDefinition332);
                    this_ConstantDefinition_0=ruleConstantDefinition();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ConstantDefinition_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:174:5: this_FunctionDefinition_1= ruleFunctionDefinition
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDefinitionAccess().getFunctionDefinitionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFunctionDefinition_in_ruleDefinition359);
                    this_FunctionDefinition_1=ruleFunctionDefinition();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_FunctionDefinition_1; 
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
    // $ANTLR end "ruleDefinition"


    // $ANTLR start "entryRuleType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:190:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:191:2: (iv_ruleType= ruleType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:192:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType394);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType404); if (state.failed) return current;

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
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:199:1: ruleType returns [EObject current=null] : ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | this_BuiltinType_4= ruleBuiltinType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject this_BuiltinType_4 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:202:28: ( ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | this_BuiltinType_4= ruleBuiltinType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:1: ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | this_BuiltinType_4= ruleBuiltinType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:1: ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | this_BuiltinType_4= ruleBuiltinType )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            else if ( ((LA3_0>=18 && LA3_0<=37)) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:2: ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:2: ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:3: () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:204:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getTypeAccess().getSetTypeAction_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleType451); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeAccess().getLeftCurlyBracketKeyword_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:213:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:214:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:214:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:215:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getTypeAccess().getTypeTypeParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleType472);
                    lv_type_2_0=ruleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_2_0, 
                              		"Type");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,17,FOLLOW_17_in_ruleType484); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTypeAccess().getRightCurlyBracketKeyword_0_3());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:237:5: this_BuiltinType_4= ruleBuiltinType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getBuiltinTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBuiltinType_in_ruleType513);
                    this_BuiltinType_4=ruleBuiltinType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BuiltinType_4; 
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
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleBuiltinType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:253:1: entryRuleBuiltinType returns [EObject current=null] : iv_ruleBuiltinType= ruleBuiltinType EOF ;
    public final EObject entryRuleBuiltinType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuiltinType = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:254:2: (iv_ruleBuiltinType= ruleBuiltinType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:255:2: iv_ruleBuiltinType= ruleBuiltinType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltinTypeRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType548);
            iv_ruleBuiltinType=ruleBuiltinType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltinType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltinType558); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBuiltinType"


    // $ANTLR start "ruleBuiltinType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:262:1: ruleBuiltinType returns [EObject current=null] : ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) ) ;
    public final EObject ruleBuiltinType() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        Token lv_name_0_3=null;
        Token lv_name_0_4=null;
        Token lv_name_0_5=null;
        Token lv_name_0_6=null;
        Token lv_name_0_7=null;
        Token lv_name_0_8=null;
        Token lv_name_0_9=null;
        Token lv_name_0_10=null;
        Token lv_name_0_11=null;
        Token lv_name_0_12=null;
        Token lv_name_0_13=null;
        Token lv_name_0_14=null;
        Token lv_name_0_15=null;
        Token lv_name_0_16=null;
        Token lv_name_0_17=null;
        Token lv_name_0_18=null;
        Token lv_name_0_19=null;
        Token lv_name_0_20=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:265:28: ( ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1: ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1: ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:267:1: ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:267:1: ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:268:1: (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:268:1: (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' )
            int alt4=20;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt4=1;
                }
                break;
            case 19:
                {
                alt4=2;
                }
                break;
            case 20:
                {
                alt4=3;
                }
                break;
            case 21:
                {
                alt4=4;
                }
                break;
            case 22:
                {
                alt4=5;
                }
                break;
            case 23:
                {
                alt4=6;
                }
                break;
            case 24:
                {
                alt4=7;
                }
                break;
            case 25:
                {
                alt4=8;
                }
                break;
            case 26:
                {
                alt4=9;
                }
                break;
            case 27:
                {
                alt4=10;
                }
                break;
            case 28:
                {
                alt4=11;
                }
                break;
            case 29:
                {
                alt4=12;
                }
                break;
            case 30:
                {
                alt4=13;
                }
                break;
            case 31:
                {
                alt4=14;
                }
                break;
            case 32:
                {
                alt4=15;
                }
                break;
            case 33:
                {
                alt4=16;
                }
                break;
            case 34:
                {
                alt4=17;
                }
                break;
            case 35:
                {
                alt4=18;
                }
                break;
            case 36:
                {
                alt4=19;
                }
                break;
            case 37:
                {
                alt4=20;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:269:3: lv_name_0_1= 'data'
                    {
                    lv_name_0_1=(Token)match(input,18,FOLLOW_18_in_ruleBuiltinType602); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_1, grammarAccess.getBuiltinTypeAccess().getNameDataKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:281:8: lv_name_0_2= 'thread'
                    {
                    lv_name_0_2=(Token)match(input,19,FOLLOW_19_in_ruleBuiltinType631); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_2, grammarAccess.getBuiltinTypeAccess().getNameThreadKeyword_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:293:8: lv_name_0_3= 'thread_group'
                    {
                    lv_name_0_3=(Token)match(input,20,FOLLOW_20_in_ruleBuiltinType660); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_3, grammarAccess.getBuiltinTypeAccess().getNameThread_groupKeyword_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:305:8: lv_name_0_4= 'process'
                    {
                    lv_name_0_4=(Token)match(input,21,FOLLOW_21_in_ruleBuiltinType689); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_4, grammarAccess.getBuiltinTypeAccess().getNameProcessKeyword_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_4, null);
                      	    
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:317:8: lv_name_0_5= 'subprogram'
                    {
                    lv_name_0_5=(Token)match(input,22,FOLLOW_22_in_ruleBuiltinType718); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_5, grammarAccess.getBuiltinTypeAccess().getNameSubprogramKeyword_0_4());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_5, null);
                      	    
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:329:8: lv_name_0_6= 'subprogram_group'
                    {
                    lv_name_0_6=(Token)match(input,23,FOLLOW_23_in_ruleBuiltinType747); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_6, grammarAccess.getBuiltinTypeAccess().getNameSubprogram_groupKeyword_0_5());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_6, null);
                      	    
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:341:8: lv_name_0_7= 'processor'
                    {
                    lv_name_0_7=(Token)match(input,24,FOLLOW_24_in_ruleBuiltinType776); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_7, grammarAccess.getBuiltinTypeAccess().getNameProcessorKeyword_0_6());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_7, null);
                      	    
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:353:8: lv_name_0_8= 'virtual_processor'
                    {
                    lv_name_0_8=(Token)match(input,25,FOLLOW_25_in_ruleBuiltinType805); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_8, grammarAccess.getBuiltinTypeAccess().getNameVirtual_processorKeyword_0_7());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_8, null);
                      	    
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:365:8: lv_name_0_9= 'memory'
                    {
                    lv_name_0_9=(Token)match(input,26,FOLLOW_26_in_ruleBuiltinType834); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_9, grammarAccess.getBuiltinTypeAccess().getNameMemoryKeyword_0_8());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_9, null);
                      	    
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:377:8: lv_name_0_10= 'bus'
                    {
                    lv_name_0_10=(Token)match(input,27,FOLLOW_27_in_ruleBuiltinType863); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_10, grammarAccess.getBuiltinTypeAccess().getNameBusKeyword_0_9());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_10, null);
                      	    
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:389:8: lv_name_0_11= 'virtual_bus'
                    {
                    lv_name_0_11=(Token)match(input,28,FOLLOW_28_in_ruleBuiltinType892); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_11, grammarAccess.getBuiltinTypeAccess().getNameVirtual_busKeyword_0_10());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_11, null);
                      	    
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:401:8: lv_name_0_12= 'device'
                    {
                    lv_name_0_12=(Token)match(input,29,FOLLOW_29_in_ruleBuiltinType921); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_12, grammarAccess.getBuiltinTypeAccess().getNameDeviceKeyword_0_11());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_12, null);
                      	    
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:413:8: lv_name_0_13= 'system'
                    {
                    lv_name_0_13=(Token)match(input,30,FOLLOW_30_in_ruleBuiltinType950); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_13, grammarAccess.getBuiltinTypeAccess().getNameSystemKeyword_0_12());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_13, null);
                      	    
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:425:8: lv_name_0_14= 'abstract'
                    {
                    lv_name_0_14=(Token)match(input,31,FOLLOW_31_in_ruleBuiltinType979); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_14, grammarAccess.getBuiltinTypeAccess().getNameAbstractKeyword_0_13());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_14, null);
                      	    
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:437:8: lv_name_0_15= 'connection'
                    {
                    lv_name_0_15=(Token)match(input,32,FOLLOW_32_in_ruleBuiltinType1008); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_15, grammarAccess.getBuiltinTypeAccess().getNameConnectionKeyword_0_14());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_15, null);
                      	    
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:449:8: lv_name_0_16= 'component'
                    {
                    lv_name_0_16=(Token)match(input,33,FOLLOW_33_in_ruleBuiltinType1037); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_16, grammarAccess.getBuiltinTypeAccess().getNameComponentKeyword_0_15());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_16, null);
                      	    
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:461:8: lv_name_0_17= 'string'
                    {
                    lv_name_0_17=(Token)match(input,34,FOLLOW_34_in_ruleBuiltinType1066); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_17, grammarAccess.getBuiltinTypeAccess().getNameStringKeyword_0_16());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_17, null);
                      	    
                    }

                    }
                    break;
                case 18 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:473:8: lv_name_0_18= 'int'
                    {
                    lv_name_0_18=(Token)match(input,35,FOLLOW_35_in_ruleBuiltinType1095); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_18, grammarAccess.getBuiltinTypeAccess().getNameIntKeyword_0_17());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_18, null);
                      	    
                    }

                    }
                    break;
                case 19 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:485:8: lv_name_0_19= 'real'
                    {
                    lv_name_0_19=(Token)match(input,36,FOLLOW_36_in_ruleBuiltinType1124); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_19, grammarAccess.getBuiltinTypeAccess().getNameRealKeyword_0_18());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_19, null);
                      	    
                    }

                    }
                    break;
                case 20 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:497:8: lv_name_0_20= 'bool'
                    {
                    lv_name_0_20=(Token)match(input,37,FOLLOW_37_in_ruleBuiltinType1153); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_20, grammarAccess.getBuiltinTypeAccess().getNameBoolKeyword_0_19());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_20, null);
                      	    
                    }

                    }
                    break;

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
    // $ANTLR end "ruleBuiltinType"


    // $ANTLR start "entryRuleArg"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:520:1: entryRuleArg returns [EObject current=null] : iv_ruleArg= ruleArg EOF ;
    public final EObject entryRuleArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArg = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:521:2: (iv_ruleArg= ruleArg EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:522:2: iv_ruleArg= ruleArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgRule()); 
            }
            pushFollow(FOLLOW_ruleArg_in_entryRuleArg1204);
            iv_ruleArg=ruleArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArg; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArg1214); if (state.failed) return current;

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
    // $ANTLR end "entryRuleArg"


    // $ANTLR start "ruleArg"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:529:1: ruleArg returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ;
    public final EObject ruleArg() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:532:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:533:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:533:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:533:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:533:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:534:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:534:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:535:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArg1256); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getArgAccess().getNameIDTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getArgRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleArg1273); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArgAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:555:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:556:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:556:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:557:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArgAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleArg1294);
            lv_type_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getArgRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_2_0, 
                      		"Type");
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
    // $ANTLR end "ruleArg"


    // $ANTLR start "entryRuleConstantDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:581:1: entryRuleConstantDefinition returns [EObject current=null] : iv_ruleConstantDefinition= ruleConstantDefinition EOF ;
    public final EObject entryRuleConstantDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:582:2: (iv_ruleConstantDefinition= ruleConstantDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:583:2: iv_ruleConstantDefinition= ruleConstantDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition1330);
            iv_ruleConstantDefinition=ruleConstantDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantDefinition1340); if (state.failed) return current;

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
    // $ANTLR end "entryRuleConstantDefinition"


    // $ANTLR start "ruleConstantDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:590:1: ruleConstantDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleConstantDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:593:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:594:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:594:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:594:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:594:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:595:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:595:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:596:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleConstantDefinition1382); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getConstantDefinitionAccess().getNameIDTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getConstantDefinitionRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleConstantDefinition1399); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getConstantDefinitionAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:616:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:617:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:617:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:618:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleConstantDefinition1420);
            lv_type_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getConstantDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_2_0, 
                      		"Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,39,FOLLOW_39_in_ruleConstantDefinition1432); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getConstantDefinitionAccess().getEqualsSignKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:638:1: ( (lv_expr_4_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:639:1: (lv_expr_4_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:639:1: (lv_expr_4_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:640:3: lv_expr_4_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getExprExprParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleConstantDefinition1453);
            lv_expr_4_0=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getConstantDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_4_0, 
                      		"Expr");
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
    // $ANTLR end "ruleConstantDefinition"


    // $ANTLR start "entryRuleFunctionDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:664:1: entryRuleFunctionDefinition returns [EObject current=null] : iv_ruleFunctionDefinition= ruleFunctionDefinition EOF ;
    public final EObject entryRuleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:665:2: (iv_ruleFunctionDefinition= ruleFunctionDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:666:2: iv_ruleFunctionDefinition= ruleFunctionDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition1489);
            iv_ruleFunctionDefinition=ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionDefinition1499); if (state.failed) return current;

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
    // $ANTLR end "entryRuleFunctionDefinition"


    // $ANTLR start "ruleFunctionDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:673:1: ruleFunctionDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) ;
    public final EObject ruleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_args_2_0 = null;

        EObject lv_args_4_0 = null;

        EObject lv_body_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:676:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:677:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:677:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:677:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:677:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:678:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:678:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:679:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFunctionDefinition1541); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getFunctionDefinitionAccess().getNameIDTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getFunctionDefinitionRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleFunctionDefinition1558); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionDefinitionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:699:1: ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:699:2: ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:699:2: ( (lv_args_2_0= ruleArg ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:700:1: (lv_args_2_0= ruleArg )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:700:1: (lv_args_2_0= ruleArg )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:701:3: lv_args_2_0= ruleArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition1580);
                    lv_args_2_0=ruleArg();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFunctionDefinitionRule());
                      	        }
                             		add(
                             			current, 
                             			"args",
                              		lv_args_2_0, 
                              		"Arg");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:717:2: (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==41) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:717:4: otherlv_3= ',' ( (lv_args_4_0= ruleArg ) )
                    	    {
                    	    otherlv_3=(Token)match(input,41,FOLLOW_41_in_ruleFunctionDefinition1593); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getFunctionDefinitionAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:721:1: ( (lv_args_4_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:722:1: (lv_args_4_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:722:1: (lv_args_4_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:723:3: lv_args_4_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition1614);
                    	    lv_args_4_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getFunctionDefinitionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_4_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,42,FOLLOW_42_in_ruleFunctionDefinition1630); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getFunctionDefinitionAccess().getRightParenthesisKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:743:1: ( (lv_body_6_0= ruleDefinitionBody ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:744:1: (lv_body_6_0= ruleDefinitionBody )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:744:1: (lv_body_6_0= ruleDefinitionBody )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:745:3: lv_body_6_0= ruleDefinitionBody
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getBodyDefinitionBodyParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition1651);
            lv_body_6_0=ruleDefinitionBody();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFunctionDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"body",
                      		lv_body_6_0, 
                      		"DefinitionBody");
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
    // $ANTLR end "ruleFunctionDefinition"


    // $ANTLR start "entryRuleClaimString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:769:1: entryRuleClaimString returns [EObject current=null] : iv_ruleClaimString= ruleClaimString EOF ;
    public final EObject entryRuleClaimString() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClaimString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:770:2: (iv_ruleClaimString= ruleClaimString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:771:2: iv_ruleClaimString= ruleClaimString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClaimStringRule()); 
            }
            pushFollow(FOLLOW_ruleClaimString_in_entryRuleClaimString1687);
            iv_ruleClaimString=ruleClaimString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClaimString; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleClaimString1697); if (state.failed) return current;

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
    // $ANTLR end "entryRuleClaimString"


    // $ANTLR start "ruleClaimString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:778:1: ruleClaimString returns [EObject current=null] : ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) ) ;
    public final EObject ruleClaimString() throws RecognitionException {
        EObject current = null;

        Token lv_str_1_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:781:28: ( ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_STRING) ) {
                alt7=1;
            }
            else if ( (LA7_0==RULE_ID) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:3: () ( (lv_str_1_0= RULE_STRING ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:782:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:783:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimStringAccess().getClaimStringAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:2: ( (lv_str_1_0= RULE_STRING ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:789:1: (lv_str_1_0= RULE_STRING )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:789:1: (lv_str_1_0= RULE_STRING )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:790:3: lv_str_1_0= RULE_STRING
                    {
                    lv_str_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleClaimString1749); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_str_1_0, grammarAccess.getClaimStringAccess().getStrSTRINGTerminalRuleCall_0_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getClaimStringRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"str",
                              		lv_str_1_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:807:6: ( () ( (otherlv_3= RULE_ID ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:807:6: ( () ( (otherlv_3= RULE_ID ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:807:7: () ( (otherlv_3= RULE_ID ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:807:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:808:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimStringAccess().getClaimArgAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:813:2: ( (otherlv_3= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:814:1: (otherlv_3= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:814:1: (otherlv_3= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:815:3: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getClaimStringRule());
                      	        }
                              
                    }
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleClaimString1791); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_3, grammarAccess.getClaimStringAccess().getArgArgCrossReference_1_1_0()); 
                      	
                    }

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
    // $ANTLR end "ruleClaimString"


    // $ANTLR start "entryRuleDefinitionBody"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:834:1: entryRuleDefinitionBody returns [EObject current=null] : iv_ruleDefinitionBody= ruleDefinitionBody EOF ;
    public final EObject entryRuleDefinitionBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinitionBody = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:835:2: (iv_ruleDefinitionBody= ruleDefinitionBody EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:836:2: iv_ruleDefinitionBody= ruleDefinitionBody EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefinitionBodyRule()); 
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody1828);
            iv_ruleDefinitionBody=ruleDefinitionBody();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefinitionBody; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefinitionBody1838); if (state.failed) return current;

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
    // $ANTLR end "entryRuleDefinitionBody"


    // $ANTLR start "ruleDefinitionBody"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:843:1: ruleDefinitionBody returns [EObject current=null] : ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) ) ;
    public final EObject ruleDefinitionBody() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;

        EObject lv_claim_8_0 = null;

        EObject lv_expr_10_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:846:28: ( ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==38) ) {
                alt10=1;
            }
            else if ( (LA10_0==43) ) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:3: () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:848:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getFuncBodyAction_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleDefinitionBody1885); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getDefinitionBodyAccess().getColonKeyword_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:857:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:858:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:858:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:859:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getTypeTypeParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleDefinitionBody1906);
                    lv_type_2_0=ruleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefinitionBodyRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_2_0, 
                              		"Type");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,39,FOLLOW_39_in_ruleDefinitionBody1918); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getDefinitionBodyAccess().getEqualsSignKeyword_0_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:879:1: ( (lv_expr_4_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:880:1: (lv_expr_4_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:880:1: (lv_expr_4_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:881:3: lv_expr_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_0_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody1939);
                    lv_expr_4_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefinitionBodyRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_4_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:7: () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:899:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getClaimBodyAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_6=(Token)match(input,43,FOLLOW_43_in_ruleDefinitionBody1968); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getDefinitionBodyAccess().getLessThanSignEqualsSignKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:908:1: (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==44) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:908:3: otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**'
                            {
                            otherlv_7=(Token)match(input,44,FOLLOW_44_in_ruleDefinitionBody1981); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_7, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:912:1: ( (lv_claim_8_0= ruleClaimString ) )*
                            loop8:
                            do {
                                int alt8=2;
                                int LA8_0 = input.LA(1);

                                if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_STRING)) ) {
                                    alt8=1;
                                }


                                switch (alt8) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:913:1: (lv_claim_8_0= ruleClaimString )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:913:1: (lv_claim_8_0= ruleClaimString )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:914:3: lv_claim_8_0= ruleClaimString
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getClaimClaimStringParserRuleCall_1_2_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleClaimString_in_ruleDefinitionBody2002);
                            	    lv_claim_8_0=ruleClaimString();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getDefinitionBodyRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"claim",
                            	              		lv_claim_8_0, 
                            	              		"ClaimString");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop8;
                                }
                            } while (true);

                            otherlv_9=(Token)match(input,44,FOLLOW_44_in_ruleDefinitionBody2015); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_9, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_2());
                                  
                            }

                            }
                            break;

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:934:3: ( (lv_expr_10_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:935:1: (lv_expr_10_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:935:1: (lv_expr_10_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:936:3: lv_expr_10_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2038);
                    lv_expr_10_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefinitionBodyRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_10_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

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
    // $ANTLR end "ruleDefinitionBody"


    // $ANTLR start "entryRuleExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:960:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:961:2: (iv_ruleExpr= ruleExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:962:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_ruleExpr_in_entryRuleExpr2075);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpr2085); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:969:1: ruleExpr returns [EObject current=null] : this_ImpliesExpr_0= ruleImpliesExpr ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_ImpliesExpr_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:972:28: (this_ImpliesExpr_0= ruleImpliesExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:974:5: this_ImpliesExpr_0= ruleImpliesExpr
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExprAccess().getImpliesExprParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_ruleExpr2131);
            this_ImpliesExpr_0=ruleImpliesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpr_0; 
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
    // $ANTLR end "ruleExpr"


    // $ANTLR start "entryRuleImpliesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:990:1: entryRuleImpliesExpr returns [EObject current=null] : iv_ruleImpliesExpr= ruleImpliesExpr EOF ;
    public final EObject entryRuleImpliesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:991:2: (iv_ruleImpliesExpr= ruleImpliesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:992:2: iv_ruleImpliesExpr= ruleImpliesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExprRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2165);
            iv_ruleImpliesExpr=ruleImpliesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpr2175); if (state.failed) return current;

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
    // $ANTLR end "entryRuleImpliesExpr"


    // $ANTLR start "ruleImpliesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:999:1: ruleImpliesExpr returns [EObject current=null] : (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) ;
    public final EObject ruleImpliesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_OrExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1002:28: ( (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1003:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1003:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1004:5: this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExprAccess().getOrExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpr_in_ruleImpliesExpr2222);
            this_OrExpr_0=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:1: ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==45) ) {
                int LA11_1 = input.LA(2);

                if ( (synpred1_InternalResolute()) ) {
                    alt11=1;
                }
            }
            switch (alt11) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:3: ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1019:6: ( () ( (lv_op_2_0= '=>' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1019:7: () ( (lv_op_2_0= '=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1019:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1020:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1025:2: ( (lv_op_2_0= '=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1026:1: (lv_op_2_0= '=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1026:1: (lv_op_2_0= '=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1027:3: lv_op_2_0= '=>'
                    {
                    lv_op_2_0=(Token)match(input,45,FOLLOW_45_in_ruleImpliesExpr2273); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_op_2_0, grammarAccess.getImpliesExprAccess().getOpEqualsSignGreaterThanSignKeyword_1_0_0_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getImpliesExprRule());
                      	        }
                             		setWithLastConsumed(current, "op", lv_op_2_0, "=>");
                      	    
                    }

                    }


                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1040:4: ( (lv_right_3_0= ruleImpliesExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1041:1: (lv_right_3_0= ruleImpliesExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1041:1: (lv_right_3_0= ruleImpliesExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1042:3: lv_right_3_0= ruleImpliesExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImpliesExprAccess().getRightImpliesExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr2309);
                    lv_right_3_0=ruleImpliesExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImpliesExprRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"ImpliesExpr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


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
    // $ANTLR end "ruleImpliesExpr"


    // $ANTLR start "entryRuleOrExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1066:1: entryRuleOrExpr returns [EObject current=null] : iv_ruleOrExpr= ruleOrExpr EOF ;
    public final EObject entryRuleOrExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1067:2: (iv_ruleOrExpr= ruleOrExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1068:2: iv_ruleOrExpr= ruleOrExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExprRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpr_in_entryRuleOrExpr2347);
            iv_ruleOrExpr=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpr2357); if (state.failed) return current;

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
    // $ANTLR end "entryRuleOrExpr"


    // $ANTLR start "ruleOrExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1075:1: ruleOrExpr returns [EObject current=null] : (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) ;
    public final EObject ruleOrExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_AndExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1078:28: ( (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1079:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1079:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1080:5: this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExprAccess().getAndExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr2404);
            this_AndExpr_0=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:1: ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==46) ) {
                    int LA12_2 = input.LA(2);

                    if ( (synpred2_InternalResolute()) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:3: ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1095:6: ( () ( (lv_op_2_0= 'or' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1095:7: () ( (lv_op_2_0= 'or' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1095:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1096:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1101:2: ( (lv_op_2_0= 'or' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1102:1: (lv_op_2_0= 'or' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1102:1: (lv_op_2_0= 'or' )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1103:3: lv_op_2_0= 'or'
            	    {
            	    lv_op_2_0=(Token)match(input,46,FOLLOW_46_in_ruleOrExpr2455); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_op_2_0, grammarAccess.getOrExprAccess().getOpOrKeyword_1_0_0_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getOrExprRule());
            	      	        }
            	             		setWithLastConsumed(current, "op", lv_op_2_0, "or");
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1116:4: ( (lv_right_3_0= ruleAndExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1117:1: (lv_right_3_0= ruleAndExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1117:1: (lv_right_3_0= ruleAndExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1118:3: lv_right_3_0= ruleAndExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExprAccess().getRightAndExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr2491);
            	    lv_right_3_0=ruleAndExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOrExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"AndExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
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
    // $ANTLR end "ruleOrExpr"


    // $ANTLR start "entryRuleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1142:1: entryRuleAndExpr returns [EObject current=null] : iv_ruleAndExpr= ruleAndExpr EOF ;
    public final EObject entryRuleAndExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1143:2: (iv_ruleAndExpr= ruleAndExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1144:2: iv_ruleAndExpr= ruleAndExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExprRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpr_in_entryRuleAndExpr2529);
            iv_ruleAndExpr=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpr2539); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAndExpr"


    // $ANTLR start "ruleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1151:1: ruleAndExpr returns [EObject current=null] : (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* ) ;
    public final EObject ruleAndExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_RelationalExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1154:28: ( (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1155:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1155:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1156:5: this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExprAccess().getRelationalExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr2586);
            this_RelationalExpr_0=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:1: ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==47) ) {
                    int LA13_2 = input.LA(2);

                    if ( (synpred3_InternalResolute()) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:2: ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:2: ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:3: ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1171:6: ( () ( (lv_op_2_0= 'and' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1171:7: () ( (lv_op_2_0= 'and' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1171:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1172:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1177:2: ( (lv_op_2_0= 'and' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1178:1: (lv_op_2_0= 'and' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1178:1: (lv_op_2_0= 'and' )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1179:3: lv_op_2_0= 'and'
            	    {
            	    lv_op_2_0=(Token)match(input,47,FOLLOW_47_in_ruleAndExpr2637); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_op_2_0, grammarAccess.getAndExprAccess().getOpAndKeyword_1_0_0_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getAndExprRule());
            	      	        }
            	             		setWithLastConsumed(current, "op", lv_op_2_0, "and");
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1192:4: ( (lv_right_3_0= ruleRelationalExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1193:1: (lv_right_3_0= ruleRelationalExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1193:1: (lv_right_3_0= ruleRelationalExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1194:3: lv_right_3_0= ruleRelationalExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExprAccess().getRightRelationalExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr2673);
            	    lv_right_3_0=ruleRelationalExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getAndExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"RelationalExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // $ANTLR end "ruleAndExpr"


    // $ANTLR start "entryRuleRelationalOp"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1218:1: entryRuleRelationalOp returns [String current=null] : iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1219:2: (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1220:2: iv_ruleRelationalOp= ruleRelationalOp EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalOpRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp2712);
            iv_ruleRelationalOp=ruleRelationalOp();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalOp.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalOp2723); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRelationalOp"


    // $ANTLR start "ruleRelationalOp"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1227:1: ruleRelationalOp returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1230:28: ( (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1231:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1231:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' )
            int alt14=7;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt14=1;
                }
                break;
            case 43:
                {
                alt14=2;
                }
                break;
            case 49:
                {
                alt14=3;
                }
                break;
            case 50:
                {
                alt14=4;
                }
                break;
            case 39:
                {
                alt14=5;
                }
                break;
            case 51:
                {
                alt14=6;
                }
                break;
            case 52:
                {
                alt14=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1232:2: kw= '<'
                    {
                    kw=(Token)match(input,48,FOLLOW_48_in_ruleRelationalOp2761); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1239:2: kw= '<='
                    {
                    kw=(Token)match(input,43,FOLLOW_43_in_ruleRelationalOp2780); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignEqualsSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1246:2: kw= '>'
                    {
                    kw=(Token)match(input,49,FOLLOW_49_in_ruleRelationalOp2799); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1253:2: kw= '>='
                    {
                    kw=(Token)match(input,50,FOLLOW_50_in_ruleRelationalOp2818); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignEqualsSignKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1260:2: kw= '='
                    {
                    kw=(Token)match(input,39,FOLLOW_39_in_ruleRelationalOp2837); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getEqualsSignKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1267:2: kw= '<>'
                    {
                    kw=(Token)match(input,51,FOLLOW_51_in_ruleRelationalOp2856); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignGreaterThanSignKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1274:2: kw= 'in'
                    {
                    kw=(Token)match(input,52,FOLLOW_52_in_ruleRelationalOp2875); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getInKeyword_6()); 
                          
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
    // $ANTLR end "ruleRelationalOp"


    // $ANTLR start "entryRuleRelationalExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1287:1: entryRuleRelationalExpr returns [EObject current=null] : iv_ruleRelationalExpr= ruleRelationalExpr EOF ;
    public final EObject entryRuleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1288:2: (iv_ruleRelationalExpr= ruleRelationalExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1289:2: iv_ruleRelationalExpr= ruleRelationalExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExprRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr2915);
            iv_ruleRelationalExpr=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpr2925); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRelationalExpr"


    // $ANTLR start "ruleRelationalExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1296:1: ruleRelationalExpr returns [EObject current=null] : (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) ;
    public final EObject ruleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject this_PlusExpr_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1299:28: ( (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1300:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1300:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1301:5: this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExprAccess().getPlusExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr2972);
            this_PlusExpr_0=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PlusExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            int alt15=2;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:3: ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1314:6: ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1314:7: () ( (lv_op_2_0= ruleRelationalOp ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1314:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1315:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1320:2: ( (lv_op_2_0= ruleRelationalOp ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1321:1: (lv_op_2_0= ruleRelationalOp )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1321:1: (lv_op_2_0= ruleRelationalOp )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1322:3: lv_op_2_0= ruleRelationalOp
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getOpRelationalOpParserRuleCall_1_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3021);
                    lv_op_2_0=ruleRelationalOp();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getRelationalExprRule());
                      	        }
                             		set(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"RelationalOp");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1338:4: ( (lv_right_3_0= rulePlusExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1339:1: (lv_right_3_0= rulePlusExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1339:1: (lv_right_3_0= rulePlusExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1340:3: lv_right_3_0= rulePlusExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getRightPlusExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr3044);
                    lv_right_3_0=rulePlusExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getRelationalExprRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"PlusExpr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


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
    // $ANTLR end "ruleRelationalExpr"


    // $ANTLR start "entryRulePlusExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1364:1: entryRulePlusExpr returns [EObject current=null] : iv_rulePlusExpr= rulePlusExpr EOF ;
    public final EObject entryRulePlusExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1365:2: (iv_rulePlusExpr= rulePlusExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1366:2: iv_rulePlusExpr= rulePlusExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusExprRule()); 
            }
            pushFollow(FOLLOW_rulePlusExpr_in_entryRulePlusExpr3082);
            iv_rulePlusExpr=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusExpr3092); if (state.failed) return current;

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
    // $ANTLR end "entryRulePlusExpr"


    // $ANTLR start "rulePlusExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1373:1: rulePlusExpr returns [EObject current=null] : (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) ;
    public final EObject rulePlusExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_TimesExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1376:28: ( (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1377:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1377:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1378:5: this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getPlusExprAccess().getTimesExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr3139);
            this_TimesExpr_0=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_TimesExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:1: ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==53) ) {
                    int LA17_2 = input.LA(2);

                    if ( (synpred5_InternalResolute()) ) {
                        alt17=1;
                    }


                }
                else if ( (LA17_0==54) ) {
                    int LA17_3 = input.LA(2);

                    if ( (synpred5_InternalResolute()) ) {
                        alt17=1;
                    }


                }


                switch (alt17) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1399:6: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1399:7: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1399:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1405:2: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1406:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1406:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1407:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1407:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==53) ) {
            	        alt16=1;
            	    }
            	    else if ( (LA16_0==54) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1408:3: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,53,FOLLOW_53_in_rulePlusExpr3211); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getPlusExprAccess().getOpPlusSignKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getPlusExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1420:8: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,54,FOLLOW_54_in_rulePlusExpr3240); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getPlusExprAccess().getOpHyphenMinusKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getPlusExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1435:4: ( (lv_right_3_0= ruleTimesExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1436:1: (lv_right_3_0= ruleTimesExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1436:1: (lv_right_3_0= ruleTimesExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1437:3: lv_right_3_0= ruleTimesExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getPlusExprAccess().getRightTimesExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr3279);
            	    lv_right_3_0=ruleTimesExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPlusExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"TimesExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
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
    // $ANTLR end "rulePlusExpr"


    // $ANTLR start "entryRuleTimesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1461:1: entryRuleTimesExpr returns [EObject current=null] : iv_ruleTimesExpr= ruleTimesExpr EOF ;
    public final EObject entryRuleTimesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1462:2: (iv_ruleTimesExpr= ruleTimesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1463:2: iv_ruleTimesExpr= ruleTimesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTimesExprRule()); 
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr3317);
            iv_ruleTimesExpr=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTimesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTimesExpr3327); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTimesExpr"


    // $ANTLR start "ruleTimesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1470:1: ruleTimesExpr returns [EObject current=null] : (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* ) ;
    public final EObject ruleTimesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_SetOpExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1473:28: ( (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1474:1: (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1474:1: (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1475:5: this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTimesExprAccess().getSetOpExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3374);
            this_SetOpExpr_0=ruleSetOpExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_SetOpExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:1: ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==55) ) {
                    int LA19_2 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }


                }
                else if ( (LA19_0==56) ) {
                    int LA19_3 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }


                }


                switch (alt19) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:2: ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:2: ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:3: ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1496:6: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1496:7: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1496:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1497:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1502:2: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1503:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1503:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1504:1: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1504:1: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt18=2;
            	    int LA18_0 = input.LA(1);

            	    if ( (LA18_0==55) ) {
            	        alt18=1;
            	    }
            	    else if ( (LA18_0==56) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1505:3: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,55,FOLLOW_55_in_ruleTimesExpr3446); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getTimesExprAccess().getOpAsteriskKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getTimesExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1517:8: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,56,FOLLOW_56_in_ruleTimesExpr3475); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getTimesExprAccess().getOpSolidusKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getTimesExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1532:4: ( (lv_right_3_0= ruleSetOpExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1533:1: (lv_right_3_0= ruleSetOpExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1533:1: (lv_right_3_0= ruleSetOpExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1534:3: lv_right_3_0= ruleSetOpExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTimesExprAccess().getRightSetOpExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3514);
            	    lv_right_3_0=ruleSetOpExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTimesExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"SetOpExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // $ANTLR end "ruleTimesExpr"


    // $ANTLR start "entryRuleSetOpExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1558:1: entryRuleSetOpExpr returns [EObject current=null] : iv_ruleSetOpExpr= ruleSetOpExpr EOF ;
    public final EObject entryRuleSetOpExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetOpExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1559:2: (iv_ruleSetOpExpr= ruleSetOpExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1560:2: iv_ruleSetOpExpr= ruleSetOpExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSetOpExprRule()); 
            }
            pushFollow(FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr3552);
            iv_ruleSetOpExpr=ruleSetOpExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSetOpExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSetOpExpr3562); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSetOpExpr"


    // $ANTLR start "ruleSetOpExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1567:1: ruleSetOpExpr returns [EObject current=null] : (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) ;
    public final EObject ruleSetOpExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_PrefixExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1570:28: ( (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1571:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1571:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1572:5: this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getSetOpExprAccess().getPrefixExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3609);
            this_PrefixExpr_0=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrefixExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:1: ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==57) ) {
                    int LA21_2 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt21=1;
                    }


                }
                else if ( (LA21_0==58) ) {
                    int LA21_3 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt21=1;
                    }


                }


                switch (alt21) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:2: ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:2: ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:3: ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1593:6: ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1593:7: () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1593:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1594:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1599:2: ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1600:1: ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1600:1: ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1601:1: (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1601:1: (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' )
            	    int alt20=2;
            	    int LA20_0 = input.LA(1);

            	    if ( (LA20_0==57) ) {
            	        alt20=1;
            	    }
            	    else if ( (LA20_0==58) ) {
            	        alt20=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 20, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt20) {
            	        case 1 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1602:3: lv_op_2_1= 'union'
            	            {
            	            lv_op_2_1=(Token)match(input,57,FOLLOW_57_in_ruleSetOpExpr3681); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_1, grammarAccess.getSetOpExprAccess().getOpUnionKeyword_1_0_0_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getSetOpExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1614:8: lv_op_2_2= 'intersect'
            	            {
            	            lv_op_2_2=(Token)match(input,58,FOLLOW_58_in_ruleSetOpExpr3710); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_2, grammarAccess.getSetOpExprAccess().getOpIntersectKeyword_1_0_0_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getSetOpExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1629:4: ( (lv_right_3_0= rulePrefixExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1630:1: (lv_right_3_0= rulePrefixExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1630:1: (lv_right_3_0= rulePrefixExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1631:3: lv_right_3_0= rulePrefixExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSetOpExprAccess().getRightPrefixExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3749);
            	    lv_right_3_0=rulePrefixExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getSetOpExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"PrefixExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // $ANTLR end "ruleSetOpExpr"


    // $ANTLR start "entryRulePrefixExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1655:1: entryRulePrefixExpr returns [EObject current=null] : iv_rulePrefixExpr= rulePrefixExpr EOF ;
    public final EObject entryRulePrefixExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1656:2: (iv_rulePrefixExpr= rulePrefixExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1657:2: iv_rulePrefixExpr= rulePrefixExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrefixExprRule()); 
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr3787);
            iv_rulePrefixExpr=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrefixExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrefixExpr3797); if (state.failed) return current;

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
    // $ANTLR end "entryRulePrefixExpr"


    // $ANTLR start "rulePrefixExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1664:1: rulePrefixExpr returns [EObject current=null] : ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr ) ;
    public final EObject rulePrefixExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_expr_2_0 = null;

        EObject this_AtomicExpr_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1667:28: ( ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==54||LA23_0==59) ) {
                alt23=1;
            }
            else if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_INTEGER_LIT)||LA23_0==16||LA23_0==40||LA23_0==60||(LA23_0>=62 && LA23_0<=63)||(LA23_0>=66 && LA23_0<=67)||(LA23_0>=70 && LA23_0<=87)||(LA23_0>=96 && LA23_0<=97)) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:3: () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1668:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1669:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixExprAccess().getUnaryExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1674:2: ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1675:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1675:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1676:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1676:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==54) ) {
                        alt22=1;
                    }
                    else if ( (LA22_0==59) ) {
                        alt22=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 0, input);

                        throw nvae;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1677:3: lv_op_1_1= '-'
                            {
                            lv_op_1_1=(Token)match(input,54,FOLLOW_54_in_rulePrefixExpr3852); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_op_1_1, grammarAccess.getPrefixExprAccess().getOpHyphenMinusKeyword_0_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getPrefixExprRule());
                              	        }
                                     		setWithLastConsumed(current, "op", lv_op_1_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1689:8: lv_op_1_2= 'not'
                            {
                            lv_op_1_2=(Token)match(input,59,FOLLOW_59_in_rulePrefixExpr3881); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_op_1_2, grammarAccess.getPrefixExprAccess().getOpNotKeyword_0_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getPrefixExprRule());
                              	        }
                                     		setWithLastConsumed(current, "op", lv_op_1_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1704:2: ( (lv_expr_2_0= rulePrefixExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1705:1: (lv_expr_2_0= rulePrefixExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1705:1: (lv_expr_2_0= rulePrefixExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1706:3: lv_expr_2_0= rulePrefixExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrefixExprAccess().getExprPrefixExprParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePrefixExpr_in_rulePrefixExpr3918);
                    lv_expr_2_0=rulePrefixExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_2_0, 
                              		"PrefixExpr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1724:5: this_AtomicExpr_3= ruleAtomicExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrefixExprAccess().getAtomicExprParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleAtomicExpr_in_rulePrefixExpr3947);
                    this_AtomicExpr_3=ruleAtomicExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_AtomicExpr_3; 
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
    // $ANTLR end "rulePrefixExpr"


    // $ANTLR start "entryRuleAtomicExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1740:1: entryRuleAtomicExpr returns [EObject current=null] : iv_ruleAtomicExpr= ruleAtomicExpr EOF ;
    public final EObject entryRuleAtomicExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1741:2: (iv_ruleAtomicExpr= ruleAtomicExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1742:2: iv_ruleAtomicExpr= ruleAtomicExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAtomicExprRule()); 
            }
            pushFollow(FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr3982);
            iv_ruleAtomicExpr=ruleAtomicExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAtomicExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomicExpr3992); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAtomicExpr"


    // $ANTLR start "ruleAtomicExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1749:1: ruleAtomicExpr returns [EObject current=null] : ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? ) | ( () ( (lv_val_10_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_12_0= ruleRealTerm ) ) ) | ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_16_0= ruleStringTerm ) ) ) | ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) ) | ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' ) | ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' ) | ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' ) | (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' ) ) ;
    public final EObject ruleAtomicExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token lv_quant_25_1=null;
        Token lv_quant_25_2=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        Token otherlv_33=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_39=null;
        Token otherlv_40=null;
        Token otherlv_42=null;
        Token otherlv_44=null;
        Token otherlv_46=null;
        Token otherlv_48=null;
        Token otherlv_49=null;
        Token otherlv_51=null;
        Token otherlv_52=null;
        Token otherlv_54=null;
        Token otherlv_55=null;
        Token otherlv_57=null;
        EObject lv_sub_5_0 = null;

        EObject lv_val_8_0 = null;

        EObject lv_val_10_0 = null;

        EObject lv_val_12_0 = null;

        EObject lv_val_14_0 = null;

        EObject lv_val_16_0 = null;

        EObject lv_cond_19_0 = null;

        EObject lv_then_21_0 = null;

        EObject lv_else_23_0 = null;

        EObject lv_args_27_0 = null;

        EObject lv_expr_30_0 = null;

        AntlrDatatypeRuleToken lv_fn_32_0 = null;

        EObject lv_args_34_0 = null;

        EObject lv_args_36_0 = null;

        EObject lv_args_41_0 = null;

        EObject lv_args_43_0 = null;

        EObject lv_map_47_0 = null;

        EObject lv_args_50_0 = null;

        EObject lv_filter_53_0 = null;

        EObject this_Expr_56 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1752:28: ( ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? ) | ( () ( (lv_val_10_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_12_0= ruleRealTerm ) ) ) | ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_16_0= ruleStringTerm ) ) ) | ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) ) | ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' ) | ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' ) | ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' ) | (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? ) | ( () ( (lv_val_10_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_12_0= ruleRealTerm ) ) ) | ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_16_0= ruleStringTerm ) ) ) | ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) ) | ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' ) | ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' ) | ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' ) | (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? ) | ( () ( (lv_val_10_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_12_0= ruleRealTerm ) ) ) | ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_16_0= ruleStringTerm ) ) ) | ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) ) | ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' ) | ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' ) | ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' ) | (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' ) )
            int alt34=13;
            alt34 = dfa34.predict(input);
            switch (alt34) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:2: ( () ( ( ruleQCREF ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:2: ( () ( ( ruleQCREF ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:3: () ( ( ruleQCREF ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1754:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIdExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1759:2: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1760:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1760:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1761:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getIdNamedElementCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleAtomicExpr4050);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:6: ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:6: ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:7: () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1776:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getThisExprAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_3=(Token)match(input,60,FOLLOW_60_in_ruleAtomicExpr4079); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getAtomicExprAccess().getThisKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1785:1: (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==61) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1785:3: otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) )
                            {
                            otherlv_4=(Token)match(input,61,FOLLOW_61_in_ruleAtomicExpr4092); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getAtomicExprAccess().getFullStopKeyword_1_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1789:1: ( (lv_sub_5_0= ruleNestedDotID ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1790:1: (lv_sub_5_0= ruleNestedDotID )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1790:1: (lv_sub_5_0= ruleNestedDotID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1791:3: lv_sub_5_0= ruleNestedDotID
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getSubNestedDotIDParserRuleCall_1_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleNestedDotID_in_ruleAtomicExpr4113);
                            lv_sub_5_0=ruleNestedDotID();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"sub",
                                      		lv_sub_5_0, 
                                      		"NestedDotID");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:6: ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:6: ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:7: () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1809:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFailExprAction_2_0(),
                                  current);
                          
                    }

                    }

                    otherlv_7=(Token)match(input,62,FOLLOW_62_in_ruleAtomicExpr4144); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getAtomicExprAccess().getFailKeyword_2_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1818:1: ( (lv_val_8_0= ruleStringTerm ) )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==RULE_STRING) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1819:1: (lv_val_8_0= ruleStringTerm )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1819:1: (lv_val_8_0= ruleStringTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1820:3: lv_val_8_0= ruleStringTerm
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_2_2_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4165);
                            lv_val_8_0=ruleStringTerm();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"val",
                                      		lv_val_8_0, 
                                      		"StringTerm");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1837:6: ( () ( (lv_val_10_0= ruleIntegerTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1837:6: ( () ( (lv_val_10_0= ruleIntegerTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1837:7: () ( (lv_val_10_0= ruleIntegerTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1837:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1838:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIntExprAction_3_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1843:2: ( (lv_val_10_0= ruleIntegerTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1844:1: (lv_val_10_0= ruleIntegerTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1844:1: (lv_val_10_0= ruleIntegerTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1845:3: lv_val_10_0= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValIntegerTermParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4204);
                    lv_val_10_0=ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_10_0, 
                              		"IntegerTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1862:6: ( () ( (lv_val_12_0= ruleRealTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1862:6: ( () ( (lv_val_12_0= ruleRealTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1862:7: () ( (lv_val_12_0= ruleRealTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1862:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1863:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getRealExprAction_4_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1868:2: ( (lv_val_12_0= ruleRealTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1869:1: (lv_val_12_0= ruleRealTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1869:1: (lv_val_12_0= ruleRealTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1870:3: lv_val_12_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValRealTermParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleAtomicExpr4242);
                    lv_val_12_0=ruleRealTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_12_0, 
                              		"RealTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1887:6: ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1887:6: ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1887:7: () ( (lv_val_14_0= ruleBooleanLiteral ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1887:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1888:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBoolExprAction_5_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1893:2: ( (lv_val_14_0= ruleBooleanLiteral ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1894:1: (lv_val_14_0= ruleBooleanLiteral )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1894:1: (lv_val_14_0= ruleBooleanLiteral )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1895:3: lv_val_14_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValBooleanLiteralParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4280);
                    lv_val_14_0=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_14_0, 
                              		"BooleanLiteral");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:6: ( () ( (lv_val_16_0= ruleStringTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:6: ( () ( (lv_val_16_0= ruleStringTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:7: () ( (lv_val_16_0= ruleStringTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getStringExprAction_6_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1918:2: ( (lv_val_16_0= ruleStringTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1919:1: (lv_val_16_0= ruleStringTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1919:1: (lv_val_16_0= ruleStringTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1920:3: lv_val_16_0= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4318);
                    lv_val_16_0=ruleStringTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_16_0, 
                              		"StringTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1937:6: ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1937:6: ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1937:7: () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1937:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1938:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIfThenElseExprAction_7_0(),
                                  current);
                          
                    }

                    }

                    otherlv_18=(Token)match(input,63,FOLLOW_63_in_ruleAtomicExpr4347); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_18, grammarAccess.getAtomicExprAccess().getIfKeyword_7_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1947:1: ( (lv_cond_19_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1948:1: (lv_cond_19_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1948:1: (lv_cond_19_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:3: lv_cond_19_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getCondExprParserRuleCall_7_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4368);
                    lv_cond_19_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"cond",
                              		lv_cond_19_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_20=(Token)match(input,64,FOLLOW_64_in_ruleAtomicExpr4380); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getAtomicExprAccess().getThenKeyword_7_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1969:1: ( (lv_then_21_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1970:1: (lv_then_21_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1970:1: (lv_then_21_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1971:3: lv_then_21_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getThenExprParserRuleCall_7_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4401);
                    lv_then_21_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"then",
                              		lv_then_21_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_22=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr4413); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_22, grammarAccess.getAtomicExprAccess().getElseKeyword_7_5());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1991:1: ( (lv_else_23_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1992:1: (lv_else_23_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1992:1: (lv_else_23_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1993:3: lv_else_23_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getElseExprParserRuleCall_7_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4434);
                    lv_else_23_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"else",
                              		lv_else_23_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2010:6: ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2010:6: ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2010:7: () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2010:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2011:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getQuantifiedExprAction_8_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2016:2: ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2017:1: ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2017:1: ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2018:1: (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2018:1: (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' )
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==66) ) {
                        alt26=1;
                    }
                    else if ( (LA26_0==67) ) {
                        alt26=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2019:3: lv_quant_25_1= 'forall'
                            {
                            lv_quant_25_1=(Token)match(input,66,FOLLOW_66_in_ruleAtomicExpr4471); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_25_1, grammarAccess.getAtomicExprAccess().getQuantForallKeyword_8_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_25_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2031:8: lv_quant_25_2= 'exists'
                            {
                            lv_quant_25_2=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr4500); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_25_2, grammarAccess.getAtomicExprAccess().getQuantExistsKeyword_8_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_25_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2046:2: (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==40) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2046:4: otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')'
                    	    {
                    	    otherlv_26=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4529); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_26, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_8_2_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2050:1: ( (lv_args_27_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2051:1: (lv_args_27_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2051:1: (lv_args_27_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:3: lv_args_27_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_8_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4550);
                    	    lv_args_27_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_27_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_28=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4562); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_28, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_8_2_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);

                    otherlv_29=(Token)match(input,61,FOLLOW_61_in_ruleAtomicExpr4576); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_29, grammarAccess.getAtomicExprAccess().getFullStopKeyword_8_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2076:1: ( (lv_expr_30_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2077:1: (lv_expr_30_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2077:1: (lv_expr_30_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2078:3: lv_expr_30_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_8_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4597);
                    lv_expr_30_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_30_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2095:6: ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2095:6: ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2095:7: () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2095:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2096:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBuiltInFuncCallExprAction_9_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2101:2: ( (lv_fn_32_0= ruleBuiltInFunc ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2102:1: (lv_fn_32_0= ruleBuiltInFunc )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2102:1: (lv_fn_32_0= ruleBuiltInFunc )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2103:3: lv_fn_32_0= ruleBuiltInFunc
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFnBuiltInFuncParserRuleCall_9_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4635);
                    lv_fn_32_0=ruleBuiltInFunc();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"fn",
                              		lv_fn_32_0, 
                              		"BuiltInFunc");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_33=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4647); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_33, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_9_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:1: ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( ((LA29_0>=RULE_ID && LA29_0<=RULE_INTEGER_LIT)||LA29_0==16||LA29_0==40||LA29_0==54||(LA29_0>=59 && LA29_0<=60)||(LA29_0>=62 && LA29_0<=63)||(LA29_0>=66 && LA29_0<=67)||(LA29_0>=70 && LA29_0<=87)||(LA29_0>=96 && LA29_0<=97)) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:2: ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:2: ( (lv_args_34_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_args_34_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_args_34_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2125:3: lv_args_34_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4669);
                            lv_args_34_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_34_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2141:2: (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )*
                            loop28:
                            do {
                                int alt28=2;
                                int LA28_0 = input.LA(1);

                                if ( (LA28_0==41) ) {
                                    alt28=1;
                                }


                                switch (alt28) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2141:4: otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) )
                            	    {
                            	    otherlv_35=(Token)match(input,41,FOLLOW_41_in_ruleAtomicExpr4682); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_35, grammarAccess.getAtomicExprAccess().getCommaKeyword_9_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2145:1: ( (lv_args_36_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:1: (lv_args_36_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:1: (lv_args_36_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2147:3: lv_args_36_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4703);
                            	    lv_args_36_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_36_0, 
                            	              		"Expr");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop28;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_37=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4719); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_37, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_9_4());
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:6: ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:6: ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:7: () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2169:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFnCallExprAction_10_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2174:2: ( (otherlv_39= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2175:1: (otherlv_39= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2175:1: (otherlv_39= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2176:3: otherlv_39= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    otherlv_39=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAtomicExpr4756); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_39, grammarAccess.getAtomicExprAccess().getFnFunctionDefinitionCrossReference_10_1_0()); 
                      	
                    }

                    }


                    }

                    otherlv_40=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4768); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_40, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_10_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2191:1: ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_INTEGER_LIT)||LA31_0==16||LA31_0==40||LA31_0==54||(LA31_0>=59 && LA31_0<=60)||(LA31_0>=62 && LA31_0<=63)||(LA31_0>=66 && LA31_0<=67)||(LA31_0>=70 && LA31_0<=87)||(LA31_0>=96 && LA31_0<=97)) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2191:2: ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2191:2: ( (lv_args_41_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2192:1: (lv_args_41_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2192:1: (lv_args_41_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2193:3: lv_args_41_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4790);
                            lv_args_41_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_41_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2209:2: (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==41) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2209:4: otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) )
                            	    {
                            	    otherlv_42=(Token)match(input,41,FOLLOW_41_in_ruleAtomicExpr4803); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_42, grammarAccess.getAtomicExprAccess().getCommaKeyword_10_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2213:1: ( (lv_args_43_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:1: (lv_args_43_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:1: (lv_args_43_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2215:3: lv_args_43_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4824);
                            	    lv_args_43_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_43_0, 
                            	              		"Expr");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop30;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_44=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4840); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_44, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_10_4());
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2236:6: ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2236:6: ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2236:7: () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2236:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2237:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFilterMapExprAction_11_0(),
                                  current);
                          
                    }

                    }

                    otherlv_46=(Token)match(input,16,FOLLOW_16_in_ruleAtomicExpr4869); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_46, grammarAccess.getAtomicExprAccess().getLeftCurlyBracketKeyword_11_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:1: ( (lv_map_47_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2247:1: (lv_map_47_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2247:1: (lv_map_47_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2248:3: lv_map_47_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getMapExprParserRuleCall_11_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4890);
                    lv_map_47_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"map",
                              		lv_map_47_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_48=(Token)match(input,68,FOLLOW_68_in_ruleAtomicExpr4902); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_48, grammarAccess.getAtomicExprAccess().getForKeyword_11_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2268:1: (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==40) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2268:3: otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')'
                    	    {
                    	    otherlv_49=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4915); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_49, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_11_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:1: ( (lv_args_50_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2273:1: (lv_args_50_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2273:1: (lv_args_50_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2274:3: lv_args_50_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_11_4_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4936);
                    	    lv_args_50_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_50_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_51=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4948); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_51, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_11_4_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt32 >= 1 ) break loop32;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(32, input);
                                throw eee;
                        }
                        cnt32++;
                    } while (true);

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2294:3: (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==69) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2294:5: otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) )
                            {
                            otherlv_52=(Token)match(input,69,FOLLOW_69_in_ruleAtomicExpr4963); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_52, grammarAccess.getAtomicExprAccess().getVerticalLineKeyword_11_5_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2298:1: ( (lv_filter_53_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:1: (lv_filter_53_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:1: (lv_filter_53_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2300:3: lv_filter_53_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFilterExprParserRuleCall_11_5_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4984);
                            lv_filter_53_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"filter",
                                      		lv_filter_53_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_54=(Token)match(input,17,FOLLOW_17_in_ruleAtomicExpr4998); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_54, grammarAccess.getAtomicExprAccess().getRightCurlyBracketKeyword_11_6());
                          
                    }

                    }


                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2321:6: (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2321:6: (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2321:8: otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')'
                    {
                    otherlv_55=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr5018); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_55, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_12_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAtomicExprAccess().getExprParserRuleCall_12_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5040);
                    this_Expr_56=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Expr_56; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_57=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr5051); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_57, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_12_2());
                          
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
    // $ANTLR end "ruleAtomicExpr"


    // $ANTLR start "entryRuleBuiltInFunc"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2346:1: entryRuleBuiltInFunc returns [String current=null] : iv_ruleBuiltInFunc= ruleBuiltInFunc EOF ;
    public final String entryRuleBuiltInFunc() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltInFunc = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2347:2: (iv_ruleBuiltInFunc= ruleBuiltInFunc EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2348:2: iv_ruleBuiltInFunc= ruleBuiltInFunc EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltInFuncRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5089);
            iv_ruleBuiltInFunc=ruleBuiltInFunc();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltInFunc.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltInFunc5100); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBuiltInFunc"


    // $ANTLR start "ruleBuiltInFunc"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2355:1: ruleBuiltInFunc returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' ) ;
    public final AntlrDatatypeRuleToken ruleBuiltInFunc() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2358:28: ( (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2359:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2359:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' )
            int alt35=18;
            switch ( input.LA(1) ) {
            case 70:
                {
                alt35=1;
                }
                break;
            case 71:
                {
                alt35=2;
                }
                break;
            case 72:
                {
                alt35=3;
                }
                break;
            case 73:
                {
                alt35=4;
                }
                break;
            case 74:
                {
                alt35=5;
                }
                break;
            case 75:
                {
                alt35=6;
                }
                break;
            case 76:
                {
                alt35=7;
                }
                break;
            case 77:
                {
                alt35=8;
                }
                break;
            case 78:
                {
                alt35=9;
                }
                break;
            case 79:
                {
                alt35=10;
                }
                break;
            case 80:
                {
                alt35=11;
                }
                break;
            case 81:
                {
                alt35=12;
                }
                break;
            case 82:
                {
                alt35=13;
                }
                break;
            case 83:
                {
                alt35=14;
                }
                break;
            case 84:
                {
                alt35=15;
                }
                break;
            case 85:
                {
                alt35=16;
                }
                break;
            case 86:
                {
                alt35=17;
                }
                break;
            case 87:
                {
                alt35=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2360:2: kw= 'connected'
                    {
                    kw=(Token)match(input,70,FOLLOW_70_in_ruleBuiltInFunc5138); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConnectedKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2367:2: kw= 'property_lookup'
                    {
                    kw=(Token)match(input,71,FOLLOW_71_in_ruleBuiltInFunc5157); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_lookupKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2374:2: kw= 'class_of'
                    {
                    kw=(Token)match(input,72,FOLLOW_72_in_ruleBuiltInFunc5176); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getClass_ofKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2381:2: kw= 'type'
                    {
                    kw=(Token)match(input,73,FOLLOW_73_in_ruleBuiltInFunc5195); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getTypeKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2388:2: kw= 'has_type'
                    {
                    kw=(Token)match(input,74,FOLLOW_74_in_ruleBuiltInFunc5214); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getHas_typeKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2395:2: kw= 'bound'
                    {
                    kw=(Token)match(input,75,FOLLOW_75_in_ruleBuiltInFunc5233); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getBoundKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2402:2: kw= 'contained'
                    {
                    kw=(Token)match(input,76,FOLLOW_76_in_ruleBuiltInFunc5252); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getContainedKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2409:2: kw= 'conn_source'
                    {
                    kw=(Token)match(input,77,FOLLOW_77_in_ruleBuiltInFunc5271); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_sourceKeyword_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2416:2: kw= 'conn_dest'
                    {
                    kw=(Token)match(input,78,FOLLOW_78_in_ruleBuiltInFunc5290); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_destKeyword_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2423:2: kw= 'property_exists'
                    {
                    kw=(Token)match(input,79,FOLLOW_79_in_ruleBuiltInFunc5309); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_existsKeyword_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2430:2: kw= 'sum'
                    {
                    kw=(Token)match(input,80,FOLLOW_80_in_ruleBuiltInFunc5328); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSumKeyword_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2437:2: kw= 'analysis'
                    {
                    kw=(Token)match(input,81,FOLLOW_81_in_ruleBuiltInFunc5347); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getAnalysisKeyword_11()); 
                          
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2444:2: kw= 'upper_bound'
                    {
                    kw=(Token)match(input,82,FOLLOW_82_in_ruleBuiltInFunc5366); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getUpper_boundKeyword_12()); 
                          
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2451:2: kw= 'lower_bound'
                    {
                    kw=(Token)match(input,83,FOLLOW_83_in_ruleBuiltInFunc5385); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getLower_boundKeyword_13()); 
                          
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2458:2: kw= 'receive_error'
                    {
                    kw=(Token)match(input,84,FOLLOW_84_in_ruleBuiltInFunc5404); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getReceive_errorKeyword_14()); 
                          
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2465:2: kw= 'contain_error'
                    {
                    kw=(Token)match(input,85,FOLLOW_85_in_ruleBuiltInFunc5423); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getContain_errorKeyword_15()); 
                          
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2472:2: kw= 'propagate_error'
                    {
                    kw=(Token)match(input,86,FOLLOW_86_in_ruleBuiltInFunc5442); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getPropagate_errorKeyword_16()); 
                          
                    }

                    }
                    break;
                case 18 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2479:2: kw= 'error_state_reachable'
                    {
                    kw=(Token)match(input,87,FOLLOW_87_in_ruleBuiltInFunc5461); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getError_state_reachableKeyword_17()); 
                          
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
    // $ANTLR end "ruleBuiltInFunc"


    // $ANTLR start "entryRuleRealTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2492:1: entryRuleRealTerm returns [EObject current=null] : iv_ruleRealTerm= ruleRealTerm EOF ;
    public final EObject entryRuleRealTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2493:2: (iv_ruleRealTerm= ruleRealTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2494:2: iv_ruleRealTerm= ruleRealTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealTermRule()); 
            }
            pushFollow(FOLLOW_ruleRealTerm_in_entryRuleRealTerm5501);
            iv_ruleRealTerm=ruleRealTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealTerm5511); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRealTerm"


    // $ANTLR start "ruleRealTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2501:1: ruleRealTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleRealTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2504:28: ( ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2505:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2505:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2505:2: ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2505:2: ( (lv_value_0_0= ruleUnsignedReal ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2506:1: (lv_value_0_0= ruleUnsignedReal )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2506:1: (lv_value_0_0= ruleUnsignedReal )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2507:3: lv_value_0_0= ruleUnsignedReal
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRealTermAccess().getValueUnsignedRealParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_ruleRealTerm5557);
            lv_value_0_0=ruleUnsignedReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getRealTermRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_0_0, 
                      		"UnsignedReal");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2523:2: ( (otherlv_1= RULE_ID ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==EOF||LA36_1==RULE_ID||LA36_1==17||LA36_1==39||(LA36_1>=41 && LA36_1<=43)||(LA36_1>=45 && LA36_1<=58)||(LA36_1>=64 && LA36_1<=65)||LA36_1==68||(LA36_1>=88 && LA36_1<=89)||LA36_1==92||LA36_1==95||(LA36_1>=103 && LA36_1<=104)) ) {
                    alt36=1;
                }
            }
            switch (alt36) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2524:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2524:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2525:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getRealTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRealTerm5577); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_1, grammarAccess.getRealTermAccess().getUnitUnitLiteralCrossReference_1_0()); 
                      	
                    }

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
    // $ANTLR end "ruleRealTerm"


    // $ANTLR start "entryRuleUnsignedReal"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2544:1: entryRuleUnsignedReal returns [String current=null] : iv_ruleUnsignedReal= ruleUnsignedReal EOF ;
    public final String entryRuleUnsignedReal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedReal = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2545:2: (iv_ruleUnsignedReal= ruleUnsignedReal EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2546:2: iv_ruleUnsignedReal= ruleUnsignedReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedRealRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5615);
            iv_ruleUnsignedReal=ruleUnsignedReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedReal.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedReal5626); if (state.failed) return current;

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
    // $ANTLR end "entryRuleUnsignedReal"


    // $ANTLR start "ruleUnsignedReal"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2553:1: ruleUnsignedReal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_REAL_LIT_0= RULE_REAL_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedReal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2556:28: (this_REAL_LIT_0= RULE_REAL_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2557:5: this_REAL_LIT_0= RULE_REAL_LIT
            {
            this_REAL_LIT_0=(Token)match(input,RULE_REAL_LIT,FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5665); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_REAL_LIT_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_REAL_LIT_0, grammarAccess.getUnsignedRealAccess().getREAL_LITTerminalRuleCall()); 
                  
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
    // $ANTLR end "ruleUnsignedReal"


    // $ANTLR start "entryRuleIntegerTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2572:1: entryRuleIntegerTerm returns [EObject current=null] : iv_ruleIntegerTerm= ruleIntegerTerm EOF ;
    public final EObject entryRuleIntegerTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2573:2: (iv_ruleIntegerTerm= ruleIntegerTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2574:2: iv_ruleIntegerTerm= ruleIntegerTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerTermRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5709);
            iv_ruleIntegerTerm=ruleIntegerTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerTerm5719); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIntegerTerm"


    // $ANTLR start "ruleIntegerTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2581:1: ruleIntegerTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleIntegerTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2584:28: ( ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:2: ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:2: ( (lv_value_0_0= ruleUnsignedInt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2586:1: (lv_value_0_0= ruleUnsignedInt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2586:1: (lv_value_0_0= ruleUnsignedInt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2587:3: lv_value_0_0= ruleUnsignedInt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntegerTermAccess().getValueUnsignedIntParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5765);
            lv_value_0_0=ruleUnsignedInt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIntegerTermRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_0_0, 
                      		"UnsignedInt");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2603:2: ( (otherlv_1= RULE_ID ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ID) ) {
                int LA37_1 = input.LA(2);

                if ( (LA37_1==EOF||LA37_1==RULE_ID||LA37_1==17||LA37_1==39||(LA37_1>=41 && LA37_1<=43)||(LA37_1>=45 && LA37_1<=58)||(LA37_1>=64 && LA37_1<=65)||LA37_1==68||(LA37_1>=88 && LA37_1<=89)||LA37_1==92||LA37_1==95||(LA37_1>=103 && LA37_1<=104)) ) {
                    alt37=1;
                }
            }
            switch (alt37) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2604:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2604:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2605:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getIntegerTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIntegerTerm5785); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_1, grammarAccess.getIntegerTermAccess().getUnitUnitLiteralCrossReference_1_0()); 
                      	
                    }

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
    // $ANTLR end "ruleIntegerTerm"


    // $ANTLR start "entryRuleUnsignedInt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2624:1: entryRuleUnsignedInt returns [String current=null] : iv_ruleUnsignedInt= ruleUnsignedInt EOF ;
    public final String entryRuleUnsignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedInt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2625:2: (iv_ruleUnsignedInt= ruleUnsignedInt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2626:2: iv_ruleUnsignedInt= ruleUnsignedInt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedIntRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5823);
            iv_ruleUnsignedInt=ruleUnsignedInt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedInt.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedInt5834); if (state.failed) return current;

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
    // $ANTLR end "entryRuleUnsignedInt"


    // $ANTLR start "ruleUnsignedInt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2633:1: ruleUnsignedInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2636:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2637:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5873); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INTEGER_LIT_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_INTEGER_LIT_0, grammarAccess.getUnsignedIntAccess().getINTEGER_LITTerminalRuleCall()); 
                  
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
    // $ANTLR end "ruleUnsignedInt"


    // $ANTLR start "entryRuleResoluteSubclause"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2652:1: entryRuleResoluteSubclause returns [EObject current=null] : iv_ruleResoluteSubclause= ruleResoluteSubclause EOF ;
    public final EObject entryRuleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteSubclause = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2653:2: (iv_ruleResoluteSubclause= ruleResoluteSubclause EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2654:2: iv_ruleResoluteSubclause= ruleResoluteSubclause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getResoluteSubclauseRule()); 
            }
            pushFollow(FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5917);
            iv_ruleResoluteSubclause=ruleResoluteSubclause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleResoluteSubclause; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleResoluteSubclause5927); if (state.failed) return current;

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
    // $ANTLR end "entryRuleResoluteSubclause"


    // $ANTLR start "ruleResoluteSubclause"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2661:1: ruleResoluteSubclause returns [EObject current=null] : ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) ;
    public final EObject ruleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject lv_proves_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2664:28: ( ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2665:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2665:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2665:2: () ( (lv_proves_1_0= ruleProveStatement ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2665:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2666:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getResoluteSubclauseAccess().getResoluteSubclauseAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2671:2: ( (lv_proves_1_0= ruleProveStatement ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==88) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2672:1: (lv_proves_1_0= ruleProveStatement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2672:1: (lv_proves_1_0= ruleProveStatement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2673:3: lv_proves_1_0= ruleProveStatement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getResoluteSubclauseAccess().getProvesProveStatementParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5982);
            	    lv_proves_1_0=ruleProveStatement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getResoluteSubclauseRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"proves",
            	              		lv_proves_1_0, 
            	              		"ProveStatement");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
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
    // $ANTLR end "ruleResoluteSubclause"


    // $ANTLR start "entryRuleNestedDotID"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2697:1: entryRuleNestedDotID returns [EObject current=null] : iv_ruleNestedDotID= ruleNestedDotID EOF ;
    public final EObject entryRuleNestedDotID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedDotID = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2698:2: (iv_ruleNestedDotID= ruleNestedDotID EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2699:2: iv_ruleNestedDotID= ruleNestedDotID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNestedDotIDRule()); 
            }
            pushFollow(FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID6019);
            iv_ruleNestedDotID=ruleNestedDotID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNestedDotID; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNestedDotID6029); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNestedDotID"


    // $ANTLR start "ruleNestedDotID"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:1: ruleNestedDotID returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) ;
    public final EObject ruleNestedDotID() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_sub_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2709:28: ( ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:2: ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2712:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNestedDotIDRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNestedDotID6074); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getNestedDotIDAccess().getBaseNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2723:2: (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==61) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2723:4: otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) )
                    {
                    otherlv_1=(Token)match(input,61,FOLLOW_61_in_ruleNestedDotID6087); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNestedDotIDAccess().getFullStopKeyword_1_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2727:1: ( (lv_sub_2_0= ruleNestedDotID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2728:1: (lv_sub_2_0= ruleNestedDotID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2728:1: (lv_sub_2_0= ruleNestedDotID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2729:3: lv_sub_2_0= ruleNestedDotID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNestedDotIDAccess().getSubNestedDotIDParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNestedDotID_in_ruleNestedDotID6108);
                    lv_sub_2_0=ruleNestedDotID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNestedDotIDRule());
                      	        }
                             		set(
                             			current, 
                             			"sub",
                              		lv_sub_2_0, 
                              		"NestedDotID");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


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
    // $ANTLR end "ruleNestedDotID"


    // $ANTLR start "entryRuleProveStatement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2753:1: entryRuleProveStatement returns [EObject current=null] : iv_ruleProveStatement= ruleProveStatement EOF ;
    public final EObject entryRuleProveStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProveStatement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2754:2: (iv_ruleProveStatement= ruleProveStatement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2755:2: iv_ruleProveStatement= ruleProveStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProveStatementRule()); 
            }
            pushFollow(FOLLOW_ruleProveStatement_in_entryRuleProveStatement6146);
            iv_ruleProveStatement=ruleProveStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProveStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleProveStatement6156); if (state.failed) return current;

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
    // $ANTLR end "entryRuleProveStatement"


    // $ANTLR start "ruleProveStatement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2762:1: ruleProveStatement returns [EObject current=null] : (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? ) ;
    public final EObject ruleProveStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_expr_1_0 = null;

        EObject lv_modes_4_0 = null;

        EObject lv_modes_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2765:28: ( (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2766:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2766:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2766:3: otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )?
            {
            otherlv_0=(Token)match(input,88,FOLLOW_88_in_ruleProveStatement6193); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getProveStatementAccess().getProveKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2770:1: ( (lv_expr_1_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2771:1: (lv_expr_1_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2771:1: (lv_expr_1_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2772:3: lv_expr_1_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getProveStatementAccess().getExprExprParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleProveStatement6214);
            lv_expr_1_0=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getProveStatementRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_1_0, 
                      		"Expr");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:2: ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==89) && (synpred8_InternalResolute())) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:3: ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2799:8: (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2799:10: otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )*
                    {
                    otherlv_2=(Token)match(input,89,FOLLOW_89_in_ruleProveStatement6266); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getProveStatementAccess().getModesKeyword_2_0_0());
                          
                    }
                    otherlv_3=(Token)match(input,38,FOLLOW_38_in_ruleProveStatement6278); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getProveStatementAccess().getColonKeyword_2_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2807:1: ( (lv_modes_4_0= ruleNestedDotID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2808:1: (lv_modes_4_0= ruleNestedDotID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2808:1: (lv_modes_4_0= ruleNestedDotID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2809:3: lv_modes_4_0= ruleNestedDotID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getProveStatementAccess().getModesNestedDotIDParserRuleCall_2_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNestedDotID_in_ruleProveStatement6299);
                    lv_modes_4_0=ruleNestedDotID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getProveStatementRule());
                      	        }
                             		add(
                             			current, 
                             			"modes",
                              		lv_modes_4_0, 
                              		"NestedDotID");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2825:2: (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==41) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2825:4: otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,41,FOLLOW_41_in_ruleProveStatement6312); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getProveStatementAccess().getCommaKeyword_2_0_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2829:1: ( (lv_modes_6_0= ruleNestedDotID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2830:1: (lv_modes_6_0= ruleNestedDotID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2830:1: (lv_modes_6_0= ruleNestedDotID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2831:3: lv_modes_6_0= ruleNestedDotID
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getProveStatementAccess().getModesNestedDotIDParserRuleCall_2_0_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleNestedDotID_in_ruleProveStatement6333);
                    	    lv_modes_6_0=ruleNestedDotID();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getProveStatementRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"modes",
                    	              		lv_modes_6_0, 
                    	              		"NestedDotID");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


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
    // $ANTLR end "ruleProveStatement"


    // $ANTLR start "entryRuleContainedPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2857:1: entryRuleContainedPropertyAssociation returns [EObject current=null] : iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF ;
    public final EObject entryRuleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainedPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2858:2: (iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2859:2: iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainedPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation6376);
            iv_ruleContainedPropertyAssociation=ruleContainedPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainedPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainedPropertyAssociation6386); if (state.failed) return current;

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
    // $ANTLR end "entryRuleContainedPropertyAssociation"


    // $ANTLR start "ruleContainedPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2866:1: ruleContainedPropertyAssociation returns [EObject current=null] : ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) ;
    public final EObject ruleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_append_2_0=null;
        Token lv_constant_3_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_ownedValue_4_0 = null;

        EObject lv_ownedValue_6_0 = null;

        EObject lv_appliesTo_9_0 = null;

        EObject lv_appliesTo_11_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2869:28: ( ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2870:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2870:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2870:2: ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2870:2: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2871:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2871:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2872:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getPropertyPropertyCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6434);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2885:2: (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==45) ) {
                alt42=1;
            }
            else if ( (LA42_0==90) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2885:4: otherlv_1= '=>'
                    {
                    otherlv_1=(Token)match(input,45,FOLLOW_45_in_ruleContainedPropertyAssociation6447); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getContainedPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2890:6: ( (lv_append_2_0= '+=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2890:6: ( (lv_append_2_0= '+=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2891:1: (lv_append_2_0= '+=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2891:1: (lv_append_2_0= '+=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2892:3: lv_append_2_0= '+=>'
                    {
                    lv_append_2_0=(Token)match(input,90,FOLLOW_90_in_ruleContainedPropertyAssociation6471); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_append_2_0, grammarAccess.getContainedPropertyAssociationAccess().getAppendPlusSignEqualsSignGreaterThanSignKeyword_1_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                             		setWithLastConsumed(current, "append", true, "+=>");
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2905:3: ( (lv_constant_3_0= 'constant' ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==91) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2906:1: (lv_constant_3_0= 'constant' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2906:1: (lv_constant_3_0= 'constant' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2907:3: lv_constant_3_0= 'constant'
                    {
                    lv_constant_3_0=(Token)match(input,91,FOLLOW_91_in_ruleContainedPropertyAssociation6503); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_constant_3_0, grammarAccess.getContainedPropertyAssociationAccess().getConstantConstantKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                             		setWithLastConsumed(current, "constant", true, "constant");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2920:3: ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2920:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2920:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2921:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2921:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2922:3: lv_ownedValue_4_0= ruleOptionalModalPropertyValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6539);
            lv_ownedValue_4_0=ruleOptionalModalPropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getContainedPropertyAssociationRule());
              	        }
                     		add(
                     			current, 
                     			"ownedValue",
                      		lv_ownedValue_4_0, 
                      		"OptionalModalPropertyValue");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2938:2: (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==41) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2938:4: otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    {
            	    otherlv_5=(Token)match(input,41,FOLLOW_41_in_ruleContainedPropertyAssociation6552); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_3_1_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2942:1: ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2943:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2943:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2944:3: lv_ownedValue_6_0= ruleOptionalModalPropertyValue
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6573);
            	    lv_ownedValue_6_0=ruleOptionalModalPropertyValue();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getContainedPropertyAssociationRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedValue",
            	              		lv_ownedValue_6_0, 
            	              		"OptionalModalPropertyValue");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2960:5: (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==92) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2960:7: otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    {
                    otherlv_7=(Token)match(input,92,FOLLOW_92_in_ruleContainedPropertyAssociation6589); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getContainedPropertyAssociationAccess().getAppliesKeyword_4_0());
                          
                    }
                    otherlv_8=(Token)match(input,93,FOLLOW_93_in_ruleContainedPropertyAssociation6601); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getContainedPropertyAssociationAccess().getToKeyword_4_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2968:1: ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2969:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2969:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2970:3: lv_appliesTo_9_0= ruleContainmentPath
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6622);
                    lv_appliesTo_9_0=ruleContainmentPath();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                             		add(
                             			current, 
                             			"appliesTo",
                              		lv_appliesTo_9_0, 
                              		"ContainmentPath");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2986:2: (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==41) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2986:4: otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    {
                    	    otherlv_10=(Token)match(input,41,FOLLOW_41_in_ruleContainedPropertyAssociation6635); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_4_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2990:1: ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2991:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2991:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2992:3: lv_appliesTo_11_0= ruleContainmentPath
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6656);
                    	    lv_appliesTo_11_0=ruleContainmentPath();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getContainedPropertyAssociationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"appliesTo",
                    	              		lv_appliesTo_11_0, 
                    	              		"ContainmentPath");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3008:6: (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==52) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3008:8: otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')'
                    {
                    otherlv_12=(Token)match(input,52,FOLLOW_52_in_ruleContainedPropertyAssociation6673); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getContainedPropertyAssociationAccess().getInKeyword_5_0());
                          
                    }
                    otherlv_13=(Token)match(input,94,FOLLOW_94_in_ruleContainedPropertyAssociation6685); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getContainedPropertyAssociationAccess().getBindingKeyword_5_1());
                          
                    }
                    otherlv_14=(Token)match(input,40,FOLLOW_40_in_ruleContainedPropertyAssociation6697); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getContainedPropertyAssociationAccess().getLeftParenthesisKeyword_5_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3020:1: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3021:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3021:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3022:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getInBindingClassifierCrossReference_5_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6720);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,42,FOLLOW_42_in_ruleContainedPropertyAssociation6732); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getContainedPropertyAssociationAccess().getRightParenthesisKeyword_5_4());
                          
                    }

                    }
                    break;

            }

            otherlv_17=(Token)match(input,95,FOLLOW_95_in_ruleContainedPropertyAssociation6746); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_17, grammarAccess.getContainedPropertyAssociationAccess().getSemicolonKeyword_6());
                  
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
    // $ANTLR end "ruleContainedPropertyAssociation"


    // $ANTLR start "entryRuleContainmentPath"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3055:1: entryRuleContainmentPath returns [EObject current=null] : iv_ruleContainmentPath= ruleContainmentPath EOF ;
    public final EObject entryRuleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPath = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3056:2: (iv_ruleContainmentPath= ruleContainmentPath EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3057:2: iv_ruleContainmentPath= ruleContainmentPath EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6786);
            iv_ruleContainmentPath=ruleContainmentPath();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPath; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPath6796); if (state.failed) return current;

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
    // $ANTLR end "entryRuleContainmentPath"


    // $ANTLR start "ruleContainmentPath"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3064:1: ruleContainmentPath returns [EObject current=null] : ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) ;
    public final EObject ruleContainmentPath() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_containmentPathElement_1_0 = null;

        EObject lv_containmentPathElement_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3067:28: ( ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:2: () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3069:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getContainmentPathAccess().getContainedNamedElementAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3074:2: ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3075:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3075:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3076:3: lv_containmentPathElement_1_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6851);
            lv_containmentPathElement_1_0=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getContainmentPathRule());
              	        }
                     		add(
                     			current, 
                     			"containmentPathElement",
                      		lv_containmentPathElement_1_0, 
                      		"ContainmentPathElement");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3092:2: (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==61) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3092:4: otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_2=(Token)match(input,61,FOLLOW_61_in_ruleContainmentPath6864); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getContainmentPathAccess().getFullStopKeyword_2_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3096:1: ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3097:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3097:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3098:3: lv_containmentPathElement_3_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6885);
            	    lv_containmentPathElement_3_0=ruleContainmentPathElement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getContainmentPathRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"containmentPathElement",
            	              		lv_containmentPathElement_3_0, 
            	              		"ContainmentPathElement");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
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
    // $ANTLR end "ruleContainmentPath"


    // $ANTLR start "entryRuleOptionalModalPropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3124:1: entryRuleOptionalModalPropertyValue returns [EObject current=null] : iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF ;
    public final EObject entryRuleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalModalPropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3125:2: (iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3126:2: iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOptionalModalPropertyValueRule()); 
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6925);
            iv_ruleOptionalModalPropertyValue=ruleOptionalModalPropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOptionalModalPropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6935); if (state.failed) return current;

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
    // $ANTLR end "entryRuleOptionalModalPropertyValue"


    // $ANTLR start "ruleOptionalModalPropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3133:1: ruleOptionalModalPropertyValue returns [EObject current=null] : ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) ;
    public final EObject ruleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_ownedValue_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3136:28: ( ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3137:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3137:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3137:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3137:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3138:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3138:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3139:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOptionalModalPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6981);
            lv_ownedValue_0_0=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOptionalModalPropertyValueRule());
              	        }
                     		set(
                     			current, 
                     			"ownedValue",
                      		lv_ownedValue_0_0, 
                      		"PropertyExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3155:2: (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==52) ) {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==89) ) {
                    alt50=1;
                }
            }
            switch (alt50) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3155:4: otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')'
                    {
                    otherlv_1=(Token)match(input,52,FOLLOW_52_in_ruleOptionalModalPropertyValue6994); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOptionalModalPropertyValueAccess().getInKeyword_1_0());
                          
                    }
                    otherlv_2=(Token)match(input,89,FOLLOW_89_in_ruleOptionalModalPropertyValue7006); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOptionalModalPropertyValueAccess().getModesKeyword_1_1());
                          
                    }
                    otherlv_3=(Token)match(input,40,FOLLOW_40_in_ruleOptionalModalPropertyValue7018); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOptionalModalPropertyValueAccess().getLeftParenthesisKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3167:1: ( (otherlv_4= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3168:1: (otherlv_4= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3168:1: (otherlv_4= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3169:3: otherlv_4= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                      	        }
                              
                    }
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue7038); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_4, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_3_0()); 
                      	
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3180:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==41) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3180:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,41,FOLLOW_41_in_ruleOptionalModalPropertyValue7051); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getOptionalModalPropertyValueAccess().getCommaKeyword_1_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3184:1: ( (otherlv_6= RULE_ID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3185:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3185:1: (otherlv_6= RULE_ID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3186:3: otherlv_6= RULE_ID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      			if (current==null) {
                    	      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                    	      	        }
                    	              
                    	    }
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue7071); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		newLeafNode(otherlv_6, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_4_1_0()); 
                    	      	
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,42,FOLLOW_42_in_ruleOptionalModalPropertyValue7085); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getOptionalModalPropertyValueAccess().getRightParenthesisKeyword_1_5());
                          
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
    // $ANTLR end "ruleOptionalModalPropertyValue"


    // $ANTLR start "entryRulePropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3209:1: entryRulePropertyValue returns [EObject current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final EObject entryRulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3210:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3211:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyValueRule()); 
            }
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue7123);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue7133); if (state.failed) return current;

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
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3218:1: rulePropertyValue returns [EObject current=null] : ( (lv_ownedValue_0_0= rulePropertyExpression ) ) ;
    public final EObject rulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedValue_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3221:28: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3222:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3222:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3223:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3223:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3224:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyValue7178);
            lv_ownedValue_0_0=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPropertyValueRule());
              	        }
                     		set(
                     			current, 
                     			"ownedValue",
                      		lv_ownedValue_0_0, 
                      		"PropertyExpression");
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
    // $ANTLR end "rulePropertyValue"


    // $ANTLR start "entryRulePropertyExpression"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3248:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3249:2: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3250:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression7213);
            iv_rulePropertyExpression=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression7223); if (state.failed) return current;

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
    // $ANTLR end "entryRulePropertyExpression"


    // $ANTLR start "rulePropertyExpression"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3257:1: rulePropertyExpression returns [EObject current=null] : (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) ;
    public final EObject rulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject this_RecordTerm_0 = null;

        EObject this_ReferenceTerm_1 = null;

        EObject this_ComponentClassifierTerm_2 = null;

        EObject this_ComputedTerm_3 = null;

        EObject this_StringTerm_4 = null;

        EObject this_NumericRangeTerm_5 = null;

        EObject this_RealTerm_6 = null;

        EObject this_IntegerTerm_7 = null;

        EObject this_ListTerm_8 = null;

        EObject this_BooleanLiteral_9 = null;

        EObject this_LiteralorReferenceTerm_10 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3260:28: ( (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3261:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3261:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            int alt51=11;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3262:5: this_RecordTerm_0= ruleRecordTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRecordTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRecordTerm_in_rulePropertyExpression7270);
                    this_RecordTerm_0=ruleRecordTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_RecordTerm_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3272:5: this_ReferenceTerm_1= ruleReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getReferenceTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleReferenceTerm_in_rulePropertyExpression7297);
                    this_ReferenceTerm_1=ruleReferenceTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ReferenceTerm_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3282:5: this_ComponentClassifierTerm_2= ruleComponentClassifierTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComponentClassifierTermParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression7324);
                    this_ComponentClassifierTerm_2=ruleComponentClassifierTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ComponentClassifierTerm_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3292:5: this_ComputedTerm_3= ruleComputedTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComputedTermParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComputedTerm_in_rulePropertyExpression7351);
                    this_ComputedTerm_3=ruleComputedTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ComputedTerm_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3302:5: this_StringTerm_4= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getStringTermParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_rulePropertyExpression7378);
                    this_StringTerm_4=ruleStringTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_StringTerm_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3312:5: this_NumericRangeTerm_5= ruleNumericRangeTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getNumericRangeTermParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7405);
                    this_NumericRangeTerm_5=ruleNumericRangeTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_NumericRangeTerm_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3322:5: this_RealTerm_6= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRealTermParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_rulePropertyExpression7432);
                    this_RealTerm_6=ruleRealTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_RealTerm_6; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3332:5: this_IntegerTerm_7= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getIntegerTermParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7459);
                    this_IntegerTerm_7=ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IntegerTerm_7; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3342:5: this_ListTerm_8= ruleListTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getListTermParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListTerm_in_rulePropertyExpression7486);
                    this_ListTerm_8=ruleListTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ListTerm_8; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3352:5: this_BooleanLiteral_9= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getBooleanLiteralParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7513);
                    this_BooleanLiteral_9=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BooleanLiteral_9; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3362:5: this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getLiteralorReferenceTermParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7540);
                    this_LiteralorReferenceTerm_10=ruleLiteralorReferenceTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_LiteralorReferenceTerm_10; 
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
    // $ANTLR end "rulePropertyExpression"


    // $ANTLR start "entryRuleLiteralorReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3378:1: entryRuleLiteralorReferenceTerm returns [EObject current=null] : iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF ;
    public final EObject entryRuleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralorReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3379:2: (iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3380:2: iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralorReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7575);
            iv_ruleLiteralorReferenceTerm=ruleLiteralorReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralorReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7585); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLiteralorReferenceTerm"


    // $ANTLR start "ruleLiteralorReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3387:1: ruleLiteralorReferenceTerm returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3390:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3391:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3391:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3392:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3392:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3393:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLiteralorReferenceTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLiteralorReferenceTermAccess().getNamedValueAbstractNamedValueCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7632);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
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
    // $ANTLR end "ruleLiteralorReferenceTerm"


    // $ANTLR start "entryRuleBooleanLiteral"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3414:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3415:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3416:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7667);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral7677); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3423:1: ruleBooleanLiteral returns [EObject current=null] : ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3426:28: ( ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3427:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3427:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3427:2: () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3427:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3428:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3433:2: ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==96) ) {
                alt52=1;
            }
            else if ( (LA52_0==97) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3433:3: ( (lv_value_1_0= 'true' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3433:3: ( (lv_value_1_0= 'true' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3434:1: (lv_value_1_0= 'true' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3434:1: (lv_value_1_0= 'true' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3435:3: lv_value_1_0= 'true'
                    {
                    lv_value_1_0=(Token)match(input,96,FOLLOW_96_in_ruleBooleanLiteral7730); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_value_1_0, grammarAccess.getBooleanLiteralAccess().getValueTrueKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      	        }
                             		setWithLastConsumed(current, "value", true, "true");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3449:7: otherlv_2= 'false'
                    {
                    otherlv_2=(Token)match(input,97,FOLLOW_97_in_ruleBooleanLiteral7761); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getBooleanLiteralAccess().getFalseKeyword_1_1());
                          
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
    // $ANTLR end "ruleBooleanLiteral"


    // $ANTLR start "entryRuleConstantValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3461:1: entryRuleConstantValue returns [EObject current=null] : iv_ruleConstantValue= ruleConstantValue EOF ;
    public final EObject entryRuleConstantValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3462:2: (iv_ruleConstantValue= ruleConstantValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3463:2: iv_ruleConstantValue= ruleConstantValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantValueRule()); 
            }
            pushFollow(FOLLOW_ruleConstantValue_in_entryRuleConstantValue7798);
            iv_ruleConstantValue=ruleConstantValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantValue7808); if (state.failed) return current;

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
    // $ANTLR end "entryRuleConstantValue"


    // $ANTLR start "ruleConstantValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3470:1: ruleConstantValue returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleConstantValue() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3473:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3474:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3474:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3475:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3475:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3476:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getConstantValueRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantValueAccess().getNamedValuePropertyConstantCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleConstantValue7855);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
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
    // $ANTLR end "ruleConstantValue"


    // $ANTLR start "entryRuleReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3497:1: entryRuleReferenceTerm returns [EObject current=null] : iv_ruleReferenceTerm= ruleReferenceTerm EOF ;
    public final EObject entryRuleReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3498:2: (iv_ruleReferenceTerm= ruleReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3499:2: iv_ruleReferenceTerm= ruleReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7890);
            iv_ruleReferenceTerm=ruleReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleReferenceTerm7900); if (state.failed) return current;

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
    // $ANTLR end "entryRuleReferenceTerm"


    // $ANTLR start "ruleReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3506:1: ruleReferenceTerm returns [EObject current=null] : (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) ;
    public final EObject ruleReferenceTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_containmentPathElement_2_0 = null;

        EObject lv_containmentPathElement_4_0 = null;

        EObject lv_containmentPathElement_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3509:28: ( (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3510:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3510:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3510:3: otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')'
            {
            otherlv_0=(Token)match(input,98,FOLLOW_98_in_ruleReferenceTerm7937); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getReferenceTermAccess().getReferenceKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleReferenceTerm7949); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getReferenceTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3518:1: ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==RULE_ID) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==105) ) {
                    alt53=1;
                }
            }
            switch (alt53) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3518:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3518:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3519:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3519:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3520:3: lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementQualifiedContainmentPathElementParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7971);
                    lv_containmentPathElement_2_0=ruleQualifiedContainmentPathElement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getReferenceTermRule());
                      	        }
                             		add(
                             			current, 
                             			"containmentPathElement",
                              		lv_containmentPathElement_2_0, 
                              		"QualifiedContainmentPathElement");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,61,FOLLOW_61_in_ruleReferenceTerm7983); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getReferenceTermAccess().getFullStopKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3540:3: ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3541:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3541:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3542:3: lv_containmentPathElement_4_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm8006);
            lv_containmentPathElement_4_0=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getReferenceTermRule());
              	        }
                     		add(
                     			current, 
                     			"containmentPathElement",
                      		lv_containmentPathElement_4_0, 
                      		"ContainmentPathElement");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3558:2: (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )*
            loop54:
            do {
                int alt54=2;
                int LA54_0 = input.LA(1);

                if ( (LA54_0==61) ) {
                    alt54=1;
                }


                switch (alt54) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3558:4: otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_5=(Token)match(input,61,FOLLOW_61_in_ruleReferenceTerm8019); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getReferenceTermAccess().getFullStopKeyword_4_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3562:1: ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3564:3: lv_containmentPathElement_6_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm8040);
            	    lv_containmentPathElement_6_0=ruleContainmentPathElement();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getReferenceTermRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"containmentPathElement",
            	              		lv_containmentPathElement_6_0, 
            	              		"ContainmentPathElement");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop54;
                }
            } while (true);

            otherlv_7=(Token)match(input,42,FOLLOW_42_in_ruleReferenceTerm8054); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getReferenceTermAccess().getRightParenthesisKeyword_5());
                  
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
    // $ANTLR end "ruleReferenceTerm"


    // $ANTLR start "entryRuleRecordTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3592:1: entryRuleRecordTerm returns [EObject current=null] : iv_ruleRecordTerm= ruleRecordTerm EOF ;
    public final EObject entryRuleRecordTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecordTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3593:2: (iv_ruleRecordTerm= ruleRecordTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3594:2: iv_ruleRecordTerm= ruleRecordTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRecordTermRule()); 
            }
            pushFollow(FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm8090);
            iv_ruleRecordTerm=ruleRecordTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRecordTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRecordTerm8100); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRecordTerm"


    // $ANTLR start "ruleRecordTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3601:1: ruleRecordTerm returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) ;
    public final EObject ruleRecordTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedFieldValue_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3604:28: ( (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3605:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3605:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3605:3: otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,99,FOLLOW_99_in_ruleRecordTerm8137); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getRecordTermAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3609:1: ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+
            int cnt55=0;
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==RULE_ID) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3610:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3610:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3611:3: lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRecordTermAccess().getOwnedFieldValueFieldPropertyAssociationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm8158);
            	    lv_ownedFieldValue_1_0=ruleFieldPropertyAssociation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getRecordTermRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"ownedFieldValue",
            	              		lv_ownedFieldValue_1_0, 
            	              		"FieldPropertyAssociation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt55 >= 1 ) break loop55;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(55, input);
                        throw eee;
                }
                cnt55++;
            } while (true);

            otherlv_2=(Token)match(input,100,FOLLOW_100_in_ruleRecordTerm8171); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRecordTermAccess().getRightSquareBracketKeyword_2());
                  
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
    // $ANTLR end "ruleRecordTerm"


    // $ANTLR start "entryRuleComputedTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3641:1: entryRuleComputedTerm returns [EObject current=null] : iv_ruleComputedTerm= ruleComputedTerm EOF ;
    public final EObject entryRuleComputedTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputedTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3642:2: (iv_ruleComputedTerm= ruleComputedTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3643:2: iv_ruleComputedTerm= ruleComputedTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComputedTermRule()); 
            }
            pushFollow(FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm8209);
            iv_ruleComputedTerm=ruleComputedTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComputedTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComputedTerm8219); if (state.failed) return current;

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
    // $ANTLR end "entryRuleComputedTerm"


    // $ANTLR start "ruleComputedTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3650:1: ruleComputedTerm returns [EObject current=null] : (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleComputedTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_function_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3653:28: ( (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3654:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3654:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3654:3: otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,101,FOLLOW_101_in_ruleComputedTerm8256); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComputedTermAccess().getComputeKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleComputedTerm8268); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComputedTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3662:1: ( (lv_function_2_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3663:1: (lv_function_2_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3663:1: (lv_function_2_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3664:3: lv_function_2_0= RULE_ID
            {
            lv_function_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleComputedTerm8285); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_function_2_0, grammarAccess.getComputedTermAccess().getFunctionIDTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getComputedTermRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"function",
                      		lv_function_2_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleComputedTerm8302); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getComputedTermAccess().getRightParenthesisKeyword_3());
                  
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
    // $ANTLR end "ruleComputedTerm"


    // $ANTLR start "entryRuleComponentClassifierTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3692:1: entryRuleComponentClassifierTerm returns [EObject current=null] : iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF ;
    public final EObject entryRuleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentClassifierTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3693:2: (iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3694:2: iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComponentClassifierTermRule()); 
            }
            pushFollow(FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm8338);
            iv_ruleComponentClassifierTerm=ruleComponentClassifierTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComponentClassifierTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComponentClassifierTerm8348); if (state.failed) return current;

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
    // $ANTLR end "entryRuleComponentClassifierTerm"


    // $ANTLR start "ruleComponentClassifierTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3701:1: ruleComponentClassifierTerm returns [EObject current=null] : (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) ;
    public final EObject ruleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3704:28: ( (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3705:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3705:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3705:3: otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,102,FOLLOW_102_in_ruleComponentClassifierTerm8385); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComponentClassifierTermAccess().getClassifierKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleComponentClassifierTerm8397); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComponentClassifierTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3713:1: ( ( ruleQCREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3714:1: ( ruleQCREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3714:1: ( ruleQCREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3715:3: ruleQCREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getComponentClassifierTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getComponentClassifierTermAccess().getClassifierComponentClassifierCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8420);
            ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleComponentClassifierTerm8432); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getComponentClassifierTermAccess().getRightParenthesisKeyword_3());
                  
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
    // $ANTLR end "ruleComponentClassifierTerm"


    // $ANTLR start "entryRuleListTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3740:1: entryRuleListTerm returns [EObject current=null] : iv_ruleListTerm= ruleListTerm EOF ;
    public final EObject entryRuleListTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3741:2: (iv_ruleListTerm= ruleListTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3742:2: iv_ruleListTerm= ruleListTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListTermRule()); 
            }
            pushFollow(FOLLOW_ruleListTerm_in_entryRuleListTerm8468);
            iv_ruleListTerm=ruleListTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListTerm8478); if (state.failed) return current;

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
    // $ANTLR end "entryRuleListTerm"


    // $ANTLR start "ruleListTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3749:1: ruleListTerm returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleListTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedListElement_2_0 = null;

        EObject lv_ownedListElement_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3752:28: ( ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3753:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3753:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3753:2: () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3753:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3754:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListTermAccess().getListValueAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleListTerm8524); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3763:1: ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=RULE_ID && LA57_0<=RULE_INTEGER_LIT)||LA57_0==40||(LA57_0>=53 && LA57_0<=54)||(LA57_0>=96 && LA57_0<=99)||(LA57_0>=101 && LA57_0<=102)) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3763:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3763:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3764:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3764:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3765:3: lv_ownedListElement_2_0= rulePropertyExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8546);
                    lv_ownedListElement_2_0=rulePropertyExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getListTermRule());
                      	        }
                             		add(
                             			current, 
                             			"ownedListElement",
                              		lv_ownedListElement_2_0, 
                              		"PropertyExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3781:2: (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==41) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3781:4: otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,41,FOLLOW_41_in_ruleListTerm8559); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListTermAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3785:1: ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3786:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3786:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3787:3: lv_ownedListElement_4_0= rulePropertyExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8580);
                    	    lv_ownedListElement_4_0=rulePropertyExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getListTermRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"ownedListElement",
                    	              		lv_ownedListElement_4_0, 
                    	              		"PropertyExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,42,FOLLOW_42_in_ruleListTerm8596); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getListTermAccess().getRightParenthesisKeyword_3());
                  
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
    // $ANTLR end "ruleListTerm"


    // $ANTLR start "entryRuleFieldPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3815:1: entryRuleFieldPropertyAssociation returns [EObject current=null] : iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF ;
    public final EObject entryRuleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFieldPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3816:2: (iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3817:2: iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFieldPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8632);
            iv_ruleFieldPropertyAssociation=ruleFieldPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFieldPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8642); if (state.failed) return current;

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
    // $ANTLR end "entryRuleFieldPropertyAssociation"


    // $ANTLR start "ruleFieldPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3824:1: ruleFieldPropertyAssociation returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedValue_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3827:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3828:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3828:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3828:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3828:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3829:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3829:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3830:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getFieldPropertyAssociationRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8687); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getFieldPropertyAssociationAccess().getPropertyBasicPropertyCrossReference_0_0()); 
              	
            }

            }


            }

            otherlv_1=(Token)match(input,45,FOLLOW_45_in_ruleFieldPropertyAssociation8699); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFieldPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3845:1: ( (lv_ownedValue_2_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3846:1: (lv_ownedValue_2_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3846:1: (lv_ownedValue_2_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3847:3: lv_ownedValue_2_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFieldPropertyAssociationAccess().getOwnedValuePropertyExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8720);
            lv_ownedValue_2_0=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFieldPropertyAssociationRule());
              	        }
                     		set(
                     			current, 
                     			"ownedValue",
                      		lv_ownedValue_2_0, 
                      		"PropertyExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,95,FOLLOW_95_in_ruleFieldPropertyAssociation8732); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getFieldPropertyAssociationAccess().getSemicolonKeyword_3());
                  
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
    // $ANTLR end "ruleFieldPropertyAssociation"


    // $ANTLR start "entryRuleContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3875:1: entryRuleContainmentPathElement returns [EObject current=null] : iv_ruleContainmentPathElement= ruleContainmentPathElement EOF ;
    public final EObject entryRuleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3876:2: (iv_ruleContainmentPathElement= ruleContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3877:2: iv_ruleContainmentPathElement= ruleContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8768);
            iv_ruleContainmentPathElement=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPathElement8778); if (state.failed) return current;

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
    // $ANTLR end "entryRuleContainmentPathElement"


    // $ANTLR start "ruleContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3884:1: ruleContainmentPathElement returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) ;
    public final EObject ruleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_arrayRange_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3887:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3888:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3888:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3888:2: ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3888:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3889:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3889:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3890:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainmentPathElementRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleContainmentPathElement8823); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3901:2: ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==99) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3902:1: (lv_arrayRange_1_0= ruleArrayRange )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3902:1: (lv_arrayRange_1_0= ruleArrayRange )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3903:3: lv_arrayRange_1_0= ruleArrayRange
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainmentPathElementAccess().getArrayRangeArrayRangeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8844);
                    lv_arrayRange_1_0=ruleArrayRange();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getContainmentPathElementRule());
                      	        }
                             		add(
                             			current, 
                             			"arrayRange",
                              		lv_arrayRange_1_0, 
                              		"ArrayRange");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

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
    // $ANTLR end "ruleContainmentPathElement"


    // $ANTLR start "entryRuleQualifiedContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3927:1: entryRuleQualifiedContainmentPathElement returns [EObject current=null] : iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF ;
    public final EObject entryRuleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3928:2: (iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3929:2: iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8881);
            iv_ruleQualifiedContainmentPathElement=ruleQualifiedContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8891); if (state.failed) return current;

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
    // $ANTLR end "entryRuleQualifiedContainmentPathElement"


    // $ANTLR start "ruleQualifiedContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3936:1: ruleQualifiedContainmentPathElement returns [EObject current=null] : ( ( ruleQCLREF ) ) ;
    public final EObject ruleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3939:28: ( ( ( ruleQCLREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3940:1: ( ( ruleQCLREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3940:1: ( ( ruleQCLREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3941:1: ( ruleQCLREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3941:1: ( ruleQCLREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3942:3: ruleQCLREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getQualifiedContainmentPathElementRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getQualifiedContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8938);
            ruleQCLREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
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
    // $ANTLR end "ruleQualifiedContainmentPathElement"


    // $ANTLR start "entryRulePlusMinus"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3965:1: entryRulePlusMinus returns [String current=null] : iv_rulePlusMinus= rulePlusMinus EOF ;
    public final String entryRulePlusMinus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePlusMinus = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3966:2: (iv_rulePlusMinus= rulePlusMinus EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3967:2: iv_rulePlusMinus= rulePlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusMinusRule()); 
            }
            pushFollow(FOLLOW_rulePlusMinus_in_entryRulePlusMinus8976);
            iv_rulePlusMinus=rulePlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusMinus.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusMinus8987); if (state.failed) return current;

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
    // $ANTLR end "entryRulePlusMinus"


    // $ANTLR start "rulePlusMinus"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3974:1: rulePlusMinus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken rulePlusMinus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3977:28: ( (kw= '+' | kw= '-' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3978:1: (kw= '+' | kw= '-' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3978:1: (kw= '+' | kw= '-' )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==53) ) {
                alt59=1;
            }
            else if ( (LA59_0==54) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3979:2: kw= '+'
                    {
                    kw=(Token)match(input,53,FOLLOW_53_in_rulePlusMinus9025); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPlusMinusAccess().getPlusSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3986:2: kw= '-'
                    {
                    kw=(Token)match(input,54,FOLLOW_54_in_rulePlusMinus9044); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPlusMinusAccess().getHyphenMinusKeyword_1()); 
                          
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
    // $ANTLR end "rulePlusMinus"


    // $ANTLR start "entryRuleStringTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3999:1: entryRuleStringTerm returns [EObject current=null] : iv_ruleStringTerm= ruleStringTerm EOF ;
    public final EObject entryRuleStringTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4000:2: (iv_ruleStringTerm= ruleStringTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4001:2: iv_ruleStringTerm= ruleStringTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringTermRule()); 
            }
            pushFollow(FOLLOW_ruleStringTerm_in_entryRuleStringTerm9084);
            iv_ruleStringTerm=ruleStringTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringTerm9094); if (state.failed) return current;

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
    // $ANTLR end "entryRuleStringTerm"


    // $ANTLR start "ruleStringTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4008:1: ruleStringTerm returns [EObject current=null] : ( (lv_value_0_0= ruleNoQuoteString ) ) ;
    public final EObject ruleStringTerm() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4011:28: ( ( (lv_value_0_0= ruleNoQuoteString ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4012:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4012:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4013:1: (lv_value_0_0= ruleNoQuoteString )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4013:1: (lv_value_0_0= ruleNoQuoteString )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4014:3: lv_value_0_0= ruleNoQuoteString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStringTermAccess().getValueNoQuoteStringParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_ruleStringTerm9139);
            lv_value_0_0=ruleNoQuoteString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getStringTermRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_0_0, 
                      		"NoQuoteString");
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
    // $ANTLR end "ruleStringTerm"


    // $ANTLR start "entryRuleNoQuoteString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4038:1: entryRuleNoQuoteString returns [String current=null] : iv_ruleNoQuoteString= ruleNoQuoteString EOF ;
    public final String entryRuleNoQuoteString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNoQuoteString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4039:2: (iv_ruleNoQuoteString= ruleNoQuoteString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4040:2: iv_ruleNoQuoteString= ruleNoQuoteString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoQuoteStringRule()); 
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString9175);
            iv_ruleNoQuoteString=ruleNoQuoteString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoQuoteString.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoQuoteString9186); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNoQuoteString"


    // $ANTLR start "ruleNoQuoteString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4047:1: ruleNoQuoteString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNoQuoteString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4050:28: (this_STRING_0= RULE_STRING )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4051:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNoQuoteString9225); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_STRING_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_STRING_0, grammarAccess.getNoQuoteStringAccess().getSTRINGTerminalRuleCall()); 
                  
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
    // $ANTLR end "ruleNoQuoteString"


    // $ANTLR start "entryRuleArrayRange"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4066:1: entryRuleArrayRange returns [EObject current=null] : iv_ruleArrayRange= ruleArrayRange EOF ;
    public final EObject entryRuleArrayRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayRange = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4067:2: (iv_ruleArrayRange= ruleArrayRange EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4068:2: iv_ruleArrayRange= ruleArrayRange EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayRangeRule()); 
            }
            pushFollow(FOLLOW_ruleArrayRange_in_entryRuleArrayRange9269);
            iv_ruleArrayRange=ruleArrayRange();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayRange; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayRange9279); if (state.failed) return current;

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
    // $ANTLR end "entryRuleArrayRange"


    // $ANTLR start "ruleArrayRange"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4075:1: ruleArrayRange returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleArrayRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_lowerBound_2_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4078:28: ( ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:2: () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4080:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayRangeAccess().getArrayRangeAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,99,FOLLOW_99_in_ruleArrayRange9325); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayRangeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4089:1: ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4090:1: (lv_lowerBound_2_0= ruleINTVALUE )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4090:1: (lv_lowerBound_2_0= ruleINTVALUE )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4091:3: lv_lowerBound_2_0= ruleINTVALUE
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayRangeAccess().getLowerBoundINTVALUEParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange9346);
            lv_lowerBound_2_0=ruleINTVALUE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getArrayRangeRule());
              	        }
                     		set(
                     			current, 
                     			"lowerBound",
                      		lv_lowerBound_2_0, 
                      		"INTVALUE");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4107:2: (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==103) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4107:4: otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    {
                    otherlv_3=(Token)match(input,103,FOLLOW_103_in_ruleArrayRange9359); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getArrayRangeAccess().getFullStopFullStopKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4111:1: ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4112:1: (lv_upperBound_4_0= ruleINTVALUE )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4112:1: (lv_upperBound_4_0= ruleINTVALUE )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4113:3: lv_upperBound_4_0= ruleINTVALUE
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayRangeAccess().getUpperBoundINTVALUEParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange9380);
                    lv_upperBound_4_0=ruleINTVALUE();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getArrayRangeRule());
                      	        }
                             		set(
                             			current, 
                             			"upperBound",
                              		lv_upperBound_4_0, 
                              		"INTVALUE");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,100,FOLLOW_100_in_ruleArrayRange9394); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getArrayRangeAccess().getRightSquareBracketKeyword_4());
                  
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
    // $ANTLR end "ruleArrayRange"


    // $ANTLR start "entryRuleSignedConstant"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4141:1: entryRuleSignedConstant returns [EObject current=null] : iv_ruleSignedConstant= ruleSignedConstant EOF ;
    public final EObject entryRuleSignedConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedConstant = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4142:2: (iv_ruleSignedConstant= ruleSignedConstant EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4143:2: iv_ruleSignedConstant= ruleSignedConstant EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSignedConstantRule()); 
            }
            pushFollow(FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9430);
            iv_ruleSignedConstant=ruleSignedConstant();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSignedConstant; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSignedConstant9440); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSignedConstant"


    // $ANTLR start "ruleSignedConstant"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4150:1: ruleSignedConstant returns [EObject current=null] : ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) ;
    public final EObject ruleSignedConstant() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_0_0 = null;

        EObject lv_ownedPropertyExpression_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4153:28: ( ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4154:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4154:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4154:2: ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4154:2: ( (lv_op_0_0= rulePlusMinus ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4155:1: (lv_op_0_0= rulePlusMinus )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4155:1: (lv_op_0_0= rulePlusMinus )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4156:3: lv_op_0_0= rulePlusMinus
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOpPlusMinusParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePlusMinus_in_ruleSignedConstant9486);
            lv_op_0_0=rulePlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSignedConstantRule());
              	        }
                     		set(
                     			current, 
                     			"op",
                      		lv_op_0_0, 
                      		"PlusMinus");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4172:2: ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4173:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4173:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4174:3: lv_ownedPropertyExpression_1_0= ruleConstantValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOwnedPropertyExpressionConstantValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleConstantValue_in_ruleSignedConstant9507);
            lv_ownedPropertyExpression_1_0=ruleConstantValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSignedConstantRule());
              	        }
                     		add(
                     			current, 
                     			"ownedPropertyExpression",
                      		lv_ownedPropertyExpression_1_0, 
                      		"ConstantValue");
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
    // $ANTLR end "ruleSignedConstant"


    // $ANTLR start "entryRuleNumericRangeTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4202:1: entryRuleNumericRangeTerm returns [EObject current=null] : iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF ;
    public final EObject entryRuleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericRangeTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4203:2: (iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4204:2: iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumericRangeTermRule()); 
            }
            pushFollow(FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9547);
            iv_ruleNumericRangeTerm=ruleNumericRangeTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumericRangeTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumericRangeTerm9557); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNumericRangeTerm"


    // $ANTLR start "ruleNumericRangeTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4211:1: ruleNumericRangeTerm returns [EObject current=null] : ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) ;
    public final EObject ruleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_minimum_0_0 = null;

        EObject lv_maximum_2_0 = null;

        EObject lv_delta_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4214:28: ( ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:2: ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:2: ( (lv_minimum_0_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4216:1: (lv_minimum_0_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4216:1: (lv_minimum_0_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4217:3: lv_minimum_0_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMinimumNumAltParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9603);
            lv_minimum_0_0=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNumericRangeTermRule());
              	        }
                     		set(
                     			current, 
                     			"minimum",
                      		lv_minimum_0_0, 
                      		"NumAlt");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,103,FOLLOW_103_in_ruleNumericRangeTerm9615); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNumericRangeTermAccess().getFullStopFullStopKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4237:1: ( (lv_maximum_2_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4238:1: (lv_maximum_2_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4238:1: (lv_maximum_2_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4239:3: lv_maximum_2_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMaximumNumAltParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9636);
            lv_maximum_2_0=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNumericRangeTermRule());
              	        }
                     		set(
                     			current, 
                     			"maximum",
                      		lv_maximum_2_0, 
                      		"NumAlt");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4255:2: (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==104) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4255:4: otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) )
                    {
                    otherlv_3=(Token)match(input,104,FOLLOW_104_in_ruleNumericRangeTerm9649); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getNumericRangeTermAccess().getDeltaKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4259:1: ( (lv_delta_4_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4260:1: (lv_delta_4_0= ruleNumAlt )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4260:1: (lv_delta_4_0= ruleNumAlt )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4261:3: lv_delta_4_0= ruleNumAlt
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getDeltaNumAltParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9670);
                    lv_delta_4_0=ruleNumAlt();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNumericRangeTermRule());
                      	        }
                             		set(
                             			current, 
                             			"delta",
                              		lv_delta_4_0, 
                              		"NumAlt");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


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
    // $ANTLR end "ruleNumericRangeTerm"


    // $ANTLR start "entryRuleNumAlt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4285:1: entryRuleNumAlt returns [EObject current=null] : iv_ruleNumAlt= ruleNumAlt EOF ;
    public final EObject entryRuleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumAlt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4286:2: (iv_ruleNumAlt= ruleNumAlt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:2: iv_ruleNumAlt= ruleNumAlt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumAltRule()); 
            }
            pushFollow(FOLLOW_ruleNumAlt_in_entryRuleNumAlt9708);
            iv_ruleNumAlt=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumAlt; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumAlt9718); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNumAlt"


    // $ANTLR start "ruleNumAlt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4294:1: ruleNumAlt returns [EObject current=null] : (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) ;
    public final EObject ruleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject this_RealTerm_0 = null;

        EObject this_IntegerTerm_1 = null;

        EObject this_SignedConstant_2 = null;

        EObject this_ConstantValue_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4297:28: ( (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4298:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4298:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            int alt62=4;
            switch ( input.LA(1) ) {
            case RULE_REAL_LIT:
                {
                alt62=1;
                }
                break;
            case RULE_INTEGER_LIT:
                {
                alt62=2;
                }
                break;
            case 53:
            case 54:
                {
                alt62=3;
                }
                break;
            case RULE_ID:
                {
                alt62=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4299:5: this_RealTerm_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getRealTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleNumAlt9765);
                    this_RealTerm_0=ruleRealTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_RealTerm_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4309:5: this_IntegerTerm_1= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getIntegerTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleNumAlt9792);
                    this_IntegerTerm_1=ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IntegerTerm_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4319:5: this_SignedConstant_2= ruleSignedConstant
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getSignedConstantParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSignedConstant_in_ruleNumAlt9819);
                    this_SignedConstant_2=ruleSignedConstant();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_SignedConstant_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4329:5: this_ConstantValue_3= ruleConstantValue
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getConstantValueParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstantValue_in_ruleNumAlt9846);
                    this_ConstantValue_3=ruleConstantValue();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ConstantValue_3; 
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
    // $ANTLR end "ruleNumAlt"


    // $ANTLR start "entryRuleINTVALUE"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4345:1: entryRuleINTVALUE returns [String current=null] : iv_ruleINTVALUE= ruleINTVALUE EOF ;
    public final String entryRuleINTVALUE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleINTVALUE = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4346:2: (iv_ruleINTVALUE= ruleINTVALUE EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4347:2: iv_ruleINTVALUE= ruleINTVALUE EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getINTVALUERule()); 
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9882);
            iv_ruleINTVALUE=ruleINTVALUE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleINTVALUE.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleINTVALUE9893); if (state.failed) return current;

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
    // $ANTLR end "entryRuleINTVALUE"


    // $ANTLR start "ruleINTVALUE"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4354:1: ruleINTVALUE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleINTVALUE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4357:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4358:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9932); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INTEGER_LIT_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_INTEGER_LIT_0, grammarAccess.getINTVALUEAccess().getINTEGER_LITTerminalRuleCall()); 
                  
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
    // $ANTLR end "ruleINTVALUE"


    // $ANTLR start "entryRuleQCLREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4373:1: entryRuleQCLREF returns [String current=null] : iv_ruleQCLREF= ruleQCLREF EOF ;
    public final String entryRuleQCLREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCLREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4374:2: (iv_ruleQCLREF= ruleQCLREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4375:2: iv_ruleQCLREF= ruleQCLREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCLREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCLREF_in_entryRuleQCLREF9977);
            iv_ruleQCLREF=ruleQCLREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCLREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCLREF9988); if (state.failed) return current;

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
    // $ANTLR end "entryRuleQCLREF"


    // $ANTLR start "ruleQCLREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4382:1: ruleQCLREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQCLREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4385:28: ( (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4386:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4386:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4386:6: this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF10028); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQCLREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            kw=(Token)match(input,105,FOLLOW_105_in_ruleQCLREF10046); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getQCLREFAccess().getColonColonKeyword_1()); 
                  
            }
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF10061); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_2, grammarAccess.getQCLREFAccess().getIDTerminalRuleCall_2()); 
                  
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
    // $ANTLR end "ruleQCLREF"


    // $ANTLR start "entryRuleQPREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4414:1: entryRuleQPREF returns [String current=null] : iv_ruleQPREF= ruleQPREF EOF ;
    public final String entryRuleQPREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQPREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4415:2: (iv_ruleQPREF= ruleQPREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4416:2: iv_ruleQPREF= ruleQPREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQPREFRule()); 
            }
            pushFollow(FOLLOW_ruleQPREF_in_entryRuleQPREF10107);
            iv_ruleQPREF=ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQPREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQPREF10118); if (state.failed) return current;

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
    // $ANTLR end "entryRuleQPREF"


    // $ANTLR start "ruleQPREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4423:1: ruleQPREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQPREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4426:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4427:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4427:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4427:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )?
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF10158); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQPREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4434:1: (kw= '::' this_ID_2= RULE_ID )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==105) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4435:2: kw= '::' this_ID_2= RULE_ID
                    {
                    kw=(Token)match(input,105,FOLLOW_105_in_ruleQPREF10177); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQPREFAccess().getColonColonKeyword_1_0()); 
                          
                    }
                    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF10192); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ID_2);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_ID_2, grammarAccess.getQPREFAccess().getIDTerminalRuleCall_1_1()); 
                          
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
    // $ANTLR end "ruleQPREF"


    // $ANTLR start "entryRuleQCREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4455:1: entryRuleQCREF returns [String current=null] : iv_ruleQCREF= ruleQCREF EOF ;
    public final String entryRuleQCREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4456:2: (iv_ruleQCREF= ruleQCREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4457:2: iv_ruleQCREF= ruleQCREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCREF_in_entryRuleQCREF10240);
            iv_ruleQCREF=ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCREF10251); if (state.failed) return current;

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
    // $ANTLR end "entryRuleQCREF"


    // $ANTLR start "ruleQCREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4464:1: ruleQCREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQCREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4467:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4468:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4468:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4468:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4468:2: (this_ID_0= RULE_ID kw= '::' )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==RULE_ID) ) {
                    int LA64_1 = input.LA(2);

                    if ( (LA64_1==105) ) {
                        alt64=1;
                    }


                }


                switch (alt64) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4468:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF10292); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_0);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_0, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_0_0()); 
            	          
            	    }
            	    kw=(Token)match(input,105,FOLLOW_105_in_ruleQCREF10310); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQCREFAccess().getColonColonKeyword_0_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF10327); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_2, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_1()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4488:1: (kw= '.' this_ID_4= RULE_ID )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==61) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4489:2: kw= '.' this_ID_4= RULE_ID
                    {
                    kw=(Token)match(input,61,FOLLOW_61_in_ruleQCREF10346); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQCREFAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF10361); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ID_4);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_ID_4, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_2_1()); 
                          
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
    // $ANTLR end "ruleQCREF"


    // $ANTLR start "entryRuleSTAR"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4509:1: entryRuleSTAR returns [String current=null] : iv_ruleSTAR= ruleSTAR EOF ;
    public final String entryRuleSTAR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSTAR = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4510:2: (iv_ruleSTAR= ruleSTAR EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4511:2: iv_ruleSTAR= ruleSTAR EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSTARRule()); 
            }
            pushFollow(FOLLOW_ruleSTAR_in_entryRuleSTAR10409);
            iv_ruleSTAR=ruleSTAR();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSTAR.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSTAR10420); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSTAR"


    // $ANTLR start "ruleSTAR"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4518:1: ruleSTAR returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken ruleSTAR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4521:28: (kw= '*' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4523:2: kw= '*'
            {
            kw=(Token)match(input,55,FOLLOW_55_in_ruleSTAR10457); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getSTARAccess().getAsteriskKeyword()); 
                  
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
    // $ANTLR end "ruleSTAR"

    // $ANTLR start synpred1_InternalResolute
    public final void synpred1_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:3: ( ( () ( ( '=>' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:4: ( () ( ( '=>' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:4: ( () ( ( '=>' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:5: () ( ( '=>' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1013:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1013:2: ( ( '=>' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1014:1: ( '=>' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1014:1: ( '=>' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1015:2: '=>'
        {
        match(input,45,FOLLOW_45_in_synpred1_InternalResolute2242); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred1_InternalResolute

    // $ANTLR start synpred2_InternalResolute
    public final void synpred2_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:3: ( ( () ( ( 'or' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:4: ( () ( ( 'or' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:4: ( () ( ( 'or' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:5: () ( ( 'or' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1089:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1089:2: ( ( 'or' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1090:1: ( 'or' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1090:1: ( 'or' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1091:2: 'or'
        {
        match(input,46,FOLLOW_46_in_synpred2_InternalResolute2424); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalResolute

    // $ANTLR start synpred3_InternalResolute
    public final void synpred3_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:3: ( ( () ( ( 'and' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:4: ( () ( ( 'and' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:4: ( () ( ( 'and' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:5: () ( ( 'and' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1165:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1165:2: ( ( 'and' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1166:1: ( 'and' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1166:1: ( 'and' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1167:2: 'and'
        {
        match(input,47,FOLLOW_47_in_synpred3_InternalResolute2606); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalResolute

    // $ANTLR start synpred4_InternalResolute
    public final void synpred4_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:3: ( ( () ( ( ruleRelationalOp ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:4: ( () ( ( ruleRelationalOp ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:4: ( () ( ( ruleRelationalOp ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:5: () ( ( ruleRelationalOp ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1310:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1310:2: ( ( ruleRelationalOp ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1311:1: ( ruleRelationalOp )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1311:1: ( ruleRelationalOp )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1312:1: ruleRelationalOp
        {
        pushFollow(FOLLOW_ruleRelationalOp_in_synpred4_InternalResolute2991);
        ruleRelationalOp();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalResolute

    // $ANTLR start synpred5_InternalResolute
    public final void synpred5_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:4: ( () ( ( ( '+' | '-' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:4: ( () ( ( ( '+' | '-' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:5: () ( ( ( '+' | '-' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1387:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1387:2: ( ( ( '+' | '-' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1388:1: ( ( '+' | '-' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1388:1: ( ( '+' | '-' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1389:1: ( '+' | '-' )
        {
        if ( (input.LA(1)>=53 && input.LA(1)<=54) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred5_InternalResolute

    // $ANTLR start synpred6_InternalResolute
    public final void synpred6_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:3: ( ( () ( ( ( '*' | '/' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:4: ( () ( ( ( '*' | '/' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:4: ( () ( ( ( '*' | '/' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:5: () ( ( ( '*' | '/' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1484:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1484:2: ( ( ( '*' | '/' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1485:1: ( ( '*' | '/' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1485:1: ( ( '*' | '/' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1486:1: ( '*' | '/' )
        {
        if ( (input.LA(1)>=55 && input.LA(1)<=56) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalResolute

    // $ANTLR start synpred7_InternalResolute
    public final void synpred7_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:3: ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:4: ( () ( ( ( 'union' | 'intersect' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:4: ( () ( ( ( 'union' | 'intersect' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:5: () ( ( ( 'union' | 'intersect' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1581:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1581:2: ( ( ( 'union' | 'intersect' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1582:1: ( ( 'union' | 'intersect' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1582:1: ( ( 'union' | 'intersect' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1583:1: ( 'union' | 'intersect' )
        {
        if ( (input.LA(1)>=57 && input.LA(1)<=58) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred7_InternalResolute

    // $ANTLR start synpred8_InternalResolute
    public final void synpred8_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:3: ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:4: ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:4: ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:6: 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )*
        {
        match(input,89,FOLLOW_89_in_synpred8_InternalResolute6227); if (state.failed) return ;
        match(input,38,FOLLOW_38_in_synpred8_InternalResolute6231); if (state.failed) return ;
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2790:1: ( ( ruleNestedDotID ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2791:1: ( ruleNestedDotID )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2791:1: ( ruleNestedDotID )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2792:1: ruleNestedDotID
        {
        pushFollow(FOLLOW_ruleNestedDotID_in_synpred8_InternalResolute6238);
        ruleNestedDotID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2794:2: ( ',' ( ( ruleNestedDotID ) ) )*
        loop66:
        do {
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==41) ) {
                alt66=1;
            }


            switch (alt66) {
        	case 1 :
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2794:4: ',' ( ( ruleNestedDotID ) )
        	    {
        	    match(input,41,FOLLOW_41_in_synpred8_InternalResolute6245); if (state.failed) return ;
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2795:1: ( ( ruleNestedDotID ) )
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2796:1: ( ruleNestedDotID )
        	    {
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2796:1: ( ruleNestedDotID )
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2797:1: ruleNestedDotID
        	    {
        	    pushFollow(FOLLOW_ruleNestedDotID_in_synpred8_InternalResolute6252);
        	    ruleNestedDotID();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }


        	    }
        	    break;

        	default :
        	    break loop66;
            }
        } while (true);


        }


        }
    }
    // $ANTLR end synpred8_InternalResolute

    // Delegated rules

    public final boolean synpred2_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA51 dfa51 = new DFA51(this);
    static final String DFA15_eotS =
        "\12\uffff";
    static final String DFA15_eofS =
        "\1\10\11\uffff";
    static final String DFA15_minS =
        "\1\4\7\0\2\uffff";
    static final String DFA15_maxS =
        "\1\131\7\0\2\uffff";
    static final String DFA15_acceptS =
        "\10\uffff\1\2\1\1";
    static final String DFA15_specialS =
        "\1\uffff\1\2\1\1\1\0\1\6\1\5\1\4\1\3\2\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\10\14\uffff\1\10\25\uffff\1\5\1\uffff\2\10\1\2\1\uffff\3"+
            "\10\1\1\1\3\1\4\1\6\1\7\6\10\5\uffff\2\10\2\uffff\1\10\23\uffff"+
            "\2\10",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "1309:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_3 = input.LA(1);

                         
                        int index15_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_3);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_2 = input.LA(1);

                         
                        int index15_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_1 = input.LA(1);

                         
                        int index15_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_1);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA15_7 = input.LA(1);

                         
                        int index15_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA15_6 = input.LA(1);

                         
                        int index15_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_6);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA15_5 = input.LA(1);

                         
                        int index15_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA15_4 = input.LA(1);

                         
                        int index15_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA34_eotS =
        "\17\uffff";
    static final String DFA34_eofS =
        "\1\uffff\1\16\15\uffff";
    static final String DFA34_minS =
        "\2\4\15\uffff";
    static final String DFA34_maxS =
        "\1\141\1\151\15\uffff";
    static final String DFA34_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\14\1\15\1\13"+
        "\1\1";
    static final String DFA34_specialS =
        "\17\uffff}>";
    static final String[] DFA34_transitionS = {
            "\1\1\1\7\1\5\1\4\10\uffff\1\13\27\uffff\1\14\23\uffff\1\2\1"+
            "\uffff\1\3\1\10\2\uffff\2\11\2\uffff\22\12\10\uffff\2\6",
            "\1\16\14\uffff\1\16\25\uffff\1\16\1\15\3\16\1\uffff\16\16"+
            "\2\uffff\1\16\2\uffff\2\16\2\uffff\1\16\23\uffff\2\16\17\uffff"+
            "\1\16",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }
        public String getDescription() {
            return "1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( (lv_val_8_0= ruleStringTerm ) )? ) | ( () ( (lv_val_10_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_12_0= ruleRealTerm ) ) ) | ( () ( (lv_val_14_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_16_0= ruleStringTerm ) ) ) | ( () otherlv_18= 'if' ( (lv_cond_19_0= ruleExpr ) ) otherlv_20= 'then' ( (lv_then_21_0= ruleExpr ) ) otherlv_22= 'else' ( (lv_else_23_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_25_1= 'forall' | lv_quant_25_2= 'exists' ) ) ) (otherlv_26= '(' ( (lv_args_27_0= ruleArg ) ) otherlv_28= ')' )+ otherlv_29= '.' ( (lv_expr_30_0= ruleExpr ) ) ) | ( () ( (lv_fn_32_0= ruleBuiltInFunc ) ) otherlv_33= '(' ( ( (lv_args_34_0= ruleExpr ) ) (otherlv_35= ',' ( (lv_args_36_0= ruleExpr ) ) )* )? otherlv_37= ')' ) | ( () ( (otherlv_39= RULE_ID ) ) otherlv_40= '(' ( ( (lv_args_41_0= ruleExpr ) ) (otherlv_42= ',' ( (lv_args_43_0= ruleExpr ) ) )* )? otherlv_44= ')' ) | ( () otherlv_46= '{' ( (lv_map_47_0= ruleExpr ) ) otherlv_48= 'for' (otherlv_49= '(' ( (lv_args_50_0= ruleArg ) ) otherlv_51= ')' )+ (otherlv_52= '|' ( (lv_filter_53_0= ruleExpr ) ) )? otherlv_54= '}' ) | (otherlv_55= '(' this_Expr_56= ruleExpr otherlv_57= ')' ) )";
        }
    }
    static final String DFA51_eotS =
        "\23\uffff";
    static final String DFA51_eofS =
        "\6\uffff\1\15\1\17\1\uffff\1\21\2\uffff\1\15\1\uffff\1\17\3\uffff"+
        "\1\21";
    static final String DFA51_minS =
        "\1\4\5\uffff\2\4\1\uffff\1\51\2\uffff\1\51\1\uffff\1\51\1\uffff"+
        "\1\4\1\uffff\1\51";
    static final String DFA51_maxS =
        "\1\146\5\uffff\2\147\1\uffff\1\151\2\uffff\1\147\1\uffff\1\147"+
        "\1\uffff\1\4\1\uffff\1\147";
    static final String DFA51_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\6\1\uffff\1\11\1\12\1\uffff"+
        "\1\7\1\uffff\1\10\1\uffff\1\13\1\uffff";
    static final String DFA51_specialS =
        "\23\uffff}>";
    static final String[] DFA51_transitionS = {
            "\1\11\1\5\1\6\1\7\40\uffff\1\12\14\uffff\2\10\51\uffff\2\13"+
            "\1\2\1\1\1\uffff\1\4\1\3",
            "",
            "",
            "",
            "",
            "",
            "\1\14\44\uffff\2\15\11\uffff\1\15\47\uffff\1\15\2\uffff\1"+
            "\15\7\uffff\1\10",
            "\1\16\44\uffff\2\17\11\uffff\1\17\47\uffff\1\17\2\uffff\1"+
            "\17\7\uffff\1\10",
            "",
            "\2\21\11\uffff\1\21\47\uffff\1\21\2\uffff\1\21\7\uffff\1\10"+
            "\1\uffff\1\20",
            "",
            "",
            "\2\15\11\uffff\1\15\47\uffff\1\15\2\uffff\1\15\7\uffff\1\10",
            "",
            "\2\17\11\uffff\1\17\47\uffff\1\17\2\uffff\1\17\7\uffff\1\10",
            "",
            "\1\22",
            "",
            "\2\21\11\uffff\1\21\47\uffff\1\21\2\uffff\1\21\7\uffff\1\10"
    };

    static final short[] DFA51_eot = DFA.unpackEncodedString(DFA51_eotS);
    static final short[] DFA51_eof = DFA.unpackEncodedString(DFA51_eofS);
    static final char[] DFA51_min = DFA.unpackEncodedStringToUnsignedChars(DFA51_minS);
    static final char[] DFA51_max = DFA.unpackEncodedStringToUnsignedChars(DFA51_maxS);
    static final short[] DFA51_accept = DFA.unpackEncodedString(DFA51_acceptS);
    static final short[] DFA51_special = DFA.unpackEncodedString(DFA51_specialS);
    static final short[][] DFA51_transition;

    static {
        int numStates = DFA51_transitionS.length;
        DFA51_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA51_transition[i] = DFA.unpackEncodedString(DFA51_transitionS[i]);
        }
    }

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = DFA51_eot;
            this.eof = DFA51_eof;
            this.min = DFA51_min;
            this.max = DFA51_max;
            this.accept = DFA51_accept;
            this.special = DFA51_special;
            this.transition = DFA51_transition;
        }
        public String getDescription() {
            return "3261:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )";
        }
    }
 

    public static final BitSet FOLLOW_ruleAnnexLibrary_in_entryRuleAnnexLibrary75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnnexLibrary85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteLibrary_in_ruleAnnexLibrary131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteLibrary_in_entryRuleResoluteLibrary173 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteLibrary183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefinition_in_ruleResoluteLibrary238 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleDefinition_in_entryRuleDefinition275 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinition285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantDefinition_in_ruleDefinition332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_ruleDefinition359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleType451 = new BitSet(new long[]{0x0000003FFFFD0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleType472 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleType484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltinType_in_ruleType513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType548 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltinType558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleBuiltinType602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleBuiltinType631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleBuiltinType660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleBuiltinType689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleBuiltinType718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleBuiltinType747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleBuiltinType776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleBuiltinType805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleBuiltinType834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleBuiltinType863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleBuiltinType892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleBuiltinType921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleBuiltinType950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleBuiltinType979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleBuiltinType1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleBuiltinType1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleBuiltinType1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleBuiltinType1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleBuiltinType1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleBuiltinType1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArg_in_entryRuleArg1204 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArg1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg1256 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleArg1273 = new BitSet(new long[]{0x0000003FFFFD0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleArg1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition1330 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantDefinition1340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstantDefinition1382 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleConstantDefinition1399 = new BitSet(new long[]{0x0000003FFFFD0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleConstantDefinition1420 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleConstantDefinition1432 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleConstantDefinition1453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition1489 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionDefinition1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFunctionDefinition1541 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleFunctionDefinition1558 = new BitSet(new long[]{0x0000040000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition1580 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleFunctionDefinition1593 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition1614 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleFunctionDefinition1630 = new BitSet(new long[]{0x0000084000000000L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClaimString_in_entryRuleClaimString1687 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClaimString1697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleClaimString1749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleClaimString1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody1828 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinitionBody1838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleDefinitionBody1885 = new BitSet(new long[]{0x0000003FFFFD0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleDefinitionBody1906 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleDefinitionBody1918 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody1939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleDefinitionBody1968 = new BitSet(new long[]{0xD8401100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody1981 = new BitSet(new long[]{0x0000100000000030L});
    public static final BitSet FOLLOW_ruleClaimString_in_ruleDefinitionBody2002 = new BitSet(new long[]{0x0000100000000030L});
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody2015 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr2075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleExpr2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2165 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpr2175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_ruleImpliesExpr2222 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleImpliesExpr2273 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_entryRuleOrExpr2347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpr2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2404 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_ruleOrExpr2455 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2491 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_entryRuleAndExpr2529 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpr2539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr2586 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_ruleAndExpr2637 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr2673 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp2712 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalOp2723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleRelationalOp2761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleRelationalOp2780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleRelationalOp2799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleRelationalOp2818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleRelationalOp2837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleRelationalOp2856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleRelationalOp2875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr2915 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpr2925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr2972 = new BitSet(new long[]{0x001F088000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3021 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr3044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_entryRulePlusExpr3082 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusExpr3092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3139 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_53_in_rulePlusExpr3211 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_54_in_rulePlusExpr3240 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3279 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr3317 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTimesExpr3327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3374 = new BitSet(new long[]{0x0180000000000002L});
    public static final BitSet FOLLOW_55_in_ruleTimesExpr3446 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_56_in_ruleTimesExpr3475 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3514 = new BitSet(new long[]{0x0180000000000002L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr3552 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSetOpExpr3562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3609 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_57_in_ruleSetOpExpr3681 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_58_in_ruleSetOpExpr3710 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3749 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr3787 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrefixExpr3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulePrefixExpr3852 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_59_in_rulePrefixExpr3881 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr3918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_rulePrefixExpr3947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr3982 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomicExpr3992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleAtomicExpr4050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleAtomicExpr4079 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleAtomicExpr4092 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleAtomicExpr4113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleAtomicExpr4144 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleAtomicExpr4242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleAtomicExpr4347 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleAtomicExpr4380 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr4413 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr4471 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr4500 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4529 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4550 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4562 = new BitSet(new long[]{0x2000010000000000L});
    public static final BitSet FOLLOW_61_in_ruleAtomicExpr4576 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4635 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4647 = new BitSet(new long[]{0xD8400500000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4669 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4682 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4703 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAtomicExpr4756 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4768 = new BitSet(new long[]{0xD8400500000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4790 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4803 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4824 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr4869 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleAtomicExpr4902 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4915 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4936 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4948 = new BitSet(new long[]{0x0000010000020000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleAtomicExpr4963 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4984 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAtomicExpr4998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr5018 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5040 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr5051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5089 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltInFunc5100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleBuiltInFunc5138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleBuiltInFunc5157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleBuiltInFunc5176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleBuiltInFunc5195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBuiltInFunc5214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleBuiltInFunc5233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleBuiltInFunc5252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleBuiltInFunc5271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleBuiltInFunc5290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleBuiltInFunc5309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleBuiltInFunc5328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleBuiltInFunc5347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleBuiltInFunc5366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleBuiltInFunc5385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_ruleBuiltInFunc5404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleBuiltInFunc5423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ruleBuiltInFunc5442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_ruleBuiltInFunc5461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_entryRuleRealTerm5501 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealTerm5511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_ruleRealTerm5557 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRealTerm5577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5615 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedReal5626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5709 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerTerm5719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5765 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIntegerTerm5785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5823 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedInt5834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5917 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteSubclause5927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5982 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID6019 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNestedDotID6029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNestedDotID6074 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleNestedDotID6087 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleNestedDotID6108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_entryRuleProveStatement6146 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProveStatement6156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_ruleProveStatement6193 = new BitSet(new long[]{0xD8400100000100F0L,0x0000000300FFFFCCL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleProveStatement6214 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_ruleProveStatement6266 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleProveStatement6278 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleProveStatement6299 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleProveStatement6312 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleProveStatement6333 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation6376 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainedPropertyAssociation6386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6434 = new BitSet(new long[]{0x0000200000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_45_in_ruleContainedPropertyAssociation6447 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_90_in_ruleContainedPropertyAssociation6471 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_91_in_ruleContainedPropertyAssociation6503 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6539 = new BitSet(new long[]{0x0010020000000000L,0x0000000090000000L});
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6552 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6573 = new BitSet(new long[]{0x0010020000000000L,0x0000000090000000L});
    public static final BitSet FOLLOW_92_in_ruleContainedPropertyAssociation6589 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_ruleContainedPropertyAssociation6601 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6622 = new BitSet(new long[]{0x0010020000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6635 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6656 = new BitSet(new long[]{0x0010020000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_52_in_ruleContainedPropertyAssociation6673 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleContainedPropertyAssociation6685 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleContainedPropertyAssociation6697 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6720 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleContainedPropertyAssociation6732 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_ruleContainedPropertyAssociation6746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6786 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPath6796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6851 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleContainmentPath6864 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6885 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6925 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6981 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_52_in_ruleOptionalModalPropertyValue6994 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_ruleOptionalModalPropertyValue7006 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleOptionalModalPropertyValue7018 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue7038 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleOptionalModalPropertyValue7051 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue7071 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleOptionalModalPropertyValue7085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue7123 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue7133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyValue7178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression7213 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression7223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_rulePropertyExpression7270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_rulePropertyExpression7297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression7324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_rulePropertyExpression7351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_rulePropertyExpression7378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_rulePropertyExpression7432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_rulePropertyExpression7486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7575 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7667 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral7677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleBooleanLiteral7730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleBooleanLiteral7761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_entryRuleConstantValue7798 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantValue7808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleConstantValue7855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7890 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReferenceTerm7900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleReferenceTerm7937 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleReferenceTerm7949 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7971 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_ruleReferenceTerm7983 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm8006 = new BitSet(new long[]{0x2000040000000000L});
    public static final BitSet FOLLOW_61_in_ruleReferenceTerm8019 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm8040 = new BitSet(new long[]{0x2000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleReferenceTerm8054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm8090 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRecordTerm8100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleRecordTerm8137 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm8158 = new BitSet(new long[]{0x0000000000000010L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_ruleRecordTerm8171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm8209 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComputedTerm8219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_ruleComputedTerm8256 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleComputedTerm8268 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleComputedTerm8285 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleComputedTerm8302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm8338 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComponentClassifierTerm8348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_ruleComponentClassifierTerm8385 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleComponentClassifierTerm8397 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8420 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleComponentClassifierTerm8432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_entryRuleListTerm8468 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListTerm8478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleListTerm8524 = new BitSet(new long[]{0x00600500000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8546 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleListTerm8559 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8580 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleListTerm8596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8632 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8687 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleFieldPropertyAssociation8699 = new BitSet(new long[]{0x00600100000000F0L,0x0000006F08000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8720 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_95_in_ruleFieldPropertyAssociation8732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8768 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPathElement8778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleContainmentPathElement8823 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8881 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_entryRulePlusMinus8976 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusMinus8987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulePlusMinus9025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulePlusMinus9044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_entryRuleStringTerm9084 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringTerm9094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_ruleStringTerm9139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString9175 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoQuoteString9186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNoQuoteString9225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayRange_in_entryRuleArrayRange9269 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayRange9279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleArrayRange9325 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange9346 = new BitSet(new long[]{0x0000000000000000L,0x0000009000000000L});
    public static final BitSet FOLLOW_103_in_ruleArrayRange9359 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange9380 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_100_in_ruleArrayRange9394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9430 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSignedConstant9440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_ruleSignedConstant9486 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleSignedConstant9507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9547 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumericRangeTerm9557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9603 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_103_in_ruleNumericRangeTerm9615 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9636 = new BitSet(new long[]{0x0000000000000002L,0x0000010000000000L});
    public static final BitSet FOLLOW_104_in_ruleNumericRangeTerm9649 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_entryRuleNumAlt9708 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumAlt9718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleNumAlt9765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleNumAlt9792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_ruleNumAlt9819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleNumAlt9846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9882 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleINTVALUE9893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_entryRuleQCLREF9977 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCLREF9988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF10028 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_105_in_ruleQCLREF10046 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF10061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_entryRuleQPREF10107 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQPREF10118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF10158 = new BitSet(new long[]{0x0000000000000002L,0x0000020000000000L});
    public static final BitSet FOLLOW_105_in_ruleQPREF10177 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF10192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_entryRuleQCREF10240 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCREF10251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF10292 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_105_in_ruleQCREF10310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF10327 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleQCREF10346 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF10361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTAR_in_entryRuleSTAR10409 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSTAR10420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleSTAR10457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred1_InternalResolute2242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred2_InternalResolute2424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_synpred3_InternalResolute2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred4_InternalResolute2991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred5_InternalResolute3158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred6_InternalResolute3393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred7_InternalResolute3628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_synpred8_InternalResolute6227 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_synpred8_InternalResolute6231 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_synpred8_InternalResolute6238 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_synpred8_InternalResolute6245 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_synpred8_InternalResolute6252 = new BitSet(new long[]{0x0000020000000002L});

}