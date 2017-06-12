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
package edu.uah.rsesc.aadlsimulator.agree.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import jkind.lustre.Expr;
import org.osate.aadl2.instance.SystemInstance;
import edu.uah.rsesc.aadlsimulator.EngineStateChangedEvent;
import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.NotificationHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngineEventListener;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineNotification;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import edu.uah.rsesc.aadlsimulator.agree.sim.Simulation;
import edu.uah.rsesc.aadlsimulator.agree.sim.SimulationState;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.agree.sim.AGREESimulatorException;
import edu.uah.rsesc.aadlsimulator.agree.sim.ConstraintEvaluationException;

/**
 * Thread safe class for executing that manages the execution of a simulation
 *
 */
public class AGREESimulationEngine implements SimulationEngine {
	public static final String ENGINE_TYPE_ID_MONOLITHIC = "edu.uah.rsesc.aadlsimulator.agree.engine.monolithic";
	public static final String ENGINE_TYPE_ID_SINGLE_LAYER = "edu.uah.rsesc.aadlsimulator.agree.engine.singleLayer";
	
	// The following variants are intended to allow simulating inductive counterexamples. They assume that the first simulation step is not the initial state.
	public static final String ENGINE_TYPE_ID_MONOLITHIC_INDUCTIVE = "edu.uah.rsesc.aadlsimulator.agree.engine.monolithicInductive";
	public static final String ENGINE_TYPE_ID_SINGLE_LAYER_INDUCTIVE = "edu.uah.rsesc.aadlsimulator.agree.engine.singleLayerInductive";
	
	// Commands
	private static interface Command {		
	}
	
	private static class StepForwardCommand implements Command {
	}
	
	private static class StepBackwardCommand implements Command {		
	}
	
	private static class NotifyCommand implements Command {
		private final NotificationHandler handler;
		
		public NotifyCommand(final NotificationHandler handler) {
			this.handler = Objects.requireNonNull(handler, "handler must not be null");
		}
	}
	
	private static class SetPropertyEnabledCommand implements Command {
		private final SimulationProperty property;
		private final boolean enabled;
		
		public SetPropertyEnabledCommand(final SimulationProperty property, final boolean enabled) {
			this.property = Objects.requireNonNull(property, "property must not be null");
			this.enabled = enabled;
		}
	}
	
	private static class SetElementInputConstraintCommand implements Command {
		private final AGREESimulationStateElement element;
		private final InputConstraint value;
		
		public SetElementInputConstraintCommand(final AGREESimulationStateElement element, final InputConstraint value) {
			this.element = Objects.requireNonNull(element, "property must not be null");
			this.value = value;
		}
	}
	
	private static class ResetInputConstraintsCommand implements Command {		
	}
	
	// Runnable
	private static class SimulationRunnable implements Runnable {		
		private final BlockingQueue<Command> commandQueue = new LinkedBlockingQueue<Command>();
		private final Collection<SimulationEngineEventListener> eventListeners;
		private final Simulation simulation;
		private final ExceptionHandler exceptionHandler;
		private final LinkedList<FrameInfo> frameInfos; // Stack for storing frame info for each simulation frame. Newer frame info is stored at the end.		
		private final Set<SimulationProperty> disabledProperties;
		private final Map<AGREESimulationStateElement, InputConstraint> elementToNextInputConstraintMap; // A mapping from elements to the input constraint for the next simulation frame
		private final Map<AGREESimulationStateElement, Expr> elementToNextLustreConstraintMap; // Same as elementToNextInputConstraintMap but contains the equivalent lustre expression
		private volatile AGREESimulationState currentState;
		private final InputConstraintToLustreConstraintExpression inputConstraintToLustreConstraintConverter = new InputConstraintToLustreConstraintExpression(new SimulationStateReferenceEvaluator(() -> currentState));
		
		public SimulationRunnable(final Simulation simulation, final ExceptionHandler exceptionHandler) {
			assert simulation != null;
			assert exceptionHandler != null;
			this.eventListeners = new CopyOnWriteArrayList<SimulationEngineEventListener>();
			this.simulation = simulation;
			this.exceptionHandler = exceptionHandler;
			this.frameInfos = new LinkedList<FrameInfo>();
			this.disabledProperties = new HashSet<>();
			this.elementToNextInputConstraintMap = new HashMap<>();
			this.elementToNextLustreConstraintMap = new HashMap<>();
			final List<AGREESimulationStateElement> stateElements = AGREESimulationStateElementFactory.createStateElements(simulation.getProgram());
			final SimulationState simState = simulation.getState();
			this.currentState = new AGREESimulationState(simulation.getProgram(), simState.toString(), simState.isError(), simulation.canStepForward(), simulation.canStepBackward(), stateElements, frameInfos);
		}
		
		/**
		 * Makes a copy of a simulation engine runnable with the specified listeners
		 * @param engine
		 * @param listeners
		 */
		public SimulationRunnable(final SimulationRunnable runnable, final Collection<SimulationEngineEventListener> listeners) {
			this.commandQueue.addAll(runnable.commandQueue);
			this.eventListeners = new CopyOnWriteArrayList<SimulationEngineEventListener>(listeners);
			this.simulation = new Simulation(runnable.simulation);
			this.exceptionHandler = runnable.exceptionHandler;
			this.frameInfos = new LinkedList<>(runnable.frameInfos);
			this.disabledProperties = new HashSet<>(runnable.disabledProperties);
			this.elementToNextInputConstraintMap = new HashMap<>(runnable.elementToNextInputConstraintMap);
			this.elementToNextLustreConstraintMap = new HashMap<>(runnable.elementToNextLustreConstraintMap);
			this.currentState = runnable.currentState;
		}
		
		public void addEventListener(final SimulationEngineEventListener listener) {
			assert listener != null;
			eventListeners.add(listener);
		}
		
		public void removeEventListener(final SimulationEngineEventListener listener) {
			assert listener != null;
			eventListeners.remove(listener);
		}

		private void sendStateChangeEvent(final EngineStateChangedEvent.Cause cause) {			
			// Notify listeners of the state change
			for(final SimulationEngineEventListener l : eventListeners) {
				l.onSimulationEngineStateChanged(new EngineStateChangedEvent(cause, currentState));
			}
		}
		
		@Override
		public void run() {
			try {		
				// Process commands
				while(!Thread.currentThread().isInterrupted()) {
					try {
						final Command cmd = commandQueue.take();
						
						// Process the command
						if(cmd instanceof StepForwardCommand) {
							if(simulation.canStepForward()) {
								final StepArguments stepArguments = createStepArguments();
								
								try {
									updateStateFrameStartAndSendEvent(stepArguments);
									simulation.stepForward(elementToNextLustreConstraintMap.values(), disabledProperties);									
									this.frameInfos.addLast(new FrameInfo(stepArguments, simulation.getLastResults()));

									// Update the state before rebuilding the lustre constraints so that pre expressions will be evaluated properly.
									updateStateWithNewFrameInfo();
									
									// If the simulation can't be stepped forwards, remove input constraints
									if(!currentState.canStepForward()) {
										elementToNextInputConstraintMap.clear();
									}
									rebuildNextLustreConstraintMap(); // Ensure that expressions which reference random variables are re-evaluated
									
									// Notify listeners of state change									
									updateStateWithNewFrameInfoAndSendEvent(EngineStateChangedEvent.Cause.STEP_FORWARD);
								} catch(ConstraintEvaluationException ex) {
									currentState = new AGREESimulationState(currentState, ex.getMessage(), true, simulation.canStepForward(), simulation.canStepBackward(), frameInfos, stepArguments);
									sendStateChangeEvent(EngineStateChangedEvent.Cause.ERROR);
								}
							}
						} else if(cmd instanceof StepBackwardCommand) {
							// Ignore the command if stepping backward is not an option. 
							if(simulation.canStepBackward()) {
								updateStateFrameStartAndSendEvent(null);
								simulation.stepBackward();
								
								final FrameInfo removedFrameInfo = frameInfos.removeLast();
								
								// Restore the input constraints from the previous frame
								elementToNextInputConstraintMap.clear();
								elementToNextInputConstraintMap.putAll(removedFrameInfo.getStepArguments().getElementToNextInputConstraintMap());
								rebuildNextLustreConstraintMap();
								
								// Notify listeners of state change
								updateStateWithNewFrameInfoAndSendEvent(EngineStateChangedEvent.Cause.STEP_BACKWARD);
							}
						} else if(cmd instanceof NotifyCommand) {
							// Call the notification handler
							((NotifyCommand) cmd).handler.handleNotification(new SimulationEngineNotification(currentState));
							updateStateFrameNotificationHandledAndSendEvent();
						} else if(cmd instanceof SetPropertyEnabledCommand) {
							final SetPropertyEnabledCommand setEnabledCmd = (SetPropertyEnabledCommand)cmd;
							if(setEnabledCmd.enabled) {
								disabledProperties.remove(setEnabledCmd.property);
							} else {
								disabledProperties.add(setEnabledCmd.property);
							}

							updateStateWithNewDisabledProperties();
						} else if(cmd instanceof SetElementInputConstraintCommand) {
							final SetElementInputConstraintCommand setConstraintCmd = (SetElementInputConstraintCommand)cmd;

							// Update the constraints for the next frame as appropriate
							final AGREESimulationStateElement element = setConstraintCmd.element;
							final InputConstraint ic = setConstraintCmd.value;
							if(ic == null) {
								elementToNextInputConstraintMap.remove(element);
								elementToNextLustreConstraintMap.remove(element);
							} else {
								elementToNextInputConstraintMap.put(element, ic);
								elementToNextLustreConstraintMap.put(element, createLustreConstraintExpression(ic, element));
							}
							
							updateStateWithNewConsraints();
						} else if(cmd instanceof ResetInputConstraintsCommand) {
							elementToNextInputConstraintMap.clear();
							elementToNextLustreConstraintMap.clear();
							updateStateWithNewConsraints();
						}
						
					} catch (final InterruptedException e) {
						// Stop the thread
						Thread.currentThread().interrupt();
					}
				};
			} catch(final Exception ex) {
				// Report exception to exception handler
				if(ex instanceof AGREESimulatorException) {
					exceptionHandler.handleException(ex);
				} else {
					// Add the lustre program if the exception is not an AGREESimulatorException 
					exceptionHandler.handleException(new AGREESimulatorException(simulation == null ? null : simulation.getProgram().getLustreProgram(), ex));
				}				
				
				// Update status and notify listeners
				final SimulationState simState = simulation.getState();
				currentState = new AGREESimulationState(currentState, simState.toString(), simState.isError(), false, false, frameInfos, createStepArguments());
				sendStateChangeEvent(EngineStateChangedEvent.Cause.ERROR);
				
				// Stop the thread
				Thread.currentThread().interrupt();
			}
		}				
		
		private void updateStateFrameStartAndSendEvent(final StepArguments stepArguments) {
			currentState = new AGREESimulationState(currentState, "Simulating", false);
			sendStateChangeEvent(EngineStateChangedEvent.Cause.FRAME_START);
		}
		
		private void updateStateFrameNotificationHandledAndSendEvent() {
			sendStateChangeEvent(EngineStateChangedEvent.Cause.NOTIFICATION);
		}
		
		private void updateStateWithNewDisabledProperties() {
			currentState = new AGREESimulationState(currentState, disabledProperties);
			sendStateChangeEvent(EngineStateChangedEvent.Cause.PROPERTY_ENABLEMENT_CHANGED);
		}
		
		private void updateStateWithNewConsraints() {
			currentState = new AGREESimulationState(currentState, createStepArguments());
			sendStateChangeEvent(EngineStateChangedEvent.Cause.CONSTRAINTS_CHANGED);
		}
		
		/**
		 * Updates the simulation state with the current frame infos and step arguments and notifies listeners
		 * @param cause
		 */
		private void updateStateWithNewFrameInfoAndSendEvent(final EngineStateChangedEvent.Cause cause) {
			updateStateWithNewFrameInfo();
			sendStateChangeEvent(cause);
		}

		private void updateStateWithNewFrameInfo() {
			final SimulationState simState = simulation.getState();
			currentState = new AGREESimulationState(currentState, simState.toString(), simState.isError(), simulation.canStepForward(), simulation.canStepBackward(), frameInfos, createStepArguments());
		}
		
		private StepArguments createStepArguments() {
			return new StepArguments(elementToNextInputConstraintMap, elementToNextLustreConstraintMap);
		}
				
		/**
		 * Clears and completely rebuilds the element to next lustre constraint map based on the elmeent to next input constraint map
		 */
		private void rebuildNextLustreConstraintMap() {
			// Create constraints based on the value providers
			elementToNextLustreConstraintMap.clear();
			for(Entry<AGREESimulationStateElement, InputConstraint> inputConstraintEntry : elementToNextInputConstraintMap.entrySet()) {
				final AGREESimulationStateElement element = inputConstraintEntry.getKey();
				try {																		
					elementToNextLustreConstraintMap.put(element, createLustreConstraintExpression(inputConstraintEntry.getValue(), element));
				} catch(final ConstraintEvaluationException ex) {
					throw new ConstraintEvaluationException("Constraint error for variable: " + element.getFullName(), ex);
				}
			}
		}
		
		/**
		 * Converts an input constraint into a lustre constraint. Throws exception on failure.
		 * @param ic
		 * @param constrainedStateElement
		 * @return
		 */
		private Expr createLustreConstraintExpression(final InputConstraint ic, final AGREESimulationStateElement constrainedStateElement) {
			final Expr lustreConstraintExpr = inputConstraintToLustreConstraintConverter.eval(ic, constrainedStateElement.getLustreExpr());
			if(lustreConstraintExpr == null) {
				throw new ConstraintEvaluationException("Lustre constraint expression is null", null);
			}
			
			return lustreConstraintExpr;
		}
	};
	
	private final SimulationRunnable runnable;
	private final SystemInstance systemInstance;
	private final Thread thread;
	private boolean stopped;
	
	public AGREESimulationEngine(final Simulation simulation, final SystemInstance systemInstance, final ExceptionHandler exceptionHandler) {
		this.runnable = new SimulationRunnable(Objects.requireNonNull(simulation, "simulation must not be null"), Objects.requireNonNull(exceptionHandler, "exceptionHandler must not be null"));
		this.systemInstance = Objects.requireNonNull(systemInstance, "systemInstance must not be null");
		this.thread = new Thread(runnable);
		this.stopped = false;
		
		// Start thread to listen for commands
		this.thread.start();
	}
	
	/**
	 * Makes a copy of a simulation engine with the specified listeners
	 * @param engine
	 * @param listeners
	 */
	private AGREESimulationEngine(final AGREESimulationEngine engine, final Collection<SimulationEngineEventListener> listeners) {
		this.runnable = new SimulationRunnable(engine.runnable, listeners);
		this.systemInstance = engine.systemInstance;
		this.thread = new Thread(runnable);
		this.stopped = engine.stopped;
		
		if(!stopped) {
			// Start thread to listen for commands
			this.thread.start();
		}
	}
	
	@Override
	public void dispose() {
		if(!stopped) {
			thread.interrupt();
			stopped = true;
		}		
	}
	
	public AGREESimulationEngine fork() {
		if(stopped) {
			throw new RuntimeException("Cannot fork a disposed simulation engine");
		}
		
		return new AGREESimulationEngine(this, Collections.emptyList());
	}
	
	@Override
	protected void finalize() throws Throwable {
		dispose();
		super.finalize();
	}
	
	@Override
	public SystemInstance getSystemInstance() {
		return systemInstance;
	}
	
	@Override
	public String getSimulationType() {
		return "AGREE Simulation - " + runnable.simulation.getProgram().getType().getName();
	}
	
	@Override
	public void addEventListener(final SimulationEngineEventListener listener) {
		this.runnable.addEventListener(Objects.requireNonNull(listener, "listener must not be null"));
	}
	
	@Override
	public void removeEventListener(final SimulationEngineEventListener listener) {
		this.runnable.removeEventListener(Objects.requireNonNull(listener, "listener must not be null"));
	}
	
	@Override
	public AGREESimulationState getCurrentState() {
		return runnable.currentState;
	}
	
	@Override
	public void stepForward() {
		try {
			runnable.commandQueue.put(new StepForwardCommand());
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	public void stepBackward() {
		try {
			runnable.commandQueue.put(new StepBackwardCommand());
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	public void queueNotification(NotificationHandler handler) {
		try {
			runnable.commandQueue.put(new NotifyCommand(handler));
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}
	
	public SimulationProgram getSimulationProgram() {
		return this.runnable.simulation.getProgram();
	}
		
	/**
	 * Sets whether a property is enabled. This change takes effect on the next step. Not modified when a step is taken backwards
	 * @param property
	 * @param enabled
	 */
	public void setPropertyEnabled(final SimulationProperty property, final boolean enabled) {
		try {
			runnable.commandQueue.put(new SetPropertyEnabledCommand(property, enabled));
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void clearPendingCommands() {
		runnable.commandQueue.clear();
	}

	public void setInputConstraint(final Object element, final InputConstraint constraint) {
		try {
			runnable.commandQueue.put(new SetElementInputConstraintCommand(asElement(element), constraint));
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}
	
	@Override 
	public void resetInputConstraints() {
		try {
			runnable.commandQueue.put(new ResetInputConstraintsCommand());
		} catch (InterruptedException e) {
			// Restore interrupted status
			Thread.currentThread().interrupt();
		}
	}
	
	private static AGREESimulationStateElement asElement(final Object element) {
		if(!(element instanceof AGREESimulationStateElement)) {
			throw new IllegalArgumentException("element is not a AGREESimulationFrameStateElement");
		}
		
		return (AGREESimulationStateElement)element;
	}	
}
