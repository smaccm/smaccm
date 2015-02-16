package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {
	
	private Map<String, String> explicitRenames = new HashMap<>();
	private Set<String> blackList = new HashSet<>();
	
	public AgreeRenaming(){
		blackList.add(".*__ASSUM.*");
		blackList.add(".*__ASSERT.*");
		blackList.add(".*__GUAR.*");
		blackList.add(".*~~GUARANTEE.*");
		blackList.add(".*~~ASSUME.*");
		blackList.add(".*__CALENDAR_NODE.*");
		blackList.add(".*__INITIALIZED.*");
		blackList.add(".*~[^_]*$.*");
		blackList.add(".*~state[0-9]*.*");
		blackList.add(".*\\._.*");
		blackList.add("__CONSIST_COUNTER");
        blackList.add("__SUBCOMP_HIST");
        blackList.add("___SUBCOMP_CONJ");
        blackList.add("___SYS_ASSUM_HIST");
        blackList.add("___SYS_GUAR_CONJ");
        blackList.add("___SYS_GUAR_HIST");
        blackList.add("___SYS_ASSUM_CONJ");
	}
	
	public void addToBlackList(String regex){
	    blackList.add(regex);
	}
	
	public void addRenamings(AgreeRenaming renaming){
		this.explicitRenames.putAll(renaming.explicitRenames);
		this.blackList.addAll(renaming.blackList);
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
			if(original.matches(black)){
				return null;
			}
		}
		
		//magic to remove the prefix
		newName = original.replaceAll("___Nod([^_]_?)*_", "");
		newName = newName.replace("~condact", "");	
		newName = newName.replaceAll("~[0-9]*", "");
		newName = newName.replace("__", ".");
		
		return newName;
		
	}
	
}
