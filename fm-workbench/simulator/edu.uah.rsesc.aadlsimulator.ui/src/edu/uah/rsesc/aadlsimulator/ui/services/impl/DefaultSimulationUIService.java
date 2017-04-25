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
package edu.uah.rsesc.aadlsimulator.ui.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.ge.services.GraphicalEditorService;
import org.osgi.framework.FrameworkUtil;

import edu.uah.rsesc.aadlsimulator.EngineStateChangedEvent;
import edu.uah.rsesc.aadlsimulator.EngineStateChangedEvent.Cause;
import edu.uah.rsesc.aadlsimulator.NullSimulationEngineEventListener;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.SimulationEngineEventListener;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.services.SimulationEngineChangeListener;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;
import edu.uah.rsesc.aadlsimulator.ui.events.BeforeStepForwardEvent;
import edu.uah.rsesc.aadlsimulator.ui.events.SimulatorUIEvents;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;
import edu.uah.rsesc.aadlsimulator.ui.services.StepForwardCanceledException;

public class DefaultSimulationUIService implements SimulationUIService {
	private class ActiveSimulation {
		public ActiveSimulation(final SimulationEngine engine, final SimulationEngineEventListener stateChangeListener, final IConsole console) {
			this.engine = Objects.requireNonNull(engine, "engine must not be null");
			this.state = engine.getCurrentState();
			this.console = Objects.requireNonNull(console, "console must not be null");
			this.stateChangeListener = Objects.requireNonNull(stateChangeListener, "stateChangeListener must not be null");
		}
		
		public final SimulationEngine engine;
		public SimulationEngineState state;
		public int selectedFrameIndex = SimulatorState.NO_FRAME_INDEX_SELECTED;
		public SimulationEngineEventListener stateChangeListener;
		public IConsole console;
	};	
	
	private final IEventBroker eventBroker;
	private final SimulationService simulationService;
	private final Map<SimulationEngine, ActiveSimulation> simulationEngineToActiveSimulationMap = new HashMap<SimulationEngine, ActiveSimulation>();
	private final Collection<SimulatorStateListener> stateChangeListeners = new CopyOnWriteArrayList<SimulatorStateListener>();
	private ActiveSimulation currentActiveSimulation;
	private volatile SimulatorState simulatorState = new SimpleSimulatorState(null, null, SimulatorState.NO_FRAME_INDEX_SELECTED);
	private volatile boolean uiLocked = false;
	private volatile boolean updatePending = false; // True if the sending of a simulator state change notification is queued
	
	@Inject
	public DefaultSimulationUIService(final IEventBroker eventBroker) {
		this.eventBroker = Objects.requireNonNull(eventBroker, "eventBroker must not be null");
		simulationService = Objects.requireNonNull(EclipseContextFactory.getServiceContext(FrameworkUtil.getBundle(getClass()).getBundleContext()).get(SimulationService.class), "unable to get simulation service");
		simulationService.addSimulationEngineChangeListener(new SimulationEngineChangeListener() {		
			@Override
			public void onSimulationEngineCreated(final SimulationEngine engine) {
				if(currentActiveSimulation != null) {
					throw new RuntimeException("Only one simulation may run at a time.");
				}
				
				setCurrentSimulationEngine(engine);
				
				// When a simulation engine is created, load the system instance in the OSATE Graphical Editor
				final SystemInstance systemInstance = engine.getSystemInstance();
				if(systemInstance != null) {
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							final GraphicalEditorService editorService = Objects.requireNonNull((GraphicalEditorService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(GraphicalEditorService.class), "unable to retrieve Graphical Editor Service");
							editorService.openBusinessObject(systemInstance);
						}						
					});
				}
			}
			
			@Override
			public void onSimulationEngineDisposed(final SimulationEngine engine) {
				setCurrentSimulationEngine(null);
			}
		});
	}
	
	@Override
	public SimulatorState getCurrentState() {
		return simulatorState;
	}
	
	private synchronized void setCurrentSimulationEngine(final SimulationEngine newCurrentSimulationEngine) {
		// Ensure simulation is not locked
		this.uiLocked = false;
		
		// Unregister simulation engine and the frame state change listener
		if(currentActiveSimulation != null) {
			currentActiveSimulation.engine.removeEventListener(currentActiveSimulation.stateChangeListener);
		    ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[]{currentActiveSimulation.console});				
		    simulationEngineToActiveSimulationMap.remove(currentActiveSimulation.engine);
		}
		
		// Adjust the active simulation engine
		if(newCurrentSimulationEngine == null) {
			currentActiveSimulation = null;
		} else {
			// Create a console for the simulation engine
			final String consoleName = "AADL Simulator";
		    final MessageConsole newConsole = new MessageConsole(consoleName, null);
		    ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{newConsole});
		    final MessageConsoleStream consoleStream = newConsole.newMessageStream();
		    consoleStream.println("Simulation Started");
		    
			final SimulationEngineEventListener engineEventListener = new NullSimulationEngineEventListener() {
				@Override
				public void onSimulationEngineStateChanged(final EngineStateChangedEvent e) {
					final int newSelectedFrameIndex;
					if(e.getCause() == Cause.STEP_FORWARD || e.getCause() == Cause.STEP_BACKWARD) {						
						newSelectedFrameIndex = e.getEngineState().getNumberOfFrames() == 0 ? SimulatorState.NO_FRAME_INDEX_SELECTED : (e.getEngineState().getNumberOfFrames() - 1);
					} else {
						newSelectedFrameIndex = simulatorState.getSelectedFrameIndex();
					}

					updateActivateSimulation(newCurrentSimulationEngine, e.getEngineState(), newSelectedFrameIndex);					
				}
			};
			
			currentActiveSimulation = new ActiveSimulation(newCurrentSimulationEngine, engineEventListener, newConsole);
			simulationEngineToActiveSimulationMap.put(newCurrentSimulationEngine, currentActiveSimulation);
			newCurrentSimulationEngine.addEventListener(engineEventListener);
		}
		
		updateCurrentState();
	}
	
	private synchronized void updateActivateSimulation(final SimulationEngine engine, final SimulationEngineState state, final int newSelectedFrameIndex) {
		final ActiveSimulation activeSimulation = simulationEngineToActiveSimulationMap.get(engine);
		if(activeSimulation != null) {
			activeSimulation.state = state;
			activeSimulation.selectedFrameIndex = newSelectedFrameIndex;
		}
		updateCurrentState();
	}
	
	private void updateCurrentState() {
		// Update the simulator state variable
		if(currentActiveSimulation == null) {
			simulatorState = new SimpleSimulatorState(null, null, SimulatorState.NO_FRAME_INDEX_SELECTED);
		} else {
			simulatorState = new SimpleSimulatorState(currentActiveSimulation.engine, currentActiveSimulation.state, currentActiveSimulation.selectedFrameIndex);
		}
		
		// Notify listeners in the display thread. In cases where many state changes occur in a short amount of time, do not notify UI listeners of all changes. 
		// Guaranteed to notify listeners of the first and last change in a sequence.
		final Runnable notificationRunnable = new Runnable() {
			@Override
			public void run() {
				updatePending = false;
				
				// Notify simulation engine change listeners
				for(final SimulatorStateListener l : stateChangeListeners) {
					l.onSimulatorStateChanged(simulatorState);
				}					
			}				
		};
		
		if(!updatePending) {
			updatePending = true;
			Display.getDefault().asyncExec(notificationRunnable);
		}
	}
	
	@Override
	public void addStateChangeListener(final SimulatorStateListener listener) {
		Objects.requireNonNull(listener, "listener must not be null");
		stateChangeListeners.add(listener);
	}
	
	@Override
	public void removeStateChangeListener(final SimulatorStateListener listener) {
		Objects.requireNonNull(listener, "listener must not be null");
		stateChangeListeners.remove(listener);
	}
	
	@Override 
	public boolean canStepForward() {
		return currentActiveSimulation != null && currentActiveSimulation.state.canStepForward() && !uiLocked;
	}
	
	@Override 
	public boolean canStepBackward() {
		return currentActiveSimulation != null && currentActiveSimulation.state.canStepBackward() && !uiLocked;
	}	
	
	public void prepareToStepForward() throws StepForwardCanceledException {
		final BeforeStepForwardEvent beforeStepForwardEvent = new BeforeStepForwardEvent();
		eventBroker.send(SimulatorUIEvents.BEFORE_STEP_FORWARD, beforeStepForwardEvent);
		
		if(beforeStepForwardEvent.isStepCanceled()) {
			throw new StepForwardCanceledException(beforeStepForwardEvent.getCancellationReason());
		}
	}

	@Override
	public void stepForward() {
		Objects.requireNonNull(currentActiveSimulation, "No active simulation");

		try {
			prepareToStepForward();
			currentActiveSimulation.engine.stepForward();
		} catch(StepForwardCanceledException ex) {
			final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "Unable to Step Forward", ex);
			StatusManager.getManager().handle(status, StatusManager.SHOW);
		}
	}	
	
	@Override
	public synchronized void lockUserInterface() {
		this.uiLocked = true;
		updateCurrentState();
	}
	
	@Override
	public synchronized void unlockUserInterface() {
		this.uiLocked = false;
		updateCurrentState();
	}
	
	@Override
	public boolean isUserInterfaceLocked() {
		return this.uiLocked;
	}	

	@Override
	public synchronized void setSelectedFrameIndex(final int newFrameIndex) {
		if(currentActiveSimulation != null && newFrameIndex != currentActiveSimulation.selectedFrameIndex) {
			currentActiveSimulation.selectedFrameIndex = newFrameIndex;
			updateCurrentState();
		}
	}
}
