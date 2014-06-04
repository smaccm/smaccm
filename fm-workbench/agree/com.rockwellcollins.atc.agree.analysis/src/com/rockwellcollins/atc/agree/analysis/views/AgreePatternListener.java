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
        	EObject ref = refMap.get(name);

        	if(ref == null){
        		while(ref == null && name.contains(".")){
        			String[] tokens = name.split("\\.");
        			String delim = "";
        			name = "";
        			for(int i = 0; i < tokens.length-1; i++){
        				name += delim + tokens[i];
        				delim = ".";
        			}
        			ref = refMap.get(name);
        		}
        	}
            
            if (ref != null) {
                IHyperlink hyperlink = new AgreeConsoleHyperLink(ref);
                console.addHyperlink(hyperlink, offset, length);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
