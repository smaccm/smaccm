package com.rockwellcollins.atc.resolute.parser.antlr.internal;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;

import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;

@SuppressWarnings("all")
public class InternalResoluteParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>",
            "<UP>", "RULE_ID", "RULE_STRING", "RULE_REAL_LIT", "RULE_INTEGER_LIT",
            "RULE_SL_COMMENT", "RULE_DIGIT", "RULE_EXPONENT", "RULE_INT_EXPONENT",
            "RULE_BASED_INTEGER", "RULE_EXTENDED_DIGIT", "RULE_ANNEXTEXT", "RULE_WS", "'{'", "'}'",
            "'data'", "'thread'", "'thread_group'", "'process'", "'subprogram'",
            "'subprogram_group'", "'processor'", "'virtual_processor'", "'memory'", "'bus'",
            "'virtual_bus'", "'device'", "'system'", "'abstract'", "'connection'", "'component'",
            "'string'", "'int'", "'real'", "'bool'", "':'", "'='", "'('", "','", "')'", "'<='",
            "'**'", "'=>'", "'or'", "'and'", "'<'", "'>'", "'>='", "'<>'", "'in'", "'+'", "'-'",
            "'*'", "'/'", "'union'", "'intersect'", "'not'", "'this'", "'fail'", "'if'", "'then'",
            "'else'", "'forall'", "'exists'", "'.'", "'for'", "'|'", "'connected'",
            "'property_lookup'", "'class_of'", "'type'", "'has_type'", "'bound'", "'contained'",
            "'conn_source'", "'conn_dest'", "'property_exists'", "'sum'", "'analysis'", "'prove'",
            "'+=>'", "'constant'", "'applies'", "'to'", "'binding'", "';'", "'modes'", "'true'",
            "'false'", "'reference'", "'['", "']'", "'compute'", "'classifier'", "'..'", "'delta'",
            "'::'" };
    public static final int RULE_ID = 4;
    public static final int T__29 = 29;
    public static final int T__28 = 28;
    public static final int T__27 = 27;
    public static final int RULE_REAL_LIT = 6;
    public static final int T__26 = 26;
    public static final int T__25 = 25;
    public static final int T__24 = 24;
    public static final int T__23 = 23;
    public static final int T__22 = 22;
    public static final int T__21 = 21;
    public static final int T__20 = 20;
    public static final int EOF = -1;
    public static final int RULE_ANNEXTEXT = 14;
    public static final int T__93 = 93;
    public static final int T__19 = 19;
    public static final int T__94 = 94;
    public static final int T__91 = 91;
    public static final int T__92 = 92;
    public static final int T__16 = 16;
    public static final int T__90 = 90;
    public static final int T__18 = 18;
    public static final int T__17 = 17;
    public static final int T__99 = 99;
    public static final int T__98 = 98;
    public static final int T__97 = 97;
    public static final int T__96 = 96;
    public static final int RULE_EXTENDED_DIGIT = 13;
    public static final int T__95 = 95;
    public static final int T__80 = 80;
    public static final int T__81 = 81;
    public static final int T__82 = 82;
    public static final int T__83 = 83;
    public static final int T__85 = 85;
    public static final int T__84 = 84;
    public static final int T__87 = 87;
    public static final int T__86 = 86;
    public static final int T__89 = 89;
    public static final int T__88 = 88;
    public static final int RULE_STRING = 5;
    public static final int T__71 = 71;
    public static final int T__72 = 72;
    public static final int T__70 = 70;
    public static final int RULE_EXPONENT = 10;
    public static final int T__76 = 76;
    public static final int RULE_DIGIT = 9;
    public static final int T__75 = 75;
    public static final int T__74 = 74;
    public static final int T__73 = 73;
    public static final int T__79 = 79;
    public static final int T__78 = 78;
    public static final int T__77 = 77;
    public static final int T__68 = 68;
    public static final int T__69 = 69;
    public static final int T__66 = 66;
    public static final int T__67 = 67;
    public static final int T__64 = 64;
    public static final int T__65 = 65;
    public static final int RULE_INTEGER_LIT = 7;
    public static final int T__62 = 62;
    public static final int T__63 = 63;
    public static final int T__61 = 61;
    public static final int T__60 = 60;
    public static final int T__55 = 55;
    public static final int T__56 = 56;
    public static final int T__57 = 57;
    public static final int T__58 = 58;
    public static final int T__51 = 51;
    public static final int T__52 = 52;
    public static final int T__53 = 53;
    public static final int T__54 = 54;
    public static final int T__59 = 59;
    public static final int T__50 = 50;
    public static final int T__42 = 42;
    public static final int T__43 = 43;
    public static final int T__40 = 40;
    public static final int T__41 = 41;
    public static final int T__46 = 46;
    public static final int T__47 = 47;
    public static final int T__44 = 44;
    public static final int T__45 = 45;
    public static final int T__48 = 48;
    public static final int T__49 = 49;
    public static final int RULE_BASED_INTEGER = 12;
    public static final int RULE_SL_COMMENT = 8;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int T__32 = 32;
    public static final int T__33 = 33;
    public static final int T__34 = 34;
    public static final int T__35 = 35;
    public static final int T__36 = 36;
    public static final int T__37 = 37;
    public static final int T__38 = 38;
    public static final int T__39 = 39;
    public static final int RULE_INT_EXPONENT = 11;
    public static final int RULE_WS = 15;

    // delegates
    // delegators

    public InternalResoluteParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public InternalResoluteParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);

    }

    public String[] getTokenNames() {
        return InternalResoluteParser.tokenNames;
    }

    public String getGrammarFileName() {
        return "../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g";
    }

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
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:67:1:
    // entryRuleAnnexLibrary returns [EObject current=null] :
    // iv_ruleAnnexLibrary= ruleAnnexLibrary EOF ;
    public final EObject entryRuleAnnexLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnexLibrary = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:68:2:
            // (iv_ruleAnnexLibrary= ruleAnnexLibrary EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:69:2:
            // iv_ruleAnnexLibrary= ruleAnnexLibrary EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getAnnexLibraryRule());
                }
                pushFollow(FOLLOW_ruleAnnexLibrary_in_entryRuleAnnexLibrary75);
                iv_ruleAnnexLibrary = ruleAnnexLibrary();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleAnnexLibrary;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleAnnexLibrary85);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleAnnexLibrary"

    // $ANTLR start "ruleAnnexLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:76:1:
    // ruleAnnexLibrary returns [EObject current=null] : this_ResoluteLibrary_0=
    // ruleResoluteLibrary ;
    public final EObject ruleAnnexLibrary() throws RecognitionException {
        EObject current = null;

        EObject this_ResoluteLibrary_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:79:28:
            // (this_ResoluteLibrary_0= ruleResoluteLibrary )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:81:5:
            // this_ResoluteLibrary_0= ruleResoluteLibrary
            {
                if (state.backtracking == 0) {

                    newCompositeNode(grammarAccess.getAnnexLibraryAccess()
                            .getResoluteLibraryParserRuleCall());

                }
                pushFollow(FOLLOW_ruleResoluteLibrary_in_ruleAnnexLibrary131);
                this_ResoluteLibrary_0 = ruleResoluteLibrary();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current = this_ResoluteLibrary_0;
                    afterParserOrEnumRuleCall();

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleAnnexLibrary"

    // $ANTLR start "entryRuleResoluteLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:103:1:
    // entryRuleResoluteLibrary returns [EObject current=null] :
    // iv_ruleResoluteLibrary= ruleResoluteLibrary EOF ;
    public final EObject entryRuleResoluteLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteLibrary = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:104:2:
            // (iv_ruleResoluteLibrary= ruleResoluteLibrary EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:105:2:
            // iv_ruleResoluteLibrary= ruleResoluteLibrary EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getResoluteLibraryRule());
                }
                pushFollow(FOLLOW_ruleResoluteLibrary_in_entryRuleResoluteLibrary171);
                iv_ruleResoluteLibrary = ruleResoluteLibrary();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleResoluteLibrary;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleResoluteLibrary181);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleResoluteLibrary"

    // $ANTLR start "ruleResoluteLibrary"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:112:1:
    // ruleResoluteLibrary returns [EObject current=null] : ( () (
    // (lv_definitions_1_0= ruleDefinition ) )* ) ;
    public final EObject ruleResoluteLibrary() throws RecognitionException {
        EObject current = null;

        EObject lv_definitions_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:115:28:
            // ( ( () ( (lv_definitions_1_0= ruleDefinition ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:116:1:
            // ( () ( (lv_definitions_1_0= ruleDefinition ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:116:1:
                // ( () ( (lv_definitions_1_0= ruleDefinition ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:116:2:
                // () ( (lv_definitions_1_0= ruleDefinition ) )*
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:116:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:117:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess
                                    .getResoluteLibraryAccess().getResoluteLibraryAction_0(),
                                    current);

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:122:2:
                    // ( (lv_definitions_1_0= ruleDefinition ) )*
                    loop1: do {
                        int alt1 = 2;
                        int LA1_0 = input.LA(1);

                        if ((LA1_0 == RULE_ID)) {
                            alt1 = 1;
                        }

                        switch (alt1) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:123:1:
                        // (lv_definitions_1_0= ruleDefinition )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:123:1:
                            // (lv_definitions_1_0= ruleDefinition )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:124:3:
                            // lv_definitions_1_0= ruleDefinition
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getResoluteLibraryAccess()
                                            .getDefinitionsDefinitionParserRuleCall_1_0());

                                }
                                pushFollow(FOLLOW_ruleDefinition_in_ruleResoluteLibrary236);
                                lv_definitions_1_0 = ruleDefinition();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getResoluteLibraryRule());
                                    }
                                    add(current, "definitions", lv_definitions_1_0, "Definition");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }
                            break;

                        default:
                            break loop1;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleResoluteLibrary"

    // $ANTLR start "entryRuleDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:148:1:
    // entryRuleDefinition returns [EObject current=null] : iv_ruleDefinition=
    // ruleDefinition EOF ;
    public final EObject entryRuleDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinition = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:149:2:
            // (iv_ruleDefinition= ruleDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:150:2:
            // iv_ruleDefinition= ruleDefinition EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getDefinitionRule());
                }
                pushFollow(FOLLOW_ruleDefinition_in_entryRuleDefinition273);
                iv_ruleDefinition = ruleDefinition();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleDefinition;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleDefinition283);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleDefinition"

    // $ANTLR start "ruleDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:157:1:
    // ruleDefinition returns [EObject current=null] :
    // (this_ConstantDefinition_0= ruleConstantDefinition |
    // this_FunctionDefinition_1= ruleFunctionDefinition ) ;
    public final EObject ruleDefinition() throws RecognitionException {
        EObject current = null;

        EObject this_ConstantDefinition_0 = null;

        EObject this_FunctionDefinition_1 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:160:28:
            // ( (this_ConstantDefinition_0= ruleConstantDefinition |
            // this_FunctionDefinition_1= ruleFunctionDefinition ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:161:1:
            // (this_ConstantDefinition_0= ruleConstantDefinition |
            // this_FunctionDefinition_1= ruleFunctionDefinition )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:161:1:
                // (this_ConstantDefinition_0= ruleConstantDefinition |
                // this_FunctionDefinition_1= ruleFunctionDefinition )
                int alt2 = 2;
                int LA2_0 = input.LA(1);

                if ((LA2_0 == RULE_ID)) {
                    int LA2_1 = input.LA(2);

                    if ((LA2_1 == 38)) {
                        alt2 = 1;
                    } else if ((LA2_1 == 40)) {
                        alt2 = 2;
                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return current;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 2, 1, input);

                        throw nvae;
                    }
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 2, 0, input);

                    throw nvae;
                }
                switch (alt2) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:162:5:
                // this_ConstantDefinition_0= ruleConstantDefinition
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getDefinitionAccess()
                                .getConstantDefinitionParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleConstantDefinition_in_ruleDefinition330);
                    this_ConstantDefinition_0 = ruleConstantDefinition();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ConstantDefinition_0;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:172:5:
                // this_FunctionDefinition_1= ruleFunctionDefinition
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getDefinitionAccess()
                                .getFunctionDefinitionParserRuleCall_1());

                    }
                    pushFollow(FOLLOW_ruleFunctionDefinition_in_ruleDefinition357);
                    this_FunctionDefinition_1 = ruleFunctionDefinition();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_FunctionDefinition_1;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleDefinition"

    // $ANTLR start "entryRuleType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:188:1:
    // entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF
    // ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:189:2:
            // (iv_ruleType= ruleType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:190:2:
            // iv_ruleType= ruleType EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getTypeRule());
                }
                pushFollow(FOLLOW_ruleType_in_entryRuleType392);
                iv_ruleType = ruleType();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleType;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleType402);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleType"

    // $ANTLR start "ruleType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:197:1:
    // ruleType returns [EObject current=null] : ( ( () otherlv_1= '{' (
    // (lv_type_2_0= ruleType ) ) otherlv_3= '}' ) | this_BuiltinType_4=
    // ruleBuiltinType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_3 = null;
        EObject lv_type_2_0 = null;

        EObject this_BuiltinType_4 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:200:28:
            // ( ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3=
            // '}' ) | this_BuiltinType_4= ruleBuiltinType ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:1:
            // ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3= '}'
            // ) | this_BuiltinType_4= ruleBuiltinType )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:1:
                // ( ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                // '}' ) | this_BuiltinType_4= ruleBuiltinType )
                int alt3 = 2;
                int LA3_0 = input.LA(1);

                if ((LA3_0 == 16)) {
                    alt3 = 1;
                } else if (((LA3_0 >= 18 && LA3_0 <= 37))) {
                    alt3 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 3, 0, input);

                    throw nvae;
                }
                switch (alt3) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:2:
                // ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                // '}' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:2:
                    // ( () otherlv_1= '{' ( (lv_type_2_0= ruleType ) )
                    // otherlv_3= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:3:
                    // () otherlv_1= '{' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                    // '}'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:201:3:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:202:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess.getTypeAccess()
                                        .getSetTypeAction_0_0(), current);

                            }

                        }

                        otherlv_1 = (Token) match(input, 16, FOLLOW_16_in_ruleType449);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_1, grammarAccess.getTypeAccess()
                                    .getLeftCurlyBracketKeyword_0_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:211:1:
                        // ( (lv_type_2_0= ruleType ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:212:1:
                        // (lv_type_2_0= ruleType )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:212:1:
                            // (lv_type_2_0= ruleType )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:213:3:
                            // lv_type_2_0= ruleType
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getTypeAccess()
                                            .getTypeTypeParserRuleCall_0_2_0());

                                }
                                pushFollow(FOLLOW_ruleType_in_ruleType470);
                                lv_type_2_0 = ruleType();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getTypeRule());
                                    }
                                    set(current, "type", lv_type_2_0, "Type");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_3 = (Token) match(input, 17, FOLLOW_17_in_ruleType482);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getTypeAccess()
                                    .getRightCurlyBracketKeyword_0_3());

                        }

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:235:5:
                // this_BuiltinType_4= ruleBuiltinType
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getTypeAccess()
                                .getBuiltinTypeParserRuleCall_1());

                    }
                    pushFollow(FOLLOW_ruleBuiltinType_in_ruleType511);
                    this_BuiltinType_4 = ruleBuiltinType();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_BuiltinType_4;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleType"

    // $ANTLR start "entryRuleBuiltinType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:251:1:
    // entryRuleBuiltinType returns [EObject current=null] : iv_ruleBuiltinType=
    // ruleBuiltinType EOF ;
    public final EObject entryRuleBuiltinType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuiltinType = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:252:2:
            // (iv_ruleBuiltinType= ruleBuiltinType EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:253:2:
            // iv_ruleBuiltinType= ruleBuiltinType EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getBuiltinTypeRule());
                }
                pushFollow(FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType546);
                iv_ruleBuiltinType = ruleBuiltinType();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleBuiltinType;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleBuiltinType556);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleBuiltinType"

    // $ANTLR start "ruleBuiltinType"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:260:1:
    // ruleBuiltinType returns [EObject current=null] : ( ( (lv_name_0_1= 'data'
    // | lv_name_0_2= 'thread' | lv_name_0_3= 'thread_group' | lv_name_0_4=
    // 'process' | lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' |
    // lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' |
    // lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus'
    // | lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14=
    // 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16= 'component' |
    // lv_name_0_17= 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' |
    // lv_name_0_20= 'bool' ) ) ) ;
    public final EObject ruleBuiltinType() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1 = null;
        Token lv_name_0_2 = null;
        Token lv_name_0_3 = null;
        Token lv_name_0_4 = null;
        Token lv_name_0_5 = null;
        Token lv_name_0_6 = null;
        Token lv_name_0_7 = null;
        Token lv_name_0_8 = null;
        Token lv_name_0_9 = null;
        Token lv_name_0_10 = null;
        Token lv_name_0_11 = null;
        Token lv_name_0_12 = null;
        Token lv_name_0_13 = null;
        Token lv_name_0_14 = null;
        Token lv_name_0_15 = null;
        Token lv_name_0_16 = null;
        Token lv_name_0_17 = null;
        Token lv_name_0_18 = null;
        Token lv_name_0_19 = null;
        Token lv_name_0_20 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:263:28:
            // ( ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3=
            // 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5=
            // 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7=
            // 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9=
            // 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' |
            // lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14=
            // 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16=
            // 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' |
            // lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:264:1:
            // ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3=
            // 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5=
            // 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7=
            // 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9=
            // 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' |
            // lv_name_0_12= 'device' | lv_name_0_13= 'system' | lv_name_0_14=
            // 'abstract' | lv_name_0_15= 'connection' | lv_name_0_16=
            // 'component' | lv_name_0_17= 'string' | lv_name_0_18= 'int' |
            // lv_name_0_19= 'real' | lv_name_0_20= 'bool' ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:264:1:
                // ( ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' |
                // lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' |
                // lv_name_0_5= 'subprogram' | lv_name_0_6= 'subprogram_group' |
                // lv_name_0_7= 'processor' | lv_name_0_8= 'virtual_processor' |
                // lv_name_0_9= 'memory' | lv_name_0_10= 'bus' | lv_name_0_11=
                // 'virtual_bus' | lv_name_0_12= 'device' | lv_name_0_13=
                // 'system' | lv_name_0_14= 'abstract' | lv_name_0_15=
                // 'connection' | lv_name_0_16= 'component' | lv_name_0_17=
                // 'string' | lv_name_0_18= 'int' | lv_name_0_19= 'real' |
                // lv_name_0_20= 'bool' ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:265:1:
                // ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' | lv_name_0_3=
                // 'thread_group' | lv_name_0_4= 'process' | lv_name_0_5=
                // 'subprogram' | lv_name_0_6= 'subprogram_group' | lv_name_0_7=
                // 'processor' | lv_name_0_8= 'virtual_processor' | lv_name_0_9=
                // 'memory' | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus'
                // | lv_name_0_12= 'device' | lv_name_0_13= 'system' |
                // lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' |
                // lv_name_0_16= 'component' | lv_name_0_17= 'string' |
                // lv_name_0_18= 'int' | lv_name_0_19= 'real' | lv_name_0_20=
                // 'bool' ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:265:1:
                    // ( (lv_name_0_1= 'data' | lv_name_0_2= 'thread' |
                    // lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' |
                    // lv_name_0_5= 'subprogram' | lv_name_0_6=
                    // 'subprogram_group' | lv_name_0_7= 'processor' |
                    // lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory'
                    // | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' |
                    // lv_name_0_12= 'device' | lv_name_0_13= 'system' |
                    // lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' |
                    // lv_name_0_16= 'component' | lv_name_0_17= 'string' |
                    // lv_name_0_18= 'int' | lv_name_0_19= 'real' |
                    // lv_name_0_20= 'bool' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1:
                    // (lv_name_0_1= 'data' | lv_name_0_2= 'thread' |
                    // lv_name_0_3= 'thread_group' | lv_name_0_4= 'process' |
                    // lv_name_0_5= 'subprogram' | lv_name_0_6=
                    // 'subprogram_group' | lv_name_0_7= 'processor' |
                    // lv_name_0_8= 'virtual_processor' | lv_name_0_9= 'memory'
                    // | lv_name_0_10= 'bus' | lv_name_0_11= 'virtual_bus' |
                    // lv_name_0_12= 'device' | lv_name_0_13= 'system' |
                    // lv_name_0_14= 'abstract' | lv_name_0_15= 'connection' |
                    // lv_name_0_16= 'component' | lv_name_0_17= 'string' |
                    // lv_name_0_18= 'int' | lv_name_0_19= 'real' |
                    // lv_name_0_20= 'bool' )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:266:1:
                        // (lv_name_0_1= 'data' | lv_name_0_2= 'thread' |
                        // lv_name_0_3= 'thread_group' | lv_name_0_4= 'process'
                        // | lv_name_0_5= 'subprogram' | lv_name_0_6=
                        // 'subprogram_group' | lv_name_0_7= 'processor' |
                        // lv_name_0_8= 'virtual_processor' | lv_name_0_9=
                        // 'memory' | lv_name_0_10= 'bus' | lv_name_0_11=
                        // 'virtual_bus' | lv_name_0_12= 'device' |
                        // lv_name_0_13= 'system' | lv_name_0_14= 'abstract' |
                        // lv_name_0_15= 'connection' | lv_name_0_16=
                        // 'component' | lv_name_0_17= 'string' | lv_name_0_18=
                        // 'int' | lv_name_0_19= 'real' | lv_name_0_20= 'bool' )
                        int alt4 = 20;
                        switch (input.LA(1)) {
                        case 18: {
                            alt4 = 1;
                        }
                            break;
                        case 19: {
                            alt4 = 2;
                        }
                            break;
                        case 20: {
                            alt4 = 3;
                        }
                            break;
                        case 21: {
                            alt4 = 4;
                        }
                            break;
                        case 22: {
                            alt4 = 5;
                        }
                            break;
                        case 23: {
                            alt4 = 6;
                        }
                            break;
                        case 24: {
                            alt4 = 7;
                        }
                            break;
                        case 25: {
                            alt4 = 8;
                        }
                            break;
                        case 26: {
                            alt4 = 9;
                        }
                            break;
                        case 27: {
                            alt4 = 10;
                        }
                            break;
                        case 28: {
                            alt4 = 11;
                        }
                            break;
                        case 29: {
                            alt4 = 12;
                        }
                            break;
                        case 30: {
                            alt4 = 13;
                        }
                            break;
                        case 31: {
                            alt4 = 14;
                        }
                            break;
                        case 32: {
                            alt4 = 15;
                        }
                            break;
                        case 33: {
                            alt4 = 16;
                        }
                            break;
                        case 34: {
                            alt4 = 17;
                        }
                            break;
                        case 35: {
                            alt4 = 18;
                        }
                            break;
                        case 36: {
                            alt4 = 19;
                        }
                            break;
                        case 37: {
                            alt4 = 20;
                        }
                            break;
                        default:
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return current;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 4, 0, input);

                            throw nvae;
                        }

                        switch (alt4) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:267:3:
                        // lv_name_0_1= 'data'
                        {
                            lv_name_0_1 = (Token) match(input, 18, FOLLOW_18_in_ruleBuiltinType600);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_1, grammarAccess.getBuiltinTypeAccess()
                                        .getNameDataKeyword_0_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_1, null);

                            }

                        }
                            break;
                        case 2:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:279:8:
                        // lv_name_0_2= 'thread'
                        {
                            lv_name_0_2 = (Token) match(input, 19, FOLLOW_19_in_ruleBuiltinType629);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_2, grammarAccess.getBuiltinTypeAccess()
                                        .getNameThreadKeyword_0_1());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_2, null);

                            }

                        }
                            break;
                        case 3:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:291:8:
                        // lv_name_0_3= 'thread_group'
                        {
                            lv_name_0_3 = (Token) match(input, 20, FOLLOW_20_in_ruleBuiltinType658);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_3, grammarAccess.getBuiltinTypeAccess()
                                        .getNameThread_groupKeyword_0_2());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_3, null);

                            }

                        }
                            break;
                        case 4:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:303:8:
                        // lv_name_0_4= 'process'
                        {
                            lv_name_0_4 = (Token) match(input, 21, FOLLOW_21_in_ruleBuiltinType687);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_4, grammarAccess.getBuiltinTypeAccess()
                                        .getNameProcessKeyword_0_3());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_4, null);

                            }

                        }
                            break;
                        case 5:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:315:8:
                        // lv_name_0_5= 'subprogram'
                        {
                            lv_name_0_5 = (Token) match(input, 22, FOLLOW_22_in_ruleBuiltinType716);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_5, grammarAccess.getBuiltinTypeAccess()
                                        .getNameSubprogramKeyword_0_4());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_5, null);

                            }

                        }
                            break;
                        case 6:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:327:8:
                        // lv_name_0_6= 'subprogram_group'
                        {
                            lv_name_0_6 = (Token) match(input, 23, FOLLOW_23_in_ruleBuiltinType745);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_6, grammarAccess.getBuiltinTypeAccess()
                                        .getNameSubprogram_groupKeyword_0_5());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_6, null);

                            }

                        }
                            break;
                        case 7:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:339:8:
                        // lv_name_0_7= 'processor'
                        {
                            lv_name_0_7 = (Token) match(input, 24, FOLLOW_24_in_ruleBuiltinType774);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_7, grammarAccess.getBuiltinTypeAccess()
                                        .getNameProcessorKeyword_0_6());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_7, null);

                            }

                        }
                            break;
                        case 8:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:351:8:
                        // lv_name_0_8= 'virtual_processor'
                        {
                            lv_name_0_8 = (Token) match(input, 25, FOLLOW_25_in_ruleBuiltinType803);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_8, grammarAccess.getBuiltinTypeAccess()
                                        .getNameVirtual_processorKeyword_0_7());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_8, null);

                            }

                        }
                            break;
                        case 9:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:363:8:
                        // lv_name_0_9= 'memory'
                        {
                            lv_name_0_9 = (Token) match(input, 26, FOLLOW_26_in_ruleBuiltinType832);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_9, grammarAccess.getBuiltinTypeAccess()
                                        .getNameMemoryKeyword_0_8());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_9, null);

                            }

                        }
                            break;
                        case 10:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:375:8:
                        // lv_name_0_10= 'bus'
                        {
                            lv_name_0_10 = (Token) match(input, 27, FOLLOW_27_in_ruleBuiltinType861);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_10, grammarAccess.getBuiltinTypeAccess()
                                        .getNameBusKeyword_0_9());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_10, null);

                            }

                        }
                            break;
                        case 11:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:387:8:
                        // lv_name_0_11= 'virtual_bus'
                        {
                            lv_name_0_11 = (Token) match(input, 28, FOLLOW_28_in_ruleBuiltinType890);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_11, grammarAccess.getBuiltinTypeAccess()
                                        .getNameVirtual_busKeyword_0_10());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_11, null);

                            }

                        }
                            break;
                        case 12:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:399:8:
                        // lv_name_0_12= 'device'
                        {
                            lv_name_0_12 = (Token) match(input, 29, FOLLOW_29_in_ruleBuiltinType919);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_12, grammarAccess.getBuiltinTypeAccess()
                                        .getNameDeviceKeyword_0_11());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_12, null);

                            }

                        }
                            break;
                        case 13:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:411:8:
                        // lv_name_0_13= 'system'
                        {
                            lv_name_0_13 = (Token) match(input, 30, FOLLOW_30_in_ruleBuiltinType948);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_13, grammarAccess.getBuiltinTypeAccess()
                                        .getNameSystemKeyword_0_12());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_13, null);

                            }

                        }
                            break;
                        case 14:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:423:8:
                        // lv_name_0_14= 'abstract'
                        {
                            lv_name_0_14 = (Token) match(input, 31, FOLLOW_31_in_ruleBuiltinType977);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_14, grammarAccess.getBuiltinTypeAccess()
                                        .getNameAbstractKeyword_0_13());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_14, null);

                            }

                        }
                            break;
                        case 15:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:435:8:
                        // lv_name_0_15= 'connection'
                        {
                            lv_name_0_15 = (Token) match(input, 32,
                                    FOLLOW_32_in_ruleBuiltinType1006);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_15, grammarAccess.getBuiltinTypeAccess()
                                        .getNameConnectionKeyword_0_14());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_15, null);

                            }

                        }
                            break;
                        case 16:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:447:8:
                        // lv_name_0_16= 'component'
                        {
                            lv_name_0_16 = (Token) match(input, 33,
                                    FOLLOW_33_in_ruleBuiltinType1035);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_16, grammarAccess.getBuiltinTypeAccess()
                                        .getNameComponentKeyword_0_15());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_16, null);

                            }

                        }
                            break;
                        case 17:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:459:8:
                        // lv_name_0_17= 'string'
                        {
                            lv_name_0_17 = (Token) match(input, 34,
                                    FOLLOW_34_in_ruleBuiltinType1064);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_17, grammarAccess.getBuiltinTypeAccess()
                                        .getNameStringKeyword_0_16());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_17, null);

                            }

                        }
                            break;
                        case 18:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:471:8:
                        // lv_name_0_18= 'int'
                        {
                            lv_name_0_18 = (Token) match(input, 35,
                                    FOLLOW_35_in_ruleBuiltinType1093);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_18, grammarAccess.getBuiltinTypeAccess()
                                        .getNameIntKeyword_0_17());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_18, null);

                            }

                        }
                            break;
                        case 19:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:483:8:
                        // lv_name_0_19= 'real'
                        {
                            lv_name_0_19 = (Token) match(input, 36,
                                    FOLLOW_36_in_ruleBuiltinType1122);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_19, grammarAccess.getBuiltinTypeAccess()
                                        .getNameRealKeyword_0_18());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getBuiltinTypeRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_19, null);

                            }

                        }
                            break;
                        case 20:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:495:8:
                        // lv_name_0_20= 'bool'
                        {
                            lv_name_0_20 = (Token) match(input, 37,
                                    FOLLOW_37_in_ruleBuiltinType1151);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_20, grammarAccess.getBuiltinTypeAccess()
                                        .getNameBoolKeyword_0_19());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
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

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleBuiltinType"

    // $ANTLR start "entryRuleArg"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:518:1:
    // entryRuleArg returns [EObject current=null] : iv_ruleArg= ruleArg EOF ;
    public final EObject entryRuleArg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArg = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:519:2:
            // (iv_ruleArg= ruleArg EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:520:2:
            // iv_ruleArg= ruleArg EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getArgRule());
                }
                pushFollow(FOLLOW_ruleArg_in_entryRuleArg1202);
                iv_ruleArg = ruleArg();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleArg;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleArg1212);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleArg"

    // $ANTLR start "ruleArg"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:527:1:
    // ruleArg returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) )
    // otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) ) ;
    public final EObject ruleArg() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0 = null;
        Token otherlv_1 = null;
        EObject lv_type_2_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:530:28:
            // ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
            // ruleType ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:531:1:
            // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
            // ruleType ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:531:1:
                // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
                // ruleType ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:531:2:
                // ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
                // ruleType ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:531:2:
                    // ( (lv_name_0_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:532:1:
                    // (lv_name_0_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:532:1:
                        // (lv_name_0_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:533:3:
                        // lv_name_0_0= RULE_ID
                        {
                            lv_name_0_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleArg1254);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_0, grammarAccess.getArgAccess()
                                        .getNameIDTerminalRuleCall_0_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getArgRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_0, "ID");

                            }

                        }

                    }

                    otherlv_1 = (Token) match(input, 38, FOLLOW_38_in_ruleArg1271);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getArgAccess().getColonKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:553:1:
                    // ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:554:1:
                    // (lv_type_2_0= ruleType )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:554:1:
                        // (lv_type_2_0= ruleType )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:555:3:
                        // lv_type_2_0= ruleType
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getArgAccess()
                                        .getTypeTypeParserRuleCall_2_0());

                            }
                            pushFollow(FOLLOW_ruleType_in_ruleArg1292);
                            lv_type_2_0 = ruleType();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getArgRule());
                                }
                                set(current, "type", lv_type_2_0, "Type");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleArg"

    // $ANTLR start "entryRuleConstantDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:579:1:
    // entryRuleConstantDefinition returns [EObject current=null] :
    // iv_ruleConstantDefinition= ruleConstantDefinition EOF ;
    public final EObject entryRuleConstantDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantDefinition = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:580:2:
            // (iv_ruleConstantDefinition= ruleConstantDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:581:2:
            // iv_ruleConstantDefinition= ruleConstantDefinition EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getConstantDefinitionRule());
                }
                pushFollow(FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition1328);
                iv_ruleConstantDefinition = ruleConstantDefinition();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleConstantDefinition;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleConstantDefinition1338);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleConstantDefinition"

    // $ANTLR start "ruleConstantDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:588:1:
    // ruleConstantDefinition returns [EObject current=null] : ( ( (lv_name_0_0=
    // RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' (
    // (lv_expr_4_0= ruleExpr ) ) ) ;
    public final EObject ruleConstantDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:591:28:
            // ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
            // ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:592:1:
            // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
            // ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:592:1:
                // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
                // ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:592:2:
                // ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0=
                // ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:592:2:
                    // ( (lv_name_0_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:593:1:
                    // (lv_name_0_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:593:1:
                        // (lv_name_0_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:594:3:
                        // lv_name_0_0= RULE_ID
                        {
                            lv_name_0_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleConstantDefinition1380);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_0, grammarAccess
                                        .getConstantDefinitionAccess()
                                        .getNameIDTerminalRuleCall_0_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getConstantDefinitionRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_0, "ID");

                            }

                        }

                    }

                    otherlv_1 = (Token) match(input, 38, FOLLOW_38_in_ruleConstantDefinition1397);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getConstantDefinitionAccess()
                                .getColonKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:614:1:
                    // ( (lv_type_2_0= ruleType ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:615:1:
                    // (lv_type_2_0= ruleType )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:615:1:
                        // (lv_type_2_0= ruleType )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:616:3:
                        // lv_type_2_0= ruleType
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getConstantDefinitionAccess()
                                        .getTypeTypeParserRuleCall_2_0());

                            }
                            pushFollow(FOLLOW_ruleType_in_ruleConstantDefinition1418);
                            lv_type_2_0 = ruleType();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getConstantDefinitionRule());
                                }
                                set(current, "type", lv_type_2_0, "Type");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    otherlv_3 = (Token) match(input, 39, FOLLOW_39_in_ruleConstantDefinition1430);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_3, grammarAccess.getConstantDefinitionAccess()
                                .getEqualsSignKeyword_3());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:636:1:
                    // ( (lv_expr_4_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:637:1:
                    // (lv_expr_4_0= ruleExpr )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:637:1:
                        // (lv_expr_4_0= ruleExpr )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:638:3:
                        // lv_expr_4_0= ruleExpr
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getConstantDefinitionAccess()
                                        .getExprExprParserRuleCall_4_0());

                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleConstantDefinition1451);
                            lv_expr_4_0 = ruleExpr();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getConstantDefinitionRule());
                                }
                                set(current, "expr", lv_expr_4_0, "Expr");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleConstantDefinition"

    // $ANTLR start "entryRuleFunctionDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:662:1:
    // entryRuleFunctionDefinition returns [EObject current=null] :
    // iv_ruleFunctionDefinition= ruleFunctionDefinition EOF ;
    public final EObject entryRuleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionDefinition = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:663:2:
            // (iv_ruleFunctionDefinition= ruleFunctionDefinition EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:664:2:
            // iv_ruleFunctionDefinition= ruleFunctionDefinition EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getFunctionDefinitionRule());
                }
                pushFollow(FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition1487);
                iv_ruleFunctionDefinition = ruleFunctionDefinition();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleFunctionDefinition;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleFunctionDefinition1497);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleFunctionDefinition"

    // $ANTLR start "ruleFunctionDefinition"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:671:1:
    // ruleFunctionDefinition returns [EObject current=null] : ( ( (lv_name_0_0=
    // RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ','
    // ( (lv_args_4_0= ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0=
    // ruleDefinitionBody ) ) ) ;
    public final EObject ruleFunctionDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;
        EObject lv_args_2_0 = null;

        EObject lv_args_4_0 = null;

        EObject lv_body_6_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:674:28:
            // ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0=
            // ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
            // otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:675:1:
            // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0=
            // ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
            // otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:675:1:
                // ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( (
                // (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0=
                // ruleArg ) ) )* )? otherlv_5= ')' ( (lv_body_6_0=
                // ruleDefinitionBody ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:675:2:
                // ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '(' ( ( (lv_args_2_0=
                // ruleArg ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )* )?
                // otherlv_5= ')' ( (lv_body_6_0= ruleDefinitionBody ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:675:2:
                    // ( (lv_name_0_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:676:1:
                    // (lv_name_0_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:676:1:
                        // (lv_name_0_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:677:3:
                        // lv_name_0_0= RULE_ID
                        {
                            lv_name_0_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleFunctionDefinition1539);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_name_0_0, grammarAccess
                                        .getFunctionDefinitionAccess()
                                        .getNameIDTerminalRuleCall_0_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getFunctionDefinitionRule());
                                }
                                setWithLastConsumed(current, "name", lv_name_0_0, "ID");

                            }

                        }

                    }

                    otherlv_1 = (Token) match(input, 40, FOLLOW_40_in_ruleFunctionDefinition1556);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getFunctionDefinitionAccess()
                                .getLeftParenthesisKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:697:1:
                    // ( ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' (
                    // (lv_args_4_0= ruleArg ) ) )* )?
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == RULE_ID)) {
                        alt6 = 1;
                    }
                    switch (alt6) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:697:2:
                    // ( (lv_args_2_0= ruleArg ) ) (otherlv_3= ',' (
                    // (lv_args_4_0= ruleArg ) ) )*
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:697:2:
                        // ( (lv_args_2_0= ruleArg ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:698:1:
                        // (lv_args_2_0= ruleArg )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:698:1:
                            // (lv_args_2_0= ruleArg )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:699:3:
                            // lv_args_2_0= ruleArg
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getFunctionDefinitionAccess()
                                            .getArgsArgParserRuleCall_2_0_0());

                                }
                                pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition1578);
                                lv_args_2_0 = ruleArg();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getFunctionDefinitionRule());
                                    }
                                    add(current, "args", lv_args_2_0, "Arg");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:715:2:
                        // (otherlv_3= ',' ( (lv_args_4_0= ruleArg ) ) )*
                        loop5: do {
                            int alt5 = 2;
                            int LA5_0 = input.LA(1);

                            if ((LA5_0 == 41)) {
                                alt5 = 1;
                            }

                            switch (alt5) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:715:4:
                            // otherlv_3= ',' ( (lv_args_4_0= ruleArg ) )
                            {
                                otherlv_3 = (Token) match(input, 41,
                                        FOLLOW_41_in_ruleFunctionDefinition1591);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_3, grammarAccess
                                            .getFunctionDefinitionAccess().getCommaKeyword_2_1_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:719:1:
                                // ( (lv_args_4_0= ruleArg ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:720:1:
                                // (lv_args_4_0= ruleArg )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:720:1:
                                    // (lv_args_4_0= ruleArg )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:721:3:
                                    // lv_args_4_0= ruleArg
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getFunctionDefinitionAccess()
                                                    .getArgsArgParserRuleCall_2_1_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleArg_in_ruleFunctionDefinition1612);
                                        lv_args_4_0 = ruleArg();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getFunctionDefinitionRule());
                                            }
                                            add(current, "args", lv_args_4_0, "Arg");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                            }
                                break;

                            default:
                                break loop5;
                            }
                        } while (true);

                    }
                        break;

                    }

                    otherlv_5 = (Token) match(input, 42, FOLLOW_42_in_ruleFunctionDefinition1628);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_5, grammarAccess.getFunctionDefinitionAccess()
                                .getRightParenthesisKeyword_3());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:741:1:
                    // ( (lv_body_6_0= ruleDefinitionBody ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:742:1:
                    // (lv_body_6_0= ruleDefinitionBody )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:742:1:
                        // (lv_body_6_0= ruleDefinitionBody )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:743:3:
                        // lv_body_6_0= ruleDefinitionBody
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getFunctionDefinitionAccess()
                                        .getBodyDefinitionBodyParserRuleCall_4_0());

                            }
                            pushFollow(FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition1649);
                            lv_body_6_0 = ruleDefinitionBody();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getFunctionDefinitionRule());
                                }
                                set(current, "body", lv_body_6_0, "DefinitionBody");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleFunctionDefinition"

    // $ANTLR start "entryRuleClaimString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:767:1:
    // entryRuleClaimString returns [EObject current=null] : iv_ruleClaimString=
    // ruleClaimString EOF ;
    public final EObject entryRuleClaimString() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClaimString = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:768:2:
            // (iv_ruleClaimString= ruleClaimString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:769:2:
            // iv_ruleClaimString= ruleClaimString EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getClaimStringRule());
                }
                pushFollow(FOLLOW_ruleClaimString_in_entryRuleClaimString1685);
                iv_ruleClaimString = ruleClaimString();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleClaimString;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleClaimString1695);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleClaimString"

    // $ANTLR start "ruleClaimString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:776:1:
    // ruleClaimString returns [EObject current=null] : ( ( () ( (lv_str_1_0=
    // RULE_STRING ) ) ) | ( () ( (otherlv_3= RULE_ID ) ) ) ) ;
    public final EObject ruleClaimString() throws RecognitionException {
        EObject current = null;

        Token lv_str_1_0 = null;
        Token otherlv_3 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:779:28:
            // ( ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3=
            // RULE_ID ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:1:
            // ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3=
            // RULE_ID ) ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:1:
                // ( ( () ( (lv_str_1_0= RULE_STRING ) ) ) | ( () ( (otherlv_3=
                // RULE_ID ) ) ) )
                int alt7 = 2;
                int LA7_0 = input.LA(1);

                if ((LA7_0 == RULE_STRING)) {
                    alt7 = 1;
                } else if ((LA7_0 == RULE_ID)) {
                    alt7 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 7, 0, input);

                    throw nvae;
                }
                switch (alt7) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:2:
                // ( () ( (lv_str_1_0= RULE_STRING ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:2:
                    // ( () ( (lv_str_1_0= RULE_STRING ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:3:
                    // () ( (lv_str_1_0= RULE_STRING ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:780:3:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:781:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getClaimStringAccess().getClaimStringAction_0_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:786:2:
                        // ( (lv_str_1_0= RULE_STRING ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:787:1:
                        // (lv_str_1_0= RULE_STRING )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:787:1:
                            // (lv_str_1_0= RULE_STRING )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:788:3:
                            // lv_str_1_0= RULE_STRING
                            {
                                lv_str_1_0 = (Token) match(input, RULE_STRING,
                                        FOLLOW_RULE_STRING_in_ruleClaimString1747);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(lv_str_1_0, grammarAccess.getClaimStringAccess()
                                            .getStrSTRINGTerminalRuleCall_0_1_0());

                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getClaimStringRule());
                                    }
                                    setWithLastConsumed(current, "str", lv_str_1_0, "STRING");

                                }

                            }

                        }

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:805:6:
                // ( () ( (otherlv_3= RULE_ID ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:805:6:
                    // ( () ( (otherlv_3= RULE_ID ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:805:7:
                    // () ( (otherlv_3= RULE_ID ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:805:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:806:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getClaimStringAccess().getClaimArgAction_1_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:811:2:
                        // ( (otherlv_3= RULE_ID ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:812:1:
                        // (otherlv_3= RULE_ID )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:812:1:
                            // (otherlv_3= RULE_ID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:813:3:
                            // otherlv_3= RULE_ID
                            {
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getClaimStringRule());
                                    }

                                }
                                otherlv_3 = (Token) match(input, RULE_ID,
                                        FOLLOW_RULE_ID_in_ruleClaimString1789);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_3, grammarAccess.getClaimStringAccess()
                                            .getArgArgCrossReference_1_1_0());

                                }

                            }

                        }

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleClaimString"

    // $ANTLR start "entryRuleDefinitionBody"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:832:1:
    // entryRuleDefinitionBody returns [EObject current=null] :
    // iv_ruleDefinitionBody= ruleDefinitionBody EOF ;
    public final EObject entryRuleDefinitionBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefinitionBody = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:833:2:
            // (iv_ruleDefinitionBody= ruleDefinitionBody EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:834:2:
            // iv_ruleDefinitionBody= ruleDefinitionBody EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getDefinitionBodyRule());
                }
                pushFollow(FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody1826);
                iv_ruleDefinitionBody = ruleDefinitionBody();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleDefinitionBody;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleDefinitionBody1836);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleDefinitionBody"

    // $ANTLR start "ruleDefinitionBody"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:841:1:
    // ruleDefinitionBody returns [EObject current=null] : ( ( () otherlv_1= ':'
    // ( (lv_type_2_0= ruleType ) ) otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) )
    // ) | ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0=
    // ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) )
    // ;
    public final EObject ruleDefinitionBody() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_6 = null;
        Token otherlv_7 = null;
        Token otherlv_9 = null;
        EObject lv_type_2_0 = null;

        EObject lv_expr_4_0 = null;

        EObject lv_claim_8_0 = null;

        EObject lv_expr_10_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:844:28:
            // ( ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3=
            // '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<='
            // (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9=
            // '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:1:
            // ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3= '='
            // ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<=' (otherlv_7=
            // '**' ( (lv_claim_8_0= ruleClaimString ) )* otherlv_9= '**' )? (
            // (lv_expr_10_0= ruleExpr ) ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:1:
                // ( ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                // '=' ( (lv_expr_4_0= ruleExpr ) ) ) | ( () otherlv_6= '<='
                // (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )*
                // otherlv_9= '**' )? ( (lv_expr_10_0= ruleExpr ) ) ) )
                int alt10 = 2;
                int LA10_0 = input.LA(1);

                if ((LA10_0 == 38)) {
                    alt10 = 1;
                } else if ((LA10_0 == 43)) {
                    alt10 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 10, 0, input);

                    throw nvae;
                }
                switch (alt10) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:2:
                // ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                // '=' ( (lv_expr_4_0= ruleExpr ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:2:
                    // ( () otherlv_1= ':' ( (lv_type_2_0= ruleType ) )
                    // otherlv_3= '=' ( (lv_expr_4_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:3:
                    // () otherlv_1= ':' ( (lv_type_2_0= ruleType ) ) otherlv_3=
                    // '=' ( (lv_expr_4_0= ruleExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:845:3:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:846:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getDefinitionBodyAccess().getFuncBodyAction_0_0(), current);

                            }

                        }

                        otherlv_1 = (Token) match(input, 38, FOLLOW_38_in_ruleDefinitionBody1883);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_1, grammarAccess.getDefinitionBodyAccess()
                                    .getColonKeyword_0_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:855:1:
                        // ( (lv_type_2_0= ruleType ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:856:1:
                        // (lv_type_2_0= ruleType )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:856:1:
                            // (lv_type_2_0= ruleType )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:857:3:
                            // lv_type_2_0= ruleType
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getDefinitionBodyAccess()
                                            .getTypeTypeParserRuleCall_0_2_0());

                                }
                                pushFollow(FOLLOW_ruleType_in_ruleDefinitionBody1904);
                                lv_type_2_0 = ruleType();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getDefinitionBodyRule());
                                    }
                                    set(current, "type", lv_type_2_0, "Type");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_3 = (Token) match(input, 39, FOLLOW_39_in_ruleDefinitionBody1916);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getDefinitionBodyAccess()
                                    .getEqualsSignKeyword_0_3());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:877:1:
                        // ( (lv_expr_4_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:878:1:
                        // (lv_expr_4_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:878:1:
                            // (lv_expr_4_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:879:3:
                            // lv_expr_4_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getDefinitionBodyAccess()
                                            .getExprExprParserRuleCall_0_4_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody1937);
                                lv_expr_4_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getDefinitionBodyRule());
                                    }
                                    set(current, "expr", lv_expr_4_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:896:6:
                // ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0=
                // ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0=
                // ruleExpr ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:896:6:
                    // ( () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0=
                    // ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0=
                    // ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:896:7:
                    // () otherlv_6= '<=' (otherlv_7= '**' ( (lv_claim_8_0=
                    // ruleClaimString ) )* otherlv_9= '**' )? ( (lv_expr_10_0=
                    // ruleExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:896:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:897:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getDefinitionBodyAccess().getClaimBodyAction_1_0(),
                                        current);

                            }

                        }

                        otherlv_6 = (Token) match(input, 43, FOLLOW_43_in_ruleDefinitionBody1966);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_6, grammarAccess.getDefinitionBodyAccess()
                                    .getLessThanSignEqualsSignKeyword_1_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:1:
                        // (otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString )
                        // )* otherlv_9= '**' )?
                        int alt9 = 2;
                        int LA9_0 = input.LA(1);

                        if ((LA9_0 == 44)) {
                            alt9 = 1;
                        }
                        switch (alt9) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:906:3:
                        // otherlv_7= '**' ( (lv_claim_8_0= ruleClaimString ) )*
                        // otherlv_9= '**'
                        {
                            otherlv_7 = (Token) match(input, 44,
                                    FOLLOW_44_in_ruleDefinitionBody1979);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_7, grammarAccess.getDefinitionBodyAccess()
                                        .getAsteriskAsteriskKeyword_1_2_0());

                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:910:1:
                            // ( (lv_claim_8_0= ruleClaimString ) )*
                            loop8: do {
                                int alt8 = 2;
                                int LA8_0 = input.LA(1);

                                if (((LA8_0 >= RULE_ID && LA8_0 <= RULE_STRING))) {
                                    alt8 = 1;
                                }

                                switch (alt8) {
                                case 1:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:911:1:
                                // (lv_claim_8_0= ruleClaimString )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:911:1:
                                    // (lv_claim_8_0= ruleClaimString )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:912:3:
                                    // lv_claim_8_0= ruleClaimString
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getDefinitionBodyAccess()
                                                    .getClaimClaimStringParserRuleCall_1_2_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleClaimString_in_ruleDefinitionBody2000);
                                        lv_claim_8_0 = ruleClaimString();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getDefinitionBodyRule());
                                            }
                                            add(current, "claim", lv_claim_8_0, "ClaimString");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }
                                    break;

                                default:
                                    break loop8;
                                }
                            } while (true);

                            otherlv_9 = (Token) match(input, 44,
                                    FOLLOW_44_in_ruleDefinitionBody2013);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_9, grammarAccess.getDefinitionBodyAccess()
                                        .getAsteriskAsteriskKeyword_1_2_2());

                            }

                        }
                            break;

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:932:3:
                        // ( (lv_expr_10_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:933:1:
                        // (lv_expr_10_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:933:1:
                            // (lv_expr_10_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:934:3:
                            // lv_expr_10_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getDefinitionBodyAccess()
                                            .getExprExprParserRuleCall_1_3_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleDefinitionBody2036);
                                lv_expr_10_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getDefinitionBodyRule());
                                    }
                                    set(current, "expr", lv_expr_10_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleDefinitionBody"

    // $ANTLR start "entryRuleExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:958:1:
    // entryRuleExpr returns [EObject current=null] : iv_ruleExpr= ruleExpr EOF
    // ;
    public final EObject entryRuleExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:959:2:
            // (iv_ruleExpr= ruleExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:960:2:
            // iv_ruleExpr= ruleExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getExprRule());
                }
                pushFollow(FOLLOW_ruleExpr_in_entryRuleExpr2073);
                iv_ruleExpr = ruleExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleExpr2083);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleExpr"

    // $ANTLR start "ruleExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:967:1:
    // ruleExpr returns [EObject current=null] : this_ImpliesExpr_0=
    // ruleImpliesExpr ;
    public final EObject ruleExpr() throws RecognitionException {
        EObject current = null;

        EObject this_ImpliesExpr_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:970:28:
            // (this_ImpliesExpr_0= ruleImpliesExpr )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:972:5:
            // this_ImpliesExpr_0= ruleImpliesExpr
            {
                if (state.backtracking == 0) {

                    newCompositeNode(grammarAccess.getExprAccess().getImpliesExprParserRuleCall());

                }
                pushFollow(FOLLOW_ruleImpliesExpr_in_ruleExpr2129);
                this_ImpliesExpr_0 = ruleImpliesExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current = this_ImpliesExpr_0;
                    afterParserOrEnumRuleCall();

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleExpr"

    // $ANTLR start "entryRuleImpliesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:988:1:
    // entryRuleImpliesExpr returns [EObject current=null] : iv_ruleImpliesExpr=
    // ruleImpliesExpr EOF ;
    public final EObject entryRuleImpliesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:989:2:
            // (iv_ruleImpliesExpr= ruleImpliesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:990:2:
            // iv_ruleImpliesExpr= ruleImpliesExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getImpliesExprRule());
                }
                pushFollow(FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2163);
                iv_ruleImpliesExpr = ruleImpliesExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleImpliesExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleImpliesExpr2173);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleImpliesExpr"

    // $ANTLR start "ruleImpliesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:997:1:
    // ruleImpliesExpr returns [EObject current=null] : (this_OrExpr_0=
    // ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) ) )
    // ( (lv_right_3_0= ruleImpliesExpr ) ) )? ) ;
    public final EObject ruleImpliesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0 = null;
        EObject this_OrExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1000:28:
            // ( (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () (
            // (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            // ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1001:1:
            // (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( () (
            // (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
            // )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1001:1:
                // (this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( ()
                // ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr )
                // ) )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1002:5:
                // this_OrExpr_0= ruleOrExpr ( ( ( ( () ( ( '=>' ) ) ) )=> ( ()
                // ( (lv_op_2_0= '=>' ) ) ) ) ( (lv_right_3_0= ruleImpliesExpr )
                // ) )?
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getImpliesExprAccess()
                                .getOrExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleOrExpr_in_ruleImpliesExpr2220);
                    this_OrExpr_0 = ruleOrExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_OrExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:1:
                    // ( ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) )
                    // ) ) ( (lv_right_3_0= ruleImpliesExpr ) ) )?
                    int alt11 = 2;
                    int LA11_0 = input.LA(1);

                    if ((LA11_0 == 45)) {
                        int LA11_1 = input.LA(2);

                        if ((synpred1_InternalResolute())) {
                            alt11 = 1;
                        }
                    }
                    switch (alt11) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:2:
                    // ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) ) )
                    // ) ( (lv_right_3_0= ruleImpliesExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:2:
                        // ( ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' )
                        // ) ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:3:
                        // ( ( () ( ( '=>' ) ) ) )=> ( () ( (lv_op_2_0= '=>' ) )
                        // )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1017:6:
                            // ( () ( (lv_op_2_0= '=>' ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1017:7:
                            // () ( (lv_op_2_0= '=>' ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1017:7:
                                // ()
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1018:5:
                                {
                                    if (state.backtracking == 0) {

                                        current = forceCreateModelElementAndSet(grammarAccess
                                                .getImpliesExprAccess()
                                                .getBinaryExprLeftAction_1_0_0_0(), current);

                                    }

                                }

                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1023:2:
                                // ( (lv_op_2_0= '=>' ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1024:1:
                                // (lv_op_2_0= '=>' )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1024:1:
                                    // (lv_op_2_0= '=>' )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1025:3:
                                    // lv_op_2_0= '=>'
                                    {
                                        lv_op_2_0 = (Token) match(input, 45,
                                                FOLLOW_45_in_ruleImpliesExpr2271);
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            newLeafNode(
                                                    lv_op_2_0,
                                                    grammarAccess
                                                            .getImpliesExprAccess()
                                                            .getOpEqualsSignGreaterThanSignKeyword_1_0_0_1_0());

                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElement(grammarAccess
                                                        .getImpliesExprRule());
                                            }
                                            setWithLastConsumed(current, "op", lv_op_2_0, "=>");

                                        }

                                    }

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1038:4:
                        // ( (lv_right_3_0= ruleImpliesExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1039:1:
                        // (lv_right_3_0= ruleImpliesExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1039:1:
                            // (lv_right_3_0= ruleImpliesExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1040:3:
                            // lv_right_3_0= ruleImpliesExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getImpliesExprAccess()
                                            .getRightImpliesExprParserRuleCall_1_1_0());

                                }
                                pushFollow(FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr2307);
                                lv_right_3_0 = ruleImpliesExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getImpliesExprRule());
                                    }
                                    set(current, "right", lv_right_3_0, "ImpliesExpr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleImpliesExpr"

    // $ANTLR start "entryRuleOrExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1064:1:
    // entryRuleOrExpr returns [EObject current=null] : iv_ruleOrExpr=
    // ruleOrExpr EOF ;
    public final EObject entryRuleOrExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1065:2:
            // (iv_ruleOrExpr= ruleOrExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1066:2:
            // iv_ruleOrExpr= ruleOrExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getOrExprRule());
                }
                pushFollow(FOLLOW_ruleOrExpr_in_entryRuleOrExpr2345);
                iv_ruleOrExpr = ruleOrExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleOrExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleOrExpr2355);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleOrExpr"

    // $ANTLR start "ruleOrExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1073:1:
    // ruleOrExpr returns [EObject current=null] : (this_AndExpr_0= ruleAndExpr
    // ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) ) ) ) (
    // (lv_right_3_0= ruleAndExpr ) ) )* ) ;
    public final EObject ruleOrExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0 = null;
        EObject this_AndExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1076:28:
            // ( (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( ()
            // ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            // )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1077:1:
            // (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> ( () (
            // (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1077:1:
                // (this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> (
                // () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr )
                // ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1078:5:
                // this_AndExpr_0= ruleAndExpr ( ( ( ( () ( ( 'or' ) ) ) )=> (
                // () ( (lv_op_2_0= 'or' ) ) ) ) ( (lv_right_3_0= ruleAndExpr )
                // ) )*
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getOrExprAccess()
                                .getAndExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr2402);
                    this_AndExpr_0 = ruleAndExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_AndExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:1:
                    // ( ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' ) )
                    // ) ) ( (lv_right_3_0= ruleAndExpr ) ) )*
                    loop12: do {
                        int alt12 = 2;
                        int LA12_0 = input.LA(1);

                        if ((LA12_0 == 46)) {
                            int LA12_2 = input.LA(2);

                            if ((synpred2_InternalResolute())) {
                                alt12 = 1;
                            }

                        }

                        switch (alt12) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:2:
                        // ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or' )
                        // ) ) ) ( (lv_right_3_0= ruleAndExpr ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:2:
                            // ( ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0=
                            // 'or' ) ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:3:
                            // ( ( () ( ( 'or' ) ) ) )=> ( () ( (lv_op_2_0= 'or'
                            // ) ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:6:
                                // ( () ( (lv_op_2_0= 'or' ) ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:7:
                                // () ( (lv_op_2_0= 'or' ) )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1093:7:
                                    // ()
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1094:5:
                                    {
                                        if (state.backtracking == 0) {

                                            current = forceCreateModelElementAndSet(grammarAccess
                                                    .getOrExprAccess()
                                                    .getBinaryExprLeftAction_1_0_0_0(), current);

                                        }

                                    }

                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1099:2:
                                    // ( (lv_op_2_0= 'or' ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1100:1:
                                    // (lv_op_2_0= 'or' )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1100:1:
                                        // (lv_op_2_0= 'or' )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1101:3:
                                        // lv_op_2_0= 'or'
                                        {
                                            lv_op_2_0 = (Token) match(input, 46,
                                                    FOLLOW_46_in_ruleOrExpr2453);
                                            if (state.failed) {
                                                return current;
                                            }
                                            if (state.backtracking == 0) {

                                                newLeafNode(lv_op_2_0, grammarAccess
                                                        .getOrExprAccess()
                                                        .getOpOrKeyword_1_0_0_1_0());

                                            }
                                            if (state.backtracking == 0) {

                                                if (current == null) {
                                                    current = createModelElement(grammarAccess
                                                            .getOrExprRule());
                                                }
                                                setWithLastConsumed(current, "op", lv_op_2_0, "or");

                                            }

                                        }

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1114:4:
                            // ( (lv_right_3_0= ruleAndExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1115:1:
                            // (lv_right_3_0= ruleAndExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1115:1:
                                // (lv_right_3_0= ruleAndExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1116:3:
                                // lv_right_3_0= ruleAndExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getOrExprAccess()
                                                .getRightAndExprParserRuleCall_1_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleAndExpr_in_ruleOrExpr2489);
                                    lv_right_3_0 = ruleAndExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getOrExprRule());
                                        }
                                        set(current, "right", lv_right_3_0, "AndExpr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop12;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleOrExpr"

    // $ANTLR start "entryRuleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1140:1:
    // entryRuleAndExpr returns [EObject current=null] : iv_ruleAndExpr=
    // ruleAndExpr EOF ;
    public final EObject entryRuleAndExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1141:2:
            // (iv_ruleAndExpr= ruleAndExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1142:2:
            // iv_ruleAndExpr= ruleAndExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getAndExprRule());
                }
                pushFollow(FOLLOW_ruleAndExpr_in_entryRuleAndExpr2527);
                iv_ruleAndExpr = ruleAndExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleAndExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleAndExpr2537);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleAndExpr"

    // $ANTLR start "ruleAndExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1149:1:
    // ruleAndExpr returns [EObject current=null] : (this_RelationalExpr_0=
    // ruleRelationalExpr ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0=
    // 'and' ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )* ) ;
    public final EObject ruleAndExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0 = null;
        EObject this_RelationalExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1152:28:
            // ( (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and'
            // ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0=
            // ruleRelationalExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:1:
            // (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( ( 'and' )
            // ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) ( (lv_right_3_0=
            // ruleRelationalExpr ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1153:1:
                // (this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( (
                // 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) (
                // (lv_right_3_0= ruleRelationalExpr ) ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1154:5:
                // this_RelationalExpr_0= ruleRelationalExpr ( ( ( ( () ( (
                // 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' ) ) ) ) (
                // (lv_right_3_0= ruleRelationalExpr ) ) )*
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getAndExprAccess()
                                .getRelationalExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr2584);
                    this_RelationalExpr_0 = ruleRelationalExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_RelationalExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:1:
                    // ( ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and' )
                    // ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) ) )*
                    loop13: do {
                        int alt13 = 2;
                        int LA13_0 = input.LA(1);

                        if ((LA13_0 == 47)) {
                            int LA13_2 = input.LA(2);

                            if ((synpred3_InternalResolute())) {
                                alt13 = 1;
                            }

                        }

                        switch (alt13) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:2:
                        // ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0= 'and'
                        // ) ) ) ) ( (lv_right_3_0= ruleRelationalExpr ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:2:
                            // ( ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0=
                            // 'and' ) ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:3:
                            // ( ( () ( ( 'and' ) ) ) )=> ( () ( (lv_op_2_0=
                            // 'and' ) ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1169:6:
                                // ( () ( (lv_op_2_0= 'and' ) ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1169:7:
                                // () ( (lv_op_2_0= 'and' ) )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1169:7:
                                    // ()
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1170:5:
                                    {
                                        if (state.backtracking == 0) {

                                            current = forceCreateModelElementAndSet(grammarAccess
                                                    .getAndExprAccess()
                                                    .getBinaryExprLeftAction_1_0_0_0(), current);

                                        }

                                    }

                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1175:2:
                                    // ( (lv_op_2_0= 'and' ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1176:1:
                                    // (lv_op_2_0= 'and' )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1176:1:
                                        // (lv_op_2_0= 'and' )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1177:3:
                                        // lv_op_2_0= 'and'
                                        {
                                            lv_op_2_0 = (Token) match(input, 47,
                                                    FOLLOW_47_in_ruleAndExpr2635);
                                            if (state.failed) {
                                                return current;
                                            }
                                            if (state.backtracking == 0) {

                                                newLeafNode(lv_op_2_0, grammarAccess
                                                        .getAndExprAccess()
                                                        .getOpAndKeyword_1_0_0_1_0());

                                            }
                                            if (state.backtracking == 0) {

                                                if (current == null) {
                                                    current = createModelElement(grammarAccess
                                                            .getAndExprRule());
                                                }
                                                setWithLastConsumed(current, "op", lv_op_2_0, "and");

                                            }

                                        }

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1190:4:
                            // ( (lv_right_3_0= ruleRelationalExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1191:1:
                            // (lv_right_3_0= ruleRelationalExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1191:1:
                                // (lv_right_3_0= ruleRelationalExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1192:3:
                                // lv_right_3_0= ruleRelationalExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getAndExprAccess()
                                                .getRightRelationalExprParserRuleCall_1_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleRelationalExpr_in_ruleAndExpr2671);
                                    lv_right_3_0 = ruleRelationalExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getAndExprRule());
                                        }
                                        set(current, "right", lv_right_3_0, "RelationalExpr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop13;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleAndExpr"

    // $ANTLR start "entryRuleRelationalOp"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1216:1:
    // entryRuleRelationalOp returns [String current=null] :
    // iv_ruleRelationalOp= ruleRelationalOp EOF ;
    public final String entryRuleRelationalOp() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationalOp = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1217:2:
            // (iv_ruleRelationalOp= ruleRelationalOp EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1218:2:
            // iv_ruleRelationalOp= ruleRelationalOp EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getRelationalOpRule());
                }
                pushFollow(FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp2710);
                iv_ruleRelationalOp = ruleRelationalOp();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleRelationalOp.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleRelationalOp2721);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleRelationalOp"

    // $ANTLR start "ruleRelationalOp"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1225:1:
    // ruleRelationalOp returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' |
    // kw= '=' | kw= '<>' | kw= 'in' ) ;
    public final AntlrDatatypeRuleToken ruleRelationalOp() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1228:28:
            // ( (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' |
            // kw= 'in' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1229:1:
            // (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>' |
            // kw= 'in' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1229:1:
                // (kw= '<' | kw= '<=' | kw= '>' | kw= '>=' | kw= '=' | kw= '<>'
                // | kw= 'in' )
                int alt14 = 7;
                switch (input.LA(1)) {
                case 48: {
                    alt14 = 1;
                }
                    break;
                case 43: {
                    alt14 = 2;
                }
                    break;
                case 49: {
                    alt14 = 3;
                }
                    break;
                case 50: {
                    alt14 = 4;
                }
                    break;
                case 39: {
                    alt14 = 5;
                }
                    break;
                case 51: {
                    alt14 = 6;
                }
                    break;
                case 52: {
                    alt14 = 7;
                }
                    break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 14, 0, input);

                    throw nvae;
                }

                switch (alt14) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1230:2:
                // kw= '<'
                {
                    kw = (Token) match(input, 48, FOLLOW_48_in_ruleRelationalOp2759);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getLessThanSignKeyword_0());

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1237:2:
                // kw= '<='
                {
                    kw = (Token) match(input, 43, FOLLOW_43_in_ruleRelationalOp2778);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getLessThanSignEqualsSignKeyword_1());

                    }

                }
                    break;
                case 3:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1244:2:
                // kw= '>'
                {
                    kw = (Token) match(input, 49, FOLLOW_49_in_ruleRelationalOp2797);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getGreaterThanSignKeyword_2());

                    }

                }
                    break;
                case 4:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1251:2:
                // kw= '>='
                {
                    kw = (Token) match(input, 50, FOLLOW_50_in_ruleRelationalOp2816);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getGreaterThanSignEqualsSignKeyword_3());

                    }

                }
                    break;
                case 5:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1258:2:
                // kw= '='
                {
                    kw = (Token) match(input, 39, FOLLOW_39_in_ruleRelationalOp2835);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getEqualsSignKeyword_4());

                    }

                }
                    break;
                case 6:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1265:2:
                // kw= '<>'
                {
                    kw = (Token) match(input, 51, FOLLOW_51_in_ruleRelationalOp2854);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess()
                                .getLessThanSignGreaterThanSignKeyword_5());

                    }

                }
                    break;
                case 7:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1272:2:
                // kw= 'in'
                {
                    kw = (Token) match(input, 52, FOLLOW_52_in_ruleRelationalOp2873);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getRelationalOpAccess().getInKeyword_6());

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleRelationalOp"

    // $ANTLR start "entryRuleRelationalExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1285:1:
    // entryRuleRelationalExpr returns [EObject current=null] :
    // iv_ruleRelationalExpr= ruleRelationalExpr EOF ;
    public final EObject entryRuleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1286:2:
            // (iv_ruleRelationalExpr= ruleRelationalExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1287:2:
            // iv_ruleRelationalExpr= ruleRelationalExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getRelationalExprRule());
                }
                pushFollow(FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr2913);
                iv_ruleRelationalExpr = ruleRelationalExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleRelationalExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleRelationalExpr2923);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleRelationalExpr"

    // $ANTLR start "ruleRelationalExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1294:1:
    // ruleRelationalExpr returns [EObject current=null] : (this_PlusExpr_0=
    // rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0=
    // ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )? ) ;
    public final EObject ruleRelationalExpr() throws RecognitionException {
        EObject current = null;

        EObject this_PlusExpr_0 = null;

        AntlrDatatypeRuleToken lv_op_2_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1297:28:
            // ( (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp
            // ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) (
            // (lv_right_3_0= rulePlusExpr ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1298:1:
            // (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp )
            // ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) (
            // (lv_right_3_0= rulePlusExpr ) ) )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1298:1:
                // (this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( (
                // ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0=
                // ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?
                // )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1299:5:
                // this_PlusExpr_0= rulePlusExpr ( ( ( ( () ( ( ruleRelationalOp
                // ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) (
                // (lv_right_3_0= rulePlusExpr ) ) )?
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getRelationalExprAccess()
                                .getPlusExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr2970);
                    this_PlusExpr_0 = rulePlusExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_PlusExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:1:
                    // ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () (
                    // (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0=
                    // rulePlusExpr ) ) )?
                    int alt15 = 2;
                    alt15 = dfa15.predict(input);
                    switch (alt15) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:2:
                    // ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () (
                    // (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0=
                    // rulePlusExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:2:
                        // ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () (
                        // (lv_op_2_0= ruleRelationalOp ) ) ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:3:
                        // ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () (
                        // (lv_op_2_0= ruleRelationalOp ) ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1312:6:
                            // ( () ( (lv_op_2_0= ruleRelationalOp ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1312:7:
                            // () ( (lv_op_2_0= ruleRelationalOp ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1312:7:
                                // ()
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1313:5:
                                {
                                    if (state.backtracking == 0) {

                                        current = forceCreateModelElementAndSet(grammarAccess
                                                .getRelationalExprAccess()
                                                .getBinaryExprLeftAction_1_0_0_0(), current);

                                    }

                                }

                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1318:2:
                                // ( (lv_op_2_0= ruleRelationalOp ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1319:1:
                                // (lv_op_2_0= ruleRelationalOp )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1319:1:
                                    // (lv_op_2_0= ruleRelationalOp )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1320:3:
                                    // lv_op_2_0= ruleRelationalOp
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getRelationalExprAccess()
                                                    .getOpRelationalOpParserRuleCall_1_0_0_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3019);
                                        lv_op_2_0 = ruleRelationalOp();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getRelationalExprRule());
                                            }
                                            set(current, "op", lv_op_2_0, "RelationalOp");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1336:4:
                        // ( (lv_right_3_0= rulePlusExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1337:1:
                        // (lv_right_3_0= rulePlusExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1337:1:
                            // (lv_right_3_0= rulePlusExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1338:3:
                            // lv_right_3_0= rulePlusExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getRelationalExprAccess()
                                            .getRightPlusExprParserRuleCall_1_1_0());

                                }
                                pushFollow(FOLLOW_rulePlusExpr_in_ruleRelationalExpr3042);
                                lv_right_3_0 = rulePlusExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getRelationalExprRule());
                                    }
                                    set(current, "right", lv_right_3_0, "PlusExpr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleRelationalExpr"

    // $ANTLR start "entryRulePlusExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1362:1:
    // entryRulePlusExpr returns [EObject current=null] : iv_rulePlusExpr=
    // rulePlusExpr EOF ;
    public final EObject entryRulePlusExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1363:2:
            // (iv_rulePlusExpr= rulePlusExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1364:2:
            // iv_rulePlusExpr= rulePlusExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getPlusExprRule());
                }
                pushFollow(FOLLOW_rulePlusExpr_in_entryRulePlusExpr3080);
                iv_rulePlusExpr = rulePlusExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_rulePlusExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRulePlusExpr3090);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRulePlusExpr"

    // $ANTLR start "rulePlusExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1371:1:
    // rulePlusExpr returns [EObject current=null] : (this_TimesExpr_0=
    // ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( ( (lv_op_2_1=
    // '+' | lv_op_2_2= '-' ) ) ) ) ) ( (lv_right_3_0= ruleTimesExpr ) ) )* ) ;
    public final EObject rulePlusExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1 = null;
        Token lv_op_2_2 = null;
        EObject this_TimesExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1374:28:
            // ( (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) )
            // ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) (
            // (lv_right_3_0= ruleTimesExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1375:1:
            // (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' ) ) )
            // ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) (
            // (lv_right_3_0= ruleTimesExpr ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1375:1:
                // (this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' )
                // ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
                // ( (lv_right_3_0= ruleTimesExpr ) ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1376:5:
                // this_TimesExpr_0= ruleTimesExpr ( ( ( ( () ( ( ( '+' | '-' )
                // ) ) ) )=> ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
                // ( (lv_right_3_0= ruleTimesExpr ) ) )*
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPlusExprAccess()
                                .getTimesExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr3137);
                    this_TimesExpr_0 = ruleTimesExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_TimesExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:1:
                    // ( ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( (
                    // (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) (
                    // (lv_right_3_0= ruleTimesExpr ) ) )*
                    loop17: do {
                        int alt17 = 2;
                        int LA17_0 = input.LA(1);

                        if ((LA17_0 == 53)) {
                            int LA17_2 = input.LA(2);

                            if ((synpred5_InternalResolute())) {
                                alt17 = 1;
                            }

                        } else if ((LA17_0 == 54)) {
                            int LA17_3 = input.LA(2);

                            if ((synpred5_InternalResolute())) {
                                alt17 = 1;
                            }

                        }

                        switch (alt17) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:2:
                        // ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( (
                        // (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) ) (
                        // (lv_right_3_0= ruleTimesExpr ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:2:
                            // ( ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( (
                            // (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:3:
                            // ( ( () ( ( ( '+' | '-' ) ) ) ) )=> ( () ( (
                            // (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1397:6:
                                // ( () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
                                // ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1397:7:
                                // () ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) ) )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1397:7:
                                    // ()
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1398:5:
                                    {
                                        if (state.backtracking == 0) {

                                            current = forceCreateModelElementAndSet(grammarAccess
                                                    .getPlusExprAccess()
                                                    .getBinaryExprLeftAction_1_0_0_0(), current);

                                        }

                                    }

                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1403:2:
                                    // ( ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
                                    // )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1404:1:
                                    // ( (lv_op_2_1= '+' | lv_op_2_2= '-' ) )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1404:1:
                                        // ( (lv_op_2_1= '+' | lv_op_2_2= '-' )
                                        // )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1405:1:
                                        // (lv_op_2_1= '+' | lv_op_2_2= '-' )
                                        {
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1405:1:
                                            // (lv_op_2_1= '+' | lv_op_2_2= '-'
                                            // )
                                            int alt16 = 2;
                                            int LA16_0 = input.LA(1);

                                            if ((LA16_0 == 53)) {
                                                alt16 = 1;
                                            } else if ((LA16_0 == 54)) {
                                                alt16 = 2;
                                            } else {
                                                if (state.backtracking > 0) {
                                                    state.failed = true;
                                                    return current;
                                                }
                                                NoViableAltException nvae = new NoViableAltException(
                                                        "", 16, 0, input);

                                                throw nvae;
                                            }
                                            switch (alt16) {
                                            case 1:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1406:3:
                                            // lv_op_2_1= '+'
                                            {
                                                lv_op_2_1 = (Token) match(input, 53,
                                                        FOLLOW_53_in_rulePlusExpr3209);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_1, grammarAccess
                                                            .getPlusExprAccess()
                                                            .getOpPlusSignKeyword_1_0_0_1_0_0());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getPlusExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_1,
                                                            null);

                                                }

                                            }
                                                break;
                                            case 2:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1418:8:
                                            // lv_op_2_2= '-'
                                            {
                                                lv_op_2_2 = (Token) match(input, 54,
                                                        FOLLOW_54_in_rulePlusExpr3238);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_2, grammarAccess
                                                            .getPlusExprAccess()
                                                            .getOpHyphenMinusKeyword_1_0_0_1_0_1());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getPlusExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_2,
                                                            null);

                                                }

                                            }
                                                break;

                                            }

                                        }

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1433:4:
                            // ( (lv_right_3_0= ruleTimesExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1434:1:
                            // (lv_right_3_0= ruleTimesExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1434:1:
                                // (lv_right_3_0= ruleTimesExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1435:3:
                                // lv_right_3_0= ruleTimesExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getPlusExprAccess()
                                                .getRightTimesExprParserRuleCall_1_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleTimesExpr_in_rulePlusExpr3277);
                                    lv_right_3_0 = ruleTimesExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getPlusExprRule());
                                        }
                                        set(current, "right", lv_right_3_0, "TimesExpr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop17;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "rulePlusExpr"

    // $ANTLR start "entryRuleTimesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1459:1:
    // entryRuleTimesExpr returns [EObject current=null] : iv_ruleTimesExpr=
    // ruleTimesExpr EOF ;
    public final EObject entryRuleTimesExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimesExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1460:2:
            // (iv_ruleTimesExpr= ruleTimesExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1461:2:
            // iv_ruleTimesExpr= ruleTimesExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getTimesExprRule());
                }
                pushFollow(FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr3315);
                iv_ruleTimesExpr = ruleTimesExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleTimesExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleTimesExpr3325);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleTimesExpr"

    // $ANTLR start "ruleTimesExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1468:1:
    // ruleTimesExpr returns [EObject current=null] : (this_SetOpExpr_0=
    // ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( ( (lv_op_2_1=
    // '*' | lv_op_2_2= '/' ) ) ) ) ) ( (lv_right_3_0= ruleSetOpExpr ) ) )* ) ;
    public final EObject ruleTimesExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1 = null;
        Token lv_op_2_2 = null;
        EObject this_SetOpExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1471:28:
            // ( (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) )
            // ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) (
            // (lv_right_3_0= ruleSetOpExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1472:1:
            // (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' ) ) )
            // ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) (
            // (lv_right_3_0= ruleSetOpExpr ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1472:1:
                // (this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' )
                // ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) )
                // ( (lv_right_3_0= ruleSetOpExpr ) ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1473:5:
                // this_SetOpExpr_0= ruleSetOpExpr ( ( ( ( () ( ( ( '*' | '/' )
                // ) ) ) )=> ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) )
                // ( (lv_right_3_0= ruleSetOpExpr ) ) )*
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getTimesExprAccess()
                                .getSetOpExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3372);
                    this_SetOpExpr_0 = ruleSetOpExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_SetOpExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:1:
                    // ( ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( (
                    // (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) (
                    // (lv_right_3_0= ruleSetOpExpr ) ) )*
                    loop19: do {
                        int alt19 = 2;
                        int LA19_0 = input.LA(1);

                        if ((LA19_0 == 55)) {
                            int LA19_2 = input.LA(2);

                            if ((synpred6_InternalResolute())) {
                                alt19 = 1;
                            }

                        } else if ((LA19_0 == 56)) {
                            int LA19_3 = input.LA(2);

                            if ((synpred6_InternalResolute())) {
                                alt19 = 1;
                            }

                        }

                        switch (alt19) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:2:
                        // ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( (
                        // (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) ) (
                        // (lv_right_3_0= ruleSetOpExpr ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:2:
                            // ( ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( (
                            // (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:3:
                            // ( ( () ( ( ( '*' | '/' ) ) ) ) )=> ( () ( (
                            // (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1494:6:
                                // ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
                                // ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1494:7:
                                // () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1494:7:
                                    // ()
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1495:5:
                                    {
                                        if (state.backtracking == 0) {

                                            current = forceCreateModelElementAndSet(grammarAccess
                                                    .getTimesExprAccess()
                                                    .getBinaryExprLeftAction_1_0_0_0(), current);

                                        }

                                    }

                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1500:2:
                                    // ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
                                    // )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1501:1:
                                    // ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1501:1:
                                        // ( (lv_op_2_1= '*' | lv_op_2_2= '/' )
                                        // )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1502:1:
                                        // (lv_op_2_1= '*' | lv_op_2_2= '/' )
                                        {
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1502:1:
                                            // (lv_op_2_1= '*' | lv_op_2_2= '/'
                                            // )
                                            int alt18 = 2;
                                            int LA18_0 = input.LA(1);

                                            if ((LA18_0 == 55)) {
                                                alt18 = 1;
                                            } else if ((LA18_0 == 56)) {
                                                alt18 = 2;
                                            } else {
                                                if (state.backtracking > 0) {
                                                    state.failed = true;
                                                    return current;
                                                }
                                                NoViableAltException nvae = new NoViableAltException(
                                                        "", 18, 0, input);

                                                throw nvae;
                                            }
                                            switch (alt18) {
                                            case 1:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1503:3:
                                            // lv_op_2_1= '*'
                                            {
                                                lv_op_2_1 = (Token) match(input, 55,
                                                        FOLLOW_55_in_ruleTimesExpr3444);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_1, grammarAccess
                                                            .getTimesExprAccess()
                                                            .getOpAsteriskKeyword_1_0_0_1_0_0());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getTimesExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_1,
                                                            null);

                                                }

                                            }
                                                break;
                                            case 2:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1515:8:
                                            // lv_op_2_2= '/'
                                            {
                                                lv_op_2_2 = (Token) match(input, 56,
                                                        FOLLOW_56_in_ruleTimesExpr3473);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_2, grammarAccess
                                                            .getTimesExprAccess()
                                                            .getOpSolidusKeyword_1_0_0_1_0_1());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getTimesExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_2,
                                                            null);

                                                }

                                            }
                                                break;

                                            }

                                        }

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1530:4:
                            // ( (lv_right_3_0= ruleSetOpExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1531:1:
                            // (lv_right_3_0= ruleSetOpExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1531:1:
                                // (lv_right_3_0= ruleSetOpExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1532:3:
                                // lv_right_3_0= ruleSetOpExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getTimesExprAccess()
                                                .getRightSetOpExprParserRuleCall_1_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3512);
                                    lv_right_3_0 = ruleSetOpExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getTimesExprRule());
                                        }
                                        set(current, "right", lv_right_3_0, "SetOpExpr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop19;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleTimesExpr"

    // $ANTLR start "entryRuleSetOpExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1556:1:
    // entryRuleSetOpExpr returns [EObject current=null] : iv_ruleSetOpExpr=
    // ruleSetOpExpr EOF ;
    public final EObject entryRuleSetOpExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetOpExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1557:2:
            // (iv_ruleSetOpExpr= ruleSetOpExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1558:2:
            // iv_ruleSetOpExpr= ruleSetOpExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getSetOpExprRule());
                }
                pushFollow(FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr3550);
                iv_ruleSetOpExpr = ruleSetOpExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleSetOpExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleSetOpExpr3560);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleSetOpExpr"

    // $ANTLR start "ruleSetOpExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1565:1:
    // ruleSetOpExpr returns [EObject current=null] : (this_PrefixExpr_0=
    // rulePrefixExpr ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () (
    // ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0=
    // rulePrefixExpr ) ) )* ) ;
    public final EObject ruleSetOpExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1 = null;
        Token lv_op_2_2 = null;
        EObject this_PrefixExpr_0 = null;

        EObject lv_right_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1568:28:
            // ( (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' |
            // 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
            // 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1569:1:
            // (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' |
            // 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
            // 'intersect' ) ) ) ) ) ( (lv_right_3_0= rulePrefixExpr ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1569:1:
                // (this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' |
                // 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' |
                // lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0=
                // rulePrefixExpr ) ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1570:5:
                // this_PrefixExpr_0= rulePrefixExpr ( ( ( ( () ( ( ( 'union' |
                // 'intersect' ) ) ) ) )=> ( () ( ( (lv_op_2_1= 'union' |
                // lv_op_2_2= 'intersect' ) ) ) ) ) ( (lv_right_3_0=
                // rulePrefixExpr ) ) )*
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getSetOpExprAccess()
                                .getPrefixExprParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3607);
                    this_PrefixExpr_0 = rulePrefixExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_PrefixExpr_0;
                        afterParserOrEnumRuleCall();

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:1:
                    // ( ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( () (
                    // ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) ) ) ) )
                    // ( (lv_right_3_0= rulePrefixExpr ) ) )*
                    loop21: do {
                        int alt21 = 2;
                        int LA21_0 = input.LA(1);

                        if ((LA21_0 == 57)) {
                            int LA21_2 = input.LA(2);

                            if ((synpred7_InternalResolute())) {
                                alt21 = 1;
                            }

                        } else if ((LA21_0 == 58)) {
                            int LA21_3 = input.LA(2);

                            if ((synpred7_InternalResolute())) {
                                alt21 = 1;
                            }

                        }

                        switch (alt21) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:2:
                        // ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> ( ()
                        // ( ( (lv_op_2_1= 'union' | lv_op_2_2= 'intersect' ) )
                        // ) ) ) ( (lv_right_3_0= rulePrefixExpr ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:2:
                            // ( ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=>
                            // ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
                            // 'intersect' ) ) ) ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:3:
                            // ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )=> (
                            // () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
                            // 'intersect' ) ) ) )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1591:6:
                                // ( () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
                                // 'intersect' ) ) ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1591:7:
                                // () ( ( (lv_op_2_1= 'union' | lv_op_2_2=
                                // 'intersect' ) ) )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1591:7:
                                    // ()
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1592:5:
                                    {
                                        if (state.backtracking == 0) {

                                            current = forceCreateModelElementAndSet(grammarAccess
                                                    .getSetOpExprAccess()
                                                    .getBinaryExprLeftAction_1_0_0_0(), current);

                                        }

                                    }

                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1597:2:
                                    // ( ( (lv_op_2_1= 'union' | lv_op_2_2=
                                    // 'intersect' ) ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1598:1:
                                    // ( (lv_op_2_1= 'union' | lv_op_2_2=
                                    // 'intersect' ) )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1598:1:
                                        // ( (lv_op_2_1= 'union' | lv_op_2_2=
                                        // 'intersect' ) )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1599:1:
                                        // (lv_op_2_1= 'union' | lv_op_2_2=
                                        // 'intersect' )
                                        {
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1599:1:
                                            // (lv_op_2_1= 'union' | lv_op_2_2=
                                            // 'intersect' )
                                            int alt20 = 2;
                                            int LA20_0 = input.LA(1);

                                            if ((LA20_0 == 57)) {
                                                alt20 = 1;
                                            } else if ((LA20_0 == 58)) {
                                                alt20 = 2;
                                            } else {
                                                if (state.backtracking > 0) {
                                                    state.failed = true;
                                                    return current;
                                                }
                                                NoViableAltException nvae = new NoViableAltException(
                                                        "", 20, 0, input);

                                                throw nvae;
                                            }
                                            switch (alt20) {
                                            case 1:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1600:3:
                                            // lv_op_2_1= 'union'
                                            {
                                                lv_op_2_1 = (Token) match(input, 57,
                                                        FOLLOW_57_in_ruleSetOpExpr3679);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_1, grammarAccess
                                                            .getSetOpExprAccess()
                                                            .getOpUnionKeyword_1_0_0_1_0_0());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getSetOpExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_1,
                                                            null);

                                                }

                                            }
                                                break;
                                            case 2:
                                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1612:8:
                                            // lv_op_2_2= 'intersect'
                                            {
                                                lv_op_2_2 = (Token) match(input, 58,
                                                        FOLLOW_58_in_ruleSetOpExpr3708);
                                                if (state.failed) {
                                                    return current;
                                                }
                                                if (state.backtracking == 0) {

                                                    newLeafNode(lv_op_2_2, grammarAccess
                                                            .getSetOpExprAccess()
                                                            .getOpIntersectKeyword_1_0_0_1_0_1());

                                                }
                                                if (state.backtracking == 0) {

                                                    if (current == null) {
                                                        current = createModelElement(grammarAccess
                                                                .getSetOpExprRule());
                                                    }
                                                    setWithLastConsumed(current, "op", lv_op_2_2,
                                                            null);

                                                }

                                            }
                                                break;

                                            }

                                        }

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1627:4:
                            // ( (lv_right_3_0= rulePrefixExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1628:1:
                            // (lv_right_3_0= rulePrefixExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1628:1:
                                // (lv_right_3_0= rulePrefixExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1629:3:
                                // lv_right_3_0= rulePrefixExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getSetOpExprAccess()
                                                .getRightPrefixExprParserRuleCall_1_1_0());

                                    }
                                    pushFollow(FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3747);
                                    lv_right_3_0 = rulePrefixExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getSetOpExprRule());
                                        }
                                        set(current, "right", lv_right_3_0, "PrefixExpr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop21;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleSetOpExpr"

    // $ANTLR start "entryRulePrefixExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1653:1:
    // entryRulePrefixExpr returns [EObject current=null] : iv_rulePrefixExpr=
    // rulePrefixExpr EOF ;
    public final EObject entryRulePrefixExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1654:2:
            // (iv_rulePrefixExpr= rulePrefixExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1655:2:
            // iv_rulePrefixExpr= rulePrefixExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getPrefixExprRule());
                }
                pushFollow(FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr3785);
                iv_rulePrefixExpr = rulePrefixExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_rulePrefixExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRulePrefixExpr3795);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRulePrefixExpr"

    // $ANTLR start "rulePrefixExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1662:1:
    // rulePrefixExpr returns [EObject current=null] : ( ( () ( ( (lv_op_1_1=
    // '-' | lv_op_1_2= 'not' ) ) ) ( (lv_expr_2_0= rulePrefixExpr ) ) ) |
    // this_AtomicExpr_3= ruleAtomicExpr ) ;
    public final EObject rulePrefixExpr() throws RecognitionException {
        EObject current = null;

        Token lv_op_1_1 = null;
        Token lv_op_1_2 = null;
        EObject lv_expr_2_0 = null;

        EObject this_AtomicExpr_3 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1665:28:
            // ( ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
            // (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3=
            // ruleAtomicExpr ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:1:
            // ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
            // (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3=
            // ruleAtomicExpr )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:1:
                // ( ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
                // (lv_expr_2_0= rulePrefixExpr ) ) ) | this_AtomicExpr_3=
                // ruleAtomicExpr )
                int alt23 = 2;
                int LA23_0 = input.LA(1);

                if ((LA23_0 == 54 || LA23_0 == 59)) {
                    alt23 = 1;
                } else if (((LA23_0 >= RULE_ID && LA23_0 <= RULE_INTEGER_LIT) || LA23_0 == 16
                        || LA23_0 == 40 || (LA23_0 >= 60 && LA23_0 <= 62)
                        || (LA23_0 >= 65 && LA23_0 <= 66) || (LA23_0 >= 70 && LA23_0 <= 81) || (LA23_0 >= 90 && LA23_0 <= 91))) {
                    alt23 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 23, 0, input);

                    throw nvae;
                }
                switch (alt23) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:2:
                // ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
                // (lv_expr_2_0= rulePrefixExpr ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:2:
                    // ( () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
                    // (lv_expr_2_0= rulePrefixExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:3:
                    // () ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) ) (
                    // (lv_expr_2_0= rulePrefixExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1666:3:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1667:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getPrefixExprAccess().getUnaryExprAction_0_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1672:2:
                        // ( ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1673:1:
                        // ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1673:1:
                            // ( (lv_op_1_1= '-' | lv_op_1_2= 'not' ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1674:1:
                            // (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1674:1:
                                // (lv_op_1_1= '-' | lv_op_1_2= 'not' )
                                int alt22 = 2;
                                int LA22_0 = input.LA(1);

                                if ((LA22_0 == 54)) {
                                    alt22 = 1;
                                } else if ((LA22_0 == 59)) {
                                    alt22 = 2;
                                } else {
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return current;
                                    }
                                    NoViableAltException nvae = new NoViableAltException("", 22, 0,
                                            input);

                                    throw nvae;
                                }
                                switch (alt22) {
                                case 1:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1675:3:
                                // lv_op_1_1= '-'
                                {
                                    lv_op_1_1 = (Token) match(input, 54,
                                            FOLLOW_54_in_rulePrefixExpr3850);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(lv_op_1_1, grammarAccess.getPrefixExprAccess()
                                                .getOpHyphenMinusKeyword_0_1_0_0());

                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElement(grammarAccess
                                                    .getPrefixExprRule());
                                        }
                                        setWithLastConsumed(current, "op", lv_op_1_1, null);

                                    }

                                }
                                    break;
                                case 2:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1687:8:
                                // lv_op_1_2= 'not'
                                {
                                    lv_op_1_2 = (Token) match(input, 59,
                                            FOLLOW_59_in_rulePrefixExpr3879);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(lv_op_1_2, grammarAccess.getPrefixExprAccess()
                                                .getOpNotKeyword_0_1_0_1());

                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElement(grammarAccess
                                                    .getPrefixExprRule());
                                        }
                                        setWithLastConsumed(current, "op", lv_op_1_2, null);

                                    }

                                }
                                    break;

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1702:2:
                        // ( (lv_expr_2_0= rulePrefixExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1703:1:
                        // (lv_expr_2_0= rulePrefixExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1703:1:
                            // (lv_expr_2_0= rulePrefixExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1704:3:
                            // lv_expr_2_0= rulePrefixExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getPrefixExprAccess()
                                            .getExprPrefixExprParserRuleCall_0_2_0());

                                }
                                pushFollow(FOLLOW_rulePrefixExpr_in_rulePrefixExpr3916);
                                lv_expr_2_0 = rulePrefixExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getPrefixExprRule());
                                    }
                                    set(current, "expr", lv_expr_2_0, "PrefixExpr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1722:5:
                // this_AtomicExpr_3= ruleAtomicExpr
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPrefixExprAccess()
                                .getAtomicExprParserRuleCall_1());

                    }
                    pushFollow(FOLLOW_ruleAtomicExpr_in_rulePrefixExpr3945);
                    this_AtomicExpr_3 = ruleAtomicExpr();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_AtomicExpr_3;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "rulePrefixExpr"

    // $ANTLR start "entryRuleAtomicExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1738:1:
    // entryRuleAtomicExpr returns [EObject current=null] : iv_ruleAtomicExpr=
    // ruleAtomicExpr EOF ;
    public final EObject entryRuleAtomicExpr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicExpr = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1739:2:
            // (iv_ruleAtomicExpr= ruleAtomicExpr EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1740:2:
            // iv_ruleAtomicExpr= ruleAtomicExpr EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getAtomicExprRule());
                }
                pushFollow(FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr3980);
                iv_ruleAtomicExpr = ruleAtomicExpr();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleAtomicExpr;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleAtomicExpr3990);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleAtomicExpr"

    // $ANTLR start "ruleAtomicExpr"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1747:1:
    // ruleAtomicExpr returns [EObject current=null] : ( ( () ( ( ruleQCREF ) )
    // ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0=
    // ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( ()
    // ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0=
    // ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | (
    // () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' (
    // (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr
    // ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) )
    // (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+
    // otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0=
    // ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) )
    // (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) |
    // ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0=
    // ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )?
    // otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) )
    // otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) )
    // otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )?
    // otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55=
    // ')' ) ) ;
    public final EObject ruleAtomicExpr() throws RecognitionException {
        EObject current = null;

        Token otherlv_3 = null;
        Token otherlv_5 = null;
        Token otherlv_16 = null;
        Token otherlv_18 = null;
        Token otherlv_20 = null;
        Token lv_quant_23_1 = null;
        Token lv_quant_23_2 = null;
        Token otherlv_24 = null;
        Token otherlv_26 = null;
        Token otherlv_27 = null;
        Token otherlv_31 = null;
        Token otherlv_33 = null;
        Token otherlv_35 = null;
        Token otherlv_37 = null;
        Token otherlv_38 = null;
        Token otherlv_40 = null;
        Token otherlv_42 = null;
        Token otherlv_44 = null;
        Token otherlv_46 = null;
        Token otherlv_47 = null;
        Token otherlv_49 = null;
        Token otherlv_50 = null;
        Token otherlv_52 = null;
        Token otherlv_53 = null;
        Token otherlv_55 = null;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1750:28:
            // ( ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( ()
            // otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () (
            // (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0=
            // ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) )
            // ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16=
            // 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' (
            // (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0=
            // ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' |
            // lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0=
            // ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0=
            // ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) )
            // otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ','
            // ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () (
            // (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0=
            // ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )?
            // otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr
            // ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg )
            // ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr
            // ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54=
            // ruleExpr otherlv_55= ')' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:1:
            // ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( ()
            // otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () (
            // (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0=
            // ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) )
            // ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16=
            // 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' (
            // (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0=
            // ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' |
            // lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0=
            // ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0=
            // ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) )
            // otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ','
            // ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () (
            // (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0=
            // ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )?
            // otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr
            // ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg )
            // ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr
            // ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54=
            // ruleExpr otherlv_55= ')' ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:1:
                // ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( ()
                // otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( ()
                // ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0=
                // ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral
                // ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( ()
                // otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18=
                // 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' (
                // (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1=
                // 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' (
                // (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.'
                // ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0=
                // ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0=
                // ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) )
                // )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) )
                // otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40=
                // ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | (
                // () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46=
                // 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) )
                // otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0=
                // ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '('
                // this_Expr_54= ruleExpr otherlv_55= ')' ) )
                int alt33 = 13;
                alt33 = dfa33.predict(input);
                switch (alt33) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:2:
                // ( () ( ( ruleQCREF ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:2:
                    // ( () ( ( ruleQCREF ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:3:
                    // () ( ( ruleQCREF ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1751:3:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1752:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getIdExprAction_0_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1757:2:
                        // ( ( ruleQCREF ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1758:1:
                        // ( ruleQCREF )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1758:1:
                            // ( ruleQCREF )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1759:3:
                            // ruleQCREF
                            {
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getAtomicExprRule());
                                    }

                                }
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getIdNamedElementCrossReference_0_1_0());

                                }
                                pushFollow(FOLLOW_ruleQCREF_in_ruleAtomicExpr4048);
                                ruleQCREF();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1773:6:
                // ( () otherlv_3= 'this' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1773:6:
                    // ( () otherlv_3= 'this' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1773:7:
                    // () otherlv_3= 'this'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1773:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1774:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getThisExprAction_1_0(), current);

                            }

                        }

                        otherlv_3 = (Token) match(input, 60, FOLLOW_60_in_ruleAtomicExpr4077);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getAtomicExprAccess()
                                    .getThisKeyword_1_1());

                        }

                    }

                }
                    break;
                case 3:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1784:6:
                // ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1784:6:
                    // ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )?
                    // )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1784:7:
                    // () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )?
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1784:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1785:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getFailExprAction_2_0(), current);

                            }

                        }

                        otherlv_5 = (Token) match(input, 61, FOLLOW_61_in_ruleAtomicExpr4106);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_5, grammarAccess.getAtomicExprAccess()
                                    .getFailKeyword_2_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1794:1:
                        // ( (lv_val_6_0= ruleStringTerm ) )?
                        int alt24 = 2;
                        int LA24_0 = input.LA(1);

                        if ((LA24_0 == RULE_STRING)) {
                            alt24 = 1;
                        }
                        switch (alt24) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:1:
                        // (lv_val_6_0= ruleStringTerm )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1795:1:
                            // (lv_val_6_0= ruleStringTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1796:3:
                            // lv_val_6_0= ruleStringTerm
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getValStringTermParserRuleCall_2_2_0());

                                }
                                pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4127);
                                lv_val_6_0 = ruleStringTerm();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "val", lv_val_6_0, "StringTerm");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }
                            break;

                        }

                    }

                }
                    break;
                case 4:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1813:6:
                // ( () ( (lv_val_8_0= ruleIntegerTerm ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1813:6:
                    // ( () ( (lv_val_8_0= ruleIntegerTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1813:7:
                    // () ( (lv_val_8_0= ruleIntegerTerm ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1813:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1814:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getIntExprAction_3_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1819:2:
                        // ( (lv_val_8_0= ruleIntegerTerm ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1820:1:
                        // (lv_val_8_0= ruleIntegerTerm )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1820:1:
                            // (lv_val_8_0= ruleIntegerTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1821:3:
                            // lv_val_8_0= ruleIntegerTerm
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getValIntegerTermParserRuleCall_3_1_0());

                                }
                                pushFollow(FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4166);
                                lv_val_8_0 = ruleIntegerTerm();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "val", lv_val_8_0, "IntegerTerm");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 5:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1838:6:
                // ( () ( (lv_val_10_0= ruleRealTerm ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1838:6:
                    // ( () ( (lv_val_10_0= ruleRealTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1838:7:
                    // () ( (lv_val_10_0= ruleRealTerm ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1838:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1839:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getRealExprAction_4_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1844:2:
                        // ( (lv_val_10_0= ruleRealTerm ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1845:1:
                        // (lv_val_10_0= ruleRealTerm )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1845:1:
                            // (lv_val_10_0= ruleRealTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1846:3:
                            // lv_val_10_0= ruleRealTerm
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getValRealTermParserRuleCall_4_1_0());

                                }
                                pushFollow(FOLLOW_ruleRealTerm_in_ruleAtomicExpr4204);
                                lv_val_10_0 = ruleRealTerm();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "val", lv_val_10_0, "RealTerm");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 6:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1863:6:
                // ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1863:6:
                    // ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1863:7:
                    // () ( (lv_val_12_0= ruleBooleanLiteral ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1863:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1864:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getBoolExprAction_5_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1869:2:
                        // ( (lv_val_12_0= ruleBooleanLiteral ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1870:1:
                        // (lv_val_12_0= ruleBooleanLiteral )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1870:1:
                            // (lv_val_12_0= ruleBooleanLiteral )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1871:3:
                            // lv_val_12_0= ruleBooleanLiteral
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getValBooleanLiteralParserRuleCall_5_1_0());

                                }
                                pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4242);
                                lv_val_12_0 = ruleBooleanLiteral();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "val", lv_val_12_0, "BooleanLiteral");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 7:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1888:6:
                // ( () ( (lv_val_14_0= ruleStringTerm ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1888:6:
                    // ( () ( (lv_val_14_0= ruleStringTerm ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1888:7:
                    // () ( (lv_val_14_0= ruleStringTerm ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1888:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1889:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getStringExprAction_6_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1894:2:
                        // ( (lv_val_14_0= ruleStringTerm ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1895:1:
                        // (lv_val_14_0= ruleStringTerm )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1895:1:
                            // (lv_val_14_0= ruleStringTerm )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1896:3:
                            // lv_val_14_0= ruleStringTerm
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getValStringTermParserRuleCall_6_1_0());

                                }
                                pushFollow(FOLLOW_ruleStringTerm_in_ruleAtomicExpr4280);
                                lv_val_14_0 = ruleStringTerm();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "val", lv_val_14_0, "StringTerm");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 8:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:6:
                // ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) )
                // otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20=
                // 'else' ( (lv_else_21_0= ruleExpr ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:6:
                    // ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) )
                    // otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) )
                    // otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:7:
                    // () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) )
                    // otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) )
                    // otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1913:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1914:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getIfThenElseExprAction_7_0(),
                                        current);

                            }

                        }

                        otherlv_16 = (Token) match(input, 62, FOLLOW_62_in_ruleAtomicExpr4309);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_16, grammarAccess.getAtomicExprAccess()
                                    .getIfKeyword_7_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1923:1:
                        // ( (lv_cond_17_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1924:1:
                        // (lv_cond_17_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1924:1:
                            // (lv_cond_17_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1925:3:
                            // lv_cond_17_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getCondExprParserRuleCall_7_2_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4330);
                                lv_cond_17_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "cond", lv_cond_17_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_18 = (Token) match(input, 63, FOLLOW_63_in_ruleAtomicExpr4342);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_18, grammarAccess.getAtomicExprAccess()
                                    .getThenKeyword_7_3());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1945:1:
                        // ( (lv_then_19_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1946:1:
                        // (lv_then_19_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1946:1:
                            // (lv_then_19_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1947:3:
                            // lv_then_19_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getThenExprParserRuleCall_7_4_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4363);
                                lv_then_19_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "then", lv_then_19_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_20 = (Token) match(input, 64, FOLLOW_64_in_ruleAtomicExpr4375);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_20, grammarAccess.getAtomicExprAccess()
                                    .getElseKeyword_7_5());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1967:1:
                        // ( (lv_else_21_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1968:1:
                        // (lv_else_21_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1968:1:
                            // (lv_else_21_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1969:3:
                            // lv_else_21_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getElseExprParserRuleCall_7_6_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4396);
                                lv_else_21_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "else", lv_else_21_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 9:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:6:
                // ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' )
                // ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26=
                // ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:6:
                    // ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2=
                    // 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg
                    // ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0=
                    // ruleExpr ) ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:7:
                    // () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists'
                    // ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) )
                    // otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0=
                    // ruleExpr ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1986:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1987:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getQuantifiedExprAction_8_0(),
                                        current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1992:2:
                        // ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2=
                        // 'exists' ) ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1993:1:
                        // ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists'
                        // ) )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1993:1:
                            // ( (lv_quant_23_1= 'forall' | lv_quant_23_2=
                            // 'exists' ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1994:1:
                            // (lv_quant_23_1= 'forall' | lv_quant_23_2=
                            // 'exists' )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1994:1:
                                // (lv_quant_23_1= 'forall' | lv_quant_23_2=
                                // 'exists' )
                                int alt25 = 2;
                                int LA25_0 = input.LA(1);

                                if ((LA25_0 == 65)) {
                                    alt25 = 1;
                                } else if ((LA25_0 == 66)) {
                                    alt25 = 2;
                                } else {
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return current;
                                    }
                                    NoViableAltException nvae = new NoViableAltException("", 25, 0,
                                            input);

                                    throw nvae;
                                }
                                switch (alt25) {
                                case 1:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1995:3:
                                // lv_quant_23_1= 'forall'
                                {
                                    lv_quant_23_1 = (Token) match(input, 65,
                                            FOLLOW_65_in_ruleAtomicExpr4433);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(lv_quant_23_1, grammarAccess
                                                .getAtomicExprAccess()
                                                .getQuantForallKeyword_8_1_0_0());

                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElement(grammarAccess
                                                    .getAtomicExprRule());
                                        }
                                        setWithLastConsumed(current, "quant", lv_quant_23_1, null);

                                    }

                                }
                                    break;
                                case 2:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2007:8:
                                // lv_quant_23_2= 'exists'
                                {
                                    lv_quant_23_2 = (Token) match(input, 66,
                                            FOLLOW_66_in_ruleAtomicExpr4462);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(lv_quant_23_2, grammarAccess
                                                .getAtomicExprAccess()
                                                .getQuantExistsKeyword_8_1_0_1());

                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElement(grammarAccess
                                                    .getAtomicExprRule());
                                        }
                                        setWithLastConsumed(current, "quant", lv_quant_23_2, null);

                                    }

                                }
                                    break;

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2022:2:
                        // (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) )
                        // otherlv_26= ')' )+
                        int cnt26 = 0;
                        loop26: do {
                            int alt26 = 2;
                            int LA26_0 = input.LA(1);

                            if ((LA26_0 == 40)) {
                                alt26 = 1;
                            }

                            switch (alt26) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2022:4:
                            // otherlv_24= '(' ( (lv_args_25_0= ruleArg ) )
                            // otherlv_26= ')'
                            {
                                otherlv_24 = (Token) match(input, 40,
                                        FOLLOW_40_in_ruleAtomicExpr4491);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_24, grammarAccess.getAtomicExprAccess()
                                            .getLeftParenthesisKeyword_8_2_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2026:1:
                                // ( (lv_args_25_0= ruleArg ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2027:1:
                                // (lv_args_25_0= ruleArg )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2027:1:
                                    // (lv_args_25_0= ruleArg )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2028:3:
                                    // lv_args_25_0= ruleArg
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess.getAtomicExprAccess()
                                                    .getArgsArgParserRuleCall_8_2_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4512);
                                        lv_args_25_0 = ruleArg();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getAtomicExprRule());
                                            }
                                            add(current, "args", lv_args_25_0, "Arg");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                                otherlv_26 = (Token) match(input, 42,
                                        FOLLOW_42_in_ruleAtomicExpr4524);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_26, grammarAccess.getAtomicExprAccess()
                                            .getRightParenthesisKeyword_8_2_2());

                                }

                            }
                                break;

                            default:
                                if (cnt26 >= 1) {
                                    break loop26;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return current;
                                }
                                EarlyExitException eee = new EarlyExitException(26, input);
                                throw eee;
                            }
                            cnt26++;
                        } while (true);

                        otherlv_27 = (Token) match(input, 67, FOLLOW_67_in_ruleAtomicExpr4538);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_27, grammarAccess.getAtomicExprAccess()
                                    .getFullStopKeyword_8_3());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2052:1:
                        // ( (lv_expr_28_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2053:1:
                        // (lv_expr_28_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2053:1:
                            // (lv_expr_28_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2054:3:
                            // lv_expr_28_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getExprExprParserRuleCall_8_4_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4559);
                                lv_expr_28_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "expr", lv_expr_28_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }

                }
                    break;
                case 10:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2071:6:
                // ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( (
                // (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0=
                // ruleExpr ) ) )* )? otherlv_35= ')' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2071:6:
                    // ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' (
                    // ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' (
                    // (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2071:7:
                    // () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( (
                    // (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' (
                    // (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2071:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2072:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getBuiltInFuncCallExprAction_9_0(),
                                        current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2077:2:
                        // ( (lv_fn_30_0= ruleBuiltInFunc ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2078:1:
                        // (lv_fn_30_0= ruleBuiltInFunc )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2078:1:
                            // (lv_fn_30_0= ruleBuiltInFunc )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2079:3:
                            // lv_fn_30_0= ruleBuiltInFunc
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getFnBuiltInFuncParserRuleCall_9_1_0());

                                }
                                pushFollow(FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4597);
                                lv_fn_30_0 = ruleBuiltInFunc();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "fn", lv_fn_30_0, "BuiltInFunc");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_31 = (Token) match(input, 40, FOLLOW_40_in_ruleAtomicExpr4609);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_31, grammarAccess.getAtomicExprAccess()
                                    .getLeftParenthesisKeyword_9_2());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2099:1:
                        // ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' (
                        // (lv_args_34_0= ruleExpr ) ) )* )?
                        int alt28 = 2;
                        int LA28_0 = input.LA(1);

                        if (((LA28_0 >= RULE_ID && LA28_0 <= RULE_INTEGER_LIT) || LA28_0 == 16
                                || LA28_0 == 40 || LA28_0 == 54 || (LA28_0 >= 59 && LA28_0 <= 62)
                                || (LA28_0 >= 65 && LA28_0 <= 66) || (LA28_0 >= 70 && LA28_0 <= 81) || (LA28_0 >= 90 && LA28_0 <= 91))) {
                            alt28 = 1;
                        }
                        switch (alt28) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2099:2:
                        // ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' (
                        // (lv_args_34_0= ruleExpr ) ) )*
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2099:2:
                            // ( (lv_args_32_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2100:1:
                            // (lv_args_32_0= ruleExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2100:1:
                                // (lv_args_32_0= ruleExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2101:3:
                                // lv_args_32_0= ruleExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getAtomicExprAccess()
                                                .getArgsExprParserRuleCall_9_3_0_0());

                                    }
                                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4631);
                                    lv_args_32_0 = ruleExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getAtomicExprRule());
                                        }
                                        add(current, "args", lv_args_32_0, "Expr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2117:2:
                            // (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )*
                            loop27: do {
                                int alt27 = 2;
                                int LA27_0 = input.LA(1);

                                if ((LA27_0 == 41)) {
                                    alt27 = 1;
                                }

                                switch (alt27) {
                                case 1:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2117:4:
                                // otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) )
                                {
                                    otherlv_33 = (Token) match(input, 41,
                                            FOLLOW_41_in_ruleAtomicExpr4644);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(otherlv_33, grammarAccess.getAtomicExprAccess()
                                                .getCommaKeyword_9_3_1_0());

                                    }
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2121:1:
                                    // ( (lv_args_34_0= ruleExpr ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2122:1:
                                    // (lv_args_34_0= ruleExpr )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2122:1:
                                        // (lv_args_34_0= ruleExpr )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2123:3:
                                        // lv_args_34_0= ruleExpr
                                        {
                                            if (state.backtracking == 0) {

                                                newCompositeNode(grammarAccess
                                                        .getAtomicExprAccess()
                                                        .getArgsExprParserRuleCall_9_3_1_1_0());

                                            }
                                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4665);
                                            lv_args_34_0 = ruleExpr();

                                            state._fsp--;
                                            if (state.failed) {
                                                return current;
                                            }
                                            if (state.backtracking == 0) {

                                                if (current == null) {
                                                    current = createModelElementForParent(grammarAccess
                                                            .getAtomicExprRule());
                                                }
                                                add(current, "args", lv_args_34_0, "Expr");
                                                afterParserOrEnumRuleCall();

                                            }

                                        }

                                    }

                                }
                                    break;

                                default:
                                    break loop27;
                                }
                            } while (true);

                        }
                            break;

                        }

                        otherlv_35 = (Token) match(input, 42, FOLLOW_42_in_ruleAtomicExpr4681);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_35, grammarAccess.getAtomicExprAccess()
                                    .getRightParenthesisKeyword_9_4());

                        }

                    }

                }
                    break;
                case 11:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2144:6:
                // ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( (
                // (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0=
                // ruleExpr ) ) )* )? otherlv_42= ')' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2144:6:
                    // ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( (
                    // (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' (
                    // (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2144:7:
                    // () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( (
                    // (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' (
                    // (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2144:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2145:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getFnCallExprAction_10_0(), current);

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2150:2:
                        // ( (otherlv_37= RULE_ID ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2151:1:
                        // (otherlv_37= RULE_ID )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2151:1:
                            // (otherlv_37= RULE_ID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2152:3:
                            // otherlv_37= RULE_ID
                            {
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getAtomicExprRule());
                                    }

                                }
                                otherlv_37 = (Token) match(input, RULE_ID,
                                        FOLLOW_RULE_ID_in_ruleAtomicExpr4718);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_37, grammarAccess.getAtomicExprAccess()
                                            .getFnFunctionDefinitionCrossReference_10_1_0());

                                }

                            }

                        }

                        otherlv_38 = (Token) match(input, 40, FOLLOW_40_in_ruleAtomicExpr4730);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_38, grammarAccess.getAtomicExprAccess()
                                    .getLeftParenthesisKeyword_10_2());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2167:1:
                        // ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' (
                        // (lv_args_41_0= ruleExpr ) ) )* )?
                        int alt30 = 2;
                        int LA30_0 = input.LA(1);

                        if (((LA30_0 >= RULE_ID && LA30_0 <= RULE_INTEGER_LIT) || LA30_0 == 16
                                || LA30_0 == 40 || LA30_0 == 54 || (LA30_0 >= 59 && LA30_0 <= 62)
                                || (LA30_0 >= 65 && LA30_0 <= 66) || (LA30_0 >= 70 && LA30_0 <= 81) || (LA30_0 >= 90 && LA30_0 <= 91))) {
                            alt30 = 1;
                        }
                        switch (alt30) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2167:2:
                        // ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' (
                        // (lv_args_41_0= ruleExpr ) ) )*
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2167:2:
                            // ( (lv_args_39_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:1:
                            // (lv_args_39_0= ruleExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2168:1:
                                // (lv_args_39_0= ruleExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2169:3:
                                // lv_args_39_0= ruleExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getAtomicExprAccess()
                                                .getArgsExprParserRuleCall_10_3_0_0());

                                    }
                                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4752);
                                    lv_args_39_0 = ruleExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getAtomicExprRule());
                                        }
                                        add(current, "args", lv_args_39_0, "Expr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2185:2:
                            // (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )*
                            loop29: do {
                                int alt29 = 2;
                                int LA29_0 = input.LA(1);

                                if ((LA29_0 == 41)) {
                                    alt29 = 1;
                                }

                                switch (alt29) {
                                case 1:
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2185:4:
                                // otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) )
                                {
                                    otherlv_40 = (Token) match(input, 41,
                                            FOLLOW_41_in_ruleAtomicExpr4765);
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        newLeafNode(otherlv_40, grammarAccess.getAtomicExprAccess()
                                                .getCommaKeyword_10_3_1_0());

                                    }
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2189:1:
                                    // ( (lv_args_41_0= ruleExpr ) )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2190:1:
                                    // (lv_args_41_0= ruleExpr )
                                    {
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2190:1:
                                        // (lv_args_41_0= ruleExpr )
                                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2191:3:
                                        // lv_args_41_0= ruleExpr
                                        {
                                            if (state.backtracking == 0) {

                                                newCompositeNode(grammarAccess
                                                        .getAtomicExprAccess()
                                                        .getArgsExprParserRuleCall_10_3_1_1_0());

                                            }
                                            pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4786);
                                            lv_args_41_0 = ruleExpr();

                                            state._fsp--;
                                            if (state.failed) {
                                                return current;
                                            }
                                            if (state.backtracking == 0) {

                                                if (current == null) {
                                                    current = createModelElementForParent(grammarAccess
                                                            .getAtomicExprRule());
                                                }
                                                add(current, "args", lv_args_41_0, "Expr");
                                                afterParserOrEnumRuleCall();

                                            }

                                        }

                                    }

                                }
                                    break;

                                default:
                                    break loop29;
                                }
                            } while (true);

                        }
                            break;

                        }

                        otherlv_42 = (Token) match(input, 42, FOLLOW_42_in_ruleAtomicExpr4802);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_42, grammarAccess.getAtomicExprAccess()
                                    .getRightParenthesisKeyword_10_4());

                        }

                    }

                }
                    break;
                case 12:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2212:6:
                // ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46=
                // 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) )
                // otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0=
                // ruleExpr ) ) )? otherlv_52= '}' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2212:6:
                    // ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) )
                    // otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0=
                    // ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' (
                    // (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2212:7:
                    // () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) )
                    // otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0=
                    // ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' (
                    // (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2212:7:
                        // ()
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2213:5:
                        {
                            if (state.backtracking == 0) {

                                current = forceCreateModelElement(grammarAccess
                                        .getAtomicExprAccess().getFilterMapExprAction_11_0(),
                                        current);

                            }

                        }

                        otherlv_44 = (Token) match(input, 16, FOLLOW_16_in_ruleAtomicExpr4831);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_44, grammarAccess.getAtomicExprAccess()
                                    .getLeftCurlyBracketKeyword_11_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2222:1:
                        // ( (lv_map_45_0= ruleExpr ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2223:1:
                        // (lv_map_45_0= ruleExpr )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2223:1:
                            // (lv_map_45_0= ruleExpr )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2224:3:
                            // lv_map_45_0= ruleExpr
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getAtomicExprAccess()
                                            .getMapExprParserRuleCall_11_2_0());

                                }
                                pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4852);
                                lv_map_45_0 = ruleExpr();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getAtomicExprRule());
                                    }
                                    set(current, "map", lv_map_45_0, "Expr");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_46 = (Token) match(input, 68, FOLLOW_68_in_ruleAtomicExpr4864);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_46, grammarAccess.getAtomicExprAccess()
                                    .getForKeyword_11_3());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2244:1:
                        // (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) )
                        // otherlv_49= ')' )+
                        int cnt31 = 0;
                        loop31: do {
                            int alt31 = 2;
                            int LA31_0 = input.LA(1);

                            if ((LA31_0 == 40)) {
                                alt31 = 1;
                            }

                            switch (alt31) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2244:3:
                            // otherlv_47= '(' ( (lv_args_48_0= ruleArg ) )
                            // otherlv_49= ')'
                            {
                                otherlv_47 = (Token) match(input, 40,
                                        FOLLOW_40_in_ruleAtomicExpr4877);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_47, grammarAccess.getAtomicExprAccess()
                                            .getLeftParenthesisKeyword_11_4_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2248:1:
                                // ( (lv_args_48_0= ruleArg ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2249:1:
                                // (lv_args_48_0= ruleArg )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2249:1:
                                    // (lv_args_48_0= ruleArg )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2250:3:
                                    // lv_args_48_0= ruleArg
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess.getAtomicExprAccess()
                                                    .getArgsArgParserRuleCall_11_4_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleArg_in_ruleAtomicExpr4898);
                                        lv_args_48_0 = ruleArg();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getAtomicExprRule());
                                            }
                                            add(current, "args", lv_args_48_0, "Arg");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                                otherlv_49 = (Token) match(input, 42,
                                        FOLLOW_42_in_ruleAtomicExpr4910);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_49, grammarAccess.getAtomicExprAccess()
                                            .getRightParenthesisKeyword_11_4_2());

                                }

                            }
                                break;

                            default:
                                if (cnt31 >= 1) {
                                    break loop31;
                                }
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return current;
                                }
                                EarlyExitException eee = new EarlyExitException(31, input);
                                throw eee;
                            }
                            cnt31++;
                        } while (true);

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2270:3:
                        // (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )?
                        int alt32 = 2;
                        int LA32_0 = input.LA(1);

                        if ((LA32_0 == 69)) {
                            alt32 = 1;
                        }
                        switch (alt32) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2270:5:
                        // otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) )
                        {
                            otherlv_50 = (Token) match(input, 69, FOLLOW_69_in_ruleAtomicExpr4925);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_50, grammarAccess.getAtomicExprAccess()
                                        .getVerticalLineKeyword_11_5_0());

                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2274:1:
                            // ( (lv_filter_51_0= ruleExpr ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2275:1:
                            // (lv_filter_51_0= ruleExpr )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2275:1:
                                // (lv_filter_51_0= ruleExpr )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2276:3:
                                // lv_filter_51_0= ruleExpr
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess.getAtomicExprAccess()
                                                .getFilterExprParserRuleCall_11_5_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr4946);
                                    lv_filter_51_0 = ruleExpr();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getAtomicExprRule());
                                        }
                                        set(current, "filter", lv_filter_51_0, "Expr");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        }

                        otherlv_52 = (Token) match(input, 17, FOLLOW_17_in_ruleAtomicExpr4960);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_52, grammarAccess.getAtomicExprAccess()
                                    .getRightCurlyBracketKeyword_11_6());

                        }

                    }

                }
                    break;
                case 13:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2297:6:
                // (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2297:6:
                    // (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2297:8:
                    // otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')'
                    {
                        otherlv_53 = (Token) match(input, 40, FOLLOW_40_in_ruleAtomicExpr4980);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_53, grammarAccess.getAtomicExprAccess()
                                    .getLeftParenthesisKeyword_12_0());

                        }
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess.getAtomicExprAccess()
                                    .getExprParserRuleCall_12_1());

                        }
                        pushFollow(FOLLOW_ruleExpr_in_ruleAtomicExpr5002);
                        this_Expr_54 = ruleExpr();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            current = this_Expr_54;
                            afterParserOrEnumRuleCall();

                        }
                        otherlv_55 = (Token) match(input, 42, FOLLOW_42_in_ruleAtomicExpr5013);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_55, grammarAccess.getAtomicExprAccess()
                                    .getRightParenthesisKeyword_12_2());

                        }

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleAtomicExpr"

    // $ANTLR start "entryRuleBuiltInFunc"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2322:1:
    // entryRuleBuiltInFunc returns [String current=null] : iv_ruleBuiltInFunc=
    // ruleBuiltInFunc EOF ;
    public final String entryRuleBuiltInFunc() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltInFunc = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2323:2:
            // (iv_ruleBuiltInFunc= ruleBuiltInFunc EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2324:2:
            // iv_ruleBuiltInFunc= ruleBuiltInFunc EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getBuiltInFuncRule());
                }
                pushFollow(FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5051);
                iv_ruleBuiltInFunc = ruleBuiltInFunc();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleBuiltInFunc.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleBuiltInFunc5062);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleBuiltInFunc"

    // $ANTLR start "ruleBuiltInFunc"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2331:1:
    // ruleBuiltInFunc returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : (kw= 'connected' | kw= 'property_lookup' |
    // kw= 'class_of' | kw= 'type' | kw= 'has_type' | kw= 'bound' | kw=
    // 'contained' | kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists'
    // | kw= 'sum' | kw= 'analysis' ) ;
    public final AntlrDatatypeRuleToken ruleBuiltInFunc() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2334:28:
            // ( (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw=
            // 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw=
            // 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw=
            // 'sum' | kw= 'analysis' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2335:1:
            // (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' | kw=
            // 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' | kw=
            // 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' | kw=
            // 'sum' | kw= 'analysis' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2335:1:
                // (kw= 'connected' | kw= 'property_lookup' | kw= 'class_of' |
                // kw= 'type' | kw= 'has_type' | kw= 'bound' | kw= 'contained' |
                // kw= 'conn_source' | kw= 'conn_dest' | kw= 'property_exists' |
                // kw= 'sum' | kw= 'analysis' )
                int alt34 = 12;
                switch (input.LA(1)) {
                case 70: {
                    alt34 = 1;
                }
                    break;
                case 71: {
                    alt34 = 2;
                }
                    break;
                case 72: {
                    alt34 = 3;
                }
                    break;
                case 73: {
                    alt34 = 4;
                }
                    break;
                case 74: {
                    alt34 = 5;
                }
                    break;
                case 75: {
                    alt34 = 6;
                }
                    break;
                case 76: {
                    alt34 = 7;
                }
                    break;
                case 77: {
                    alt34 = 8;
                }
                    break;
                case 78: {
                    alt34 = 9;
                }
                    break;
                case 79: {
                    alt34 = 10;
                }
                    break;
                case 80: {
                    alt34 = 11;
                }
                    break;
                case 81: {
                    alt34 = 12;
                }
                    break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 34, 0, input);

                    throw nvae;
                }

                switch (alt34) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2336:2:
                // kw= 'connected'
                {
                    kw = (Token) match(input, 70, FOLLOW_70_in_ruleBuiltInFunc5100);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getConnectedKeyword_0());

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2343:2:
                // kw= 'property_lookup'
                {
                    kw = (Token) match(input, 71, FOLLOW_71_in_ruleBuiltInFunc5119);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getProperty_lookupKeyword_1());

                    }

                }
                    break;
                case 3:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2350:2:
                // kw= 'class_of'
                {
                    kw = (Token) match(input, 72, FOLLOW_72_in_ruleBuiltInFunc5138);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getClass_ofKeyword_2());

                    }

                }
                    break;
                case 4:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2357:2:
                // kw= 'type'
                {
                    kw = (Token) match(input, 73, FOLLOW_73_in_ruleBuiltInFunc5157);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getTypeKeyword_3());

                    }

                }
                    break;
                case 5:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2364:2:
                // kw= 'has_type'
                {
                    kw = (Token) match(input, 74, FOLLOW_74_in_ruleBuiltInFunc5176);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getHas_typeKeyword_4());

                    }

                }
                    break;
                case 6:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2371:2:
                // kw= 'bound'
                {
                    kw = (Token) match(input, 75, FOLLOW_75_in_ruleBuiltInFunc5195);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getBoundKeyword_5());

                    }

                }
                    break;
                case 7:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2378:2:
                // kw= 'contained'
                {
                    kw = (Token) match(input, 76, FOLLOW_76_in_ruleBuiltInFunc5214);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getContainedKeyword_6());

                    }

                }
                    break;
                case 8:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2385:2:
                // kw= 'conn_source'
                {
                    kw = (Token) match(input, 77, FOLLOW_77_in_ruleBuiltInFunc5233);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getConn_sourceKeyword_7());

                    }

                }
                    break;
                case 9:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2392:2:
                // kw= 'conn_dest'
                {
                    kw = (Token) match(input, 78, FOLLOW_78_in_ruleBuiltInFunc5252);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getConn_destKeyword_8());

                    }

                }
                    break;
                case 10:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2399:2:
                // kw= 'property_exists'
                {
                    kw = (Token) match(input, 79, FOLLOW_79_in_ruleBuiltInFunc5271);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getProperty_existsKeyword_9());

                    }

                }
                    break;
                case 11:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2406:2:
                // kw= 'sum'
                {
                    kw = (Token) match(input, 80, FOLLOW_80_in_ruleBuiltInFunc5290);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess().getSumKeyword_10());

                    }

                }
                    break;
                case 12:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2413:2:
                // kw= 'analysis'
                {
                    kw = (Token) match(input, 81, FOLLOW_81_in_ruleBuiltInFunc5309);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getBuiltInFuncAccess()
                                .getAnalysisKeyword_11());

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleBuiltInFunc"

    // $ANTLR start "entryRuleRealTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2426:1:
    // entryRuleRealTerm returns [EObject current=null] : iv_ruleRealTerm=
    // ruleRealTerm EOF ;
    public final EObject entryRuleRealTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2427:2:
            // (iv_ruleRealTerm= ruleRealTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2428:2:
            // iv_ruleRealTerm= ruleRealTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getRealTermRule());
                }
                pushFollow(FOLLOW_ruleRealTerm_in_entryRuleRealTerm5349);
                iv_ruleRealTerm = ruleRealTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleRealTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleRealTerm5359);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleRealTerm"

    // $ANTLR start "ruleRealTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2435:1:
    // ruleRealTerm returns [EObject current=null] : ( ( (lv_value_0_0=
    // ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleRealTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2438:28:
            // ( ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID )
            // )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2439:1:
            // ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID )
            // )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2439:1:
                // ( ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID
                // ) )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2439:2:
                // ( (lv_value_0_0= ruleUnsignedReal ) ) ( (otherlv_1= RULE_ID )
                // )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2439:2:
                    // ( (lv_value_0_0= ruleUnsignedReal ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2440:1:
                    // (lv_value_0_0= ruleUnsignedReal )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2440:1:
                        // (lv_value_0_0= ruleUnsignedReal )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2441:3:
                        // lv_value_0_0= ruleUnsignedReal
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getRealTermAccess()
                                        .getValueUnsignedRealParserRuleCall_0_0());

                            }
                            pushFollow(FOLLOW_ruleUnsignedReal_in_ruleRealTerm5405);
                            lv_value_0_0 = ruleUnsignedReal();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getRealTermRule());
                                }
                                set(current, "value", lv_value_0_0, "UnsignedReal");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2457:2:
                    // ( (otherlv_1= RULE_ID ) )?
                    int alt35 = 2;
                    int LA35_0 = input.LA(1);

                    if ((LA35_0 == RULE_ID)) {
                        int LA35_1 = input.LA(2);

                        if ((LA35_1 == EOF || LA35_1 == RULE_ID || LA35_1 == 17 || LA35_1 == 39
                                || (LA35_1 >= 41 && LA35_1 <= 43) || (LA35_1 >= 45 && LA35_1 <= 58)
                                || (LA35_1 >= 63 && LA35_1 <= 64) || LA35_1 == 68 || LA35_1 == 82
                                || LA35_1 == 85 || LA35_1 == 88 || (LA35_1 >= 97 && LA35_1 <= 98))) {
                            alt35 = 1;
                        }
                    }
                    switch (alt35) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2458:1:
                    // (otherlv_1= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2458:1:
                        // (otherlv_1= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2459:3:
                        // otherlv_1= RULE_ID
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getRealTermRule());
                                }

                            }
                            otherlv_1 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleRealTerm5425);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_1, grammarAccess.getRealTermAccess()
                                        .getUnitUnitLiteralCrossReference_1_0());

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleRealTerm"

    // $ANTLR start "entryRuleUnsignedReal"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2478:1:
    // entryRuleUnsignedReal returns [String current=null] :
    // iv_ruleUnsignedReal= ruleUnsignedReal EOF ;
    public final String entryRuleUnsignedReal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedReal = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2479:2:
            // (iv_ruleUnsignedReal= ruleUnsignedReal EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2480:2:
            // iv_ruleUnsignedReal= ruleUnsignedReal EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getUnsignedRealRule());
                }
                pushFollow(FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5463);
                iv_ruleUnsignedReal = ruleUnsignedReal();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleUnsignedReal.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleUnsignedReal5474);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleUnsignedReal"

    // $ANTLR start "ruleUnsignedReal"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2487:1:
    // ruleUnsignedReal returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : this_REAL_LIT_0= RULE_REAL_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedReal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_REAL_LIT_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2490:28:
            // (this_REAL_LIT_0= RULE_REAL_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2491:5:
            // this_REAL_LIT_0= RULE_REAL_LIT
            {
                this_REAL_LIT_0 = (Token) match(input, RULE_REAL_LIT,
                        FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5513);
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current.merge(this_REAL_LIT_0);

                }
                if (state.backtracking == 0) {

                    newLeafNode(this_REAL_LIT_0, grammarAccess.getUnsignedRealAccess()
                            .getREAL_LITTerminalRuleCall());

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleUnsignedReal"

    // $ANTLR start "entryRuleIntegerTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2506:1:
    // entryRuleIntegerTerm returns [EObject current=null] : iv_ruleIntegerTerm=
    // ruleIntegerTerm EOF ;
    public final EObject entryRuleIntegerTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2507:2:
            // (iv_ruleIntegerTerm= ruleIntegerTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2508:2:
            // iv_ruleIntegerTerm= ruleIntegerTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getIntegerTermRule());
                }
                pushFollow(FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5557);
                iv_ruleIntegerTerm = ruleIntegerTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleIntegerTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleIntegerTerm5567);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleIntegerTerm"

    // $ANTLR start "ruleIntegerTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2515:1:
    // ruleIntegerTerm returns [EObject current=null] : ( ( (lv_value_0_0=
    // ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )? ) ;
    public final EObject ruleIntegerTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        AntlrDatatypeRuleToken lv_value_0_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2518:28:
            // ( ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID )
            // )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2519:1:
            // ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID ) )?
            // )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2519:1:
                // ( ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID
                // ) )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2519:2:
                // ( (lv_value_0_0= ruleUnsignedInt ) ) ( (otherlv_1= RULE_ID )
                // )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2519:2:
                    // ( (lv_value_0_0= ruleUnsignedInt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2520:1:
                    // (lv_value_0_0= ruleUnsignedInt )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2520:1:
                        // (lv_value_0_0= ruleUnsignedInt )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2521:3:
                        // lv_value_0_0= ruleUnsignedInt
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getIntegerTermAccess()
                                        .getValueUnsignedIntParserRuleCall_0_0());

                            }
                            pushFollow(FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5613);
                            lv_value_0_0 = ruleUnsignedInt();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getIntegerTermRule());
                                }
                                set(current, "value", lv_value_0_0, "UnsignedInt");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2537:2:
                    // ( (otherlv_1= RULE_ID ) )?
                    int alt36 = 2;
                    int LA36_0 = input.LA(1);

                    if ((LA36_0 == RULE_ID)) {
                        int LA36_1 = input.LA(2);

                        if ((LA36_1 == EOF || LA36_1 == RULE_ID || LA36_1 == 17 || LA36_1 == 39
                                || (LA36_1 >= 41 && LA36_1 <= 43) || (LA36_1 >= 45 && LA36_1 <= 58)
                                || (LA36_1 >= 63 && LA36_1 <= 64) || LA36_1 == 68 || LA36_1 == 82
                                || LA36_1 == 85 || LA36_1 == 88 || (LA36_1 >= 97 && LA36_1 <= 98))) {
                            alt36 = 1;
                        }
                    }
                    switch (alt36) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2538:1:
                    // (otherlv_1= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2538:1:
                        // (otherlv_1= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2539:3:
                        // otherlv_1= RULE_ID
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess.getIntegerTermRule());
                                }

                            }
                            otherlv_1 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleIntegerTerm5633);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_1, grammarAccess.getIntegerTermAccess()
                                        .getUnitUnitLiteralCrossReference_1_0());

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleIntegerTerm"

    // $ANTLR start "entryRuleUnsignedInt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2558:1:
    // entryRuleUnsignedInt returns [String current=null] : iv_ruleUnsignedInt=
    // ruleUnsignedInt EOF ;
    public final String entryRuleUnsignedInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnsignedInt = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2559:2:
            // (iv_ruleUnsignedInt= ruleUnsignedInt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2560:2:
            // iv_ruleUnsignedInt= ruleUnsignedInt EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getUnsignedIntRule());
                }
                pushFollow(FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5671);
                iv_ruleUnsignedInt = ruleUnsignedInt();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleUnsignedInt.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleUnsignedInt5682);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleUnsignedInt"

    // $ANTLR start "ruleUnsignedInt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2567:1:
    // ruleUnsignedInt returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleUnsignedInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2570:28:
            // (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2571:5:
            // this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
                this_INTEGER_LIT_0 = (Token) match(input, RULE_INTEGER_LIT,
                        FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5721);
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current.merge(this_INTEGER_LIT_0);

                }
                if (state.backtracking == 0) {

                    newLeafNode(this_INTEGER_LIT_0, grammarAccess.getUnsignedIntAccess()
                            .getINTEGER_LITTerminalRuleCall());

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleUnsignedInt"

    // $ANTLR start "entryRuleResoluteSubclause"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2586:1:
    // entryRuleResoluteSubclause returns [EObject current=null] :
    // iv_ruleResoluteSubclause= ruleResoluteSubclause EOF ;
    public final EObject entryRuleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResoluteSubclause = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2587:2:
            // (iv_ruleResoluteSubclause= ruleResoluteSubclause EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2588:2:
            // iv_ruleResoluteSubclause= ruleResoluteSubclause EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getResoluteSubclauseRule());
                }
                pushFollow(FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5765);
                iv_ruleResoluteSubclause = ruleResoluteSubclause();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleResoluteSubclause;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleResoluteSubclause5775);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleResoluteSubclause"

    // $ANTLR start "ruleResoluteSubclause"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2595:1:
    // ruleResoluteSubclause returns [EObject current=null] : ( () (
    // (lv_proves_1_0= ruleProveStatement ) )* ) ;
    public final EObject ruleResoluteSubclause() throws RecognitionException {
        EObject current = null;

        EObject lv_proves_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2598:28:
            // ( ( () ( (lv_proves_1_0= ruleProveStatement ) )* ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2599:1:
            // ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2599:1:
                // ( () ( (lv_proves_1_0= ruleProveStatement ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2599:2:
                // () ( (lv_proves_1_0= ruleProveStatement ) )*
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2599:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2600:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess
                                    .getResoluteSubclauseAccess().getResoluteSubclauseAction_0(),
                                    current);

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2605:2:
                    // ( (lv_proves_1_0= ruleProveStatement ) )*
                    loop37: do {
                        int alt37 = 2;
                        int LA37_0 = input.LA(1);

                        if ((LA37_0 == 82)) {
                            alt37 = 1;
                        }

                        switch (alt37) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2606:1:
                        // (lv_proves_1_0= ruleProveStatement )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2606:1:
                            // (lv_proves_1_0= ruleProveStatement )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2607:3:
                            // lv_proves_1_0= ruleProveStatement
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getResoluteSubclauseAccess()
                                            .getProvesProveStatementParserRuleCall_1_0());

                                }
                                pushFollow(FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5830);
                                lv_proves_1_0 = ruleProveStatement();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getResoluteSubclauseRule());
                                    }
                                    add(current, "proves", lv_proves_1_0, "ProveStatement");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }
                            break;

                        default:
                            break loop37;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleResoluteSubclause"

    // $ANTLR start "entryRuleProveStatement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2631:1:
    // entryRuleProveStatement returns [EObject current=null] :
    // iv_ruleProveStatement= ruleProveStatement EOF ;
    public final EObject entryRuleProveStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProveStatement = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2632:2:
            // (iv_ruleProveStatement= ruleProveStatement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2633:2:
            // iv_ruleProveStatement= ruleProveStatement EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getProveStatementRule());
                }
                pushFollow(FOLLOW_ruleProveStatement_in_entryRuleProveStatement5867);
                iv_ruleProveStatement = ruleProveStatement();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleProveStatement;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleProveStatement5877);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleProveStatement"

    // $ANTLR start "ruleProveStatement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2640:1:
    // ruleProveStatement returns [EObject current=null] : (otherlv_0= 'prove' (
    // (lv_expr_1_0= ruleExpr ) ) ) ;
    public final EObject ruleProveStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        EObject lv_expr_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2643:28:
            // ( (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2644:1:
            // (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2644:1:
                // (otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2644:3:
                // otherlv_0= 'prove' ( (lv_expr_1_0= ruleExpr ) )
                {
                    otherlv_0 = (Token) match(input, 82, FOLLOW_82_in_ruleProveStatement5914);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_0, grammarAccess.getProveStatementAccess()
                                .getProveKeyword_0());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2648:1:
                    // ( (lv_expr_1_0= ruleExpr ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2649:1:
                    // (lv_expr_1_0= ruleExpr )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2649:1:
                        // (lv_expr_1_0= ruleExpr )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2650:3:
                        // lv_expr_1_0= ruleExpr
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getProveStatementAccess()
                                        .getExprExprParserRuleCall_1_0());

                            }
                            pushFollow(FOLLOW_ruleExpr_in_ruleProveStatement5935);
                            lv_expr_1_0 = ruleExpr();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getProveStatementRule());
                                }
                                set(current, "expr", lv_expr_1_0, "Expr");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleProveStatement"

    // $ANTLR start "entryRuleContainedPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2676:1:
    // entryRuleContainedPropertyAssociation returns [EObject current=null] :
    // iv_ruleContainedPropertyAssociation= ruleContainedPropertyAssociation EOF
    // ;
    public final EObject entryRuleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainedPropertyAssociation = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2677:2:
            // (iv_ruleContainedPropertyAssociation=
            // ruleContainedPropertyAssociation EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2678:2:
            // iv_ruleContainedPropertyAssociation=
            // ruleContainedPropertyAssociation EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getContainedPropertyAssociationRule());
                }
                pushFollow(FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation5973);
                iv_ruleContainedPropertyAssociation = ruleContainedPropertyAssociation();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleContainedPropertyAssociation;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleContainedPropertyAssociation5983);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleContainedPropertyAssociation"

    // $ANTLR start "ruleContainedPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2685:1:
    // ruleContainedPropertyAssociation returns [EObject current=null] : ( ( (
    // ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) ) (
    // (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0=
    // ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' ( (lv_ownedValue_6_0=
    // ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies' otherlv_8=
    // 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10= ',' (
    // (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )? (otherlv_12= 'in'
    // otherlv_13= 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')'
    // )? otherlv_17= ';' ) ;
    public final EObject ruleContainedPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token lv_append_2_0 = null;
        Token lv_constant_3_0 = null;
        Token otherlv_5 = null;
        Token otherlv_7 = null;
        Token otherlv_8 = null;
        Token otherlv_10 = null;
        Token otherlv_12 = null;
        Token otherlv_13 = null;
        Token otherlv_14 = null;
        Token otherlv_16 = null;
        Token otherlv_17 = null;
        EObject lv_ownedValue_4_0 = null;

        EObject lv_ownedValue_6_0 = null;

        EObject lv_appliesTo_9_0 = null;

        EObject lv_appliesTo_11_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2688:28:
            // ( ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>'
            // ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0=
            // ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' (
            // (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0=
            // ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0=
            // ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13=
            // 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            // otherlv_17= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2689:1:
            // ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' )
            // ) ) ( (lv_constant_3_0= 'constant' ) )? ( ( (lv_ownedValue_4_0=
            // ruleOptionalModalPropertyValue ) ) (otherlv_5= ',' (
            // (lv_ownedValue_6_0= ruleOptionalModalPropertyValue ) ) )* )
            // (otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0=
            // ruleContainmentPath ) ) (otherlv_10= ',' ( (lv_appliesTo_11_0=
            // ruleContainmentPath ) ) )* )? (otherlv_12= 'in' otherlv_13=
            // 'binding' otherlv_14= '(' ( ( ruleQCREF ) ) otherlv_16= ')' )?
            // otherlv_17= ';' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2689:1:
                // ( ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0=
                // '+=>' ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( (
                // (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
                // (otherlv_5= ',' ( (lv_ownedValue_6_0=
                // ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies'
                // otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                // (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath )
                // ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14=
                // '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2689:2:
                // ( ( ruleQPREF ) ) (otherlv_1= '=>' | ( (lv_append_2_0= '+=>'
                // ) ) ) ( (lv_constant_3_0= 'constant' ) )? ( (
                // (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
                // (otherlv_5= ',' ( (lv_ownedValue_6_0=
                // ruleOptionalModalPropertyValue ) ) )* ) (otherlv_7= 'applies'
                // otherlv_8= 'to' ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                // (otherlv_10= ',' ( (lv_appliesTo_11_0= ruleContainmentPath )
                // ) )* )? (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14=
                // '(' ( ( ruleQCREF ) ) otherlv_16= ')' )? otherlv_17= ';'
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2689:2:
                    // ( ( ruleQPREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2690:1:
                    // ( ruleQPREF )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2690:1:
                        // ( ruleQPREF )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2691:3:
                        // ruleQPREF
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getContainedPropertyAssociationRule());
                                }

                            }
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess
                                        .getContainedPropertyAssociationAccess()
                                        .getPropertyPropertyCrossReference_0_0());

                            }
                            pushFollow(FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6031);
                            ruleQPREF();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2704:2:
                    // (otherlv_1= '=>' | ( (lv_append_2_0= '+=>' ) ) )
                    int alt38 = 2;
                    int LA38_0 = input.LA(1);

                    if ((LA38_0 == 45)) {
                        alt38 = 1;
                    } else if ((LA38_0 == 83)) {
                        alt38 = 2;
                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return current;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 38, 0, input);

                        throw nvae;
                    }
                    switch (alt38) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2704:4:
                    // otherlv_1= '=>'
                    {
                        otherlv_1 = (Token) match(input, 45,
                                FOLLOW_45_in_ruleContainedPropertyAssociation6044);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_1, grammarAccess
                                    .getContainedPropertyAssociationAccess()
                                    .getEqualsSignGreaterThanSignKeyword_1_0());

                        }

                    }
                        break;
                    case 2:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2709:6:
                    // ( (lv_append_2_0= '+=>' ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2709:6:
                        // ( (lv_append_2_0= '+=>' ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:1:
                        // (lv_append_2_0= '+=>' )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2710:1:
                            // (lv_append_2_0= '+=>' )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2711:3:
                            // lv_append_2_0= '+=>'
                            {
                                lv_append_2_0 = (Token) match(input, 83,
                                        FOLLOW_83_in_ruleContainedPropertyAssociation6068);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(
                                            lv_append_2_0,
                                            grammarAccess
                                                    .getContainedPropertyAssociationAccess()
                                                    .getAppendPlusSignEqualsSignGreaterThanSignKeyword_1_1_0());

                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getContainedPropertyAssociationRule());
                                    }
                                    setWithLastConsumed(current, "append", true, "+=>");

                                }

                            }

                        }

                    }
                        break;

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2724:3:
                    // ( (lv_constant_3_0= 'constant' ) )?
                    int alt39 = 2;
                    int LA39_0 = input.LA(1);

                    if ((LA39_0 == 84)) {
                        alt39 = 1;
                    }
                    switch (alt39) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2725:1:
                    // (lv_constant_3_0= 'constant' )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2725:1:
                        // (lv_constant_3_0= 'constant' )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2726:3:
                        // lv_constant_3_0= 'constant'
                        {
                            lv_constant_3_0 = (Token) match(input, 84,
                                    FOLLOW_84_in_ruleContainedPropertyAssociation6100);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_constant_3_0, grammarAccess
                                        .getContainedPropertyAssociationAccess()
                                        .getConstantConstantKeyword_2_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getContainedPropertyAssociationRule());
                                }
                                setWithLastConsumed(current, "constant", true, "constant");

                            }

                        }

                    }
                        break;

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2739:3:
                    // ( ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
                    // ) (otherlv_5= ',' ( (lv_ownedValue_6_0=
                    // ruleOptionalModalPropertyValue ) ) )* )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2739:4:
                    // ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue ) )
                    // (otherlv_5= ',' ( (lv_ownedValue_6_0=
                    // ruleOptionalModalPropertyValue ) ) )*
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2739:4:
                        // ( (lv_ownedValue_4_0= ruleOptionalModalPropertyValue
                        // ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2740:1:
                        // (lv_ownedValue_4_0= ruleOptionalModalPropertyValue )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2740:1:
                            // (lv_ownedValue_4_0=
                            // ruleOptionalModalPropertyValue )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2741:3:
                            // lv_ownedValue_4_0= ruleOptionalModalPropertyValue
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getContainedPropertyAssociationAccess()
                                            .getOwnedValueOptionalModalPropertyValueParserRuleCall_3_0_0());

                                }
                                pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6136);
                                lv_ownedValue_4_0 = ruleOptionalModalPropertyValue();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getContainedPropertyAssociationRule());
                                    }
                                    add(current, "ownedValue", lv_ownedValue_4_0,
                                            "OptionalModalPropertyValue");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2757:2:
                        // (otherlv_5= ',' ( (lv_ownedValue_6_0=
                        // ruleOptionalModalPropertyValue ) ) )*
                        loop40: do {
                            int alt40 = 2;
                            int LA40_0 = input.LA(1);

                            if ((LA40_0 == 41)) {
                                alt40 = 1;
                            }

                            switch (alt40) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2757:4:
                            // otherlv_5= ',' ( (lv_ownedValue_6_0=
                            // ruleOptionalModalPropertyValue ) )
                            {
                                otherlv_5 = (Token) match(input, 41,
                                        FOLLOW_41_in_ruleContainedPropertyAssociation6149);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_5, grammarAccess
                                            .getContainedPropertyAssociationAccess()
                                            .getCommaKeyword_3_1_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2761:1:
                                // ( (lv_ownedValue_6_0=
                                // ruleOptionalModalPropertyValue ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2762:1:
                                // (lv_ownedValue_6_0=
                                // ruleOptionalModalPropertyValue )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2762:1:
                                    // (lv_ownedValue_6_0=
                                    // ruleOptionalModalPropertyValue )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2763:3:
                                    // lv_ownedValue_6_0=
                                    // ruleOptionalModalPropertyValue
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getContainedPropertyAssociationAccess()
                                                    .getOwnedValueOptionalModalPropertyValueParserRuleCall_3_1_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6170);
                                        lv_ownedValue_6_0 = ruleOptionalModalPropertyValue();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getContainedPropertyAssociationRule());
                                            }
                                            add(current, "ownedValue", lv_ownedValue_6_0,
                                                    "OptionalModalPropertyValue");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                            }
                                break;

                            default:
                                break loop40;
                            }
                        } while (true);

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2779:5:
                    // (otherlv_7= 'applies' otherlv_8= 'to' (
                    // (lv_appliesTo_9_0= ruleContainmentPath ) ) (otherlv_10=
                    // ',' ( (lv_appliesTo_11_0= ruleContainmentPath ) ) )* )?
                    int alt42 = 2;
                    int LA42_0 = input.LA(1);

                    if ((LA42_0 == 85)) {
                        alt42 = 1;
                    }
                    switch (alt42) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2779:7:
                    // otherlv_7= 'applies' otherlv_8= 'to' ( (lv_appliesTo_9_0=
                    // ruleContainmentPath ) ) (otherlv_10= ',' (
                    // (lv_appliesTo_11_0= ruleContainmentPath ) ) )*
                    {
                        otherlv_7 = (Token) match(input, 85,
                                FOLLOW_85_in_ruleContainedPropertyAssociation6186);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_7, grammarAccess
                                    .getContainedPropertyAssociationAccess()
                                    .getAppliesKeyword_4_0());

                        }
                        otherlv_8 = (Token) match(input, 86,
                                FOLLOW_86_in_ruleContainedPropertyAssociation6198);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_8, grammarAccess
                                    .getContainedPropertyAssociationAccess().getToKeyword_4_1());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2787:1:
                        // ( (lv_appliesTo_9_0= ruleContainmentPath ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:1:
                        // (lv_appliesTo_9_0= ruleContainmentPath )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2788:1:
                            // (lv_appliesTo_9_0= ruleContainmentPath )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2789:3:
                            // lv_appliesTo_9_0= ruleContainmentPath
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getContainedPropertyAssociationAccess()
                                            .getAppliesToContainmentPathParserRuleCall_4_2_0());

                                }
                                pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6219);
                                lv_appliesTo_9_0 = ruleContainmentPath();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getContainedPropertyAssociationRule());
                                    }
                                    add(current, "appliesTo", lv_appliesTo_9_0, "ContainmentPath");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2805:2:
                        // (otherlv_10= ',' ( (lv_appliesTo_11_0=
                        // ruleContainmentPath ) ) )*
                        loop41: do {
                            int alt41 = 2;
                            int LA41_0 = input.LA(1);

                            if ((LA41_0 == 41)) {
                                alt41 = 1;
                            }

                            switch (alt41) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2805:4:
                            // otherlv_10= ',' ( (lv_appliesTo_11_0=
                            // ruleContainmentPath ) )
                            {
                                otherlv_10 = (Token) match(input, 41,
                                        FOLLOW_41_in_ruleContainedPropertyAssociation6232);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_10, grammarAccess
                                            .getContainedPropertyAssociationAccess()
                                            .getCommaKeyword_4_3_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2809:1:
                                // ( (lv_appliesTo_11_0= ruleContainmentPath ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2810:1:
                                // (lv_appliesTo_11_0= ruleContainmentPath )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2810:1:
                                    // (lv_appliesTo_11_0= ruleContainmentPath )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2811:3:
                                    // lv_appliesTo_11_0= ruleContainmentPath
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getContainedPropertyAssociationAccess()
                                                    .getAppliesToContainmentPathParserRuleCall_4_3_1_0());

                                        }
                                        pushFollow(FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6253);
                                        lv_appliesTo_11_0 = ruleContainmentPath();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getContainedPropertyAssociationRule());
                                            }
                                            add(current, "appliesTo", lv_appliesTo_11_0,
                                                    "ContainmentPath");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                            }
                                break;

                            default:
                                break loop41;
                            }
                        } while (true);

                    }
                        break;

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2827:6:
                    // (otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' (
                    // ( ruleQCREF ) ) otherlv_16= ')' )?
                    int alt43 = 2;
                    int LA43_0 = input.LA(1);

                    if ((LA43_0 == 52)) {
                        alt43 = 1;
                    }
                    switch (alt43) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2827:8:
                    // otherlv_12= 'in' otherlv_13= 'binding' otherlv_14= '(' (
                    // ( ruleQCREF ) ) otherlv_16= ')'
                    {
                        otherlv_12 = (Token) match(input, 52,
                                FOLLOW_52_in_ruleContainedPropertyAssociation6270);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_12, grammarAccess
                                    .getContainedPropertyAssociationAccess().getInKeyword_5_0());

                        }
                        otherlv_13 = (Token) match(input, 87,
                                FOLLOW_87_in_ruleContainedPropertyAssociation6282);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_13, grammarAccess
                                    .getContainedPropertyAssociationAccess()
                                    .getBindingKeyword_5_1());

                        }
                        otherlv_14 = (Token) match(input, 40,
                                FOLLOW_40_in_ruleContainedPropertyAssociation6294);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_14, grammarAccess
                                    .getContainedPropertyAssociationAccess()
                                    .getLeftParenthesisKeyword_5_2());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2839:1:
                        // ( ( ruleQCREF ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2840:1:
                        // ( ruleQCREF )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2840:1:
                            // ( ruleQCREF )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2841:3:
                            // ruleQCREF
                            {
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getContainedPropertyAssociationRule());
                                    }

                                }
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getContainedPropertyAssociationAccess()
                                            .getInBindingClassifierCrossReference_5_3_0());

                                }
                                pushFollow(FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6317);
                                ruleQCREF();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_16 = (Token) match(input, 42,
                                FOLLOW_42_in_ruleContainedPropertyAssociation6329);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_16, grammarAccess
                                    .getContainedPropertyAssociationAccess()
                                    .getRightParenthesisKeyword_5_4());

                        }

                    }
                        break;

                    }

                    otherlv_17 = (Token) match(input, 88,
                            FOLLOW_88_in_ruleContainedPropertyAssociation6343);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_17, grammarAccess
                                .getContainedPropertyAssociationAccess().getSemicolonKeyword_6());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleContainedPropertyAssociation"

    // $ANTLR start "entryRuleContainmentPath"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2874:1:
    // entryRuleContainmentPath returns [EObject current=null] :
    // iv_ruleContainmentPath= ruleContainmentPath EOF ;
    public final EObject entryRuleContainmentPath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPath = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2875:2:
            // (iv_ruleContainmentPath= ruleContainmentPath EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2876:2:
            // iv_ruleContainmentPath= ruleContainmentPath EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getContainmentPathRule());
                }
                pushFollow(FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6383);
                iv_ruleContainmentPath = ruleContainmentPath();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleContainmentPath;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleContainmentPath6393);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleContainmentPath"

    // $ANTLR start "ruleContainmentPath"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2883:1:
    // ruleContainmentPath returns [EObject current=null] : ( () (
    // (lv_containmentPathElement_1_0= ruleContainmentPathElement ) )
    // (otherlv_2= '.' ( (lv_containmentPathElement_3_0=
    // ruleContainmentPathElement ) ) )* ) ;
    public final EObject ruleContainmentPath() throws RecognitionException {
        EObject current = null;

        Token otherlv_2 = null;
        EObject lv_containmentPathElement_1_0 = null;

        EObject lv_containmentPathElement_3_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2886:28:
            // ( ( () ( (lv_containmentPathElement_1_0=
            // ruleContainmentPathElement ) ) (otherlv_2= '.' (
            // (lv_containmentPathElement_3_0= ruleContainmentPathElement ) ) )*
            // ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2887:1:
            // ( () ( (lv_containmentPathElement_1_0= ruleContainmentPathElement
            // ) ) (otherlv_2= '.' ( (lv_containmentPathElement_3_0=
            // ruleContainmentPathElement ) ) )* )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2887:1:
                // ( () ( (lv_containmentPathElement_1_0=
                // ruleContainmentPathElement ) ) (otherlv_2= '.' (
                // (lv_containmentPathElement_3_0= ruleContainmentPathElement )
                // ) )* )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2887:2:
                // () ( (lv_containmentPathElement_1_0=
                // ruleContainmentPathElement ) ) (otherlv_2= '.' (
                // (lv_containmentPathElement_3_0= ruleContainmentPathElement )
                // ) )*
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2887:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2888:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess
                                    .getContainmentPathAccess().getContainedNamedElementAction_0(),
                                    current);

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2893:2:
                    // ( (lv_containmentPathElement_1_0=
                    // ruleContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2894:1:
                    // (lv_containmentPathElement_1_0=
                    // ruleContainmentPathElement )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2894:1:
                        // (lv_containmentPathElement_1_0=
                        // ruleContainmentPathElement )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2895:3:
                        // lv_containmentPathElement_1_0=
                        // ruleContainmentPathElement
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess
                                        .getContainmentPathAccess()
                                        .getContainmentPathElementContainmentPathElementParserRuleCall_1_0());

                            }
                            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6448);
                            lv_containmentPathElement_1_0 = ruleContainmentPathElement();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getContainmentPathRule());
                                }
                                add(current, "containmentPathElement",
                                        lv_containmentPathElement_1_0, "ContainmentPathElement");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2911:2:
                    // (otherlv_2= '.' ( (lv_containmentPathElement_3_0=
                    // ruleContainmentPathElement ) ) )*
                    loop44: do {
                        int alt44 = 2;
                        int LA44_0 = input.LA(1);

                        if ((LA44_0 == 67)) {
                            alt44 = 1;
                        }

                        switch (alt44) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2911:4:
                        // otherlv_2= '.' ( (lv_containmentPathElement_3_0=
                        // ruleContainmentPathElement ) )
                        {
                            otherlv_2 = (Token) match(input, 67,
                                    FOLLOW_67_in_ruleContainmentPath6461);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_2, grammarAccess.getContainmentPathAccess()
                                        .getFullStopKeyword_2_0());

                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2915:1:
                            // ( (lv_containmentPathElement_3_0=
                            // ruleContainmentPathElement ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2916:1:
                            // (lv_containmentPathElement_3_0=
                            // ruleContainmentPathElement )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2916:1:
                                // (lv_containmentPathElement_3_0=
                                // ruleContainmentPathElement )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2917:3:
                                // lv_containmentPathElement_3_0=
                                // ruleContainmentPathElement
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess
                                                .getContainmentPathAccess()
                                                .getContainmentPathElementContainmentPathElementParserRuleCall_2_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6482);
                                    lv_containmentPathElement_3_0 = ruleContainmentPathElement();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getContainmentPathRule());
                                        }
                                        add(current, "containmentPathElement",
                                                lv_containmentPathElement_3_0,
                                                "ContainmentPathElement");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop44;
                        }
                    } while (true);

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleContainmentPath"

    // $ANTLR start "entryRuleOptionalModalPropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2943:1:
    // entryRuleOptionalModalPropertyValue returns [EObject current=null] :
    // iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue EOF ;
    public final EObject entryRuleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptionalModalPropertyValue = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2944:2:
            // (iv_ruleOptionalModalPropertyValue=
            // ruleOptionalModalPropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2945:2:
            // iv_ruleOptionalModalPropertyValue= ruleOptionalModalPropertyValue
            // EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getOptionalModalPropertyValueRule());
                }
                pushFollow(FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6522);
                iv_ruleOptionalModalPropertyValue = ruleOptionalModalPropertyValue();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleOptionalModalPropertyValue;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6532);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleOptionalModalPropertyValue"

    // $ANTLR start "ruleOptionalModalPropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2952:1:
    // ruleOptionalModalPropertyValue returns [EObject current=null] : ( (
    // (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1= 'in'
    // otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) ) (otherlv_5=
    // ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? ) ;
    public final EObject ruleOptionalModalPropertyValue() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_2 = null;
        Token otherlv_3 = null;
        Token otherlv_4 = null;
        Token otherlv_5 = null;
        Token otherlv_6 = null;
        Token otherlv_7 = null;
        EObject lv_ownedValue_0_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2955:28:
            // ( ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1=
            // 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) )
            // (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            // )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2956:1:
            // ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1=
            // 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID ) )
            // (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7= ')' )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2956:1:
                // ( ( (lv_ownedValue_0_0= rulePropertyExpression ) )
                // (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' (
                // (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID
                // ) ) )* otherlv_7= ')' )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2956:2:
                // ( (lv_ownedValue_0_0= rulePropertyExpression ) ) (otherlv_1=
                // 'in' otherlv_2= 'modes' otherlv_3= '(' ( (otherlv_4= RULE_ID
                // ) ) (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )* otherlv_7=
                // ')' )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2956:2:
                    // ( (lv_ownedValue_0_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2957:1:
                    // (lv_ownedValue_0_0= rulePropertyExpression )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2957:1:
                        // (lv_ownedValue_0_0= rulePropertyExpression )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2958:3:
                        // lv_ownedValue_0_0= rulePropertyExpression
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess
                                        .getOptionalModalPropertyValueAccess()
                                        .getOwnedValuePropertyExpressionParserRuleCall_0_0());

                            }
                            pushFollow(FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6578);
                            lv_ownedValue_0_0 = rulePropertyExpression();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getOptionalModalPropertyValueRule());
                                }
                                set(current, "ownedValue", lv_ownedValue_0_0, "PropertyExpression");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2974:2:
                    // (otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' (
                    // (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6=
                    // RULE_ID ) ) )* otherlv_7= ')' )?
                    int alt46 = 2;
                    int LA46_0 = input.LA(1);

                    if ((LA46_0 == 52)) {
                        int LA46_1 = input.LA(2);

                        if ((LA46_1 == 89)) {
                            alt46 = 1;
                        }
                    }
                    switch (alt46) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2974:4:
                    // otherlv_1= 'in' otherlv_2= 'modes' otherlv_3= '(' (
                    // (otherlv_4= RULE_ID ) ) (otherlv_5= ',' ( (otherlv_6=
                    // RULE_ID ) ) )* otherlv_7= ')'
                    {
                        otherlv_1 = (Token) match(input, 52,
                                FOLLOW_52_in_ruleOptionalModalPropertyValue6591);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_1, grammarAccess
                                    .getOptionalModalPropertyValueAccess().getInKeyword_1_0());

                        }
                        otherlv_2 = (Token) match(input, 89,
                                FOLLOW_89_in_ruleOptionalModalPropertyValue6603);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_2, grammarAccess
                                    .getOptionalModalPropertyValueAccess().getModesKeyword_1_1());

                        }
                        otherlv_3 = (Token) match(input, 40,
                                FOLLOW_40_in_ruleOptionalModalPropertyValue6615);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess
                                    .getOptionalModalPropertyValueAccess()
                                    .getLeftParenthesisKeyword_1_2());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2986:1:
                        // ( (otherlv_4= RULE_ID ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2987:1:
                        // (otherlv_4= RULE_ID )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2987:1:
                            // (otherlv_4= RULE_ID )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2988:3:
                            // otherlv_4= RULE_ID
                            {
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getOptionalModalPropertyValueRule());
                                    }

                                }
                                otherlv_4 = (Token) match(input, RULE_ID,
                                        FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6635);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_4, grammarAccess
                                            .getOptionalModalPropertyValueAccess()
                                            .getInModeModeCrossReference_1_3_0());

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2999:2:
                        // (otherlv_5= ',' ( (otherlv_6= RULE_ID ) ) )*
                        loop45: do {
                            int alt45 = 2;
                            int LA45_0 = input.LA(1);

                            if ((LA45_0 == 41)) {
                                alt45 = 1;
                            }

                            switch (alt45) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:2999:4:
                            // otherlv_5= ',' ( (otherlv_6= RULE_ID ) )
                            {
                                otherlv_5 = (Token) match(input, 41,
                                        FOLLOW_41_in_ruleOptionalModalPropertyValue6648);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_5, grammarAccess
                                            .getOptionalModalPropertyValueAccess()
                                            .getCommaKeyword_1_4_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3003:1:
                                // ( (otherlv_6= RULE_ID ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3004:1:
                                // (otherlv_6= RULE_ID )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3004:1:
                                    // (otherlv_6= RULE_ID )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3005:3:
                                    // otherlv_6= RULE_ID
                                    {
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElement(grammarAccess
                                                        .getOptionalModalPropertyValueRule());
                                            }

                                        }
                                        otherlv_6 = (Token) match(input, RULE_ID,
                                                FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6668);
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            newLeafNode(otherlv_6, grammarAccess
                                                    .getOptionalModalPropertyValueAccess()
                                                    .getInModeModeCrossReference_1_4_1_0());

                                        }

                                    }

                                }

                            }
                                break;

                            default:
                                break loop45;
                            }
                        } while (true);

                        otherlv_7 = (Token) match(input, 42,
                                FOLLOW_42_in_ruleOptionalModalPropertyValue6682);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_7, grammarAccess
                                    .getOptionalModalPropertyValueAccess()
                                    .getRightParenthesisKeyword_1_5());

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleOptionalModalPropertyValue"

    // $ANTLR start "entryRulePropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3028:1:
    // entryRulePropertyValue returns [EObject current=null] :
    // iv_rulePropertyValue= rulePropertyValue EOF ;
    public final EObject entryRulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyValue = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3029:2:
            // (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3030:2:
            // iv_rulePropertyValue= rulePropertyValue EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getPropertyValueRule());
                }
                pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue6720);
                iv_rulePropertyValue = rulePropertyValue();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_rulePropertyValue;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRulePropertyValue6730);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRulePropertyValue"

    // $ANTLR start "rulePropertyValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3037:1:
    // rulePropertyValue returns [EObject current=null] : ( (lv_ownedValue_0_0=
    // rulePropertyExpression ) ) ;
    public final EObject rulePropertyValue() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedValue_0_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3040:28:
            // ( ( (lv_ownedValue_0_0= rulePropertyExpression ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3041:1:
            // ( (lv_ownedValue_0_0= rulePropertyExpression ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3041:1:
                // ( (lv_ownedValue_0_0= rulePropertyExpression ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3042:1:
                // (lv_ownedValue_0_0= rulePropertyExpression )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3042:1:
                    // (lv_ownedValue_0_0= rulePropertyExpression )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3043:3:
                    // lv_ownedValue_0_0= rulePropertyExpression
                    {
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess.getPropertyValueAccess()
                                    .getOwnedValuePropertyExpressionParserRuleCall_0());

                        }
                        pushFollow(FOLLOW_rulePropertyExpression_in_rulePropertyValue6775);
                        lv_ownedValue_0_0 = rulePropertyExpression();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            if (current == null) {
                                current = createModelElementForParent(grammarAccess
                                        .getPropertyValueRule());
                            }
                            set(current, "ownedValue", lv_ownedValue_0_0, "PropertyExpression");
                            afterParserOrEnumRuleCall();

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "rulePropertyValue"

    // $ANTLR start "entryRulePropertyExpression"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3067:1:
    // entryRulePropertyExpression returns [EObject current=null] :
    // iv_rulePropertyExpression= rulePropertyExpression EOF ;
    public final EObject entryRulePropertyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyExpression = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3068:2:
            // (iv_rulePropertyExpression= rulePropertyExpression EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3069:2:
            // iv_rulePropertyExpression= rulePropertyExpression EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getPropertyExpressionRule());
                }
                pushFollow(FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression6810);
                iv_rulePropertyExpression = rulePropertyExpression();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_rulePropertyExpression;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRulePropertyExpression6820);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRulePropertyExpression"

    // $ANTLR start "rulePropertyExpression"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3076:1:
    // rulePropertyExpression returns [EObject current=null] :
    // (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1=
    // ruleReferenceTerm | this_ComponentClassifierTerm_2=
    // ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm |
    // this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5=
    // ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm |
    // this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm |
    // this_BooleanLiteral_9= ruleBooleanLiteral |
    // this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm ) ;
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
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3079:28:
            // ( (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1=
            // ruleReferenceTerm | this_ComponentClassifierTerm_2=
            // ruleComponentClassifierTerm | this_ComputedTerm_3=
            // ruleComputedTerm | this_StringTerm_4= ruleStringTerm |
            // this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6=
            // ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm |
            // this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9=
            // ruleBooleanLiteral | this_LiteralorReferenceTerm_10=
            // ruleLiteralorReferenceTerm ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3080:1:
            // (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1=
            // ruleReferenceTerm | this_ComponentClassifierTerm_2=
            // ruleComponentClassifierTerm | this_ComputedTerm_3=
            // ruleComputedTerm | this_StringTerm_4= ruleStringTerm |
            // this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6=
            // ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm |
            // this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9=
            // ruleBooleanLiteral | this_LiteralorReferenceTerm_10=
            // ruleLiteralorReferenceTerm )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3080:1:
                // (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1=
                // ruleReferenceTerm | this_ComponentClassifierTerm_2=
                // ruleComponentClassifierTerm | this_ComputedTerm_3=
                // ruleComputedTerm | this_StringTerm_4= ruleStringTerm |
                // this_NumericRangeTerm_5= ruleNumericRangeTerm |
                // this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7=
                // ruleIntegerTerm | this_ListTerm_8= ruleListTerm |
                // this_BooleanLiteral_9= ruleBooleanLiteral |
                // this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )
                int alt47 = 11;
                alt47 = dfa47.predict(input);
                switch (alt47) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3081:5:
                // this_RecordTerm_0= ruleRecordTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getRecordTermParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleRecordTerm_in_rulePropertyExpression6867);
                    this_RecordTerm_0 = ruleRecordTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_RecordTerm_0;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3091:5:
                // this_ReferenceTerm_1= ruleReferenceTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getReferenceTermParserRuleCall_1());

                    }
                    pushFollow(FOLLOW_ruleReferenceTerm_in_rulePropertyExpression6894);
                    this_ReferenceTerm_1 = ruleReferenceTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ReferenceTerm_1;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 3:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3101:5:
                // this_ComponentClassifierTerm_2= ruleComponentClassifierTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getComponentClassifierTermParserRuleCall_2());

                    }
                    pushFollow(FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression6921);
                    this_ComponentClassifierTerm_2 = ruleComponentClassifierTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ComponentClassifierTerm_2;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 4:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3111:5:
                // this_ComputedTerm_3= ruleComputedTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getComputedTermParserRuleCall_3());

                    }
                    pushFollow(FOLLOW_ruleComputedTerm_in_rulePropertyExpression6948);
                    this_ComputedTerm_3 = ruleComputedTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ComputedTerm_3;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 5:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3121:5:
                // this_StringTerm_4= ruleStringTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getStringTermParserRuleCall_4());

                    }
                    pushFollow(FOLLOW_ruleStringTerm_in_rulePropertyExpression6975);
                    this_StringTerm_4 = ruleStringTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_StringTerm_4;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 6:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3131:5:
                // this_NumericRangeTerm_5= ruleNumericRangeTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getNumericRangeTermParserRuleCall_5());

                    }
                    pushFollow(FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7002);
                    this_NumericRangeTerm_5 = ruleNumericRangeTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_NumericRangeTerm_5;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 7:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3141:5:
                // this_RealTerm_6= ruleRealTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getRealTermParserRuleCall_6());

                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_rulePropertyExpression7029);
                    this_RealTerm_6 = ruleRealTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_RealTerm_6;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 8:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3151:5:
                // this_IntegerTerm_7= ruleIntegerTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getIntegerTermParserRuleCall_7());

                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7056);
                    this_IntegerTerm_7 = ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_IntegerTerm_7;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 9:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3161:5:
                // this_ListTerm_8= ruleListTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getListTermParserRuleCall_8());

                    }
                    pushFollow(FOLLOW_ruleListTerm_in_rulePropertyExpression7083);
                    this_ListTerm_8 = ruleListTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ListTerm_8;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 10:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3171:5:
                // this_BooleanLiteral_9= ruleBooleanLiteral
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getBooleanLiteralParserRuleCall_9());

                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7110);
                    this_BooleanLiteral_9 = ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_BooleanLiteral_9;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 11:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3181:5:
                // this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getPropertyExpressionAccess()
                                .getLiteralorReferenceTermParserRuleCall_10());

                    }
                    pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7137);
                    this_LiteralorReferenceTerm_10 = ruleLiteralorReferenceTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_LiteralorReferenceTerm_10;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "rulePropertyExpression"

    // $ANTLR start "entryRuleLiteralorReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3197:1:
    // entryRuleLiteralorReferenceTerm returns [EObject current=null] :
    // iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF ;
    public final EObject entryRuleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralorReferenceTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3198:2:
            // (iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3199:2:
            // iv_ruleLiteralorReferenceTerm= ruleLiteralorReferenceTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getLiteralorReferenceTermRule());
                }
                pushFollow(FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7172);
                iv_ruleLiteralorReferenceTerm = ruleLiteralorReferenceTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleLiteralorReferenceTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7182);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleLiteralorReferenceTerm"

    // $ANTLR start "ruleLiteralorReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3206:1:
    // ruleLiteralorReferenceTerm returns [EObject current=null] : ( ( ruleQPREF
    // ) ) ;
    public final EObject ruleLiteralorReferenceTerm() throws RecognitionException {
        EObject current = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3209:28:
            // ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3210:1:
            // ( ( ruleQPREF ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3210:1:
                // ( ( ruleQPREF ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3211:1:
                // ( ruleQPREF )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3211:1:
                    // ( ruleQPREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3212:3:
                    // ruleQPREF
                    {
                        if (state.backtracking == 0) {

                            if (current == null) {
                                current = createModelElement(grammarAccess
                                        .getLiteralorReferenceTermRule());
                            }

                        }
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess.getLiteralorReferenceTermAccess()
                                    .getNamedValueAbstractNamedValueCrossReference_0());

                        }
                        pushFollow(FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7229);
                        ruleQPREF();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            afterParserOrEnumRuleCall();

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleLiteralorReferenceTerm"

    // $ANTLR start "entryRuleBooleanLiteral"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3233:1:
    // entryRuleBooleanLiteral returns [EObject current=null] :
    // iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3234:2:
            // (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3235:2:
            // iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getBooleanLiteralRule());
                }
                pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7264);
                iv_ruleBooleanLiteral = ruleBooleanLiteral();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleBooleanLiteral;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleBooleanLiteral7274);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleBooleanLiteral"

    // $ANTLR start "ruleBooleanLiteral"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3242:1:
    // ruleBooleanLiteral returns [EObject current=null] : ( () ( (
    // (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0 = null;
        Token otherlv_2 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3245:28:
            // ( ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3246:1:
            // ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3246:1:
                // ( () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3246:2:
                // () ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3246:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3247:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess
                                    .getBooleanLiteralAccess().getBooleanLiteralAction_0(), current);

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3252:2:
                    // ( ( (lv_value_1_0= 'true' ) ) | otherlv_2= 'false' )
                    int alt48 = 2;
                    int LA48_0 = input.LA(1);

                    if ((LA48_0 == 90)) {
                        alt48 = 1;
                    } else if ((LA48_0 == 91)) {
                        alt48 = 2;
                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return current;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 48, 0, input);

                        throw nvae;
                    }
                    switch (alt48) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3252:3:
                    // ( (lv_value_1_0= 'true' ) )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3252:3:
                        // ( (lv_value_1_0= 'true' ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3253:1:
                        // (lv_value_1_0= 'true' )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3253:1:
                            // (lv_value_1_0= 'true' )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3254:3:
                            // lv_value_1_0= 'true'
                            {
                                lv_value_1_0 = (Token) match(input, 90,
                                        FOLLOW_90_in_ruleBooleanLiteral7327);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(lv_value_1_0, grammarAccess
                                            .getBooleanLiteralAccess().getValueTrueKeyword_1_0_0());

                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElement(grammarAccess
                                                .getBooleanLiteralRule());
                                    }
                                    setWithLastConsumed(current, "value", true, "true");

                                }

                            }

                        }

                    }
                        break;
                    case 2:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3268:7:
                    // otherlv_2= 'false'
                    {
                        otherlv_2 = (Token) match(input, 91, FOLLOW_91_in_ruleBooleanLiteral7358);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_2, grammarAccess.getBooleanLiteralAccess()
                                    .getFalseKeyword_1_1());

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleBooleanLiteral"

    // $ANTLR start "entryRuleConstantValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3280:1:
    // entryRuleConstantValue returns [EObject current=null] :
    // iv_ruleConstantValue= ruleConstantValue EOF ;
    public final EObject entryRuleConstantValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstantValue = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3281:2:
            // (iv_ruleConstantValue= ruleConstantValue EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3282:2:
            // iv_ruleConstantValue= ruleConstantValue EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getConstantValueRule());
                }
                pushFollow(FOLLOW_ruleConstantValue_in_entryRuleConstantValue7395);
                iv_ruleConstantValue = ruleConstantValue();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleConstantValue;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleConstantValue7405);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleConstantValue"

    // $ANTLR start "ruleConstantValue"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3289:1:
    // ruleConstantValue returns [EObject current=null] : ( ( ruleQPREF ) ) ;
    public final EObject ruleConstantValue() throws RecognitionException {
        EObject current = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3292:28:
            // ( ( ( ruleQPREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3293:1:
            // ( ( ruleQPREF ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3293:1:
                // ( ( ruleQPREF ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3294:1:
                // ( ruleQPREF )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3294:1:
                    // ( ruleQPREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3295:3:
                    // ruleQPREF
                    {
                        if (state.backtracking == 0) {

                            if (current == null) {
                                current = createModelElement(grammarAccess.getConstantValueRule());
                            }

                        }
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess.getConstantValueAccess()
                                    .getNamedValuePropertyConstantCrossReference_0());

                        }
                        pushFollow(FOLLOW_ruleQPREF_in_ruleConstantValue7452);
                        ruleQPREF();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            afterParserOrEnumRuleCall();

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleConstantValue"

    // $ANTLR start "entryRuleReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3316:1:
    // entryRuleReferenceTerm returns [EObject current=null] :
    // iv_ruleReferenceTerm= ruleReferenceTerm EOF ;
    public final EObject entryRuleReferenceTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferenceTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3317:2:
            // (iv_ruleReferenceTerm= ruleReferenceTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3318:2:
            // iv_ruleReferenceTerm= ruleReferenceTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getReferenceTermRule());
                }
                pushFollow(FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7487);
                iv_ruleReferenceTerm = ruleReferenceTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleReferenceTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleReferenceTerm7497);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleReferenceTerm"

    // $ANTLR start "ruleReferenceTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3325:1:
    // ruleReferenceTerm returns [EObject current=null] : (otherlv_0=
    // 'reference' otherlv_1= '(' ( ( (lv_containmentPathElement_2_0=
    // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? (
    // (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
    // (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
    // ruleContainmentPathElement ) ) )* otherlv_7= ')' ) ;
    public final EObject ruleReferenceTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;
        Token otherlv_7 = null;
        EObject lv_containmentPathElement_2_0 = null;

        EObject lv_containmentPathElement_4_0 = null;

        EObject lv_containmentPathElement_6_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3328:28:
            // ( (otherlv_0= 'reference' otherlv_1= '(' ( (
            // (lv_containmentPathElement_2_0=
            // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? (
            // (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
            // (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
            // ruleContainmentPathElement ) ) )* otherlv_7= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3329:1:
            // (otherlv_0= 'reference' otherlv_1= '(' ( (
            // (lv_containmentPathElement_2_0=
            // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? (
            // (lv_containmentPathElement_4_0= ruleContainmentPathElement ) )
            // (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
            // ruleContainmentPathElement ) ) )* otherlv_7= ')' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3329:1:
                // (otherlv_0= 'reference' otherlv_1= '(' ( (
                // (lv_containmentPathElement_2_0=
                // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? (
                // (lv_containmentPathElement_4_0= ruleContainmentPathElement )
                // ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
                // ruleContainmentPathElement ) ) )* otherlv_7= ')' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3329:3:
                // otherlv_0= 'reference' otherlv_1= '(' ( (
                // (lv_containmentPathElement_2_0=
                // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )? (
                // (lv_containmentPathElement_4_0= ruleContainmentPathElement )
                // ) (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
                // ruleContainmentPathElement ) ) )* otherlv_7= ')'
                {
                    otherlv_0 = (Token) match(input, 92, FOLLOW_92_in_ruleReferenceTerm7534);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_0, grammarAccess.getReferenceTermAccess()
                                .getReferenceKeyword_0());

                    }
                    otherlv_1 = (Token) match(input, 40, FOLLOW_40_in_ruleReferenceTerm7546);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getReferenceTermAccess()
                                .getLeftParenthesisKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3337:1:
                    // ( ( (lv_containmentPathElement_2_0=
                    // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.' )?
                    int alt49 = 2;
                    int LA49_0 = input.LA(1);

                    if ((LA49_0 == RULE_ID)) {
                        int LA49_1 = input.LA(2);

                        if ((LA49_1 == 99)) {
                            alt49 = 1;
                        }
                    }
                    switch (alt49) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3337:2:
                    // ( (lv_containmentPathElement_2_0=
                    // ruleQualifiedContainmentPathElement ) ) otherlv_3= '.'
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3337:2:
                        // ( (lv_containmentPathElement_2_0=
                        // ruleQualifiedContainmentPathElement ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3338:1:
                        // (lv_containmentPathElement_2_0=
                        // ruleQualifiedContainmentPathElement )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3338:1:
                            // (lv_containmentPathElement_2_0=
                            // ruleQualifiedContainmentPathElement )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3339:3:
                            // lv_containmentPathElement_2_0=
                            // ruleQualifiedContainmentPathElement
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getReferenceTermAccess()
                                            .getContainmentPathElementQualifiedContainmentPathElementParserRuleCall_2_0_0());

                                }
                                pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7568);
                                lv_containmentPathElement_2_0 = ruleQualifiedContainmentPathElement();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getReferenceTermRule());
                                    }
                                    add(current, "containmentPathElement",
                                            lv_containmentPathElement_2_0,
                                            "QualifiedContainmentPathElement");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        otherlv_3 = (Token) match(input, 67, FOLLOW_67_in_ruleReferenceTerm7580);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getReferenceTermAccess()
                                    .getFullStopKeyword_2_1());

                        }

                    }
                        break;

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3359:3:
                    // ( (lv_containmentPathElement_4_0=
                    // ruleContainmentPathElement ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3360:1:
                    // (lv_containmentPathElement_4_0=
                    // ruleContainmentPathElement )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3360:1:
                        // (lv_containmentPathElement_4_0=
                        // ruleContainmentPathElement )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3361:3:
                        // lv_containmentPathElement_4_0=
                        // ruleContainmentPathElement
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess
                                        .getReferenceTermAccess()
                                        .getContainmentPathElementContainmentPathElementParserRuleCall_3_0());

                            }
                            pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7603);
                            lv_containmentPathElement_4_0 = ruleContainmentPathElement();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getReferenceTermRule());
                                }
                                add(current, "containmentPathElement",
                                        lv_containmentPathElement_4_0, "ContainmentPathElement");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3377:2:
                    // (otherlv_5= '.' ( (lv_containmentPathElement_6_0=
                    // ruleContainmentPathElement ) ) )*
                    loop50: do {
                        int alt50 = 2;
                        int LA50_0 = input.LA(1);

                        if ((LA50_0 == 67)) {
                            alt50 = 1;
                        }

                        switch (alt50) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3377:4:
                        // otherlv_5= '.' ( (lv_containmentPathElement_6_0=
                        // ruleContainmentPathElement ) )
                        {
                            otherlv_5 = (Token) match(input, 67, FOLLOW_67_in_ruleReferenceTerm7616);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_5, grammarAccess.getReferenceTermAccess()
                                        .getFullStopKeyword_4_0());

                            }
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3381:1:
                            // ( (lv_containmentPathElement_6_0=
                            // ruleContainmentPathElement ) )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3382:1:
                            // (lv_containmentPathElement_6_0=
                            // ruleContainmentPathElement )
                            {
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3382:1:
                                // (lv_containmentPathElement_6_0=
                                // ruleContainmentPathElement )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3383:3:
                                // lv_containmentPathElement_6_0=
                                // ruleContainmentPathElement
                                {
                                    if (state.backtracking == 0) {

                                        newCompositeNode(grammarAccess
                                                .getReferenceTermAccess()
                                                .getContainmentPathElementContainmentPathElementParserRuleCall_4_1_0());

                                    }
                                    pushFollow(FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7637);
                                    lv_containmentPathElement_6_0 = ruleContainmentPathElement();

                                    state._fsp--;
                                    if (state.failed) {
                                        return current;
                                    }
                                    if (state.backtracking == 0) {

                                        if (current == null) {
                                            current = createModelElementForParent(grammarAccess
                                                    .getReferenceTermRule());
                                        }
                                        add(current, "containmentPathElement",
                                                lv_containmentPathElement_6_0,
                                                "ContainmentPathElement");
                                        afterParserOrEnumRuleCall();

                                    }

                                }

                            }

                        }
                            break;

                        default:
                            break loop50;
                        }
                    } while (true);

                    otherlv_7 = (Token) match(input, 42, FOLLOW_42_in_ruleReferenceTerm7651);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_7, grammarAccess.getReferenceTermAccess()
                                .getRightParenthesisKeyword_5());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleReferenceTerm"

    // $ANTLR start "entryRuleRecordTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3411:1:
    // entryRuleRecordTerm returns [EObject current=null] : iv_ruleRecordTerm=
    // ruleRecordTerm EOF ;
    public final EObject entryRuleRecordTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecordTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3412:2:
            // (iv_ruleRecordTerm= ruleRecordTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3413:2:
            // iv_ruleRecordTerm= ruleRecordTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getRecordTermRule());
                }
                pushFollow(FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm7687);
                iv_ruleRecordTerm = ruleRecordTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleRecordTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleRecordTerm7697);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleRecordTerm"

    // $ANTLR start "ruleRecordTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3420:1:
    // ruleRecordTerm returns [EObject current=null] : (otherlv_0= '[' (
    // (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
    // ) ;
    public final EObject ruleRecordTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        EObject lv_ownedFieldValue_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3423:28:
            // ( (otherlv_0= '[' ( (lv_ownedFieldValue_1_0=
            // ruleFieldPropertyAssociation ) )+ otherlv_2= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3424:1:
            // (otherlv_0= '[' ( (lv_ownedFieldValue_1_0=
            // ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3424:1:
                // (otherlv_0= '[' ( (lv_ownedFieldValue_1_0=
                // ruleFieldPropertyAssociation ) )+ otherlv_2= ']' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3424:3:
                // otherlv_0= '[' ( (lv_ownedFieldValue_1_0=
                // ruleFieldPropertyAssociation ) )+ otherlv_2= ']'
                {
                    otherlv_0 = (Token) match(input, 93, FOLLOW_93_in_ruleRecordTerm7734);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_0, grammarAccess.getRecordTermAccess()
                                .getLeftSquareBracketKeyword_0());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3428:1:
                    // ( (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation )
                    // )+
                    int cnt51 = 0;
                    loop51: do {
                        int alt51 = 2;
                        int LA51_0 = input.LA(1);

                        if ((LA51_0 == RULE_ID)) {
                            alt51 = 1;
                        }

                        switch (alt51) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3429:1:
                        // (lv_ownedFieldValue_1_0= ruleFieldPropertyAssociation
                        // )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3429:1:
                            // (lv_ownedFieldValue_1_0=
                            // ruleFieldPropertyAssociation )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3430:3:
                            // lv_ownedFieldValue_1_0=
                            // ruleFieldPropertyAssociation
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getRecordTermAccess()
                                            .getOwnedFieldValueFieldPropertyAssociationParserRuleCall_1_0());

                                }
                                pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm7755);
                                lv_ownedFieldValue_1_0 = ruleFieldPropertyAssociation();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getRecordTermRule());
                                    }
                                    add(current, "ownedFieldValue", lv_ownedFieldValue_1_0,
                                            "FieldPropertyAssociation");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }
                            break;

                        default:
                            if (cnt51 >= 1) {
                                break loop51;
                            }
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return current;
                            }
                            EarlyExitException eee = new EarlyExitException(51, input);
                            throw eee;
                        }
                        cnt51++;
                    } while (true);

                    otherlv_2 = (Token) match(input, 94, FOLLOW_94_in_ruleRecordTerm7768);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_2, grammarAccess.getRecordTermAccess()
                                .getRightSquareBracketKeyword_2());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleRecordTerm"

    // $ANTLR start "entryRuleComputedTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3460:1:
    // entryRuleComputedTerm returns [EObject current=null] :
    // iv_ruleComputedTerm= ruleComputedTerm EOF ;
    public final EObject entryRuleComputedTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComputedTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3461:2:
            // (iv_ruleComputedTerm= ruleComputedTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3462:2:
            // iv_ruleComputedTerm= ruleComputedTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getComputedTermRule());
                }
                pushFollow(FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm7806);
                iv_ruleComputedTerm = ruleComputedTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleComputedTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleComputedTerm7816);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleComputedTerm"

    // $ANTLR start "ruleComputedTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3469:1:
    // ruleComputedTerm returns [EObject current=null] : (otherlv_0= 'compute'
    // otherlv_1= '(' ( (lv_function_2_0= RULE_ID ) ) otherlv_3= ')' ) ;
    public final EObject ruleComputedTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token lv_function_2_0 = null;
        Token otherlv_3 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3472:28:
            // ( (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0=
            // RULE_ID ) ) otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3473:1:
            // (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0= RULE_ID
            // ) ) otherlv_3= ')' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3473:1:
                // (otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0=
                // RULE_ID ) ) otherlv_3= ')' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3473:3:
                // otherlv_0= 'compute' otherlv_1= '(' ( (lv_function_2_0=
                // RULE_ID ) ) otherlv_3= ')'
                {
                    otherlv_0 = (Token) match(input, 95, FOLLOW_95_in_ruleComputedTerm7853);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_0, grammarAccess.getComputedTermAccess()
                                .getComputeKeyword_0());

                    }
                    otherlv_1 = (Token) match(input, 40, FOLLOW_40_in_ruleComputedTerm7865);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getComputedTermAccess()
                                .getLeftParenthesisKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3481:1:
                    // ( (lv_function_2_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3482:1:
                    // (lv_function_2_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3482:1:
                        // (lv_function_2_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3483:3:
                        // lv_function_2_0= RULE_ID
                        {
                            lv_function_2_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleComputedTerm7882);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(lv_function_2_0, grammarAccess.getComputedTermAccess()
                                        .getFunctionIDTerminalRuleCall_2_0());

                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getComputedTermRule());
                                }
                                setWithLastConsumed(current, "function", lv_function_2_0, "ID");

                            }

                        }

                    }

                    otherlv_3 = (Token) match(input, 42, FOLLOW_42_in_ruleComputedTerm7899);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_3, grammarAccess.getComputedTermAccess()
                                .getRightParenthesisKeyword_3());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleComputedTerm"

    // $ANTLR start "entryRuleComponentClassifierTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3511:1:
    // entryRuleComponentClassifierTerm returns [EObject current=null] :
    // iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF ;
    public final EObject entryRuleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentClassifierTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3512:2:
            // (iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            // )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3513:2:
            // iv_ruleComponentClassifierTerm= ruleComponentClassifierTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getComponentClassifierTermRule());
                }
                pushFollow(FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm7935);
                iv_ruleComponentClassifierTerm = ruleComponentClassifierTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleComponentClassifierTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleComponentClassifierTerm7945);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleComponentClassifierTerm"

    // $ANTLR start "ruleComponentClassifierTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3520:1:
    // ruleComponentClassifierTerm returns [EObject current=null] : (otherlv_0=
    // 'classifier' otherlv_1= '(' ( ( ruleQCREF ) ) otherlv_3= ')' ) ;
    public final EObject ruleComponentClassifierTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3523:28:
            // ( (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) )
            // otherlv_3= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3524:1:
            // (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) )
            // otherlv_3= ')' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3524:1:
                // (otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) )
                // otherlv_3= ')' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3524:3:
                // otherlv_0= 'classifier' otherlv_1= '(' ( ( ruleQCREF ) )
                // otherlv_3= ')'
                {
                    otherlv_0 = (Token) match(input, 96,
                            FOLLOW_96_in_ruleComponentClassifierTerm7982);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_0, grammarAccess.getComponentClassifierTermAccess()
                                .getClassifierKeyword_0());

                    }
                    otherlv_1 = (Token) match(input, 40,
                            FOLLOW_40_in_ruleComponentClassifierTerm7994);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getComponentClassifierTermAccess()
                                .getLeftParenthesisKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3532:1:
                    // ( ( ruleQCREF ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3533:1:
                    // ( ruleQCREF )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3533:1:
                        // ( ruleQCREF )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3534:3:
                        // ruleQCREF
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getComponentClassifierTermRule());
                                }

                            }
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getComponentClassifierTermAccess()
                                        .getClassifierComponentClassifierCrossReference_2_0());

                            }
                            pushFollow(FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8017);
                            ruleQCREF();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    otherlv_3 = (Token) match(input, 42,
                            FOLLOW_42_in_ruleComponentClassifierTerm8029);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_3, grammarAccess.getComponentClassifierTermAccess()
                                .getRightParenthesisKeyword_3());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleComponentClassifierTerm"

    // $ANTLR start "entryRuleListTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3559:1:
    // entryRuleListTerm returns [EObject current=null] : iv_ruleListTerm=
    // ruleListTerm EOF ;
    public final EObject entryRuleListTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3560:2:
            // (iv_ruleListTerm= ruleListTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3561:2:
            // iv_ruleListTerm= ruleListTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getListTermRule());
                }
                pushFollow(FOLLOW_ruleListTerm_in_entryRuleListTerm8065);
                iv_ruleListTerm = ruleListTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleListTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleListTerm8075);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleListTerm"

    // $ANTLR start "ruleListTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3568:1:
    // ruleListTerm returns [EObject current=null] : ( () otherlv_1= '(' ( (
    // (lv_ownedListElement_2_0= rulePropertyExpression ) ) (otherlv_3= ',' (
    // (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )? otherlv_5= ')'
    // ) ;
    public final EObject ruleListTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;
        EObject lv_ownedListElement_2_0 = null;

        EObject lv_ownedListElement_4_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3571:28:
            // ( ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0=
            // rulePropertyExpression ) ) (otherlv_3= ',' (
            // (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            // otherlv_5= ')' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3572:1:
            // ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0=
            // rulePropertyExpression ) ) (otherlv_3= ',' (
            // (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
            // otherlv_5= ')' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3572:1:
                // ( () otherlv_1= '(' ( ( (lv_ownedListElement_2_0=
                // rulePropertyExpression ) ) (otherlv_3= ',' (
                // (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
                // otherlv_5= ')' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3572:2:
                // () otherlv_1= '(' ( ( (lv_ownedListElement_2_0=
                // rulePropertyExpression ) ) (otherlv_3= ',' (
                // (lv_ownedListElement_4_0= rulePropertyExpression ) ) )* )?
                // otherlv_5= ')'
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3572:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3573:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess.getListTermAccess()
                                    .getListValueAction_0(), current);

                        }

                    }

                    otherlv_1 = (Token) match(input, 40, FOLLOW_40_in_ruleListTerm8121);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getListTermAccess()
                                .getLeftParenthesisKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3582:1:
                    // ( ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // (otherlv_3= ',' ( (lv_ownedListElement_4_0=
                    // rulePropertyExpression ) ) )* )?
                    int alt53 = 2;
                    int LA53_0 = input.LA(1);

                    if (((LA53_0 >= RULE_ID && LA53_0 <= RULE_INTEGER_LIT) || LA53_0 == 40
                            || (LA53_0 >= 53 && LA53_0 <= 54) || (LA53_0 >= 90 && LA53_0 <= 93) || (LA53_0 >= 95 && LA53_0 <= 96))) {
                        alt53 = 1;
                    }
                    switch (alt53) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3582:2:
                    // ( (lv_ownedListElement_2_0= rulePropertyExpression ) )
                    // (otherlv_3= ',' ( (lv_ownedListElement_4_0=
                    // rulePropertyExpression ) ) )*
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3582:2:
                        // ( (lv_ownedListElement_2_0= rulePropertyExpression )
                        // )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3583:1:
                        // (lv_ownedListElement_2_0= rulePropertyExpression )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3583:1:
                            // (lv_ownedListElement_2_0= rulePropertyExpression
                            // )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3584:3:
                            // lv_ownedListElement_2_0= rulePropertyExpression
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess
                                            .getListTermAccess()
                                            .getOwnedListElementPropertyExpressionParserRuleCall_2_0_0());

                                }
                                pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8143);
                                lv_ownedListElement_2_0 = rulePropertyExpression();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getListTermRule());
                                    }
                                    add(current, "ownedListElement", lv_ownedListElement_2_0,
                                            "PropertyExpression");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3600:2:
                        // (otherlv_3= ',' ( (lv_ownedListElement_4_0=
                        // rulePropertyExpression ) ) )*
                        loop52: do {
                            int alt52 = 2;
                            int LA52_0 = input.LA(1);

                            if ((LA52_0 == 41)) {
                                alt52 = 1;
                            }

                            switch (alt52) {
                            case 1:
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3600:4:
                            // otherlv_3= ',' ( (lv_ownedListElement_4_0=
                            // rulePropertyExpression ) )
                            {
                                otherlv_3 = (Token) match(input, 41, FOLLOW_41_in_ruleListTerm8156);
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    newLeafNode(otherlv_3, grammarAccess.getListTermAccess()
                                            .getCommaKeyword_2_1_0());

                                }
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3604:1:
                                // ( (lv_ownedListElement_4_0=
                                // rulePropertyExpression ) )
                                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3605:1:
                                // (lv_ownedListElement_4_0=
                                // rulePropertyExpression )
                                {
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3605:1:
                                    // (lv_ownedListElement_4_0=
                                    // rulePropertyExpression )
                                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3606:3:
                                    // lv_ownedListElement_4_0=
                                    // rulePropertyExpression
                                    {
                                        if (state.backtracking == 0) {

                                            newCompositeNode(grammarAccess
                                                    .getListTermAccess()
                                                    .getOwnedListElementPropertyExpressionParserRuleCall_2_1_1_0());

                                        }
                                        pushFollow(FOLLOW_rulePropertyExpression_in_ruleListTerm8177);
                                        lv_ownedListElement_4_0 = rulePropertyExpression();

                                        state._fsp--;
                                        if (state.failed) {
                                            return current;
                                        }
                                        if (state.backtracking == 0) {

                                            if (current == null) {
                                                current = createModelElementForParent(grammarAccess
                                                        .getListTermRule());
                                            }
                                            add(current, "ownedListElement",
                                                    lv_ownedListElement_4_0, "PropertyExpression");
                                            afterParserOrEnumRuleCall();

                                        }

                                    }

                                }

                            }
                                break;

                            default:
                                break loop52;
                            }
                        } while (true);

                    }
                        break;

                    }

                    otherlv_5 = (Token) match(input, 42, FOLLOW_42_in_ruleListTerm8193);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_5, grammarAccess.getListTermAccess()
                                .getRightParenthesisKeyword_3());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleListTerm"

    // $ANTLR start "entryRuleFieldPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3634:1:
    // entryRuleFieldPropertyAssociation returns [EObject current=null] :
    // iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF ;
    public final EObject entryRuleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFieldPropertyAssociation = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3635:2:
            // (iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation
            // EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3636:2:
            // iv_ruleFieldPropertyAssociation= ruleFieldPropertyAssociation EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getFieldPropertyAssociationRule());
                }
                pushFollow(FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8229);
                iv_ruleFieldPropertyAssociation = ruleFieldPropertyAssociation();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleFieldPropertyAssociation;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8239);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleFieldPropertyAssociation"

    // $ANTLR start "ruleFieldPropertyAssociation"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3643:1:
    // ruleFieldPropertyAssociation returns [EObject current=null] : ( (
    // (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0=
    // rulePropertyExpression ) ) otherlv_3= ';' ) ;
    public final EObject ruleFieldPropertyAssociation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        EObject lv_ownedValue_2_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3646:28:
            // ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' (
            // (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3647:1:
            // ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' ( (lv_ownedValue_2_0=
            // rulePropertyExpression ) ) otherlv_3= ';' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3647:1:
                // ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' (
                // (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
                // )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3647:2:
                // ( (otherlv_0= RULE_ID ) ) otherlv_1= '=>' (
                // (lv_ownedValue_2_0= rulePropertyExpression ) ) otherlv_3= ';'
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3647:2:
                    // ( (otherlv_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3648:1:
                    // (otherlv_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3648:1:
                        // (otherlv_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3649:3:
                        // otherlv_0= RULE_ID
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getFieldPropertyAssociationRule());
                                }

                            }
                            otherlv_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8284);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_0, grammarAccess
                                        .getFieldPropertyAssociationAccess()
                                        .getPropertyBasicPropertyCrossReference_0_0());

                            }

                        }

                    }

                    otherlv_1 = (Token) match(input, 45,
                            FOLLOW_45_in_ruleFieldPropertyAssociation8296);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getFieldPropertyAssociationAccess()
                                .getEqualsSignGreaterThanSignKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3664:1:
                    // ( (lv_ownedValue_2_0= rulePropertyExpression ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3665:1:
                    // (lv_ownedValue_2_0= rulePropertyExpression )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3665:1:
                        // (lv_ownedValue_2_0= rulePropertyExpression )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3666:3:
                        // lv_ownedValue_2_0= rulePropertyExpression
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getFieldPropertyAssociationAccess()
                                        .getOwnedValuePropertyExpressionParserRuleCall_2_0());

                            }
                            pushFollow(FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8317);
                            lv_ownedValue_2_0 = rulePropertyExpression();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getFieldPropertyAssociationRule());
                                }
                                set(current, "ownedValue", lv_ownedValue_2_0, "PropertyExpression");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    otherlv_3 = (Token) match(input, 88,
                            FOLLOW_88_in_ruleFieldPropertyAssociation8329);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_3, grammarAccess.getFieldPropertyAssociationAccess()
                                .getSemicolonKeyword_3());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleFieldPropertyAssociation"

    // $ANTLR start "entryRuleContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3694:1:
    // entryRuleContainmentPathElement returns [EObject current=null] :
    // iv_ruleContainmentPathElement= ruleContainmentPathElement EOF ;
    public final EObject entryRuleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContainmentPathElement = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3695:2:
            // (iv_ruleContainmentPathElement= ruleContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3696:2:
            // iv_ruleContainmentPathElement= ruleContainmentPathElement EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getContainmentPathElementRule());
                }
                pushFollow(FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8365);
                iv_ruleContainmentPathElement = ruleContainmentPathElement();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleContainmentPathElement;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleContainmentPathElement8375);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleContainmentPathElement"

    // $ANTLR start "ruleContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3703:1:
    // ruleContainmentPathElement returns [EObject current=null] : ( (
    // (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange ) )? ) ;
    public final EObject ruleContainmentPathElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        EObject lv_arrayRange_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3706:28:
            // ( ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0=
            // ruleArrayRange ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3707:1:
            // ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0= ruleArrayRange
            // ) )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3707:1:
                // ( ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0=
                // ruleArrayRange ) )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3707:2:
                // ( (otherlv_0= RULE_ID ) ) ( (lv_arrayRange_1_0=
                // ruleArrayRange ) )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3707:2:
                    // ( (otherlv_0= RULE_ID ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3708:1:
                    // (otherlv_0= RULE_ID )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3708:1:
                        // (otherlv_0= RULE_ID )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3709:3:
                        // otherlv_0= RULE_ID
                        {
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElement(grammarAccess
                                            .getContainmentPathElementRule());
                                }

                            }
                            otherlv_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleContainmentPathElement8420);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                newLeafNode(otherlv_0, grammarAccess
                                        .getContainmentPathElementAccess()
                                        .getNamedElementNamedElementCrossReference_0_0());

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3720:2:
                    // ( (lv_arrayRange_1_0= ruleArrayRange ) )?
                    int alt54 = 2;
                    int LA54_0 = input.LA(1);

                    if ((LA54_0 == 93)) {
                        alt54 = 1;
                    }
                    switch (alt54) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3721:1:
                    // (lv_arrayRange_1_0= ruleArrayRange )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3721:1:
                        // (lv_arrayRange_1_0= ruleArrayRange )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3722:3:
                        // lv_arrayRange_1_0= ruleArrayRange
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getContainmentPathElementAccess()
                                        .getArrayRangeArrayRangeParserRuleCall_1_0());

                            }
                            pushFollow(FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8441);
                            lv_arrayRange_1_0 = ruleArrayRange();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getContainmentPathElementRule());
                                }
                                add(current, "arrayRange", lv_arrayRange_1_0, "ArrayRange");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleContainmentPathElement"

    // $ANTLR start "entryRuleQualifiedContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3746:1:
    // entryRuleQualifiedContainmentPathElement returns [EObject current=null] :
    // iv_ruleQualifiedContainmentPathElement=
    // ruleQualifiedContainmentPathElement EOF ;
    public final EObject entryRuleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQualifiedContainmentPathElement = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3747:2:
            // (iv_ruleQualifiedContainmentPathElement=
            // ruleQualifiedContainmentPathElement EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3748:2:
            // iv_ruleQualifiedContainmentPathElement=
            // ruleQualifiedContainmentPathElement EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getQualifiedContainmentPathElementRule());
                }
                pushFollow(FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8478);
                iv_ruleQualifiedContainmentPathElement = ruleQualifiedContainmentPathElement();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleQualifiedContainmentPathElement;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8488);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleQualifiedContainmentPathElement"

    // $ANTLR start "ruleQualifiedContainmentPathElement"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3755:1:
    // ruleQualifiedContainmentPathElement returns [EObject current=null] : ( (
    // ruleQCLREF ) ) ;
    public final EObject ruleQualifiedContainmentPathElement() throws RecognitionException {
        EObject current = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3758:28:
            // ( ( ( ruleQCLREF ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3759:1:
            // ( ( ruleQCLREF ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3759:1:
                // ( ( ruleQCLREF ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3760:1:
                // ( ruleQCLREF )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3760:1:
                    // ( ruleQCLREF )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3761:3:
                    // ruleQCLREF
                    {
                        if (state.backtracking == 0) {

                            if (current == null) {
                                current = createModelElement(grammarAccess
                                        .getQualifiedContainmentPathElementRule());
                            }

                        }
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess
                                    .getQualifiedContainmentPathElementAccess()
                                    .getNamedElementNamedElementCrossReference_0());

                        }
                        pushFollow(FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8535);
                        ruleQCLREF();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            afterParserOrEnumRuleCall();

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleQualifiedContainmentPathElement"

    // $ANTLR start "entryRulePlusMinus"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3784:1:
    // entryRulePlusMinus returns [String current=null] : iv_rulePlusMinus=
    // rulePlusMinus EOF ;
    public final String entryRulePlusMinus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePlusMinus = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3785:2:
            // (iv_rulePlusMinus= rulePlusMinus EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3786:2:
            // iv_rulePlusMinus= rulePlusMinus EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getPlusMinusRule());
                }
                pushFollow(FOLLOW_rulePlusMinus_in_entryRulePlusMinus8573);
                iv_rulePlusMinus = rulePlusMinus();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_rulePlusMinus.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRulePlusMinus8584);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRulePlusMinus"

    // $ANTLR start "rulePlusMinus"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3793:1:
    // rulePlusMinus returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken rulePlusMinus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3796:28:
            // ( (kw= '+' | kw= '-' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3797:1:
            // (kw= '+' | kw= '-' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3797:1:
                // (kw= '+' | kw= '-' )
                int alt55 = 2;
                int LA55_0 = input.LA(1);

                if ((LA55_0 == 53)) {
                    alt55 = 1;
                } else if ((LA55_0 == 54)) {
                    alt55 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 55, 0, input);

                    throw nvae;
                }
                switch (alt55) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3798:2:
                // kw= '+'
                {
                    kw = (Token) match(input, 53, FOLLOW_53_in_rulePlusMinus8622);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getPlusMinusAccess().getPlusSignKeyword_0());

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3805:2:
                // kw= '-'
                {
                    kw = (Token) match(input, 54, FOLLOW_54_in_rulePlusMinus8641);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getPlusMinusAccess()
                                .getHyphenMinusKeyword_1());

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "rulePlusMinus"

    // $ANTLR start "entryRuleStringTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3818:1:
    // entryRuleStringTerm returns [EObject current=null] : iv_ruleStringTerm=
    // ruleStringTerm EOF ;
    public final EObject entryRuleStringTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3819:2:
            // (iv_ruleStringTerm= ruleStringTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3820:2:
            // iv_ruleStringTerm= ruleStringTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getStringTermRule());
                }
                pushFollow(FOLLOW_ruleStringTerm_in_entryRuleStringTerm8681);
                iv_ruleStringTerm = ruleStringTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleStringTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleStringTerm8691);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleStringTerm"

    // $ANTLR start "ruleStringTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3827:1:
    // ruleStringTerm returns [EObject current=null] : ( (lv_value_0_0=
    // ruleNoQuoteString ) ) ;
    public final EObject ruleStringTerm() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3830:28:
            // ( ( (lv_value_0_0= ruleNoQuoteString ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3831:1:
            // ( (lv_value_0_0= ruleNoQuoteString ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3831:1:
                // ( (lv_value_0_0= ruleNoQuoteString ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3832:1:
                // (lv_value_0_0= ruleNoQuoteString )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3832:1:
                    // (lv_value_0_0= ruleNoQuoteString )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3833:3:
                    // lv_value_0_0= ruleNoQuoteString
                    {
                        if (state.backtracking == 0) {

                            newCompositeNode(grammarAccess.getStringTermAccess()
                                    .getValueNoQuoteStringParserRuleCall_0());

                        }
                        pushFollow(FOLLOW_ruleNoQuoteString_in_ruleStringTerm8736);
                        lv_value_0_0 = ruleNoQuoteString();

                        state._fsp--;
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            if (current == null) {
                                current = createModelElementForParent(grammarAccess
                                        .getStringTermRule());
                            }
                            set(current, "value", lv_value_0_0, "NoQuoteString");
                            afterParserOrEnumRuleCall();

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleStringTerm"

    // $ANTLR start "entryRuleNoQuoteString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3857:1:
    // entryRuleNoQuoteString returns [String current=null] :
    // iv_ruleNoQuoteString= ruleNoQuoteString EOF ;
    public final String entryRuleNoQuoteString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNoQuoteString = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3858:2:
            // (iv_ruleNoQuoteString= ruleNoQuoteString EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3859:2:
            // iv_ruleNoQuoteString= ruleNoQuoteString EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getNoQuoteStringRule());
                }
                pushFollow(FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString8772);
                iv_ruleNoQuoteString = ruleNoQuoteString();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleNoQuoteString.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleNoQuoteString8783);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleNoQuoteString"

    // $ANTLR start "ruleNoQuoteString"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3866:1:
    // ruleNoQuoteString returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleNoQuoteString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3869:28:
            // (this_STRING_0= RULE_STRING )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3870:5:
            // this_STRING_0= RULE_STRING
            {
                this_STRING_0 = (Token) match(input, RULE_STRING,
                        FOLLOW_RULE_STRING_in_ruleNoQuoteString8822);
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current.merge(this_STRING_0);

                }
                if (state.backtracking == 0) {

                    newLeafNode(this_STRING_0, grammarAccess.getNoQuoteStringAccess()
                            .getSTRINGTerminalRuleCall());

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleNoQuoteString"

    // $ANTLR start "entryRuleArrayRange"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3885:1:
    // entryRuleArrayRange returns [EObject current=null] : iv_ruleArrayRange=
    // ruleArrayRange EOF ;
    public final EObject entryRuleArrayRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayRange = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3886:2:
            // (iv_ruleArrayRange= ruleArrayRange EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3887:2:
            // iv_ruleArrayRange= ruleArrayRange EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getArrayRangeRule());
                }
                pushFollow(FOLLOW_ruleArrayRange_in_entryRuleArrayRange8866);
                iv_ruleArrayRange = ruleArrayRange();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleArrayRange;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleArrayRange8876);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleArrayRange"

    // $ANTLR start "ruleArrayRange"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3894:1:
    // ruleArrayRange returns [EObject current=null] : ( () otherlv_1= '[' (
    // (lv_lowerBound_2_0= ruleINTVALUE ) ) (otherlv_3= '..' (
    // (lv_upperBound_4_0= ruleINTVALUE ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleArrayRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;
        AntlrDatatypeRuleToken lv_lowerBound_2_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_4_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3897:28:
            // ( ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            // otherlv_5= ']' ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3898:1:
            // ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) )
            // (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
            // otherlv_5= ']' )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3898:1:
                // ( () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) )
                // (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
                // otherlv_5= ']' )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3898:2:
                // () otherlv_1= '[' ( (lv_lowerBound_2_0= ruleINTVALUE ) )
                // (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) ) )?
                // otherlv_5= ']'
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3898:2:
                    // ()
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3899:5:
                    {
                        if (state.backtracking == 0) {

                            current = forceCreateModelElement(grammarAccess.getArrayRangeAccess()
                                    .getArrayRangeAction_0(), current);

                        }

                    }

                    otherlv_1 = (Token) match(input, 93, FOLLOW_93_in_ruleArrayRange8922);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getArrayRangeAccess()
                                .getLeftSquareBracketKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3908:1:
                    // ( (lv_lowerBound_2_0= ruleINTVALUE ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3909:1:
                    // (lv_lowerBound_2_0= ruleINTVALUE )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3909:1:
                        // (lv_lowerBound_2_0= ruleINTVALUE )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3910:3:
                        // lv_lowerBound_2_0= ruleINTVALUE
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getArrayRangeAccess()
                                        .getLowerBoundINTVALUEParserRuleCall_2_0());

                            }
                            pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange8943);
                            lv_lowerBound_2_0 = ruleINTVALUE();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getArrayRangeRule());
                                }
                                set(current, "lowerBound", lv_lowerBound_2_0, "INTVALUE");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3926:2:
                    // (otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    // )?
                    int alt56 = 2;
                    int LA56_0 = input.LA(1);

                    if ((LA56_0 == 97)) {
                        alt56 = 1;
                    }
                    switch (alt56) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3926:4:
                    // otherlv_3= '..' ( (lv_upperBound_4_0= ruleINTVALUE ) )
                    {
                        otherlv_3 = (Token) match(input, 97, FOLLOW_97_in_ruleArrayRange8956);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getArrayRangeAccess()
                                    .getFullStopFullStopKeyword_3_0());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3930:1:
                        // ( (lv_upperBound_4_0= ruleINTVALUE ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3931:1:
                        // (lv_upperBound_4_0= ruleINTVALUE )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3931:1:
                            // (lv_upperBound_4_0= ruleINTVALUE )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3932:3:
                            // lv_upperBound_4_0= ruleINTVALUE
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getArrayRangeAccess()
                                            .getUpperBoundINTVALUEParserRuleCall_3_1_0());

                                }
                                pushFollow(FOLLOW_ruleINTVALUE_in_ruleArrayRange8977);
                                lv_upperBound_4_0 = ruleINTVALUE();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getArrayRangeRule());
                                    }
                                    set(current, "upperBound", lv_upperBound_4_0, "INTVALUE");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }
                        break;

                    }

                    otherlv_5 = (Token) match(input, 94, FOLLOW_94_in_ruleArrayRange8991);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_5, grammarAccess.getArrayRangeAccess()
                                .getRightSquareBracketKeyword_4());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleArrayRange"

    // $ANTLR start "entryRuleSignedConstant"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3960:1:
    // entryRuleSignedConstant returns [EObject current=null] :
    // iv_ruleSignedConstant= ruleSignedConstant EOF ;
    public final EObject entryRuleSignedConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignedConstant = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3961:2:
            // (iv_ruleSignedConstant= ruleSignedConstant EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3962:2:
            // iv_ruleSignedConstant= ruleSignedConstant EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getSignedConstantRule());
                }
                pushFollow(FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9027);
                iv_ruleSignedConstant = ruleSignedConstant();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleSignedConstant;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleSignedConstant9037);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleSignedConstant"

    // $ANTLR start "ruleSignedConstant"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3969:1:
    // ruleSignedConstant returns [EObject current=null] : ( ( (lv_op_0_0=
    // rulePlusMinus ) ) ( (lv_ownedPropertyExpression_1_0= ruleConstantValue )
    // ) ) ;
    public final EObject ruleSignedConstant() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_op_0_0 = null;

        EObject lv_ownedPropertyExpression_1_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3972:28:
            // ( ( ( (lv_op_0_0= rulePlusMinus ) ) (
            // (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3973:1:
            // ( ( (lv_op_0_0= rulePlusMinus ) ) (
            // (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3973:1:
                // ( ( (lv_op_0_0= rulePlusMinus ) ) (
                // (lv_ownedPropertyExpression_1_0= ruleConstantValue ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3973:2:
                // ( (lv_op_0_0= rulePlusMinus ) ) (
                // (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3973:2:
                    // ( (lv_op_0_0= rulePlusMinus ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3974:1:
                    // (lv_op_0_0= rulePlusMinus )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3974:1:
                        // (lv_op_0_0= rulePlusMinus )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3975:3:
                        // lv_op_0_0= rulePlusMinus
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getSignedConstantAccess()
                                        .getOpPlusMinusParserRuleCall_0_0());

                            }
                            pushFollow(FOLLOW_rulePlusMinus_in_ruleSignedConstant9083);
                            lv_op_0_0 = rulePlusMinus();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getSignedConstantRule());
                                }
                                set(current, "op", lv_op_0_0, "PlusMinus");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3991:2:
                    // ( (lv_ownedPropertyExpression_1_0= ruleConstantValue ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3992:1:
                    // (lv_ownedPropertyExpression_1_0= ruleConstantValue )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3992:1:
                        // (lv_ownedPropertyExpression_1_0= ruleConstantValue )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:3993:3:
                        // lv_ownedPropertyExpression_1_0= ruleConstantValue
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess
                                        .getSignedConstantAccess()
                                        .getOwnedPropertyExpressionConstantValueParserRuleCall_1_0());

                            }
                            pushFollow(FOLLOW_ruleConstantValue_in_ruleSignedConstant9104);
                            lv_ownedPropertyExpression_1_0 = ruleConstantValue();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getSignedConstantRule());
                                }
                                add(current, "ownedPropertyExpression",
                                        lv_ownedPropertyExpression_1_0, "ConstantValue");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleSignedConstant"

    // $ANTLR start "entryRuleNumericRangeTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4021:1:
    // entryRuleNumericRangeTerm returns [EObject current=null] :
    // iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF ;
    public final EObject entryRuleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumericRangeTerm = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4022:2:
            // (iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4023:2:
            // iv_ruleNumericRangeTerm= ruleNumericRangeTerm EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getNumericRangeTermRule());
                }
                pushFollow(FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9144);
                iv_ruleNumericRangeTerm = ruleNumericRangeTerm();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleNumericRangeTerm;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleNumericRangeTerm9154);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleNumericRangeTerm"

    // $ANTLR start "ruleNumericRangeTerm"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4030:1:
    // ruleNumericRangeTerm returns [EObject current=null] : ( (
    // (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' ( (lv_maximum_2_0=
    // ruleNumAlt ) ) (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )? ) ;
    public final EObject ruleNumericRangeTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_1 = null;
        Token otherlv_3 = null;
        EObject lv_minimum_0_0 = null;

        EObject lv_maximum_2_0 = null;

        EObject lv_delta_4_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4033:28:
            // ( ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' (
            // (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' (
            // (lv_delta_4_0= ruleNumAlt ) ) )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4034:1:
            // ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' (
            // (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' (
            // (lv_delta_4_0= ruleNumAlt ) ) )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4034:1:
                // ( ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' (
                // (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' (
                // (lv_delta_4_0= ruleNumAlt ) ) )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4034:2:
                // ( (lv_minimum_0_0= ruleNumAlt ) ) otherlv_1= '..' (
                // (lv_maximum_2_0= ruleNumAlt ) ) (otherlv_3= 'delta' (
                // (lv_delta_4_0= ruleNumAlt ) ) )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4034:2:
                    // ( (lv_minimum_0_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4035:1:
                    // (lv_minimum_0_0= ruleNumAlt )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4035:1:
                        // (lv_minimum_0_0= ruleNumAlt )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4036:3:
                        // lv_minimum_0_0= ruleNumAlt
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getNumericRangeTermAccess()
                                        .getMinimumNumAltParserRuleCall_0_0());

                            }
                            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9200);
                            lv_minimum_0_0 = ruleNumAlt();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getNumericRangeTermRule());
                                }
                                set(current, "minimum", lv_minimum_0_0, "NumAlt");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    otherlv_1 = (Token) match(input, 97, FOLLOW_97_in_ruleNumericRangeTerm9212);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        newLeafNode(otherlv_1, grammarAccess.getNumericRangeTermAccess()
                                .getFullStopFullStopKeyword_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4056:1:
                    // ( (lv_maximum_2_0= ruleNumAlt ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4057:1:
                    // (lv_maximum_2_0= ruleNumAlt )
                    {
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4057:1:
                        // (lv_maximum_2_0= ruleNumAlt )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4058:3:
                        // lv_maximum_2_0= ruleNumAlt
                        {
                            if (state.backtracking == 0) {

                                newCompositeNode(grammarAccess.getNumericRangeTermAccess()
                                        .getMaximumNumAltParserRuleCall_2_0());

                            }
                            pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9233);
                            lv_maximum_2_0 = ruleNumAlt();

                            state._fsp--;
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                if (current == null) {
                                    current = createModelElementForParent(grammarAccess
                                            .getNumericRangeTermRule());
                                }
                                set(current, "maximum", lv_maximum_2_0, "NumAlt");
                                afterParserOrEnumRuleCall();

                            }

                        }

                    }

                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4074:2:
                    // (otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) ) )?
                    int alt57 = 2;
                    int LA57_0 = input.LA(1);

                    if ((LA57_0 == 98)) {
                        alt57 = 1;
                    }
                    switch (alt57) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4074:4:
                    // otherlv_3= 'delta' ( (lv_delta_4_0= ruleNumAlt ) )
                    {
                        otherlv_3 = (Token) match(input, 98, FOLLOW_98_in_ruleNumericRangeTerm9246);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            newLeafNode(otherlv_3, grammarAccess.getNumericRangeTermAccess()
                                    .getDeltaKeyword_3_0());

                        }
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4078:1:
                        // ( (lv_delta_4_0= ruleNumAlt ) )
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:1:
                        // (lv_delta_4_0= ruleNumAlt )
                        {
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4079:1:
                            // (lv_delta_4_0= ruleNumAlt )
                            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4080:3:
                            // lv_delta_4_0= ruleNumAlt
                            {
                                if (state.backtracking == 0) {

                                    newCompositeNode(grammarAccess.getNumericRangeTermAccess()
                                            .getDeltaNumAltParserRuleCall_3_1_0());

                                }
                                pushFollow(FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9267);
                                lv_delta_4_0 = ruleNumAlt();

                                state._fsp--;
                                if (state.failed) {
                                    return current;
                                }
                                if (state.backtracking == 0) {

                                    if (current == null) {
                                        current = createModelElementForParent(grammarAccess
                                                .getNumericRangeTermRule());
                                    }
                                    set(current, "delta", lv_delta_4_0, "NumAlt");
                                    afterParserOrEnumRuleCall();

                                }

                            }

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleNumericRangeTerm"

    // $ANTLR start "entryRuleNumAlt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4104:1:
    // entryRuleNumAlt returns [EObject current=null] : iv_ruleNumAlt=
    // ruleNumAlt EOF ;
    public final EObject entryRuleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumAlt = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4105:2:
            // (iv_ruleNumAlt= ruleNumAlt EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4106:2:
            // iv_ruleNumAlt= ruleNumAlt EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getNumAltRule());
                }
                pushFollow(FOLLOW_ruleNumAlt_in_entryRuleNumAlt9305);
                iv_ruleNumAlt = ruleNumAlt();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleNumAlt;
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleNumAlt9315);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleNumAlt"

    // $ANTLR start "ruleNumAlt"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4113:1:
    // ruleNumAlt returns [EObject current=null] : (this_RealTerm_0=
    // ruleRealTerm | this_IntegerTerm_1= ruleIntegerTerm |
    // this_SignedConstant_2= ruleSignedConstant | this_ConstantValue_3=
    // ruleConstantValue ) ;
    public final EObject ruleNumAlt() throws RecognitionException {
        EObject current = null;

        EObject this_RealTerm_0 = null;

        EObject this_IntegerTerm_1 = null;

        EObject this_SignedConstant_2 = null;

        EObject this_ConstantValue_3 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4116:28:
            // ( (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1=
            // ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant |
            // this_ConstantValue_3= ruleConstantValue ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4117:1:
            // (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1=
            // ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant |
            // this_ConstantValue_3= ruleConstantValue )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4117:1:
                // (this_RealTerm_0= ruleRealTerm | this_IntegerTerm_1=
                // ruleIntegerTerm | this_SignedConstant_2= ruleSignedConstant |
                // this_ConstantValue_3= ruleConstantValue )
                int alt58 = 4;
                switch (input.LA(1)) {
                case RULE_REAL_LIT: {
                    alt58 = 1;
                }
                    break;
                case RULE_INTEGER_LIT: {
                    alt58 = 2;
                }
                    break;
                case 53:
                case 54: {
                    alt58 = 3;
                }
                    break;
                case RULE_ID: {
                    alt58 = 4;
                }
                    break;
                default:
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return current;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 58, 0, input);

                    throw nvae;
                }

                switch (alt58) {
                case 1:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4118:5:
                // this_RealTerm_0= ruleRealTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getNumAltAccess()
                                .getRealTermParserRuleCall_0());

                    }
                    pushFollow(FOLLOW_ruleRealTerm_in_ruleNumAlt9362);
                    this_RealTerm_0 = ruleRealTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_RealTerm_0;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 2:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4128:5:
                // this_IntegerTerm_1= ruleIntegerTerm
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getNumAltAccess()
                                .getIntegerTermParserRuleCall_1());

                    }
                    pushFollow(FOLLOW_ruleIntegerTerm_in_ruleNumAlt9389);
                    this_IntegerTerm_1 = ruleIntegerTerm();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_IntegerTerm_1;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 3:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4138:5:
                // this_SignedConstant_2= ruleSignedConstant
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getNumAltAccess()
                                .getSignedConstantParserRuleCall_2());

                    }
                    pushFollow(FOLLOW_ruleSignedConstant_in_ruleNumAlt9416);
                    this_SignedConstant_2 = ruleSignedConstant();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_SignedConstant_2;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;
                case 4:
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4148:5:
                // this_ConstantValue_3= ruleConstantValue
                {
                    if (state.backtracking == 0) {

                        newCompositeNode(grammarAccess.getNumAltAccess()
                                .getConstantValueParserRuleCall_3());

                    }
                    pushFollow(FOLLOW_ruleConstantValue_in_ruleNumAlt9443);
                    this_ConstantValue_3 = ruleConstantValue();

                    state._fsp--;
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current = this_ConstantValue_3;
                        afterParserOrEnumRuleCall();

                    }

                }
                    break;

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleNumAlt"

    // $ANTLR start "entryRuleINTVALUE"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4164:1:
    // entryRuleINTVALUE returns [String current=null] : iv_ruleINTVALUE=
    // ruleINTVALUE EOF ;
    public final String entryRuleINTVALUE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleINTVALUE = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4165:2:
            // (iv_ruleINTVALUE= ruleINTVALUE EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4166:2:
            // iv_ruleINTVALUE= ruleINTVALUE EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getINTVALUERule());
                }
                pushFollow(FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9479);
                iv_ruleINTVALUE = ruleINTVALUE();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleINTVALUE.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleINTVALUE9490);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleINTVALUE"

    // $ANTLR start "ruleINTVALUE"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4173:1:
    // ruleINTVALUE returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : this_INTEGER_LIT_0= RULE_INTEGER_LIT ;
    public final AntlrDatatypeRuleToken ruleINTVALUE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INTEGER_LIT_0 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4176:28:
            // (this_INTEGER_LIT_0= RULE_INTEGER_LIT )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4177:5:
            // this_INTEGER_LIT_0= RULE_INTEGER_LIT
            {
                this_INTEGER_LIT_0 = (Token) match(input, RULE_INTEGER_LIT,
                        FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9529);
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current.merge(this_INTEGER_LIT_0);

                }
                if (state.backtracking == 0) {

                    newLeafNode(this_INTEGER_LIT_0, grammarAccess.getINTVALUEAccess()
                            .getINTEGER_LITTerminalRuleCall());

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleINTVALUE"

    // $ANTLR start "entryRuleQCLREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4192:1:
    // entryRuleQCLREF returns [String current=null] : iv_ruleQCLREF= ruleQCLREF
    // EOF ;
    public final String entryRuleQCLREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCLREF = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4193:2:
            // (iv_ruleQCLREF= ruleQCLREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4194:2:
            // iv_ruleQCLREF= ruleQCLREF EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getQCLREFRule());
                }
                pushFollow(FOLLOW_ruleQCLREF_in_entryRuleQCLREF9574);
                iv_ruleQCLREF = ruleQCLREF();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleQCLREF.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleQCLREF9585);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleQCLREF"

    // $ANTLR start "ruleQCLREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4201:1:
    // ruleQCLREF returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID kw= '::' this_ID_2=
    // RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQCLREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0 = null;
        Token kw = null;
        Token this_ID_2 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4204:28:
            // ( (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4205:1:
            // (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4205:1:
                // (this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4205:6:
                // this_ID_0= RULE_ID kw= '::' this_ID_2= RULE_ID
                {
                    this_ID_0 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQCLREF9625);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(this_ID_0);

                    }
                    if (state.backtracking == 0) {

                        newLeafNode(this_ID_0, grammarAccess.getQCLREFAccess()
                                .getIDTerminalRuleCall_0());

                    }
                    kw = (Token) match(input, 99, FOLLOW_99_in_ruleQCLREF9643);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(kw);
                        newLeafNode(kw, grammarAccess.getQCLREFAccess().getColonColonKeyword_1());

                    }
                    this_ID_2 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQCLREF9658);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(this_ID_2);

                    }
                    if (state.backtracking == 0) {

                        newLeafNode(this_ID_2, grammarAccess.getQCLREFAccess()
                                .getIDTerminalRuleCall_2());

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleQCLREF"

    // $ANTLR start "entryRuleQPREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4233:1:
    // entryRuleQPREF returns [String current=null] : iv_ruleQPREF= ruleQPREF
    // EOF ;
    public final String entryRuleQPREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQPREF = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4234:2:
            // (iv_ruleQPREF= ruleQPREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4235:2:
            // iv_ruleQPREF= ruleQPREF EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getQPREFRule());
                }
                pushFollow(FOLLOW_ruleQPREF_in_entryRuleQPREF9704);
                iv_ruleQPREF = ruleQPREF();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleQPREF.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleQPREF9715);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleQPREF"

    // $ANTLR start "ruleQPREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4242:1:
    // ruleQPREF returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2=
    // RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQPREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0 = null;
        Token kw = null;
        Token this_ID_2 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4245:28:
            // ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4246:1:
            // (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4246:1:
                // (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4246:6:
                // this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )?
                {
                    this_ID_0 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQPREF9755);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(this_ID_0);

                    }
                    if (state.backtracking == 0) {

                        newLeafNode(this_ID_0, grammarAccess.getQPREFAccess()
                                .getIDTerminalRuleCall_0());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4253:1:
                    // (kw= '::' this_ID_2= RULE_ID )?
                    int alt59 = 2;
                    int LA59_0 = input.LA(1);

                    if ((LA59_0 == 99)) {
                        alt59 = 1;
                    }
                    switch (alt59) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4254:2:
                    // kw= '::' this_ID_2= RULE_ID
                    {
                        kw = (Token) match(input, 99, FOLLOW_99_in_ruleQPREF9774);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getQPREFAccess()
                                    .getColonColonKeyword_1_0());

                        }
                        this_ID_2 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQPREF9789);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            current.merge(this_ID_2);

                        }
                        if (state.backtracking == 0) {

                            newLeafNode(this_ID_2, grammarAccess.getQPREFAccess()
                                    .getIDTerminalRuleCall_1_1());

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleQPREF"

    // $ANTLR start "entryRuleQCREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4274:1:
    // entryRuleQCREF returns [String current=null] : iv_ruleQCREF= ruleQCREF
    // EOF ;
    public final String entryRuleQCREF() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQCREF = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4275:2:
            // (iv_ruleQCREF= ruleQCREF EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4276:2:
            // iv_ruleQCREF= ruleQCREF EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getQCREFRule());
                }
                pushFollow(FOLLOW_ruleQCREF_in_entryRuleQCREF9837);
                iv_ruleQCREF = ruleQCREF();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleQCREF.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleQCREF9848);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleQCREF"

    // $ANTLR start "ruleQCREF"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4283:1:
    // ruleQCREF returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2=
    // RULE_ID (kw= '.' this_ID_4= RULE_ID )? ) ;
    public final AntlrDatatypeRuleToken ruleQCREF() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0 = null;
        Token kw = null;
        Token this_ID_2 = null;
        Token this_ID_4 = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4286:28:
            // ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.'
            // this_ID_4= RULE_ID )? ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:1:
            // ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.'
            // this_ID_4= RULE_ID )? )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:1:
                // ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.'
                // this_ID_4= RULE_ID )? )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:2:
                // (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID (kw= '.'
                // this_ID_4= RULE_ID )?
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:2:
                    // (this_ID_0= RULE_ID kw= '::' )*
                    loop60: do {
                        int alt60 = 2;
                        int LA60_0 = input.LA(1);

                        if ((LA60_0 == RULE_ID)) {
                            int LA60_1 = input.LA(2);

                            if ((LA60_1 == 99)) {
                                alt60 = 1;
                            }

                        }

                        switch (alt60) {
                        case 1:
                        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4287:7:
                        // this_ID_0= RULE_ID kw= '::'
                        {
                            this_ID_0 = (Token) match(input, RULE_ID,
                                    FOLLOW_RULE_ID_in_ruleQCREF9889);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                current.merge(this_ID_0);

                            }
                            if (state.backtracking == 0) {

                                newLeafNode(this_ID_0, grammarAccess.getQCREFAccess()
                                        .getIDTerminalRuleCall_0_0());

                            }
                            kw = (Token) match(input, 99, FOLLOW_99_in_ruleQCREF9907);
                            if (state.failed) {
                                return current;
                            }
                            if (state.backtracking == 0) {

                                current.merge(kw);
                                newLeafNode(kw, grammarAccess.getQCREFAccess()
                                        .getColonColonKeyword_0_1());

                            }

                        }
                            break;

                        default:
                            break loop60;
                        }
                    } while (true);

                    this_ID_2 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQCREF9924);
                    if (state.failed) {
                        return current;
                    }
                    if (state.backtracking == 0) {

                        current.merge(this_ID_2);

                    }
                    if (state.backtracking == 0) {

                        newLeafNode(this_ID_2, grammarAccess.getQCREFAccess()
                                .getIDTerminalRuleCall_1());

                    }
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4307:1:
                    // (kw= '.' this_ID_4= RULE_ID )?
                    int alt61 = 2;
                    int LA61_0 = input.LA(1);

                    if ((LA61_0 == 67)) {
                        alt61 = 1;
                    }
                    switch (alt61) {
                    case 1:
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4308:2:
                    // kw= '.' this_ID_4= RULE_ID
                    {
                        kw = (Token) match(input, 67, FOLLOW_67_in_ruleQCREF9943);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getQCREFAccess().getFullStopKeyword_2_0());

                        }
                        this_ID_4 = (Token) match(input, RULE_ID, FOLLOW_RULE_ID_in_ruleQCREF9958);
                        if (state.failed) {
                            return current;
                        }
                        if (state.backtracking == 0) {

                            current.merge(this_ID_4);

                        }
                        if (state.backtracking == 0) {

                            newLeafNode(this_ID_4, grammarAccess.getQCREFAccess()
                                    .getIDTerminalRuleCall_2_1());

                        }

                    }
                        break;

                    }

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleQCREF"

    // $ANTLR start "entryRuleSTAR"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4328:1:
    // entryRuleSTAR returns [String current=null] : iv_ruleSTAR= ruleSTAR EOF ;
    public final String entryRuleSTAR() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSTAR = null;

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4329:2:
            // (iv_ruleSTAR= ruleSTAR EOF )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4330:2:
            // iv_ruleSTAR= ruleSTAR EOF
            {
                if (state.backtracking == 0) {
                    newCompositeNode(grammarAccess.getSTARRule());
                }
                pushFollow(FOLLOW_ruleSTAR_in_entryRuleSTAR10006);
                iv_ruleSTAR = ruleSTAR();

                state._fsp--;
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {
                    current = iv_ruleSTAR.getText();
                }
                match(input, EOF, FOLLOW_EOF_in_entryRuleSTAR10017);
                if (state.failed) {
                    return current;
                }

            }

        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "entryRuleSTAR"

    // $ANTLR start "ruleSTAR"
    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4337:1:
    // ruleSTAR returns [AntlrDatatypeRuleToken current=new
    // AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken ruleSTAR() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw = null;

        enterRule();

        try {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4340:28:
            // (kw= '*' )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:4342:2:
            // kw= '*'
            {
                kw = (Token) match(input, 55, FOLLOW_55_in_ruleSTAR10054);
                if (state.failed) {
                    return current;
                }
                if (state.backtracking == 0) {

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getSTARAccess().getAsteriskKeyword());

                }

            }

            if (state.backtracking == 0) {
                leaveRule();
            }
        }

        catch (RecognitionException re) {
            recover(input, re);
            appendSkippedTokens();
        } finally {
        }
        return current;
    }

    // $ANTLR end "ruleSTAR"

    // $ANTLR start synpred1_InternalResolute
    public final void synpred1_InternalResolute_fragment() throws RecognitionException {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:3:
        // ( ( () ( ( '=>' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:4:
        // ( () ( ( '=>' ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:4:
            // ( () ( ( '=>' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:5:
            // () ( ( '=>' ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1010:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1011:2:
                // ( ( '=>' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:1:
                // ( '=>' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1012:1:
                    // ( '=>' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1013:2:
                    // '=>'
                    {
                        match(input, 45, FOLLOW_45_in_synpred1_InternalResolute2240);
                        if (state.failed) {
                            return;
                        }

                    }

                }

            }

        }
    }

    // $ANTLR end synpred1_InternalResolute

    // $ANTLR start synpred2_InternalResolute
    public final void synpred2_InternalResolute_fragment() throws RecognitionException {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:3:
        // ( ( () ( ( 'or' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:4:
        // ( () ( ( 'or' ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:4:
            // ( () ( ( 'or' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:5:
            // () ( ( 'or' ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1086:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1087:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1087:2:
                // ( ( 'or' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:1:
                // ( 'or' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1088:1:
                    // ( 'or' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1089:2:
                    // 'or'
                    {
                        match(input, 46, FOLLOW_46_in_synpred2_InternalResolute2422);
                        if (state.failed) {
                            return;
                        }

                    }

                }

            }

        }
    }

    // $ANTLR end synpred2_InternalResolute

    // $ANTLR start synpred3_InternalResolute
    public final void synpred3_InternalResolute_fragment() throws RecognitionException {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:3:
        // ( ( () ( ( 'and' ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:4:
        // ( () ( ( 'and' ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:4:
            // ( () ( ( 'and' ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:5:
            // () ( ( 'and' ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1162:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1163:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1163:2:
                // ( ( 'and' ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:1:
                // ( 'and' )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1164:1:
                    // ( 'and' )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1165:2:
                    // 'and'
                    {
                        match(input, 47, FOLLOW_47_in_synpred3_InternalResolute2604);
                        if (state.failed) {
                            return;
                        }

                    }

                }

            }

        }
    }

    // $ANTLR end synpred3_InternalResolute

    // $ANTLR start synpred4_InternalResolute
    public final void synpred4_InternalResolute_fragment() throws RecognitionException {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:3:
        // ( ( () ( ( ruleRelationalOp ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:4:
        // ( () ( ( ruleRelationalOp ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:4:
            // ( () ( ( ruleRelationalOp ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:5:
            // () ( ( ruleRelationalOp ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1307:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1308:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1308:2:
                // ( ( ruleRelationalOp ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:1:
                // ( ruleRelationalOp )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1309:1:
                    // ( ruleRelationalOp )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1310:1:
                    // ruleRelationalOp
                    {
                        pushFollow(FOLLOW_ruleRelationalOp_in_synpred4_InternalResolute2989);
                        ruleRelationalOp();

                        state._fsp--;
                        if (state.failed) {
                            return;
                        }

                    }

                }

            }

        }
    }

    // $ANTLR end synpred4_InternalResolute

    // $ANTLR start synpred5_InternalResolute
    public final void synpred5_InternalResolute_fragment() throws RecognitionException {
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:3:
        // ( ( () ( ( ( '+' | '-' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:4:
        // ( () ( ( ( '+' | '-' ) ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:4:
            // ( () ( ( ( '+' | '-' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:5:
            // () ( ( ( '+' | '-' ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1384:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1385:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1385:2:
                // ( ( ( '+' | '-' ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:1:
                // ( ( '+' | '-' ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1386:1:
                    // ( ( '+' | '-' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1387:1:
                    // ( '+' | '-' )
                    {
                        if ((input.LA(1) >= 53 && input.LA(1) <= 54)) {
                            input.consume();
                            state.errorRecovery = false;
                            state.failed = false;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return;
                            }
                            MismatchedSetException mse = new MismatchedSetException(null, input);
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:3:
        // ( ( () ( ( ( '*' | '/' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:4:
        // ( () ( ( ( '*' | '/' ) ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:4:
            // ( () ( ( ( '*' | '/' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:5:
            // () ( ( ( '*' | '/' ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1481:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1482:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1482:2:
                // ( ( ( '*' | '/' ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:1:
                // ( ( '*' | '/' ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1483:1:
                    // ( ( '*' | '/' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1484:1:
                    // ( '*' | '/' )
                    {
                        if ((input.LA(1) >= 55 && input.LA(1) <= 56)) {
                            input.consume();
                            state.errorRecovery = false;
                            state.failed = false;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return;
                            }
                            MismatchedSetException mse = new MismatchedSetException(null, input);
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
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:3:
        // ( ( () ( ( ( 'union' | 'intersect' ) ) ) ) )
        // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:4:
        // ( () ( ( ( 'union' | 'intersect' ) ) ) )
        {
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:4:
            // ( () ( ( ( 'union' | 'intersect' ) ) ) )
            // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:5:
            // () ( ( ( 'union' | 'intersect' ) ) )
            {
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1578:5:
                // ()
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1579:1:
                {
                }

                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1579:2:
                // ( ( ( 'union' | 'intersect' ) ) )
                // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:1:
                // ( ( 'union' | 'intersect' ) )
                {
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1580:1:
                    // ( ( 'union' | 'intersect' ) )
                    // ../com.rockwellcollins.atc.resolute/src-gen/com/rockwellcollins/atc/resolute/parser/antlr/internal/InternalResolute.g:1581:1:
                    // ( 'union' | 'intersect' )
                    {
                        if ((input.LA(1) >= 57 && input.LA(1) <= 58)) {
                            input.consume();
                            state.errorRecovery = false;
                            state.failed = false;
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return;
                            }
                            MismatchedSetException mse = new MismatchedSetException(null, input);
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
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred4_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred1_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred7_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred6_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred5_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    public final boolean synpred3_InternalResolute() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalResolute_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed = false;
        return success;
    }

    protected DFA15 dfa15 = new DFA15(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA47 dfa47 = new DFA47(this);
    static final String DFA15_eotS = "\12\uffff";
    static final String DFA15_eofS = "\1\10\11\uffff";
    static final String DFA15_minS = "\1\4\7\0\2\uffff";
    static final String DFA15_maxS = "\1\122\7\0\2\uffff";
    static final String DFA15_acceptS = "\10\uffff\1\2\1\1";
    static final String DFA15_specialS = "\1\uffff\1\6\1\5\1\1\1\0\1\4\1\3\1\2\2\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\10\14\uffff\1\10\25\uffff\1\5\1\uffff\2\10\1\2\1\uffff\3"
                    + "\10\1\1\1\3\1\4\1\6\1\7\6\10\4\uffff\2\10\3\uffff\1\10\15\uffff" + "\1\10",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "",
            "" };

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
        for (int i = 0; i < numStates; i++) {
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
            return "1307:1: ( ( ( ( () ( ( ruleRelationalOp ) ) ) )=> ( () ( (lv_op_2_0= ruleRelationalOp ) ) ) ) ( (lv_right_3_0= rulePlusExpr ) ) )?";
        }

        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s) {
            case 0:
                int LA15_4 = input.LA(1);

                int index15_4 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_4);
                if (s >= 0) {
                    return s;
                }
                break;
            case 1:
                int LA15_3 = input.LA(1);

                int index15_3 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_3);
                if (s >= 0) {
                    return s;
                }
                break;
            case 2:
                int LA15_7 = input.LA(1);

                int index15_7 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_7);
                if (s >= 0) {
                    return s;
                }
                break;
            case 3:
                int LA15_6 = input.LA(1);

                int index15_6 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_6);
                if (s >= 0) {
                    return s;
                }
                break;
            case 4:
                int LA15_5 = input.LA(1);

                int index15_5 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_5);
                if (s >= 0) {
                    return s;
                }
                break;
            case 5:
                int LA15_2 = input.LA(1);

                int index15_2 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_2);
                if (s >= 0) {
                    return s;
                }
                break;
            case 6:
                int LA15_1 = input.LA(1);

                int index15_1 = input.index();
                input.rewind();
                s = -1;
                if ((synpred4_InternalResolute())) {
                    s = 9;
                }

                else if ((true)) {
                    s = 8;
                }

                input.seek(index15_1);
                if (s >= 0) {
                    return s;
                }
                break;
            }
            if (state.backtracking > 0) {
                state.failed = true;
                return -1;
            }
            NoViableAltException nvae = new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    static final String DFA33_eotS = "\17\uffff";
    static final String DFA33_eofS = "\1\uffff\1\15\15\uffff";
    static final String DFA33_minS = "\2\4\15\uffff";
    static final String DFA33_maxS = "\1\133\1\143\15\uffff";
    static final String DFA33_acceptS = "\2\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\14\1\15\1\1\1"
            + "\13";
    static final String DFA33_specialS = "\17\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1\1\7\1\5\1\4\10\uffff\1\13\27\uffff\1\14\23\uffff\1\2\1"
                    + "\3\1\10\2\uffff\2\11\3\uffff\14\12\10\uffff\2\6",
            "\1\15\14\uffff\1\15\25\uffff\1\15\1\16\3\15\1\uffff\16\15"
                    + "\4\uffff\2\15\2\uffff\2\15\15\uffff\1\15\20\uffff\1\15", "", "", "", "", "",
            "", "", "", "", "", "", "", "" };

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
        for (int i = 0; i < numStates; i++) {
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
            return "1751:1: ( ( () ( ( ruleQCREF ) ) ) | ( () otherlv_3= 'this' ) | ( () otherlv_5= 'fail' ( (lv_val_6_0= ruleStringTerm ) )? ) | ( () ( (lv_val_8_0= ruleIntegerTerm ) ) ) | ( () ( (lv_val_10_0= ruleRealTerm ) ) ) | ( () ( (lv_val_12_0= ruleBooleanLiteral ) ) ) | ( () ( (lv_val_14_0= ruleStringTerm ) ) ) | ( () otherlv_16= 'if' ( (lv_cond_17_0= ruleExpr ) ) otherlv_18= 'then' ( (lv_then_19_0= ruleExpr ) ) otherlv_20= 'else' ( (lv_else_21_0= ruleExpr ) ) ) | ( () ( ( (lv_quant_23_1= 'forall' | lv_quant_23_2= 'exists' ) ) ) (otherlv_24= '(' ( (lv_args_25_0= ruleArg ) ) otherlv_26= ')' )+ otherlv_27= '.' ( (lv_expr_28_0= ruleExpr ) ) ) | ( () ( (lv_fn_30_0= ruleBuiltInFunc ) ) otherlv_31= '(' ( ( (lv_args_32_0= ruleExpr ) ) (otherlv_33= ',' ( (lv_args_34_0= ruleExpr ) ) )* )? otherlv_35= ')' ) | ( () ( (otherlv_37= RULE_ID ) ) otherlv_38= '(' ( ( (lv_args_39_0= ruleExpr ) ) (otherlv_40= ',' ( (lv_args_41_0= ruleExpr ) ) )* )? otherlv_42= ')' ) | ( () otherlv_44= '{' ( (lv_map_45_0= ruleExpr ) ) otherlv_46= 'for' (otherlv_47= '(' ( (lv_args_48_0= ruleArg ) ) otherlv_49= ')' )+ (otherlv_50= '|' ( (lv_filter_51_0= ruleExpr ) ) )? otherlv_52= '}' ) | (otherlv_53= '(' this_Expr_54= ruleExpr otherlv_55= ')' ) )";
        }
    }

    static final String DFA47_eotS = "\23\uffff";
    static final String DFA47_eofS = "\6\uffff\1\15\1\17\1\uffff\1\21\2\uffff\1\15\1\uffff\1\17\3\uffff"
            + "\1\21";
    static final String DFA47_minS = "\1\4\5\uffff\2\4\1\uffff\1\51\2\uffff\1\51\1\uffff\1\51\1\uffff"
            + "\1\4\1\uffff\1\51";
    static final String DFA47_maxS = "\1\140\5\uffff\2\141\1\uffff\1\143\2\uffff\1\141\1\uffff\1\141"
            + "\1\uffff\1\4\1\uffff\1\141";
    static final String DFA47_acceptS = "\1\uffff\1\1\1\2\1\3\1\4\1\5\2\uffff\1\6\1\uffff\1\11\1\12\1\uffff"
            + "\1\7\1\uffff\1\10\1\uffff\1\13\1\uffff";
    static final String DFA47_specialS = "\23\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\11\1\5\1\6\1\7\40\uffff\1\12\14\uffff\2\10\43\uffff\2\13"
                    + "\1\2\1\1\1\uffff\1\4\1\3", "", "", "", "", "",
            "\1\14\44\uffff\2\15\11\uffff\1\15\40\uffff\1\15\2\uffff\1" + "\15\10\uffff\1\10",
            "\1\16\44\uffff\2\17\11\uffff\1\17\40\uffff\1\17\2\uffff\1" + "\17\10\uffff\1\10", "",
            "\2\21\11\uffff\1\21\40\uffff\1\21\2\uffff\1\21\10\uffff\1" + "\10\1\uffff\1\20", "",
            "", "\2\15\11\uffff\1\15\40\uffff\1\15\2\uffff\1\15\10\uffff\1" + "\10", "",
            "\2\17\11\uffff\1\17\40\uffff\1\17\2\uffff\1\17\10\uffff\1" + "\10", "", "\1\22", "",
            "\2\21\11\uffff\1\21\40\uffff\1\21\2\uffff\1\21\10\uffff\1" + "\10" };

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
        for (int i = 0; i < numStates; i++) {
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
            return "3080:1: (this_RecordTerm_0= ruleRecordTerm | this_ReferenceTerm_1= ruleReferenceTerm | this_ComponentClassifierTerm_2= ruleComponentClassifierTerm | this_ComputedTerm_3= ruleComputedTerm | this_StringTerm_4= ruleStringTerm | this_NumericRangeTerm_5= ruleNumericRangeTerm | this_RealTerm_6= ruleRealTerm | this_IntegerTerm_7= ruleIntegerTerm | this_ListTerm_8= ruleListTerm | this_BooleanLiteral_9= ruleBooleanLiteral | this_LiteralorReferenceTerm_10= ruleLiteralorReferenceTerm )";
        }
    }

    public static final BitSet FOLLOW_ruleAnnexLibrary_in_entryRuleAnnexLibrary75 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleAnnexLibrary85 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleResoluteLibrary_in_ruleAnnexLibrary131 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleResoluteLibrary_in_entryRuleResoluteLibrary171 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteLibrary181 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleDefinition_in_ruleResoluteLibrary236 = new BitSet(
            new long[] { 0x0000000000000012L });
    public static final BitSet FOLLOW_ruleDefinition_in_entryRuleDefinition273 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinition283 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstantDefinition_in_ruleDefinition330 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_ruleDefinition357 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleType_in_entryRuleType392 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleType402 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_16_in_ruleType449 = new BitSet(
            new long[] { 0x0000003FFFFD0000L });
    public static final BitSet FOLLOW_ruleType_in_ruleType470 = new BitSet(
            new long[] { 0x0000000000020000L });
    public static final BitSet FOLLOW_17_in_ruleType482 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBuiltinType_in_ruleType511 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBuiltinType_in_entryRuleBuiltinType546 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltinType556 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_18_in_ruleBuiltinType600 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_19_in_ruleBuiltinType629 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_20_in_ruleBuiltinType658 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_21_in_ruleBuiltinType687 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_22_in_ruleBuiltinType716 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_23_in_ruleBuiltinType745 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_ruleBuiltinType774 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_25_in_ruleBuiltinType803 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_26_in_ruleBuiltinType832 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_27_in_ruleBuiltinType861 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_ruleBuiltinType890 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_29_in_ruleBuiltinType919 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_30_in_ruleBuiltinType948 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_31_in_ruleBuiltinType977 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_32_in_ruleBuiltinType1006 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_33_in_ruleBuiltinType1035 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_34_in_ruleBuiltinType1064 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_35_in_ruleBuiltinType1093 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_36_in_ruleBuiltinType1122 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_37_in_ruleBuiltinType1151 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleArg_in_entryRuleArg1202 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleArg1212 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleArg1254 = new BitSet(
            new long[] { 0x0000004000000000L });
    public static final BitSet FOLLOW_38_in_ruleArg1271 = new BitSet(
            new long[] { 0x0000003FFFFD0000L });
    public static final BitSet FOLLOW_ruleType_in_ruleArg1292 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstantDefinition_in_entryRuleConstantDefinition1328 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantDefinition1338 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleConstantDefinition1380 = new BitSet(
            new long[] { 0x0000004000000000L });
    public static final BitSet FOLLOW_38_in_ruleConstantDefinition1397 = new BitSet(
            new long[] { 0x0000003FFFFD0000L });
    public static final BitSet FOLLOW_ruleType_in_ruleConstantDefinition1418 = new BitSet(
            new long[] { 0x0000008000000000L });
    public static final BitSet FOLLOW_39_in_ruleConstantDefinition1430 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleConstantDefinition1451 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleFunctionDefinition_in_entryRuleFunctionDefinition1487 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleFunctionDefinition1497 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleFunctionDefinition1539 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleFunctionDefinition1556 = new BitSet(
            new long[] { 0x0000040000000010L });
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition1578 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_41_in_ruleFunctionDefinition1591 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleArg_in_ruleFunctionDefinition1612 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_42_in_ruleFunctionDefinition1628 = new BitSet(
            new long[] { 0x0000084000000000L });
    public static final BitSet FOLLOW_ruleDefinitionBody_in_ruleFunctionDefinition1649 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleClaimString_in_entryRuleClaimString1685 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleClaimString1695 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleClaimString1747 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleClaimString1789 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleDefinitionBody_in_entryRuleDefinitionBody1826 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleDefinitionBody1836 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_38_in_ruleDefinitionBody1883 = new BitSet(
            new long[] { 0x0000003FFFFD0000L });
    public static final BitSet FOLLOW_ruleType_in_ruleDefinitionBody1904 = new BitSet(
            new long[] { 0x0000008000000000L });
    public static final BitSet FOLLOW_39_in_ruleDefinitionBody1916 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody1937 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_43_in_ruleDefinitionBody1966 = new BitSet(new long[] {
            0x78401100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody1979 = new BitSet(
            new long[] { 0x0000100000000030L });
    public static final BitSet FOLLOW_ruleClaimString_in_ruleDefinitionBody2000 = new BitSet(
            new long[] { 0x0000100000000030L });
    public static final BitSet FOLLOW_44_in_ruleDefinitionBody2013 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleDefinitionBody2036 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleExpr_in_entryRuleExpr2073 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleExpr2083 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleExpr2129 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleImpliesExpr_in_entryRuleImpliesExpr2163 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpr2173 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleOrExpr_in_ruleImpliesExpr2220 = new BitSet(
            new long[] { 0x0000200000000002L });
    public static final BitSet FOLLOW_45_in_ruleImpliesExpr2271 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleImpliesExpr_in_ruleImpliesExpr2307 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleOrExpr_in_entryRuleOrExpr2345 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpr2355 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2402 = new BitSet(
            new long[] { 0x0000400000000002L });
    public static final BitSet FOLLOW_46_in_ruleOrExpr2453 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleAndExpr_in_ruleOrExpr2489 = new BitSet(
            new long[] { 0x0000400000000002L });
    public static final BitSet FOLLOW_ruleAndExpr_in_entryRuleAndExpr2527 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpr2537 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr2584 = new BitSet(
            new long[] { 0x0000800000000002L });
    public static final BitSet FOLLOW_47_in_ruleAndExpr2635 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleRelationalExpr_in_ruleAndExpr2671 = new BitSet(
            new long[] { 0x0000800000000002L });
    public static final BitSet FOLLOW_ruleRelationalOp_in_entryRuleRelationalOp2710 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalOp2721 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_48_in_ruleRelationalOp2759 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_43_in_ruleRelationalOp2778 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_49_in_ruleRelationalOp2797 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_50_in_ruleRelationalOp2816 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_39_in_ruleRelationalOp2835 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_51_in_ruleRelationalOp2854 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_52_in_ruleRelationalOp2873 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRelationalExpr_in_entryRuleRelationalExpr2913 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpr2923 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr2970 = new BitSet(
            new long[] { 0x001F088000000002L });
    public static final BitSet FOLLOW_ruleRelationalOp_in_ruleRelationalExpr3019 = new BitSet(
            new long[] { 0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_rulePlusExpr_in_ruleRelationalExpr3042 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePlusExpr_in_entryRulePlusExpr3080 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRulePlusExpr3090 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3137 = new BitSet(
            new long[] { 0x0060000000000002L });
    public static final BitSet FOLLOW_53_in_rulePlusExpr3209 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_54_in_rulePlusExpr3238 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleTimesExpr_in_rulePlusExpr3277 = new BitSet(
            new long[] { 0x0060000000000002L });
    public static final BitSet FOLLOW_ruleTimesExpr_in_entryRuleTimesExpr3315 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleTimesExpr3325 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3372 = new BitSet(
            new long[] { 0x0180000000000002L });
    public static final BitSet FOLLOW_55_in_ruleTimesExpr3444 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_56_in_ruleTimesExpr3473 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleSetOpExpr_in_ruleTimesExpr3512 = new BitSet(
            new long[] { 0x0180000000000002L });
    public static final BitSet FOLLOW_ruleSetOpExpr_in_entryRuleSetOpExpr3550 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleSetOpExpr3560 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3607 = new BitSet(
            new long[] { 0x0600000000000002L });
    public static final BitSet FOLLOW_57_in_ruleSetOpExpr3679 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_58_in_ruleSetOpExpr3708 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_rulePrefixExpr_in_ruleSetOpExpr3747 = new BitSet(
            new long[] { 0x0600000000000002L });
    public static final BitSet FOLLOW_rulePrefixExpr_in_entryRulePrefixExpr3785 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRulePrefixExpr3795 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_54_in_rulePrefixExpr3850 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_59_in_rulePrefixExpr3879 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_rulePrefixExpr_in_rulePrefixExpr3916 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleAtomicExpr_in_rulePrefixExpr3945 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleAtomicExpr_in_entryRuleAtomicExpr3980 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleAtomicExpr3990 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQCREF_in_ruleAtomicExpr4048 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_60_in_ruleAtomicExpr4077 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_61_in_ruleAtomicExpr4106 = new BitSet(
            new long[] { 0x0000000000000022L });
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4127 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleAtomicExpr4166 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleAtomicExpr4204 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleAtomicExpr4242 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleStringTerm_in_ruleAtomicExpr4280 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_62_in_ruleAtomicExpr4309 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4330 = new BitSet(
            new long[] { 0x8000000000000000L });
    public static final BitSet FOLLOW_63_in_ruleAtomicExpr4342 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4363 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000001L });
    public static final BitSet FOLLOW_64_in_ruleAtomicExpr4375 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4396 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_65_in_ruleAtomicExpr4433 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_66_in_ruleAtomicExpr4462 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4491 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4512 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4524 = new BitSet(new long[] {
            0x0000010000000000L, 0x0000000000000008L });
    public static final BitSet FOLLOW_67_in_ruleAtomicExpr4538 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4559 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_ruleAtomicExpr4597 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4609 = new BitSet(new long[] {
            0x78400500000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4631 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4644 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4665 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4681 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleAtomicExpr4718 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4730 = new BitSet(new long[] {
            0x78400500000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4752 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_41_in_ruleAtomicExpr4765 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4786 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4802 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_16_in_ruleAtomicExpr4831 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4852 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000010L });
    public static final BitSet FOLLOW_68_in_ruleAtomicExpr4864 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4877 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleArg_in_ruleAtomicExpr4898 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr4910 = new BitSet(new long[] {
            0x0000010000020000L, 0x0000000000000020L });
    public static final BitSet FOLLOW_69_in_ruleAtomicExpr4925 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr4946 = new BitSet(
            new long[] { 0x0000000000020000L });
    public static final BitSet FOLLOW_17_in_ruleAtomicExpr4960 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_40_in_ruleAtomicExpr4980 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleAtomicExpr5002 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleAtomicExpr5013 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBuiltInFunc_in_entryRuleBuiltInFunc5051 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleBuiltInFunc5062 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_70_in_ruleBuiltInFunc5100 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_71_in_ruleBuiltInFunc5119 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_72_in_ruleBuiltInFunc5138 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_73_in_ruleBuiltInFunc5157 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_74_in_ruleBuiltInFunc5176 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_75_in_ruleBuiltInFunc5195 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_76_in_ruleBuiltInFunc5214 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_77_in_ruleBuiltInFunc5233 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_78_in_ruleBuiltInFunc5252 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_79_in_ruleBuiltInFunc5271 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_80_in_ruleBuiltInFunc5290 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_81_in_ruleBuiltInFunc5309 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRealTerm_in_entryRuleRealTerm5349 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleRealTerm5359 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleUnsignedReal_in_ruleRealTerm5405 = new BitSet(
            new long[] { 0x0000000000000012L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleRealTerm5425 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleUnsignedReal_in_entryRuleUnsignedReal5463 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedReal5474 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_REAL_LIT_in_ruleUnsignedReal5513 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleIntegerTerm_in_entryRuleIntegerTerm5557 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerTerm5567 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleUnsignedInt_in_ruleIntegerTerm5613 = new BitSet(
            new long[] { 0x0000000000000012L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleIntegerTerm5633 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleUnsignedInt_in_entryRuleUnsignedInt5671 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleUnsignedInt5682 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleUnsignedInt5721 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleResoluteSubclause_in_entryRuleResoluteSubclause5765 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleResoluteSubclause5775 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleProveStatement_in_ruleResoluteSubclause5830 = new BitSet(
            new long[] { 0x0000000000000002L, 0x0000000000040000L });
    public static final BitSet FOLLOW_ruleProveStatement_in_entryRuleProveStatement5867 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleProveStatement5877 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_82_in_ruleProveStatement5914 = new BitSet(new long[] {
            0x78400100000100F0L, 0x000000000C03FFC6L });
    public static final BitSet FOLLOW_ruleExpr_in_ruleProveStatement5935 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleContainedPropertyAssociation_in_entryRuleContainedPropertyAssociation5973 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleContainedPropertyAssociation5983 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQPREF_in_ruleContainedPropertyAssociation6031 = new BitSet(
            new long[] { 0x0000200000000000L, 0x0000000000080000L });
    public static final BitSet FOLLOW_45_in_ruleContainedPropertyAssociation6044 = new BitSet(
            new long[] { 0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_83_in_ruleContainedPropertyAssociation6068 = new BitSet(
            new long[] { 0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_84_in_ruleContainedPropertyAssociation6100 = new BitSet(
            new long[] { 0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6136 = new BitSet(
            new long[] { 0x0010020000000000L, 0x0000000001200000L });
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6149 = new BitSet(
            new long[] { 0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_ruleContainedPropertyAssociation6170 = new BitSet(
            new long[] { 0x0010020000000000L, 0x0000000001200000L });
    public static final BitSet FOLLOW_85_in_ruleContainedPropertyAssociation6186 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000000400000L });
    public static final BitSet FOLLOW_86_in_ruleContainedPropertyAssociation6198 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6219 = new BitSet(
            new long[] { 0x0010020000000000L, 0x0000000001000000L });
    public static final BitSet FOLLOW_41_in_ruleContainedPropertyAssociation6232 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleContainmentPath_in_ruleContainedPropertyAssociation6253 = new BitSet(
            new long[] { 0x0010020000000000L, 0x0000000001000000L });
    public static final BitSet FOLLOW_52_in_ruleContainedPropertyAssociation6270 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000000800000L });
    public static final BitSet FOLLOW_87_in_ruleContainedPropertyAssociation6282 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleContainedPropertyAssociation6294 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleQCREF_in_ruleContainedPropertyAssociation6317 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleContainedPropertyAssociation6329 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000001000000L });
    public static final BitSet FOLLOW_88_in_ruleContainedPropertyAssociation6343 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleContainmentPath_in_entryRuleContainmentPath6383 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPath6393 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6448 = new BitSet(
            new long[] { 0x0000000000000002L, 0x0000000000000008L });
    public static final BitSet FOLLOW_67_in_ruleContainmentPath6461 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleContainmentPath6482 = new BitSet(
            new long[] { 0x0000000000000002L, 0x0000000000000008L });
    public static final BitSet FOLLOW_ruleOptionalModalPropertyValue_in_entryRuleOptionalModalPropertyValue6522 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleOptionalModalPropertyValue6532 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleOptionalModalPropertyValue6578 = new BitSet(
            new long[] { 0x0010000000000002L });
    public static final BitSet FOLLOW_52_in_ruleOptionalModalPropertyValue6591 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000002000000L });
    public static final BitSet FOLLOW_89_in_ruleOptionalModalPropertyValue6603 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleOptionalModalPropertyValue6615 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6635 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_41_in_ruleOptionalModalPropertyValue6648 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleOptionalModalPropertyValue6668 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_42_in_ruleOptionalModalPropertyValue6682 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue6720 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue6730 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_rulePropertyValue6775 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_entryRulePropertyExpression6810 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyExpression6820 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRecordTerm_in_rulePropertyExpression6867 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleReferenceTerm_in_rulePropertyExpression6894 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_rulePropertyExpression6921 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleComputedTerm_in_rulePropertyExpression6948 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleStringTerm_in_rulePropertyExpression6975 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_rulePropertyExpression7002 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRealTerm_in_rulePropertyExpression7029 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleIntegerTerm_in_rulePropertyExpression7056 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleListTerm_in_rulePropertyExpression7083 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_rulePropertyExpression7110 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_rulePropertyExpression7137 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleLiteralorReferenceTerm_in_entryRuleLiteralorReferenceTerm7172 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteralorReferenceTerm7182 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQPREF_in_ruleLiteralorReferenceTerm7229 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral7264 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral7274 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_90_in_ruleBooleanLiteral7327 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_91_in_ruleBooleanLiteral7358 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstantValue_in_entryRuleConstantValue7395 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleConstantValue7405 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQPREF_in_ruleConstantValue7452 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleReferenceTerm_in_entryRuleReferenceTerm7487 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleReferenceTerm7497 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_92_in_ruleReferenceTerm7534 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleReferenceTerm7546 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_ruleReferenceTerm7568 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000000000008L });
    public static final BitSet FOLLOW_67_in_ruleReferenceTerm7580 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7603 = new BitSet(
            new long[] { 0x0000040000000000L, 0x0000000000000008L });
    public static final BitSet FOLLOW_67_in_ruleReferenceTerm7616 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_ruleReferenceTerm7637 = new BitSet(
            new long[] { 0x0000040000000000L, 0x0000000000000008L });
    public static final BitSet FOLLOW_42_in_ruleReferenceTerm7651 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRecordTerm_in_entryRuleRecordTerm7687 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleRecordTerm7697 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_93_in_ruleRecordTerm7734 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_ruleRecordTerm7755 = new BitSet(
            new long[] { 0x0000000000000010L, 0x0000000040000000L });
    public static final BitSet FOLLOW_94_in_ruleRecordTerm7768 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleComputedTerm_in_entryRuleComputedTerm7806 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleComputedTerm7816 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_95_in_ruleComputedTerm7853 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleComputedTerm7865 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleComputedTerm7882 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleComputedTerm7899 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleComponentClassifierTerm_in_entryRuleComponentClassifierTerm7935 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleComponentClassifierTerm7945 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_96_in_ruleComponentClassifierTerm7982 = new BitSet(
            new long[] { 0x0000010000000000L });
    public static final BitSet FOLLOW_40_in_ruleComponentClassifierTerm7994 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_ruleQCREF_in_ruleComponentClassifierTerm8017 = new BitSet(
            new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ruleComponentClassifierTerm8029 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleListTerm_in_entryRuleListTerm8065 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleListTerm8075 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_40_in_ruleListTerm8121 = new BitSet(new long[] {
            0x00600500000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8143 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_41_in_ruleListTerm8156 = new BitSet(new long[] {
            0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleListTerm8177 = new BitSet(
            new long[] { 0x0000060000000000L });
    public static final BitSet FOLLOW_42_in_ruleListTerm8193 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleFieldPropertyAssociation_in_entryRuleFieldPropertyAssociation8229 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleFieldPropertyAssociation8239 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleFieldPropertyAssociation8284 = new BitSet(
            new long[] { 0x0000200000000000L });
    public static final BitSet FOLLOW_45_in_ruleFieldPropertyAssociation8296 = new BitSet(
            new long[] { 0x00600100000000F0L, 0x00000001BC100000L });
    public static final BitSet FOLLOW_rulePropertyExpression_in_ruleFieldPropertyAssociation8317 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000001000000L });
    public static final BitSet FOLLOW_88_in_ruleFieldPropertyAssociation8329 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleContainmentPathElement_in_entryRuleContainmentPathElement8365 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleContainmentPathElement8375 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleContainmentPathElement8420 = new BitSet(
            new long[] { 0x0000000000000002L, 0x0000000020000000L });
    public static final BitSet FOLLOW_ruleArrayRange_in_ruleContainmentPathElement8441 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQualifiedContainmentPathElement_in_entryRuleQualifiedContainmentPathElement8478 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedContainmentPathElement8488 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQCLREF_in_ruleQualifiedContainmentPathElement8535 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePlusMinus_in_entryRulePlusMinus8573 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRulePlusMinus8584 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_53_in_rulePlusMinus8622 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_54_in_rulePlusMinus8641 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleStringTerm_in_entryRuleStringTerm8681 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleStringTerm8691 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNoQuoteString_in_ruleStringTerm8736 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNoQuoteString_in_entryRuleNoQuoteString8772 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleNoQuoteString8783 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNoQuoteString8822 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleArrayRange_in_entryRuleArrayRange8866 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayRange8876 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_93_in_ruleArrayRange8922 = new BitSet(
            new long[] { 0x0000000000000080L });
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange8943 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000240000000L });
    public static final BitSet FOLLOW_97_in_ruleArrayRange8956 = new BitSet(
            new long[] { 0x0000000000000080L });
    public static final BitSet FOLLOW_ruleINTVALUE_in_ruleArrayRange8977 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000040000000L });
    public static final BitSet FOLLOW_94_in_ruleArrayRange8991 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleSignedConstant_in_entryRuleSignedConstant9027 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleSignedConstant9037 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_rulePlusMinus_in_ruleSignedConstant9083 = new BitSet(
            new long[] { 0x00600000000000D0L });
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleSignedConstant9104 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNumericRangeTerm_in_entryRuleNumericRangeTerm9144 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleNumericRangeTerm9154 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9200 = new BitSet(
            new long[] { 0x0000000000000000L, 0x0000000200000000L });
    public static final BitSet FOLLOW_97_in_ruleNumericRangeTerm9212 = new BitSet(
            new long[] { 0x00600000000000D0L });
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9233 = new BitSet(
            new long[] { 0x0000000000000002L, 0x0000000400000000L });
    public static final BitSet FOLLOW_98_in_ruleNumericRangeTerm9246 = new BitSet(
            new long[] { 0x00600000000000D0L });
    public static final BitSet FOLLOW_ruleNumAlt_in_ruleNumericRangeTerm9267 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNumAlt_in_entryRuleNumAlt9305 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleNumAlt9315 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRealTerm_in_ruleNumAlt9362 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleIntegerTerm_in_ruleNumAlt9389 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleSignedConstant_in_ruleNumAlt9416 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstantValue_in_ruleNumAlt9443 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleINTVALUE_in_entryRuleINTVALUE9479 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleINTVALUE9490 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_INTEGER_LIT_in_ruleINTVALUE9529 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQCLREF_in_entryRuleQCLREF9574 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleQCLREF9585 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF9625 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000800000000L });
    public static final BitSet FOLLOW_99_in_ruleQCLREF9643 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCLREF9658 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQPREF_in_entryRuleQPREF9704 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleQPREF9715 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF9755 = new BitSet(new long[] {
            0x0000000000000002L, 0x0000000800000000L });
    public static final BitSet FOLLOW_99_in_ruleQPREF9774 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQPREF9789 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleQCREF_in_entryRuleQCREF9837 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleQCREF9848 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9889 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000800000000L });
    public static final BitSet FOLLOW_99_in_ruleQCREF9907 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9924 = new BitSet(new long[] {
            0x0000000000000002L, 0x0000000000000008L });
    public static final BitSet FOLLOW_67_in_ruleQCREF9943 = new BitSet(
            new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleQCREF9958 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleSTAR_in_entryRuleSTAR10006 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleSTAR10017 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_55_in_ruleSTAR10054 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_45_in_synpred1_InternalResolute2240 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_46_in_synpred2_InternalResolute2422 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_47_in_synpred3_InternalResolute2604 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRelationalOp_in_synpred4_InternalResolute2989 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_in_synpred5_InternalResolute3156 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_in_synpred6_InternalResolute3391 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_set_in_synpred7_InternalResolute3626 = new BitSet(
            new long[] { 0x0000000000000002L });

}