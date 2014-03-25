package com.rockwellcollins.atc.agree.validation;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;

public class FnCallRecursionHelper extends AgreeSwitch<Expr>{
    
    public LinkedList<CallDef> visited;
    public List<LinkedList<CallDef>> loops;
    
    public void doSwitchPreserveVisited(Expr expr){
    	LinkedList<CallDef> copyOfVisited = new LinkedList<>(visited);
    	doSwitch(expr);
        visited = copyOfVisited;
    }
    
    public FnCallRecursionHelper(){
        visited = new LinkedList<>();
        loops = new ArrayList<>();
    }

    @Override
    public Expr caseBinaryExpr(BinaryExpr object) {
        doSwitchPreserveVisited(object.getLeft());
        doSwitchPreserveVisited(object.getRight());
        return null;
    }

    @Override
    public Expr caseUnaryExpr(UnaryExpr object) {
        doSwitchPreserveVisited(object.getExpr());
        return null;
    }

    @Override
    public Expr caseIfThenElseExpr(IfThenElseExpr object) {
        doSwitchPreserveVisited(object.getC());
        doSwitchPreserveVisited(object.getB());
        doSwitchPreserveVisited(object.getA());
        return null;
    }

    @Override
    public Expr casePrevExpr(PrevExpr object) {
        doSwitchPreserveVisited(object.getDelay());
        doSwitchPreserveVisited(object.getInit());
        return null;
    }

    @Override
    public Expr casePreExpr(PreExpr object) {
        doSwitchPreserveVisited(object.getExpr());
        return null;
    }

    @Override
    public Expr caseNodeBodyExpr(NodeBodyExpr object){
        
        for(NodeStmt stmt : object.getStmts()){
            doSwitch(stmt);
        }
        return null;
    }
    
    @Override
    public Expr caseNodeEq(NodeEq object){
        doSwitchPreserveVisited(object.getExpr());
        return null;
    }
    
    @Override
    public Expr caseNodeLemma(NodeLemma object){
        doSwitchPreserveVisited(object.getExpr());
        return null;
    }
    
    @Override
    public Expr caseNodeDefExpr(NodeDefExpr object){
        
        if(visited.contains(object)){
            LinkedList<CallDef> loop = new LinkedList<CallDef>(visited);
            loop.push(object);
            loops.add(loop);
        }else{
            visited.push(object);
            doSwitch(object.getNodeBody());
        }
        
        return null;
    }
    
    @Override
    public Expr caseFnDefExpr(FnDefExpr object){
        
        if(visited.contains(object)){
            LinkedList<CallDef> loop = new LinkedList<CallDef>(visited);
            loop.push(object);
            loops.add(loop);
        }else{
            visited.push(object);
            doSwitchPreserveVisited(object.getExpr());
        }
        return null;
    }
    
    @Override
    public Expr caseFnCallExpr(FnCallExpr object) {
        NestedDotID id = object.getFn();
        
        while(id.getSub() != null){
            id = id.getSub();
        }
        CallDef callDef = null;
        
        try{
            callDef = (CallDef)id.getBase();
        }catch(ClassCastException e){
            return null;
        }
        
        doSwitch(callDef);
        return null;
    }


}
