/*
Copyright (c) 2011, 2013, Rockwell Collins and the University of Minnesota.
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

import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.topsort.DependsOn;

public abstract class Type implements DependsOn<Type> {
	// public abstract Expr getDefaultValue();

	abstract public boolean isBaseType();
	
	public boolean getIsBaseType() {
	  return isBaseType();
	}

	public class CType {
	  String prefix;
	  String postfix; 
	  
	  CType(String prefix, String postfix) {
	    this.prefix = prefix;
	    this.postfix = postfix; 
	  }
	  
	  public String getPrefix() { return prefix; }
	  public String getPostfix() {return postfix; } 
	  
	  public String varString(String varId) {
	    return this.prefix + " " + varId + " " + this.postfix;
	  }
	  
	  public String typeString() {
	    if (postfix == null || "".equals(postfix)) {
	      return prefix;
	    } else {
	      throw new 
	        TbException("Error: CType.typeString() called on type with postfix");
	    }
	  }
	};
	
	@Override
	public abstract List<Type> dependencies();

	public void init(Map<String, Type> env) {
	}

	// C array types need to be split around identifiers
	// x : array [20] of (array [10] of int) ==>
	// int x[20][10];
	abstract public CType getCType(int indent);
	
	public String CTypeDecl(int indent, String name) {
	  return getCType(indent).varString(name);
	}
	
	public CType getCType() { return this.getCType(0); }
	
   	
	public Type getRootType() throws TbFailure {
		return this;
	}
	
	// for 32 bit alignment, choose byteAlign = 4; for 64 bit, choose 8.
	public abstract int getOverApproximateSizeInBytes(int byteAlign) throws TbFailure;
	
	public boolean getIsUnit() { return false; }
	
}
