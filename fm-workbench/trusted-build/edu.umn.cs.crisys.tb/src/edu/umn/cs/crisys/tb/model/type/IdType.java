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

package edu.umn.cs.crisys.tb.model.type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;

public class IdType extends Type {
	private final String typeId;
	private Type typeRef = null;

	public IdType(String tid) {
		typeId = tid;
	}

	public IdType(String typeId, Type typeRef) {
		this.typeId = typeId;
		this.typeRef = typeRef;
	}

	public String getTypeId() {
		return typeId;
	}

	@Override
	public CType getCType(int indent) {
		return new CType(toString(), "");
	}

	// TODO: this may be problematic for type aliases.
	// Type aliases are not supported in CORBA IDL.
	@Override
	public boolean isBaseType() {
		return false;
	}

	public Type getTypeRef() {
		return typeRef;
	}

	private Type getRootTypeInt(Set<Type> visited) {
		if (visited.contains(this)) {
			throw new TbException("Circular reference in ids for type " + typeId);
		} else if (typeRef instanceof IdType) {
			visited.add(this);
			return ((IdType) typeRef).getRootTypeInt(visited);
		} else {
			return typeRef;
		}
	}

	@Override
	public Type getRootType() throws TbFailure {
		Set<Type> visited = new HashSet<Type>();
		try {
			return getRootTypeInt(visited);
		} catch (TbException e) {
			TbFailure f = new TbFailure();
			f.addMessage(e.toString());
			f.addMessage("Circular reference in ids for type " + typeId);
			throw f;
		}
	}

	@Override
	public void init(Map<String, Type> types) {
		assert (types != null);

		Type ty = types.get(typeId);
		if (ty == null) {
			throw new TbException("Unable to find type " + typeId + " (last lookup: "
					+ typeId + ")");
		} else {
			typeRef = ty;
		}
	}

	@Override
	public List<Type> dependencies() {
		if (typeRef != null) {
		  List<Type> deps = new ArrayList<Type>(1);
		  deps.add(this.getTypeRef());
		  return deps;
		} else {
		  return new ArrayList<Type>();
		}
	}

	@Override
	public String toString() {
		return typeId;
	}
	
	@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
    result = prime * result + ((typeRef == null) ? 0 : typeRef.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    IdType other = (IdType) obj;
    if (typeId == null) {
      if (other.typeId != null)
        return false;
    } else if (!typeId.equals(other.typeId))
      return false;
    if (typeRef == null) {
      if (other.typeRef != null)
        return false;
    } else if (!typeRef.equals(other.typeRef))
      return false;
    return true;
  }
	
  public int getOverApproximateSizeInBytes(int byteAlign) throws TbFailure {
     return getRootType().getOverApproximateSizeInBytes(byteAlign);
  }
  
}
