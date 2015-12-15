package com.rockwellcollins.atc.agree.scoping;

import org.eclipse.xtext.scoping.IScope;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.Type;

public class RecordExprScoper {
	
	public static IScope getScope(Expr expr){
		
		if(expr instanceof BinaryExpr){
			return getScope((BinaryExpr)expr);
		}else if(expr instanceof PreExpr){
			return getScope((PreExpr)expr);
		}else if(expr instanceof IfThenElseExpr){
			return getScope((IfThenElseExpr)expr);
		}else if(expr instanceof RecordUpdateExpr){
			return getScope((RecordUpdateExpr)expr);
		}else if(expr instanceof NestedDotID){
			return getScope((NestedDotID)expr);
		}else if(expr instanceof RecordExpr){
			return getScope((RecordExpr)expr);
		}
		
		return IScope.NULLSCOPE;
	}

	public static IScope getScope(RecordExpr expr){
		return getScope(expr.getRecord());
	}
	
	public static IScope getScope(BinaryExpr binExpr){
		
		//just assume its an arrow expr, the validiator
		//will throw an error otherwise.  Just assume
		//that the lhs and rhs times match as well
		return getScope(binExpr.getLeft());
	}
	
	public static IScope getScope(RecordUpdateExpr upExpr){
		return getScope(upExpr.getRecord());
	}

	public static IScope getScope(PreExpr preExpr){
		return getScope(preExpr.getExpr());
	}

	public static IScope getScope(IfThenElseExpr iteExpr){
		//just assume the then and else types match
		//the validator will check this
		return getScope(iteExpr.getB());
	}
	
	public static IScope getScope(NestedDotID nestExpr){
		
    	while(nestExpr.getSub() != null){
    		nestExpr = nestExpr.getSub();
    	}
    	NamedElement recStatement = nestExpr.getBase();
    	if(recStatement instanceof Arg){
    		Type type = ((Arg) recStatement).getType();
    		if(type instanceof RecordType){
    			nestExpr = ((RecordType) type).getRecord();
    			while(nestExpr.getSub() != null){
    	    		nestExpr = nestExpr.getSub();
    	    	}
    			return AgreeScopeProvider.getRecordComponents(nestExpr.getBase());
    		}
    	}else if(recStatement instanceof DataPort){
    		DataSubcomponentType dataClass = ((DataPort) recStatement).getDataFeatureClassifier();
    		if(dataClass instanceof DataImplementation){
    			return AgreeScopeProvider.getRecordComponents(dataClass);
    		}
    	}else if(recStatement instanceof EventDataPort){
    		DataSubcomponentType dataClass = ((EventDataPort) recStatement).getDataFeatureClassifier();
    		if(dataClass instanceof DataImplementation){
    			return AgreeScopeProvider.getRecordComponents(dataClass);
    		}
    	}else if(recStatement instanceof RecordDefExpr){
    	    return AgreeScopeProvider.getRecordComponents(recStatement);
    	}
    	return IScope.NULLSCOPE;
	}
	
}
