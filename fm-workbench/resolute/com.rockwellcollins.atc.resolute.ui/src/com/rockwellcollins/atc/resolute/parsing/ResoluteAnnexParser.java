package com.rockwellcollins.atc.resolute.parsing;

import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.modelsupport.errorreporting.ParseErrorReporter;
import org.osate.annexsupport.AnnexParseUtil;
import org.osate.annexsupport.AnnexParser;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rockwellcollins.atc.resolute.parser.antlr.ResoluteParser;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;
import com.rockwellcollins.atc.resolute.ui.internal.ResoluteActivator;

// Based on EMV2AnnexParser from Error Model annex
public class ResoluteAnnexParser implements AnnexParser {
    @Inject
    public ResoluteParser parser;

    protected ResoluteParser getParser() {
        if (parser == null) {
            Injector injector = ResoluteActivator.getInstance().getInjector(
            		ResoluteActivator.COM_ROCKWELLCOLLINS_ATC_RESOLUTE_RESOLUTE);
            parser = injector.getInstance(ResoluteParser.class);
        }
        return parser;
    }

    protected ResoluteGrammarAccess getGrammarAccess() {
        return getParser().getGrammarAccess();
    }

    public AnnexLibrary parseAnnexLibrary(String annexName, String source, String filename,
            int line, int column, ParseErrorReporter errReporter) {
        return (AnnexLibrary) AnnexParseUtil.parse(getParser(),source, getGrammarAccess().getResoluteLibraryRule(), filename, line,
                column, errReporter);
    }

    public AnnexSubclause parseAnnexSubclause(String annexName, String source, String filename,
            int line, int column, ParseErrorReporter errReporter) {
        return (AnnexSubclause) AnnexParseUtil.parse(getParser(),source,getGrammarAccess().getResoluteSubclauseRule(),filename,line,column, errReporter);
    }

}