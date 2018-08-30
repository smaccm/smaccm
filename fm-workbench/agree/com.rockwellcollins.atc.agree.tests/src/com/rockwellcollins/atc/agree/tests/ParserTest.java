package com.rockwellcollins.atc.agree.tests;

import static org.junit.Assert.assertNull;
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
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl;

@InjectWith(AgreeInjectorProvider.class)
@RunWith(XtextRunner.class)
public class ParserTest {

	@Inject
	public ParseHelper<Element> parser;

	private void assertStringSame(String str1, String str2) {
		assertTrue(str1.equals(str2));
	}

	@Test
	public void parseFnDefExprMulti() {
		try {
			Element e = parser.parse("fun foo(a1:t1, a2:t2): rt = e");
			assertTrue(e instanceof FnDefExprImpl);
			FnDefExpr fd = (FnDefExpr) e;
			assertStringSame(fd.getName(), "foo");
			assertSame(fd.getArgs().size(), 2);

			assertStringSame(fd.getArgs().get(0).getName(), "a1");
			Type t1 = fd.getArgs().get(0).getType();
			assertTrue(t1 instanceof RecordType);
			assertNull(((RecordType) t1).getRecord().getBase().getName()); // cross-ref


			assertStringSame(fd.getArgs().get(1).getName(), "a2");
			Type t2 = fd.getArgs().get(1).getType();
			assertTrue(t2 instanceof RecordType);
			assertNull(((RecordType) t2).getRecord().getBase().getName()); // cross-ref

			Type rt = fd.getType();
			assertTrue(rt instanceof RecordType);
			assertNull(((RecordType) rt).getRecord().getBase().getName()); /// cross-ref

			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void parseEnumStatementMulti() {
		try {
			Element elm = parser.parse("enum color = {red, blue, yellow}");
			assertTrue(elm instanceof EnumStatementImpl);
			EnumStatement e = (EnumStatement) elm;
			assertSame(e.getEnums().size(), 3);
			assertStringSame(e.getName(), "color");
			assertStringSame(e.getEnums().get(0).getName(), "red");
			assertStringSame(e.getEnums().get(1).getName(), "blue");
			assertStringSame(e.getEnums().get(2).getName(), "yellow");
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void parseEnumStatementSingle() {
		try {
			Element elm = parser.parse("enum color = {red}");
			assertTrue(elm instanceof EnumStatementImpl);
			EnumStatement e = (EnumStatement) elm;
			assertSame(e.getEnums().size(), 1);
			assertStringSame(e.getName(), "color");
			assertStringSame(e.getEnums().get(0).getName(), "red");
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}