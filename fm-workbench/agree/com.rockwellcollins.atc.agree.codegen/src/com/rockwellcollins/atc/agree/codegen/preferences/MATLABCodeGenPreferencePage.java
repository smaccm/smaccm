package com.rockwellcollins.atc.agree.codegen.preferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rockwellcollins.atc.agree.codegen.Activator;

public class MATLABCodeGenPreferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public MATLABCodeGenPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("MATLAB Function Code Generation Settings");
    }
    
    private static final String[][] INT_TYPES = {
            { PreferenceConstants.INT_INT8, PreferenceConstants.INT_INT8 },
            { PreferenceConstants.INT_UINT8, PreferenceConstants.INT_UINT8 },
            { PreferenceConstants.INT_INT16, PreferenceConstants.INT_INT16 },
            { PreferenceConstants.INT_UINT16, PreferenceConstants.INT_UINT16 },
            { PreferenceConstants.INT_INT32, PreferenceConstants.INT_INT32 },
            { PreferenceConstants.INT_UINT32, PreferenceConstants.INT_UINT32 },
            { PreferenceConstants.INT_INT64, PreferenceConstants.INT_INT64 },
            { PreferenceConstants.INT_UINT64, PreferenceConstants.INT_UINT64 }     
    };
    private ComboFieldEditor intTypeFieldEditor;
    private String selectedIntType;

    private static final String[][] REAL_TYPES = { 
    	{ PreferenceConstants.REAL_SINGLE, PreferenceConstants.REAL_SINGLE },
    	{ PreferenceConstants.REAL_DOUBLE, PreferenceConstants.REAL_DOUBLE } };
    private ComboFieldEditor realTypeFieldEditor;
    private String selectedRealType;

    @Override
    public void createFieldEditors() {
        intTypeFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_INT,
                "Int Data Type in MATLAB", INT_TYPES, getFieldEditorParent());
        addField(intTypeFieldEditor);

        realTypeFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_REAL, "Real Data Type in MATLAB", REAL_TYPES,
                getFieldEditorParent());
        addField(realTypeFieldEditor);
    }
    
    public String getSelectedIntType() {
       return selectedIntType;
    }
    
    public String getSelectedRealType() {
        return selectedRealType;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        super.propertyChange(event);
        if (event.getSource().equals(intTypeFieldEditor)) {
        	selectedIntType = (String) event.getNewValue();
        } else if (event.getSource().equals(realTypeFieldEditor)) {
        	selectedRealType = (String) event.getNewValue();
        }
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        IPreferenceStore prefs = getPreferenceStore();
        selectedIntType = prefs.getDefaultString(PreferenceConstants.PREF_INT);
        selectedRealType = prefs.getDefaultString(PreferenceConstants.PREF_REAL);
    }


    @Override
    protected void initialize() {
        super.initialize();
        initializeStateVariables();
    }

    private void initializeStateVariables() {
        IPreferenceStore prefs = getPreferenceStore();
        selectedIntType = prefs.getString(PreferenceConstants.PREF_INT);
        selectedRealType = prefs.getString(PreferenceConstants.PREF_REAL);
    }

    @Override
    public void init(IWorkbench workbench) {
    }
	

}
