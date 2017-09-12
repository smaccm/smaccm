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
package edu.uah.rsesc.aadlsimulator.agree;

import java.util.Objects;

import jkind.lustre.Type;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

public class SimulationVariable {
	private final ComponentInstance componentInstance;
	private final String name;
	private final String lustreId;
	private final Type type;
	private final FeatureInstance featureInstance;
	private final EObject declReference;
	
	/**
	 * 
	 * @param componentInstance
	 * @param name
	 * @param lustreId must be the lustre identifier for a variable. Must be a valid string for an IdExpr. (Must not contain record access "." or array access "[]"). 
	 * @param type
	 * @param featureInstance the feature instance associated with the variable. May be null if the variable isn't associated with a feature instance,
	 * @param declReference is the declarative model reference
	 */
	public SimulationVariable(final ComponentInstance componentInstance, final String name, final String lustreId, final Type type, final FeatureInstance featureInstance, final EObject declReference) {
		this.componentInstance = Objects.requireNonNull(componentInstance, "componentInstance must not be null");
		this.name = Objects.requireNonNull(name, "name must not be null");
		this.lustreId = Objects.requireNonNull(lustreId, "lustreId must not be null");
		this.type = Objects.requireNonNull(type, "type must not be null");
		this.featureInstance = featureInstance;
		this.declReference = declReference;
	}
	
	public ComponentInstance getComponentInstance() {
		return componentInstance;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLustreId() {
		return lustreId;
	}
	
	public Type getType() {
		return type;
	}
	
	public FeatureInstance getFeatureInstance() {
		return featureInstance;
	}
	
	public EObject getDeclarativeReference() {
		return declReference;
	}
}
