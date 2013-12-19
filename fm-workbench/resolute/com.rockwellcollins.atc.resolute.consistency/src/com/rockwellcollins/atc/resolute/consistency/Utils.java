package com.rockwellcollins.atc.resolute.consistency;

public class Utils {
	public enum ModelType 		{GEOMETRY_STP,SIMULINK,UNKNOWN}
	public enum ConsistencyType {ANY,ALL,EXIST}
	
	public static ModelType getModelType (String c)
	{
		if (c.equalsIgnoreCase("simulink"))
		{
			return ModelType.SIMULINK;
		}
		
		if (c.equalsIgnoreCase("geometry_stp"))
		{
			return ModelType.GEOMETRY_STP;
		}
		
		return ModelType.UNKNOWN;
	}
	
	
	public static ConsistencyType getConsistencyType(String c)
	{
		if (c.equalsIgnoreCase("exist"))
		{
			return ConsistencyType.EXIST;
		}
		return ConsistencyType.ANY;
	}
}
