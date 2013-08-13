package com.rockwellcollins.atc.agree.analysis.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import jkind.api.JKindApi;
import jkind.api.results.JKindResult;
import jkind.api.results.MapRenaming;
import jkind.api.results.PropertyResult;
import jkind.api.results.Renaming;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.Signal;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;

import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.analysis.AgreeEvaluator;
import com.rockwellcollins.atc.agree.analysis.AgreeSingleResultsView;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public abstract class AgreeAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private Object currentSelection;
	protected AgreeEvaluator evaluator = null;

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.size() == 1) {
				currentSelection = iss.getFirstElement();
			}
		}
	}

	abstract protected IStatus runJob(Element sel, IProgressMonitor monitor);

	@Override
	public void run(IAction action) {
		// do injection stuff for the hyperlinker in the console
		AgreeActivator inst = AgreeActivator.getInstance();
		Injector inj = inst.getInjector(AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE);
		GlobalURIEditorOpener uriEd = inj.getInstance(GlobalURIEditorOpener.class);
		AgreeConsoleHyperLink.setGlobalURIEditorOpener(uriEd);

		WorkspaceJob job = new WorkspaceJob("AGREE Job") {
			@Override
			public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
				IEditorPart activeEditor = window.getActivePage().getActiveEditor();
				if (activeEditor == null) {
					return Status.CANCEL_STATUS;
				}

				if (!(currentSelection instanceof EObjectNode)) {
					return Status.CANCEL_STATUS;
				}

				XtextEditor xtextEditor = (XtextEditor) activeEditor.getAdapter(XtextEditor.class);
				if (xtextEditor == null) {
					return Status.CANCEL_STATUS;
				}

				EObjectNode node = (EObjectNode) currentSelection;
				final URI uri = node.getEObjectURI();
				xtextEditor.getDocument().readOnly(new IUnitOfWork<IStatus, XtextResource>() {
					@Override
					public IStatus exec(XtextResource resource) throws Exception {
						EObject eobj = resource.getResourceSet().getEObject(uri, true);
						if (eobj instanceof Element) {
							return runJob((Element) eobj, monitor);
						} else {
							return Status.CANCEL_STATUS;
						}
					}
				});
				return Status.OK_STATUS;
			}
		};

		job.setRule(ResourcesPlugin.getWorkspace().getRoot());
		job.schedule();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void runKindQueryAPI(Subcomponent subContext, AgreeEvaluator eval, String query,
			MessageConsoleStream out, IProgressMonitor monitor) {

		final Renaming varRenaming = new MapRenaming(eval.varRenaming, MapRenaming.Mode.NULL);

		JKindApi japi = new JKindApi();
		final JKindResult result;

		try {
			String name;
			if (subContext != null) {
				name = subContext.getName();
			} else {
				name = "TOP";
			}

			result = new JKindResult(name);
			showView(result);
			japi.execute(query, result, monitor);

			File tempFile = File.createTempFile("agree_" + name + "_", ".xls");
			result.toExcel(tempFile);
		} catch (Exception e) {
			out.print(e.getMessage());
			return;
		}

		List<PropertyResult> props = result.getPropertyResults();

		for (PropertyResult propRes : props) {
			Property prop = propRes.getProperty();
			out.print("Result for property '" + prop.getName() + "': ");
			if (prop instanceof InvalidProperty) {
				out.println("INVALID");
				out.println();

				InvalidProperty invProp = (InvalidProperty) prop;
				final Counterexample cex = invProp.getCounterexample();
				Comparator<String> compareFunc = new Comparator<String>() {
					@Override
					public int compare(String s1, String s2) {
						String r1 = varRenaming.rename(s1);
						String r2 = varRenaming.rename(s2);

						Signal<Value> sig1 = cex.getSignal(s1);
						Signal<Value> sig2 = cex.getSignal(s2);

						if (sig1 == null && sig2 == null) {
							return 0;
						}
						if (sig1 == null) {
							return 1;
						}
						if (sig2 == null) {
							return -1;
						}

						return r1.compareTo(r2);
					}
				};

				// print out values for the top level component

				Set<String> kVars = null;
				String name;
				if (subContext != null) { // this is a result from a recursive
											// call
					kVars = eval.getCompToKVarMap().get(subContext);
					name = subContext.getName();
				} else {
					SystemImplementation sysImpl = eval.getSysImpl();
					kVars = eval.getCompToKVarMap().get(sysImpl);
					name = sysImpl.getName();
				}

				// get vars in alphebetical order
				ArrayList<String> kVarList = new ArrayList<String>();
				kVarList.addAll(kVars);
				Collections.sort(kVarList, compareFunc);

				printOutputVars(name, kVarList, varRenaming, cex, out);

				// print out values for variables in each component
				for (Subcomponent subComp : eval.getSubComponents()) {
					kVars = eval.getCompToKVarMap().get(subComp);

					// get vars in alphebetical order
					kVarList.clear();
					kVarList.addAll(kVars);
					Collections.sort(kVarList, compareFunc);

					printOutputVars(subComp.getName(), kVarList, varRenaming, cex, out);

				}

			} else if (prop instanceof UnknownProperty) {
				out.println("UNKNOWN");
			} else {
				assert (prop instanceof ValidProperty);
				out.println("VALID");
			}
		}

	}

	private void showView(final JKindResult result) {
		window.getShell().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				AgreeSingleResultsView page;
				try {
					page = (AgreeSingleResultsView) window.getActivePage().showView(
							AgreeSingleResultsView.ID);
					page.setInput(result);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void printOutputVars(String compName, List<String> kVarList, Renaming varRenaming,
			Counterexample cex, MessageConsoleStream out) {

		printHLine(out, cex.getLength());
		out.print("Variables for component: '" + compName + "'\n");
		printHLine(out, cex.getLength());

		// print the top key
		out.print(String.format("%-50s", "Variable Name"));
		for (int k = 0; k < cex.getLength(); k++) {
			out.print(String.format("%-8s", k));
		}
		out.println();
		printHLine(out, cex.getLength());

		// print all the variables
		for (String kVar : kVarList) {
			String aadlName = varRenaming.rename(kVar);
			Signal<Value> sig = cex.getSignal(kVar);
			if (sig != null) {
				out.print(String.format("%-50s", "[" + aadlName + "]"));
				for (int k = 0; k < cex.getLength(); k++) {
					Value val = sig.getValue(k);
					out.print(String.format("%-8s", val));
				}
				out.println();
			}
		}
		out.println();
	}

	public void printHLine(MessageConsoleStream out, int nVars) {
		out.print("--------------------------------------------------");
		for (int k = 0; k < nVars; k++) {
			out.print("-------");
		}
		out.println();
	}

	protected static MessageConsole findConsole(String name) {
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
}