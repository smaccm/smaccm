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

public class GeometryChecker extends AbstractChecker{

	public GeometryChecker (NamedElement ne, ConsistencyType ct, String a, String s)
	{
		super (ne, ct, a, s);
	}

	public boolean check ()
	{
		boolean result = false;
		try 
		{
			String current_line;
			
			BufferedReader br;
			OsateDebug.osateDebug("[GeometryChecker] open file " + this.sourceFile);
			OsateDebug.osateDebug("directory=" + AadlUtil.getResourcePath(component).toString());
			
			IPath path = AadlUtil.getResourcePath(component);
			path = path.append("/" + this.sourceFile);
			OsateDebug.osateDebug("file=" + path.toString());

			IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			
			br = new BufferedReader(new InputStreamReader (targetFile.getContents()));
			
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
			return false;
		}

		
        return result;
	}

}
