package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AbstractConnectionEnd;
import org.osate.aadl2.AbstractNamedValue;
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
import org.osate.aadl2.SystemSubcomponent;
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
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IdExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.NestIdExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.util.AgreeSwitch;

public class AgreeEvaluator extends AgreeSwitch<KindExpr> {

  public class VarDecl implements Comparable<Object> {
    public String jKindStr = null;
    public String aadlStr  = null;
    public String type     = null;

    @Override
    public int hashCode() {
      return jKindStr.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof VarDecl))
        return false;
      return ((VarDecl) o).jKindStr.equals(jKindStr);
    }

    @Override
    public int compareTo(Object o) {
      // TODO Auto-generated method stub
      if (!(o instanceof VarDecl))
        return -1;

      return jKindStr.compareTo(((VarDecl) o).jKindStr);
    }

  }

  private class ComponentContract {
    public String         compName;
    public List<KindExpr> assumps;
    public List<KindExpr> guars;
    public List<KindExpr> asserts;
    public List<KindExpr> props;
    public List<KindExpr> eqs;
    public List<KindExpr> consts;
    public List<KindExpr> funcs;
    public List<KindExpr> nodes;

    public ComponentContract(String s, List<KindExpr> assu, List<KindExpr> g,
        List<KindExpr> asse, List<KindExpr> p, List<KindExpr> e,
        List<KindExpr> c, List<KindExpr> f, List<KindExpr> n) {
      compName = s;
      assumps = assu;
      guars = g;
      asserts = asse;
      props = p;
      eqs = e;
      consts = c;
      funcs = f;
      nodes = n;
    }

  }

  public AgreeLogger                           log             = new AgreeLogger();

  // the top level component implementation
  private ComponentImplementation                 topCompImpl;

  // the contract for the top level system implementation
  private ComponentContract                    sysContr;

  private Map<Subcomponent, ComponentContract> subContrs       = new HashMap<Subcomponent, ComponentContract>();
  private Map<NamedElement, Set<String>>       compToKindVars  = new HashMap<NamedElement, Set<String>>();
  // keeps track of variable equivalences inferred from connections in the
  // top level system implementation.
  private List<KindExpr>                       connExpressions = new ArrayList<KindExpr>();

  // lists of expressions that are gathered for each individual component
  private List<KindExpr>                       assumpExpressions;
  private List<KindExpr>                       guarExpressions;
  private List<KindExpr>                       assertExpressions;
  private List<KindExpr>                       propExpressions;
  private List<KindExpr>                       eqExpressions;
  private List<KindExpr>                       constExpressions;
  private List<KindExpr>                       funDefExpressions;
  private List<KindExpr>                       nodeDefExpressions;

  private Set<VarDecl>                         inputVars       = new HashSet<VarDecl>();                        // keeps
                                                                                                                 // track
                                                                                                                 // of
                                                                                                                 // variables
                                                                                                                 // to
                                                                                                                 // be
                                                                                                                 // marked
                                                                                                                 // as
                                                                                                                 // inputs
                                                                                                                 // to
                                                                                                                 // the
                                                                                                                 // top
                                                                                                                 // level
                                                                                                                 // node
  private Set<VarDecl>                         internalVars    = new HashSet<VarDecl>();                        // keeps
                                                                                                                 // track
                                                                                                                 // of
                                                                                                                 // variabels
                                                                                                                 // to
                                                                                                                 // be
                                                                                                                 // marked
                                                                                                                 // internal
                                                                                                                 // to
                                                                                                                 // the
                                                                                                                 // top
                                                                                                                 // level
                                                                                                                 // node
  private Set<Subcomponent>                    subComps        = new HashSet<Subcomponent>();                   // set
                                                                                                                 // of
                                                                                                                 // all
                                                                                                                 // subcomponents
  public Map<Subcomponent, Set<Subcomponent>>  closureMap      = new HashMap<Subcomponent, Set<Subcomponent>>();
  private String                               dotChar         = "_";                                           // used
                                                                                                                 // to
                                                                                                                 // replace
                                                                                                                 // "."
                                                                                                                 // in
                                                                                                                 // identifiers
  private String                               jKindNameTag;                                                    // a
                                                                                                                 // tag
                                                                                                                 // appended
                                                                                                                 // to
                                                                                                                 // property,
                                                                                                                 // const,
                                                                                                                 // and
                                                                                                                 // eq
                                                                                                                 // statements
                                                                                                                 // of
                                                                                                                 // a
                                                                                                                 // specific
                                                                                                                 // component
  private String                               aadlNameTag;                                                     // a
                                                                                                                 // tag
                                                                                                                 // appended
                                                                                                                 // to
                                                                                                                 // property,
                                                                                                                 // const,
                                                                                                                 // and
                                                                                                                 // eq
                                                                                                                 // statements
                                                                                                                 // of
                                                                                                                 // a
                                                                                                                 // specific
                                                                                                                 // component
  public Map<String, String>                   varRenaming     = new HashMap<String, String>();
  public Map<String, EObject>                  refMap          = new HashMap<String, EObject>();

  public Subcomponent                          curComp         = null;                                          // used
                                                                                                                 // for
                                                                                                                 // ThisExprs
                                                                                                                 // (also
                                                                                                                 // modified
                                                                                                                 // externally
                                                                                                                 // when
                                                                                                                 // checking
                                                                                                                 // recursively)

  public AgreeEvaluator(ComponentImplementation compImpl) {
    topCompImpl = compImpl;
  }

  public String evaluate() {

    compToKindVars.put(topCompImpl, new HashSet<String>());

    if (curComp != null)
      compToKindVars.put(curComp, new HashSet<String>());

    for (Subcomponent subComp : topCompImpl.getAllSubcomponents()) {
      compToKindVars.put(subComp, new HashSet<String>());
    }

    setLustreVars(topCompImpl.getType());
    setVarEquivs(topCompImpl);
    makeContracts(topCompImpl);
    String result = getLustre();

    return result;

  }

  // ************** CASE STATEMENTS **********************

  @Override
  public KindExpr caseAgreeContractSubclause(AgreeContractSubclause contract) {

    return doSwitch(contract.getContract());
  }

  @Override
  public KindExpr caseAgreeContract(AgreeContract contract) {

    for (SpecStatement spec : contract.getSpecs()) {
      doSwitch(spec);
    }

    return null;
  }

  @Override
  public KindExpr caseAssumeStatement(AssumeStatement state) {

    KindExpr kExpr = doSwitch(state.getExpr());
    assumpExpressions.add(kExpr);
    return kExpr;
  }

  @Override
  public KindExpr caseGuaranteeStatement(GuaranteeStatement state) {

    KindExpr kExpr = doSwitch(state.getExpr());
    guarExpressions.add(kExpr);
    return kExpr;
  }

  @Override
  public KindExpr caseAssertStatement(AssertStatement state) {

    KindExpr kExpr = doSwitch(state.getExpr());
    assertExpressions.add(kExpr);

    return kExpr;
  }

  @Override
  public KindExpr casePropertyStatement(PropertyStatement state) {

    KindExpr kExpr = doSwitch(state.getExpr());
    kExpr.setVarName(jKindNameTag + state.getName());
    VarDecl varDecl = new VarDecl();
    varDecl.jKindStr = jKindNameTag + state.getName();
    varDecl.aadlStr = aadlNameTag + state.getName();
    varDecl.type = "bool";

    if (curComp != null)
      compToKindVars.get(curComp).add(varDecl.jKindStr);
    else
      compToKindVars.get(topCompImpl).add(varDecl.jKindStr);

    varRenaming.put(varDecl.jKindStr, varDecl.aadlStr);
    refMap.put(varDecl.aadlStr, state);
    internalVars.add(varDecl);
    propExpressions.add(kExpr);
    return kExpr;
  }

  // TODO: name clashes for equations are inevitable if we don't prefix
  // with scopes - e.g. multiple function or node instances.  
  // See commented out code.  
  // This will require name lookup/replacement for id expressions or 
  // (seemingly less good) a preprocessing phase that modifies names.
  
  @Override
  public KindExpr caseEqStatement(EqStatement state) {

    KindExpr kExpr = doSwitch(state.getExpr());
    for (Arg arg : state.getArgs()) {
      String baseName = arg.getName();
      VarDecl varDecl = new VarDecl();
      varDecl.jKindStr = /* jKindNameTag + */baseName;
      kExpr.appendVarName(varDecl.jKindStr);
        
      varDecl.aadlStr = aadlNameTag + baseName;
      varDecl.type = arg.getType().getName();

      if (curComp != null)
        compToKindVars.get(curComp).add(varDecl.jKindStr);
      else
        compToKindVars.get(topCompImpl).add(varDecl.jKindStr);

      varRenaming.put(varDecl.jKindStr, varDecl.aadlStr);
      refMap.put(varDecl.aadlStr, state);
      internalVars.add(varDecl);
    }
    eqExpressions.add(kExpr);
    return kExpr;
  }

  @Override
  public KindExpr caseConstStatement(ConstStatement state) {
    KindExpr kExpr = doSwitch(state.getExpr());
    kExpr.setVarName(jKindNameTag + state.getName());
    VarDecl varType = new VarDecl();
    varType.jKindStr = jKindNameTag + state.getName();
    varType.aadlStr = aadlNameTag + state.getName();
    varType.type = state.getType().getName();

    if (curComp != null)
      compToKindVars.get(curComp).add(varType.jKindStr);
    else
      compToKindVars.get(topCompImpl).add(varType.jKindStr);

    varRenaming.put(varType.jKindStr, varType.aadlStr);
    refMap.put(varType.aadlStr, state);
    internalVars.add(varType);
    constExpressions.add(kExpr);

    return kExpr;
  }

  @Override
  public KindExpr caseParamStatement(ParamStatement state) {

    assert (false);
    return null;
  }

  @Override
  public KindExpr caseBinaryExpr(BinaryExpr expr) {

    KindExpr leftKExpr = doSwitch(expr.getLeft());
    KindExpr rightKExpr = doSwitch(expr.getRight());

    String op = expr.getOp();

    // hack for equivalence and difference
    if (op.equals("<=>"))
      op = "=";
    else if (op.equals("!="))
      op = "<>";

    KindExpr returnExpr = leftKExpr.appendRight(rightKExpr.appendLeft(" " + op
        + " "));

    return returnExpr.appendLeft("(").appendRight(")");
  }

  @Override
  public KindExpr caseBoolLitExpr(BoolLitExpr expr) {
    return new KindExpr(Boolean.toString(expr.getVal().getValue()));
  }

  public String getFnCallExprName(FnCallExpr expr) {
    CallDef callDef = expr.getFn();
    if (callDef instanceof FnDefExpr) {
      return ((FnDefExpr) callDef).getName();
    } else if (callDef instanceof NodeDefExpr) {
      return ((NodeDefExpr) callDef).getName();
    } else
      throw new AgreeException(
          "In getFnCallExprName: Match failure; FnCallExpr does not resolve "
              + "to either FnDefExpr or NodeDefExpr");
  }

  @Override
  public KindExpr caseFnCallExpr(FnCallExpr expr) {
    // TODO: handle this correctly
    String fnName = jKindNameTag + getFnCallExprName(expr);
    KindExpr args = null;

    for (int i = 0; i < expr.getArgs().size(); i++) {
      if (i == 0) {
        args = doSwitch(expr.getArgs().get(0));
        args.appendLeft("(");
      } else {
        KindExpr tempArg = doSwitch(expr.getArgs().get(i));
        args.appendRight(", ").appendRight(tempArg);
      }
    }
    assert (args != null);
    args.appendRight(")");

    return args.appendLeft(fnName);
  }

  private String printArg(Arg arg) {
    return arg.getName() + " : " + arg.getType().getName();
  }
  private String printArgList(List<Arg> args) {
    String name;
    name = " (";
    for (int i = 0; i < args.size(); i++) {
      name += printArg(args.get(i));
      if (i != args.size() - 1) {
        name += ";\n";
      }
    }
    name += ") ";
    return name;
  }

  private String printNodeEq(NodeEq eq) {
    String name; 
    name = "  ";
    Iterator<Arg> it = eq.getNames().iterator();
    while (it.hasNext()) {
      name += it.next().getName();
      if (it.hasNext()) {
        name += ", ";
      }
    }
    name += " = ";
    KindExpr bodyExpr = doSwitch(eq.getExpr());
    
    return name + bodyExpr.toString() + ";\n";
  }

  // TODO: ordering nodes/functions in dependency order
  // TODO: why is this returning KindExpr?  What does this mean?
  @Override
  public KindExpr caseNodeDefExpr(NodeDefExpr expr) {

    System.out.println("Visiting caseNodeDefExpr");
    
    String name = "node ";
    name += jKindNameTag + expr.getName();
    name += printArgList(expr.getArgs()) ; 
    name += "\n   returns ";
    name += printArgList(expr.getRets()) ; 
    name += ";\n";

    NodeBodyExpr body = expr.getNodeBody();
    if (!body.getLocs().isEmpty()) {
      name += "var \n";
      for (Arg arg: body.getLocs()) {
        name += "  " + printArg(arg) + "; \n";
      }
    }
    name += "let \n";
    for (NodeEq eq: body.getEqs()) {
      name += printNodeEq(eq) ; 
    }
    
    name += "tel;\n\n";
    
    KindExpr kindExpr = new KindExpr(name);
    nodeDefExpressions.add(kindExpr);
    
    return kindExpr;
    // String body = "let\n_FNOUTVAR = ";
  }

  @Override
  public KindExpr caseFnDefExpr(FnDefExpr expr) {

    String name = "node ";
    name += jKindNameTag + expr.getName();
    name += " (";
    for (int i = 0; i < expr.getArgs().size(); i++) {
      Arg arg = expr.getArgs().get(i);
      name += arg.getName() + " : " + arg.getType().getName();

      if (i != expr.getArgs().size() - 1) {
        name += ";\n";
      }

    }
    name += ") returns (_FNOUTVAR : ";
    name += expr.getType().getName() + ");\n";

    String body = "let\n_FNOUTVAR = ";

    KindExpr bodyExpr = doSwitch(expr.getExpr());

    bodyExpr.appendLeft(name + body);
    bodyExpr.appendRight(";\ntel;");

    funDefExpressions.add(bodyExpr);

    return bodyExpr;
  }

  // TODO: place node definition here.

  @Override
  public KindExpr caseGetPropertyExpr(GetPropertyExpr expr) {
    KindExpr compExpr = doSwitch(expr.getComponent());
    KindExpr propExpr = doSwitch(expr.getName());

    assert (propExpr.getNamedEl() instanceof Property);
    assert (compExpr.getNamedEl() instanceof Subcomponent);

    Property prop = (Property) propExpr.getNamedEl();
    Subcomponent subComp = (Subcomponent) compExpr.getNamedEl();

    PropertyExpression propVal = getPropExpression(subComp, prop);

    if (propVal == null) {
      ComponentImplementation comp = subComp.getComponentImplementation();
      propVal = getPropExpression(comp, prop);
    }

    assert (propVal != null);
    KindExpr kExpr = null;
    if (propVal != null) {
      if (propVal instanceof StringLiteral) {
        StringLiteral value = (StringLiteral) propVal;
        // nodeStr += value.getValue() + ")";
        kExpr = new KindExpr(value.getValue());
      } else if (propVal instanceof NamedValue) {
        NamedValue namedVal = (NamedValue) propVal;
        AbstractNamedValue absVal = namedVal.getNamedValue();
        assert (absVal instanceof EnumerationLiteral);
        EnumerationLiteral enVal = (EnumerationLiteral) absVal;
        kExpr = new KindExpr(enVal.getName());
      } else if (propVal instanceof BooleanLiteral) {
        BooleanLiteral value = (BooleanLiteral) propVal;
        kExpr = new KindExpr(Boolean.toString(value.getValue()));
      } else if (propVal instanceof IntegerLiteral) {
        IntegerLiteral value = (IntegerLiteral) propVal;
        kExpr = new KindExpr(Long.toString((long) value.getScaledValue()));
      } else {
        assert (propVal instanceof RealLiteral);
        RealLiteral value = (RealLiteral) propVal;
        kExpr = new KindExpr(Double.toString(value.getValue()));
      }
    }
    assert (kExpr != null);

    return kExpr;
  }

  @Override
  public KindExpr caseIdExpr(IdExpr expr) {
    // just make an expression of the NamedElement
    return new KindExpr(expr.getId());
  }

  @Override
  public KindExpr caseThisExpr(ThisExpr expr) {

    return new KindExpr(curComp);
  }

  @Override
  public KindExpr caseIfThenElseExpr(IfThenElseExpr expr) {
    KindExpr condExpr = doSwitch(expr.getA());
    KindExpr thenExpr = doSwitch(expr.getB());
    KindExpr elseExpr = doSwitch(expr.getC());

    KindExpr result = condExpr.appendLeft("if (");
    result.appendRight(") then (");
    result.appendRight(thenExpr);
    result.appendRight(") else (");
    result.appendRight(elseExpr);
    result.appendRight(")");

    return result;
  }

  @Override
  public KindExpr caseIntLitExpr(IntLitExpr expr) {
    return new KindExpr(expr.getVal());
  }

  @Override
  public KindExpr caseNestedDotID(NestedDotID Id) {

    NestedDotID orgId = Id;
    String jKindVar = "";
    String aadlVar = "";
    while (Id.getSubName() != null) {
      jKindVar += Id.getName().getName() + dotChar;
      aadlVar += Id.getName().getName() + ".";
      Id = Id.getSubName();
    }

    KindExpr result = null;
    NamedElement namedEl = Id.getName();

    result = new KindExpr(namedEl);
    result.appendLeft(jKindVar);

    // tags are appended to the variables to
    // make sure that scope is properly maintained
    // in the generated lustre
    if (!(namedEl instanceof Arg)) {
      result.appendLeft(jKindNameTag);

      // TODO: this code is for the case when there is some sort of
      // "floating" port on a component. I.e., a port that is not
      // transatively connected to a feature on the top level system
      // and is not connected on one side to another internal component
      // perhaps we should throw an error here rather than creating
      // a new random input?

      // hack for making sure all inputs have been created
      if (namedEl instanceof DataSubcomponent) {
        String tempStr = result.toString();
        VarDecl tempStrType = new VarDecl();
        tempStrType.jKindStr = tempStr;

        if (!inputVars.contains(tempStrType)
            && !internalVars.contains(tempStrType)) {

          log.logWarning("In component '"
              + orgId.getName().getContainingClassifier().getFullName()
              + "', Port '" + tempStr
              + "' is not connected to anything. Considering it to be"
              + " an unconstrained primary input.");

          // throw new AgreeException("In component '"+
          // orgId.getName().getContainingClassifier().getFullName()+"', Port '"+
          // tempStr+"' is not connected to anything.");

          // NamedElement origEl = orgId.getName();
          // if(origEl instanceof DataPort){
          // DataPort port = (DataPort)origEl;
          // if(port.getDirection().incoming())
          // throw new
          // AgreeException("Port '"+tempStr+"' is not connected to anything.");
          // }
          // this code just creates a new PI
          tempStrType = dataTypeToVarType((DataSubcomponent) namedEl);
          jKindVar += jKindNameTag + Id.getName().getName();
          aadlVar += aadlNameTag + Id.getName().getName();

          tempStrType.jKindStr = tempStr;
          tempStrType.aadlStr = aadlVar;

          assert (curComp != null);
          compToKindVars.get(curComp).add(tempStr);
          varRenaming.put(jKindVar, aadlVar);
          refMap.put(aadlVar, Id);
          inputVars.add(tempStrType);

        }

      }

    }

    return result;
  }

  @Override
  public KindExpr caseNestIdExpr(NestIdExpr expr) {
    return doSwitch(expr.getId());
  }

  @Override
  public KindExpr casePreExpr(PreExpr expr) {
    KindExpr kExpr = doSwitch(expr.getExpr());

    return kExpr.appendLeft("pre(").appendRight(")");
  }

  @Override
  public KindExpr casePrevExpr(PrevExpr expr) {
    KindExpr delayExpr = doSwitch(expr.getDelay());
    KindExpr initExpr = doSwitch(expr.getInit());

    KindExpr resultExpr = initExpr.appendRight(" -> pre(")
        .appendRight(delayExpr).appendRight(")");

    return resultExpr.appendLeft("(").appendRight(")");
  }

  @Override
  public KindExpr caseRealLitExpr(RealLitExpr expr) {
    return new KindExpr(expr.getVal());
  }

  @Override
  public KindExpr caseUnaryExpr(UnaryExpr expr) {
    KindExpr kExpr = doSwitch(expr.getExpr());

    return kExpr.appendLeft("(").appendRight(")").appendLeft(expr.getOp());
  }

  // *********** Utility Methods *****************

  private PropertyExpression getPropExpression(NamedElement comp, Property prop) {
    try {
      comp.getPropertyValue(prop); // this just checks to see if the
      // property is associated
      PropertyExpression expr = PropertyUtils
          .getSimplePropertyValue(comp, prop);
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

  private void makeContracts(ComponentImplementation compImpl) {
    ComponentType ct = compImpl.getType();

    for (Subcomponent sub : compImpl.getAllSubcomponents()) {
      Set<Subcomponent> outputClosure = new HashSet<Subcomponent>();
      outputClosure.add(sub);
      getOutputClosure(compImpl.getAllConnections(), outputClosure);
      closureMap.put(sub, outputClosure);
    }

    // curComp = sysImpl;

    // add all of the facts
    assumpExpressions = new ArrayList<KindExpr>();
    guarExpressions = new ArrayList<KindExpr>();
    assertExpressions = new ArrayList<KindExpr>();
    propExpressions = new ArrayList<KindExpr>();
    eqExpressions = new ArrayList<KindExpr>();
    constExpressions = new ArrayList<KindExpr>();
    funDefExpressions = new ArrayList<KindExpr>();
    nodeDefExpressions = new ArrayList<KindExpr>();

    // set the tag
    jKindNameTag = "";
    aadlNameTag = "";

    for (AnnexSubclause annex : compImpl.getAllAnnexSubclauses()) {
      if (annex instanceof AgreeContractSubclause) {
        doSwitch(annex);
      }
    }

    for (AnnexSubclause annex : ct.getAllAnnexSubclauses()) {
      if (annex instanceof AgreeContractSubclause) {
        doSwitch(annex);
      }
    }

    sysContr = new ComponentContract(ct.getName(), assumpExpressions,
        guarExpressions, assertExpressions, propExpressions, eqExpressions,
        constExpressions, funDefExpressions, nodeDefExpressions);

    for (Subcomponent subComp : compImpl.getAllSubcomponents()) {

      if (!(subComp instanceof SystemSubcomponent))
        continue;

      subComps.add(subComp);
      curComp = subComp;

      assumpExpressions = new ArrayList<KindExpr>();
      guarExpressions = new ArrayList<KindExpr>();
      assertExpressions = new ArrayList<KindExpr>();
      propExpressions = new ArrayList<KindExpr>();
      eqExpressions = new ArrayList<KindExpr>();
      constExpressions = new ArrayList<KindExpr>();
      funDefExpressions = new ArrayList<KindExpr>();
      nodeDefExpressions = new ArrayList<KindExpr>();

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

      subContrs.put(subComp, new ComponentContract(subComp.getName(),
          assumpExpressions, guarExpressions, assertExpressions,
          propExpressions, eqExpressions, constExpressions, funDefExpressions, nodeDefExpressions));
    }

  }

  // fills the connExpressions lists with expressions
  // that equate variables that are connected to one another
  private void setVarEquivs(ComponentImplementation compImpl) {

    // use for checking delay
    Property commTimingProp = EMFIndexRetrieval
        .getPropertyDefinitionInWorkspace(OsateResourceUtil.getResourceSet(),
            "Communication_Properties::Timing");

    for (Connection conn : compImpl.getAllConnections()) {

      AbstractConnectionEnd absConnDest = conn.getDestination();
      AbstractConnectionEnd absConnSour = conn.getSource();
      assert (absConnDest instanceof ConnectedElement);
      assert (absConnSour instanceof ConnectedElement);

      boolean delayed = true;
      EnumerationLiteral lit = PropertyUtils.getEnumLiteral(conn,
          commTimingProp);

      if (lit.getName().equals("immediate"))
        delayed = false;

      ConnectionEnd destConn = ((ConnectedElement) absConnDest)
          .getConnectionEnd();
      ConnectionEnd sourConn = ((ConnectedElement) absConnSour)
          .getConnectionEnd();
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

      if (!(dataSub instanceof DataImplementation))
        continue;

      Set<VarDecl> tempSet = new HashSet<VarDecl>();
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

      for (VarDecl varType : tempSet) {
        String newDestStr = destStr + dotChar + varType.jKindStr;
        String newSourStr = sourStr + dotChar + varType.jKindStr;
        String newAADLDestStr = aadlDestStr + "." + varType.aadlStr;
        String newAADLSourStr = aadlSourStr + "." + varType.aadlStr;

        // make an internal var for this
        varType.jKindStr = newDestStr;
        varType.aadlStr = newAADLDestStr;

        if (destContext != null) {
          compToKindVars.get(destContext).add(varType.jKindStr);
        }

        refMap.put(varType.aadlStr, destConn);
        varRenaming.put(varType.jKindStr, varType.aadlStr);
        internalVars.add(varType);

        // if the source context is not null, then this is a variable
        // that was not in the top level system features. Therefore
        // a new input variable must be created
        if (sourContext != null) {
          VarDecl inputVar = new VarDecl();
          inputVar.type = varType.type;
          inputVar.jKindStr = newSourStr;
          inputVar.aadlStr = newAADLSourStr;

          compToKindVars.get(sourContext).add(inputVar.jKindStr);
          varRenaming.put(inputVar.jKindStr, inputVar.aadlStr);
          refMap.put(inputVar.aadlStr, sourConn);
          inputVars.add(inputVar);
        }

        newDestStr += " = (";
        if (sourContext != null && destContext != null && delayed) {
          // this is not an input, and the output is not a terminal
          switch (varType.type) {
          case "bool":
            newDestStr += "true";
            break;
          case "int":
            newDestStr += "0";
            break;
          case "real":
            newDestStr += "0.0";
            break;
          }
          newDestStr += " -> pre(" + newSourStr + "));";
        } else {
          newDestStr += newSourStr + ");";
        }

        KindExpr kExpr = new KindExpr(newDestStr);
        connExpressions.add(kExpr);
      }

    }
  }

  private void setLustreVars(ComponentType ct) {

    Set<String> varList = compToKindVars.get(topCompImpl);
    assert (varList != null);

    for (Feature feat : ct.getAllFeatures()) {
      if (feat instanceof DataPort) {
        DataPort port = (DataPort) feat;
        DataSubcomponentType dataSub = port.getDataFeatureClassifier();
        if (dataSub instanceof DataImplementation) {
          Set<VarDecl> tempSet = new HashSet<VarDecl>();
          getAllDataNames((DataImplementation) dataSub, tempSet);
          for (VarDecl var : tempSet) {
            var.jKindStr = feat.getName() + dotChar + var.jKindStr;
            var.aadlStr = feat.getName() + "." + var.jKindStr;

          }
          if (port.getDirection().incoming()) {
            for (VarDecl var : tempSet) {
              varRenaming.put(var.jKindStr, var.aadlStr);
              refMap.put(var.aadlStr, port);
              varList.add(var.jKindStr);
              inputVars.add(var);
            }
          }
        }
      }
    }
  }

  private void getAllDataNames(DataImplementation dataImpl,
      Set<VarDecl> subStrTypes) {

    for (Subcomponent sub : dataImpl.getAllSubcomponents()) {
      if (sub instanceof DataSubcomponent) {
        Set<VarDecl> newStrTypes = new HashSet<VarDecl>();
        ComponentClassifier subImpl = sub.getAllClassifier();
        if (subImpl instanceof DataImplementation) {
          getAllDataNames((DataImplementation) subImpl, newStrTypes);
          for (VarDecl strType : newStrTypes) {
            VarDecl newStrType = new VarDecl();
            newStrType.jKindStr = sub.getName() + dotChar + strType.jKindStr;
            newStrType.aadlStr = sub.getName() + "." + strType.aadlStr;
            newStrType.type = strType.type;
            subStrTypes.add(newStrType);
          }
        } else {
          assert (subImpl instanceof DataType);
          VarDecl newStrType = dataTypeToVarType((DataSubcomponent) sub);
          if (newStrType.type != null)
            subStrTypes.add(newStrType);
        }
      }
    }
  }

  private VarDecl dataTypeToVarType(DataSubcomponent sub) {

    DataType type = (DataType) sub.getAllClassifier();
    VarDecl newStrType = new VarDecl();
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

  private String getLustreHistory(String lustre, String name) {

    name = name + " = (" + lustre + " -> (" + lustre + " and pre(" + name
        + ")))";

    return name;

  }

  private String getLustreAssumptions(ComponentContract contract) {

    String assumStr = "";
    for (KindExpr kExpr : contract.assumps) {
      assumStr += "(" + kExpr.toString() + ") and ";
    }

    assumStr += "true";

    return "(" + assumStr + ")";
  }

  private String getLustreAssumptionsAndAssertions(ComponentContract contract) {

    String assumStr = "";
    for (KindExpr kExpr : contract.assumps) {
      assumStr += "(" + kExpr.toString() + ") and ";
    }

    assumStr += "true";

    String asserStr = "";
    for (KindExpr kExpr : contract.asserts) {
      asserStr += "(" + kExpr.toString() + ") and ";
    }

    asserStr += "true";

    return "(" + assumStr + ") and (" + asserStr + ")";
  }

  private String getLustreContract(ComponentContract contract) {

    String assumStr = "";
    for (KindExpr kExpr : contract.assumps) {
      assumStr += "(" + kExpr.toString() + ") and ";
    }

    assumStr += "true";

    String guarStr = "";
    for (KindExpr kExpr : contract.guars) {
      guarStr += "(" + kExpr.toString() + ") and ";
    }

    guarStr += "true";

    String asserStr = "";
    for (KindExpr kExpr : contract.asserts) {
      asserStr += "(" + kExpr.toString() + ") and ";
    }

    asserStr += "true";

    return "(" + assumStr + ") and (" + asserStr + ") and (" + guarStr + ")";
  }

  private String getLustreGuarantee(ComponentContract contract) {

    String guarStr = "";
    for (KindExpr kExpr : contract.guars) {
      guarStr += "(" + kExpr.toString() + ") and ";
    }

    guarStr += "true";

    return "(" + guarStr + ")";
  }

  private String getLustre() {

    // first print out the functions which will be
    // other nodes

    String output = "";
    output += "-- functions and nodes defined in top level components\n";
    for (KindExpr kExpr : sysContr.funcs)
      output += kExpr.toString() + "\n\n";
    
    for (KindExpr kExpr : sysContr.nodes) 
      output += kExpr.toString() + "\n\n";

    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      output += "-- functions and nodes defined in subcomponent: " + contract.compName
          + "\n";
      for (KindExpr kExpr : contract.funcs)
        output += kExpr.toString() + "\n\n";
      
      for (KindExpr kExpr : contract.nodes)
        output += kExpr.toString() + "\n\n";

    }

    // now create the main node and print input variables
    output += "node top(\n";

    // alphabetical ordering can make it easier to read...
    List<VarDecl> tempList = new ArrayList<VarDecl>();
    tempList.addAll(inputVars);
    Collections.sort(tempList);

    for (VarDecl varStr : tempList) {
      output += varStr.jKindStr + " : " + varStr.type + ";\n";
    }

    if (tempList.size() > 1) {
      // get rid of the last semi colon
      output = output.substring(0, output.length() - 2);
    }
    output += ") returns ();\n";

    // print out the intermediate vars

    output += "var\n";

    tempList.clear();
    tempList.addAll(internalVars);
    Collections.sort(tempList);
    for (VarDecl varStr : tempList) {
      output += varStr.jKindStr + " : " + varStr.type + ";\n";
    }

    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      output += "_Hist_" + contract.compName + " : bool;\n";
    }

    // print other special vars
    output += "_TOTAL_COMP_HIST : bool;\n";
    output += "_SYSTEM_ASSUMP_HIST : bool;\n";
    output += "_SYSTEM_GUARANTEES : bool;\n";

    // print out all the let stuff

    output += "let\n";

    output += "-- Connection equavalence expressions\n";
    for (KindExpr kExpr : connExpressions) {
      output += kExpr.toString() + "\n";
    }

    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      output += "-- Property, Constant, and Eq statements for '"
          + contract.compName + "'\n";
      for (KindExpr kExpr : contract.props)
        output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";
      for (KindExpr kExpr : contract.consts)
        output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";
      for (KindExpr kExpr : contract.eqs)
        output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";
    }

    output += "-- Property, Constant, and Eq statements for '"
        + sysContr.compName + "'\n";
    for (KindExpr kExpr : sysContr.props)
      output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";
    for (KindExpr kExpr : sysContr.consts)
      output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";
    for (KindExpr kExpr : sysContr.eqs)
      output += kExpr.getVarName() + " = " + kExpr.toString() + ";\n";

    // get total component history
    String totalCompHist = "true";
    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      totalCompHist += " and " + getLustreContract(contract);
    }

    totalCompHist = getLustreHistory(totalCompHist, "_TOTAL_COMP_HIST");
    output += totalCompHist + ";\n";

    // get system assumption history

    String sysAssumpHist = getLustreAssumptionsAndAssertions(sysContr);
    sysAssumpHist = getLustreHistory(sysAssumpHist, "_SYSTEM_ASSUMP_HIST");
    output += sysAssumpHist + ";\n";

    // get the individual history variables
    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      Subcomponent comp = entry.getKey();

      String higherContracts = "true";

      for (Subcomponent otherComp : closureMap.get(comp)) {
        higherContracts += " and "
            + getLustreContract(subContrs.get(otherComp));
      }

      higherContracts = "(" + higherContracts + ")";

      String contrAssumps = getLustreAssumptions(contract);

      output += "_Hist_" + contract.compName + " = ";
      output += "(_SYSTEM_ASSUMP_HIST and (true -> pre(_TOTAL_COMP_HIST)) and ";
      output += higherContracts + ") => (" + contrAssumps + ");\n";
    }

    // finally print he system level guarantees
    output += "_SYSTEM_GUARANTEES = (_SYSTEM_ASSUMP_HIST and _TOTAL_COMP_HIST) => ";

    String sysGuar = getLustreGuarantee(sysContr);

    output += sysGuar + ";\n";

    // print out the property statements

    for (Entry<Subcomponent, ComponentContract> entry : subContrs.entrySet()) {
      ComponentContract contract = entry.getValue();
      output += "--%PROPERTY _Hist_" + contract.compName + ";\n";
    }
    output += "--%PROPERTY _SYSTEM_GUARANTEES;\n";

    output += "tel;\n";

    return output;
  }

  public ComponentImplementation getComponentImplementation() {
    return topCompImpl;
  }

  public Set<Subcomponent> getSubComponents() {
    return subComps;
  }

  public Map<NamedElement, Set<String>> getCompToKVarMap() {
    return compToKindVars;
  }

}
