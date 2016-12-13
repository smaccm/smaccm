/*
Copyright (c) 2016, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/

package com.rockwellcollins.atc.tcg.views;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import jkind.api.results.AnalysisResult;
import jkind.api.results.JRealizabilityResult;
import jkind.api.results.PropertyResult;
import jkind.lustre.Program;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.Signal;
import jkind.results.UnknownProperty;
import jkind.results.layout.Layout;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.osate.aadl2.ComponentImplementation;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.views.AgreePatternListener;
import com.rockwellcollins.atc.tcg.extensions.TcgExtractor;
import com.rockwellcollins.atc.tcg.extensions.TcgExtractorRegistry;
import com.rockwellcollins.atc.tcg.extensions.ExtensionRegistry;

public class TestCaseGeneratorMenuListener implements IMenuListener {
    private static final GlobalURIEditorOpener globalURIEditorOpener = AgreeUtils.getGlobalURIEditorOpener();
    private final IWorkbenchWindow window;
    private final TestCaseGeneratorResultTable table;
    private TestSuiteLinker linker;

    public TestCaseGeneratorMenuListener(IWorkbenchWindow window, TestCaseGeneratorResultTable table) {
    	this.window = window;
    	this.table = table;
    }

    public void setLinker(TestSuiteLinker linker) {
        this.linker = linker;
    }

    @Override
    public void menuAboutToShow(IMenuManager manager) {
        IStructuredSelection selection = (IStructuredSelection) table.getViewer().getSelection();
        if (!selection.isEmpty()) {
            AnalysisResult result = (AnalysisResult) selection.getFirstElement();
            addLinkedMenus(manager, result);
        }
    }

    private void addLinkedMenus(IMenuManager manager, AnalysisResult result) {
        addOpenComponentMenu(manager, result);
        addOpenContractMenu(manager, result);
        addViewLogMenu(manager, result);
        addViewTestCaseMenu(manager, result);
        addViewLustreMenu(manager, result);
        addResultsLinkingMenu(manager, result);
    }

    private void addOpenComponentMenu(IMenuManager manager, AnalysisResult result) {
        ComponentImplementation ci = linker.getComponent();
        if (ci != null) {
            manager.add(createHyperlinkAction("Open " + ci.getName(), ci));
        }
    }

    private void addOpenContractMenu(IMenuManager manager, AnalysisResult result) {
        AgreeSubclause contract = linker.getContract();
        if (contract != null) {
            manager.add(createHyperlinkAction("Open Contract", contract));
        }
    }

    private void addViewLogMenu(IMenuManager manager, AnalysisResult result) {
        String log = linker.getLog();
        if (log != null && !log.isEmpty()) {
            manager.add(createWriteConsoleAction("View Log", "Log", log));
        }
    }

    private void addViewTestCaseMenu(IMenuManager manager, AnalysisResult result) {
        final Counterexample cex = getCounterexample(result);
        TcgExtractorRegistry tcgReg = (TcgExtractorRegistry) ExtensionRegistry
                .getRegistry(ExtensionRegistry.TCG_EXTRACTOR_EXT_ID);
        List<TcgExtractor> extractors = tcgReg.getTcgExtractors();

        if (cex != null) {
            final String cexType = getCounterexampleType(result);
            final Layout layout = linker.getLayout();
            final Map<String, EObject> refMap = linker.getRenaming().getTcgRefMap();

            MenuManager sub = new MenuManager("View " + cexType + "Test Case in");
            manager.add(sub);

            sub.add(new Action("Console") {
                @Override
                public void run() {
                    viewCexConsole(cex, layout, refMap);
                }
            });

            sub.add(new Action("Eclipse") {
                @Override
                public void run() {
                    viewCexEclipse(cex, layout, refMap);
                }
            });

            sub.add(new Action("Spreadsheet") {
                @Override
                public void run() {
                    viewCexSpreadsheet(cex, layout);
                }
            });
            
            // send counterexamples to external plugins
            PropertyResult pr = (PropertyResult) result;
            EObject property = refMap.get(pr.getName());
            ComponentImplementation compImpl = linker.getComponent();

            for (TcgExtractor ex : extractors) {
                sub.add(new Action(ex.getDisplayText()) {
                    @Override
                    public void run() {
                        ex.receiveCex(compImpl, property, cex, refMap);
                    }
                });
            }

        }
    }

    private void addViewLustreMenu(IMenuManager manager, AnalysisResult result) {
        Program program = linker.getProgram();
        if (program != null) {
            manager.add(createWriteConsoleAction("View Lustre", "Lustre", program));
        }
    }

    private void addResultsLinkingMenu(IMenuManager manager, AnalysisResult result) {
        if (result instanceof PropertyResult) {
            PropertyResult pr = (PropertyResult) result;
            Map<String, EObject> refMap = linker.getRenaming().getTcgRefMap();
            if (refMap != null) {
            	EObject property = refMap.get(pr.getName());
            	if (property instanceof GuaranteeStatement) {
            		manager.add(createHyperlinkAction("Go To Guarantee", property));
            	}
            	if (property instanceof LemmaStatement) {
            		manager.add(createHyperlinkAction("Go To Lemma", property));
            	}
            	if (property instanceof AssumeStatement) {
            		manager.add(createHyperlinkAction("Go To Assumption", property));
            	}
            	if (property instanceof FnCallExpr) {
            		manager.add(createHyperlinkAction("Go To Node Call", property));
            	}
            }
        }
    }

    private static Counterexample getCounterexample(AnalysisResult result) {
        if (result instanceof PropertyResult) {
            Property prop = ((PropertyResult) result).getProperty();
            if (prop instanceof InvalidProperty) {
                return ((InvalidProperty) prop).getCounterexample();
            } else if (prop instanceof UnknownProperty) {
                return ((UnknownProperty) prop).getInductiveCounterexample();
            }
        } else if (result instanceof JRealizabilityResult) {
            PropertyResult propResult = ((JRealizabilityResult) result).getPropertyResult();
            Property prop = propResult.getProperty();
            if (prop instanceof InvalidProperty) {
                return ((InvalidProperty) prop).getCounterexample();
            }

        }

        return null;
    }

    private static String getCounterexampleType(AnalysisResult result) {
        if (result instanceof PropertyResult) {
            Property prop = ((PropertyResult) result).getProperty();
            if (prop instanceof UnknownProperty) {
                return "Inductive ";
            }
        }

        return " ";
    }

    private IAction createHyperlinkAction(String text, final EObject eObject) {
        return new Action(text) {
            @Override
            public void run() {
                globalURIEditorOpener.open(EcoreUtil.getURI(eObject), true);
            }
        };
    }

    private Action createWriteConsoleAction(String actionName, final String consoleName, final Object content) {
        return new Action(actionName) {
            @Override
            public void run() {
                final MessageConsole console = findConsole(consoleName);
                showConsole(console);
                console.clearConsole();

                /*
                 * From the Eclipse API: "Clients should avoid writing large
                 * amounts of output to this stream in the UI thread. The
                 * console needs to process the output in the UI thread and if
                 * the client hogs the UI thread writing output to the console,
                 * the console will not be able to process the output."
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        console.newMessageStream().println(content.toString());
                    }
                }).start();
            }
        };
    }

    private void viewCexConsole(final Counterexample cex, final Layout layout, Map<String, EObject> refMap) {
      final MessageConsole console = findConsole("Test Case");
        showConsole(console);
        console.clearConsole();
        console.addPatternMatchListener(new AgreePatternListener(refMap));

        /*
         * From the Eclipse API: "Clients should avoid writing large amounts of
         * output to this stream in the UI thread. The console needs to process
         * the output in the UI thread and if the client hogs the UI thread
         * writing output to the console, the console will not be able to
         * process the output."
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (MessageConsoleStream out = console.newMessageStream()) {
                    for (String category : layout.getCategories()) {
                        if (isEmpty(category, cex, layout)) {
                            continue;
                        }

                        printHLine(out, cex.getLength());
                        out.println("Variables for " + category);
                        printHLine(out, cex.getLength());

                        out.print(String.format("%-60s", "Variable Name"));
                        for (int k = 0; k < cex.getLength(); k++) {
                            out.print(String.format("%-15s", k));
                        }
                        out.println();
                        printHLine(out, cex.getLength());

                        for (Signal<Value> signal : cex.getCategorySignals(layout, category)) {
                            // dont' print out values for properties
                            if (signal.getName().contains(":")) {
                                continue;
                            }
                            out.print(String.format("%-60s", "{" + signal.getName() + "}"));
                            for (int k = 0; k < cex.getLength(); k++) {
                                Value val = signal.getValue(k);
                                if (jkind.util.Util.isArbitrary(val)) {
                                    out.print(String.format("%-15s", "-"));
                                } else {
                                    out.print(String.format("%-15s", val.toString()));
                                }
                            }
                            out.println();
                        }
                        out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private boolean isEmpty(String category, Counterexample cex, Layout layout) {
        return cex.getCategorySignals(layout, category).isEmpty();
    }

    private void printHLine(MessageConsoleStream out, int nVars) {
        out.print("--------------------------------------------------");
        for (int k = 0; k < nVars; k++) {
            out.print("---------------------");
        }
        out.println();
    }

    private static MessageConsole findConsole(String name) {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] existing = conMan.getConsoles();
        for (int i = 0; i < existing.length; i++) {
            if (name.equals(existing[i].getName())) {
                return (MessageConsole) existing[i];
            }
        }
        // no console found, so create a new one
        MessageConsole myConsole = new MessageConsole(name, null);
        conMan.addConsoles(new IConsole[] { myConsole });
        return myConsole;
    }

    private void showConsole(IConsole console) {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        try {
            IConsoleView view = (IConsoleView) page.showView(IConsoleConstants.ID_CONSOLE_VIEW);
            view.display(console);
            view.setScrollLock(true);
        } catch (PartInitException e) {
        }
    }

    private void viewCexEclipse(Counterexample cex, Layout layout, Map<String, EObject> refMap) {
        try {
        	TestCaseGeneratorTestCaseView tcView = (TestCaseGeneratorTestCaseView) window.getActivePage().showView(
        			TestCaseGeneratorTestCaseView.ID);
            tcView.setInput(cex, layout, refMap);
            tcView.setFocus();
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    private void viewCexSpreadsheet(Counterexample cex, Layout layout) {
        try {
            File file = File.createTempFile("cex", ".xls");
            cex.toExcel(file, layout);
            org.eclipse.swt.program.Program.launch(file.toString());
        } catch (IOException e) {
            Dialog.showError("Unable to open spreadsheet", e.getMessage());
            e.printStackTrace();
        }
    }
}
