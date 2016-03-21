package com.rockwellcollins.atc.agree.analysis;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.handlers.VerifyHandler;
import com.rockwellcollins.atc.agree.analysis.handlers.VerifyMonolithicHandler;

public class AgreeSupportRenaming extends AgreeRenaming {

	public AgreeSupportRenaming(Map<String, EObject> refMap) {
		super(refMap);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public String rename(String original) {
		
		
		String renamed = super.explicitRenames.get(original);
        if (renamed != null) {
        	return renamed;
        }
        
        String newName = original.replaceAll("___Nod([^_]_?)*_", "");
        newName = newName.replaceAll("~condact", "");
        newName = newName.replaceAll("~[0-9]*", "");
        
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
            return renamed;
        }
        
        newName = newName.replaceAll("_TOP__", "");
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
            return renamed;
        }
        
       //the following is special for kind 2 contracts
        newName = newName.replaceAll("guarantee\\[.*?\\]", "");
        newName = newName.replace("__", ".");
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
            return renamed;
        }
        
        return renamed;
    }

}
