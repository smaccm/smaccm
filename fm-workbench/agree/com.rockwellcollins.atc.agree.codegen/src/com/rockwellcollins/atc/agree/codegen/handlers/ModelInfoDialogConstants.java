package com.rockwellcollins.atc.agree.codegen.handlers;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;

public interface ModelInfoDialogConstants extends IDialogConstants {

    /**
     * Button id for a "Export Script" button (value 257).
     */
    public int EXPORT_SCRIPT_ID = 257;
    
    /**
     * Button id for a "Update Model" button (value 258).
     */
    public int UPDATE_MODEL_ID = 258;
    
    
    /**
     * Button id for a "Verify Subsystem" button (value 259).
     */
    public int VERIFY_SUBSYSTEM_ID = 259;
    
 
    
    /**
     * The label for export buttons.
     * Using this static label string provides optimum performance by looking
     * up the label only once when JFace is initialized.  However, clients that 
     * wish to support multiple locales in one system should instead use the pattern
     * <code>JFaceResources.getString(IDialogLabelKeys.EXPORT_LABEL_KEY)</code>
     * so that a locale other than the default may be consulted.
     */
    public String EXPORT_LABEL = JFaceResources.getString(ModelInfoDialogLabelKeys.EXPORT_LABEL_KEY);
	
    
    /**
     * The label for update buttons.
     * Using this static label string provides optimum performance by looking
     * up the label only once when JFace is initialized.  However, clients that 
     * wish to support multiple locales in one system should instead use the pattern
     * <code>JFaceResources.getString(IDialogLabelKeys.UPDATE_LABEL_KEY)</code>
     * so that a locale other than the default may be consulted.
     */
    public String UPDATE_LABEL = JFaceResources.getString(ModelInfoDialogLabelKeys.UPDATE_LABEL_KEY);
	
    
    
    /**
     * The label for verify buttons.
     * Using this static label string provides optimum performance by looking
     * up the label only once when JFace is initialized.  However, clients that 
     * wish to support multiple locales in one system should instead use the pattern
     * <code>JFaceResources.getString(IDialogLabelKeys.VERIFY_LABEL_KEY)</code>
     * so that a locale other than the default may be consulted.
     */
    public String VERIFY_LABEL = JFaceResources.getString(ModelInfoDialogLabelKeys.VERIFY_LABEL_KEY);
	
    
	
}
