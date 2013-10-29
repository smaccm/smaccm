package com.rockwellcollins.atc.agree.analysis;
 
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.osate.aadl2.AbstractConnectionEnd;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.ClassifierFeature;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ConnectionEnd;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.DataType;
import org.osate.aadl2.DirectedFeature;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.ThreadSubcomponent;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CondactExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.Program;
import jkind.lustre.RealExpr;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;

public class AgreeAnnexEmitter extends AgreeSwitch<Expr> {

    //lists of all the jkind expressions from the annex
    private final List<Expr> assumpExpressions = new ArrayList<>();
    private final List<Equation> guarExpressions = new ArrayList<>();
    private final List<Expr> assertExpressions = new ArrayList<>();
    private final List<Equation> propExpressions = new ArrayList<>();
    private final List<Equation> eqExpressions = new ArrayList<>();
    private final List<Equation> constExpressions = new ArrayList<>();
    private final List<Node> nodeDefExpressions = new ArrayList<>();
    private final List<Equation> connExpressions = new ArrayList<>();
    private final String sysGuarTag = "__SYS_GUARANTEE_";
    
    //reference map used for hyperlinking from the console
    public final Map<String, EObject> refMap = new HashMap<>();
    
    //used for preventing name collision, and for pretty printing aadl variables
    private final String jKindNameTag;
    private final String aadlNameTag;
    
    //a list of the parents of this component for handling "Get_Property" queries
    private List<NamedElement> modelParents;

    //used for pretty printing jkind -> aadl variables
    public final Map<String, String> varRenaming = new HashMap<>();
    
    //used for printing results
    public final AgreeLayout layout;
    private final String category;
    
    //keeps track of new variables
    private final Set<AgreeVarDecl> inputVars = new HashSet<>();
    private final Set<AgreeVarDecl> internalVars = new HashSet<>();
    private final Set<AgreeVarDecl> outputVars = new HashSet<>();

    //the special string used to replace the "." characters in aadl
    private final String dotChar = "__";
    
    //the current subcomponent
    private Subcomponent curComp; 
    
    //the current implementation
    private ComponentInstance curInst;
    
    //print errors and warnings here
    public final AgreeLogger log = new AgreeLogger();
    
    //used to keep track of the different properties
    public List<String> assumProps = null;
    public List<String> consistProps = null;
    public List<String> guarProps = null;
    
    //during recursive analysis we do not want to use lifts
    //when proving top level components
    private boolean ignoreLifts;
    
    //the depth of which to check consistency
    private int consistUnrollDepth = 5;
    
    //TODO: do something more robust about this later
    //holds initial values for types
    private Map<String, Expr> initTypeMap = new HashMap<>();
    private BoolExpr initBool = new BoolExpr(true);
    private RealExpr initReal = new RealExpr(new BigDecimal("0.0"));
    private IntExpr initInt = new IntExpr(BigInteger.ZERO);
    
    //the node generated by the getComponentNode() call;
    private AgreeNode agreeNode = null;

    public AgreeAnnexEmitter(ComponentInstance compInst,
            AgreeLayout layout,
            String category,
            String jPrefix,
            String aPrefix,
            boolean ignoreLifts){
        this.layout = layout;
        this.curInst = compInst;
        this.category = category;
        this.ignoreLifts  = ignoreLifts;
        
        initTypeMap.put("bool", initBool);
        initTypeMap.put("real", initReal);
        initTypeMap.put("int", initInt);
        
        if(!layout.getCategories().contains(category)){
            layout.addCategory(category);
        }
        
        curComp = curInst.getSubcomponent();
        
        jKindNameTag = jPrefix;
        aadlNameTag = aPrefix;
        //populates the connection equivalences
        ComponentClassifier compClass = compInst.getComponentClassifier();
        if(compClass instanceof ComponentImplementation){
            setVarEquivs((ComponentImplementation)compClass, jPrefix, aPrefix);
        }
        
    }
    
   // ************** CASE STATEMENTS **********************

    
    @Override
    public Expr caseLiftStatement(LiftStatement lift){
        
        if(ignoreLifts){
            return null;
        }
        
        NestedDotID nestId = lift.getSubcomp();
        
        Subcomponent subComp = (Subcomponent)nestId.getBase();
        ComponentInstance subCompInst = curInst.findSubcomponentInstance(subComp);
        ComponentImplementation subCompImpl = subComp.getComponentImplementation();
        ComponentType subCompType = subCompImpl.getType();

        AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                subCompInst, layout, category,
                jKindNameTag + subComp.getName() + dotChar,
                aadlNameTag + subComp.getFullName() + ".", false);
        
        for (AnnexSubclause annex : subCompImpl.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                subEmitter.doSwitch(annex);
            }
        }

        for (AnnexSubclause annex : subCompType.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                subEmitter.doSwitch(annex);
            }
        }
        
        connExpressions.addAll(subEmitter.connExpressions);
        guarExpressions.addAll(subEmitter.guarExpressions);
        assumpExpressions.addAll(subEmitter.assumpExpressions);
        constExpressions.addAll(subEmitter.constExpressions);
        assertExpressions.addAll(subEmitter.assertExpressions);
        eqExpressions.addAll(subEmitter.eqExpressions);
        nodeDefExpressions.addAll(subEmitter.nodeDefExpressions);
        propExpressions.addAll(subEmitter.propExpressions);
        
        subEmitter.inputVars.removeAll(internalVars);
        subEmitter.outputVars.removeAll(internalVars);
        inputVars.removeAll(subEmitter.internalVars);
        outputVars.removeAll(subEmitter.internalVars);
        
        inputVars.addAll(subEmitter.inputVars);
        outputVars.addAll(subEmitter.outputVars);
        internalVars.addAll(subEmitter.internalVars);
        
        varRenaming.putAll(subEmitter.varRenaming);
        refMap.putAll(subEmitter.refMap);
       
        return null;
    }
    
    
    @Override
    public Expr caseAgreeContractSubclause(AgreeContractSubclause contract) {

        return doSwitch(contract.getContract());
    }

    @Override
    public Expr caseAgreeContract(AgreeContract contract) {

        for (SpecStatement spec : contract.getSpecs()) {
            doSwitch(spec);
        }

        return null;
    }

    @Override
    public Expr caseAssumeStatement(AssumeStatement state) {

        Expr expr = doSwitch(state.getExpr());
        assumpExpressions.add(expr);
        return expr;
    }

    @Override
    public Expr caseLemmaStatement(LemmaStatement state) {
        Expr expr = doSwitch(state.getExpr());
        String guarStr = state.getStr();
        guarStr = guarStr.replace("\"", "");
        refMap.put(guarStr, state);
        IdExpr strId = new IdExpr(guarStr);
        Equation eq = new Equation(strId, expr);
        guarExpressions.add(eq);
        return expr;
    }

    @Override
    public Expr caseGuaranteeStatement(GuaranteeStatement state) {

        Expr expr = doSwitch(state.getExpr());
        String guarStr = state.getStr();
        guarStr = guarStr.replace("\"", "");
        refMap.put(guarStr, state);
        IdExpr strId = new IdExpr(guarStr);
        Equation eq = new Equation(strId, expr);
        guarExpressions.add(eq);
        return expr;
    }

    @Override
    public Expr caseAssertStatement(AssertStatement state) {

        Expr expr = doSwitch(state.getExpr());
        assertExpressions.add(expr);

        return expr;
    }

    @Override
    public Expr casePropertyStatement(PropertyStatement state) {

        Expr expr = doSwitch(state.getExpr());

        AgreeVarDecl varDecl = new AgreeVarDecl();
        varDecl.jKindStr = jKindNameTag + state.getName();
        varDecl.aadlStr = aadlNameTag + state.getName();
        varDecl.type = "bool";

        layout.addElement(category, varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);

        varRenaming.put(varDecl.jKindStr, varDecl.aadlStr);
        refMap.put(varDecl.aadlStr, state);
        internalVars.add(varDecl);

        IdExpr id = new IdExpr(varDecl.jKindStr);
        Equation eq = new Equation(id, expr);
        propExpressions.add(eq);
        return expr;
    }

    // TODO: name clashes for equations are inevitable if we don't prefix
    // with scopes - e.g. multiple function or node instances.
    // See commented out code.
    // This will require name lookup/replacement for id expressions or
    // (seemingly less good) a preprocessing phase that modifies names.

    @Override
    public Expr caseEqStatement(EqStatement state) {

        Expr expr = doSwitch(state.getExpr());

        List<IdExpr> varIds = new ArrayList<IdExpr>();

        for (Arg arg : state.getLhs()) {
            String baseName = arg.getName();
            AgreeVarDecl varDecl = new AgreeVarDecl();
            varDecl.jKindStr = jKindNameTag + baseName;

            IdExpr idExpr = new IdExpr(varDecl.jKindStr);
            varIds.add(idExpr);

            varDecl.aadlStr = aadlNameTag + baseName;
            varDecl.type = arg.getType().getString();

            layout.addElement(category, varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);
            

            varRenaming.put(varDecl.jKindStr, varDecl.aadlStr);
            refMap.put(varDecl.aadlStr, state);
            internalVars.add(varDecl);
        }

        Equation eq = new Equation(varIds, expr);

        eqExpressions.add(eq);
        return null;
    }

    @Override
    public Expr caseConstStatement(ConstStatement state) {
        Expr expr = doSwitch(state.getExpr());

        AgreeVarDecl varType = new AgreeVarDecl();
        varType.jKindStr = jKindNameTag + state.getName();
        varType.aadlStr = aadlNameTag + state.getName();
        varType.type = state.getType().getString();

        layout.addElement(category, varType.aadlStr, AgreeLayout.SigType.OUTPUT);


        varRenaming.put(varType.jKindStr, varType.aadlStr);
        refMap.put(varType.aadlStr, state);
        internalVars.add(varType);

        IdExpr idExpr = new IdExpr(varType.jKindStr);
        Equation eq = new Equation(idExpr, expr);

        constExpressions.add(eq);

        return null;
    }

    @Override
    public Expr caseParamStatement(ParamStatement state) {

        assert (false);
        return null;
    }

    @Override
    public Expr caseBinaryExpr(com.rockwellcollins.atc.agree.agree.BinaryExpr expr) {

        Expr leftExpr = doSwitch(expr.getLeft());
        Expr rightExpr = doSwitch(expr.getRight());

        String op = expr.getOp();

        BinaryOp binOp = null;
        switch (op) {
        case "+":
            binOp = BinaryOp.PLUS;
            break;
        case "-":
            binOp = BinaryOp.MINUS;
            break;
        case "*":
            binOp = BinaryOp.MULTIPLY;
            break;
        case "/":
            binOp = BinaryOp.DIVIDE;
            break;
        case "div":
            binOp = BinaryOp.INT_DIVIDE;
            break;
        case "<=>":
        case "=":
            binOp = BinaryOp.EQUAL;
            break;
        case "!=":
        case "<>":
            binOp = BinaryOp.NOTEQUAL;
            break;
        case ">":
            binOp = BinaryOp.GREATER;
            break;
        case "<":
            binOp = BinaryOp.LESS;
            break;
        case ">=":
            binOp = BinaryOp.GREATEREQUAL;
            break;
        case "<=":
            binOp = BinaryOp.LESSEQUAL;
            break;
        case "or":
            binOp = BinaryOp.OR;
            break;
        case "and":
            binOp = BinaryOp.AND;
            break;
        case "xor":
            binOp = BinaryOp.XOR;
            break;
        case "=>":
            binOp = BinaryOp.IMPLIES;
            break;
        case "->":
            binOp = BinaryOp.ARROW;
            break;
        }

        assert (binOp != null);
        BinaryExpr binExpr = new BinaryExpr(leftExpr, binOp, rightExpr);

        return binExpr;
    }

    @Override
    public Expr caseBoolLitExpr(BoolLitExpr expr) {
        return new BoolExpr(expr.getVal().getValue());
    }

    @Override
    public Expr caseFnCallExpr(FnCallExpr expr) {
        NestedDotID dotId = expr.getFn();
        NamedElement namedEl = AgreeEmitterUtilities.getFinalNestId(dotId);
     
        String fnName = jKindNameTag + namedEl.getName();

        boolean found = false;
        for(Node node : nodeDefExpressions){
            if(node.id.equals(fnName)){
                found = true;
                break;
            }
        }
        
        if(!found){
            NestedDotID fn = expr.getFn();
            doSwitch(AgreeEmitterUtilities.getFinalNestId(fn));
        }
        
     
        List<Expr> argResults = new ArrayList<Expr>();

        for (com.rockwellcollins.atc.agree.agree.Expr argExpr : expr.getArgs()) {
            argResults.add(doSwitch(argExpr));
        }

        NodeCallExpr nodeCall = new NodeCallExpr(fnName, argResults);

        return nodeCall;
    }

    // TODO: ordering nodes/functions in dependency order
    @Override
    public Expr caseNodeDefExpr(NodeDefExpr expr) {
        // System.out.println("Visiting caseNodeDefExpr");

        String nodeName = jKindNameTag + expr.getName();
        
        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getArgs());
        List<VarDecl> outputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getRets());
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, body.getLocs());
        List<Equation> eqs = new ArrayList<Equation>();
        List<String> props = new ArrayList<String>();

        for (NodeStmt stmt : body.getStmts()) {
            if (stmt instanceof NodeLemma) {
                NodeLemma nodeLemma = (NodeLemma) stmt;
                String propName = nodeLemma.getStr();
                props.add(propName);
                IdExpr eqId = new IdExpr(propName);
                Expr eqExpr = doSwitch(nodeLemma.getExpr());
                Equation eq = new Equation(eqId, eqExpr);
                eqs.add(eq);
                VarDecl lemmaVar = new VarDecl(propName, NamedType.BOOL);
                internals.add(lemmaVar);
            } else if (stmt instanceof NodeEq) {
                eqs.add(nodeEqToEq((NodeEq) stmt));
            }
        }

        nodeDefExpressions.add(new Node(nodeName, inputs, outputs, internals, eqs, props));
        return null;
    }
    
    //helper method for the above method
    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(jKindNameTag + arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }

    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {

        String nodeName = jKindNameTag + expr.getName();

        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = AgreeEmitterUtilities.argsToVarDeclList(jKindNameTag, expr.getArgs());
        Expr bodyExpr = doSwitch(expr.getExpr());

        Type outType = new NamedType(expr.getType().getString());
        VarDecl outVar = new VarDecl("_outvar", outType);
        List<VarDecl> outputs = Collections.singletonList(outVar);
        Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
        List<Equation> eqs = Collections.singletonList(eq);

        Node node = new Node(nodeName, inputs, outputs, Collections.<VarDecl> emptyList(), eqs);

        nodeDefExpressions.add(node);

        return null;
    }

    // TODO: place node definition here.

    @Override
    public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

        NamedElement propName = namedElFromId(expr.getProp());
        NamedElement compName = namedElFromId(expr.getComponent());
        
        Property prop = (Property) propName;

        PropertyExpression propVal = AgreeEmitterUtilities.getPropExpression(compName, prop);

        //if (propVal == null) {
        //    assert (compName instanceof Subcomponent);
        //    Subcomponent subComp = (Subcomponent) compName;
        //    ComponentImplementation comp = subComp.getComponentImplementation();
        //    propVal = getPropExpression(comp, prop);
        //}

        if(propVal == null){
            throw new AgreeException("Could not locate property value '"+
                    prop.getFullName()+"' in component '"+compName.getName()+"'.  Is it possible "
                    + "that a 'this' statement is used in a context in which it wasn't supposed to?");
        }
        Expr res = null;
        if (propVal != null) {
            if (propVal instanceof StringLiteral) {
                // StringLiteral value = (StringLiteral) propVal;
                // nodeStr += value.getValue() + ")";
                throw new AgreeException("Property value for '" + prop.getFullName()
                        + "' cannot be of string type");
            } else if (propVal instanceof NamedValue) {
                // NamedValue namedVal = (NamedValue) propVal;
                // AbstractNamedValue absVal = namedVal.getNamedValue();
                // assert (absVal instanceof EnumerationLiteral);
                // EnumerationLiteral enVal = (EnumerationLiteral) absVal;
                throw new AgreeException("Property value for '" + prop.getFullName()
                        + "' cannot be of enumeration type");
            } else if (propVal instanceof BooleanLiteral) {
                BooleanLiteral value = (BooleanLiteral) propVal;
                res = new BoolExpr(value.getValue());
            } else if (propVal instanceof IntegerLiteral) {
                IntegerLiteral value = (IntegerLiteral) propVal;
                res = new IntExpr(BigInteger.valueOf((long) value.getScaledValue()));
            } else {
                assert (propVal instanceof RealLiteral);
                RealLiteral value = (RealLiteral) propVal;
                res = new RealExpr(BigDecimal.valueOf(value.getValue()));
            }
        }
        assert (res != null);

        return res;
    }
    
    //helper method for above
    private NamedElement namedElFromId(EObject obj) {
        if (obj instanceof NestedDotID) {
            return AgreeEmitterUtilities.getFinalNestId((NestedDotID) obj);
        } else if (obj instanceof com.rockwellcollins.atc.agree.agree.IdExpr) {
            return ((com.rockwellcollins.atc.agree.agree.IdExpr) obj).getId();
        } else {
            assert (obj instanceof ThisExpr);
           
            ThisExpr thisExpr = (ThisExpr)obj;
            
            ComponentInstance compInst = curInst;
            NestedDotID nestId = thisExpr.getSubThis();
            
            while(nestId != null){
                NamedElement base = nestId.getBase();
                
                if(base instanceof Subcomponent){
                    compInst = compInst.findSubcomponentInstance((Subcomponent)base);
                    nestId = nestId.getSub();
                }else{
                    assert(nestId.getSub() == null);
                    return compInst.findFeatureInstance((Feature)base);
                }
                
            }
            return compInst;
        }
    }

    @Override
    public Expr caseIdExpr(com.rockwellcollins.atc.agree.agree.IdExpr expr) {
        // just make an expression of the NamedElement
        return new IdExpr(jKindNameTag + expr.getId().getName());
    }

    @Override
    public Expr caseThisExpr(ThisExpr expr) {
        assert (false);
        return null;
        // return new NamedElementExpr(curComp);
    }

    @Override
    public Expr caseIfThenElseExpr(com.rockwellcollins.atc.agree.agree.IfThenElseExpr expr) {
        Expr condExpr = doSwitch(expr.getA());
        Expr thenExpr = doSwitch(expr.getB());
        Expr elseExpr = doSwitch(expr.getC());

        Expr result = new IfThenElseExpr(condExpr, thenExpr, elseExpr);

        return result;
    }

    @Override
    public Expr caseIntLitExpr(IntLitExpr expr) {
        return new IntExpr(BigInteger.valueOf(Integer.parseInt(expr.getVal())));
    }

    @Override
    public Expr caseNestedDotID(NestedDotID Id) {

        NestedDotID orgId = Id;
        String jKindVar = "";
        String aadlVar = "";
        while (Id.getSub() != null) {
            jKindVar += Id.getBase().getName() + dotChar;
            aadlVar += Id.getBase().getName() + ".";
            Id = Id.getSub();
        }

        NamedElement namedEl = Id.getBase();

        String baseName = namedEl.getName();
        IdExpr result = new IdExpr(jKindVar + baseName);

        // tags are appended to the variables to
        // make sure that scope is properly maintained
        // in the generated lustre
        // if (!(namedEl instanceof Arg)) {
        result = new IdExpr(jKindNameTag + jKindVar + baseName);

        // TODO: this code is for the case when there is some sort of
        // "floating" port on a component. I.e., a port that is not
        // transatively connected to a feature on the top level component
        // and is not connected on one side to another internal component
        // perhaps we should throw an error here rather than creating
        // a new random input?

        // hack for making sure all inputs have been created
        if (namedEl instanceof DataSubcomponent || namedEl instanceof DataPort) {
            String tempStr = result.id;
            AgreeVarDecl tempStrType = new AgreeVarDecl();
            tempStrType.jKindStr = tempStr;

            if (!inputVars.contains(tempStrType) && !internalVars.contains(tempStrType)) {

                log.logWarning("In component '"
                        + orgId.getBase().getContainingClassifier().getFullName() + "', Port '"
                        + tempStr + "' is not connected to anything. Considering it to be"
                        + " an unconstrained primary input.");

             
                // this code just creates a new PI
                if(namedEl instanceof DataSubcomponent){
                    tempStrType = AgreeEmitterUtilities.dataTypeToVarType((DataSubcomponent) namedEl);
                }else{
                    DataType dataType = (DataType) ((DataPort)namedEl).getDataFeatureClassifier();
                            
                    String typeStr = AgreeEmitterUtilities.dataTypeToVarType(dataType);
                    tempStrType = new AgreeVarDecl();
                    tempStrType.type = typeStr;
                }
                jKindVar = jKindNameTag + jKindVar + Id.getBase().getName();
                aadlVar = aadlNameTag + aadlVar + Id.getBase().getName();

                //get rid of this. this is just a sanity check
                assert(jKindVar.equals(tempStr));
                tempStrType.jKindStr = tempStr;
                tempStrType.aadlStr = aadlVar;
                
                layout.addElement(category, aadlVar, AgreeLayout.SigType.INPUT);
             
                varRenaming.put(jKindVar, aadlVar);
                refMap.put(aadlVar, Id);
                inputVars.add(tempStrType);
                
                //have to keep track of outputs for condacts
                if(namedEl instanceof DataSubcomponent){
                    if(((DirectedFeature)orgId.getBase()).getDirection() == DirectionType.OUT){
                        outputVars.add(tempStrType);
                    }
                }else if(((DirectedFeature)namedEl).getDirection() == DirectionType.OUT){
                    outputVars.add(tempStrType);
                }
                
            }
        }

        return result;
    }

    @Override
    public Expr casePreExpr(PreExpr expr) {
        Expr res = doSwitch(expr.getExpr());

        return new UnaryExpr(UnaryOp.PRE, res);
    }

    @Override
    public Expr casePrevExpr(PrevExpr expr) {
        Expr delayExpr = doSwitch(expr.getDelay());
        Expr initExpr = doSwitch(expr.getInit());

        Expr preExpr = new UnaryExpr(UnaryOp.PRE, delayExpr);

        Expr res = new BinaryExpr(initExpr, BinaryOp.ARROW, preExpr);

        return res;
    }

    @Override
    public Expr caseRealLitExpr(RealLitExpr expr) {
        return new RealExpr(BigDecimal.valueOf(Double.parseDouble(expr.getVal())));
    }

    @Override
    public Expr caseUnaryExpr(com.rockwellcollins.atc.agree.agree.UnaryExpr expr) {
        Expr subExpr = doSwitch(expr.getExpr());

        Expr res = null;
        switch (expr.getOp()) {
        case "-":
            res = new UnaryExpr(UnaryOp.NEGATIVE, subExpr);
            break;
        case "not":
            res = new UnaryExpr(UnaryOp.NOT, subExpr);
            break;
        default:
            assert (false);
        }

        return res;
    }
    
    //********* end case statements ************//
    
    //********* begin lustre generation *******//
    
    // fills the connExpressions lists with expressions
    // that equate variables that are connected to one another
    private void setVarEquivs(ComponentImplementation compImpl, String initJStr, String initAStr) {

        // use for checking delay
        Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                OsateResourceUtil.getResourceSet(), "Communication_Properties::Timing");

        for (Connection conn : compImpl.getAllConnections()) {

            AbstractConnectionEnd absConnDest = conn.getDestination();
            AbstractConnectionEnd absConnSour = conn.getSource();
            assert (absConnDest instanceof ConnectedElement);
            assert (absConnSour instanceof ConnectedElement);

            EnumerationLiteral lit = PropertyUtils.getEnumLiteral(conn, commTimingProp);
            boolean delayed = !lit.getName().equals("immediate");

            ConnectionEnd destConn = ((ConnectedElement) absConnDest).getConnectionEnd();
            ConnectionEnd sourConn = ((ConnectedElement) absConnSour).getConnectionEnd();
            Context destContext = ((ConnectedElement) absConnDest).getContext();
            Context sourContext = ((ConnectedElement) absConnSour).getContext();

            Feature port = null;
            if (destConn != null) {
                port = (Feature)destConn;
                if(port instanceof FeatureGroup){
                    FeatureGroupType featType = ((FeatureGroup)port).getAllFeatureGroupType();
                    for(DataPort dPort: featType.getOwnedDataPorts()){
                        port = dPort;
                        setVarEquiv(port.getName(), initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                    }
                }else{
                    setVarEquiv("", initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                }
            } else if (sourConn != null) {
                port = (Feature)sourConn;
                if(port instanceof FeatureGroup){
                    FeatureGroupType featType = ((FeatureGroup)port).getAllFeatureGroupType();
                    for(DataPort dPort: featType.getOwnedDataPorts()){
                        port = dPort;
                        setVarEquiv(port.getName(), initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                    }
                }else{
                    setVarEquiv("", initJStr, initAStr, port, sourContext, sourConn, destContext, destConn, delayed);
                }
            }
        }
    }
    
    
    private void setVarEquiv(String prefix,
            String initJStr,
            String initAStr,
            Feature port,
            Context sourContext,
            ConnectionEnd sourConn,
            Context destContext, 
            ConnectionEnd destConn,
            boolean delayed){
        
        
        DataSubcomponentType dataSub;
        if(port instanceof DataPort){
            dataSub = ((DataPort)port).getDataFeatureClassifier();
        }else{
            dataSub = ((EventDataPort)port).getDataFeatureClassifier();
        }
        
        if(dataSub instanceof DataType){
            //we only want to reason about known types
            if(AgreeEmitterUtilities.dataTypeToVarType((DataType)dataSub) == null){
                return;
            }
        }

        if (!(dataSub instanceof DataImplementation || dataSub instanceof DataType)) {
            return;
        }

        String sourStr;
        String destStr;
        String aadlSourStr;
        String aadlDestStr;
        if (sourContext != null) { // source is not an end point
            assert (sourContext instanceof Subcomponent || sourContext instanceof FeatureGroup);
            sourStr = sourContext.getName() + dotChar + sourConn.getName();
            aadlSourStr = sourContext.getName() + "." + sourConn.getName();
        } else {
            sourStr = sourConn.getName();
            aadlSourStr = sourConn.getName();
        }

        if (destContext != null) { // destination is not an end point
            assert (destContext instanceof Subcomponent || destContext instanceof ThreadSubcomponent);
            destStr = destContext.getName() + dotChar + destConn.getName();
            aadlDestStr = destContext.getName() + "." + destConn.getName();
        } else {
            destStr = destConn.getName();
            aadlDestStr = destConn.getName();
        }
        
        
        if(dataSub instanceof DataImplementation){
            Set<AgreeVarDecl> tempSet = new HashSet<AgreeVarDecl>();
            getAllDataNames((DataImplementation) dataSub, tempSet);

            for (AgreeVarDecl varType : tempSet) {
                //stupid hack to handle feature groups correctly
                if(!prefix.equals("")){
                    varType.jKindStr = prefix + dotChar + varType.jKindStr;
                    varType.aadlStr = prefix + "." + varType.aadlStr;
                }
                String newDestStr = initJStr + destStr + dotChar + varType.jKindStr;
                String newSourStr = initJStr + sourStr + dotChar + varType.jKindStr;
                String newAADLDestStr = initAStr + aadlDestStr + "." + varType.aadlStr;
                String newAADLSourStr = initAStr + aadlSourStr + "." + varType.aadlStr;

                // make an internal var for this
                varType.jKindStr = newDestStr;
                varType.aadlStr = newAADLDestStr;

                setVarEquiv_Helper(sourContext, sourConn, destContext, destConn, 
                        varType, newDestStr, newSourStr, newAADLSourStr, delayed);
            }
        }else{
            AgreeVarDecl varType = new AgreeVarDecl();
            
            String newDestStr = initJStr + destStr;
            String newSourStr = initJStr + sourStr;
            String newAADLDestStr = initAStr + aadlDestStr;
            String newAADLSourStr = initAStr + aadlSourStr;
            
            varType.jKindStr = newDestStr;
            varType.aadlStr = newAADLDestStr;
            varType.type = AgreeEmitterUtilities.dataTypeToVarType((DataType)dataSub);
            
            setVarEquiv_Helper(sourContext, sourConn, destContext, destConn, 
                    varType, newDestStr, newSourStr, newAADLSourStr, delayed);
            
            //setVarEquiv_Helper(sourContext, sourConn, destContext, destConn, 
            //        varType, destStr, sourStr, aadlSourStr, delayed);
            
        }

    }
    
    private void setVarEquiv_Helper(Context sourContext,
            ConnectionEnd sourConn,
            Context destContext, 
            ConnectionEnd destConn,
            AgreeVarDecl varType,
            String newDestStr,
            String newSourStr,
            String newAADLSourStr,
            boolean delayed){
        
        if (destContext != null) {
            layout.addElement(destContext.getName(), varType.aadlStr,
                    AgreeLayout.SigType.OUTPUT);
        }

        refMap.put(varType.aadlStr, destConn);
        varRenaming.put(varType.jKindStr, varType.aadlStr);
        internalVars.add(varType);


        AgreeVarDecl inputVar = new AgreeVarDecl();
        inputVar.type = varType.type;
        inputVar.jKindStr = newSourStr;
        inputVar.aadlStr = newAADLSourStr;

        layout.addElement(category, inputVar.aadlStr,
                AgreeLayout.SigType.INPUT);
        varRenaming.put(inputVar.jKindStr, inputVar.aadlStr);
        refMap.put(inputVar.aadlStr, sourConn);
        inputVars.add(inputVar);

        if(sourConn != null){
            if(((DirectedFeature)sourConn).getDirection() == DirectionType.OUT){
                outputVars.add(inputVar);
            }
        }

        Expr connExpr = null;
        IdExpr sourId = new IdExpr(newSourStr);

        if (sourContext != null && destContext != null && delayed) {
            // this is not an input, and the output is not a terminal
            Expr initValExpr = initTypeMap.get(varType.type);
            connExpr = new UnaryExpr(UnaryOp.PRE, sourId);
            connExpr = new BinaryExpr(initValExpr, BinaryOp.ARROW, connExpr);
            
        } else {
            connExpr = sourId;
        }
        IdExpr destId = new IdExpr(newDestStr);
        Equation connEq = new Equation(destId, connExpr);

        connExpressions.add(connEq);
        
    }
    
    //helper method to above
    private void getAllDataNames(DataImplementation dataImpl, Set<AgreeVarDecl> subStrTypes) {

        for (Subcomponent sub : dataImpl.getAllSubcomponents()) {
            if (sub instanceof DataSubcomponent) {
                Set<AgreeVarDecl> newStrTypes = new HashSet<AgreeVarDecl>();
                ComponentClassifier subImpl = sub.getAllClassifier();
                if (subImpl instanceof DataImplementation) {
                    getAllDataNames((DataImplementation) subImpl, newStrTypes);
                    for (AgreeVarDecl strType : newStrTypes) {
                        AgreeVarDecl newStrType = new AgreeVarDecl();
                        newStrType.jKindStr = sub.getName() + dotChar + strType.jKindStr;
                        newStrType.aadlStr = sub.getName() + "." + strType.aadlStr;
                        newStrType.type = strType.type;
                        subStrTypes.add(newStrType);
                    }
                } else {
                    assert (subImpl instanceof DataType);
                    AgreeVarDecl newStrType = AgreeEmitterUtilities.dataTypeToVarType((DataSubcomponent) sub);
                    if (newStrType.type != null) {
                        subStrTypes.add(newStrType);
                    }
                }
            }
        }
    }
    
    public AgreeNode getComponentNode(){
        
        if(agreeNode != null){
            return agreeNode;
        }
        
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
                
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInternalVars.addAll(this.internalVars);
        
        //convert the variables
        for(AgreeVarDecl aVar : agreeInputVars){
            inputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        
        for(AgreeVarDecl aVar : agreeInternalVars){
            outputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        
        //create equations for assumptions, assertions, and guarantees
        List<Expr> guarVars = new ArrayList<>();
        int i = 0;
        for(Equation eq : this.guarExpressions){
            IdExpr id = new IdExpr("__GUAR_"+category+"_"+i++);
            VarDecl guarVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(guarVar);
            guarVars.add(id);
            eqs.add(new Equation(id, eq.expr));
        }
        
        List<Expr> asserVars = new ArrayList<>();
        i = 0;
        for(Expr expr : this.assertExpressions){
            IdExpr id = new IdExpr("__ASSER_"+category+"_"+i++);
            VarDecl asserVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(asserVar);
            asserVars.add(id);
            eqs.add(new Equation(id, expr));
        }
        
        List<Expr> assumVars = new ArrayList<>();
        i = 0;
        for(Expr expr : this.assumpExpressions){
            IdExpr id = new IdExpr("__ASSUM_"+category+"_"+i++);
            VarDecl assumVar = new VarDecl(id.id, NamedType.BOOL);
            outputs.add(assumVar);
            assumVars.add(id);
            eqs.add(new Equation(id, expr));
        }

        Node node = new Node("__NODE_"+this.category, inputs, outputs, Collections.EMPTY_LIST, eqs);
        VarDecl clockVar = new VarDecl("__CLOCK_"+category, NamedType.BOOL);
        
        List<Node> agreeNodes = new ArrayList<>(this.nodeDefExpressions);
        agreeNodes.add(node);
        
        agreeNode = new AgreeNode(inputs, outputs, assumVars,
                asserVars, guarVars, agreeNodes, node, clockVar);
        
        return agreeNode;
    }
    
    public List<Node> getLustreWithCondacts(List<AgreeAnnexEmitter> subEmitters){
        // first print out the functions which will be
        // other nodes

        assumProps = new ArrayList<String>();
        consistProps = new ArrayList<String>();
        guarProps = new ArrayList<String>();
        
        // start constructing the top level node
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<Expr> clocks = new ArrayList<>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();
        List<VarDecl> internals = new ArrayList<VarDecl>();
        List<String> properties = new ArrayList<String>();        

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
        
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInternalVars.addAll(this.internalVars);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            
            AgreeNode agreeNode = subEmitter.getComponentNode();
            nodeSet.addAll(agreeNode.nodes);
            inputs.add(agreeNode.clockVar);
            
            List<Expr> initOutputs = new ArrayList<>();
            List<Expr> nodeInputs = new ArrayList<>();
            List<IdExpr> nodeOutputs = new ArrayList<>();
            Expr clockExpr = new IdExpr(agreeNode.clockVar.id);
            
            clocks.add(clockExpr);
            
            agreeInputVars.addAll(subEmitter.inputVars);
            agreeInternalVars.addAll(subEmitter.internalVars); 
            //also add new assumption, assertion, and guarantee vars
            for(Expr expr : agreeNode.assertions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.assumptions){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            for(Expr expr : agreeNode.guarantees){
                IdExpr varId = (IdExpr)expr;
                AgreeVarDecl agreeVar = new AgreeVarDecl();
                agreeVar.aadlStr = agreeVar.jKindStr = varId.id;
                agreeVar.type = "bool";
                agreeInternalVars.add(agreeVar);
            }
            
            //TODO: set different initial values for outputs
            for(VarDecl var : agreeNode.outputs){
                NamedType type = (NamedType)var.type;
                initOutputs.add(initTypeMap.get(type.name));
                nodeOutputs.add(new IdExpr(var.id));
            }
            
            for(VarDecl var : agreeNode.inputs){
                nodeInputs.add(new IdExpr(var.id));
            }

            NodeCallExpr nodeCall = new NodeCallExpr(agreeNode.mainNode.id, nodeInputs);
            
            CondactExpr condact = new CondactExpr(clockExpr, nodeCall, initOutputs);
            Equation condactEq = new Equation(nodeOutputs, condact);
            eqs.add(condactEq);
            
            varRenaming.putAll(subEmitter.varRenaming);
            refMap.putAll(subEmitter.refMap);
            
            //create the hold equations for the subcomponent outputs
            for(AgreeVarDecl varDec : subEmitter.outputVars){
                AgreeVarDecl dummyAgreeVar = new AgreeVarDecl();
                dummyAgreeVar.type = varDec.type;
                dummyAgreeVar.jKindStr = "___DUMMY_" + varDec.jKindStr;
                agreeInputVars.add(dummyAgreeVar);
                agreeInternalVars.add(varDec);
                
                IdExpr dummyId = new IdExpr(dummyAgreeVar.jKindStr);
                IdExpr clockId = new IdExpr(agreeNode.clockVar.id);
                IdExpr outId = new IdExpr(varDec.jKindStr);
                Expr preExpr = new BinaryExpr(dummyId, BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, outId));
                
                Expr ifElse = new IfThenElseExpr(clockId, dummyId, preExpr);
                Equation clockOutEq = new Equation(outId, ifElse);
                eqs.add(clockOutEq);
            }

        }

        //warn about combinational cycles
        logCycleWarning(eqs);
        
        agreeInputVars.removeAll(agreeInternalVars);
        
        //convert the variables
        for(AgreeVarDecl aVar : agreeInputVars){
            inputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        for(AgreeVarDecl aVar : agreeInternalVars){
            internals.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, new NamedType("bool")));
        internals.add(new VarDecl(sysAssumpHistId.id, new NamedType("bool")));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, getLustreContract(subEmitter));
        }

        eqs.add(getLustreHistory(totalCompHist, totalCompHistId));

        // system assumptions
        Expr sysAssumpHist = getLustreAssumptionsAndAssertions(this);
        eqs.add(getLustreHistory(sysAssumpHist, sysAssumpHistId));

        
        //make the closure map for proving assumptions
        Map<Subcomponent, Set<Subcomponent>> closureMap = new HashMap<>();
        for(AgreeAnnexEmitter subEmitter : subEmitters){
            Set<Subcomponent> outputClosure = new HashSet<Subcomponent>();
            outputClosure.add(subEmitter.curComp);
            ComponentImplementation compImpl = (ComponentImplementation) curInst.getComponentClassifier();
            getOutputClosure(compImpl.getAllConnections(), outputClosure);
            closureMap.put(subEmitter.curComp, outputClosure);
        }
        
        //make a counter for checking finite consistency
        IdExpr countId = new IdExpr("__CONSIST_COUNTER");
        internals.add(new VarDecl(countId.id, new NamedType("int")));
        
        Expr countPre = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, countId), BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        countPre = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, countPre);
        Equation contEq = new Equation(countId, countPre);
        eqs.add(contEq);

        
        // get the individual history variables
        for(AgreeAnnexEmitter subEmitter : subEmitters){

            Expr higherContracts = new BoolExpr(true);

            for (Subcomponent otherComp : closureMap.get(subEmitter.curComp)) {
                higherContracts = new BinaryExpr(higherContracts, BinaryOp.AND,
                        getLustreContract(getSubcomponentEmitter(otherComp, subEmitters)));
            }

            Expr contrAssumps = getLustreAssumptions(subEmitter);

            IdExpr compId = new IdExpr("_Hist_" + subEmitter.category);
            internals.add(new VarDecl(compId.id, new NamedType("bool")));

            Expr leftSide = new UnaryExpr(UnaryOp.PRE, totalCompHist);
            leftSide = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, leftSide);
            leftSide = new BinaryExpr(sysAssumpHist, BinaryOp.AND, leftSide);
            leftSide = new BinaryExpr(higherContracts, BinaryOp.AND, leftSide);

            Expr contrHistExpr = new BinaryExpr(leftSide, BinaryOp.IMPLIES, contrAssumps);
            Equation contrHist = new Equation(compId, contrHistExpr);
            eqs.add(contrHist);
            properties.add(compId.id);
            assumProps.add(compId.id);
            String propertyName = subEmitter.category + " Assumptions";
            varRenaming.put(compId.id, propertyName);
            //layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, propertyName, AgreeLayout.SigType.OUTPUT);
            
            
            //add a property that is true if the contract is a contradiction
            IdExpr contrHistId = new IdExpr("__CONTR_HIST_" + subEmitter.category);
            IdExpr consistId = new IdExpr("__NULL_CONTR_HIST_" + subEmitter.category);

            Expr contExpr = getLustreContract(subEmitter);
            Equation contHist = getLustreHistory(contExpr, contrHistId);
            Expr finiteConsist = getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
            Equation contrConsistEq = new Equation(consistId, finiteConsist);
            eqs.add(contrConsistEq);
            eqs.add(contHist);
            internals.add(new VarDecl(contrHistId.id, new NamedType("bool")));
            internals.add(new VarDecl(consistId.id, new NamedType("bool")));

            properties.add(consistId.id);
            consistProps.add(consistId.id);
            //reversePropStatus.add(true);
            String contractName = subEmitter.category + " Consistant";
            varRenaming.put(consistId.id, contractName);
            //layout.addElement("Top", contractName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, contractName, AgreeLayout.SigType.OUTPUT);
        }

        // create individual properties for guarantees
        int i = 0;
        for (Equation guar : guarExpressions) {
            String guarName = guar.lhs.get(0).id;
            IdExpr sysGuaranteesId = new IdExpr(sysGuarTag + i);
            internals.add(new VarDecl(sysGuaranteesId.id, new NamedType("bool")));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.IMPLIES, guar.expr);

            Equation finalGuar = new Equation(sysGuaranteesId, totalSysGuarExpr);
            eqs.add(finalGuar);
            properties.add(sysGuaranteesId.id);
            guarProps.add(sysGuaranteesId.id);
            //reversePropStatus.add(false);
            varRenaming.put(sysGuaranteesId.id, guarName);
            //layout.addElement("Top", "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
        }
        
        //check for contradiction in total component history
        IdExpr notTotalCompHistId = new IdExpr("_TOTAL_COMP_FINITE_CONSIST");
        Expr finiteConsist = getFinteConsistancy(totalCompHistId, countId, consistUnrollDepth);
        Equation contrConsistEq = new Equation(notTotalCompHistId, finiteConsist);
        
        internals.add(new VarDecl(notTotalCompHistId.id, new NamedType("bool")));
        eqs.add(contrConsistEq);
        
        properties.add(notTotalCompHistId.id);
        consistProps.add(notTotalCompHistId.id);
        //reversePropStatus.add(true);
        varRenaming.put(notTotalCompHistId.id, "Total Contract Consistant");
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        layout.addElement(category, "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);

        //create the assertions for the clocks
        Expr clockAssertion = new BoolExpr(true);
        for(Expr expr : clocks){
            clockAssertion = new BinaryExpr(expr, BinaryOp.AND, clockAssertion);
        }
        
        List<Expr> assertions = new ArrayList<>();
        assertions.add(clockAssertion);
        
        Node topNode = new Node("_MAIN", inputs, outputs, internals, eqs, properties, assertions);
        nodeSet.add(topNode);
        return nodeSet;
    }
    
    public List<Node> getLustre(List<AgreeAnnexEmitter> subEmitters) {
        // first print out the functions which will be
        // other nodes

        assumProps = new ArrayList<String>();
        consistProps = new ArrayList<String>();
        guarProps = new ArrayList<String>();
        
        // start constructing the top level node
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();
        List<VarDecl> internals = new ArrayList<VarDecl>();
        List<String> properties = new ArrayList<String>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(this.nodeDefExpressions);
        eqs.addAll(this.constExpressions);
        eqs.addAll(this.eqExpressions);
        eqs.addAll(this.propExpressions);
        eqs.addAll(this.connExpressions);
        
        Set<AgreeVarDecl> agreeInputVars = new HashSet<>();
        Set<AgreeVarDecl> agreeInternalVars = new HashSet<>();
        
        agreeInputVars.addAll(this.inputVars);
        agreeInternalVars.addAll(this.internalVars);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            nodeSet.addAll(subEmitter.nodeDefExpressions);
            eqs.addAll(subEmitter.constExpressions);
            eqs.addAll(subEmitter.eqExpressions);
            eqs.addAll(subEmitter.propExpressions);
            eqs.addAll(subEmitter.connExpressions);
            
            varRenaming.putAll(subEmitter.varRenaming);
            refMap.putAll(subEmitter.refMap);
            agreeInputVars.addAll(subEmitter.inputVars);
            agreeInternalVars.addAll(subEmitter.internalVars);
        }

        //warn about combinational cycles
        logCycleWarning(eqs);
        
        agreeInputVars.removeAll(agreeInternalVars);
        
        //convert the variables
        for(AgreeVarDecl aVar : agreeInputVars){
            //sSystem.out.println(aVar.jKindStr);
            inputs.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }
        for(AgreeVarDecl aVar : agreeInternalVars){
            internals.add(new VarDecl(aVar.jKindStr, new NamedType(aVar.type)));
        }

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, new NamedType("bool")));
        internals.add(new VarDecl(sysAssumpHistId.id, new NamedType("bool")));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for(AgreeAnnexEmitter subEmitter : subEmitters){
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, getLustreContract(subEmitter));
        }

        eqs.add(getLustreHistory(totalCompHist, totalCompHistId));

        // system assumptions
        Expr sysAssumpHist = getLustreAssumptionsAndAssertions(this);
        eqs.add(getLustreHistory(sysAssumpHist, sysAssumpHistId));

        
        //make the closure map for proving assumptions
        Map<Subcomponent, Set<Subcomponent>> closureMap = new HashMap<>();
        for(AgreeAnnexEmitter subEmitter : subEmitters){
            Set<Subcomponent> outputClosure = new HashSet<Subcomponent>();
            outputClosure.add(subEmitter.curComp);
            ComponentImplementation compImpl = (ComponentImplementation) curInst.getComponentClassifier();
            getOutputClosure(compImpl.getAllConnections(), outputClosure);
            closureMap.put(subEmitter.curComp, outputClosure);
        }
        
        //make a counter for checking finite consistency
        IdExpr countId = new IdExpr("__CONSIST_COUNTER");
        internals.add(new VarDecl(countId.id, new NamedType("int")));
        
        Expr countPre = new BinaryExpr(new UnaryExpr(UnaryOp.PRE, countId), BinaryOp.PLUS, new IntExpr(BigInteger.ONE));
        countPre = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, countPre);
        Equation contEq = new Equation(countId, countPre);
        eqs.add(contEq);

        
        // get the individual history variables
        for(AgreeAnnexEmitter subEmitter : subEmitters){

            Expr higherContracts = new BoolExpr(true);

            for (Subcomponent otherComp : closureMap.get(subEmitter.curComp)) {
                higherContracts = new BinaryExpr(higherContracts, BinaryOp.AND,
                        getLustreContract(getSubcomponentEmitter(otherComp, subEmitters)));
            }

            Expr contrAssumps = getLustreAssumptions(subEmitter);

            IdExpr compId = new IdExpr("_Hist_" + subEmitter.category);
            internals.add(new VarDecl(compId.id, new NamedType("bool")));

            Expr leftSide = new UnaryExpr(UnaryOp.PRE, totalCompHist);
            leftSide = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, leftSide);
            leftSide = new BinaryExpr(sysAssumpHist, BinaryOp.AND, leftSide);
            leftSide = new BinaryExpr(higherContracts, BinaryOp.AND, leftSide);

            Expr contrHistExpr = new BinaryExpr(leftSide, BinaryOp.IMPLIES, contrAssumps);
            Equation contrHist = new Equation(compId, contrHistExpr);
            eqs.add(contrHist);
            properties.add(compId.id);
            assumProps.add(compId.id);
            String propertyName = subEmitter.category + " Assumptions";
            varRenaming.put(compId.id, propertyName);
            //layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, propertyName, AgreeLayout.SigType.OUTPUT);
            
            
            //add a property that is true if the contract is a contradiction
            IdExpr contrHistId = new IdExpr("__CONTR_HIST_" + subEmitter.category);
            IdExpr consistId = new IdExpr("__NULL_CONTR_HIST_" + subEmitter.category);

            Expr contExpr = getLustreContract(subEmitter);
            Equation contHist = getLustreHistory(contExpr, contrHistId);
            Expr finiteConsist = getFinteConsistancy(contrHistId, countId, consistUnrollDepth);
            Equation contrConsistEq = new Equation(consistId, finiteConsist);
            eqs.add(contrConsistEq);
            eqs.add(contHist);
            internals.add(new VarDecl(contrHistId.id, new NamedType("bool")));
            internals.add(new VarDecl(consistId.id, new NamedType("bool")));

            properties.add(consistId.id);
            consistProps.add(consistId.id);
            //reversePropStatus.add(true);
            String contractName = subEmitter.category + " Consistant";
            varRenaming.put(consistId.id, contractName);
            //layout.addElement("Top", contractName, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, contractName, AgreeLayout.SigType.OUTPUT);
        }

        // create individual properties for guarantees
        int i = 0;
        for (Equation guar : guarExpressions) {
            String guarName = guar.lhs.get(0).id;
            IdExpr sysGuaranteesId = new IdExpr(sysGuarTag + i);
            internals.add(new VarDecl(sysGuaranteesId.id, new NamedType("bool")));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.IMPLIES, guar.expr);

            Equation finalGuar = new Equation(sysGuaranteesId, totalSysGuarExpr);
            eqs.add(finalGuar);
            properties.add(sysGuaranteesId.id);
            guarProps.add(sysGuaranteesId.id);
            //reversePropStatus.add(false);
            varRenaming.put(sysGuaranteesId.id, guarName);
            //layout.addElement("Top", "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
            layout.addElement(category, "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);
        }
        
        //check for contradiction in total component history
        IdExpr notTotalCompHistId = new IdExpr("_TOTAL_COMP_FINITE_CONSIST");
        Expr finiteConsist = getFinteConsistancy(totalCompHistId, countId, consistUnrollDepth);
        Equation contrConsistEq = new Equation(notTotalCompHistId, finiteConsist);
        
        internals.add(new VarDecl(notTotalCompHistId.id, new NamedType("bool")));
        eqs.add(contrConsistEq);
        
        properties.add(notTotalCompHistId.id);
        consistProps.add(notTotalCompHistId.id);
        //reversePropStatus.add(true);
        varRenaming.put(notTotalCompHistId.id, "Total Contract Consistant");
        //layout.addElement("Top", "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);
        layout.addElement(category, "Total Contract Consistants", AgreeLayout.SigType.OUTPUT);

        Node topNode = new Node("_MAIN", inputs, outputs, internals, eqs, properties);
        nodeSet.add(topNode);
        return nodeSet;
    }
    
    //warns the user if there is a cycle
    private void logCycleWarning(List<Equation> eqs){
        Map<String, Set<String>> idGraph = new HashMap<>();
        List<String> ids = new ArrayList<>();
        AgreeCycleVisitor visitor = new AgreeCycleVisitor();
        
        for(Equation eq : eqs){
            for(IdExpr id : eq.lhs){
                ids.add(id.id);
                idGraph.put(id.id, eq.expr.accept(visitor));
            }
        }
        
        Set<String> discovered = new HashSet<>();
        
        for(String id : ids){
            if(discovered.contains(id)){
                continue;
            }
            List<String> cycle = cycleWarning_Helper(id, id, new HashSet<String>(), idGraph);
            
            if(cycle != null){
                discovered.addAll(cycle);
                String aadlString = this.varRenaming.get(id);
                StringBuilder cycleStr = new StringBuilder("Possible cycle: "+aadlString);
                
                String sep = " -> ";
                for(String cycleId : cycle){
                    cycleStr.append(sep);
                    aadlString = this.varRenaming.get(cycleId);
                    cycleStr.append(aadlString);                
                }
                
                log.logWarning(cycleStr.toString());
                
            }
        }
    }
    
    private LinkedList<String> cycleWarning_Helper(String visit, String target, 
            Set<String> visited, Map<String, Set<String>> graph){
        
        if(visited.contains(visited)){
            return null;
        }
        
        visited.add(visit);
        
        Set<String> toVisit = graph.get(visit);
        
        if(toVisit != null){
            for(String nextVisit : toVisit){
                if(nextVisit.equals(target)){
                    return new LinkedList<>(Collections.singletonList(target));
                }
                LinkedList<String> cycle = cycleWarning_Helper(nextVisit, target, visited, graph);
                if(cycle != null){
                    cycle.push(nextVisit);
                    return cycle;
                }
            }
        }
        
        return null;
    }
    
    private AgreeAnnexEmitter getSubcomponentEmitter(Subcomponent sub, 
            List<AgreeAnnexEmitter> subEmitters){
        for(AgreeAnnexEmitter subEmitter : subEmitters){
            if(subEmitter.curComp == sub){
                return subEmitter;
            }
        }
        return null;
    }
    
    private Expr conjoin(List<Expr> exprs) {
        if (exprs.isEmpty()) {
            return new BoolExpr(true);
        }

        Iterator<Expr> iterator = exprs.iterator();
        Expr result = iterator.next();
        while (iterator.hasNext()) {
            result = new BinaryExpr(result, BinaryOp.AND, iterator.next());
        }
        return result;
    }

    private Expr conjoinEqs(List<Equation> eqs) {
        if (eqs.isEmpty()) {
            return new BoolExpr(true);
        }

        Iterator<Equation> iterator = eqs.iterator();
        Expr result = iterator.next().expr;
        while (iterator.hasNext()) {
            result = new BinaryExpr(result, BinaryOp.AND, iterator.next().expr);
        }
        return result;
    }

    private Expr conjoin(Expr... exprs) {
        return conjoin(Arrays.asList(exprs));
    }

    private Expr getLustreAssumptions(AgreeAnnexEmitter emitter) {
        if(emitter.agreeNode == null){
            return conjoin(emitter.assumpExpressions);
        }else{
            return conjoin(emitter.agreeNode.assumptions);
        }
    }

    private Expr getLustreAssumptionsAndAssertions(AgreeAnnexEmitter emitter) {
        if(emitter.agreeNode == null){
            return conjoin(conjoin(emitter.assumpExpressions), conjoin(emitter.assertExpressions));
        }else{
            return conjoin(conjoin(emitter.agreeNode.assertions), conjoin(emitter.agreeNode.assumptions));
        }
    }

    private Expr getLustreContract(AgreeAnnexEmitter emitter) {
        if(emitter.agreeNode == null){
            return conjoin(conjoin(emitter.assumpExpressions), conjoin(emitter.assertExpressions),
                    conjoinEqs(emitter.guarExpressions));
        }else{
            return conjoin(conjoin(emitter.agreeNode.assertions),
                    conjoin(emitter.agreeNode.assumptions),
                    conjoin(emitter.agreeNode.guarantees));
        }
    }

    private Expr getLustreGuarantee(AgreeAnnexEmitter emitter) {
        if(emitter.agreeNode == null){
            return conjoinEqs(emitter.guarExpressions);
        }else{
            return conjoin(emitter.agreeNode.guarantees);
        }
    }
    
    private Equation getLustreHistory(Expr expr, IdExpr histId) {

        Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
        Expr histExpr = new BinaryExpr(expr, BinaryOp.AND, preHist);
        histExpr = new BinaryExpr(expr, BinaryOp.ARROW, histExpr);

        Equation histEq = new Equation(histId, histExpr);

        return histEq;

    }
    
    //returns an expression for bounded history
    private Expr getFinteConsistancy(IdExpr histId, IdExpr countId, int n) {
        Expr countExpr = new BinaryExpr(countId, BinaryOp.EQUAL, new IntExpr(BigInteger.valueOf((long)n)));

        Expr consistExpr = new BinaryExpr(histId, BinaryOp.AND, countExpr);
        consistExpr = new UnaryExpr(UnaryOp.NOT, consistExpr);

        return consistExpr;
    }
    
    static public void getOutputClosure(List<Connection> conns, Set<Subcomponent> subs) {

        assert (subs.size() == 1);
        Subcomponent orig = (Subcomponent) (subs.toArray()[0]);
        int prevSize = subs.size();
        do {
            prevSize = subs.size();
            for (Connection conn : conns) {
                AbstractConnectionEnd absConnDest = conn.getDestination();
                AbstractConnectionEnd absConnSour = conn.getSource();

                assert (absConnDest instanceof ConnectedElement);
                Context destContext = ((ConnectedElement) absConnDest).getContext();
                assert (absConnSour instanceof ConnectedElement);
                Context sourContext = ((ConnectedElement) absConnSour).getContext();
                if (sourContext != null && subs.contains(sourContext)) {
                    if (destContext != null && destContext instanceof Subcomponent) {
                        //assert (destContext instanceof Subcomponent);
                        if (orig.equals(destContext)) {
                            // there is a loop
                            subs.clear();
                            break;
                        }
                        subs.add((Subcomponent) destContext);
                    }
                }
            }
        } while (subs.size() != prevSize);

    }
}
