package com.rockwellcollins.atc.agree.codegen.handlers;

import org.eclipse.jface.dialogs.IDialogLabelKeys;

public interface ModelInfoDialogLabelKeys extends IDialogLabelKeys {

    /**
     * The key used to retrieve the label for EXPORT buttons. Clients should use the pattern
     * <code>JFaceResources.getString(IDialogLabelKeys.EXPORT_LABEL_KEY)</code> to retrieve the label
     * dynamically when using multiple locales.
     * 
     * @since 3.7
     */
    public String EXPORT_LABEL_KEY = "Script for AGREE Contracts"; //$NON-NLS-1$

    /**
     * The key used to retrieve the label for UPDATE buttons.
     * Clients should use the pattern
     * <code>JFaceResources.getString(IDialogLabelKeys.UPDATE_LABEL_KEY)</code>
     * to retrieve the label dynamically when using multiple locales.
     * @since 3.7
     */
    public String UPDATE_LABEL_KEY = "Script to Insert Observer"; //$NON-NLS-1$
	
	
}
