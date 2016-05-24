package com.rockwellcollins.atc.agree.analysis.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.Pair;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Contract;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.RecordType;
import jkind.lustre.TupleExpr;
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;
import jkind.lustre.builders.NodeBuilder;

import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode.TimingModel;

public class LustreContractAstBuilder extends LustreAstBuilder {

    public static Program getContractLustreProgram(AgreeProgram agreeProgram) {

        nodes = new ArrayList<>();
        List<TypeDef> types = new ArrayList<>();
        for (Type type : agreeProgram.globalTypes) {
            RecordType recType = (RecordType) type;
            types.add(new TypeDef(recType.id, type));
        }

        AgreeNode flatNode = flattenAgreeNodeKindContract(agreeProgram.topNode, "_TOP__");
        List<Expr> assertions = new ArrayList<>();
        List<VarDecl> locals = new ArrayList<>();
        List<VarDecl> inputs = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<>();
        List<Equation> equations = new ArrayList<>();
        List<String> properties = new ArrayList<>();
        List<Expr> requires = new ArrayList<>();
        List<Expr> ensures = new ArrayList<>();

        for (AgreeStatement assertion : flatNode.assertions) {
            assertions.add(assertion.expr);
        }

        for (AgreeStatement assumption : flatNode.assumptions) {
            requires.add(assumption.expr);
        }

        for (AgreeStatement guarantee : flatNode.lemmas) {
            ensures.add(guarantee.expr);
        }

        for (AgreeStatement guarantee : flatNode.guarantees) {
            ensures.add(guarantee.expr);
        }

        for (AgreeVar var : flatNode.inputs) {
            inputs.add(var);
        }

        for (AgreeVar var : flatNode.outputs) {
            outputs.add(var);
        }

        for (AgreeVar var : flatNode.locals) {
            locals.add(var);
        }

        for (AgreeVar var : flatNode.outputs) {
            if (var.reference instanceof AssumeStatement || var.reference instanceof LemmaStatement) {
                throw new AgreeException("This shouldn't happen");
            }
        }
        
        equations.addAll(flatNode.localEquations);

        Contract contract = new Contract(requires, ensures);

        NodeBuilder builder = new NodeBuilder("_TOP");
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(equations);
        builder.addProperties(properties);
        builder.addAssertions(assertions);
        builder.setContract(contract);
        
        Node main = builder.build();
        
        nodes.addAll(agreeProgram.globalLustreNodes);
        nodes.add(main);
        
        Program program = new Program(types, null, nodes, main.id);

        return program;

    }

    protected static AgreeNode flattenAgreeNodeKindContract(AgreeNode agreeNode, String nodePrefix) {

        List<AgreeVar> inputs = new ArrayList<>();
        List<AgreeVar> outputs = new ArrayList<>();
        List<AgreeVar> locals = new ArrayList<>();
        List<AgreeStatement> assertions = new ArrayList<>();
        List<AgreeEquation> equations = new ArrayList<>();

        Expr someoneTicks = null;
        for (AgreeNode subAgreeNode : agreeNode.subNodes) {
            String prefix = subAgreeNode.id + AgreeASTBuilder.dotChar;
            Expr clockExpr = getClockExpr(agreeNode, subAgreeNode);

            if (someoneTicks == null) {
                someoneTicks = clockExpr;
            } else {
                someoneTicks = new BinaryExpr(someoneTicks, BinaryOp.OR, clockExpr);
            }

            AgreeNode flatNode = flattenAgreeNodeKindContract(subAgreeNode,
                    nodePrefix + subAgreeNode.id + AgreeASTBuilder.dotChar);

            Node lustreNode = addSubNodeLustre(agreeNode, nodePrefix, flatNode);

            addInputsAndOutputs(inputs, outputs, locals, flatNode, lustreNode, prefix);

            addCondactCall(agreeNode, nodePrefix, inputs, equations, flatNode, prefix, clockExpr,
                    lustreNode);

            addClockHolds(agreeNode, assertions, flatNode, clockExpr, prefix, lustreNode);

            addInitConstraint(agreeNode, outputs, assertions, flatNode, prefix, clockExpr, lustreNode);

        }

        if (agreeNode.timing == TimingModel.ASYNC) {
            if (someoneTicks == null) {
                throw new AgreeException("Somehow we generated a clock constraint without any clocks");
            }
            assertions.add(new AgreeStatement("someone ticks", someoneTicks, null));
        }

        addConnectionConstraints(agreeNode, equations, inputs, locals);

        // add any clock constraints
        assertions.addAll(agreeNode.assertions);
        assertions.add(new AgreeStatement("", agreeNode.clockConstraint, null));
        inputs.addAll(agreeNode.inputs);
        outputs.addAll(agreeNode.outputs);
        locals.addAll(agreeNode.locals);
        equations.addAll(agreeNode.localEquations);

        return new AgreeNode(agreeNode.id, inputs, outputs, locals, equations, null, agreeNode.subNodes, assertions,
                agreeNode.assumptions, agreeNode.guarantees, agreeNode.lemmas, agreeNode.patternProps, new BoolExpr(true),
                agreeNode.initialConstraint, agreeNode.clockVar, agreeNode.reference, null, agreeNode.eventTimes,
                agreeNode.compInst);
    }

    protected static Node addSubNodeLustre(AgreeNode agreeNode, String nodePrefix, AgreeNode flatNode) {

        Node lustreNode = getLustreNode(flatNode, nodePrefix);
        addToNodes(lustreNode);
        return lustreNode;
    }

    protected static Node getLustreNode(AgreeNode agreeNode, String nodePrefix) {

        List<VarDecl> inputs = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<>();
        List<VarDecl> locals = new ArrayList<>();
        List<Equation> equations = new ArrayList<>();
        List<Expr> assertions = new ArrayList<>();
        List<Expr> requires = new ArrayList<>();
        List<Expr> ensures = new ArrayList<>();

        for (AgreeStatement statement : agreeNode.assumptions) {
            requires.add(statement.expr);
        }

        for (AgreeStatement statement : agreeNode.lemmas) {
            ensures.add(statement.expr);
        }

        for (AgreeStatement statement : agreeNode.guarantees) {
            ensures.add(statement.expr);
        }

        for (AgreeStatement statement : agreeNode.assertions) {
            if(AgreeUtils.referenceIsInContract(statement.reference)){
                ensures.add(statement.expr);
            }
            if (statement.reference instanceof AssignStatement) {
                Expr var = ((BinaryExpr) statement.expr).left;
                Expr expr = ((BinaryExpr) statement.expr).right;
                equations.add(new AgreeEquation(new Equation((IdExpr) var, expr), statement.reference));
            } else {
                assertions.add(statement.expr);
            }

        }

        // gather the remaining inputs
        for (AgreeVar var : agreeNode.inputs) {
            inputs.add(var);
        }
        for (AgreeVar var : agreeNode.outputs) {
            outputs.add(var);
        }
        
        for (AgreeVar var : agreeNode.locals){
            locals.add(var);
        }
        
        equations.addAll(agreeNode.localEquations);
        
        //Contract contract = new Contract(nodePrefix + agreeNode.id, requires, ensures);
        Contract contract = new Contract(requires, ensures);

        
        NodeBuilder builder = new NodeBuilder(nodePrefix + agreeNode.id);
        builder.addInputs(inputs);
        builder.addOutputs(outputs);
        builder.addLocals(locals);
        builder.addEquations(equations);
        builder.addAssertions(assertions);
        builder.setContract(contract);
        
        return builder.build();
    }

    protected static void addInputsAndOutputs(List<AgreeVar> inputs, List<AgreeVar> outputs, List<AgreeVar> locals,
            AgreeNode subAgreeNode, Node lustreNode, String prefix) {
        for (AgreeVar var : subAgreeNode.inputs) {
            AgreeVar input = new AgreeVar(prefix + var.id, var.type, var.reference, var.compInst);
            inputs.add(input);
        }

        for (AgreeVar var : subAgreeNode.outputs) {
            AgreeVar output = new AgreeVar(prefix + var.id, var.type, var.reference, var.compInst);
            outputs.add(output);
        }

        inputs.add(subAgreeNode.clockVar);

    }

    protected static void addCondactCall(AgreeNode agreeNode, String nodePrefix, List<AgreeVar> inputs,
            List<AgreeEquation> equations, AgreeNode subAgreeNode, String prefix, Expr clockExpr,
            Node lustreNode) {
        List<Expr> inputIds = new ArrayList<>();
        List<Expr> initOutputsVals = new ArrayList<>();
        List<IdExpr> nodeOutputIds = new ArrayList<>();
        for (VarDecl var : lustreNode.inputs) {
            inputIds.add(new IdExpr(prefix + var.id));
        }

        for (VarDecl var : lustreNode.outputs) {
            AgreeVar outputVar = (AgreeVar) var;
            String dummyName = prefix + var.id + "__DUMMY";
            AgreeVar dummyVar = new AgreeVar(dummyName, outputVar.type, outputVar.reference, outputVar.compInst);
            
            if (!inputs.contains(dummyVar)) {
                inputs.add(dummyVar);
            }

            initOutputsVals.add(new IdExpr(dummyName));
            nodeOutputIds.add(new IdExpr(prefix + var.id));
        }

        if (agreeNode.timing == TimingModel.LATCHED) {
            throw new AgreeException("this was refactored for jkind but never fixed for kind2");
            //addLatchedConstraints(nodePrefix, inputs, assertions, subAgreeNode, prefix, inputIds);
        }

        Expr condactExpr =
                new CondactExpr(clockExpr, new NodeCallExpr(lustreNode.id, inputIds), initOutputsVals);

        equations.add(new AgreeEquation(new Equation(nodeOutputIds, condactExpr), null));
    }

    
    public static List<Pair<String, Program>> getConsistencyChecks(AgreeProgram agreeProgram,
            boolean monolithic) {
        List<Pair<String, Program>> consistencies = LustreAstBuilder.getConsistencyChecks(agreeProgram, monolithic);
        //TODO there is a bug right now with computing composition consistency
        consistencies.remove(consistencies.size()-1);
        return consistencies;
    }

    protected static void addConnectionConstraints(AgreeNode agreeNode, List<AgreeEquation> equations,
            List<AgreeVar> inputs, List<AgreeVar> locals) {
        for (AgreeConnection conn : agreeNode.connections) {
            String destName =
                    conn.destinationNode == null ? "" : conn.destinationNode + AgreeASTBuilder.dotChar;
            destName = destName + conn.destVar.id;

            String sourName = conn.sourceNode == null ? "" : conn.sourceNode + AgreeASTBuilder.dotChar;
            sourName = sourName + conn.sourVar.id;

            Expr connExpr; 
            
            if(!conn.delayed){
                connExpr = new IdExpr(sourName);
            }else{
                //we need to get the correct type for the connection
                //we can assume that the source and destination types are
                //the same at this point
                Expr initExpr = AgreeUtils.getInitValueFromType(conn.sourVar.type);
                Expr preSource = new UnaryExpr(UnaryOp.PRE, new IdExpr(sourName));
                Expr sourExpr = new BinaryExpr(initExpr, BinaryOp.ARROW, preSource);
                connExpr = sourExpr;
            }
            
            //add the destination variable to locals iff it is in the inputs
            AgreeVar var = null;
            for(AgreeVar inputVar : inputs){
                if(inputVar.id.equals(destName)){
                    var = inputVar;
                    break;
                }
            }
            if(var != null){
                inputs.remove(var);
                locals.add(var);
            }

            equations.add(new AgreeEquation(new Equation(new IdExpr(destName), connExpr), conn.reference));
  
        }
    }
    
}
