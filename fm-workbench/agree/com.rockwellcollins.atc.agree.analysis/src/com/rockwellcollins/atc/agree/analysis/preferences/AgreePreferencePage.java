package com.rockwellcollins.atc.agree.analysis.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.widgets.Composite;
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
public class AgreePreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    public AgreePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Agree Analysis Settings");
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    @Override
    public void createFieldEditors() {
        addField(new BooleanFieldEditor(PreferenceConstants.PREF_INDUCT_CEX,
                "Generate inductive counterexamples", getFieldEditorParent()));
        addField(new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_DEPTH,
                "Maximum depth for k-induction", getFieldEditorParent()));
        addField(new NonNegativeIntegerFieldEditor(PreferenceConstants.PREF_TIMEOUT,
                "Timeout per layer in seconds", getFieldEditorParent()));
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