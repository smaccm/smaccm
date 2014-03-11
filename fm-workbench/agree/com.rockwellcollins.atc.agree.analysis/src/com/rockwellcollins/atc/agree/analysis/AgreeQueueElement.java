package com.rockwellcollins.atc.agree.analysis;

import org.osate.aadl2.EventDataPort;
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
	public EventDataPort sourConn;
	public EventDataPort destConn;	
	
	public AgreeQueueElement(String jSour, 
			String aSour, 
			String jDest, 
			String aDest, 
			Type type, 
			EventDataPort sourConn, 
			EventDataPort destConn, 
			String sourCategory, 
			String destCategory, 
			long queueSize){
		
		this.jSour = jSour;
		this.aSour = aSour;
		this.jDest = jDest;
		this.aDest = aDest;
		this.type = type;
		this.sourConn = sourConn;
		this.destConn = destConn;
		this.destCategory = destCategory;// == null ? "" : destCategory;
		this.sourCategory = sourCategory;// == null ? "" : sourCategory;
		this.queueSize = queueSize;

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
