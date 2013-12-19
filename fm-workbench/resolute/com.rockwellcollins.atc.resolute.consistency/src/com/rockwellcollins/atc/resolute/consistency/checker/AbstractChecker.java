package com.rockwellcollins.atc.resolute.consistency.checker;

import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.resolute.consistency.Utils;
import com.rockwellcollins.atc.resolute.consistency.Utils.ConsistencyType;

public abstract class AbstractChecker {
	protected NamedElement 				component;
	protected String						artifact;
	protected String 						sourceFile;
	protected Utils.ConsistencyType 		consistencyType;
	
	
	public AbstractChecker ()
	{
		this.component 			= null;
		this.artifact  			= null;
		this.sourceFile			= null;
		this.consistencyType	= ConsistencyType.ANY;
	}
	
	public AbstractChecker (NamedElement ne, ConsistencyType ct, String a, String s)
	{
		this.component			= ne;
		this.artifact			= a;
		this.sourceFile			= s;
		this.consistencyType 	= ct;
	}
	
	public void setConsistencyType (ConsistencyType ct)
	{
		this.consistencyType = ct;
	}
	
	public ConsistencyType getConsistencyType ()
	{
		return this.consistencyType;
	}
	
	public String getSourceFile ()
	{
		return this.sourceFile;
	}
	
	public String getArtifact ()
	{
		return this.artifact;
	}
	
	public void setArtifact (String a)
	{
		this.artifact = a;
	}
	
	public void setSourceFile (String s)
	{
		this.sourceFile = s;
	}
	
	public NamedElement getComponent ()
	{
		return this.component;
	}
	
	public abstract boolean check ();
}
