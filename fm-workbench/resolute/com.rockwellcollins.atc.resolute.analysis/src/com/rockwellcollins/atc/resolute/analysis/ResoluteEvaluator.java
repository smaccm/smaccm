package com.rockwellcollins.atc.resolute.analysis;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.ConnectionReference;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.SystemOperationMode;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.aadl2.util.OsateDebug;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorTypes;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;

import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.GetProperties;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.IntValue;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.RangeValue;
import com.rockwellcollins.atc.resolute.analysis.values.RealValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.SetValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ClaimString;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.ElementSet;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.Type;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;
import com.rockwellcollins.atc.resolute.resolute.util.ResoluteSwitch;

public class ResoluteEvaluator extends ResoluteSwitch<ResoluteValue> {
    // keeps track of the current value of arguments on the stack
    private Deque<Map<Arg, ResoluteValue>> argMapStack;

    // keeps track of which component the current prove statement was called on
    final private ComponentInstance thisInst;

    // used to draw assurance cases
    final private ResoluteProofTree proofTree;

    // keeps track of which claims have been called with what arguments
    private Set<ClaimCallContext> claimCallContexts;

    private List<SystemOperationMode> modes;
    private SystemOperationMode sysMode = null;


    public ResoluteEvaluator(ComponentInstance thisInst, ResoluteProofTree proofTree) {
        this.thisInst = thisInst;
        this.proofTree = proofTree;

        argMapStack = new LinkedList<>();
        claimCallContexts = new HashSet<>();
    }
    
    public void setModes(List<SystemOperationMode> modes){
        this.modes = modes;
        
        if(modes.size() > 0){
            if (modes.size() != 1) {
                throw new UnsupportedOperationException("Multiple modes not supported: " + modes);
            }
            sysMode = modes.get(0);
            ResoluteQuantifiableAadlObjects.filterBySysMode(sysMode);
        }
        
    }

    @Override
    public ResoluteValue caseFailExpr(FailExpr object) {
        proofTree.addNewCurrent(object, "FAIL");
        if (object.getVal() != null) {
            String failStr = object.getVal().getValue();
            throw new ResoluteFailException(failStr);
        }
        throw new ResoluteFailException("Fail Statement Reached");
    }

    @Override
    public ResoluteValue caseConstantDefinition(ConstantDefinition object) {
        String nodeStr = object.getName();

        proofTree.addNewCurrent(object, nodeStr);
        ResoluteValue result = doSwitch(object.getExpr());

        proofTree.setCurReturnVal(object, nodeStr, result);

        return result;
    }

    @Override
    public ResoluteValue caseArg(Arg object) {
        return argMapStack.getFirst().get(object);
    }

    @Override
    public ResoluteValue caseBinaryExpr(BinaryExpr object) {
        String nodeStr = object.getOp();
        proofTree.addNewCurrent(object, object.getOp());

        // hack to draw implications in a readable way on the graph
        if (object.getOp().equals("=>")) {
            proofTree.addNewCurrent(object, "PREMISE");
        }

        ResoluteValue leftResult = doSwitch(object.getLeft());

        ResoluteValue result = null;

        switch (object.getOp())
        {
        case "lower_bound":
            if (leftResult.getBool()) {
                ResoluteValue rightResult = doSwitch(object.getRight());
                result = rightResult;
            } else {
                result = new BoolValue(false);
            }
            break;
            
        case "upper_bound":
            if (leftResult.getBool()) {
                ResoluteValue rightResult = doSwitch(object.getRight());
                result = rightResult;
            } else {
                result = new BoolValue(false);
            }
            break;
            
            
        case "and":
            if (leftResult.getBool()) {
                ResoluteValue rightResult = doSwitch(object.getRight());
                result = rightResult;
            } else {
                result = new BoolValue(false);
            }
            break;
        case "or":
            if (leftResult.getBool()) {
                result = new BoolValue(true);
            } else {
                ResoluteValue rightResult = doSwitch(object.getRight());
                result = rightResult;
            }
            break;
        case "=>":
            if (!leftResult.getBool()) {

                proofTree.setCurReturnVal(object, "PREMISE", leftResult);
                result = new BoolValue(true);

            } else {
                proofTree.setCurReturnVal(object, "PREMISE", leftResult); // sets
                                                                          // the
                                                                          // 'PREMISE'
                                                                          // value
                proofTree.addNewCurrent(object, "CONCLUSION");
                ResoluteValue rightResult = doSwitch(object.getRight());
                proofTree.setCurReturnVal(object, "CONCLUSION", rightResult);
                result = rightResult;
            }
            break;
        case "=":
        case "<>":
            ResoluteValue rightResult = doSwitch(object.getRight());
            Expr rightExpr = object.getRight();
            Expr leftExpr = object.getLeft();

            if (rightExpr instanceof IdExpr) {
                String retString = rightResult.toString();
                proofTree.addNewCurrent(object, retString);
                proofTree.setCurReturnVal(object, retString, rightResult);
            }

            if (leftExpr instanceof IdExpr) {
                String retString = leftResult.toString();
                proofTree.addNewCurrent(object, retString);
                proofTree.setCurReturnVal(object, retString, leftResult);
            }

            boolean value = leftResult.equals(rightResult);
            result = new BoolValue(object.getOp().equals("=") ? value : !value);
            break;
        case "+":
            rightResult = doSwitch(object.getRight());
            if (leftResult.isInt()) {
                assert (rightResult.isInt());
                result = new IntValue(leftResult.getInt() + rightResult.getInt());
            } else {
                assert (leftResult.isReal() && rightResult.isReal());
                result = new RealValue(leftResult.getReal() + rightResult.getReal());
            }
            break;
        case "-":
            rightResult = doSwitch(object.getRight());
            if (leftResult.isInt()) {
                assert (rightResult.isInt());
                result = new IntValue(leftResult.getInt() - rightResult.getInt());
            } else {
                assert (leftResult.isReal() && rightResult.isReal());
                result = new RealValue(leftResult.getReal() - rightResult.getReal());
            }
            break;
        case "*":
            rightResult = doSwitch(object.getRight());
            if (leftResult.isInt()) {
                assert (rightResult.isInt());
                result = new IntValue(leftResult.getInt() * rightResult.getInt());
            } else {
                assert (leftResult.isReal() && rightResult.isReal());
                result = new RealValue(leftResult.getReal() * rightResult.getReal());
            }
            break;
        case "/":
            rightResult = doSwitch(object.getRight());
            if (leftResult.isInt()) {
                assert (rightResult.isInt());
                result = new IntValue(leftResult.getInt() / rightResult.getInt());
            } else {
                assert (leftResult.isReal() && rightResult.isReal());
                result = new RealValue(leftResult.getReal() / rightResult.getReal());
            }
            break;
        case "in":
            rightResult = doSwitch(object.getRight());
            nodeStr = leftResult + " in " + rightResult;

            assert (rightResult.isSet());
            result = new BoolValue(rightResult.getSet().contains(leftResult));
            break;
        case "union":
            rightResult = doSwitch(object.getRight());
            assert (rightResult.isSet());
            assert (leftResult.isSet());
            Set<ResoluteValue> union = new HashSet<ResoluteValue>();
            union.addAll(leftResult.getSet());
            union.addAll(rightResult.getSet());
            result = new SetValue(union);
            break;
        case "intersect":
            rightResult = doSwitch(object.getRight());
            assert (rightResult.isSet());
            assert (leftResult.isSet());
            Set<ResoluteValue> intersect = new HashSet<ResoluteValue>();
            intersect.addAll(leftResult.getSet());
            intersect.retainAll(rightResult.getSet());
            result = new SetValue(intersect);
            result = rightResult;
            break;
        case "<":
            rightResult = doSwitch(object.getRight());
            if (rightResult.isReal() && leftResult.isReal()) {
                result = new BoolValue(leftResult.getReal() < rightResult.getReal());
            } else {
                assert (rightResult.isInt() && leftResult.isInt());
                result = new BoolValue(leftResult.getInt() < rightResult.getInt());
            }
            break;
        case ">":
            rightResult = doSwitch(object.getRight());
            if (rightResult.isReal() && leftResult.isReal()) {
                result = new BoolValue(leftResult.getReal() > rightResult.getReal());
            } else {
                assert (rightResult.isInt() && leftResult.isInt());
                result = new BoolValue(leftResult.getInt() > rightResult.getInt());
            }
            break;
        case "<=":
            rightResult = doSwitch(object.getRight());
            if (rightResult.isReal() && leftResult.isReal()) {
                result = new BoolValue(leftResult.getReal() <= rightResult.getReal());
            } else {
                assert (rightResult.isInt() && leftResult.isInt());
                result = new BoolValue(leftResult.getInt() <= rightResult.getInt());
            }
            break;
        case ">=":
            rightResult = doSwitch(object.getRight());
            if (rightResult.isReal() && leftResult.isReal()) {
                result = new BoolValue(leftResult.getReal() >= rightResult.getReal());
            } else {
                assert (rightResult.isInt() && leftResult.isInt());
                result = new BoolValue(leftResult.getInt() >= rightResult.getInt());
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown binary operator: " + object.getOp());
        }

        proofTree.setCurReturnVal(object, nodeStr, result);

        return result;
    }

    @Override
    public ResoluteValue caseUnaryExpr(UnaryExpr object) {

        ResoluteValue result = null;
        proofTree.addNewCurrent(object, object.getOp());

        switch (object.getOp()) {
        case "not":
            ResoluteValue tempResult = doSwitch(object.getExpr());
            assert (tempResult.isBool());
            result = new BoolValue(!tempResult.getBool());
            break;
        case "-":
            tempResult = doSwitch(object.getExpr());
            assert (tempResult.isInt() || tempResult.isReal());
            if (tempResult.isInt()) {
                result = new IntValue(-tempResult.getInt());
            } else {
                result = new RealValue(-tempResult.getReal());
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown unary operator: " + object.getOp());
        }
        proofTree.setCurReturnVal(object, object.getOp(), result);
        return result;
    }

    @Override
    public ResoluteValue caseIdExpr(IdExpr object) {
        NamedElement ref = object.getId();
        ElementSet subEls = object.getSubelements();
        if(subEls != null){
            return subElsFromObject(ref, subEls.getName());
        }else if (ref instanceof Classifier || ref instanceof Property) {
            return new NamedElementValue(ref);
        } else {
            return doSwitch(ref);
        }
    }

    private void addAllFeatureGroupConns(FeatureInstance feature, Set<ResoluteValue> returnSet){
        for(FeatureInstance featInst : feature.getFeatureInstances()){
            addAllFeatureGroupConns(featInst, returnSet);
        }
        for(ConnectionInstance conn : feature.getSrcConnectionInstances()){
            returnSet.add(new NamedElementValue(conn));
        }
        for(ConnectionInstance conn : feature.getDstConnectionInstances()){
            returnSet.add(new NamedElementValue(conn));
        }
    }
    
    private ResoluteValue subElsFromObject(NamedElement id, String setName) {
        //TODO: finish this
        
        ComponentInstance compInst = null;
        ResoluteValue idVal = doSwitch(id);
        compInst = (ComponentInstance)idVal.getNamedElement();
        
        if(setName.equals("connections")){
            Set<ResoluteValue> returnSet = new HashSet<ResoluteValue>();
            if(compInst.getCategory() == ComponentCategory.THREAD){
                EList<FeatureInstance> features = compInst.getFeatureInstances();

                for(FeatureInstance feature : features){
                    addAllFeatureGroupConns(feature, returnSet);
                }
            }

            //EList<ConnectionInstance> connections = compInst.getConnectionInstances();
            EList<ConnectionInstance> connections;
            connections = compInst.getSrcConnectionInstances();
            for(ConnectionInstance conn : connections){
                //System.out.println(conn);
                returnSet.add(new NamedElementValue(conn));
            }
            connections = compInst.getDstConnectionInstances();
            for(ConnectionInstance conn : connections){
                //System.out.println(conn);
                returnSet.add(new NamedElementValue(conn));
            }
            return new SetValue(returnSet);
        }
        
        EList<ComponentInstance> childInsts = compInst.getComponentInstances();
        Set<ComponentInstance> filteredInsts = new HashSet<ComponentInstance>();
        for(ComponentInstance childInst : childInsts){
            switch(setName){
            case "threads":
                if(childInst.getCategory() == ComponentCategory.THREAD){
                    filteredInsts.add(childInst);
                }
                break;
            case "data":
                if(childInst.getCategory() == ComponentCategory.DATA){
                    filteredInsts.add(childInst);
                }
                break;
            case "memory":
                if(childInst.getCategory() == ComponentCategory.MEMORY){
                    filteredInsts.add(childInst);
                }
                break;
            case "thread_groups":
                if(childInst.getCategory() == ComponentCategory.THREAD_GROUP){
                    filteredInsts.add(childInst);
                }
                break;
            case "processes":
                if(childInst.getCategory() == ComponentCategory.PROCESS){
                    filteredInsts.add(childInst);
                }
                break;
            case "subprograms":
                if(childInst.getCategory() == ComponentCategory.SUBPROGRAM){
                    filteredInsts.add(childInst);
                }
                break;
            case "subprogram_groups":
                if(childInst.getCategory() == ComponentCategory.SUBPROGRAM_GROUP){
                    filteredInsts.add(childInst);
                }
                break;
            case "processors":
                if(childInst.getCategory() == ComponentCategory.PROCESSOR){
                    filteredInsts.add(childInst);
                }
                break;
            case "virtual_processors":
                if(childInst.getCategory() == ComponentCategory.VIRTUAL_PROCESSOR){
                    filteredInsts.add(childInst);
                }
                break;
            case "buses":
                if(childInst.getCategory() == ComponentCategory.BUS){
                    filteredInsts.add(childInst);
                }
                break;
            case "virtual_buses":
                if(childInst.getCategory() == ComponentCategory.VIRTUAL_BUS){
                    filteredInsts.add(childInst);
                }
                break;
            case "devices":
                if(childInst.getCategory() == ComponentCategory.DEVICE){
                    filteredInsts.add(childInst);
                }
                break;
            case "systems":
                if(childInst.getCategory() == ComponentCategory.SYSTEM){
                    filteredInsts.add(childInst);
                }
                break;
            case "abstracts":
                if(childInst.getCategory() == ComponentCategory.ABSTRACT){
                    filteredInsts.add(childInst);
                }
                break;
            case "components":
                filteredInsts.add(childInst);
                break;
            default:
                assert(false); //unimplemented
            }
            
        }
        
        Set<ResoluteValue> returnSet = new HashSet<ResoluteValue>();
        for(ComponentInstance childInst : filteredInsts){
            returnSet.add(new NamedElementValue(childInst));
        }
        
        return new SetValue(returnSet);
    }
    
    
    private <T> Set<Element> getAllSubElsOfType(Class<T> klass, Set<Element> elements){
        
        Set<Element> result = new HashSet<Element>();
        for(Element el : elements){
            
            if(klass.isInstance(el)){
                
            }
            
        }
        
        
        return null;
    }
    
    

    @Override
    public ResoluteValue caseThisExpr(ThisExpr object) {
        
        NestedDotID id = object.getSub();
        ComponentInstance compInst = thisInst;
        boolean found = false;
        while(id != null){
            
            found = false;
            for(ComponentInstance subCompInst : compInst.getAllComponentInstances()){
                if(subCompInst.getSubcomponent().equals(id.getBase())){
                    compInst = subCompInst;
                    id = id.getSub();
                    found = true;
                    break;
                }
            }
            assert(found);
           
        }
        if(found){
            return new NamedElementValue(compInst);
        }
        
        return new NamedElementValue(thisInst);
    }

    @Override
    public ResoluteValue caseIntExpr(IntExpr object) {
        long intVal = (long) object.getVal().getScaledValue();
        String retString = Long.toString(intVal);
        proofTree.addNewCurrent(object, retString);
        ResoluteValue result = new IntValue(intVal);

        proofTree.setCurReturnVal(object, retString, result);
        return result;
    }

    @Override
    public ResoluteValue caseRealExpr(RealExpr object) {
        double realVal = object.getVal().getValue();
        String retString = Double.toString(realVal);
        proofTree.addNewCurrent(object, retString);
        ResoluteValue result = new RealValue(realVal);

        proofTree.setCurReturnVal(object, retString, result);
        return result;
    }

    @Override
    public ResoluteValue caseBoolExpr(BoolExpr object) {
        boolean boolVal = object.getVal().getValue();
        String retString = Boolean.toString(boolVal);
        proofTree.addNewCurrent(object, retString);
        ResoluteValue result = new BoolValue(boolVal);

        proofTree.setCurReturnVal(object, retString, result);
        return result;
    }

    @Override
    public ResoluteValue caseStringExpr(StringExpr object) {
        // there are an extra set of quotes ("") around a StringExpr
        // that need to be removed
        String str = object.getVal().getValue();
        str = str.replace("\"", "");
        proofTree.addNewCurrent(object, str);
        ResoluteValue result = new StringValue(str);

        proofTree.setCurReturnVal(object, str, result);
        return result;
    }

    @Override
    public ResoluteValue caseIfThenElseExpr(IfThenElseExpr object) {

        Expr cond = object.getCond();

        String nodeStr = "if " + exprToString(cond);
        proofTree.addNewCurrent(object, nodeStr);

        ResoluteValue condResult = doSwitch(cond);
        assert (condResult.isBool());

        ResoluteValue result;
        if (condResult.getBool()) {
            Expr thenExpr = object.getThen();
            result = doSwitch(thenExpr);
        } else {
            Expr elseExpr = object.getElse();
            result = doSwitch(elseExpr);
        }

        proofTree.setCurReturnVal(object, nodeStr, result);
        return result;
    }

    @Override
    public ResoluteValue caseQuantifiedExpr(QuantifiedExpr object) {

        ResoluteValue result = null;
        EList<Arg> argsEList = object.getArgs();
        LinkedList<Arg> argsLinkedList = new LinkedList<Arg>();

        String nodeStr = object.getQuant();
        // make the arglist a linked list
        for (Arg arg : argsEList) {
            nodeStr += "(" + arg.getName() + ")";
            argsLinkedList.add(arg);
        }

        proofTree.addNewCurrent(object, nodeStr);

        boolean boolResult = false;
        switch (object.getQuant()) {
        case "exists":
            boolResult = quantHelper(object.getExpr(), argsLinkedList, false);
            result = new BoolValue(boolResult);

            break;
        case "forall":
            boolResult = quantHelper(object.getExpr(), argsLinkedList, true);
            // assert(proofTree.getCurNode().getExprStr().equals(nodeStr));
            result = new BoolValue(!boolResult);
            break;
        default:
            throw new IllegalArgumentException("Unknown quantifier: " + object.getQuant());
        }

        proofTree.setCurReturnVal(object, nodeStr, result);

        return result;
    }

    @Override
    public ResoluteValue caseFnCallExpr(FnCallExpr object) {
        FunctionDefinition funcDef = object.getFn();
        List<ResoluteValue> argVals = doSwitchList(object.getArgs());

        String text = createFunctionText(funcDef.getName(), funcDef.getArgs(), argVals);
        proofTree.addNewCurrent(funcDef, text);
        //System.out.println(text);

        DefinitionBody body = funcDef.getBody();
        ClaimCallContext context = null;
        if (body instanceof ClaimBody) {
            context = new ClaimCallContext(funcDef, argVals);
            if (claimCallContexts.contains(context)) {
                ResoluteValue result = new BoolValue(false);
                proofTree.setCurReturnVal(funcDef, text, result);
                return result;
            }
            claimCallContexts.add(context);
        }

        argMapStack.push(pairArguments(funcDef.getArgs(), argVals));
        if (body instanceof ClaimBody) {
            ClaimBody claimBody = (ClaimBody) body;
            if (!claimBody.getClaim().isEmpty()) {
                text = createClaimText(claimBody);
            }
        }

        ResoluteValue result = doSwitch(body.getExpr());

        argMapStack.pop();

        if (context != null) {
            claimCallContexts.remove(context);
        }

        proofTree.setCurReturnVal(funcDef, text, result);
        return result;
    }

    private String createFunctionText(String name, EList<Arg> args, List<ResoluteValue> argVals) {
        StringBuilder text = new StringBuilder();
        text.append(name);
        text.append("(");

        if (args != null) {
            for (int i = 0; i < args.size(); i++) {
                ResoluteValue arg = argVals.get(i);
                String argStr;
                if(arg != null){
                    argStr = arg.toString();
                }else{
                    argStr = "null";
                }
                text.append(argStr);
                if (i < args.size() - 1) {
                    text.append(", ");
                }
            }
        }

        text.append(")");
        return text.toString();
    }

    private Map<Arg, ResoluteValue> pairArguments(List<Arg> args, List<ResoluteValue> argVals) {
        Map<Arg, ResoluteValue> result = new HashMap<>();
        if (args == null) {
            return result;
        }

        for (int i = 0; i < args.size(); i++) {
            result.put(args.get(i), argVals.get(i));
        }
        return result;
    }

    private String createClaimText(ClaimBody claimBody) {
        StringBuilder text = new StringBuilder();

        for (Element claim : claimBody.getClaim()) {
            if (claim instanceof ClaimArg) {
                Arg claimArg = ((ClaimArg) claim).getArg();
                ResoluteValue argVal = doSwitch(claimArg);
                if (argVal.isNamedElement()) {
                    addClaimReference(proofTree.getCurNode(), argVal.getNamedElement(),
                            argVal.toString());
                }
                text.append("'");
                text.append(argVal.toString());
                text.append("'");
            } else if (claim instanceof ClaimString) {
                text.append(((ClaimString) claim).getStr());
            } else {
                throw new IllegalArgumentException("Unknown claim type: " + claim.getClass());
            }
        }

        return text.toString();
    }

    private static void addClaimReference(ResoluteProofNode node, NamedElement ne, String argText) {
        if (ne instanceof ComponentInstance) {
            ComponentInstance ci = (ComponentInstance) ne;
            node.addClaimReference(argText, ci.getComponentClassifier());
        } else if (ne instanceof ConnectionInstance) {
            ConnectionInstance ci = (ConnectionInstance) ne;
            node.addClaimReference(argText, ci.getConnectionReferences().get(0).getConnection());
        }
    }

    @Override
    public ResoluteValue caseFilterMapExpr(FilterMapExpr object) {
        LinkedList<Set<NamedElement>> listOfCompLists = new LinkedList<Set<NamedElement>>();
        LinkedList<Arg> args = new LinkedList<Arg>();
        for (Arg arg : object.getArgs()) {
            args.push(arg);
            
            if(arg instanceof QuantArg){
                ResoluteValue resVal = doSwitch(((QuantArg)arg).getExpr());
                Set<NamedElement> compSet = new HashSet<NamedElement>();
                for(ResoluteValue resValIter : ((SetValue)resVal).getSet()){
                    compSet.add(resValIter.getNamedElement());
                }
                listOfCompLists.add(compSet);
            }else{
                listOfCompLists.add(ResoluteQuantifiableAadlObjects.getAllComponentsOfType(arg
                    .getType().getName(), modes.size() > 0));
            }
        }
        proofTree.addNewCurrent(object, "{LIST CALC}");
        LinkedList<ResoluteValue> valSet = new LinkedList<ResoluteValue>();
        mapIterateSets(object.getMap(), object.getFilter(), args, listOfCompLists, valSet);

        ResoluteValue result = new SetValue(valSet);
        proofTree.setCurReturnVal(object, result.toString(), result);
        return result;
    }

    @Override
    public ResoluteValue caseBuiltInFuncCallExpr(BuiltInFuncCallExpr object) {
        String funName = object.getFn();
        String nodeStr = funName;
        ResoluteValue result = null;
        List<ResoluteValue> argVals = doSwitchList(object.getArgs());

        proofTree.addNewCurrent(object, exprToString(object));

        // proofTree.addNewCurrent(funName);

        ResoluteValue compVal;
        ComponentInstance compInst;
        switch (funName)
        {
        case "error_state_reachable":
        {
        	result = new BoolValue(false);
        	
            ResoluteValue comp = argVals.get(0);
            ResoluteValue stateVal = argVals.get(1);
            
            assert (comp.getNamedElement() instanceof ComponentInstance);
            
            ComponentInstance componentInstance = (ComponentInstance) comp.getNamedElement();
            String stateName = stateVal.getString();

            for (ErrorBehaviorTransition ebt : EMV2Util.getAllErrorBehaviorTransitions(componentInstance))
            {
            	String tmp = ebt.getTarget().getName();
            	if (tmp.equalsIgnoreCase(stateName))
            	{
            		result = new BoolValue(true);
            	}
            	
            }
            break;
        }
        
        
        case "propagate_error":
        {
        	result = new BoolValue(false);
        	
            ResoluteValue comp = argVals.get(0);
            ResoluteValue errorVal = argVals.get(1);
            
            assert (comp.getNamedElement() instanceof ComponentInstance);
            
            ComponentInstance componentInstance = (ComponentInstance) comp.getNamedElement();
            String errorName = errorVal.getString();

            for (ErrorPropagation ep : EMV2Util.getAllOutgoingErrorPropagations (componentInstance.getComponentClassifier()))
            {
            	for (TypeToken tt : ep.getTypeSet().getTypeTokens())
            	{
            		for (ErrorTypes et : tt.getType())
            		{
                        if (et.getName().equalsIgnoreCase(errorName))
                        {
                        	result = new BoolValue(true);
                        }
            			
            		}
            	}
            }
            
            break;
        }
        case "connected":
            // TODO: come up with better defined semantics about what it means
            // to be "connected"
        {

            ResoluteValue comp0Val;
            ResoluteValue comp1Val;
            ResoluteValue connVal;
            ConnectionInstanceEnd allDest; 
            ConnectionInstanceEnd allSource;
            
            comp0Val = argVals.get(0);
            connVal = argVals.get(1);
            comp1Val = argVals.get(2);

            assert (comp0Val.getNamedElement() instanceof ComponentInstance);
            assert (connVal.getNamedElement() instanceof ConnectionInstance);
            assert (comp1Val.getNamedElement() instanceof ComponentInstance);

            ComponentInstance compInst0 = (ComponentInstance) comp0Val.getNamedElement();
            ConnectionInstance conn = (ConnectionInstance) connVal.getNamedElement();
            ComponentInstance compInst1 = (ComponentInstance) comp1Val.getNamedElement();

            nodeStr += "(" + compInst0.getName() + ", " + conn.getName() + ", "
                    + compInst1.getName() + ")";

            allDest = conn.getDestination();
            allSource = conn.getSource();
            
            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");
            
            
            //code for data accesses
            if(allDest instanceof FeatureInstance && 
                    ((FeatureInstance)allDest).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allDest, accessRightProp);
                if(compInst1.equals(allDest.eContainer()) && compInst0.equals(allSource.eContainer())){
                    if(lit.getName().equals("read_only") || lit.getName().equals("read_write")){
                        result = new BoolValue(true);
                        break;
                    }
                }
                if(compInst0.equals(allDest.eContainer()) && compInst1.equals(allSource.eContainer())){
                    if(lit.getName().equals("write_only") || lit.getName().equals("read_write")){
                        result = new BoolValue(true);
                        break;
                    }
                }
                result = new BoolValue(false);
                break;
            }
            
            //code for data accesses
            if(allSource instanceof FeatureInstance && 
                    ((FeatureInstance)allSource).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allSource, accessRightProp);
                if(compInst1.equals(allDest.eContainer()) && compInst0.equals(allSource.eContainer())){
                    if(lit.getName().equals("write_only") || lit.getName().equals("read_write")){
                        result = new BoolValue(true);
                        break;
                    }
                }
                if(compInst0.equals(allDest.eContainer()) && compInst1.equals(allSource.eContainer())){
                    if(lit.getName().equals("read_only") || lit.getName().equals("read_write")){
                        result = new BoolValue(true);
                        break;
                    }
                }
                result = new BoolValue(false);
                break;
            }

            if (allSource.getComponentInstance().equals(compInst0)
                    && allDest.getComponentInstance().equals(compInst1)) {
                result = new BoolValue(true);
            } else {
                result = new BoolValue(false);
            }

            break;
        }

        case "property_lookup":
        {
            ResoluteValue comp0Val;
            ResoluteValue propVal;
            
            comp0Val = argVals.get(0);
            propVal = argVals.get(1);

            assert (propVal.getNamedElement() instanceof Property);
            Property prop = (Property) propVal.getNamedElement();

            PropertyExpression expr;
            if (comp0Val.getNamedElement() instanceof ComponentInstance) {
                ComponentInstance comp = (ComponentInstance) comp0Val.getNamedElement();
                nodeStr += "(" + comp.getName() + ", " + prop.getName() + ")";
                expr = getPropExpression(comp, prop);
            } else if (comp0Val.getNamedElement() instanceof FeatureInstance)
            {
            	 FeatureInstance feat = (FeatureInstance) comp0Val.getNamedElement();
            	 expr = getPropExpression(feat, prop);
            }
            else {
                assert (comp0Val.getNamedElement() instanceof ComponentType);
                ComponentType comp = (ComponentType) comp0Val.getNamedElement();
                nodeStr += "(" + comp.getName() + ", " + prop.getName() + ")";
                expr = getPropExpression(comp, prop);
            }

            if (expr != null) {
                if (expr instanceof StringLiteral) {
                    StringLiteral value = (StringLiteral) expr;
                    // nodeStr += value.getValue() + ")";
                    result = new StringValue(value.getValue());
                } else if (expr instanceof NamedValue) {
                    NamedValue namedVal = (NamedValue) expr;
                    AbstractNamedValue absVal = namedVal.getNamedValue();
                    assert (absVal instanceof EnumerationLiteral);
                    EnumerationLiteral enVal = (EnumerationLiteral) absVal;

                    // nodeStr += enVal.getName() + ")";
                    result = new StringValue(enVal.getName());
                } else if (expr instanceof BooleanLiteral) {
                    BooleanLiteral value = (BooleanLiteral) expr;
                    result = new BoolValue(value.getValue());
                } else if (expr instanceof IntegerLiteral) {
                    IntegerLiteral value = (IntegerLiteral) expr;
                    result = new IntValue((long) value.getScaledValue());
                } else if (expr instanceof RealLiteral)
                {
                    assert (expr instanceof RealLiteral);
                    RealLiteral value = (RealLiteral) expr;
                    result = new RealValue(value.getValue());
                }
                else if (expr instanceof org.osate.aadl2.RangeValue){
                    assert (expr instanceof org.osate.aadl2.RangeValue);
                    org.osate.aadl2.RangeValue value = (org.osate.aadl2.RangeValue) expr;
                    PropertyExpression lb = value.getMinimumValue();
                    PropertyExpression ub = value.getMaximumValue();
                    if (value.getMinimumValue() instanceof IntegerLiteral)
                    {
                    	IntegerLiteral min = (IntegerLiteral) value.getMinimumValue();
                    	IntegerLiteral max = (IntegerLiteral) value.getMaximumValue(); 
                    	result = new RangeValue ((long)min.getScaledValue(), (long)max.getScaledValue());
                    }
                    else if (value.getMinimumValue() instanceof RealLiteral)
                    {
                    	IntegerLiteral min = (IntegerLiteral) value.getMinimumValue();
                    	IntegerLiteral max = (IntegerLiteral) value.getMaximumValue(); 
                    	result = new RangeValue ((double)min.getScaledValue(), (double)max.getScaledValue());
                    }
                    else
                    {
                    	OsateDebug.osateDebug("[ResoluteEvaluator] Unknown range type: " + expr);

                    }
                   
                    
                }
                else
                {
                	OsateDebug.osateDebug("[ResoluteEvaluator] Unknown type: " + expr);
                }
                break;
            }
            break;
        }
        
        case "upper_bound":
        {
        	ResoluteValue arg0 = argVals.get(0);
            
        	assert (arg0 instanceof RangeValue);
        	
        	RangeValue rv = (RangeValue) arg0;
        	
        	if (rv.getType() == RangeValue.TYPE_INTEGER)
        	{
        		result = new IntValue (rv.getMaximumLong());
        	}
        	
        	if (rv.getType() == RangeValue.TYPE_REAL)
        	{
        		result = new RealValue (rv.getMaximumDouble());
        	}
        	
        	
            break;
        }
        
        case "lower_bound":
        {
        	ResoluteValue arg0 = argVals.get(0);
            
        	assert (arg0 instanceof RangeValue);
        	
        	RangeValue rv = (RangeValue) arg0;
        	
        	if (rv.getType() == RangeValue.TYPE_INTEGER)
        	{
        		result = new IntValue (rv.getMinimumLong());
        	}
        	
        	if (rv.getType() == RangeValue.TYPE_REAL)
        	{
        		result = new RealValue (rv.getMinimumDouble());
        	}
        	
        	
            break;
        }
            
        case "property_exists":
        {

            ResoluteValue comp0Val;
            ResoluteValue propVal;

            ResoluteValue comp1Val;
            ResoluteValue connVal;
            ConnectionInstance conn;
            
            Property prop;
            // the first element is the component
            comp0Val = argVals.get(0);
            // the second element is the property
            propVal = argVals.get(1);

            assert (propVal.getNamedElement() instanceof Property);
            prop = (Property) propVal.getNamedElement();

            if (comp0Val.getNamedElement() instanceof ComponentInstance) {
                ComponentInstance comp = (ComponentInstance) comp0Val.getNamedElement();
                nodeStr += "(" + comp.getName() + ", " + prop.getName() + ")";
                result = getPropExists(comp, prop);
            } else if (comp0Val.getNamedElement() instanceof ComponentType) {
                assert (comp0Val.getNamedElement() instanceof ComponentType);
                ComponentType comp = (ComponentType) comp0Val.getNamedElement();
                nodeStr += "(" + comp.getName() + ", " + prop.getName() + ")";
                result = getPropExists(comp, prop);
            } else {
                assert (comp0Val.getNamedElement() instanceof ConnectionInstance);
                conn = (ConnectionInstance) comp0Val.getNamedElement();

                // result = getPropExists(conn, prop);
                for (ConnectionReference ref : conn.getConnectionReferences()) {
                    result = getPropExists(ref, prop);
                    assert (result.isBool());
                    if (result.getBool()) {
                        break;
                    }
                }
                nodeStr += "(" + conn.getName() + ", " + prop.getName() + ")";

            }

            break;    
        }
        
        case "class_of":
        {

            ResoluteValue comp0Val;
            ResoluteValue comp1Val;
            comp0Val = argVals.get(0);
            comp1Val = argVals.get(1);

            assert (comp0Val.getNamedElement() instanceof ComponentInstance);
            assert (comp1Val.getNamedElement() instanceof ComponentClassifier);

            compInst = (ComponentInstance) comp0Val.getNamedElement();
            ComponentClassifier compClass = (ComponentClassifier) comp1Val.getNamedElement();

            nodeStr += "(" + compInst.getName() + ", " + compClass.getName() + ")";

            result = new BoolValue(compInst.getComponentClassifier().equals(compClass));
            break;
        }
        
        case "type":
            NamedElement el = argVals.get(0).getNamedElement();
            nodeStr += "(" + el.getName() + ")";
            result = new NamedElementValue(builtinType(el));
            break;

        case "has_type":
            NamedElement el2 = argVals.get(0).getNamedElement();
            nodeStr += "(" + el2.getName() + ")";
            result = new BoolValue(builtinType(el2) != null);
            break;

        case "bound":
            ComponentInstance ci0 = (ComponentInstance) argVals.get(0).getNamedElement();
            ComponentInstance ci1 = (ComponentInstance) argVals.get(1).getNamedElement();
            boolean bound = GetProperties.getActualMemoryBinding(ci0).contains(ci1)
                    || GetProperties.getActualConnectionBinding(ci0).contains(ci1)
                    || GetProperties.getActualProcessorBinding(ci0).contains(ci1);
            result = new BoolValue(bound);
            break;

        case "contained":
        {
        	ConnectionInstance conn;
        	ConnectionInstanceEnd allDest; 
            ConnectionInstanceEnd allSource;
            ResoluteValue val0;
            ResoluteValue val1; 
            val0 = argVals.get(0);
            val1 = argVals.get(1);

            NamedElement innerEl = val0.getNamedElement();
            assert (val1.getNamedElement() instanceof ComponentInstance);
            ComponentInstance outerComp = (ComponentInstance) val1.getNamedElement();

            nodeStr += "(" + innerEl.getName() + ", " + outerComp.getName() + ")";

            if (innerEl instanceof ConnectionInstance) {
                conn = (ConnectionInstance) innerEl;

                allDest = conn.getDestination();
                allSource = conn.getSource();

                if (allDest.getComponentInstance().equals(outerComp)
                        || allSource.getComponentInstance().equals(outerComp)) {
                    result = new BoolValue(true);
                } else {
                    result = new BoolValue(false);
                }
                break;
            }
            assert (innerEl instanceof ComponentInstance);
            ComponentInstance innerComp = (ComponentInstance) innerEl;
            ComponentInstance innerCompContainer = innerComp.getContainingComponentInstance();

            result = new BoolValue(innerCompContainer.equals(outerComp));
            break;
        }
        
        case "conn_source":
        {

            ResoluteValue connVal;
            ConnectionInstance conn;
            connVal = argVals.get(0);
            assert (connVal.getNamedElement() instanceof ConnectionInstance);

            conn = (ConnectionInstance) connVal.getNamedElement();
            nodeStr += "(" + conn.getName() + ")";
            
            ConnectionInstanceEnd allDest = conn.getDestination();
            ConnectionInstanceEnd allSource = conn.getSource();
            
            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");
            
            //code for data accesses
            if(allDest instanceof FeatureInstance && 
                    ((FeatureInstance)allDest).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allDest, accessRightProp);
                if(lit.getName().equals("read_write") || lit.getName().equals("write_only")){
                    NamedElement namedEl = allDest;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }else{
                    NamedElement namedEl = allSource;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);

                }
                break;
            }
            
            //code for data accesses
            if(allSource instanceof FeatureInstance && 
                    ((FeatureInstance)allSource).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allSource, accessRightProp);
                if(lit.getName().equals("read_write") || lit.getName().equals("write_only")){
                    NamedElement namedEl = allSource;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }else{
                    NamedElement namedEl = allDest;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }
                break;
            }
            
            result = new NamedElementValue(conn.getSource().getComponentInstance());

            break;
        }
        
        case "conn_dest":
        {
            ResoluteValue connVal;
            ConnectionInstance conn;
        
            connVal = argVals.get(0);
            assert (connVal.getNamedElement() instanceof ConnectionInstance);

            conn = (ConnectionInstance) connVal.getNamedElement();
            nodeStr += "(" + conn.getName() + ")";
            
            ConnectionInstanceEnd allDest = conn.getDestination();
            ConnectionInstanceEnd allSource = conn.getSource();
            
            Property accessRightProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                    OsateResourceUtil.getResourceSet(), "Memory_Properties::Access_Right");
            
            
            //code for data accesses
            if(allDest instanceof FeatureInstance && 
                    ((FeatureInstance)allDest).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allDest, accessRightProp);
                if(lit.getName().equals("read_write") || lit.getName().equals("read_only")){
                    NamedElement namedEl = allDest;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }else{
                    NamedElement namedEl = allSource;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);

                }
            }

            
            //code for data accesses
            if(allSource instanceof FeatureInstance && 
                    ((FeatureInstance)allSource).getCategory() == FeatureCategory.DATA_ACCESS){
                EnumerationLiteral lit = PropertyUtils.getEnumLiteral(allSource, accessRightProp);
                if(lit.getName().equals("read_write") || lit.getName().equals("read_only")){
                    NamedElement namedEl = allSource;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }else{
                    NamedElement namedEl = allDest;
                    while(!(namedEl instanceof ComponentInstance)){
                        namedEl = (NamedElement) namedEl.eContainer();
                    }
                    result = new NamedElementValue(namedEl);
                }
                assert(false);
            }
            
            
            result = new NamedElementValue(conn.getDestination().getComponentInstance());
            break;
        }
        
        case "get_feature":
        {

            ResoluteValue connVal;
            ComponentInstance comp0Val;
            ConnectionInstance conn;
            connVal = argVals.get(1);
            comp0Val = (ComponentInstance) argVals.get(0).getNamedElement();
            
            assert (connVal.getNamedElement() instanceof ConnectionInstance);
            assert (comp0Val instanceof ConnectionInstance);
            
            conn = (ConnectionInstance) connVal.getNamedElement();
            for (FeatureInstance fi : comp0Val.getFeatureInstances())
            {
            	for (ConnectionInstance ci : fi.getAllEnclosingConnectionInstances())
            	{
            		if ((ci == conn) && (ci.getDestination() == fi))
            		{
            		result = new NamedElementValue(fi);
            		break;
            		}
            	}
            }
            OsateDebug.osateDebug("result=" + result);
            break;  
        }
            
        case "sum":
            ResoluteValue setVal = argVals.get(0);
            assert (setVal.isSet());

            // determine if the elements are ints or reals

            if (!setVal.getSet().iterator().hasNext()) {
                result = new IntValue(0);
                break;
            }
            ResoluteValue firstItem = setVal.getSet().iterator().next();

            if (firstItem.isInt()) {
                long sum = 0;
                for (ResoluteValue setItem : setVal.getSet()) {
                    sum += setItem.getInt();
                }
                result = new IntValue(sum);
            } else {
                assert (firstItem.isReal());
                double sum = 0;
                for (ResoluteValue setItem : setVal.getSet()) {
                    sum += setItem.getReal();
                }
                result = new RealValue(sum);
            }
            break;
        case "analysis":

            ResoluteValue stringVal = argVals.get(0);
            assert (stringVal.isString());

            String analysisName = stringVal.getString();
            nodeStr = "analysis(\"" + analysisName + "\", ";

            List<ResoluteValue> analysisArgVals = new ArrayList<ResoluteValue>();

            for (int i = 1; i < argVals.size(); i++) {
                analysisArgVals.add(argVals.get(i));
                nodeStr += argVals.get(i).toString() + ", ";
            }

            nodeStr = nodeStr.substring(0, nodeStr.length() - 2) + ")";
            proofTree.getCurNode().setExprStr(nodeStr);

            result = EvaluateExternalAnalysis.evaluate(this, analysisName, argVals);

            if (result == null) {
                String errorString = "External analysis '" + analysisName + "' failed with args:\n";
                for (ResoluteValue argVal : argVals) {
                    errorString += argVal.toString() + "\n";
                }
                throw new ResoluteFailException(errorString);
            }

            break;
        case "is_data":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.DATA);
            break;
        case "is_thread":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.THREAD);
            break;
        case "is_thread_group":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.THREAD_GROUP);
            break;
        case "is_process":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.PROCESS);
            break;
        case "is_subprogram":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.SUBPROGRAM);
            break;
        case "is_subprogram_group":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.SUBPROGRAM_GROUP);
            break;
        case "is_processor":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.PROCESS);
            break;
        case "is_virtual_processor":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.VIRTUAL_PROCESSOR);
            break;
        case "is_memory":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.MEMORY);
            break;
        case "is_bus":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.BUS);
            break;
        case "is_virtual_bus":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.VIRTUAL_BUS);
            break;
        case "is_device":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.DEVICE);
            break;
        case "is_system":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.SYSTEM);
            break;
        case "is_abstract":
            compVal = argVals.get(0);
            compInst = (ComponentInstance)compVal.getNamedElement();
            result = new BoolValue(compInst.getCategory() == ComponentCategory.ABSTRACT);
            break;
        case "is_empty":
            compVal = argVals.get(0);
            result = new BoolValue(((SetValue)compVal).getSet().isEmpty());
            break;
             
        case "identity":
            compVal = argVals.get(0);
            Set<ResoluteValue> compSet = new HashSet<ResoluteValue>();
            compSet.add(compVal);
            result = new SetValue(compSet);
            break;
            
        default:
            throw new IllegalArgumentException("Unknown function: " + funName);
        }

        proofTree.setCurReturnVal(object, nodeStr, result);
        if(result.isNamedElement()){
            if(result.getNamedElement() instanceof FeatureInstance){
                System.out.println(result.getNamedElement());
            }
        }
        return result;
    }

    private NamedElement builtinType(NamedElement ne) {
        if (ne instanceof ConnectionInstance) {
            ConnectionInstance ci = (ConnectionInstance) ne;
            if (ci.getSource() instanceof FeatureInstance) {
                FeatureInstance src = (FeatureInstance) ci.getSource();
                return (NamedElement) src.getFeature().getFeatureClassifier();
            } else if (ci.getSource() instanceof ComponentInstance) {
                return ci.getSource();
            }
        }
        
        throw new IllegalArgumentException("Unable to get type of: " + ne);
    }

    /************** begin utility functions ***************/

    private PropertyExpression getPropExpression(NamedElement comp, Property prop) {
        try {
            comp.getPropertyValue(prop); // this just checks to see if the
                                         // property is associated
            PropertyExpression expr = PropertyUtils.getSimplePropertyValue(comp, prop);
            return expr;
        } catch (PropertyDoesNotApplyToHolderException propException) {
            return null;
        } catch (PropertyNotPresentException propNotPresentException) {
            return null;
        }
    }

    private ResoluteValue getPropExists(NamedElement comp, Property prop) {
        try {
            comp.getPropertyValue(prop); // this just checks to see if the
                                         // property is associated
            PropertyUtils.getSimplePropertyValue(comp, prop);
        } catch (PropertyDoesNotApplyToHolderException propException) {
            return new BoolValue(false);
        } catch (PropertyNotPresentException propNotPresentException) {
            return new BoolValue(false);
        }
        return new BoolValue(true);
    }

    public void mapIterateSets(Expr expr, Expr filterExpr, LinkedList<Arg> freeArgs,
            LinkedList<Set<NamedElement>> listOfCompLists, LinkedList<ResoluteValue> valList) {

        assert (freeArgs.size() == listOfCompLists.size());

        if (freeArgs.size() == 0) {
            if (filterExpr == null) {
                ResoluteValue mapResult = doSwitch(expr);
                valList.add(mapResult);
                return;
            }
            ResoluteValue result = doSwitch(filterExpr);
            assert (result.isBool());
            if (result.getBool()) {
                ResoluteValue mapResult = doSwitch(expr);
                valList.add(mapResult);
            } else {
                // remove failed checks from the proof
                // tree to improve readability
                // ResoluteProofNode proofNode = proofTree.getCurNode();
                // proofNode.removeChildIndex(proofNode.getChildren().size()-1);
            }
            return;
        }

        Set<NamedElement> components = listOfCompLists.pop();
        Arg arg = freeArgs.pop();
        Map<Arg, ResoluteValue> argVals = argMapStack.getFirst();
        for (NamedElement el : components) {
            argVals.put(arg, new NamedElementValue(el));
            mapIterateSets(expr, filterExpr, freeArgs, listOfCompLists, valList);
            argVals.remove(arg);
        }
        freeArgs.push(arg);
        listOfCompLists.push(components);
    }

    // this is a very stupid hacky way of implementing this
    // it could be vastly improved. the "compl" value is true
    // if the expression should be complemented (i.e., if "compl" should be
    // false
    // for existential quantification and true for universal
    public boolean quantHelper(Expr expr, LinkedList<Arg> freeArgs, boolean compl) {

        LinkedList<Set<NamedElement>> listOfCompLists = new LinkedList<Set<NamedElement>>();

        for (Arg arg : freeArgs) {

            if(arg instanceof QuantArg){
                QuantArg quantArg = (QuantArg)arg;
                ResoluteValue argSetVal = doSwitch(quantArg.getExpr());
                SetValue setValue = (SetValue)argSetVal;

                Set<ResoluteValue> resVals = setValue.getSet();
                Set<NamedElement> namedVals = new HashSet<NamedElement>();

                for(ResoluteValue resVal : resVals){
                    namedVals.add(resVal.getNamedElement());
                }

                listOfCompLists.add(namedVals);
            }else{
                listOfCompLists.add(ResoluteQuantifiableAadlObjects.getAllComponentsOfType(arg
                        .getType().getName(), modes.size() > 0));
            }
        }

        return quantIterateSets(expr, freeArgs, listOfCompLists, compl);
    }

    // this function evaluates the given expression for every combination of
    // values to the given "free args". It returns true if it finds a satisfying
    // assignment to all the free arguments
    public boolean quantIterateSets(Expr expr, LinkedList<Arg> freeArgs,
            LinkedList<Set<NamedElement>> listOfCompLists, boolean compl) {

        assert (freeArgs.size() == listOfCompLists.size());

        if (freeArgs.size() == 0) {
            ResoluteValue result = doSwitch(expr);
            assert (result.isBool());
            if (result.getBool() ^ compl) {
                return true;
            }
            return false;
        }

        Set<NamedElement> components = listOfCompLists.pop();

        if (components.size() == 0) {
            //throw new ResoluteQuantifierException("quantifier references components "
            //        + "of a type which are not present in this instance");
            return !compl;
        }

        Arg arg = freeArgs.pop();
        Map<Arg, ResoluteValue> argVals = argMapStack.getFirst();

        assert (components.size() > 0);
        for (NamedElement el : components) {
            argVals.put(arg, new NamedElementValue(el));
            if (quantIterateSets(expr, freeArgs, listOfCompLists, compl)) {
                return true;
            }
            argVals.remove(arg);
        }
        freeArgs.push(arg);
        listOfCompLists.push(components);

        return false;
    }

    public ResoluteProofTree getProofTree() {
        return proofTree;
    }

    public String proveStatementToString(ProveStatement ps) {
        FnCallExpr fnCall = (FnCallExpr) ps.getExpr();

        StringBuilder text = new StringBuilder();
        text.append(fnCall.getFn().getName());
        text.append("(");
        Iterator<Expr> iterator = fnCall.getArgs().iterator();
        while (iterator.hasNext()) {
            Expr arg = iterator.next();
            if (arg instanceof ThisExpr) {
                text.append(new NamedElementValue(thisInst));
            } else {
                text.append(exprToString(arg));
            }
            if (iterator.hasNext()) {
                text.append(", ");
            }
        }
        text.append(")");
        return text.toString();
    }

    public String exprToString(Expr expr) {
        ICompositeNode compNode = NodeModelUtils.getNode(expr);
        if (compNode != null) {
            return NodeModelUtils.getTokenText(compNode);
        }
        return null;
    }

    public List<ResoluteValue> doSwitchList(List<? extends EObject> list) {
        List<ResoluteValue> result = new ArrayList<>();
        for (EObject e : list) {
            result.add(doSwitch(e));
        }
        return result;
    }
    
    public void printStack(){
        
        Iterator<Map<Arg, ResoluteValue>> stackIter = argMapStack.descendingIterator();
        System.out.println("Stack Entry:");
        while(stackIter.hasNext()){
            Map<Arg, ResoluteValue> argMap = stackIter.next();
            
            for(Entry<Arg, ResoluteValue> entry : argMap.entrySet()){
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println();
        }
        
    }
    
}
