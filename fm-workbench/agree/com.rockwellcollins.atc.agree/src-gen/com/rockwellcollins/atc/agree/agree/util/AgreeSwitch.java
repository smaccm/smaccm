/**
 */
package com.rockwellcollins.atc.agree.agree.util;

import com.rockwellcollins.atc.agree.agree.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModalElement;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage
 * @generated
 */
public class AgreeSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static AgreePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = AgreePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case AgreePackage.AGREE_LIBRARY:
      {
        AgreeLibrary agreeLibrary = (AgreeLibrary)theEObject;
        T result = caseAgreeLibrary(agreeLibrary);
        if (result == null) result = caseAnnexLibrary(agreeLibrary);
        if (result == null) result = caseNamedElement(agreeLibrary);
        if (result == null) result = caseElement(agreeLibrary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.AGREE_SUBCLAUSE:
      {
        AgreeSubclause agreeSubclause = (AgreeSubclause)theEObject;
        T result = caseAgreeSubclause(agreeSubclause);
        if (result == null) result = caseAnnexSubclause(agreeSubclause);
        if (result == null) result = caseModalElement(agreeSubclause);
        if (result == null) result = caseNamedElement(agreeSubclause);
        if (result == null) result = caseElement(agreeSubclause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CONTRACT:
      {
        Contract contract = (Contract)theEObject;
        T result = caseContract(contract);
        if (result == null) result = caseElement(contract);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.SPEC_STATEMENT:
      {
        SpecStatement specStatement = (SpecStatement)theEObject;
        T result = caseSpecStatement(specStatement);
        if (result == null) result = caseElement(specStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.SYNCH_STATEMENT:
      {
        SynchStatement synchStatement = (SynchStatement)theEObject;
        T result = caseSynchStatement(synchStatement);
        if (result == null) result = caseSpecStatement(synchStatement);
        if (result == null) result = caseElement(synchStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ORDER_STATEMENT:
      {
        OrderStatement orderStatement = (OrderStatement)theEObject;
        T result = caseOrderStatement(orderStatement);
        if (result == null) result = caseSpecStatement(orderStatement);
        if (result == null) result = caseElement(orderStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CALL_DEF:
      {
        CallDef callDef = (CallDef)theEObject;
        T result = caseCallDef(callDef);
        if (result == null) result = caseNamedElement(callDef);
        if (result == null) result = caseElement(callDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PROPERTY_STATEMENT:
      {
        PropertyStatement propertyStatement = (PropertyStatement)theEObject;
        T result = casePropertyStatement(propertyStatement);
        if (result == null) result = caseNamedElement(propertyStatement);
        if (result == null) result = caseSpecStatement(propertyStatement);
        if (result == null) result = caseElement(propertyStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CONST_STATEMENT:
      {
        ConstStatement constStatement = (ConstStatement)theEObject;
        T result = caseConstStatement(constStatement);
        if (result == null) result = caseNamedElement(constStatement);
        if (result == null) result = caseSpecStatement(constStatement);
        if (result == null) result = caseElement(constStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.EQ_STATEMENT:
      {
        EqStatement eqStatement = (EqStatement)theEObject;
        T result = caseEqStatement(eqStatement);
        if (result == null) result = caseSpecStatement(eqStatement);
        if (result == null) result = caseElement(eqStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ASSIGN_STATEMENT:
      {
        AssignStatement assignStatement = (AssignStatement)theEObject;
        T result = caseAssignStatement(assignStatement);
        if (result == null) result = caseSpecStatement(assignStatement);
        if (result == null) result = caseElement(assignStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FN_DEF_EXPR:
      {
        FnDefExpr fnDefExpr = (FnDefExpr)theEObject;
        T result = caseFnDefExpr(fnDefExpr);
        if (result == null) result = caseSpecStatement(fnDefExpr);
        if (result == null) result = caseCallDef(fnDefExpr);
        if (result == null) result = caseNamedElement(fnDefExpr);
        if (result == null) result = caseElement(fnDefExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_DEF_EXPR:
      {
        NodeDefExpr nodeDefExpr = (NodeDefExpr)theEObject;
        T result = caseNodeDefExpr(nodeDefExpr);
        if (result == null) result = caseSpecStatement(nodeDefExpr);
        if (result == null) result = caseCallDef(nodeDefExpr);
        if (result == null) result = caseNamedElement(nodeDefExpr);
        if (result == null) result = caseElement(nodeDefExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_BODY_EXPR:
      {
        NodeBodyExpr nodeBodyExpr = (NodeBodyExpr)theEObject;
        T result = caseNodeBodyExpr(nodeBodyExpr);
        if (result == null) result = caseElement(nodeBodyExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_STMT:
      {
        NodeStmt nodeStmt = (NodeStmt)theEObject;
        T result = caseNodeStmt(nodeStmt);
        if (result == null) result = caseElement(nodeStmt);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ARG:
      {
        Arg arg = (Arg)theEObject;
        T result = caseArg(arg);
        if (result == null) result = caseNamedElement(arg);
        if (result == null) result = caseElement(arg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TYPE:
      {
        Type type = (Type)theEObject;
        T result = caseType(type);
        if (result == null) result = caseElement(type);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.RECORD_DEF_EXPR:
      {
        RecordDefExpr recordDefExpr = (RecordDefExpr)theEObject;
        T result = caseRecordDefExpr(recordDefExpr);
        if (result == null) result = caseNamedElement(recordDefExpr);
        if (result == null) result = caseSpecStatement(recordDefExpr);
        if (result == null) result = caseElement(recordDefExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.EXPR:
      {
        Expr expr = (Expr)theEObject;
        T result = caseExpr(expr);
        if (result == null) result = caseElement(expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.COMPLEX_EXPR:
      {
        ComplexExpr complexExpr = (ComplexExpr)theEObject;
        T result = caseComplexExpr(complexExpr);
        if (result == null) result = caseExpr(complexExpr);
        if (result == null) result = caseElement(complexExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NESTED_DOT_ID:
      {
        NestedDotID nestedDotID = (NestedDotID)theEObject;
        T result = caseNestedDotID(nestedDotID);
        if (result == null) result = caseComplexExpr(nestedDotID);
        if (result == null) result = caseExpr(nestedDotID);
        if (result == null) result = caseElement(nestedDotID);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.AGREE_CONTRACT_LIBRARY:
      {
        AgreeContractLibrary agreeContractLibrary = (AgreeContractLibrary)theEObject;
        T result = caseAgreeContractLibrary(agreeContractLibrary);
        if (result == null) result = caseAgreeLibrary(agreeContractLibrary);
        if (result == null) result = caseAnnexLibrary(agreeContractLibrary);
        if (result == null) result = caseNamedElement(agreeContractLibrary);
        if (result == null) result = caseElement(agreeContractLibrary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.AGREE_CONTRACT_SUBCLAUSE:
      {
        AgreeContractSubclause agreeContractSubclause = (AgreeContractSubclause)theEObject;
        T result = caseAgreeContractSubclause(agreeContractSubclause);
        if (result == null) result = caseAgreeSubclause(agreeContractSubclause);
        if (result == null) result = caseAnnexSubclause(agreeContractSubclause);
        if (result == null) result = caseModalElement(agreeContractSubclause);
        if (result == null) result = caseNamedElement(agreeContractSubclause);
        if (result == null) result = caseElement(agreeContractSubclause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.AGREE_CONTRACT:
      {
        AgreeContract agreeContract = (AgreeContract)theEObject;
        T result = caseAgreeContract(agreeContract);
        if (result == null) result = caseContract(agreeContract);
        if (result == null) result = caseElement(agreeContract);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ASSUME_STATEMENT:
      {
        AssumeStatement assumeStatement = (AssumeStatement)theEObject;
        T result = caseAssumeStatement(assumeStatement);
        if (result == null) result = caseSpecStatement(assumeStatement);
        if (result == null) result = caseElement(assumeStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.GUARANTEE_STATEMENT:
      {
        GuaranteeStatement guaranteeStatement = (GuaranteeStatement)theEObject;
        T result = caseGuaranteeStatement(guaranteeStatement);
        if (result == null) result = caseSpecStatement(guaranteeStatement);
        if (result == null) result = caseElement(guaranteeStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ASSERT_STATEMENT:
      {
        AssertStatement assertStatement = (AssertStatement)theEObject;
        T result = caseAssertStatement(assertStatement);
        if (result == null) result = caseSpecStatement(assertStatement);
        if (result == null) result = caseElement(assertStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.INITIAL_STATEMENT:
      {
        InitialStatement initialStatement = (InitialStatement)theEObject;
        T result = caseInitialStatement(initialStatement);
        if (result == null) result = caseSpecStatement(initialStatement);
        if (result == null) result = caseElement(initialStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PARAM_STATEMENT:
      {
        ParamStatement paramStatement = (ParamStatement)theEObject;
        T result = caseParamStatement(paramStatement);
        if (result == null) result = caseSpecStatement(paramStatement);
        if (result == null) result = caseElement(paramStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LEMMA_STATEMENT:
      {
        LemmaStatement lemmaStatement = (LemmaStatement)theEObject;
        T result = caseLemmaStatement(lemmaStatement);
        if (result == null) result = caseSpecStatement(lemmaStatement);
        if (result == null) result = caseElement(lemmaStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LIFT_STATEMENT:
      {
        LiftStatement liftStatement = (LiftStatement)theEObject;
        T result = caseLiftStatement(liftStatement);
        if (result == null) result = caseSpecStatement(liftStatement);
        if (result == null) result = caseElement(liftStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CONNECTION_STATEMENT:
      {
        ConnectionStatement connectionStatement = (ConnectionStatement)theEObject;
        T result = caseConnectionStatement(connectionStatement);
        if (result == null) result = caseSpecStatement(connectionStatement);
        if (result == null) result = caseElement(connectionStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.MN_SYNCH_STATEMENT:
      {
        MNSynchStatement mnSynchStatement = (MNSynchStatement)theEObject;
        T result = caseMNSynchStatement(mnSynchStatement);
        if (result == null) result = caseSynchStatement(mnSynchStatement);
        if (result == null) result = caseSpecStatement(mnSynchStatement);
        if (result == null) result = caseElement(mnSynchStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CALEN_STATEMENT:
      {
        CalenStatement calenStatement = (CalenStatement)theEObject;
        T result = caseCalenStatement(calenStatement);
        if (result == null) result = caseSynchStatement(calenStatement);
        if (result == null) result = caseSpecStatement(calenStatement);
        if (result == null) result = caseElement(calenStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ASYNCH_STATEMENT:
      {
        AsynchStatement asynchStatement = (AsynchStatement)theEObject;
        T result = caseAsynchStatement(asynchStatement);
        if (result == null) result = caseSynchStatement(asynchStatement);
        if (result == null) result = caseSpecStatement(asynchStatement);
        if (result == null) result = caseElement(asynchStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LATCHED_STATEMENT:
      {
        LatchedStatement latchedStatement = (LatchedStatement)theEObject;
        T result = caseLatchedStatement(latchedStatement);
        if (result == null) result = caseSynchStatement(latchedStatement);
        if (result == null) result = caseSpecStatement(latchedStatement);
        if (result == null) result = caseElement(latchedStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_EQ:
      {
        NodeEq nodeEq = (NodeEq)theEObject;
        T result = caseNodeEq(nodeEq);
        if (result == null) result = caseNodeStmt(nodeEq);
        if (result == null) result = caseElement(nodeEq);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_LEMMA:
      {
        NodeLemma nodeLemma = (NodeLemma)theEObject;
        T result = caseNodeLemma(nodeLemma);
        if (result == null) result = caseNodeStmt(nodeLemma);
        if (result == null) result = caseElement(nodeLemma);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PRIM_TYPE:
      {
        PrimType primType = (PrimType)theEObject;
        T result = casePrimType(primType);
        if (result == null) result = caseType(primType);
        if (result == null) result = caseElement(primType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.RECORD_TYPE:
      {
        RecordType recordType = (RecordType)theEObject;
        T result = caseRecordType(recordType);
        if (result == null) result = caseType(recordType);
        if (result == null) result = caseElement(recordType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.BINARY_EXPR:
      {
        BinaryExpr binaryExpr = (BinaryExpr)theEObject;
        T result = caseBinaryExpr(binaryExpr);
        if (result == null) result = caseExpr(binaryExpr);
        if (result == null) result = caseElement(binaryExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.UNARY_EXPR:
      {
        UnaryExpr unaryExpr = (UnaryExpr)theEObject;
        T result = caseUnaryExpr(unaryExpr);
        if (result == null) result = caseExpr(unaryExpr);
        if (result == null) result = caseElement(unaryExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.IF_THEN_ELSE_EXPR:
      {
        IfThenElseExpr ifThenElseExpr = (IfThenElseExpr)theEObject;
        T result = caseIfThenElseExpr(ifThenElseExpr);
        if (result == null) result = caseExpr(ifThenElseExpr);
        if (result == null) result = caseElement(ifThenElseExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PREV_EXPR:
      {
        PrevExpr prevExpr = (PrevExpr)theEObject;
        T result = casePrevExpr(prevExpr);
        if (result == null) result = caseExpr(prevExpr);
        if (result == null) result = caseElement(prevExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.GET_PROPERTY_EXPR:
      {
        GetPropertyExpr getPropertyExpr = (GetPropertyExpr)theEObject;
        T result = caseGetPropertyExpr(getPropertyExpr);
        if (result == null) result = caseExpr(getPropertyExpr);
        if (result == null) result = caseElement(getPropertyExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.RECORD_UPDATE_EXPR:
      {
        RecordUpdateExpr recordUpdateExpr = (RecordUpdateExpr)theEObject;
        T result = caseRecordUpdateExpr(recordUpdateExpr);
        if (result == null) result = caseExpr(recordUpdateExpr);
        if (result == null) result = caseElement(recordUpdateExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.INT_LIT_EXPR:
      {
        IntLitExpr intLitExpr = (IntLitExpr)theEObject;
        T result = caseIntLitExpr(intLitExpr);
        if (result == null) result = caseExpr(intLitExpr);
        if (result == null) result = caseElement(intLitExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PRE_EXPR:
      {
        PreExpr preExpr = (PreExpr)theEObject;
        T result = casePreExpr(preExpr);
        if (result == null) result = caseExpr(preExpr);
        if (result == null) result = caseElement(preExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.EVENT_EXPR:
      {
        EventExpr eventExpr = (EventExpr)theEObject;
        T result = caseEventExpr(eventExpr);
        if (result == null) result = caseExpr(eventExpr);
        if (result == null) result = caseElement(eventExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.REAL_LIT_EXPR:
      {
        RealLitExpr realLitExpr = (RealLitExpr)theEObject;
        T result = caseRealLitExpr(realLitExpr);
        if (result == null) result = caseExpr(realLitExpr);
        if (result == null) result = caseElement(realLitExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.BOOL_LIT_EXPR:
      {
        BoolLitExpr boolLitExpr = (BoolLitExpr)theEObject;
        T result = caseBoolLitExpr(boolLitExpr);
        if (result == null) result = caseExpr(boolLitExpr);
        if (result == null) result = caseElement(boolLitExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.THIS_EXPR:
      {
        ThisExpr thisExpr = (ThisExpr)theEObject;
        T result = caseThisExpr(thisExpr);
        if (result == null) result = caseExpr(thisExpr);
        if (result == null) result = caseElement(thisExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FLOOR_CAST:
      {
        FloorCast floorCast = (FloorCast)theEObject;
        T result = caseFloorCast(floorCast);
        if (result == null) result = caseExpr(floorCast);
        if (result == null) result = caseElement(floorCast);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.REAL_CAST:
      {
        RealCast realCast = (RealCast)theEObject;
        T result = caseRealCast(realCast);
        if (result == null) result = caseExpr(realCast);
        if (result == null) result = caseElement(realCast);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FN_CALL_EXPR:
      {
        FnCallExpr fnCallExpr = (FnCallExpr)theEObject;
        T result = caseFnCallExpr(fnCallExpr);
        if (result == null) result = caseComplexExpr(fnCallExpr);
        if (result == null) result = caseExpr(fnCallExpr);
        if (result == null) result = caseElement(fnCallExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.RECORD_EXPR:
      {
        RecordExpr recordExpr = (RecordExpr)theEObject;
        T result = caseRecordExpr(recordExpr);
        if (result == null) result = caseComplexExpr(recordExpr);
        if (result == null) result = caseExpr(recordExpr);
        if (result == null) result = caseElement(recordExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Library</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Library</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAgreeLibrary(AgreeLibrary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Subclause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Subclause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAgreeSubclause(AgreeSubclause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Contract</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Contract</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContract(Contract object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Spec Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Spec Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpecStatement(SpecStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Synch Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Synch Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSynchStatement(SynchStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderStatement(OrderStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Call Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCallDef(CallDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyStatement(PropertyStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Const Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Const Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstStatement(ConstStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Eq Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Eq Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEqStatement(EqStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assign Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assign Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssignStatement(AssignStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fn Def Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fn Def Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFnDefExpr(FnDefExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Def Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Def Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeDefExpr(NodeDefExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Body Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Body Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeBodyExpr(NodeBodyExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Stmt</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Stmt</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeStmt(NodeStmt object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Arg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Arg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArg(Arg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseType(Type object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Record Def Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Def Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordDefExpr(RecordDefExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpr(Expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Complex Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Complex Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComplexExpr(ComplexExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Nested Dot ID</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Nested Dot ID</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNestedDotID(NestedDotID object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Contract Library</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Contract Library</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAgreeContractLibrary(AgreeContractLibrary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Contract Subclause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Contract Subclause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAgreeContractSubclause(AgreeContractSubclause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Contract</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Contract</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAgreeContract(AgreeContract object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assume Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assume Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssumeStatement(AssumeStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Guarantee Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Guarantee Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGuaranteeStatement(GuaranteeStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assert Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assert Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssertStatement(AssertStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Initial Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Initial Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInitialStatement(InitialStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Param Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Param Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParamStatement(ParamStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Lemma Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Lemma Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLemmaStatement(LemmaStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Lift Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Lift Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiftStatement(LiftStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Connection Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Connection Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConnectionStatement(ConnectionStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>MN Synch Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>MN Synch Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMNSynchStatement(MNSynchStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Calen Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Calen Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCalenStatement(CalenStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Asynch Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Asynch Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAsynchStatement(AsynchStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Latched Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Latched Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLatchedStatement(LatchedStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Eq</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Eq</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeEq(NodeEq object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Lemma</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Lemma</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeLemma(NodeLemma object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prim Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prim Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrimType(PrimType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Record Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordType(RecordType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Binary Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Binary Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBinaryExpr(BinaryExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unary Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnaryExpr(UnaryExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Then Else Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Then Else Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfThenElseExpr(IfThenElseExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prev Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prev Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrevExpr(PrevExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Get Property Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Get Property Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGetPropertyExpr(GetPropertyExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Record Update Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Update Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordUpdateExpr(RecordUpdateExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Lit Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Lit Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntLitExpr(IntLitExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pre Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pre Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePreExpr(PreExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Event Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Event Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEventExpr(EventExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Real Lit Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Lit Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealLitExpr(RealLitExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bool Lit Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bool Lit Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBoolLitExpr(BoolLitExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>This Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>This Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseThisExpr(ThisExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Floor Cast</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Floor Cast</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFloorCast(FloorCast object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Real Cast</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Cast</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealCast(RealCast object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fn Call Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fn Call Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFnCallExpr(FnCallExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Record Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordExpr(RecordExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElement(Element object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedElement(NamedElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annex Library</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annex Library</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnnexLibrary(AnnexLibrary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Modal Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modal Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModalElement(ModalElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annex Subclause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annex Subclause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnnexSubclause(AnnexSubclause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //AgreeSwitch
