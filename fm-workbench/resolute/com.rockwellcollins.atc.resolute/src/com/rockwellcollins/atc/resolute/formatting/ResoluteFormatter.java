/*
 * generated by Xtext
 */
package com.rockwellcollins.atc.resolute.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;

/**
 * This class contains custom formatting description.
 *
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 *
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an
 * example
 */
public class ResoluteFormatter extends AbstractDeclarativeFormatter {

	@Inject
	private ResoluteGrammarAccess g;

    @Override
    protected void configureFormatting(FormattingConfig c) {
        // It's usually a good idea to activate the following three statements.
        // They will add and preserve newlines around comments
        // c.setLinewrap(0, 1,
        // 2).before(getGrammarAccess().getSL_COMMENTRule());
        // c.setLinewrap(0, 1,
        // 2).before(getGrammarAccess().getML_COMMENTRule());
        // c.setLinewrap(0, 1, 1).after(getGrammarAccess().getML_COMMENTRule());

		c.setIndentationIncrement().before(g.getResoluteLibraryRule());
		c.setLinewrap().around(g.getDefinitionRule());
		c.setIndentationDecrement();
		c.setIndentationDecrement().after(g.getResoluteLibraryRule());

		for (Keyword comma : g.findKeywords(",")) {
			c.setNoSpace().before(comma);
		}

		for (Keyword semi : g.findKeywords(":")) {
			c.setNoSpace().before(semi);
		}

		for (Keyword period : g.findKeywords(".")) {
			c.setNoSpace().around(period);
		}

		for (Keyword openParen : g.findKeywords("(")) {
			c.setNoSpace().after(openParen);
		}

		for (Keyword closeParen : g.findKeywords(")")) {
			c.setNoSpace().before(closeParen);
		}

    }
}
