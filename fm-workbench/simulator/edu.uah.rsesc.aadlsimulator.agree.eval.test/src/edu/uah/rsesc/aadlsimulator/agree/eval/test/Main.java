package edu.uah.rsesc.aadlsimulator.agree.eval.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import edu.uah.rsesc.aadlsimulator.agree.eval.Evaluator;
import edu.uah.rsesc.aadlsimulator.agree.transformation.CreateSimulationGuarantee;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.Program;
import jkind.lustre.RealExpr;
import jkind.lustre.parsing.LustreLexer;
import jkind.lustre.parsing.LustreParser;
import jkind.lustre.parsing.LustreToAstVisitor;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.util.BigFraction;

public class Main {
	private static String INPUT_DIRECTORY = "input/";
	
	public static void main(String[] args) throws Exception {
		testInteger();
		testReal();
		testBoolean();
	}
		
	private static void testValue(final String id, final Evaluator evaluator, final Value expectedValue) {
		final Value value = evaluator.evalOrNull(id);
		final boolean valueMatches = Objects.equals(value, expectedValue);
		System.out.println((valueMatches ? "SUCCESS" : "FAILURE") + " : " + id + " : " + value);
	}
	
	private static Evaluator createEvaluator(final String lustreFilepath) throws IOException {
		final LustreLexer lexer = new LustreLexer(new ANTLRFileStream(lustreFilepath));
		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final LustreParser parser = new LustreParser(tokens);
		final Program program = new LustreToAstVisitor().program(parser.program());
		final Evaluator evaluator = new Evaluator(program);
		return new Evaluator(evaluator, Collections.singleton(new IdExpr(CreateSimulationGuarantee.SIMULATION_ASSERTIONS_ID)));
	}
	
	private static void testInteger() throws IOException {
		System.out.println("=============Integer Test=============");
		final Evaluator baseEvaluator = createEvaluator(INPUT_DIRECTORY + "symb_test_int.lus");
		final Evaluator evaluator = new Evaluator(baseEvaluator, Arrays.asList(
				new BinaryExpr(new IdExpr("in1"), BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(-10))),
				new BinaryExpr(new IdExpr("in2"), BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(10)))));
		
		testValue("in1", evaluator, new IntegerValue(BigInteger.valueOf(-10)));
		testValue("in2", evaluator, new IntegerValue(BigInteger.valueOf(10)));
		testValue("out1", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("out2", evaluator, new IntegerValue(BigInteger.valueOf(-15)));
		testValue("ss__a1[0]", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__a1[1]", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__a1[2]", evaluator, new IntegerValue(BigInteger.valueOf(-20)));
		testValue("ss__a2[0]", evaluator, new IntegerValue(BigInteger.valueOf(10)));
		testValue("ss__a2[1]", evaluator, new IntegerValue(BigInteger.valueOf(20)));
		testValue("ss__a2[2]", evaluator, new IntegerValue(BigInteger.valueOf(30)));
		testValue("ss__a3[0]", evaluator, new IntegerValue(BigInteger.valueOf(42)));
		testValue("ss__a3[1]", evaluator, new IntegerValue(BigInteger.valueOf(88)));
		testValue("ss__a3[2]", evaluator, new IntegerValue(BigInteger.valueOf(-20)));	
		testValue("ss__r1.x", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__r1.y", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__r1.z", evaluator, new IntegerValue(BigInteger.valueOf(42)));
		testValue("ss__r2.x", evaluator, new IntegerValue(BigInteger.valueOf(10)));
		testValue("ss__r2.y", evaluator, new IntegerValue(BigInteger.valueOf(20)));
		testValue("ss__r2.z", evaluator, new IntegerValue(BigInteger.valueOf(30)));
		testValue("ss__r3.x", evaluator, new IntegerValue(BigInteger.valueOf(42)));
		testValue("ss__r3.y", evaluator, new IntegerValue(BigInteger.valueOf(88)));
		testValue("ss__r3.z", evaluator, new IntegerValue(BigInteger.valueOf(42)));	
		testValue("ss__ra1[0].x", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__ra1[0].y", evaluator, new IntegerValue(BigInteger.valueOf(-5)));
		testValue("ss__ra1[0].z", evaluator, new IntegerValue(BigInteger.valueOf(42)));
		testValue("ss__ra1[1].x", evaluator, new IntegerValue(BigInteger.valueOf(10)));
		testValue("ss__ra1[1].y", evaluator, new IntegerValue(BigInteger.valueOf(20)));
		testValue("ss__ra1[1].z", evaluator, new IntegerValue(BigInteger.valueOf(30)));
	}
	
	private static void testReal() throws IOException {
		System.out.println("=============Real Test=============");
		final Evaluator baseEvaluator = createEvaluator(INPUT_DIRECTORY + "symb_test_real.lus");
		final Evaluator evaluator = new Evaluator(baseEvaluator, Arrays.asList(
			new BinaryExpr(new IdExpr("__SIM_PE___ASSUME0"), BinaryOp.EQUAL, new BoolExpr(true)),
			new BinaryExpr(new IdExpr("__SIM_PE___ASSUME1"), BinaryOp.EQUAL, new BoolExpr(true)),
			new BinaryExpr(new IdExpr("__SIM_PE__TOP__ss__TILDE__0__DOT____GUARANTEE0"), BinaryOp.EQUAL, new BoolExpr(true)),
			new BinaryExpr(new IdExpr("__SIM_PE__TOP__ss__TILDE__0__DOT____GUARANTEE1"), BinaryOp.EQUAL, new BoolExpr(true)),		
			new BinaryExpr(new IdExpr("in1"), BinaryOp.EQUAL, new RealExpr(BigDecimal.valueOf(60))),
			new BinaryExpr(new IdExpr("in2"), BinaryOp.EQUAL, new RealExpr(BigDecimal.valueOf(10)))));
		
		testValue("in1", evaluator, new RealValue(BigFraction.valueOf(BigDecimal.valueOf(60))));
		testValue("in2", evaluator, new RealValue(BigFraction.valueOf(BigDecimal.valueOf(10))));
		testValue("out1", evaluator, new RealValue(BigFraction.valueOf(BigDecimal.valueOf(30))));
		testValue("out2", evaluator, new RealValue(BigFraction.valueOf(BigDecimal.valueOf(20))));
	}
	
	private static void testBoolean() throws IOException {
		System.out.println("=============Boolean Test=============");
		final Evaluator baseEvaluator = createEvaluator(INPUT_DIRECTORY + "symb_test_bool.lus");
		final Evaluator evaluator = new Evaluator(baseEvaluator, Arrays.asList(
			new BinaryExpr(new IdExpr("__SIM_PE__TOP__ss__TILDE__0__DOT____GUARANTEE0"), BinaryOp.EQUAL, new BoolExpr(true)),
			new BinaryExpr(new IdExpr("__SIM_PE__TOP__ss__TILDE__0__DOT____GUARANTEE1"), BinaryOp.EQUAL, new BoolExpr(true)),		
			new BinaryExpr(new IdExpr("in1"), BinaryOp.EQUAL, new BoolExpr(true)),
			new BinaryExpr(new IdExpr("in2"), BinaryOp.EQUAL, new BoolExpr(true))));
		
		testValue("in1", evaluator, BooleanValue.TRUE);
		testValue("in2", evaluator, BooleanValue.TRUE);
		testValue("out1", evaluator, BooleanValue.TRUE);
		testValue("out2", evaluator, BooleanValue.TRUE);
	}
}
