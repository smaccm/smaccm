/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AgreeFactoryImpl extends EFactoryImpl implements AgreeFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AgreeFactory init()
  {
    try
    {
      AgreeFactory theAgreeFactory = (AgreeFactory)EPackage.Registry.INSTANCE.getEFactory(AgreePackage.eNS_URI);
      if (theAgreeFactory != null)
      {
        return theAgreeFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new AgreeFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case AgreePackage.AGREE_LIBRARY: return createAgreeLibrary();
      case AgreePackage.AGREE_SUBCLAUSE: return createAgreeSubclause();
      case AgreePackage.CONTRACT: return createContract();
      case AgreePackage.SPEC_STATEMENT: return createSpecStatement();
      case AgreePackage.NAMED_SPEC_STATEMENT: return createNamedSpecStatement();
      case AgreePackage.PATTERN_STATEMENT: return createPatternStatement();
      case AgreePackage.WHEN_STATEMENT: return createWhenStatement();
      case AgreePackage.WHENEVER_STATEMENT: return createWheneverStatement();
      case AgreePackage.REAL_TIME_STATEMENT: return createRealTimeStatement();
      case AgreePackage.TIME_INTERVAL: return createTimeInterval();
      case AgreePackage.SYNCH_STATEMENT: return createSynchStatement();
      case AgreePackage.ORDER_STATEMENT: return createOrderStatement();
      case AgreePackage.ABSTRACTION: return createAbstraction();
      case AgreePackage.PROPERTY_STATEMENT: return createPropertyStatement();
      case AgreePackage.CONST_STATEMENT: return createConstStatement();
      case AgreePackage.EQ_STATEMENT: return createEqStatement();
      case AgreePackage.INPUT_STATEMENT: return createInputStatement();
      case AgreePackage.ASSIGN_STATEMENT: return createAssignStatement();
      case AgreePackage.FN_DEF: return createFnDef();
      case AgreePackage.LIBRARY_FN_DEF: return createLibraryFnDef();
      case AgreePackage.LINEARIZATION_DEF: return createLinearizationDef();
      case AgreePackage.LINEARIZATION_INTERVAL: return createLinearizationInterval();
      case AgreePackage.NODE_DEF: return createNodeDef();
      case AgreePackage.NODE_BODY_EXPR: return createNodeBodyExpr();
      case AgreePackage.NODE_STMT: return createNodeStmt();
      case AgreePackage.ARG: return createArg();
      case AgreePackage.TYPE: return createType();
      case AgreePackage.RECORD_DEF: return createRecordDef();
      case AgreePackage.ENUM_STATEMENT: return createEnumStatement();
      case AgreePackage.EXPR: return createExpr();
      case AgreePackage.COMPONENT_REF: return createComponentRef();
      case AgreePackage.ARRAY_LITERAL_EXPR: return createArrayLiteralExpr();
      case AgreePackage.DOUBLE_DOT_REF: return createDoubleDotRef();
      case AgreePackage.NAMED_ID: return createNamedID();
      case AgreePackage.ARRAY_SUB_BINDING: return createArraySubBinding();
      case AgreePackage.ACC: return createAcc();
      case AgreePackage.AGREE_CONTRACT_LIBRARY: return createAgreeContractLibrary();
      case AgreePackage.AGREE_CONTRACT_SUBCLAUSE: return createAgreeContractSubclause();
      case AgreePackage.AGREE_CONTRACT: return createAgreeContract();
      case AgreePackage.INITIAL_STATEMENT: return createInitialStatement();
      case AgreePackage.PARAM_STATEMENT: return createParamStatement();
      case AgreePackage.LIFT_STATEMENT: return createLiftStatement();
      case AgreePackage.CONNECTION_STATEMENT: return createConnectionStatement();
      case AgreePackage.ASSUME_STATEMENT: return createAssumeStatement();
      case AgreePackage.GUARANTEE_STATEMENT: return createGuaranteeStatement();
      case AgreePackage.ASSERT_STATEMENT: return createAssertStatement();
      case AgreePackage.LEMMA_STATEMENT: return createLemmaStatement();
      case AgreePackage.ALWAYS_STATEMENT: return createAlwaysStatement();
      case AgreePackage.WHEN_HOLDS_STATEMENT: return createWhenHoldsStatement();
      case AgreePackage.WHEN_OCCURS_STATMENT: return createWhenOccursStatment();
      case AgreePackage.WHENEVER_OCCURS_STATEMENT: return createWheneverOccursStatement();
      case AgreePackage.WHENEVER_BECOMES_TRUE_STATEMENT: return createWheneverBecomesTrueStatement();
      case AgreePackage.WHENEVER_HOLDS_STATEMENT: return createWheneverHoldsStatement();
      case AgreePackage.WHENEVER_IMPLIES_STATEMENT: return createWheneverImpliesStatement();
      case AgreePackage.PERIODIC_STATEMENT: return createPeriodicStatement();
      case AgreePackage.SPORADIC_STATEMENT: return createSporadicStatement();
      case AgreePackage.CLOSED_TIME_INTERVAL: return createClosedTimeInterval();
      case AgreePackage.OPEN_LEFT_TIME_INTERVAL: return createOpenLeftTimeInterval();
      case AgreePackage.OPEN_RIGHT_TIME_INTERVAL: return createOpenRightTimeInterval();
      case AgreePackage.OPEN_TIME_INTERVAL: return createOpenTimeInterval();
      case AgreePackage.MN_SYNCH_STATEMENT: return createMNSynchStatement();
      case AgreePackage.CALEN_STATEMENT: return createCalenStatement();
      case AgreePackage.ASYNCH_STATEMENT: return createAsynchStatement();
      case AgreePackage.LATCHED_STATEMENT: return createLatchedStatement();
      case AgreePackage.NODE_EQ: return createNodeEq();
      case AgreePackage.NODE_LEMMA: return createNodeLemma();
      case AgreePackage.ARRAY_TYPE: return createArrayType();
      case AgreePackage.PRIM_TYPE: return createPrimType();
      case AgreePackage.FORALL_EXPR: return createForallExpr();
      case AgreePackage.EXISTS_EXPR: return createExistsExpr();
      case AgreePackage.FOREACH_EXPR: return createForeachExpr();
      case AgreePackage.FOLD_LEFT_EXPR: return createFoldLeftExpr();
      case AgreePackage.FOLD_RIGHT_EXPR: return createFoldRightExpr();
      case AgreePackage.BINARY_EXPR: return createBinaryExpr();
      case AgreePackage.UNARY_EXPR: return createUnaryExpr();
      case AgreePackage.IF_THEN_ELSE_EXPR: return createIfThenElseExpr();
      case AgreePackage.THIS_REF: return createThisRef();
      case AgreePackage.PREV_EXPR: return createPrevExpr();
      case AgreePackage.GET_PROPERTY_EXPR: return createGetPropertyExpr();
      case AgreePackage.ARRAY_UPDATE_EXPR: return createArrayUpdateExpr();
      case AgreePackage.RECORD_UPDATE_EXPR: return createRecordUpdateExpr();
      case AgreePackage.ARRAY_SUB_EXPR: return createArraySubExpr();
      case AgreePackage.TAG_EXPR: return createTagExpr();
      case AgreePackage.PROJECTION_EXPR: return createProjectionExpr();
      case AgreePackage.NAMED_ELM_EXPR: return createNamedElmExpr();
      case AgreePackage.TIME_EXPR: return createTimeExpr();
      case AgreePackage.INDICES_EXPR: return createIndicesExpr();
      case AgreePackage.CALL_EXPR: return createCallExpr();
      case AgreePackage.RECORD_LIT_EXPR: return createRecordLitExpr();
      case AgreePackage.ENUM_LIT_EXPR: return createEnumLitExpr();
      case AgreePackage.INT_LIT_EXPR: return createIntLitExpr();
      case AgreePackage.PRE_EXPR: return createPreExpr();
      case AgreePackage.EVENT_EXPR: return createEventExpr();
      case AgreePackage.LATCHED_EXPR: return createLatchedExpr();
      case AgreePackage.TIME_OF_EXPR: return createTimeOfExpr();
      case AgreePackage.TIME_RISE_EXPR: return createTimeRiseExpr();
      case AgreePackage.TIME_FALL_EXPR: return createTimeFallExpr();
      case AgreePackage.REAL_LIT_EXPR: return createRealLitExpr();
      case AgreePackage.BOOL_LIT_EXPR: return createBoolLitExpr();
      case AgreePackage.FLOOR_CAST: return createFloorCast();
      case AgreePackage.REAL_CAST: return createRealCast();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeLibrary createAgreeLibrary()
  {
    AgreeLibraryImpl agreeLibrary = new AgreeLibraryImpl();
    return agreeLibrary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeSubclause createAgreeSubclause()
  {
    AgreeSubclauseImpl agreeSubclause = new AgreeSubclauseImpl();
    return agreeSubclause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Contract createContract()
  {
    ContractImpl contract = new ContractImpl();
    return contract;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SpecStatement createSpecStatement()
  {
    SpecStatementImpl specStatement = new SpecStatementImpl();
    return specStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedSpecStatement createNamedSpecStatement()
  {
    NamedSpecStatementImpl namedSpecStatement = new NamedSpecStatementImpl();
    return namedSpecStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PatternStatement createPatternStatement()
  {
    PatternStatementImpl patternStatement = new PatternStatementImpl();
    return patternStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhenStatement createWhenStatement()
  {
    WhenStatementImpl whenStatement = new WhenStatementImpl();
    return whenStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WheneverStatement createWheneverStatement()
  {
    WheneverStatementImpl wheneverStatement = new WheneverStatementImpl();
    return wheneverStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealTimeStatement createRealTimeStatement()
  {
    RealTimeStatementImpl realTimeStatement = new RealTimeStatementImpl();
    return realTimeStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeInterval createTimeInterval()
  {
    TimeIntervalImpl timeInterval = new TimeIntervalImpl();
    return timeInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SynchStatement createSynchStatement()
  {
    SynchStatementImpl synchStatement = new SynchStatementImpl();
    return synchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderStatement createOrderStatement()
  {
    OrderStatementImpl orderStatement = new OrderStatementImpl();
    return orderStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Abstraction createAbstraction()
  {
    AbstractionImpl abstraction = new AbstractionImpl();
    return abstraction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyStatement createPropertyStatement()
  {
    PropertyStatementImpl propertyStatement = new PropertyStatementImpl();
    return propertyStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstStatement createConstStatement()
  {
    ConstStatementImpl constStatement = new ConstStatementImpl();
    return constStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EqStatement createEqStatement()
  {
    EqStatementImpl eqStatement = new EqStatementImpl();
    return eqStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InputStatement createInputStatement()
  {
    InputStatementImpl inputStatement = new InputStatementImpl();
    return inputStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssignStatement createAssignStatement()
  {
    AssignStatementImpl assignStatement = new AssignStatementImpl();
    return assignStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FnDef createFnDef()
  {
    FnDefImpl fnDef = new FnDefImpl();
    return fnDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryFnDef createLibraryFnDef()
  {
    LibraryFnDefImpl libraryFnDef = new LibraryFnDefImpl();
    return libraryFnDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinearizationDef createLinearizationDef()
  {
    LinearizationDefImpl linearizationDef = new LinearizationDefImpl();
    return linearizationDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinearizationInterval createLinearizationInterval()
  {
    LinearizationIntervalImpl linearizationInterval = new LinearizationIntervalImpl();
    return linearizationInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeDef createNodeDef()
  {
    NodeDefImpl nodeDef = new NodeDefImpl();
    return nodeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeBodyExpr createNodeBodyExpr()
  {
    NodeBodyExprImpl nodeBodyExpr = new NodeBodyExprImpl();
    return nodeBodyExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeStmt createNodeStmt()
  {
    NodeStmtImpl nodeStmt = new NodeStmtImpl();
    return nodeStmt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Arg createArg()
  {
    ArgImpl arg = new ArgImpl();
    return arg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type createType()
  {
    TypeImpl type = new TypeImpl();
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RecordDef createRecordDef()
  {
    RecordDefImpl recordDef = new RecordDefImpl();
    return recordDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumStatement createEnumStatement()
  {
    EnumStatementImpl enumStatement = new EnumStatementImpl();
    return enumStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr createExpr()
  {
    ExprImpl expr = new ExprImpl();
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentRef createComponentRef()
  {
    ComponentRefImpl componentRef = new ComponentRefImpl();
    return componentRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayLiteralExpr createArrayLiteralExpr()
  {
    ArrayLiteralExprImpl arrayLiteralExpr = new ArrayLiteralExprImpl();
    return arrayLiteralExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoubleDotRef createDoubleDotRef()
  {
    DoubleDotRefImpl doubleDotRef = new DoubleDotRefImpl();
    return doubleDotRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedID createNamedID()
  {
    NamedIDImpl namedID = new NamedIDImpl();
    return namedID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArraySubBinding createArraySubBinding()
  {
    ArraySubBindingImpl arraySubBinding = new ArraySubBindingImpl();
    return arraySubBinding;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Acc createAcc()
  {
    AccImpl acc = new AccImpl();
    return acc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeContractLibrary createAgreeContractLibrary()
  {
    AgreeContractLibraryImpl agreeContractLibrary = new AgreeContractLibraryImpl();
    return agreeContractLibrary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeContractSubclause createAgreeContractSubclause()
  {
    AgreeContractSubclauseImpl agreeContractSubclause = new AgreeContractSubclauseImpl();
    return agreeContractSubclause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeContract createAgreeContract()
  {
    AgreeContractImpl agreeContract = new AgreeContractImpl();
    return agreeContract;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InitialStatement createInitialStatement()
  {
    InitialStatementImpl initialStatement = new InitialStatementImpl();
    return initialStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParamStatement createParamStatement()
  {
    ParamStatementImpl paramStatement = new ParamStatementImpl();
    return paramStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiftStatement createLiftStatement()
  {
    LiftStatementImpl liftStatement = new LiftStatementImpl();
    return liftStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConnectionStatement createConnectionStatement()
  {
    ConnectionStatementImpl connectionStatement = new ConnectionStatementImpl();
    return connectionStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssumeStatement createAssumeStatement()
  {
    AssumeStatementImpl assumeStatement = new AssumeStatementImpl();
    return assumeStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GuaranteeStatement createGuaranteeStatement()
  {
    GuaranteeStatementImpl guaranteeStatement = new GuaranteeStatementImpl();
    return guaranteeStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssertStatement createAssertStatement()
  {
    AssertStatementImpl assertStatement = new AssertStatementImpl();
    return assertStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LemmaStatement createLemmaStatement()
  {
    LemmaStatementImpl lemmaStatement = new LemmaStatementImpl();
    return lemmaStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlwaysStatement createAlwaysStatement()
  {
    AlwaysStatementImpl alwaysStatement = new AlwaysStatementImpl();
    return alwaysStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhenHoldsStatement createWhenHoldsStatement()
  {
    WhenHoldsStatementImpl whenHoldsStatement = new WhenHoldsStatementImpl();
    return whenHoldsStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhenOccursStatment createWhenOccursStatment()
  {
    WhenOccursStatmentImpl whenOccursStatment = new WhenOccursStatmentImpl();
    return whenOccursStatment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WheneverOccursStatement createWheneverOccursStatement()
  {
    WheneverOccursStatementImpl wheneverOccursStatement = new WheneverOccursStatementImpl();
    return wheneverOccursStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WheneverBecomesTrueStatement createWheneverBecomesTrueStatement()
  {
    WheneverBecomesTrueStatementImpl wheneverBecomesTrueStatement = new WheneverBecomesTrueStatementImpl();
    return wheneverBecomesTrueStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WheneverHoldsStatement createWheneverHoldsStatement()
  {
    WheneverHoldsStatementImpl wheneverHoldsStatement = new WheneverHoldsStatementImpl();
    return wheneverHoldsStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WheneverImpliesStatement createWheneverImpliesStatement()
  {
    WheneverImpliesStatementImpl wheneverImpliesStatement = new WheneverImpliesStatementImpl();
    return wheneverImpliesStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PeriodicStatement createPeriodicStatement()
  {
    PeriodicStatementImpl periodicStatement = new PeriodicStatementImpl();
    return periodicStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SporadicStatement createSporadicStatement()
  {
    SporadicStatementImpl sporadicStatement = new SporadicStatementImpl();
    return sporadicStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClosedTimeInterval createClosedTimeInterval()
  {
    ClosedTimeIntervalImpl closedTimeInterval = new ClosedTimeIntervalImpl();
    return closedTimeInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpenLeftTimeInterval createOpenLeftTimeInterval()
  {
    OpenLeftTimeIntervalImpl openLeftTimeInterval = new OpenLeftTimeIntervalImpl();
    return openLeftTimeInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpenRightTimeInterval createOpenRightTimeInterval()
  {
    OpenRightTimeIntervalImpl openRightTimeInterval = new OpenRightTimeIntervalImpl();
    return openRightTimeInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OpenTimeInterval createOpenTimeInterval()
  {
    OpenTimeIntervalImpl openTimeInterval = new OpenTimeIntervalImpl();
    return openTimeInterval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MNSynchStatement createMNSynchStatement()
  {
    MNSynchStatementImpl mnSynchStatement = new MNSynchStatementImpl();
    return mnSynchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalenStatement createCalenStatement()
  {
    CalenStatementImpl calenStatement = new CalenStatementImpl();
    return calenStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AsynchStatement createAsynchStatement()
  {
    AsynchStatementImpl asynchStatement = new AsynchStatementImpl();
    return asynchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LatchedStatement createLatchedStatement()
  {
    LatchedStatementImpl latchedStatement = new LatchedStatementImpl();
    return latchedStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeEq createNodeEq()
  {
    NodeEqImpl nodeEq = new NodeEqImpl();
    return nodeEq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeLemma createNodeLemma()
  {
    NodeLemmaImpl nodeLemma = new NodeLemmaImpl();
    return nodeLemma;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayType createArrayType()
  {
    ArrayTypeImpl arrayType = new ArrayTypeImpl();
    return arrayType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrimType createPrimType()
  {
    PrimTypeImpl primType = new PrimTypeImpl();
    return primType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForallExpr createForallExpr()
  {
    ForallExprImpl forallExpr = new ForallExprImpl();
    return forallExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExistsExpr createExistsExpr()
  {
    ExistsExprImpl existsExpr = new ExistsExprImpl();
    return existsExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForeachExpr createForeachExpr()
  {
    ForeachExprImpl foreachExpr = new ForeachExprImpl();
    return foreachExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FoldLeftExpr createFoldLeftExpr()
  {
    FoldLeftExprImpl foldLeftExpr = new FoldLeftExprImpl();
    return foldLeftExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FoldRightExpr createFoldRightExpr()
  {
    FoldRightExprImpl foldRightExpr = new FoldRightExprImpl();
    return foldRightExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BinaryExpr createBinaryExpr()
  {
    BinaryExprImpl binaryExpr = new BinaryExprImpl();
    return binaryExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnaryExpr createUnaryExpr()
  {
    UnaryExprImpl unaryExpr = new UnaryExprImpl();
    return unaryExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfThenElseExpr createIfThenElseExpr()
  {
    IfThenElseExprImpl ifThenElseExpr = new IfThenElseExprImpl();
    return ifThenElseExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThisRef createThisRef()
  {
    ThisRefImpl thisRef = new ThisRefImpl();
    return thisRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrevExpr createPrevExpr()
  {
    PrevExprImpl prevExpr = new PrevExprImpl();
    return prevExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GetPropertyExpr createGetPropertyExpr()
  {
    GetPropertyExprImpl getPropertyExpr = new GetPropertyExprImpl();
    return getPropertyExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayUpdateExpr createArrayUpdateExpr()
  {
    ArrayUpdateExprImpl arrayUpdateExpr = new ArrayUpdateExprImpl();
    return arrayUpdateExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RecordUpdateExpr createRecordUpdateExpr()
  {
    RecordUpdateExprImpl recordUpdateExpr = new RecordUpdateExprImpl();
    return recordUpdateExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArraySubExpr createArraySubExpr()
  {
    ArraySubExprImpl arraySubExpr = new ArraySubExprImpl();
    return arraySubExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TagExpr createTagExpr()
  {
    TagExprImpl tagExpr = new TagExprImpl();
    return tagExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProjectionExpr createProjectionExpr()
  {
    ProjectionExprImpl projectionExpr = new ProjectionExprImpl();
    return projectionExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElmExpr createNamedElmExpr()
  {
    NamedElmExprImpl namedElmExpr = new NamedElmExprImpl();
    return namedElmExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeExpr createTimeExpr()
  {
    TimeExprImpl timeExpr = new TimeExprImpl();
    return timeExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IndicesExpr createIndicesExpr()
  {
    IndicesExprImpl indicesExpr = new IndicesExprImpl();
    return indicesExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CallExpr createCallExpr()
  {
    CallExprImpl callExpr = new CallExprImpl();
    return callExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RecordLitExpr createRecordLitExpr()
  {
    RecordLitExprImpl recordLitExpr = new RecordLitExprImpl();
    return recordLitExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumLitExpr createEnumLitExpr()
  {
    EnumLitExprImpl enumLitExpr = new EnumLitExprImpl();
    return enumLitExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntLitExpr createIntLitExpr()
  {
    IntLitExprImpl intLitExpr = new IntLitExprImpl();
    return intLitExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreExpr createPreExpr()
  {
    PreExprImpl preExpr = new PreExprImpl();
    return preExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EventExpr createEventExpr()
  {
    EventExprImpl eventExpr = new EventExprImpl();
    return eventExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LatchedExpr createLatchedExpr()
  {
    LatchedExprImpl latchedExpr = new LatchedExprImpl();
    return latchedExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeOfExpr createTimeOfExpr()
  {
    TimeOfExprImpl timeOfExpr = new TimeOfExprImpl();
    return timeOfExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeRiseExpr createTimeRiseExpr()
  {
    TimeRiseExprImpl timeRiseExpr = new TimeRiseExprImpl();
    return timeRiseExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeFallExpr createTimeFallExpr()
  {
    TimeFallExprImpl timeFallExpr = new TimeFallExprImpl();
    return timeFallExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealLitExpr createRealLitExpr()
  {
    RealLitExprImpl realLitExpr = new RealLitExprImpl();
    return realLitExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoolLitExpr createBoolLitExpr()
  {
    BoolLitExprImpl boolLitExpr = new BoolLitExprImpl();
    return boolLitExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FloorCast createFloorCast()
  {
    FloorCastImpl floorCast = new FloorCastImpl();
    return floorCast;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealCast createRealCast()
  {
    RealCastImpl realCast = new RealCastImpl();
    return realCast;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreePackage getAgreePackage()
  {
    return (AgreePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static AgreePackage getPackage()
  {
    return AgreePackage.eINSTANCE;
  }

} //AgreeFactoryImpl
