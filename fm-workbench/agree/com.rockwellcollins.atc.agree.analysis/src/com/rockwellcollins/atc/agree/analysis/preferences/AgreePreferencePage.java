package com.rockwellcollins.atc.agree.analysis.preferences;

import java.awt.Desktop;
import java.io.File;

import jkind.api.KindApi;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rockwellcollins.atc.agree.analysis.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */
public class AgreePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public AgreePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Agree Analysis Settings");
    }

    private static final String[][] MODEL_CHECKERS = {
            { PreferenceConstants.MODEL_CHECKER_JKIND, PreferenceConstants.MODEL_CHECKER_JKIND },
            { PreferenceConstants.MODEL_CHECKER_KIND2, PreferenceConstants.MODEL_CHECKER_KIND2 },
            { PreferenceConstants.MODEL_CHECKER_KIND2WEB, PreferenceConstants.MODEL_CHECKER_KIND2WEB } };
    private ComboFieldEditor modelCheckerFieldEditor;
    private String selectedModelChecker;

    private StringFieldEditor remoteUrlFieldEditor;

    private static final String[][] SOLVERS =
            { { PreferenceConstants.SOLVER_YICES, PreferenceConstants.SOLVER_YICES },
                    { PreferenceConstants.SOLVER_Z3, PreferenceConstants.SOLVER_Z3 },
                    { PreferenceConstants.SOLVER_CVC4, PreferenceConstants.SOLVER_CVC4 },
                    { PreferenceConstants.SOLVER_YICES2, PreferenceConstants.SOLVER_YICES2 } };
    private ComboFieldEditor solverFieldEditor;
    private String selectedSolver;

    private BooleanFieldEditor inductiveCounterexampleFieldEditor;
    private BooleanFieldEditor noKInductionEditor;
    private BooleanFieldEditor smoothingFieldEditor;
    private BooleanFieldEditor generalizeFieldEditor;
    private NonNegativeIntegerFieldEditor depthFieldEditor;
    private NonNegativeIntegerFieldEditor timeoutFieldEditor;
    private NonNegativeIntegerFieldEditor pdrMaxEditor;
    private NonNegativeIntegerFieldEditor consistDepthEditor;
    private BooleanFieldEditor debugFieldEditor;

    @Override
    public void createFieldEditors() {
        modelCheckerFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_MODEL_CHECKER,
                "Model Checker", MODEL_CHECKERS, getFieldEditorParent());
        addField(modelCheckerFieldEditor);

        remoteUrlFieldEditor = new StringFieldEditor(PreferenceConstants.PREF_REMOTE_URL, "Remote URL",
                getFieldEditorParent());
        addField(remoteUrlFieldEditor);

        solverFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_SOLVER, "SMT Solver", SOLVERS,
                getFieldEditorParent());
        addField(solverFieldEditor);

        noKInductionEditor = new BooleanFieldEditor(PreferenceConstants.PREF_NO_KINDUCTION,
                "Disable K-Induction", getFieldEditorParent());
        addField(noKInductionEditor);

        inductiveCounterexampleFieldEditor = new BooleanFieldEditor(PreferenceConstants.PREF_INDUCT_CEX,
                "Generate inductive counterexamples", getFieldEditorParent());
        addField(inductiveCounterexampleFieldEditor);

        smoothingFieldEditor = new BooleanFieldEditor(PreferenceConstants.PREF_SMOOTH_CEX,
                "Generate smooth counterexamples (minimal number of input value changes)",
                getFieldEditorParent());
        addField(smoothingFieldEditor);

        generalizeFieldEditor = new BooleanFieldEditor(PreferenceConstants.PREF_BLAME_CEX,
                "Generate blamed counterexamples (generalized counter examples)", getFieldEditorParent());
        addField(generalizeFieldEditor);

        depthFieldEditor = new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_DEPTH,
                "Maximum depth for k-induction", getFieldEditorParent());
        addField(depthFieldEditor);

        pdrMaxEditor = new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_PDR_MAX,
                "Maximum Number of PDR Instances", getFieldEditorParent());
        addField(pdrMaxEditor);

        timeoutFieldEditor = new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_TIMEOUT,
                "Timeout in seconds", getFieldEditorParent());
        addField(timeoutFieldEditor);

        consistDepthEditor = new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_CONSIST_DEPTH,
                "Depth to check consistency up to", getFieldEditorParent());
		addField(consistDepthEditor);

		debugFieldEditor = new BooleanButtonFieldEditor(PreferenceConstants.PREF_DEBUG,
				"Debug mode (record log files)", "Open temporary folder", this::openTemporaryFolder,
				getFieldEditorParent());
		addField(debugFieldEditor);
        
        Button checkAvailableButton = new Button(getFieldEditorParent(), SWT.PUSH);
        checkAvailableButton.setText("Check if available");
        checkAvailableButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                checkAvailable();
            }
        });
    }

    private void openTemporaryFolder() {
    	Desktop desktop = Desktop.getDesktop();
        try {
        	File tempFile = File.createTempFile("agree", ".tmp");
        	tempFile.delete();
			File tempDir = tempFile.getParentFile();
        	desktop.open(tempDir);
        } catch (Throwable t) {
            MessageDialog.openError(getShell(), "Error opening temporary directory",
                    "Error opening temporary directory: " + t.getMessage());
        }
    }
    
    private void checkAvailable() {
        try {
            String remoteUrl = remoteUrlFieldEditor.getStringValue();
            KindApi api = PreferencesUtil.getKindApi(selectedModelChecker, remoteUrl);
            String details = api.checkAvailable();
            MessageDialog.openInformation(getShell(), "Model checker available", details);
        } catch (Throwable t) {
            MessageDialog.openError(getShell(), "Error running model checker",
                    "Error running model checker: " + t.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
        if (event.getSource().equals(solverFieldEditor)) {
            selectedSolver = (String) event.getNewValue();
        } else if (event.getSource().equals(modelCheckerFieldEditor)) {
            selectedModelChecker = (String) event.getNewValue();
        }
        configureEnabledFieldEditors();
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        IPreferenceStore prefs = getPreferenceStore();
        selectedSolver = prefs.getDefaultString(PreferenceConstants.PREF_SOLVER);
        selectedModelChecker = prefs.getDefaultString(PreferenceConstants.PREF_MODEL_CHECKER);
        configureEnabledFieldEditors();
    }

    private void configureEnabledFieldEditors() {
        boolean isJKind = selectedModelChecker.equals(PreferenceConstants.MODEL_CHECKER_JKIND);
        boolean isRemote = selectedModelChecker.equals(PreferenceConstants.MODEL_CHECKER_KIND2WEB);
        boolean isYices = selectedSolver.equals(PreferenceConstants.SOLVER_YICES);

        remoteUrlFieldEditor.setEnabled(isRemote, getFieldEditorParent());
        solverFieldEditor.setEnabled(isJKind, getFieldEditorParent());
        inductiveCounterexampleFieldEditor.setEnabled(isJKind, getFieldEditorParent());
        smoothingFieldEditor.setEnabled(isJKind && isYices, getFieldEditorParent());
        noKInductionEditor.setEnabled(isJKind, getFieldEditorParent());
        pdrMaxEditor.setEnabled(isJKind, getFieldEditorParent());
        generalizeFieldEditor.setEnabled(isJKind, getFieldEditorParent());
        depthFieldEditor.setEnabled(isJKind, getFieldEditorParent());
    }

    @Override
    protected void initialize() {
        super.initialize();
        initializeStateVariables();
        configureEnabledFieldEditors();
    }

    private void initializeStateVariables() {
        IPreferenceStore prefs = getPreferenceStore();
        selectedSolver = prefs.getString(PreferenceConstants.PREF_SOLVER);
        selectedModelChecker = prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER);
    }

    private class NonNegativeIntegerFieldEditor extends IntegerFieldEditor {
        public NonNegativeIntegerFieldEditor(String name, String labelText, Composite parent) {
            super(name, labelText, parent);
            setValidRange(0, Integer.MAX_VALUE);
            setErrorMessage("Field must be a non-negative integer");
        }
    }

    @Override
    public void init(IWorkbench workbench) {
    }
}