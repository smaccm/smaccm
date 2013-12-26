package com.rockwellcollins.atc.resolute.consistency.checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;









import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.util.OsateDebug;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.rockwellcollins.atc.resolute.consistency.Utils;
import com.rockwellcollins.atc.resolute.consistency.Utils.ConsistencyType;

public class SimulinkChecker extends AbstractChecker{

	public SimulinkChecker (NamedElement ne, ConsistencyType ct, String a, String s)
	{
		super (ne, ct, a, s);
	}

	public boolean check ()
	{
		boolean result = false;
		if (consistencyType == ConsistencyType.EXIST)
		{
			Document doc = Utils.loadSimulinkBlock (this.sourceFile);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			try {
				String xpathString = "//ModelInformation/Library";
				for (String c : this.artifact.split("/"))
				{
					xpathString += "/System/Block[@Name=\'"+c.replace("\n", "\n")+"\']";
				}
				//XPathExpression expr = xpath.compile("//ModelInformation/Library/System/Block[@Name='WBS']/System/Block[@Name='Blue\nPump']");
				
				XPathExpression expr = xpath.compile(xpathString);

//				OsateDebug.osateDebug("[SimulinkChecker] xpath="+xpathString);
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//				OsateDebug.osateDebug("[SimulinkChecker] result size="+nodeList.getLength());
				if (nodeList.getLength() <= 0)
				{
					return false;
				}
			
			} catch (XPathExpressionException e) {
				e.printStackTrace();
				return false;
			}
		}
		

        return true;
	}

}
