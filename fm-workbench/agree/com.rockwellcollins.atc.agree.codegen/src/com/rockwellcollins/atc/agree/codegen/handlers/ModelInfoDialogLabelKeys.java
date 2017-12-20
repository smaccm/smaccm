package com.rockwellcollins.atc.agree.codegen.handlers;

import org.eclipse.jface.dialogs.IDialogLabelKeys;

public interface ModelInfoDialogLabelKeys extends IDialogLabelKeys {

	/**
	 * The key used to retrieve the label for Export Contracts buttons. Clients should use the pattern
	 * <code>JFaceResources.getString(IDialogLabelKeys.EXPORT_LABEL_KEY)</code> to retrieve the label
	 * dynamically when using multiple locales.
	 *
	 * @since 3.7
	 */
	public String EXPORT_LABEL_KEY = "Export Contracts"; //$NON-NLS-1$

	/**
	 * The key used to retrieve the label for Generate Implementation buttons.
	 * Clients should use the pattern
	 * <code>JFaceResources.getString(IDialogLabelKeys.GENERATE_LABEL_KEY)</code>
	 * to retrieve the label dynamically when using multiple locales.
	 * @since 3.7
	 */
	public String GEN_IMPL_LABEL_KEY = "Generate Implementation"; //$NON-NLS-1$

	/**
	 * The key used to retrieve the label for Generate Verification buttons.
	 * Clients should use the pattern
	 * <code>JFaceResources.getString(IDialogLabelKeys.UPDATE_LABEL_KEY)</code>
	 * to retrieve the label dynamically when using multiple locales.
	 * @since 3.7
	 */
	public String GEN_VERIFICATION_LABEL_KEY = "Generate Verification"; //$NON-NLS-1$

	/**
	 * The key used to retrieve the label for VERIFY buttons.
	 * Clients should use the pattern
	 * <code>JFaceResources.getString(IDialogLabelKeys.VERIFY_LABEL_KEY)</code>
	 * to retrieve the label dynamically when using multiple locales.
	 * @since 3.7
	 */
	public String VERIFY_LABEL_KEY = "Verify Subsystem"; //$NON-NLS-1$

}
