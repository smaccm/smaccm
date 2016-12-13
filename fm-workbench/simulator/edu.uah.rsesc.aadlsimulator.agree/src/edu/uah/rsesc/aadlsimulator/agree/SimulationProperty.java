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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;

/**
 * Represents a logical simulation property. May consist of multiple boolean Lustre expressions.
 */
public class SimulationProperty {
	private final Collection<String> lustreIds;
	private final String description;
	private final EObject declarativeModelReference;
	private final String enablementVarId;
	
	/**
	 * 
	 * @param lustreIds - Set to boolean identifiers which must all be true for the property to be true
	 * @param description
	 * @param declarativeModelReference may be null. 
	 */
	public SimulationProperty(final Collection<String> lustreId, final String description, final EObject declarativeModelReference, final String enablementVarId) {
		this.lustreIds = Collections.unmodifiableSet(new HashSet<>(Objects.requireNonNull(lustreId, "lustreId must not be null")));
		this.description = Objects.requireNonNull(description, "description must not be null");
		this.declarativeModelReference = Objects.requireNonNull(declarativeModelReference, "declarativeModelReference must not be null");
		this.enablementVarId = enablementVarId;
	}
	
	public final Collection<String> getLustreIds() {
		return lustreIds;
	}
	
	public final String getDescription() {
		return description;
	}
	
	public final EObject getDeclarativeModelReference() {
		return declarativeModelReference;
	}
	
	/**
	 * Returns the name of the local variable that determines whether the property is enabled. If a property is disabled, the simulation will not
	 * be halted if it is not-satisfied.
	 */
	public final String getEnablementVariableId() {
		return enablementVarId;
	}
}
