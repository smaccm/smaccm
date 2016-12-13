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
package edu.uah.rsesc.aadlsimulator.agree.transformation;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import edu.uah.rsesc.aadlsimulator.agree.SimulationProgram;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProgramBuilder;
import edu.uah.rsesc.aadlsimulator.agree.SimulationProperty;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;
import jkind.lustre.builders.ProgramBuilder;
import jkind.lustre.visitors.AstMapVisitor;

/**
 * Add simulation properties for local AgreeVar variables which reference a lemma, assume, guarantee, or assert statement
 * This transformation assumes that only one node exists in the simulation program and that local variables represented by AgreeVar objects exist for all simulation properties.
 */
public class CreateSimulationProperties {
	private static String propertyEnablementPrefix = "__SIM_PE_";
	
	public static SimulationProgram transform(final SimulationProgram program) {
		final Program lustreProgram = program.getLustreProgram();
		if(lustreProgram.nodes.size() != 1) {
			throw new IllegalArgumentException("Only lustre programs with exactly one node are supported");
		}
		
		final SimulationProgramBuilder builder = new SimulationProgramBuilder(program);
		final Node mainNode = lustreProgram.getMainNode();
		
		// Build a new main node which includes support statements 
		final NodeBuilder lustreNodeBuilder = new NodeBuilder(mainNode);
		
		// Create simulation properties for each local variable that references a lemma, assume, guarantee, or assert statement
		lustreNodeBuilder.clearIvc();
		final Map<String, SimulationProperty> idToSimulationPropertyMap = new HashMap<>();
		
		// Create a mapping from the component instance/variable reference to a collection of Lustre Id's that will be used to create the simulation properties
		final Map<ComponentInstance, Map<EObject, Collection<String>>> componentInstanceToReferenceToVarIdMap = new HashMap<>();	
		for(final VarDecl local : mainNode.locals) {
            if(local instanceof AgreeVar) {
                final AgreeVar var = (AgreeVar)local;
                if(var.reference instanceof LemmaStatement || var.reference instanceof AssumeStatement || var.reference instanceof GuaranteeStatement || var.reference instanceof AssertStatement) {
                    final boolean createSimProp = (!(var.reference instanceof AssumeStatement) || var.compInst == var.compInst.getSystemInstance()) || var instanceof SimulationPropertyVar;
                    if(createSimProp) {                     
                        Map<EObject, Collection<String>> referenceToVarIdMap = componentInstanceToReferenceToVarIdMap.get(var.compInst);
                        if(referenceToVarIdMap == null) {
                            referenceToVarIdMap = new HashMap<>();
                            componentInstanceToReferenceToVarIdMap.put(var.compInst, referenceToVarIdMap);
                        }
                        
                        Collection<String> varIds = referenceToVarIdMap.get(var.reference);
                        if(varIds == null) {
                            varIds = new HashSet<>();
                            referenceToVarIdMap.put(var.reference, varIds);
                        }
                        
                        varIds.add(var.id);
                    }
                }
            }
        }
		
		// Create the simulation properties.
		// A single simulation property will be created for each component instance, reference combination.
		int propertyIndex = 0;
		for(final Entry<ComponentInstance, Map<EObject, Collection<String>>> componentInstanceToReferenceToVarIdMapEntry : componentInstanceToReferenceToVarIdMap.entrySet()) {
		    final ComponentInstance propComponentInstance = componentInstanceToReferenceToVarIdMapEntry.getKey();
		    for(Entry<EObject, Collection<String>> referenceToVarIdsEntry : componentInstanceToReferenceToVarIdMapEntry.getValue().entrySet()) {
		        final EObject propReference = referenceToVarIdsEntry.getKey();
                final Collection<String> propLustreIds = referenceToVarIdsEntry.getValue();
                final String propertyDesc = getDescription(propComponentInstance, propReference);
                final String enablementVariableId;              
        
                // Only properties which cause the simulation to be halted may be disabled.
                // That is: only top-level assumptions and non-top-level guarantees may be disabled
                final boolean disableable = (propReference instanceof GuaranteeStatement && propComponentInstance != propComponentInstance.getSystemInstance()) ||
                        (propReference instanceof AssumeStatement && propComponentInstance == propComponentInstance.getSystemInstance());
                if(disableable) {
                    enablementVariableId = propertyEnablementPrefix + propertyIndex;
                    lustreNodeBuilder.addLocal(new VarDecl(enablementVariableId, NamedType.BOOL));
                } else {
                    enablementVariableId = null;
                }
                            
                final SimulationProperty simProp = new SimulationProperty(propLustreIds, propertyDesc, propReference, enablementVariableId);
                builder.addSimulationProperty(simProp);
                propertyIndex++;
                
                for(final String propLustreId : propLustreIds) {    
                    idToSimulationPropertyMap.put(propLustreId, simProp);
                    lustreNodeBuilder.addIvc(propLustreId);
                }
		    }
		}		
		
		// Edit main node to make simulation properties inside of assertions to be disableable
		final AstMapVisitor propertyVarDisableTransformation = new AstMapVisitor() {
			@Override
			public Expr visit(final IdExpr e) {
				final SimulationProperty simProp = idToSimulationPropertyMap.get(e.id);
				if(simProp != null && simProp.getEnablementVariableId() != null) {
					return new BinaryExpr(new UnaryExpr(UnaryOp.NOT, new IdExpr(simProp.getEnablementVariableId())), BinaryOp.OR, e);
				}
				return super.visit(e);
			}
			
			@Override
			public Equation visit(final Equation e) {
				// Don't transform the equations for simulation properties
				if(e.lhs.size() == 1 && idToSimulationPropertyMap.containsKey(e.lhs.get(0))) {
					return e;
				}
				
				return super.visit(e);
			}
		};	
		
		// Transform assertions and equations
		lustreNodeBuilder.clearAssertions();
		for(final Expr assertion : mainNode.assertions) {
			lustreNodeBuilder.addAssertion(assertion.accept(propertyVarDisableTransformation));
		}
		
		lustreNodeBuilder.clearEquations();
		for(final Equation eq : mainNode.equations) {
			lustreNodeBuilder.addEquation((Equation)eq.accept(propertyVarDisableTransformation));
		}		
		
		
		// Build a new program with the new main node
		final ProgramBuilder lustreProgramBuilder = new ProgramBuilder(lustreProgram);
		lustreProgramBuilder.clearNodes();
		lustreProgramBuilder.addNode(lustreNodeBuilder.build());
		
		builder.setLustreProgram(lustreProgramBuilder.build());
		
		return builder.build();
	}

	private static String getDescription(final ComponentInstance ci, final EObject reference) {
		final String statementDescription = getStatementDescription(reference);
		final String prefix;
		if(reference instanceof LemmaStatement) {
			prefix = "lemma: ";
		} else {
			prefix = "";
		}

		final String rootSiPrefix = ci.getSystemInstance().getInstanceObjectPath() + ".";
		final String ciPath = ci.getInstanceObjectPath();
		final String componentInstName = ciPath.length() <= rootSiPrefix.length() ? "" : (ciPath.substring(rootSiPrefix.length()) + " ");	
		return prefix + componentInstName + statementDescription;
	}
	
	private static String getStatementDescription(final EObject statement) {
		Objects.requireNonNull(statement, "statement must not be null");
		
		final String result;
		if(statement instanceof LemmaStatement) {
			result = ((LemmaStatement)statement).getStr();
		} else if(statement instanceof AssumeStatement) {
			result = ((AssumeStatement)statement).getStr();
		} else if(statement instanceof GuaranteeStatement) {
			result = ((GuaranteeStatement)statement).getStr();
		} else if(statement instanceof AssertStatement) {
			final INode node = NodeModelUtils.getNode(statement);
			final String tokenTxt = node == null ? null : NodeModelUtils.getTokenText(node);
			result = tokenTxt == null ? "<assertion>" : tokenTxt;
		} else {
			throw new RuntimeException("Unexpected statement type: " + statement.getClass());
		}

		return result;
	}
}
