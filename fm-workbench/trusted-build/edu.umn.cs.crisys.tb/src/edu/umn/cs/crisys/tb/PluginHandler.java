package edu.umn.cs.crisys.tb;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class PluginHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public PluginHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "trusted_build",
				"This is my change...I hope it works");

		// TODO: FIX HACK!
		edu.umn.cs.crisys.tb.TbAction action = new edu.umn.cs.crisys.tb.TbAction();
		MessageDialog.openInformation(window.getShell(), "trusted_build",
				"Created an AadlToRTOSAction!");
		action.run(null);

		return null;
	}
}
