/**
 */
package com.rockwellcollins.atc.agree.agree.util;

import com.rockwellcollins.atc.agree.agree.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModalElement;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage
 * @generated
 */
public class AgreeAdapterFactory extends AdapterFactoryImpl
{
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
  public AgreeAdapterFactory()
  {
    if (modelPackage == null)
    {
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
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AgreeSwitch<Adapter> modelSwitch =
    new AgreeSwitch<Adapter>()
    {
      @Override
      public Adapter caseAgreeLibrary(AgreeLibrary object)
      {
        return createAgreeLibraryAdapter();
      }
      @Override
      public Adapter caseAgreeSubclause(AgreeSubclause object)
      {
        return createAgreeSubclauseAdapter();
      }
      @Override
      public Adapter caseContract(Contract object)
      {
        return createContractAdapter();
      }
      @Override
      public Adapter caseSpecStatement(SpecStatement object)
      {
        return createSpecStatementAdapter();
      }
      @Override
      public Adapter caseNamedSpecStatement(NamedSpecStatement object)
      {
        return createNamedSpecStatementAdapter();
      }
      @Override
      public Adapter casePatternStatement(PatternStatement object)
      {
        return createPatternStatementAdapter();
      }
      @Override
      public Adapter caseWhenStatement(WhenStatement object)
      {
        return createWhenStatementAdapter();
      }
      @Override
      public Adapter caseWheneverStatement(WheneverStatement object)
      {
        return createWheneverStatementAdapter();
      }
      @Override
      public Adapter caseRealTimeStatement(RealTimeStatement object)
      {
        return createRealTimeStatementAdapter();
      }
      @Override
      public Adapter caseTimeInterval(TimeInterval object)
      {
        return createTimeIntervalAdapter();
      }
      @Override
      public Adapter caseSynchStatement(SynchStatement object)
      {
        return createSynchStatementAdapter();
      }
      @Override
      public Adapter caseOrderStatement(OrderStatement object)
      {
        return createOrderStatementAdapter();
      }
      @Override
      public Adapter caseAbstraction(Abstraction object)
      {
        return createAbstractionAdapter();
      }
      @Override
      public Adapter casePropertyStatement(PropertyStatement object)
      {
        return createPropertyStatementAdapter();
      }
      @Override
      public Adapter caseConstStatement(ConstStatement object)
      {
        return createConstStatementAdapter();
      }
      @Override
      public Adapter caseEqStatement(EqStatement object)
      {
        return createEqStatementAdapter();
      }
      @Override
      public Adapter caseInputStatement(InputStatement object)
      {
        return createInputStatementAdapter();
      }
      @Override
      public Adapter caseAssignStatement(AssignStatement object)
      {
        return createAssignStatementAdapter();
      }
      @Override
      public Adapter caseFnDef(FnDef object)
      {
        return createFnDefAdapter();
      }
      @Override
      public Adapter caseLibraryFnDef(LibraryFnDef object)
      {
        return createLibraryFnDefAdapter();
      }
      @Override
      public Adapter caseLinearizationDef(LinearizationDef object)
      {
        return createLinearizationDefAdapter();
      }
      @Override
      public Adapter caseLinearizationInterval(LinearizationInterval object)
      {
        return createLinearizationIntervalAdapter();
      }
      @Override
      public Adapter caseNodeDef(NodeDef object)
      {
        return createNodeDefAdapter();
      }
      @Override
      public Adapter caseNodeBodyExpr(NodeBodyExpr object)
      {
        return createNodeBodyExprAdapter();
      }
      @Override
      public Adapter caseNodeStmt(NodeStmt object)
      {
        return createNodeStmtAdapter();
      }
      @Override
      public Adapter caseArg(Arg object)
      {
        return createArgAdapter();
      }
      @Override
      public Adapter caseType(Type object)
      {
        return createTypeAdapter();
      }
      @Override
      public Adapter caseRecordDef(RecordDef object)
      {
        return createRecordDefAdapter();
      }
      @Override
      public Adapter caseEnumStatement(EnumStatement object)
      {
        return createEnumStatementAdapter();
      }
      @Override
      public Adapter caseExpr(Expr object)
      {
        return createExprAdapter();
      }
      @Override
      public Adapter caseComponentRef(ComponentRef object)
      {
        return createComponentRefAdapter();
      }
      @Override
      public Adapter caseArrayLiteralExpr(ArrayLiteralExpr object)
      {
        return createArrayLiteralExprAdapter();
      }
      @Override
      public Adapter caseDoubleDotRef(DoubleDotRef object)
      {
        return createDoubleDotRefAdapter();
      }
      @Override
      public Adapter caseNamedID(NamedID object)
      {
        return createNamedIDAdapter();
      }
      @Override
      public Adapter caseAgreeContractLibrary(AgreeContractLibrary object)
      {
        return createAgreeContractLibraryAdapter();
      }
      @Override
      public Adapter caseAgreeContractSubclause(AgreeContractSubclause object)
      {
        return createAgreeContractSubclauseAdapter();
      }
      @Override
      public Adapter caseAgreeContract(AgreeContract object)
      {
        return createAgreeContractAdapter();
      }
      @Override
      public Adapter caseInitialStatement(InitialStatement object)
      {
        return createInitialStatementAdapter();
      }
      @Override
      public Adapter caseParamStatement(ParamStatement object)
      {
        return createParamStatementAdapter();
      }
      @Override
      public Adapter caseLiftStatement(LiftStatement object)
      {
        return createLiftStatementAdapter();
      }
      @Override
      public Adapter caseConnectionStatement(ConnectionStatement object)
      {
        return createConnectionStatementAdapter();
      }
      @Override
      public Adapter caseAssumeStatement(AssumeStatement object)
      {
        return createAssumeStatementAdapter();
      }
      @Override
      public Adapter caseGuaranteeStatement(GuaranteeStatement object)
      {
        return createGuaranteeStatementAdapter();
      }
      @Override
      public Adapter caseAssertStatement(AssertStatement object)
      {
        return createAssertStatementAdapter();
      }
      @Override
      public Adapter caseLemmaStatement(LemmaStatement object)
      {
        return createLemmaStatementAdapter();
      }
      @Override
      public Adapter caseAlwaysStatement(AlwaysStatement object)
      {
        return createAlwaysStatementAdapter();
      }
      @Override
      public Adapter caseWhenHoldsStatement(WhenHoldsStatement object)
      {
        return createWhenHoldsStatementAdapter();
      }
      @Override
      public Adapter caseWhenOccursStatment(WhenOccursStatment object)
      {
        return createWhenOccursStatmentAdapter();
      }
      @Override
      public Adapter caseWheneverOccursStatement(WheneverOccursStatement object)
      {
        return createWheneverOccursStatementAdapter();
      }
      @Override
      public Adapter caseWheneverBecomesTrueStatement(WheneverBecomesTrueStatement object)
      {
        return createWheneverBecomesTrueStatementAdapter();
      }
      @Override
      public Adapter caseWheneverHoldsStatement(WheneverHoldsStatement object)
      {
        return createWheneverHoldsStatementAdapter();
      }
      @Override
      public Adapter caseWheneverImpliesStatement(WheneverImpliesStatement object)
      {
        return createWheneverImpliesStatementAdapter();
      }
      @Override
      public Adapter casePeriodicStatement(PeriodicStatement object)
      {
        return createPeriodicStatementAdapter();
      }
      @Override
      public Adapter caseSporadicStatement(SporadicStatement object)
      {
        return createSporadicStatementAdapter();
      }
      @Override
      public Adapter caseClosedTimeInterval(ClosedTimeInterval object)
      {
        return createClosedTimeIntervalAdapter();
      }
      @Override
      public Adapter caseOpenLeftTimeInterval(OpenLeftTimeInterval object)
      {
        return createOpenLeftTimeIntervalAdapter();
      }
      @Override
      public Adapter caseOpenRightTimeInterval(OpenRightTimeInterval object)
      {
        return createOpenRightTimeIntervalAdapter();
      }
      @Override
      public Adapter caseOpenTimeInterval(OpenTimeInterval object)
      {
        return createOpenTimeIntervalAdapter();
      }
      @Override
      public Adapter caseMNSynchStatement(MNSynchStatement object)
      {
        return createMNSynchStatementAdapter();
      }
      @Override
      public Adapter caseCalenStatement(CalenStatement object)
      {
        return createCalenStatementAdapter();
      }
      @Override
      public Adapter caseAsynchStatement(AsynchStatement object)
      {
        return createAsynchStatementAdapter();
      }
      @Override
      public Adapter caseLatchedStatement(LatchedStatement object)
      {
        return createLatchedStatementAdapter();
      }
      @Override
      public Adapter caseNodeEq(NodeEq object)
      {
        return createNodeEqAdapter();
      }
      @Override
      public Adapter caseNodeLemma(NodeLemma object)
      {
        return createNodeLemmaAdapter();
      }
      @Override
      public Adapter caseArrayType(ArrayType object)
      {
        return createArrayTypeAdapter();
      }
      @Override
      public Adapter casePrimType(PrimType object)
      {
        return createPrimTypeAdapter();
      }
      @Override
      public Adapter caseForallExpr(ForallExpr object)
      {
        return createForallExprAdapter();
      }
      @Override
      public Adapter caseExistsExpr(ExistsExpr object)
      {
        return createExistsExprAdapter();
      }
      @Override
      public Adapter caseFlatmapExpr(FlatmapExpr object)
      {
        return createFlatmapExprAdapter();
      }
      @Override
      public Adapter caseFoldLeftExpr(FoldLeftExpr object)
      {
        return createFoldLeftExprAdapter();
      }
      @Override
      public Adapter caseFoldRightExpr(FoldRightExpr object)
      {
        return createFoldRightExprAdapter();
      }
      @Override
      public Adapter caseBinaryExpr(BinaryExpr object)
      {
        return createBinaryExprAdapter();
      }
      @Override
      public Adapter caseUnaryExpr(UnaryExpr object)
      {
        return createUnaryExprAdapter();
      }
      @Override
      public Adapter caseIfThenElseExpr(IfThenElseExpr object)
      {
        return createIfThenElseExprAdapter();
      }
      @Override
      public Adapter caseThisRef(ThisRef object)
      {
        return createThisRefAdapter();
      }
      @Override
      public Adapter casePrevExpr(PrevExpr object)
      {
        return createPrevExprAdapter();
      }
      @Override
      public Adapter caseGetPropertyExpr(GetPropertyExpr object)
      {
        return createGetPropertyExprAdapter();
      }
      @Override
      public Adapter caseArrayUpdateExpr(ArrayUpdateExpr object)
      {
        return createArrayUpdateExprAdapter();
      }
      @Override
      public Adapter caseRecordUpdateExpr(RecordUpdateExpr object)
      {
        return createRecordUpdateExprAdapter();
      }
      @Override
      public Adapter caseArraySubExpr(ArraySubExpr object)
      {
        return createArraySubExprAdapter();
      }
      @Override
      public Adapter caseTagExpr(TagExpr object)
      {
        return createTagExprAdapter();
      }
      @Override
      public Adapter caseSelectionExpr(SelectionExpr object)
      {
        return createSelectionExprAdapter();
      }
      @Override
      public Adapter caseNamedElmExpr(NamedElmExpr object)
      {
        return createNamedElmExprAdapter();
      }
      @Override
      public Adapter caseTimeExpr(TimeExpr object)
      {
        return createTimeExprAdapter();
      }
      @Override
      public Adapter caseIndicesExpr(IndicesExpr object)
      {
        return createIndicesExprAdapter();
      }
      @Override
      public Adapter caseCallExpr(CallExpr object)
      {
        return createCallExprAdapter();
      }
      @Override
      public Adapter caseRecordLitExpr(RecordLitExpr object)
      {
        return createRecordLitExprAdapter();
      }
      @Override
      public Adapter caseEnumLitExpr(EnumLitExpr object)
      {
        return createEnumLitExprAdapter();
      }
      @Override
      public Adapter caseIntLitExpr(IntLitExpr object)
      {
        return createIntLitExprAdapter();
      }
      @Override
      public Adapter casePreExpr(PreExpr object)
      {
        return createPreExprAdapter();
      }
      @Override
      public Adapter caseEventExpr(EventExpr object)
      {
        return createEventExprAdapter();
      }
      @Override
      public Adapter caseLatchedExpr(LatchedExpr object)
      {
        return createLatchedExprAdapter();
      }
      @Override
      public Adapter caseTimeOfExpr(TimeOfExpr object)
      {
        return createTimeOfExprAdapter();
      }
      @Override
      public Adapter caseTimeRiseExpr(TimeRiseExpr object)
      {
        return createTimeRiseExprAdapter();
      }
      @Override
      public Adapter caseTimeFallExpr(TimeFallExpr object)
      {
        return createTimeFallExprAdapter();
      }
      @Override
      public Adapter caseRealLitExpr(RealLitExpr object)
      {
        return createRealLitExprAdapter();
      }
      @Override
      public Adapter caseBoolLitExpr(BoolLitExpr object)
      {
        return createBoolLitExprAdapter();
      }
      @Override
      public Adapter caseFloorCast(FloorCast object)
      {
        return createFloorCastAdapter();
      }
      @Override
      public Adapter caseRealCast(RealCast object)
      {
        return createRealCastAdapter();
      }
      @Override
      public Adapter caseElement(Element object)
      {
        return createElementAdapter();
      }
      @Override
      public Adapter caseNamedElement(NamedElement object)
      {
        return createNamedElementAdapter();
      }
      @Override
      public Adapter caseAnnexLibrary(AnnexLibrary object)
      {
        return createAnnexLibraryAdapter();
      }
      @Override
      public Adapter caseModalElement(ModalElement object)
      {
        return createModalElementAdapter();
      }
      @Override
      public Adapter caseAnnexSubclause(AnnexSubclause object)
      {
        return createAnnexSubclauseAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
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
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
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
  public Adapter createAgreeLibraryAdapter()
  {
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
  public Adapter createAgreeSubclauseAdapter()
  {
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
  public Adapter createContractAdapter()
  {
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
  public Adapter createSpecStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NamedSpecStatement <em>Named Spec Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.NamedSpecStatement
   * @generated
   */
  public Adapter createNamedSpecStatementAdapter()
  {
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
  public Adapter createPatternStatementAdapter()
  {
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
  public Adapter createWhenStatementAdapter()
  {
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
  public Adapter createWheneverStatementAdapter()
  {
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
  public Adapter createRealTimeStatementAdapter()
  {
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
  public Adapter createTimeIntervalAdapter()
  {
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
  public Adapter createSynchStatementAdapter()
  {
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
  public Adapter createOrderStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.Abstraction <em>Abstraction</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.Abstraction
   * @generated
   */
  public Adapter createAbstractionAdapter()
  {
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
  public Adapter createPropertyStatementAdapter()
  {
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
  public Adapter createConstStatementAdapter()
  {
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
  public Adapter createEqStatementAdapter()
  {
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
  public Adapter createInputStatementAdapter()
  {
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
  public Adapter createAssignStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FnDef <em>Fn Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FnDef
   * @generated
   */
  public Adapter createFnDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDef <em>Library Fn Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDef
   * @generated
   */
  public Adapter createLibraryFnDefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef <em>Linearization Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef
   * @generated
   */
  public Adapter createLinearizationDefAdapter()
  {
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
  public Adapter createLinearizationIntervalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NodeDef <em>Node Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.NodeDef
   * @generated
   */
  public Adapter createNodeDefAdapter()
  {
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
  public Adapter createNodeBodyExprAdapter()
  {
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
  public Adapter createNodeStmtAdapter()
  {
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
  public Adapter createArgAdapter()
  {
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
  public Adapter createTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordDef <em>Record Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.RecordDef
   * @generated
   */
  public Adapter createRecordDefAdapter()
  {
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
  public Adapter createEnumStatementAdapter()
  {
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
  public Adapter createExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ComponentRef <em>Component Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ComponentRef
   * @generated
   */
  public Adapter createComponentRefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr <em>Array Literal Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr
   * @generated
   */
  public Adapter createArrayLiteralExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.DoubleDotRef <em>Double Dot Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.DoubleDotRef
   * @generated
   */
  public Adapter createDoubleDotRefAdapter()
  {
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
  public Adapter createNamedIDAdapter()
  {
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
  public Adapter createAgreeContractLibraryAdapter()
  {
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
  public Adapter createAgreeContractSubclauseAdapter()
  {
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
  public Adapter createAgreeContractAdapter()
  {
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
  public Adapter createInitialStatementAdapter()
  {
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
  public Adapter createParamStatementAdapter()
  {
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
  public Adapter createLiftStatementAdapter()
  {
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
  public Adapter createConnectionStatementAdapter()
  {
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
  public Adapter createAssumeStatementAdapter()
  {
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
  public Adapter createGuaranteeStatementAdapter()
  {
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
  public Adapter createAssertStatementAdapter()
  {
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
  public Adapter createLemmaStatementAdapter()
  {
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
  public Adapter createAlwaysStatementAdapter()
  {
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
  public Adapter createWhenHoldsStatementAdapter()
  {
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
  public Adapter createWhenOccursStatmentAdapter()
  {
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
  public Adapter createWheneverOccursStatementAdapter()
  {
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
  public Adapter createWheneverBecomesTrueStatementAdapter()
  {
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
  public Adapter createWheneverHoldsStatementAdapter()
  {
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
  public Adapter createWheneverImpliesStatementAdapter()
  {
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
  public Adapter createPeriodicStatementAdapter()
  {
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
  public Adapter createSporadicStatementAdapter()
  {
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
  public Adapter createClosedTimeIntervalAdapter()
  {
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
  public Adapter createOpenLeftTimeIntervalAdapter()
  {
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
  public Adapter createOpenRightTimeIntervalAdapter()
  {
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
  public Adapter createOpenTimeIntervalAdapter()
  {
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
  public Adapter createMNSynchStatementAdapter()
  {
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
  public Adapter createCalenStatementAdapter()
  {
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
  public Adapter createAsynchStatementAdapter()
  {
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
  public Adapter createLatchedStatementAdapter()
  {
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
  public Adapter createNodeEqAdapter()
  {
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
  public Adapter createNodeLemmaAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ArrayType <em>Array Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ArrayType
   * @generated
   */
  public Adapter createArrayTypeAdapter()
  {
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
  public Adapter createPrimTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ForallExpr <em>Forall Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ForallExpr
   * @generated
   */
  public Adapter createForallExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ExistsExpr <em>Exists Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ExistsExpr
   * @generated
   */
  public Adapter createExistsExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FlatmapExpr <em>Flatmap Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FlatmapExpr
   * @generated
   */
  public Adapter createFlatmapExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr <em>Fold Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr
   * @generated
   */
  public Adapter createFoldLeftExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr <em>Fold Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr
   * @generated
   */
  public Adapter createFoldRightExprAdapter()
  {
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
  public Adapter createBinaryExprAdapter()
  {
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
  public Adapter createUnaryExprAdapter()
  {
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
  public Adapter createIfThenElseExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ThisRef <em>This Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ThisRef
   * @generated
   */
  public Adapter createThisRefAdapter()
  {
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
  public Adapter createPrevExprAdapter()
  {
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
  public Adapter createGetPropertyExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr <em>Array Update Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr
   * @generated
   */
  public Adapter createArrayUpdateExprAdapter()
  {
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
  public Adapter createRecordUpdateExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ArraySubExpr <em>Array Sub Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ArraySubExpr
   * @generated
   */
  public Adapter createArraySubExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.TagExpr <em>Tag Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.TagExpr
   * @generated
   */
  public Adapter createTagExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr <em>Selection Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.SelectionExpr
   * @generated
   */
  public Adapter createSelectionExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.NamedElmExpr <em>Named Elm Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.NamedElmExpr
   * @generated
   */
  public Adapter createNamedElmExprAdapter()
  {
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
  public Adapter createTimeExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.IndicesExpr <em>Indices Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.IndicesExpr
   * @generated
   */
  public Adapter createIndicesExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.CallExpr <em>Call Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.CallExpr
   * @generated
   */
  public Adapter createCallExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr <em>Record Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.RecordLitExpr
   * @generated
   */
  public Adapter createRecordLitExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.EnumLitExpr <em>Enum Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.EnumLitExpr
   * @generated
   */
  public Adapter createEnumLitExprAdapter()
  {
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
  public Adapter createIntLitExprAdapter()
  {
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
  public Adapter createPreExprAdapter()
  {
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
  public Adapter createEventExprAdapter()
  {
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
  public Adapter createLatchedExprAdapter()
  {
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
  public Adapter createTimeOfExprAdapter()
  {
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
  public Adapter createTimeRiseExprAdapter()
  {
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
  public Adapter createTimeFallExprAdapter()
  {
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
  public Adapter createRealLitExprAdapter()
  {
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
  public Adapter createBoolLitExprAdapter()
  {
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
  public Adapter createFloorCastAdapter()
  {
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
  public Adapter createRealCastAdapter()
  {
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
  public Adapter createElementAdapter()
  {
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
  public Adapter createNamedElementAdapter()
  {
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
  public Adapter createAnnexLibraryAdapter()
  {
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
  public Adapter createModalElementAdapter()
  {
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
  public Adapter createAnnexSubclauseAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //AgreeAdapterFactory
