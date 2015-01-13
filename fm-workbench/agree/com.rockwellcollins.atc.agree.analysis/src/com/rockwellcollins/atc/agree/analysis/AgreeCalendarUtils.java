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
import jkind.lustre.SubrangeIntType;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

public class AgreeCalendarUtils {
    
    static public Node getDFANode(String name, int synchrony){
        if(synchrony <= 0){
            throw new AgreeException("Attempt to use quasi-synchrony of value: "+synchrony);
        }
        
        //VarDecl rVar = new VarDecl("_r", 
        //        new SubrangeIntType(BigInteger.valueOf(-synchrony), 
        //                BigInteger.valueOf(synchrony)));
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
        
        //(0 -> pre r)
        Expr rPreExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, r));
        //(0 -> pre r) < 0
        Expr rPreLTExpr = new BinaryExpr(rPreExpr, BinaryOp.LESS, intZeroExpr);
        //(0 -> pre r) > 0
        Expr rPreGTExpr = new BinaryExpr(rPreExpr, BinaryOp.GREATER, intZeroExpr);
        //(0 -> pre r) + 1
        Expr rPrePlus = new BinaryExpr(rPreExpr, BinaryOp.PLUS, intOneExpr);
        //(0 -> pre r) - 1
        Expr rPreMinus = new BinaryExpr(rPreExpr, BinaryOp.MINUS, intOneExpr);        
        //if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1)
        Expr ifExpr0 = new IfThenElseExpr(rPreLTExpr, intOneExpr, rPrePlus);
        //if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)
        Expr ifExpr1 = new IfThenElseExpr(rPreGTExpr, intNegOneExpr, rPreMinus);
        //if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)) else (0 -> pre r)
        Expr ifExprClk1 = new IfThenElseExpr(q, ifExpr1, rPreExpr);
        //if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        //else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        //else (0 -> pre r);
        Expr ifExprClk0 = new IfThenElseExpr(p, ifExpr0, ifExprClk1);
        //if p and q then 0 
        //else if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        //else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        //else (0 -> pre r);
        Expr rExpr = new IfThenElseExpr(new BinaryExpr(p, BinaryOp.AND, q), intZeroExpr, ifExprClk0);
        
        //((0 -> pre r) >= 2 and p)
        Expr condExpr0 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.GREATEREQUAL, intSyncValExpr), BinaryOp.AND, p);
        //((0 -> pre r) <= -2 and q)
        Expr condExpr1 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.LESSEQUAL, intNegSyncValxpr), BinaryOp.AND, q);
        //not (((0 -> pre r) >= 2 and p) or ((0 -> pre r) <= -2 and q))
        Expr outExpr = new UnaryExpr(UnaryOp.NOT, new BinaryExpr(condExpr0, BinaryOp.OR, condExpr1));
        
        //r <= 2 and r >= -2
        Expr rIsBoundedExpr = new BinaryExpr(new BinaryExpr(r, BinaryOp.LESSEQUAL, intSyncValExpr),
                BinaryOp.AND,
                new BinaryExpr(r, BinaryOp.GREATEREQUAL, intNegSyncValxpr));
        
        List<Equation> equations = new ArrayList<>();
        equations.add(new Equation(r, rExpr));
        equations.add(new Equation(rIsBounded, rIsBoundedExpr));
        equations.add(new Equation(out, outExpr));
        
        List<String> properties = new ArrayList<>();
        properties.add(rIsBounded.id);
        
        return new Node(name, inputs, outputs, locals, equations, properties);
    }
    
    public static Node getExplicitCalendarNode(String nodeName, List<IdExpr> calendar, List<Expr> clocks) {
		//filter the calendar if some clocks are not present
		List<IdExpr> filteredCalendar = new ArrayList<>();
		Map<String, List<Integer>> clockTickMap = new HashMap<>();

		for(IdExpr calId : calendar){
			for(Expr clockExpr : clocks){
				IdExpr clockId = (IdExpr)clockExpr;
				if(calId.id.equals(clockId.id)){
					filteredCalendar.add(clockId);
					break;
				}
			}
		}

		int i = 0;
		for(IdExpr clockId : filteredCalendar){
			List<Integer> ticks = clockTickMap.get(clockId.id);
			if(ticks == null){
				ticks = new ArrayList<>();
				clockTickMap.put(clockId.id, ticks);
			}
			ticks.add(i++);
		}
		
		for(Expr clockExpr : clocks){
			IdExpr clockId = (IdExpr)clockExpr;
			if(clockTickMap.get(clockId.id) == null){
				throw new AgreeException("Clock Id '"+clockId.id+"' is not present in calendar statement");
			}
		}
		
		//add all of the clocks to to the inputs of the node
		List<VarDecl> inputs = new ArrayList<>();
		for(Expr clockExpr : clocks){
			VarDecl input = new VarDecl(((IdExpr)clockExpr).id, NamedType.BOOL);
			inputs.add(input);
		}
		
		//the output is the variable asserting the calendar
		List<VarDecl> outputs = new ArrayList<>();
		IdExpr outputAssert = new IdExpr("__CALENDAR_ASSERTION");
		outputs.add(new VarDecl(outputAssert.id, NamedType.BOOL));

		//create a variable that counts through the calendar elements

		List<VarDecl> locals = new ArrayList<>();
		VarDecl clockCounterVar = new VarDecl("__CALANDER_COUNTER", NamedType.INT);
		locals.add(clockCounterVar);
		
		List<Equation> equations = new ArrayList<>();
		
		//create the expression for the counter variable
		IdExpr clockCountId = new IdExpr(clockCounterVar.id);
		IntExpr calendarSize = new IntExpr(BigInteger.valueOf(filteredCalendar.size()-1));
		
		Expr preClockCount = new UnaryExpr(UnaryOp.PRE, clockCountId);
		Expr preLast = new BinaryExpr(preClockCount, BinaryOp.EQUAL, calendarSize);
		Expr prePlus = new BinaryExpr(preClockCount, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
		Expr ifClock = new IfThenElseExpr(preLast, new IntExpr(BigInteger.ZERO), prePlus);
		Expr clockArrow = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, ifClock);
		
		Equation clockCountEq = new Equation(clockCountId, clockArrow);
		equations.add(clockCountEq);
		
		//create constraints for which calendar element is ticking
		Expr calendarConstraint = new BoolExpr(true);
		for(Expr clockExpr : clocks){
			IdExpr clockId = (IdExpr)clockExpr;
			List<Integer> ticks = clockTickMap.get(clockId.id);
			Expr clockTicking = new BoolExpr(false);
			for(Integer tick : ticks){
				Expr clockIsTickValue = new BinaryExpr(clockCountId, 
						BinaryOp.EQUAL, 
						new IntExpr(BigInteger.valueOf(tick.longValue())));
				clockTicking = new BinaryExpr(clockTicking, BinaryOp.OR, clockIsTickValue);
			}
			Expr ifExpr = new IfThenElseExpr(clockTicking, clockId, new UnaryExpr(UnaryOp.NOT, clockId));
			calendarConstraint = new BinaryExpr(calendarConstraint, BinaryOp.AND, ifExpr);
		}
		Equation outEq = new Equation(outputAssert, calendarConstraint);
		equations.add(outEq);
		
		return new Node(nodeName, inputs, outputs, locals, equations);
	}
    
    static public Node getCalendarNode(String name, String dfaName, int numClks){
        Node calendarNode;
        Expr nodeExpr = null;
        String clkVarPrefix = "_clk_";
        IdExpr outVar = new IdExpr("_out");
        
        //make the inputs and outputs
        List<VarDecl> inputs = new ArrayList<>();
        List<IdExpr> clks = new ArrayList<>();
        for(int i = 0; i < numClks; i++){
            inputs.add(new VarDecl(clkVarPrefix+i, NamedType.BOOL));
            clks.add(new IdExpr(clkVarPrefix+i));
        }
  
        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(new VarDecl(outVar.id, NamedType.BOOL));
        
        for(int i = 0; i < clks.size()-1; i++){
            Expr clk0 = clks.get(i);
            for(int j = i+1; j < clks.size(); j++){
                Expr clk1 = clks.get(j);
                Expr dfaExpr = getDFAExpr(dfaName, clk0, clk1);
                if(nodeExpr == null){
                    nodeExpr = dfaExpr;
                }else{
                    nodeExpr = new BinaryExpr(nodeExpr, BinaryOp.AND, dfaExpr);
                }
            }
        }

        Equation nodeEq = new Equation(outVar, nodeExpr);
        calendarNode = new Node(name, inputs, outputs, new ArrayList<VarDecl>(), Collections.singletonList(nodeEq));
        
        dfaName = null;
        
        return calendarNode;
    }
    
    static public List<Equation> getAllClksHaveTicked(String name, String clkPref, List<Expr> clks){
        
        Expr result = new BoolExpr(true);
        List<Equation> eqs = new ArrayList<>();
        
        int i = 0;
        for(Expr clk : clks){
            Equation clkHasTicked = getClkHasTicked(new IdExpr(clkPref+i), clk);
            result = new BinaryExpr(result, BinaryOp.AND, clkHasTicked.lhs.get(0));
            eqs.add(clkHasTicked);
            i++;
        }
        eqs.add(new Equation(new IdExpr(name), result));
        return eqs;
        
    }
    
    static public Equation getClkHasTicked(IdExpr clkTickedId, Expr clkExpr){
        
        // clkTickedID = clkExpr -> clkExpr or pre clkTickedId
        Expr tickExpr = new BinaryExpr(clkExpr, BinaryOp.OR, new UnaryExpr(UnaryOp.PRE, clkTickedId));
        
        return new Equation(clkTickedId, new BinaryExpr(clkExpr, BinaryOp.ARROW, tickExpr));
    }
    
    static private Expr getDFAExpr(String dfaName, Expr clk0, Expr clk1){
        return new NodeCallExpr(dfaName, clk0, clk1);
    }

	public static Expr getSingleTick(List<Expr> clocks) {
		
		Expr returnExpr = new BoolExpr(false);
		for(Expr clock0 : clocks){
			Expr tickExpr = clock0;
			for(Expr clock1: clocks){
				if(clock0 != clock1){
					Expr notClock1 = new UnaryExpr(UnaryOp.NOT, clock1);
					tickExpr = new BinaryExpr(tickExpr, BinaryOp.AND, notClock1);
				}
			}
			returnExpr = new BinaryExpr(tickExpr, BinaryOp.OR, returnExpr);
		}
		
		return returnExpr;
	}
    
}
