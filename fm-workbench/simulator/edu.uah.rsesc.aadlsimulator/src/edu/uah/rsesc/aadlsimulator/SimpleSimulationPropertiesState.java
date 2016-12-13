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
package edu.uah.rsesc.aadlsimulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Basic implementation of the SimulationPropertiesState interface.
- *
 */
public class SimpleSimulationPropertiesState implements SimulationPropertiesState {
	public static class SimpleSimulationProperty {
		public SimpleSimulationProperty(final String name, final String details, final Object bo, final PropertyStatus status, final boolean enableNextStep) {
			this.name = name;
			this.details = details;
			this.bo = bo;
			this.status = status;
			this.enableNextStep = enableNextStep;
		}
		
		private final String name;
		private final String details;
		private final Object bo;
		private final PropertyStatus status;
		private final boolean enableNextStep;
	}
	
	private final Collection<SimpleSimulationProperty> properties;
	
	public SimpleSimulationPropertiesState(final Collection<SimpleSimulationProperty> properties) {
		this.properties = Collections.unmodifiableCollection(new ArrayList<>(Objects.requireNonNull(properties, "properties must not be null")));
	}
	
	@Override
	public Collection<?> getProperties() {
		return properties;
	}

	@Override
	public boolean isProperty(final Object property) {
		return property instanceof SimpleSimulationProperty;
	}
	
	@Override
	public String getPropertyName(final Object property) {
		return ((SimpleSimulationProperty)property).name;
	}

	@Override
	public Object getPropertyBusinessObject(final Object property) {
		return ((SimpleSimulationProperty)property).bo;
	}

	@Override
	public PropertyStatus getPropertyStatus(final Object property) {
		return ((SimpleSimulationProperty)property).status;
	}

	@Override
	public String getPropertyDetails(final Object property) {
		return ((SimpleSimulationProperty)property).details;
	}
	
	@Override
	public boolean isPropertyEnabledForNextFrame(Object property) {
		return ((SimpleSimulationProperty)property).enableNextStep;
	}
}
