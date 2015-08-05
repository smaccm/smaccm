package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {
	
	private Map<String, String> explicitRenames = new HashMap<>();
	private Map<String, EObject> refMap;
	
	public AgreeRenaming(Map<String, EObject> refMap){
        this.refMap = refMap;
	}

	public void addRenamings(AgreeRenaming renaming){
		this.explicitRenames.putAll(renaming.explicitRenames);
		this.refMap.putAll(renaming.refMap);
	}

	public void addExplicitRename(String oldName, String newName){
		this.explicitRenames.put(oldName, newName);
	}
	
    public String forceRename(String original){

        //magic to remove the prefix
        String newName;
        newName = original.replaceAll("___Nod([^_]_?)*_", "");
        newName = newName.replace("~condact", "");  
        newName = newName.replaceAll("~[0-9]*", "");
        newName = newName.replace("__", ".");
        
       
        return newName;
        
    }
	
	@Override
	public String rename(String original) {
		
		String newName = this.explicitRenames.get(original);
		if(newName != null){
			return newName;
		}
		newName = forceRename(original);
		
		if(findBestReference(original) == null){
		    if(original.equals("%REALIZABLE")){
		        return "Realizability Result";
		    }else if(original.contains("__nodeLemma")){
		    	return newName;
		    }
		    return null;
		}
		
		return newName;
		
	}
	
    private EObject findBestReference(String refStr){
        
        EObject ref = null;
        refStr = refStr.replace(".", "__");
        while(ref == null && refStr != null && !refStr.equals("")){
            ref = refMap.get(refStr);
            int index = refStr.lastIndexOf("__");
            if(index == -1){
                break;
            }
            refStr = refStr.substring(0, index);
        }

        return ref;
    }
	
	
	
	
}
