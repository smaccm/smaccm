package com.rockwellcollins.atc.agree.analysis.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
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
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ConsistencyResult;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomater;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomaterRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.CexExtractor;
import com.rockwellcollins.atc.agree.analysis.extentions.CexExtractorRegistry;
import com.rockwellcollins.atc.agree.analysis.extentions.ExtensionRegistry;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.analysis.realtime.AgreePattern;

import jkind.api.results.AnalysisResult;
import jkind.api.results.JKindResult;
import jkind.api.results.JRealizabilityResult;
import jkind.api.results.PropertyResult;
import jkind.api.results.Renaming;
import jkind.api.ui.results.AnalysisResultTree;
import jkind.lustre.Program;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.Signal;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;
import jkind.results.layout.Layout;

public class AgreeMenuListener implements IMenuListener {
	private static final GlobalURIEditorOpener globalURIEditorOpener = AgreeUtils.getGlobalURIEditorOpener();
	private final IWorkbenchWindow window;
	private final AnalysisResultTree tree;
	private AgreeResultsLinker linker;
	private AgreePatternListener patternListener = null;

	public AgreeMenuListener(IWorkbenchWindow window, AnalysisResultTree tree) {
		this.window = window;
		this.tree = tree;
	}

	public void setLinker(AgreeResultsLinker linker) {
		this.linker = linker;
	}

	@Override
	public void menuAboutToShow(IMenuManager manager) {
		IStructuredSelection selection = (IStructuredSelection) tree.getViewer().getSelection();
		if (!selection.isEmpty()) {
			AnalysisResult result = (AnalysisResult) selection.getFirstElement();
			addLinkedMenus(manager, result);
		}
	}

	private void addLinkedMenus(IMenuManager manager, AnalysisResult result) {
		addOpenComponentMenu(manager, result);
		addOpenContractMenu(manager, result);
		addViewLogMenu(manager, result);
		addViewCounterexampleMenu(manager, result);
		addViewLustreMenu(manager, result);
		addResultsLinkingMenu(manager, result);
		addViewSupportMenu(manager, result);
		addTraceabilityDocMenu(manager, result);
		addTraceabilityMatrixMenu(manager, result);
	}

	private void addTraceabilityDocMenu(IMenuManager manager, AnalysisResult result) {

		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		if (prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER).equals(PreferenceConstants.MODEL_CHECKER_JKIND)
				&& prefs.getBoolean(PreferenceConstants.PREF_SUPPORT)) {
			if (result.getName().equals("Contract Guarantees")) {
				if (result.toString().contains("- Valid")) {
					manager.add(addViewTraceabilityConsole("View Traceability Document", manager, result));
				}
			}
		}
	}

	private void addTraceabilityMatrixMenu(IMenuManager manager, AnalysisResult result) {

		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		if (prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER).equals(PreferenceConstants.MODEL_CHECKER_JKIND)
				&& prefs.getBoolean(PreferenceConstants.PREF_SUPPORT) && result instanceof JKindResult) {
			JKindResult jresult = (JKindResult) result;

			Set<String> reqs = new HashSet<String>();
			for (PropertyResult pr : jresult.getPropertyResults()) {
				if (pr.getProperty() instanceof ValidProperty) {
					ValidProperty vp = (ValidProperty) pr.getProperty();
					Set<String> ivc = vp.getIvc();
					if (ivc != null && !ivc.isEmpty()) {
						reqs.addAll(ivc);
					}
				}
			}
			String nodeName = linker.getComponent(result).getName();
			manager.add(new Action("View traceability matrix for " + nodeName) {
				@Override
				public void run() {
					viewTraceabilityMatrix(jresult, reqs);
				}
			});
		}
	}

	private void viewTraceabilityMatrix(JKindResult result, Set<String> reqs) {

		try {
			AgreeTraceabilityMatrixView matrix = (AgreeTraceabilityMatrixView) window.getActivePage()
					.showView(AgreeTraceabilityMatrixView.ID);
			matrix.setInput(result, (AgreeRenaming) linker.getRenaming(result));
			matrix.setFocus();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	private IAction addViewTraceabilityConsole(String text, IMenuManager manager, AnalysisResult result) {
		return new Action(text) {
			@Override
			public void run() {
				Map<String, EObject> tempRefMap = linker.getReferenceMap(result.getParent());
				if (tempRefMap == null) {
					tempRefMap = linker.getReferenceMap(result);
				}
				final Map<String, EObject> refMap = tempRefMap;
				final MessageConsole console = findConsole("Traceability");
				final Renaming renaming = linker.getRenaming(result);
				showConsole(console);
				console.clearConsole();
				console.addPatternMatchListener(new AgreePatternListener(refMap));
				new Thread(() -> {
					try {
						MessageConsoleStream out = console.newMessageStream();
						printHLine(out, 2);
						out.println("Traceability for Valid Contract Guarantees");
						printHLine(out, 2);
						out.println("");
						List<PropertyResult> allProperties = new ArrayList<PropertyResult>(
								((JKindResult) result).getPropertyResults());
						if (!allProperties.isEmpty()) {
							for (PropertyResult prop : allProperties) {
								if (prop.getStatus().equals(jkind.api.results.Status.VALID)) {
									if (renaming instanceof AgreeRenaming) {
										writeIvcResult(prop, console, (AgreeRenaming) renaming);
									}
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
		};
	}

	private void addViewSupportMenu(IMenuManager manager, AnalysisResult result) {
		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		if (prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER).equals(PreferenceConstants.MODEL_CHECKER_JKIND)
				&& prefs.getBoolean(PreferenceConstants.PREF_SUPPORT)) {
			if (!(result instanceof ConsistencyResult || result instanceof JRealizabilityResult)) {
				if (result instanceof PropertyResult) {
					if (((PropertyResult) result).getProperty() instanceof ValidProperty) {
						manager.add(addViewSupportConsole("Set of Support", manager, result));
					}
				}
			}
		}
	}

	private IAction addViewSupportConsole(String text, IMenuManager manager, AnalysisResult result) {
		return new Action(text) {
			@Override
			public void run() {
				Map<String, EObject> tempRefMap = linker.getReferenceMap(result.getParent());
				if (tempRefMap == null) {
					tempRefMap = linker.getReferenceMap(result);
				}
				final Map<String, EObject> refMap = tempRefMap;
				final MessageConsole console = findConsole("Support");
				Renaming tempRenaming = linker.getRenaming(result);
				while (tempRenaming == null) {
					AnalysisResult parent = result.getParent();
					if (parent == null) {
						throw new AgreeException("Problem finding renaming");
					}
					tempRenaming = linker.getRenaming(parent);
				}
				final Renaming renaming = tempRenaming;
				showConsole(console);
				console.clearConsole();
				if (patternListener != null) {
					console.removePatternMatchListener(patternListener);
				}
				patternListener = new AgreePatternListener(refMap);
				console.addPatternMatchListener(patternListener);
				new Thread(() -> {
					if (renaming instanceof AgreeRenaming) {
						writeIvcResult(result, console, (AgreeRenaming) renaming);
					}
				}).start();
			}
		};
	}

	public static void writeIvcResult(AnalysisResult result, final MessageConsole console,
			final AgreeRenaming renaming) {
		try (MessageConsoleStream out = console.newMessageStream()) {
			ValidProperty vp = (ValidProperty) (((PropertyResult) result).getProperty());
			printHLine(out, 2);
			out.println("Set of Support for Guarantee: " + "{" + vp.getName() + "}");
			printHLine(out, 2);
			if (!vp.getIvc().isEmpty()) {
				printHLine(out, 2);
				out.println(String.format("%-25s%-25s", "Component name", "Property name"));
				printHLine(out, 2);
				for (String supportString : vp.getIvc()) {
					String componentName = "";
					String refStr = renaming.getSupportRefString(supportString);
					if (supportString.contains(".")) {
						componentName = supportString.substring(0, supportString.indexOf('.'));
					} else {
						componentName = "Top Level System";
					}
					out.println(String.format("%-25s%-25s", componentName, "{" + refStr + "}"));
				}
				printHLine(out, 2);
			} else {
				out.println("There are no support elements to display.");
			}
			out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addOpenComponentMenu(IMenuManager manager, AnalysisResult result) {
		ComponentImplementation ci = linker.getComponent(result);
		if (ci != null) {
			manager.add(createHyperlinkAction("Open " + ci.getName(), ci));
		}
	}

	private void addOpenContractMenu(IMenuManager manager, AnalysisResult result) {
		AgreeSubclause contract = linker.getContract(result);
		if (contract != null) {
			manager.add(createHyperlinkAction("Open Contract", contract));
		}
	}

	private void addViewLogMenu(IMenuManager manager, AnalysisResult result) {
		String log = linker.getLog(result);
		if (log == null && result instanceof PropertyResult) {
			log = linker.getLog(result.getParent());
		}
		if (log != null && !log.isEmpty()) {
			manager.add(createWriteConsoleAction("View Log", "Log", log));
		}
	}

	private void addViewCounterexampleMenu(IMenuManager manager, AnalysisResult result) {

		result = transformResult(result);

		final List<Counterexample> cexs = getCounterexamples(result);
		final Property property = getProperty(result);
		CexExtractorRegistry cexReg = (CexExtractorRegistry) ExtensionRegistry
				.getRegistry(ExtensionRegistry.CEX_EXTRACTOR_EXT_ID);
		List<CexExtractor> extractors = cexReg.getCexExtractors();

		if (cexs != null) {
			for (Counterexample cex : cexs) {
				final String cexType = getCounterexampleType(result);

				Map<String, EObject> tempRefMap = linker.getReferenceMap(result.getParent());
				if (tempRefMap == null) {
					tempRefMap = linker.getReferenceMap(result);
				}
				Layout tempLayout = linker.getLayout(result.getParent());
				if (tempLayout == null) {
					tempLayout = linker.getLayout(result);
				}

				final Layout layout = tempLayout;
				final Map<String, EObject> refMap = tempRefMap;

//                MenuManager sub = new MenuManager(
//                        "View " + cexType + "Counterexample from " + cex.getSource() + " in");
//                manager.add(sub);

				MenuManager sub = new MenuManager("View " + cexType + "Counterexample in");
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
				EObject agreeProperty = refMap.get(result.getName());
				ComponentImplementation compImpl = linker.getComponent(result.getParent());

				for (CexExtractor ex : extractors) {
					sub.add(new Action(ex.getDisplayText()) {
						@Override
						public void run() {
							ex.receiveCex(compImpl, property, agreeProperty, cex, refMap);
						}
					});
				}
			}
		}
	}

	// calls other agreeautomators to transform results as they see fit
	private AnalysisResult transformResult(AnalysisResult result) {
		AgreeAutomaterRegistry aAReg = (AgreeAutomaterRegistry) ExtensionRegistry
				.getRegistry(ExtensionRegistry.AGREE_AUTOMATER_EXT_ID);
		List<AgreeAutomater> automaters = aAReg.getAgreeAutomaters();

		for (AgreeAutomater aa : automaters) {
			result = aa.transformResult(result);
		}
		return result;
	}

	private void addViewLustreMenu(IMenuManager manager, AnalysisResult result) {
		Program program = linker.getProgram(result);
		if (program == null && result instanceof PropertyResult) {
			program = linker.getProgram(result.getParent());
		}
		if (program != null) {
			manager.add(createWriteConsoleAction("View Lustre", "Lustre", program));
		}
	}

	private void addResultsLinkingMenu(IMenuManager manager, AnalysisResult result) {
		if (result instanceof PropertyResult) {
			PropertyResult pr = (PropertyResult) result;
			Map<String, EObject> refMap = linker.getReferenceMap(pr.getParent());
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
			if (property instanceof CallExpr) {
				manager.add(createHyperlinkAction("Go To Node Call", property));
			}
			if (property instanceof AgreeStatement) {
				AgreeStatement statement = (AgreeStatement) property;
				if (statement.reference instanceof AgreePattern) {
					AgreePattern pattern = (AgreePattern) statement.reference;
					manager.add(createHyperlinkAction("Go To Pattern", pattern.reference));
				}
			}
		}
	}

	private static List<Counterexample> getCounterexamples(AnalysisResult result) {
		if (result instanceof PropertyResult) {
			Property prop = ((PropertyResult) result).getProperty();
			if (prop instanceof InvalidProperty) {
				return Collections.singletonList(((InvalidProperty) prop).getCounterexample());
			} else if (prop instanceof UnknownProperty) {
				return Collections.singletonList(((UnknownProperty) prop).getInductiveCounterexample());
			}
		} else if (result instanceof JRealizabilityResult) {
			PropertyResult propResult = ((JRealizabilityResult) result).getPropertyResult();
			Property prop = propResult.getProperty();
			if (prop instanceof InvalidProperty) {
				return Collections.singletonList(((InvalidProperty) prop).getCounterexample());
			}

		}

		return null;
	}

	private static Property getProperty(AnalysisResult result) {
		if (result instanceof PropertyResult) {
			return ((PropertyResult) result).getProperty();
		} else if (result instanceof JRealizabilityResult) {
			PropertyResult propResult = ((JRealizabilityResult) result).getPropertyResult();
			return propResult.getProperty();
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
				new Thread(() -> console.newMessageStream().println(content.toString())).start();
			}
		};
	}

	private void viewCexConsole(final Counterexample cex, final Layout layout, Map<String, EObject> refMap) {
		final MessageConsole console = findConsole("Counterexample");
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
		new Thread(() -> {
			try (MessageConsoleStream out = console.newMessageStream()) {
				for (String category : layout.getCategories()) {
					if (isEmpty(category, cex, layout)) {
						continue;
					}

					printHLine(out, cex.getLength());
					if (category == "") {
						out.println("Variables for the selected component implementation");

					} else {
						out.println("Variables for " + category);
					}
					printHLine(out, cex.getLength());

					out.print(String.format("%-60s", "Variable Name"));
					for (int k1 = 0; k1 < cex.getLength(); k1++) {
						out.print(String.format("%-15s", k1));
					}
					out.println();
					printHLine(out, cex.getLength());

					for (Signal<Value> signal : cex.getCategorySignals(layout, category)) {
						// dont' print out values for properties
						if (signal.getName().contains(":")) {
							continue;
						}
						out.print(String.format("%-60s", "{" + signal.getName() + "}"));
						for (int k2 = 0; k2 < cex.getLength(); k2++) {
							out.print(String.format("%-15s", signal.getValue(k2).toString()));
						}
						out.println();
					}
					out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean isEmpty(String category, Counterexample cex, Layout layout) {
		return cex.getCategorySignals(layout, category).isEmpty();
	}

	public static void printHLine(MessageConsoleStream out, int nVars) {
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
			AgreeCounterexampleView cexView = (AgreeCounterexampleView) window.getActivePage()
					.showView(AgreeCounterexampleView.ID);
			cexView.setInput(cex, layout, refMap);
			cexView.setFocus();
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
