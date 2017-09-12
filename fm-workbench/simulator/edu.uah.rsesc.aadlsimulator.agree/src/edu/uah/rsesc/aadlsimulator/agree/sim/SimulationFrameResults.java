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
package edu.uah.rsesc.aadlsimulator.agree.sim;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import jkind.lustre.ArrayAccessExpr;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.Program;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.results.Counterexample;
import jkind.util.BigFraction;

// Immutable class that represents the results of a single frame of simulation
public class SimulationFrameResults {
	private final Program lustreProgram; // The fully constrained lustre program that was passed to JKind to generate these results
	private final boolean hasCounterexample;
	private final Map<String, Value> idToValueMap;
	private final Set<SimulationProperty> disabledProperties;
	private final Set<String> inductiveValidityCore; // The set of the support is a set of identifiers used to prove the simulation guarantee. Identifies the reason why a counterexample could not be generated.
	private final SimulationState state;
	
	SimulationFrameResults(final Program lustreProgram, final Collection<Expr> assertions, final Set<SimulationProperty> disabledProperties, final Collection<String> inductiveValidityCore, final SimulationState state) {
		this.lustreProgram = Objects.requireNonNull(lustreProgram, "lustreProgram must not be null");
		this.hasCounterexample = false;
		this.idToValueMap = new HashMap<String, Value>(); 
		this.disabledProperties = Collections.unmodifiableSet(new HashSet<>(Objects.requireNonNull(disabledProperties, "disabledProperties must not be null")));
		this.inductiveValidityCore = inductiveValidityCore == null ? null : new HashSet<>(inductiveValidityCore);
		this.state = Objects.requireNonNull(state, "state must not be null");
				
		// Create id to value mapping for assertions. Not all expressions are supported only those that are in the form of id = scalar.
		for(final Expr assertion : assertions) {
			if(assertion instanceof BinaryExpr) {
				final BinaryExpr be = (BinaryExpr)assertion;
				if(be.op == BinaryOp.EQUAL && (be.left instanceof IdExpr || be.left instanceof RecordAccessExpr || be.left instanceof ArrayAccessExpr)) {
					final String id = be.left.toString();
					final Value value = eval(be.right);					
					if(value != null) {
						idToValueMap.put(id, value);
					}
				}
			}
		}
	}
	
	private Value eval(final Expr lustreExpr) {
		if(lustreExpr instanceof BoolExpr) {
			return BooleanValue.fromBoolean(((BoolExpr) lustreExpr).value);
		} else if(lustreExpr instanceof IntExpr) {
			return new IntegerValue(((IntExpr) lustreExpr).value);
		} else if(lustreExpr instanceof RealExpr) {
			final RealExpr realExpr = (RealExpr)lustreExpr;
			return new RealValue(BigFraction.valueOf(realExpr.value));
		} else if(lustreExpr instanceof BinaryExpr) {
			final BinaryExpr binaryExpr = (BinaryExpr)lustreExpr;
			final Value leftValue = eval(binaryExpr.left);
			final Value rightValue = eval(binaryExpr.right);
			if(leftValue == null || rightValue == null) {
				return null;
			}
			return leftValue.applyBinaryOp(binaryExpr.op, rightValue);
		} else if(lustreExpr instanceof UnaryExpr) {
			final UnaryExpr unaryExpr = (UnaryExpr)lustreExpr;
			final Value operandValue = eval(unaryExpr.expr);
			if(operandValue == null) {
				return null;
			}
			return operandValue.applyUnaryOp(unaryExpr.op);
		}
		
		return null;	
	}
	
	SimulationFrameResults(final Program lustreProgram, final Collection<Expr> assertions, final Set<SimulationProperty> disabledProperties, final SimulationState state) {
		this(lustreProgram, assertions, disabledProperties, null, state);
	}
	
	SimulationFrameResults(final Program lustreProgram, final Counterexample counterexample, final Set<SimulationProperty> disabledProperties, final SimulationState state) {
		this.lustreProgram = Objects.requireNonNull(lustreProgram, "lustreProgram must not be null");
		this.hasCounterexample = true;
		this.idToValueMap = Objects.requireNonNull(counterexample, "counterexample must not be null").getStep(0);
		this.state = Objects.requireNonNull(state, "state must not be null");
		this.disabledProperties = Collections.unmodifiableSet(new HashSet<>(Objects.requireNonNull(disabledProperties, "disabledProperties must not be null")));
		this.inductiveValidityCore = null;
	}
	
	public final Program getLustreProgram() {
		return lustreProgram;
	}

	/**
	 * @param id
	 * @return
	 */
	public final Value getValue(final String id) {
		Objects.requireNonNull(id, "id must not be null");
		return idToValueMap.get(id);
	}
	
	public final SimulationState getState() {
		return state;
	}
	
	public final boolean hasCounterexample() {
		return hasCounterexample;
	}
	
	public final Set<SimulationProperty> getDisabledProperties() {
		return disabledProperties;
	}
	
	public final boolean hasInductiveValidityCore() {
		return inductiveValidityCore != null;
	}
	
	public final boolean isInInductiveValidityCore(final String id) {
		return hasInductiveValidityCore() && inductiveValidityCore.contains(id);
	}	
}
