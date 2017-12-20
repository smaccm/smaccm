/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.rockwellcollins.atc.agree.agree.AADLEnumerator;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.AlwaysStatement;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.AsynchStatement;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.ClosedTimeInterval;
import com.rockwellcollins.atc.agree.agree.ComplexExpr;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.InitialStatement;
import com.rockwellcollins.atc.agree.agree.InputStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LatchedExpr;
import com.rockwellcollins.atc.agree.agree.LatchedStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDefExpr;
import com.rockwellcollins.atc.agree.agree.LinearizationInterval;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval;
import com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval;
import com.rockwellcollins.atc.agree.agree.OpenTimeInterval;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PatternStatement;
import com.rockwellcollins.atc.agree.agree.PeriodicStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RealTimeStatement;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SporadicStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.TimeExpr;
import com.rockwellcollins.atc.agree.agree.TimeFallExpr;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.agree.TimeOfExpr;
import com.rockwellcollins.atc.agree.agree.TimeRiseExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.agree.WhenHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WhenOccursStatment;
import com.rockwellcollins.atc.agree.agree.WhenStatement;
import com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement;
import com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement;
import com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement;
import com.rockwellcollins.atc.agree.agree.WheneverOccursStatement;
import com.rockwellcollins.atc.agree.agree.WheneverStatement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AgreeFactoryImpl extends EFactoryImpl implements AgreeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AgreeFactory init() {
		try {
			AgreeFactory theAgreeFactory = (AgreeFactory) EPackage.Registry.INSTANCE.getEFactory(AgreePackage.eNS_URI);
			if (theAgreeFactory != null) {
				return theAgreeFactory;
			}
		} catch (Exception exception) {
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
	public AgreeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case AgreePackage.AGREE_LIBRARY:
			return createAgreeLibrary();
		case AgreePackage.AGREE_SUBCLAUSE:
			return createAgreeSubclause();
		case AgreePackage.CONTRACT:
			return createContract();
		case AgreePackage.SPEC_STATEMENT:
			return createSpecStatement();
		case AgreePackage.PATTERN_STATEMENT:
			return createPatternStatement();
		case AgreePackage.WHEN_STATEMENT:
			return createWhenStatement();
		case AgreePackage.WHENEVER_STATEMENT:
			return createWheneverStatement();
		case AgreePackage.REAL_TIME_STATEMENT:
			return createRealTimeStatement();
		case AgreePackage.TIME_INTERVAL:
			return createTimeInterval();
		case AgreePackage.SYNCH_STATEMENT:
			return createSynchStatement();
		case AgreePackage.ORDER_STATEMENT:
			return createOrderStatement();
		case AgreePackage.CALL_DEF:
			return createCallDef();
		case AgreePackage.PROPERTY_STATEMENT:
			return createPropertyStatement();
		case AgreePackage.CONST_STATEMENT:
			return createConstStatement();
		case AgreePackage.ENUM_STATEMENT:
			return createEnumStatement();
		case AgreePackage.EQ_STATEMENT:
			return createEqStatement();
		case AgreePackage.INPUT_STATEMENT:
			return createInputStatement();
		case AgreePackage.ASSIGN_STATEMENT:
			return createAssignStatement();
		case AgreePackage.FN_DEF_EXPR:
			return createFnDefExpr();
		case AgreePackage.LIBRARY_FN_DEF_EXPR:
			return createLibraryFnDefExpr();
		case AgreePackage.LINEARIZATION_DEF_EXPR:
			return createLinearizationDefExpr();
		case AgreePackage.LINEARIZATION_INTERVAL:
			return createLinearizationInterval();
		case AgreePackage.NODE_DEF_EXPR:
			return createNodeDefExpr();
		case AgreePackage.NODE_BODY_EXPR:
			return createNodeBodyExpr();
		case AgreePackage.NODE_STMT:
			return createNodeStmt();
		case AgreePackage.ARG:
			return createArg();
		case AgreePackage.TYPE:
			return createType();
		case AgreePackage.RECORD_DEF_EXPR:
			return createRecordDefExpr();
		case AgreePackage.EXPR:
			return createExpr();
		case AgreePackage.COMPLEX_EXPR:
			return createComplexExpr();
		case AgreePackage.NESTED_DOT_ID:
			return createNestedDotID();
		case AgreePackage.NAMED_ID:
			return createNamedID();
		case AgreePackage.AGREE_CONTRACT_LIBRARY:
			return createAgreeContractLibrary();
		case AgreePackage.AGREE_CONTRACT_SUBCLAUSE:
			return createAgreeContractSubclause();
		case AgreePackage.AGREE_CONTRACT:
			return createAgreeContract();
		case AgreePackage.ASSUME_STATEMENT:
			return createAssumeStatement();
		case AgreePackage.GUARANTEE_STATEMENT:
			return createGuaranteeStatement();
		case AgreePackage.ASSERT_STATEMENT:
			return createAssertStatement();
		case AgreePackage.INITIAL_STATEMENT:
			return createInitialStatement();
		case AgreePackage.PARAM_STATEMENT:
			return createParamStatement();
		case AgreePackage.LEMMA_STATEMENT:
			return createLemmaStatement();
		case AgreePackage.LIFT_STATEMENT:
			return createLiftStatement();
		case AgreePackage.CONNECTION_STATEMENT:
			return createConnectionStatement();
		case AgreePackage.ALWAYS_STATEMENT:
			return createAlwaysStatement();
		case AgreePackage.WHEN_HOLDS_STATEMENT:
			return createWhenHoldsStatement();
		case AgreePackage.WHEN_OCCURS_STATMENT:
			return createWhenOccursStatment();
		case AgreePackage.WHENEVER_OCCURS_STATEMENT:
			return createWheneverOccursStatement();
		case AgreePackage.WHENEVER_BECOMES_TRUE_STATEMENT:
			return createWheneverBecomesTrueStatement();
		case AgreePackage.WHENEVER_HOLDS_STATEMENT:
			return createWheneverHoldsStatement();
		case AgreePackage.WHENEVER_IMPLIES_STATEMENT:
			return createWheneverImpliesStatement();
		case AgreePackage.PERIODIC_STATEMENT:
			return createPeriodicStatement();
		case AgreePackage.SPORADIC_STATEMENT:
			return createSporadicStatement();
		case AgreePackage.CLOSED_TIME_INTERVAL:
			return createClosedTimeInterval();
		case AgreePackage.OPEN_LEFT_TIME_INTERVAL:
			return createOpenLeftTimeInterval();
		case AgreePackage.OPEN_RIGHT_TIME_INTERVAL:
			return createOpenRightTimeInterval();
		case AgreePackage.OPEN_TIME_INTERVAL:
			return createOpenTimeInterval();
		case AgreePackage.MN_SYNCH_STATEMENT:
			return createMNSynchStatement();
		case AgreePackage.CALEN_STATEMENT:
			return createCalenStatement();
		case AgreePackage.ASYNCH_STATEMENT:
			return createAsynchStatement();
		case AgreePackage.LATCHED_STATEMENT:
			return createLatchedStatement();
		case AgreePackage.NODE_EQ:
			return createNodeEq();
		case AgreePackage.NODE_LEMMA:
			return createNodeLemma();
		case AgreePackage.PRIM_TYPE:
			return createPrimType();
		case AgreePackage.RECORD_TYPE:
			return createRecordType();
		case AgreePackage.BINARY_EXPR:
			return createBinaryExpr();
		case AgreePackage.UNARY_EXPR:
			return createUnaryExpr();
		case AgreePackage.IF_THEN_ELSE_EXPR:
			return createIfThenElseExpr();
		case AgreePackage.PREV_EXPR:
			return createPrevExpr();
		case AgreePackage.GET_PROPERTY_EXPR:
			return createGetPropertyExpr();
		case AgreePackage.RECORD_UPDATE_EXPR:
			return createRecordUpdateExpr();
		case AgreePackage.TIME_EXPR:
			return createTimeExpr();
		case AgreePackage.INT_LIT_EXPR:
			return createIntLitExpr();
		case AgreePackage.PRE_EXPR:
			return createPreExpr();
		case AgreePackage.EVENT_EXPR:
			return createEventExpr();
		case AgreePackage.LATCHED_EXPR:
			return createLatchedExpr();
		case AgreePackage.TIME_OF_EXPR:
			return createTimeOfExpr();
		case AgreePackage.TIME_RISE_EXPR:
			return createTimeRiseExpr();
		case AgreePackage.TIME_FALL_EXPR:
			return createTimeFallExpr();
		case AgreePackage.REAL_LIT_EXPR:
			return createRealLitExpr();
		case AgreePackage.BOOL_LIT_EXPR:
			return createBoolLitExpr();
		case AgreePackage.THIS_EXPR:
			return createThisExpr();
		case AgreePackage.FLOOR_CAST:
			return createFloorCast();
		case AgreePackage.REAL_CAST:
			return createRealCast();
		case AgreePackage.AADL_ENUMERATOR:
			return createAADLEnumerator();
		case AgreePackage.FN_CALL_EXPR:
			return createFnCallExpr();
		case AgreePackage.RECORD_EXPR:
			return createRecordExpr();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreeLibrary createAgreeLibrary() {
		AgreeLibraryImpl agreeLibrary = new AgreeLibraryImpl();
		return agreeLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreeSubclause createAgreeSubclause() {
		AgreeSubclauseImpl agreeSubclause = new AgreeSubclauseImpl();
		return agreeSubclause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Contract createContract() {
		ContractImpl contract = new ContractImpl();
		return contract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SpecStatement createSpecStatement() {
		SpecStatementImpl specStatement = new SpecStatementImpl();
		return specStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PatternStatement createPatternStatement() {
		PatternStatementImpl patternStatement = new PatternStatementImpl();
		return patternStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhenStatement createWhenStatement() {
		WhenStatementImpl whenStatement = new WhenStatementImpl();
		return whenStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WheneverStatement createWheneverStatement() {
		WheneverStatementImpl wheneverStatement = new WheneverStatementImpl();
		return wheneverStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealTimeStatement createRealTimeStatement() {
		RealTimeStatementImpl realTimeStatement = new RealTimeStatementImpl();
		return realTimeStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeInterval createTimeInterval() {
		TimeIntervalImpl timeInterval = new TimeIntervalImpl();
		return timeInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SynchStatement createSynchStatement() {
		SynchStatementImpl synchStatement = new SynchStatementImpl();
		return synchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OrderStatement createOrderStatement() {
		OrderStatementImpl orderStatement = new OrderStatementImpl();
		return orderStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CallDef createCallDef() {
		CallDefImpl callDef = new CallDefImpl();
		return callDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertyStatement createPropertyStatement() {
		PropertyStatementImpl propertyStatement = new PropertyStatementImpl();
		return propertyStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConstStatement createConstStatement() {
		ConstStatementImpl constStatement = new ConstStatementImpl();
		return constStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnumStatement createEnumStatement() {
		EnumStatementImpl enumStatement = new EnumStatementImpl();
		return enumStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EqStatement createEqStatement() {
		EqStatementImpl eqStatement = new EqStatementImpl();
		return eqStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputStatement createInputStatement() {
		InputStatementImpl inputStatement = new InputStatementImpl();
		return inputStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssignStatement createAssignStatement() {
		AssignStatementImpl assignStatement = new AssignStatementImpl();
		return assignStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FnDefExpr createFnDefExpr() {
		FnDefExprImpl fnDefExpr = new FnDefExprImpl();
		return fnDefExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LibraryFnDefExpr createLibraryFnDefExpr() {
		LibraryFnDefExprImpl libraryFnDefExpr = new LibraryFnDefExprImpl();
		return libraryFnDefExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinearizationDefExpr createLinearizationDefExpr() {
		LinearizationDefExprImpl linearizationDefExpr = new LinearizationDefExprImpl();
		return linearizationDefExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinearizationInterval createLinearizationInterval() {
		LinearizationIntervalImpl linearizationInterval = new LinearizationIntervalImpl();
		return linearizationInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeDefExpr createNodeDefExpr() {
		NodeDefExprImpl nodeDefExpr = new NodeDefExprImpl();
		return nodeDefExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeBodyExpr createNodeBodyExpr() {
		NodeBodyExprImpl nodeBodyExpr = new NodeBodyExprImpl();
		return nodeBodyExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeStmt createNodeStmt() {
		NodeStmtImpl nodeStmt = new NodeStmtImpl();
		return nodeStmt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Arg createArg() {
		ArgImpl arg = new ArgImpl();
		return arg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RecordDefExpr createRecordDefExpr() {
		RecordDefExprImpl recordDefExpr = new RecordDefExprImpl();
		return recordDefExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expr createExpr() {
		ExprImpl expr = new ExprImpl();
		return expr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComplexExpr createComplexExpr() {
		ComplexExprImpl complexExpr = new ComplexExprImpl();
		return complexExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NestedDotID createNestedDotID() {
		NestedDotIDImpl nestedDotID = new NestedDotIDImpl();
		return nestedDotID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedID createNamedID() {
		NamedIDImpl namedID = new NamedIDImpl();
		return namedID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreeContractLibrary createAgreeContractLibrary() {
		AgreeContractLibraryImpl agreeContractLibrary = new AgreeContractLibraryImpl();
		return agreeContractLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreeContractSubclause createAgreeContractSubclause() {
		AgreeContractSubclauseImpl agreeContractSubclause = new AgreeContractSubclauseImpl();
		return agreeContractSubclause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreeContract createAgreeContract() {
		AgreeContractImpl agreeContract = new AgreeContractImpl();
		return agreeContract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssumeStatement createAssumeStatement() {
		AssumeStatementImpl assumeStatement = new AssumeStatementImpl();
		return assumeStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GuaranteeStatement createGuaranteeStatement() {
		GuaranteeStatementImpl guaranteeStatement = new GuaranteeStatementImpl();
		return guaranteeStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssertStatement createAssertStatement() {
		AssertStatementImpl assertStatement = new AssertStatementImpl();
		return assertStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InitialStatement createInitialStatement() {
		InitialStatementImpl initialStatement = new InitialStatementImpl();
		return initialStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParamStatement createParamStatement() {
		ParamStatementImpl paramStatement = new ParamStatementImpl();
		return paramStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LemmaStatement createLemmaStatement() {
		LemmaStatementImpl lemmaStatement = new LemmaStatementImpl();
		return lemmaStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LiftStatement createLiftStatement() {
		LiftStatementImpl liftStatement = new LiftStatementImpl();
		return liftStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectionStatement createConnectionStatement() {
		ConnectionStatementImpl connectionStatement = new ConnectionStatementImpl();
		return connectionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AlwaysStatement createAlwaysStatement() {
		AlwaysStatementImpl alwaysStatement = new AlwaysStatementImpl();
		return alwaysStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhenHoldsStatement createWhenHoldsStatement() {
		WhenHoldsStatementImpl whenHoldsStatement = new WhenHoldsStatementImpl();
		return whenHoldsStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhenOccursStatment createWhenOccursStatment() {
		WhenOccursStatmentImpl whenOccursStatment = new WhenOccursStatmentImpl();
		return whenOccursStatment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WheneverOccursStatement createWheneverOccursStatement() {
		WheneverOccursStatementImpl wheneverOccursStatement = new WheneverOccursStatementImpl();
		return wheneverOccursStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WheneverBecomesTrueStatement createWheneverBecomesTrueStatement() {
		WheneverBecomesTrueStatementImpl wheneverBecomesTrueStatement = new WheneverBecomesTrueStatementImpl();
		return wheneverBecomesTrueStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WheneverHoldsStatement createWheneverHoldsStatement() {
		WheneverHoldsStatementImpl wheneverHoldsStatement = new WheneverHoldsStatementImpl();
		return wheneverHoldsStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WheneverImpliesStatement createWheneverImpliesStatement() {
		WheneverImpliesStatementImpl wheneverImpliesStatement = new WheneverImpliesStatementImpl();
		return wheneverImpliesStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PeriodicStatement createPeriodicStatement() {
		PeriodicStatementImpl periodicStatement = new PeriodicStatementImpl();
		return periodicStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SporadicStatement createSporadicStatement() {
		SporadicStatementImpl sporadicStatement = new SporadicStatementImpl();
		return sporadicStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ClosedTimeInterval createClosedTimeInterval() {
		ClosedTimeIntervalImpl closedTimeInterval = new ClosedTimeIntervalImpl();
		return closedTimeInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OpenLeftTimeInterval createOpenLeftTimeInterval() {
		OpenLeftTimeIntervalImpl openLeftTimeInterval = new OpenLeftTimeIntervalImpl();
		return openLeftTimeInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OpenRightTimeInterval createOpenRightTimeInterval() {
		OpenRightTimeIntervalImpl openRightTimeInterval = new OpenRightTimeIntervalImpl();
		return openRightTimeInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OpenTimeInterval createOpenTimeInterval() {
		OpenTimeIntervalImpl openTimeInterval = new OpenTimeIntervalImpl();
		return openTimeInterval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MNSynchStatement createMNSynchStatement() {
		MNSynchStatementImpl mnSynchStatement = new MNSynchStatementImpl();
		return mnSynchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CalenStatement createCalenStatement() {
		CalenStatementImpl calenStatement = new CalenStatementImpl();
		return calenStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AsynchStatement createAsynchStatement() {
		AsynchStatementImpl asynchStatement = new AsynchStatementImpl();
		return asynchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LatchedStatement createLatchedStatement() {
		LatchedStatementImpl latchedStatement = new LatchedStatementImpl();
		return latchedStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeEq createNodeEq() {
		NodeEqImpl nodeEq = new NodeEqImpl();
		return nodeEq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NodeLemma createNodeLemma() {
		NodeLemmaImpl nodeLemma = new NodeLemmaImpl();
		return nodeLemma;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrimType createPrimType() {
		PrimTypeImpl primType = new PrimTypeImpl();
		return primType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RecordType createRecordType() {
		RecordTypeImpl recordType = new RecordTypeImpl();
		return recordType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BinaryExpr createBinaryExpr() {
		BinaryExprImpl binaryExpr = new BinaryExprImpl();
		return binaryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnaryExpr createUnaryExpr() {
		UnaryExprImpl unaryExpr = new UnaryExprImpl();
		return unaryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfThenElseExpr createIfThenElseExpr() {
		IfThenElseExprImpl ifThenElseExpr = new IfThenElseExprImpl();
		return ifThenElseExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrevExpr createPrevExpr() {
		PrevExprImpl prevExpr = new PrevExprImpl();
		return prevExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetPropertyExpr createGetPropertyExpr() {
		GetPropertyExprImpl getPropertyExpr = new GetPropertyExprImpl();
		return getPropertyExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RecordUpdateExpr createRecordUpdateExpr() {
		RecordUpdateExprImpl recordUpdateExpr = new RecordUpdateExprImpl();
		return recordUpdateExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeExpr createTimeExpr() {
		TimeExprImpl timeExpr = new TimeExprImpl();
		return timeExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntLitExpr createIntLitExpr() {
		IntLitExprImpl intLitExpr = new IntLitExprImpl();
		return intLitExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PreExpr createPreExpr() {
		PreExprImpl preExpr = new PreExprImpl();
		return preExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EventExpr createEventExpr() {
		EventExprImpl eventExpr = new EventExprImpl();
		return eventExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LatchedExpr createLatchedExpr() {
		LatchedExprImpl latchedExpr = new LatchedExprImpl();
		return latchedExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeOfExpr createTimeOfExpr() {
		TimeOfExprImpl timeOfExpr = new TimeOfExprImpl();
		return timeOfExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeRiseExpr createTimeRiseExpr() {
		TimeRiseExprImpl timeRiseExpr = new TimeRiseExprImpl();
		return timeRiseExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimeFallExpr createTimeFallExpr() {
		TimeFallExprImpl timeFallExpr = new TimeFallExprImpl();
		return timeFallExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealLitExpr createRealLitExpr() {
		RealLitExprImpl realLitExpr = new RealLitExprImpl();
		return realLitExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoolLitExpr createBoolLitExpr() {
		BoolLitExprImpl boolLitExpr = new BoolLitExprImpl();
		return boolLitExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ThisExpr createThisExpr() {
		ThisExprImpl thisExpr = new ThisExprImpl();
		return thisExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloorCast createFloorCast() {
		FloorCastImpl floorCast = new FloorCastImpl();
		return floorCast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealCast createRealCast() {
		RealCastImpl realCast = new RealCastImpl();
		return realCast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AADLEnumerator createAADLEnumerator() {
		AADLEnumeratorImpl aadlEnumerator = new AADLEnumeratorImpl();
		return aadlEnumerator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FnCallExpr createFnCallExpr() {
		FnCallExprImpl fnCallExpr = new FnCallExprImpl();
		return fnCallExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RecordExpr createRecordExpr() {
		RecordExprImpl recordExpr = new RecordExprImpl();
		return recordExpr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AgreePackage getAgreePackage() {
		return (AgreePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AgreePackage getPackage() {
		return AgreePackage.eINSTANCE;
	}

} // AgreeFactoryImpl
