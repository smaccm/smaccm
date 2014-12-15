package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.CastExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordAccessExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.InitialStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;

public class AgreeEmitterState  extends AgreeSwitch<Expr> {

	  //lists of all the jkind expressions from the annex
    public final List<Expr> assumpExpressions = new ArrayList<>();
    public final List<Equation> guarExpressions = new ArrayList<>();
    public final List<Expr> assertExpressions = new ArrayList<>();
    public final List<Equation> propExpressions = new ArrayList<>();
    public final List<Equation> eqExpressions = new ArrayList<>();
    public final List<Equation> constExpressions = new ArrayList<>();
    public final List<Node> nodeDefExpressions = new ArrayList<>();
    public final List<Equation> connExpressions = new ArrayList<>();
	public final List<Expr> initialExpressions = new ArrayList<>();

    public final Set<jkind.lustre.RecordType> typeExpressions = new HashSet<>();
    //this set keeps track of all the left hand sides of connection
    //expressions
    
    //reference map used for hyperlinking from the console
    public final Map<String, EObject> refMap = new HashMap<>();
    public final Map<NamedElement, String> typeMap = new HashMap<>();
    
    //used for pretty printing jkind -> aadl variables
    public final Map<String, String> varRenaming = new HashMap<>();
    public final AgreeRenaming agreeRename;
    
    //keeps track of new variables
    public final Set<AgreeVarDecl> inputVars = new HashSet<>();
    public final Set<AgreeVarDecl> internalVars = new HashSet<>();
    public final Set<AgreeVarDecl> outputVars = new HashSet<>();
    

    public int synchrony = 0;
    public List<IdExpr> calendar = new ArrayList<IdExpr>();
    public List<MNSynchronyElement> mnSyncEls = new ArrayList<>();
    public boolean simultaneity = true;
	public boolean connectionExpressionsSet = false;

    private final String clockIDPrefix = "__CLOCK_";
    private final String eventPrefix = "__EVENT_";
    
	public final String thisPrefix;
	public final String topLevelPrefix;
	
    //used for printing results
    public final AgreeLayout layout;
    public final String category;
    
    //the current implementation
    public final ComponentInstance curInst;
	public final Subcomponent curComp;
	public final List<Subcomponent> ordering = new ArrayList<>();
    
    //used for string formatting
	private static final String dotChar = "__";

    
    public AgreeEmitterState(String topPrefix, String thisPrefix, AgreeLayout layout, String category,
    		ComponentInstance compInst, Subcomponent subComp){
    	agreeRename = new AgreeRenaming(topPrefix, varRenaming);
    	this.thisPrefix = thisPrefix;
    	this.topLevelPrefix = topPrefix;
    	this.layout = layout;
    	this.category = category;
    	this.curInst = compInst;
    	this.curComp = subComp;

    	 if(!layout.getCategories().contains(category)){
             layout.addCategory(category);
         }
    	
    }
    
    @Override
    public Expr caseOrderStatement(OrderStatement order){
    	for(NamedElement el : order.getComps()){
    		ordering.add((Subcomponent)el);
    	}
    	return null;
    }
    
    
    @Override
    public Expr caseCalenStatement(CalenStatement calen){
    	for(NamedElement namedEl : calen.getEls()){
    		IdExpr clockId = new IdExpr(clockIDPrefix+thisPrefix+namedEl.getName());
    		this.calendar.add(clockId);
    	}
        return null;
    }
    
    @Override
    public Expr caseSynchStatement(SynchStatement sync){
    	
    	if(sync instanceof CalenStatement
    		|| sync instanceof MNSynchStatement){
    		return null;
    	}
    	
        this.synchrony  = Integer.valueOf(sync.getVal());
        String simVal = sync.getSim();
        
        if(simVal != null){
        	this.simultaneity = !simVal.equals("no_simult");
        }
        
        return null;
    }
    
    @Override
    public Expr caseMNSynchStatement(MNSynchStatement sync){
    	
    	for(int i = 0; i < sync.getComp1().size(); i++){
    		Subcomponent maxComp = (Subcomponent) sync.getComp1().get(i);
    		Subcomponent minComp = (Subcomponent) sync.getComp2().get(i);
    		
    		Expr maxClock = new IdExpr(clockIDPrefix+thisPrefix+maxComp.getName());
    		Expr minClock = new IdExpr(clockIDPrefix+thisPrefix+minComp.getName());
    		int max = Integer.valueOf(sync.getMax().get(i));
    		int min = Integer.valueOf(sync.getMin().get(i));
    		
    		MNSynchronyElement elem = new MNSynchronyElement(maxClock, minClock, max, min);
    		
    		mnSyncEls.add(elem);
    	}
    	
    	return null;
    }
    
    @Override
    public Expr caseLiftStatement(LiftStatement lift){
    	throw new AgreeException("Lift statements should not be called in a do switch\n");
    }
    
    @Override
    public Expr caseAgreeContractSubclause(AgreeContractSubclause contract) {

        return doSwitch(contract.getContract());
    }

    @Override
    public Expr caseAgreeContract(AgreeContract contract) {

    	for (SpecStatement spec : contract.getSpecs()) {
    		if(!(spec instanceof LiftStatement)){
    			doSwitch(spec);
    		}
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
        AgreeStateUtils.addToRefMap(guarStr, state, agreeRename, refMap);
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
        AgreeStateUtils.addToRefMap(guarStr, state, agreeRename, refMap);
        IdExpr strId = new IdExpr(guarStr);
        Equation eq = new Equation(strId, expr);
        guarExpressions.add(eq);
        return expr;
    }
    
    @Override
    public Expr caseInitialStatement(InitialStatement state){
    	Expr expr = doSwitch(state.getExpr());
        initialExpressions.add(expr);

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

        AgreeVarDecl varDecl = new AgreeVarDecl(thisPrefix+state.getName(), NamedType.BOOL);

        layout.addElement(category, agreeRename.rename(varDecl.id), AgreeLayout.SigType.OUTPUT);

        //addToRenaming(varDecl.jKindStr, varDecl.aadlStr);
        AgreeStateUtils.addToRefMap(varDecl.id, state, agreeRename, refMap);
        internalVars.add(varDecl);

        IdExpr id = new IdExpr(varDecl.id);
        Equation eq = new Equation(id, expr);
        propExpressions.add(eq);
        return expr;
        
    }
    
    @Override
    public Expr caseEqStatement(EqStatement state) {

        Expr expr = null;
        if(state.getExpr() != null){
            //this is an explicitly defined variable
            expr = doSwitch(state.getExpr());
        }
        List<IdExpr> varIds = new ArrayList<IdExpr>();

        for (Arg arg : state.getLhs()) {
            String baseName = arg.getName();
            String recordTypeName = AgreeStateUtils.getRecordTypeName(arg.getType(), typeMap, typeExpressions);
			AgreeVarDecl varDecl = new AgreeVarDecl(thisPrefix + baseName,
            		AgreeStateUtils.getNamedType(recordTypeName));

            IdExpr idExpr = new IdExpr(varDecl.id);
            varIds.add(idExpr);

            layout.addElement(category, agreeRename.rename(varDecl.id), AgreeLayout.SigType.OUTPUT);
            AgreeStateUtils.addToRefMap(varDecl.id, state, agreeRename, refMap);
            
            if(expr != null){
                internalVars.add(varDecl);
            }else{
                outputVars.add(varDecl);
            }
        }

        if(expr != null){
            Equation eq = new Equation(varIds, expr);
            eqExpressions.add(eq);
        }

        return null;
    }

    @Override
    public Expr caseRecordUpdateExpr(RecordUpdateExpr upExpr){
    	EList<NamedElement> args = upExpr.getArgs();
    	EList<com.rockwellcollins.atc.agree.agree.Expr> argExprs = upExpr.getArgExpr();
    	
    	Expr lustreExpr = doSwitch(upExpr.getRecord());
    	for(int i= 0; i < args.size(); i++){
    		com.rockwellcollins.atc.agree.agree.Expr argExpr = argExprs.get(i);
    		NamedElement arg = args.get(i);
    		Expr lustreArgExpr = doSwitch(argExpr);
    		lustreExpr = new jkind.lustre.RecordUpdateExpr(lustreExpr, arg.getName(), lustreArgExpr);
    	}
    	
    	return lustreExpr;
    }
    
    @Override
    public Expr caseRecordExpr(RecordExpr recExpr){
    	
    	EList<NamedElement> agreeArgs = recExpr.getArgs();
    	EList<com.rockwellcollins.atc.agree.agree.Expr> agreeArgExprs = recExpr.getArgExpr();
    	Map<String, Expr> argExprMap = new HashMap<String, Expr>();
    	
    	for(int i = 0; i < agreeArgs.size(); i++){
    		NamedElement agreeArg = agreeArgs.get(i);
    		com.rockwellcollins.atc.agree.agree.Expr agreeExpr = agreeArgExprs.get(i);
    		
    		Expr lustreExpr = doSwitch(agreeExpr);
    		String argName = agreeArg.getName();
    		
    		argExprMap.put(argName, lustreExpr);
    		
    	}
    	
    	NestedDotID recId = recExpr.getRecord();
    	String recName = AgreeStateUtils.getRecordTypeName(recId, typeMap, typeExpressions); 
    	return new jkind.lustre.RecordExpr(recName, argExprMap);

    }
    
    @Override
    public Expr caseFloorCast(FloorCast floor){
    	Expr expr = doSwitch(floor.getExpr());
    	Expr castExpr = new CastExpr(NamedType.INT, expr);
    	return castExpr;
    }
    
    @Override
    public Expr caseRealCast(RealCast real){
    	Expr expr = doSwitch(real.getExpr());
    	Expr castExpr = new CastExpr(NamedType.REAL, expr);
    	return castExpr;
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
        case "mod":
            binOp = BinaryOp.MODULUS;
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
     
        String fnName = AgreeStateUtils.getNodeName(namedEl);

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
    
    @Override
    public Expr caseFnDefExpr(FnDefExpr expr) {
    	String nodeName = AgreeStateUtils.getNodeName(expr);
    	for(Node node : nodeDefExpressions){
    		if(node.id.equals(nodeName)){
    			return null;
    		}
    	}
    	List<VarDecl> inputs = AgreeStateUtils.argsToVarDeclList(thisPrefix, expr.getArgs(), typeMap, typeExpressions);
    	Expr bodyExpr = doSwitch(expr.getExpr());
    	NamedType outType = AgreeStateUtils.getNamedType(AgreeStateUtils.getRecordTypeName(expr.getType(), typeMap, typeExpressions));
    	VarDecl outVar = new VarDecl("_outvar", outType);
    	List<VarDecl> outputs = Collections.singletonList(outVar);
    	Equation eq = new Equation(new IdExpr("_outvar"), bodyExpr);
    	List<Equation> eqs = Collections.singletonList(eq);
    	Node node = new Node(nodeName, inputs, outputs, Collections.<VarDecl> emptyList(), eqs);
    	nodeDefExpressions.add(node);
    	return null;
    }
    
    @Override
    public Expr caseNodeDefExpr(NodeDefExpr expr) {
        // System.out.println("Visiting caseNodeDefExpr");

        String nodeName = AgreeStateUtils.getNodeName(expr);
        
        for(Node node : nodeDefExpressions){
            if(node.id.equals(nodeName)){
                return null;
            }
        }

        List<VarDecl> inputs = AgreeStateUtils.argsToVarDeclList(thisPrefix, expr.getArgs(), typeMap, typeExpressions);
        List<VarDecl> outputs = AgreeStateUtils.argsToVarDeclList(thisPrefix, expr.getRets(), typeMap, typeExpressions);
        NodeBodyExpr body = expr.getNodeBody();
        List<VarDecl> internals = AgreeStateUtils.argsToVarDeclList(thisPrefix, body.getLocs(), typeMap, typeExpressions);
        List<Equation> eqs = new ArrayList<Equation>();
        List<String> props = new ArrayList<String>();

        String lemmaName = "nodeLemma";
        int lemmaIndex = 0;
        for (NodeStmt stmt : body.getStmts()) {
            if (stmt instanceof NodeLemma) {
                NodeLemma nodeLemma = (NodeLemma) stmt;
                //String propName = ((NodeLemma) stmt).getStr();
                String propName = lemmaName + lemmaIndex++;
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
    
    //helper method for above
    private Equation nodeEqToEq(NodeEq nodeEq) {
        Expr expr = doSwitch(nodeEq.getExpr());
        List<IdExpr> ids = new ArrayList<IdExpr>();
        for (Arg arg : nodeEq.getLhs()) {
            ids.add(new IdExpr(thisPrefix + arg.getName()));
        }
        Equation eq = new Equation(ids, expr);
        return eq;
    }
    
    @Override
    public Expr caseGetPropertyExpr(GetPropertyExpr expr) {

        NamedElement propName = expr.getProp();
        NamedElement compName = AgreeStateUtils.namedElFromId(expr.getComponent(), curInst);
        
        Property prop = (Property) propName;

        PropertyExpression propVal = AgreeEmitterUtilities.getPropExpression(compName, prop);

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
    
    @Override
    public Expr caseThisExpr(ThisExpr expr) {
        throw new AgreeException("A 'this' expression should never be called in a case statement");
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
    public Expr caseNestedDotID(NestedDotID id) {

        String jKindVar = "";
        String aadlVar = "";
        NamedElement base = id.getBase();
        
        while (id.getSub() != null &&
        		(base instanceof FeatureGroup
        		|| base instanceof AadlPackage
        		|| base instanceof Subcomponent)) {
            jKindVar += base.getName() + dotChar;
            aadlVar += base.getName() + ".";
            id = id.getSub();
            base = id.getBase();
        }

        NamedElement namedEl = id.getBase();
        
        String tag = id.getTag();
        if(tag != null){
        	
        	String jVarPrefix = this.thisPrefix + jKindVar + namedEl.getName();
        	
        	switch(tag){
        	case "_CLK":
        		//a variable of the same name as this should be created by setEventPortQueues()
            	//in the AgreeAnnexEmitter which created "this" AgreeAnnexEmitter
            	AgreeVarDecl clockVar = new AgreeVarDecl(clockIDPrefix+thisPrefix+namedEl.getName(),
                		NamedType.BOOL);
            	
            	IdExpr clockId = new IdExpr(clockVar.id);

            	//if we have already made the expression then don't make it again
                if(inputVars.contains(clockVar)){
                	return clockId;
                }
                
                inputVars.add(clockVar);
                //addToRenaming(clockVar.jKindStr,clockVar.aadlStr);
                AgreeStateUtils.addToRefMap(clockVar.id, namedEl, agreeRename, refMap);
                layout.addElement(category, agreeRename.rename(clockVar.id), AgreeLayout.SigType.INPUT);
            	
            	return clockId;
        	default:
        		throw new AgreeException("use of uknown tag: '"+tag+"' in expression: '"+aadlVar+tag+"'");
        	}
        }
        

        //special case for constants
        if(namedEl instanceof ConstStatement){
            return doSwitch(((ConstStatement)namedEl).getExpr());
        }
        
        jKindVar = thisPrefix + jKindVar + namedEl.getName();
    	Expr result = new IdExpr(jKindVar);
    	//this is a record accessrecord
    	while(id.getSub() != null){
    		id = id.getSub();
    		namedEl = id.getBase();
    		result = new RecordAccessExpr(result, namedEl.getName());
    	}


        return result;
    }

    @Override
    public Expr casePreExpr(PreExpr expr) {
        Expr res = doSwitch(expr.getExpr());

        return new UnaryExpr(UnaryOp.PRE, res);
    }

    @Override
    public Expr caseEventExpr(EventExpr expr){
    	
    	IdExpr nestIdExpr = (IdExpr) doSwitch(expr.getId());
    	String eventStr = eventPrefix+nestIdExpr.id;
    	return new IdExpr(eventStr);
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
	
}
