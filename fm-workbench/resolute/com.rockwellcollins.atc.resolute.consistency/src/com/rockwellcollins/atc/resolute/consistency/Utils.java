package com.rockwellcollins.atc.resolute.consistency;

import java.io.InputStream;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osate.aadl2.util.OsateDebug;
import org.w3c.dom.Document;

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
	
	
	public static Document loadSimulinkBlock (String path)
	{
		
		InputStream in;
		ZipFile 	zipFile;
		
		zipFile = null;
		
		try 
		{
			OsateDebug.osateDebug("[Utils] Try to load " + path);
			IPath ipath = new Path (path);
			IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(ipath);
			/*
			 * In fact, the simulink model (slx file) is a zip file
			 * like openfocument file. We parse the zip and extract
			 * the file that contains all interesting information
			 * (blockdiagram.xml).
			 */
			if (path.contains(".slx"))
			{
				String toOpen = targetFile.getLocation().toOSString();
				OsateDebug.osateDebug("[Utils] try to open " + toOpen);
				zipFile = new ZipFile(toOpen);
				in = zipFile.getInputStream(zipFile.getEntry("simulink/blockdiagram.xml"));
			}
			else
			{
				OsateDebug.osateDebug("[Utils] Not a Simulink model");
				return null;
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(in);
			if (zipFile != null)
			{
				zipFile.close();
			}
			in.close();
			return doc;
		}
		catch (Exception e)
		{
//			OsateDebug.osateDebug("[FileImport] Exception in loadXMLZip()");
			e.printStackTrace();
		}
		return null;
	}
}
