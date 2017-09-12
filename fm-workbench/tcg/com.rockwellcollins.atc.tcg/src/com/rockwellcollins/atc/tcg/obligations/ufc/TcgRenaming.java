/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.tcg.obligations.ufc;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.tcg.lustre.visitors.GenerateUfcObligationsVisitor;

import jkind.api.results.Renaming;

/*
 * 
 * Some context: 
 * 	agreeRenaming maps from lustre ids to Agree ids
 * 	agreeRefMap maps from Agree ids to EObjects in the Eclipse editor
 *  testPropNameToLustreProp maps from added test obligation ids to Lustre guarantee ids
 *  testPropNameToTestedExpr maps from test obligation ids to expressions under test.
 * 
 */

public class TcgRenaming extends Renaming {

	AgreeRenaming agreeRenaming; 
	Map<String, EObject> agreeRefMap;
	Map<String, EObject> tcgRefMap = new HashMap<>();
	HashMap<String, String> testPropNameToLustreProp = new HashMap<>();
	HashMap<String, String> testPropNameToTestedExpr = new HashMap<>();
	static final boolean Debug = true;
	
	public TcgRenaming(AgreeRenaming agreeRenaming, Map<String, EObject> refMap) {
		this.agreeRenaming = agreeRenaming;
		this.agreeRefMap = refMap;
	}
	
	/*
	public Map<String, EObject> constructTcgRefMap() {

		Map<String, EObject> tcgRefMap = new HashMap<>();
		for (Map.Entry<String, String> elem: testPropNameToLustreProp.entrySet()) {
			String lustrePropKey = agreeName(elem.getKey());
			EObject agreeRefMapVal = agreeRefMap.get(lustrePropKey);
			if (Debug) {
				System.out.println("tcgRefMap: " + rename(elem.getKey()) + " -> " + agreeRefMapVal);
			}
			tcgRefMap.put(rename(elem.getKey()), agreeRefMapVal);
		}
		return tcgRefMap;
	}
	*/
	
	public void addRenaming(String testPropName, String lustrePropName, String testedExprString) {
		testPropNameToLustreProp.put(testPropName, lustrePropName);
		testPropNameToTestedExpr.put(testPropName, testedExprString);
	
		// for tcgRefMap
		String lustrePropKey = agreeRenaming.rename(lustrePropName);
		EObject agreeRefMapVal = agreeRefMap.get(lustrePropKey);
		tcgRefMap.put(rename(testPropName), agreeRefMapVal);
	}

	public boolean isPropertyName(String name) { 
		return testPropNameToLustreProp.containsKey(name); 
	}
	
	public String agreeName(String tcgName) {
		String lustrePropName = testPropNameToLustreProp.get(tcgName);
		String agreePropName = agreeRenaming.rename(lustrePropName);
		return agreePropName;
	}

	public String conditionName(String tcgName) {
		return testPropNameToTestedExpr.get(tcgName);
	}
	@Override
	public String rename(String original) {

		String result = null; 
		if (isPropertyName(original)) {
			// Only want the test case number
			String testNumber = original.replace(GenerateUfcObligationsVisitor.TRAP_PROP_PREFIX, "");
			result = "Test Case " + testNumber + " ["+ agreeName(original) + "] for condition [" + conditionName(original) +  "]";
		} 
		else {
			result = agreeRenaming.rename(original);
		}
		return result;
	}

	public EObject mapTcgToEObject(String tcgName) {
		return tcgRefMap.get(tcgName);
	}
	
	public Map<String, EObject> getTcgRefMap() {
		return tcgRefMap;
	}
}
