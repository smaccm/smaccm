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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.ArrayExpr;
import jkind.lustre.ArrayType;
import jkind.lustre.ArrayUpdateExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Expr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordType;
import jkind.lustre.RecordUpdateExpr;
import jkind.lustre.Type;
import jkind.lustre.visitors.TypeReconstructor;

// Flattens record and array equality expressions into a list of expressions which reference the individual array elements and/or record fields.
class ExpressionFlattener {
	private final TypeReconstructor typeReconstructor;
	
	public ExpressionFlattener(final TypeReconstructor typeReconstructor) {
		this.typeReconstructor = Objects.requireNonNull(typeReconstructor, "typeReconstructor must not be null");
	}
	
	public List<BinaryExpr> flattenEqualsExpression(final BinaryExpr expr) {
		if(expr.op != BinaryOp.EQUAL) {
			throw new RuntimeException("Unexpected operator: " + expr.op);
		}
		
		final List<BinaryExpr> results = new ArrayList<>();
		flattenEqualsExpression(expr, results);
		return results;
	}	
	
	private void flattenEqualsExpression(final BinaryExpr expr, final List<BinaryExpr> results) {
		// Assume that both sides of the binary expression are the same type		
		final Type leftType = expr.left.accept(typeReconstructor);
		if(leftType instanceof ArrayType) {
			flattenArrayEquality(expr, (ArrayType)leftType, results);
		} else if(leftType instanceof RecordType) {
			flattenRecordEquality(expr, (RecordType)leftType, results);
		} else if(leftType instanceof NamedType) {			
			// Flatten both sides of the expression
			final Expr newLeft = flattenExpression(expr.left);
			final Expr newRight = flattenExpression(expr.right);
			if(newLeft != null && newRight != null) {
				results.add(new BinaryExpr(newLeft, expr.op, newRight));
			}
		}
	}
		
	// Flattens the expression if possible. 
	// Returns null if the flattening operation did not produce any expressions
	// Returns the expression itself if flattening the expression is not supported.
	private Expr flattenExpression(final Expr expr) {
		if(expr instanceof BinaryExpr && ((BinaryExpr) expr).op == BinaryOp.EQUAL) {
			final List<BinaryExpr> flattenedExpr = flattenEqualsExpression((BinaryExpr)expr);
			if(flattenedExpr.size() == 0) {
				return null;
			} else if(flattenedExpr.size() == 1) {
				return flattenedExpr.get(0);
			} else {
				Expr result = new BinaryExpr(flattenedExpr.get(0), BinaryOp.AND, flattenedExpr.get(1));
				for(int i = 2; i < flattenedExpr.size(); i++) {
					result = new BinaryExpr(result, BinaryOp.AND, flattenedExpr.get(i));
				}
				
				return result;
			}
		} else {
			return expr;
		}
	}	
	
	private void flattenArrayEquality(final BinaryExpr expr, final ArrayType arrayType, final List<BinaryExpr> results) {
		final Expr[] leftExpressions = flattenArrayExpression(expr.left, arrayType);
		final Expr[] rightExpressions = flattenArrayExpression(expr.right, arrayType);
		for(int i = 0; i < arrayType.size; i++) {
			if(leftExpressions[i] != null && rightExpressions[i] != null) {
				flattenEqualsExpression(new BinaryExpr(leftExpressions[i], BinaryOp.EQUAL, rightExpressions[i]), results);
			}
		}
	}
	
	private static Expr[] flattenArrayExpression(final Expr expr, final ArrayType arrayType) {
		final Expr[] results = new Expr[arrayType.size];
		flattenArrayExpression(expr, arrayType, results);
		return results;
	}
	
	private static void flattenArrayExpression(final Expr expr, final ArrayType arrayType, final Expr[] results) {
		if(expr instanceof ArrayUpdateExpr) {
			final ArrayUpdateExpr updateExpr = (ArrayUpdateExpr)expr;
			
			// Handle base array
			flattenArrayExpression(updateExpr.array, arrayType, results);
			
			// Handle the update
			if(updateExpr.index instanceof IntExpr) {
				final BigInteger bigIndex = ((IntExpr)updateExpr.index).value;
				try {
					final int index = bigIndex.intValueExact();
					results[index] = updateExpr.value;
				} catch(ArithmeticException ex) {
					// Ignore
				}
			}
			

		} else if(expr instanceof ArrayExpr) {
			final ArrayExpr arrayExpr = (ArrayExpr)expr;
			final int elementExpressions = Math.min(arrayType.size,  arrayExpr.elements.size());
			for(int i = 0; i < elementExpressions; i++) {
				results[i] = arrayExpr.elements.get(i);
			}
		} else { // IdExpr
			for(int i = 0; i < arrayType.size; i++) {
				results[i] = new ArrayAccessExpr(expr, i);
			}
		}
	}	
	
	private List<BinaryExpr> flattenRecordEquality(final BinaryExpr expr, final RecordType recordType, final List<BinaryExpr> results) {
		final Map<String, Expr> leftExpressions = flattenRecordExpression(expr.left, recordType);
		final Map<String, Expr> rightExpressions = flattenRecordExpression(expr.right, recordType);
		for(final String field : recordType.fields.keySet()) {
			final Expr leftExpr = leftExpressions.get(field);
			final Expr rightExpr = rightExpressions.get(field);			
			if(leftExpr != null && rightExpr != null) {
				flattenEqualsExpression(new BinaryExpr(leftExpr, expr.op, rightExpr), results);
			}
		}
				
		return results;
	}
	
	private static Map<String, Expr> flattenRecordExpression(final Expr expr, final RecordType recordType) {
		final Map<String, Expr> results = new HashMap<>(recordType.fields.size());		
		flattenRecordExpression(expr, recordType, results);
		return results;
	}
	
	private static void flattenRecordExpression(final Expr expr, final RecordType recordType, final Map<String, Expr> results) {
		if(expr instanceof RecordUpdateExpr) {
			final RecordUpdateExpr updateExpr = (RecordUpdateExpr)expr;
			
			// Handle base record
			flattenRecordExpression(updateExpr.record, recordType, results);
			
			// Handle the update
			results.put(updateExpr.field, updateExpr.value);
		} else if(expr instanceof RecordExpr) {
			final RecordExpr recordExpr = (RecordExpr)expr;
			results.putAll(recordExpr.fields);
		} else { // IdExpr
			for(final String field : recordType.fields.keySet()) {
				results.put(field, new RecordAccessExpr(expr, field));
			}
		}
	}
}
