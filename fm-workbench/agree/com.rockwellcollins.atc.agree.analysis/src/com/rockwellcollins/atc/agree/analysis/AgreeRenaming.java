package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.Map;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {
	
	private String prefix;
	private Map<String, String> explicitRenames = new HashMap<String, String>();

	public void addExplicitRename(String oldName, String newName){
		this.explicitRenames.put(oldName, newName);
	}
	
	@Override
	public String rename(String original) {
		
		String newName = this.explicitRenames.get(original);
		if(newName != null){
			return newName;
		}
		return original;
		
		//return original;
		
		
//		if(original.contains("~")){
//			return null;
//		}
//		
//		String renamed = explicitRenames.get(original);
//		
//		if(renamed != null){
//			return renamed;
//		}
//		
//		if(original.contains("__EVENT_")){
//			original = original.replace("__EVENT_", "event(");
//			original = original + ")";
//		}
//		
//		renamed = original.replace(prefix, "");
//		renamed = renamed.replaceAll("^_*", "");
//		renamed = renamed.replace("__", ".");
//		return renamed;
	}

	public String renameKeepPrefix(String original) {
		
		if(original.contains("~")){
			return null;
		}
		
		String renamed = explicitRenames.get(original);
		
		if(renamed != null){
			return renamed;
		}
		
		if(original.contains("__EVENT_")){
			original = original.replace("__EVENT_", "event(");
			original = original + ")";
		}
		
		renamed = original.replaceAll("^_*", "");
		renamed = renamed.replace("__", ".");
		return renamed;
	}
	
}
