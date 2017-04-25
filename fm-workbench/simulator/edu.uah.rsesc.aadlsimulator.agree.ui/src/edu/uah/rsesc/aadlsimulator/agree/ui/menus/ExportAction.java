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
package edu.uah.rsesc.aadlsimulator.agree.ui.menus;

import java.util.Collection;
import java.util.Objects;

import jkind.api.results.Renaming;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.results.Signal;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;
import org.osate.aadl2.ComponentImplementation;

import edu.uah.rsesc.aadlsimulator.agree.engine.AGREESimulationEngine;
import edu.uah.rsesc.aadlsimulator.agree.engine.AGREESimulationState;
import edu.uah.rsesc.aadlsimulator.agree.services.AGREESimulatorExtensionService.ExporterProxy;
import edu.uah.rsesc.aadlsimulator.agree.ext.AGREESimulationExporter.ExportArguments;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;

public class ExportAction extends Action {
	final ExporterProxy exporter; 
	
	public ExportAction(final ExporterProxy exporter) {
		super(exporter.getName());
		this.exporter = exporter;
	}
	
	@Override
	public void run() {
		exporter.export(buildExportArguments());
	}

	private ExportArguments buildExportArguments() {
		final SimulationUIService simUiService = (SimulationUIService)Objects.requireNonNull(PlatformUI.getWorkbench().getService(SimulationUIService.class), "Unable to retrieve Simulation UI Service");
		final SimulatorState simulatorState = simUiService.getCurrentState();

		if(!(simulatorState.getEngineState() instanceof AGREESimulationState)) {
			throw new RuntimeException("Simulation engine state must be an AGREESimulationState");
		}
		
		if(!(simulatorState.getSimulationEngine() instanceof AGREESimulationEngine)) {
			throw new RuntimeException("Simulation engine must be an AGREESimulationEngine");
		}

		final AGREESimulationState agreeSimState = (AGREESimulationState)simulatorState.getEngineState();
		final AGREESimulationEngine engine = (AGREESimulationEngine)simulatorState.getSimulationEngine();
		final ComponentImplementation componentImplementation = engine.getSystemInstance().getComponentImplementation();
		
		final Counterexample cex = buildCounterexample(agreeSimState );
		final Renaming renaming = engine.getSimulationProgram().getAgreeRenaming();

		return new ExportArguments() {
			@Override
			public ComponentImplementation getComponentImplementation() {				
				return componentImplementation;
			}

			@Override
			public Counterexample getCounterexample() {
				return cex;
			}

			@Override
			public Renaming getAgreeRenaming() {
				return renaming;
			}			
		};
	}
	
	private static Counterexample buildCounterexample(final AGREESimulationState engineState) {
		assert engineState != null;
		final Counterexample cex = new Counterexample(engineState.getNumberOfFrames());
		populateCounterexample(cex, engineState.getRootElements(), engineState);		
		return cex;
	}
	
	private static void populateCounterexample(final Counterexample cex, final Collection<?> stateElements, final AGREESimulationState engineState) {
		for(final Object element : stateElements) {
			if(!engineState.isElementHidden(element)) {
				final String lustreId = engineState.getElementLustreId(element);
				if(lustreId != null) {
					addElementToCounterexample(cex, element, engineState);
				}
				populateCounterexample(cex, engineState.getChildElements(element), engineState);
			}
		}
	}
	
	private static void addElementToCounterexample(final Counterexample cex, final Object element, final AGREESimulationState engineState) {
		assert engineState.getElementLustreId(element) != null;
		
		// Create the signal
		final Signal<Value> newSignal = new Signal<>(engineState.getElementLustreId(element));
		
		// Populate the signal's values
		final int numberOfFrames = engineState.getNumberOfFrames();
		for(int frameIndex = 0; frameIndex < numberOfFrames; frameIndex++) {
			final Value value = engineState.getElementLustreValue(frameIndex, element);
			if(value != null) {
				newSignal.putValue(frameIndex, value);
			}
		}
		
		// Add signal to the counterexample
		cex.addSignal(newSignal);
	}
}
