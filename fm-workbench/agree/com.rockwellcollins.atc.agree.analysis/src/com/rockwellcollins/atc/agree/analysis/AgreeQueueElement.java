package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.Type;

public class AgreeQueueElement {

	public String jSour;
	public String aSour;
	public String jDest;
	public String aDest;
	public Type type;
	public String destCategory;
	public String sourCategory;
	public long queueSize;	
	
	public AgreeQueueElement(String jSour, String aSour, 
			String jDest, String aDest, Type type, String sourCategory, String destCategory, long queueSize){
		
		this.jSour = jSour;
		this.aSour = aSour;
		this.jDest = jDest;
		this.aDest = aDest;
		this.type = type;
		this.destCategory = destCategory;
		this.sourCategory = sourCategory;
		this.queueSize = 2;
		
		if(destCategory == null || sourCategory == null){
			throw new AgreeException("Created queue with null source or destination category");
		}
		
		
	}
	
	@Override
	public boolean equals(Object o){
		
		if(!(o instanceof AgreeQueueElement)){
			return false;
		}
		
		AgreeQueueElement that = (AgreeQueueElement)o;
		return that.jDest.equals(this.jDest);
	}
	
	@Override
	public int hashCode(){
		return this.jDest.hashCode();
	}
	
	
	
	
	
}
