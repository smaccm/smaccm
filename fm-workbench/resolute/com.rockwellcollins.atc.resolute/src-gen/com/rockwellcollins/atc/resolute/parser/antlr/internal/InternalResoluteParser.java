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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_REAL_LIT", "RULE_INTEGER_LIT", "RULE_SL_COMMENT", "RULE_DIGIT", "RULE_EXPONENT", "RULE_INT_EXPONENT", "RULE_BASED_INTEGER", "RULE_EXTENDED_DIGIT", "RULE_WS", "'{'", "'}'", "'<'", "'>'", "'int'", "'real'", "'string'", "'bool'", "'range'", "'aadl'", "'component'", "'abstract'", "'bus'", "'data'", "'device'", "'memory'", "'processor'", "'process'", "'subprogram_group'", "'subprogram'", "'system'", "'thread_group'", "'thread'", "'virtual_bus'", "'virtual_processor'", "'connection'", "'property'", "'feature'", "'port'", "'data_port'", "'event_port'", "'event_data_port'", "'feature_group'", "'access'", "'bus_access'", "'provides_bus_access'", "'requires_bus_access'", "'data_access'", "'provides_data_access'", "'requires_data_access'", "'subprogram_access'", "'provides_subprogram_access'", "'requires_subprogram_access'", "'subprogram_group_access'", "'provides_subprogram_group_access'", "'requires_subprogram_group_access'", "'flow_specification'", "'end_to_end_flow'", "':'", "'='", "'('", "','", "')'", "'<='", "'**'", "'%'", "'=>'", "'or'", "'and'", "'andthen'", "'instanceof'", "'>='", "'<>'", "'+'", "'-'", "'*'", "'/'", "'not'", "'this'", "'.'", "'fail'", "'if'", "'then'", "'else'", "'forall'", "'exists'", "'for'", "'|'", "'let'", "';'", "'has_property'", "'property_member'", "'has_parent'", "'parent'", "'name'", "'type'", "'has_type'", "'is_in_array'", "'has_prototypes'", "'has_modes'", "'is_processor'", "'is_virtual_processor'", "'is_system'", "'is_bus'", "'is_virtual_bus'", "'is_device'", "'is_memory'", "'is_thread'", "'is_process'", "'is_data'", "'is_subprogram'", "'is_of_type'", "'is_bound_to'", "'has_member'", "'features'", "'connections'", "'enumerated_values'", "'subcomponents'", "'source'", "'destination'", "'direction'", "'is_event_port'", "'is_abstract_feature'", "'is_data_port'", "'is_port'", "'is_data_access'", "'lower_bound'", "'upper_bound'", "'member'", "'sum'", "'union'", "'length'", "'size'", "'intersect'", "'instance'", "'instances'", "'debug'", "'analysis'", "'receive_error'", "'contain_error'", "'propagate_error'", "'error_state_reachable'", "'flow_source'", "'flow_destination'", "'flow_elements'", "'flow_specifications'", "'end_to_end_flows'", "'prove'", "'+=>'", "'constant'", "'applies'", "'to'", "'in'", "'binding'", "'modes'", "'true'", "'false'", "'reference'", "'['", "']'", "'compute'", "'classifier'", "'..'", "'delta'", "'::'"
    };
    public static final int T__144=144;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__137=137;
    public static final int T__52=52;
    public static final int T__136=136;
    public static final int T__53=53;
    public static final int T__139=139;
    public static final int T__54=54;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__60=60;
    public static final int T__135=135;
    public static final int T__61=61;
    public static final int T__134=134;
    public static final int RULE_ID=4;
    public static final int T__131=131;
    public static final int T__130=130;
    public static final int RULE_DIGIT=9;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__129=129;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int T__128=128;
    public static final int T__65=65;
    public static final int T__127=127;
    public static final int T__166=166;
    public static final int T__165=165;
    public static final int T__168=168;
    public static final int T__167=167;
    public static final int T__162=162;
    public static final int T__161=161;
    public static final int T__164=164;
    public static final int T__163=163;
    public static final int RULE_REAL_LIT=6;
    public static final int T__160=160;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__159=159;
    public static final int T__30=30;
    public static final int T__158=158;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__155=155;
    public static final int T__154=154;
    public static final int T__157=157;
    public static final int T__156=156;
    public static final int T__151=151;
    public static final int T__150=150;
    public static final int T__153=153;
    public static final int T__152=152;
    public static final int RULE_EXPONENT=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int RULE_EXTENDED_DIGIT=13;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__148=148;
    public static final int T__41=41;
    public static final int T__147=147;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__149=149;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int RULE_INT_EXPONENT=11;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int RULE_BASED_INTEGER=12;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__169=169;
    public static final int T__20=20;
    public static final int RULE_INTEGER_LIT=7;
    public static final int T__21=21;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int T__71=71;
    public static final int T__124=124;
    public static final int T__72=72;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=14;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

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

                if ( (LA2_1==63) ) {
                    alt2=1;
                }
                else if ( (LA2_1==65) ) {
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:199:1: ruleType returns [EObject current=null] : ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? ) ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_type_2_0 = null;

        EObject this_BaseType_4 = null;

        EObject lv_paramType_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:202:28: ( ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:1: ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:203:1: ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=19 && LA4_0<=62)) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
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

                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleType451); if (state.failed) return current;
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

                    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleType484); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTypeAccess().getRightCurlyBracketKeyword_0_3());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:236:6: (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:236:6: (this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:237:5: this_BaseType_4= ruleBaseType (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )?
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getBaseTypeParserRuleCall_1_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBaseType_in_ruleType514);
                    this_BaseType_4=ruleBaseType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BaseType_4; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:245:1: (otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>' )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==17) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:245:3: otherlv_5= '<' ( (lv_paramType_6_0= ruleType ) ) otherlv_7= '>'
                            {
                            otherlv_5=(Token)match(input,17,FOLLOW_17_in_ruleType526); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getTypeAccess().getLessThanSignKeyword_1_1_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:249:1: ( (lv_paramType_6_0= ruleType ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:250:1: (lv_paramType_6_0= ruleType )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:250:1: (lv_paramType_6_0= ruleType )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:251:3: lv_paramType_6_0= ruleType
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getTypeAccess().getParamTypeTypeParserRuleCall_1_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleType_in_ruleType547);
                            lv_paramType_6_0=ruleType();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getTypeRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"paramType",
                                      		lv_paramType_6_0, 
                                      		"Type");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_7=(Token)match(input,18,FOLLOW_18_in_ruleType559); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_7, grammarAccess.getTypeAccess().getGreaterThanSignKeyword_1_1_2());
                                  
                            }

                            }
                            break;

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
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleBaseType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:279:1: entryRuleBaseType returns [EObject current=null] : iv_ruleBaseType= ruleBaseType EOF ;
    public final EObject entryRuleBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBaseType = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:280:2: (iv_ruleBaseType= ruleBaseType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:281:2: iv_ruleBaseType= ruleBaseType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBaseTypeRule()); 
            }
            pushFollow(FOLLOW_ruleBaseType_in_entryRuleBaseType598);
            iv_ruleBaseType=ruleBaseType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBaseType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBaseType608); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBaseType"


    // $ANTLR start "ruleBaseType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:288:1: ruleBaseType returns [EObject current=null] : ( ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) ) ) ;
    public final EObject ruleBaseType() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_1=null;
        Token lv_type_0_2=null;
        Token lv_type_0_3=null;
        Token lv_type_0_4=null;
        Token lv_type_0_5=null;
        Token lv_type_0_6=null;
        Token lv_type_0_7=null;
        Token lv_type_0_8=null;
        Token lv_type_0_9=null;
        Token lv_type_0_10=null;
        Token lv_type_0_11=null;
        Token lv_type_0_12=null;
        Token lv_type_0_13=null;
        Token lv_type_0_14=null;
        Token lv_type_0_15=null;
        Token lv_type_0_16=null;
        Token lv_type_0_17=null;
        Token lv_type_0_18=null;
        Token lv_type_0_19=null;
        Token lv_type_0_20=null;
        Token lv_type_0_21=null;
        Token lv_type_0_22=null;
        Token lv_type_0_23=null;
        Token lv_type_0_24=null;
        Token lv_type_0_25=null;
        Token lv_type_0_26=null;
        Token lv_type_0_27=null;
        Token lv_type_0_28=null;
        Token lv_type_0_29=null;
        Token lv_type_0_30=null;
        Token lv_type_0_31=null;
        Token lv_type_0_32=null;
        Token lv_type_0_33=null;
        Token lv_type_0_34=null;
        Token lv_type_0_35=null;
        Token lv_type_0_36=null;
        Token lv_type_0_37=null;
        Token lv_type_0_38=null;
        Token lv_type_0_39=null;
        Token lv_type_0_40=null;
        Token lv_type_0_41=null;
        Token lv_type_0_42=null;
        Token lv_type_0_43=null;
        Token lv_type_0_44=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:291:28: ( ( ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:292:1: ( ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:292:1: ( ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:293:1: ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:293:1: ( (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:294:1: (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:294:1: (lv_type_0_1= 'int' | lv_type_0_2= 'real' | lv_type_0_3= 'string' | lv_type_0_4= 'bool' | lv_type_0_5= 'range' | lv_type_0_6= 'aadl' | lv_type_0_7= 'component' | lv_type_0_8= 'abstract' | lv_type_0_9= 'bus' | lv_type_0_10= 'data' | lv_type_0_11= 'device' | lv_type_0_12= 'memory' | lv_type_0_13= 'processor' | lv_type_0_14= 'process' | lv_type_0_15= 'subprogram_group' | lv_type_0_16= 'subprogram' | lv_type_0_17= 'system' | lv_type_0_18= 'thread_group' | lv_type_0_19= 'thread' | lv_type_0_20= 'virtual_bus' | lv_type_0_21= 'virtual_processor' | lv_type_0_22= 'connection' | lv_type_0_23= 'property' | lv_type_0_24= 'feature' | lv_type_0_25= 'port' | lv_type_0_26= 'data_port' | lv_type_0_27= 'event_port' | lv_type_0_28= 'event_data_port' | lv_type_0_29= 'feature_group' | lv_type_0_30= 'access' | lv_type_0_31= 'bus_access' | lv_type_0_32= 'provides_bus_access' | lv_type_0_33= 'requires_bus_access' | lv_type_0_34= 'data_access' | lv_type_0_35= 'provides_data_access' | lv_type_0_36= 'requires_data_access' | lv_type_0_37= 'subprogram_access' | lv_type_0_38= 'provides_subprogram_access' | lv_type_0_39= 'requires_subprogram_access' | lv_type_0_40= 'subprogram_group_access' | lv_type_0_41= 'provides_subprogram_group_access' | lv_type_0_42= 'requires_subprogram_group_access' | lv_type_0_43= 'flow_specification' | lv_type_0_44= 'end_to_end_flow' )
            int alt5=44;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt5=1;
                }
                break;
            case 20:
                {
                alt5=2;
                }
                break;
            case 21:
                {
                alt5=3;
                }
                break;
            case 22:
                {
                alt5=4;
                }
                break;
            case 23:
                {
                alt5=5;
                }
                break;
            case 24:
                {
                alt5=6;
                }
                break;
            case 25:
                {
                alt5=7;
                }
                break;
            case 26:
                {
                alt5=8;
                }
                break;
            case 27:
                {
                alt5=9;
                }
                break;
            case 28:
                {
                alt5=10;
                }
                break;
            case 29:
                {
                alt5=11;
                }
                break;
            case 30:
                {
                alt5=12;
                }
                break;
            case 31:
                {
                alt5=13;
                }
                break;
            case 32:
                {
                alt5=14;
                }
                break;
            case 33:
                {
                alt5=15;
                }
                break;
            case 34:
                {
                alt5=16;
                }
                break;
            case 35:
                {
                alt5=17;
                }
                break;
            case 36:
                {
                alt5=18;
                }
                break;
            case 37:
                {
                alt5=19;
                }
                break;
            case 38:
                {
                alt5=20;
                }
                break;
            case 39:
                {
                alt5=21;
                }
                break;
            case 40:
                {
                alt5=22;
                }
                break;
            case 41:
                {
                alt5=23;
                }
                break;
            case 42:
                {
                alt5=24;
                }
                break;
            case 43:
                {
                alt5=25;
                }
                break;
            case 44:
                {
                alt5=26;
                }
                break;
            case 45:
                {
                alt5=27;
                }
                break;
            case 46:
                {
                alt5=28;
                }
                break;
            case 47:
                {
                alt5=29;
                }
                break;
            case 48:
                {
                alt5=30;
                }
                break;
            case 49:
                {
                alt5=31;
                }
                break;
            case 50:
                {
                alt5=32;
                }
                break;
            case 51:
                {
                alt5=33;
                }
                break;
            case 52:
                {
                alt5=34;
                }
                break;
            case 53:
                {
                alt5=35;
                }
                break;
            case 54:
                {
                alt5=36;
                }
                break;
            case 55:
                {
                alt5=37;
                }
                break;
            case 56:
                {
                alt5=38;
                }
                break;
            case 57:
                {
                alt5=39;
                }
                break;
            case 58:
                {
                alt5=40;
                }
                break;
            case 59:
                {
                alt5=41;
                }
                break;
            case 60:
                {
                alt5=42;
                }
                break;
            case 61:
                {
                alt5=43;
                }
                break;
            case 62:
                {
                alt5=44;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:295:3: lv_type_0_1= 'int'
                    {
                    lv_type_0_1=(Token)match(input,19,FOLLOW_19_in_ruleBaseType652); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_1, grammarAccess.getBaseTypeAccess().getTypeIntKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:307:8: lv_type_0_2= 'real'
                    {
                    lv_type_0_2=(Token)match(input,20,FOLLOW_20_in_ruleBaseType681); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_2, grammarAccess.getBaseTypeAccess().getTypeRealKeyword_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:319:8: lv_type_0_3= 'string'
                    {
                    lv_type_0_3=(Token)match(input,21,FOLLOW_21_in_ruleBaseType710); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_3, grammarAccess.getBaseTypeAccess().getTypeStringKeyword_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:331:8: lv_type_0_4= 'bool'
                    {
                    lv_type_0_4=(Token)match(input,22,FOLLOW_22_in_ruleBaseType739); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_4, grammarAccess.getBaseTypeAccess().getTypeBoolKeyword_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_4, null);
                      	    
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:343:8: lv_type_0_5= 'range'
                    {
                    lv_type_0_5=(Token)match(input,23,FOLLOW_23_in_ruleBaseType768); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_5, grammarAccess.getBaseTypeAccess().getTypeRangeKeyword_0_4());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_5, null);
                      	    
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:355:8: lv_type_0_6= 'aadl'
                    {
                    lv_type_0_6=(Token)match(input,24,FOLLOW_24_in_ruleBaseType797); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_6, grammarAccess.getBaseTypeAccess().getTypeAadlKeyword_0_5());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_6, null);
                      	    
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:367:8: lv_type_0_7= 'component'
                    {
                    lv_type_0_7=(Token)match(input,25,FOLLOW_25_in_ruleBaseType826); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_7, grammarAccess.getBaseTypeAccess().getTypeComponentKeyword_0_6());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_7, null);
                      	    
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:379:8: lv_type_0_8= 'abstract'
                    {
                    lv_type_0_8=(Token)match(input,26,FOLLOW_26_in_ruleBaseType855); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_8, grammarAccess.getBaseTypeAccess().getTypeAbstractKeyword_0_7());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_8, null);
                      	    
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:391:8: lv_type_0_9= 'bus'
                    {
                    lv_type_0_9=(Token)match(input,27,FOLLOW_27_in_ruleBaseType884); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_9, grammarAccess.getBaseTypeAccess().getTypeBusKeyword_0_8());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_9, null);
                      	    
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:403:8: lv_type_0_10= 'data'
                    {
                    lv_type_0_10=(Token)match(input,28,FOLLOW_28_in_ruleBaseType913); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_10, grammarAccess.getBaseTypeAccess().getTypeDataKeyword_0_9());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_10, null);
                      	    
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:415:8: lv_type_0_11= 'device'
                    {
                    lv_type_0_11=(Token)match(input,29,FOLLOW_29_in_ruleBaseType942); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_11, grammarAccess.getBaseTypeAccess().getTypeDeviceKeyword_0_10());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_11, null);
                      	    
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:427:8: lv_type_0_12= 'memory'
                    {
                    lv_type_0_12=(Token)match(input,30,FOLLOW_30_in_ruleBaseType971); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_12, grammarAccess.getBaseTypeAccess().getTypeMemoryKeyword_0_11());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_12, null);
                      	    
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:439:8: lv_type_0_13= 'processor'
                    {
                    lv_type_0_13=(Token)match(input,31,FOLLOW_31_in_ruleBaseType1000); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_13, grammarAccess.getBaseTypeAccess().getTypeProcessorKeyword_0_12());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_13, null);
                      	    
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:451:8: lv_type_0_14= 'process'
                    {
                    lv_type_0_14=(Token)match(input,32,FOLLOW_32_in_ruleBaseType1029); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_14, grammarAccess.getBaseTypeAccess().getTypeProcessKeyword_0_13());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_14, null);
                      	    
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:463:8: lv_type_0_15= 'subprogram_group'
                    {
                    lv_type_0_15=(Token)match(input,33,FOLLOW_33_in_ruleBaseType1058); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_15, grammarAccess.getBaseTypeAccess().getTypeSubprogram_groupKeyword_0_14());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_15, null);
                      	    
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:475:8: lv_type_0_16= 'subprogram'
                    {
                    lv_type_0_16=(Token)match(input,34,FOLLOW_34_in_ruleBaseType1087); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_16, grammarAccess.getBaseTypeAccess().getTypeSubprogramKeyword_0_15());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_16, null);
                      	    
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:487:8: lv_type_0_17= 'system'
                    {
                    lv_type_0_17=(Token)match(input,35,FOLLOW_35_in_ruleBaseType1116); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_17, grammarAccess.getBaseTypeAccess().getTypeSystemKeyword_0_16());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_17, null);
                      	    
                    }

                    }
                    break;
                case 18 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:499:8: lv_type_0_18= 'thread_group'
                    {
                    lv_type_0_18=(Token)match(input,36,FOLLOW_36_in_ruleBaseType1145); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_18, grammarAccess.getBaseTypeAccess().getTypeThread_groupKeyword_0_17());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_18, null);
                      	    
                    }

                    }
                    break;
                case 19 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:511:8: lv_type_0_19= 'thread'
                    {
                    lv_type_0_19=(Token)match(input,37,FOLLOW_37_in_ruleBaseType1174); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_19, grammarAccess.getBaseTypeAccess().getTypeThreadKeyword_0_18());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_19, null);
                      	    
                    }

                    }
                    break;
                case 20 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:523:8: lv_type_0_20= 'virtual_bus'
                    {
                    lv_type_0_20=(Token)match(input,38,FOLLOW_38_in_ruleBaseType1203); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_20, grammarAccess.getBaseTypeAccess().getTypeVirtual_busKeyword_0_19());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_20, null);
                      	    
                    }

                    }
                    break;
                case 21 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:535:8: lv_type_0_21= 'virtual_processor'
                    {
                    lv_type_0_21=(Token)match(input,39,FOLLOW_39_in_ruleBaseType1232); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_21, grammarAccess.getBaseTypeAccess().getTypeVirtual_processorKeyword_0_20());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_21, null);
                      	    
                    }

                    }
                    break;
                case 22 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:547:8: lv_type_0_22= 'connection'
                    {
                    lv_type_0_22=(Token)match(input,40,FOLLOW_40_in_ruleBaseType1261); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_22, grammarAccess.getBaseTypeAccess().getTypeConnectionKeyword_0_21());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_22, null);
                      	    
                    }

                    }
                    break;
                case 23 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:559:8: lv_type_0_23= 'property'
                    {
                    lv_type_0_23=(Token)match(input,41,FOLLOW_41_in_ruleBaseType1290); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_23, grammarAccess.getBaseTypeAccess().getTypePropertyKeyword_0_22());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_23, null);
                      	    
                    }

                    }
                    break;
                case 24 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:571:8: lv_type_0_24= 'feature'
                    {
                    lv_type_0_24=(Token)match(input,42,FOLLOW_42_in_ruleBaseType1319); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_24, grammarAccess.getBaseTypeAccess().getTypeFeatureKeyword_0_23());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_24, null);
                      	    
                    }

                    }
                    break;
                case 25 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:583:8: lv_type_0_25= 'port'
                    {
                    lv_type_0_25=(Token)match(input,43,FOLLOW_43_in_ruleBaseType1348); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_25, grammarAccess.getBaseTypeAccess().getTypePortKeyword_0_24());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_25, null);
                      	    
                    }

                    }
                    break;
                case 26 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:595:8: lv_type_0_26= 'data_port'
                    {
                    lv_type_0_26=(Token)match(input,44,FOLLOW_44_in_ruleBaseType1377); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_26, grammarAccess.getBaseTypeAccess().getTypeData_portKeyword_0_25());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_26, null);
                      	    
                    }

                    }
                    break;
                case 27 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:607:8: lv_type_0_27= 'event_port'
                    {
                    lv_type_0_27=(Token)match(input,45,FOLLOW_45_in_ruleBaseType1406); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_27, grammarAccess.getBaseTypeAccess().getTypeEvent_portKeyword_0_26());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_27, null);
                      	    
                    }

                    }
                    break;
                case 28 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:619:8: lv_type_0_28= 'event_data_port'
                    {
                    lv_type_0_28=(Token)match(input,46,FOLLOW_46_in_ruleBaseType1435); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_28, grammarAccess.getBaseTypeAccess().getTypeEvent_data_portKeyword_0_27());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_28, null);
                      	    
                    }

                    }
                    break;
                case 29 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:631:8: lv_type_0_29= 'feature_group'
                    {
                    lv_type_0_29=(Token)match(input,47,FOLLOW_47_in_ruleBaseType1464); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_29, grammarAccess.getBaseTypeAccess().getTypeFeature_groupKeyword_0_28());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_29, null);
                      	    
                    }

                    }
                    break;
                case 30 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:643:8: lv_type_0_30= 'access'
                    {
                    lv_type_0_30=(Token)match(input,48,FOLLOW_48_in_ruleBaseType1493); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_30, grammarAccess.getBaseTypeAccess().getTypeAccessKeyword_0_29());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_30, null);
                      	    
                    }

                    }
                    break;
                case 31 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:655:8: lv_type_0_31= 'bus_access'
                    {
                    lv_type_0_31=(Token)match(input,49,FOLLOW_49_in_ruleBaseType1522); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_31, grammarAccess.getBaseTypeAccess().getTypeBus_accessKeyword_0_30());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_31, null);
                      	    
                    }

                    }
                    break;
                case 32 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:667:8: lv_type_0_32= 'provides_bus_access'
                    {
                    lv_type_0_32=(Token)match(input,50,FOLLOW_50_in_ruleBaseType1551); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_32, grammarAccess.getBaseTypeAccess().getTypeProvides_bus_accessKeyword_0_31());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_32, null);
                      	    
                    }

                    }
                    break;
                case 33 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:679:8: lv_type_0_33= 'requires_bus_access'
                    {
                    lv_type_0_33=(Token)match(input,51,FOLLOW_51_in_ruleBaseType1580); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_33, grammarAccess.getBaseTypeAccess().getTypeRequires_bus_accessKeyword_0_32());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_33, null);
                      	    
                    }

                    }
                    break;
                case 34 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:691:8: lv_type_0_34= 'data_access'
                    {
                    lv_type_0_34=(Token)match(input,52,FOLLOW_52_in_ruleBaseType1609); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_34, grammarAccess.getBaseTypeAccess().getTypeData_accessKeyword_0_33());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_34, null);
                      	    
                    }

                    }
                    break;
                case 35 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:703:8: lv_type_0_35= 'provides_data_access'
                    {
                    lv_type_0_35=(Token)match(input,53,FOLLOW_53_in_ruleBaseType1638); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_35, grammarAccess.getBaseTypeAccess().getTypeProvides_data_accessKeyword_0_34());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_35, null);
                      	    
                    }

                    }
                    break;
                case 36 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:715:8: lv_type_0_36= 'requires_data_access'
                    {
                    lv_type_0_36=(Token)match(input,54,FOLLOW_54_in_ruleBaseType1667); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_36, grammarAccess.getBaseTypeAccess().getTypeRequires_data_accessKeyword_0_35());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_36, null);
                      	    
                    }

                    }
                    break;
                case 37 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:727:8: lv_type_0_37= 'subprogram_access'
                    {
                    lv_type_0_37=(Token)match(input,55,FOLLOW_55_in_ruleBaseType1696); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_37, grammarAccess.getBaseTypeAccess().getTypeSubprogram_accessKeyword_0_36());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_37, null);
                      	    
                    }

                    }
                    break;
                case 38 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:739:8: lv_type_0_38= 'provides_subprogram_access'
                    {
                    lv_type_0_38=(Token)match(input,56,FOLLOW_56_in_ruleBaseType1725); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_38, grammarAccess.getBaseTypeAccess().getTypeProvides_subprogram_accessKeyword_0_37());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_38, null);
                      	    
                    }

                    }
                    break;
                case 39 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:751:8: lv_type_0_39= 'requires_subprogram_access'
                    {
                    lv_type_0_39=(Token)match(input,57,FOLLOW_57_in_ruleBaseType1754); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_39, grammarAccess.getBaseTypeAccess().getTypeRequires_subprogram_accessKeyword_0_38());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_39, null);
                      	    
                    }

                    }
                    break;
                case 40 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:763:8: lv_type_0_40= 'subprogram_group_access'
                    {
                    lv_type_0_40=(Token)match(input,58,FOLLOW_58_in_ruleBaseType1783); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_40, grammarAccess.getBaseTypeAccess().getTypeSubprogram_group_accessKeyword_0_39());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_40, null);
                      	    
                    }

                    }
                    break;
                case 41 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:775:8: lv_type_0_41= 'provides_subprogram_group_access'
                    {
                    lv_type_0_41=(Token)match(input,59,FOLLOW_59_in_ruleBaseType1812); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_41, grammarAccess.getBaseTypeAccess().getTypeProvides_subprogram_group_accessKeyword_0_40());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_41, null);
                      	    
                    }

                    }
                    break;
                case 42 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:787:8: lv_type_0_42= 'requires_subprogram_group_access'
                    {
                    lv_type_0_42=(Token)match(input,60,FOLLOW_60_in_ruleBaseType1841); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_42, grammarAccess.getBaseTypeAccess().getTypeRequires_subprogram_group_accessKeyword_0_41());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_42, null);
                      	    
                    }

                    }
                    break;
                case 43 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:799:8: lv_type_0_43= 'flow_specification'
                    {
                    lv_type_0_43=(Token)match(input,61,FOLLOW_61_in_ruleBaseType1870); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_43, grammarAccess.getBaseTypeAccess().getTypeFlow_specificationKeyword_0_42());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_43, null);
                      	    
                    }

                    }
                    break;
                case 44 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:811:8: lv_type_0_44= 'end_to_end_flow'
                    {
                    lv_type_0_44=(Token)match(input,62,FOLLOW_62_in_ruleBaseType1899); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_type_0_44, grammarAccess.getBaseTypeAccess().getTypeEnd_to_end_flowKeyword_0_43());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBaseTypeRule());
                      	        }
                             		setWithLastConsumed(current, "type", lv_type_0_44, null);
                      	    
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
    // $ANTLR end "ruleBaseType"


    // $ANTLR start "entryRuleArg"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:834:1: entryRuleArg returns [EObject current=null] : iv_ruleArg= ruleArg EOF ;
    public final EObject entryRuleArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArg = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:835:2: (iv_ruleArg= ruleArg EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:836:2: iv_ruleArg= ruleArg EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArgRule()); 
            }
            pushFollow(FOLLOW_ruleArg_in_entryRuleArg1950);
            iv_ruleArg=ruleArg();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArg; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArg1960); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:843:1: ruleArg returns [EObject current=null] : ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:846:28: ( ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:1: ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:1: ( ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ) | ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) ) )
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:2: ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:2: ( ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:3: ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )=> ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:857:6: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:857:7: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:857:7: ( (lv_name_0_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:858:1: (lv_name_0_0= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:858:1: (lv_name_0_0= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:859:3: lv_name_0_0= RULE_ID
                    {
                    lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArg2032); if (state.failed) return current;
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

                    otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleArg2049); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getArgAccess().getColonKeyword_0_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:879:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:880:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:880:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:881:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgAccess().getTypeTypeParserRuleCall_0_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleArg2070);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:6: ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:6: ( () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:7: () ( (lv_name_4_0= RULE_ID ) ) otherlv_5= ':' ( (lv_expr_6_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:898:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:899:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getArgAccess().getQuantArgAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:904:2: ( (lv_name_4_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:905:1: (lv_name_4_0= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:905:1: (lv_name_4_0= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:3: lv_name_4_0= RULE_ID
                    {
                    lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArg2105); if (state.failed) return current;
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

                    otherlv_5=(Token)match(input,63,FOLLOW_63_in_ruleArg2122); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getArgAccess().getColonKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:926:1: ( (lv_expr_6_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:927:1: (lv_expr_6_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:927:1: (lv_expr_6_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:928:3: lv_expr_6_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArgAccess().getExprExprParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleArg2143);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:952:1: entryRuleConstantDefinition returns [EObject current=null] : iv_ruleConstantDefinition= ruleConstantDefinition EOF ;
    public final EObject entryRuleConstantDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:953:2: (iv_ruleConstantDefinition= ruleConstantDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:954:2: iv_ruleConstantDefinition= ruleConstantDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition2180);
            iv_ruleConstantDefinition=ruleConstantDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantDefinition2190); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:961:1: ruleConstantDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleConstantDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:964:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:965:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:965:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:966:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:966:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:967:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleConstantDefinition2232); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleConstantDefinition2249); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getConstantDefinitionAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:987:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:988:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:988:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleConstantDefinition2270);
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

            otherlv_3=(Token)match(input,64,FOLLOW_64_in_ruleConstantDefinition2282); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getConstantDefinitionAccess().getEqualsSignKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1009:1: ( (lv_expr_4_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:1: (lv_expr_4_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:1: (lv_expr_4_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:3: lv_expr_4_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantDefinitionAccess().getExprExprParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleConstantDefinition2303);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1035:1: entryRuleFunctionDefinition returns [EObject current=null] : iv_ruleFunctionDefinition= ruleFunctionDefinition EOF ;
    public final EObject entryRuleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionDefinition = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1036:2: (iv_ruleFunctionDefinition= ruleFunctionDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1037:2: iv_ruleFunctionDefinition= ruleFunctionDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFunctionDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition2339);
            iv_ruleFunctionDefinition=ruleFunctionDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFunctionDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFunctionDefinition2349); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1044:1: ruleFunctionDefinition returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1047:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1048:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1048:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1048:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1048:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1049:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1049:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1050:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFunctionDefinition2391); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleFunctionDefinition2408); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFunctionDefinitionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1070:1: ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1070:2: ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1070:2: ( (lv_args_2_0= ruleArg ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1071:1: (lv_args_2_0= ruleArg )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1071:1: (lv_args_2_0= ruleArg )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1072:3: lv_args_2_0= ruleArg
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition2430);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:2: (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==66) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:4: otherlv_3= ',' ( (lv_args_4_0= ruleArg ) )
                    	    {
                    	    otherlv_3=(Token)match(input,66,FOLLOW_66_in_ruleFunctionDefinition2443); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getFunctionDefinitionAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1092:1: ( (lv_args_4_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:1: (lv_args_4_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:1: (lv_args_4_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:3: lv_args_4_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getArgsArgParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition2464);
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

            otherlv_5=(Token)match(input,67,FOLLOW_67_in_ruleFunctionDefinition2480); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getFunctionDefinitionAccess().getRightParenthesisKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1114:1: ( (lv_body_6_0= ruleDefinitionBody ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1115:1: (lv_body_6_0= ruleDefinitionBody )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1115:1: (lv_body_6_0= ruleDefinitionBody )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1116:3: lv_body_6_0= ruleDefinitionBody
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFunctionDefinitionAccess().getBodyDefinitionBodyParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition2501);
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


    // $ANTLR start "entryRuleDefinitionBody"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1140:1: entryRuleDefinitionBody returns [EObject current=null] : iv_ruleDefinitionBody= ruleDefinitionBody EOF ;
    public final EObject entryRuleDefinitionBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinitionBody = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1141:2: (iv_ruleDefinitionBody= ruleDefinitionBody EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1142:2: iv_ruleDefinitionBody= ruleDefinitionBody EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefinitionBodyRule()); 
            }
            pushFollow(FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody2537);
            iv_ruleDefinitionBody=ruleDefinitionBody();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefinitionBody; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDefinitionBody2547); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1149:1: ruleDefinitionBody returns [EObject current=null] : ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1152:28: ( ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:1: ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==63) ) {
                alt10=1;
            }
            else if ( (LA10_0==68) ) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:2: ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:3: () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1154:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getFunctionBodyAction_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleDefinitionBody2594); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getDefinitionBodyAccess().getColonKeyword_0_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1163:1: ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:1: (lv_type_2_0= ruleType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:1: (lv_type_2_0= ruleType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1165:3: lv_type_2_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getTypeTypeParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleDefinitionBody2615);
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

                    otherlv_3=(Token)match(input,64,FOLLOW_64_in_ruleDefinitionBody2627); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getDefinitionBodyAccess().getEqualsSignKeyword_0_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1185:1: ( (lv_expr_4_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1186:1: (lv_expr_4_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1186:1: (lv_expr_4_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1187:3: lv_expr_4_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_0_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2648);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1204:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1204:6: ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1204:7: () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' ) ( (lv_expr_10_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1204:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1205:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getDefinitionBodyAccess().getClaimBodyAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_6=(Token)match(input,68,FOLLOW_68_in_ruleDefinitionBody2677); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getDefinitionBodyAccess().getLessThanSignEqualsSignKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1214:1: (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1214:3: otherlv_7= '**' ( (lv_claim_8_0= ruleClaimText ) )+ otherlv_9= '**'
                    {
                    otherlv_7=(Token)match(input,69,FOLLOW_69_in_ruleDefinitionBody2690); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1218:1: ( (lv_claim_8_0= ruleClaimText ) )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>=RULE_ID && LA9_0<=RULE_STRING)) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1219:1: (lv_claim_8_0= ruleClaimText )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1219:1: (lv_claim_8_0= ruleClaimText )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1220:3: lv_claim_8_0= ruleClaimText
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getClaimClaimTextParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleClaimText_in_ruleDefinitionBody2711);
                    	    lv_claim_8_0=ruleClaimText();

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
                    	              		"ClaimText");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

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

                    otherlv_9=(Token)match(input,69,FOLLOW_69_in_ruleDefinitionBody2724); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getDefinitionBodyAccess().getAsteriskAsteriskKeyword_1_2_2());
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1240:2: ( (lv_expr_10_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1241:1: (lv_expr_10_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1241:1: (lv_expr_10_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1242:3: lv_expr_10_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getDefinitionBodyAccess().getExprExprParserRuleCall_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2746);
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


    // $ANTLR start "entryRuleClaimText"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1266:1: entryRuleClaimText returns [EObject current=null] : iv_ruleClaimText= ruleClaimText EOF ;
    public final EObject entryRuleClaimText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClaimText = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1267:2: (iv_ruleClaimText= ruleClaimText EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1268:2: iv_ruleClaimText= ruleClaimText EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClaimTextRule()); 
            }
            pushFollow(FOLLOW_ruleClaimText_in_entryRuleClaimText2783);
            iv_ruleClaimText=ruleClaimText();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClaimText; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleClaimText2793); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClaimText"


    // $ANTLR start "ruleClaimText"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1275:1: ruleClaimText returns [EObject current=null] : ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? ) ) ;
    public final EObject ruleClaimText() throws RecognitionException {
        EObject current = null;

        Token lv_str_1_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1278:28: ( ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:1: ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_STRING) ) {
                alt12=1;
            }
            else if ( (LA12_0==RULE_ID) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:2: ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:3: () ( (lv_str_1_0= RULE_STRING ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1279:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1280:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimTextAccess().getClaimStringAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1285:2: ( (lv_str_1_0= RULE_STRING ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1286:1: (lv_str_1_0= RULE_STRING )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1286:1: (lv_str_1_0= RULE_STRING )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1287:3: lv_str_1_0= RULE_STRING
                    {
                    lv_str_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleClaimText2845); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_str_1_0, grammarAccess.getClaimTextAccess().getStrSTRINGTerminalRuleCall_0_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getClaimTextRule());
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1304:6: ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1304:6: ( () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1304:7: () ( (otherlv_3= RULE_ID ) ) (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1304:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1305:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getClaimTextAccess().getClaimArgAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1310:2: ( (otherlv_3= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1311:1: (otherlv_3= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1311:1: (otherlv_3= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1312:3: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getClaimTextRule());
                      	        }
                              
                    }
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleClaimText2887); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_3, grammarAccess.getClaimTextAccess().getArgClaimTextVarCrossReference_1_1_0()); 
                      	
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1323:2: (otherlv_4= '%' ( (otherlv_5= RULE_ID ) ) )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==70) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1323:4: otherlv_4= '%' ( (otherlv_5= RULE_ID ) )
                            {
                            otherlv_4=(Token)match(input,70,FOLLOW_70_in_ruleClaimText2900); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getClaimTextAccess().getPercentSignKeyword_1_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1327:1: ( (otherlv_5= RULE_ID ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1328:1: (otherlv_5= RULE_ID )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1328:1: (otherlv_5= RULE_ID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1329:3: otherlv_5= RULE_ID
                            {
                            if ( state.backtracking==0 ) {

                              			if (current==null) {
                              	            current = createModelElement(grammarAccess.getClaimTextRule());
                              	        }
                                      
                            }
                            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleClaimText2920); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              		newLeafNode(otherlv_5, grammarAccess.getClaimTextAccess().getUnitUnitLiteralCrossReference_1_2_1_0()); 
                              	
                            }

                            }


                            }


                            }
                            break;

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
    // $ANTLR end "ruleClaimText"


    // $ANTLR start "entryRuleExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1350:1: entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1351:2: (iv_ruleExpr= ruleExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1352:2: iv_ruleExpr= ruleExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExprRule()); 
            }
            pushFollow(FOLLOW_ruleExpr_in_entryRuleExpr2961);
            iv_ruleExpr=ruleExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpr2971); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1359:1: ruleExpr returns [EObject current=null] : this_ImpliesExpr_0= ruleImpliesExpr ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_ImpliesExpr_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1362:28: (this_ImpliesExpr_0= ruleImpliesExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1364:5: this_ImpliesExpr_0= ruleImpliesExpr
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExprAccess().getImpliesExprParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_ruleExpr3017);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1380:1: entryRuleImpliesExpr returns [EObject current=null] : iv_ruleImpliesExpr= ruleImpliesExpr EOF ;
    public final EObject entryRuleImpliesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1381:2: (iv_ruleImpliesExpr= ruleImpliesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1382:2: iv_ruleImpliesExpr= ruleImpliesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExprRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr3051);
            iv_ruleImpliesExpr=ruleImpliesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpr3061); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1389:1: ruleImpliesExpr returns [EObject current=null] : (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) ;
    public final EObject ruleImpliesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_OrExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1392:28: ( (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1393:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1393:1: (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1394:5: this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExprAccess().getOrExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpr_in_ruleImpliesExpr3108);
            this_OrExpr_0=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:1: ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==71) ) {
                int LA13_1 = input.LA(2);

                if ( (synpred2_InternalResolute()) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:2: ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:3: ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1409:6: ( () ( (lv_op_2_0= '=>' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1409:7: () ( (lv_op_2_0= '=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1409:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1410:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1415:2: ( (lv_op_2_0= '=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1416:1: (lv_op_2_0= '=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1416:1: (lv_op_2_0= '=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1417:3: lv_op_2_0= '=>'
                    {
                    lv_op_2_0=(Token)match(input,71,FOLLOW_71_in_ruleImpliesExpr3159); if (state.failed) return current;
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1430:4: ( (lv_right_3_0= ruleImpliesExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1431:1: (lv_right_3_0= ruleImpliesExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1431:1: (lv_right_3_0= ruleImpliesExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1432:3: lv_right_3_0= ruleImpliesExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImpliesExprAccess().getRightImpliesExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr3195);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1456:1: entryRuleOrExpr returns [EObject current=null] : iv_ruleOrExpr= ruleOrExpr EOF ;
    public final EObject entryRuleOrExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1457:2: (iv_ruleOrExpr= ruleOrExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1458:2: iv_ruleOrExpr= ruleOrExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExprRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpr_in_entryRuleOrExpr3233);
            iv_ruleOrExpr=ruleOrExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpr3243); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1465:1: ruleOrExpr returns [EObject current=null] : (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) ;
    public final EObject ruleOrExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_AndExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1468:28: ( (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1469:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1469:1: (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1470:5: this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExprAccess().getAndExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr3290);
            this_AndExpr_0=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:1: ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==72) ) {
                    int LA14_2 = input.LA(2);

                    if ( (synpred3_InternalResolute()) ) {
                        alt14=1;
                    }


                }


                switch (alt14) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:2: ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:3: ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1485:6: ( () ( (lv_op_2_0= 'or' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1485:7: () ( (lv_op_2_0= 'or' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1485:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1486:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1491:2: ( (lv_op_2_0= 'or' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1492:1: (lv_op_2_0= 'or' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1492:1: (lv_op_2_0= 'or' )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1493:3: lv_op_2_0= 'or'
            	    {
            	    lv_op_2_0=(Token)match(input,72,FOLLOW_72_in_ruleOrExpr3341); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1506:4: ( (lv_right_3_0= ruleAndExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1507:1: (lv_right_3_0= ruleAndExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1507:1: (lv_right_3_0= ruleAndExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1508:3: lv_right_3_0= ruleAndExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExprAccess().getRightAndExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr3377);
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
    // $ANTLR end "ruleOrExpr"


    // $ANTLR start "entryRuleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1532:1: entryRuleAndExpr returns [EObject current=null] : iv_ruleAndExpr= ruleAndExpr EOF ;
    public final EObject entryRuleAndExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1533:2: (iv_ruleAndExpr= ruleAndExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1534:2: iv_ruleAndExpr= ruleAndExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExprRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpr_in_entryRuleAndExpr3415);
            iv_ruleAndExpr=ruleAndExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpr3425); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1541:1: ruleAndExpr returns [EObject current=null] : (this_InstanceOfExpr_0= ruleInstanceOfExpr ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )* ) ;
    public final EObject ruleAndExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        Token lv_op_3_0=null;
        EObject this_InstanceOfExpr_0 = null;

        EObject lv_right_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1544:28: ( (this_InstanceOfExpr_0= ruleInstanceOfExpr ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1545:1: (this_InstanceOfExpr_0= ruleInstanceOfExpr ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1545:1: (this_InstanceOfExpr_0= ruleInstanceOfExpr ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1546:5: this_InstanceOfExpr_0= ruleInstanceOfExpr ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExprAccess().getInstanceOfExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleInstanceOfExpr_in_ruleAndExpr3472);
            this_InstanceOfExpr_0=ruleInstanceOfExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_InstanceOfExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:1: ( ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==73) ) {
                    int LA16_2 = input.LA(2);

                    if ( (synpred4_InternalResolute()) ) {
                        alt16=1;
                    }


                }
                else if ( (LA16_0==74) ) {
                    int LA16_3 = input.LA(2);

                    if ( (synpred4_InternalResolute()) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:2: ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) ) ( (lv_right_4_0= ruleInstanceOfExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:2: ( ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:3: ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )=> ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1568:7: ( () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1568:8: () ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1568:8: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1569:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1574:2: ( ( (lv_op_2_0= 'and' ) ) | ( (lv_op_3_0= 'andthen' ) ) )
            	    int alt15=2;
            	    int LA15_0 = input.LA(1);

            	    if ( (LA15_0==73) ) {
            	        alt15=1;
            	    }
            	    else if ( (LA15_0==74) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1574:3: ( (lv_op_2_0= 'and' ) )
            	            {
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1574:3: ( (lv_op_2_0= 'and' ) )
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1575:1: (lv_op_2_0= 'and' )
            	            {
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1575:1: (lv_op_2_0= 'and' )
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1576:3: lv_op_2_0= 'and'
            	            {
            	            lv_op_2_0=(Token)match(input,73,FOLLOW_73_in_ruleAndExpr3546); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_0, grammarAccess.getAndExprAccess().getOpAndKeyword_1_0_0_1_0_0());
            	                  
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
            	            break;
            	        case 2 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1590:6: ( (lv_op_3_0= 'andthen' ) )
            	            {
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1590:6: ( (lv_op_3_0= 'andthen' ) )
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1591:1: (lv_op_3_0= 'andthen' )
            	            {
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1591:1: (lv_op_3_0= 'andthen' )
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1592:3: lv_op_3_0= 'andthen'
            	            {
            	            lv_op_3_0=(Token)match(input,74,FOLLOW_74_in_ruleAndExpr3583); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_3_0, grammarAccess.getAndExprAccess().getOpAndthenKeyword_1_0_0_1_1_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getAndExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_3_0, "andthen");
            	              	    
            	            }

            	            }


            	            }


            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1605:5: ( (lv_right_4_0= ruleInstanceOfExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1606:1: (lv_right_4_0= ruleInstanceOfExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1606:1: (lv_right_4_0= ruleInstanceOfExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1607:3: lv_right_4_0= ruleInstanceOfExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExprAccess().getRightInstanceOfExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInstanceOfExpr_in_ruleAndExpr3620);
            	    lv_right_4_0=ruleInstanceOfExpr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getAndExprRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_4_0, 
            	              		"InstanceOfExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
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


    // $ANTLR start "entryRuleInstanceOfExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1631:1: entryRuleInstanceOfExpr returns [EObject current=null] : iv_ruleInstanceOfExpr= ruleInstanceOfExpr EOF ;
    public final EObject entryRuleInstanceOfExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstanceOfExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1632:2: (iv_ruleInstanceOfExpr= ruleInstanceOfExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1633:2: iv_ruleInstanceOfExpr= ruleInstanceOfExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInstanceOfExprRule()); 
            }
            pushFollow(FOLLOW_ruleInstanceOfExpr_in_entryRuleInstanceOfExpr3658);
            iv_ruleInstanceOfExpr=ruleInstanceOfExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInstanceOfExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstanceOfExpr3668); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInstanceOfExpr"


    // $ANTLR start "ruleInstanceOfExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1640:1: ruleInstanceOfExpr returns [EObject current=null] : (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )? ) ;
    public final EObject ruleInstanceOfExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_RelationalExpr_0 = null;

        EObject lv_type_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1643:28: ( (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1644:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1644:1: (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1645:5: this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInstanceOfExprAccess().getRelationalExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_ruleInstanceOfExpr3715);
            this_RelationalExpr_0=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:1: ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==75) ) {
                int LA17_1 = input.LA(2);

                if ( (synpred5_InternalResolute()) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:2: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleBaseType ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:2: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:3: ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1655:5: ( () otherlv_2= 'instanceof' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1655:6: () otherlv_2= 'instanceof'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1655:6: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1656:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,75,FOLLOW_75_in_ruleInstanceOfExpr3750); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getInstanceOfExprAccess().getInstanceofKeyword_1_0_0_1());
                          
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1665:3: ( (lv_type_3_0= ruleBaseType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:1: (lv_type_3_0= ruleBaseType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:1: (lv_type_3_0= ruleBaseType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1667:3: lv_type_3_0= ruleBaseType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInstanceOfExprAccess().getTypeBaseTypeParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBaseType_in_ruleInstanceOfExpr3773);
                    lv_type_3_0=ruleBaseType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInstanceOfExprRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_3_0, 
                              		"BaseType");
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
    // $ANTLR end "ruleInstanceOfExpr"


    // $ANTLR start "entryRuleRelationalOp"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1691:1: entryRuleRelationalOp returns [String current=null] : iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1692:2: (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1693:2: iv_ruleRelationalOp= ruleRelationalOp EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalOpRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp3812);
            iv_ruleRelationalOp=ruleRelationalOp();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalOp.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalOp3823); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1700:1: ruleRelationalOp returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1703:28: ( (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1704:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1704:1: (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' )
            int alt18=6;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt18=1;
                }
                break;
            case 68:
                {
                alt18=2;
                }
                break;
            case 18:
                {
                alt18=3;
                }
                break;
            case 76:
                {
                alt18=4;
                }
                break;
            case 64:
                {
                alt18=5;
                }
                break;
            case 77:
                {
                alt18=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1705:2: kw= '<'
                    {
                    kw=(Token)match(input,17,FOLLOW_17_in_ruleRelationalOp3861); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1712:2: kw= '<='
                    {
                    kw=(Token)match(input,68,FOLLOW_68_in_ruleRelationalOp3880); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignEqualsSignKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1719:2: kw= '>'
                    {
                    kw=(Token)match(input,18,FOLLOW_18_in_ruleRelationalOp3899); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1726:2: kw= '>='
                    {
                    kw=(Token)match(input,76,FOLLOW_76_in_ruleRelationalOp3918); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getGreaterThanSignEqualsSignKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1733:2: kw= '='
                    {
                    kw=(Token)match(input,64,FOLLOW_64_in_ruleRelationalOp3937); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getEqualsSignKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1740:2: kw= '<>'
                    {
                    kw=(Token)match(input,77,FOLLOW_77_in_ruleRelationalOp3956); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getRelationalOpAccess().getLessThanSignGreaterThanSignKeyword_5()); 
                          
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1753:1: entryRuleRelationalExpr returns [EObject current=null] : iv_ruleRelationalExpr= ruleRelationalExpr EOF ;
    public final EObject entryRuleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1754:2: (iv_ruleRelationalExpr= ruleRelationalExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1755:2: iv_ruleRelationalExpr= ruleRelationalExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExprRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr3996);
            iv_ruleRelationalExpr=ruleRelationalExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpr4006); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1762:1: ruleRelationalExpr returns [EObject current=null] : (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) ;
    public final EObject ruleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject this_PlusExpr_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1765:28: ( (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1766:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1766:1: (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1767:5: this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExprAccess().getPlusExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr4053);
            this_PlusExpr_0=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PlusExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
            int alt19=2;
            switch ( input.LA(1) ) {
                case 17:
                    {
                    int LA19_1 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
                case 68:
                    {
                    int LA19_2 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
                case 18:
                    {
                    int LA19_3 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
                case 76:
                    {
                    int LA19_4 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
                case 64:
                    {
                    int LA19_5 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
                case 77:
                    {
                    int LA19_6 = input.LA(2);

                    if ( (synpred6_InternalResolute()) ) {
                        alt19=1;
                    }
                    }
                    break;
            }

            switch (alt19) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:2: ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:3: ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1780:6: ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1780:7: () ( (lv_op_2_0= ruleRelationalOp ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1780:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1781:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1786:2: ( (lv_op_2_0= ruleRelationalOp ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1787:1: (lv_op_2_0= ruleRelationalOp )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1787:1: (lv_op_2_0= ruleRelationalOp )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1788:3: lv_op_2_0= ruleRelationalOp
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getOpRelationalOpParserRuleCall_1_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRelationalOp_in_ruleRelationalExpr4102);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1804:4: ( (lv_right_3_0= rulePlusExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1805:1: (lv_right_3_0= rulePlusExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1805:1: (lv_right_3_0= rulePlusExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1806:3: lv_right_3_0= rulePlusExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getRelationalExprAccess().getRightPlusExprParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr4125);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1830:1: entryRulePlusExpr returns [EObject current=null] : iv_rulePlusExpr= rulePlusExpr EOF ;
    public final EObject entryRulePlusExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1831:2: (iv_rulePlusExpr= rulePlusExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1832:2: iv_rulePlusExpr= rulePlusExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusExprRule()); 
            }
            pushFollow(FOLLOW_rulePlusExpr_in_entryRulePlusExpr4163);
            iv_rulePlusExpr=rulePlusExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusExpr4173); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1839:1: rulePlusExpr returns [EObject current=null] : (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) ;
    public final EObject rulePlusExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_TimesExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1842:28: ( (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1843:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1843:1: (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1844:5: this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getPlusExprAccess().getTimesExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr4220);
            this_TimesExpr_0=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_TimesExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:1: ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==78) ) {
                    int LA21_2 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt21=1;
                    }


                }
                else if ( (LA21_0==79) ) {
                    int LA21_3 = input.LA(2);

                    if ( (synpred7_InternalResolute()) ) {
                        alt21=1;
                    }


                }


                switch (alt21) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:2: ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:6: ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:7: () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1865:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1866:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1871:2: ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1872:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1872:1: ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1873:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1873:1: (lv_op_2_1= '+' | lv_op_2_2= '-' )
            	    int alt20=2;
            	    int LA20_0 = input.LA(1);

            	    if ( (LA20_0==78) ) {
            	        alt20=1;
            	    }
            	    else if ( (LA20_0==79) ) {
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1874:3: lv_op_2_1= '+'
            	            {
            	            lv_op_2_1=(Token)match(input,78,FOLLOW_78_in_rulePlusExpr4292); if (state.failed) return current;
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1886:8: lv_op_2_2= '-'
            	            {
            	            lv_op_2_2=(Token)match(input,79,FOLLOW_79_in_rulePlusExpr4321); if (state.failed) return current;
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

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1901:4: ( (lv_right_3_0= ruleTimesExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1902:1: (lv_right_3_0= ruleTimesExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1902:1: (lv_right_3_0= ruleTimesExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1903:3: lv_right_3_0= ruleTimesExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getPlusExprAccess().getRightTimesExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr4360);
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
    // $ANTLR end "rulePlusExpr"


    // $ANTLR start "entryRuleTimesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1927:1: entryRuleTimesExpr returns [EObject current=null] : iv_ruleTimesExpr= ruleTimesExpr EOF ;
    public final EObject entryRuleTimesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimesExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1928:2: (iv_ruleTimesExpr= ruleTimesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1929:2: iv_ruleTimesExpr= ruleTimesExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTimesExprRule()); 
            }
            pushFollow(FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr4398);
            iv_ruleTimesExpr=ruleTimesExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTimesExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTimesExpr4408); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1936:1: ruleTimesExpr returns [EObject current=null] : (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) ;
    public final EObject ruleTimesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        EObject this_PrefixExpr_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1939:28: ( (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1940:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1940:1: (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1941:5: this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getTimesExprAccess().getPrefixExprParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_ruleTimesExpr4455);
            this_PrefixExpr_0=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrefixExpr_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:1: ( ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )*
            loop23:
            do {
                int alt23=2;
                switch ( input.LA(1) ) {
                case 80:
                    {
                    int LA23_2 = input.LA(2);

                    if ( (synpred8_InternalResolute()) ) {
                        alt23=1;
                    }


                    }
                    break;
                case 81:
                    {
                    int LA23_3 = input.LA(2);

                    if ( (synpred8_InternalResolute()) ) {
                        alt23=1;
                    }


                    }
                    break;
                case 70:
                    {
                    int LA23_4 = input.LA(2);

                    if ( (synpred8_InternalResolute()) ) {
                        alt23=1;
                    }


                    }
                    break;

                }

                switch (alt23) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:2: ( ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1965:6: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1965:7: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1965:7: ()
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1966:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1971:2: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1972:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1972:1: ( (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1973:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1973:1: (lv_op_2_1= '*' | lv_op_2_2= '/' | lv_op_2_3= '%' )
            	    int alt22=3;
            	    switch ( input.LA(1) ) {
            	    case 80:
            	        {
            	        alt22=1;
            	        }
            	        break;
            	    case 81:
            	        {
            	        alt22=2;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt22=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 22, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt22) {
            	        case 1 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1974:3: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,80,FOLLOW_80_in_ruleTimesExpr4541); if (state.failed) return current;
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
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:8: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,81,FOLLOW_81_in_ruleTimesExpr4570); if (state.failed) return current;
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
            	        case 3 :
            	            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1998:8: lv_op_2_3= '%'
            	            {
            	            lv_op_2_3=(Token)match(input,70,FOLLOW_70_in_ruleTimesExpr4599); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_op_2_3, grammarAccess.getTimesExprAccess().getOpPercentSignKeyword_1_0_0_1_0_2());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getTimesExprRule());
            	              	        }
            	                     		setWithLastConsumed(current, "op", lv_op_2_3, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }


            	    }

            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2013:4: ( (lv_right_3_0= rulePrefixExpr ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2014:1: (lv_right_3_0= rulePrefixExpr )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2014:1: (lv_right_3_0= rulePrefixExpr )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2015:3: lv_right_3_0= rulePrefixExpr
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTimesExprAccess().getRightPrefixExprParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_rulePrefixExpr_in_ruleTimesExpr4638);
            	    lv_right_3_0=rulePrefixExpr();

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
            	              		"PrefixExpr");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
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


    // $ANTLR start "entryRulePrefixExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2039:1: entryRulePrefixExpr returns [EObject current=null] : iv_rulePrefixExpr= rulePrefixExpr EOF ;
    public final EObject entryRulePrefixExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2040:2: (iv_rulePrefixExpr= rulePrefixExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2041:2: iv_rulePrefixExpr= rulePrefixExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrefixExprRule()); 
            }
            pushFollow(FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr4676);
            iv_rulePrefixExpr=rulePrefixExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrefixExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrefixExpr4686); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2048:1: rulePrefixExpr returns [EObject current=null] : ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) ) | this_AtomicExpr_8= ruleAtomicExpr ) ;
    public final EObject rulePrefixExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_expr_2_0 = null;

        EObject lv_type_5_0 = null;

        EObject lv_expr_7_0 = null;

        EObject this_AtomicExpr_8 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2051:28: ( ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) ) | this_AtomicExpr_8= ruleAtomicExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) ) | this_AtomicExpr_8= ruleAtomicExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:1: ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) | ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) ) | this_AtomicExpr_8= ruleAtomicExpr )
            int alt25=3;
            switch ( input.LA(1) ) {
            case 79:
            case 82:
                {
                alt25=1;
                }
                break;
            case 65:
                {
                switch ( input.LA(2) ) {
                case RULE_ID:
                case RULE_STRING:
                case RULE_REAL_LIT:
                case RULE_INTEGER_LIT:
                case 15:
                case 65:
                case 79:
                case 82:
                case 83:
                case 85:
                case 86:
                case 89:
                case 90:
                case 93:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 160:
                case 161:
                    {
                    alt25=3;
                    }
                    break;
                case 41:
                    {
                    int LA25_4 = input.LA(3);

                    if ( (LA25_4==67) ) {
                        alt25=2;
                    }
                    else if ( (LA25_4==65) ) {
                        alt25=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 4, input);

                        throw nvae;
                    }
                    }
                    break;
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                    {
                    alt25=2;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 2, input);

                    throw nvae;
                }

                }
                break;
            case RULE_ID:
            case RULE_STRING:
            case RULE_REAL_LIT:
            case RULE_INTEGER_LIT:
            case 15:
            case 41:
            case 83:
            case 85:
            case 86:
            case 89:
            case 90:
            case 93:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 160:
            case 161:
                {
                alt25=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:2: ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:3: () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2053:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixExprAccess().getUnaryExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2058:2: ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2059:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2059:1: ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2060:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2060:1: (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==79) ) {
                        alt24=1;
                    }
                    else if ( (LA24_0==82) ) {
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2061:3: lv_op_1_1= '-'
                            {
                            lv_op_1_1=(Token)match(input,79,FOLLOW_79_in_rulePrefixExpr4741); if (state.failed) return current;
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2073:8: lv_op_1_2= 'not'
                            {
                            lv_op_1_2=(Token)match(input,82,FOLLOW_82_in_rulePrefixExpr4770); if (state.failed) return current;
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2088:2: ( (lv_expr_2_0= rulePrefixExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2089:1: (lv_expr_2_0= rulePrefixExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2089:1: (lv_expr_2_0= rulePrefixExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2090:3: lv_expr_2_0= rulePrefixExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrefixExprAccess().getExprPrefixExprParserRuleCall_0_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePrefixExpr_in_rulePrefixExpr4807);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2107:6: ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2107:6: ( () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2107:7: () otherlv_4= '(' ( (lv_type_5_0= ruleBaseType ) ) otherlv_6= ')' ( (lv_expr_7_0= rulePrefixExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2107:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2108:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixExprAccess().getCastExprAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_4=(Token)match(input,65,FOLLOW_65_in_rulePrefixExpr4836); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getPrefixExprAccess().getLeftParenthesisKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2117:1: ( (lv_type_5_0= ruleBaseType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2118:1: (lv_type_5_0= ruleBaseType )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2118:1: (lv_type_5_0= ruleBaseType )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2119:3: lv_type_5_0= ruleBaseType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrefixExprAccess().getTypeBaseTypeParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBaseType_in_rulePrefixExpr4857);
                    lv_type_5_0=ruleBaseType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixExprRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_5_0, 
                              		"BaseType");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,67,FOLLOW_67_in_rulePrefixExpr4869); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getPrefixExprAccess().getRightParenthesisKeyword_1_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2139:1: ( (lv_expr_7_0= rulePrefixExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2140:1: (lv_expr_7_0= rulePrefixExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2140:1: (lv_expr_7_0= rulePrefixExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2141:3: lv_expr_7_0= rulePrefixExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getPrefixExprAccess().getExprPrefixExprParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePrefixExpr_in_rulePrefixExpr4890);
                    lv_expr_7_0=rulePrefixExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_7_0, 
                              		"PrefixExpr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2159:5: this_AtomicExpr_8= ruleAtomicExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrefixExprAccess().getAtomicExprParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleAtomicExpr_in_rulePrefixExpr4919);
                    this_AtomicExpr_8=ruleAtomicExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_AtomicExpr_8; 
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2175:1: entryRuleAtomicExpr returns [EObject current=null] : iv_ruleAtomicExpr= ruleAtomicExpr EOF ;
    public final EObject entryRuleAtomicExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicExpr = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2176:2: (iv_ruleAtomicExpr= ruleAtomicExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2177:2: iv_ruleAtomicExpr= ruleAtomicExpr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAtomicExprRule()); 
            }
            pushFollow(FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr4954);
            iv_ruleAtomicExpr=ruleAtomicExpr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAtomicExpr; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtomicExpr4964); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2184:1: ruleAtomicExpr returns [EObject current=null] : ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) ) | ( () ( (lv_val_13_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_15_0= ruleRealTerm ) ) ) | ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_19_0= ruleStringTerm ) ) ) | ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) ) | ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' ) | ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' ) | (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' ) | ( () otherlv_62= '{' otherlv_63= '}' ) | ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) ) | (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' ) ) ;
    public final EObject ruleAtomicExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token lv_quant_28_1=null;
        Token lv_quant_28_2=null;
        Token otherlv_29=null;
        Token otherlv_31=null;
        Token otherlv_32=null;
        Token otherlv_36=null;
        Token otherlv_38=null;
        Token otherlv_40=null;
        Token otherlv_42=null;
        Token otherlv_43=null;
        Token otherlv_45=null;
        Token otherlv_47=null;
        Token otherlv_48=null;
        Token otherlv_51=null;
        Token otherlv_52=null;
        Token otherlv_54=null;
        Token otherlv_55=null;
        Token otherlv_58=null;
        Token otherlv_60=null;
        Token otherlv_62=null;
        Token otherlv_63=null;
        Token otherlv_65=null;
        Token otherlv_67=null;
        Token otherlv_69=null;
        Token otherlv_71=null;
        EObject lv_sub_5_0 = null;

        EObject lv_val_8_0 = null;

        EObject lv_failmsg_10_0 = null;

        EObject lv_val_13_0 = null;

        EObject lv_val_15_0 = null;

        EObject lv_val_17_0 = null;

        EObject lv_val_19_0 = null;

        EObject lv_cond_22_0 = null;

        EObject lv_then_24_0 = null;

        EObject lv_else_26_0 = null;

        EObject lv_args_30_0 = null;

        EObject lv_expr_33_0 = null;

        AntlrDatatypeRuleToken lv_fn_35_0 = null;

        EObject lv_args_37_0 = null;

        EObject lv_args_39_0 = null;

        EObject lv_args_44_0 = null;

        EObject lv_args_46_0 = null;

        EObject this_Expr_49 = null;

        EObject lv_args_53_0 = null;

        EObject lv_filter_56_0 = null;

        EObject lv_exprs_59_0 = null;

        EObject lv_binding_66_0 = null;

        EObject lv_expr_68_0 = null;

        EObject this_Expr_70 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2187:28: ( ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) ) | ( () ( (lv_val_13_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_15_0= ruleRealTerm ) ) ) | ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_19_0= ruleStringTerm ) ) ) | ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) ) | ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' ) | ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' ) | (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' ) | ( () otherlv_62= '{' otherlv_63= '}' ) | ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) ) | (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) ) | ( () ( (lv_val_13_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_15_0= ruleRealTerm ) ) ) | ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_19_0= ruleStringTerm ) ) ) | ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) ) | ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' ) | ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' ) | (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' ) | ( () otherlv_62= '{' otherlv_63= '}' ) | ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) ) | (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) ) | ( () ( (lv_val_13_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_15_0= ruleRealTerm ) ) ) | ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_19_0= ruleStringTerm ) ) ) | ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) ) | ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' ) | ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' ) | (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' ) | ( () otherlv_62= '{' otherlv_63= '}' ) | ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) ) | (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' ) )
            int alt39=15;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:2: ( () ( ( ruleQCREF ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:2: ( () ( ( ruleQCREF ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:3: () ( ( ruleQCREF ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2188:3: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2189:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIdExprAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2194:2: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2195:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2195:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2196:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getIdNamedElementCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleAtomicExpr5022);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2210:6: ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2210:6: ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2210:7: () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )?
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2210:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2211:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getThisExprAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_3=(Token)match(input,83,FOLLOW_83_in_ruleAtomicExpr5051); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getAtomicExprAccess().getThisKeyword_1_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2220:1: (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==84) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2220:3: otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) )
                            {
                            otherlv_4=(Token)match(input,84,FOLLOW_84_in_ruleAtomicExpr5064); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getAtomicExprAccess().getFullStopKeyword_1_2_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2224:1: ( (lv_sub_5_0= ruleNestedDotID ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2225:1: (lv_sub_5_0= ruleNestedDotID )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2225:1: (lv_sub_5_0= ruleNestedDotID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2226:3: lv_sub_5_0= ruleNestedDotID
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getSubNestedDotIDParserRuleCall_1_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleNestedDotID_in_ruleAtomicExpr5085);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2243:6: ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2243:6: ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2243:7: () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2243:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2244:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFailExprAction_2_0(),
                                  current);
                          
                    }

                    }

                    otherlv_7=(Token)match(input,85,FOLLOW_85_in_ruleAtomicExpr5116); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getAtomicExprAccess().getFailKeyword_2_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2253:1: ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) )
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>=RULE_ID && LA28_0<=RULE_INTEGER_LIT)||LA28_0==15||LA28_0==41||LA28_0==65||LA28_0==79||(LA28_0>=82 && LA28_0<=83)||(LA28_0>=85 && LA28_0<=86)||(LA28_0>=89 && LA28_0<=90)||LA28_0==93||(LA28_0>=95 && LA28_0<=151)||(LA28_0>=160 && LA28_0<=161)) ) {
                        alt28=1;
                    }
                    else if ( (LA28_0==69) ) {
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2253:2: ( (lv_val_8_0= ruleExpr ) )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2253:2: ( (lv_val_8_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2254:1: (lv_val_8_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2254:1: (lv_val_8_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2255:3: lv_val_8_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValExprParserRuleCall_2_2_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5138);
                            lv_val_8_0=ruleExpr();

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
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:6: (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:6: (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2272:8: otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**'
                            {
                            otherlv_9=(Token)match(input,69,FOLLOW_69_in_ruleAtomicExpr5157); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_9, grammarAccess.getAtomicExprAccess().getAsteriskAsteriskKeyword_2_2_1_0());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2276:1: ( (lv_failmsg_10_0= ruleClaimText ) )+
                            int cnt27=0;
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( ((LA27_0>=RULE_ID && LA27_0<=RULE_STRING)) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2277:1: (lv_failmsg_10_0= ruleClaimText )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2277:1: (lv_failmsg_10_0= ruleClaimText )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2278:3: lv_failmsg_10_0= ruleClaimText
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFailmsgClaimTextParserRuleCall_2_2_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleClaimText_in_ruleAtomicExpr5178);
                            	    lv_failmsg_10_0=ruleClaimText();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"failmsg",
                            	              		lv_failmsg_10_0, 
                            	              		"ClaimText");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

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

                            otherlv_11=(Token)match(input,69,FOLLOW_69_in_ruleAtomicExpr5191); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_11, grammarAccess.getAtomicExprAccess().getAsteriskAsteriskKeyword_2_2_1_2());
                                  
                            }

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:6: ( () ( (lv_val_13_0= ruleIntegerTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:6: ( () ( (lv_val_13_0= ruleIntegerTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:7: () ( (lv_val_13_0= ruleIntegerTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2299:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2300:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIntExprAction_3_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2305:2: ( (lv_val_13_0= ruleIntegerTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2306:1: (lv_val_13_0= ruleIntegerTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2306:1: (lv_val_13_0= ruleIntegerTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2307:3: lv_val_13_0= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValIntegerTermParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr5231);
                    lv_val_13_0=ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_13_0, 
                              		"IntegerTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:6: ( () ( (lv_val_15_0= ruleRealTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:6: ( () ( (lv_val_15_0= ruleRealTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:7: () ( (lv_val_15_0= ruleRealTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2325:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getRealExprAction_4_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2330:2: ( (lv_val_15_0= ruleRealTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2331:1: (lv_val_15_0= ruleRealTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2331:1: (lv_val_15_0= ruleRealTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2332:3: lv_val_15_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValRealTermParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleAtomicExpr5269);
                    lv_val_15_0=ruleRealTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_15_0, 
                              		"RealTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2349:6: ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2349:6: ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2349:7: () ( (lv_val_17_0= ruleBooleanLiteral ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2349:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2350:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBoolExprAction_5_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2355:2: ( (lv_val_17_0= ruleBooleanLiteral ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2356:1: (lv_val_17_0= ruleBooleanLiteral )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2356:1: (lv_val_17_0= ruleBooleanLiteral )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2357:3: lv_val_17_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValBooleanLiteralParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr5307);
                    lv_val_17_0=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_17_0, 
                              		"BooleanLiteral");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2374:6: ( () ( (lv_val_19_0= ruleStringTerm ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2374:6: ( () ( (lv_val_19_0= ruleStringTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2374:7: () ( (lv_val_19_0= ruleStringTerm ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2374:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2375:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getStringExprAction_6_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2380:2: ( (lv_val_19_0= ruleStringTerm ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2381:1: (lv_val_19_0= ruleStringTerm )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2381:1: (lv_val_19_0= ruleStringTerm )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2382:3: lv_val_19_0= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getValStringTermParserRuleCall_6_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr5345);
                    lv_val_19_0=ruleStringTerm();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"val",
                              		lv_val_19_0, 
                              		"StringTerm");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2399:6: ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2399:6: ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2399:7: () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2399:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2400:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getIfThenElseExprAction_7_0(),
                                  current);
                          
                    }

                    }

                    otherlv_21=(Token)match(input,86,FOLLOW_86_in_ruleAtomicExpr5374); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_21, grammarAccess.getAtomicExprAccess().getIfKeyword_7_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2409:1: ( (lv_cond_22_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2410:1: (lv_cond_22_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2410:1: (lv_cond_22_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2411:3: lv_cond_22_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getCondExprParserRuleCall_7_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5395);
                    lv_cond_22_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"cond",
                              		lv_cond_22_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_23=(Token)match(input,87,FOLLOW_87_in_ruleAtomicExpr5407); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_23, grammarAccess.getAtomicExprAccess().getThenKeyword_7_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2431:1: ( (lv_then_24_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2432:1: (lv_then_24_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2432:1: (lv_then_24_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2433:3: lv_then_24_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getThenExprParserRuleCall_7_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5428);
                    lv_then_24_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"then",
                              		lv_then_24_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_25=(Token)match(input,88,FOLLOW_88_in_ruleAtomicExpr5440); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_25, grammarAccess.getAtomicExprAccess().getElseKeyword_7_5());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2453:1: ( (lv_else_26_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2454:1: (lv_else_26_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2454:1: (lv_else_26_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2455:3: lv_else_26_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getElseExprParserRuleCall_7_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5461);
                    lv_else_26_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"else",
                              		lv_else_26_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2472:6: ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2472:6: ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2472:7: () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2472:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2473:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getQuantifiedExprAction_8_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2478:2: ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2479:1: ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2479:1: ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:1: (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:1: (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' )
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==89) ) {
                        alt29=1;
                    }
                    else if ( (LA29_0==90) ) {
                        alt29=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);

                        throw nvae;
                    }
                    switch (alt29) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2481:3: lv_quant_28_1= 'forall'
                            {
                            lv_quant_28_1=(Token)match(input,89,FOLLOW_89_in_ruleAtomicExpr5498); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_28_1, grammarAccess.getAtomicExprAccess().getQuantForallKeyword_8_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_28_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2493:8: lv_quant_28_2= 'exists'
                            {
                            lv_quant_28_2=(Token)match(input,90,FOLLOW_90_in_ruleAtomicExpr5527); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_quant_28_2, grammarAccess.getAtomicExprAccess().getQuantExistsKeyword_8_1_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getAtomicExprRule());
                              	        }
                                     		setWithLastConsumed(current, "quant", lv_quant_28_2, null);
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2508:2: (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==65) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2508:4: otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')'
                    	    {
                    	    otherlv_29=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr5556); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_29, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_8_2_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2512:1: ( (lv_args_30_0= ruleArg ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2513:1: (lv_args_30_0= ruleArg )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2513:1: (lv_args_30_0= ruleArg )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2514:3: lv_args_30_0= ruleArg
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_8_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr5577);
                    	    lv_args_30_0=ruleArg();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"args",
                    	              		lv_args_30_0, 
                    	              		"Arg");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }

                    	    otherlv_31=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr5589); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_31, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_8_2_2());
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);

                    otherlv_32=(Token)match(input,84,FOLLOW_84_in_ruleAtomicExpr5603); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_32, grammarAccess.getAtomicExprAccess().getFullStopKeyword_8_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2538:1: ( (lv_expr_33_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2539:1: (lv_expr_33_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2539:1: (lv_expr_33_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2540:3: lv_expr_33_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_8_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5624);
                    lv_expr_33_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_33_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2557:6: ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2557:6: ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2557:7: () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2557:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2558:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getBuiltInFnCallExprAction_9_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2563:2: ( (lv_fn_35_0= ruleBuiltInFn ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2564:1: (lv_fn_35_0= ruleBuiltInFn )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2564:1: (lv_fn_35_0= ruleBuiltInFn )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2565:3: lv_fn_35_0= ruleBuiltInFn
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFnBuiltInFnParserRuleCall_9_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleBuiltInFn_in_ruleAtomicExpr5662);
                    lv_fn_35_0=ruleBuiltInFn();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"fn",
                              		lv_fn_35_0, 
                              		"BuiltInFn");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_36=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr5674); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_36, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_9_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:1: ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( ((LA32_0>=RULE_ID && LA32_0<=RULE_INTEGER_LIT)||LA32_0==15||LA32_0==41||LA32_0==65||LA32_0==79||(LA32_0>=82 && LA32_0<=83)||(LA32_0>=85 && LA32_0<=86)||(LA32_0>=89 && LA32_0<=90)||LA32_0==93||(LA32_0>=95 && LA32_0<=151)||(LA32_0>=160 && LA32_0<=161)) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:2: ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2585:2: ( (lv_args_37_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2586:1: (lv_args_37_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2586:1: (lv_args_37_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2587:3: lv_args_37_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5696);
                            lv_args_37_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_37_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2603:2: (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==66) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2603:4: otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) )
                            	    {
                            	    otherlv_38=(Token)match(input,66,FOLLOW_66_in_ruleAtomicExpr5709); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_38, grammarAccess.getAtomicExprAccess().getCommaKeyword_9_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2607:1: ( (lv_args_39_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2608:1: (lv_args_39_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2608:1: (lv_args_39_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2609:3: lv_args_39_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_9_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5730);
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


                            	    }
                            	    break;

                            	default :
                            	    break loop31;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_40=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr5746); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_40, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_9_4());
                          
                    }

                    }


                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2630:6: ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2630:6: ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2630:7: () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2630:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2631:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getFnCallExprAction_10_0(),
                                  current);
                          
                    }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2636:2: ( (otherlv_42= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2637:1: (otherlv_42= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2637:1: (otherlv_42= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2638:3: otherlv_42= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getAtomicExprRule());
                      	        }
                              
                    }
                    otherlv_42=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAtomicExpr5783); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_42, grammarAccess.getAtomicExprAccess().getFnFunctionDefinitionCrossReference_10_1_0()); 
                      	
                    }

                    }


                    }

                    otherlv_43=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr5795); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_43, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_10_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2653:1: ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( ((LA34_0>=RULE_ID && LA34_0<=RULE_INTEGER_LIT)||LA34_0==15||LA34_0==41||LA34_0==65||LA34_0==79||(LA34_0>=82 && LA34_0<=83)||(LA34_0>=85 && LA34_0<=86)||(LA34_0>=89 && LA34_0<=90)||LA34_0==93||(LA34_0>=95 && LA34_0<=151)||(LA34_0>=160 && LA34_0<=161)) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2653:2: ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2653:2: ( (lv_args_44_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2654:1: (lv_args_44_0= ruleExpr )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2654:1: (lv_args_44_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2655:3: lv_args_44_0= ruleExpr
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5817);
                            lv_args_44_0=ruleExpr();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"args",
                                      		lv_args_44_0, 
                                      		"Expr");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2671:2: (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )*
                            loop33:
                            do {
                                int alt33=2;
                                int LA33_0 = input.LA(1);

                                if ( (LA33_0==66) ) {
                                    alt33=1;
                                }


                                switch (alt33) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2671:4: otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) )
                            	    {
                            	    otherlv_45=(Token)match(input,66,FOLLOW_66_in_ruleAtomicExpr5830); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_45, grammarAccess.getAtomicExprAccess().getCommaKeyword_10_3_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2675:1: ( (lv_args_46_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2676:1: (lv_args_46_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2676:1: (lv_args_46_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2677:3: lv_args_46_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsExprParserRuleCall_10_3_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5851);
                            	    lv_args_46_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_46_0, 
                            	              		"Expr");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop33;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_47=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr5867); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_47, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_10_4());
                          
                    }

                    }


                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2698:6: (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2698:6: (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2698:8: otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}'
                    {
                    otherlv_48=(Token)match(input,15,FOLLOW_15_in_ruleAtomicExpr5887); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_48, grammarAccess.getAtomicExprAccess().getLeftCurlyBracketKeyword_11_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAtomicExprAccess().getExprParserRuleCall_11_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5909);
                    this_Expr_49=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Expr_49; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:1: ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) )
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==91) ) {
                        alt38=1;
                    }
                    else if ( (LA38_0==16||LA38_0==66) ) {
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:2: ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:2: ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:3: () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )?
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:3: ()
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2712:5: 
                            {
                            if ( state.backtracking==0 ) {

                                      current = forceCreateModelElementAndSet(
                                          grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0(),
                                          current);
                                  
                            }

                            }

                            otherlv_51=(Token)match(input,91,FOLLOW_91_in_ruleAtomicExpr5931); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_51, grammarAccess.getAtomicExprAccess().getForKeyword_11_2_0_1());
                                  
                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2721:1: (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+
                            int cnt35=0;
                            loop35:
                            do {
                                int alt35=2;
                                int LA35_0 = input.LA(1);

                                if ( (LA35_0==65) ) {
                                    alt35=1;
                                }


                                switch (alt35) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2721:3: otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')'
                            	    {
                            	    otherlv_52=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr5944); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_52, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_11_2_0_2_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2725:1: ( (lv_args_53_0= ruleArg ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2726:1: (lv_args_53_0= ruleArg )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2726:1: (lv_args_53_0= ruleArg )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2727:3: lv_args_53_0= ruleArg
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getArgsArgParserRuleCall_11_2_0_2_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr5965);
                            	    lv_args_53_0=ruleArg();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"args",
                            	              		lv_args_53_0, 
                            	              		"Arg");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }

                            	    otherlv_54=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr5977); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_54, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_11_2_0_2_2());
                            	          
                            	    }

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt35 >= 1 ) break loop35;
                            	    if (state.backtracking>0) {state.failed=true; return current;}
                                        EarlyExitException eee =
                                            new EarlyExitException(35, input);
                                        throw eee;
                                }
                                cnt35++;
                            } while (true);

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2747:3: (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==92) ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2747:5: otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) )
                                    {
                                    otherlv_55=(Token)match(input,92,FOLLOW_92_in_ruleAtomicExpr5992); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_55, grammarAccess.getAtomicExprAccess().getVerticalLineKeyword_11_2_0_3_0());
                                          
                                    }
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2751:1: ( (lv_filter_56_0= ruleExpr ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2752:1: (lv_filter_56_0= ruleExpr )
                                    {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2752:1: (lv_filter_56_0= ruleExpr )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2753:3: lv_filter_56_0= ruleExpr
                                    {
                                    if ( state.backtracking==0 ) {
                                       
                                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getFilterExprParserRuleCall_11_2_0_3_1_0()); 
                                      	    
                                    }
                                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr6013);
                                    lv_filter_56_0=ruleExpr();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                                      	        }
                                             		set(
                                             			current, 
                                             			"filter",
                                              		lv_filter_56_0, 
                                              		"Expr");
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
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2770:6: ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* )
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2770:6: ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2770:7: () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )*
                            {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2770:7: ()
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2771:5: 
                            {
                            if ( state.backtracking==0 ) {

                                      current = forceCreateModelElementAndAdd(
                                          grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0(),
                                          current);
                                  
                            }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2776:2: (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )*
                            loop37:
                            do {
                                int alt37=2;
                                int LA37_0 = input.LA(1);

                                if ( (LA37_0==66) ) {
                                    alt37=1;
                                }


                                switch (alt37) {
                            	case 1 :
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2776:4: otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) )
                            	    {
                            	    otherlv_58=(Token)match(input,66,FOLLOW_66_in_ruleAtomicExpr6045); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_58, grammarAccess.getAtomicExprAccess().getCommaKeyword_11_2_1_1_0());
                            	          
                            	    }
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2780:1: ( (lv_exprs_59_0= ruleExpr ) )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2781:1: (lv_exprs_59_0= ruleExpr )
                            	    {
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2781:1: (lv_exprs_59_0= ruleExpr )
                            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2782:3: lv_exprs_59_0= ruleExpr
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprsExprParserRuleCall_11_2_1_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr6066);
                            	    lv_exprs_59_0=ruleExpr();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"exprs",
                            	              		lv_exprs_59_0, 
                            	              		"Expr");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

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
                            break;

                    }

                    otherlv_60=(Token)match(input,16,FOLLOW_16_in_ruleAtomicExpr6082); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_60, grammarAccess.getAtomicExprAccess().getRightCurlyBracketKeyword_11_3());
                          
                    }

                    }


                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2803:6: ( () otherlv_62= '{' otherlv_63= '}' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2803:6: ( () otherlv_62= '{' otherlv_63= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2803:7: () otherlv_62= '{' otherlv_63= '}'
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2803:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2804:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getSetExprAction_12_0(),
                                  current);
                          
                    }

                    }

                    otherlv_62=(Token)match(input,15,FOLLOW_15_in_ruleAtomicExpr6111); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_62, grammarAccess.getAtomicExprAccess().getLeftCurlyBracketKeyword_12_1());
                          
                    }
                    otherlv_63=(Token)match(input,16,FOLLOW_16_in_ruleAtomicExpr6123); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_63, grammarAccess.getAtomicExprAccess().getRightCurlyBracketKeyword_12_2());
                          
                    }

                    }


                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2818:6: ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2818:6: ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2818:7: () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2818:7: ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2819:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getAtomicExprAccess().getLetExprAction_13_0(),
                                  current);
                          
                    }

                    }

                    otherlv_65=(Token)match(input,93,FOLLOW_93_in_ruleAtomicExpr6152); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_65, grammarAccess.getAtomicExprAccess().getLetKeyword_13_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2828:1: ( (lv_binding_66_0= ruleLetBinding ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2829:1: (lv_binding_66_0= ruleLetBinding )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2829:1: (lv_binding_66_0= ruleLetBinding )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2830:3: lv_binding_66_0= ruleLetBinding
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getBindingLetBindingParserRuleCall_13_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleLetBinding_in_ruleAtomicExpr6173);
                    lv_binding_66_0=ruleLetBinding();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"binding",
                              		lv_binding_66_0, 
                              		"LetBinding");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_67=(Token)match(input,94,FOLLOW_94_in_ruleAtomicExpr6185); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_67, grammarAccess.getAtomicExprAccess().getSemicolonKeyword_13_3());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2850:1: ( (lv_expr_68_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2851:1: (lv_expr_68_0= ruleExpr )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2851:1: (lv_expr_68_0= ruleExpr )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2852:3: lv_expr_68_0= ruleExpr
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getAtomicExprAccess().getExprExprParserRuleCall_13_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr6206);
                    lv_expr_68_0=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getAtomicExprRule());
                      	        }
                             		set(
                             			current, 
                             			"expr",
                              		lv_expr_68_0, 
                              		"Expr");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2869:6: (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2869:6: (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2869:8: otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')'
                    {
                    otherlv_69=(Token)match(input,65,FOLLOW_65_in_ruleAtomicExpr6226); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_69, grammarAccess.getAtomicExprAccess().getLeftParenthesisKeyword_14_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAtomicExprAccess().getExprParserRuleCall_14_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr6248);
                    this_Expr_70=ruleExpr();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Expr_70; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_71=(Token)match(input,67,FOLLOW_67_in_ruleAtomicExpr6259); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_71, grammarAccess.getAtomicExprAccess().getRightParenthesisKeyword_14_2());
                          
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2894:1: entryRuleLetBinding returns [EObject current=null] : iv_ruleLetBinding= ruleLetBinding EOF ;
    public final EObject entryRuleLetBinding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetBinding = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2895:2: (iv_ruleLetBinding= ruleLetBinding EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2896:2: iv_ruleLetBinding= ruleLetBinding EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetBindingRule()); 
            }
            pushFollow(FOLLOW_ruleLetBinding_in_entryRuleLetBinding6296);
            iv_ruleLetBinding=ruleLetBinding();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetBinding; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetBinding6306); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2903:1: ruleLetBinding returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleLetBinding() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2906:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2907:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2907:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2907:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2907:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2908:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2908:1: (lv_name_0_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2909:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLetBinding6348); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleLetBinding6365); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLetBindingAccess().getColonKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2929:1: ( (lv_type_2_0= ruleType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2930:1: (lv_type_2_0= ruleType )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2930:1: (lv_type_2_0= ruleType )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2931:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetBindingAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleLetBinding6386);
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

            otherlv_3=(Token)match(input,64,FOLLOW_64_in_ruleLetBinding6398); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getLetBindingAccess().getEqualsSignKeyword_3());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2951:1: ( (lv_expr_4_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2952:1: (lv_expr_4_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2952:1: (lv_expr_4_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2953:3: lv_expr_4_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetBindingAccess().getExprExprParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleLetBinding6419);
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


    // $ANTLR start "entryRuleBuiltInFn"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2977:1: entryRuleBuiltInFn returns [String current=null] : iv_ruleBuiltInFn= ruleBuiltInFn EOF ;
    public final String entryRuleBuiltInFn() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltInFn = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2978:2: (iv_ruleBuiltInFn= ruleBuiltInFn EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2979:2: iv_ruleBuiltInFn= ruleBuiltInFn EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBuiltInFnRule()); 
            }
            pushFollow(FOLLOW_ruleBuiltInFn_in_entryRuleBuiltInFn6456);
            iv_ruleBuiltInFn=ruleBuiltInFn();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBuiltInFn.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBuiltInFn6467); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBuiltInFn"


    // $ANTLR start "ruleBuiltInFn"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2986:1: ruleBuiltInFn returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'has_property' | kw= 'property' | kw= 'property_member' | kw= 'has_parent' | kw= 'parent' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_in_array' | kw= 'has_prototypes' | kw= 'has_modes' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_system' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_memory' | kw= 'is_thread' | kw= 'is_process' | kw= 'is_data' | kw= 'is_subprogram' | kw= 'is_of_type' | kw= 'is_bound_to' | kw= 'has_member' | kw= 'features' | kw= 'connections' | kw= 'enumerated_values' | kw= 'subcomponents' | kw= 'source' | kw= 'destination' | kw= 'direction' | kw= 'is_event_port' | kw= 'is_abstract_feature' | kw= 'is_data_port' | kw= 'is_port' | kw= 'is_data_access' | kw= 'lower_bound' | kw= 'upper_bound' | kw= 'member' | kw= 'sum' | kw= 'union' | kw= 'length' | kw= 'size' | kw= 'intersect' | kw= 'instance' | kw= 'instances' | kw= 'debug' | kw= 'analysis' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'flow_source' | kw= 'flow_destination' | kw= 'flow_elements' | kw= 'flow_specifications' | kw= 'end_to_end_flows' ) ;
    public final AntlrDatatypeRuleToken ruleBuiltInFn() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2989:28: ( (kw= 'has_property' | kw= 'property' | kw= 'property_member' | kw= 'has_parent' | kw= 'parent' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_in_array' | kw= 'has_prototypes' | kw= 'has_modes' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_system' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_memory' | kw= 'is_thread' | kw= 'is_process' | kw= 'is_data' | kw= 'is_subprogram' | kw= 'is_of_type' | kw= 'is_bound_to' | kw= 'has_member' | kw= 'features' | kw= 'connections' | kw= 'enumerated_values' | kw= 'subcomponents' | kw= 'source' | kw= 'destination' | kw= 'direction' | kw= 'is_event_port' | kw= 'is_abstract_feature' | kw= 'is_data_port' | kw= 'is_port' | kw= 'is_data_access' | kw= 'lower_bound' | kw= 'upper_bound' | kw= 'member' | kw= 'sum' | kw= 'union' | kw= 'length' | kw= 'size' | kw= 'intersect' | kw= 'instance' | kw= 'instances' | kw= 'debug' | kw= 'analysis' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'flow_source' | kw= 'flow_destination' | kw= 'flow_elements' | kw= 'flow_specifications' | kw= 'end_to_end_flows' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2990:1: (kw= 'has_property' | kw= 'property' | kw= 'property_member' | kw= 'has_parent' | kw= 'parent' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_in_array' | kw= 'has_prototypes' | kw= 'has_modes' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_system' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_memory' | kw= 'is_thread' | kw= 'is_process' | kw= 'is_data' | kw= 'is_subprogram' | kw= 'is_of_type' | kw= 'is_bound_to' | kw= 'has_member' | kw= 'features' | kw= 'connections' | kw= 'enumerated_values' | kw= 'subcomponents' | kw= 'source' | kw= 'destination' | kw= 'direction' | kw= 'is_event_port' | kw= 'is_abstract_feature' | kw= 'is_data_port' | kw= 'is_port' | kw= 'is_data_access' | kw= 'lower_bound' | kw= 'upper_bound' | kw= 'member' | kw= 'sum' | kw= 'union' | kw= 'length' | kw= 'size' | kw= 'intersect' | kw= 'instance' | kw= 'instances' | kw= 'debug' | kw= 'analysis' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'flow_source' | kw= 'flow_destination' | kw= 'flow_elements' | kw= 'flow_specifications' | kw= 'end_to_end_flows' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2990:1: (kw= 'has_property' | kw= 'property' | kw= 'property_member' | kw= 'has_parent' | kw= 'parent' | kw= 'name' | kw= 'type' | kw= 'has_type' | kw= 'is_in_array' | kw= 'has_prototypes' | kw= 'has_modes' | kw= 'is_processor' | kw= 'is_virtual_processor' | kw= 'is_system' | kw= 'is_bus' | kw= 'is_virtual_bus' | kw= 'is_device' | kw= 'is_memory' | kw= 'is_thread' | kw= 'is_process' | kw= 'is_data' | kw= 'is_subprogram' | kw= 'is_of_type' | kw= 'is_bound_to' | kw= 'has_member' | kw= 'features' | kw= 'connections' | kw= 'enumerated_values' | kw= 'subcomponents' | kw= 'source' | kw= 'destination' | kw= 'direction' | kw= 'is_event_port' | kw= 'is_abstract_feature' | kw= 'is_data_port' | kw= 'is_port' | kw= 'is_data_access' | kw= 'lower_bound' | kw= 'upper_bound' | kw= 'member' | kw= 'sum' | kw= 'union' | kw= 'length' | kw= 'size' | kw= 'intersect' | kw= 'instance' | kw= 'instances' | kw= 'debug' | kw= 'analysis' | kw= 'receive_error' | kw= 'contain_error' | kw= 'propagate_error' | kw= 'error_state_reachable' | kw= 'flow_source' | kw= 'flow_destination' | kw= 'flow_elements' | kw= 'flow_specifications' | kw= 'end_to_end_flows' )
            int alt40=58;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt40=1;
                }
                break;
            case 41:
                {
                alt40=2;
                }
                break;
            case 96:
                {
                alt40=3;
                }
                break;
            case 97:
                {
                alt40=4;
                }
                break;
            case 98:
                {
                alt40=5;
                }
                break;
            case 99:
                {
                alt40=6;
                }
                break;
            case 100:
                {
                alt40=7;
                }
                break;
            case 101:
                {
                alt40=8;
                }
                break;
            case 102:
                {
                alt40=9;
                }
                break;
            case 103:
                {
                alt40=10;
                }
                break;
            case 104:
                {
                alt40=11;
                }
                break;
            case 105:
                {
                alt40=12;
                }
                break;
            case 106:
                {
                alt40=13;
                }
                break;
            case 107:
                {
                alt40=14;
                }
                break;
            case 108:
                {
                alt40=15;
                }
                break;
            case 109:
                {
                alt40=16;
                }
                break;
            case 110:
                {
                alt40=17;
                }
                break;
            case 111:
                {
                alt40=18;
                }
                break;
            case 112:
                {
                alt40=19;
                }
                break;
            case 113:
                {
                alt40=20;
                }
                break;
            case 114:
                {
                alt40=21;
                }
                break;
            case 115:
                {
                alt40=22;
                }
                break;
            case 116:
                {
                alt40=23;
                }
                break;
            case 117:
                {
                alt40=24;
                }
                break;
            case 118:
                {
                alt40=25;
                }
                break;
            case 119:
                {
                alt40=26;
                }
                break;
            case 120:
                {
                alt40=27;
                }
                break;
            case 121:
                {
                alt40=28;
                }
                break;
            case 122:
                {
                alt40=29;
                }
                break;
            case 123:
                {
                alt40=30;
                }
                break;
            case 124:
                {
                alt40=31;
                }
                break;
            case 125:
                {
                alt40=32;
                }
                break;
            case 126:
                {
                alt40=33;
                }
                break;
            case 127:
                {
                alt40=34;
                }
                break;
            case 128:
                {
                alt40=35;
                }
                break;
            case 129:
                {
                alt40=36;
                }
                break;
            case 130:
                {
                alt40=37;
                }
                break;
            case 131:
                {
                alt40=38;
                }
                break;
            case 132:
                {
                alt40=39;
                }
                break;
            case 133:
                {
                alt40=40;
                }
                break;
            case 134:
                {
                alt40=41;
                }
                break;
            case 135:
                {
                alt40=42;
                }
                break;
            case 136:
                {
                alt40=43;
                }
                break;
            case 137:
                {
                alt40=44;
                }
                break;
            case 138:
                {
                alt40=45;
                }
                break;
            case 139:
                {
                alt40=46;
                }
                break;
            case 140:
                {
                alt40=47;
                }
                break;
            case 141:
                {
                alt40=48;
                }
                break;
            case 142:
                {
                alt40=49;
                }
                break;
            case 143:
                {
                alt40=50;
                }
                break;
            case 144:
                {
                alt40=51;
                }
                break;
            case 145:
                {
                alt40=52;
                }
                break;
            case 146:
                {
                alt40=53;
                }
                break;
            case 147:
                {
                alt40=54;
                }
                break;
            case 148:
                {
                alt40=55;
                }
                break;
            case 149:
                {
                alt40=56;
                }
                break;
            case 150:
                {
                alt40=57;
                }
                break;
            case 151:
                {
                alt40=58;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2991:2: kw= 'has_property'
                    {
                    kw=(Token)match(input,95,FOLLOW_95_in_ruleBuiltInFn6505); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_propertyKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2998:2: kw= 'property'
                    {
                    kw=(Token)match(input,41,FOLLOW_41_in_ruleBuiltInFn6524); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getPropertyKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3005:2: kw= 'property_member'
                    {
                    kw=(Token)match(input,96,FOLLOW_96_in_ruleBuiltInFn6543); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getProperty_memberKeyword_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3012:2: kw= 'has_parent'
                    {
                    kw=(Token)match(input,97,FOLLOW_97_in_ruleBuiltInFn6562); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_parentKeyword_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3019:2: kw= 'parent'
                    {
                    kw=(Token)match(input,98,FOLLOW_98_in_ruleBuiltInFn6581); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getParentKeyword_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3026:2: kw= 'name'
                    {
                    kw=(Token)match(input,99,FOLLOW_99_in_ruleBuiltInFn6600); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getNameKeyword_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3033:2: kw= 'type'
                    {
                    kw=(Token)match(input,100,FOLLOW_100_in_ruleBuiltInFn6619); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getTypeKeyword_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3040:2: kw= 'has_type'
                    {
                    kw=(Token)match(input,101,FOLLOW_101_in_ruleBuiltInFn6638); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_typeKeyword_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3047:2: kw= 'is_in_array'
                    {
                    kw=(Token)match(input,102,FOLLOW_102_in_ruleBuiltInFn6657); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_in_arrayKeyword_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3054:2: kw= 'has_prototypes'
                    {
                    kw=(Token)match(input,103,FOLLOW_103_in_ruleBuiltInFn6676); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_prototypesKeyword_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3061:2: kw= 'has_modes'
                    {
                    kw=(Token)match(input,104,FOLLOW_104_in_ruleBuiltInFn6695); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_modesKeyword_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:2: kw= 'is_processor'
                    {
                    kw=(Token)match(input,105,FOLLOW_105_in_ruleBuiltInFn6714); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_processorKeyword_11()); 
                          
                    }

                    }
                    break;
                case 13 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3075:2: kw= 'is_virtual_processor'
                    {
                    kw=(Token)match(input,106,FOLLOW_106_in_ruleBuiltInFn6733); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_virtual_processorKeyword_12()); 
                          
                    }

                    }
                    break;
                case 14 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3082:2: kw= 'is_system'
                    {
                    kw=(Token)match(input,107,FOLLOW_107_in_ruleBuiltInFn6752); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_systemKeyword_13()); 
                          
                    }

                    }
                    break;
                case 15 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3089:2: kw= 'is_bus'
                    {
                    kw=(Token)match(input,108,FOLLOW_108_in_ruleBuiltInFn6771); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_busKeyword_14()); 
                          
                    }

                    }
                    break;
                case 16 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3096:2: kw= 'is_virtual_bus'
                    {
                    kw=(Token)match(input,109,FOLLOW_109_in_ruleBuiltInFn6790); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_virtual_busKeyword_15()); 
                          
                    }

                    }
                    break;
                case 17 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3103:2: kw= 'is_device'
                    {
                    kw=(Token)match(input,110,FOLLOW_110_in_ruleBuiltInFn6809); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_deviceKeyword_16()); 
                          
                    }

                    }
                    break;
                case 18 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3110:2: kw= 'is_memory'
                    {
                    kw=(Token)match(input,111,FOLLOW_111_in_ruleBuiltInFn6828); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_memoryKeyword_17()); 
                          
                    }

                    }
                    break;
                case 19 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3117:2: kw= 'is_thread'
                    {
                    kw=(Token)match(input,112,FOLLOW_112_in_ruleBuiltInFn6847); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_threadKeyword_18()); 
                          
                    }

                    }
                    break;
                case 20 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3124:2: kw= 'is_process'
                    {
                    kw=(Token)match(input,113,FOLLOW_113_in_ruleBuiltInFn6866); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_processKeyword_19()); 
                          
                    }

                    }
                    break;
                case 21 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3131:2: kw= 'is_data'
                    {
                    kw=(Token)match(input,114,FOLLOW_114_in_ruleBuiltInFn6885); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_dataKeyword_20()); 
                          
                    }

                    }
                    break;
                case 22 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3138:2: kw= 'is_subprogram'
                    {
                    kw=(Token)match(input,115,FOLLOW_115_in_ruleBuiltInFn6904); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_subprogramKeyword_21()); 
                          
                    }

                    }
                    break;
                case 23 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3145:2: kw= 'is_of_type'
                    {
                    kw=(Token)match(input,116,FOLLOW_116_in_ruleBuiltInFn6923); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_of_typeKeyword_22()); 
                          
                    }

                    }
                    break;
                case 24 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3152:2: kw= 'is_bound_to'
                    {
                    kw=(Token)match(input,117,FOLLOW_117_in_ruleBuiltInFn6942); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_bound_toKeyword_23()); 
                          
                    }

                    }
                    break;
                case 25 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3159:2: kw= 'has_member'
                    {
                    kw=(Token)match(input,118,FOLLOW_118_in_ruleBuiltInFn6961); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getHas_memberKeyword_24()); 
                          
                    }

                    }
                    break;
                case 26 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3166:2: kw= 'features'
                    {
                    kw=(Token)match(input,119,FOLLOW_119_in_ruleBuiltInFn6980); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getFeaturesKeyword_25()); 
                          
                    }

                    }
                    break;
                case 27 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3173:2: kw= 'connections'
                    {
                    kw=(Token)match(input,120,FOLLOW_120_in_ruleBuiltInFn6999); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getConnectionsKeyword_26()); 
                          
                    }

                    }
                    break;
                case 28 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3180:2: kw= 'enumerated_values'
                    {
                    kw=(Token)match(input,121,FOLLOW_121_in_ruleBuiltInFn7018); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getEnumerated_valuesKeyword_27()); 
                          
                    }

                    }
                    break;
                case 29 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3187:2: kw= 'subcomponents'
                    {
                    kw=(Token)match(input,122,FOLLOW_122_in_ruleBuiltInFn7037); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getSubcomponentsKeyword_28()); 
                          
                    }

                    }
                    break;
                case 30 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3194:2: kw= 'source'
                    {
                    kw=(Token)match(input,123,FOLLOW_123_in_ruleBuiltInFn7056); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getSourceKeyword_29()); 
                          
                    }

                    }
                    break;
                case 31 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3201:2: kw= 'destination'
                    {
                    kw=(Token)match(input,124,FOLLOW_124_in_ruleBuiltInFn7075); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getDestinationKeyword_30()); 
                          
                    }

                    }
                    break;
                case 32 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3208:2: kw= 'direction'
                    {
                    kw=(Token)match(input,125,FOLLOW_125_in_ruleBuiltInFn7094); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getDirectionKeyword_31()); 
                          
                    }

                    }
                    break;
                case 33 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3215:2: kw= 'is_event_port'
                    {
                    kw=(Token)match(input,126,FOLLOW_126_in_ruleBuiltInFn7113); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_event_portKeyword_32()); 
                          
                    }

                    }
                    break;
                case 34 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3222:2: kw= 'is_abstract_feature'
                    {
                    kw=(Token)match(input,127,FOLLOW_127_in_ruleBuiltInFn7132); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_abstract_featureKeyword_33()); 
                          
                    }

                    }
                    break;
                case 35 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3229:2: kw= 'is_data_port'
                    {
                    kw=(Token)match(input,128,FOLLOW_128_in_ruleBuiltInFn7151); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_data_portKeyword_34()); 
                          
                    }

                    }
                    break;
                case 36 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3236:2: kw= 'is_port'
                    {
                    kw=(Token)match(input,129,FOLLOW_129_in_ruleBuiltInFn7170); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_portKeyword_35()); 
                          
                    }

                    }
                    break;
                case 37 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3243:2: kw= 'is_data_access'
                    {
                    kw=(Token)match(input,130,FOLLOW_130_in_ruleBuiltInFn7189); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIs_data_accessKeyword_36()); 
                          
                    }

                    }
                    break;
                case 38 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3250:2: kw= 'lower_bound'
                    {
                    kw=(Token)match(input,131,FOLLOW_131_in_ruleBuiltInFn7208); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getLower_boundKeyword_37()); 
                          
                    }

                    }
                    break;
                case 39 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3257:2: kw= 'upper_bound'
                    {
                    kw=(Token)match(input,132,FOLLOW_132_in_ruleBuiltInFn7227); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getUpper_boundKeyword_38()); 
                          
                    }

                    }
                    break;
                case 40 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3264:2: kw= 'member'
                    {
                    kw=(Token)match(input,133,FOLLOW_133_in_ruleBuiltInFn7246); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getMemberKeyword_39()); 
                          
                    }

                    }
                    break;
                case 41 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3271:2: kw= 'sum'
                    {
                    kw=(Token)match(input,134,FOLLOW_134_in_ruleBuiltInFn7265); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getSumKeyword_40()); 
                          
                    }

                    }
                    break;
                case 42 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3278:2: kw= 'union'
                    {
                    kw=(Token)match(input,135,FOLLOW_135_in_ruleBuiltInFn7284); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getUnionKeyword_41()); 
                          
                    }

                    }
                    break;
                case 43 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3285:2: kw= 'length'
                    {
                    kw=(Token)match(input,136,FOLLOW_136_in_ruleBuiltInFn7303); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getLengthKeyword_42()); 
                          
                    }

                    }
                    break;
                case 44 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3292:2: kw= 'size'
                    {
                    kw=(Token)match(input,137,FOLLOW_137_in_ruleBuiltInFn7322); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getSizeKeyword_43()); 
                          
                    }

                    }
                    break;
                case 45 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3299:2: kw= 'intersect'
                    {
                    kw=(Token)match(input,138,FOLLOW_138_in_ruleBuiltInFn7341); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getIntersectKeyword_44()); 
                          
                    }

                    }
                    break;
                case 46 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3306:2: kw= 'instance'
                    {
                    kw=(Token)match(input,139,FOLLOW_139_in_ruleBuiltInFn7360); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getInstanceKeyword_45()); 
                          
                    }

                    }
                    break;
                case 47 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3313:2: kw= 'instances'
                    {
                    kw=(Token)match(input,140,FOLLOW_140_in_ruleBuiltInFn7379); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getInstancesKeyword_46()); 
                          
                    }

                    }
                    break;
                case 48 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3320:2: kw= 'debug'
                    {
                    kw=(Token)match(input,141,FOLLOW_141_in_ruleBuiltInFn7398); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getDebugKeyword_47()); 
                          
                    }

                    }
                    break;
                case 49 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3327:2: kw= 'analysis'
                    {
                    kw=(Token)match(input,142,FOLLOW_142_in_ruleBuiltInFn7417); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getAnalysisKeyword_48()); 
                          
                    }

                    }
                    break;
                case 50 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3334:2: kw= 'receive_error'
                    {
                    kw=(Token)match(input,143,FOLLOW_143_in_ruleBuiltInFn7436); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getReceive_errorKeyword_49()); 
                          
                    }

                    }
                    break;
                case 51 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3341:2: kw= 'contain_error'
                    {
                    kw=(Token)match(input,144,FOLLOW_144_in_ruleBuiltInFn7455); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getContain_errorKeyword_50()); 
                          
                    }

                    }
                    break;
                case 52 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3348:2: kw= 'propagate_error'
                    {
                    kw=(Token)match(input,145,FOLLOW_145_in_ruleBuiltInFn7474); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getPropagate_errorKeyword_51()); 
                          
                    }

                    }
                    break;
                case 53 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3355:2: kw= 'error_state_reachable'
                    {
                    kw=(Token)match(input,146,FOLLOW_146_in_ruleBuiltInFn7493); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getError_state_reachableKeyword_52()); 
                          
                    }

                    }
                    break;
                case 54 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3362:2: kw= 'flow_source'
                    {
                    kw=(Token)match(input,147,FOLLOW_147_in_ruleBuiltInFn7512); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getFlow_sourceKeyword_53()); 
                          
                    }

                    }
                    break;
                case 55 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3369:2: kw= 'flow_destination'
                    {
                    kw=(Token)match(input,148,FOLLOW_148_in_ruleBuiltInFn7531); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getFlow_destinationKeyword_54()); 
                          
                    }

                    }
                    break;
                case 56 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3376:2: kw= 'flow_elements'
                    {
                    kw=(Token)match(input,149,FOLLOW_149_in_ruleBuiltInFn7550); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getFlow_elementsKeyword_55()); 
                          
                    }

                    }
                    break;
                case 57 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3383:2: kw= 'flow_specifications'
                    {
                    kw=(Token)match(input,150,FOLLOW_150_in_ruleBuiltInFn7569); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getFlow_specificationsKeyword_56()); 
                          
                    }

                    }
                    break;
                case 58 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3390:2: kw= 'end_to_end_flows'
                    {
                    kw=(Token)match(input,151,FOLLOW_151_in_ruleBuiltInFn7588); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getBuiltInFnAccess().getEnd_to_end_flowsKeyword_57()); 
                          
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
    // $ANTLR end "ruleBuiltInFn"


    // $ANTLR start "entryRuleRealTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3403:1: entryRuleRealTerm returns [EObject current=null] : iv_ruleRealTerm= ruleRealTerm EOF ;
    public final EObject entryRuleRealTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3404:2: (iv_ruleRealTerm= ruleRealTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3405:2: iv_ruleRealTerm= ruleRealTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealTermRule()); 
            }
            pushFollow(FOLLOW_ruleRealTerm_in_entryRuleRealTerm7628);
            iv_ruleRealTerm=ruleRealTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealTerm7638); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3412:1: ruleRealTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleRealTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3415:28: ( ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3416:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3416:1: ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3416:2: ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3416:2: ( (lv_value_0_0= ruleUnsignedReal ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3417:1: (lv_value_0_0= ruleUnsignedReal )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3417:1: (lv_value_0_0= ruleUnsignedReal )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3418:3: lv_value_0_0= ruleUnsignedReal
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getRealTermAccess().getValueUnsignedRealParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_ruleRealTerm7684);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3434:2: ( (otherlv_1= RULE_ID ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_ID) ) {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==EOF||LA41_1==RULE_ID||(LA41_1>=16 && LA41_1<=18)||LA41_1==64||(LA41_1>=66 && LA41_1<=68)||(LA41_1>=70 && LA41_1<=81)||(LA41_1>=87 && LA41_1<=88)||LA41_1==91||LA41_1==94||LA41_1==152||LA41_1==155||LA41_1==157||(LA41_1>=167 && LA41_1<=168)) ) {
                    alt41=1;
                }
            }
            switch (alt41) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3435:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3435:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3436:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getRealTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRealTerm7704); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3455:1: entryRuleUnsignedReal returns [String current=null] : iv_ruleUnsignedReal= ruleUnsignedReal EOF ;
    public final String entryRuleUnsignedReal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedReal = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3456:2: (iv_ruleUnsignedReal= ruleUnsignedReal EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3457:2: iv_ruleUnsignedReal= ruleUnsignedReal EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedRealRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal7742);
            iv_ruleUnsignedReal=ruleUnsignedReal();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedReal.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedReal7753); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3464:1: ruleUnsignedReal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_REAL_LIT_0= RULE_REAL_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedReal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3467:28: (this_REAL_LIT_0= RULE_REAL_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3468:5: this_REAL_LIT_0= RULE_REAL_LIT
            {
            this_REAL_LIT_0=(Token)match(input,RULE_REAL_LIT,FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal7792); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3483:1: entryRuleIntegerTerm returns [EObject current=null] : iv_ruleIntegerTerm= ruleIntegerTerm EOF ;
    public final EObject entryRuleIntegerTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3484:2: (iv_ruleIntegerTerm= ruleIntegerTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3485:2: iv_ruleIntegerTerm= ruleIntegerTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerTermRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm7836);
            iv_ruleIntegerTerm=ruleIntegerTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerTerm7846); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3492:1: ruleIntegerTerm returns [EObject current=null] : ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleIntegerTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3495:28: ( ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3496:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3496:1: ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3496:2: ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3496:2: ( (lv_value_0_0= ruleUnsignedInt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3497:1: (lv_value_0_0= ruleUnsignedInt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3497:1: (lv_value_0_0= ruleUnsignedInt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3498:3: lv_value_0_0= ruleUnsignedInt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIntegerTermAccess().getValueUnsignedIntParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm7892);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3514:2: ( (otherlv_1= RULE_ID ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_ID) ) {
                int LA42_1 = input.LA(2);

                if ( (LA42_1==EOF||LA42_1==RULE_ID||(LA42_1>=16 && LA42_1<=18)||LA42_1==64||(LA42_1>=66 && LA42_1<=68)||(LA42_1>=70 && LA42_1<=81)||(LA42_1>=87 && LA42_1<=88)||LA42_1==91||LA42_1==94||LA42_1==152||LA42_1==155||LA42_1==157||(LA42_1>=167 && LA42_1<=168)) ) {
                    alt42=1;
                }
            }
            switch (alt42) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3515:1: (otherlv_1= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3515:1: (otherlv_1= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3516:3: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getIntegerTermRule());
                      	        }
                              
                    }
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIntegerTerm7912); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3535:1: entryRuleUnsignedInt returns [String current=null] : iv_ruleUnsignedInt= ruleUnsignedInt EOF ;
    public final String entryRuleUnsignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedInt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3536:2: (iv_ruleUnsignedInt= ruleUnsignedInt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3537:2: iv_ruleUnsignedInt= ruleUnsignedInt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnsignedIntRule()); 
            }
            pushFollow(FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt7950);
            iv_ruleUnsignedInt=ruleUnsignedInt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnsignedInt.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnsignedInt7961); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3544:1: ruleUnsignedInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3547:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3548:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt8000); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3563:1: entryRuleResoluteSubclause returns [EObject current=null] : iv_ruleResoluteSubclause= ruleResoluteSubclause EOF ;
    public final EObject entryRuleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteSubclause = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3564:2: (iv_ruleResoluteSubclause= ruleResoluteSubclause EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3565:2: iv_ruleResoluteSubclause= ruleResoluteSubclause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getResoluteSubclauseRule()); 
            }
            pushFollow(FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause8044);
            iv_ruleResoluteSubclause=ruleResoluteSubclause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleResoluteSubclause; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleResoluteSubclause8054); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3572:1: ruleResoluteSubclause returns [EObject current=null] : ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) ;
    public final EObject ruleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject lv_proves_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3575:28: ( ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3576:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3576:1: ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3576:2: () ( (lv_proves_1_0= ruleProveStatement ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3576:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3577:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getResoluteSubclauseAccess().getResoluteSubclauseAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3582:2: ( (lv_proves_1_0= ruleProveStatement ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==152) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3583:1: (lv_proves_1_0= ruleProveStatement )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3583:1: (lv_proves_1_0= ruleProveStatement )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3584:3: lv_proves_1_0= ruleProveStatement
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getResoluteSubclauseAccess().getProvesProveStatementParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleProveStatement_in_ruleResoluteSubclause8109);
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
            	    break loop43;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3608:1: entryRuleNestedDotID returns [EObject current=null] : iv_ruleNestedDotID= ruleNestedDotID EOF ;
    public final EObject entryRuleNestedDotID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedDotID = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3609:2: (iv_ruleNestedDotID= ruleNestedDotID EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3610:2: iv_ruleNestedDotID= ruleNestedDotID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNestedDotIDRule()); 
            }
            pushFollow(FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID8146);
            iv_ruleNestedDotID=ruleNestedDotID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNestedDotID; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNestedDotID8156); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3617:1: ruleNestedDotID returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) ;
    public final EObject ruleNestedDotID() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_sub_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3620:28: ( ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3621:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3621:1: ( ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3621:2: ( (otherlv_0= RULE_ID ) ) (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3621:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3622:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3622:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3623:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNestedDotIDRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNestedDotID8201); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getNestedDotIDAccess().getBaseNamedElementCrossReference_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3634:2: (otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==84) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3634:4: otherlv_1= '.' ( (lv_sub_2_0= ruleNestedDotID ) )
                    {
                    otherlv_1=(Token)match(input,84,FOLLOW_84_in_ruleNestedDotID8214); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNestedDotIDAccess().getFullStopKeyword_1_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3638:1: ( (lv_sub_2_0= ruleNestedDotID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3639:1: (lv_sub_2_0= ruleNestedDotID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3639:1: (lv_sub_2_0= ruleNestedDotID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3640:3: lv_sub_2_0= ruleNestedDotID
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNestedDotIDAccess().getSubNestedDotIDParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNestedDotID_in_ruleNestedDotID8235);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3664:1: entryRuleProveStatement returns [EObject current=null] : iv_ruleProveStatement= ruleProveStatement EOF ;
    public final EObject entryRuleProveStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProveStatement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3665:2: (iv_ruleProveStatement= ruleProveStatement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3666:2: iv_ruleProveStatement= ruleProveStatement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProveStatementRule()); 
            }
            pushFollow(FOLLOW_ruleProveStatement_in_entryRuleProveStatement8273);
            iv_ruleProveStatement=ruleProveStatement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProveStatement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleProveStatement8283); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3673:1: ruleProveStatement returns [EObject current=null] : (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ) ;
    public final EObject ruleProveStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3676:28: ( (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3677:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3677:1: (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3677:3: otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) )
            {
            otherlv_0=(Token)match(input,152,FOLLOW_152_in_ruleProveStatement8320); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getProveStatementAccess().getProveKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3681:1: ( (lv_expr_1_0= ruleExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3682:1: (lv_expr_1_0= ruleExpr )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3682:1: (lv_expr_1_0= ruleExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3683:3: lv_expr_1_0= ruleExpr
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getProveStatementAccess().getExprExprParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpr_in_ruleProveStatement8341);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:1: entryRuleContainedPropertyAssociation returns [EObject current=null] : iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF ;
    public final EObject entryRuleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainedPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3710:2: (iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3711:2: iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainedPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation8379);
            iv_ruleContainedPropertyAssociation=ruleContainedPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainedPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainedPropertyAssociation8389); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3718:1: ruleContainedPropertyAssociation returns [EObject current=null] : ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3721:28: ( ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:1: ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:2: ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:2: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3723:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3723:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3724:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getPropertyPropertyCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation8437);
            ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3737:2: (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==71) ) {
                alt45=1;
            }
            else if ( (LA45_0==153) ) {
                alt45=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3737:4: otherlv_1= '=>'
                    {
                    otherlv_1=(Token)match(input,71,FOLLOW_71_in_ruleContainedPropertyAssociation8450); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getContainedPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1_0());
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3742:6: ( (lv_append_2_0= '+=>' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3742:6: ( (lv_append_2_0= '+=>' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3743:1: (lv_append_2_0= '+=>' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3743:1: (lv_append_2_0= '+=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3744:3: lv_append_2_0= '+=>'
                    {
                    lv_append_2_0=(Token)match(input,153,FOLLOW_153_in_ruleContainedPropertyAssociation8474); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3757:3: ( (lv_constant_3_0= 'constant' ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==154) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3758:1: (lv_constant_3_0= 'constant' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3758:1: (lv_constant_3_0= 'constant' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3759:3: lv_constant_3_0= 'constant'
                    {
                    lv_constant_3_0=(Token)match(input,154,FOLLOW_154_in_ruleContainedPropertyAssociation8506); if (state.failed) return current;
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3772:3: ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3772:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3772:4: ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3773:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3773:1: (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3774:3: lv_ownedValue_4_0= ruleOptionalModalPropertyValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8542);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3790:2: (otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==66) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3790:4: otherlv_5= ',' ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    {
            	    otherlv_5=(Token)match(input,66,FOLLOW_66_in_ruleContainedPropertyAssociation8555); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_5, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_3_1_0());
            	          
            	    }
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3794:1: ( (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3795:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3795:1: (lv_ownedValue_6_0= ruleOptionalModalPropertyValue )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3796:3: lv_ownedValue_6_0= ruleOptionalModalPropertyValue
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getOwnedValueOptionalModalPropertyValueParserRuleCall_3_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8576);
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
            	    break loop47;
                }
            } while (true);


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3812:5: (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==155) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3812:7: otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    {
                    otherlv_7=(Token)match(input,155,FOLLOW_155_in_ruleContainedPropertyAssociation8592); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getContainedPropertyAssociationAccess().getAppliesKeyword_4_0());
                          
                    }
                    otherlv_8=(Token)match(input,156,FOLLOW_156_in_ruleContainedPropertyAssociation8604); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getContainedPropertyAssociationAccess().getToKeyword_4_1());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3820:1: ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3821:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3821:1: (lv_appliesTo_9_0= ruleContainmentPath )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3822:3: lv_appliesTo_9_0= ruleContainmentPath
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8625);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3838:2: (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==66) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3838:4: otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    {
                    	    otherlv_10=(Token)match(input,66,FOLLOW_66_in_ruleContainedPropertyAssociation8638); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_10, grammarAccess.getContainedPropertyAssociationAccess().getCommaKeyword_4_3_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3842:1: ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3843:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3843:1: (lv_appliesTo_11_0= ruleContainmentPath )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3844:3: lv_appliesTo_11_0= ruleContainmentPath
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getAppliesToContainmentPathParserRuleCall_4_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8659);
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
                    	    break loop48;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3860:6: (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==157) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3860:8: otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')'
                    {
                    otherlv_12=(Token)match(input,157,FOLLOW_157_in_ruleContainedPropertyAssociation8676); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getContainedPropertyAssociationAccess().getInKeyword_5_0());
                          
                    }
                    otherlv_13=(Token)match(input,158,FOLLOW_158_in_ruleContainedPropertyAssociation8688); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getContainedPropertyAssociationAccess().getBindingKeyword_5_1());
                          
                    }
                    otherlv_14=(Token)match(input,65,FOLLOW_65_in_ruleContainedPropertyAssociation8700); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getContainedPropertyAssociationAccess().getLeftParenthesisKeyword_5_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3872:1: ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3873:1: ( ruleQCREF )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3873:1: ( ruleQCREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3874:3: ruleQCREF
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getContainedPropertyAssociationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainedPropertyAssociationAccess().getInBindingClassifierCrossReference_5_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation8723);
                    ruleQCREF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,67,FOLLOW_67_in_ruleContainedPropertyAssociation8735); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getContainedPropertyAssociationAccess().getRightParenthesisKeyword_5_4());
                          
                    }

                    }
                    break;

            }

            otherlv_17=(Token)match(input,94,FOLLOW_94_in_ruleContainedPropertyAssociation8749); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3907:1: entryRuleContainmentPath returns [EObject current=null] : iv_ruleContainmentPath= ruleContainmentPath EOF ;
    public final EObject entryRuleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPath = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3908:2: (iv_ruleContainmentPath= ruleContainmentPath EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3909:2: iv_ruleContainmentPath= ruleContainmentPath EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath8789);
            iv_ruleContainmentPath=ruleContainmentPath();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPath; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPath8799); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3916:1: ruleContainmentPath returns [EObject current=null] : ( (lv_path_0_0= ruleContainmentPathElement ) ) ;
    public final EObject ruleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject lv_path_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3919:28: ( ( (lv_path_0_0= ruleContainmentPathElement ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3920:1: ( (lv_path_0_0= ruleContainmentPathElement ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3920:1: ( (lv_path_0_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3921:1: (lv_path_0_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3921:1: (lv_path_0_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3922:3: lv_path_0_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getContainmentPathAccess().getPathContainmentPathElementParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8844);
            lv_path_0_0=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getContainmentPathRule());
              	        }
                     		set(
                     			current, 
                     			"path",
                      		lv_path_0_0, 
                      		"ContainmentPathElement");
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
    // $ANTLR end "ruleContainmentPath"


    // $ANTLR start "entryRuleOptionalModalPropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3948:1: entryRuleOptionalModalPropertyValue returns [EObject current=null] : iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF ;
    public final EObject entryRuleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalModalPropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3949:2: (iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3950:2: iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOptionalModalPropertyValueRule()); 
            }
            pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue8881);
            iv_ruleOptionalModalPropertyValue=ruleOptionalModalPropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOptionalModalPropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue8891); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3957:1: ruleOptionalModalPropertyValue returns [EObject current=null] : ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3960:28: ( ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3961:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3961:1: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3961:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3961:2: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3962:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3962:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3963:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOptionalModalPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue8937);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3979:2: (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==157) ) {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==159) ) {
                    alt52=1;
                }
            }
            switch (alt52) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3979:4: otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')'
                    {
                    otherlv_1=(Token)match(input,157,FOLLOW_157_in_ruleOptionalModalPropertyValue8950); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getOptionalModalPropertyValueAccess().getInKeyword_1_0());
                          
                    }
                    otherlv_2=(Token)match(input,159,FOLLOW_159_in_ruleOptionalModalPropertyValue8962); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getOptionalModalPropertyValueAccess().getModesKeyword_1_1());
                          
                    }
                    otherlv_3=(Token)match(input,65,FOLLOW_65_in_ruleOptionalModalPropertyValue8974); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getOptionalModalPropertyValueAccess().getLeftParenthesisKeyword_1_2());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3991:1: ( (otherlv_4= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3992:1: (otherlv_4= RULE_ID )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3992:1: (otherlv_4= RULE_ID )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3993:3: otherlv_4= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                      	        }
                              
                    }
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8994); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_4, grammarAccess.getOptionalModalPropertyValueAccess().getInModeModeCrossReference_1_3_0()); 
                      	
                    }

                    }


                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4004:2: (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==66) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4004:4: otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
                    	    {
                    	    otherlv_5=(Token)match(input,66,FOLLOW_66_in_ruleOptionalModalPropertyValue9007); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getOptionalModalPropertyValueAccess().getCommaKeyword_1_4_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4008:1: ( (otherlv_6= RULE_ID ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4009:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4009:1: (otherlv_6= RULE_ID )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4010:3: otherlv_6= RULE_ID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      			if (current==null) {
                    	      	            current = createModelElement(grammarAccess.getOptionalModalPropertyValueRule());
                    	      	        }
                    	              
                    	    }
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue9027); if (state.failed) return current;
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

                    otherlv_7=(Token)match(input,67,FOLLOW_67_in_ruleOptionalModalPropertyValue9041); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4033:1: entryRulePropertyValue returns [EObject current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final EObject entryRulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4034:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4035:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyValueRule()); 
            }
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue9079);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue9089); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4042:1: rulePropertyValue returns [EObject current=null] : ( (lv_ownedValue_0_0= rulePropertyExpression ) ) ;
    public final EObject rulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedValue_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4045:28: ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4046:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4046:1: ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4047:1: (lv_ownedValue_0_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4047:1: (lv_ownedValue_0_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4048:3: lv_ownedValue_0_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getPropertyValueAccess().getOwnedValuePropertyExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyValue9134);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4072:1: entryRulePropertyExpression returns [EObject current=null] : iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4073:2: (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4074:2: iv_rulePropertyExpression= rulePropertyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression9169);
            iv_rulePropertyExpression=rulePropertyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyExpression9179); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4081:1: rulePropertyExpression returns [EObject current=null] : (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4084:28: ( (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4085:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4085:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
            int alt53=11;
            alt53 = dfa53.predict(input);
            switch (alt53) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4086:5: this_RecordTerm_0= ruleRecordTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRecordTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRecordTerm_in_rulePropertyExpression9226);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4096:5: this_ReferenceTerm_1= ruleReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getReferenceTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleReferenceTerm_in_rulePropertyExpression9253);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4106:5: this_ComponentClassifierTerm_2= ruleComponentClassifierTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComponentClassifierTermParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression9280);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4116:5: this_ComputedTerm_3= ruleComputedTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getComputedTermParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleComputedTerm_in_rulePropertyExpression9307);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4126:5: this_StringTerm_4= ruleStringTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getStringTermParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_rulePropertyExpression9334);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4136:5: this_NumericRangeTerm_5= ruleNumericRangeTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getNumericRangeTermParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression9361);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4146:5: this_RealTerm_6= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getRealTermParserRuleCall_6()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_rulePropertyExpression9388);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4156:5: this_IntegerTerm_7= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getIntegerTermParserRuleCall_7()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_rulePropertyExpression9415);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4166:5: this_ListTerm_8= ruleListTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getListTermParserRuleCall_8()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListTerm_in_rulePropertyExpression9442);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4176:5: this_BooleanLiteral_9= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getBooleanLiteralParserRuleCall_9()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression9469);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4186:5: this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPropertyExpressionAccess().getLiteralorReferenceTermParserRuleCall_10()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression9496);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4202:1: entryRuleLiteralorReferenceTerm returns [EObject current=null] : iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF ;
    public final EObject entryRuleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralorReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4203:2: (iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4204:2: iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralorReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm9531);
            iv_ruleLiteralorReferenceTerm=ruleLiteralorReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteralorReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm9541); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4211:1: ruleLiteralorReferenceTerm returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4214:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4215:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4216:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4216:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4217:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLiteralorReferenceTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLiteralorReferenceTermAccess().getNamedValueAbstractNamedValueCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm9588);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4238:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4239:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4240:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral9623);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral9633); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4247:1: ruleBooleanLiteral returns [EObject current=null] : ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4250:28: ( ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4251:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4251:1: ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4251:2: () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4251:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4252:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getBooleanLiteralAccess().getBooleanLiteralAction_0(),
                          current);
                  
            }

            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4257:2: ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==160) ) {
                alt54=1;
            }
            else if ( (LA54_0==161) ) {
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4257:3: ( (lv_value_1_0= 'true' ) )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4257:3: ( (lv_value_1_0= 'true' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4258:1: (lv_value_1_0= 'true' )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4258:1: (lv_value_1_0= 'true' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4259:3: lv_value_1_0= 'true'
                    {
                    lv_value_1_0=(Token)match(input,160,FOLLOW_160_in_ruleBooleanLiteral9686); if (state.failed) return current;
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4273:7: otherlv_2= 'false'
                    {
                    otherlv_2=(Token)match(input,161,FOLLOW_161_in_ruleBooleanLiteral9717); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4285:1: entryRuleConstantValue returns [EObject current=null] : iv_ruleConstantValue= ruleConstantValue EOF ;
    public final EObject entryRuleConstantValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantValue = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4286:2: (iv_ruleConstantValue= ruleConstantValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:2: iv_ruleConstantValue= ruleConstantValue EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstantValueRule()); 
            }
            pushFollow(FOLLOW_ruleConstantValue_in_entryRuleConstantValue9754);
            iv_ruleConstantValue=ruleConstantValue();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstantValue; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstantValue9764); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4294:1: ruleConstantValue returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleConstantValue() throws RecognitionException {
        EObject current = null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4297:28: ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4298:1: ( ( ruleQPREF ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4298:1: ( ( ruleQPREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4299:1: ( ruleQPREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4299:1: ( ruleQPREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4300:3: ruleQPREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getConstantValueRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstantValueAccess().getNamedValuePropertyConstantCrossReference_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQPREF_in_ruleConstantValue9811);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4321:1: entryRuleReferenceTerm returns [EObject current=null] : iv_ruleReferenceTerm= ruleReferenceTerm EOF ;
    public final EObject entryRuleReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4322:2: (iv_ruleReferenceTerm= ruleReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4323:2: iv_ruleReferenceTerm= ruleReferenceTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferenceTermRule()); 
            }
            pushFollow(FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm9846);
            iv_ruleReferenceTerm=ruleReferenceTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferenceTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleReferenceTerm9856); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4330:1: ruleReferenceTerm returns [EObject current=null] : (otherlv_0= 'reference' otherlv_1= '(' ( (lv_path_2_0= ruleContainmentPathElement ) ) otherlv_3= ')' ) ;
    public final EObject ruleReferenceTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_path_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4333:28: ( (otherlv_0= 'reference' otherlv_1= '(' ( (lv_path_2_0= ruleContainmentPathElement ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4334:1: (otherlv_0= 'reference' otherlv_1= '(' ( (lv_path_2_0= ruleContainmentPathElement ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4334:1: (otherlv_0= 'reference' otherlv_1= '(' ( (lv_path_2_0= ruleContainmentPathElement ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4334:3: otherlv_0= 'reference' otherlv_1= '(' ( (lv_path_2_0= ruleContainmentPathElement ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,162,FOLLOW_162_in_ruleReferenceTerm9893); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getReferenceTermAccess().getReferenceKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleReferenceTerm9905); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getReferenceTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4342:1: ( (lv_path_2_0= ruleContainmentPathElement ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4343:1: (lv_path_2_0= ruleContainmentPathElement )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4343:1: (lv_path_2_0= ruleContainmentPathElement )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4344:3: lv_path_2_0= ruleContainmentPathElement
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getReferenceTermAccess().getPathContainmentPathElementParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9926);
            lv_path_2_0=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getReferenceTermRule());
              	        }
                     		set(
                     			current, 
                     			"path",
                      		lv_path_2_0, 
                      		"ContainmentPathElement");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,67,FOLLOW_67_in_ruleReferenceTerm9938); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getReferenceTermAccess().getRightParenthesisKeyword_3());
                  
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4372:1: entryRuleRecordTerm returns [EObject current=null] : iv_ruleRecordTerm= ruleRecordTerm EOF ;
    public final EObject entryRuleRecordTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecordTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4373:2: (iv_ruleRecordTerm= ruleRecordTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4374:2: iv_ruleRecordTerm= ruleRecordTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRecordTermRule()); 
            }
            pushFollow(FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm9974);
            iv_ruleRecordTerm=ruleRecordTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRecordTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRecordTerm9984); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4381:1: ruleRecordTerm returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) ;
    public final EObject ruleRecordTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedFieldValue_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4384:28: ( (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4385:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4385:1: (otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4385:3: otherlv_0= '[' ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
            {
            otherlv_0=(Token)match(input,163,FOLLOW_163_in_ruleRecordTerm10021); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getRecordTermAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4389:1: ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+
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
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4390:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4390:1: (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4391:3: lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRecordTermAccess().getOwnedFieldValueFieldPropertyAssociationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm10042);
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

            otherlv_2=(Token)match(input,164,FOLLOW_164_in_ruleRecordTerm10055); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4421:1: entryRuleComputedTerm returns [EObject current=null] : iv_ruleComputedTerm= ruleComputedTerm EOF ;
    public final EObject entryRuleComputedTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputedTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4422:2: (iv_ruleComputedTerm= ruleComputedTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4423:2: iv_ruleComputedTerm= ruleComputedTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComputedTermRule()); 
            }
            pushFollow(FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm10093);
            iv_ruleComputedTerm=ruleComputedTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComputedTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComputedTerm10103); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4430:1: ruleComputedTerm returns [EObject current=null] : (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleComputedTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_function_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4433:28: ( (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4434:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4434:1: (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4434:3: otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,165,FOLLOW_165_in_ruleComputedTerm10140); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComputedTermAccess().getComputeKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleComputedTerm10152); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComputedTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4442:1: ( (lv_function_2_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4443:1: (lv_function_2_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4443:1: (lv_function_2_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4444:3: lv_function_2_0= RULE_ID
            {
            lv_function_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleComputedTerm10169); if (state.failed) return current;
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

            otherlv_3=(Token)match(input,67,FOLLOW_67_in_ruleComputedTerm10186); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4472:1: entryRuleComponentClassifierTerm returns [EObject current=null] : iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF ;
    public final EObject entryRuleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentClassifierTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4473:2: (iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4474:2: iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getComponentClassifierTermRule()); 
            }
            pushFollow(FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm10222);
            iv_ruleComponentClassifierTerm=ruleComponentClassifierTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleComponentClassifierTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleComponentClassifierTerm10232); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4481:1: ruleComponentClassifierTerm returns [EObject current=null] : (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) ;
    public final EObject ruleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4484:28: ( (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:1: (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4485:3: otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,166,FOLLOW_166_in_ruleComponentClassifierTerm10269); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getComponentClassifierTermAccess().getClassifierKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleComponentClassifierTerm10281); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getComponentClassifierTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4493:1: ( ( ruleQCREF ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4494:1: ( ruleQCREF )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4494:1: ( ruleQCREF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4495:3: ruleQCREF
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getComponentClassifierTermRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getComponentClassifierTermAccess().getClassifierComponentClassifierCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm10304);
            ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,67,FOLLOW_67_in_ruleComponentClassifierTerm10316); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4520:1: entryRuleListTerm returns [EObject current=null] : iv_ruleListTerm= ruleListTerm EOF ;
    public final EObject entryRuleListTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4521:2: (iv_ruleListTerm= ruleListTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4522:2: iv_ruleListTerm= ruleListTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListTermRule()); 
            }
            pushFollow(FOLLOW_ruleListTerm_in_entryRuleListTerm10352);
            iv_ruleListTerm=ruleListTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListTerm10362); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4529:1: ruleListTerm returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleListTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedListElement_2_0 = null;

        EObject lv_ownedListElement_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4532:28: ( ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4533:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4533:1: ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4533:2: () otherlv_1= '(' ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4533:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4534:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListTermAccess().getListValueAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,65,FOLLOW_65_in_ruleListTerm10408); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListTermAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4543:1: ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=RULE_ID && LA57_0<=RULE_INTEGER_LIT)||LA57_0==65||(LA57_0>=78 && LA57_0<=79)||(LA57_0>=160 && LA57_0<=163)||(LA57_0>=165 && LA57_0<=166)) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4543:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4543:2: ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4544:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4544:1: (lv_ownedListElement_2_0= rulePropertyExpression )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4545:3: lv_ownedListElement_2_0= rulePropertyExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm10430);
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

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4561:2: (otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==66) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4561:4: otherlv_3= ',' ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,66,FOLLOW_66_in_ruleListTerm10443); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListTermAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4565:1: ( (lv_ownedListElement_4_0= rulePropertyExpression ) )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4566:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    {
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4566:1: (lv_ownedListElement_4_0= rulePropertyExpression )
                    	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4567:3: lv_ownedListElement_4_0= rulePropertyExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListTermAccess().getOwnedListElementPropertyExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm10464);
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

            otherlv_5=(Token)match(input,67,FOLLOW_67_in_ruleListTerm10480); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4595:1: entryRuleFieldPropertyAssociation returns [EObject current=null] : iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF ;
    public final EObject entryRuleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFieldPropertyAssociation = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4596:2: (iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4597:2: iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFieldPropertyAssociationRule()); 
            }
            pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation10516);
            iv_ruleFieldPropertyAssociation=ruleFieldPropertyAssociation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFieldPropertyAssociation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFieldPropertyAssociation10526); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4604:1: ruleFieldPropertyAssociation returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedValue_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4607:28: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4608:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4608:1: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4608:2: ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4608:2: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4609:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4609:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4610:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getFieldPropertyAssociationRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation10571); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getFieldPropertyAssociationAccess().getPropertyBasicPropertyCrossReference_0_0()); 
              	
            }

            }


            }

            otherlv_1=(Token)match(input,71,FOLLOW_71_in_ruleFieldPropertyAssociation10583); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getFieldPropertyAssociationAccess().getEqualsSignGreaterThanSignKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4625:1: ( (lv_ownedValue_2_0= rulePropertyExpression ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4626:1: (lv_ownedValue_2_0= rulePropertyExpression )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4626:1: (lv_ownedValue_2_0= rulePropertyExpression )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4627:3: lv_ownedValue_2_0= rulePropertyExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFieldPropertyAssociationAccess().getOwnedValuePropertyExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation10604);
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

            otherlv_3=(Token)match(input,94,FOLLOW_94_in_ruleFieldPropertyAssociation10616); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4655:1: entryRuleContainmentPathElement returns [EObject current=null] : iv_ruleContainmentPathElement= ruleContainmentPathElement EOF ;
    public final EObject entryRuleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPathElement = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4656:2: (iv_ruleContainmentPathElement= ruleContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4657:2: iv_ruleContainmentPathElement= ruleContainmentPathElement EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContainmentPathElementRule()); 
            }
            pushFollow(FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement10652);
            iv_ruleContainmentPathElement=ruleContainmentPathElement();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContainmentPathElement; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleContainmentPathElement10662); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4664:1: ruleContainmentPathElement returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* ) (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )? ) ;
    public final EObject ruleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_arrayRange_1_0 = null;

        EObject lv_path_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4667:28: ( ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* ) (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:1: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* ) (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:1: ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* ) (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* ) (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:2: ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )* )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:3: ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )*
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4668:3: ( (otherlv_0= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4669:1: (otherlv_0= RULE_ID )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4669:1: (otherlv_0= RULE_ID )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4670:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getContainmentPathElementRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleContainmentPathElement10708); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getContainmentPathElementAccess().getNamedElementNamedElementCrossReference_0_0_0()); 
              	
            }

            }


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4681:2: ( (lv_arrayRange_1_0= ruleArrayRange ) )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==163) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4682:1: (lv_arrayRange_1_0= ruleArrayRange )
            	    {
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4682:1: (lv_arrayRange_1_0= ruleArrayRange )
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4683:3: lv_arrayRange_1_0= ruleArrayRange
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getContainmentPathElementAccess().getArrayRangeArrayRangeParserRuleCall_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleArrayRange_in_ruleContainmentPathElement10729);
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

            	default :
            	    break loop58;
                }
            } while (true);


            }

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4699:4: (otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==84) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4699:6: otherlv_2= '.' ( (lv_path_3_0= ruleContainmentPathElement ) )
                    {
                    otherlv_2=(Token)match(input,84,FOLLOW_84_in_ruleContainmentPathElement10744); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getContainmentPathElementAccess().getFullStopKeyword_1_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4703:1: ( (lv_path_3_0= ruleContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4704:1: (lv_path_3_0= ruleContainmentPathElement )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4704:1: (lv_path_3_0= ruleContainmentPathElement )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4705:3: lv_path_3_0= ruleContainmentPathElement
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getContainmentPathElementAccess().getPathContainmentPathElementParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPathElement10765);
                    lv_path_3_0=ruleContainmentPathElement();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getContainmentPathElementRule());
                      	        }
                             		set(
                             			current, 
                             			"path",
                              		lv_path_3_0, 
                              		"ContainmentPathElement");
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
    // $ANTLR end "ruleContainmentPathElement"


    // $ANTLR start "entryRulePlusMinus"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4731:1: entryRulePlusMinus returns [String current=null] : iv_rulePlusMinus= rulePlusMinus EOF ;
    public final String entryRulePlusMinus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePlusMinus = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4732:2: (iv_rulePlusMinus= rulePlusMinus EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4733:2: iv_rulePlusMinus= rulePlusMinus EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPlusMinusRule()); 
            }
            pushFollow(FOLLOW_rulePlusMinus_in_entryRulePlusMinus10806);
            iv_rulePlusMinus=rulePlusMinus();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePlusMinus.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePlusMinus10817); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4740:1: rulePlusMinus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken rulePlusMinus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4743:28: ( (kw= '+' | kw= '-' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4744:1: (kw= '+' | kw= '-' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4744:1: (kw= '+' | kw= '-' )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==78) ) {
                alt60=1;
            }
            else if ( (LA60_0==79) ) {
                alt60=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4745:2: kw= '+'
                    {
                    kw=(Token)match(input,78,FOLLOW_78_in_rulePlusMinus10855); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPlusMinusAccess().getPlusSignKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4752:2: kw= '-'
                    {
                    kw=(Token)match(input,79,FOLLOW_79_in_rulePlusMinus10874); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4765:1: entryRuleStringTerm returns [EObject current=null] : iv_ruleStringTerm= ruleStringTerm EOF ;
    public final EObject entryRuleStringTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4766:2: (iv_ruleStringTerm= ruleStringTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4767:2: iv_ruleStringTerm= ruleStringTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringTermRule()); 
            }
            pushFollow(FOLLOW_ruleStringTerm_in_entryRuleStringTerm10914);
            iv_ruleStringTerm=ruleStringTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringTerm10924); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4774:1: ruleStringTerm returns [EObject current=null] : ( (lv_value_0_0= ruleNoQuoteString ) ) ;
    public final EObject ruleStringTerm() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4777:28: ( ( (lv_value_0_0= ruleNoQuoteString ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4778:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4778:1: ( (lv_value_0_0= ruleNoQuoteString ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4779:1: (lv_value_0_0= ruleNoQuoteString )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4779:1: (lv_value_0_0= ruleNoQuoteString )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4780:3: lv_value_0_0= ruleNoQuoteString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getStringTermAccess().getValueNoQuoteStringParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_ruleStringTerm10969);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4804:1: entryRuleNoQuoteString returns [String current=null] : iv_ruleNoQuoteString= ruleNoQuoteString EOF ;
    public final String entryRuleNoQuoteString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNoQuoteString = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4805:2: (iv_ruleNoQuoteString= ruleNoQuoteString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4806:2: iv_ruleNoQuoteString= ruleNoQuoteString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoQuoteStringRule()); 
            }
            pushFollow(FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString11005);
            iv_ruleNoQuoteString=ruleNoQuoteString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoQuoteString.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoQuoteString11016); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4813:1: ruleNoQuoteString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNoQuoteString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4816:28: (this_STRING_0= RULE_STRING )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4817:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNoQuoteString11055); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4832:1: entryRuleArrayRange returns [EObject current=null] : iv_ruleArrayRange= ruleArrayRange EOF ;
    public final EObject entryRuleArrayRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayRange = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4833:2: (iv_ruleArrayRange= ruleArrayRange EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4834:2: iv_ruleArrayRange= ruleArrayRange EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayRangeRule()); 
            }
            pushFollow(FOLLOW_ruleArrayRange_in_entryRuleArrayRange11099);
            iv_ruleArrayRange=ruleArrayRange();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayRange; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayRange11109); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4841:1: ruleArrayRange returns [EObject current=null] : ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleArrayRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_lowerBound_2_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4844:28: ( ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4845:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4845:1: ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4845:2: () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']'
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4845:2: ()
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4846:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getArrayRangeAccess().getArrayRangeAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,163,FOLLOW_163_in_ruleArrayRange11155); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getArrayRangeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4855:1: ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4856:1: (lv_lowerBound_2_0= ruleINTVALUE )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4856:1: (lv_lowerBound_2_0= ruleINTVALUE )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4857:3: lv_lowerBound_2_0= ruleINTVALUE
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getArrayRangeAccess().getLowerBoundINTVALUEParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange11176);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4873:2: (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==167) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4873:4: otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    {
                    otherlv_3=(Token)match(input,167,FOLLOW_167_in_ruleArrayRange11189); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getArrayRangeAccess().getFullStopFullStopKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4877:1: ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4878:1: (lv_upperBound_4_0= ruleINTVALUE )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4878:1: (lv_upperBound_4_0= ruleINTVALUE )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4879:3: lv_upperBound_4_0= ruleINTVALUE
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getArrayRangeAccess().getUpperBoundINTVALUEParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange11210);
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

            otherlv_5=(Token)match(input,164,FOLLOW_164_in_ruleArrayRange11224); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4907:1: entryRuleSignedConstant returns [EObject current=null] : iv_ruleSignedConstant= ruleSignedConstant EOF ;
    public final EObject entryRuleSignedConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedConstant = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4908:2: (iv_ruleSignedConstant= ruleSignedConstant EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4909:2: iv_ruleSignedConstant= ruleSignedConstant EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSignedConstantRule()); 
            }
            pushFollow(FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant11260);
            iv_ruleSignedConstant=ruleSignedConstant();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSignedConstant; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSignedConstant11270); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4916:1: ruleSignedConstant returns [EObject current=null] : ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) ;
    public final EObject ruleSignedConstant() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_0_0 = null;

        EObject lv_ownedPropertyExpression_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4919:28: ( ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4920:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4920:1: ( ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4920:2: ( (lv_op_0_0= rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4920:2: ( (lv_op_0_0= rulePlusMinus ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4921:1: (lv_op_0_0= rulePlusMinus )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4921:1: (lv_op_0_0= rulePlusMinus )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4922:3: lv_op_0_0= rulePlusMinus
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOpPlusMinusParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_rulePlusMinus_in_ruleSignedConstant11316);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4938:2: ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4939:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4939:1: (lv_ownedPropertyExpression_1_0= ruleConstantValue )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4940:3: lv_ownedPropertyExpression_1_0= ruleConstantValue
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSignedConstantAccess().getOwnedPropertyExpressionConstantValueParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleConstantValue_in_ruleSignedConstant11337);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4968:1: entryRuleNumericRangeTerm returns [EObject current=null] : iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF ;
    public final EObject entryRuleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericRangeTerm = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4969:2: (iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4970:2: iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumericRangeTermRule()); 
            }
            pushFollow(FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm11377);
            iv_ruleNumericRangeTerm=ruleNumericRangeTerm();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumericRangeTerm; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumericRangeTerm11387); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4977:1: ruleNumericRangeTerm returns [EObject current=null] : ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) ;
    public final EObject ruleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_minimum_0_0 = null;

        EObject lv_maximum_2_0 = null;

        EObject lv_delta_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4980:28: ( ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4981:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4981:1: ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4981:2: ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4981:2: ( (lv_minimum_0_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4982:1: (lv_minimum_0_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4982:1: (lv_minimum_0_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4983:3: lv_minimum_0_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMinimumNumAltParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11433);
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

            otherlv_1=(Token)match(input,167,FOLLOW_167_in_ruleNumericRangeTerm11445); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNumericRangeTermAccess().getFullStopFullStopKeyword_1());
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5003:1: ( (lv_maximum_2_0= ruleNumAlt ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5004:1: (lv_maximum_2_0= ruleNumAlt )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5004:1: (lv_maximum_2_0= ruleNumAlt )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5005:3: lv_maximum_2_0= ruleNumAlt
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getMaximumNumAltParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11466);
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

            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5021:2: (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==168) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5021:4: otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) )
                    {
                    otherlv_3=(Token)match(input,168,FOLLOW_168_in_ruleNumericRangeTerm11479); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getNumericRangeTermAccess().getDeltaKeyword_3_0());
                          
                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5025:1: ( (lv_delta_4_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5026:1: (lv_delta_4_0= ruleNumAlt )
                    {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5026:1: (lv_delta_4_0= ruleNumAlt )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5027:3: lv_delta_4_0= ruleNumAlt
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNumericRangeTermAccess().getDeltaNumAltParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11500);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5051:1: entryRuleNumAlt returns [EObject current=null] : iv_ruleNumAlt= ruleNumAlt EOF ;
    public final EObject entryRuleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumAlt = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5052:2: (iv_ruleNumAlt= ruleNumAlt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5053:2: iv_ruleNumAlt= ruleNumAlt EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumAltRule()); 
            }
            pushFollow(FOLLOW_ruleNumAlt_in_entryRuleNumAlt11538);
            iv_ruleNumAlt=ruleNumAlt();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumAlt; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumAlt11548); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5060:1: ruleNumAlt returns [EObject current=null] : (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) ;
    public final EObject ruleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject this_RealTerm_0 = null;

        EObject this_IntegerTerm_1 = null;

        EObject this_SignedConstant_2 = null;

        EObject this_ConstantValue_3 = null;


         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5063:28: ( (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5064:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5064:1: (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3= ruleConstantValue )
            int alt63=4;
            switch ( input.LA(1) ) {
            case RULE_REAL_LIT:
                {
                alt63=1;
                }
                break;
            case RULE_INTEGER_LIT:
                {
                alt63=2;
                }
                break;
            case 78:
            case 79:
                {
                alt63=3;
                }
                break;
            case RULE_ID:
                {
                alt63=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5065:5: this_RealTerm_0= ruleRealTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getRealTermParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleNumAlt11595);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5075:5: this_IntegerTerm_1= ruleIntegerTerm
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getIntegerTermParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleNumAlt11622);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5085:5: this_SignedConstant_2= ruleSignedConstant
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getSignedConstantParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSignedConstant_in_ruleNumAlt11649);
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
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5095:5: this_ConstantValue_3= ruleConstantValue
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNumAltAccess().getConstantValueParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstantValue_in_ruleNumAlt11676);
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5111:1: entryRuleINTVALUE returns [String current=null] : iv_ruleINTVALUE= ruleINTVALUE EOF ;
    public final String entryRuleINTVALUE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleINTVALUE = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5112:2: (iv_ruleINTVALUE= ruleINTVALUE EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5113:2: iv_ruleINTVALUE= ruleINTVALUE EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getINTVALUERule()); 
            }
            pushFollow(FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE11712);
            iv_ruleINTVALUE=ruleINTVALUE();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleINTVALUE.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleINTVALUE11723); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5120:1: ruleINTVALUE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleINTVALUE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5123:28: (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5124:5: this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
            this_INTEGER_LIT_0=(Token)match(input,RULE_INTEGER_LIT,FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE11762); if (state.failed) return current;
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


    // $ANTLR start "entryRuleQPREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5141:1: entryRuleQPREF returns [String current=null] : iv_ruleQPREF= ruleQPREF EOF ;
    public final String entryRuleQPREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQPREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5142:2: (iv_ruleQPREF= ruleQPREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5143:2: iv_ruleQPREF= ruleQPREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQPREFRule()); 
            }
            pushFollow(FOLLOW_ruleQPREF_in_entryRuleQPREF11809);
            iv_ruleQPREF=ruleQPREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQPREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQPREF11820); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5150:1: ruleQPREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQPREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5153:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5154:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5154:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5154:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )?
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF11860); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQPREFAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5161:1: (kw= '::' this_ID_2= RULE_ID )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==169) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5162:2: kw= '::' this_ID_2= RULE_ID
                    {
                    kw=(Token)match(input,169,FOLLOW_169_in_ruleQPREF11879); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQPREFAccess().getColonColonKeyword_1_0()); 
                          
                    }
                    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQPREF11894); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5182:1: entryRuleQCREF returns [String current=null] : iv_ruleQCREF= ruleQCREF EOF ;
    public final String entryRuleQCREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCREF = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5183:2: (iv_ruleQCREF= ruleQCREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5184:2: iv_ruleQCREF= ruleQCREF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQCREFRule()); 
            }
            pushFollow(FOLLOW_ruleQCREF_in_entryRuleQCREF11942);
            iv_ruleQCREF=ruleQCREF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQCREF.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQCREF11953); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5191:1: ruleQCREF returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQCREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;
        Token this_ID_4=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5194:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5195:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5195:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )? )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5195:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.' this_ID_4= RULE_ID )?
            {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5195:2: (this_ID_0= RULE_ID kw= '::' )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==RULE_ID) ) {
                    int LA65_1 = input.LA(2);

                    if ( (LA65_1==169) ) {
                        alt65=1;
                    }


                }


                switch (alt65) {
            	case 1 :
            	    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5195:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF11994); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_0);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_0, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_0_0()); 
            	          
            	    }
            	    kw=(Token)match(input,169,FOLLOW_169_in_ruleQCREF12012); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQCREFAccess().getColonColonKeyword_0_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF12029); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_2);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_2, grammarAccess.getQCREFAccess().getIDTerminalRuleCall_1()); 
                  
            }
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5215:1: (kw= '.' this_ID_4= RULE_ID )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==84) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5216:2: kw= '.' this_ID_4= RULE_ID
                    {
                    kw=(Token)match(input,84,FOLLOW_84_in_ruleQCREF12048); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getQCREFAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    this_ID_4=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQCREF12063); if (state.failed) return current;
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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5236:1: entryRuleSTAR returns [String current=null] : iv_ruleSTAR= ruleSTAR EOF ;
    public final String entryRuleSTAR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSTAR = null;


        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5237:2: (iv_ruleSTAR= ruleSTAR EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5238:2: iv_ruleSTAR= ruleSTAR EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSTARRule()); 
            }
            pushFollow(FOLLOW_ruleSTAR_in_entryRuleSTAR12111);
            iv_ruleSTAR=ruleSTAR();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSTAR.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSTAR12122); if (state.failed) return current;

            }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5245:1: ruleSTAR returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken ruleSTAR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5248:28: (kw= '*' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:5250:2: kw= '*'
            {
            kw=(Token)match(input,80,FOLLOW_80_in_ruleSTAR12159); if (state.failed) return current;
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:3: ( ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:4: ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:4: ( ( ( RULE_ID ) ) ':' ( ( ruleType ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:5: ( ( RULE_ID ) ) ':' ( ( ruleType ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:847:5: ( ( RULE_ID ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:848:1: ( RULE_ID )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:848:1: ( RULE_ID )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:849:1: RULE_ID
        {
        match(input,RULE_ID,FOLLOW_RULE_ID_in_synpred1_InternalResolute2001); if (state.failed) return ;

        }


        }

        match(input,63,FOLLOW_63_in_synpred1_InternalResolute2008); if (state.failed) return ;
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:853:1: ( ( ruleType ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:854:1: ( ruleType )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:854:1: ( ruleType )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:855:1: ruleType
        {
        pushFollow(FOLLOW_ruleType_in_synpred1_InternalResolute2015);
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:3: ( ( () ( ( '=>' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:4: ( () ( ( '=>' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:4: ( () ( ( '=>' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:5: () ( ( '=>' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1402:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1403:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1403:2: ( ( '=>' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1404:1: ( '=>' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1404:1: ( '=>' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1405:2: '=>'
        {
        match(input,71,FOLLOW_71_in_synpred2_InternalResolute3128); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalResolute

    // $ANTLR start synpred3_InternalResolute
    public final void synpred3_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:3: ( ( () ( ( 'or' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:4: ( () ( ( 'or' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:4: ( () ( ( 'or' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:5: () ( ( 'or' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1478:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1479:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1479:2: ( ( 'or' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1480:1: ( 'or' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1480:1: ( 'or' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:2: 'or'
        {
        match(input,72,FOLLOW_72_in_synpred3_InternalResolute3310); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalResolute

    // $ANTLR start synpred4_InternalResolute
    public final void synpred4_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:3: ( ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:4: ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:4: ( () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:5: () ( ( ( 'and' ) ) | ( ( 'andthen' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1554:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1555:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1555:2: ( ( ( 'and' ) ) | ( ( 'andthen' ) ) )
        int alt67=2;
        int LA67_0 = input.LA(1);

        if ( (LA67_0==73) ) {
            alt67=1;
        }
        else if ( (LA67_0==74) ) {
            alt67=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 67, 0, input);

            throw nvae;
        }
        switch (alt67) {
            case 1 :
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1555:3: ( ( 'and' ) )
                {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1555:3: ( ( 'and' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1556:1: ( 'and' )
                {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1556:1: ( 'and' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1557:2: 'and'
                {
                match(input,73,FOLLOW_73_in_synpred4_InternalResolute3493); if (state.failed) return ;

                }


                }


                }
                break;
            case 2 :
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1562:6: ( ( 'andthen' ) )
                {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1562:6: ( ( 'andthen' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1563:1: ( 'andthen' )
                {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1563:1: ( 'andthen' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1564:2: 'andthen'
                {
                match(input,74,FOLLOW_74_in_synpred4_InternalResolute3513); if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred4_InternalResolute

    // $ANTLR start synpred5_InternalResolute
    public final void synpred5_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:3: ( ( () 'instanceof' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:4: ( () 'instanceof' )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:4: ( () 'instanceof' )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:5: () 'instanceof'
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1654:1: 
        {
        }

        match(input,75,FOLLOW_75_in_synpred5_InternalResolute3731); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred5_InternalResolute

    // $ANTLR start synpred6_InternalResolute
    public final void synpred6_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:3: ( ( () ( ( ruleRelationalOp ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:4: ( () ( ( ruleRelationalOp ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:4: ( () ( ( ruleRelationalOp ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:5: () ( ( ruleRelationalOp ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1775:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1776:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1776:2: ( ( ruleRelationalOp ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1777:1: ( ruleRelationalOp )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1777:1: ( ruleRelationalOp )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1778:1: ruleRelationalOp
        {
        pushFollow(FOLLOW_ruleRelationalOp_in_synpred6_InternalResolute4072);
        ruleRelationalOp();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalResolute

    // $ANTLR start synpred7_InternalResolute
    public final void synpred7_InternalResolute_fragment() throws RecognitionException {   
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:3: ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:4: ( () ( ( ( '+' | '-' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:4: ( () ( ( ( '+' | '-' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:5: () ( ( ( '+' | '-' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1852:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1853:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1853:2: ( ( ( '+' | '-' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1854:1: ( ( '+' | '-' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1854:1: ( ( '+' | '-' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1855:1: ( '+' | '-' )
        {
        if ( (input.LA(1)>=78 && input.LA(1)<=79) ) {
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:3: ( ( () ( ( ( '*' | '/' | '%' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:4: ( () ( ( ( '*' | '/' | '%' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:5: () ( ( ( '*' | '/' | '%' ) ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1949:5: ()
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1950:1: 
        {
        }

        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1950:2: ( ( ( '*' | '/' | '%' ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1951:1: ( ( '*' | '/' | '%' ) )
        {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1951:1: ( ( '*' | '/' | '%' ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1952:1: ( '*' | '/' | '%' )
        {
        if ( input.LA(1)==70||(input.LA(1)>=80 && input.LA(1)<=81) ) {
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

    // Delegated rules

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


    protected DFA39 dfa39 = new DFA39(this);
    protected DFA53 dfa53 = new DFA53(this);
    static final String DFA39_eotS =
        "\22\uffff";
    static final String DFA39_eofS =
        "\1\uffff\1\16\20\uffff";
    static final String DFA39_minS =
        "\2\4\11\uffff\1\4\6\uffff";
    static final String DFA39_maxS =
        "\1\u00a1\1\u00a9\11\uffff\1\u00a1\6\uffff";
    static final String DFA39_acceptS =
        "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\16\1"+
        "\17\1\1\1\13\1\14\1\15";
    static final String DFA39_specialS =
        "\22\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\1\1\7\1\5\1\4\7\uffff\1\13\31\uffff\1\12\27\uffff\1\15\21"+
            "\uffff\1\2\1\uffff\1\3\1\10\2\uffff\2\11\2\uffff\1\14\1\uffff"+
            "\71\12\10\uffff\2\6",
            "\1\16\13\uffff\3\16\55\uffff\1\16\1\17\3\16\1\uffff\14\16"+
            "\2\uffff\1\16\2\uffff\2\16\2\uffff\1\16\2\uffff\1\16\71\uffff"+
            "\1\16\20\uffff\1\16",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\4\20\7\uffff\1\20\1\21\30\uffff\1\20\27\uffff\1\20\15\uffff"+
            "\1\20\2\uffff\2\20\1\uffff\2\20\2\uffff\2\20\2\uffff\1\20\1"+
            "\uffff\71\20\10\uffff\2\20",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "2188:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' (otherlv_4= '.' ( (lv_sub_5_0= ruleNestedDotID ) ) )? ) | ( () otherlv_7= 'fail' ( ( (lv_val_8_0= ruleExpr ) ) | (otherlv_9= '**' ( (lv_failmsg_10_0= ruleClaimText ) )+ otherlv_11= '**' ) ) ) | ( () ( (lv_val_13_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_15_0= ruleRealTerm ) ) ) | ( () ( (lv_val_17_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_19_0= ruleStringTerm ) ) ) | ( () otherlv_21= 'if' ( (lv_cond_22_0= ruleExpr ) ) otherlv_23= 'then' ( (lv_then_24_0= ruleExpr ) ) otherlv_25= 'else' ( (lv_else_26_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_28_1= 'forall' | lv_quant_28_2= 'exists' ) ) ) (otherlv_29= '(' ( (lv_args_30_0= ruleArg ) ) otherlv_31= ')' )+ otherlv_32= '.' ( (lv_expr_33_0= ruleExpr ) ) ) | ( () ( (lv_fn_35_0= ruleBuiltInFn ) ) otherlv_36= '(' ( ( (lv_args_37_0= ruleExpr ) ) (otherlv_38= ',' ( (lv_args_39_0= ruleExpr ) ) )* )? otherlv_40= ')' ) | ( () ( (otherlv_42= RULE_ID ) ) otherlv_43= '(' ( ( (lv_args_44_0= ruleExpr ) ) (otherlv_45= ',' ( (lv_args_46_0= ruleExpr ) ) )* )? otherlv_47= ')' ) | (otherlv_48= '{' this_Expr_49= ruleExpr ( ( () otherlv_51= 'for' (otherlv_52= '(' ( (lv_args_53_0= ruleArg ) ) otherlv_54= ')' )+ (otherlv_55= '|' ( (lv_filter_56_0= ruleExpr ) ) )? ) | ( () (otherlv_58= ',' ( (lv_exprs_59_0= ruleExpr ) ) )* ) ) otherlv_60= '}' ) | ( () otherlv_62= '{' otherlv_63= '}' ) | ( () otherlv_65= 'let' ( (lv_binding_66_0= ruleLetBinding ) ) otherlv_67= ';' ( (lv_expr_68_0= ruleExpr ) ) ) | (otherlv_69= '(' this_Expr_70= ruleExpr otherlv_71= ')' ) )";
        }
    }
    static final String DFA53_eotS =
        "\23\uffff";
    static final String DFA53_eofS =
        "\6\uffff\1\15\1\17\1\uffff\1\21\2\uffff\1\15\1\uffff\1\17\3\uffff"+
        "\1\21";
    static final String DFA53_minS =
        "\1\4\5\uffff\2\4\1\uffff\1\102\2\uffff\1\102\1\uffff\1\102\1\uffff"+
        "\1\4\1\uffff\1\102";
    static final String DFA53_maxS =
        "\1\u00a6\5\uffff\2\u00a7\1\uffff\1\u00a9\2\uffff\1\u00a7\1\uffff"+
        "\1\u00a7\1\uffff\1\4\1\uffff\1\u00a7";
    static final String DFA53_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\6\1\uffff\1\11\1\12\1\uffff"+
        "\1\7\1\uffff\1\10\1\uffff\1\13\1\uffff";
    static final String DFA53_specialS =
        "\23\uffff}>";
    static final String[] DFA53_transitionS = {
            "\1\11\1\5\1\6\1\7\71\uffff\1\12\14\uffff\2\10\120\uffff\2\13"+
            "\1\2\1\1\1\uffff\1\4\1\3",
            "",
            "",
            "",
            "",
            "",
            "\1\14\75\uffff\2\15\32\uffff\1\15\74\uffff\1\15\1\uffff\1"+
            "\15\11\uffff\1\10",
            "\1\16\75\uffff\2\17\32\uffff\1\17\74\uffff\1\17\1\uffff\1"+
            "\17\11\uffff\1\10",
            "",
            "\2\21\32\uffff\1\21\74\uffff\1\21\1\uffff\1\21\11\uffff\1"+
            "\10\1\uffff\1\20",
            "",
            "",
            "\2\15\32\uffff\1\15\74\uffff\1\15\1\uffff\1\15\11\uffff\1"+
            "\10",
            "",
            "\2\17\32\uffff\1\17\74\uffff\1\17\1\uffff\1\17\11\uffff\1"+
            "\10",
            "",
            "\1\22",
            "",
            "\2\21\32\uffff\1\21\74\uffff\1\21\1\uffff\1\21\11\uffff\1"+
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
            return "4085:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )";
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
    public static final BitSet FOLLOW_15_in_ruleType451 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleType472 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleType484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBaseType_in_ruleType514 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleType526 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleType547 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleType559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBaseType_in_entryRuleBaseType598 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBaseType608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleBaseType652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleBaseType681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleBaseType710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleBaseType739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleBaseType768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleBaseType797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleBaseType826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleBaseType855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleBaseType884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleBaseType913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleBaseType942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleBaseType971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleBaseType1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleBaseType1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleBaseType1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleBaseType1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleBaseType1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleBaseType1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleBaseType1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleBaseType1203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleBaseType1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleBaseType1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleBaseType1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleBaseType1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleBaseType1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleBaseType1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleBaseType1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleBaseType1435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleBaseType1464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleBaseType1493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleBaseType1522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleBaseType1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleBaseType1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_ruleBaseType1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleBaseType1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleBaseType1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleBaseType1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleBaseType1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleBaseType1754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ruleBaseType1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleBaseType1812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleBaseType1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleBaseType1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleBaseType1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArg_in_entryRuleArg1950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArg1960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg2032 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleArg2049 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleArg2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg2105 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleArg2122 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleArg2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition2180 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantDefinition2190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstantDefinition2232 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleConstantDefinition2249 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleConstantDefinition2270 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleConstantDefinition2282 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleConstantDefinition2303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition2339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionDefinition2349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFunctionDefinition2391 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleFunctionDefinition2408 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000008L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition2430 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleFunctionDefinition2443 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition2464 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_67_in_ruleFunctionDefinition2480 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition2501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody2537 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinitionBody2547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleDefinitionBody2594 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleDefinitionBody2615 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleDefinitionBody2627 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleDefinitionBody2677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleDefinitionBody2690 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleClaimText_in_ruleDefinitionBody2711 = new BitSet(new long[]{0x0000000000000030L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleDefinitionBody2724 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClaimText_in_entryRuleClaimText2783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClaimText2793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleClaimText2845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleClaimText2887 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_ruleClaimText2900 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleClaimText2920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr2961 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr2971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleExpr3017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr3051 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpr3061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_ruleImpliesExpr3108 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleImpliesExpr3159 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr3195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpr_in_entryRuleOrExpr3233 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpr3243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr3290 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleOrExpr3341 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr3377 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_ruleAndExpr_in_entryRuleAndExpr3415 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpr3425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstanceOfExpr_in_ruleAndExpr3472 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_73_in_ruleAndExpr3546 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_74_in_ruleAndExpr3583 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleInstanceOfExpr_in_ruleAndExpr3620 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000600L});
    public static final BitSet FOLLOW_ruleInstanceOfExpr_in_entryRuleInstanceOfExpr3658 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstanceOfExpr3668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleInstanceOfExpr3715 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_ruleInstanceOfExpr3750 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleBaseType_in_ruleInstanceOfExpr3773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp3812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalOp3823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleRelationalOp3861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleRelationalOp3880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleRelationalOp3899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleRelationalOp3918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleRelationalOp3937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleRelationalOp3956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr3996 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpr4006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr4053 = new BitSet(new long[]{0x0000000000060002L,0x0000000000003011L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpr4102 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr4125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusExpr_in_entryRulePlusExpr4163 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusExpr4173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr4220 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_78_in_rulePlusExpr4292 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_79_in_rulePlusExpr4321 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr4360 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
    public static final BitSet FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr4398 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTimesExpr4408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleTimesExpr4455 = new BitSet(new long[]{0x0000000000000002L,0x0000000000030040L});
    public static final BitSet FOLLOW_80_in_ruleTimesExpr4541 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_81_in_ruleTimesExpr4570 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_70_in_ruleTimesExpr4599 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleTimesExpr4638 = new BitSet(new long[]{0x0000000000000002L,0x0000000000030040L});
    public static final BitSet FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr4676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrefixExpr4686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_rulePrefixExpr4741 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_82_in_rulePrefixExpr4770 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr4807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_rulePrefixExpr4836 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleBaseType_in_rulePrefixExpr4857 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_rulePrefixExpr4869 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr4890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_rulePrefixExpr4919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr4954 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomicExpr4964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleAtomicExpr5022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleAtomicExpr5051 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_ruleAtomicExpr5064 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleAtomicExpr5085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleAtomicExpr5116 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8022L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleAtomicExpr5157 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_ruleClaimText_in_ruleAtomicExpr5178 = new BitSet(new long[]{0x0000000000000030L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleAtomicExpr5191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr5231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleAtomicExpr5269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr5307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr5345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ruleAtomicExpr5374 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5395 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_ruleAtomicExpr5407 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5428 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleAtomicExpr5440 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ruleAtomicExpr5498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleAtomicExpr5527 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr5556 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr5577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr5589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100002L});
    public static final BitSet FOLLOW_84_in_ruleAtomicExpr5603 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFn_in_ruleAtomicExpr5662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr5674 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C800AL,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5696 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr5709 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5730 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr5746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAtomicExpr5783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr5795 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C800AL,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5817 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr5830 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5851 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr5867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleAtomicExpr5887 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5909 = new BitSet(new long[]{0x0000000000010000L,0x0000000008000004L});
    public static final BitSet FOLLOW_91_in_ruleAtomicExpr5931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr5944 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr5965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr5977 = new BitSet(new long[]{0x0000000000010000L,0x0000000010000002L});
    public static final BitSet FOLLOW_92_in_ruleAtomicExpr5992 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr6013 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr6045 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr6066 = new BitSet(new long[]{0x0000000000010000L,0x0000000000000004L});
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr6082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleAtomicExpr6111 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr6123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleAtomicExpr6152 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleLetBinding_in_ruleAtomicExpr6173 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleAtomicExpr6185 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr6206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr6226 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr6248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr6259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetBinding_in_entryRuleLetBinding6296 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetBinding6306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLetBinding6348 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleLetBinding6365 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_ruleLetBinding6386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleLetBinding6398 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleLetBinding6419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBuiltInFn_in_entryRuleBuiltInFn6456 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltInFn6467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleBuiltInFn6505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleBuiltInFn6524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleBuiltInFn6543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleBuiltInFn6562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleBuiltInFn6581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_ruleBuiltInFn6600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_ruleBuiltInFn6619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_101_in_ruleBuiltInFn6638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_102_in_ruleBuiltInFn6657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_ruleBuiltInFn6676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_104_in_ruleBuiltInFn6695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_ruleBuiltInFn6714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_ruleBuiltInFn6733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_ruleBuiltInFn6752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_ruleBuiltInFn6771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_ruleBuiltInFn6790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_ruleBuiltInFn6809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_ruleBuiltInFn6828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_ruleBuiltInFn6847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_ruleBuiltInFn6866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_ruleBuiltInFn6885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_ruleBuiltInFn6904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_ruleBuiltInFn6923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_ruleBuiltInFn6942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_118_in_ruleBuiltInFn6961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_ruleBuiltInFn6980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_120_in_ruleBuiltInFn6999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_ruleBuiltInFn7018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_ruleBuiltInFn7037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_ruleBuiltInFn7056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_ruleBuiltInFn7075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_125_in_ruleBuiltInFn7094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_ruleBuiltInFn7113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_127_in_ruleBuiltInFn7132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_128_in_ruleBuiltInFn7151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_ruleBuiltInFn7170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_130_in_ruleBuiltInFn7189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_131_in_ruleBuiltInFn7208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_132_in_ruleBuiltInFn7227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_133_in_ruleBuiltInFn7246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_134_in_ruleBuiltInFn7265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_135_in_ruleBuiltInFn7284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_136_in_ruleBuiltInFn7303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_137_in_ruleBuiltInFn7322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_138_in_ruleBuiltInFn7341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_139_in_ruleBuiltInFn7360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_140_in_ruleBuiltInFn7379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_141_in_ruleBuiltInFn7398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_142_in_ruleBuiltInFn7417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_143_in_ruleBuiltInFn7436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_144_in_ruleBuiltInFn7455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_145_in_ruleBuiltInFn7474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_146_in_ruleBuiltInFn7493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_147_in_ruleBuiltInFn7512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_148_in_ruleBuiltInFn7531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_149_in_ruleBuiltInFn7550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_150_in_ruleBuiltInFn7569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_151_in_ruleBuiltInFn7588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_entryRuleRealTerm7628 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealTerm7638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_ruleRealTerm7684 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRealTerm7704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal7742 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedReal7753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal7792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm7836 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerTerm7846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm7892 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIntegerTerm7912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt7950 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedInt7961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt8000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause8044 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteSubclause8054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_ruleResoluteSubclause8109 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_entryRuleNestedDotID8146 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNestedDotID8156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNestedDotID8201 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_ruleNestedDotID8214 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleNestedDotID_in_ruleNestedDotID8235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProveStatement_in_entryRuleProveStatement8273 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProveStatement8283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_152_in_ruleProveStatement8320 = new BitSet(new long[]{0x00000200000080F0L,0xFFFFFFFFA66C8002L,0x0000000300FFFFFFL});
    public static final BitSet FOLLOW_ruleExpr_in_ruleProveStatement8341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation8379 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainedPropertyAssociation8389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation8437 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L,0x0000000002000000L});
    public static final BitSet FOLLOW_71_in_ruleContainedPropertyAssociation8450 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_153_in_ruleContainedPropertyAssociation8474 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_154_in_ruleContainedPropertyAssociation8506 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8542 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000004L,0x0000000028000000L});
    public static final BitSet FOLLOW_66_in_ruleContainedPropertyAssociation8555 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation8576 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000004L,0x0000000028000000L});
    public static final BitSet FOLLOW_155_in_ruleContainedPropertyAssociation8592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_156_in_ruleContainedPropertyAssociation8604 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8625 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000004L,0x0000000020000000L});
    public static final BitSet FOLLOW_66_in_ruleContainedPropertyAssociation8638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation8659 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000004L,0x0000000020000000L});
    public static final BitSet FOLLOW_157_in_ruleContainedPropertyAssociation8676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_158_in_ruleContainedPropertyAssociation8688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleContainedPropertyAssociation8700 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation8723 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleContainedPropertyAssociation8735 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleContainedPropertyAssociation8749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath8789 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPath8799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath8844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue8881 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue8891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue8937 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_157_in_ruleOptionalModalPropertyValue8950 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_159_in_ruleOptionalModalPropertyValue8962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleOptionalModalPropertyValue8974 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue8994 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleOptionalModalPropertyValue9007 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue9027 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_67_in_ruleOptionalModalPropertyValue9041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue9079 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue9089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyValue9134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression9169 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression9179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_rulePropertyExpression9226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_rulePropertyExpression9253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression9280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_rulePropertyExpression9307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_rulePropertyExpression9334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression9361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_rulePropertyExpression9388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_rulePropertyExpression9415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_rulePropertyExpression9442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression9469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression9496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm9531 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm9541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm9588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral9623 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral9633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_160_in_ruleBooleanLiteral9686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_161_in_ruleBooleanLiteral9717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_entryRuleConstantValue9754 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantValue9764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_ruleConstantValue9811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm9846 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReferenceTerm9856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_162_in_ruleReferenceTerm9893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleReferenceTerm9905 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm9926 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleReferenceTerm9938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm9974 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRecordTerm9984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_163_in_ruleRecordTerm10021 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm10042 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_164_in_ruleRecordTerm10055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm10093 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComputedTerm10103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_165_in_ruleComputedTerm10140 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleComputedTerm10152 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleComputedTerm10169 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleComputedTerm10186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm10222 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleComponentClassifierTerm10232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_166_in_ruleComponentClassifierTerm10269 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleComponentClassifierTerm10281 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm10304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleComponentClassifierTerm10316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListTerm_in_entryRuleListTerm10352 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListTerm10362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleListTerm10408 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C00AL,0x0000006F04000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm10430 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleListTerm10443 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm10464 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000CL});
    public static final BitSet FOLLOW_67_in_ruleListTerm10480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation10516 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFieldPropertyAssociation10526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation10571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleFieldPropertyAssociation10583 = new BitSet(new long[]{0x00000000000000F0L,0x000000000000C002L,0x0000006F04000000L});
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation10604 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_ruleFieldPropertyAssociation10616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement10652 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPathElement10662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleContainmentPathElement10708 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L,0x0000000800000000L});
    public static final BitSet FOLLOW_ruleArrayRange_in_ruleContainmentPathElement10729 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L,0x0000000800000000L});
    public static final BitSet FOLLOW_84_in_ruleContainmentPathElement10744 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPathElement10765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_entryRulePlusMinus10806 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePlusMinus10817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_rulePlusMinus10855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_rulePlusMinus10874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringTerm_in_entryRuleStringTerm10914 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringTerm10924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_ruleStringTerm10969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString11005 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoQuoteString11016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNoQuoteString11055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayRange_in_entryRuleArrayRange11099 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayRange11109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_163_in_ruleArrayRange11155 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange11176 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000009000000000L});
    public static final BitSet FOLLOW_167_in_ruleArrayRange11189 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange11210 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_164_in_ruleArrayRange11224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant11260 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSignedConstant11270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePlusMinus_in_ruleSignedConstant11316 = new BitSet(new long[]{0x00000000000000D0L,0x000000000000C000L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleSignedConstant11337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm11377 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumericRangeTerm11387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11433 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_167_in_ruleNumericRangeTerm11445 = new BitSet(new long[]{0x00000000000000D0L,0x000000000000C000L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11466 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000010000000000L});
    public static final BitSet FOLLOW_168_in_ruleNumericRangeTerm11479 = new BitSet(new long[]{0x00000000000000D0L,0x000000000000C000L});
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm11500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumAlt_in_entryRuleNumAlt11538 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumAlt11548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleNumAlt11595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleNumAlt11622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSignedConstant_in_ruleNumAlt11649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleNumAlt11676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE11712 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleINTVALUE11723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE11762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQPREF_in_entryRuleQPREF11809 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQPREF11820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF11860 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_169_in_ruleQPREF11879 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF11894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQCREF_in_entryRuleQCREF11942 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQCREF11953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF11994 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_169_in_ruleQCREF12012 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF12029 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_ruleQCREF12048 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF12063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSTAR_in_entryRuleSTAR12111 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSTAR12122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleSTAR12159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_synpred1_InternalResolute2001 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_synpred1_InternalResolute2008 = new BitSet(new long[]{0x7FFFFFFFFFF88000L});
    public static final BitSet FOLLOW_ruleType_in_synpred1_InternalResolute2015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_synpred2_InternalResolute3128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_synpred3_InternalResolute3310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_synpred4_InternalResolute3493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_synpred4_InternalResolute3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_synpred5_InternalResolute3731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred6_InternalResolute4072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred7_InternalResolute4239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred8_InternalResolute4474 = new BitSet(new long[]{0x0000000000000002L});

}