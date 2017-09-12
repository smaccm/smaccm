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
package edu.uah.rsesc.aadlsimulator.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.instance.SystemInstance;

import edu.uah.rsesc.aadlsimulator.ExceptionHandler;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineFactory;
import edu.uah.rsesc.aadlsimulator.services.EngineType;
import edu.uah.rsesc.aadlsimulator.services.SimulationEngineChangeListener;
import edu.uah.rsesc.aadlsimulator.services.SimulationService;

public class DefaultSimulationService implements SimulationService {
	private final String engineTypesExtensionPointId = "edu.uah.rsesc.aadlsimulator.engineTypes";
	private final MasterExceptionHandler masterExceptionHandler = new MasterExceptionHandler();
	private final Collection<DefaultEngineType> engineTypes; 
	private final Collection<SimulationEngineChangeListener> simulationEngineChangeListeners = new CopyOnWriteArrayList<SimulationEngineChangeListener>();
	
	private static class DefaultEngineType implements EngineType {
		private final String id;
		private final String name;
		private final int priority;
		private final SimulationEngineFactory factory;
		
		public DefaultEngineType(final String id, final String name, final int priority, final SimulationEngineFactory factory) {
			this.id = Objects.requireNonNull(id, "id must not be null");
			this.name = Objects.requireNonNull(name, "name must not be null");
			this.priority = priority;
			this.factory = Objects.requireNonNull(factory, "factory must not be null");
		}
		
		@Override
		public String getId() {
			return id;
		}
		
		@Override
		public String getName() {
			return name;
		}
	};
	
	public DefaultSimulationService() {
		final List<DefaultEngineType> mutableEngineTypes = new ArrayList<>();
		final IExtensionRegistry reg = Platform.getExtensionRegistry();
	    final IConfigurationElement[] elements = reg.getConfigurationElementsFor(engineTypesExtensionPointId);
	    for(final IConfigurationElement element : elements) {
			try {
				final String id = Objects.requireNonNull(element.getAttribute("id"), "id must not be null");
				final String name = Objects.requireNonNull(element.getAttribute("name"), "name must not be null");
				final String priorityStr = element.getAttribute("priority");
				final int priority = priorityStr == null ? Integer.MIN_VALUE : Integer.parseInt(priorityStr);
				final SimulationEngineFactory factory = (SimulationEngineFactory)element.createExecutableExtension("factory");
				mutableEngineTypes.add(new DefaultEngineType(id, name, priority, factory));
			} catch (final CoreException e) {
				throw new RuntimeException(e);
			}
	    }
	    
	    
	    mutableEngineTypes.sort(new Comparator<DefaultEngineType>() {
			@Override
			public int compare(final DefaultEngineType t1, final DefaultEngineType t2) {
				return Integer.compare(t2.priority, t1.priority);
			}
		});
	    
	    engineTypes = Collections.unmodifiableCollection(mutableEngineTypes);
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return masterExceptionHandler;
	}
	
	@Override
	public Collection<DefaultEngineType> getEngineTypes() {
		return engineTypes;
	}
	
	@Override
	public EngineType getEngineTypeById(final String id) {
		Objects.requireNonNull(id, "id must not be null");
		
		for(final EngineType engineType : engineTypes) {
			if(id.equals(engineType.getId())) {
				return engineType;
			}
		}
		
		throw new IllegalArgumentException("A simulation engine with id '" + id + "' is not registered.");
	}
	
	@Override
	public EngineType getCompatibleEngineType(final ComponentImplementation ci) {
		for(final DefaultEngineType engineType : engineTypes) {
			// Check if engine type is compatible
			if(engineType.factory.isCompatible(ci)) {
				return engineType;
			}
		}
		
		return null;
	}
	
	@Override
	public void addSimulationEngineChangeListener(final SimulationEngineChangeListener listener) {
		simulationEngineChangeListeners.add(Objects.requireNonNull(listener, "listener must not be null"));
	}
	
	@Override
	public void removeSimulationEngineChangeListener(final SimulationEngineChangeListener listener) {
		simulationEngineChangeListeners.remove(Objects.requireNonNull(listener, "listener must not be null"));
	}

	@Override
	public SimulationEngine createEngine(final EngineType engineType, final SystemInstance systemInstance) {
		Objects.requireNonNull(engineType, "engineType must not be null");
		Objects.requireNonNull(engineType, "systemInstance must not be null");
		
		if(!(engineType instanceof DefaultEngineType)) {
			throw new IllegalArgumentException("engineType must be an engine type provided by this simulation service");
		}

		final DefaultEngineType defaultEngineType = (DefaultEngineType)engineType;
		final SimulationEngine newSimulationEngine = defaultEngineType.factory.create(systemInstance, masterExceptionHandler);
		
		// Notify listeners of the new simulation engine
		for(final SimulationEngineChangeListener l : simulationEngineChangeListeners) {
			l.onSimulationEngineCreated(newSimulationEngine);
		}

		return newSimulationEngine;
	}
	
	@Override
	public void dispose(final SimulationEngine engine) {
		// Notify listeners of the disposed simulation engine
		for(final SimulationEngineChangeListener l : simulationEngineChangeListeners) {
			l.onSimulationEngineDisposed(engine);
		}
		
		// Close the simulation
		engine.dispose();
	}
}
