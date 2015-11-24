package com.rockwellcollins.atc.agree.analysis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;

public class AgreeCalendarUtils {

    static private String dfaName = null;

    static public Node getMNCalendar(String nodeName, int max, int min) {
        if (max < min || max < 1 || min < 1) {
            throw new AgreeException("Malformed quasi-synchronous constraint");
        }

        List<VarDecl> inputs = new ArrayList<>();
        inputs.add(new VarDecl("p", NamedType.BOOL));
        inputs.add(new VarDecl("q", NamedType.BOOL));

        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(new VarDecl("ok", NamedType.BOOL));

        List<VarDecl> locals = new ArrayList<>();
        List<Equation> eqs = new ArrayList<>();

        Expr pId = new IdExpr("p");
        Expr qId = new IdExpr("q");
        for (int i = 0; i < max; i++) {
            VarDecl countVar = new VarDecl("c_q_" + i, NamedType.INT);
            IdExpr curId = new IdExpr("c_q_" + i);
            IdExpr preId = new IdExpr("c_q_" + (i - 1));
            locals.add(countVar);

            Expr minExpr = new IntExpr(BigInteger.valueOf(min));
            Expr zeroExpr = new IntExpr(BigInteger.ZERO);
            Expr oneExpr = new IntExpr(BigInteger.ONE);

            Expr minThenPre = new UnaryExpr(UnaryOp.PRE, preId);
            minThenPre = new BinaryExpr(minExpr, BinaryOp.ARROW, minThenPre);

            Expr elseExpr = new UnaryExpr(UnaryOp.PRE, curId);
            elseExpr = new BinaryExpr(minExpr, BinaryOp.ARROW, elseExpr);

            Expr thenExpr = (i == 0) ? oneExpr : new BinaryExpr(minThenPre, BinaryOp.PLUS, oneExpr);
            Expr condExpr = new BinaryExpr(pId, BinaryOp.AND, qId);

            Expr expr = new IfThenElseExpr(condExpr, thenExpr, elseExpr);
            thenExpr = (i == 0) ? zeroExpr : minThenPre;
            condExpr = new BinaryExpr(pId, BinaryOp.AND, new UnaryExpr(UnaryOp.NOT, qId));

            expr = new IfThenElseExpr(condExpr, thenExpr, expr);
            thenExpr = new UnaryExpr(UnaryOp.PRE, curId);
            thenExpr = new BinaryExpr(minExpr, BinaryOp.ARROW, thenExpr);
            thenExpr = new BinaryExpr(thenExpr, BinaryOp.PLUS, oneExpr);

            condExpr = new BinaryExpr(qId, BinaryOp.AND, new UnaryExpr(UnaryOp.NOT, pId));

            expr = new IfThenElseExpr(condExpr, thenExpr, expr);

            eqs.add(new Equation(curId, expr));
        }

        Expr okExpr = new IdExpr("c_q_" + (max - 1));
        okExpr = new UnaryExpr(UnaryOp.PRE, okExpr);
        okExpr = new BinaryExpr(new IntExpr(BigInteger.valueOf(2 * min)), BinaryOp.ARROW, okExpr);
        okExpr = new BinaryExpr(okExpr, BinaryOp.GREATEREQUAL, new IntExpr(BigInteger.valueOf(min)));
        okExpr = new BinaryExpr(pId, BinaryOp.IMPLIES, okExpr);
        eqs.add(new Equation(new IdExpr("ok"), okExpr));

        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(eqs);
        return builder.build();

    }

    static public Node getDFANode(String name, int synchrony) {
        if (synchrony <= 0) {
            throw new AgreeException("Attempt to use quasi-synchrony of value: " + synchrony);
        }

        // VarDecl rVar = new VarDecl("_r",
        // new SubrangeIntType(BigInteger.valueOf(-synchrony),
        // BigInteger.valueOf(synchrony)));
        VarDecl rVar = new VarDecl("_r", NamedType.INT);
        IdExpr r = new IdExpr(rVar.id);

        VarDecl rIsBoundedVar = new VarDecl("_r_is_bounded", NamedType.BOOL);
        IdExpr rIsBounded = new IdExpr(rIsBoundedVar.id);

        VarDecl outVar = new VarDecl("_out", NamedType.BOOL);
        IdExpr out = new IdExpr(outVar.id);

        VarDecl clkVar0 = new VarDecl("_clk0", NamedType.BOOL);
        IdExpr p = new IdExpr(clkVar0.id);

        VarDecl clkVar1 = new VarDecl("_clk1", NamedType.BOOL);
        IdExpr q = new IdExpr(clkVar1.id);

        List<VarDecl> inputs = new ArrayList<>();
        inputs.add(clkVar0);
        inputs.add(clkVar1);

        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(outVar);

        List<VarDecl> locals = new ArrayList<>();
        locals.add(rVar);
        locals.add(rIsBoundedVar);

        Expr intZeroExpr = new IntExpr(BigInteger.ZERO);
        Expr intOneExpr = new IntExpr(BigInteger.ONE);
        Expr intNegOneExpr = new IntExpr(BigInteger.valueOf(-1));
        Expr intSyncValExpr = new IntExpr(BigInteger.valueOf(synchrony));
        Expr intNegSyncValxpr = new IntExpr(BigInteger.valueOf(-synchrony));

        // (0 -> pre r)
        Expr rPreExpr =
                new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, r));
        // (0 -> pre r) < 0
        Expr rPreLTExpr = new BinaryExpr(rPreExpr, BinaryOp.LESS, intZeroExpr);
        // (0 -> pre r) > 0
        Expr rPreGTExpr = new BinaryExpr(rPreExpr, BinaryOp.GREATER, intZeroExpr);
        // (0 -> pre r) + 1
        Expr rPrePlus = new BinaryExpr(rPreExpr, BinaryOp.PLUS, intOneExpr);
        // (0 -> pre r) - 1
        Expr rPreMinus = new BinaryExpr(rPreExpr, BinaryOp.MINUS, intOneExpr);
        // if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1)
        Expr ifExpr0 = new IfThenElseExpr(rPreLTExpr, intOneExpr, rPrePlus);
        // if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)
        Expr ifExpr1 = new IfThenElseExpr(rPreGTExpr, intNegOneExpr, rPreMinus);
        // if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)) else
        // (0 -> pre r)
        Expr ifExprClk1 = new IfThenElseExpr(q, ifExpr1, rPreExpr);
        // if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        // else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        // else (0 -> pre r);
        Expr ifExprClk0 = new IfThenElseExpr(p, ifExpr0, ifExprClk1);
        // if p and q then 0
        // else if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        // else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        // else (0 -> pre r);
        Expr rExpr = new IfThenElseExpr(new BinaryExpr(p, BinaryOp.AND, q), intZeroExpr, ifExprClk0);

        // ((0 -> pre r) >= 2 and p)
        Expr condExpr0 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.GREATEREQUAL, intSyncValExpr),
                BinaryOp.AND, p);
        // ((0 -> pre r) <= -2 and q)
        Expr condExpr1 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.LESSEQUAL, intNegSyncValxpr),
                BinaryOp.AND, q);
        // not (((0 -> pre r) >= 2 and p) or ((0 -> pre r) <= -2 and q))
        Expr outExpr = new UnaryExpr(UnaryOp.NOT, new BinaryExpr(condExpr0, BinaryOp.OR, condExpr1));

        // r <= 2 and r >= -2
        Expr rIsBoundedExpr = new BinaryExpr(new BinaryExpr(r, BinaryOp.LESSEQUAL, intSyncValExpr),
                BinaryOp.AND, new BinaryExpr(r, BinaryOp.GREATEREQUAL, intNegSyncValxpr));

        List<Equation> equations = new ArrayList<>();
        equations.add(new Equation(r, rExpr));
        equations.add(new Equation(rIsBounded, rIsBoundedExpr));
        equations.add(new Equation(out, outExpr));

        //TODO we used to have the subnode property in here, but it messes up kind2
        List<String> properties = new ArrayList<>();
        //properties.add(rIsBounded.id);

        NodeBuilder builder = new NodeBuilder(name);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(equations);
        builder.addProperties(properties);
        
        return builder.build();
    }

    public static Node getExplicitCalendarNode(String nodeName, List<IdExpr> calendar, List<Expr> clocks) {
        // filter the calendar if some clocks are not present
        List<IdExpr> filteredCalendar = new ArrayList<>();
        Map<String, List<Integer>> clockTickMap = new HashMap<>();

        for (IdExpr calId : calendar) {
            for (Expr clockExpr : clocks) {
                IdExpr clockId = (IdExpr) clockExpr;
                if (calId.id.equals(clockId.id)) {
                    filteredCalendar.add(clockId);
                    break;
                }
            }
        }

        int i = 0;
        for (IdExpr clockId : filteredCalendar) {
            List<Integer> ticks = clockTickMap.get(clockId.id);
            if (ticks == null) {
                ticks = new ArrayList<>();
                clockTickMap.put(clockId.id, ticks);
            }
            ticks.add(i++);
        }

        for (Expr clockExpr : clocks) {
            IdExpr clockId = (IdExpr) clockExpr;
            if (clockTickMap.get(clockId.id) == null) {
                throw new AgreeException(
                        "Clock Id '" + clockId.id + "' is not present in calendar statement");
            }
        }

        // add all of the clocks to to the inputs of the node
        List<VarDecl> inputs = new ArrayList<>();
        for (Expr clockExpr : clocks) {
            VarDecl input = new VarDecl(((IdExpr) clockExpr).id, NamedType.BOOL);
            inputs.add(input);
        }

        // the output is the variable asserting the calendar
        List<VarDecl> outputs = new ArrayList<>();
        IdExpr outputAssert = new IdExpr("__CALENDAR_ASSERTION");
        outputs.add(new VarDecl(outputAssert.id, NamedType.BOOL));

        // create a variable that counts through the calendar elements

        List<VarDecl> locals = new ArrayList<>();
        VarDecl clockCounterVar = new VarDecl("__CALANDER_COUNTER", NamedType.INT);
        locals.add(clockCounterVar);

        List<Equation> equations = new ArrayList<>();

        // create the expression for the counter variable
        IdExpr clockCountId = new IdExpr(clockCounterVar.id);
        IntExpr calendarSize = new IntExpr(BigInteger.valueOf(filteredCalendar.size() - 1));

        Expr preClockCount = new UnaryExpr(UnaryOp.PRE, clockCountId);
        Expr preLast = new BinaryExpr(preClockCount, BinaryOp.EQUAL, calendarSize);
        Expr prePlus = new BinaryExpr(preClockCount, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr ifClock = new IfThenElseExpr(preLast, new IntExpr(BigInteger.ZERO), prePlus);
        Expr clockArrow = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, ifClock);

        Equation clockCountEq = new Equation(clockCountId, clockArrow);
        equations.add(clockCountEq);

        // create constraints for which calendar element is ticking
        Expr calendarConstraint = new BoolExpr(true);
        for (Expr clockExpr : clocks) {
            IdExpr clockId = (IdExpr) clockExpr;
            List<Integer> ticks = clockTickMap.get(clockId.id);
            Expr clockTicking = new BoolExpr(false);
            for (Integer tick : ticks) {
                Expr clockIsTickValue = new BinaryExpr(clockCountId, BinaryOp.EQUAL,
                        new IntExpr(BigInteger.valueOf(tick.longValue())));
                clockTicking = new BinaryExpr(clockTicking, BinaryOp.OR, clockIsTickValue);
            }
            Expr ifExpr = new IfThenElseExpr(clockTicking, clockId, new UnaryExpr(UnaryOp.NOT, clockId));
            calendarConstraint = new BinaryExpr(calendarConstraint, BinaryOp.AND, ifExpr);
        }
        Equation outEq = new Equation(outputAssert, calendarConstraint);
        equations.add(outEq);
        
        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(equations);

        return builder.build();
    }

    static public Node getCalendarNode(String name, String dfaName, int numClks) {
        Expr nodeExpr = null;
        String clkVarPrefix = "_clk_";
        IdExpr outVar = new IdExpr("_out");

        // make the inputs and outputs
        List<VarDecl> inputs = new ArrayList<>();
        List<IdExpr> clks = new ArrayList<>();
        for (int i = 0; i < numClks; i++) {
            inputs.add(new VarDecl(clkVarPrefix + i, NamedType.BOOL));
            clks.add(new IdExpr(clkVarPrefix + i));
        }

        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(new VarDecl(outVar.id, NamedType.BOOL));

        for (int i = 0; i < clks.size() - 1; i++) {
            Expr clk0 = clks.get(i);
            for (int j = i + 1; j < clks.size(); j++) {
                Expr clk1 = clks.get(j);
                Expr dfaExpr = getDFAExpr(dfaName, clk0, clk1);
                if (nodeExpr == null) {
                    nodeExpr = dfaExpr;
                } else {
                    nodeExpr = new BinaryExpr(nodeExpr, BinaryOp.AND, dfaExpr);
                }
            }
        }

        Equation nodeEq = new Equation(outVar, nodeExpr);
        
        NodeBuilder builder = new NodeBuilder(name);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addEquations(Collections.singletonList(nodeEq));        
        
        return builder.build();
    }

    static public List<Equation> getAllClksHaveTicked(String name, String clkPref, List<Expr> clks) {

        Expr result = new BoolExpr(true);
        List<Equation> eqs = new ArrayList<>();

        int i = 0;
        for (Expr clk : clks) {
            Equation clkHasTicked = getClkHasTicked(new IdExpr(clkPref + i), clk);
            result = new BinaryExpr(result, BinaryOp.AND, clkHasTicked.lhs.get(0));
            eqs.add(clkHasTicked);
            i++;
        }
        eqs.add(new Equation(new IdExpr(name), result));
        return eqs;

    }

    static public Equation getClkHasTicked(IdExpr clkTickedId, Expr clkExpr) {

        // clkTickedID = clkExpr -> clkExpr or pre clkTickedId
        Expr tickExpr = new BinaryExpr(clkExpr, BinaryOp.OR, new UnaryExpr(UnaryOp.PRE, clkTickedId));

        return new Equation(clkTickedId, new BinaryExpr(clkExpr, BinaryOp.ARROW, tickExpr));
    }

    static private Expr getDFAExpr(String dfaName, Expr clk0, Expr clk1) {
        return new NodeCallExpr(dfaName, clk0, clk1);
    }

    public static Node queueCircleNode(String nodeName, Type type, int queueSize) {

        List<VarDecl> inputs = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<>();
        List<VarDecl> locals = new ArrayList<>();
        List<IdExpr> els = new ArrayList<>();
        List<Equation> eqs = new ArrayList<>();

        String elBase = "el";

        IdExpr elemIn = new IdExpr("el_in");
        IdExpr elemOut = new IdExpr("el_out");
        IdExpr insert = new IdExpr("insert");
        IdExpr remove = new IdExpr("remove");
        IdExpr output = new IdExpr("output");
        IdExpr input = new IdExpr("input");
        IdExpr numEls = new IdExpr("num_els");

        inputs.add(new VarDecl(input.id, type));
        inputs.add(new VarDecl(elemIn.id, NamedType.BOOL));
        inputs.add(new VarDecl(elemOut.id, NamedType.BOOL));
        outputs.add(new VarDecl(numEls.id, NamedType.INT));
        outputs.add(new VarDecl(output.id, type));
        locals.add(new VarDecl(insert.id, NamedType.INT));
        locals.add(new VarDecl(remove.id, NamedType.INT));

        for (int i = 0; i < queueSize; i++) {
            IdExpr el = new IdExpr(elBase + i);
            els.add(el);
            locals.add(new VarDecl(el.id, type));
        }

        // equations for insert
        Expr preInsert = new UnaryExpr(UnaryOp.PRE, insert);
        Expr preInsertMore = new BinaryExpr(preInsert, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr insertIf0 = new IfThenElseExpr(elemIn, preInsertMore, preInsert);
        Expr insertIfCond =
                new BinaryExpr(preInsert, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(queueSize - 1)));
        insertIfCond = new BinaryExpr(elemIn, BinaryOp.AND, insertIfCond);
        Expr insertIf1 = new IfThenElseExpr(insertIfCond, new IntExpr(BigInteger.ZERO), insertIf0);
        Expr insertIf2 =
                new IfThenElseExpr(elemIn, new IntExpr(BigInteger.ONE), new IntExpr(BigInteger.ZERO));
        Expr insertExpr = new BinaryExpr(insertIf2, BinaryOp.ARROW, insertIf1);

        Equation insertEq = new Equation(insert, insertExpr);
        eqs.add(insertEq);

        // equations for remove
        Expr preRemove = new UnaryExpr(UnaryOp.PRE, remove);
        Expr preRemoveMore = new BinaryExpr(preRemove, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr removeIf0 = new IfThenElseExpr(elemOut, preRemoveMore, preRemove);
        Expr removeIfCond =
                new BinaryExpr(preRemove, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(queueSize - 1)));
        removeIfCond = new BinaryExpr(elemOut, BinaryOp.AND, removeIfCond);
        Expr removeExpr = new IfThenElseExpr(removeIfCond, new IntExpr(BigInteger.ZERO), removeIf0);
        removeExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, removeExpr);

        Equation removeEq = new Equation(remove, removeExpr);
        eqs.add(removeEq);

        Expr preElemIn = new UnaryExpr(UnaryOp.PRE, elemIn);
        Expr preElemOut = new UnaryExpr(UnaryOp.PRE, elemOut);
        Expr preNumEls = new UnaryExpr(UnaryOp.PRE, numEls);
        Expr preNumElsMore = new BinaryExpr(preNumEls, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr preNumElsLessExpr = new BinaryExpr(preNumEls, BinaryOp.MINUS, new IntExpr(BigInteger.ONE));
        Expr numElsIf0 = new IfThenElseExpr(preElemIn, preNumElsMore, preNumEls);
        Expr numElsExpr = new IfThenElseExpr(preElemOut, preNumElsLessExpr, numElsIf0);
        numElsExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, numElsExpr);

        Equation numElsEq = new Equation(numEls, numElsExpr);
        eqs.add(numElsEq);

        // equation for the output

        Expr outputExpr = els.get(queueSize - 1);
        for (int i = 0; i < queueSize - 1; i++) {
            Expr cond = new BinaryExpr(preRemove, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(i)));
            outputExpr = new IfThenElseExpr(cond, els.get(i), outputExpr);
        }

        outputExpr = new BinaryExpr(els.get(0), BinaryOp.ARROW, outputExpr);
        Equation outputEq = new Equation(output, outputExpr);
        eqs.add(outputEq);

        // equations for each queue element
        for (int i = 0; i < queueSize; i++) {
            Expr preEl = new UnaryExpr(UnaryOp.PRE, els.get(i));
            Expr cond = new UnaryExpr(UnaryOp.PRE, insert);
            cond = new BinaryExpr(cond, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(i)));
            cond = new BinaryExpr(elemIn, BinaryOp.AND, cond);
            Expr elemIfExpr = new IfThenElseExpr(cond, input, preEl);
            Expr elExpr = new BinaryExpr(input, BinaryOp.ARROW, elemIfExpr);

            Equation elEq = new Equation(els.get(i), elExpr);
            eqs.add(elEq);
        }

        // queue properties:
        List<String> props = new ArrayList<>();
        // don't remove more than are present:
        // Expr propExpr0 = new BinaryExpr(preRemove, BinaryOp.EQUAL,
        // preInsert);
        // Expr propExpr1 = new BinaryExpr(remove, BinaryOp.EQUAL, preRemove);
        // Expr propImpl = new BinaryExpr(propExpr0, BinaryOp.IMPLIES,
        // propExpr1);
        // Expr propArrow = new BinaryExpr(remove, BinaryOp.LESSEQUAL, insert);
        // propArrow = new BinaryExpr(propArrow, BinaryOp.ARROW, propImpl);
        Expr propExpr = new BinaryExpr(numEls, BinaryOp.GREATEREQUAL, new IntExpr(BigInteger.ZERO));
        IdExpr propId0 = new IdExpr("__REMOVE_LTE_INSERT_" + nodeName);
        locals.add(new VarDecl(propId0.id, NamedType.BOOL));
        Equation propEq0 = new Equation(propId0, propExpr);
        eqs.add(propEq0);
        props.add(propId0.id);
        
        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(eqs);
        builder.addProperties(props);
        
        return builder.build();

    }

    public static Node queueNode(String nodeName, Type type, int queueSize) {

        List<VarDecl> inputs = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<>();
        List<VarDecl> locals = new ArrayList<>();
        List<IdExpr> els = new ArrayList<>();
        List<Equation> eqs = new ArrayList<>();

        String elBase = "el";

        IdExpr elemIn = new IdExpr("el_in");
        IdExpr elemOut = new IdExpr("el_out");
        IdExpr insert = new IdExpr("insert");
        IdExpr output = new IdExpr("output");
        IdExpr input = new IdExpr("input");
        IdExpr numEls = new IdExpr("num_els");

        inputs.add(new VarDecl(input.id, type));
        inputs.add(new VarDecl(elemIn.id, NamedType.BOOL));
        inputs.add(new VarDecl(elemOut.id, NamedType.BOOL));
        outputs.add(new VarDecl(numEls.id, NamedType.INT));
        outputs.add(new VarDecl(output.id, type));
        locals.add(new VarDecl(insert.id, NamedType.INT));

        // add an extra "dummy element" for handling too many inserts
        for (int i = 0; i < queueSize + 1; i++) {
            IdExpr el = new IdExpr(elBase + i);
            els.add(el);
            locals.add(new VarDecl(el.id, type));
        }

        // equation for insert
        Expr preElemIn = new UnaryExpr(UnaryOp.PRE, elemIn);
        Expr preElemOut = new UnaryExpr(UnaryOp.PRE, elemOut);
        Expr preInsert = new UnaryExpr(UnaryOp.PRE, insert);
        Expr preInsertMore = new BinaryExpr(preInsert, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr preInsertLess = new BinaryExpr(preInsert, BinaryOp.MINUS, new IntExpr(BigInteger.ONE));
        Expr insertIf0 = new IfThenElseExpr(preElemIn, preInsertMore, preInsert);
        Expr insertIf1 = new IfThenElseExpr(preElemOut, preInsertLess, insertIf0);
        Expr insertExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, insertIf1);

        Equation insertEq = new Equation(insert, insertExpr);
        eqs.add(insertEq);

        // equation for numEls
        Expr preNumEls = new UnaryExpr(UnaryOp.PRE, numEls);
        Expr preNumElsMore = new BinaryExpr(preNumEls, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        Expr preNumElsLessExpr = new BinaryExpr(preNumEls, BinaryOp.MINUS, new IntExpr(BigInteger.ONE));
        Expr numElsIf0 = new IfThenElseExpr(preElemIn, preNumElsMore, preNumEls);
        Expr numElsExpr = new IfThenElseExpr(preElemOut, preNumElsLessExpr, numElsIf0);
        numElsExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, numElsExpr);

        Equation numElsEq = new Equation(numEls, numElsExpr);
        eqs.add(numElsEq);

        // equation for the output
        Equation outputEq = new Equation(output, new IdExpr(elBase + 0));
        eqs.add(outputEq);

        // equations for each queue element
        Expr preInput = new UnaryExpr(UnaryOp.PRE, input);
        for (int i = 0; i < queueSize; i++) {
            Expr preEl = new UnaryExpr(UnaryOp.PRE, els.get(i));
            Expr cond = new UnaryExpr(UnaryOp.PRE, insert);
            cond = new BinaryExpr(cond, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(i)));
            cond = new BinaryExpr(preElemIn, BinaryOp.AND, cond);
            Expr elemIf0 = new IfThenElseExpr(cond, preInput, preEl);
            Expr elemIf1 = new IfThenElseExpr(preElemOut, els.get(i + 1), elemIf0);
            Expr elExpr = new BinaryExpr(input, BinaryOp.ARROW, elemIf1);

            Equation elEq = new Equation(els.get(i), elExpr);
            eqs.add(elEq);
        }
        // special case for the dummy element
        Equation elEq = new Equation(els.get(queueSize), input);
        eqs.add(elEq);

        // queue properties:
        List<String> props = new ArrayList<>();
        // don't remove more than are present:
        // Expr propExpr0 = new BinaryExpr(preRemove, BinaryOp.EQUAL,
        // preInsert);
        // Expr propExpr1 = new BinaryExpr(remove, BinaryOp.EQUAL, preRemove);
        // Expr propImpl = new BinaryExpr(propExpr0, BinaryOp.IMPLIES,
        // propExpr1);
        // Expr propArrow = new BinaryExpr(remove, BinaryOp.LESSEQUAL, insert);
        // propArrow = new BinaryExpr(propArrow, BinaryOp.ARROW, propImpl);
        Expr propExpr = new BinaryExpr(numEls, BinaryOp.GREATEREQUAL, new IntExpr(BigInteger.ZERO));
        IdExpr propId0 = new IdExpr("__REMOVE_LTE_INSERT_" + nodeName);
        locals.add(new VarDecl(propId0.id, NamedType.BOOL));
        Equation propEq0 = new Equation(propId0, propExpr);
        eqs.add(propEq0);
        props.add(propId0.id);

        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(eqs);
        builder.addProperties(props);
        
        return builder.build();

    }

    public static Node queueMultiplexNode(String nodeName, Type type, int numInputs) {

        List<VarDecl> inputs = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<>();
        List<VarDecl> locals = new ArrayList<>();
        List<IdExpr> clks = new ArrayList<>();
        List<IdExpr> ins = new ArrayList<>();

        List<Equation> eqs = new ArrayList<>();

        for (int i = 0; i < numInputs; i++) {
            IdExpr inId = new IdExpr("in" + i);
            IdExpr clkId = new IdExpr("out" + i);

            ins.add(inId);
            clks.add(clkId);
            inputs.add(new VarDecl(inId.id, type));
            inputs.add(new VarDecl(clkId.id, NamedType.BOOL));
        }

        IdExpr output = new IdExpr("output");
        outputs.add(new VarDecl(output.id, type));

        // the output expression
        Expr outExpr = ins.get(0); // just an arbitrary value
        for (int i = 0; i < numInputs; i++) {
            outExpr = new IfThenElseExpr(clks.get(i), ins.get(i), outExpr);
        }
        Equation outEq = new Equation(output, outExpr);
        eqs.add(outEq);

        NodeBuilder builder = new NodeBuilder(nodeName);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(eqs);
        
        return builder.build();

    }

    public static Expr getSingleTick(List<Expr> clocks) {

        Expr returnExpr = new BoolExpr(false);
        for (Expr clock0 : clocks) {
            Expr tickExpr = clock0;
            for (Expr clock1 : clocks) {
                if (clock0 != clock1) {
                    Expr notClock1 = new UnaryExpr(UnaryOp.NOT, clock1);
                    tickExpr = new BinaryExpr(tickExpr, BinaryOp.AND, notClock1);
                }
            }
            returnExpr = new BinaryExpr(tickExpr, BinaryOp.OR, returnExpr);
        }

        return returnExpr;
    }

}
