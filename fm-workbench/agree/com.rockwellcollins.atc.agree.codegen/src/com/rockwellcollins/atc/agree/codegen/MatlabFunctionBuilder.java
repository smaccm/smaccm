package com.rockwellcollins.atc.agree.codegen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.util.Pair;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection.ConnectionType;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;


public class MatlabFunctionBuilder {

    protected static final String guarSuffix = "__GUARANTEE";
    protected static final String assumeSuffix = "__ASSUME";
    protected static final String lemmaSuffix = "__LEMMA";

  
    public static void createMatlabFunction (AgreeProgram agreeProgram) {

        AgreeNode topAgreeNode = agreeProgram.topNode;
        int index = 0;
        boolean firstVar = true;

        for (AgreeNode agreeNode : topAgreeNode.subNodes) {
        	index = 0;
        	firstVar = true;
        	
        	/*
        	 * println “function check_"+component.id+"_properties"+
        	 * ”(“+all inputs and outputs in the component AGREE spec+”)”
        	 * println “result = (“+guarantee statement (replace “=” with “==”) +”);”
        	 * println “sldv.prove(result);”
        	 * println “sldv.assume(assumption statement);”
        	 */
        	
        	/*
        	 * function check_A_sub_properties+(Input, Output, result0 = ((Output < (2 * Input)));
sldv.prove(result0);
assumption0 = ((Input < 20));
sldv.assume(assumption0);
function check_B_sub_properties+(Input, Output, result0 = ((Output < (Input + 15)));
sldv.prove(result0);
assumption0 = ((Input < 20));
sldv.assume(assumption0);
function check_C_sub_properties+(Input1, Input2, Output, result0 = ((Output = (Input1 + Input2)));
sldv.prove(result0);
        	 */
        	
        	System.out.print("function check_"+agreeNode.id+"_properties (");
        	
            for (AgreeVar var : agreeNode.inputs) {
            	if(!firstVar)
            	{            	
            		System.out.print(", ");
            	}
            	firstVar = false;
                //add inputs
             	System.out.print(var.id);
             }
            
            for (AgreeVar var : agreeNode.outputs) {
            	System.out.print(", ");
             	System.out.print(var.id);
             }
            System.out.println(")");
            
            for (AgreeStatement statement : agreeNode.guarantees) {
            	String guaranteeStr = statement.expr.toString();
            	Pattern pattern = Pattern.compile("([^><!]{1})(={1})([^><!]{1})");
            	Matcher matcher = pattern.matcher(guaranteeStr);
            	String guaranteeStrNew = matcher.replaceAll("$1==$3");
            	String guaranteeName = "guarantee" + index++;
            	System.out.println(guaranteeName +" = "+guaranteeStrNew+";");
            	System.out.println("sldv.prove("+guaranteeName+");");
            }
            
        	index = 0;
        	for (AgreeStatement statement : agreeNode.assumptions) {
        		String assuptionName = "assumption" + index++;
            	//add statement.expr
            	System.out.println(assuptionName +" = ("+statement.expr+");");
            	System.out.println("sldv.assume("+assuptionName+");");
            }     
        	System.out.println();
        }
    }
}
