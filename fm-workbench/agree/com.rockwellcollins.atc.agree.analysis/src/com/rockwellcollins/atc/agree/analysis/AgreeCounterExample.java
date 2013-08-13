package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgreeCounterExample {
	
	private String propName = null;
	private Map<String, List<String>> varVals = new HashMap<>();
	
	public AgreeCounterExample(String str){
		propName = str;
	}
	
	
	public void addVarVal(String varName, int k, String val){
		
		List<String> vals = varVals.get(varName);
		varVals.remove(varName);
		if(vals == null){
			vals = new ArrayList<String>();
		}
		
		for(int i = vals.size(); i <= k; i++){
			vals.add(i, null);
		}
		vals.set(k, val);
		varVals.put(varName, vals);
	}
	
	public String getPropName(){
		return propName;
	}
	
	public Map<String, List<String>> getValMap(){
		return varVals;
	}
	

}
