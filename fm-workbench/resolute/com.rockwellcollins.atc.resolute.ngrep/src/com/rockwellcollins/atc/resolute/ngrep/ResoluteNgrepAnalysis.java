package com.rockwellcollins.atc.resolute.ngrep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;



public class ResoluteNgrepAnalysis implements ResoluteExternalAnalysis {
    @Override
    public ResoluteValue run(EvaluationContext context, List<ResoluteValue> args) 
    {
    	int nmatches = 0;
    	String current_line;
    	ResoluteValue arg0 = args.get(0);
    	ResoluteValue arg1 = args.get(1);
    	ResoluteValue arg2 = args.get(2);
    	ResoluteValue arg3 = args.get(3);
    	
    	assert (arg0.isString());
    	assert (arg1.isString());
    	assert (arg2.isString());
    	assert (arg3.isInt());
    			
		try 
		{
			BufferedReader br;
			br = new BufferedReader(new FileReader(arg1.getString()));
			
			while ((current_line = br.readLine()) != null)
			{
				if (current_line.contains(arg2.getString()))
				{
					nmatches++;
				}
			}
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new BoolValue(false);
		}

		if (nmatches == arg3.getInt())
		{
			return new BoolValue(true);
		}
        return new BoolValue(false);
    }

}
