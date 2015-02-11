/*
Copyright (c) 2011,2013 Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

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

package edu.umn.cs.crisys.smaccm.aadl2camkes.ast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2camkes.Aadl2CamkesException;
import edu.umn.cs.crisys.smaccm.aadl2camkes.Aadl2CamkesFailure;

public class IdType extends Type {
	private final String typeId; 
	private Type typeRef = null; 
	
	
	public IdType(String tid) { 
		typeId = tid; 
	}
	
	public String getTypeId() {
		return typeId;
	}
	
	// TODO: this may be problematic for type aliases.  
	// 	Type aliases are not supported in CORBA IDL.
	public boolean isBaseType() { return false ; }
	
	public Type getTypeRef() { return typeRef; }
	
	private Type getRootTypeInt(Set<Type> visited) {
		if (visited.contains(this)) {
			throw new Aadl2CamkesException("Circular reference in ids for type " + typeId);
		}
		else if (typeRef instanceof IdType) {
			visited.add(this);
			return ((IdType)typeRef).getRootTypeInt(visited);
		}
		else {
			return typeRef;
		}
	}
	
	@Override
	public Type getRootType() throws Aadl2CamkesFailure {
		Set<Type> visited = new HashSet<Type>();
		try {
			return getRootTypeInt(visited);
		} catch (Aadl2CamkesException e) {
			Aadl2CamkesFailure f = new Aadl2CamkesFailure(); 
			f.addMessage(e.toString());
			f.addMessage("Circular reference in ids for type " + typeId); 
			throw f;
		}
	}
	
	@Override
	public void init(Map<String, Type> types) {
		assert(types != null);
		
		Type ty = types.get(typeId);
		if (ty == null)  {
			throw new Aadl2CamkesException("Unable to find type " + typeId + " (last lookup: " + typeId + ")");
		}
		else {
			typeRef = ty;
		}
	}
	
	@Override
	public String toString() {
		return typeId; 
	}
}
