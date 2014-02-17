package com.rockwellcollins.atc.agree.analysis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    static private String dfaName = null;
    
    static public Node getDFANode(String name, int synchrony){
        if(synchrony <= 0){
            throw new AgreeException("Attempt to use quasi-synchrony of value: "+synchrony);
        }
        
        
        dfaName = name;
        
        VarDecl rVar = new VarDecl("_r", 
                new SubrangeIntType(BigInteger.valueOf(-synchrony), 
                        BigInteger.valueOf(synchrony)));
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
        
        return new Node(dfaName, inputs, outputs, locals, equations, properties);
    }
    
    static public Node getCalendarNode(String name, int numClks){
        
        if(dfaName == null){
            throw new AgreeException("Each call to getCalendarNode must be preeceded by a call to getDFANode");
        }
        
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
                Expr dfaExpr = getDFAExpr(clk0, clk1);
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
    
    static private Expr getDFAExpr(Expr clk0, Expr clk1){
        return new NodeCallExpr(dfaName, clk0, clk1);
    }
    
    
    public static Node queueNode(String nodeName, Type type, int queueSize){
    	
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

		for(int i = 0 ; i < queueSize; i++){
			IdExpr el = new IdExpr(elBase+i);
			els.add(el);
			locals.add(new VarDecl(el.id, type));
		}
		
		//equations for insert
		Expr preInsert = new UnaryExpr(UnaryOp.PRE, insert);
		Expr preInsertMore = new BinaryExpr(preInsert, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
		Expr insertIf0 = new IfThenElseExpr(elemIn, preInsertMore, preInsert);
		Expr insertIfCond = new BinaryExpr(preInsert, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(queueSize-1)));
		insertIfCond = new BinaryExpr(elemIn, BinaryOp.AND, insertIfCond);
		Expr insertExpr = new IfThenElseExpr(insertIfCond, new IntExpr(BigInteger.ZERO), insertIf0);
		
		Equation insertEq = new Equation(insert, insertExpr);
		eqs.add(insertEq);
		
		//equations for remove
		Expr preRemove = new UnaryExpr(UnaryOp.PRE, remove);
		Expr preRemoveMore = new BinaryExpr(preRemove, BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
		Expr removeIf0 = new IfThenElseExpr(elemOut, preRemoveMore, preRemove);
		Expr removeIfCond = new BinaryExpr(preRemove, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(queueSize-1)));
		removeIfCond = new BinaryExpr(elemOut, BinaryOp.AND, removeIfCond);
		Expr removeExpr = new IfThenElseExpr(removeIfCond, new IntExpr(BigInteger.ZERO), removeIf0);
		
		Equation removeEq = new Equation(remove, removeExpr);
		eqs.add(removeEq);
		
		//equation for num_els
		Expr removeGtInsert = new BinaryExpr(remove, BinaryOp.GREATER, insert);
		Expr gtResult = new BinaryExpr(new IntExpr(BigInteger.valueOf(queueSize)), BinaryOp.MINUS, remove);
		gtResult = new BinaryExpr(gtResult, BinaryOp.PLUS, insert);
		Expr ltEqReslt = new BinaryExpr(insert, BinaryOp.MINUS, remove);
		Expr numElsExpr = new IfThenElseExpr(removeGtInsert, gtResult, ltEqReslt);
		
		Equation numElsEq = new Equation(numEls, numElsExpr);
		eqs.add(numElsEq);
		
		//equation for the output
		
		Expr outputExpr = els.get(queueSize-1);
		for(int i = 0; i < queueSize-1; i++){
			Expr cond = new BinaryExpr(preRemove, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(i)));
			outputExpr = new IfThenElseExpr(cond, els.get(i), outputExpr);
		}		

		outputExpr = new BinaryExpr(els.get(0), BinaryOp.ARROW, outputExpr);
		Equation outputEq = new Equation(output, outputExpr);
		eqs.add(outputEq);
		
		//equations for each queue element
		for(int i = 0; i < queueSize; i++){
			Expr preEl = new UnaryExpr(UnaryOp.PRE, els.get(i));
			Expr cond = new UnaryExpr(UnaryOp.PRE, insert);
			cond = new BinaryExpr(cond, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf(i)));
			cond = new BinaryExpr(elemIn, BinaryOp.AND, cond);
			Expr elemIfExpr = new IfThenElseExpr(cond, input, preEl);
			Expr elExpr = new BinaryExpr(input, BinaryOp.ARROW, elemIfExpr);
			
			Equation elEq = new Equation(els.get(i), elExpr);
			eqs.add(elEq);
		}
		
		return new Node(nodeName, inputs, outputs, locals, eqs);
    	
    }
    
    public static Node queueMultiplexNode(String nodeName, Type type, int numInputs){
    	
    	List<VarDecl> inputs = new ArrayList<>();
    	List<VarDecl> outputs = new ArrayList<>();
    	List<VarDecl> locals = new ArrayList<>();
    	List<IdExpr> clks = new ArrayList<>();
    	List<IdExpr> ins = new ArrayList<>();

		List<Equation> eqs = new ArrayList<>();
    	
    	for(int i = 0; i < numInputs; i++){
    		IdExpr inId = new IdExpr("in"+i);
    		IdExpr clkId = new IdExpr("out"+i);
    		
    		ins.add(inId);
    		clks.add(clkId);
    		inputs.add(new VarDecl(inId.id, type));
    		inputs.add(new VarDecl(clkId.id, NamedType.BOOL));
    	}
    	
    	IdExpr output = new IdExpr("output");
    	outputs.add(new VarDecl(output.id, type));
    	
    	//the output expression
    	Expr outExpr = ins.get(0); // just an arbitrary value
    	for(int i = 0; i < numInputs; i++){
    		outExpr = new IfThenElseExpr(clks.get(i), ins.get(i), outExpr);
    	}
    	Equation outEq = new Equation(output, outExpr);
    	eqs.add(outEq);
    	
		return new Node(nodeName, inputs, outputs, locals, eqs);

    }
    
}
