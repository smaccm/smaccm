package com.rockwellcollins.atc.resolute.consistency.checker;

import java.io.BufferedReader;
import java.io.FileReader;

public class Geometry extends AbstractChecker{


	public boolean check ()
	{
		boolean result = false;
		try 
		{
			String current_line;
			BufferedReader br;
			br = new BufferedReader(new FileReader(this.sourceFile));
			
			while ((current_line = br.readLine()) != null)
			{
				if (current_line.contains(this.artifact))
				{
					result = true;
				}
			}
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		
        return result;
	}

}
