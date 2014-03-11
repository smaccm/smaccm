package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.Type;

import org.osate.aadl2.DataAccess;
import org.osate.aadl2.EventDataPort;

public class AgreeDataAccess {

	public String jSour;
	public String aSour;
	public String jDest;
	public String aDest;
	public Type type;
	public String destCategory;
	public String sourCategory;
	public DataAccess sourConn;
	public DataAccess destConn;	
	
	public AgreeDataAccess(String jSour, 
			String aSour, 
			String jDest, 
			String aDest, 
			Type type, 
			DataAccess sourConn, 
			DataAccess destConn, 
			String sourCategory, 
			String destCategory){
		
		this.jSour = jSour;
		this.aSour = aSour;
		this.jDest = jDest;
		this.aDest = aDest;
		this.type = type;
		this.sourConn = sourConn;
		this.destConn = destConn;
		this.destCategory = destCategory;// == null ? "" : destCategory;
		this.sourCategory = sourCategory;// == null ? "" : sourCategory;
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
