/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.Abstraction;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.AlwaysStatement;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr;
import com.rockwellcollins.atc.agree.agree.ArraySubExpr;
import com.rockwellcollins.atc.agree.agree.ArrayType;
import com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.AsynchStatement;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CalenStatement;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.ClosedTimeInterval;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.EnumLitExpr;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.ExistsExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FlatmapExpr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnDef;
import com.rockwellcollins.atc.agree.agree.FoldLeftExpr;
import com.rockwellcollins.atc.agree.agree.FoldRightExpr;
import com.rockwellcollins.atc.agree.agree.ForallExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IndicesExpr;
import com.rockwellcollins.atc.agree.agree.InitialStatement;
import com.rockwellcollins.atc.agree.agree.InputStatement;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LatchedExpr;
import com.rockwellcollins.atc.agree.agree.LatchedStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LibraryFnDef;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.LinearizationInterval;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NamedSpecStatement;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
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
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SelectionExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SporadicStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.TagExpr;
import com.rockwellcollins.atc.agree.agree.ThisRef;
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
  private EClass namedSpecStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass patternStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass whenStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass wheneverStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass realTimeStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeIntervalEClass = null;

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
  private EClass orderStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass abstractionEClass = null;

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
  private EClass inputStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assignStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fnDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass libraryFnDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linearizationDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linearizationIntervalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeDefEClass = null;

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
  private EClass recordDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumStatementEClass = null;

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
  private EClass componentRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arrayLiteralExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass doubleDotRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedIDEClass = null;

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
  private EClass initialStatementEClass = null;

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
  private EClass liftStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass connectionStatementEClass = null;

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
  private EClass lemmaStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass alwaysStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass whenHoldsStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass whenOccursStatmentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass wheneverOccursStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass wheneverBecomesTrueStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass wheneverHoldsStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass wheneverImpliesStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass periodicStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sporadicStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass closedTimeIntervalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass openLeftTimeIntervalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass openRightTimeIntervalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass openTimeIntervalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mnSynchStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass calenStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass asynchStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass latchedStatementEClass = null;

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
  private EClass arrayTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass forallExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass existsExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass flatmapExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass foldLeftExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass foldRightExprEClass = null;

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
  private EClass thisRefEClass = null;

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
  private EClass getPropertyExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arrayUpdateExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass recordUpdateExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arraySubExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tagExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectionExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedElmExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass indicesExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass callExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass recordLitExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumLitExprEClass = null;

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
  private EClass eventExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass latchedExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeOfExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeRiseExprEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeFallExprEClass = null;

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
  private EClass floorCastEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass realCastEClass = null;

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
    Object registeredAgreePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    AgreePackageImpl theAgreePackage = registeredAgreePackage instanceof AgreePackageImpl ? (AgreePackageImpl)registeredAgreePackage : new AgreePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
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
  @Override
  public EClass getAgreeLibrary()
  {
    return agreeLibraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAgreeSubclause()
  {
    return agreeSubclauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getContract()
  {
    return contractEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSpecStatement()
  {
    return specStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNamedSpecStatement()
  {
    return namedSpecStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getNamedSpecStatement_Str()
  {
    return (EAttribute)namedSpecStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNamedSpecStatement_Expr()
  {
    return (EReference)namedSpecStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNamedSpecStatement_Pattern()
  {
    return (EReference)namedSpecStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPatternStatement()
  {
    return patternStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWhenStatement()
  {
    return whenStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenStatement_Condition()
  {
    return (EReference)whenStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenStatement_Event()
  {
    return (EReference)whenStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getWhenStatement_Excl()
  {
    return (EAttribute)whenStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWheneverStatement()
  {
    return wheneverStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverStatement_Cause()
  {
    return (EReference)wheneverStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getWheneverStatement_Excl()
  {
    return (EAttribute)wheneverStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverStatement_Interval()
  {
    return (EReference)wheneverStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRealTimeStatement()
  {
    return realTimeStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRealTimeStatement_Event()
  {
    return (EReference)realTimeStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRealTimeStatement_Jitter()
  {
    return (EReference)realTimeStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTimeInterval()
  {
    return timeIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTimeInterval_Low()
  {
    return (EReference)timeIntervalEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTimeInterval_High()
  {
    return (EReference)timeIntervalEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSynchStatement()
  {
    return synchStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getSynchStatement_Val()
  {
    return (EAttribute)synchStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getSynchStatement_Val2()
  {
    return (EAttribute)synchStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getSynchStatement_Sim()
  {
    return (EAttribute)synchStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getOrderStatement()
  {
    return orderStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getOrderStatement_Comps()
  {
    return (EReference)orderStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAbstraction()
  {
    return abstractionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPropertyStatement()
  {
    return propertyStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPropertyStatement_Expr()
  {
    return (EReference)propertyStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getConstStatement()
  {
    return constStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getConstStatement_Type()
  {
    return (EReference)constStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getConstStatement_Expr()
  {
    return (EReference)constStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEqStatement()
  {
    return eqStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getEqStatement_Lhs()
  {
    return (EReference)eqStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getEqStatement_Expr()
  {
    return (EReference)eqStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getInputStatement()
  {
    return inputStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getInputStatement_Lhs()
  {
    return (EReference)inputStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAssignStatement()
  {
    return assignStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAssignStatement_Id()
  {
    return (EReference)assignStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAssignStatement_Expr()
  {
    return (EReference)assignStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFnDef()
  {
    return fnDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFnDef_Args()
  {
    return (EReference)fnDefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFnDef_Type()
  {
    return (EReference)fnDefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFnDef_Expr()
  {
    return (EReference)fnDefEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLibraryFnDef()
  {
    return libraryFnDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLibraryFnDef_Args()
  {
    return (EReference)libraryFnDefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLibraryFnDef_Type()
  {
    return (EReference)libraryFnDefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLinearizationDef()
  {
    return linearizationDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationDef_Args()
  {
    return (EReference)linearizationDefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationDef_Intervals()
  {
    return (EReference)linearizationDefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationDef_Precision()
  {
    return (EReference)linearizationDefEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationDef_ExprBody()
  {
    return (EReference)linearizationDefEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLinearizationInterval()
  {
    return linearizationIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationInterval_Start()
  {
    return (EReference)linearizationIntervalEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLinearizationInterval_End()
  {
    return (EReference)linearizationIntervalEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNodeDef()
  {
    return nodeDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeDef_Args()
  {
    return (EReference)nodeDefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeDef_Rets()
  {
    return (EReference)nodeDefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeDef_NodeBody()
  {
    return (EReference)nodeDefEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNodeBodyExpr()
  {
    return nodeBodyExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeBodyExpr_Locs()
  {
    return (EReference)nodeBodyExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeBodyExpr_Stmts()
  {
    return (EReference)nodeBodyExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNodeStmt()
  {
    return nodeStmtEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeStmt_Expr()
  {
    return (EReference)nodeStmtEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getArg()
  {
    return argEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArg_Type()
  {
    return (EReference)argEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getType()
  {
    return typeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRecordDef()
  {
    return recordDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordDef_Args()
  {
    return (EReference)recordDefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEnumStatement()
  {
    return enumStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getEnumStatement_Enums()
  {
    return (EReference)enumStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getExpr()
  {
    return exprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getComponentRef()
  {
    return componentRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getArrayLiteralExpr()
  {
    return arrayLiteralExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArrayLiteralExpr_Elems()
  {
    return (EReference)arrayLiteralExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getDoubleDotRef()
  {
    return doubleDotRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getDoubleDotRef_Elm()
  {
    return (EReference)doubleDotRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNamedID()
  {
    return namedIDEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAgreeContractLibrary()
  {
    return agreeContractLibraryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAgreeContractLibrary_Contract()
  {
    return (EReference)agreeContractLibraryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAgreeContractSubclause()
  {
    return agreeContractSubclauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAgreeContractSubclause_Contract()
  {
    return (EReference)agreeContractSubclauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAgreeContract()
  {
    return agreeContractEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAgreeContract_Specs()
  {
    return (EReference)agreeContractEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getInitialStatement()
  {
    return initialStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getInitialStatement_Expr()
  {
    return (EReference)initialStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getParamStatement()
  {
    return paramStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getParamStatement_Expr()
  {
    return (EReference)paramStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getParamStatement_Type()
  {
    return (EReference)paramStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLiftStatement()
  {
    return liftStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLiftStatement_Subcomp()
  {
    return (EReference)liftStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getConnectionStatement()
  {
    return connectionStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getConnectionStatement_Conn()
  {
    return (EReference)connectionStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getConnectionStatement_Expr()
  {
    return (EReference)connectionStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAssumeStatement()
  {
    return assumeStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGuaranteeStatement()
  {
    return guaranteeStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAssertStatement()
  {
    return assertStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLemmaStatement()
  {
    return lemmaStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAlwaysStatement()
  {
    return alwaysStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getAlwaysStatement_Expr()
  {
    return (EReference)alwaysStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWhenHoldsStatement()
  {
    return whenHoldsStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenHoldsStatement_ConditionInterval()
  {
    return (EReference)whenHoldsStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenHoldsStatement_EventInterval()
  {
    return (EReference)whenHoldsStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWhenOccursStatment()
  {
    return whenOccursStatmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenOccursStatment_Times()
  {
    return (EReference)whenOccursStatmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWhenOccursStatment_Interval()
  {
    return (EReference)whenOccursStatmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWheneverOccursStatement()
  {
    return wheneverOccursStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverOccursStatement_Effect()
  {
    return (EReference)wheneverOccursStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWheneverBecomesTrueStatement()
  {
    return wheneverBecomesTrueStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverBecomesTrueStatement_Effect()
  {
    return (EReference)wheneverBecomesTrueStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWheneverHoldsStatement()
  {
    return wheneverHoldsStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverHoldsStatement_Effect()
  {
    return (EReference)wheneverHoldsStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getWheneverImpliesStatement()
  {
    return wheneverImpliesStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverImpliesStatement_Lhs()
  {
    return (EReference)wheneverImpliesStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getWheneverImpliesStatement_Rhs()
  {
    return (EReference)wheneverImpliesStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPeriodicStatement()
  {
    return periodicStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPeriodicStatement_Period()
  {
    return (EReference)periodicStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSporadicStatement()
  {
    return sporadicStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getSporadicStatement_Iat()
  {
    return (EReference)sporadicStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getClosedTimeInterval()
  {
    return closedTimeIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getOpenLeftTimeInterval()
  {
    return openLeftTimeIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getOpenRightTimeInterval()
  {
    return openRightTimeIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getOpenTimeInterval()
  {
    return openTimeIntervalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getMNSynchStatement()
  {
    return mnSynchStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getMNSynchStatement_Comp1()
  {
    return (EReference)mnSynchStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getMNSynchStatement_Comp2()
  {
    return (EReference)mnSynchStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getMNSynchStatement_Max()
  {
    return (EAttribute)mnSynchStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getMNSynchStatement_Min()
  {
    return (EAttribute)mnSynchStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getCalenStatement()
  {
    return calenStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getCalenStatement_Els()
  {
    return (EReference)calenStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getAsynchStatement()
  {
    return asynchStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLatchedStatement()
  {
    return latchedStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNodeEq()
  {
    return nodeEqEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNodeEq_Lhs()
  {
    return (EReference)nodeEqEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNodeLemma()
  {
    return nodeLemmaEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getNodeLemma_Str()
  {
    return (EAttribute)nodeLemmaEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getArrayType()
  {
    return arrayTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArrayType_Stem()
  {
    return (EReference)arrayTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getArrayType_Size()
  {
    return (EAttribute)arrayTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPrimType()
  {
    return primTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrimType_Name()
  {
    return (EAttribute)primTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrimType_LowNeg()
  {
    return (EAttribute)primTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrimType_RangeLow()
  {
    return (EAttribute)primTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrimType_HighNeg()
  {
    return (EAttribute)primTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getPrimType_RangeHigh()
  {
    return (EAttribute)primTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getForallExpr()
  {
    return forallExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getForallExpr_Binding()
  {
    return (EReference)forallExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getForallExpr_Array()
  {
    return (EReference)forallExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getForallExpr_Expr()
  {
    return (EReference)forallExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getExistsExpr()
  {
    return existsExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getExistsExpr_Binding()
  {
    return (EReference)existsExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getExistsExpr_Array()
  {
    return (EReference)existsExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getExistsExpr_Expr()
  {
    return (EReference)existsExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFlatmapExpr()
  {
    return flatmapExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFlatmapExpr_Binding()
  {
    return (EReference)flatmapExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFlatmapExpr_Array()
  {
    return (EReference)flatmapExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFlatmapExpr_Expr()
  {
    return (EReference)flatmapExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFoldLeftExpr()
  {
    return foldLeftExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldLeftExpr_Binding()
  {
    return (EReference)foldLeftExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldLeftExpr_Array()
  {
    return (EReference)foldLeftExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldLeftExpr_Accumulator()
  {
    return (EReference)foldLeftExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldLeftExpr_Initial()
  {
    return (EReference)foldLeftExprEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldLeftExpr_Expr()
  {
    return (EReference)foldLeftExprEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFoldRightExpr()
  {
    return foldRightExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldRightExpr_Binding()
  {
    return (EReference)foldRightExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldRightExpr_Array()
  {
    return (EReference)foldRightExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldRightExpr_Accumulator()
  {
    return (EReference)foldRightExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldRightExpr_Initial()
  {
    return (EReference)foldRightExprEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFoldRightExpr_Expr()
  {
    return (EReference)foldRightExprEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getBinaryExpr()
  {
    return binaryExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getBinaryExpr_Left()
  {
    return (EReference)binaryExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getBinaryExpr_Op()
  {
    return (EAttribute)binaryExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getBinaryExpr_Right()
  {
    return (EReference)binaryExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getUnaryExpr()
  {
    return unaryExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getUnaryExpr_Op()
  {
    return (EAttribute)unaryExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getUnaryExpr_Expr()
  {
    return (EReference)unaryExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getIfThenElseExpr()
  {
    return ifThenElseExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIfThenElseExpr_A()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIfThenElseExpr_B()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIfThenElseExpr_C()
  {
    return (EReference)ifThenElseExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getThisRef()
  {
    return thisRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPrevExpr()
  {
    return prevExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPrevExpr_Delay()
  {
    return (EReference)prevExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPrevExpr_Init()
  {
    return (EReference)prevExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getGetPropertyExpr()
  {
    return getPropertyExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGetPropertyExpr_ComponentRef()
  {
    return (EReference)getPropertyExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getGetPropertyExpr_Prop()
  {
    return (EReference)getPropertyExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getArrayUpdateExpr()
  {
    return arrayUpdateExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArrayUpdateExpr_Array()
  {
    return (EReference)arrayUpdateExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArrayUpdateExpr_Indices()
  {
    return (EReference)arrayUpdateExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArrayUpdateExpr_ValueExprs()
  {
    return (EReference)arrayUpdateExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRecordUpdateExpr()
  {
    return recordUpdateExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordUpdateExpr_Record()
  {
    return (EReference)recordUpdateExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordUpdateExpr_Key()
  {
    return (EReference)recordUpdateExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordUpdateExpr_Expr()
  {
    return (EReference)recordUpdateExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getArraySubExpr()
  {
    return arraySubExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArraySubExpr_Expr()
  {
    return (EReference)arraySubExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getArraySubExpr_Index()
  {
    return (EReference)arraySubExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTagExpr()
  {
    return tagExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTagExpr_Stem()
  {
    return (EReference)tagExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getTagExpr_Tag()
  {
    return (EAttribute)tagExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getSelectionExpr()
  {
    return selectionExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getSelectionExpr_Target()
  {
    return (EReference)selectionExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getSelectionExpr_Field()
  {
    return (EReference)selectionExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getNamedElmExpr()
  {
    return namedElmExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getNamedElmExpr_Elm()
  {
    return (EReference)namedElmExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTimeExpr()
  {
    return timeExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getIndicesExpr()
  {
    return indicesExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getIndicesExpr_Array()
  {
    return (EReference)indicesExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getCallExpr()
  {
    return callExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getCallExpr_Ref()
  {
    return (EReference)callExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getCallExpr_Args()
  {
    return (EReference)callExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRecordLitExpr()
  {
    return recordLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordLitExpr_RecordType()
  {
    return (EReference)recordLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordLitExpr_Args()
  {
    return (EReference)recordLitExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRecordLitExpr_ArgExpr()
  {
    return (EReference)recordLitExprEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEnumLitExpr()
  {
    return enumLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getEnumLitExpr_EnumType()
  {
    return (EReference)enumLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getEnumLitExpr_Value()
  {
    return (EAttribute)enumLitExprEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getIntLitExpr()
  {
    return intLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getIntLitExpr_Val()
  {
    return (EAttribute)intLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getPreExpr()
  {
    return preExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getPreExpr_Expr()
  {
    return (EReference)preExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getEventExpr()
  {
    return eventExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getEventExpr_Id()
  {
    return (EReference)eventExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getLatchedExpr()
  {
    return latchedExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getLatchedExpr_Expr()
  {
    return (EReference)latchedExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTimeOfExpr()
  {
    return timeOfExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTimeOfExpr_Id()
  {
    return (EReference)timeOfExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTimeRiseExpr()
  {
    return timeRiseExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTimeRiseExpr_Id()
  {
    return (EReference)timeRiseExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getTimeFallExpr()
  {
    return timeFallExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getTimeFallExpr_Id()
  {
    return (EReference)timeFallExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRealLitExpr()
  {
    return realLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EAttribute getRealLitExpr_Val()
  {
    return (EAttribute)realLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getBoolLitExpr()
  {
    return boolLitExprEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getBoolLitExpr_Val()
  {
    return (EReference)boolLitExprEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getFloorCast()
  {
    return floorCastEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getFloorCast_Expr()
  {
    return (EReference)floorCastEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getRealCast()
  {
    return realCastEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EReference getRealCast_Expr()
  {
    return (EReference)realCastEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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

    namedSpecStatementEClass = createEClass(NAMED_SPEC_STATEMENT);
    createEAttribute(namedSpecStatementEClass, NAMED_SPEC_STATEMENT__STR);
    createEReference(namedSpecStatementEClass, NAMED_SPEC_STATEMENT__EXPR);
    createEReference(namedSpecStatementEClass, NAMED_SPEC_STATEMENT__PATTERN);

    patternStatementEClass = createEClass(PATTERN_STATEMENT);

    whenStatementEClass = createEClass(WHEN_STATEMENT);
    createEReference(whenStatementEClass, WHEN_STATEMENT__CONDITION);
    createEReference(whenStatementEClass, WHEN_STATEMENT__EVENT);
    createEAttribute(whenStatementEClass, WHEN_STATEMENT__EXCL);

    wheneverStatementEClass = createEClass(WHENEVER_STATEMENT);
    createEReference(wheneverStatementEClass, WHENEVER_STATEMENT__CAUSE);
    createEAttribute(wheneverStatementEClass, WHENEVER_STATEMENT__EXCL);
    createEReference(wheneverStatementEClass, WHENEVER_STATEMENT__INTERVAL);

    realTimeStatementEClass = createEClass(REAL_TIME_STATEMENT);
    createEReference(realTimeStatementEClass, REAL_TIME_STATEMENT__EVENT);
    createEReference(realTimeStatementEClass, REAL_TIME_STATEMENT__JITTER);

    timeIntervalEClass = createEClass(TIME_INTERVAL);
    createEReference(timeIntervalEClass, TIME_INTERVAL__LOW);
    createEReference(timeIntervalEClass, TIME_INTERVAL__HIGH);

    synchStatementEClass = createEClass(SYNCH_STATEMENT);
    createEAttribute(synchStatementEClass, SYNCH_STATEMENT__VAL);
    createEAttribute(synchStatementEClass, SYNCH_STATEMENT__VAL2);
    createEAttribute(synchStatementEClass, SYNCH_STATEMENT__SIM);

    orderStatementEClass = createEClass(ORDER_STATEMENT);
    createEReference(orderStatementEClass, ORDER_STATEMENT__COMPS);

    abstractionEClass = createEClass(ABSTRACTION);

    propertyStatementEClass = createEClass(PROPERTY_STATEMENT);
    createEReference(propertyStatementEClass, PROPERTY_STATEMENT__EXPR);

    constStatementEClass = createEClass(CONST_STATEMENT);
    createEReference(constStatementEClass, CONST_STATEMENT__TYPE);
    createEReference(constStatementEClass, CONST_STATEMENT__EXPR);

    eqStatementEClass = createEClass(EQ_STATEMENT);
    createEReference(eqStatementEClass, EQ_STATEMENT__LHS);
    createEReference(eqStatementEClass, EQ_STATEMENT__EXPR);

    inputStatementEClass = createEClass(INPUT_STATEMENT);
    createEReference(inputStatementEClass, INPUT_STATEMENT__LHS);

    assignStatementEClass = createEClass(ASSIGN_STATEMENT);
    createEReference(assignStatementEClass, ASSIGN_STATEMENT__ID);
    createEReference(assignStatementEClass, ASSIGN_STATEMENT__EXPR);

    fnDefEClass = createEClass(FN_DEF);
    createEReference(fnDefEClass, FN_DEF__ARGS);
    createEReference(fnDefEClass, FN_DEF__TYPE);
    createEReference(fnDefEClass, FN_DEF__EXPR);

    libraryFnDefEClass = createEClass(LIBRARY_FN_DEF);
    createEReference(libraryFnDefEClass, LIBRARY_FN_DEF__ARGS);
    createEReference(libraryFnDefEClass, LIBRARY_FN_DEF__TYPE);

    linearizationDefEClass = createEClass(LINEARIZATION_DEF);
    createEReference(linearizationDefEClass, LINEARIZATION_DEF__ARGS);
    createEReference(linearizationDefEClass, LINEARIZATION_DEF__INTERVALS);
    createEReference(linearizationDefEClass, LINEARIZATION_DEF__PRECISION);
    createEReference(linearizationDefEClass, LINEARIZATION_DEF__EXPR_BODY);

    linearizationIntervalEClass = createEClass(LINEARIZATION_INTERVAL);
    createEReference(linearizationIntervalEClass, LINEARIZATION_INTERVAL__START);
    createEReference(linearizationIntervalEClass, LINEARIZATION_INTERVAL__END);

    nodeDefEClass = createEClass(NODE_DEF);
    createEReference(nodeDefEClass, NODE_DEF__ARGS);
    createEReference(nodeDefEClass, NODE_DEF__RETS);
    createEReference(nodeDefEClass, NODE_DEF__NODE_BODY);

    nodeBodyExprEClass = createEClass(NODE_BODY_EXPR);
    createEReference(nodeBodyExprEClass, NODE_BODY_EXPR__LOCS);
    createEReference(nodeBodyExprEClass, NODE_BODY_EXPR__STMTS);

    nodeStmtEClass = createEClass(NODE_STMT);
    createEReference(nodeStmtEClass, NODE_STMT__EXPR);

    argEClass = createEClass(ARG);
    createEReference(argEClass, ARG__TYPE);

    typeEClass = createEClass(TYPE);

    recordDefEClass = createEClass(RECORD_DEF);
    createEReference(recordDefEClass, RECORD_DEF__ARGS);

    enumStatementEClass = createEClass(ENUM_STATEMENT);
    createEReference(enumStatementEClass, ENUM_STATEMENT__ENUMS);

    exprEClass = createEClass(EXPR);

    componentRefEClass = createEClass(COMPONENT_REF);

    arrayLiteralExprEClass = createEClass(ARRAY_LITERAL_EXPR);
    createEReference(arrayLiteralExprEClass, ARRAY_LITERAL_EXPR__ELEMS);

    doubleDotRefEClass = createEClass(DOUBLE_DOT_REF);
    createEReference(doubleDotRefEClass, DOUBLE_DOT_REF__ELM);

    namedIDEClass = createEClass(NAMED_ID);

    agreeContractLibraryEClass = createEClass(AGREE_CONTRACT_LIBRARY);
    createEReference(agreeContractLibraryEClass, AGREE_CONTRACT_LIBRARY__CONTRACT);

    agreeContractSubclauseEClass = createEClass(AGREE_CONTRACT_SUBCLAUSE);
    createEReference(agreeContractSubclauseEClass, AGREE_CONTRACT_SUBCLAUSE__CONTRACT);

    agreeContractEClass = createEClass(AGREE_CONTRACT);
    createEReference(agreeContractEClass, AGREE_CONTRACT__SPECS);

    initialStatementEClass = createEClass(INITIAL_STATEMENT);
    createEReference(initialStatementEClass, INITIAL_STATEMENT__EXPR);

    paramStatementEClass = createEClass(PARAM_STATEMENT);
    createEReference(paramStatementEClass, PARAM_STATEMENT__EXPR);
    createEReference(paramStatementEClass, PARAM_STATEMENT__TYPE);

    liftStatementEClass = createEClass(LIFT_STATEMENT);
    createEReference(liftStatementEClass, LIFT_STATEMENT__SUBCOMP);

    connectionStatementEClass = createEClass(CONNECTION_STATEMENT);
    createEReference(connectionStatementEClass, CONNECTION_STATEMENT__CONN);
    createEReference(connectionStatementEClass, CONNECTION_STATEMENT__EXPR);

    assumeStatementEClass = createEClass(ASSUME_STATEMENT);

    guaranteeStatementEClass = createEClass(GUARANTEE_STATEMENT);

    assertStatementEClass = createEClass(ASSERT_STATEMENT);

    lemmaStatementEClass = createEClass(LEMMA_STATEMENT);

    alwaysStatementEClass = createEClass(ALWAYS_STATEMENT);
    createEReference(alwaysStatementEClass, ALWAYS_STATEMENT__EXPR);

    whenHoldsStatementEClass = createEClass(WHEN_HOLDS_STATEMENT);
    createEReference(whenHoldsStatementEClass, WHEN_HOLDS_STATEMENT__CONDITION_INTERVAL);
    createEReference(whenHoldsStatementEClass, WHEN_HOLDS_STATEMENT__EVENT_INTERVAL);

    whenOccursStatmentEClass = createEClass(WHEN_OCCURS_STATMENT);
    createEReference(whenOccursStatmentEClass, WHEN_OCCURS_STATMENT__TIMES);
    createEReference(whenOccursStatmentEClass, WHEN_OCCURS_STATMENT__INTERVAL);

    wheneverOccursStatementEClass = createEClass(WHENEVER_OCCURS_STATEMENT);
    createEReference(wheneverOccursStatementEClass, WHENEVER_OCCURS_STATEMENT__EFFECT);

    wheneverBecomesTrueStatementEClass = createEClass(WHENEVER_BECOMES_TRUE_STATEMENT);
    createEReference(wheneverBecomesTrueStatementEClass, WHENEVER_BECOMES_TRUE_STATEMENT__EFFECT);

    wheneverHoldsStatementEClass = createEClass(WHENEVER_HOLDS_STATEMENT);
    createEReference(wheneverHoldsStatementEClass, WHENEVER_HOLDS_STATEMENT__EFFECT);

    wheneverImpliesStatementEClass = createEClass(WHENEVER_IMPLIES_STATEMENT);
    createEReference(wheneverImpliesStatementEClass, WHENEVER_IMPLIES_STATEMENT__LHS);
    createEReference(wheneverImpliesStatementEClass, WHENEVER_IMPLIES_STATEMENT__RHS);

    periodicStatementEClass = createEClass(PERIODIC_STATEMENT);
    createEReference(periodicStatementEClass, PERIODIC_STATEMENT__PERIOD);

    sporadicStatementEClass = createEClass(SPORADIC_STATEMENT);
    createEReference(sporadicStatementEClass, SPORADIC_STATEMENT__IAT);

    closedTimeIntervalEClass = createEClass(CLOSED_TIME_INTERVAL);

    openLeftTimeIntervalEClass = createEClass(OPEN_LEFT_TIME_INTERVAL);

    openRightTimeIntervalEClass = createEClass(OPEN_RIGHT_TIME_INTERVAL);

    openTimeIntervalEClass = createEClass(OPEN_TIME_INTERVAL);

    mnSynchStatementEClass = createEClass(MN_SYNCH_STATEMENT);
    createEReference(mnSynchStatementEClass, MN_SYNCH_STATEMENT__COMP1);
    createEReference(mnSynchStatementEClass, MN_SYNCH_STATEMENT__COMP2);
    createEAttribute(mnSynchStatementEClass, MN_SYNCH_STATEMENT__MAX);
    createEAttribute(mnSynchStatementEClass, MN_SYNCH_STATEMENT__MIN);

    calenStatementEClass = createEClass(CALEN_STATEMENT);
    createEReference(calenStatementEClass, CALEN_STATEMENT__ELS);

    asynchStatementEClass = createEClass(ASYNCH_STATEMENT);

    latchedStatementEClass = createEClass(LATCHED_STATEMENT);

    nodeEqEClass = createEClass(NODE_EQ);
    createEReference(nodeEqEClass, NODE_EQ__LHS);

    nodeLemmaEClass = createEClass(NODE_LEMMA);
    createEAttribute(nodeLemmaEClass, NODE_LEMMA__STR);

    arrayTypeEClass = createEClass(ARRAY_TYPE);
    createEReference(arrayTypeEClass, ARRAY_TYPE__STEM);
    createEAttribute(arrayTypeEClass, ARRAY_TYPE__SIZE);

    primTypeEClass = createEClass(PRIM_TYPE);
    createEAttribute(primTypeEClass, PRIM_TYPE__NAME);
    createEAttribute(primTypeEClass, PRIM_TYPE__LOW_NEG);
    createEAttribute(primTypeEClass, PRIM_TYPE__RANGE_LOW);
    createEAttribute(primTypeEClass, PRIM_TYPE__HIGH_NEG);
    createEAttribute(primTypeEClass, PRIM_TYPE__RANGE_HIGH);

    forallExprEClass = createEClass(FORALL_EXPR);
    createEReference(forallExprEClass, FORALL_EXPR__BINDING);
    createEReference(forallExprEClass, FORALL_EXPR__ARRAY);
    createEReference(forallExprEClass, FORALL_EXPR__EXPR);

    existsExprEClass = createEClass(EXISTS_EXPR);
    createEReference(existsExprEClass, EXISTS_EXPR__BINDING);
    createEReference(existsExprEClass, EXISTS_EXPR__ARRAY);
    createEReference(existsExprEClass, EXISTS_EXPR__EXPR);

    flatmapExprEClass = createEClass(FLATMAP_EXPR);
    createEReference(flatmapExprEClass, FLATMAP_EXPR__BINDING);
    createEReference(flatmapExprEClass, FLATMAP_EXPR__ARRAY);
    createEReference(flatmapExprEClass, FLATMAP_EXPR__EXPR);

    foldLeftExprEClass = createEClass(FOLD_LEFT_EXPR);
    createEReference(foldLeftExprEClass, FOLD_LEFT_EXPR__BINDING);
    createEReference(foldLeftExprEClass, FOLD_LEFT_EXPR__ARRAY);
    createEReference(foldLeftExprEClass, FOLD_LEFT_EXPR__ACCUMULATOR);
    createEReference(foldLeftExprEClass, FOLD_LEFT_EXPR__INITIAL);
    createEReference(foldLeftExprEClass, FOLD_LEFT_EXPR__EXPR);

    foldRightExprEClass = createEClass(FOLD_RIGHT_EXPR);
    createEReference(foldRightExprEClass, FOLD_RIGHT_EXPR__BINDING);
    createEReference(foldRightExprEClass, FOLD_RIGHT_EXPR__ARRAY);
    createEReference(foldRightExprEClass, FOLD_RIGHT_EXPR__ACCUMULATOR);
    createEReference(foldRightExprEClass, FOLD_RIGHT_EXPR__INITIAL);
    createEReference(foldRightExprEClass, FOLD_RIGHT_EXPR__EXPR);

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

    thisRefEClass = createEClass(THIS_REF);

    prevExprEClass = createEClass(PREV_EXPR);
    createEReference(prevExprEClass, PREV_EXPR__DELAY);
    createEReference(prevExprEClass, PREV_EXPR__INIT);

    getPropertyExprEClass = createEClass(GET_PROPERTY_EXPR);
    createEReference(getPropertyExprEClass, GET_PROPERTY_EXPR__COMPONENT_REF);
    createEReference(getPropertyExprEClass, GET_PROPERTY_EXPR__PROP);

    arrayUpdateExprEClass = createEClass(ARRAY_UPDATE_EXPR);
    createEReference(arrayUpdateExprEClass, ARRAY_UPDATE_EXPR__ARRAY);
    createEReference(arrayUpdateExprEClass, ARRAY_UPDATE_EXPR__INDICES);
    createEReference(arrayUpdateExprEClass, ARRAY_UPDATE_EXPR__VALUE_EXPRS);

    recordUpdateExprEClass = createEClass(RECORD_UPDATE_EXPR);
    createEReference(recordUpdateExprEClass, RECORD_UPDATE_EXPR__RECORD);
    createEReference(recordUpdateExprEClass, RECORD_UPDATE_EXPR__KEY);
    createEReference(recordUpdateExprEClass, RECORD_UPDATE_EXPR__EXPR);

    arraySubExprEClass = createEClass(ARRAY_SUB_EXPR);
    createEReference(arraySubExprEClass, ARRAY_SUB_EXPR__EXPR);
    createEReference(arraySubExprEClass, ARRAY_SUB_EXPR__INDEX);

    tagExprEClass = createEClass(TAG_EXPR);
    createEReference(tagExprEClass, TAG_EXPR__STEM);
    createEAttribute(tagExprEClass, TAG_EXPR__TAG);

    selectionExprEClass = createEClass(SELECTION_EXPR);
    createEReference(selectionExprEClass, SELECTION_EXPR__TARGET);
    createEReference(selectionExprEClass, SELECTION_EXPR__FIELD);

    namedElmExprEClass = createEClass(NAMED_ELM_EXPR);
    createEReference(namedElmExprEClass, NAMED_ELM_EXPR__ELM);

    timeExprEClass = createEClass(TIME_EXPR);

    indicesExprEClass = createEClass(INDICES_EXPR);
    createEReference(indicesExprEClass, INDICES_EXPR__ARRAY);

    callExprEClass = createEClass(CALL_EXPR);
    createEReference(callExprEClass, CALL_EXPR__REF);
    createEReference(callExprEClass, CALL_EXPR__ARGS);

    recordLitExprEClass = createEClass(RECORD_LIT_EXPR);
    createEReference(recordLitExprEClass, RECORD_LIT_EXPR__RECORD_TYPE);
    createEReference(recordLitExprEClass, RECORD_LIT_EXPR__ARGS);
    createEReference(recordLitExprEClass, RECORD_LIT_EXPR__ARG_EXPR);

    enumLitExprEClass = createEClass(ENUM_LIT_EXPR);
    createEReference(enumLitExprEClass, ENUM_LIT_EXPR__ENUM_TYPE);
    createEAttribute(enumLitExprEClass, ENUM_LIT_EXPR__VALUE);

    intLitExprEClass = createEClass(INT_LIT_EXPR);
    createEAttribute(intLitExprEClass, INT_LIT_EXPR__VAL);

    preExprEClass = createEClass(PRE_EXPR);
    createEReference(preExprEClass, PRE_EXPR__EXPR);

    eventExprEClass = createEClass(EVENT_EXPR);
    createEReference(eventExprEClass, EVENT_EXPR__ID);

    latchedExprEClass = createEClass(LATCHED_EXPR);
    createEReference(latchedExprEClass, LATCHED_EXPR__EXPR);

    timeOfExprEClass = createEClass(TIME_OF_EXPR);
    createEReference(timeOfExprEClass, TIME_OF_EXPR__ID);

    timeRiseExprEClass = createEClass(TIME_RISE_EXPR);
    createEReference(timeRiseExprEClass, TIME_RISE_EXPR__ID);

    timeFallExprEClass = createEClass(TIME_FALL_EXPR);
    createEReference(timeFallExprEClass, TIME_FALL_EXPR__ID);

    realLitExprEClass = createEClass(REAL_LIT_EXPR);
    createEAttribute(realLitExprEClass, REAL_LIT_EXPR__VAL);

    boolLitExprEClass = createEClass(BOOL_LIT_EXPR);
    createEReference(boolLitExprEClass, BOOL_LIT_EXPR__VAL);

    floorCastEClass = createEClass(FLOOR_CAST);
    createEReference(floorCastEClass, FLOOR_CAST__EXPR);

    realCastEClass = createEClass(REAL_CAST);
    createEReference(realCastEClass, REAL_CAST__EXPR);
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
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    agreeLibraryEClass.getESuperTypes().add(theAadl2Package.getAnnexLibrary());
    agreeSubclauseEClass.getESuperTypes().add(theAadl2Package.getAnnexSubclause());
    contractEClass.getESuperTypes().add(theAadl2Package.getElement());
    specStatementEClass.getESuperTypes().add(theAadl2Package.getElement());
    namedSpecStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    namedSpecStatementEClass.getESuperTypes().add(this.getSpecStatement());
    whenStatementEClass.getESuperTypes().add(this.getPatternStatement());
    wheneverStatementEClass.getESuperTypes().add(this.getPatternStatement());
    realTimeStatementEClass.getESuperTypes().add(this.getPatternStatement());
    synchStatementEClass.getESuperTypes().add(this.getSpecStatement());
    orderStatementEClass.getESuperTypes().add(this.getSpecStatement());
    abstractionEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    propertyStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    propertyStatementEClass.getESuperTypes().add(this.getSpecStatement());
    constStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    constStatementEClass.getESuperTypes().add(this.getSpecStatement());
    eqStatementEClass.getESuperTypes().add(theAadl2Package.getElement());
    eqStatementEClass.getESuperTypes().add(this.getSpecStatement());
    inputStatementEClass.getESuperTypes().add(theAadl2Package.getElement());
    inputStatementEClass.getESuperTypes().add(this.getSpecStatement());
    assignStatementEClass.getESuperTypes().add(this.getSpecStatement());
    fnDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    fnDefEClass.getESuperTypes().add(this.getSpecStatement());
    fnDefEClass.getESuperTypes().add(this.getAbstraction());
    libraryFnDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    libraryFnDefEClass.getESuperTypes().add(this.getSpecStatement());
    libraryFnDefEClass.getESuperTypes().add(this.getAbstraction());
    linearizationDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    linearizationDefEClass.getESuperTypes().add(this.getSpecStatement());
    linearizationDefEClass.getESuperTypes().add(this.getAbstraction());
    linearizationIntervalEClass.getESuperTypes().add(theAadl2Package.getElement());
    nodeDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    nodeDefEClass.getESuperTypes().add(this.getSpecStatement());
    nodeDefEClass.getESuperTypes().add(this.getAbstraction());
    nodeBodyExprEClass.getESuperTypes().add(theAadl2Package.getElement());
    nodeStmtEClass.getESuperTypes().add(theAadl2Package.getElement());
    argEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    typeEClass.getESuperTypes().add(theAadl2Package.getElement());
    recordDefEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    recordDefEClass.getESuperTypes().add(this.getSpecStatement());
    enumStatementEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    enumStatementEClass.getESuperTypes().add(this.getSpecStatement());
    exprEClass.getESuperTypes().add(theAadl2Package.getElement());
    arrayLiteralExprEClass.getESuperTypes().add(this.getExpr());
    doubleDotRefEClass.getESuperTypes().add(this.getType());
    doubleDotRefEClass.getESuperTypes().add(this.getComponentRef());
    namedIDEClass.getESuperTypes().add(theAadl2Package.getNamedElement());
    agreeContractLibraryEClass.getESuperTypes().add(this.getAgreeLibrary());
    agreeContractSubclauseEClass.getESuperTypes().add(this.getAgreeSubclause());
    agreeContractEClass.getESuperTypes().add(this.getContract());
    initialStatementEClass.getESuperTypes().add(this.getSpecStatement());
    paramStatementEClass.getESuperTypes().add(this.getSpecStatement());
    liftStatementEClass.getESuperTypes().add(this.getSpecStatement());
    connectionStatementEClass.getESuperTypes().add(this.getSpecStatement());
    assumeStatementEClass.getESuperTypes().add(this.getNamedSpecStatement());
    guaranteeStatementEClass.getESuperTypes().add(this.getNamedSpecStatement());
    assertStatementEClass.getESuperTypes().add(this.getNamedSpecStatement());
    lemmaStatementEClass.getESuperTypes().add(this.getNamedSpecStatement());
    alwaysStatementEClass.getESuperTypes().add(this.getPatternStatement());
    whenHoldsStatementEClass.getESuperTypes().add(this.getWhenStatement());
    whenOccursStatmentEClass.getESuperTypes().add(this.getWhenStatement());
    wheneverOccursStatementEClass.getESuperTypes().add(this.getWheneverStatement());
    wheneverBecomesTrueStatementEClass.getESuperTypes().add(this.getWheneverStatement());
    wheneverHoldsStatementEClass.getESuperTypes().add(this.getWheneverStatement());
    wheneverImpliesStatementEClass.getESuperTypes().add(this.getWheneverStatement());
    periodicStatementEClass.getESuperTypes().add(this.getRealTimeStatement());
    sporadicStatementEClass.getESuperTypes().add(this.getRealTimeStatement());
    closedTimeIntervalEClass.getESuperTypes().add(this.getTimeInterval());
    openLeftTimeIntervalEClass.getESuperTypes().add(this.getTimeInterval());
    openRightTimeIntervalEClass.getESuperTypes().add(this.getTimeInterval());
    openTimeIntervalEClass.getESuperTypes().add(this.getTimeInterval());
    mnSynchStatementEClass.getESuperTypes().add(this.getSynchStatement());
    calenStatementEClass.getESuperTypes().add(this.getSynchStatement());
    asynchStatementEClass.getESuperTypes().add(this.getSynchStatement());
    latchedStatementEClass.getESuperTypes().add(this.getSynchStatement());
    nodeEqEClass.getESuperTypes().add(this.getNodeStmt());
    nodeLemmaEClass.getESuperTypes().add(this.getNodeStmt());
    arrayTypeEClass.getESuperTypes().add(this.getType());
    primTypeEClass.getESuperTypes().add(this.getType());
    forallExprEClass.getESuperTypes().add(this.getExpr());
    existsExprEClass.getESuperTypes().add(this.getExpr());
    flatmapExprEClass.getESuperTypes().add(this.getExpr());
    foldLeftExprEClass.getESuperTypes().add(this.getExpr());
    foldRightExprEClass.getESuperTypes().add(this.getExpr());
    binaryExprEClass.getESuperTypes().add(this.getExpr());
    unaryExprEClass.getESuperTypes().add(this.getExpr());
    ifThenElseExprEClass.getESuperTypes().add(this.getExpr());
    thisRefEClass.getESuperTypes().add(this.getComponentRef());
    prevExprEClass.getESuperTypes().add(this.getExpr());
    getPropertyExprEClass.getESuperTypes().add(this.getExpr());
    arrayUpdateExprEClass.getESuperTypes().add(this.getExpr());
    recordUpdateExprEClass.getESuperTypes().add(this.getExpr());
    arraySubExprEClass.getESuperTypes().add(this.getExpr());
    tagExprEClass.getESuperTypes().add(this.getExpr());
    selectionExprEClass.getESuperTypes().add(this.getExpr());
    namedElmExprEClass.getESuperTypes().add(this.getExpr());
    timeExprEClass.getESuperTypes().add(this.getExpr());
    indicesExprEClass.getESuperTypes().add(this.getExpr());
    callExprEClass.getESuperTypes().add(this.getExpr());
    recordLitExprEClass.getESuperTypes().add(this.getExpr());
    enumLitExprEClass.getESuperTypes().add(this.getExpr());
    intLitExprEClass.getESuperTypes().add(this.getExpr());
    preExprEClass.getESuperTypes().add(this.getExpr());
    eventExprEClass.getESuperTypes().add(this.getExpr());
    latchedExprEClass.getESuperTypes().add(this.getExpr());
    timeOfExprEClass.getESuperTypes().add(this.getExpr());
    timeRiseExprEClass.getESuperTypes().add(this.getExpr());
    timeFallExprEClass.getESuperTypes().add(this.getExpr());
    realLitExprEClass.getESuperTypes().add(this.getExpr());
    boolLitExprEClass.getESuperTypes().add(this.getExpr());
    floorCastEClass.getESuperTypes().add(this.getExpr());
    realCastEClass.getESuperTypes().add(this.getExpr());

    // Initialize classes and features; add operations and parameters
    initEClass(agreeLibraryEClass, AgreeLibrary.class, "AgreeLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(agreeSubclauseEClass, AgreeSubclause.class, "AgreeSubclause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(contractEClass, Contract.class, "Contract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(specStatementEClass, SpecStatement.class, "SpecStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(namedSpecStatementEClass, NamedSpecStatement.class, "NamedSpecStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamedSpecStatement_Str(), theEcorePackage.getEString(), "str", null, 0, 1, NamedSpecStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedSpecStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, NamedSpecStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedSpecStatement_Pattern(), this.getPatternStatement(), null, "pattern", null, 0, 1, NamedSpecStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(patternStatementEClass, PatternStatement.class, "PatternStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(whenStatementEClass, WhenStatement.class, "WhenStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWhenStatement_Condition(), this.getExpr(), null, "condition", null, 0, 1, WhenStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWhenStatement_Event(), this.getExpr(), null, "event", null, 0, 1, WhenStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWhenStatement_Excl(), theEcorePackage.getEString(), "excl", null, 0, 1, WhenStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(wheneverStatementEClass, WheneverStatement.class, "WheneverStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWheneverStatement_Cause(), this.getExpr(), null, "cause", null, 0, 1, WheneverStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWheneverStatement_Excl(), theEcorePackage.getEString(), "excl", null, 0, 1, WheneverStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWheneverStatement_Interval(), this.getTimeInterval(), null, "interval", null, 0, 1, WheneverStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realTimeStatementEClass, RealTimeStatement.class, "RealTimeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRealTimeStatement_Event(), this.getExpr(), null, "event", null, 0, 1, RealTimeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRealTimeStatement_Jitter(), this.getExpr(), null, "jitter", null, 0, 1, RealTimeStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeIntervalEClass, TimeInterval.class, "TimeInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTimeInterval_Low(), this.getExpr(), null, "low", null, 0, 1, TimeInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTimeInterval_High(), this.getExpr(), null, "high", null, 0, 1, TimeInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(synchStatementEClass, SynchStatement.class, "SynchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSynchStatement_Val(), theEcorePackage.getEString(), "val", null, 0, 1, SynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSynchStatement_Val2(), theEcorePackage.getEString(), "val2", null, 0, 1, SynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSynchStatement_Sim(), theEcorePackage.getEString(), "sim", null, 0, 1, SynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orderStatementEClass, OrderStatement.class, "OrderStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrderStatement_Comps(), theAadl2Package.getNamedElement(), null, "comps", null, 0, -1, OrderStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(abstractionEClass, Abstraction.class, "Abstraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(propertyStatementEClass, PropertyStatement.class, "PropertyStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constStatementEClass, ConstStatement.class, "ConstStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConstStatement_Type(), this.getType(), null, "type", null, 0, 1, ConstStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConstStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, ConstStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eqStatementEClass, EqStatement.class, "EqStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEqStatement_Lhs(), this.getArg(), null, "lhs", null, 0, -1, EqStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEqStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, EqStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inputStatementEClass, InputStatement.class, "InputStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInputStatement_Lhs(), this.getArg(), null, "lhs", null, 0, -1, InputStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assignStatementEClass, AssignStatement.class, "AssignStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAssignStatement_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, AssignStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAssignStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, AssignStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fnDefEClass, FnDef.class, "FnDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFnDef_Args(), this.getArg(), null, "args", null, 0, -1, FnDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFnDef_Type(), this.getType(), null, "type", null, 0, 1, FnDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFnDef_Expr(), this.getExpr(), null, "expr", null, 0, 1, FnDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(libraryFnDefEClass, LibraryFnDef.class, "LibraryFnDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLibraryFnDef_Args(), this.getArg(), null, "args", null, 0, -1, LibraryFnDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibraryFnDef_Type(), this.getType(), null, "type", null, 0, 1, LibraryFnDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(linearizationDefEClass, LinearizationDef.class, "LinearizationDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLinearizationDef_Args(), this.getArg(), null, "args", null, 0, -1, LinearizationDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLinearizationDef_Intervals(), this.getLinearizationInterval(), null, "intervals", null, 0, -1, LinearizationDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLinearizationDef_Precision(), this.getExpr(), null, "precision", null, 0, 1, LinearizationDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLinearizationDef_ExprBody(), this.getExpr(), null, "exprBody", null, 0, 1, LinearizationDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(linearizationIntervalEClass, LinearizationInterval.class, "LinearizationInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLinearizationInterval_Start(), this.getExpr(), null, "start", null, 0, 1, LinearizationInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLinearizationInterval_End(), this.getExpr(), null, "end", null, 0, 1, LinearizationInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeDefEClass, NodeDef.class, "NodeDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeDef_Args(), this.getArg(), null, "args", null, 0, -1, NodeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeDef_Rets(), this.getArg(), null, "rets", null, 0, -1, NodeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeDef_NodeBody(), this.getNodeBodyExpr(), null, "nodeBody", null, 0, 1, NodeDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeBodyExprEClass, NodeBodyExpr.class, "NodeBodyExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeBodyExpr_Locs(), this.getArg(), null, "locs", null, 0, -1, NodeBodyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeBodyExpr_Stmts(), this.getNodeStmt(), null, "stmts", null, 0, -1, NodeBodyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeStmtEClass, NodeStmt.class, "NodeStmt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeStmt_Expr(), this.getExpr(), null, "expr", null, 0, 1, NodeStmt.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argEClass, Arg.class, "Arg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArg_Type(), this.getType(), null, "type", null, 0, 1, Arg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(typeEClass, Type.class, "Type", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(recordDefEClass, RecordDef.class, "RecordDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRecordDef_Args(), this.getArg(), null, "args", null, 0, -1, RecordDef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumStatementEClass, EnumStatement.class, "EnumStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumStatement_Enums(), this.getNamedID(), null, "enums", null, 0, -1, EnumStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(exprEClass, Expr.class, "Expr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(componentRefEClass, ComponentRef.class, "ComponentRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(arrayLiteralExprEClass, ArrayLiteralExpr.class, "ArrayLiteralExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArrayLiteralExpr_Elems(), this.getExpr(), null, "elems", null, 0, -1, ArrayLiteralExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(doubleDotRefEClass, DoubleDotRef.class, "DoubleDotRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDoubleDotRef_Elm(), theAadl2Package.getNamedElement(), null, "elm", null, 0, 1, DoubleDotRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedIDEClass, NamedID.class, "NamedID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(agreeContractLibraryEClass, AgreeContractLibrary.class, "AgreeContractLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContractLibrary_Contract(), this.getContract(), null, "contract", null, 0, 1, AgreeContractLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(agreeContractSubclauseEClass, AgreeContractSubclause.class, "AgreeContractSubclause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContractSubclause_Contract(), this.getContract(), null, "contract", null, 0, 1, AgreeContractSubclause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(agreeContractEClass, AgreeContract.class, "AgreeContract", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAgreeContract_Specs(), this.getSpecStatement(), null, "specs", null, 0, -1, AgreeContract.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(initialStatementEClass, InitialStatement.class, "InitialStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInitialStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, InitialStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(paramStatementEClass, ParamStatement.class, "ParamStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParamStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, ParamStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParamStatement_Type(), this.getType(), null, "type", null, 0, 1, ParamStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(liftStatementEClass, LiftStatement.class, "LiftStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLiftStatement_Subcomp(), theAadl2Package.getNamedElement(), null, "subcomp", null, 0, 1, LiftStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(connectionStatementEClass, ConnectionStatement.class, "ConnectionStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConnectionStatement_Conn(), theAadl2Package.getNamedElement(), null, "conn", null, 0, 1, ConnectionStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConnectionStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, ConnectionStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assumeStatementEClass, AssumeStatement.class, "AssumeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(guaranteeStatementEClass, GuaranteeStatement.class, "GuaranteeStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(assertStatementEClass, AssertStatement.class, "AssertStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(lemmaStatementEClass, LemmaStatement.class, "LemmaStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(alwaysStatementEClass, AlwaysStatement.class, "AlwaysStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAlwaysStatement_Expr(), this.getExpr(), null, "expr", null, 0, 1, AlwaysStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(whenHoldsStatementEClass, WhenHoldsStatement.class, "WhenHoldsStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWhenHoldsStatement_ConditionInterval(), this.getTimeInterval(), null, "conditionInterval", null, 0, 1, WhenHoldsStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWhenHoldsStatement_EventInterval(), this.getTimeInterval(), null, "eventInterval", null, 0, 1, WhenHoldsStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(whenOccursStatmentEClass, WhenOccursStatment.class, "WhenOccursStatment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWhenOccursStatment_Times(), this.getExpr(), null, "times", null, 0, 1, WhenOccursStatment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWhenOccursStatment_Interval(), this.getTimeInterval(), null, "interval", null, 0, 1, WhenOccursStatment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(wheneverOccursStatementEClass, WheneverOccursStatement.class, "WheneverOccursStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWheneverOccursStatement_Effect(), this.getExpr(), null, "effect", null, 0, 1, WheneverOccursStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(wheneverBecomesTrueStatementEClass, WheneverBecomesTrueStatement.class, "WheneverBecomesTrueStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWheneverBecomesTrueStatement_Effect(), this.getExpr(), null, "effect", null, 0, 1, WheneverBecomesTrueStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(wheneverHoldsStatementEClass, WheneverHoldsStatement.class, "WheneverHoldsStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWheneverHoldsStatement_Effect(), this.getExpr(), null, "effect", null, 0, 1, WheneverHoldsStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(wheneverImpliesStatementEClass, WheneverImpliesStatement.class, "WheneverImpliesStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWheneverImpliesStatement_Lhs(), this.getExpr(), null, "lhs", null, 0, 1, WheneverImpliesStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWheneverImpliesStatement_Rhs(), this.getExpr(), null, "rhs", null, 0, 1, WheneverImpliesStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(periodicStatementEClass, PeriodicStatement.class, "PeriodicStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPeriodicStatement_Period(), this.getExpr(), null, "period", null, 0, 1, PeriodicStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sporadicStatementEClass, SporadicStatement.class, "SporadicStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSporadicStatement_Iat(), this.getExpr(), null, "iat", null, 0, 1, SporadicStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(closedTimeIntervalEClass, ClosedTimeInterval.class, "ClosedTimeInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(openLeftTimeIntervalEClass, OpenLeftTimeInterval.class, "OpenLeftTimeInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(openRightTimeIntervalEClass, OpenRightTimeInterval.class, "OpenRightTimeInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(openTimeIntervalEClass, OpenTimeInterval.class, "OpenTimeInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(mnSynchStatementEClass, MNSynchStatement.class, "MNSynchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMNSynchStatement_Comp1(), theAadl2Package.getNamedElement(), null, "comp1", null, 0, -1, MNSynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMNSynchStatement_Comp2(), theAadl2Package.getNamedElement(), null, "comp2", null, 0, -1, MNSynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMNSynchStatement_Max(), theEcorePackage.getEString(), "max", null, 0, -1, MNSynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMNSynchStatement_Min(), theEcorePackage.getEString(), "min", null, 0, -1, MNSynchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(calenStatementEClass, CalenStatement.class, "CalenStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCalenStatement_Els(), theAadl2Package.getNamedElement(), null, "els", null, 0, -1, CalenStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(asynchStatementEClass, AsynchStatement.class, "AsynchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(latchedStatementEClass, LatchedStatement.class, "LatchedStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(nodeEqEClass, NodeEq.class, "NodeEq", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeEq_Lhs(), this.getArg(), null, "lhs", null, 0, -1, NodeEq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeLemmaEClass, NodeLemma.class, "NodeLemma", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNodeLemma_Str(), theEcorePackage.getEString(), "str", null, 0, 1, NodeLemma.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arrayTypeEClass, ArrayType.class, "ArrayType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArrayType_Stem(), this.getType(), null, "stem", null, 0, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getArrayType_Size(), theEcorePackage.getEString(), "size", null, 0, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primTypeEClass, PrimType.class, "PrimType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPrimType_Name(), theEcorePackage.getEString(), "name", null, 0, 1, PrimType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrimType_LowNeg(), theEcorePackage.getEString(), "lowNeg", null, 0, 1, PrimType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrimType_RangeLow(), theEcorePackage.getEString(), "rangeLow", null, 0, 1, PrimType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrimType_HighNeg(), theEcorePackage.getEString(), "highNeg", null, 0, 1, PrimType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrimType_RangeHigh(), theEcorePackage.getEString(), "rangeHigh", null, 0, 1, PrimType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(forallExprEClass, ForallExpr.class, "ForallExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getForallExpr_Binding(), this.getNamedID(), null, "binding", null, 0, 1, ForallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForallExpr_Array(), this.getExpr(), null, "array", null, 0, 1, ForallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForallExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, ForallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(existsExprEClass, ExistsExpr.class, "ExistsExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExistsExpr_Binding(), this.getNamedID(), null, "binding", null, 0, 1, ExistsExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExistsExpr_Array(), this.getExpr(), null, "array", null, 0, 1, ExistsExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExistsExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, ExistsExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(flatmapExprEClass, FlatmapExpr.class, "FlatmapExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFlatmapExpr_Binding(), this.getNamedID(), null, "binding", null, 0, 1, FlatmapExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFlatmapExpr_Array(), this.getExpr(), null, "array", null, 0, 1, FlatmapExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFlatmapExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, FlatmapExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(foldLeftExprEClass, FoldLeftExpr.class, "FoldLeftExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFoldLeftExpr_Binding(), this.getNamedID(), null, "binding", null, 0, 1, FoldLeftExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldLeftExpr_Array(), this.getExpr(), null, "array", null, 0, 1, FoldLeftExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldLeftExpr_Accumulator(), this.getNamedID(), null, "accumulator", null, 0, 1, FoldLeftExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldLeftExpr_Initial(), this.getExpr(), null, "initial", null, 0, 1, FoldLeftExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldLeftExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, FoldLeftExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(foldRightExprEClass, FoldRightExpr.class, "FoldRightExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFoldRightExpr_Binding(), this.getNamedID(), null, "binding", null, 0, 1, FoldRightExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldRightExpr_Array(), this.getExpr(), null, "array", null, 0, 1, FoldRightExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldRightExpr_Accumulator(), this.getNamedID(), null, "accumulator", null, 0, 1, FoldRightExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldRightExpr_Initial(), this.getExpr(), null, "initial", null, 0, 1, FoldRightExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFoldRightExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, FoldRightExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(binaryExprEClass, BinaryExpr.class, "BinaryExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBinaryExpr_Left(), this.getExpr(), null, "left", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBinaryExpr_Op(), theEcorePackage.getEString(), "op", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBinaryExpr_Right(), this.getExpr(), null, "right", null, 0, 1, BinaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unaryExprEClass, UnaryExpr.class, "UnaryExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUnaryExpr_Op(), theEcorePackage.getEString(), "op", null, 0, 1, UnaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, UnaryExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifThenElseExprEClass, IfThenElseExpr.class, "IfThenElseExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfThenElseExpr_A(), this.getExpr(), null, "a", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfThenElseExpr_B(), this.getExpr(), null, "b", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfThenElseExpr_C(), this.getExpr(), null, "c", null, 0, 1, IfThenElseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(thisRefEClass, ThisRef.class, "ThisRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(prevExprEClass, PrevExpr.class, "PrevExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPrevExpr_Delay(), this.getExpr(), null, "delay", null, 0, 1, PrevExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrevExpr_Init(), this.getExpr(), null, "init", null, 0, 1, PrevExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(getPropertyExprEClass, GetPropertyExpr.class, "GetPropertyExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGetPropertyExpr_ComponentRef(), this.getComponentRef(), null, "componentRef", null, 0, 1, GetPropertyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGetPropertyExpr_Prop(), theAadl2Package.getNamedElement(), null, "prop", null, 0, 1, GetPropertyExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arrayUpdateExprEClass, ArrayUpdateExpr.class, "ArrayUpdateExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArrayUpdateExpr_Array(), this.getExpr(), null, "array", null, 0, 1, ArrayUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArrayUpdateExpr_Indices(), this.getExpr(), null, "indices", null, 0, -1, ArrayUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArrayUpdateExpr_ValueExprs(), this.getExpr(), null, "valueExprs", null, 0, -1, ArrayUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(recordUpdateExprEClass, RecordUpdateExpr.class, "RecordUpdateExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRecordUpdateExpr_Record(), this.getExpr(), null, "record", null, 0, 1, RecordUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRecordUpdateExpr_Key(), theAadl2Package.getNamedElement(), null, "key", null, 0, 1, RecordUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRecordUpdateExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, RecordUpdateExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arraySubExprEClass, ArraySubExpr.class, "ArraySubExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArraySubExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, ArraySubExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArraySubExpr_Index(), this.getExpr(), null, "index", null, 0, 1, ArraySubExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tagExprEClass, TagExpr.class, "TagExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTagExpr_Stem(), this.getExpr(), null, "stem", null, 0, 1, TagExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTagExpr_Tag(), theEcorePackage.getEString(), "tag", null, 0, 1, TagExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selectionExprEClass, SelectionExpr.class, "SelectionExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSelectionExpr_Target(), this.getExpr(), null, "target", null, 0, 1, SelectionExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSelectionExpr_Field(), theAadl2Package.getNamedElement(), null, "field", null, 0, 1, SelectionExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedElmExprEClass, NamedElmExpr.class, "NamedElmExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNamedElmExpr_Elm(), theAadl2Package.getNamedElement(), null, "elm", null, 0, 1, NamedElmExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeExprEClass, TimeExpr.class, "TimeExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(indicesExprEClass, IndicesExpr.class, "IndicesExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIndicesExpr_Array(), this.getExpr(), null, "array", null, 0, 1, IndicesExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(callExprEClass, CallExpr.class, "CallExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCallExpr_Ref(), this.getDoubleDotRef(), null, "ref", null, 0, 1, CallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCallExpr_Args(), this.getExpr(), null, "args", null, 0, -1, CallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(recordLitExprEClass, RecordLitExpr.class, "RecordLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRecordLitExpr_RecordType(), this.getDoubleDotRef(), null, "recordType", null, 0, 1, RecordLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRecordLitExpr_Args(), theAadl2Package.getNamedElement(), null, "args", null, 0, -1, RecordLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRecordLitExpr_ArgExpr(), this.getExpr(), null, "argExpr", null, 0, -1, RecordLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumLitExprEClass, EnumLitExpr.class, "EnumLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumLitExpr_EnumType(), this.getDoubleDotRef(), null, "enumType", null, 0, 1, EnumLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEnumLitExpr_Value(), theEcorePackage.getEString(), "value", null, 0, 1, EnumLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(intLitExprEClass, IntLitExpr.class, "IntLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntLitExpr_Val(), theEcorePackage.getEString(), "val", null, 0, 1, IntLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(preExprEClass, PreExpr.class, "PreExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPreExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, PreExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eventExprEClass, EventExpr.class, "EventExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEventExpr_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, EventExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(latchedExprEClass, LatchedExpr.class, "LatchedExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLatchedExpr_Expr(), this.getExpr(), null, "expr", null, 0, 1, LatchedExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeOfExprEClass, TimeOfExpr.class, "TimeOfExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTimeOfExpr_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, TimeOfExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeRiseExprEClass, TimeRiseExpr.class, "TimeRiseExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTimeRiseExpr_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, TimeRiseExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeFallExprEClass, TimeFallExpr.class, "TimeFallExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTimeFallExpr_Id(), theAadl2Package.getNamedElement(), null, "id", null, 0, 1, TimeFallExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realLitExprEClass, RealLitExpr.class, "RealLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRealLitExpr_Val(), theEcorePackage.getEString(), "val", null, 0, 1, RealLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(boolLitExprEClass, BoolLitExpr.class, "BoolLitExpr", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBoolLitExpr_Val(), theAadl2Package.getBooleanLiteral(), null, "val", null, 0, 1, BoolLitExpr.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(floorCastEClass, FloorCast.class, "FloorCast", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFloorCast_Expr(), this.getExpr(), null, "expr", null, 0, 1, FloorCast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realCastEClass, RealCast.class, "RealCast", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRealCast_Expr(), this.getExpr(), null, "expr", null, 0, 1, RealCast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //AgreePackageImpl
