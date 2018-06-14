package com.rockwellcollins.atc.resolute.analysis.views;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.analysis.export.ResoluteDOTUtils;
import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.FailResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;

public class AssuranceCaseView extends ViewPart {
    public static final String ID = "com.rockwellcollins.atc.resolute.views.assuranceCaseView";
    private TreeViewer treeViewer;

    @Inject
    GlobalURIEditorOpener globalURIEditorOpener;

    @Override
    public void createPartControl(Composite parent) {
        treeViewer = new TreeViewer(parent, SWT.SINGLE);
        treeViewer.setContentProvider(new ResoluteResultContentProvider());
        treeViewer.setLabelProvider(new ResoluteResultLabelProvider());
        ResoluteTooltipListener.createAndRegister(treeViewer);

        MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);

        manager.addMenuListener(manager1 -> {
		    IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		    if (!selection.isEmpty()) {
		        final ClaimResult claim = (ClaimResult) selection.getFirstElement();

		        EObject location = claim.getLocation();
		        if (claim instanceof FailResult) {
		            manager1.add(createHyperlinkAction("Open Failure Location", location));
		        } else if (location instanceof ProveStatement) {
		            manager1.add(createHyperlinkAction("Open Prove Statement", location));
		            manager1.add(createExportSubmenu(claim));
		        } else {
		            manager1.add(createHyperlinkAction("Open Claim Definition", location));
		        }

		        Map<String, EObject> references = claim.getReferences();
		        for (String name : new TreeSet<String>(references.keySet())) {
		            manager1.add(createHyperlinkAction("Go to '" + name + "'",
		                    references.get(name)));
		        }

		        manager1.add(new Action("Copy Claim Text") {
		            @Override
		            public void run() {
		                Transferable text = new StringSelection(claim.getText());
		                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		                clipboard.setContents(text, null);
		            }
		        });
			}
			if (doResoluteDebug) {
				manager.add(createDisableResoluteConsoleAction());
			} else {
				manager.add(createEnableResoluteConsoleAction());
			}
		});
        treeViewer.getControl().setMenu(manager.createContextMenu(treeViewer.getTree()));
    }

    private MenuManager createExportSubmenu(ClaimResult claim) {
        MenuManager manager = new MenuManager("Export");

        manager.add(createExportDOTAction(claim));
        manager.add(createExportCAZAction(claim));
        return manager;
    }

    private IAction createHyperlinkAction(String text, final EObject eObject) {
        return new Action(text) {
            @Override
            public void run() {
                globalURIEditorOpener.open(EcoreUtil.getURI(eObject), true);
            }
        };
    }

    private static boolean CERTWARE_INSTALLED;
    static {
        try {
//            CertWareExport.tryLoad();
            CERTWARE_INSTALLED = true;
        } catch (NoClassDefFoundError e) {
            CERTWARE_INSTALLED = false;
        }
    }



    private IAction createExportCAZAction(final ClaimResult claim) {
        String name = "Export to CertWare";
        if (!CERTWARE_INSTALLED) {
            name += " [CertWare plug-ins not installed]";
        }

        return new Action(name) {
            @Override
            public void run() {
                try {
//                    CertWareExport.export(claim);
                } catch (Throwable t) {
                    MessageDialog.openError(treeViewer.getControl().getShell(),
                            "Error during export to CertWare", t.getMessage());
                    t.printStackTrace();
                }
            }

            @Override
            public boolean isEnabled() {
                return CERTWARE_INSTALLED;
            }
        };
    }


    private Action createExportDOTAction(final ClaimResult claim) {
        return new Action("Show DOT Text in Console") {
            @Override
            public void run() {
                MessageConsole console = findConsole("DOT Export");
                showConsole(console);
                console.clearConsole();
                console.newMessageStream().println(ResoluteDOTUtils.claimToDOTText(claim));
            }
        };
    }

	private static boolean doResoluteDebug = false;
	private static MessageConsoleStream consoleStream = null;

	private Action createEnableResoluteConsoleAction() {
		return new Action("Enable Resolute Debug Console") {
			@Override
			public void run() {
				MessageConsole console = findConsole("Resolute Console");
				showConsole(console);
				console.clearConsole();
				doResoluteDebug = true;
				consoleStream = console.newMessageStream();
				consoleStream.println("Resolute debug tracing enabled");
			}
		};
	}

	private Action createDisableResoluteConsoleAction() {
		return new Action("Disable Resolute Debug Console") {
			@Override
			public void run() {
				MessageConsole console = findConsole("Resolute Console");
				showConsole(console);
				doResoluteDebug = false;
				consoleStream.println("Resolute debug tracing disabled");
			}
		};
	}

	public static void writeToConsole(String msg) {
		if (doResoluteDebug) {
			consoleStream.println(msg);
			try {
				consoleStream.flush();
			} catch (IOException e) {
			}
		}
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

    public void setProofs(List<ResoluteResult> proofTrees) {
        Object[] expandedElements = treeViewer.getExpandedElements();
        TreePath[] expandedTreePaths = treeViewer.getExpandedTreePaths();
        treeViewer.setInput(proofTrees);
        treeViewer.setExpandedElements(expandedElements);
        treeViewer.setExpandedTreePaths(expandedTreePaths);
    }

    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
}