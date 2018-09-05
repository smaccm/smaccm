package com.rockwellcollins.atc.agree.tests;

import static com.rockwellcollins.atc.agree.tests.Util.assertStringSame;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osate.aadl2.Element;

import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.AgreeInjectorProvider;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl;

@InjectWith(AgreeInjectorProvider.class)
@RunWith(XtextRunner.class)
public class ParserTest {

	@Inject
	public ParseHelper<AgreeContract> parser;

	private Element parseSpec(String str) {
		try {
			return parser.parse(str).getSpecs().get(0);
		} catch (Exception e) {
			fail();
		}
		return null;
	}

	@Test
	public void parseAssumeExpr() {
		Element e = parseSpec("assume \"xyz\": e; ");
		assertTrue(e instanceof AssumeStatementImpl);
		AssumeStatement a = (AssumeStatement) e;
		assertStringSame(a.getStr(), "xyz");
		assertNotNull(a.getExpr());
		assertNull(a.getPattern());
	}

	@Test
	public void parseAssumePattern() {
		Element e = parseSpec("assume \"xyz\": whenever e1 occurs e2 occurs;");
		assertTrue(e instanceof AssumeStatementImpl);
		AssumeStatement a = (AssumeStatement) e;
		assertStringSame(a.getStr(), "xyz");
		assertNull(a.getExpr());
		assertNotNull(a.getPattern());
	}

	@Test
	public void parseFnDefExprMultiRecord() {
		Element e = parseSpec("fun foo(a1:t1, a2:t2): rt = e");
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

		Expr ex = fd.getExpr();
		assertNotNull(ex);


	}

	@Test
	public void parseFnDefExprSinglePrim() {
		Element e = parseSpec("fun foo(a:int): int = e");
		assertTrue(e instanceof FnDefExprImpl);
		FnDefExpr fd = (FnDefExpr) e;
		assertStringSame(fd.getName(), "foo");
		assertSame(fd.getArgs().size(), 1);

		assertStringSame(fd.getArgs().get(0).getName(), "a");
		Type t = fd.getArgs().get(0).getType();
		assertTrue(t instanceof PrimType);
		assertStringSame(((PrimType) t).getString(), "int");

		Expr ex = fd.getExpr();
		assertNotNull(ex);

	}

	@Test
	public void parseEnumStatementMulti() {

		Element elm = parseSpec("enum color = {red, blue, yellow}");
		assertTrue(elm instanceof EnumStatementImpl);
		EnumStatement e = (EnumStatement) elm;
		assertSame(e.getEnums().size(), 3);
		assertStringSame(e.getName(), "color");
		assertStringSame(e.getEnums().get(0).getName(), "red");
		assertStringSame(e.getEnums().get(1).getName(), "blue");
		assertStringSame(e.getEnums().get(2).getName(), "yellow");

	}

	@Test
	public void parseEnumStatementSingle() {

		Element elm = parseSpec("enum color = {red}");
		assertTrue(elm instanceof EnumStatementImpl);
		EnumStatement e = (EnumStatement) elm;
		assertSame(e.getEnums().size(), 1);
		assertStringSame(e.getName(), "color");
		assertStringSame(e.getEnums().get(0).getName(), "red");

	}

}