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
      public Adapter caseCallDef(CallDef object)
      {
        return createCallDefAdapter();
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
      public Adapter caseAssignStatement(AssignStatement object)
      {
        return createAssignStatementAdapter();
      }
      @Override
      public Adapter caseFnDefExpr(FnDefExpr object)
      {
        return createFnDefExprAdapter();
      }
      @Override
      public Adapter caseNodeDefExpr(NodeDefExpr object)
      {
        return createNodeDefExprAdapter();
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
      public Adapter caseRecordDefExpr(RecordDefExpr object)
      {
        return createRecordDefExprAdapter();
      }
      @Override
      public Adapter caseExpr(Expr object)
      {
        return createExprAdapter();
      }
      @Override
      public Adapter caseComplexExpr(ComplexExpr object)
      {
        return createComplexExprAdapter();
      }
      @Override
      public Adapter caseNestedDotID(NestedDotID object)
      {
        return createNestedDotIDAdapter();
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
      public Adapter caseLemmaStatement(LemmaStatement object)
      {
        return createLemmaStatementAdapter();
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
      public Adapter casePrimType(PrimType object)
      {
        return createPrimTypeAdapter();
      }
      @Override
      public Adapter caseRecordType(RecordType object)
      {
        return createRecordTypeAdapter();
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
      public Adapter caseRecordUpdateExpr(RecordUpdateExpr object)
      {
        return createRecordUpdateExprAdapter();
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
      public Adapter caseThisExpr(ThisExpr object)
      {
        return createThisExprAdapter();
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
      public Adapter caseFnCallExpr(FnCallExpr object)
      {
        return createFnCallExprAdapter();
      }
      @Override
      public Adapter caseRecordExpr(RecordExpr object)
      {
        return createRecordExprAdapter();
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.CallDef <em>Call Def</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.CallDef
   * @generated
   */
  public Adapter createCallDefAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr <em>Fn Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FnDefExpr
   * @generated
   */
  public Adapter createFnDefExprAdapter()
  {
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
  public Adapter createNodeDefExprAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordDefExpr <em>Record Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.RecordDefExpr
   * @generated
   */
  public Adapter createRecordDefExprAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ComplexExpr <em>Complex Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ComplexExpr
   * @generated
   */
  public Adapter createComplexExprAdapter()
  {
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
  public Adapter createNestedDotIDAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.RecordType <em>Record Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.RecordType
   * @generated
   */
  public Adapter createRecordTypeAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.ThisExpr <em>This Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.ThisExpr
   * @generated
   */
  public Adapter createThisExprAdapter()
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
   * Creates a new adapter for an object of class '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr <em>Fn Call Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.rockwellcollins.atc.agree.agree.FnCallExpr
   * @generated
   */
  public Adapter createFnCallExprAdapter()
  {
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
  public Adapter createRecordExprAdapter()
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
