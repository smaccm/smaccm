/**
 */
package com.rockwellcollins.atc.agree.agree.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModalElement;
import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.agree.agree.AADLEnumerator;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage
 * @generated
 */
public class AgreeAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AgreePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AgreeAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AgreePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgreeSwitch<Adapter> modelSwitch = new AgreeSwitch<Adapter>() {
		@Override
		public Adapter caseAgreeLibrary(AgreeLibrary object) {
			return createAgreeLibraryAdapter();
		}

		@Override
		public Adapter caseAgreeSubclause(AgreeSubclause object) {
			return createAgreeSubclauseAdapter();
		}

		@Override
		public Adapter caseContract(Contract object) {
			return createContractAdapter();
		}

		@Override
		public Adapter caseSpecStatement(SpecStatement object) {
			return createSpecStatementAdapter();
		}

		@Override
		public Adapter casePatternStatement(PatternStatement object) {
			return createPatternStatementAdapter();
		}

		@Override
		public Adapter caseWhenStatement(WhenStatement object) {
			return createWhenStatementAdapter();
		}

		@Override
		public Adapter caseWheneverStatement(WheneverStatement object) {
			return createWheneverStatementAdapter();
		}

		@Override
		public Adapter caseRealTimeStatement(RealTimeStatement object) {
			return createRealTimeStatementAdapter();
		}

		@Override
		public Adapter caseTimeInterval(TimeInterval object) {
			return createTimeIntervalAdapter();
		}

		@Override
		public Adapter caseSynchStatement(SynchStatement object) {
			return createSynchStatementAdapter();
		}

		@Override
		public Adapter caseOrderStatement(OrderStatement object) {
			return createOrderStatementAdapter();
		}

		@Override
		public Adapter caseCallDef(CallDef object) {
			return createCallDefAdapter();
		}

		@Override
		public Adapter casePropertyStatement(PropertyStatement object) {
			return createPropertyStatementAdapter();
		}

		@Override
		public Adapter caseConstStatement(ConstStatement object) {
			return createConstStatementAdapter();
		}

		@Override
		public Adapter caseEnumStatement(EnumStatement object) {
			return createEnumStatementAdapter();
		}

		@Override
		public Adapter caseEqStatement(EqStatement object) {
			return createEqStatementAdapter();
		}

		@Override
		public Adapter caseInputStatement(InputStatement object) {
			return createInputStatementAdapter();
		}

		@Override
		public Adapter caseAssignStatement(AssignStatement object) {
			return createAssignStatementAdapter();
		}

		@Override
		public Adapter caseFnDefExpr(FnDefExpr object) {
			return createFnDefExprAdapter();
		}

		@Override
		public Adapter caseLibraryFnDefExpr(LibraryFnDefExpr object) {
			return createLibraryFnDefExprAdapter();
		}

		@Override
		public Adapter caseLinearizationDefExpr(LinearizationDefExpr object) {
			return createLinearizationDefExprAdapter();
		}

		@Override
		public Adapter caseLinearizationInterval(LinearizationInterval object) {
			return createLinearizationIntervalAdapter();
		}

		@Override
		public Adapter caseNodeDefExpr(NodeDefExpr object) {
			return createNodeDefExprAdapter();
		}

		@Override
		public Adapter caseNodeBodyExpr(NodeBodyExpr object) {
			return createNodeBodyExprAdapter();
		}

		@Override
		public Adapter caseNodeStmt(NodeStmt object) {
			return createNodeStmtAdapter();
		}

		@Override
		public Adapter caseArg(Arg object) {
			return createArgAdapter();
		}

		@Override
		public Adapter caseType(Type object) {
			return createTypeAdapter();
		}

		@Override
		public Adapter caseRecordDefExpr(RecordDefExpr object) {
			return createRecordDefExprAdapter();
		}

		@Override
		public Adapter caseExpr(Expr object) {
			return createExprAdapter();
		}

		@Override
		public Adapter caseComplexExpr(ComplexExpr object) {
			return createComplexExprAdapter();
		}

		@Override
		public Adapter caseNestedDotID(NestedDotID object) {
			return createNestedDotIDAdapter();
		}

		@Override
		public Adapter caseNamedID(NamedID object) {
			return createNamedIDAdapter();
		}

		@Override
		public Adapter caseAgreeContractLibrary(AgreeContractLibrary object) {
			return createAgreeContractLibraryAdapter();
		}

		@Override
		public Adapter caseAgreeContractSubclause(AgreeContractSubclause object) {
			return createAgreeContractSubclauseAdapter();
		}

		@Override
		public Adapter caseAgreeContract(AgreeContract object) {
			return createAgreeContractAdapter();
		}

		@Override
		public Adapter caseAssumeStatement(AssumeStatement object) {
			return createAssumeStatementAdapter();
		}

		@Override
		public Adapter caseGuaranteeStatement(GuaranteeStatement object) {
			return createGuaranteeStatementAdapter();
		}

		@Override
		public Adapter caseAssertStatement(AssertStatement object) {
			return createAssertStatementAdapter();
		}

		@Override
		public Adapter caseInitialStatement(InitialStatement object) {
			return createInitialStatementAdapter();
		}

		@Override
		public Adapter caseParamStatement(ParamStatement object) {
			return createParamStatementAdapter();
		}

		@Override
		public Adapter caseLemmaStatement(LemmaStatement object) {
			return createLemmaStatementAdapter();
		}

		@Override
		public Adapter caseLiftStatement(LiftStatement object) {
			return createLiftStatementAdapter();
		}

		@Override
		public Adapter caseConnectionStatement(ConnectionStatement object) {
			return createConnectionStatementAdapter();
		}

		@Override
		public Adapter caseAlwaysStatement(AlwaysStatement object) {
			return createAlwaysStatementAdapter();
		}

		@Override
		public Adapter caseWhenHoldsStatement(WhenHoldsStatement object) {
			return createWhenHoldsStatementAdapter();
		}

		@Override
		public Adapter caseWhenOccursStatment(WhenOccursStatment object) {
			return createWhenOccursStatmentAdapter();
		}

		@Override
		public Adapter caseWheneverOccursStatement(WheneverOccursStatement object) {
			return createWheneverOccursStatementAdapter();
		}

		@Override
		public Adapter caseWheneverBecomesTrueStatement(WheneverBecomesTrueStatement object) {
			return createWheneverBecomesTrueStatementAdapter();
		}

		@Override
		public Adapter caseWheneverHoldsStatement(WheneverHoldsStatement object) {
			return createWheneverHoldsStatementAdapter();
		}

		@Override
		public Adapter caseWheneverImpliesStatement(WheneverImpliesStatement object) {
			return createWheneverImpliesStatementAdapter();
		}

		@Override
		public Adapter casePeriodicStatement(PeriodicStatement object) {
			return createPeriodicStatementAdapter();
		}

		@Override
		public Adapter caseSporadicStatement(SporadicStatement object) {
			return createSporadicStatementAdapter();
		}

		@Override
		public Adapter caseClosedTimeInterval(ClosedTimeInterval object) {
			return createClosedTimeIntervalAdapter();
		}

		@Override
		public Adapter caseOpenLeftTimeInterval(OpenLeftTimeInterval object) {
			return createOpenLeftTimeIntervalAdapter();
		}

		@Override
		public Adapter caseOpenRightTimeInterval(OpenRightTimeInterval object) {
			return createOpenRightTimeIntervalAdapter();
		}

		@Override
		public Adapter caseOpenTimeInterval(OpenTimeInterval object) {
			return createOpenTimeIntervalAdapter();
		}

		@Override
		public Adapter caseMNSynchStatement(MNSynchStatement object) {
			return createMNSynchStatementAdapter();
		}

		@Override
		public Adapter caseCalenStatement(CalenStatement object) {
			return createCalenStatementAdapter();
		}

		@Override
		public Adapter caseAsynchStatement(AsynchStatement object) {
			return createAsynchStatementAdapter();
		}

		@Override
		public Adapter caseLatchedStatement(LatchedStatement object) {
			return createLatchedStatementAdapter();
		}

		@Override
		public Adapter caseNodeEq(NodeEq object) {
			return createNodeEqAdapter();
		}

		@Override
		public Adapter caseNodeLemma(NodeLemma object) {
			return createNodeLemmaAdapter();
		}

		@Override
		public Adapter casePrimType(PrimType object) {
			return createPrimTypeAdapter();
		}

		@Override
		public Adapter caseRecordType(RecordType object) {
			return createRecordTypeAdapter();
		}

		@Override
		public Adapter caseBinaryExpr(BinaryExpr object) {
			return createBinaryExprAdapter();
		}

		@Override
		public Adapter caseUnaryExpr(UnaryExpr object) {
			return createUnaryExprAdapter();
		}

		@Override
		public Adapter caseIfThenElseExpr(IfThenElseExpr object) {
			return createIfThenElseExprAdapter();
		}

		@Override
		public Adapter casePrevExpr(PrevExpr object) {
			return createPrevExprAdapter();
		}

		@Override
		public Adapter caseGetPropertyExpr(GetPropertyExpr object) {
			return createGetPropertyExprAdapter();
		}

		@Override
		public Adapter caseRecordUpdateExpr(RecordUpdateExpr object) {
			return createRecordUpdateExprAdapter();
		}

		@Override
		public Adapter caseTimeExpr(TimeExpr object) {
			return createTimeExprAdapter();
		}

		@Override
		public Adapter caseIntLitExpr(IntLitExpr object) {
			return createIntLitExprAdapter();
		}

		@Override
		public Adapter casePreExpr(PreExpr object) {
			return createPreExprAdapter();
		}

		@Override
		public Adapter caseEventExpr(EventExpr object) {
			return createEventExprAdapter();
		}

		@Override
		public Adapter caseLatchedExpr(LatchedExpr object) {
			return createLatchedExprAdapter();
		}

		@Override
		public Adapter caseTimeOfExpr(TimeOfExpr object) {
			return createTimeOfExprAdapter();
		}

		@Override
		public Adapter caseTimeRiseExpr(TimeRiseExpr object) {
			return createTimeRiseExprAdapter();
		}

		@Override
		public Adapter caseTimeFallExpr(TimeFallExpr object) {
			return createTimeFallExprAdapter();
		}

		@Override
		public Adapter caseRealLitExpr(RealLitExpr object) {
			return createRealLitExprAdapter();
		}

		@Override
		public Adapter caseBoolLitExpr(BoolLitExpr object) {
			return createBoolLitExprAdapter();
		}

		@Override
		public Adapter caseThisExpr(ThisExpr object) {
			return createThisExprAdapter();
		}

		@Override
		public Adapter caseFloorCast(FloorCast object) {
			return createFloorCastAdapter();
		}

		@Override
		public Adapter caseRealCast(RealCast object) {
			return createRealCastAdapter();
		}

		@Override
		public Adapter caseAADLEnumerator(AADLEnumerator object) {
			return createAADLEnumeratorAdapter();
		}

		@Override
		public Adapter caseFnCallExpr(FnCallExpr object) {
			return createFnCallExprAdapter();
		}

		@Override
		public Adapter caseRecordExpr(RecordExpr object) {
			return createRecordExprAdapter();
		}

		@Override
		public Adapter caseElement(Element object) {
			return createElementAdapter();
		}

		@Override
		public Adapter caseNamedElement(NamedElement object) {
			return createNamedElementAdapter();
		}

		@Override
		public Adapter caseAnnexLibrary(AnnexLibrary object) {
			return createAnnexLibraryAdapter();
		}

		@Override
		public Adapter caseModalElement(ModalElement object) {
			return createModalElementAdapter();
		}

		@Override
		public Adapter caseAnnexSubclause(AnnexSubclause object) {
			return createAnnexSubclauseAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AgreeLibrary <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AgreeLibrary
	 * @generated
	 */
	public Adapter createAgreeLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AgreeSubclause <em>Subclause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AgreeSubclause
	 * @generated
	 */
	public Adapter createAgreeSubclauseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.Contract <em>Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.Contract
	 * @generated
	 */
	public Adapter createContractAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.SpecStatement <em>Spec Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.SpecStatement
	 * @generated
	 */
	public Adapter createSpecStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PatternStatement <em>Pattern Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PatternStatement
	 * @generated
	 */
	public Adapter createPatternStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WhenStatement <em>When Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WhenStatement
	 * @generated
	 */
	public Adapter createWhenStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement <em>Whenever Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WheneverStatement
	 * @generated
	 */
	public Adapter createWheneverStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement <em>Real Time Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RealTimeStatement
	 * @generated
	 */
	public Adapter createRealTimeStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TimeInterval <em>Time Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.TimeInterval
	 * @generated
	 */
	public Adapter createTimeIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.SynchStatement <em>Synch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.SynchStatement
	 * @generated
	 */
	public Adapter createSynchStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.OrderStatement <em>Order Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.OrderStatement
	 * @generated
	 */
	public Adapter createOrderStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.CallDef <em>Call Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.CallDef
	 * @generated
	 */
	public Adapter createCallDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PropertyStatement <em>Property Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PropertyStatement
	 * @generated
	 */
	public Adapter createPropertyStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ConstStatement <em>Const Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ConstStatement
	 * @generated
	 */
	public Adapter createConstStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.EnumStatement <em>Enum Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.EnumStatement
	 * @generated
	 */
	public Adapter createEnumStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.EqStatement <em>Eq Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.EqStatement
	 * @generated
	 */
	public Adapter createEqStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.InputStatement <em>Input Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.InputStatement
	 * @generated
	 */
	public Adapter createInputStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AssignStatement <em>Assign Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AssignStatement
	 * @generated
	 */
	public Adapter createAssignStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr <em>Fn Def Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.FnDefExpr
	 * @generated
	 */
	public Adapter createFnDefExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr <em>Library Fn Def Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr
	 * @generated
	 */
	public Adapter createLibraryFnDefExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr <em>Linearization Def Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr
	 * @generated
	 */
	public Adapter createLinearizationDefExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval <em>Linearization Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LinearizationInterval
	 * @generated
	 */
	public Adapter createLinearizationIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr <em>Node Def Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NodeDefExpr
	 * @generated
	 */
	public Adapter createNodeDefExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr <em>Node Body Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NodeBodyExpr
	 * @generated
	 */
	public Adapter createNodeBodyExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeStmt <em>Node Stmt</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NodeStmt
	 * @generated
	 */
	public Adapter createNodeStmtAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.Arg <em>Arg</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.Arg
	 * @generated
	 */
	public Adapter createArgAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordDefExpr <em>Record Def Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RecordDefExpr
	 * @generated
	 */
	public Adapter createRecordDefExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.Expr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.Expr
	 * @generated
	 */
	public Adapter createExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ComplexExpr <em>Complex Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ComplexExpr
	 * @generated
	 */
	public Adapter createComplexExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NestedDotID <em>Nested Dot ID</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NestedDotID
	 * @generated
	 */
	public Adapter createNestedDotIDAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NamedID <em>Named ID</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NamedID
	 * @generated
	 */
	public Adapter createNamedIDAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AgreeContractLibrary <em>Contract Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AgreeContractLibrary
	 * @generated
	 */
	public Adapter createAgreeContractLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AgreeContractSubclause <em>Contract Subclause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AgreeContractSubclause
	 * @generated
	 */
	public Adapter createAgreeContractSubclauseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AgreeContract <em>Contract</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AgreeContract
	 * @generated
	 */
	public Adapter createAgreeContractAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement <em>Assume Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AssumeStatement
	 * @generated
	 */
	public Adapter createAssumeStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.GuaranteeStatement <em>Guarantee Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.GuaranteeStatement
	 * @generated
	 */
	public Adapter createGuaranteeStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AssertStatement <em>Assert Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AssertStatement
	 * @generated
	 */
	public Adapter createAssertStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.InitialStatement <em>Initial Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.InitialStatement
	 * @generated
	 */
	public Adapter createInitialStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ParamStatement <em>Param Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ParamStatement
	 * @generated
	 */
	public Adapter createParamStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LemmaStatement <em>Lemma Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LemmaStatement
	 * @generated
	 */
	public Adapter createLemmaStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LiftStatement <em>Lift Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LiftStatement
	 * @generated
	 */
	public Adapter createLiftStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ConnectionStatement <em>Connection Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ConnectionStatement
	 * @generated
	 */
	public Adapter createConnectionStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AlwaysStatement <em>Always Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AlwaysStatement
	 * @generated
	 */
	public Adapter createAlwaysStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement <em>When Holds Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WhenHoldsStatement
	 * @generated
	 */
	public Adapter createWhenHoldsStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment <em>When Occurs Statment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WhenOccursStatment
	 * @generated
	 */
	public Adapter createWhenOccursStatmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WheneverOccursStatement <em>Whenever Occurs Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WheneverOccursStatement
	 * @generated
	 */
	public Adapter createWheneverOccursStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement <em>Whenever Becomes True Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement
	 * @generated
	 */
	public Adapter createWheneverBecomesTrueStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement <em>Whenever Holds Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement
	 * @generated
	 */
	public Adapter createWheneverHoldsStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement <em>Whenever Implies Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement
	 * @generated
	 */
	public Adapter createWheneverImpliesStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PeriodicStatement <em>Periodic Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PeriodicStatement
	 * @generated
	 */
	public Adapter createPeriodicStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.SporadicStatement <em>Sporadic Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.SporadicStatement
	 * @generated
	 */
	public Adapter createSporadicStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ClosedTimeInterval <em>Closed Time Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ClosedTimeInterval
	 * @generated
	 */
	public Adapter createClosedTimeIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval <em>Open Left Time Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval
	 * @generated
	 */
	public Adapter createOpenLeftTimeIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval <em>Open Right Time Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval
	 * @generated
	 */
	public Adapter createOpenRightTimeIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.OpenTimeInterval <em>Open Time Interval</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.OpenTimeInterval
	 * @generated
	 */
	public Adapter createOpenTimeIntervalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement <em>MN Synch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement
	 * @generated
	 */
	public Adapter createMNSynchStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.CalenStatement <em>Calen Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.CalenStatement
	 * @generated
	 */
	public Adapter createCalenStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AsynchStatement <em>Asynch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AsynchStatement
	 * @generated
	 */
	public Adapter createAsynchStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LatchedStatement <em>Latched Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LatchedStatement
	 * @generated
	 */
	public Adapter createLatchedStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeEq <em>Node Eq</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NodeEq
	 * @generated
	 */
	public Adapter createNodeEqAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeLemma <em>Node Lemma</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.NodeLemma
	 * @generated
	 */
	public Adapter createNodeLemmaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PrimType <em>Prim Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PrimType
	 * @generated
	 */
	public Adapter createPrimTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordType <em>Record Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RecordType
	 * @generated
	 */
	public Adapter createRecordTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.BinaryExpr <em>Binary Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.BinaryExpr
	 * @generated
	 */
	public Adapter createBinaryExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.UnaryExpr <em>Unary Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.UnaryExpr
	 * @generated
	 */
	public Adapter createUnaryExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.IfThenElseExpr <em>If Then Else Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.IfThenElseExpr
	 * @generated
	 */
	public Adapter createIfThenElseExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PrevExpr <em>Prev Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PrevExpr
	 * @generated
	 */
	public Adapter createPrevExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr <em>Get Property Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.GetPropertyExpr
	 * @generated
	 */
	public Adapter createGetPropertyExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr <em>Record Update Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr
	 * @generated
	 */
	public Adapter createRecordUpdateExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TimeExpr <em>Time Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.TimeExpr
	 * @generated
	 */
	public Adapter createTimeExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.IntLitExpr <em>Int Lit Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.IntLitExpr
	 * @generated
	 */
	public Adapter createIntLitExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.PreExpr <em>Pre Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.PreExpr
	 * @generated
	 */
	public Adapter createPreExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.EventExpr <em>Event Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.EventExpr
	 * @generated
	 */
	public Adapter createEventExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LatchedExpr <em>Latched Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.LatchedExpr
	 * @generated
	 */
	public Adapter createLatchedExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr <em>Time Of Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.TimeOfExpr
	 * @generated
	 */
	public Adapter createTimeOfExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TimeRiseExpr <em>Time Rise Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.TimeRiseExpr
	 * @generated
	 */
	public Adapter createTimeRiseExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TimeFallExpr <em>Time Fall Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.TimeFallExpr
	 * @generated
	 */
	public Adapter createTimeFallExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RealLitExpr <em>Real Lit Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RealLitExpr
	 * @generated
	 */
	public Adapter createRealLitExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.BoolLitExpr <em>Bool Lit Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.BoolLitExpr
	 * @generated
	 */
	public Adapter createBoolLitExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ThisExpr <em>This Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.ThisExpr
	 * @generated
	 */
	public Adapter createThisExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FloorCast <em>Floor Cast</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.FloorCast
	 * @generated
	 */
	public Adapter createFloorCastAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RealCast <em>Real Cast</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RealCast
	 * @generated
	 */
	public Adapter createRealCastAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.AADLEnumerator <em>AADL Enumerator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.AADLEnumerator
	 * @generated
	 */
	public Adapter createAADLEnumeratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr <em>Fn Call Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.FnCallExpr
	 * @generated
	 */
	public Adapter createFnCallExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordExpr <em>Record Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.rockwellcollins.atc.agree.agree.RecordExpr
	 * @generated
	 */
	public Adapter createRecordExprAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.osate.aadl2.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.osate.aadl2.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.osate.aadl2.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.osate.aadl2.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.osate.aadl2.AnnexLibrary <em>Annex Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.osate.aadl2.AnnexLibrary
	 * @generated
	 */
	public Adapter createAnnexLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.osate.aadl2.ModalElement <em>Modal Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.osate.aadl2.ModalElement
	 * @generated
	 */
	public Adapter createModalElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.osate.aadl2.AnnexSubclause <em>Annex Subclause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.osate.aadl2.AnnexSubclause
	 * @generated
	 */
	public Adapter createAnnexSubclauseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // AgreeAdapterFactory
