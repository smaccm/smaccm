package com.rockwellcollins.atc.agree.parsing;

import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.modelsupport.errorreporting.ParseErrorReporter;
import org.osate.annexsupport.AnnexParseUtil;
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
			Injector injector = AgreeActivator.getInstance()
					.getInjector(AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE);
			parser = injector.getInstance(AgreeParser.class);
		}
		return parser;
	}

	protected AgreeGrammarAccess getGrammarAccess() {
		return getParser().getGrammarAccess();
	}

	@Override
	public AnnexLibrary parseAnnexLibrary(String annexName, String source, String filename, int line, int column,
			ParseErrorReporter errReporter) {
		return (AnnexLibrary) AnnexParseUtil.parse(getParser(), source, getGrammarAccess().getAgreeLibraryRule(),
				filename, line, column, errReporter);
	}

	@Override
	public AnnexSubclause parseAnnexSubclause(String annexName, String source, String filename, int line, int column,
			ParseErrorReporter errReporter) {
		return (AnnexSubclause) AnnexParseUtil.parse(getParser(), source, getGrammarAccess().getAgreeSubclauseRule(),
				filename, line, column, errReporter);
	}

}