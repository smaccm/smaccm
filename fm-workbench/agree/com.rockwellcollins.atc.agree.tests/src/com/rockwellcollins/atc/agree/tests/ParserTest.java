package com.rockwellcollins.atc.agree.tests;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osate.aadl2.Element;

import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.AgreeInjectorProvider;
import com.rockwellcollins.atc.agree.agree.EnumStatement;

@InjectWith(AgreeInjectorProvider.class)
@RunWith(XtextRunner.class)
public class ParserTest {

	@Inject
	public ParseHelper<Element> parser;

	private void assertStringSame(String str1, String str2) {
		assertTrue(str1.equals(str2));
	}

	@Test
	public void parseEnumStatement() {
		try {
			EnumStatement e = (EnumStatement) parser.parse("enum color = {red, blue, yellow, green}");
			assertSame(e.getEnums().size(), 4);
			assertStringSame(e.getName(), "color");
			assertStringSame(e.getEnums().get(0).getName(), "red");
			assertStringSame(e.getEnums().get(1).getName(), "blue");
			assertStringSame(e.getEnums().get(2).getName(), "yellow");
			assertStringSame(e.getEnums().get(3).getName(), "green");
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}