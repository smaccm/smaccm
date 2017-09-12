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
package edu.uah.rsesc.aadlsimulator.agree.eval;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jkind.lustre.BinaryExpr;
import jkind.lustre.NamedType;

class Variable {
	public final NamedType type;
	public final Set<String> ids = new HashSet<>();
	public final Set<BinaryExpr> relatedExpressions = new HashSet<>(); // Expressions that could be used to solve for the variable.
	
	public Variable(final NamedType type) {
		this.type = Objects.requireNonNull(type, "type must not be null");
	}
	
	public Variable(final Variable base) {
		this.type = base.type;
		this.ids.addAll(base.ids);
		this.relatedExpressions.addAll(base.relatedExpressions);
	}
}
