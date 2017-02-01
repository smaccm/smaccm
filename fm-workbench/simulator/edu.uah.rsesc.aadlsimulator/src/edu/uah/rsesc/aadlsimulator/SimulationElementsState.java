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

import java.util.Collection;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.InstanceObject;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;

/**
 * Interface for accessing the state of the simulation elements. Simulation elements form a tree structure containing simulation variables.
 *
 */
public interface SimulationElementsState {
	int getNumberOfFrames();
	
	Collection<?> getRootElements();
	Object getParentElement(final Object element);
	Collection<?> getChildElements(final Object element);
	String getElementName(final Object element);
	VariableType getElementType(final Object element);
	InstanceObject getElementInstanceObject(Object element); // Returns the instance object from which the state element is derived. May be null.
	EObject getElementDeclarativeObject(Object element); // Returns the declarative object related to the state element. May be null.
	boolean isElementHidden(Object element); // Returns whether the element is a hidden state element. That is, if it is a state element that would not ordinarily be relevant to users. For example accumulator variables.

	boolean isElement(Object obj); // Returns whether the object is an element

	/**
	 * Returns the constraint for a specified element for the next frame
	 * @param element
	 * @return
	 */
	InputConstraint getElementInputConstraintForNextFrame(final Object element);
	
	/**
	 * @param frameIndex the 0 based frame index. Must be within the range [0, numberOfFrames]. If frameIndex == numberOfFrames, then the value of the next frame will be 
	 * returned if possible.
	 * @param element
	 * @return the value of the specified element or null if the value was not available for the specified element.
	 */
	Object getElementValue(final int frameIndex, final Object element);
	
	Object findElement(final InstanceObject io);
	
	/**
	 * Retrieve an element using an absolute path.
	 * @param path is composed of a series of element names separated by the "." character. For example. parent.child.var
	 * @return
	 */
	Object findElementByPath(final String path);
}
