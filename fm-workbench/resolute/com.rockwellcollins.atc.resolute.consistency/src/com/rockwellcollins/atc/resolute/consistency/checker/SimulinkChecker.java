package com.rockwellcollins.atc.resolute.consistency.checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.util.OsateDebug;

import com.rockwellcollins.atc.resolute.consistency.Utils.ConsistencyType;

public class SimulinkChecker extends AbstractChecker{

	public SimulinkChecker (NamedElement ne, ConsistencyType ct, String a, String s)
	{
		super (ne, ct, a, s);
	}

	public boolean check ()
	{
		boolean result = false;
		
		
        return true;
	}

}
