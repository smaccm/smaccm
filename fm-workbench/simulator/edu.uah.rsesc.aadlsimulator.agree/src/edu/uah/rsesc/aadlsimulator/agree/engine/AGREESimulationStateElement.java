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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.InstanceObject;

import edu.uah.rsesc.aadlsimulator.VariableType;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.RecordAccessExpr;

class AGREESimulationStateElement {
	private final AGREESimulationStateElement parent;
	private final String name;
	private final VariableType type;
	private List<AGREESimulationStateElement> children = Collections.emptyList();
	private final Expr lustreExpr;
	private final String lustreId;
	private final InstanceObject instanceObject;
	private final EObject declObject;
	private final boolean hidden;
	
	/**
	 * 
	 * @param name
	 * @param type
	 * @param children
	 * @param lustreExpr must be null or a IdExpr, RecordAccessExpr, or ArrayAccessExpr
	 * @param instanceObject the AADL instance object associated with the state element. For a particular instance object there should only be one state element associated with it.
	 * @param hidden whether the nature of the variable is such that it does not directly relate to the AGREE specification and should be hidden from the user. Accumulator variables for example.
	 */
	public AGREESimulationStateElement(final AGREESimulationStateElement parent, final String name, final VariableType type, final Expr lustreExpr, final InstanceObject instanceObject, final EObject declObject, final boolean hidden) {
		// Ensure that the lustre expression is a valid type
		if(lustreExpr != null && !(lustreExpr instanceof IdExpr || lustreExpr instanceof RecordAccessExpr || lustreExpr instanceof ArrayAccessExpr)) {
			throw new RuntimeException("Invalid lustre expression type: " + lustreExpr.getClass());
		}
		
		this.parent = parent;
		this.name = Objects.requireNonNull(name, "name must not be null");
		this.type = Objects.requireNonNull(type, "type must not be null");
		this.lustreExpr = lustreExpr;
		this.lustreId = lustreExpr == null ? null : lustreExpr.toString();
		this.instanceObject = instanceObject;
		this.declObject = declObject;
		this.hidden = hidden;
	}
	
	public AGREESimulationStateElement getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFullName() {
		return parent == null ? getName() : parent.getFullName() + "." + getName();
	}
	
	public VariableType getType() {
		return type;
	}

	public List<AGREESimulationStateElement> getChildren() {
		return children;
	}
	
	public String getLustreId() {
		return lustreId;
	}
	
	public Expr getLustreExpr() {
		return lustreExpr;
	}
	
	public InstanceObject getInstanceObject() {
		return instanceObject;
	}
	
	public EObject getDeclarativeObject() {
		return declObject;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public void setChildren(List<AGREESimulationStateElement> children) {
		this.children = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(children, "children must not be null")));
	}
}
