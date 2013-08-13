package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class KindXMLHandler extends DefaultHandler{
	
	private List<AgreeCounterExample> cexs = new ArrayList<>();
	private AgreeCounterExample curEx = null;
	private String curVarName = null;
	private String curVal = null;
	private int curK;
	
	public KindXMLHandler(){
		super();
	}
	

	public void startElement (String uri, String name,
			String qName, Attributes atts)
	{
		switch(name){
		case "Property":
			String propName = atts.getValue("name");
			curEx = new AgreeCounterExample(propName);
			break;
		case "Signal":
			curVarName = atts.getValue("name");
			break;
		case "Value":
			String timeStr = atts.getValue("time");
			curK = Integer.parseInt(timeStr);
			break;
		}
	}
	
	public void characters (char ch[], int start, int length){
		StringBuilder sb = new StringBuilder();
		for(int i = start; i < start + length; i++){
			sb.append(ch[i]);
		}
		curVal = sb.toString();
	}
	
	public void endElement (String uri, String name, String qName)
	{
		switch(name){
		case "Value":
			curEx.addVarVal(curVarName, curK, curVal);
			break;
		case "Property":
			cexs.add(curEx);
			break;
		}
	}



}
