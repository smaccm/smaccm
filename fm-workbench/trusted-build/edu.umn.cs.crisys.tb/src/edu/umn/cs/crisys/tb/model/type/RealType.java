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

import java.util.List;

public class RealType extends Type {

	FloatEnum realKind;

	public RealType(FloatEnum kind) {
		realKind = kind;
	}

	@Override
	public boolean isBaseType() {
		return true;
	}

	@Override
	public CType getCType(int indent) {
		return new CType(toString(), "");
	}

	@Override
	public List<Type> dependencies() {
		return null;
	}

	@Override
	public String toString() {
		if (FloatEnum.DOUBLE == realKind) {
			return "double";
		} else {
			return "float";
		}
	}

	// @Override
	// public Expr getDefaultValue() {
	// return new RealExpr(0.0);
	// }

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RealType) {
		  RealType other = (RealType)obj;
		  return (this.realKind.equals(other.realKind));
		}
	  return false;
	}

	// assume IEEE types.
   public int getOverApproximateSizeInBytes(int byteAlign) {
      if (FloatEnum.DOUBLE == realKind) {
         return 8; 
      } else {
         return 4;
      }
   }
}
