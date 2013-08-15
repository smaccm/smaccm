package aadl2rtos.handlers;

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
public class SampleHandler extends AbstractHandler {
    /**
     * The constructor.
     */
    public SampleHandler() {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        MessageDialog.openInformation(window.getShell(), "Aadl2camkes",
                "This is my change...I hope it works");

        // TODO: FIX HACK!
        // System.out.println("Hello, world!");
        edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosAction action = new edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosAction();
        MessageDialog.openInformation(window.getShell(), "Aadl2camkes",
                "Created an AadlToCamkesAction!");
        action.run(null);

        return null;
    }
}
