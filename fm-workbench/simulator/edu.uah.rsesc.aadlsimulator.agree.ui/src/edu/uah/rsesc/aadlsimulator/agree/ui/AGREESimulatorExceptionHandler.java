/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator.agree.ui;

import java.io.IOException;
import java.io.PrintWriter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.agree.sim.AGREESimulatorException;

public class AGREESimulatorExceptionHandler implements ExceptionHandler {
	private final static int maxErrorMsgSize = 400;
	
	@Override
	public void handleException(final Exception ex) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				final String fullMsg = ex.getLocalizedMessage();
				final String errorMsg = fullMsg.length() > maxErrorMsgSize ? fullMsg.substring(0, maxErrorMsgSize) : fullMsg;
				final MessageDialog dlg = new MessageDialog(shell, "AGREE Simulator Error", null, errorMsg, MessageDialog.ERROR, new String[] {"OK", "Save Stack trace...", "Save Lustre..."}, 0) {
					@Override
					protected void buttonPressed(final int buttonId) {
						switch(buttonId) {
						case 0:
							super.buttonPressed(buttonId);
							break;
							
						case 1:
							handleSaveStackTrace(getShell(), ex);	
							break;
							
						case 2:
							LustreProgramSaver.handleSave((AGREESimulatorException)ex);				
							break;
						}
					}
				};
				dlg.open();
			}
			
			private void handleSaveStackTrace(final Shell shell, final Exception ex) {
				// Prompt the user for a filepath
				final FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
				fileDialog.setText("Save Stack trace");
				fileDialog.setFilterExtensions(new String[] { "*.txt" });
				fileDialog.setFilterNames(new String[] { "Text Files(*.txt)" });
				final String filepath = fileDialog.open();
				if(filepath != null) {
					try(final PrintWriter writer = new PrintWriter(filepath)) {
						ex.printStackTrace(writer);
					} catch (IOException ioException) {
						throw new RuntimeException(ioException);
					}
				}
			}
		});
	}
}
