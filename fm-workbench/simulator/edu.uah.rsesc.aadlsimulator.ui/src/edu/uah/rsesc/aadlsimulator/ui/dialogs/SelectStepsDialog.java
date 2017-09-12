/*
Copyright (c) 2016, Rockwell Collins.
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
package edu.uah.rsesc.aadlsimulator.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

public class SelectStepsDialog extends Dialog {
	private int steps;

	public SelectStepsDialog(final Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.ON_TOP | SWT.BORDER | SWT.TITLE | SWT.RESIZE | SWT.APPLICATION_MODAL);
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite flowSegmentComposite = (Composite)super.createDialogArea(parent);
		GridLayout layout = (GridLayout)flowSegmentComposite.getLayout();
		layout.marginLeft = 10;
		layout.marginTop = 5;

		final Composite stepsComposite = new Composite(flowSegmentComposite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(stepsComposite);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).grab(true, true).applyTo(stepsComposite);

		// Label
		final Label stepsLbl = new Label(stepsComposite, SWT.NONE);
		stepsLbl.setText("Steps: ");
		GridDataFactory.fillDefaults().applyTo(stepsLbl);

		// Spinner
		final Spinner spinner = new Spinner(stepsComposite, SWT.NONE);
		spinner.setSelection(10);
		spinner.setMaximum(Integer.MAX_VALUE);
		spinner.setMinimum(1);
		GridDataFactory.fillDefaults().hint(50, SWT.DEFAULT).applyTo(spinner);

		steps = spinner.getSelection();
		spinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				steps = spinner.getSelection();
			}
		});

		return flowSegmentComposite;
	}

	@Override
	protected void configureShell(final Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Enter Number of Steps");
		newShell.setSize(250, 140);
		newShell.setMinimumSize(250, 140);

		// Center dialog in window
		final Rectangle screenBounds = Display.getDefault().getActiveShell().getBounds();
		newShell.setLocation(((screenBounds.x + screenBounds.x+screenBounds.width)/2)-95, ((screenBounds.y + screenBounds.y+screenBounds.height)/2)-70);
	}

	@Override
	protected Control createButtonBar(final Composite parent) {
		final Control buttonControl = super.createButtonBar(parent);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(buttonControl);
		
		return buttonControl;
	}

	@Override
	protected Button createButton(final Composite parent, final int id, final String label, final boolean defaultButton) {
		final Button btn = super.createButton(parent, id, label, defaultButton);
		final GridData gridData = (GridData)btn.getLayoutData();
		gridData.widthHint = id == Dialog.OK ? 40 : 50;
		gridData.horizontalAlignment = SWT.END;
		btn.setLayoutData(gridData);

		return btn;
	}
	
	public Integer getSteps() {
		return steps;
	}
}