package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.excel.Layout;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AbstractConnectionEnd;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.BooleanLiteral;
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
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.Feature;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
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

public class AgreeEmitter extends AgreeSwitch<Expr> {

    public class AgreeVarDecl implements Comparable<AgreeVarDecl> {
        public String jKindStr = null;
        public String aadlStr = null;
        public String type = null;

        @Override
        public int hashCode() {
            return jKindStr.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof AgreeVarDecl)) {
                return false;
            }
            return ((AgreeVarDecl) o).jKindStr.equals(jKindStr);
        }

        @Override
        public int compareTo(AgreeVarDecl o) {
            return jKindStr.compareTo(o.jKindStr);
        }

    }

    private class ComponentContract {
        public String compName;
        public List<Expr> assumps;
        public List<Equation> guars;
        public List<Expr> asserts;
        public List<Equation> props;
        public List<Equation> eqs;
        public List<Equation> consts;
        public List<Node> nodes;

        public ComponentContract(String s, List<Expr> assu, List<Equation> g, List<Expr> asse,
                List<Equation> p, List<Equation> e, List<Equation> c, List<Node> n) {
            compName = s;
            assumps = assu;
            guars = g;
            asserts = asse;
            props = p;
            eqs = e;
            consts = c;
            nodes = n;
        }

    }

    private final AgreeLogger log = new AgreeLogger();

    // the top level component implementation
    private final ComponentImplementation topCompImpl;

    // the contract for the top level component implementation
    private ComponentContract sysContr;

    private final Map<Subcomponent, ComponentContract> subContrs = new HashMap<>();
    private final AgreeLayout layout = new AgreeLayout();
    // keeps track of variable equivalences inferred from connections in the
    // top level component implementation.
    private final List<Equation> connExpressions = new ArrayList<Equation>();

    // lists of expressions that are gathered for each individual component
    private List<Expr> assumpExpressions;
    private List<Equation> guarExpressions;
    private List<Expr> assertExpressions;
    private List<Equation> propExpressions;
    private List<Equation> eqExpressions;
    private List<Equation> constExpressions;
    // private List<Expr> funDefExpressions;
    private List<Node> nodeDefExpressions;

    private final Set<AgreeVarDecl> inputVars = new HashSet<>();
    private final Set<AgreeVarDecl> internalVars = new HashSet<>();
    private final Set<Subcomponent> subComps = new HashSet<>();
    private final Map<Subcomponent, Set<Subcomponent>> closureMap = new HashMap<>();
    private final String dotChar = "__";
    private String jKindNameTag;
    private String aadlNameTag;
    private final Map<String, String> varRenaming = new HashMap<>();
    private final Map<String, EObject> refMap = new HashMap<>();
    private Subcomponent curComp = null;
    private final Map<String, CallDef> nodeDefs = new HashMap<>();

    // *********************** BEGIN METHODS ********************************

    public AgreeEmitter(ComponentImplementation compImpl, Subcomponent curComp) {
        topCompImpl = compImpl;
        this.curComp = curComp;
    }

    public Program evaluate() {
        layout.addCategory("Top");

        if (curComp != null) {
            layout.addCategory(curComp.getName());
        }

        for (Subcomponent subComp : topCompImpl.getAllSubcomponents()) {
            layout.addCategory(subComp.getName());
        }

        ComponentType ct = topCompImpl.getType();
        setLustreVars(ct);
        setVarEquivs();
        makeContracts();
        List<Node> nodes = getLustre();

        Program prog = new Program(nodes);

        return prog;

    }

    // ************** CASE STATEMENTS **********************

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

        if (curComp != null) {
            layout.addElement(curComp.getName(), varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);
        } else {
            layout.addElement("Top", varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);
        }

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

            if (curComp != null) {
                layout.addElement(curComp.getName(), varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);
            } else {
                layout.addElement("Top", varDecl.aadlStr, AgreeLayout.SigType.OUTPUT);
            }

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

        if (curComp != null) {
            layout.addElement(curComp.getName(), varType.aadlStr, AgreeLayout.SigType.OUTPUT);
        } else {
            layout.addElement("Top", varType.aadlStr, AgreeLayout.SigType.OUTPUT);
        }

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
        // TODO: handle this correctly
        String fnName = jKindNameTag + getFnCallExprName(expr);

        if (!nodeDefs.containsKey(fnName)) {
            doSwitch(getFinalNestId(expr.getFn()));
            assert (nodeDefs.containsKey(fnName));
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

        if (nodeDefs.containsKey(nodeName))
            return null; // don't create the node more than once

        nodeDefs.put(nodeName, expr);
        List<VarDecl> inputs = argsToVarDeclList(expr.getArgs());
        List<VarDecl> outputs = argsToVarDeclList(expr.getRets());
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = argsToVarDeclList(body.getLocs());
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

    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {

        String nodeName = jKindNameTag + expr.getName();

        if (nodeDefs.containsKey(nodeName))
            return null;

        List<VarDecl> inputs = argsToVarDeclList(expr.getArgs());
        Expr bodyExpr = doSwitch(expr.getExpr());

        Type outType = new NamedType(expr.getType().getString());
        VarDecl outVar = new VarDecl("_outvar", outType);
        List<VarDecl> outputs = Collections.singletonList(outVar);
        Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
        List<Equation> eqs = Collections.singletonList(eq);

        Node node = new Node(nodeName, inputs, outputs, Collections.<VarDecl> emptyList(), eqs);

        nodeDefs.put(nodeName, expr);
        nodeDefExpressions.add(node);

        return null;
    }

    // TODO: place node definition here.

    @Override
    public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

        NamedElement propName = namedElFromId(expr.getProp());
        NamedElement compName = namedElFromId(expr.getComponent());

        assert (propName instanceof Property);
        assert (compName instanceof Subcomponent);

        Property prop = (Property) propName;
        Subcomponent subComp = (Subcomponent) compName;

        PropertyExpression propVal = getPropExpression(subComp, prop);

        if (propVal == null) {
            ComponentImplementation comp = subComp.getComponentImplementation();
            propVal = getPropExpression(comp, prop);
        }

        assert (propVal != null);
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
        if (namedEl instanceof DataSubcomponent) {
            String tempStr = result.id;
            AgreeVarDecl tempStrType = new AgreeVarDecl();
            tempStrType.jKindStr = tempStr;

            if (!inputVars.contains(tempStrType) && !internalVars.contains(tempStrType)) {

                log.logWarning("In component '"
                        + orgId.getBase().getContainingClassifier().getFullName() + "', Port '"
                        + tempStr + "' is not connected to anything. Considering it to be"
                        + " an unconstrained primary input.");

             
                // this code just creates a new PI
                tempStrType = dataTypeToVarType((DataSubcomponent) namedEl);
                jKindVar = jKindNameTag + jKindVar + Id.getBase().getName();
                aadlVar = aadlNameTag + aadlVar + Id.getBase().getName();

                //get rid of this. this is just a sanity check
                assert(jKindVar.equals(tempStr));
                tempStrType.jKindStr = tempStr;
                tempStrType.aadlStr = aadlVar;
                
                if(curComp != null){
                    layout.addElement(curComp.getName(), aadlVar, AgreeLayout.SigType.INPUT);
                }else{
                    layout.addElement("Top", aadlVar, AgreeLayout.SigType.INPUT);
                }
                varRenaming.put(jKindVar, aadlVar);
                refMap.put(aadlVar, Id);
                inputVars.add(tempStrType);
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

    // *********** Utility Methods *****************

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

    private void getOutputClosure(List<Connection> conns, Set<Subcomponent> subs) {

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
                    if (destContext != null) {
                        assert (destContext instanceof Subcomponent);
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

    private void makeContracts() {
        ComponentType ct = topCompImpl.getType();

        for (Subcomponent sub : topCompImpl.getAllSubcomponents()) {
            Set<Subcomponent> outputClosure = new HashSet<Subcomponent>();
            outputClosure.add(sub);
            getOutputClosure(topCompImpl.getAllConnections(), outputClosure);
            closureMap.put(sub, outputClosure);
        }

        // curComp = sysImpl;

        // add all of the facts
        assumpExpressions = new ArrayList<Expr>();
        guarExpressions = new ArrayList<Equation>();
        assertExpressions = new ArrayList<Expr>();
        propExpressions = new ArrayList<Equation>();
        eqExpressions = new ArrayList<Equation>();
        constExpressions = new ArrayList<Equation>();
        // funDefExpressions = new ArrayList<KindExpr>();
        nodeDefExpressions = new ArrayList<Node>();

        // set the tag
        jKindNameTag = "";
        aadlNameTag = "";

        for (AnnexSubclause annex : topCompImpl.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                doSwitch(annex);
            }
        }

        for (AnnexSubclause annex : ct.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                doSwitch(annex);
            }
        }

        sysContr = new ComponentContract(ct.getName(), assumpExpressions, guarExpressions,
                assertExpressions, propExpressions, eqExpressions, constExpressions,
                nodeDefExpressions);

        for (Subcomponent subComp : topCompImpl.getAllSubcomponents()) {
            subComps.add(subComp);
            curComp = subComp;

            assumpExpressions = new ArrayList<Expr>();
            guarExpressions = new ArrayList<Equation>();
            assertExpressions = new ArrayList<Expr>();
            propExpressions = new ArrayList<Equation>();
            eqExpressions = new ArrayList<Equation>();
            constExpressions = new ArrayList<Equation>();
            // funDefExpressions = new ArrayList<KindExpr>();
            nodeDefExpressions = new ArrayList<Node>();

            // ******************* VERY IMPORTANT ************************
            // the name tag is used to prevent namespace collisions in the
            // generated lustre code. The name tag must also correctly
            // resolve connections to their instance names. For example,
            // consider the following aadl connection:
            //
            // FGSLtoAP: port FGS_L.GC -> AP.GC_L;
            //
            // The variables in the subcomponent "FGS_L" must be appended
            // with "FGS_L_" (assuming the "dotChar" is "_"). Likewise,
            // the variables in the subcomponent "AP" must be appended with
            // "AP_"

            jKindNameTag = subComp.getName() + dotChar;
            aadlNameTag = subComp.getName() + ".";

            // compToKindVars.put(curComp, new ArrayList<String>());
            ComponentImplementation compImp = subComp.getComponentImplementation();

            for (AnnexSubclause annex : compImp.getAllAnnexSubclauses()) {
                if (annex instanceof AgreeContractSubclause) {
                    doSwitch(annex);
                }
            }

            ComponentType compType = compImp.getType();
            for (AnnexSubclause annex : compType.getAllAnnexSubclauses()) {
                if (annex instanceof AgreeContractSubclause) {
                    doSwitch(annex);
                }
            }

            subContrs.put(subComp, new ComponentContract(subComp.getName(), assumpExpressions,
                    guarExpressions, assertExpressions, propExpressions, eqExpressions,
                    constExpressions, nodeDefExpressions));
        }

    }

    // fills the connExpressions lists with expressions
    // that equate variables that are connected to one another
    private void setVarEquivs() {

        // use for checking delay
        Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                OsateResourceUtil.getResourceSet(), "Communication_Properties::Timing");

        for (Connection conn : topCompImpl.getAllConnections()) {

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

            DataPort port = null;
            if (destConn != null && (destConn instanceof DataPort)) {
                port = (DataPort) destConn;
            } else if (sourConn != null && (sourConn instanceof DataPort)) {
                port = (DataPort) destConn;
            }
            assert (port != null);

            DataSubcomponentType dataSub = port.getDataFeatureClassifier();

            if (!(dataSub instanceof DataImplementation)) {
                continue;
            }

            Set<AgreeVarDecl> tempSet = new HashSet<AgreeVarDecl>();
            getAllDataNames((DataImplementation) dataSub, tempSet);

            String sourStr;
            String destStr;
            String aadlSourStr;
            String aadlDestStr;
            if (sourContext != null) { // source is not an end point
                assert (sourContext instanceof Subcomponent);
                sourStr = sourContext.getName() + dotChar + sourConn.getName();
                aadlSourStr = sourContext.getName() + "." + sourConn.getName();
            } else {
                sourStr = sourConn.getName();
                aadlSourStr = sourConn.getName();
            }

            if (destContext != null) { // destination is not an end point
                assert (destContext instanceof Subcomponent);
                destStr = destContext.getName() + dotChar + destConn.getName();
                aadlDestStr = destContext.getName() + "." + destConn.getName();
            } else {
                destStr = destConn.getName();
                aadlDestStr = destConn.getName();
            }

            for (AgreeVarDecl varType : tempSet) {
                String newDestStr = destStr + dotChar + varType.jKindStr;
                String newSourStr = sourStr + dotChar + varType.jKindStr;
                String newAADLDestStr = aadlDestStr + "." + varType.aadlStr;
                String newAADLSourStr = aadlSourStr + "." + varType.aadlStr;

                // make an internal var for this
                varType.jKindStr = newDestStr;
                varType.aadlStr = newAADLDestStr;

                if (destContext != null) {
                    layout.addElement(destContext.getName(), varType.aadlStr,
                            AgreeLayout.SigType.OUTPUT);
                }

                refMap.put(varType.aadlStr, destConn);
                varRenaming.put(varType.jKindStr, varType.aadlStr);
                internalVars.add(varType);

                // if the source context is not null, then this is a variable
                // that was not in the top level component features. Therefore
                // a new input variable must be created
                if (sourContext != null) {
                    AgreeVarDecl inputVar = new AgreeVarDecl();
                    inputVar.type = varType.type;
                    inputVar.jKindStr = newSourStr;
                    inputVar.aadlStr = newAADLSourStr;

                    layout.addElement(sourContext.getName(), inputVar.aadlStr,
                            AgreeLayout.SigType.INPUT);
                    varRenaming.put(inputVar.jKindStr, inputVar.aadlStr);
                    refMap.put(inputVar.aadlStr, sourConn);
                    inputVars.add(inputVar);
                }

                Expr connExpr = null;
                IdExpr sourId = new IdExpr(newSourStr);

                if (sourContext != null && destContext != null && delayed) {
                    // this is not an input, and the output is not a terminal
                    Expr initValExpr = null;
                    switch (varType.type) {
                    case "bool":
                        initValExpr = new BoolExpr(true);
                        break;
                    case "int":
                        initValExpr = new IntExpr(BigInteger.valueOf(0));
                        break;
                    case "real":
                        initValExpr = new RealExpr(BigDecimal.valueOf(0.0));
                        break;
                    }
                    connExpr = new UnaryExpr(UnaryOp.PRE, sourId);
                    connExpr = new BinaryExpr(initValExpr, BinaryOp.ARROW, connExpr);
                } else {
                    connExpr = sourId;
                }
                IdExpr destId = new IdExpr(newDestStr);
                Equation connEq = new Equation(destId, connExpr);

                connExpressions.add(connEq);
            }

        }
    }

    private void setLustreVars(ComponentType ct) {
        for (Feature feat : ct.getAllFeatures()) {
            if (feat instanceof DataPort) {
                DataPort port = (DataPort) feat;
                DataSubcomponentType dataSub = port.getDataFeatureClassifier();
                if (dataSub instanceof DataImplementation) {
                    Set<AgreeVarDecl> tempSet = new HashSet<AgreeVarDecl>();
                    getAllDataNames((DataImplementation) dataSub, tempSet);
                    for (AgreeVarDecl var : tempSet) {
                        var.jKindStr = feat.getName() + dotChar + var.jKindStr;
                        var.aadlStr = feat.getName() + "." + var.aadlStr;

                    }
                    if (port.getDirection().incoming()) {
                        for (AgreeVarDecl var : tempSet) {
                            varRenaming.put(var.jKindStr, var.aadlStr);
                            refMap.put(var.aadlStr, port);
                            layout.addElement("Top", var.aadlStr, AgreeLayout.SigType.INPUT);
                            inputVars.add(var);
                        }
                    }
                }
            }
        }
    }

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
                    AgreeVarDecl newStrType = dataTypeToVarType((DataSubcomponent) sub);
                    if (newStrType.type != null) {
                        subStrTypes.add(newStrType);
                    }
                }
            }
        }
    }

    private AgreeVarDecl dataTypeToVarType(DataSubcomponent sub) {

        DataType type = (DataType) sub.getAllClassifier();
        AgreeVarDecl newStrType = new AgreeVarDecl();
        newStrType.jKindStr = sub.getName();
        newStrType.aadlStr = sub.getName();

        do {
            String name = type.getQualifiedName();
            switch (name) {
            case "Base_Types::Boolean":
                newStrType.type = "bool";
                return newStrType;
            case "Base_Types::Integer":
                newStrType.type = "int";
                return newStrType;
            case "Base_Types::Float":
                newStrType.type = "real";
                return newStrType;
            }
            type = (DataType) type.getExtended();

        } while (type != null);

        // validation should make sure that this never happens
        assert (false);
        return null;

    }

    private Equation getLustreHistory(Expr expr, IdExpr histId) {

        Expr preHist = new UnaryExpr(UnaryOp.PRE, histId);
        Expr histExpr = new BinaryExpr(expr, BinaryOp.AND, preHist);
        histExpr = new BinaryExpr(expr, BinaryOp.ARROW, histExpr);

        Equation histEq = new Equation(histId, histExpr);

        return histEq;

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

    private Expr getLustreAssumptions(ComponentContract contract) {
        return conjoin(contract.assumps);
    }

    private Expr getLustreAssumptionsAndAssertions(ComponentContract contract) {
        return conjoin(conjoin(contract.assumps), conjoin(contract.asserts));
    }

    private Expr getLustreContract(ComponentContract contract) {
        return conjoin(conjoin(contract.assumps), conjoin(contract.asserts),
                conjoinEqs(contract.guars));
    }

    private Expr getLustreGuarantee(ComponentContract contract) {
        return conjoinEqs(contract.guars);
    }

    private List<Node> getLustre() {
        // first print out the functions which will be
        // other nodes

        // start constructing the top level node
        List<Equation> eqs = new ArrayList<Equation>();
        List<VarDecl> inputs = new ArrayList<VarDecl>();
        List<VarDecl> outputs = new ArrayList<VarDecl>();
        List<VarDecl> internals = new ArrayList<VarDecl>();
        List<String> properties = new ArrayList<String>();

        List<Node> nodeSet = new ArrayList<Node>();

        nodeSet.addAll(sysContr.nodes);
        eqs.addAll(sysContr.consts);
        eqs.addAll(sysContr.eqs);
        eqs.addAll(sysContr.props);

        // debug printing
        printCompVars("Top");
        for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
            ComponentContract contract = entry.getValue();
            nodeSet.addAll(contract.nodes);

            eqs.addAll(contract.consts);
            eqs.addAll(contract.eqs);
            eqs.addAll(contract.props);

            // some stupid debug stuff
            Subcomponent comp = entry.getKey();
            printCompVars(comp.getName());
        }

        eqs.addAll(connExpressions);

        for (AgreeVarDecl agreeVar : inputVars) {
            VarDecl var = new VarDecl(agreeVar.jKindStr, new NamedType(agreeVar.type));
            inputs.add(var);
        }
        for (AgreeVarDecl agreeVar : internalVars) {
            VarDecl var = new VarDecl(agreeVar.jKindStr, new NamedType(agreeVar.type));
            internals.add(var);
        }

        IdExpr totalCompHistId = new IdExpr("_TOTAL_COMP_HIST");
        IdExpr sysAssumpHistId = new IdExpr("_SYSTEM_ASSUMP_HIST");

        internals.add(new VarDecl(totalCompHistId.id, new NamedType("bool")));
        internals.add(new VarDecl(sysAssumpHistId.id, new NamedType("bool")));

        // total component history
        Expr totalCompHist = new BoolExpr(true);

        for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
            ComponentContract contract = entry.getValue();
            totalCompHist = new BinaryExpr(totalCompHist, BinaryOp.AND, getLustreContract(contract));
        }

        eqs.add(getLustreHistory(totalCompHist, totalCompHistId));

        // system assumptions
        Expr sysAssumpHist = getLustreAssumptionsAndAssertions(sysContr);
        eqs.add(getLustreHistory(sysAssumpHist, sysAssumpHistId));

        // get the individual history variables
        for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
            ComponentContract contract = entry.getValue();
            Subcomponent comp = entry.getKey();

            Expr higherContracts = new BoolExpr(true);

            for (Subcomponent otherComp : closureMap.get(comp)) {
                higherContracts = new BinaryExpr(higherContracts, BinaryOp.AND,
                        getLustreContract(subContrs.get(otherComp)));
            }

            Expr contrAssumps = getLustreAssumptions(contract);

            IdExpr compId = new IdExpr("_Hist_" + contract.compName);
            internals.add(new VarDecl(compId.id, new NamedType("bool")));

            Expr leftSide = new UnaryExpr(UnaryOp.PRE, totalCompHist);
            leftSide = new BinaryExpr(new BoolExpr(true), BinaryOp.ARROW, leftSide);
            leftSide = new BinaryExpr(sysAssumpHist, BinaryOp.AND, leftSide);
            leftSide = new BinaryExpr(higherContracts, BinaryOp.AND, leftSide);

            Expr contrHistExpr = new BinaryExpr(leftSide, BinaryOp.IMPLIES, contrAssumps);
            Equation contrHist = new Equation(compId, contrHistExpr);
            eqs.add(contrHist);
            properties.add(compId.id);
            String propertyName = contract.compName + " Assumptions";
            varRenaming.put(compId.id, propertyName);
            layout.addElement("Top", propertyName, AgreeLayout.SigType.OUTPUT);
            
            //add a property that is true if the contract is a contradiction
            IdExpr contrId = new IdExpr("_CONTR_HIST_" + contract.compName);
            IdExpr notContrId = new IdExpr("_NULL_CONTR_HIST_" + contract.compName);

            Expr contExpr = getLustreContract(contract);
            Equation contHist = getLustreHistory(contExpr, contrId);
            Equation notContHist = new Equation(notContrId, new UnaryExpr(UnaryOp.NOT, contrId));
            eqs.add(notContHist);
            eqs.add(contHist);
            internals.add(new VarDecl(contrId.id, new NamedType("bool")));
            internals.add(new VarDecl(notContrId.id, new NamedType("bool")));
            properties.add(notContrId.id);
            String contractName = contract.compName + " Contradiction";
            varRenaming.put(notContrId.id, contractName);
            layout.addElement("Top", contractName, AgreeLayout.SigType.OUTPUT);
            
        }

        // create individual properties for guarantees
        int i = 0;
        for (Equation guar : sysContr.guars) {
            String guarName = guar.lhs.get(0).id;
            IdExpr sysGuaranteesId = new IdExpr("_SYS_GUARANTEE_" + i);
            internals.add(new VarDecl(sysGuaranteesId.id, new NamedType("bool")));

            Expr totalSysGuarExpr = new BinaryExpr(sysAssumpHistId, BinaryOp.AND, totalCompHistId);
            totalSysGuarExpr = new BinaryExpr(totalSysGuarExpr, BinaryOp.IMPLIES, guar.expr);

            Equation finalGuar = new Equation(sysGuaranteesId, totalSysGuarExpr);
            eqs.add(finalGuar);
            properties.add(sysGuaranteesId.id);
            varRenaming.put(sysGuaranteesId.id, guarName);
            layout.addElement("Top", "Component Guarantee " + i++, AgreeLayout.SigType.OUTPUT);

        }
        
        //check for contradiction in total component history
        IdExpr notTotalCompHistId = new IdExpr("_NOT_TOTAL_COMP_HIST");
        Equation contrEq = new Equation(notTotalCompHistId, new UnaryExpr(UnaryOp.NOT, totalCompHistId));
        internals.add(new VarDecl(notTotalCompHistId.id, new NamedType("bool")));
        eqs.add(contrEq);
        properties.add(notTotalCompHistId.id);
        varRenaming.put(notTotalCompHistId.id, "total component history contradiction");
        layout.addElement("Top", "total component history contradiction", AgreeLayout.SigType.OUTPUT);


        Node topNode = new Node("_MAIN", inputs, outputs, internals, eqs, properties);
        nodeSet.add(topNode);
        return nodeSet;
    }

    public ComponentImplementation getCompImpl() {
        return topCompImpl;
    }

    public Set<Subcomponent> getSubComponents() {
        return subComps;
    }

    public Layout getLayout() {
        return layout;
    }

    public String getFnCallExprName(FnCallExpr expr) {
        NestedDotID dotId = expr.getFn();
        NamedElement namedEl = getFinalNestId(dotId);
        return namedEl.getName();
    }

    private List<VarDecl> argsToVarDeclList(EList<Arg> args) {
        List<VarDecl> varList = new ArrayList<VarDecl>();
        for (Arg arg : args) {
            Type type = new NamedType(arg.getType().getString());
            VarDecl varDecl = new VarDecl(jKindNameTag + arg.getName(), type);
            varList.add(varDecl);
        }

        return varList;
    }

    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(jKindNameTag + arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }

    private NamedElement namedElFromId(EObject obj) {
        if (obj instanceof NestedDotID) {
            return getFinalNestId((NestedDotID) obj);
        } else if (obj instanceof com.rockwellcollins.atc.agree.agree.IdExpr) {
            return ((com.rockwellcollins.atc.agree.agree.IdExpr) obj).getId();
        } else {
            assert (obj instanceof ThisExpr);
            return curComp;
        }
    }

    private NamedElement getFinalNestId(NestedDotID dotId) {
        while (dotId.getSub() != null) {
            dotId = dotId.getSub();
        }
        return dotId.getBase();
    }

    public String getLog() {
        return log.toString();
    }

    public Map<String, EObject> getReferenceMap() {
        return refMap;
    }

    public Renaming getRenaming() {
        return new MapRenaming(varRenaming, MapRenaming.Mode.NULL);
    }

    public void printCompVars(String compName) {
        List<String> vars = layout.getAllInputsFromCategory(compName);
        if (vars != null) {
            System.out.println("Input Vars from '" + compName + "'");
            for (String var : vars) {
                var = var.replaceAll("\\.", dotChar);
                System.out.println(var);
            }
            System.out.println();
        }

        vars = layout.getAllOutputsFromCategory(compName);
        if (vars != null) {
            System.out.println("Output Vars from '" + compName + "'");
            for (String var : vars) {
                var = var.replaceAll("\\.", dotChar);
                System.out.println(var);
            }
            System.out.println();
        }
    }

}
