package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {
	
	private String prefix;
	private Map<String, String> explicitRenames = new HashMap<>();
	private List<String> blackList = new ArrayList<>();
	
	public AgreeRenaming(){
		blackList.add("__ASSUM");
		blackList.add("__ASSERT");
		blackList.add("__GUAR");
		blackList.add("~~GUARANTEE");
		blackList.add("~~ASSUME");
	}

	public void addExplicitRename(String oldName, String newName){
		this.explicitRenames.put(oldName, newName);
	}
	
	@Override
	public String rename(String original) {
		
		String newName = this.explicitRenames.get(original);
		if(newName != null){
			return newName;
		}
		
		//check if it contains a blacklisted string
		for(String black : blackList){
			if(original.contains(black)){
				return null;
			}
		}
		
		//magic to remove the prefix
		newName = original.replace("__", ".");
		newName = newName.replaceFirst("_Node[^\\.]*\\.", "");
		newName = newName.replaceAll("~[0-9]*", "");
		
		return newName;
		
	}
	
}
