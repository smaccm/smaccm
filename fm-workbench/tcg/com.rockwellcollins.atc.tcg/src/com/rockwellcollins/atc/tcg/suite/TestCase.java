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

package com.rockwellcollins.atc.tcg.suite;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import com.rockwellcollins.atc.tcg.obligations.ufc.Obligation;

import jkind.results.Counterexample;

public class TestCase {
	private String name;
	private String description; 
	private Counterexample cex;
	private Set<Obligation> satisfiedObligations;
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public TestCase(String name, String description, Counterexample cex) {
		this(name, description, cex, new HashSet<Obligation>());
	}

	public TestCase(String name, String description, Counterexample cex,
			Set<Obligation> satisfiedObligations) {
		super();
		this.name = name;
		this.description = description;
		this.cex = cex;
		this.satisfiedObligations = satisfiedObligations;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		pcs.firePropertyChange("name", this.name, this.name = name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		pcs.firePropertyChange("description", this.description, this.description = description);
	}

	public Counterexample getCex() {
		return cex;
	}

	public void setCex(Counterexample cex) {
		pcs.firePropertyChange("counterexample", this.cex, this.cex = cex);
	}

	public Set<Obligation> getSatisfiedObligations() {
		return satisfiedObligations;
	}

	public void setSatisfiedObligations(Set<Obligation> satisfiedObligations) {
		pcs.firePropertyChange("satisfiedObligations", this.satisfiedObligations, this.satisfiedObligations = satisfiedObligations);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}

