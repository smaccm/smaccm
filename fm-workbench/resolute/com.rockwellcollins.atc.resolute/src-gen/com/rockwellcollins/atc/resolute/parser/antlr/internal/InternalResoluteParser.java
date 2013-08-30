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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_REAL_LIT", "RULE_INTEGER_LIT", "RULE_SL_COMMENT", "RULE_DIGIT", "RULE_EXPONENT", "RULE_INT_EXPONENT", "RULE_BASED_INTEGER", "RULE_EXTENDED_DIGIT", "RULE_ANNEXTEXT", "RULE_WS", "'{'", "'}'", "'data'", "'thread'", "'thread_group'", "'process'", "'subprogram'", "'subprogram_group'", "'processor'", "'virtual_processor'", "'memory'", "'bus'", "'virtual_bus'", "'device'", "'system'", "'abstract'", "'connection'", "'component'", "'string'", "'int'", "'real'", "'bool'", "':'", "'='", "'('", "','", "')'", "'<='", "'**'", "'=>'", "'or'", "'and'", "'<'", "'>'", "'>='", "'<>'", "'in'", "'+'", "'-'", "'*'", "'/'", "'union'", "'intersect'", "'not'", "'this'", "'fail'", "'if'", "'then'", "'else'", "'forall'", "'exists'", "'.'", "'for'", "'|'", "'connected'", "'property_lookup'", "'class_of'", "'type'", "'has_type'", "'bound'", "'contained'", "'conn_source'", "'conn_dest'", "'property_exists'", "'sum'", "'analysis'", "'prove'", "'+=>'", "'constant'", "'applies'", "'to'", "'binding'", "';'", "'modes'", "'true'", "'false'", "'reference'", "'['", "']'", "'compute'", "'classifier'", "'..'", "'delta'", "'::'"
    };
    public static final int RULE_ID=4;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int RULE_REAL_LIT=6;
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
    public static final int T__59=59;
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

                if ( (LA2_1==38) ) {
                    alt2=1;
                }
                else if ( (LA2_1==40) ) {
                    alt2=2;
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
            else if ( ((LA23_0>=RULE_ID && LA23_0<=RULE_INTEGER_LIT)||LA23_0==16||LA23_0==40||(LA23_0>=60 && LA23_0<=62)||(LA23_0>=65 && LA23_0<=66)||(LA23_0>=70 && LA23_0<=81)||(LA23_0>=90 && LA23_0<=91)) ) {
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1749:1: ruleAtomicExpr returns [EObject current=null] : ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) ) ;
    public final EObject ruleAtomicExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token lv_quant_23_1=null;
        Token lv_quant_23_2=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_38=null;
        Token otherlv_40=null;
        Token otherlv_42=null;
        Token otherlv_44=null;
        Token otherlv_46=null;
        Token otherlv_47=null;
        Token otherlv_49=null;
        Token otherlv_50=null;
        Token otherlv_52=null;
        Token otherlv_53=null;
        Token otherlv_55=null;
        EObject lv_val_6_0 = null;

        EObject lv_val_8_0 = null;

        EObject lv_val_10_0 = null;

        EObject lv_val_12_0 = null;

        EObject lv_val_14_0 = null;

        EObject lv_cond_17_0 = null;

        EObject lv_then_19_0 = null;

        EObject lv_else_21_0 = null;

        EObject lv_args_25_0 = null;

        EObject lv_expr_28_0 = null;

        AntlrDatatypeRuleToken lv_fn_30_0 = null;

        EObject lv_args_32_0 = null;

        EObject lv_args_34_0 = null;

        EObject lv_args_39_0 = null;

        EObject lv_args_41_0 = null;

        EObject lv_map_45_0 = null;

        EObject lv_args_48_0 = null;

        EObject lv_filter_51_0 = null;

        EObject this_Expr_54 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1752:28: ( ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) )
            int alt33=13;
            alt33 = dfa33.predict(input);
            switch (alt33) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:6: ( () otherlv_3= 'this' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:6: ( () otherlv_3= 'this' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:7: () otherlv_3= 'this'
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

                    }


                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:6: ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:6: ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:7: () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1787:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFailExprAction_2_0(),
                                  current);
                          
                    }

                    }

                    otherlv_5=(Token)match(input,61,FOLLOW_61_in_ruleAtomicExpr4108); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getAtomicExprAccess().getFailKeyword_2_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1796:1: ( (lv_val_6_0= ruleStringTerm ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==RULE_STRING) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1797:1: (lv_val_6_0= ruleStringTerm )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1797:1: (lv_val_6_0= ruleStringTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1798:3: lv_val_6_0= ruleStringTerm
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_2_2_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4129);
                            lv_val_6_0=ruleStringTerm();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"val",
                                      		lv_val_6_0, 
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:6: ( () ( (lv_val_8_0= ruleIntegerTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:6: ( () ( (lv_val_8_0= ruleIntegerTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:7: () ( (lv_val_8_0= ruleIntegerTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1816:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIntExprAction_3_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1821:2: ( (lv_val_8_0= ruleIntegerTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1822:1: (lv_val_8_0= ruleIntegerTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1822:1: (lv_val_8_0= ruleIntegerTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1823:3: lv_val_8_0= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValIntegerTermParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4168);
                    lv_val_8_0=ruleIntegerTerm();

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
                              		"IntegerTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1840:6: ( () ( (lv_val_10_0= ruleRealTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1840:6: ( () ( (lv_val_10_0= ruleRealTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1840:7: () ( (lv_val_10_0= ruleRealTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1840:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1841:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getRealExprAction_4_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1846:2: ( (lv_val_10_0= ruleRealTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1847:1: (lv_val_10_0= ruleRealTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1847:1: (lv_val_10_0= ruleRealTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1848:3: lv_val_10_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValRealTermParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleAtomicExpr4206);
                    lv_val_10_0=ruleRealTerm();

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
                              		"RealTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:6: ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:6: ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:7: () ( (lv_val_12_0= ruleBooleanLiteral ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1866:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBoolExprAction_5_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1871:2: ( (lv_val_12_0= ruleBooleanLiteral ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1872:1: (lv_val_12_0= ruleBooleanLiteral )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1872:1: (lv_val_12_0= ruleBooleanLiteral )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1873:3: lv_val_12_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValBooleanLiteralParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4244);
                    lv_val_12_0=ruleBooleanLiteral();

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
                              		"BooleanLiteral");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1890:6: ( () ( (lv_val_14_0= ruleStringTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1890:6: ( () ( (lv_val_14_0= ruleStringTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1890:7: () ( (lv_val_14_0= ruleStringTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1890:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1891:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getStringExprAction_6_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1896:2: ( (lv_val_14_0= ruleStringTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1897:1: (lv_val_14_0= ruleStringTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1897:1: (lv_val_14_0= ruleStringTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1898:3: lv_val_14_0= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4282);
                    lv_val_14_0=ruleStringTerm();

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
                              		"StringTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1915:6: ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1915:6: ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1915:7: () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1915:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1916:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIfThenElseExprAction_7_0(),
                                  current);
                          
                    }

                    }

                    otherlv_16=(Token)match(input,62,FOLLOW_62_in_ruleAtomicExpr4311); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getAtomicExprAccess().getIfKeyword_7_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1925:1: ( (lv_cond_17_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1926:1: (lv_cond_17_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1926:1: (lv_cond_17_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1927:3: lv_cond_17_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getCondExprParserRuleCall_7_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4332);
                    lv_cond_17_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"cond",
                              		lv_cond_17_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_18=(Token)match(input,63,FOLLOW_63_in_ruleAtomicExpr4344); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_18, grammarAccess.getAtomicExprAccess().getThenKeyword_7_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1947:1: ( (lv_then_19_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1948:1: (lv_then_19_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1948:1: (lv_then_19_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:3: lv_then_19_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getThenExprParserRuleCall_7_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4365);
                    lv_then_19_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"then",
                              		lv_then_19_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_20=(Token)match(input,64,FOLLOW_64_in_ruleAtomicExpr4377); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getAtomicExprAccess().getElseKeyword_7_5());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1969:1: ( (lv_else_21_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1970:1: (lv_else_21_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1970:1: (lv_else_21_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1971:3: lv_else_21_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getElseExprParserRuleCall_7_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4398);
                    lv_else_21_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"else",
                              		lv_else_21_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:6: ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:6: ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:7: () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1989:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getQuantifiedExprAction_8_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1994:2: ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1995:1: ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1995:1: ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1996:1: (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1996:1: (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' )
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==65) ) {
                        alt25=1;
                    }
                    else if ( (LA25_0==66) ) {
                        alt25=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 0, input);

                        throw nvae;
                    }
                    switch (alt25) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1997:3: lv_quant_23_1= 'forall'
                            {
                            lv_quant_23_1=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr4435); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_23_1, grammarAccess.getAtomicExprAccess().getQuantForallKeyword_8_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_23_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2009:8: lv_quant_23_2= 'exists'
                            {
                            lv_quant_23_2=(Token)match(input,66,FOLLOW_66_in_ruleAtomicExpr4464); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_23_2, grammarAccess.getAtomicExprAccess().getQuantExistsKeyword_8_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_23_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2024:2: (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==40) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2024:4: otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')'
                    	    {
                    	    otherlv_24=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4493); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_24, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_8_2_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2028:1: ( (lv_args_25_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2029:1: (lv_args_25_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2029:1: (lv_args_25_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2030:3: lv_args_25_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_8_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4514);
                    	    lv_args_25_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_25_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_26=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4526); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_26, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_8_2_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);

                    otherlv_27=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr4540); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_27, grammarAccess.getAtomicExprAccess().getFullStopKeyword_8_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2054:1: ( (lv_expr_28_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2055:1: (lv_expr_28_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2055:1: (lv_expr_28_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2056:3: lv_expr_28_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_8_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4561);
                    lv_expr_28_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_28_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:6: ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:6: ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:7: () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2074:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBuiltInFuncCallExprAction_9_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2079:2: ( (lv_fn_30_0= ruleBuiltInFunc ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2080:1: (lv_fn_30_0= ruleBuiltInFunc )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2080:1: (lv_fn_30_0= ruleBuiltInFunc )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2081:3: lv_fn_30_0= ruleBuiltInFunc
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFnBuiltInFuncParserRuleCall_9_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4599);
                    lv_fn_30_0=ruleBuiltInFunc();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"fn",
                              		lv_fn_30_0, 
                              		"BuiltInFunc");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_31=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4611); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_31, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_9_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2101:1: ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>=RULE_ID && LA28_0<=RULE_INTEGER_LIT)||LA28_0==16||LA28_0==40||LA28_0==54||(LA28_0>=59 && LA28_0<=62)||(LA28_0>=65 && LA28_0<=66)||(LA28_0>=70 && LA28_0<=81)||(LA28_0>=90 && LA28_0<=91)) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2101:2: ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2101:2: ( (lv_args_32_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2102:1: (lv_args_32_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2102:1: (lv_args_32_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2103:3: lv_args_32_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4633);
                            lv_args_32_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_32_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2119:2: (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )*
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==41) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2119:4: otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) )
                            	    {
                            	    otherlv_33=(Token)match(input,41,FOLLOW_41_in_ruleAtomicExpr4646); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_33, grammarAccess.getAtomicExprAccess().getCommaKeyword_9_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:1: ( (lv_args_34_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_args_34_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_args_34_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2125:3: lv_args_34_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4667);
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


                            	    }
                            	    break;

                            	default :
                            	    break loop27;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_35=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4683); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_35, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_9_4());
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:6: ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:6: ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:7: () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2146:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2147:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFnCallExprAction_10_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2152:2: ( (otherlv_37= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2153:1: (otherlv_37= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2153:1: (otherlv_37= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2154:3: otherlv_37= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    otherlv_37=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAtomicExpr4720); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_37, grammarAccess.getAtomicExprAccess().getFnFunctionDefinitionCrossReference_10_1_0()); 
                      	
                    }

                    }


                    }

                    otherlv_38=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4732); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_38, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_10_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2169:1: ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( ((LA30_0>=RULE_ID && LA30_0<=RULE_INTEGER_LIT)||LA30_0==16||LA30_0==40||LA30_0==54||(LA30_0>=59 && LA30_0<=62)||(LA30_0>=65 && LA30_0<=66)||(LA30_0>=70 && LA30_0<=81)||(LA30_0>=90 && LA30_0<=91)) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2169:2: ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2169:2: ( (lv_args_39_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2170:1: (lv_args_39_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2170:1: (lv_args_39_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2171:3: lv_args_39_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4754);
                            lv_args_39_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_39_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2187:2: (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )*
                            loop29:
                            do {
                                int alt29=2;
                                int LA29_0 = input.LA(1);

                                if ( (LA29_0==41) ) {
                                    alt29=1;
                                }


                                switch (alt29) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2187:4: otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) )
                            	    {
                            	    otherlv_40=(Token)match(input,41,FOLLOW_41_in_ruleAtomicExpr4767); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_40, grammarAccess.getAtomicExprAccess().getCommaKeyword_10_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2191:1: ( (lv_args_41_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2192:1: (lv_args_41_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2192:1: (lv_args_41_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2193:3: lv_args_41_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4788);
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


                            	    }
                            	    break;

                            	default :
                            	    break loop29;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_42=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4804); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_42, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_10_4());
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:6: ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:6: ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:7: () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2214:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2215:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFilterMapExprAction_11_0(),
                                  current);
                          
                    }

                    }

                    otherlv_44=(Token)match(input,16,FOLLOW_16_in_ruleAtomicExpr4833); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_44, grammarAccess.getAtomicExprAccess().getLeftCurlyBracketKeyword_11_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2224:1: ( (lv_map_45_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2225:1: (lv_map_45_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2225:1: (lv_map_45_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2226:3: lv_map_45_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getMapExprParserRuleCall_11_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4854);
                    lv_map_45_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"map",
                              		lv_map_45_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_46=(Token)match(input,68,FOLLOW_68_in_ruleAtomicExpr4866); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_46, grammarAccess.getAtomicExprAccess().getForKeyword_11_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:1: (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+
                    int cnt31=0;
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==40) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:3: otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')'
                    	    {
                    	    otherlv_47=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4879); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_47, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_11_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2250:1: ( (lv_args_48_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2251:1: (lv_args_48_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2251:1: (lv_args_48_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2252:3: lv_args_48_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_11_4_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4900);
                    	    lv_args_48_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_48_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_49=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr4912); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_49, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_11_4_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt31 >= 1 ) break loop31;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
                    } while (true);

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:3: (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==69) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:5: otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) )
                            {
                            otherlv_50=(Token)match(input,69,FOLLOW_69_in_ruleAtomicExpr4927); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_50, grammarAccess.getAtomicExprAccess().getVerticalLineKeyword_11_5_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2276:1: ( (lv_filter_51_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2277:1: (lv_filter_51_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2277:1: (lv_filter_51_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2278:3: lv_filter_51_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFilterExprParserRuleCall_11_5_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4948);
                            lv_filter_51_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"filter",
                                      		lv_filter_51_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_52=(Token)match(input,17,FOLLOW_17_in_ruleAtomicExpr4962); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_52, grammarAccess.getAtomicExprAccess().getRightCurlyBracketKeyword_11_6());
                          
                    }

                    }


                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:6: (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:6: (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:8: otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')'
                    {
                    otherlv_53=(Token)match(input,40,FOLLOW_40_in_ruleAtomicExpr4982); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_53, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_12_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAtomicExprAccess().getExprParserRuleCall_12_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5004);
                    this_Expr_54=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Expr_54; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_55=(Token)match(input,42,FOLLOW_42_in_ruleAtomicExpr5015); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_55, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_12_2());
                          
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:1: entryRuleBuiltInFunc returns [String current=null] : iv_ruleBuiltInFunc= ruleBuiltInFunc EOF ;
    public final String entryRuleBuiltInFunc() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltInFunc = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2325:2: (iv_ruleBuiltInFunc= ruleBuiltInFunc EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2326:2: iv_ruleBuiltInFunc= ruleBuiltInFunc EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltInFuncRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5053);
            iv_ruleBuiltInFunc=ruleBuiltInFunc();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltInFunc.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltInFunc5064); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2333:1: ruleBuiltInFunc returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' ) ;
    public final AntlrDatatypeRuleToken ruleBuiltInFunc() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2336:28: ( (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2337:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2337:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' )
            int alt34=12;
            switch ( input.LA(1) ) {
            case 70:
                {
                alt34=1;
                }
                break;
            case 71:
                {
                alt34=2;
                }
                break;
            case 72:
                {
                alt34=3;
                }
                break;
            case 73:
                {
                alt34=4;
                }
                break;
            case 74:
                {
                alt34=5;
                }
                break;
            case 75:
                {
                alt34=6;
                }
                break;
            case 76:
                {
                alt34=7;
                }
                break;
            case 77:
                {
                alt34=8;
                }
                break;
            case 78:
                {
                alt34=9;
                }
                break;
            case 79:
                {
                alt34=10;
                }
                break;
            case 80:
                {
                alt34=11;
                }
                break;
            case 81:
                {
                alt34=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2338:2: kw= 'connected'
                    {
                    kw=(Token)match(input,70,FOLLOW_70_in_ruleBuiltInFunc5102); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConnectedKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2345:2: kw= 'property_lookup'
                    {
                    kw=(Token)match(input,71,FOLLOW_71_in_ruleBuiltInFunc5121); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_lookupKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2352:2: kw= 'class_of'
                    {
                    kw=(Token)match(input,72,FOLLOW_72_in_ruleBuiltInFunc5140); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getClass_ofKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2359:2: kw= 'type'
                    {
                    kw=(Token)match(input,73,FOLLOW_73_in_ruleBuiltInFunc5159); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getTypeKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2366:2: kw= 'has_type'
                    {
                    kw=(Token)match(input,74,FOLLOW_74_in_ruleBuiltInFunc5178); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getHas_typeKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2373:2: kw= 'bound'
                    {
                    kw=(Token)match(input,75,FOLLOW_75_in_ruleBuiltInFunc5197); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getBoundKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2380:2: kw= 'contained'
                    {
                    kw=(Token)match(input,76,FOLLOW_76_in_ruleBuiltInFunc5216); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getContainedKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2387:2: kw= 'conn_source'
                    {
                    kw=(Token)match(input,77,FOLLOW_77_in_ruleBuiltInFunc5235); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_sourceKeyword_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2394:2: kw= 'conn_dest'
                    {
                    kw=(Token)match(input,78,FOLLOW_78_in_ruleBuiltInFunc5254); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_destKeyword_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2401:2: kw= 'property_exists'
                    {
                    kw=(Token)match(input,79,FOLLOW_79_in_ruleBuiltInFunc5273); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_existsKeyword_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2408:2: kw= 'sum'
                    {
                    kw=(Token)match(input,80,FOLLOW_80_in_ruleBuiltInFunc5292); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSumKeyword_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2415:2: kw= 'analysis'
                    {
                    kw=(Token)match(input,81,FOLLOW_81_in_ruleBuiltInFunc5311); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getAnalysisKeyword_11()); 
                          
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2428:1: entryRuleRealTerm returns [EObject current=null] : iv_ruleRealTerm= ruleRealTerm EOF ;
    public final EObject entryRuleRealTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2429:2: (iv_ruleRealTerm= ruleRealTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2430:2: iv_ruleRealTerm= ruleRealTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealTermRule()); 
            }
            pushFollow(FOLLOW_ruleRealTerm_in_entryRuleRealTerm5351);
            iv_ruleRealTerm=ruleRealTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealTerm5361); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2437:1: ruleRealTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleRealTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2440:28: ( ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2441:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2441:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2441:2: ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2441:2: ( (lv_value_0_0= ruleUnsignedReal ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2442:1: (lv_value_0_0= ruleUnsignedReal )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2442:1: (lv_value_0_0= ruleUnsignedReal )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2443:3: lv_value_0_0= ruleUnsignedReal
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRealTermAccess().getValueUnsignedRealParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_ruleRealTerm5407);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2459:2: ( (otherlv_1= RULE_ID ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==EOF||LA35_1==RULE_ID||LA35_1==17||LA35_1==39||(LA35_1>=41 && LA35_1<=43)||(LA35_1>=45 && LA35_1<=58)||(LA35_1>=63 && LA35_1<=64)||LA35_1==68||LA35_1==82||LA35_1==85||LA35_1==88||(LA35_1>=97 && LA35_1<=98)) ) {
                    alt35=1;
                }
            }
            switch (alt35) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2460:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2460:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2461:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getRealTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRealTerm5427); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:1: entryRuleUnsignedReal returns [String current=null] : iv_ruleUnsignedReal= ruleUnsignedReal EOF ;
    public final String entryRuleUnsignedReal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedReal = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2481:2: (iv_ruleUnsignedReal= ruleUnsignedReal EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2482:2: iv_ruleUnsignedReal= ruleUnsignedReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedRealRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5465);
            iv_ruleUnsignedReal=ruleUnsignedReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedReal.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedReal5476); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2489:1: ruleUnsignedReal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_REAL_LIT_0= RULE_REAL_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedReal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2492:28: (this_REAL_LIT_0= RULE_REAL_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2493:5: this_REAL_LIT_0= RULE_REAL_LIT
            {
            this_REAL_LIT_0=(Token)match(input,RULE_REAL_LIT,FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5515); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2508:1: entryRuleIntegerTerm returns [EObject current=null] : iv_ruleIntegerTerm= ruleIntegerTerm EOF ;
    public final EObject entryRuleIntegerTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2509:2: (iv_ruleIntegerTerm= ruleIntegerTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2510:2: iv_ruleIntegerTerm= ruleIntegerTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerTermRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5559);
            iv_ruleIntegerTerm=ruleIntegerTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerTerm5569); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2517:1: ruleIntegerTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleIntegerTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2520:28: ( ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2521:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2521:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2521:2: ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2521:2: ( (lv_value_0_0= ruleUnsignedInt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2522:1: (lv_value_0_0= ruleUnsignedInt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2522:1: (lv_value_0_0= ruleUnsignedInt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2523:3: lv_value_0_0= ruleUnsignedInt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntegerTermAccess().getValueUnsignedIntParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5615);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2539:2: ( (otherlv_1= RULE_ID ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==EOF||LA36_1==RULE_ID||LA36_1==17||LA36_1==39||(LA36_1>=41 && LA36_1<=43)||(LA36_1>=45 && LA36_1<=58)||(LA36_1>=63 && LA36_1<=64)||LA36_1==68||LA36_1==82||LA36_1==85||LA36_1==88||(LA36_1>=97 && LA36_1<=98)) ) {
                    alt36=1;
                }
            }
            switch (alt36) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2540:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2540:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2541:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getIntegerTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIntegerTerm5635); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2560:1: entryRuleUnsignedInt returns [String current=null] : iv_ruleUnsignedInt= ruleUnsignedInt EOF ;
    public final String entryRuleUnsignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedInt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2561:2: (iv_ruleUnsignedInt= ruleUnsignedInt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2562:2: iv_ruleUnsignedInt= ruleUnsignedInt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedIntRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5673);
            iv_ruleUnsignedInt=ruleUnsignedInt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedInt.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedInt5684); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2569:1: ruleUnsignedInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2572:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2573:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5723); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2588:1: entryRuleResoluteSubclause returns [EObject current=null] : iv_ruleResoluteSubclause= ruleResoluteSubclause EOF ;
    public final EObject entryRuleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteSubclause = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2589:2: (iv_ruleResoluteSubclause= ruleResoluteSubclause EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2590:2: iv_ruleResoluteSubclause= ruleResoluteSubclause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getResoluteSubclauseRule()); 
            }
            pushFollow(FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5767);
            iv_ruleResoluteSubclause=ruleResoluteSubclause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleResoluteSubclause; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleResoluteSubclause5777); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2597:1: ruleResoluteSubclause returns [EObject current=null] : ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) ;
    public final EObject ruleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject lv_proves_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2600:28: ( ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2601:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2601:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2601:2: () ( (lv_proves_1_0= ruleProveStatement ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2601:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2602:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getResoluteSubclauseAccess().getResoluteSubclauseAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2607:2: ( (lv_proves_1_0= ruleProveStatement ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==82) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2608:1: (lv_proves_1_0= ruleProveStatement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2608:1: (lv_proves_1_0= ruleProveStatement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2609:3: lv_proves_1_0= ruleProveStatement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getResoluteSubclauseAccess().getProvesProveStatementParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5832);
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
            	    break loop37;
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


    // $ANTLR start "entryRuleProveStatement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2633:1: entryRuleProveStatement returns [EObject current=null] : iv_ruleProveStatement= ruleProveStatement EOF ;
    public final EObject entryRuleProveStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProveStatement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2634:2: (iv_ruleProveStatement= ruleProveStatement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2635:2: iv_ruleProveStatement= ruleProveStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProveStatementRule()); 
            }
            pushFollow(FOLLOW_ruleProveStatement_in_entryRuleProveStatement5869);
            iv_ruleProveStatement=ruleProveStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProveStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleProveStatement5879); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2642:1: ruleProveStatement returns [EObject current=null] : (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ) ;
    public final EObject ruleProveStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2645:28: ( (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2646:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2646:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2646:3: otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) )
            {
            otherlv_0=(Token)match(input,82,FOLLOW_82_in_ruleProveStatement5916); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getProveStatementAccess().getProveKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2650:1: ( (lv_expr_1_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2651:1: (lv_expr_1_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2651:1: (lv_expr_1_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2652:3: lv_expr_1_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getProveStatementAccess().getExprExprParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleProveStatement5937);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2678:1: entryRuleContainedPropertyAssociation returns [EObject current=null] : iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF ;
    public final EObject entryRuleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainedPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2679:2: (iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2680:2: iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainedPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation5975);
            iv_ruleContainedPropertyAssociation=ruleContainedPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainedPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainedPropertyAssociation5985); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2687:1: ruleContainedPropertyAssociation returns [EObject current=null] : ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2690:28: ( ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2691:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2691:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2691:2: ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2691:2: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2692:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2692:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2693:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getPropertyPropertyCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6033);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:2: (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==45) ) {
                alt38=1;
            }
            else if ( (LA38_0==83) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:4: otherlv_1= '=>'
                    {
                    otherlv_1=(Token)match(input,45,FOLLOW_45_in_ruleContainedPropertyAssociation6046); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getContainedPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:6: ( (lv_append_2_0= '+=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:6: ( (lv_append_2_0= '+=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2712:1: (lv_append_2_0= '+=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2712:1: (lv_append_2_0= '+=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2713:3: lv_append_2_0= '+=>'
                    {
                    lv_append_2_0=(Token)match(input,83,FOLLOW_83_in_ruleContainedPropertyAssociation6070); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2726:3: ( (lv_constant_3_0= 'constant' ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==84) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2727:1: (lv_constant_3_0= 'constant' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2727:1: (lv_constant_3_0= 'constant' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2728:3: lv_constant_3_0= 'constant'
                    {
                    lv_constant_3_0=(Token)match(input,84,FOLLOW_84_in_ruleContainedPropertyAssociation6102); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2741:3: ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2741:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2741:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2742:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2742:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2743:3: lv_ownedValue_4_0= ruleOptionalModalPropertyValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6138);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2759:2: (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==41) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2759:4: otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    {
            	    otherlv_5=(Token)match(input,41,FOLLOW_41_in_ruleContainedPropertyAssociation6151); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_3_1_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2763:1: ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2764:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2764:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2765:3: lv_ownedValue_6_0= ruleOptionalModalPropertyValue
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6172);
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
            	    break loop40;
                }
            } while (true);


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2781:5: (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==85) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2781:7: otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    {
                    otherlv_7=(Token)match(input,85,FOLLOW_85_in_ruleContainedPropertyAssociation6188); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getContainedPropertyAssociationAccess().getAppliesKeyword_4_0());
                          
                    }
                    otherlv_8=(Token)match(input,86,FOLLOW_86_in_ruleContainedPropertyAssociation6200); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getContainedPropertyAssociationAccess().getToKeyword_4_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2789:1: ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2790:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2790:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2791:3: lv_appliesTo_9_0= ruleContainmentPath
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6221);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2807:2: (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==41) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2807:4: otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    {
                    	    otherlv_10=(Token)match(input,41,FOLLOW_41_in_ruleContainedPropertyAssociation6234); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_4_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2811:1: ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2812:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2812:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2813:3: lv_appliesTo_11_0= ruleContainmentPath
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6255);
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
                    	    break loop41;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2829:6: (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==52) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2829:8: otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')'
                    {
                    otherlv_12=(Token)match(input,52,FOLLOW_52_in_ruleContainedPropertyAssociation6272); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getContainedPropertyAssociationAccess().getInKeyword_5_0());
                          
                    }
                    otherlv_13=(Token)match(input,87,FOLLOW_87_in_ruleContainedPropertyAssociation6284); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getContainedPropertyAssociationAccess().getBindingKeyword_5_1());
                          
                    }
                    otherlv_14=(Token)match(input,40,FOLLOW_40_in_ruleContainedPropertyAssociation6296); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getContainedPropertyAssociationAccess().getLeftParenthesisKeyword_5_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2841:1: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2842:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2842:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2843:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getInBindingClassifierCrossReference_5_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6319);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,42,FOLLOW_42_in_ruleContainedPropertyAssociation6331); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getContainedPropertyAssociationAccess().getRightParenthesisKeyword_5_4());
                          
                    }

                    }
                    break;

            }

            otherlv_17=(Token)match(input,88,FOLLOW_88_in_ruleContainedPropertyAssociation6345); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2876:1: entryRuleContainmentPath returns [EObject current=null] : iv_ruleContainmentPath= ruleContainmentPath EOF ;
    public final EObject entryRuleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPath = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2877:2: (iv_ruleContainmentPath= ruleContainmentPath EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2878:2: iv_ruleContainmentPath= ruleContainmentPath EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6385);
            iv_ruleContainmentPath=ruleContainmentPath();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPath; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPath6395); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2885:1: ruleContainmentPath returns [EObject current=null] : ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) ;
    public final EObject ruleContainmentPath() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_containmentPathElement_1_0 = null;

        EObject lv_containmentPathElement_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2888:28: ( ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2889:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2889:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2889:2: () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2889:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2890:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getContainmentPathAccess().getContainedNamedElementAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2895:2: ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2896:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2896:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2897:3: lv_containmentPathElement_1_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6450);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2913:2: (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==67) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2913:4: otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_2=(Token)match(input,67,FOLLOW_67_in_ruleContainmentPath6463); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getContainmentPathAccess().getFullStopKeyword_2_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2917:1: ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2918:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2918:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2919:3: lv_containmentPathElement_3_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6484);
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
            	    break loop44;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2945:1: entryRuleOptionalModalPropertyValue returns [EObject current=null] : iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF ;
    public final EObject entryRuleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalModalPropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2946:2: (iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2947:2: iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOptionalModalPropertyValueRule()); 
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6524);
            iv_ruleOptionalModalPropertyValue=ruleOptionalModalPropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOptionalModalPropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6534); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2954:1: ruleOptionalModalPropertyValue returns [EObject current=null] : ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2957:28: ( ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2958:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2958:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2958:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2958:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2959:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2959:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2960:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOptionalModalPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6580);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2976:2: (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==52) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==89) ) {
                    alt46=1;
                }
            }
            switch (alt46) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2976:4: otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')'
                    {
                    otherlv_1=(Token)match(input,52,FOLLOW_52_in_ruleOptionalModalPropertyValue6593); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOptionalModalPropertyValueAccess().getInKeyword_1_0());
                          
                    }
                    otherlv_2=(Token)match(input,89,FOLLOW_89_in_ruleOptionalModalPropertyValue6605); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOptionalModalPropertyValueAccess().getModesKeyword_1_1());
                          
                    }
                    otherlv_3=(Token)match(input,40,FOLLOW_40_in_ruleOptionalModalPropertyValue6617); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOptionalModalPropertyValueAccess().getLeftParenthesisKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2988:1: ( (otherlv_4= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2989:1: (otherlv_4= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2989:1: (otherlv_4= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2990:3: otherlv_4= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                      	        }
                              
                    }
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6637); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_4, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_3_0()); 
                      	
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3001:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==41) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3001:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,41,FOLLOW_41_in_ruleOptionalModalPropertyValue6650); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getOptionalModalPropertyValueAccess().getCommaKeyword_1_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3005:1: ( (otherlv_6= RULE_ID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3006:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3006:1: (otherlv_6= RULE_ID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3007:3: otherlv_6= RULE_ID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      			if (current==null) {
                    	      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                    	      	        }
                    	              
                    	    }
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6670); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		newLeafNode(otherlv_6, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_4_1_0()); 
                    	      	
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop45;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,42,FOLLOW_42_in_ruleOptionalModalPropertyValue6684); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3030:1: entryRulePropertyValue returns [EObject current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final EObject entryRulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3031:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3032:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyValueRule()); 
            }
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue6722);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue6732); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3039:1: rulePropertyValue returns [EObject current=null] : ( (lv_ownedValue_0_0= rulePropertyExpression ) ) ;
    public final EObject rulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedValue_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3042:28: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3043:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3043:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3044:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3044:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3045:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyValue6777);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3069:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3070:2: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3071:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression6812);
            iv_rulePropertyExpression=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression6822); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3078:1: rulePropertyExpression returns [EObject current=null] : (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3081:28: ( (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3082:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3082:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            int alt47=11;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3083:5: this_RecordTerm_0= ruleRecordTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRecordTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRecordTerm_in_rulePropertyExpression6869);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3093:5: this_ReferenceTerm_1= ruleReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getReferenceTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleReferenceTerm_in_rulePropertyExpression6896);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3103:5: this_ComponentClassifierTerm_2= ruleComponentClassifierTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComponentClassifierTermParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression6923);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3113:5: this_ComputedTerm_3= ruleComputedTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComputedTermParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComputedTerm_in_rulePropertyExpression6950);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3123:5: this_StringTerm_4= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getStringTermParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_rulePropertyExpression6977);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3133:5: this_NumericRangeTerm_5= ruleNumericRangeTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getNumericRangeTermParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7004);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3143:5: this_RealTerm_6= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRealTermParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_rulePropertyExpression7031);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3153:5: this_IntegerTerm_7= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getIntegerTermParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7058);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3163:5: this_ListTerm_8= ruleListTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getListTermParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListTerm_in_rulePropertyExpression7085);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3173:5: this_BooleanLiteral_9= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getBooleanLiteralParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7112);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3183:5: this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getLiteralorReferenceTermParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7139);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3199:1: entryRuleLiteralorReferenceTerm returns [EObject current=null] : iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF ;
    public final EObject entryRuleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralorReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3200:2: (iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3201:2: iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralorReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7174);
            iv_ruleLiteralorReferenceTerm=ruleLiteralorReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralorReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7184); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3208:1: ruleLiteralorReferenceTerm returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3211:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3212:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3212:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3213:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3213:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3214:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLiteralorReferenceTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLiteralorReferenceTermAccess().getNamedValueAbstractNamedValueCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7231);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3235:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3236:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3237:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7266);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral7276); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3244:1: ruleBooleanLiteral returns [EObject current=null] : ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3247:28: ( ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3248:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3248:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3248:2: () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3248:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3249:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3254:2: ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==90) ) {
                alt48=1;
            }
            else if ( (LA48_0==91) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3254:3: ( (lv_value_1_0= 'true' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3254:3: ( (lv_value_1_0= 'true' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3255:1: (lv_value_1_0= 'true' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3255:1: (lv_value_1_0= 'true' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3256:3: lv_value_1_0= 'true'
                    {
                    lv_value_1_0=(Token)match(input,90,FOLLOW_90_in_ruleBooleanLiteral7329); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3270:7: otherlv_2= 'false'
                    {
                    otherlv_2=(Token)match(input,91,FOLLOW_91_in_ruleBooleanLiteral7360); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3282:1: entryRuleConstantValue returns [EObject current=null] : iv_ruleConstantValue= ruleConstantValue EOF ;
    public final EObject entryRuleConstantValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3283:2: (iv_ruleConstantValue= ruleConstantValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3284:2: iv_ruleConstantValue= ruleConstantValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantValueRule()); 
            }
            pushFollow(FOLLOW_ruleConstantValue_in_entryRuleConstantValue7397);
            iv_ruleConstantValue=ruleConstantValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantValue7407); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3291:1: ruleConstantValue returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleConstantValue() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3294:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3295:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3295:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3296:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3296:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3297:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getConstantValueRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantValueAccess().getNamedValuePropertyConstantCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleConstantValue7454);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3318:1: entryRuleReferenceTerm returns [EObject current=null] : iv_ruleReferenceTerm= ruleReferenceTerm EOF ;
    public final EObject entryRuleReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3319:2: (iv_ruleReferenceTerm= ruleReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3320:2: iv_ruleReferenceTerm= ruleReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7489);
            iv_ruleReferenceTerm=ruleReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleReferenceTerm7499); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3327:1: ruleReferenceTerm returns [EObject current=null] : (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3330:28: ( (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3331:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3331:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3331:3: otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')'
            {
            otherlv_0=(Token)match(input,92,FOLLOW_92_in_ruleReferenceTerm7536); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getReferenceTermAccess().getReferenceKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleReferenceTerm7548); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getReferenceTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3339:1: ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RULE_ID) ) {
                int LA49_1 = input.LA(2);

                if ( (LA49_1==99) ) {
                    alt49=1;
                }
            }
            switch (alt49) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3339:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3339:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3340:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3340:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3341:3: lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementQualifiedContainmentPathElementParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7570);
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

                    otherlv_3=(Token)match(input,67,FOLLOW_67_in_ruleReferenceTerm7582); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getReferenceTermAccess().getFullStopKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3361:3: ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3362:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3362:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3363:3: lv_containmentPathElement_4_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7605);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3379:2: (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==67) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3379:4: otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_5=(Token)match(input,67,FOLLOW_67_in_ruleReferenceTerm7618); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getReferenceTermAccess().getFullStopKeyword_4_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3383:1: ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3384:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3384:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3385:3: lv_containmentPathElement_6_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7639);
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
            	    break loop50;
                }
            } while (true);

            otherlv_7=(Token)match(input,42,FOLLOW_42_in_ruleReferenceTerm7653); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3413:1: entryRuleRecordTerm returns [EObject current=null] : iv_ruleRecordTerm= ruleRecordTerm EOF ;
    public final EObject entryRuleRecordTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecordTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3414:2: (iv_ruleRecordTerm= ruleRecordTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3415:2: iv_ruleRecordTerm= ruleRecordTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRecordTermRule()); 
            }
            pushFollow(FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm7689);
            iv_ruleRecordTerm=ruleRecordTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRecordTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRecordTerm7699); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3422:1: ruleRecordTerm returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) ;
    public final EObject ruleRecordTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedFieldValue_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3425:28: ( (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3426:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3426:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3426:3: otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,93,FOLLOW_93_in_ruleRecordTerm7736); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getRecordTermAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3430:1: ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+
            int cnt51=0;
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==RULE_ID) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3431:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3431:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3432:3: lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRecordTermAccess().getOwnedFieldValueFieldPropertyAssociationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm7757);
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
            	    if ( cnt51 >= 1 ) break loop51;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(51, input);
                        throw eee;
                }
                cnt51++;
            } while (true);

            otherlv_2=(Token)match(input,94,FOLLOW_94_in_ruleRecordTerm7770); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3462:1: entryRuleComputedTerm returns [EObject current=null] : iv_ruleComputedTerm= ruleComputedTerm EOF ;
    public final EObject entryRuleComputedTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputedTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3463:2: (iv_ruleComputedTerm= ruleComputedTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3464:2: iv_ruleComputedTerm= ruleComputedTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComputedTermRule()); 
            }
            pushFollow(FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm7808);
            iv_ruleComputedTerm=ruleComputedTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComputedTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComputedTerm7818); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3471:1: ruleComputedTerm returns [EObject current=null] : (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleComputedTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_function_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3474:28: ( (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3475:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3475:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3475:3: otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,95,FOLLOW_95_in_ruleComputedTerm7855); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComputedTermAccess().getComputeKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleComputedTerm7867); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComputedTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3483:1: ( (lv_function_2_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3484:1: (lv_function_2_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3484:1: (lv_function_2_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3485:3: lv_function_2_0= RULE_ID
            {
            lv_function_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleComputedTerm7884); if (state.failed) return current;
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

            otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleComputedTerm7901); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3513:1: entryRuleComponentClassifierTerm returns [EObject current=null] : iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF ;
    public final EObject entryRuleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentClassifierTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3514:2: (iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3515:2: iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComponentClassifierTermRule()); 
            }
            pushFollow(FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm7937);
            iv_ruleComponentClassifierTerm=ruleComponentClassifierTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComponentClassifierTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComponentClassifierTerm7947); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3522:1: ruleComponentClassifierTerm returns [EObject current=null] : (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) ;
    public final EObject ruleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3525:28: ( (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3526:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3526:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3526:3: otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,96,FOLLOW_96_in_ruleComponentClassifierTerm7984); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComponentClassifierTermAccess().getClassifierKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleComponentClassifierTerm7996); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComponentClassifierTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3534:1: ( ( ruleQCREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3535:1: ( ruleQCREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3535:1: ( ruleQCREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3536:3: ruleQCREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getComponentClassifierTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getComponentClassifierTermAccess().getClassifierComponentClassifierCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8019);
            ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleComponentClassifierTerm8031); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3561:1: entryRuleListTerm returns [EObject current=null] : iv_ruleListTerm= ruleListTerm EOF ;
    public final EObject entryRuleListTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3562:2: (iv_ruleListTerm= ruleListTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:2: iv_ruleListTerm= ruleListTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListTermRule()); 
            }
            pushFollow(FOLLOW_ruleListTerm_in_entryRuleListTerm8067);
            iv_ruleListTerm=ruleListTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListTerm8077); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3570:1: ruleListTerm returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleListTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedListElement_2_0 = null;

        EObject lv_ownedListElement_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3573:28: ( ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3574:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3574:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3574:2: () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3574:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3575:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListTermAccess().getListValueAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,40,FOLLOW_40_in_ruleListTerm8123); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3584:1: ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=RULE_ID && LA53_0<=RULE_INTEGER_LIT)||LA53_0==40||(LA53_0>=53 && LA53_0<=54)||(LA53_0>=90 && LA53_0<=93)||(LA53_0>=95 && LA53_0<=96)) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3584:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3584:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3585:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3585:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3586:3: lv_ownedListElement_2_0= rulePropertyExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8145);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3602:2: (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==41) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3602:4: otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,41,FOLLOW_41_in_ruleListTerm8158); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListTermAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3606:1: ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3607:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3607:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3608:3: lv_ownedListElement_4_0= rulePropertyExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8179);
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
                    	    break loop52;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,42,FOLLOW_42_in_ruleListTerm8195); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3636:1: entryRuleFieldPropertyAssociation returns [EObject current=null] : iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF ;
    public final EObject entryRuleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFieldPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3637:2: (iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3638:2: iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFieldPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8231);
            iv_ruleFieldPropertyAssociation=ruleFieldPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFieldPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8241); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3645:1: ruleFieldPropertyAssociation returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedValue_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3648:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3650:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3650:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3651:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getFieldPropertyAssociationRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8286); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getFieldPropertyAssociationAccess().getPropertyBasicPropertyCrossReference_0_0()); 
              	
            }

            }


            }

            otherlv_1=(Token)match(input,45,FOLLOW_45_in_ruleFieldPropertyAssociation8298); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFieldPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3666:1: ( (lv_ownedValue_2_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3667:1: (lv_ownedValue_2_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3667:1: (lv_ownedValue_2_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3668:3: lv_ownedValue_2_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFieldPropertyAssociationAccess().getOwnedValuePropertyExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8319);
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

            otherlv_3=(Token)match(input,88,FOLLOW_88_in_ruleFieldPropertyAssociation8331); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3696:1: entryRuleContainmentPathElement returns [EObject current=null] : iv_ruleContainmentPathElement= ruleContainmentPathElement EOF ;
    public final EObject entryRuleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3697:2: (iv_ruleContainmentPathElement= ruleContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3698:2: iv_ruleContainmentPathElement= ruleContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8367);
            iv_ruleContainmentPathElement=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPathElement8377); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3705:1: ruleContainmentPathElement returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) ;
    public final EObject ruleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_arrayRange_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3708:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:2: ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3710:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3710:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3711:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainmentPathElementRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleContainmentPathElement8422); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:2: ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==93) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3723:1: (lv_arrayRange_1_0= ruleArrayRange )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3723:1: (lv_arrayRange_1_0= ruleArrayRange )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3724:3: lv_arrayRange_1_0= ruleArrayRange
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainmentPathElementAccess().getArrayRangeArrayRangeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8443);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3748:1: entryRuleQualifiedContainmentPathElement returns [EObject current=null] : iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF ;
    public final EObject entryRuleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3749:2: (iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3750:2: iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8480);
            iv_ruleQualifiedContainmentPathElement=ruleQualifiedContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8490); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3757:1: ruleQualifiedContainmentPathElement returns [EObject current=null] : ( ( ruleQCLREF ) ) ;
    public final EObject ruleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3760:28: ( ( ( ruleQCLREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3761:1: ( ( ruleQCLREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3761:1: ( ( ruleQCLREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3762:1: ( ruleQCLREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3762:1: ( ruleQCLREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3763:3: ruleQCLREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getQualifiedContainmentPathElementRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getQualifiedContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8537);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3786:1: entryRulePlusMinus returns [String current=null] : iv_rulePlusMinus= rulePlusMinus EOF ;
    public final String entryRulePlusMinus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePlusMinus = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3787:2: (iv_rulePlusMinus= rulePlusMinus EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3788:2: iv_rulePlusMinus= rulePlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusMinusRule()); 
            }
            pushFollow(FOLLOW_rulePlusMinus_in_entryRulePlusMinus8575);
            iv_rulePlusMinus=rulePlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusMinus.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusMinus8586); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3795:1: rulePlusMinus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken rulePlusMinus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3798:28: ( (kw= '+' | kw= '-' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3799:1: (kw= '+' | kw= '-' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3799:1: (kw= '+' | kw= '-' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==53) ) {
                alt55=1;
            }
            else if ( (LA55_0==54) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3800:2: kw= '+'
                    {
                    kw=(Token)match(input,53,FOLLOW_53_in_rulePlusMinus8624); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPlusMinusAccess().getPlusSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3807:2: kw= '-'
                    {
                    kw=(Token)match(input,54,FOLLOW_54_in_rulePlusMinus8643); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3820:1: entryRuleStringTerm returns [EObject current=null] : iv_ruleStringTerm= ruleStringTerm EOF ;
    public final EObject entryRuleStringTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3821:2: (iv_ruleStringTerm= ruleStringTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3822:2: iv_ruleStringTerm= ruleStringTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringTermRule()); 
            }
            pushFollow(FOLLOW_ruleStringTerm_in_entryRuleStringTerm8683);
            iv_ruleStringTerm=ruleStringTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringTerm8693); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3829:1: ruleStringTerm returns [EObject current=null] : ( (lv_value_0_0= ruleNoQuoteString ) ) ;
    public final EObject ruleStringTerm() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3832:28: ( ( (lv_value_0_0= ruleNoQuoteString ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3833:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3833:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3834:1: (lv_value_0_0= ruleNoQuoteString )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3834:1: (lv_value_0_0= ruleNoQuoteString )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3835:3: lv_value_0_0= ruleNoQuoteString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStringTermAccess().getValueNoQuoteStringParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_ruleStringTerm8738);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3859:1: entryRuleNoQuoteString returns [String current=null] : iv_ruleNoQuoteString= ruleNoQuoteString EOF ;
    public final String entryRuleNoQuoteString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNoQuoteString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3860:2: (iv_ruleNoQuoteString= ruleNoQuoteString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3861:2: iv_ruleNoQuoteString= ruleNoQuoteString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoQuoteStringRule()); 
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString8774);
            iv_ruleNoQuoteString=ruleNoQuoteString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoQuoteString.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoQuoteString8785); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3868:1: ruleNoQuoteString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNoQuoteString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3871:28: (this_STRING_0= RULE_STRING )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3872:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNoQuoteString8824); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3887:1: entryRuleArrayRange returns [EObject current=null] : iv_ruleArrayRange= ruleArrayRange EOF ;
    public final EObject entryRuleArrayRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayRange = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3888:2: (iv_ruleArrayRange= ruleArrayRange EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3889:2: iv_ruleArrayRange= ruleArrayRange EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayRangeRule()); 
            }
            pushFollow(FOLLOW_ruleArrayRange_in_entryRuleArrayRange8868);
            iv_ruleArrayRange=ruleArrayRange();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayRange; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayRange8878); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3896:1: ruleArrayRange returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleArrayRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_lowerBound_2_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3899:28: ( ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3900:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3900:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3900:2: () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3900:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3901:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayRangeAccess().getArrayRangeAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,93,FOLLOW_93_in_ruleArrayRange8924); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayRangeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3910:1: ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3911:1: (lv_lowerBound_2_0= ruleINTVALUE )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3911:1: (lv_lowerBound_2_0= ruleINTVALUE )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3912:3: lv_lowerBound_2_0= ruleINTVALUE
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayRangeAccess().getLowerBoundINTVALUEParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange8945);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3928:2: (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==97) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3928:4: otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    {
                    otherlv_3=(Token)match(input,97,FOLLOW_97_in_ruleArrayRange8958); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getArrayRangeAccess().getFullStopFullStopKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3932:1: ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3933:1: (lv_upperBound_4_0= ruleINTVALUE )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3933:1: (lv_upperBound_4_0= ruleINTVALUE )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3934:3: lv_upperBound_4_0= ruleINTVALUE
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayRangeAccess().getUpperBoundINTVALUEParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange8979);
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

            otherlv_5=(Token)match(input,94,FOLLOW_94_in_ruleArrayRange8993); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3962:1: entryRuleSignedConstant returns [EObject current=null] : iv_ruleSignedConstant= ruleSignedConstant EOF ;
    public final EObject entryRuleSignedConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedConstant = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3963:2: (iv_ruleSignedConstant= ruleSignedConstant EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3964:2: iv_ruleSignedConstant= ruleSignedConstant EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSignedConstantRule()); 
            }
            pushFollow(FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9029);
            iv_ruleSignedConstant=ruleSignedConstant();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSignedConstant; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSignedConstant9039); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3971:1: ruleSignedConstant returns [EObject current=null] : ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) ;
    public final EObject ruleSignedConstant() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_0_0 = null;

        EObject lv_ownedPropertyExpression_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3974:28: ( ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3975:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3975:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3975:2: ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3975:2: ( (lv_op_0_0= rulePlusMinus ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3976:1: (lv_op_0_0= rulePlusMinus )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3976:1: (lv_op_0_0= rulePlusMinus )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3977:3: lv_op_0_0= rulePlusMinus
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOpPlusMinusParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePlusMinus_in_ruleSignedConstant9085);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3993:2: ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3994:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3994:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3995:3: lv_ownedPropertyExpression_1_0= ruleConstantValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOwnedPropertyExpressionConstantValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleConstantValue_in_ruleSignedConstant9106);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4023:1: entryRuleNumericRangeTerm returns [EObject current=null] : iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF ;
    public final EObject entryRuleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericRangeTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4024:2: (iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4025:2: iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumericRangeTermRule()); 
            }
            pushFollow(FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9146);
            iv_ruleNumericRangeTerm=ruleNumericRangeTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumericRangeTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumericRangeTerm9156); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4032:1: ruleNumericRangeTerm returns [EObject current=null] : ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) ;
    public final EObject ruleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_minimum_0_0 = null;

        EObject lv_maximum_2_0 = null;

        EObject lv_delta_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4035:28: ( ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:2: ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:2: ( (lv_minimum_0_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4037:1: (lv_minimum_0_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4037:1: (lv_minimum_0_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4038:3: lv_minimum_0_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMinimumNumAltParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9202);
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

            otherlv_1=(Token)match(input,97,FOLLOW_97_in_ruleNumericRangeTerm9214); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNumericRangeTermAccess().getFullStopFullStopKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4058:1: ( (lv_maximum_2_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4059:1: (lv_maximum_2_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4059:1: (lv_maximum_2_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4060:3: lv_maximum_2_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMaximumNumAltParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9235);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4076:2: (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==98) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4076:4: otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) )
                    {
                    otherlv_3=(Token)match(input,98,FOLLOW_98_in_ruleNumericRangeTerm9248); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getNumericRangeTermAccess().getDeltaKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4080:1: ( (lv_delta_4_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4081:1: (lv_delta_4_0= ruleNumAlt )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4081:1: (lv_delta_4_0= ruleNumAlt )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4082:3: lv_delta_4_0= ruleNumAlt
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getDeltaNumAltParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9269);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4106:1: entryRuleNumAlt returns [EObject current=null] : iv_ruleNumAlt= ruleNumAlt EOF ;
    public final EObject entryRuleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumAlt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4107:2: (iv_ruleNumAlt= ruleNumAlt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4108:2: iv_ruleNumAlt= ruleNumAlt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumAltRule()); 
            }
            pushFollow(FOLLOW_ruleNumAlt_in_entryRuleNumAlt9307);
            iv_ruleNumAlt=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumAlt; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumAlt9317); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4115:1: ruleNumAlt returns [EObject current=null] : (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) ;
    public final EObject ruleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject this_RealTerm_0 = null;

        EObject this_IntegerTerm_1 = null;

        EObject this_SignedConstant_2 = null;

        EObject this_ConstantValue_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4118:28: ( (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4119:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4119:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            int alt58=4;
            switch ( input.LA(1) ) {
            case RULE_REAL_LIT:
                {
                alt58=1;
                }
                break;
            case RULE_INTEGER_LIT:
                {
                alt58=2;
                }
                break;
            case 53:
            case 54:
                {
                alt58=3;
                }
                break;
            case RULE_ID:
                {
                alt58=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4120:5: this_RealTerm_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getRealTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleNumAlt9364);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4130:5: this_IntegerTerm_1= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getIntegerTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleNumAlt9391);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4140:5: this_SignedConstant_2= ruleSignedConstant
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getSignedConstantParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSignedConstant_in_ruleNumAlt9418);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4150:5: this_ConstantValue_3= ruleConstantValue
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getConstantValueParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstantValue_in_ruleNumAlt9445);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4166:1: entryRuleINTVALUE returns [String current=null] : iv_ruleINTVALUE= ruleINTVALUE EOF ;
    public final String entryRuleINTVALUE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleINTVALUE = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4167:2: (iv_ruleINTVALUE= ruleINTVALUE EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4168:2: iv_ruleINTVALUE= ruleINTVALUE EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getINTVALUERule()); 
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9481);
            iv_ruleINTVALUE=ruleINTVALUE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleINTVALUE.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleINTVALUE9492); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4175:1: ruleINTVALUE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleINTVALUE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4178:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4179:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9531); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4194:1: entryRuleQCLREF returns [String current=null] : iv_ruleQCLREF= ruleQCLREF EOF ;
    public final String entryRuleQCLREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCLREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4195:2: (iv_ruleQCLREF= ruleQCLREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4196:2: iv_ruleQCLREF= ruleQCLREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCLREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCLREF_in_entryRuleQCLREF9576);
            iv_ruleQCLREF=ruleQCLREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCLREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCLREF9587); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4203:1: ruleQCLREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQCLREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4206:28: ( (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4207:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4207:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4207:6: this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF9627); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQCLREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            kw=(Token)match(input,99,FOLLOW_99_in_ruleQCLREF9645); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getQCLREFAccess().getColonColonKeyword_1()); 
                  
            }
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF9660); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4235:1: entryRuleQPREF returns [String current=null] : iv_ruleQPREF= ruleQPREF EOF ;
    public final String entryRuleQPREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQPREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4236:2: (iv_ruleQPREF= ruleQPREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4237:2: iv_ruleQPREF= ruleQPREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQPREFRule()); 
            }
            pushFollow(FOLLOW_ruleQPREF_in_entryRuleQPREF9706);
            iv_ruleQPREF=ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQPREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQPREF9717); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4244:1: ruleQPREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQPREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4247:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4248:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4248:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4248:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )?
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF9757); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQPREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4255:1: (kw= '::' this_ID_2= RULE_ID )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==99) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4256:2: kw= '::' this_ID_2= RULE_ID
                    {
                    kw=(Token)match(input,99,FOLLOW_99_in_ruleQPREF9776); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQPREFAccess().getColonColonKeyword_1_0()); 
                          
                    }
                    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF9791); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4276:1: entryRuleQCREF returns [String current=null] : iv_ruleQCREF= ruleQCREF EOF ;
    public final String entryRuleQCREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4277:2: (iv_ruleQCREF= ruleQCREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4278:2: iv_ruleQCREF= ruleQCREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCREF_in_entryRuleQCREF9839);
            iv_ruleQCREF=ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCREF9850); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4285:1: ruleQCREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQCREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4288:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4289:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4289:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4289:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4289:2: (this_ID_0= RULE_ID kw= '::' )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==RULE_ID) ) {
                    int LA60_1 = input.LA(2);

                    if ( (LA60_1==99) ) {
                        alt60=1;
                    }


                }


                switch (alt60) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4289:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF9891); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_0);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_0, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_0_0()); 
            	          
            	    }
            	    kw=(Token)match(input,99,FOLLOW_99_in_ruleQCREF9909); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQCREFAccess().getColonColonKeyword_0_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF9926); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_2, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_1()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4309:1: (kw= '.' this_ID_4= RULE_ID )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==67) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4310:2: kw= '.' this_ID_4= RULE_ID
                    {
                    kw=(Token)match(input,67,FOLLOW_67_in_ruleQCREF9945); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQCREFAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF9960); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4330:1: entryRuleSTAR returns [String current=null] : iv_ruleSTAR= ruleSTAR EOF ;
    public final String entryRuleSTAR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSTAR = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4331:2: (iv_ruleSTAR= ruleSTAR EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4332:2: iv_ruleSTAR= ruleSTAR EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSTARRule()); 
            }
            pushFollow(FOLLOW_ruleSTAR_in_entryRuleSTAR10008);
            iv_ruleSTAR=ruleSTAR();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSTAR.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSTAR10019); if (state.failed) return current;

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4339:1: ruleSTAR returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken ruleSTAR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4342:28: (kw= '*' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4344:2: kw= '*'
            {
            kw=(Token)match(input,55,FOLLOW_55_in_ruleSTAR10056); if (state.failed) return current;
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
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA47 dfa47 = new DFA47(this);
    static final String DFA15_eotS =
        "\12\uffff";
    static final String DFA15_eofS =
        "\1\10\11\uffff";
    static final String DFA15_minS =
        "\1\4\7\0\2\uffff";
    static final String DFA15_maxS =
        "\1\122\7\0\2\uffff";
    static final String DFA15_acceptS =
        "\10\uffff\1\2\1\1";
    static final String DFA15_specialS =
        "\1\uffff\1\6\1\5\1\1\1\0\1\4\1\3\1\2\2\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\10\14\uffff\1\10\25\uffff\1\5\1\uffff\2\10\1\2\1\uffff\3"+
            "\10\1\1\1\3\1\4\1\6\1\7\6\10\4\uffff\2\10\3\uffff\1\10\15\uffff"+
            "\1\10",
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
                        int LA15_4 = input.LA(1);

                         
                        int index15_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_4);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_3 = input.LA(1);

                         
                        int index15_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_3);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_7 = input.LA(1);

                         
                        int index15_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_7);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA15_6 = input.LA(1);

                         
                        int index15_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_6);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA15_5 = input.LA(1);

                         
                        int index15_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA15_2 = input.LA(1);

                         
                        int index15_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_2);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA15_1 = input.LA(1);

                         
                        int index15_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_1);
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
    static final String DFA33_eotS =
        "\17\uffff";
    static final String DFA33_eofS =
        "\1\uffff\1\15\15\uffff";
    static final String DFA33_minS =
        "\2\4\15\uffff";
    static final String DFA33_maxS =
        "\1\133\1\143\15\uffff";
    static final String DFA33_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\14\1\15\1\1\1"+
        "\13";
    static final String DFA33_specialS =
        "\17\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1\1\7\1\5\1\4\10\uffff\1\13\27\uffff\1\14\23\uffff\1\2\1"+
            "\3\1\10\2\uffff\2\11\3\uffff\14\12\10\uffff\2\6",
            "\1\15\14\uffff\1\15\25\uffff\1\15\1\16\3\15\1\uffff\16\15"+
            "\4\uffff\2\15\2\uffff\2\15\15\uffff\1\15\20\uffff\1\15",
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

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "1753:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) )";
        }
    }
    static final String DFA47_eotS =
        "\23\uffff";
    static final String DFA47_eofS =
        "\6\uffff\1\15\1\17\1\uffff\1\21\2\uffff\1\15\1\uffff\1\17\3\uffff"+
        "\1\21";
    static final String DFA47_minS =
        "\1\4\5\uffff\2\4\1\uffff\1\51\2\uffff\1\51\1\uffff\1\51\1\uffff"+
        "\1\4\1\uffff\1\51";
    static final String DFA47_maxS =
        "\1\140\5\uffff\2\141\1\uffff\1\143\2\uffff\1\141\1\uffff\1\141"+
        "\1\uffff\1\4\1\uffff\1\141";
    static final String DFA47_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\6\1\uffff\1\11\1\12\1\uffff"+
        "\1\7\1\uffff\1\10\1\uffff\1\13\1\uffff";
    static final String DFA47_specialS =
        "\23\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\11\1\5\1\6\1\7\40\uffff\1\12\14\uffff\2\10\43\uffff\2\13"+
            "\1\2\1\1\1\uffff\1\4\1\3",
            "",
            "",
            "",
            "",
            "",
            "\1\14\44\uffff\2\15\11\uffff\1\15\40\uffff\1\15\2\uffff\1"+
            "\15\10\uffff\1\10",
            "\1\16\44\uffff\2\17\11\uffff\1\17\40\uffff\1\17\2\uffff\1"+
            "\17\10\uffff\1\10",
            "",
            "\2\21\11\uffff\1\21\40\uffff\1\21\2\uffff\1\21\10\uffff\1"+
            "\10\1\uffff\1\20",
            "",
            "",
            "\2\15\11\uffff\1\15\40\uffff\1\15\2\uffff\1\15\10\uffff\1"+
            "\10",
            "",
            "\2\17\11\uffff\1\17\40\uffff\1\17\2\uffff\1\17\10\uffff\1"+
            "\10",
            "",
            "\1\22",
            "",
            "\2\21\11\uffff\1\21\40\uffff\1\21\2\uffff\1\21\10\uffff\1"+
            "\10"
    };

    static final short[] DFA47_eot = DFA.unpackEncodedString(DFA47_eotS);
    static final short[] DFA47_eof = DFA.unpackEncodedString(DFA47_eofS);
    static final char[] DFA47_min = DFA.unpackEncodedStringToUnsignedChars(DFA47_minS);
    static final char[] DFA47_max = DFA.unpackEncodedStringToUnsignedChars(DFA47_maxS);
    static final short[] DFA47_accept = DFA.unpackEncodedString(DFA47_acceptS);
    static final short[] DFA47_special = DFA.unpackEncodedString(DFA47_specialS);
    static final short[][] DFA47_transition;

    static {
        int numStates = DFA47_transitionS.length;
        DFA47_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA47_transition[i] = DFA.unpackEncodedString(DFA47_transitionS[i]);
        }
    }

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = DFA47_eot;
            this.eof = DFA47_eof;
            this.min = DFA47_min;
            this.max = DFA47_max;
            this.accept = DFA47_accept;
            this.special = DFA47_special;
            this.transition = DFA47_transition;
        }
        public String getDescription() {
            return "3082:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )";
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
    public static final BitSet FOLLOW_39_in_ruleConstantDefinition1432 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
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
    public static final BitSet FOLLOW_39_in_ruleDefinitionBody1918 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody1939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleDefinitionBody1968 = new BitSet(new long[]{0x78401100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody1981 = new BitSet(new long[]{0x0000100000000030L});
    public static final BitSet FOLLOW_ruleClaimString_in_ruleDefinitionBody2002 = new BitSet(new long[]{0x0000100000000030L});
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody2015 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr2075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleExpr2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2165 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpr2175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_ruleImpliesExpr2222 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleImpliesExpr2273 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr2309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_entryRuleOrExpr2347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpr2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2404 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_ruleOrExpr2455 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2491 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_entryRuleAndExpr2529 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpr2539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr2586 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_ruleAndExpr2637 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
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
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3021 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr3044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_entryRulePlusExpr3082 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusExpr3092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3139 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_53_in_rulePlusExpr3211 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_54_in_rulePlusExpr3240 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3279 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr3317 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTimesExpr3327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3374 = new BitSet(new long[]{0x0180000000000002L});
    public static final BitSet FOLLOW_55_in_ruleTimesExpr3446 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_56_in_ruleTimesExpr3475 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3514 = new BitSet(new long[]{0x0180000000000002L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr3552 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSetOpExpr3562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3609 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_57_in_ruleSetOpExpr3681 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_58_in_ruleSetOpExpr3710 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3749 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr3787 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrefixExpr3797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulePrefixExpr3852 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_59_in_rulePrefixExpr3881 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr3918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_rulePrefixExpr3947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr3982 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomicExpr3992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleAtomicExpr4050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleAtomicExpr4079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleAtomicExpr4108 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleAtomicExpr4206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleAtomicExpr4311 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4332 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleAtomicExpr4344 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4365 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleAtomicExpr4377 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr4435 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr4464 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4493 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4514 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4526 = new BitSet(new long[]{0x0000010000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr4540 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4599 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4611 = new BitSet(new long[]{0x78400500000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4633 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4646 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4667 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAtomicExpr4720 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4732 = new BitSet(new long[]{0x78400500000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4754 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4767 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4788 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr4833 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4854 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleAtomicExpr4866 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4879 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4900 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4912 = new BitSet(new long[]{0x0000010000020000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleAtomicExpr4927 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4948 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAtomicExpr4962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4982 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5004 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr5015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5053 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltInFunc5064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleBuiltInFunc5102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleBuiltInFunc5121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleBuiltInFunc5140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleBuiltInFunc5159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBuiltInFunc5178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleBuiltInFunc5197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleBuiltInFunc5216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleBuiltInFunc5235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleBuiltInFunc5254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleBuiltInFunc5273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleBuiltInFunc5292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleBuiltInFunc5311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_entryRuleRealTerm5351 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealTerm5361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_ruleRealTerm5407 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRealTerm5427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5465 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedReal5476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5559 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerTerm5569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5615 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIntegerTerm5635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5673 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedInt5684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5767 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteSubclause5777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5832 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_ruleProveStatement_in_entryRuleProveStatement5869 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProveStatement5879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleProveStatement5916 = new BitSet(new long[]{0x78400100000100F0L,0x000000000C03FFC6L});
    public static final BitSet FOLLOW_ruleExpr_in_ruleProveStatement5937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation5975 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainedPropertyAssociation5985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6033 = new BitSet(new long[]{0x0000200000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_45_in_ruleContainedPropertyAssociation6046 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_83_in_ruleContainedPropertyAssociation6070 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_84_in_ruleContainedPropertyAssociation6102 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6138 = new BitSet(new long[]{0x0010020000000000L,0x0000000001200000L});
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6151 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6172 = new BitSet(new long[]{0x0010020000000000L,0x0000000001200000L});
    public static final BitSet FOLLOW_85_in_ruleContainedPropertyAssociation6188 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_ruleContainedPropertyAssociation6200 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6221 = new BitSet(new long[]{0x0010020000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6234 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6255 = new BitSet(new long[]{0x0010020000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_52_in_ruleContainedPropertyAssociation6272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_ruleContainedPropertyAssociation6284 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleContainedPropertyAssociation6296 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6319 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleContainedPropertyAssociation6331 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleContainedPropertyAssociation6345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6385 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPath6395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6450 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleContainmentPath6463 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6484 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6524 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6580 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_52_in_ruleOptionalModalPropertyValue6593 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_ruleOptionalModalPropertyValue6605 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleOptionalModalPropertyValue6617 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6637 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleOptionalModalPropertyValue6650 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6670 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleOptionalModalPropertyValue6684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue6722 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue6732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyValue6777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression6812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression6822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_rulePropertyExpression6869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_rulePropertyExpression6896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression6923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_rulePropertyExpression6950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_rulePropertyExpression6977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_rulePropertyExpression7031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_rulePropertyExpression7085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7266 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral7276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleBooleanLiteral7329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleBooleanLiteral7360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_entryRuleConstantValue7397 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantValue7407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleConstantValue7454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7489 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReferenceTerm7499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleReferenceTerm7536 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleReferenceTerm7548 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleReferenceTerm7582 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7605 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleReferenceTerm7618 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7639 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_42_in_ruleReferenceTerm7653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm7689 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRecordTerm7699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleRecordTerm7736 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm7757 = new BitSet(new long[]{0x0000000000000010L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleRecordTerm7770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm7808 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComputedTerm7818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleComputedTerm7855 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleComputedTerm7867 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleComputedTerm7884 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleComputedTerm7901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm7937 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComponentClassifierTerm7947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleComponentClassifierTerm7984 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleComponentClassifierTerm7996 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8019 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleComponentClassifierTerm8031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_entryRuleListTerm8067 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListTerm8077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleListTerm8123 = new BitSet(new long[]{0x00600500000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8145 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_41_in_ruleListTerm8158 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8179 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_42_in_ruleListTerm8195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8231 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8286 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleFieldPropertyAssociation8298 = new BitSet(new long[]{0x00600100000000F0L,0x00000001BC100000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8319 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleFieldPropertyAssociation8331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8367 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPathElement8377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleContainmentPathElement8422 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_entryRulePlusMinus8575 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusMinus8586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulePlusMinus8624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulePlusMinus8643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_entryRuleStringTerm8683 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringTerm8693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_ruleStringTerm8738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString8774 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoQuoteString8785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNoQuoteString8824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayRange_in_entryRuleArrayRange8868 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayRange8878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleArrayRange8924 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange8945 = new BitSet(new long[]{0x0000000000000000L,0x0000000240000000L});
    public static final BitSet FOLLOW_97_in_ruleArrayRange8958 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange8979 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleArrayRange8993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9029 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSignedConstant9039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_ruleSignedConstant9085 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleSignedConstant9106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9146 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumericRangeTerm9156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9202 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_97_in_ruleNumericRangeTerm9214 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9235 = new BitSet(new long[]{0x0000000000000002L,0x0000000400000000L});
    public static final BitSet FOLLOW_98_in_ruleNumericRangeTerm9248 = new BitSet(new long[]{0x00600000000000D0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_entryRuleNumAlt9307 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumAlt9317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleNumAlt9364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleNumAlt9391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_ruleNumAlt9418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleNumAlt9445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleINTVALUE9492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_entryRuleQCLREF9576 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCLREF9587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF9627 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_ruleQCLREF9645 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF9660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_entryRuleQPREF9706 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQPREF9717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF9757 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_ruleQPREF9776 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF9791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_entryRuleQCREF9839 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCREF9850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9891 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_99_in_ruleQCREF9909 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9926 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleQCREF9945 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTAR_in_entryRuleSTAR10008 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSTAR10019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleSTAR10056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred1_InternalResolute2242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred2_InternalResolute2424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_synpred3_InternalResolute2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred4_InternalResolute2991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred5_InternalResolute3158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred6_InternalResolute3393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred7_InternalResolute3628 = new BitSet(new long[]{0x0000000000000002L});

}