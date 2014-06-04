package com.rockwellcollins.atc.agree.analysis;

import java.util.Map;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {
	
	private String prefix;
	private Map<String, String> explicitRenames;
	public AgreeRenaming(String prefix, Map<String, String> explicitRenames){
		this.prefix = prefix;
		this.explicitRenames = explicitRenames;
	}

	@Override
	public String rename(String original) {
		
		if(original.contains("~")){
			return null;
		}
		
		String renamed = explicitRenames.get(original);
		
		if(renamed != null){
			return renamed;
		}
		
		renamed = original.replace(prefix, "");
		renamed = renamed.replaceAll("^_*", "");
		renamed = renamed.replace("__", ".");
		return renamed;
	}

}
