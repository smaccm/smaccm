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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_REAL_LIT", "RULE_INTEGER_LIT", "RULE_SL_COMMENT", "RULE_DIGIT", "RULE_EXPONENT", "RULE_INT_EXPONENT", "RULE_BASED_INTEGER", "RULE_EXTENDED_DIGIT", "RULE_ANNEXTEXT", "RULE_WS", "'{'", "'}'", "'threads'", "'data'", "'memory'", "'thread_groups'", "'processes'", "'subprograms'", "'subprogram_groups'", "'processors'", "'virtual_processors'", "'buses'", "'virtual_buses'", "'devices'", "'systems'", "'abstracts'", "'connections'", "'components'", "'features'", "'thread'", "'thread_group'", "'process'", "'subprogram'", "'subprogram_group'", "'processor'", "'virtual_processor'", "'bus'", "'virtual_bus'", "'device'", "'system'", "'abstract'", "'aadl'", "'feature'", "'connection'", "'component'", "'string'", "'int'", "'real'", "'bool'", "':'", "'='", "'('", "','", "')'", "'<='", "'**'", "'=>'", "'or'", "'and'", "'<'", "'>'", "'>='", "'<>'", "'in'", "'+'", "'-'", "'*'", "'/'", "'union'", "'intersect'", "'not'", "'.'", "'this'", "'fail'", "'if'", "'then'", "'else'", "'forall'", "'exists'", "'for'", "'|'", "'let'", "';'", "'connected'", "'property_lookup'", "'class_of'", "'subcomponent_of'", "'name'", "'type'", "'has_type'", "'is_connected'", "'bound'", "'contained'", "'conn_source'", "'conn_dest'", "'property_exists'", "'sum'", "'analysis'", "'instance'", "'instances'", "'upper_bound'", "'lower_bound'", "'receive_error'", "'contain_error'", "'propagate_error'", "'error_state_reachable'", "'is_data'", "'is_thread'", "'is_thread_group'", "'is_process'", "'is_subprogram'", "'is_subprogram_group'", "'is_processor'", "'is_virtual_processor'", "'is_memory'", "'is_bus'", "'is_virtual_bus'", "'is_device'", "'is_system'", "'is_abstract'", "'is_empty'", "'singleton'", "'is_bidirectional'", "'is_data_access'", "'is_bus_access'", "'is_data_port'", "'is_event_port'", "'prove'", "'modes'", "'+=>'", "'constant'", "'applies'", "'to'", "'binding'", "'true'", "'false'", "'reference'", "'['", "']'", "'compute'", "'classifier'", "'..'", "'delta'", "'::'"
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
    public static final int T__148=148;
    public static final int T__16=16;
    public static final int T__147=147;
    public static final int T__90=90;
    public static final int T__18=18;
    public static final int T__149=149;
    public static final int T__17=17;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int RULE_EXTENDED_DIGIT=13;
    public static final int T__95=95;
    public static final int T__139=139;
    public static final int T__138=138;
    public static final int T__137=137;
    public static final int T__136=136;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__141=141;
    public static final int T__85=85;
    public static final int T__142=142;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__140=140;
    public static final int T__86=86;
    public static final int T__145=145;
    public static final int T__89=89;
    public static final int T__146=146;
    public static final int T__88=88;
    public static final int T__143=143;
    public static final int T__144=144;
    public static final int T__126=126;
    public static final int T__125=125;
    public static final int T__128=128;
    public static final int RULE_STRING=5;
    public static final int T__127=127;
    public static final int T__71=71;
    public static final int T__129=129;
    public static final int T__72=72;
    public static final int T__70=70;
    public static final int RULE_EXPONENT=10;
    public static final int T__76=76;
    public static final int RULE_DIGIT=9;
    public static final int T__75=75;
    public static final int T__130=130;
    public static final int T__74=74;
    public static final int T__131=131;
    public static final int T__73=73;
    public static final int T__132=132;
    public static final int T__133=133;
    public static final int T__79=79;
    public static final int T__134=134;
    public static final int T__78=78;
    public static final int T__135=135;
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
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int T__122=122;
    public static final int T__121=121;
    public static final int T__120=120;
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
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
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

                if ( (LA2_1==57) ) {
                    alt2=2;
                }
                else if ( (LA2_1==55) ) {
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
            else if ( ((LA3_0>=19 && LA3_0<=20)||(LA3_0>=35 && LA3_0<=54)) ) {
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


    // $ANTLR start "entryRuleElementSet"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:253:1: entryRuleElementSet returns [EObject current=null] : iv_ruleElementSet= ruleElementSet EOF ;
    public final EObject entryRuleElementSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementSet = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:254:2: (iv_ruleElementSet= ruleElementSet EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:255:2: iv_ruleElementSet= ruleElementSet EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElementSetRule()); 
            }
            pushFollow(FOLLOW_ruleElementSet_in_entryRuleElementSet548);
            iv_ruleElementSet=ruleElementSet();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElementSet; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementSet558); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElementSet"


    // $ANTLR start "ruleElementSet"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:262:1: ruleElementSet returns [EObject current=null] : ( ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) ) ) ;
    public final EObject ruleElementSet() throws RecognitionException {
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

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:265:28: ( ( ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1: ( ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1: ( ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:267:1: ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:267:1: ( (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:268:1: (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:268:1: (lv_name_0_1= 'threads' | lv_name_0_2= 'data' | lv_name_0_3= 'memory' | lv_name_0_4= 'thread_groups' | lv_name_0_5= 'processes' | lv_name_0_6= 'subprograms' | lv_name_0_7= 'subprogram_groups' | lv_name_0_8= 'processors' | lv_name_0_9= 'virtual_processors' | lv_name_0_10= 'buses' | lv_name_0_11= 'virtual_buses' | lv_name_0_12= 'devices' | lv_name_0_13= 'systems' | lv_name_0_14= 'abstracts' | lv_name_0_15= 'connections' | lv_name_0_16= 'components' | lv_name_0_17= 'features' )
            int alt4=17;
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
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:269:3: lv_name_0_1= 'threads'
                    {
                    lv_name_0_1=(Token)match(input,18,FOLLOW_18_in_ruleElementSet602); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_1, grammarAccess.getElementSetAccess().getNameThreadsKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:281:8: lv_name_0_2= 'data'
                    {
                    lv_name_0_2=(Token)match(input,19,FOLLOW_19_in_ruleElementSet631); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_2, grammarAccess.getElementSetAccess().getNameDataKeyword_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:293:8: lv_name_0_3= 'memory'
                    {
                    lv_name_0_3=(Token)match(input,20,FOLLOW_20_in_ruleElementSet660); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_3, grammarAccess.getElementSetAccess().getNameMemoryKeyword_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:305:8: lv_name_0_4= 'thread_groups'
                    {
                    lv_name_0_4=(Token)match(input,21,FOLLOW_21_in_ruleElementSet689); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_4, grammarAccess.getElementSetAccess().getNameThread_groupsKeyword_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_4, null);
                      	    
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:317:8: lv_name_0_5= 'processes'
                    {
                    lv_name_0_5=(Token)match(input,22,FOLLOW_22_in_ruleElementSet718); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_5, grammarAccess.getElementSetAccess().getNameProcessesKeyword_0_4());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_5, null);
                      	    
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:329:8: lv_name_0_6= 'subprograms'
                    {
                    lv_name_0_6=(Token)match(input,23,FOLLOW_23_in_ruleElementSet747); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_6, grammarAccess.getElementSetAccess().getNameSubprogramsKeyword_0_5());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_6, null);
                      	    
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:341:8: lv_name_0_7= 'subprogram_groups'
                    {
                    lv_name_0_7=(Token)match(input,24,FOLLOW_24_in_ruleElementSet776); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_7, grammarAccess.getElementSetAccess().getNameSubprogram_groupsKeyword_0_6());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_7, null);
                      	    
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:353:8: lv_name_0_8= 'processors'
                    {
                    lv_name_0_8=(Token)match(input,25,FOLLOW_25_in_ruleElementSet805); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_8, grammarAccess.getElementSetAccess().getNameProcessorsKeyword_0_7());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_8, null);
                      	    
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:365:8: lv_name_0_9= 'virtual_processors'
                    {
                    lv_name_0_9=(Token)match(input,26,FOLLOW_26_in_ruleElementSet834); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_9, grammarAccess.getElementSetAccess().getNameVirtual_processorsKeyword_0_8());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_9, null);
                      	    
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:377:8: lv_name_0_10= 'buses'
                    {
                    lv_name_0_10=(Token)match(input,27,FOLLOW_27_in_ruleElementSet863); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_10, grammarAccess.getElementSetAccess().getNameBusesKeyword_0_9());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_10, null);
                      	    
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:389:8: lv_name_0_11= 'virtual_buses'
                    {
                    lv_name_0_11=(Token)match(input,28,FOLLOW_28_in_ruleElementSet892); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_11, grammarAccess.getElementSetAccess().getNameVirtual_busesKeyword_0_10());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_11, null);
                      	    
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:401:8: lv_name_0_12= 'devices'
                    {
                    lv_name_0_12=(Token)match(input,29,FOLLOW_29_in_ruleElementSet921); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_12, grammarAccess.getElementSetAccess().getNameDevicesKeyword_0_11());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_12, null);
                      	    
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:413:8: lv_name_0_13= 'systems'
                    {
                    lv_name_0_13=(Token)match(input,30,FOLLOW_30_in_ruleElementSet950); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_13, grammarAccess.getElementSetAccess().getNameSystemsKeyword_0_12());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_13, null);
                      	    
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:425:8: lv_name_0_14= 'abstracts'
                    {
                    lv_name_0_14=(Token)match(input,31,FOLLOW_31_in_ruleElementSet979); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_14, grammarAccess.getElementSetAccess().getNameAbstractsKeyword_0_13());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_14, null);
                      	    
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:437:8: lv_name_0_15= 'connections'
                    {
                    lv_name_0_15=(Token)match(input,32,FOLLOW_32_in_ruleElementSet1008); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_15, grammarAccess.getElementSetAccess().getNameConnectionsKeyword_0_14());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_15, null);
                      	    
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:449:8: lv_name_0_16= 'components'
                    {
                    lv_name_0_16=(Token)match(input,33,FOLLOW_33_in_ruleElementSet1037); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_16, grammarAccess.getElementSetAccess().getNameComponentsKeyword_0_15());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_16, null);
                      	    
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:461:8: lv_name_0_17= 'features'
                    {
                    lv_name_0_17=(Token)match(input,34,FOLLOW_34_in_ruleElementSet1066); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_17, grammarAccess.getElementSetAccess().getNameFeaturesKeyword_0_16());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getElementSetRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_17, null);
                      	    
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
    // $ANTLR end "ruleElementSet"


    // $ANTLR start "entryRuleBuiltinType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:484:1: entryRuleBuiltinType returns [EObject current=null] : iv_ruleBuiltinType= ruleBuiltinType EOF ;
    public final EObject entryRuleBuiltinType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuiltinType = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:485:2: (iv_ruleBuiltinType= ruleBuiltinType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:486:2: iv_ruleBuiltinType= ruleBuiltinType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltinTypeRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType1117);
            iv_ruleBuiltinType=ruleBuiltinType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltinType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltinType1127); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:493:1: ruleBuiltinType returns [EObject current=null] : ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) ) ) ;
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
        Token lv_name_0_21=null;
        Token lv_name_0_22=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:496:28: ( ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:497:1: ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:497:1: ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:498:1: ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:498:1: ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:499:1: (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:499:1: (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14= 'abstract' | lv_name_0_15= 'aadl' | lv_name_0_16= 'feature' | lv_name_0_17= 'connection' | lv_name_0_18= 'component' | lv_name_0_19= 'string' | lv_name_0_20= 'int' | lv_name_0_21= 'real' | lv_name_0_22= 'bool' )
            int alt5=22;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt5=1;
                }
                break;
            case 35:
                {
                alt5=2;
                }
                break;
            case 36:
                {
                alt5=3;
                }
                break;
            case 37:
                {
                alt5=4;
                }
                break;
            case 38:
                {
                alt5=5;
                }
                break;
            case 39:
                {
                alt5=6;
                }
                break;
            case 40:
                {
                alt5=7;
                }
                break;
            case 41:
                {
                alt5=8;
                }
                break;
            case 20:
                {
                alt5=9;
                }
                break;
            case 42:
                {
                alt5=10;
                }
                break;
            case 43:
                {
                alt5=11;
                }
                break;
            case 44:
                {
                alt5=12;
                }
                break;
            case 45:
                {
                alt5=13;
                }
                break;
            case 46:
                {
                alt5=14;
                }
                break;
            case 47:
                {
                alt5=15;
                }
                break;
            case 48:
                {
                alt5=16;
                }
                break;
            case 49:
                {
                alt5=17;
                }
                break;
            case 50:
                {
                alt5=18;
                }
                break;
            case 51:
                {
                alt5=19;
                }
                break;
            case 52:
                {
                alt5=20;
                }
                break;
            case 53:
                {
                alt5=21;
                }
                break;
            case 54:
                {
                alt5=22;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:500:3: lv_name_0_1= 'data'
                    {
                    lv_name_0_1=(Token)match(input,19,FOLLOW_19_in_ruleBuiltinType1171); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:512:8: lv_name_0_2= 'thread'
                    {
                    lv_name_0_2=(Token)match(input,35,FOLLOW_35_in_ruleBuiltinType1200); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:524:8: lv_name_0_3= 'thread_group'
                    {
                    lv_name_0_3=(Token)match(input,36,FOLLOW_36_in_ruleBuiltinType1229); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:536:8: lv_name_0_4= 'process'
                    {
                    lv_name_0_4=(Token)match(input,37,FOLLOW_37_in_ruleBuiltinType1258); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:548:8: lv_name_0_5= 'subprogram'
                    {
                    lv_name_0_5=(Token)match(input,38,FOLLOW_38_in_ruleBuiltinType1287); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:560:8: lv_name_0_6= 'subprogram_group'
                    {
                    lv_name_0_6=(Token)match(input,39,FOLLOW_39_in_ruleBuiltinType1316); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:572:8: lv_name_0_7= 'processor'
                    {
                    lv_name_0_7=(Token)match(input,40,FOLLOW_40_in_ruleBuiltinType1345); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:584:8: lv_name_0_8= 'virtual_processor'
                    {
                    lv_name_0_8=(Token)match(input,41,FOLLOW_41_in_ruleBuiltinType1374); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:596:8: lv_name_0_9= 'memory'
                    {
                    lv_name_0_9=(Token)match(input,20,FOLLOW_20_in_ruleBuiltinType1403); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:608:8: lv_name_0_10= 'bus'
                    {
                    lv_name_0_10=(Token)match(input,42,FOLLOW_42_in_ruleBuiltinType1432); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:620:8: lv_name_0_11= 'virtual_bus'
                    {
                    lv_name_0_11=(Token)match(input,43,FOLLOW_43_in_ruleBuiltinType1461); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:632:8: lv_name_0_12= 'device'
                    {
                    lv_name_0_12=(Token)match(input,44,FOLLOW_44_in_ruleBuiltinType1490); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:644:8: lv_name_0_13= 'system'
                    {
                    lv_name_0_13=(Token)match(input,45,FOLLOW_45_in_ruleBuiltinType1519); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:656:8: lv_name_0_14= 'abstract'
                    {
                    lv_name_0_14=(Token)match(input,46,FOLLOW_46_in_ruleBuiltinType1548); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:668:8: lv_name_0_15= 'aadl'
                    {
                    lv_name_0_15=(Token)match(input,47,FOLLOW_47_in_ruleBuiltinType1577); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_15, grammarAccess.getBuiltinTypeAccess().getNameAadlKeyword_0_14());
                          
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:680:8: lv_name_0_16= 'feature'
                    {
                    lv_name_0_16=(Token)match(input,48,FOLLOW_48_in_ruleBuiltinType1606); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_16, grammarAccess.getBuiltinTypeAccess().getNameFeatureKeyword_0_15());
                          
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:692:8: lv_name_0_17= 'connection'
                    {
                    lv_name_0_17=(Token)match(input,49,FOLLOW_49_in_ruleBuiltinType1635); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_17, grammarAccess.getBuiltinTypeAccess().getNameConnectionKeyword_0_16());
                          
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:704:8: lv_name_0_18= 'component'
                    {
                    lv_name_0_18=(Token)match(input,50,FOLLOW_50_in_ruleBuiltinType1664); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_18, grammarAccess.getBuiltinTypeAccess().getNameComponentKeyword_0_17());
                          
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:716:8: lv_name_0_19= 'string'
                    {
                    lv_name_0_19=(Token)match(input,51,FOLLOW_51_in_ruleBuiltinType1693); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_19, grammarAccess.getBuiltinTypeAccess().getNameStringKeyword_0_18());
                          
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:728:8: lv_name_0_20= 'int'
                    {
                    lv_name_0_20=(Token)match(input,52,FOLLOW_52_in_ruleBuiltinType1722); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_20, grammarAccess.getBuiltinTypeAccess().getNameIntKeyword_0_19());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_20, null);
                      	    
                    }

                    }
                    break;
                case 21 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:740:8: lv_name_0_21= 'real'
                    {
                    lv_name_0_21=(Token)match(input,53,FOLLOW_53_in_ruleBuiltinType1751); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_21, grammarAccess.getBuiltinTypeAccess().getNameRealKeyword_0_20());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_21, null);
                      	    
                    }

                    }
                    break;
                case 22 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:752:8: lv_name_0_22= 'bool'
                    {
                    lv_name_0_22=(Token)match(input,54,FOLLOW_54_in_ruleBuiltinType1780); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_22, grammarAccess.getBuiltinTypeAccess().getNameBoolKeyword_0_21());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBuiltinTypeRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_22, null);
                      	    
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:775:1: entryRuleArg returns [EObject current=null] : iv_ruleArg= ruleArg EOF ;
    public final EObject entryRuleArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArg = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:776:2: (iv_ruleArg= ruleArg EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:777:2: iv_ruleArg= ruleArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgRule()); 
            }
            pushFollow(FOLLOW_ruleArg_in_entryRuleArg1831);
            iv_ruleArg=ruleArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArg; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArg1841); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:784:1: ruleArg returns [EObject current=null] : ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) ) ;
    public final EObject ruleArg() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:787:28: ( ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:1: ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:1: ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                int LA6_1 = input.LA(2);

                if ( (synpred1_InternalResolute()) ) {
                    alt6=1;
                }
                else if ( (true) ) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:2: ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:2: ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:3: ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:798:6: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:798:7: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:798:7: ( (lv_name_0_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:799:1: (lv_name_0_0= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:799:1: (lv_name_0_0= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:800:3: lv_name_0_0= RULE_ID
                    {
                    lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArg1913); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_0_0, grammarAccess.getArgAccess().getNameIDTerminalRuleCall_0_0_0_0()); 
                      		
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

                    otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleArg1930); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getArgAccess().getColonKeyword_0_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:820:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:821:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:821:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:822:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgAccess().getTypeTypeParserRuleCall_0_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleArg1951);
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


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:839:6: ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:839:6: ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:839:7: () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:839:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:840:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getArgAccess().getQuantArgAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:2: ( (lv_name_4_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:846:1: (lv_name_4_0= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:846:1: (lv_name_4_0= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:3: lv_name_4_0= RULE_ID
                    {
                    lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArg1986); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_4_0, grammarAccess.getArgAccess().getNameIDTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getArgRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"ID");
                      	    
                    }

                    }


                    }

                    otherlv_5=(Token)match(input,55,FOLLOW_55_in_ruleArg2003); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getArgAccess().getColonKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:867:1: ( (lv_expr_6_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:868:1: (lv_expr_6_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:868:1: (lv_expr_6_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:869:3: lv_expr_6_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgAccess().getExprExprParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleArg2024);
                    lv_expr_6_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getArgRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_6_0, 
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
    // $ANTLR end "ruleArg"


    // $ANTLR start "entryRuleConstantDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:893:1: entryRuleConstantDefinition returns [EObject current=null] : iv_ruleConstantDefinition= ruleConstantDefinition EOF ;
    public final EObject entryRuleConstantDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:894:2: (iv_ruleConstantDefinition= ruleConstantDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:895:2: iv_ruleConstantDefinition= ruleConstantDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition2061);
            iv_ruleConstantDefinition=ruleConstantDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantDefinition2071); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:902:1: ruleConstantDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleConstantDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:905:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:907:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:907:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:908:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleConstantDefinition2113); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleConstantDefinition2130); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getConstantDefinitionAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:928:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:929:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:929:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:930:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleConstantDefinition2151);
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

            otherlv_3=(Token)match(input,56,FOLLOW_56_in_ruleConstantDefinition2163); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getConstantDefinitionAccess().getEqualsSignKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:950:1: ( (lv_expr_4_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:951:1: (lv_expr_4_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:951:1: (lv_expr_4_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:952:3: lv_expr_4_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getExprExprParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleConstantDefinition2184);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:976:1: entryRuleFunctionDefinition returns [EObject current=null] : iv_ruleFunctionDefinition= ruleFunctionDefinition EOF ;
    public final EObject entryRuleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:977:2: (iv_ruleFunctionDefinition= ruleFunctionDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:978:2: iv_ruleFunctionDefinition= ruleFunctionDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition2220);
            iv_ruleFunctionDefinition=ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionDefinition2230); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:985:1: ruleFunctionDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:988:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:990:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:990:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:991:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFunctionDefinition2272); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleFunctionDefinition2289); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionDefinitionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:1: ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:2: ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:2: ( (lv_args_2_0= ruleArg ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:1: (lv_args_2_0= ruleArg )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:1: (lv_args_2_0= ruleArg )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1013:3: lv_args_2_0= ruleArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition2311);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1029:2: (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==58) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1029:4: otherlv_3= ',' ( (lv_args_4_0= ruleArg ) )
                    	    {
                    	    otherlv_3=(Token)match(input,58,FOLLOW_58_in_ruleFunctionDefinition2324); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getFunctionDefinitionAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1033:1: ( (lv_args_4_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1034:1: (lv_args_4_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1034:1: (lv_args_4_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1035:3: lv_args_4_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition2345);
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
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,59,FOLLOW_59_in_ruleFunctionDefinition2361); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getFunctionDefinitionAccess().getRightParenthesisKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1055:1: ( (lv_body_6_0= ruleDefinitionBody ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1056:1: (lv_body_6_0= ruleDefinitionBody )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1056:1: (lv_body_6_0= ruleDefinitionBody )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1057:3: lv_body_6_0= ruleDefinitionBody
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getBodyDefinitionBodyParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition2382);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1081:1: entryRuleClaimString returns [EObject current=null] : iv_ruleClaimString= ruleClaimString EOF ;
    public final EObject entryRuleClaimString() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClaimString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1082:2: (iv_ruleClaimString= ruleClaimString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1083:2: iv_ruleClaimString= ruleClaimString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClaimStringRule()); 
            }
            pushFollow(FOLLOW_ruleClaimString_in_entryRuleClaimString2418);
            iv_ruleClaimString=ruleClaimString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClaimString; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleClaimString2428); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1090:1: ruleClaimString returns [EObject current=null] : ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) ) ;
    public final EObject ruleClaimString() throws RecognitionException {
        EObject current = null;

        Token lv_str_1_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:28: ( ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_STRING) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_ID) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:3: () ( (lv_str_1_0= RULE_STRING ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1095:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimStringAccess().getClaimStringAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1100:2: ( (lv_str_1_0= RULE_STRING ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1101:1: (lv_str_1_0= RULE_STRING )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1101:1: (lv_str_1_0= RULE_STRING )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1102:3: lv_str_1_0= RULE_STRING
                    {
                    lv_str_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleClaimString2480); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1119:6: ( () ( (otherlv_3= RULE_ID ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1119:6: ( () ( (otherlv_3= RULE_ID ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1119:7: () ( (otherlv_3= RULE_ID ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1119:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1120:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimStringAccess().getClaimArgAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1125:2: ( (otherlv_3= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1126:1: (otherlv_3= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1126:1: (otherlv_3= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1127:3: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getClaimStringRule());
                      	        }
                              
                    }
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleClaimString2522); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1146:1: entryRuleDefinitionBody returns [EObject current=null] : iv_ruleDefinitionBody= ruleDefinitionBody EOF ;
    public final EObject entryRuleDefinitionBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinitionBody = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1147:2: (iv_ruleDefinitionBody= ruleDefinitionBody EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1148:2: iv_ruleDefinitionBody= ruleDefinitionBody EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefinitionBodyRule()); 
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody2559);
            iv_ruleDefinitionBody=ruleDefinitionBody();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefinitionBody; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefinitionBody2569); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1155:1: ruleDefinitionBody returns [EObject current=null] : ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1158:28: ( ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==55) ) {
                alt11=1;
            }
            else if ( (LA11_0==60) ) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:3: () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1159:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1160:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getFuncBodyAction_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleDefinitionBody2616); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getDefinitionBodyAccess().getColonKeyword_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1169:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1170:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1170:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1171:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getTypeTypeParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleDefinitionBody2637);
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

                    otherlv_3=(Token)match(input,56,FOLLOW_56_in_ruleDefinitionBody2649); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getDefinitionBodyAccess().getEqualsSignKeyword_0_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1191:1: ( (lv_expr_4_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1192:1: (lv_expr_4_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1192:1: (lv_expr_4_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1193:3: lv_expr_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_0_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2670);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1210:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1210:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1210:7: () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1210:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1211:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getClaimBodyAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_6=(Token)match(input,60,FOLLOW_60_in_ruleDefinitionBody2699); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getDefinitionBodyAccess().getLessThanSignEqualsSignKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1220:1: (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1220:3: otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )+ otherlv_9= '**'
                    {
                    otherlv_7=(Token)match(input,61,FOLLOW_61_in_ruleDefinitionBody2712); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1224:1: ( (lv_claim_8_0= ruleClaimString ) )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_STRING)) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1225:1: (lv_claim_8_0= ruleClaimString )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1225:1: (lv_claim_8_0= ruleClaimString )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1226:3: lv_claim_8_0= ruleClaimString
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getClaimClaimStringParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleClaimString_in_ruleDefinitionBody2733);
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
                    	    if ( cnt10 >= 1 ) break loop10;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);

                    otherlv_9=(Token)match(input,61,FOLLOW_61_in_ruleDefinitionBody2746); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_2());
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1246:2: ( (lv_expr_10_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1247:1: (lv_expr_10_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1247:1: (lv_expr_10_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1248:3: lv_expr_10_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2768);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1272:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1273:2: (iv_ruleExpr= ruleExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1274:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_ruleExpr_in_entryRuleExpr2805);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpr2815); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1281:1: ruleExpr returns [EObject current=null] : this_ImpliesExpr_0= ruleImpliesExpr ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_ImpliesExpr_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1284:28: (this_ImpliesExpr_0= ruleImpliesExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1286:5: this_ImpliesExpr_0= ruleImpliesExpr
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExprAccess().getImpliesExprParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_ruleExpr2861);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1302:1: entryRuleImpliesExpr returns [EObject current=null] : iv_ruleImpliesExpr= ruleImpliesExpr EOF ;
    public final EObject entryRuleImpliesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1303:2: (iv_ruleImpliesExpr= ruleImpliesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1304:2: iv_ruleImpliesExpr= ruleImpliesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExprRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2895);
            iv_ruleImpliesExpr=ruleImpliesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpr2905); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1311:1: ruleImpliesExpr returns [EObject current=null] : (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) ;
    public final EObject ruleImpliesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_OrExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1314:28: ( (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1315:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1315:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1316:5: this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExprAccess().getOrExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpr_in_ruleImpliesExpr2952);
            this_OrExpr_0=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:1: ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==62) ) {
                int LA12_1 = input.LA(2);

                if ( (synpred2_InternalResolute()) ) {
                    alt12=1;
                }
            }
            switch (alt12) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:3: ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1331:6: ( () ( (lv_op_2_0= '=>' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1331:7: () ( (lv_op_2_0= '=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1331:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1332:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1337:2: ( (lv_op_2_0= '=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1338:1: (lv_op_2_0= '=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1338:1: (lv_op_2_0= '=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1339:3: lv_op_2_0= '=>'
                    {
                    lv_op_2_0=(Token)match(input,62,FOLLOW_62_in_ruleImpliesExpr3003); if (state.failed) return current;
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1352:4: ( (lv_right_3_0= ruleImpliesExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1353:1: (lv_right_3_0= ruleImpliesExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1353:1: (lv_right_3_0= ruleImpliesExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1354:3: lv_right_3_0= ruleImpliesExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImpliesExprAccess().getRightImpliesExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr3039);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1378:1: entryRuleOrExpr returns [EObject current=null] : iv_ruleOrExpr= ruleOrExpr EOF ;
    public final EObject entryRuleOrExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1379:2: (iv_ruleOrExpr= ruleOrExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1380:2: iv_ruleOrExpr= ruleOrExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExprRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpr_in_entryRuleOrExpr3077);
            iv_ruleOrExpr=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpr3087); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1387:1: ruleOrExpr returns [EObject current=null] : (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) ;
    public final EObject ruleOrExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_AndExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1390:28: ( (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1391:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1391:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1392:5: this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExprAccess().getAndExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr3134);
            this_AndExpr_0=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:1: ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==63) ) {
                    int LA13_2 = input.LA(2);

                    if ( (synpred3_InternalResolute()) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:3: ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1407:6: ( () ( (lv_op_2_0= 'or' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1407:7: () ( (lv_op_2_0= 'or' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1407:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1408:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1413:2: ( (lv_op_2_0= 'or' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1414:1: (lv_op_2_0= 'or' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1414:1: (lv_op_2_0= 'or' )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1415:3: lv_op_2_0= 'or'
            	    {
            	    lv_op_2_0=(Token)match(input,63,FOLLOW_63_in_ruleOrExpr3185); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1428:4: ( (lv_right_3_0= ruleAndExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1429:1: (lv_right_3_0= ruleAndExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1429:1: (lv_right_3_0= ruleAndExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1430:3: lv_right_3_0= ruleAndExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExprAccess().getRightAndExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr3221);
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
    // $ANTLR end "ruleOrExpr"


    // $ANTLR start "entryRuleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1454:1: entryRuleAndExpr returns [EObject current=null] : iv_ruleAndExpr= ruleAndExpr EOF ;
    public final EObject entryRuleAndExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1455:2: (iv_ruleAndExpr= ruleAndExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1456:2: iv_ruleAndExpr= ruleAndExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExprRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpr_in_entryRuleAndExpr3259);
            iv_ruleAndExpr=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpr3269); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1463:1: ruleAndExpr returns [EObject current=null] : (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* ) ;
    public final EObject ruleAndExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_RelationalExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1466:28: ( (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1467:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1467:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1468:5: this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExprAccess().getRelationalExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr3316);
            this_RelationalExpr_0=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:1: ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==64) ) {
                    int LA14_2 = input.LA(2);

                    if ( (synpred4_InternalResolute()) ) {
                        alt14=1;
                    }


                }


                switch (alt14) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:2: ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:2: ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:3: ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:6: ( () ( (lv_op_2_0= 'and' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:7: () ( (lv_op_2_0= 'and' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1484:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1489:2: ( (lv_op_2_0= 'and' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1490:1: (lv_op_2_0= 'and' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1490:1: (lv_op_2_0= 'and' )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1491:3: lv_op_2_0= 'and'
            	    {
            	    lv_op_2_0=(Token)match(input,64,FOLLOW_64_in_ruleAndExpr3367); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1504:4: ( (lv_right_3_0= ruleRelationalExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1505:1: (lv_right_3_0= ruleRelationalExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1505:1: (lv_right_3_0= ruleRelationalExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1506:3: lv_right_3_0= ruleRelationalExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExprAccess().getRightRelationalExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr3403);
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
            	    break loop14;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1530:1: entryRuleRelationalOp returns [String current=null] : iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1531:2: (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1532:2: iv_ruleRelationalOp= ruleRelationalOp EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalOpRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp3442);
            iv_ruleRelationalOp=ruleRelationalOp();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalOp.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalOp3453); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1539:1: ruleRelationalOp returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1542:28: ( (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1543:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1543:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' | kw= 'in' )
            int alt15=7;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt15=1;
                }
                break;
            case 60:
                {
                alt15=2;
                }
                break;
            case 66:
                {
                alt15=3;
                }
                break;
            case 67:
                {
                alt15=4;
                }
                break;
            case 56:
                {
                alt15=5;
                }
                break;
            case 68:
                {
                alt15=6;
                }
                break;
            case 69:
                {
                alt15=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1544:2: kw= '<'
                    {
                    kw=(Token)match(input,65,FOLLOW_65_in_ruleRelationalOp3491); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1551:2: kw= '<='
                    {
                    kw=(Token)match(input,60,FOLLOW_60_in_ruleRelationalOp3510); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignEqualsSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1558:2: kw= '>'
                    {
                    kw=(Token)match(input,66,FOLLOW_66_in_ruleRelationalOp3529); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1565:2: kw= '>='
                    {
                    kw=(Token)match(input,67,FOLLOW_67_in_ruleRelationalOp3548); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignEqualsSignKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1572:2: kw= '='
                    {
                    kw=(Token)match(input,56,FOLLOW_56_in_ruleRelationalOp3567); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getEqualsSignKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1579:2: kw= '<>'
                    {
                    kw=(Token)match(input,68,FOLLOW_68_in_ruleRelationalOp3586); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignGreaterThanSignKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1586:2: kw= 'in'
                    {
                    kw=(Token)match(input,69,FOLLOW_69_in_ruleRelationalOp3605); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1599:1: entryRuleRelationalExpr returns [EObject current=null] : iv_ruleRelationalExpr= ruleRelationalExpr EOF ;
    public final EObject entryRuleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1600:2: (iv_ruleRelationalExpr= ruleRelationalExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1601:2: iv_ruleRelationalExpr= ruleRelationalExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExprRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr3645);
            iv_ruleRelationalExpr=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpr3655); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1608:1: ruleRelationalExpr returns [EObject current=null] : (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) ;
    public final EObject ruleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject this_PlusExpr_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1611:28: ( (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1612:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1612:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1613:5: this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExprAccess().getPlusExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr3702);
            this_PlusExpr_0=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PlusExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            int alt16=2;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:3: ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1626:6: ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1626:7: () ( (lv_op_2_0= ruleRelationalOp ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1626:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1627:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1632:2: ( (lv_op_2_0= ruleRelationalOp ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1633:1: (lv_op_2_0= ruleRelationalOp )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1633:1: (lv_op_2_0= ruleRelationalOp )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1634:3: lv_op_2_0= ruleRelationalOp
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getOpRelationalOpParserRuleCall_1_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3751);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1650:4: ( (lv_right_3_0= rulePlusExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1651:1: (lv_right_3_0= rulePlusExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1651:1: (lv_right_3_0= rulePlusExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1652:3: lv_right_3_0= rulePlusExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getRightPlusExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr3774);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1676:1: entryRulePlusExpr returns [EObject current=null] : iv_rulePlusExpr= rulePlusExpr EOF ;
    public final EObject entryRulePlusExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1677:2: (iv_rulePlusExpr= rulePlusExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1678:2: iv_rulePlusExpr= rulePlusExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusExprRule()); 
            }
            pushFollow(FOLLOW_rulePlusExpr_in_entryRulePlusExpr3812);
            iv_rulePlusExpr=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusExpr3822); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1685:1: rulePlusExpr returns [EObject current=null] : (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) ;
    public final EObject rulePlusExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_TimesExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1688:28: ( (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1689:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1689:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1690:5: this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getPlusExprAccess().getTimesExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr3869);
            this_TimesExpr_0=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_TimesExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:1: ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==70) ) {
                    int LA18_2 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt18=1;
                    }


                }
                else if ( (LA18_0==71) ) {
                    int LA18_3 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt18=1;
                    }


                }


                switch (alt18) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1711:6: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1711:7: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1711:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1712:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1717:2: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1718:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1718:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1719:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1719:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    int alt17=2;
            	    int LA17_0 = input.LA(1);

            	    if ( (LA17_0==70) ) {
            	        alt17=1;
            	    }
            	    else if ( (LA17_0==71) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1720:3: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,70,FOLLOW_70_in_rulePlusExpr3941); if (state.failed) return current;
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1732:8: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,71,FOLLOW_71_in_rulePlusExpr3970); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1747:4: ( (lv_right_3_0= ruleTimesExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1748:1: (lv_right_3_0= ruleTimesExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1748:1: (lv_right_3_0= ruleTimesExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1749:3: lv_right_3_0= ruleTimesExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getPlusExprAccess().getRightTimesExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr4009);
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
            	    break loop18;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1773:1: entryRuleTimesExpr returns [EObject current=null] : iv_ruleTimesExpr= ruleTimesExpr EOF ;
    public final EObject entryRuleTimesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1774:2: (iv_ruleTimesExpr= ruleTimesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:2: iv_ruleTimesExpr= ruleTimesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTimesExprRule()); 
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr4047);
            iv_ruleTimesExpr=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTimesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTimesExpr4057); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1782:1: ruleTimesExpr returns [EObject current=null] : (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* ) ;
    public final EObject ruleTimesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_SetOpExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1785:28: ( (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:1: (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:1: (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1787:5: this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTimesExprAccess().getSetOpExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr4104);
            this_SetOpExpr_0=ruleSetOpExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_SetOpExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:1: ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==72) ) {
                    int LA20_2 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt20=1;
                    }


                }
                else if ( (LA20_0==73) ) {
                    int LA20_3 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt20=1;
                    }


                }


                switch (alt20) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:2: ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:2: ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:3: ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:6: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:7: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1808:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1809:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1814:2: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1815:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1816:1: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1816:1: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==72) ) {
            	        alt19=1;
            	    }
            	    else if ( (LA19_0==73) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1817:3: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,72,FOLLOW_72_in_ruleTimesExpr4176); if (state.failed) return current;
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1829:8: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,73,FOLLOW_73_in_ruleTimesExpr4205); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1844:4: ( (lv_right_3_0= ruleSetOpExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1845:1: (lv_right_3_0= ruleSetOpExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1845:1: (lv_right_3_0= ruleSetOpExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1846:3: lv_right_3_0= ruleSetOpExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTimesExprAccess().getRightSetOpExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr4244);
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
            	    break loop20;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1870:1: entryRuleSetOpExpr returns [EObject current=null] : iv_ruleSetOpExpr= ruleSetOpExpr EOF ;
    public final EObject entryRuleSetOpExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetOpExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1871:2: (iv_ruleSetOpExpr= ruleSetOpExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1872:2: iv_ruleSetOpExpr= ruleSetOpExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSetOpExprRule()); 
            }
            pushFollow(FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr4282);
            iv_ruleSetOpExpr=ruleSetOpExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSetOpExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSetOpExpr4292); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1879:1: ruleSetOpExpr returns [EObject current=null] : (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) ;
    public final EObject ruleSetOpExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_PrefixExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1882:28: ( (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1883:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1883:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1884:5: this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getSetOpExprAccess().getPrefixExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr4339);
            this_PrefixExpr_0=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrefixExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:1: ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==74) ) {
                    int LA22_2 = input.LA(2);

                    if ( (synpred8_InternalResolute()) ) {
                        alt22=1;
                    }


                }
                else if ( (LA22_0==75) ) {
                    int LA22_3 = input.LA(2);

                    if ( (synpred8_InternalResolute()) ) {
                        alt22=1;
                    }


                }


                switch (alt22) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:2: ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:2: ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:3: ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1905:6: ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1905:7: () ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1905:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1906:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1911:2: ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:1: ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1912:1: ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:1: (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:1: (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' )
            	    int alt21=2;
            	    int LA21_0 = input.LA(1);

            	    if ( (LA21_0==74) ) {
            	        alt21=1;
            	    }
            	    else if ( (LA21_0==75) ) {
            	        alt21=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 21, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt21) {
            	        case 1 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1914:3: lv_op_2_1= 'union'
            	            {
            	            lv_op_2_1=(Token)match(input,74,FOLLOW_74_in_ruleSetOpExpr4411); if (state.failed) return current;
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1926:8: lv_op_2_2= 'intersect'
            	            {
            	            lv_op_2_2=(Token)match(input,75,FOLLOW_75_in_ruleSetOpExpr4440); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1941:4: ( (lv_right_3_0= rulePrefixExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1942:1: (lv_right_3_0= rulePrefixExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1942:1: (lv_right_3_0= rulePrefixExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1943:3: lv_right_3_0= rulePrefixExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSetOpExprAccess().getRightPrefixExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr4479);
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
            	    break loop22;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1967:1: entryRulePrefixExpr returns [EObject current=null] : iv_rulePrefixExpr= rulePrefixExpr EOF ;
    public final EObject entryRulePrefixExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1968:2: (iv_rulePrefixExpr= rulePrefixExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1969:2: iv_rulePrefixExpr= rulePrefixExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrefixExprRule()); 
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr4517);
            iv_rulePrefixExpr=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrefixExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrefixExpr4527); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1976:1: rulePrefixExpr returns [EObject current=null] : ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr ) ;
    public final EObject rulePrefixExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        EObject lv_expr_2_0 = null;

        EObject this_AtomicExpr_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1979:28: ( ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3= ruleAtomicExpr )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==71||LA24_0==76) ) {
                alt24=1;
            }
            else if ( ((LA24_0>=RULE_ID && LA24_0<=RULE_INTEGER_LIT)||LA24_0==16||LA24_0==32||LA24_0==57||(LA24_0>=78 && LA24_0<=80)||(LA24_0>=83 && LA24_0<=84)||LA24_0==87||(LA24_0>=89 && LA24_0<=132)||(LA24_0>=140 && LA24_0<=141)) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:3: () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1980:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1981:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixExprAccess().getUnaryExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:2: ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1987:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1987:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1988:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==71) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==76) ) {
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1989:3: lv_op_1_1= '-'
                            {
                            lv_op_1_1=(Token)match(input,71,FOLLOW_71_in_rulePrefixExpr4582); if (state.failed) return current;
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2001:8: lv_op_1_2= 'not'
                            {
                            lv_op_1_2=(Token)match(input,76,FOLLOW_76_in_rulePrefixExpr4611); if (state.failed) return current;
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2016:2: ( (lv_expr_2_0= rulePrefixExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2017:1: (lv_expr_2_0= rulePrefixExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2017:1: (lv_expr_2_0= rulePrefixExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2018:3: lv_expr_2_0= rulePrefixExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrefixExprAccess().getExprPrefixExprParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePrefixExpr_in_rulePrefixExpr4648);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2036:5: this_AtomicExpr_3= ruleAtomicExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrefixExprAccess().getAtomicExprParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleAtomicExpr_in_rulePrefixExpr4677);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:1: entryRuleAtomicExpr returns [EObject current=null] : iv_ruleAtomicExpr= ruleAtomicExpr EOF ;
    public final EObject entryRuleAtomicExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2053:2: (iv_ruleAtomicExpr= ruleAtomicExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2054:2: iv_ruleAtomicExpr= ruleAtomicExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAtomicExprRule()); 
            }
            pushFollow(FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr4712);
            iv_ruleAtomicExpr=ruleAtomicExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAtomicExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomicExpr4722); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2061:1: ruleAtomicExpr returns [EObject current=null] : ( ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? ) | ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? ) | ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? ) | ( () ( (lv_val_12_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_14_0= ruleRealTerm ) ) ) | ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_18_0= ruleStringTerm ) ) ) | ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) ) | ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' ) | ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' ) | ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' ) | ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) ) | (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' ) ) ;
    public final EObject ruleAtomicExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_9=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token lv_quant_27_1=null;
        Token lv_quant_27_2=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_39=null;
        Token otherlv_41=null;
        Token otherlv_42=null;
        Token otherlv_44=null;
        Token otherlv_46=null;
        Token otherlv_48=null;
        Token otherlv_50=null;
        Token otherlv_51=null;
        Token otherlv_53=null;
        Token otherlv_54=null;
        Token otherlv_56=null;
        Token otherlv_58=null;
        Token otherlv_60=null;
        Token otherlv_62=null;
        Token otherlv_64=null;
        EObject lv_subelements_3_0 = null;

        EObject lv_sub_7_0 = null;

        EObject lv_val_10_0 = null;

        EObject lv_val_12_0 = null;

        EObject lv_val_14_0 = null;

        EObject lv_val_16_0 = null;

        EObject lv_val_18_0 = null;

        EObject lv_cond_21_0 = null;

        EObject lv_then_23_0 = null;

        EObject lv_else_25_0 = null;

        EObject lv_args_29_0 = null;

        EObject lv_expr_32_0 = null;

        AntlrDatatypeRuleToken lv_fn_34_0 = null;

        EObject lv_args_36_0 = null;

        EObject lv_args_38_0 = null;

        EObject lv_args_43_0 = null;

        EObject lv_args_45_0 = null;

        EObject lv_map_49_0 = null;

        EObject lv_args_52_0 = null;

        EObject lv_filter_55_0 = null;

        EObject lv_binding_59_0 = null;

        EObject lv_expr_61_0 = null;

        EObject this_Expr_63 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2064:28: ( ( ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? ) | ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? ) | ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? ) | ( () ( (lv_val_12_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_14_0= ruleRealTerm ) ) ) | ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_18_0= ruleStringTerm ) ) ) | ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) ) | ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' ) | ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' ) | ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' ) | ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) ) | (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:1: ( ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? ) | ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? ) | ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? ) | ( () ( (lv_val_12_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_14_0= ruleRealTerm ) ) ) | ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_18_0= ruleStringTerm ) ) ) | ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) ) | ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' ) | ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' ) | ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' ) | ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) ) | (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:1: ( ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? ) | ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? ) | ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? ) | ( () ( (lv_val_12_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_14_0= ruleRealTerm ) ) ) | ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_18_0= ruleStringTerm ) ) ) | ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) ) | ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' ) | ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' ) | ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' ) | ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) ) | (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' ) )
            int alt36=14;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:2: ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:2: ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:3: () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2065:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2066:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIdExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2071:2: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2072:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2072:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getIdNamedElementCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleAtomicExpr4780);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2086:2: (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==77) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2086:4: otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) )
                            {
                            otherlv_2=(Token)match(input,77,FOLLOW_77_in_ruleAtomicExpr4793); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getAtomicExprAccess().getFullStopKeyword_0_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2090:1: ( (lv_subelements_3_0= ruleElementSet ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2091:1: (lv_subelements_3_0= ruleElementSet )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2091:1: (lv_subelements_3_0= ruleElementSet )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2092:3: lv_subelements_3_0= ruleElementSet
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getSubelementsElementSetParserRuleCall_0_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleElementSet_in_ruleAtomicExpr4814);
                            lv_subelements_3_0=ruleElementSet();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"subelements",
                                      		lv_subelements_3_0, 
                                      		"ElementSet");
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
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2109:6: ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2109:6: ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2109:7: () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2109:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2110:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getThisExprAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_5=(Token)match(input,78,FOLLOW_78_in_ruleAtomicExpr4845); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getAtomicExprAccess().getThisKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2119:1: (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==77) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2119:3: otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) )
                            {
                            otherlv_6=(Token)match(input,77,FOLLOW_77_in_ruleAtomicExpr4858); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getAtomicExprAccess().getFullStopKeyword_1_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:1: ( (lv_sub_7_0= ruleNestedDotID ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_sub_7_0= ruleNestedDotID )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2124:1: (lv_sub_7_0= ruleNestedDotID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2125:3: lv_sub_7_0= ruleNestedDotID
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getSubNestedDotIDParserRuleCall_1_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleNestedDotID_in_ruleAtomicExpr4879);
                            lv_sub_7_0=ruleNestedDotID();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"sub",
                                      		lv_sub_7_0, 
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2142:6: ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2142:6: ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2142:7: () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2142:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2143:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFailExprAction_2_0(),
                                  current);
                          
                    }

                    }

                    otherlv_9=(Token)match(input,79,FOLLOW_79_in_ruleAtomicExpr4910); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getAtomicExprAccess().getFailKeyword_2_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2152:1: ( (lv_val_10_0= ruleStringTerm ) )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==RULE_STRING) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2153:1: (lv_val_10_0= ruleStringTerm )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2153:1: (lv_val_10_0= ruleStringTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2154:3: lv_val_10_0= ruleStringTerm
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_2_2_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4931);
                            lv_val_10_0=ruleStringTerm();

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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2171:6: ( () ( (lv_val_12_0= ruleIntegerTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2171:6: ( () ( (lv_val_12_0= ruleIntegerTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2171:7: () ( (lv_val_12_0= ruleIntegerTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2171:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2172:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIntExprAction_3_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2177:2: ( (lv_val_12_0= ruleIntegerTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2178:1: (lv_val_12_0= ruleIntegerTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2178:1: (lv_val_12_0= ruleIntegerTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2179:3: lv_val_12_0= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValIntegerTermParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4970);
                    lv_val_12_0=ruleIntegerTerm();

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
                              		"IntegerTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2196:6: ( () ( (lv_val_14_0= ruleRealTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2196:6: ( () ( (lv_val_14_0= ruleRealTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2196:7: () ( (lv_val_14_0= ruleRealTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2196:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2197:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getRealExprAction_4_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2202:2: ( (lv_val_14_0= ruleRealTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2203:1: (lv_val_14_0= ruleRealTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2203:1: (lv_val_14_0= ruleRealTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2204:3: lv_val_14_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValRealTermParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleAtomicExpr5008);
                    lv_val_14_0=ruleRealTerm();

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
                              		"RealTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2221:6: ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2221:6: ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2221:7: () ( (lv_val_16_0= ruleBooleanLiteral ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2221:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2222:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBoolExprAction_5_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2227:2: ( (lv_val_16_0= ruleBooleanLiteral ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2228:1: (lv_val_16_0= ruleBooleanLiteral )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2228:1: (lv_val_16_0= ruleBooleanLiteral )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2229:3: lv_val_16_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValBooleanLiteralParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr5046);
                    lv_val_16_0=ruleBooleanLiteral();

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
                              		"BooleanLiteral");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:6: ( () ( (lv_val_18_0= ruleStringTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:6: ( () ( (lv_val_18_0= ruleStringTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:7: () ( (lv_val_18_0= ruleStringTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2246:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2247:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getStringExprAction_6_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2252:2: ( (lv_val_18_0= ruleStringTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2253:1: (lv_val_18_0= ruleStringTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2253:1: (lv_val_18_0= ruleStringTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2254:3: lv_val_18_0= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr5084);
                    lv_val_18_0=ruleStringTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_18_0, 
                              		"StringTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2271:6: ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2271:6: ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2271:7: () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2271:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIfThenElseExprAction_7_0(),
                                  current);
                          
                    }

                    }

                    otherlv_20=(Token)match(input,80,FOLLOW_80_in_ruleAtomicExpr5113); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getAtomicExprAccess().getIfKeyword_7_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2281:1: ( (lv_cond_21_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2282:1: (lv_cond_21_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2282:1: (lv_cond_21_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2283:3: lv_cond_21_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getCondExprParserRuleCall_7_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5134);
                    lv_cond_21_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"cond",
                              		lv_cond_21_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_22=(Token)match(input,81,FOLLOW_81_in_ruleAtomicExpr5146); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_22, grammarAccess.getAtomicExprAccess().getThenKeyword_7_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2303:1: ( (lv_then_23_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2304:1: (lv_then_23_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2304:1: (lv_then_23_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2305:3: lv_then_23_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getThenExprParserRuleCall_7_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5167);
                    lv_then_23_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"then",
                              		lv_then_23_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_24=(Token)match(input,82,FOLLOW_82_in_ruleAtomicExpr5179); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_24, grammarAccess.getAtomicExprAccess().getElseKeyword_7_5());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2325:1: ( (lv_else_25_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2326:1: (lv_else_25_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2326:1: (lv_else_25_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2327:3: lv_else_25_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getElseExprParserRuleCall_7_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5200);
                    lv_else_25_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"else",
                              		lv_else_25_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2344:6: ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2344:6: ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2344:7: () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2344:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2345:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getQuantifiedExprAction_8_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2350:2: ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2351:1: ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2351:1: ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2352:1: (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2352:1: (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==83) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==84) ) {
                        alt28=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2353:3: lv_quant_27_1= 'forall'
                            {
                            lv_quant_27_1=(Token)match(input,83,FOLLOW_83_in_ruleAtomicExpr5237); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_27_1, grammarAccess.getAtomicExprAccess().getQuantForallKeyword_8_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_27_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2365:8: lv_quant_27_2= 'exists'
                            {
                            lv_quant_27_2=(Token)match(input,84,FOLLOW_84_in_ruleAtomicExpr5266); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_27_2, grammarAccess.getAtomicExprAccess().getQuantExistsKeyword_8_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_27_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2380:2: (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+
                    int cnt29=0;
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==57) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2380:4: otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')'
                    	    {
                    	    otherlv_28=(Token)match(input,57,FOLLOW_57_in_ruleAtomicExpr5295); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_28, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_8_2_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2384:1: ( (lv_args_29_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2385:1: (lv_args_29_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2385:1: (lv_args_29_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2386:3: lv_args_29_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_8_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr5316);
                    	    lv_args_29_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_29_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_30=(Token)match(input,59,FOLLOW_59_in_ruleAtomicExpr5328); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_30, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_8_2_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt29 >= 1 ) break loop29;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(29, input);
                                throw eee;
                        }
                        cnt29++;
                    } while (true);

                    otherlv_31=(Token)match(input,77,FOLLOW_77_in_ruleAtomicExpr5342); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_31, grammarAccess.getAtomicExprAccess().getFullStopKeyword_8_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2410:1: ( (lv_expr_32_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2411:1: (lv_expr_32_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2411:1: (lv_expr_32_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2412:3: lv_expr_32_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_8_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5363);
                    lv_expr_32_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_32_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2429:6: ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2429:6: ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2429:7: () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2429:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2430:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBuiltInFuncCallExprAction_9_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2435:2: ( (lv_fn_34_0= ruleBuiltInFunc ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2436:1: (lv_fn_34_0= ruleBuiltInFunc )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2436:1: (lv_fn_34_0= ruleBuiltInFunc )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2437:3: lv_fn_34_0= ruleBuiltInFunc
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFnBuiltInFuncParserRuleCall_9_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr5401);
                    lv_fn_34_0=ruleBuiltInFunc();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"fn",
                              		lv_fn_34_0, 
                              		"BuiltInFunc");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_35=(Token)match(input,57,FOLLOW_57_in_ruleAtomicExpr5413); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_35, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_9_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2457:1: ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_INTEGER_LIT)||LA31_0==16||LA31_0==32||LA31_0==57||LA31_0==71||LA31_0==76||(LA31_0>=78 && LA31_0<=80)||(LA31_0>=83 && LA31_0<=84)||LA31_0==87||(LA31_0>=89 && LA31_0<=132)||(LA31_0>=140 && LA31_0<=141)) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2457:2: ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2457:2: ( (lv_args_36_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2458:1: (lv_args_36_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2458:1: (lv_args_36_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2459:3: lv_args_36_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5435);
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

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2475:2: (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==58) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2475:4: otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) )
                            	    {
                            	    otherlv_37=(Token)match(input,58,FOLLOW_58_in_ruleAtomicExpr5448); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_37, grammarAccess.getAtomicExprAccess().getCommaKeyword_9_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2479:1: ( (lv_args_38_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:1: (lv_args_38_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:1: (lv_args_38_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2481:3: lv_args_38_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5469);
                            	    lv_args_38_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_38_0, 
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

                    otherlv_39=(Token)match(input,59,FOLLOW_59_in_ruleAtomicExpr5485); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_39, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_9_4());
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2502:6: ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2502:6: ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2502:7: () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2502:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2503:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFnCallExprAction_10_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2508:2: ( (otherlv_41= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2509:1: (otherlv_41= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2509:1: (otherlv_41= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2510:3: otherlv_41= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    otherlv_41=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAtomicExpr5522); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_41, grammarAccess.getAtomicExprAccess().getFnFunctionDefinitionCrossReference_10_1_0()); 
                      	
                    }

                    }


                    }

                    otherlv_42=(Token)match(input,57,FOLLOW_57_in_ruleAtomicExpr5534); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_42, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_10_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2525:1: ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( ((LA33_0>=RULE_ID && LA33_0<=RULE_INTEGER_LIT)||LA33_0==16||LA33_0==32||LA33_0==57||LA33_0==71||LA33_0==76||(LA33_0>=78 && LA33_0<=80)||(LA33_0>=83 && LA33_0<=84)||LA33_0==87||(LA33_0>=89 && LA33_0<=132)||(LA33_0>=140 && LA33_0<=141)) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2525:2: ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2525:2: ( (lv_args_43_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2526:1: (lv_args_43_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2526:1: (lv_args_43_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2527:3: lv_args_43_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5556);
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

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2543:2: (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )*
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==58) ) {
                                    alt32=1;
                                }


                                switch (alt32) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2543:4: otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) )
                            	    {
                            	    otherlv_44=(Token)match(input,58,FOLLOW_58_in_ruleAtomicExpr5569); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_44, grammarAccess.getAtomicExprAccess().getCommaKeyword_10_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2547:1: ( (lv_args_45_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2548:1: (lv_args_45_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2548:1: (lv_args_45_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2549:3: lv_args_45_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5590);
                            	    lv_args_45_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_45_0, 
                            	              		"Expr");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop32;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_46=(Token)match(input,59,FOLLOW_59_in_ruleAtomicExpr5606); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_46, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_10_4());
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2570:6: ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2570:6: ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2570:7: () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2570:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2571:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFilterMapExprAction_11_0(),
                                  current);
                          
                    }

                    }

                    otherlv_48=(Token)match(input,16,FOLLOW_16_in_ruleAtomicExpr5635); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_48, grammarAccess.getAtomicExprAccess().getLeftCurlyBracketKeyword_11_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2580:1: ( (lv_map_49_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2581:1: (lv_map_49_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2581:1: (lv_map_49_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2582:3: lv_map_49_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getMapExprParserRuleCall_11_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5656);
                    lv_map_49_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"map",
                              		lv_map_49_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_50=(Token)match(input,85,FOLLOW_85_in_ruleAtomicExpr5668); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_50, grammarAccess.getAtomicExprAccess().getForKeyword_11_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2602:1: (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==57) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2602:3: otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')'
                    	    {
                    	    otherlv_51=(Token)match(input,57,FOLLOW_57_in_ruleAtomicExpr5681); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_51, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_11_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2606:1: ( (lv_args_52_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2607:1: (lv_args_52_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2607:1: (lv_args_52_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2608:3: lv_args_52_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_11_4_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr5702);
                    	    lv_args_52_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_52_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_53=(Token)match(input,59,FOLLOW_59_in_ruleAtomicExpr5714); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_53, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_11_4_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2628:3: (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==86) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2628:5: otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) )
                            {
                            otherlv_54=(Token)match(input,86,FOLLOW_86_in_ruleAtomicExpr5729); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_54, grammarAccess.getAtomicExprAccess().getVerticalLineKeyword_11_5_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2632:1: ( (lv_filter_55_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2633:1: (lv_filter_55_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2633:1: (lv_filter_55_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2634:3: lv_filter_55_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFilterExprParserRuleCall_11_5_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5750);
                            lv_filter_55_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"filter",
                                      		lv_filter_55_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_56=(Token)match(input,17,FOLLOW_17_in_ruleAtomicExpr5764); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_56, grammarAccess.getAtomicExprAccess().getRightCurlyBracketKeyword_11_6());
                          
                    }

                    }


                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2655:6: ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2655:6: ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2655:7: () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2655:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2656:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getLetExprAction_12_0(),
                                  current);
                          
                    }

                    }

                    otherlv_58=(Token)match(input,87,FOLLOW_87_in_ruleAtomicExpr5793); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_58, grammarAccess.getAtomicExprAccess().getLetKeyword_12_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2665:1: ( (lv_binding_59_0= ruleLetBinding ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2666:1: (lv_binding_59_0= ruleLetBinding )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2666:1: (lv_binding_59_0= ruleLetBinding )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2667:3: lv_binding_59_0= ruleLetBinding
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getBindingLetBindingParserRuleCall_12_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleLetBinding_in_ruleAtomicExpr5814);
                    lv_binding_59_0=ruleLetBinding();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"binding",
                              		lv_binding_59_0, 
                              		"LetBinding");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_60=(Token)match(input,88,FOLLOW_88_in_ruleAtomicExpr5826); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_60, grammarAccess.getAtomicExprAccess().getSemicolonKeyword_12_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2687:1: ( (lv_expr_61_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2688:1: (lv_expr_61_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2688:1: (lv_expr_61_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2689:3: lv_expr_61_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_12_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5847);
                    lv_expr_61_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_61_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:6: (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:6: (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2706:8: otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')'
                    {
                    otherlv_62=(Token)match(input,57,FOLLOW_57_in_ruleAtomicExpr5867); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_62, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_13_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAtomicExprAccess().getExprParserRuleCall_13_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5889);
                    this_Expr_63=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Expr_63; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_64=(Token)match(input,59,FOLLOW_59_in_ruleAtomicExpr5900); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_64, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_13_2());
                          
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


    // $ANTLR start "entryRuleLetBinding"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2731:1: entryRuleLetBinding returns [EObject current=null] : iv_ruleLetBinding= ruleLetBinding EOF ;
    public final EObject entryRuleLetBinding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetBinding = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2732:2: (iv_ruleLetBinding= ruleLetBinding EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2733:2: iv_ruleLetBinding= ruleLetBinding EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetBindingRule()); 
            }
            pushFollow(FOLLOW_ruleLetBinding_in_entryRuleLetBinding5937);
            iv_ruleLetBinding=ruleLetBinding();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetBinding; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetBinding5947); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLetBinding"


    // $ANTLR start "ruleLetBinding"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2740:1: ruleLetBinding returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleLetBinding() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2743:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2744:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2744:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2744:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2744:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2745:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2745:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2746:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLetBinding5989); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getLetBindingAccess().getNameIDTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getLetBindingRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,55,FOLLOW_55_in_ruleLetBinding6006); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLetBindingAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2766:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2767:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2767:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2768:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetBindingAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleLetBinding6027);
            lv_type_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetBindingRule());
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

            otherlv_3=(Token)match(input,56,FOLLOW_56_in_ruleLetBinding6039); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getLetBindingAccess().getEqualsSignKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:1: ( (lv_expr_4_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2789:1: (lv_expr_4_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2789:1: (lv_expr_4_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2790:3: lv_expr_4_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetBindingAccess().getExprExprParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleLetBinding6060);
            lv_expr_4_0=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetBindingRule());
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
    // $ANTLR end "ruleLetBinding"


    // $ANTLR start "entryRuleBuiltInFunc"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2814:1: entryRuleBuiltInFunc returns [String current=null] : iv_ruleBuiltInFunc= ruleBuiltInFunc EOF ;
    public final String entryRuleBuiltInFunc() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltInFunc = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2815:2: (iv_ruleBuiltInFunc= ruleBuiltInFunc EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2816:2: iv_ruleBuiltInFunc= ruleBuiltInFunc EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltInFuncRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc6097);
            iv_ruleBuiltInFunc=ruleBuiltInFunc();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltInFunc.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltInFunc6108); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2823:1: ruleBuiltInFunc returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'subcomponent_of' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_connected' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'instance' | kw= 'instances' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'connections' | kw= 'is_data' | kw= 'is_thread' | kw= 'is_thread_group' | kw= 'is_process' | kw= 'is_subprogram' | kw= 'is_subprogram_group' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_memory' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_system' | kw= 'is_abstract' | kw= 'is_empty' | kw= 'singleton' | kw= 'is_bidirectional' | kw= 'is_data_access' | kw= 'is_bus_access' | kw= 'is_data_port' | kw= 'is_event_port' ) ;
    public final AntlrDatatypeRuleToken ruleBuiltInFunc() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2826:28: ( (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'subcomponent_of' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_connected' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'instance' | kw= 'instances' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'connections' | kw= 'is_data' | kw= 'is_thread' | kw= 'is_thread_group' | kw= 'is_process' | kw= 'is_subprogram' | kw= 'is_subprogram_group' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_memory' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_system' | kw= 'is_abstract' | kw= 'is_empty' | kw= 'singleton' | kw= 'is_bidirectional' | kw= 'is_data_access' | kw= 'is_bus_access' | kw= 'is_data_port' | kw= 'is_event_port' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2827:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'subcomponent_of' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_connected' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'instance' | kw= 'instances' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'connections' | kw= 'is_data' | kw= 'is_thread' | kw= 'is_thread_group' | kw= 'is_process' | kw= 'is_subprogram' | kw= 'is_subprogram_group' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_memory' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_system' | kw= 'is_abstract' | kw= 'is_empty' | kw= 'singleton' | kw= 'is_bidirectional' | kw= 'is_data_access' | kw= 'is_bus_access' | kw= 'is_data_port' | kw= 'is_event_port' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2827:1: (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw= 'subcomponent_of' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_connected' | kw= 'bound' | kw= 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw= 'sum' | kw= 'analysis' | kw= 'instance' | kw= 'instances' | kw= 'upper_bound' | kw= 'lower_bound' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'connections' | kw= 'is_data' | kw= 'is_thread' | kw= 'is_thread_group' | kw= 'is_process' | kw= 'is_subprogram' | kw= 'is_subprogram_group' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_memory' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_system' | kw= 'is_abstract' | kw= 'is_empty' | kw= 'singleton' | kw= 'is_bidirectional' | kw= 'is_data_access' | kw= 'is_bus_access' | kw= 'is_data_port' | kw= 'is_event_port' )
            int alt37=45;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt37=1;
                }
                break;
            case 90:
                {
                alt37=2;
                }
                break;
            case 91:
                {
                alt37=3;
                }
                break;
            case 92:
                {
                alt37=4;
                }
                break;
            case 93:
                {
                alt37=5;
                }
                break;
            case 94:
                {
                alt37=6;
                }
                break;
            case 95:
                {
                alt37=7;
                }
                break;
            case 96:
                {
                alt37=8;
                }
                break;
            case 97:
                {
                alt37=9;
                }
                break;
            case 98:
                {
                alt37=10;
                }
                break;
            case 99:
                {
                alt37=11;
                }
                break;
            case 100:
                {
                alt37=12;
                }
                break;
            case 101:
                {
                alt37=13;
                }
                break;
            case 102:
                {
                alt37=14;
                }
                break;
            case 103:
                {
                alt37=15;
                }
                break;
            case 104:
                {
                alt37=16;
                }
                break;
            case 105:
                {
                alt37=17;
                }
                break;
            case 106:
                {
                alt37=18;
                }
                break;
            case 107:
                {
                alt37=19;
                }
                break;
            case 108:
                {
                alt37=20;
                }
                break;
            case 109:
                {
                alt37=21;
                }
                break;
            case 110:
                {
                alt37=22;
                }
                break;
            case 111:
                {
                alt37=23;
                }
                break;
            case 32:
                {
                alt37=24;
                }
                break;
            case 112:
                {
                alt37=25;
                }
                break;
            case 113:
                {
                alt37=26;
                }
                break;
            case 114:
                {
                alt37=27;
                }
                break;
            case 115:
                {
                alt37=28;
                }
                break;
            case 116:
                {
                alt37=29;
                }
                break;
            case 117:
                {
                alt37=30;
                }
                break;
            case 118:
                {
                alt37=31;
                }
                break;
            case 119:
                {
                alt37=32;
                }
                break;
            case 120:
                {
                alt37=33;
                }
                break;
            case 121:
                {
                alt37=34;
                }
                break;
            case 122:
                {
                alt37=35;
                }
                break;
            case 123:
                {
                alt37=36;
                }
                break;
            case 124:
                {
                alt37=37;
                }
                break;
            case 125:
                {
                alt37=38;
                }
                break;
            case 126:
                {
                alt37=39;
                }
                break;
            case 127:
                {
                alt37=40;
                }
                break;
            case 128:
                {
                alt37=41;
                }
                break;
            case 129:
                {
                alt37=42;
                }
                break;
            case 130:
                {
                alt37=43;
                }
                break;
            case 131:
                {
                alt37=44;
                }
                break;
            case 132:
                {
                alt37=45;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2828:2: kw= 'connected'
                    {
                    kw=(Token)match(input,89,FOLLOW_89_in_ruleBuiltInFunc6146); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConnectedKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2835:2: kw= 'property_lookup'
                    {
                    kw=(Token)match(input,90,FOLLOW_90_in_ruleBuiltInFunc6165); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_lookupKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2842:2: kw= 'class_of'
                    {
                    kw=(Token)match(input,91,FOLLOW_91_in_ruleBuiltInFunc6184); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getClass_ofKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2849:2: kw= 'subcomponent_of'
                    {
                    kw=(Token)match(input,92,FOLLOW_92_in_ruleBuiltInFunc6203); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSubcomponent_ofKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2856:2: kw= 'name'
                    {
                    kw=(Token)match(input,93,FOLLOW_93_in_ruleBuiltInFunc6222); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getNameKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2863:2: kw= 'type'
                    {
                    kw=(Token)match(input,94,FOLLOW_94_in_ruleBuiltInFunc6241); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getTypeKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2870:2: kw= 'has_type'
                    {
                    kw=(Token)match(input,95,FOLLOW_95_in_ruleBuiltInFunc6260); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getHas_typeKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2877:2: kw= 'is_connected'
                    {
                    kw=(Token)match(input,96,FOLLOW_96_in_ruleBuiltInFunc6279); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_connectedKeyword_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2884:2: kw= 'bound'
                    {
                    kw=(Token)match(input,97,FOLLOW_97_in_ruleBuiltInFunc6298); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getBoundKeyword_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2891:2: kw= 'contained'
                    {
                    kw=(Token)match(input,98,FOLLOW_98_in_ruleBuiltInFunc6317); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getContainedKeyword_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2898:2: kw= 'conn_source'
                    {
                    kw=(Token)match(input,99,FOLLOW_99_in_ruleBuiltInFunc6336); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_sourceKeyword_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2905:2: kw= 'conn_dest'
                    {
                    kw=(Token)match(input,100,FOLLOW_100_in_ruleBuiltInFunc6355); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConn_destKeyword_11()); 
                          
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2912:2: kw= 'property_exists'
                    {
                    kw=(Token)match(input,101,FOLLOW_101_in_ruleBuiltInFunc6374); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getProperty_existsKeyword_12()); 
                          
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2919:2: kw= 'sum'
                    {
                    kw=(Token)match(input,102,FOLLOW_102_in_ruleBuiltInFunc6393); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSumKeyword_13()); 
                          
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2926:2: kw= 'analysis'
                    {
                    kw=(Token)match(input,103,FOLLOW_103_in_ruleBuiltInFunc6412); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getAnalysisKeyword_14()); 
                          
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2933:2: kw= 'instance'
                    {
                    kw=(Token)match(input,104,FOLLOW_104_in_ruleBuiltInFunc6431); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getInstanceKeyword_15()); 
                          
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2940:2: kw= 'instances'
                    {
                    kw=(Token)match(input,105,FOLLOW_105_in_ruleBuiltInFunc6450); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getInstancesKeyword_16()); 
                          
                    }

                    }
                    break;
                case 18 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2947:2: kw= 'upper_bound'
                    {
                    kw=(Token)match(input,106,FOLLOW_106_in_ruleBuiltInFunc6469); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getUpper_boundKeyword_17()); 
                          
                    }

                    }
                    break;
                case 19 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2954:2: kw= 'lower_bound'
                    {
                    kw=(Token)match(input,107,FOLLOW_107_in_ruleBuiltInFunc6488); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getLower_boundKeyword_18()); 
                          
                    }

                    }
                    break;
                case 20 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2961:2: kw= 'receive_error'
                    {
                    kw=(Token)match(input,108,FOLLOW_108_in_ruleBuiltInFunc6507); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getReceive_errorKeyword_19()); 
                          
                    }

                    }
                    break;
                case 21 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2968:2: kw= 'contain_error'
                    {
                    kw=(Token)match(input,109,FOLLOW_109_in_ruleBuiltInFunc6526); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getContain_errorKeyword_20()); 
                          
                    }

                    }
                    break;
                case 22 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2975:2: kw= 'propagate_error'
                    {
                    kw=(Token)match(input,110,FOLLOW_110_in_ruleBuiltInFunc6545); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getPropagate_errorKeyword_21()); 
                          
                    }

                    }
                    break;
                case 23 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2982:2: kw= 'error_state_reachable'
                    {
                    kw=(Token)match(input,111,FOLLOW_111_in_ruleBuiltInFunc6564); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getError_state_reachableKeyword_22()); 
                          
                    }

                    }
                    break;
                case 24 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2989:2: kw= 'connections'
                    {
                    kw=(Token)match(input,32,FOLLOW_32_in_ruleBuiltInFunc6583); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getConnectionsKeyword_23()); 
                          
                    }

                    }
                    break;
                case 25 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2996:2: kw= 'is_data'
                    {
                    kw=(Token)match(input,112,FOLLOW_112_in_ruleBuiltInFunc6602); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_dataKeyword_24()); 
                          
                    }

                    }
                    break;
                case 26 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3003:2: kw= 'is_thread'
                    {
                    kw=(Token)match(input,113,FOLLOW_113_in_ruleBuiltInFunc6621); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_threadKeyword_25()); 
                          
                    }

                    }
                    break;
                case 27 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3010:2: kw= 'is_thread_group'
                    {
                    kw=(Token)match(input,114,FOLLOW_114_in_ruleBuiltInFunc6640); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_thread_groupKeyword_26()); 
                          
                    }

                    }
                    break;
                case 28 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3017:2: kw= 'is_process'
                    {
                    kw=(Token)match(input,115,FOLLOW_115_in_ruleBuiltInFunc6659); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_processKeyword_27()); 
                          
                    }

                    }
                    break;
                case 29 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3024:2: kw= 'is_subprogram'
                    {
                    kw=(Token)match(input,116,FOLLOW_116_in_ruleBuiltInFunc6678); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_subprogramKeyword_28()); 
                          
                    }

                    }
                    break;
                case 30 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3031:2: kw= 'is_subprogram_group'
                    {
                    kw=(Token)match(input,117,FOLLOW_117_in_ruleBuiltInFunc6697); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_subprogram_groupKeyword_29()); 
                          
                    }

                    }
                    break;
                case 31 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3038:2: kw= 'is_processor'
                    {
                    kw=(Token)match(input,118,FOLLOW_118_in_ruleBuiltInFunc6716); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_processorKeyword_30()); 
                          
                    }

                    }
                    break;
                case 32 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3045:2: kw= 'is_virtual_processor'
                    {
                    kw=(Token)match(input,119,FOLLOW_119_in_ruleBuiltInFunc6735); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_virtual_processorKeyword_31()); 
                          
                    }

                    }
                    break;
                case 33 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3052:2: kw= 'is_memory'
                    {
                    kw=(Token)match(input,120,FOLLOW_120_in_ruleBuiltInFunc6754); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_memoryKeyword_32()); 
                          
                    }

                    }
                    break;
                case 34 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3059:2: kw= 'is_bus'
                    {
                    kw=(Token)match(input,121,FOLLOW_121_in_ruleBuiltInFunc6773); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_busKeyword_33()); 
                          
                    }

                    }
                    break;
                case 35 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3066:2: kw= 'is_virtual_bus'
                    {
                    kw=(Token)match(input,122,FOLLOW_122_in_ruleBuiltInFunc6792); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_virtual_busKeyword_34()); 
                          
                    }

                    }
                    break;
                case 36 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3073:2: kw= 'is_device'
                    {
                    kw=(Token)match(input,123,FOLLOW_123_in_ruleBuiltInFunc6811); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_deviceKeyword_35()); 
                          
                    }

                    }
                    break;
                case 37 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3080:2: kw= 'is_system'
                    {
                    kw=(Token)match(input,124,FOLLOW_124_in_ruleBuiltInFunc6830); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_systemKeyword_36()); 
                          
                    }

                    }
                    break;
                case 38 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3087:2: kw= 'is_abstract'
                    {
                    kw=(Token)match(input,125,FOLLOW_125_in_ruleBuiltInFunc6849); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_abstractKeyword_37()); 
                          
                    }

                    }
                    break;
                case 39 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3094:2: kw= 'is_empty'
                    {
                    kw=(Token)match(input,126,FOLLOW_126_in_ruleBuiltInFunc6868); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_emptyKeyword_38()); 
                          
                    }

                    }
                    break;
                case 40 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3101:2: kw= 'singleton'
                    {
                    kw=(Token)match(input,127,FOLLOW_127_in_ruleBuiltInFunc6887); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSingletonKeyword_39()); 
                          
                    }

                    }
                    break;
                case 41 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3108:2: kw= 'is_bidirectional'
                    {
                    kw=(Token)match(input,128,FOLLOW_128_in_ruleBuiltInFunc6906); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_bidirectionalKeyword_40()); 
                          
                    }

                    }
                    break;
                case 42 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3115:2: kw= 'is_data_access'
                    {
                    kw=(Token)match(input,129,FOLLOW_129_in_ruleBuiltInFunc6925); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_data_accessKeyword_41()); 
                          
                    }

                    }
                    break;
                case 43 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3122:2: kw= 'is_bus_access'
                    {
                    kw=(Token)match(input,130,FOLLOW_130_in_ruleBuiltInFunc6944); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_bus_accessKeyword_42()); 
                          
                    }

                    }
                    break;
                case 44 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3129:2: kw= 'is_data_port'
                    {
                    kw=(Token)match(input,131,FOLLOW_131_in_ruleBuiltInFunc6963); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_data_portKeyword_43()); 
                          
                    }

                    }
                    break;
                case 45 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3136:2: kw= 'is_event_port'
                    {
                    kw=(Token)match(input,132,FOLLOW_132_in_ruleBuiltInFunc6982); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getIs_event_portKeyword_44()); 
                          
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3149:1: entryRuleRealTerm returns [EObject current=null] : iv_ruleRealTerm= ruleRealTerm EOF ;
    public final EObject entryRuleRealTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3150:2: (iv_ruleRealTerm= ruleRealTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3151:2: iv_ruleRealTerm= ruleRealTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealTermRule()); 
            }
            pushFollow(FOLLOW_ruleRealTerm_in_entryRuleRealTerm7022);
            iv_ruleRealTerm=ruleRealTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealTerm7032); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3158:1: ruleRealTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleRealTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3161:28: ( ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3162:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3162:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3162:2: ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3162:2: ( (lv_value_0_0= ruleUnsignedReal ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3163:1: (lv_value_0_0= ruleUnsignedReal )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3163:1: (lv_value_0_0= ruleUnsignedReal )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3164:3: lv_value_0_0= ruleUnsignedReal
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRealTermAccess().getValueUnsignedRealParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_ruleRealTerm7078);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3180:2: ( (otherlv_1= RULE_ID ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_ID) ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==EOF||LA38_1==RULE_ID||LA38_1==17||LA38_1==56||(LA38_1>=58 && LA38_1<=60)||(LA38_1>=62 && LA38_1<=75)||(LA38_1>=81 && LA38_1<=82)||LA38_1==85||LA38_1==88||(LA38_1>=133 && LA38_1<=134)||LA38_1==137||(LA38_1>=147 && LA38_1<=148)) ) {
                    alt38=1;
                }
            }
            switch (alt38) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3181:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3181:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3182:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getRealTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRealTerm7098); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3201:1: entryRuleUnsignedReal returns [String current=null] : iv_ruleUnsignedReal= ruleUnsignedReal EOF ;
    public final String entryRuleUnsignedReal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedReal = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3202:2: (iv_ruleUnsignedReal= ruleUnsignedReal EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3203:2: iv_ruleUnsignedReal= ruleUnsignedReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedRealRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal7136);
            iv_ruleUnsignedReal=ruleUnsignedReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedReal.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedReal7147); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3210:1: ruleUnsignedReal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_REAL_LIT_0= RULE_REAL_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedReal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3213:28: (this_REAL_LIT_0= RULE_REAL_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3214:5: this_REAL_LIT_0= RULE_REAL_LIT
            {
            this_REAL_LIT_0=(Token)match(input,RULE_REAL_LIT,FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal7186); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3229:1: entryRuleIntegerTerm returns [EObject current=null] : iv_ruleIntegerTerm= ruleIntegerTerm EOF ;
    public final EObject entryRuleIntegerTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3230:2: (iv_ruleIntegerTerm= ruleIntegerTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3231:2: iv_ruleIntegerTerm= ruleIntegerTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerTermRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm7230);
            iv_ruleIntegerTerm=ruleIntegerTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerTerm7240); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3238:1: ruleIntegerTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleIntegerTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3241:28: ( ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3242:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3242:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3242:2: ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3242:2: ( (lv_value_0_0= ruleUnsignedInt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3243:1: (lv_value_0_0= ruleUnsignedInt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3243:1: (lv_value_0_0= ruleUnsignedInt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3244:3: lv_value_0_0= ruleUnsignedInt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntegerTermAccess().getValueUnsignedIntParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm7286);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3260:2: ( (otherlv_1= RULE_ID ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==RULE_ID) ) {
                int LA39_1 = input.LA(2);

                if ( (LA39_1==EOF||LA39_1==RULE_ID||LA39_1==17||LA39_1==56||(LA39_1>=58 && LA39_1<=60)||(LA39_1>=62 && LA39_1<=75)||(LA39_1>=81 && LA39_1<=82)||LA39_1==85||LA39_1==88||(LA39_1>=133 && LA39_1<=134)||LA39_1==137||(LA39_1>=147 && LA39_1<=148)) ) {
                    alt39=1;
                }
            }
            switch (alt39) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3261:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3261:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3262:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getIntegerTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIntegerTerm7306); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3281:1: entryRuleUnsignedInt returns [String current=null] : iv_ruleUnsignedInt= ruleUnsignedInt EOF ;
    public final String entryRuleUnsignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedInt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3282:2: (iv_ruleUnsignedInt= ruleUnsignedInt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3283:2: iv_ruleUnsignedInt= ruleUnsignedInt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedIntRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt7344);
            iv_ruleUnsignedInt=ruleUnsignedInt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedInt.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedInt7355); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3290:1: ruleUnsignedInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3293:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3294:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt7394); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3309:1: entryRuleResoluteSubclause returns [EObject current=null] : iv_ruleResoluteSubclause= ruleResoluteSubclause EOF ;
    public final EObject entryRuleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteSubclause = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3310:2: (iv_ruleResoluteSubclause= ruleResoluteSubclause EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3311:2: iv_ruleResoluteSubclause= ruleResoluteSubclause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getResoluteSubclauseRule()); 
            }
            pushFollow(FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause7438);
            iv_ruleResoluteSubclause=ruleResoluteSubclause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleResoluteSubclause; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleResoluteSubclause7448); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3318:1: ruleResoluteSubclause returns [EObject current=null] : ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) ;
    public final EObject ruleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject lv_proves_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3321:28: ( ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3322:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3322:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3322:2: () ( (lv_proves_1_0= ruleProveStatement ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3322:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3323:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getResoluteSubclauseAccess().getResoluteSubclauseAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3328:2: ( (lv_proves_1_0= ruleProveStatement ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==133) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3329:1: (lv_proves_1_0= ruleProveStatement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3329:1: (lv_proves_1_0= ruleProveStatement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3330:3: lv_proves_1_0= ruleProveStatement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getResoluteSubclauseAccess().getProvesProveStatementParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleProveStatement_in_ruleResoluteSubclause7503);
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
            	    break loop40;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3354:1: entryRuleNestedDotID returns [EObject current=null] : iv_ruleNestedDotID= ruleNestedDotID EOF ;
    public final EObject entryRuleNestedDotID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedDotID = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3355:2: (iv_ruleNestedDotID= ruleNestedDotID EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3356:2: iv_ruleNestedDotID= ruleNestedDotID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNestedDotIDRule()); 
            }
            pushFollow(FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID7540);
            iv_ruleNestedDotID=ruleNestedDotID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNestedDotID; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNestedDotID7550); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3363:1: ruleNestedDotID returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) ;
    public final EObject ruleNestedDotID() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_sub_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3366:28: ( ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3367:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3367:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3367:2: ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3367:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3368:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3368:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3369:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNestedDotIDRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNestedDotID7595); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getNestedDotIDAccess().getBaseNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3380:2: (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==77) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3380:4: otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) )
                    {
                    otherlv_1=(Token)match(input,77,FOLLOW_77_in_ruleNestedDotID7608); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNestedDotIDAccess().getFullStopKeyword_1_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3384:1: ( (lv_sub_2_0= ruleNestedDotID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3385:1: (lv_sub_2_0= ruleNestedDotID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3385:1: (lv_sub_2_0= ruleNestedDotID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3386:3: lv_sub_2_0= ruleNestedDotID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNestedDotIDAccess().getSubNestedDotIDParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNestedDotID_in_ruleNestedDotID7629);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3410:1: entryRuleProveStatement returns [EObject current=null] : iv_ruleProveStatement= ruleProveStatement EOF ;
    public final EObject entryRuleProveStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProveStatement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3411:2: (iv_ruleProveStatement= ruleProveStatement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3412:2: iv_ruleProveStatement= ruleProveStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProveStatementRule()); 
            }
            pushFollow(FOLLOW_ruleProveStatement_in_entryRuleProveStatement7667);
            iv_ruleProveStatement=ruleProveStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProveStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleProveStatement7677); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3419:1: ruleProveStatement returns [EObject current=null] : (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3422:28: ( (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3423:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3423:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3423:3: otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )?
            {
            otherlv_0=(Token)match(input,133,FOLLOW_133_in_ruleProveStatement7714); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getProveStatementAccess().getProveKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3427:1: ( (lv_expr_1_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3428:1: (lv_expr_1_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3428:1: (lv_expr_1_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3429:3: lv_expr_1_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getProveStatementAccess().getExprExprParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleProveStatement7735);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:2: ( ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==134) && (synpred9_InternalResolute())) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:3: ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )=> (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3456:8: (otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )* )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3456:10: otherlv_2= 'modes' otherlv_3= ':' ( (lv_modes_4_0= ruleNestedDotID ) ) (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )*
                    {
                    otherlv_2=(Token)match(input,134,FOLLOW_134_in_ruleProveStatement7787); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getProveStatementAccess().getModesKeyword_2_0_0());
                          
                    }
                    otherlv_3=(Token)match(input,55,FOLLOW_55_in_ruleProveStatement7799); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getProveStatementAccess().getColonKeyword_2_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3464:1: ( (lv_modes_4_0= ruleNestedDotID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3465:1: (lv_modes_4_0= ruleNestedDotID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3465:1: (lv_modes_4_0= ruleNestedDotID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3466:3: lv_modes_4_0= ruleNestedDotID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getProveStatementAccess().getModesNestedDotIDParserRuleCall_2_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNestedDotID_in_ruleProveStatement7820);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3482:2: (otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==58) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3482:4: otherlv_5= ',' ( (lv_modes_6_0= ruleNestedDotID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,58,FOLLOW_58_in_ruleProveStatement7833); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getProveStatementAccess().getCommaKeyword_2_0_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3486:1: ( (lv_modes_6_0= ruleNestedDotID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3487:1: (lv_modes_6_0= ruleNestedDotID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3487:1: (lv_modes_6_0= ruleNestedDotID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3488:3: lv_modes_6_0= ruleNestedDotID
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getProveStatementAccess().getModesNestedDotIDParserRuleCall_2_0_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleNestedDotID_in_ruleProveStatement7854);
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
                    	    break loop42;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3514:1: entryRuleContainedPropertyAssociation returns [EObject current=null] : iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF ;
    public final EObject entryRuleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainedPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3515:2: (iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3516:2: iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainedPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation7897);
            iv_ruleContainedPropertyAssociation=ruleContainedPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainedPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainedPropertyAssociation7907); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3523:1: ruleContainedPropertyAssociation returns [EObject current=null] : ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3526:28: ( ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3527:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3527:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3527:2: ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3527:2: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3528:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3528:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3529:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getPropertyPropertyCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation7955);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3542:2: (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==62) ) {
                alt44=1;
            }
            else if ( (LA44_0==135) ) {
                alt44=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3542:4: otherlv_1= '=>'
                    {
                    otherlv_1=(Token)match(input,62,FOLLOW_62_in_ruleContainedPropertyAssociation7968); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getContainedPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3547:6: ( (lv_append_2_0= '+=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3547:6: ( (lv_append_2_0= '+=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3548:1: (lv_append_2_0= '+=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3548:1: (lv_append_2_0= '+=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3549:3: lv_append_2_0= '+=>'
                    {
                    lv_append_2_0=(Token)match(input,135,FOLLOW_135_in_ruleContainedPropertyAssociation7992); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3562:3: ( (lv_constant_3_0= 'constant' ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==136) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:1: (lv_constant_3_0= 'constant' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:1: (lv_constant_3_0= 'constant' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3564:3: lv_constant_3_0= 'constant'
                    {
                    lv_constant_3_0=(Token)match(input,136,FOLLOW_136_in_ruleContainedPropertyAssociation8024); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3577:3: ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3577:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3577:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3578:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3578:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3579:3: lv_ownedValue_4_0= ruleOptionalModalPropertyValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8060);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3595:2: (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==58) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3595:4: otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    {
            	    otherlv_5=(Token)match(input,58,FOLLOW_58_in_ruleContainedPropertyAssociation8073); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_3_1_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3599:1: ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3600:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3600:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3601:3: lv_ownedValue_6_0= ruleOptionalModalPropertyValue
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8094);
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
            	    break loop46;
                }
            } while (true);


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3617:5: (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==137) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3617:7: otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    {
                    otherlv_7=(Token)match(input,137,FOLLOW_137_in_ruleContainedPropertyAssociation8110); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getContainedPropertyAssociationAccess().getAppliesKeyword_4_0());
                          
                    }
                    otherlv_8=(Token)match(input,138,FOLLOW_138_in_ruleContainedPropertyAssociation8122); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getContainedPropertyAssociationAccess().getToKeyword_4_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3625:1: ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3626:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3626:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3627:3: lv_appliesTo_9_0= ruleContainmentPath
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8143);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3643:2: (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==58) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3643:4: otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    {
                    	    otherlv_10=(Token)match(input,58,FOLLOW_58_in_ruleContainedPropertyAssociation8156); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_4_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3647:1: ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3648:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3648:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:3: lv_appliesTo_11_0= ruleContainmentPath
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8177);
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
                    	    break loop47;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3665:6: (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==69) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3665:8: otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')'
                    {
                    otherlv_12=(Token)match(input,69,FOLLOW_69_in_ruleContainedPropertyAssociation8194); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getContainedPropertyAssociationAccess().getInKeyword_5_0());
                          
                    }
                    otherlv_13=(Token)match(input,139,FOLLOW_139_in_ruleContainedPropertyAssociation8206); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getContainedPropertyAssociationAccess().getBindingKeyword_5_1());
                          
                    }
                    otherlv_14=(Token)match(input,57,FOLLOW_57_in_ruleContainedPropertyAssociation8218); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getContainedPropertyAssociationAccess().getLeftParenthesisKeyword_5_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3677:1: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3678:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3678:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3679:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getInBindingClassifierCrossReference_5_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation8241);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,59,FOLLOW_59_in_ruleContainedPropertyAssociation8253); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getContainedPropertyAssociationAccess().getRightParenthesisKeyword_5_4());
                          
                    }

                    }
                    break;

            }

            otherlv_17=(Token)match(input,88,FOLLOW_88_in_ruleContainedPropertyAssociation8267); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3712:1: entryRuleContainmentPath returns [EObject current=null] : iv_ruleContainmentPath= ruleContainmentPath EOF ;
    public final EObject entryRuleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPath = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3713:2: (iv_ruleContainmentPath= ruleContainmentPath EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3714:2: iv_ruleContainmentPath= ruleContainmentPath EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath8307);
            iv_ruleContainmentPath=ruleContainmentPath();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPath; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPath8317); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3721:1: ruleContainmentPath returns [EObject current=null] : ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) ;
    public final EObject ruleContainmentPath() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_containmentPathElement_1_0 = null;

        EObject lv_containmentPathElement_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3724:28: ( ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3725:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3725:1: ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3725:2: () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3725:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3726:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getContainmentPathAccess().getContainedNamedElementAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3731:2: ( (lv_containmentPathElement_1_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3732:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3732:1: (lv_containmentPathElement_1_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3733:3: lv_containmentPathElement_1_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8372);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3749:2: (otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==77) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3749:4: otherlv_2= '.' ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_2=(Token)match(input,77,FOLLOW_77_in_ruleContainmentPath8385); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getContainmentPathAccess().getFullStopKeyword_2_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3753:1: ( (lv_containmentPathElement_3_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3754:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3754:1: (lv_containmentPathElement_3_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3755:3: lv_containmentPathElement_3_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainmentPathAccess().getContainmentPathElementContainmentPathElementParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8406);
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
            	    break loop50;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3781:1: entryRuleOptionalModalPropertyValue returns [EObject current=null] : iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF ;
    public final EObject entryRuleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalModalPropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3782:2: (iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3783:2: iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOptionalModalPropertyValueRule()); 
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue8446);
            iv_ruleOptionalModalPropertyValue=ruleOptionalModalPropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOptionalModalPropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue8456); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3790:1: ruleOptionalModalPropertyValue returns [EObject current=null] : ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3793:28: ( ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3794:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3794:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3794:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3794:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3795:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3795:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3796:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOptionalModalPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue8502);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3812:2: (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==69) ) {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==134) ) {
                    alt52=1;
                }
            }
            switch (alt52) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3812:4: otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')'
                    {
                    otherlv_1=(Token)match(input,69,FOLLOW_69_in_ruleOptionalModalPropertyValue8515); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOptionalModalPropertyValueAccess().getInKeyword_1_0());
                          
                    }
                    otherlv_2=(Token)match(input,134,FOLLOW_134_in_ruleOptionalModalPropertyValue8527); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOptionalModalPropertyValueAccess().getModesKeyword_1_1());
                          
                    }
                    otherlv_3=(Token)match(input,57,FOLLOW_57_in_ruleOptionalModalPropertyValue8539); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOptionalModalPropertyValueAccess().getLeftParenthesisKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3824:1: ( (otherlv_4= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3825:1: (otherlv_4= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3825:1: (otherlv_4= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3826:3: otherlv_4= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                      	        }
                              
                    }
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8559); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_4, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_3_0()); 
                      	
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3837:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==58) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3837:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,58,FOLLOW_58_in_ruleOptionalModalPropertyValue8572); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getOptionalModalPropertyValueAccess().getCommaKeyword_1_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3841:1: ( (otherlv_6= RULE_ID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3842:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3842:1: (otherlv_6= RULE_ID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3843:3: otherlv_6= RULE_ID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      			if (current==null) {
                    	      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                    	      	        }
                    	              
                    	    }
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8592); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		newLeafNode(otherlv_6, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_4_1_0()); 
                    	      	
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,59,FOLLOW_59_in_ruleOptionalModalPropertyValue8606); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3866:1: entryRulePropertyValue returns [EObject current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final EObject entryRulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3867:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3868:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyValueRule()); 
            }
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue8644);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue8654); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3875:1: rulePropertyValue returns [EObject current=null] : ( (lv_ownedValue_0_0= rulePropertyExpression ) ) ;
    public final EObject rulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedValue_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3878:28: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3879:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3879:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3880:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3880:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3881:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyValue8699);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3905:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3906:2: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3907:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression8734);
            iv_rulePropertyExpression=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression8744); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3914:1: rulePropertyExpression returns [EObject current=null] : (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3917:28: ( (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3918:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3918:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            int alt53=11;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3919:5: this_RecordTerm_0= ruleRecordTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRecordTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRecordTerm_in_rulePropertyExpression8791);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3929:5: this_ReferenceTerm_1= ruleReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getReferenceTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleReferenceTerm_in_rulePropertyExpression8818);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3939:5: this_ComponentClassifierTerm_2= ruleComponentClassifierTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComponentClassifierTermParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression8845);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3949:5: this_ComputedTerm_3= ruleComputedTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComputedTermParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComputedTerm_in_rulePropertyExpression8872);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3959:5: this_StringTerm_4= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getStringTermParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_rulePropertyExpression8899);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3969:5: this_NumericRangeTerm_5= ruleNumericRangeTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getNumericRangeTermParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression8926);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3979:5: this_RealTerm_6= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRealTermParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_rulePropertyExpression8953);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3989:5: this_IntegerTerm_7= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getIntegerTermParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_rulePropertyExpression8980);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3999:5: this_ListTerm_8= ruleListTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getListTermParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListTerm_in_rulePropertyExpression9007);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4009:5: this_BooleanLiteral_9= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getBooleanLiteralParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression9034);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4019:5: this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getLiteralorReferenceTermParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression9061);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4035:1: entryRuleLiteralorReferenceTerm returns [EObject current=null] : iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF ;
    public final EObject entryRuleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralorReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:2: (iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4037:2: iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralorReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm9096);
            iv_ruleLiteralorReferenceTerm=ruleLiteralorReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralorReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm9106); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4044:1: ruleLiteralorReferenceTerm returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4047:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4048:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4048:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4049:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4049:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4050:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLiteralorReferenceTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLiteralorReferenceTermAccess().getNamedValueAbstractNamedValueCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm9153);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4071:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4072:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4073:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral9188);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral9198); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4080:1: ruleBooleanLiteral returns [EObject current=null] : ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4083:28: ( ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4084:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4084:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4084:2: () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4084:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4085:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4090:2: ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==140) ) {
                alt54=1;
            }
            else if ( (LA54_0==141) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4090:3: ( (lv_value_1_0= 'true' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4090:3: ( (lv_value_1_0= 'true' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4091:1: (lv_value_1_0= 'true' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4091:1: (lv_value_1_0= 'true' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4092:3: lv_value_1_0= 'true'
                    {
                    lv_value_1_0=(Token)match(input,140,FOLLOW_140_in_ruleBooleanLiteral9251); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4106:7: otherlv_2= 'false'
                    {
                    otherlv_2=(Token)match(input,141,FOLLOW_141_in_ruleBooleanLiteral9282); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4118:1: entryRuleConstantValue returns [EObject current=null] : iv_ruleConstantValue= ruleConstantValue EOF ;
    public final EObject entryRuleConstantValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4119:2: (iv_ruleConstantValue= ruleConstantValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4120:2: iv_ruleConstantValue= ruleConstantValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantValueRule()); 
            }
            pushFollow(FOLLOW_ruleConstantValue_in_entryRuleConstantValue9319);
            iv_ruleConstantValue=ruleConstantValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantValue9329); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4127:1: ruleConstantValue returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleConstantValue() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4130:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4131:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4131:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4132:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4132:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4133:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getConstantValueRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantValueAccess().getNamedValuePropertyConstantCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleConstantValue9376);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4154:1: entryRuleReferenceTerm returns [EObject current=null] : iv_ruleReferenceTerm= ruleReferenceTerm EOF ;
    public final EObject entryRuleReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4155:2: (iv_ruleReferenceTerm= ruleReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4156:2: iv_ruleReferenceTerm= ruleReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm9411);
            iv_ruleReferenceTerm=ruleReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleReferenceTerm9421); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4163:1: ruleReferenceTerm returns [EObject current=null] : (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4166:28: ( (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4167:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4167:1: (otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4167:3: otherlv_0= 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )* otherlv_7= ')'
            {
            otherlv_0=(Token)match(input,142,FOLLOW_142_in_ruleReferenceTerm9458); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getReferenceTermAccess().getReferenceKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleReferenceTerm9470); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getReferenceTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4175:1: ( ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==RULE_ID) ) {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==149) ) {
                    alt55=1;
                }
            }
            switch (alt55) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4175:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) ) otherlv_3= '.'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4175:2: ( (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4176:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4176:1: (lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4177:3: lv_containmentPathElement_2_0= ruleQualifiedContainmentPathElement
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementQualifiedContainmentPathElementParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm9492);
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

                    otherlv_3=(Token)match(input,77,FOLLOW_77_in_ruleReferenceTerm9504); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getReferenceTermAccess().getFullStopKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4197:3: ( (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4198:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4198:1: (lv_containmentPathElement_4_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4199:3: lv_containmentPathElement_4_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9527);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:2: (otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) ) )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==77) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:4: otherlv_5= '.' ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    {
            	    otherlv_5=(Token)match(input,77,FOLLOW_77_in_ruleReferenceTerm9540); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getReferenceTermAccess().getFullStopKeyword_4_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4219:1: ( (lv_containmentPathElement_6_0= ruleContainmentPathElement ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4220:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4220:1: (lv_containmentPathElement_6_0= ruleContainmentPathElement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4221:3: lv_containmentPathElement_6_0= ruleContainmentPathElement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getReferenceTermAccess().getContainmentPathElementContainmentPathElementParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9561);
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
            	    break loop56;
                }
            } while (true);

            otherlv_7=(Token)match(input,59,FOLLOW_59_in_ruleReferenceTerm9575); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4249:1: entryRuleRecordTerm returns [EObject current=null] : iv_ruleRecordTerm= ruleRecordTerm EOF ;
    public final EObject entryRuleRecordTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecordTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4250:2: (iv_ruleRecordTerm= ruleRecordTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4251:2: iv_ruleRecordTerm= ruleRecordTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRecordTermRule()); 
            }
            pushFollow(FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm9611);
            iv_ruleRecordTerm=ruleRecordTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRecordTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRecordTerm9621); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4258:1: ruleRecordTerm returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) ;
    public final EObject ruleRecordTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedFieldValue_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4261:28: ( (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4262:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4262:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4262:3: otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,143,FOLLOW_143_in_ruleRecordTerm9658); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getRecordTermAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4266:1: ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+
            int cnt57=0;
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==RULE_ID) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4267:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4267:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4268:3: lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRecordTermAccess().getOwnedFieldValueFieldPropertyAssociationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm9679);
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
            	    if ( cnt57 >= 1 ) break loop57;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(57, input);
                        throw eee;
                }
                cnt57++;
            } while (true);

            otherlv_2=(Token)match(input,144,FOLLOW_144_in_ruleRecordTerm9692); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4298:1: entryRuleComputedTerm returns [EObject current=null] : iv_ruleComputedTerm= ruleComputedTerm EOF ;
    public final EObject entryRuleComputedTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputedTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4299:2: (iv_ruleComputedTerm= ruleComputedTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4300:2: iv_ruleComputedTerm= ruleComputedTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComputedTermRule()); 
            }
            pushFollow(FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm9730);
            iv_ruleComputedTerm=ruleComputedTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComputedTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComputedTerm9740); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4307:1: ruleComputedTerm returns [EObject current=null] : (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleComputedTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_function_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4310:28: ( (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4311:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4311:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4311:3: otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,145,FOLLOW_145_in_ruleComputedTerm9777); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComputedTermAccess().getComputeKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleComputedTerm9789); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComputedTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4319:1: ( (lv_function_2_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4320:1: (lv_function_2_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4320:1: (lv_function_2_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4321:3: lv_function_2_0= RULE_ID
            {
            lv_function_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleComputedTerm9806); if (state.failed) return current;
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

            otherlv_3=(Token)match(input,59,FOLLOW_59_in_ruleComputedTerm9823); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4349:1: entryRuleComponentClassifierTerm returns [EObject current=null] : iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF ;
    public final EObject entryRuleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentClassifierTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4350:2: (iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4351:2: iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComponentClassifierTermRule()); 
            }
            pushFollow(FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm9859);
            iv_ruleComponentClassifierTerm=ruleComponentClassifierTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComponentClassifierTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComponentClassifierTerm9869); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4358:1: ruleComponentClassifierTerm returns [EObject current=null] : (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) ;
    public final EObject ruleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4361:28: ( (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4362:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4362:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4362:3: otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,146,FOLLOW_146_in_ruleComponentClassifierTerm9906); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComponentClassifierTermAccess().getClassifierKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleComponentClassifierTerm9918); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComponentClassifierTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4370:1: ( ( ruleQCREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4371:1: ( ruleQCREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4371:1: ( ruleQCREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4372:3: ruleQCREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getComponentClassifierTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getComponentClassifierTermAccess().getClassifierComponentClassifierCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm9941);
            ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,59,FOLLOW_59_in_ruleComponentClassifierTerm9953); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4397:1: entryRuleListTerm returns [EObject current=null] : iv_ruleListTerm= ruleListTerm EOF ;
    public final EObject entryRuleListTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4398:2: (iv_ruleListTerm= ruleListTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4399:2: iv_ruleListTerm= ruleListTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListTermRule()); 
            }
            pushFollow(FOLLOW_ruleListTerm_in_entryRuleListTerm9989);
            iv_ruleListTerm=ruleListTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListTerm9999); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4406:1: ruleListTerm returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleListTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedListElement_2_0 = null;

        EObject lv_ownedListElement_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4409:28: ( ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4410:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4410:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4410:2: () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4410:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4411:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListTermAccess().getListValueAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,57,FOLLOW_57_in_ruleListTerm10045); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4420:1: ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( ((LA59_0>=RULE_ID && LA59_0<=RULE_INTEGER_LIT)||LA59_0==57||(LA59_0>=70 && LA59_0<=71)||(LA59_0>=140 && LA59_0<=143)||(LA59_0>=145 && LA59_0<=146)) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4420:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4420:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4421:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4421:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4422:3: lv_ownedListElement_2_0= rulePropertyExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm10067);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4438:2: (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==58) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4438:4: otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,58,FOLLOW_58_in_ruleListTerm10080); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListTermAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4442:1: ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4443:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4443:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4444:3: lv_ownedListElement_4_0= rulePropertyExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm10101);
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
                    	    break loop58;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,59,FOLLOW_59_in_ruleListTerm10117); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4472:1: entryRuleFieldPropertyAssociation returns [EObject current=null] : iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF ;
    public final EObject entryRuleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFieldPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4473:2: (iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4474:2: iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFieldPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation10153);
            iv_ruleFieldPropertyAssociation=ruleFieldPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFieldPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFieldPropertyAssociation10163); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4481:1: ruleFieldPropertyAssociation returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedValue_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4484:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4486:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4486:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4487:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getFieldPropertyAssociationRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation10208); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getFieldPropertyAssociationAccess().getPropertyBasicPropertyCrossReference_0_0()); 
              	
            }

            }


            }

            otherlv_1=(Token)match(input,62,FOLLOW_62_in_ruleFieldPropertyAssociation10220); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFieldPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4502:1: ( (lv_ownedValue_2_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4503:1: (lv_ownedValue_2_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4503:1: (lv_ownedValue_2_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4504:3: lv_ownedValue_2_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFieldPropertyAssociationAccess().getOwnedValuePropertyExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation10241);
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

            otherlv_3=(Token)match(input,88,FOLLOW_88_in_ruleFieldPropertyAssociation10253); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4532:1: entryRuleContainmentPathElement returns [EObject current=null] : iv_ruleContainmentPathElement= ruleContainmentPathElement EOF ;
    public final EObject entryRuleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4533:2: (iv_ruleContainmentPathElement= ruleContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4534:2: iv_ruleContainmentPathElement= ruleContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement10289);
            iv_ruleContainmentPathElement=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPathElement10299); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4541:1: ruleContainmentPathElement returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) ;
    public final EObject ruleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_arrayRange_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4544:28: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4545:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4545:1: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4545:2: ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4545:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4546:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4546:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4547:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainmentPathElementRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleContainmentPathElement10344); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4558:2: ( (lv_arrayRange_1_0= ruleArrayRange ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==143) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4559:1: (lv_arrayRange_1_0= ruleArrayRange )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4559:1: (lv_arrayRange_1_0= ruleArrayRange )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4560:3: lv_arrayRange_1_0= ruleArrayRange
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainmentPathElementAccess().getArrayRangeArrayRangeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArrayRange_in_ruleContainmentPathElement10365);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4584:1: entryRuleQualifiedContainmentPathElement returns [EObject current=null] : iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF ;
    public final EObject entryRuleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4585:2: (iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4586:2: iv_ruleQualifiedContainmentPathElement= ruleQualifiedContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement10402);
            iv_ruleQualifiedContainmentPathElement=ruleQualifiedContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement10412); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4593:1: ruleQualifiedContainmentPathElement returns [EObject current=null] : ( ( ruleQCLREF ) ) ;
    public final EObject ruleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4596:28: ( ( ( ruleQCLREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4597:1: ( ( ruleQCLREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4597:1: ( ( ruleQCLREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4598:1: ( ruleQCLREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4598:1: ( ruleQCLREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4599:3: ruleQCLREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getQualifiedContainmentPathElementRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getQualifiedContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement10459);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4622:1: entryRulePlusMinus returns [String current=null] : iv_rulePlusMinus= rulePlusMinus EOF ;
    public final String entryRulePlusMinus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePlusMinus = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4623:2: (iv_rulePlusMinus= rulePlusMinus EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4624:2: iv_rulePlusMinus= rulePlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusMinusRule()); 
            }
            pushFollow(FOLLOW_rulePlusMinus_in_entryRulePlusMinus10497);
            iv_rulePlusMinus=rulePlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusMinus.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusMinus10508); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4631:1: rulePlusMinus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken rulePlusMinus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4634:28: ( (kw= '+' | kw= '-' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4635:1: (kw= '+' | kw= '-' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4635:1: (kw= '+' | kw= '-' )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==70) ) {
                alt61=1;
            }
            else if ( (LA61_0==71) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4636:2: kw= '+'
                    {
                    kw=(Token)match(input,70,FOLLOW_70_in_rulePlusMinus10546); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPlusMinusAccess().getPlusSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4643:2: kw= '-'
                    {
                    kw=(Token)match(input,71,FOLLOW_71_in_rulePlusMinus10565); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4656:1: entryRuleStringTerm returns [EObject current=null] : iv_ruleStringTerm= ruleStringTerm EOF ;
    public final EObject entryRuleStringTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4657:2: (iv_ruleStringTerm= ruleStringTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4658:2: iv_ruleStringTerm= ruleStringTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringTermRule()); 
            }
            pushFollow(FOLLOW_ruleStringTerm_in_entryRuleStringTerm10605);
            iv_ruleStringTerm=ruleStringTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringTerm10615); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4665:1: ruleStringTerm returns [EObject current=null] : ( (lv_value_0_0= ruleNoQuoteString ) ) ;
    public final EObject ruleStringTerm() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:28: ( ( (lv_value_0_0= ruleNoQuoteString ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4669:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4669:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4670:1: (lv_value_0_0= ruleNoQuoteString )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4670:1: (lv_value_0_0= ruleNoQuoteString )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4671:3: lv_value_0_0= ruleNoQuoteString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStringTermAccess().getValueNoQuoteStringParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_ruleStringTerm10660);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4695:1: entryRuleNoQuoteString returns [String current=null] : iv_ruleNoQuoteString= ruleNoQuoteString EOF ;
    public final String entryRuleNoQuoteString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNoQuoteString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4696:2: (iv_ruleNoQuoteString= ruleNoQuoteString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4697:2: iv_ruleNoQuoteString= ruleNoQuoteString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoQuoteStringRule()); 
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString10696);
            iv_ruleNoQuoteString=ruleNoQuoteString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoQuoteString.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoQuoteString10707); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4704:1: ruleNoQuoteString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNoQuoteString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4707:28: (this_STRING_0= RULE_STRING )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4708:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNoQuoteString10746); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4723:1: entryRuleArrayRange returns [EObject current=null] : iv_ruleArrayRange= ruleArrayRange EOF ;
    public final EObject entryRuleArrayRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayRange = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4724:2: (iv_ruleArrayRange= ruleArrayRange EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4725:2: iv_ruleArrayRange= ruleArrayRange EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayRangeRule()); 
            }
            pushFollow(FOLLOW_ruleArrayRange_in_entryRuleArrayRange10790);
            iv_ruleArrayRange=ruleArrayRange();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayRange; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayRange10800); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4732:1: ruleArrayRange returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleArrayRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_lowerBound_2_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4735:28: ( ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4736:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4736:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4736:2: () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4736:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4737:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayRangeAccess().getArrayRangeAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,143,FOLLOW_143_in_ruleArrayRange10846); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayRangeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4746:1: ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4747:1: (lv_lowerBound_2_0= ruleINTVALUE )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4747:1: (lv_lowerBound_2_0= ruleINTVALUE )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4748:3: lv_lowerBound_2_0= ruleINTVALUE
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayRangeAccess().getLowerBoundINTVALUEParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange10867);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4764:2: (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==147) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4764:4: otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    {
                    otherlv_3=(Token)match(input,147,FOLLOW_147_in_ruleArrayRange10880); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getArrayRangeAccess().getFullStopFullStopKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4768:1: ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4769:1: (lv_upperBound_4_0= ruleINTVALUE )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4769:1: (lv_upperBound_4_0= ruleINTVALUE )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4770:3: lv_upperBound_4_0= ruleINTVALUE
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayRangeAccess().getUpperBoundINTVALUEParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange10901);
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

            otherlv_5=(Token)match(input,144,FOLLOW_144_in_ruleArrayRange10915); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4798:1: entryRuleSignedConstant returns [EObject current=null] : iv_ruleSignedConstant= ruleSignedConstant EOF ;
    public final EObject entryRuleSignedConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedConstant = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4799:2: (iv_ruleSignedConstant= ruleSignedConstant EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4800:2: iv_ruleSignedConstant= ruleSignedConstant EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSignedConstantRule()); 
            }
            pushFollow(FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant10951);
            iv_ruleSignedConstant=ruleSignedConstant();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSignedConstant; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSignedConstant10961); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4807:1: ruleSignedConstant returns [EObject current=null] : ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) ;
    public final EObject ruleSignedConstant() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_0_0 = null;

        EObject lv_ownedPropertyExpression_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4810:28: ( ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4811:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4811:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4811:2: ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4811:2: ( (lv_op_0_0= rulePlusMinus ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4812:1: (lv_op_0_0= rulePlusMinus )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4812:1: (lv_op_0_0= rulePlusMinus )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4813:3: lv_op_0_0= rulePlusMinus
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOpPlusMinusParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePlusMinus_in_ruleSignedConstant11007);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4829:2: ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4830:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4830:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4831:3: lv_ownedPropertyExpression_1_0= ruleConstantValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOwnedPropertyExpressionConstantValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleConstantValue_in_ruleSignedConstant11028);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4859:1: entryRuleNumericRangeTerm returns [EObject current=null] : iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF ;
    public final EObject entryRuleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericRangeTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4860:2: (iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4861:2: iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumericRangeTermRule()); 
            }
            pushFollow(FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm11068);
            iv_ruleNumericRangeTerm=ruleNumericRangeTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumericRangeTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumericRangeTerm11078); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4868:1: ruleNumericRangeTerm returns [EObject current=null] : ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) ;
    public final EObject ruleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_minimum_0_0 = null;

        EObject lv_maximum_2_0 = null;

        EObject lv_delta_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4871:28: ( ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4872:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4872:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4872:2: ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4872:2: ( (lv_minimum_0_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4873:1: (lv_minimum_0_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4873:1: (lv_minimum_0_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4874:3: lv_minimum_0_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMinimumNumAltParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11124);
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

            otherlv_1=(Token)match(input,147,FOLLOW_147_in_ruleNumericRangeTerm11136); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNumericRangeTermAccess().getFullStopFullStopKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4894:1: ( (lv_maximum_2_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4895:1: (lv_maximum_2_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4895:1: (lv_maximum_2_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4896:3: lv_maximum_2_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMaximumNumAltParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11157);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4912:2: (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==148) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4912:4: otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) )
                    {
                    otherlv_3=(Token)match(input,148,FOLLOW_148_in_ruleNumericRangeTerm11170); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getNumericRangeTermAccess().getDeltaKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4916:1: ( (lv_delta_4_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4917:1: (lv_delta_4_0= ruleNumAlt )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4917:1: (lv_delta_4_0= ruleNumAlt )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4918:3: lv_delta_4_0= ruleNumAlt
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getDeltaNumAltParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11191);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4942:1: entryRuleNumAlt returns [EObject current=null] : iv_ruleNumAlt= ruleNumAlt EOF ;
    public final EObject entryRuleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumAlt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4943:2: (iv_ruleNumAlt= ruleNumAlt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4944:2: iv_ruleNumAlt= ruleNumAlt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumAltRule()); 
            }
            pushFollow(FOLLOW_ruleNumAlt_in_entryRuleNumAlt11229);
            iv_ruleNumAlt=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumAlt; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumAlt11239); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4951:1: ruleNumAlt returns [EObject current=null] : (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) ;
    public final EObject ruleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject this_RealTerm_0 = null;

        EObject this_IntegerTerm_1 = null;

        EObject this_SignedConstant_2 = null;

        EObject this_ConstantValue_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4954:28: ( (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4955:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4955:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            int alt64=4;
            switch ( input.LA(1) ) {
            case RULE_REAL_LIT:
                {
                alt64=1;
                }
                break;
            case RULE_INTEGER_LIT:
                {
                alt64=2;
                }
                break;
            case 70:
            case 71:
                {
                alt64=3;
                }
                break;
            case RULE_ID:
                {
                alt64=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4956:5: this_RealTerm_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getRealTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleNumAlt11286);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4966:5: this_IntegerTerm_1= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getIntegerTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleNumAlt11313);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4976:5: this_SignedConstant_2= ruleSignedConstant
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getSignedConstantParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSignedConstant_in_ruleNumAlt11340);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4986:5: this_ConstantValue_3= ruleConstantValue
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getConstantValueParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstantValue_in_ruleNumAlt11367);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5002:1: entryRuleINTVALUE returns [String current=null] : iv_ruleINTVALUE= ruleINTVALUE EOF ;
    public final String entryRuleINTVALUE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleINTVALUE = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5003:2: (iv_ruleINTVALUE= ruleINTVALUE EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5004:2: iv_ruleINTVALUE= ruleINTVALUE EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getINTVALUERule()); 
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE11403);
            iv_ruleINTVALUE=ruleINTVALUE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleINTVALUE.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleINTVALUE11414); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5011:1: ruleINTVALUE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleINTVALUE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5014:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5015:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE11453); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5030:1: entryRuleQCLREF returns [String current=null] : iv_ruleQCLREF= ruleQCLREF EOF ;
    public final String entryRuleQCLREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCLREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5031:2: (iv_ruleQCLREF= ruleQCLREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5032:2: iv_ruleQCLREF= ruleQCLREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCLREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCLREF_in_entryRuleQCLREF11498);
            iv_ruleQCLREF=ruleQCLREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCLREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCLREF11509); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5039:1: ruleQCLREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQCLREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5042:28: ( (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5043:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5043:1: (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5043:6: this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF11549); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQCLREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            kw=(Token)match(input,149,FOLLOW_149_in_ruleQCLREF11567); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current.merge(kw);
                      newLeafNode(kw, grammarAccess.getQCLREFAccess().getColonColonKeyword_1()); 
                  
            }
            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCLREF11582); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5071:1: entryRuleQPREF returns [String current=null] : iv_ruleQPREF= ruleQPREF EOF ;
    public final String entryRuleQPREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQPREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5072:2: (iv_ruleQPREF= ruleQPREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5073:2: iv_ruleQPREF= ruleQPREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQPREFRule()); 
            }
            pushFollow(FOLLOW_ruleQPREF_in_entryRuleQPREF11628);
            iv_ruleQPREF=ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQPREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQPREF11639); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5080:1: ruleQPREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQPREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5083:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5084:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5084:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5084:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )?
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF11679); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQPREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5091:1: (kw= '::' this_ID_2= RULE_ID )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==149) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5092:2: kw= '::' this_ID_2= RULE_ID
                    {
                    kw=(Token)match(input,149,FOLLOW_149_in_ruleQPREF11698); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQPREFAccess().getColonColonKeyword_1_0()); 
                          
                    }
                    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF11713); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5112:1: entryRuleQCREF returns [String current=null] : iv_ruleQCREF= ruleQCREF EOF ;
    public final String entryRuleQCREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5113:2: (iv_ruleQCREF= ruleQCREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5114:2: iv_ruleQCREF= ruleQCREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCREF_in_entryRuleQCREF11761);
            iv_ruleQCREF=ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCREF11772); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5121:1: ruleQCREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQCREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5124:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5125:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5125:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5125:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5125:2: (this_ID_0= RULE_ID kw= '::' )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_ID) ) {
                    int LA66_1 = input.LA(2);

                    if ( (LA66_1==149) ) {
                        alt66=1;
                    }


                }


                switch (alt66) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5125:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF11813); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_0);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_0, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_0_0()); 
            	          
            	    }
            	    kw=(Token)match(input,149,FOLLOW_149_in_ruleQCREF11831); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQCREFAccess().getColonColonKeyword_0_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF11848); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_2, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_1()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5145:1: (kw= '.' this_ID_4= RULE_ID )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==77) ) {
                int LA67_1 = input.LA(2);

                if ( (LA67_1==RULE_ID) ) {
                    alt67=1;
                }
            }
            switch (alt67) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5146:2: kw= '.' this_ID_4= RULE_ID
                    {
                    kw=(Token)match(input,77,FOLLOW_77_in_ruleQCREF11867); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQCREFAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF11882); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5166:1: entryRuleSTAR returns [String current=null] : iv_ruleSTAR= ruleSTAR EOF ;
    public final String entryRuleSTAR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSTAR = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5167:2: (iv_ruleSTAR= ruleSTAR EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5168:2: iv_ruleSTAR= ruleSTAR EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSTARRule()); 
            }
            pushFollow(FOLLOW_ruleSTAR_in_entryRuleSTAR11930);
            iv_ruleSTAR=ruleSTAR();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSTAR.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSTAR11941); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5175:1: ruleSTAR returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken ruleSTAR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5178:28: (kw= '*' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5180:2: kw= '*'
            {
            kw=(Token)match(input,72,FOLLOW_72_in_ruleSTAR11978); if (state.failed) return current;
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:3: ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:4: ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:4: ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:5: ( ( RULE_ID ) ) ':' ( ( ruleType ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:5: ( ( RULE_ID ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:789:1: ( RULE_ID )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:789:1: ( RULE_ID )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:790:1: RULE_ID
        {
        match(input,RULE_ID,FOLLOW_RULE_ID_in_synpred1_InternalResolute1882); if (state.failed) return ;

        }


        }

        match(input,55,FOLLOW_55_in_synpred1_InternalResolute1889); if (state.failed) return ;
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:794:1: ( ( ruleType ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:795:1: ( ruleType )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:795:1: ( ruleType )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:796:1: ruleType
        {
        pushFollow(FOLLOW_ruleType_in_synpred1_InternalResolute1896);
        ruleType();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred1_InternalResolute

    // $ANTLR start synpred2_InternalResolute
    public final void synpred2_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:3: ( ( () ( ( '=>' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:4: ( () ( ( '=>' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:4: ( () ( ( '=>' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:5: () ( ( '=>' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1324:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1325:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1325:2: ( ( '=>' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1326:1: ( '=>' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1326:1: ( '=>' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1327:2: '=>'
        {
        match(input,62,FOLLOW_62_in_synpred2_InternalResolute2972); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalResolute

    // $ANTLR start synpred3_InternalResolute
    public final void synpred3_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:3: ( ( () ( ( 'or' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:4: ( () ( ( 'or' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:4: ( () ( ( 'or' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:5: () ( ( 'or' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1400:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1401:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1401:2: ( ( 'or' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:1: ( 'or' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:1: ( 'or' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1403:2: 'or'
        {
        match(input,63,FOLLOW_63_in_synpred3_InternalResolute3154); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalResolute

    // $ANTLR start synpred4_InternalResolute
    public final void synpred4_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:3: ( ( () ( ( 'and' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:4: ( () ( ( 'and' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:4: ( () ( ( 'and' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:5: () ( ( 'and' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1476:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1477:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1477:2: ( ( 'and' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:1: ( 'and' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:1: ( 'and' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1479:2: 'and'
        {
        match(input,64,FOLLOW_64_in_synpred4_InternalResolute3336); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalResolute

    // $ANTLR start synpred5_InternalResolute
    public final void synpred5_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:3: ( ( () ( ( ruleRelationalOp ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:4: ( () ( ( ruleRelationalOp ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:4: ( () ( ( ruleRelationalOp ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:5: () ( ( ruleRelationalOp ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1621:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1622:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1622:2: ( ( ruleRelationalOp ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1623:1: ( ruleRelationalOp )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1623:1: ( ruleRelationalOp )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1624:1: ruleRelationalOp
        {
        pushFollow(FOLLOW_ruleRelationalOp_in_synpred5_InternalResolute3721);
        ruleRelationalOp();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred5_InternalResolute

    // $ANTLR start synpred6_InternalResolute
    public final void synpred6_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:4: ( () ( ( ( '+' | '-' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:4: ( () ( ( ( '+' | '-' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:5: () ( ( ( '+' | '-' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1698:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1699:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1699:2: ( ( ( '+' | '-' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1700:1: ( ( '+' | '-' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1700:1: ( ( '+' | '-' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1701:1: ( '+' | '-' )
        {
        if ( (input.LA(1)>=70 && input.LA(1)<=71) ) {
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:3: ( ( () ( ( ( '*' | '/' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:4: ( () ( ( ( '*' | '/' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:4: ( () ( ( ( '*' | '/' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:5: () ( ( ( '*' | '/' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1796:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1796:2: ( ( ( '*' | '/' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1797:1: ( ( '*' | '/' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1797:1: ( ( '*' | '/' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1798:1: ( '*' | '/' )
        {
        if ( (input.LA(1)>=72 && input.LA(1)<=73) ) {
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:3: ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:4: ( () ( ( ( 'union' | 'intersect' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:4: ( () ( ( ( 'union' | 'intersect' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:5: () ( ( ( 'union' | 'intersect' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1892:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1893:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1893:2: ( ( ( 'union' | 'intersect' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1894:1: ( ( 'union' | 'intersect' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1894:1: ( ( 'union' | 'intersect' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1895:1: ( 'union' | 'intersect' )
        {
        if ( (input.LA(1)>=74 && input.LA(1)<=75) ) {
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
    // $ANTLR end synpred8_InternalResolute

    // $ANTLR start synpred9_InternalResolute
    public final void synpred9_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:3: ( ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:4: ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:4: ( 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )* )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3445:6: 'modes' ':' ( ( ruleNestedDotID ) ) ( ',' ( ( ruleNestedDotID ) ) )*
        {
        match(input,134,FOLLOW_134_in_synpred9_InternalResolute7748); if (state.failed) return ;
        match(input,55,FOLLOW_55_in_synpred9_InternalResolute7752); if (state.failed) return ;
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3447:1: ( ( ruleNestedDotID ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3448:1: ( ruleNestedDotID )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3448:1: ( ruleNestedDotID )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3449:1: ruleNestedDotID
        {
        pushFollow(FOLLOW_ruleNestedDotID_in_synpred9_InternalResolute7759);
        ruleNestedDotID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3451:2: ( ',' ( ( ruleNestedDotID ) ) )*
        loop68:
        do {
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==58) ) {
                alt68=1;
            }


            switch (alt68) {
        	case 1 :
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3451:4: ',' ( ( ruleNestedDotID ) )
        	    {
        	    match(input,58,FOLLOW_58_in_synpred9_InternalResolute7766); if (state.failed) return ;
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3452:1: ( ( ruleNestedDotID ) )
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3453:1: ( ruleNestedDotID )
        	    {
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3453:1: ( ruleNestedDotID )
        	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3454:1: ruleNestedDotID
        	    {
        	    pushFollow(FOLLOW_ruleNestedDotID_in_synpred9_InternalResolute7773);
        	    ruleNestedDotID();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }


        	    }
        	    break;

        	default :
        	    break loop68;
            }
        } while (true);


        }


        }
    }
    // $ANTLR end synpred9_InternalResolute

    // Delegated rules

    public final boolean synpred9_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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


    protected DFA16 dfa16 = new DFA16(this);
    protected DFA36 dfa36 = new DFA36(this);
    protected DFA53 dfa53 = new DFA53(this);
    static final String DFA16_eotS =
        "\12\uffff";
    static final String DFA16_eofS =
        "\1\10\11\uffff";
    static final String DFA16_minS =
        "\1\4\7\0\2\uffff";
    static final String DFA16_maxS =
        "\1\u0086\7\0\2\uffff";
    static final String DFA16_acceptS =
        "\10\uffff\1\2\1\1";
    static final String DFA16_specialS =
        "\1\uffff\1\1\1\0\1\5\1\4\1\3\1\2\1\6\2\uffff}>";
    static final String[] DFA16_transitionS = {
            "\1\10\14\uffff\1\10\46\uffff\1\5\1\uffff\2\10\1\2\1\uffff\3"+
            "\10\1\1\1\3\1\4\1\6\1\7\6\10\5\uffff\2\10\2\uffff\1\10\2\uffff"+
            "\1\10\54\uffff\2\10",
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

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1621:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_2 = input.LA(1);

                         
                        int index16_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_1 = input.LA(1);

                         
                        int index16_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_6 = input.LA(1);

                         
                        int index16_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA16_5 = input.LA(1);

                         
                        int index16_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_5);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA16_4 = input.LA(1);

                         
                        int index16_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA16_3 = input.LA(1);

                         
                        int index16_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_3);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA16_7 = input.LA(1);

                         
                        int index16_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_InternalResolute()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA36_eotS =
        "\20\uffff";
    static final String DFA36_eofS =
        "\1\uffff\1\16\16\uffff";
    static final String DFA36_minS =
        "\2\4\16\uffff";
    static final String DFA36_maxS =
        "\1\u008d\1\u0095\16\uffff";
    static final String DFA36_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\14\1\15\1\16"+
        "\1\1\1\13";
    static final String DFA36_specialS =
        "\20\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\1\1\7\1\5\1\4\10\uffff\1\13\17\uffff\1\12\30\uffff\1\15"+
            "\24\uffff\1\2\1\3\1\10\2\uffff\2\11\2\uffff\1\14\1\uffff\54"+
            "\12\7\uffff\2\6",
            "\1\16\14\uffff\1\16\46\uffff\1\16\1\17\3\16\1\uffff\16\16"+
            "\1\uffff\1\16\3\uffff\2\16\2\uffff\1\16\2\uffff\1\16\54\uffff"+
            "\2\16\16\uffff\1\16",
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
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "2065:1: ( ( () ( ( ruleQCREF ) ) (otherlv_2= '.' ( (lv_subelements_3_0= ruleElementSet ) ) )? ) | ( () otherlv_5= 'this' (otherlv_6= '.' ( (lv_sub_7_0= ruleNestedDotID ) ) )? ) | ( () otherlv_9= 'fail' ( (lv_val_10_0= ruleStringTerm ) )? ) | ( () ( (lv_val_12_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_14_0= ruleRealTerm ) ) ) | ( () ( (lv_val_16_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_18_0= ruleStringTerm ) ) ) | ( () otherlv_20= 'if' ( (lv_cond_21_0= ruleExpr ) ) otherlv_22= 'then' ( (lv_then_23_0= ruleExpr ) ) otherlv_24= 'else' ( (lv_else_25_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_27_1= 'forall' | lv_quant_27_2= 'exists' ) ) ) (otherlv_28= '(' ( (lv_args_29_0= ruleArg ) ) otherlv_30= ')' )+ otherlv_31= '.' ( (lv_expr_32_0= ruleExpr ) ) ) | ( () ( (lv_fn_34_0= ruleBuiltInFunc ) ) otherlv_35= '(' ( ( (lv_args_36_0= ruleExpr ) ) (otherlv_37= ',' ( (lv_args_38_0= ruleExpr ) ) )* )? otherlv_39= ')' ) | ( () ( (otherlv_41= RULE_ID ) ) otherlv_42= '(' ( ( (lv_args_43_0= ruleExpr ) ) (otherlv_44= ',' ( (lv_args_45_0= ruleExpr ) ) )* )? otherlv_46= ')' ) | ( () otherlv_48= '{' ( (lv_map_49_0= ruleExpr ) ) otherlv_50= 'for' (otherlv_51= '(' ( (lv_args_52_0= ruleArg ) ) otherlv_53= ')' )+ (otherlv_54= '|' ( (lv_filter_55_0= ruleExpr ) ) )? otherlv_56= '}' ) | ( () otherlv_58= 'let' ( (lv_binding_59_0= ruleLetBinding ) ) otherlv_60= ';' ( (lv_expr_61_0= ruleExpr ) ) ) | (otherlv_62= '(' this_Expr_63= ruleExpr otherlv_64= ')' ) )";
        }
    }
    static final String DFA53_eotS =
        "\23\uffff";
    static final String DFA53_eofS =
        "\6\uffff\1\15\1\17\1\uffff\1\21\2\uffff\1\15\1\uffff\1\17\3\uffff"+
        "\1\21";
    static final String DFA53_minS =
        "\1\4\5\uffff\2\4\1\uffff\1\72\2\uffff\1\72\1\uffff\1\72\1\uffff"+
        "\1\4\1\uffff\1\72";
    static final String DFA53_maxS =
        "\1\u0092\5\uffff\2\u0093\1\uffff\1\u0095\2\uffff\1\u0093\1\uffff"+
        "\1\u0093\1\uffff\1\4\1\uffff\1\u0093";
    static final String DFA53_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\6\1\uffff\1\11\1\12\1\uffff"+
        "\1\7\1\uffff\1\10\1\uffff\1\13\1\uffff";
    static final String DFA53_specialS =
        "\23\uffff}>";
    static final String[] DFA53_transitionS = {
            "\1\11\1\5\1\6\1\7\61\uffff\1\12\14\uffff\2\10\104\uffff\2\13"+
            "\1\2\1\1\1\uffff\1\4\1\3",
            "",
            "",
            "",
            "",
            "",
            "\1\14\65\uffff\2\15\11\uffff\1\15\22\uffff\1\15\60\uffff\1"+
            "\15\11\uffff\1\10",
            "\1\16\65\uffff\2\17\11\uffff\1\17\22\uffff\1\17\60\uffff\1"+
            "\17\11\uffff\1\10",
            "",
            "\2\21\11\uffff\1\21\22\uffff\1\21\60\uffff\1\21\11\uffff\1"+
            "\10\1\uffff\1\20",
            "",
            "",
            "\2\15\11\uffff\1\15\22\uffff\1\15\60\uffff\1\15\11\uffff\1"+
            "\10",
            "",
            "\2\17\11\uffff\1\17\22\uffff\1\17\60\uffff\1\17\11\uffff\1"+
            "\10",
            "",
            "\1\22",
            "",
            "\2\21\11\uffff\1\21\22\uffff\1\21\60\uffff\1\21\11\uffff\1"+
            "\10"
    };

    static final short[] DFA53_eot = DFA.unpackEncodedString(DFA53_eotS);
    static final short[] DFA53_eof = DFA.unpackEncodedString(DFA53_eofS);
    static final char[] DFA53_min = DFA.unpackEncodedStringToUnsignedChars(DFA53_minS);
    static final char[] DFA53_max = DFA.unpackEncodedStringToUnsignedChars(DFA53_maxS);
    static final short[] DFA53_accept = DFA.unpackEncodedString(DFA53_acceptS);
    static final short[] DFA53_special = DFA.unpackEncodedString(DFA53_specialS);
    static final short[][] DFA53_transition;

    static {
        int numStates = DFA53_transitionS.length;
        DFA53_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA53_transition[i] = DFA.unpackEncodedString(DFA53_transitionS[i]);
        }
    }

    class DFA53 extends DFA {

        public DFA53(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 53;
            this.eot = DFA53_eot;
            this.eof = DFA53_eof;
            this.min = DFA53_min;
            this.max = DFA53_max;
            this.accept = DFA53_accept;
            this.special = DFA53_special;
            this.transition = DFA53_transition;
        }
        public String getDescription() {
            return "3918:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )";
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
    public static final BitSet FOLLOW_16_in_ruleType451 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_ruleType472 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleType484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltinType_in_ruleType513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementSet_in_entryRuleElementSet548 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementSet558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleElementSet602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleElementSet631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleElementSet660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleElementSet689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleElementSet718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleElementSet747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleElementSet776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleElementSet805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleElementSet834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleElementSet863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleElementSet892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleElementSet921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleElementSet950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleElementSet979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleElementSet1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleElementSet1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleElementSet1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType1117 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltinType1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleBuiltinType1171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleBuiltinType1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleBuiltinType1229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleBuiltinType1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleBuiltinType1287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleBuiltinType1316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleBuiltinType1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleBuiltinType1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleBuiltinType1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleBuiltinType1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleBuiltinType1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleBuiltinType1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleBuiltinType1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleBuiltinType1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleBuiltinType1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleBuiltinType1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleBuiltinType1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleBuiltinType1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleBuiltinType1693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleBuiltinType1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleBuiltinType1751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleBuiltinType1780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArg_in_entryRuleArg1831 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArg1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg1913 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleArg1930 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_ruleArg1951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg1986 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleArg2003 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleArg2024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition2061 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantDefinition2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstantDefinition2113 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleConstantDefinition2130 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_ruleConstantDefinition2151 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ruleConstantDefinition2163 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleConstantDefinition2184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition2220 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionDefinition2230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFunctionDefinition2272 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleFunctionDefinition2289 = new BitSet(new long[]{0x0800000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition2311 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_58_in_ruleFunctionDefinition2324 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition2345 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_ruleFunctionDefinition2361 = new BitSet(new long[]{0x1080000000000000L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition2382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClaimString_in_entryRuleClaimString2418 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClaimString2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleClaimString2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleClaimString2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody2559 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinitionBody2569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleDefinitionBody2616 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_ruleDefinitionBody2637 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ruleDefinitionBody2649 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleDefinitionBody2699 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_ruleDefinitionBody2712 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleClaimString_in_ruleDefinitionBody2733 = new BitSet(new long[]{0x2000000000000030L});
    public static final BitSet FOLLOW_61_in_ruleDefinitionBody2746 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr2805 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr2815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleExpr2861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2895 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpr2905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_ruleImpliesExpr2952 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleImpliesExpr3003 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr3039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_entryRuleOrExpr3077 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpr3087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr3134 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleOrExpr3185 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr3221 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_entryRuleAndExpr3259 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpr3269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr3316 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleAndExpr3367 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr3403 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp3442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalOp3453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleRelationalOp3491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleRelationalOp3510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleRelationalOp3529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleRelationalOp3548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleRelationalOp3567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleRelationalOp3586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleRelationalOp3605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr3645 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpr3655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr3702 = new BitSet(new long[]{0x1100000000000002L,0x000000000000003EL});
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3751 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr3774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_entryRulePlusExpr3812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusExpr3822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3869 = new BitSet(new long[]{0x0000000000000002L,0x00000000000000C0L});
    public static final BitSet FOLLOW_70_in_rulePlusExpr3941 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_71_in_rulePlusExpr3970 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr4009 = new BitSet(new long[]{0x0000000000000002L,0x00000000000000C0L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr4047 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTimesExpr4057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr4104 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000300L});
    public static final BitSet FOLLOW_72_in_ruleTimesExpr4176 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_73_in_ruleTimesExpr4205 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr4244 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000300L});
    public static final BitSet FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr4282 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSetOpExpr4292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr4339 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000C00L});
    public static final BitSet FOLLOW_74_in_ruleSetOpExpr4411 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_75_in_ruleSetOpExpr4440 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr4479 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000C00L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr4517 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrefixExpr4527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_rulePrefixExpr4582 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_76_in_rulePrefixExpr4611 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr4648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_rulePrefixExpr4677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr4712 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomicExpr4722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleAtomicExpr4780 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleAtomicExpr4793 = new BitSet(new long[]{0x00000007FFFC0000L});
    public static final BitSet FOLLOW_ruleElementSet_in_ruleAtomicExpr4814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleAtomicExpr4845 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleAtomicExpr4858 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleAtomicExpr4879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleAtomicExpr4910 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleAtomicExpr5008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr5046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr5084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleAtomicExpr5113 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_ruleAtomicExpr5146 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_ruleAtomicExpr5179 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleAtomicExpr5237 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_84_in_ruleAtomicExpr5266 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleAtomicExpr5295 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr5316 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAtomicExpr5328 = new BitSet(new long[]{0x0200000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleAtomicExpr5342 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr5401 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleAtomicExpr5413 = new BitSet(new long[]{0x0A000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5435 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_58_in_ruleAtomicExpr5448 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5469 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAtomicExpr5485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAtomicExpr5522 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleAtomicExpr5534 = new BitSet(new long[]{0x0A000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5556 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_58_in_ruleAtomicExpr5569 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5590 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAtomicExpr5606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr5635 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_ruleAtomicExpr5668 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleAtomicExpr5681 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr5702 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAtomicExpr5714 = new BitSet(new long[]{0x0200000000020000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_ruleAtomicExpr5729 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5750 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleAtomicExpr5764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_ruleAtomicExpr5793 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleLetBinding_in_ruleAtomicExpr5814 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleAtomicExpr5826 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleAtomicExpr5867 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5889 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleAtomicExpr5900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetBinding_in_entryRuleLetBinding5937 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetBinding5947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLetBinding5989 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleLetBinding6006 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_ruleLetBinding6027 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ruleLetBinding6039 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleLetBinding6060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc6097 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltInFunc6108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ruleBuiltInFunc6146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleBuiltInFunc6165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleBuiltInFunc6184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleBuiltInFunc6203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleBuiltInFunc6222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_ruleBuiltInFunc6241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleBuiltInFunc6260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleBuiltInFunc6279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleBuiltInFunc6298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleBuiltInFunc6317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleBuiltInFunc6336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_ruleBuiltInFunc6355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_ruleBuiltInFunc6374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_ruleBuiltInFunc6393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_ruleBuiltInFunc6412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_ruleBuiltInFunc6431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_ruleBuiltInFunc6450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_ruleBuiltInFunc6469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_ruleBuiltInFunc6488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_ruleBuiltInFunc6507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_ruleBuiltInFunc6526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_ruleBuiltInFunc6545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_ruleBuiltInFunc6564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleBuiltInFunc6583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_ruleBuiltInFunc6602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_ruleBuiltInFunc6621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_ruleBuiltInFunc6640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_ruleBuiltInFunc6659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_ruleBuiltInFunc6678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_ruleBuiltInFunc6697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_ruleBuiltInFunc6716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_ruleBuiltInFunc6735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_ruleBuiltInFunc6754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_ruleBuiltInFunc6773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_ruleBuiltInFunc6792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_ruleBuiltInFunc6811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_ruleBuiltInFunc6830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_ruleBuiltInFunc6849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_ruleBuiltInFunc6868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_ruleBuiltInFunc6887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_ruleBuiltInFunc6906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_ruleBuiltInFunc6925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_ruleBuiltInFunc6944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_ruleBuiltInFunc6963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_132_in_ruleBuiltInFunc6982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_entryRuleRealTerm7022 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealTerm7032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_ruleRealTerm7078 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRealTerm7098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal7136 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedReal7147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal7186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm7230 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerTerm7240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm7286 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIntegerTerm7306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt7344 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedInt7355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt7394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause7438 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteSubclause7448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_ruleResoluteSubclause7503 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID7540 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNestedDotID7550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNestedDotID7595 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleNestedDotID7608 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleNestedDotID7629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_entryRuleProveStatement7667 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProveStatement7677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_133_in_ruleProveStatement7714 = new BitSet(new long[]{0x02000001000100F0L,0xFFFFFFFFFE99D080L,0x000000000000301FL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleProveStatement7735 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_ruleProveStatement7787 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ruleProveStatement7799 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleProveStatement7820 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_58_in_ruleProveStatement7833 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleProveStatement7854 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation7897 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainedPropertyAssociation7907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation7955 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_62_in_ruleContainedPropertyAssociation7968 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_135_in_ruleContainedPropertyAssociation7992 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_136_in_ruleContainedPropertyAssociation8024 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8060 = new BitSet(new long[]{0x0400000000000000L,0x0000000001000020L,0x0000000000000200L});
    public static final BitSet FOLLOW_58_in_ruleContainedPropertyAssociation8073 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8094 = new BitSet(new long[]{0x0400000000000000L,0x0000000001000020L,0x0000000000000200L});
    public static final BitSet FOLLOW_137_in_ruleContainedPropertyAssociation8110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_138_in_ruleContainedPropertyAssociation8122 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8143 = new BitSet(new long[]{0x0400000000000000L,0x0000000001000020L});
    public static final BitSet FOLLOW_58_in_ruleContainedPropertyAssociation8156 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8177 = new BitSet(new long[]{0x0400000000000000L,0x0000000001000020L});
    public static final BitSet FOLLOW_69_in_ruleContainedPropertyAssociation8194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_139_in_ruleContainedPropertyAssociation8206 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleContainedPropertyAssociation8218 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation8241 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleContainedPropertyAssociation8253 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleContainedPropertyAssociation8267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath8307 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPath8317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8372 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleContainmentPath8385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8406 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue8446 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue8456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue8502 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleOptionalModalPropertyValue8515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_134_in_ruleOptionalModalPropertyValue8527 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleOptionalModalPropertyValue8539 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8559 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_58_in_ruleOptionalModalPropertyValue8572 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8592 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_ruleOptionalModalPropertyValue8606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue8644 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue8654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyValue8699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression8734 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression8744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_rulePropertyExpression8791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_rulePropertyExpression8818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression8845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_rulePropertyExpression8872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_rulePropertyExpression8899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression8926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_rulePropertyExpression8953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_rulePropertyExpression8980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_rulePropertyExpression9007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression9034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression9061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm9096 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm9106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm9153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral9188 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral9198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_140_in_ruleBooleanLiteral9251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_141_in_ruleBooleanLiteral9282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_entryRuleConstantValue9319 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantValue9329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleConstantValue9376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm9411 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReferenceTerm9421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_142_in_ruleReferenceTerm9458 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleReferenceTerm9470 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm9492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleReferenceTerm9504 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9527 = new BitSet(new long[]{0x0800000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleReferenceTerm9540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9561 = new BitSet(new long[]{0x0800000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_59_in_ruleReferenceTerm9575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm9611 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRecordTerm9621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_143_in_ruleRecordTerm9658 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm9679 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_144_in_ruleRecordTerm9692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm9730 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComputedTerm9740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_145_in_ruleComputedTerm9777 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleComputedTerm9789 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleComputedTerm9806 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleComputedTerm9823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm9859 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComponentClassifierTerm9869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_146_in_ruleComponentClassifierTerm9906 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleComponentClassifierTerm9918 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm9941 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleComponentClassifierTerm9953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_entryRuleListTerm9989 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListTerm9999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleListTerm10045 = new BitSet(new long[]{0x0A000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm10067 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_58_in_ruleListTerm10080 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm10101 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_ruleListTerm10117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation10153 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFieldPropertyAssociation10163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation10208 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleFieldPropertyAssociation10220 = new BitSet(new long[]{0x02000000000000F0L,0x00000000000000C0L,0x000000000006F100L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation10241 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleFieldPropertyAssociation10253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement10289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPathElement10299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleContainmentPathElement10344 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleArrayRange_in_ruleContainmentPathElement10365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement10402 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement10412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement10459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_entryRulePlusMinus10497 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusMinus10508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_rulePlusMinus10546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_rulePlusMinus10565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_entryRuleStringTerm10605 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringTerm10615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_ruleStringTerm10660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString10696 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoQuoteString10707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNoQuoteString10746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayRange_in_entryRuleArrayRange10790 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayRange10800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_143_in_ruleArrayRange10846 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange10867 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000090000L});
    public static final BitSet FOLLOW_147_in_ruleArrayRange10880 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange10901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_144_in_ruleArrayRange10915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant10951 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSignedConstant10961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_ruleSignedConstant11007 = new BitSet(new long[]{0x00000000000000D0L,0x00000000000000C0L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleSignedConstant11028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm11068 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumericRangeTerm11078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11124 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_147_in_ruleNumericRangeTerm11136 = new BitSet(new long[]{0x00000000000000D0L,0x00000000000000C0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11157 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_148_in_ruleNumericRangeTerm11170 = new BitSet(new long[]{0x00000000000000D0L,0x00000000000000C0L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_entryRuleNumAlt11229 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumAlt11239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleNumAlt11286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleNumAlt11313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_ruleNumAlt11340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleNumAlt11367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE11403 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleINTVALUE11414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE11453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCLREF_in_entryRuleQCLREF11498 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCLREF11509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF11549 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_149_in_ruleQCLREF11567 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF11582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_entryRuleQPREF11628 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQPREF11639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF11679 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_149_in_ruleQPREF11698 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF11713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_entryRuleQCREF11761 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCREF11772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF11813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_149_in_ruleQCREF11831 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF11848 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleQCREF11867 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF11882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTAR_in_entryRuleSTAR11930 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSTAR11941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleSTAR11978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_synpred1_InternalResolute1882 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_synpred1_InternalResolute1889 = new BitSet(new long[]{0x007FFFF800190000L});
    public static final BitSet FOLLOW_ruleType_in_synpred1_InternalResolute1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_synpred2_InternalResolute2972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_synpred3_InternalResolute3154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_synpred4_InternalResolute3336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred5_InternalResolute3721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred6_InternalResolute3888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred7_InternalResolute4123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred8_InternalResolute4358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_134_in_synpred9_InternalResolute7748 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_synpred9_InternalResolute7752 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_synpred9_InternalResolute7759 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_58_in_synpred9_InternalResolute7766 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_synpred9_InternalResolute7773 = new BitSet(new long[]{0x0400000000000002L});

}