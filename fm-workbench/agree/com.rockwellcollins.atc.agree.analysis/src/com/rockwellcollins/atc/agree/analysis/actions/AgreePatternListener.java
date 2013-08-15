package com.rockwellcollins.atc.agree.analysis.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class AgreePatternListener implements IPatternMatchListener {

    private TextConsole console;
    private Map<String, EObject> refMap = new HashMap<String, EObject>();

    public AgreePatternListener(Map<String, EObject> r) {
        refMap = r;
    }

    @Override
    public void connect(TextConsole c) {
        console = c;
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public void matchFound(PatternMatchEvent event) {
        // remove the brackets
        int offset = event.getOffset() + 1;
        int length = event.getLength() - 2;
        try {
            IDocument doc = console.getDocument();
            String pattern = doc.get(offset, length);
            EObject ref = refMap.get(pattern);
            assert (ref != null);
            IHyperlink hyperlink = new AgreeConsoleHyperLink(ref);

            console.addHyperlink(hyperlink, offset, length);
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getPattern() {
        return "\\[.*\\]";
    }

    @Override
    public int getCompilerFlags() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getLineQualifier() {
        // TODO Auto-generated method stub
        return null;
    }

}
