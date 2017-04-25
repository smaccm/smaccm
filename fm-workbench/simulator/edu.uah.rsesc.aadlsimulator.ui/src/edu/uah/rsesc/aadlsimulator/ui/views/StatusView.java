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
package edu.uah.rsesc.aadlsimulator.ui.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;

public class StatusView {
	private final SimulationUIService simulationUiService;
	
	private final SimulatorStateListener stateListener = new SimulatorStateListener() {
		@Override
		public void onSimulatorStateChanged(final SimulatorState simulatorState) {
			final SimulationEngine engine = simulatorState.getSimulationEngine();
			if(engine == null) {
				container.setVisible(false);
			} else {
				container.setVisible(true);
				componentImplementationLabel.setText(engine.getSystemInstance().getComponentImplementation().getQualifiedName());
				typeLabel.setText(engine.getSimulationType());
				statusLabel.setText(engine.getCurrentState().getStatusDescription());
			}			
			
			final SimulationEngineState engineState = simulatorState.getEngineState();
			statusLabel.setForeground(engineState != null && engineState.isStatusError() ? Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED) : null);
			
			container.layout();
		}		
	};
	
	private Composite container;	
	private Label componentImplementationLabel;
	private Label typeLabel;
	private Label statusLabel;

	@Inject
	public StatusView(final SimulationUIService simulationUiService) {
		this.simulationUiService = simulationUiService;
	}

	@PostConstruct
	void createView(final Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setVisible(false);
		final GridLayout gridLayout = new GridLayout(2, false);		
		container.setLayout(gridLayout);
		
		addLabel(container, "Component");
		componentImplementationLabel = addWrappingLabel(container, "");

		addLabel(container, "Type");
		typeLabel = addWrappingLabel(container, "");

		addLabel(container, "Status");
		statusLabel = addWrappingLabel(container, "");
		
		container.layout();
		
		// Add listener and populate UI with current state
		simulationUiService.addStateChangeListener(stateListener);
		stateListener.onSimulatorStateChanged(simulationUiService.getCurrentState());
	}

	@PreDestroy
	public void preDestroy() {
		simulationUiService.removeStateChangeListener(stateListener);
	}
	
	private Label addLabel(final Composite parent, final String txt) {
		final Label label = new Label(parent, SWT.NONE);
		GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.TOP).applyTo(label);
		label.setText(txt);
		return label;
	}
	
	private Label addWrappingLabel(final Composite parent, final String txt) {
		final Label label = new Label(parent, SWT.WRAP);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(label);			
		label.setText(txt);
		return label;
	}
}
