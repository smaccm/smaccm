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
      case AgreePackage.NAMED_SPEC_STATEMENT:
      {
        NamedSpecStatement namedSpecStatement = (NamedSpecStatement)theEObject;
        T result = caseNamedSpecStatement(namedSpecStatement);
        if (result == null) result = caseNamedElement(namedSpecStatement);
        if (result == null) result = caseSpecStatement(namedSpecStatement);
        if (result == null) result = caseElement(namedSpecStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PATTERN_STATEMENT:
      {
        PatternStatement patternStatement = (PatternStatement)theEObject;
        T result = casePatternStatement(patternStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHEN_STATEMENT:
      {
        WhenStatement whenStatement = (WhenStatement)theEObject;
        T result = caseWhenStatement(whenStatement);
        if (result == null) result = casePatternStatement(whenStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHENEVER_STATEMENT:
      {
        WheneverStatement wheneverStatement = (WheneverStatement)theEObject;
        T result = caseWheneverStatement(wheneverStatement);
        if (result == null) result = casePatternStatement(wheneverStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.REAL_TIME_STATEMENT:
      {
        RealTimeStatement realTimeStatement = (RealTimeStatement)theEObject;
        T result = caseRealTimeStatement(realTimeStatement);
        if (result == null) result = casePatternStatement(realTimeStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TIME_INTERVAL:
      {
        TimeInterval timeInterval = (TimeInterval)theEObject;
        T result = caseTimeInterval(timeInterval);
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
      case AgreePackage.ABSTRACTION:
      {
        Abstraction abstraction = (Abstraction)theEObject;
        T result = caseAbstraction(abstraction);
        if (result == null) result = caseNamedElement(abstraction);
        if (result == null) result = caseElement(abstraction);
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
      case AgreePackage.INPUT_STATEMENT:
      {
        InputStatement inputStatement = (InputStatement)theEObject;
        T result = caseInputStatement(inputStatement);
        if (result == null) result = caseSpecStatement(inputStatement);
        if (result == null) result = caseElement(inputStatement);
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
      case AgreePackage.FN_DEF:
      {
        FnDef fnDef = (FnDef)theEObject;
        T result = caseFnDef(fnDef);
        if (result == null) result = caseSpecStatement(fnDef);
        if (result == null) result = caseAbstraction(fnDef);
        if (result == null) result = caseNamedElement(fnDef);
        if (result == null) result = caseElement(fnDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LIBRARY_FN_DEF:
      {
        LibraryFnDef libraryFnDef = (LibraryFnDef)theEObject;
        T result = caseLibraryFnDef(libraryFnDef);
        if (result == null) result = caseSpecStatement(libraryFnDef);
        if (result == null) result = caseAbstraction(libraryFnDef);
        if (result == null) result = caseNamedElement(libraryFnDef);
        if (result == null) result = caseElement(libraryFnDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LINEARIZATION_DEF:
      {
        LinearizationDef linearizationDef = (LinearizationDef)theEObject;
        T result = caseLinearizationDef(linearizationDef);
        if (result == null) result = caseSpecStatement(linearizationDef);
        if (result == null) result = caseAbstraction(linearizationDef);
        if (result == null) result = caseNamedElement(linearizationDef);
        if (result == null) result = caseElement(linearizationDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LINEARIZATION_INTERVAL:
      {
        LinearizationInterval linearizationInterval = (LinearizationInterval)theEObject;
        T result = caseLinearizationInterval(linearizationInterval);
        if (result == null) result = caseElement(linearizationInterval);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NODE_DEF:
      {
        NodeDef nodeDef = (NodeDef)theEObject;
        T result = caseNodeDef(nodeDef);
        if (result == null) result = caseSpecStatement(nodeDef);
        if (result == null) result = caseAbstraction(nodeDef);
        if (result == null) result = caseNamedElement(nodeDef);
        if (result == null) result = caseElement(nodeDef);
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
      case AgreePackage.RECORD_DEF:
      {
        RecordDef recordDef = (RecordDef)theEObject;
        T result = caseRecordDef(recordDef);
        if (result == null) result = caseNamedElement(recordDef);
        if (result == null) result = caseSpecStatement(recordDef);
        if (result == null) result = caseElement(recordDef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ENUM_STATEMENT:
      {
        EnumStatement enumStatement = (EnumStatement)theEObject;
        T result = caseEnumStatement(enumStatement);
        if (result == null) result = caseNamedElement(enumStatement);
        if (result == null) result = caseSpecStatement(enumStatement);
        if (result == null) result = caseElement(enumStatement);
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
      case AgreePackage.COMPONENT_REF:
      {
        ComponentRef componentRef = (ComponentRef)theEObject;
        T result = caseComponentRef(componentRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ARRAY_LITERAL_EXPR:
      {
        ArrayLiteralExpr arrayLiteralExpr = (ArrayLiteralExpr)theEObject;
        T result = caseArrayLiteralExpr(arrayLiteralExpr);
        if (result == null) result = caseExpr(arrayLiteralExpr);
        if (result == null) result = caseElement(arrayLiteralExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.DOUBLE_DOT_REF:
      {
        DoubleDotRef doubleDotRef = (DoubleDotRef)theEObject;
        T result = caseDoubleDotRef(doubleDotRef);
        if (result == null) result = caseType(doubleDotRef);
        if (result == null) result = caseComponentRef(doubleDotRef);
        if (result == null) result = caseElement(doubleDotRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NAMED_ID:
      {
        NamedID namedID = (NamedID)theEObject;
        T result = caseNamedID(namedID);
        if (result == null) result = caseNamedElement(namedID);
        if (result == null) result = caseElement(namedID);
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
      case AgreePackage.ASSUME_STATEMENT:
      {
        AssumeStatement assumeStatement = (AssumeStatement)theEObject;
        T result = caseAssumeStatement(assumeStatement);
        if (result == null) result = caseNamedSpecStatement(assumeStatement);
        if (result == null) result = caseNamedElement(assumeStatement);
        if (result == null) result = caseSpecStatement(assumeStatement);
        if (result == null) result = caseElement(assumeStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.GUARANTEE_STATEMENT:
      {
        GuaranteeStatement guaranteeStatement = (GuaranteeStatement)theEObject;
        T result = caseGuaranteeStatement(guaranteeStatement);
        if (result == null) result = caseNamedSpecStatement(guaranteeStatement);
        if (result == null) result = caseNamedElement(guaranteeStatement);
        if (result == null) result = caseSpecStatement(guaranteeStatement);
        if (result == null) result = caseElement(guaranteeStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ASSERT_STATEMENT:
      {
        AssertStatement assertStatement = (AssertStatement)theEObject;
        T result = caseAssertStatement(assertStatement);
        if (result == null) result = caseNamedSpecStatement(assertStatement);
        if (result == null) result = caseNamedElement(assertStatement);
        if (result == null) result = caseSpecStatement(assertStatement);
        if (result == null) result = caseElement(assertStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.LEMMA_STATEMENT:
      {
        LemmaStatement lemmaStatement = (LemmaStatement)theEObject;
        T result = caseLemmaStatement(lemmaStatement);
        if (result == null) result = caseNamedSpecStatement(lemmaStatement);
        if (result == null) result = caseNamedElement(lemmaStatement);
        if (result == null) result = caseSpecStatement(lemmaStatement);
        if (result == null) result = caseElement(lemmaStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ALWAYS_STATEMENT:
      {
        AlwaysStatement alwaysStatement = (AlwaysStatement)theEObject;
        T result = caseAlwaysStatement(alwaysStatement);
        if (result == null) result = casePatternStatement(alwaysStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHEN_HOLDS_STATEMENT:
      {
        WhenHoldsStatement whenHoldsStatement = (WhenHoldsStatement)theEObject;
        T result = caseWhenHoldsStatement(whenHoldsStatement);
        if (result == null) result = caseWhenStatement(whenHoldsStatement);
        if (result == null) result = casePatternStatement(whenHoldsStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHEN_OCCURS_STATMENT:
      {
        WhenOccursStatment whenOccursStatment = (WhenOccursStatment)theEObject;
        T result = caseWhenOccursStatment(whenOccursStatment);
        if (result == null) result = caseWhenStatement(whenOccursStatment);
        if (result == null) result = casePatternStatement(whenOccursStatment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHENEVER_OCCURS_STATEMENT:
      {
        WheneverOccursStatement wheneverOccursStatement = (WheneverOccursStatement)theEObject;
        T result = caseWheneverOccursStatement(wheneverOccursStatement);
        if (result == null) result = caseWheneverStatement(wheneverOccursStatement);
        if (result == null) result = casePatternStatement(wheneverOccursStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHENEVER_BECOMES_TRUE_STATEMENT:
      {
        WheneverBecomesTrueStatement wheneverBecomesTrueStatement = (WheneverBecomesTrueStatement)theEObject;
        T result = caseWheneverBecomesTrueStatement(wheneverBecomesTrueStatement);
        if (result == null) result = caseWheneverStatement(wheneverBecomesTrueStatement);
        if (result == null) result = casePatternStatement(wheneverBecomesTrueStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHENEVER_HOLDS_STATEMENT:
      {
        WheneverHoldsStatement wheneverHoldsStatement = (WheneverHoldsStatement)theEObject;
        T result = caseWheneverHoldsStatement(wheneverHoldsStatement);
        if (result == null) result = caseWheneverStatement(wheneverHoldsStatement);
        if (result == null) result = casePatternStatement(wheneverHoldsStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.WHENEVER_IMPLIES_STATEMENT:
      {
        WheneverImpliesStatement wheneverImpliesStatement = (WheneverImpliesStatement)theEObject;
        T result = caseWheneverImpliesStatement(wheneverImpliesStatement);
        if (result == null) result = caseWheneverStatement(wheneverImpliesStatement);
        if (result == null) result = casePatternStatement(wheneverImpliesStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.PERIODIC_STATEMENT:
      {
        PeriodicStatement periodicStatement = (PeriodicStatement)theEObject;
        T result = casePeriodicStatement(periodicStatement);
        if (result == null) result = caseRealTimeStatement(periodicStatement);
        if (result == null) result = casePatternStatement(periodicStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.SPORADIC_STATEMENT:
      {
        SporadicStatement sporadicStatement = (SporadicStatement)theEObject;
        T result = caseSporadicStatement(sporadicStatement);
        if (result == null) result = caseRealTimeStatement(sporadicStatement);
        if (result == null) result = casePatternStatement(sporadicStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CLOSED_TIME_INTERVAL:
      {
        ClosedTimeInterval closedTimeInterval = (ClosedTimeInterval)theEObject;
        T result = caseClosedTimeInterval(closedTimeInterval);
        if (result == null) result = caseTimeInterval(closedTimeInterval);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.OPEN_LEFT_TIME_INTERVAL:
      {
        OpenLeftTimeInterval openLeftTimeInterval = (OpenLeftTimeInterval)theEObject;
        T result = caseOpenLeftTimeInterval(openLeftTimeInterval);
        if (result == null) result = caseTimeInterval(openLeftTimeInterval);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.OPEN_RIGHT_TIME_INTERVAL:
      {
        OpenRightTimeInterval openRightTimeInterval = (OpenRightTimeInterval)theEObject;
        T result = caseOpenRightTimeInterval(openRightTimeInterval);
        if (result == null) result = caseTimeInterval(openRightTimeInterval);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.OPEN_TIME_INTERVAL:
      {
        OpenTimeInterval openTimeInterval = (OpenTimeInterval)theEObject;
        T result = caseOpenTimeInterval(openTimeInterval);
        if (result == null) result = caseTimeInterval(openTimeInterval);
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
      case AgreePackage.ARRAY_TYPE:
      {
        ArrayType arrayType = (ArrayType)theEObject;
        T result = caseArrayType(arrayType);
        if (result == null) result = caseType(arrayType);
        if (result == null) result = caseElement(arrayType);
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
      case AgreePackage.FORALL_EXPR:
      {
        ForallExpr forallExpr = (ForallExpr)theEObject;
        T result = caseForallExpr(forallExpr);
        if (result == null) result = caseExpr(forallExpr);
        if (result == null) result = caseElement(forallExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.EXISTS_EXPR:
      {
        ExistsExpr existsExpr = (ExistsExpr)theEObject;
        T result = caseExistsExpr(existsExpr);
        if (result == null) result = caseExpr(existsExpr);
        if (result == null) result = caseElement(existsExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FLATMAP_EXPR:
      {
        FlatmapExpr flatmapExpr = (FlatmapExpr)theEObject;
        T result = caseFlatmapExpr(flatmapExpr);
        if (result == null) result = caseExpr(flatmapExpr);
        if (result == null) result = caseElement(flatmapExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FOLD_LEFT_EXPR:
      {
        FoldLeftExpr foldLeftExpr = (FoldLeftExpr)theEObject;
        T result = caseFoldLeftExpr(foldLeftExpr);
        if (result == null) result = caseExpr(foldLeftExpr);
        if (result == null) result = caseElement(foldLeftExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.FOLD_RIGHT_EXPR:
      {
        FoldRightExpr foldRightExpr = (FoldRightExpr)theEObject;
        T result = caseFoldRightExpr(foldRightExpr);
        if (result == null) result = caseExpr(foldRightExpr);
        if (result == null) result = caseElement(foldRightExpr);
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
      case AgreePackage.THIS_REF:
      {
        ThisRef thisRef = (ThisRef)theEObject;
        T result = caseThisRef(thisRef);
        if (result == null) result = caseComponentRef(thisRef);
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
      case AgreePackage.ARRAY_UPDATE_EXPR:
      {
        ArrayUpdateExpr arrayUpdateExpr = (ArrayUpdateExpr)theEObject;
        T result = caseArrayUpdateExpr(arrayUpdateExpr);
        if (result == null) result = caseExpr(arrayUpdateExpr);
        if (result == null) result = caseElement(arrayUpdateExpr);
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
      case AgreePackage.ARRAY_SUB_EXPR:
      {
        ArraySubExpr arraySubExpr = (ArraySubExpr)theEObject;
        T result = caseArraySubExpr(arraySubExpr);
        if (result == null) result = caseExpr(arraySubExpr);
        if (result == null) result = caseElement(arraySubExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TAG_EXPR:
      {
        TagExpr tagExpr = (TagExpr)theEObject;
        T result = caseTagExpr(tagExpr);
        if (result == null) result = caseExpr(tagExpr);
        if (result == null) result = caseElement(tagExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.SELECTION_EXPR:
      {
        SelectionExpr selectionExpr = (SelectionExpr)theEObject;
        T result = caseSelectionExpr(selectionExpr);
        if (result == null) result = caseExpr(selectionExpr);
        if (result == null) result = caseElement(selectionExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.NAMED_ELM_EXPR:
      {
        NamedElmExpr namedElmExpr = (NamedElmExpr)theEObject;
        T result = caseNamedElmExpr(namedElmExpr);
        if (result == null) result = caseExpr(namedElmExpr);
        if (result == null) result = caseElement(namedElmExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TIME_EXPR:
      {
        TimeExpr timeExpr = (TimeExpr)theEObject;
        T result = caseTimeExpr(timeExpr);
        if (result == null) result = caseExpr(timeExpr);
        if (result == null) result = caseElement(timeExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.INDICES_EXPR:
      {
        IndicesExpr indicesExpr = (IndicesExpr)theEObject;
        T result = caseIndicesExpr(indicesExpr);
        if (result == null) result = caseExpr(indicesExpr);
        if (result == null) result = caseElement(indicesExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.CALL_EXPR:
      {
        CallExpr callExpr = (CallExpr)theEObject;
        T result = caseCallExpr(callExpr);
        if (result == null) result = caseExpr(callExpr);
        if (result == null) result = caseElement(callExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.RECORD_LIT_EXPR:
      {
        RecordLitExpr recordLitExpr = (RecordLitExpr)theEObject;
        T result = caseRecordLitExpr(recordLitExpr);
        if (result == null) result = caseExpr(recordLitExpr);
        if (result == null) result = caseElement(recordLitExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.ENUM_LIT_EXPR:
      {
        EnumLitExpr enumLitExpr = (EnumLitExpr)theEObject;
        T result = caseEnumLitExpr(enumLitExpr);
        if (result == null) result = caseExpr(enumLitExpr);
        if (result == null) result = caseElement(enumLitExpr);
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
      case AgreePackage.LATCHED_EXPR:
      {
        LatchedExpr latchedExpr = (LatchedExpr)theEObject;
        T result = caseLatchedExpr(latchedExpr);
        if (result == null) result = caseExpr(latchedExpr);
        if (result == null) result = caseElement(latchedExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TIME_OF_EXPR:
      {
        TimeOfExpr timeOfExpr = (TimeOfExpr)theEObject;
        T result = caseTimeOfExpr(timeOfExpr);
        if (result == null) result = caseExpr(timeOfExpr);
        if (result == null) result = caseElement(timeOfExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TIME_RISE_EXPR:
      {
        TimeRiseExpr timeRiseExpr = (TimeRiseExpr)theEObject;
        T result = caseTimeRiseExpr(timeRiseExpr);
        if (result == null) result = caseExpr(timeRiseExpr);
        if (result == null) result = caseElement(timeRiseExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AgreePackage.TIME_FALL_EXPR:
      {
        TimeFallExpr timeFallExpr = (TimeFallExpr)theEObject;
        T result = caseTimeFallExpr(timeFallExpr);
        if (result == null) result = caseExpr(timeFallExpr);
        if (result == null) result = caseElement(timeFallExpr);
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
   * Returns the result of interpreting the object as an instance of '<em>Named Spec Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Spec Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedSpecStatement(NamedSpecStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pattern Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pattern Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePatternStatement(PatternStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>When Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>When Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhenStatement(WhenStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Whenever Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Whenever Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWheneverStatement(WheneverStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Real Time Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Time Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealTimeStatement(RealTimeStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Time Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTimeInterval(TimeInterval object)
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
   * Returns the result of interpreting the object as an instance of '<em>Abstraction</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstraction</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAbstraction(Abstraction object)
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
   * Returns the result of interpreting the object as an instance of '<em>Input Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Input Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInputStatement(InputStatement object)
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
   * Returns the result of interpreting the object as an instance of '<em>Fn Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fn Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFnDef(FnDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Library Fn Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Library Fn Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLibraryFnDef(LibraryFnDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Linearization Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Linearization Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLinearizationDef(LinearizationDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Linearization Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Linearization Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLinearizationInterval(LinearizationInterval object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeDef(NodeDef object)
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
   * Returns the result of interpreting the object as an instance of '<em>Record Def</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Def</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordDef(RecordDef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enum Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumStatement(EnumStatement object)
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
   * Returns the result of interpreting the object as an instance of '<em>Component Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponentRef(ComponentRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Array Literal Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Literal Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayLiteralExpr(ArrayLiteralExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Double Dot Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Double Dot Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDoubleDotRef(DoubleDotRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named ID</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named ID</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedID(NamedID object)
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
   * Returns the result of interpreting the object as an instance of '<em>Always Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Always Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlwaysStatement(AlwaysStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>When Holds Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>When Holds Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhenHoldsStatement(WhenHoldsStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>When Occurs Statment</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>When Occurs Statment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhenOccursStatment(WhenOccursStatment object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Whenever Occurs Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Whenever Occurs Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWheneverOccursStatement(WheneverOccursStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Whenever Becomes True Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Whenever Becomes True Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWheneverBecomesTrueStatement(WheneverBecomesTrueStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Whenever Holds Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Whenever Holds Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWheneverHoldsStatement(WheneverHoldsStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Whenever Implies Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Whenever Implies Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWheneverImpliesStatement(WheneverImpliesStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Periodic Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Periodic Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePeriodicStatement(PeriodicStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Sporadic Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sporadic Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSporadicStatement(SporadicStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Closed Time Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Closed Time Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClosedTimeInterval(ClosedTimeInterval object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Open Left Time Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Open Left Time Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpenLeftTimeInterval(OpenLeftTimeInterval object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Open Right Time Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Open Right Time Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpenRightTimeInterval(OpenRightTimeInterval object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Open Time Interval</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Open Time Interval</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpenTimeInterval(OpenTimeInterval object)
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
   * Returns the result of interpreting the object as an instance of '<em>Array Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayType(ArrayType object)
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
   * Returns the result of interpreting the object as an instance of '<em>Forall Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Forall Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForallExpr(ForallExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Exists Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exists Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExistsExpr(ExistsExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Flatmap Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Flatmap Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFlatmapExpr(FlatmapExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fold Left Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fold Left Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFoldLeftExpr(FoldLeftExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fold Right Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fold Right Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFoldRightExpr(FoldRightExpr object)
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
   * Returns the result of interpreting the object as an instance of '<em>This Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>This Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseThisRef(ThisRef object)
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
   * Returns the result of interpreting the object as an instance of '<em>Array Update Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Update Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayUpdateExpr(ArrayUpdateExpr object)
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
   * Returns the result of interpreting the object as an instance of '<em>Array Sub Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Sub Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArraySubExpr(ArraySubExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Tag Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tag Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTagExpr(TagExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Selection Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Selection Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectionExpr(SelectionExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Elm Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Elm Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedElmExpr(NamedElmExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Time Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTimeExpr(TimeExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Indices Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Indices Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIndicesExpr(IndicesExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Call Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCallExpr(CallExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Record Lit Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Record Lit Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRecordLitExpr(RecordLitExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enum Lit Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum Lit Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumLitExpr(EnumLitExpr object)
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
   * Returns the result of interpreting the object as an instance of '<em>Latched Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Latched Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLatchedExpr(LatchedExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Time Of Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Of Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTimeOfExpr(TimeOfExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Time Rise Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Rise Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTimeRiseExpr(TimeRiseExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Time Fall Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Fall Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTimeFallExpr(TimeFallExpr object)
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
