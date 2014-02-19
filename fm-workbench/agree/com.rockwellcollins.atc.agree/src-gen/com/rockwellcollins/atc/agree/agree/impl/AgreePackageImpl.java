/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.ClockID;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IdExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NextExpr;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.NodeLemma;
import com.rockwellcollins.atc.agree.agree.NodeStmt;
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.QueueCountID;
import com.rockwellcollins.atc.agree.agree.QueueInsertID;
import com.rockwellcollins.atc.agree.agree.QueueRemoveID;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.osate.aadl2.Aadl2Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AgreePackageImpl extends EPackageImpl implements AgreePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass agreeLibraryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass agreeSubclauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass contractEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass specStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass synchStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass callDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eqStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fnDefExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeDefExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeBodyExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeStmtEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nestedDotIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass clockIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass queueRemoveIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass queueInsertIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass queueCountIDEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass agreeContractLibraryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass agreeContractSubclauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass agreeContractEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assumeStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass guaranteeStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assertStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass paramStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lemmaStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass liftStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeEqEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeLemmaEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass binaryExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unaryExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifThenElseExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass prevExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nextExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass getPropertyExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass idExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fnCallExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass intLitExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass preExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass realLitExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass boolLitExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass thisExprEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private AgreePackageImpl()
  {
    super(eNS_URI, AgreeFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link AgreePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static AgreePackage init()
  {
    if (isInited) return (AgreePackage)EPackage.Registry.INSTANCE.getEPackage(AgreePackage.eNS_URI);

    // Obtain or create and register package
    AgreePackageImpl theAgreePackage = (AgreePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AgreePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AgreePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    Aadl2Package.eINSTANCE.eClass();

    // Create package meta-data objects
    theAgreePackage.createPackageContents();

    // Initialize created meta-data
    theAgreePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theAgreePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(AgreePackage.eNS_URI, theAgreePackage);
    return theAgreePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAgreeLibrary()
  {
    return agreeLibraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAgreeSubclause()
  {
    return agreeSubclauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContract()
  {
    return contractEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSpecStatement()
  {
    return specStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSynchStatement()
  {
    return synchStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSynchStatement_Val()
  {
    return (EAttribute)synchStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSynchStatement_Sim()
  {
    return (EAttribute)synchStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCallDef()
  {
    return callDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyStatement()
  {
    return propertyStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyStatement_Expr()
  {
    return (EReference)propertyStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstStatement()
  {
    return constStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstStatement_Type()
  {
    return (EReference)constStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstStatement_Expr()
  {
    return (EReference)constStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEqStatement()
  {
    return eqStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEqStatement_Lhs()
  {
    return (EReference)eqStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEqStatement_Expr()
  {
    return (EReference)eqStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFnDefExpr()
  {
    return fnDefExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFnDefExpr_Args()
  {
    return (EReference)fnDefExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFnDefExpr_Type()
  {
    return (EReference)fnDefExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFnDefExpr_Expr()
  {
    return (EReference)fnDefExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeDefExpr()
  {
    return nodeDefExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeDefExpr_Args()
  {
    return (EReference)nodeDefExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeDefExpr_Rets()
  {
    return (EReference)nodeDefExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeDefExpr_NodeBody()
  {
    return (EReference)nodeDefExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeBodyExpr()
  {
    return nodeBodyExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeBodyExpr_Locs()
  {
    return (EReference)nodeBodyExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeBodyExpr_Stmts()
  {
    return (EReference)nodeBodyExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeStmt()
  {
    return nodeStmtEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeStmt_Expr()
  {
    return (EReference)nodeStmtEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArg()
  {
    return argEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArg_Type()
  {
    return (EReference)argEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getType()
  {
    return typeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getType_String()
  {
    return (EAttribute)typeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpr()
  {
    return exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNestedDotID()
  {
    return nestedDotIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNestedDotID_Base()
  {
    return (EReference)nestedDotIDEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNestedDotID_Sub()
  {
    return (EReference)nestedDotIDEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClockID()
  {
    return clockIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClockID_Comp()
  {
    return (EReference)clockIDEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQueueRemoveID()
  {
    return queueRemoveIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQueueRemoveID_Comp()
  {
    return (EReference)queueRemoveIDEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQueueInsertID()
  {
    return queueInsertIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQueueInsertID_Comp()
  {
    return (EReference)queueInsertIDEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQueueCountID()
  {
    return queueCountIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQueueCountID_Comp()
  {
    return (EReference)queueCountIDEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAgreeContractLibrary()
  {
    return agreeContractLibraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAgreeContractLibrary_Contract()
  {
    return (EReference)agreeContractLibraryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAgreeContractSubclause()
  {
    return agreeContractSubclauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAgreeContractSubclause_Contract()
  {
    return (EReference)agreeContractSubclauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAgreeContract()
  {
    return agreeContractEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAgreeContract_Specs()
  {
    return (EReference)agreeContractEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAssumeStatement()
  {
    return assumeStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAssumeStatement_Str()
  {
    return (EAttribute)assumeStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssumeStatement_Expr()
  {
    return (EReference)assumeStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGuaranteeStatement()
  {
    return guaranteeStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGuaranteeStatement_Str()
  {
    return (EAttribute)guaranteeStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGuaranteeStatement_Expr()
  {
    return (EReference)guaranteeStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAssertStatement()
  {
    return assertStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssertStatement_Expr()
  {
    return (EReference)assertStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParamStatement()
  {
    return paramStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParamStatement_Expr()
  {
    return (EReference)paramStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParamStatement_Type()
  {
    return (EReference)paramStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLemmaStatement()
  {
    return lemmaStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLemmaStatement_Str()
  {
    return (EAttribute)lemmaStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLemmaStatement_Expr()
  {
    return (EReference)lemmaStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLiftStatement()
  {
    return liftStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLiftStatement_Subcomp()
  {
    return (EReference)liftStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeEq()
  {
    return nodeEqEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeEq_Lhs()
  {
    return (EReference)nodeEqEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeLemma()
  {
    return nodeLemmaEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeLemma_Str()
  {
    return (EAttribute)nodeLemmaEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBinaryExpr()
  {
    return binaryExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBinaryExpr_Left()
  {
    return (EReference)binaryExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBinaryExpr_Op()
  {
    return (EAttribute)binaryExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBinaryExpr_Right()
  {
    return (EReference)binaryExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnaryExpr()
  {
    return unaryExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUnaryExpr_Op()
  {
    return (EAttribute)unaryExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnaryExpr_Expr()
  {
    return (EReference)unaryExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfThenElseExpr()
  {
    return ifThenElseExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfThenElseExpr_A()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfThenElseExpr_B()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfThenElseExpr_C()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrevExpr()
  {
    return prevExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrevExpr_Delay()
  {
    return (EReference)prevExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrevExpr_Init()
  {
    return (EReference)prevExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNextExpr()
  {
    return nextExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNextExpr_Expr()
  {
    return (EReference)nextExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGetPropertyExpr()
  {
    return getPropertyExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGetPropertyExpr_Component()
  {
    return (EReference)getPropertyExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGetPropertyExpr_Prop()
  {
    return (EReference)getPropertyExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIdExpr()
  {
    return idExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIdExpr_Id()
  {
    return (EReference)idExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFnCallExpr()
  {
    return fnCallExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFnCallExpr_Fn()
  {
    return (EReference)fnCallExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFnCallExpr_Args()
  {
    return (EReference)fnCallExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntLitExpr()
  {
    return intLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntLitExpr_Val()
  {
    return (EAttribute)intLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPreExpr()
  {
    return preExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPreExpr_Expr()
  {
    return (EReference)preExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRealLitExpr()
  {
    return realLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRealLitExpr_Val()
  {
    return (EAttribute)realLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBoolLitExpr()
  {
    return boolLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBoolLitExpr_Val()
  {
    return (EReference)boolLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getThisExpr()
  {
    return thisExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getThisExpr_SubThis()
  {
    return (EReference)thisExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AgreeFactory getAgreeFactory()
  {
    return (AgreeFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    agreeLibraryEClass = createEClass(AGREE_LIBRARY);

    agreeSubclauseEClass = createEClass(AGREE_SUBCLAUSE);

    contractEClass = createEClass(CONTRACT);

    specStatementEClass = createEClass(SPEC_STATEMENT);

    synchStatementEClass = createEClass(SYNCH_STATEMENT);
    createEAttribute(synchStatementEClass, SYNCH_STATEMENT__VAL);
    createEAttribute(synchStatementEClass, SYNCH_STATEMENT__SIM);

    callDefEClass = createEClass(CALL_DEF);

    propertyStatementEClass = createEClass(PROPERTY_STATEMENT);
    createEReference(propertyStatementEClass, PROPERTY_STATEMENT__EXPR);

    constStatementEClass = createEClass(CONST_STATEMENT);
    createEReference(constStatementEClass, CONST_STATEMENT__TYPE);
    createEReference(constStatementEClass, CONST_STATEMENT__EXPR);

    eqStatementEClass = createEClass(EQ_STATEMENT);
    createEReference(eqStatementEClass, EQ_STATEMENT__LHS);
    createEReference(eqStatementEClass, EQ_STATEMENT__EXPR);

    fnDefExprEClass = createEClass(FN_DEF_EXPR);
    createEReference(fnDefExprEClass, FN_DEF_EXPR__ARGS);
    createEReference(fnDefExprEClass, FN_DEF_EXPR__TYPE);
    createEReference(fnDefExprEClass, FN_DEF_EXPR__EXPR);

    nodeDefExprEClass = createEClass(NODE_DEF_EXPR);
    createEReference(nodeDefExprEClass, NODE_DEF_EXPR__ARGS);
    createEReference(nodeDefExprEClass, NODE_DEF_EXPR__RETS);
    createEReference(nodeDefExprEClass, NODE_DEF_EXPR__NODE_BODY);

    nodeBodyExprEClass = createEClass(NODE_BODY_EXPR);
    createEReference(nodeBodyExprEClass, NODE_BODY_EXPR__LOCS);
    createEReference(nodeBodyExprEClass, NODE_BODY_EXPR__STMTS);

    nodeStmtEClass = createEClass(NODE_STMT);
    createEReference(nodeStmtEClass, NODE_STMT__EXPR);

    argEClass = createEClass(ARG);
    createEReference(argEClass, ARG__TYPE);

    typeEClass = createEClass(TYPE);
    createEAttribute(typeEClass, TYPE__STRING);

    exprEClass = createEClass(EXPR);

    nestedDotIDEClass = createEClass(NESTED_DOT_ID);
    createEReference(nestedDotIDEClass, NESTED_DOT_ID__BASE);
    createEReference(nestedDotIDEClass, NESTED_DOT_ID__SUB);

    clockIDEClass = createEClass(CLOCK_ID);
    createEReference(clockIDEClass, CLOCK_ID__COMP);

    queueRemoveIDEClass = createEClass(QUEUE_REMOVE_ID);
    createEReference(queueRemoveIDEClass, QUEUE_REMOVE_ID__COMP);

    queueInsertIDEClass = createEClass(QUEUE_INSERT_ID);
    createEReference(queueInsertIDEClass, QUEUE_INSERT_ID__COMP);

    queueCountIDEClass = createEClass(QUEUE_COUNT_ID);
    createEReference(queueCountIDEClass, QUEUE_COUNT_ID__COMP);

    agreeContractLibraryEClass = createEClass(AGREE_CONTRACT_LIBRARY);
    createEReference(agreeContractLibraryEClass, AGREE_CONTRACT_LIBRARY__CONTRACT);

    agreeContractSubclauseEClass = createEClass(AGREE_CONTRACT_SUBCLAUSE);
    createEReference(agreeContractSubclauseEClass, AGREE_CONTRACT_SUBCLAUSE__CONTRACT);

    agreeContractEClass = createEClass(AGREE_CONTRACT);
    createEReference(agreeContractEClass, AGREE_CONTRACT__SPECS);

    assumeStatementEClass = createEClass(ASSUME_STATEMENT);
    createEAttribute(assumeStatementEClass, ASSUME_STATEMENT__STR);
    createEReference(assumeStatementEClass, ASSUME_STATEMENT__EXPR);

    guaranteeStatementEClass = createEClass(GUARANTEE_STATEMENT);
    createEAttribute(guaranteeStatementEClass, GUARANTEE_STATEMENT__STR);
    createEReference(guaranteeStatementEClass, GUARANTEE_STATEMENT__EXPR);

    assertStatementEClass = createEClass(ASSERT_STATEMENT);
    createEReference(assertStatementEClass, ASSERT_STATEMENT__EXPR);

    paramStatementEClass = createEClass(PARAM_STATEMENT);
    createEReference(paramStatementEClass, PARAM_STATEMENT__EXPR);
    createEReference(paramStatementEClass, PARAM_STATEMENT__TYPE);

    lemmaStatementEClass = createEClass(LEMMA_STATEMENT);
    createEAttribute(lemmaStatementEClass, LEMMA_STATEMENT__STR);
    createEReference(lemmaStatementEClass, LEMMA_STATEMENT__EXPR);

    liftStatementEClass = createEClass(LIFT_STATEMENT);
    createEReference(liftStatementEClass, LIFT_STATEMENT__SUBCOMP);

    nodeEqEClass = createEClass(NODE_EQ);
    createEReference(nodeEqEClass, NODE_EQ__LHS);

    nodeLemmaEClass = createEClass(NODE_LEMMA);
    createEAttribute(nodeLemmaEClass, NODE_LEMMA__STR);

    binaryExprEClass = createEClass(BINARY_EXPR);
    createEReference(binaryExprEClass, BINARY_EXPR__LEFT);
    createEAttribute(binaryExprEClass, BINARY_EXPR__OP);
    createEReference(binaryExprEClass, BINARY_EXPR__RIGHT);

    unaryExprEClass = createEClass(UNARY_EXPR);
    createEAttribute(unaryExprEClass, UNARY_EXPR__OP);
    createEReference(unaryExprEClass, UNARY_EXPR__EXPR);

    ifThenElseExprEClass = createEClass(IF_THEN_ELSE_EXPR);
    createEReference(ifThenElseExprEClass, IF_THEN_ELSE_EXPR__A);
    createEReference(ifThenElseExprEClass, IF_THEN_ELSE_EXPR__B);
    createEReference(ifThenElseExprEClass, IF_THEN_ELSE_EXPR__C);

    prevExprEClass = createEClass(PREV_EXPR);
    createEReference(prevExprEClass, PREV_EXPR__DELAY);
    createEReference(prevExprEClass, PREV_EXPR__INIT);

    nextExprEClass = createEClass(NEXT_EXPR);
    createEReference(nextExprEClass, NEXT_EXPR__EXPR);

    getPropertyExprEClass = createEClass(GET_PROPERTY_EXPR);
    createEReference(getPropertyExprEClass, GET_PROPERTY_EXPR__COMPONENT);
    createEReference(getPropertyExprEClass, GET_PROPERTY_EXPR__PROP);

    idExprEClass = createEClass(ID_EXPR);
    createEReference(idExprEClass, ID_EXPR__ID);

    fnCallExprEClass = createEClass(FN_CALL_EXPR);
    createEReference(fnCallExprEClass, FN_CALL_EXPR__FN);
    createEReference(fnCallExprEClass, FN_CALL_EXPR__ARGS);

    intLitExprEClass = createEClass(INT_LIT_EXPR);
    createEAttribute(intLitExprEClass, INT_LIT_EXPR__VAL);

    preExprEClass = createEClass(PRE_EXPR);
    createEReference(preExprEClass, PRE_EXPR__EXPR);

    realLitExprEClass = createEClass(REAL_LIT_EXPR);
    createEAttribute(realLitExprEClass, REAL_LIT_EXPR__VAL);

    boolLitExprEClass = createEClass(BOOL_LIT_EXPR);
    createEReference(boolLitExprEClass, BOOL_LIT_EXPR__VAL);

    thisExprEClass = createEClass(THIS_EXPR);
    createEReference(thisExprEClass, THIS_EXPR__SUB_THIS);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    Aadl2Package theAadl2Package = (Aadl2Package)EPackage.Registry.INSTANCE.getEPackage(Aadl2Package.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    agreeLibraryEClass.getESuperTypes().add(theAadl2Package.getAnnexLibrary());
    agreeSubclauseEClass.getESuperTypes().add(theAadl2Package.getAnnexSubclause());
    contractEClass.getESuperTypes().add(theAadl2Package.getElement());
    specStatementEClass.getESuperTypes().add(theAadl2Package.getElement());
    synchStatementEClass.getESuperTypes().add(this.getSpecStatement());
    callDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    propertyStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    propertyStatementEClass.getESuperTypes().add(this.getSpecStatement());
    constStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    constStatementEClass.getESuperTypes().add(this.getSpecStatement());
    eqStatementEClass.getESuperTypes().add(theAadl2Package.getElement());
    eqStatementEClass.getESuperTypes().add(this.getSpecStatement());
    fnDefExprEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    fnDefExprEClass.getESuperTypes().add(this.getSpecStatement());
    fnDefExprEClass.getESuperTypes().add(this.getCallDef());
    nodeDefExprEClass.getESuperTypes().add(theAadl2Package.getNamespace());
    nodeDefExprEClass.getESuperTypes().add(this.getSpecStatement());
    nodeDefExprEClass.getESuperTypes().add(this.getCallDef());
    nodeBodyExprEClass.getESuperTypes().add(theAadl2Package.getElement());
    nodeStmtEClass.getESuperTypes().add(theAadl2Package.getElement());
    argEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    typeEClass.getESuperTypes().add(theAadl2Package.getElement());
    exprEClass.getESuperTypes().add(theAadl2Package.getElement());
    nestedDotIDEClass.getESuperTypes().add(this.getExpr());
    clockIDEClass.getESuperTypes().add(this.getExpr());
    queueRemoveIDEClass.getESuperTypes().add(this.getExpr());
    queueInsertIDEClass.getESuperTypes().add(this.getExpr());
    queueCountIDEClass.getESuperTypes().add(this.getExpr());
    agreeContractLibraryEClass.getESuperTypes().add(this.getAgreeLibrary());
    agreeContractSubclauseEClass.getESuperTypes().add(this.getAgreeSubclause());
    agreeContractEClass.getESuperTypes().add(this.getContract());
    assumeStatementEClass.getESuperTypes().add(this.getSpecStatement());
    guaranteeStatementEClass.getESuperTypes().add(this.getSpecStatement());
    assertStatementEClass.getESuperTypes().add(this.getSpecStatement());
    paramStatementEClass.getESuperTypes().add(this.getSpecStatement());
    lemmaStatementEClass.getESuperTypes().add(this.getSpecStatement());
    liftStatementEClass.getESuperTypes().add(this.getSpecStatement());
    nodeEqEClass.getESuperTypes().add(this.getNodeStmt());
    nodeLemmaEClass.getESuperTypes().add(this.getNodeStmt());
    binaryExprEClass.getESuperTypes().add(this.getExpr());
    unaryExprEClass.getESuperTypes().add(this.getExpr());
    ifThenElseExprEClass.getESuperTypes().add(this.getExpr());
    prevExprEClass.getESuperTypes().add(this.getExpr());
    nextExprEClass.getESuperTypes().add(this.getExpr());
    getPropertyExprEClass.getESuperTypes().add(this.getExpr());
    idExprEClass.getESuperTypes().add(this.getExpr());
    fnCallExprEClass.getESuperTypes().add(this.getExpr());
    intLitExprEClass.getESuperTypes().add(this.getExpr());
    preExprEClass.getESuperTypes().add(this.getExpr());
    realLitExprEClass.getESuperTypes().add(this.getExpr());
    boolLitExprEClass.getESuperTypes().add(this.getExpr());
    thisExprEClass.getESuperTypes().add(this.getExpr());

    // Initialize classes and features; add operations and parameters
    initEClass(agreeLibraryEClass, AgreeLibrary.class, "AgreeLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(agreeSubclauseEClass, AgreeSubclause.class, "AgreeSubclause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(contractEClass, Contract.class, "Contract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(specStatementEClass, SpecStatement.class, "SpecStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(synchStatementEClass, SynchStatement.class, "SynchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSynchStatement_Val(), ecorePackage.getEString(), "val", null, 0, 1, SynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSynchStatement_Sim(), ecorePackage.getEString(), "sim", null, 0, 1, SynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(callDefEClass, CallDef.class, "CallDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(propertyStatementEClass, PropertyStatement.class, "PropertyStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constStatementEClass, ConstStatement.class, "ConstStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConstStatement_Type(), this.getType(), null, "type", null, 0, 1, ConstStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConstStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, ConstStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eqStatementEClass, EqStatement.class, "EqStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEqStatement_Lhs(), this.getArg(), null, "lhs", null, 0, -1, EqStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEqStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, EqStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fnDefExprEClass, FnDefExpr.class, "FnDefExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFnDefExpr_Args(), this.getArg(), null, "args", null, 0, -1, FnDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFnDefExpr_Type(), this.getType(), null, "type", null, 0, 1, FnDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFnDefExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, FnDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeDefExprEClass, NodeDefExpr.class, "NodeDefExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeDefExpr_Args(), this.getArg(), null, "args", null, 0, -1, NodeDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeDefExpr_Rets(), this.getArg(), null, "rets", null, 0, -1, NodeDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeDefExpr_NodeBody(), this.getNodeBodyExpr(), null, "nodeBody", null, 0, 1, NodeDefExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeBodyExprEClass, NodeBodyExpr.class, "NodeBodyExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeBodyExpr_Locs(), this.getArg(), null, "locs", null, 0, -1, NodeBodyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeBodyExpr_Stmts(), this.getNodeStmt(), null, "stmts", null, 0, -1, NodeBodyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeStmtEClass, NodeStmt.class, "NodeStmt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeStmt_Expr(), this.getExpr(), null, "expr", null, 0, 1, NodeStmt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argEClass, Arg.class, "Arg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArg_Type(), this.getType(), null, "type", null, 0, 1, Arg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(typeEClass, Type.class, "Type", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getType_String(), ecorePackage.getEString(), "string", null, 0, 1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(exprEClass, Expr.class, "Expr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(nestedDotIDEClass, NestedDotID.class, "NestedDotID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNestedDotID_Base(), theAadl2Package.getNamedElement(), null, "base", null, 0, 1, NestedDotID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNestedDotID_Sub(), this.getNestedDotID(), null, "sub", null, 0, 1, NestedDotID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(clockIDEClass, ClockID.class, "ClockID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getClockID_Comp(), theAadl2Package.getNamedElement(), null, "comp", null, 0, 1, ClockID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(queueRemoveIDEClass, QueueRemoveID.class, "QueueRemoveID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getQueueRemoveID_Comp(), theAadl2Package.getNamedElement(), null, "comp", null, 0, 1, QueueRemoveID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(queueInsertIDEClass, QueueInsertID.class, "QueueInsertID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getQueueInsertID_Comp(), theAadl2Package.getNamedElement(), null, "comp", null, 0, 1, QueueInsertID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(queueCountIDEClass, QueueCountID.class, "QueueCountID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getQueueCountID_Comp(), theAadl2Package.getNamedElement(), null, "comp", null, 0, 1, QueueCountID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(agreeContractLibraryEClass, AgreeContractLibrary.class, "AgreeContractLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContractLibrary_Contract(), this.getContract(), null, "contract", null, 0, 1, AgreeContractLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(agreeContractSubclauseEClass, AgreeContractSubclause.class, "AgreeContractSubclause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContractSubclause_Contract(), this.getContract(), null, "contract", null, 0, 1, AgreeContractSubclause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(agreeContractEClass, AgreeContract.class, "AgreeContract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContract_Specs(), this.getSpecStatement(), null, "specs", null, 0, -1, AgreeContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assumeStatementEClass, AssumeStatement.class, "AssumeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAssumeStatement_Str(), ecorePackage.getEString(), "str", null, 0, 1, AssumeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAssumeStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, AssumeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(guaranteeStatementEClass, GuaranteeStatement.class, "GuaranteeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGuaranteeStatement_Str(), ecorePackage.getEString(), "str", null, 0, 1, GuaranteeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGuaranteeStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, GuaranteeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assertStatementEClass, AssertStatement.class, "AssertStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAssertStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, AssertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(paramStatementEClass, ParamStatement.class, "ParamStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParamStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, ParamStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParamStatement_Type(), this.getType(), null, "type", null, 0, 1, ParamStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(lemmaStatementEClass, LemmaStatement.class, "LemmaStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLemmaStatement_Str(), ecorePackage.getEString(), "str", null, 0, 1, LemmaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLemmaStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, LemmaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(liftStatementEClass, LiftStatement.class, "LiftStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLiftStatement_Subcomp(), this.getNestedDotID(), null, "subcomp", null, 0, 1, LiftStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeEqEClass, NodeEq.class, "NodeEq", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeEq_Lhs(), this.getArg(), null, "lhs", null, 0, -1, NodeEq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeLemmaEClass, NodeLemma.class, "NodeLemma", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNodeLemma_Str(), ecorePackage.getEString(), "str", null, 0, 1, NodeLemma.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(binaryExprEClass, BinaryExpr.class, "BinaryExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBinaryExpr_Left(), this.getExpr(), null, "left", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBinaryExpr_Op(), ecorePackage.getEString(), "op", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBinaryExpr_Right(), this.getExpr(), null, "right", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unaryExprEClass, UnaryExpr.class, "UnaryExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUnaryExpr_Op(), ecorePackage.getEString(), "op", null, 0, 1, UnaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, UnaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifThenElseExprEClass, IfThenElseExpr.class, "IfThenElseExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfThenElseExpr_A(), this.getExpr(), null, "a", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfThenElseExpr_B(), this.getExpr(), null, "b", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfThenElseExpr_C(), this.getExpr(), null, "c", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(prevExprEClass, PrevExpr.class, "PrevExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPrevExpr_Delay(), this.getExpr(), null, "delay", null, 0, 1, PrevExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrevExpr_Init(), this.getExpr(), null, "init", null, 0, 1, PrevExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nextExprEClass, NextExpr.class, "NextExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNextExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, NextExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(getPropertyExprEClass, GetPropertyExpr.class, "GetPropertyExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGetPropertyExpr_Component(), this.getExpr(), null, "component", null, 0, 1, GetPropertyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGetPropertyExpr_Prop(), this.getExpr(), null, "prop", null, 0, 1, GetPropertyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(idExprEClass, IdExpr.class, "IdExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIdExpr_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, IdExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fnCallExprEClass, FnCallExpr.class, "FnCallExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFnCallExpr_Fn(), this.getNestedDotID(), null, "fn", null, 0, 1, FnCallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFnCallExpr_Args(), this.getExpr(), null, "args", null, 0, -1, FnCallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(intLitExprEClass, IntLitExpr.class, "IntLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntLitExpr_Val(), ecorePackage.getEString(), "val", null, 0, 1, IntLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(preExprEClass, PreExpr.class, "PreExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPreExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, PreExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realLitExprEClass, RealLitExpr.class, "RealLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRealLitExpr_Val(), ecorePackage.getEString(), "val", null, 0, 1, RealLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(boolLitExprEClass, BoolLitExpr.class, "BoolLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBoolLitExpr_Val(), theAadl2Package.getBooleanLiteral(), null, "val", null, 0, 1, BoolLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(thisExprEClass, ThisExpr.class, "ThisExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getThisExpr_SubThis(), this.getNestedDotID(), null, "subThis", null, 0, 1, ThisExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //AgreePackageImpl
