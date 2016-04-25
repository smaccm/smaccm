package com.rockwellcollins.atc.agree.analysis.views;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class AgreePatternListener implements IPatternMatchListener {
    private TextConsole console;
    private final Map<String, EObject> refMap;

    public AgreePatternListener(Map<String, EObject> refMap) {
        this.refMap = refMap;
    }

    @Override
    public void connect(TextConsole console) {
        this.console = console;
    }

    @Override
    public void matchFound(PatternMatchEvent event) {
    	
        // remove the brackets
        int offset = event.getOffset() + 1;
        int length = event.getLength() - 2;
        try {
            String name = console.getDocument().get(offset, length);
        	EObject ref = findBestReference(name);
            if (ref != null) {
                IHyperlink hyperlink = new AgreeConsoleHyperLink(ref);
                console.addHyperlink(hyperlink, offset, length);
            }
        } catch (BadLocationException e) {        
            e.printStackTrace();
        }
    }

    private EObject findBestReference(String refStr) {
    	
    	String originalString = refStr;
    	
    	EObject ref = null;
        //Anitha: new Code for getting reference without replacing . with __
        //This works for getting reference for regaulr and support strings
        if (refStr != null && !refStr.equals("")) {
        	//check for the string as is
        	ref = refMap.get(refStr);
        	if (ref != null) {
        		return ref;
            }
        	//check if there is a . - first part is usually component name.
        	//we want to take things after for support strings.
            int index = refStr.indexOf(".");
            if (index != -1) {                  
	            refStr = refStr.substring(index+1, refStr.length());
	            ref = refMap.get(refStr);
	            if (ref != null) {
	        		return ref;
	            }
            }
        }
        
        /*Anitha: This piece of code helps identify if the variables of form: CONFIG__OP_CMD_IN.Data_Config
         * Such variables reference will be CONFIG.OP_CMD_IN 
         * We want to display the whole name, but when clicked it should go to its reference 
         * */
       
        ref = null;
        String tempName = originalString;
        while (ref == null && tempName != null && !tempName.equals("")) {        	
            ref = refMap.get(tempName);
            if (!(ref == null)) {
            	return ref;
            }
    		int index = tempName.lastIndexOf(".");
            if (index == -1) {
                break;
            }
            tempName = tempName.substring(0, index);
           }
        
        
        //Anitha: old Code for getting reference with replacing . with __
		// this is done for monolithic
		refStr = refStr.replace(".", "__");
		while (ref == null && refStr != null && !refStr.equals("")) {
			ref = refMap.get(refStr);
			if (ref == null) {
				break;
		    }
		    int index = refStr.lastIndexOf("__");
		    if (index == -1) {
		        break;
		    }
		    refStr = refStr.substring(0, index);
		}
        return ref;
    }

    @Override
    public String getPattern() {
        return "\\{.*\\}";
    }

    @Override
    public void disconnect() {
    }

    @Override
    public int getCompilerFlags() {
        return 0;
    }

    @Override
    public String getLineQualifier() {
        return null;
    }
}
