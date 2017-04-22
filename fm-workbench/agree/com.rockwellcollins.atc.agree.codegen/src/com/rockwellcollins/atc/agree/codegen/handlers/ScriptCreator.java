package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;

public abstract class ScriptCreator {
	protected StringBuilder sb = new StringBuilder();


	public String toString() {
		return sb.toString();
	}

	protected void write(Object o) {
		sb.append(o);
	}

	protected static final String seperator = System.getProperty("line.separator");

	protected void newline() {
		write(seperator);
	}
	
	protected void writeline(Object o){
		write(o);
		newline();
	}
	
	protected abstract void createScript() throws Exception;
	
	//copy script text from an existing file
	protected void copyScript(String scriptFileName) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("CommonScriptImplMdl.txt"));
		try {
		    //StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	write(line);
		    	newline();
		        //sb.append(line);
		        //sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    //String everything = sb.toString();
		} finally {
		    br.close();
		}
//		//try {
//			//URL path = ScriptCreator.class.getResource(scriptFileName);
//			URL path = ScriptCreator.class.getResource(scriptFileName);
//			if(path == null){
//				throw new Exception("File not found: "+scriptFileName);
//			}
//			File f = new File(path.getFile());
//			//File f = new File(path.toURI());
//			BufferedReader br = new BufferedReader(new FileReader(f));
////			
////			Path file = Paths.get(scriptFileName);
////			InputStream is = Files.newInputStream(file);
////			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			String line = br.readLine();
//		    while (line != null) {
//		    	writeline(line);
//		        line = br.readLine();
//		    }
//		    br.close();
//		//} 
////		catch (Exception e) {
////			e.printStackTrace();
////		} 
	}
}
