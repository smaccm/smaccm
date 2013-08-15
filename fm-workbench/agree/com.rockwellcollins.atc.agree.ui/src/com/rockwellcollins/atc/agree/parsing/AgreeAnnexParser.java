package com.rockwellcollins.atc.agree.parsing;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.modelsupport.errorreporting.MarkerParseErrorReporter;
import org.osate.aadl2.modelsupport.errorreporting.ParseErrorReporter;
import org.osate.annexsupport.AnnexParseResultImpl;
import org.osate.annexsupport.AnnexParser;

import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.parser.antlr.AgreeParser;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

// Based on EMV2AnnexParser from Error Model annex
public class AgreeAnnexParser implements AnnexParser {
    private AgreeParser parser;

    protected AgreeParser getParser() {
        if (parser == null) {
            Injector injector = AgreeActivator.getInstance().getInjector(
                    AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE);
            parser = injector.getInstance(AgreeParser.class);
        }
        return parser;
    }

    protected AgreeGrammarAccess getGrammarAccess() {
        return getParser().getGrammarAccess();
    }

    @Override
    public AnnexLibrary parseAnnexLibrary(String annexName, String source, String filename,
            int line, int column, ParseErrorReporter errReporter) {
        return (AnnexLibrary) parse(source, getGrammarAccess().getAgreeLibraryRule(), filename,
                line, column, errReporter);

    }

    @Override
    public AnnexSubclause parseAnnexSubclause(String annexName, String source, String filename,
            int line, int column, ParseErrorReporter errReporter) {
        return (AnnexSubclause) parse(source, getGrammarAccess().getAgreeSubclauseRule(), filename,
                line, column, errReporter);
    }

    public EObject parse(String editString, ParserRule parserRule, String filename, int line,
            int offset, ParseErrorReporter err) {
        try {
            editString = genWhitespace(offset) + editString + "\r\n";
            IParseResult parseResult = getParser().parse(parserRule, new StringReader(editString));

            EObject result = null;
            if (isValidParseResult(parseResult)) {
                result = parseResult.getRootASTElement();
                result.eAdapters().add(new AnnexParseResultImpl(parseResult, offset));
                return result;
            } else {
                createDiagnostics(parseResult, filename, err);
                result = parseResult.getRootASTElement();
                if (result != null) {
                    result.eAdapters().add(new AnnexParseResultImpl(parseResult, offset));
                    return result;
                } else {
                    return null;
                }
            }
        } catch (Exception exc) {
            return null;
        }
    }

    protected String genWhitespace(int length) {
        char[] array = new char[length];
        Arrays.fill(array, ' ');
        return new String(array);
    }

    private boolean isValidParseResult(IParseResult parseResult) {
        EObject rootASTElement = parseResult.getRootASTElement();
        return (rootASTElement != null && !parseResult.hasSyntaxErrors());
    }

    /**
     * Creates {@link Diagnostic diagnostics} from {@link SyntaxError syntax
     * errors} in {@link ParseResult}. No diagnostics will be created if
     * {@link #isValidationDisabled() validation is disabled} for this resource.
     * 
     * @param parseResult
     *            the parse result that provides the syntax errors.
     * @return list of {@link Diagnostic}. Never <code>null</code>.
     */
    private void createDiagnostics(IParseResult parseResult, String filename, ParseErrorReporter err) {
        if (err instanceof MarkerParseErrorReporter) {
            List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
            Resource res = ((MarkerParseErrorReporter) err).getContextResource();
            for (INode error : parseResult.getSyntaxErrors()) {
                if (res == null) {
                    SyntaxErrorMessage errormsg = error.getSyntaxErrorMessage();
                    String msg = errormsg.getMessage();
                    err.error(filename, error.getStartLine(), msg);
                } else {
                    diagnostics.add(new XtextSyntaxDiagnostic(error));
                }
            }
            if (res != null) {
                res.getErrors().addAll(diagnostics);
            }
        } else {
            for (INode error : parseResult.getSyntaxErrors()) {
                SyntaxErrorMessage errormsg = error.getSyntaxErrorMessage();
                String msg = errormsg.getMessage();
                err.error(filename, error.getStartLine(), msg);
            }
        }
    }
}