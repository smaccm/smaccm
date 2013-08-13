package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osate.aadl2.NamedElement;

public class KindExpr {

	private String lustreString = null;
	private List<String> varNames = null;
	private NamedElement namedEl = null;
	
	public KindExpr(String str){
		lustreString = str;
	}
	
	public KindExpr(NamedElement el){
		namedEl = el;
		lustreString = el.getName();
	}
	
	public KindExpr appendLeft(KindExpr expr){
		return appendLeft(expr.lustreString);
	}
	
	public KindExpr appendRight(KindExpr expr){
		return appendRight(expr.lustreString);
	}
	
	public KindExpr appendLeft(String str){
		lustreString = str + lustreString;
		return this;
	}
	
	public KindExpr appendRight(String str){
		lustreString = lustreString + str;
		return this;
	}
	
	// ********* Begin Get Methods ***********
	
	public NamedElement getNamedEl(){
		return namedEl;
	}
	
	public List<String> getVarNames(){
		return varNames;
	}
	
	public String getVarName() {
	  String result = "";
	  Iterator<String> stringIt = getVarNames().iterator();
	  assert(stringIt.hasNext());
	  while (stringIt.hasNext()) {
	    result += stringIt.next();
	    if (stringIt.hasNext()) {
	      result += ", ";
	    }
	  }
	  return result;
	}
	
	public String toString(){
		return lustreString;
	}
	
	// ********* Begin Set Methods ************
	
	public void setVarNames(List<String> strs){
		varNames = strs;
	}
	
	public void setVarName(String str) {
	  varNames = new ArrayList<String>();
	  varNames.add(str);
	}
	
	public void appendVarName(String str) {
	  if (varNames == null) {
	    varNames = new ArrayList<String>();
	  }
	  varNames.add(str);
	}
	
}
