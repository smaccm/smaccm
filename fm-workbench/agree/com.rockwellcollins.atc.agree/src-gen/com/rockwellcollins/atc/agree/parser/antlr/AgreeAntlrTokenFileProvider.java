/*
 * generated by Xtext
 */
package com.rockwellcollins.atc.agree.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class AgreeAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("com/rockwellcollins/atc/agree/parser/antlr/internal/InternalAgreeParser.tokens");
	}
}
