package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.IdExpr;

public class AgreeVarDecl implements Comparable<AgreeVarDecl> {
    public String jKindStr = null;
    public String aadlStr = null;
    public String type = null;

    
    public AgreeVarDecl(){}
    
    public AgreeVarDecl(String jKindStr, String aadlStr, String type){
    	this.jKindStr = jKindStr;
    	this.aadlStr = aadlStr;
    	this.type = type;
    }
    
    @Override
    public int hashCode() {
        return jKindStr.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        
        if(o instanceof IdExpr){
            return ((IdExpr)o).id.equals(jKindStr);
        }
        
        if (o instanceof AgreeVarDecl) {
            return ((AgreeVarDecl) o).jKindStr.equals(jKindStr);
        }
        return false;
        
    }

    @Override
    public int compareTo(AgreeVarDecl o) {
        return jKindStr.compareTo(o.jKindStr);
    }

}