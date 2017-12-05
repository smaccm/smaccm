/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.osate.aadl2.Aadl2Package;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.rockwellcollins.atc.agree.agree.AgreeFactory
 * @model kind="package"
 * @generated
 */
public interface AgreePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "agree";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.rockwellcollins.com/atc/agree/Agree";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "agree";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  AgreePackage eINSTANCE = com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl.init();

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeLibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreeLibraryImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeLibrary()
   * @generated
   */
  int AGREE_LIBRARY = 0;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY__OWNED_ELEMENT = Aadl2Package.ANNEX_LIBRARY__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY__OWNED_COMMENT = Aadl2Package.ANNEX_LIBRARY__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY__NAME = Aadl2Package.ANNEX_LIBRARY__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY__QUALIFIED_NAME = Aadl2Package.ANNEX_LIBRARY__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.ANNEX_LIBRARY__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_LIBRARY_FEATURE_COUNT = Aadl2Package.ANNEX_LIBRARY_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeSubclauseImpl <em>Subclause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreeSubclauseImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeSubclause()
   * @generated
   */
  int AGREE_SUBCLAUSE = 1;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__OWNED_ELEMENT = Aadl2Package.ANNEX_SUBCLAUSE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__OWNED_COMMENT = Aadl2Package.ANNEX_SUBCLAUSE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__NAME = Aadl2Package.ANNEX_SUBCLAUSE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__QUALIFIED_NAME = Aadl2Package.ANNEX_SUBCLAUSE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.ANNEX_SUBCLAUSE__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>In Mode</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE__IN_MODE = Aadl2Package.ANNEX_SUBCLAUSE__IN_MODE;

  /**
   * The number of structural features of the '<em>Subclause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_SUBCLAUSE_FEATURE_COUNT = Aadl2Package.ANNEX_SUBCLAUSE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ContractImpl <em>Contract</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ContractImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getContract()
   * @generated
   */
  int CONTRACT = 2;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRACT__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRACT__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Contract</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRACT_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.SpecStatementImpl <em>Spec Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.SpecStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSpecStatement()
   * @generated
   */
  int SPEC_STATEMENT = 3;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_STATEMENT__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_STATEMENT__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Spec Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_STATEMENT_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl <em>Pattern Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPatternStatement()
   * @generated
   */
  int PATTERN_STATEMENT = 4;

  /**
   * The number of structural features of the '<em>Pattern Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN_STATEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenStatementImpl <em>When Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WhenStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenStatement()
   * @generated
   */
  int WHEN_STATEMENT = 5;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__CONDITION = PATTERN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__EVENT = PATTERN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__EXCL = PATTERN_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>When Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT_FEATURE_COUNT = PATTERN_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl <em>Whenever Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverStatement()
   * @generated
   */
  int WHENEVER_STATEMENT = 6;

  /**
   * The feature id for the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_STATEMENT__CAUSE = PATTERN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_STATEMENT__EXCL = PATTERN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_STATEMENT__INTERVAL = PATTERN_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Whenever Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_STATEMENT_FEATURE_COUNT = PATTERN_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealTimeStatementImpl <em>Real Time Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RealTimeStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealTimeStatement()
   * @generated
   */
  int REAL_TIME_STATEMENT = 7;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_TIME_STATEMENT__EVENT = PATTERN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Jitter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_TIME_STATEMENT__JITTER = PATTERN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Real Time Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_TIME_STATEMENT_FEATURE_COUNT = PATTERN_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeIntervalImpl <em>Time Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeInterval()
   * @generated
   */
  int TIME_INTERVAL = 8;

  /**
   * The feature id for the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_INTERVAL__LOW = 0;

  /**
   * The feature id for the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_INTERVAL__HIGH = 1;

  /**
   * The number of structural features of the '<em>Time Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_INTERVAL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.SynchStatementImpl <em>Synch Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.SynchStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSynchStatement()
   * @generated
   */
  int SYNCH_STATEMENT = 9;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT__VAL = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Val2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT__VAL2 = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Sim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT__SIM = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Synch Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNCH_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.OrderStatementImpl <em>Order Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.OrderStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOrderStatement()
   * @generated
   */
  int ORDER_STATEMENT = 10;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Comps</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_STATEMENT__COMPS = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Order Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORDER_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.CallDefImpl <em>Call Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.CallDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCallDef()
   * @generated
   */
  int CALL_DEF = 11;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The number of structural features of the '<em>Call Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl <em>Property Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPropertyStatement()
   * @generated
   */
  int PROPERTY_STATEMENT = 12;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT__EXPR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Property Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_STATEMENT_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ConstStatementImpl <em>Const Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ConstStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getConstStatement()
   * @generated
   */
  int CONST_STATEMENT = 13;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT__EXPR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Const Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONST_STATEMENT_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl <em>Enum Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEnumStatement()
   * @generated
   */
  int ENUM_STATEMENT = 14;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Enums</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT__ENUMS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Enum Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_STATEMENT_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.EqStatementImpl <em>Eq Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.EqStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEqStatement()
   * @generated
   */
  int EQ_STATEMENT = 15;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQ_STATEMENT__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQ_STATEMENT__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Lhs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQ_STATEMENT__LHS = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQ_STATEMENT__EXPR = Aadl2Package.ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Eq Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQ_STATEMENT_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.InputStatementImpl <em>Input Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.InputStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getInputStatement()
   * @generated
   */
  int INPUT_STATEMENT = 16;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_STATEMENT__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_STATEMENT__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Lhs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_STATEMENT__LHS = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Input Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_STATEMENT_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssignStatementImpl <em>Assign Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AssignStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssignStatement()
   * @generated
   */
  int ASSIGN_STATEMENT = 17;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGN_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGN_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGN_STATEMENT__ID = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGN_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Assign Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGN_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl <em>Fn Def Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnDefExpr()
   * @generated
   */
  int FN_DEF_EXPR = 18;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR__EXPR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Fn Def Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_EXPR_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefExprImpl <em>Library Fn Def Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLibraryFnDefExpr()
   * @generated
   */
  int LIBRARY_FN_DEF_EXPR = 19;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Library Fn Def Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_EXPR_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefExprImpl <em>Linearization Def Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationDefExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationDefExpr()
   * @generated
   */
  int LINEARIZATION_DEF_EXPR = 20;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Intervals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__INTERVALS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Precision</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__PRECISION = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Expr Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR__EXPR_BODY = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Linearization Def Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_EXPR_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationIntervalImpl <em>Linearization Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationInterval()
   * @generated
   */
  int LINEARIZATION_INTERVAL = 21;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_INTERVAL__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_INTERVAL__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_INTERVAL__START = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_INTERVAL__END = Aadl2Package.ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Linearization Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_INTERVAL_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefExprImpl <em>Node Def Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeDefExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeDefExpr()
   * @generated
   */
  int NODE_DEF_EXPR = 22;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__RETS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Node Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR__NODE_BODY = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Node Def Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_EXPR_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeBodyExprImpl <em>Node Body Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeBodyExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeBodyExpr()
   * @generated
   */
  int NODE_BODY_EXPR = 23;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_BODY_EXPR__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_BODY_EXPR__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Locs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_BODY_EXPR__LOCS = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Stmts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_BODY_EXPR__STMTS = Aadl2Package.ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Node Body Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_BODY_EXPR_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeStmtImpl <em>Node Stmt</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeStmtImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeStmt()
   * @generated
   */
  int NODE_STMT = 24;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_STMT__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_STMT__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_STMT__EXPR = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Node Stmt</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_STMT_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArgImpl <em>Arg</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ArgImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArg()
   * @generated
   */
  int ARG = 25;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Arg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARG_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TypeImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getType()
   * @generated
   */
  int TYPE = 26;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordDefExprImpl <em>Record Def Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordDefExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordDefExpr()
   * @generated
   */
  int RECORD_DEF_EXPR = 27;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Record Def Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_EXPR_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExprImpl <em>Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExpr()
   * @generated
   */
  int EXPR = 28;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR__OWNED_ELEMENT = Aadl2Package.ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR__OWNED_COMMENT = Aadl2Package.ELEMENT__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_FEATURE_COUNT = Aadl2Package.ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ComplexExprImpl <em>Complex Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ComplexExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getComplexExpr()
   * @generated
   */
  int COMPLEX_EXPR = 29;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Complex Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPLEX_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl <em>Nested Dot ID</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNestedDotID()
   * @generated
   */
  int NESTED_DOT_ID = 30;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID__OWNED_ELEMENT = COMPLEX_EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID__OWNED_COMMENT = COMPLEX_EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Base</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID__BASE = COMPLEX_EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID__TAG = COMPLEX_EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Sub</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID__SUB = COMPLEX_EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Nested Dot ID</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_DOT_ID_FEATURE_COUNT = COMPLEX_EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl <em>Named ID</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedID()
   * @generated
   */
  int NAMED_ID = 31;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The number of structural features of the '<em>Named ID</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ID_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractLibraryImpl <em>Contract Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractLibraryImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContractLibrary()
   * @generated
   */
  int AGREE_CONTRACT_LIBRARY = 32;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__OWNED_ELEMENT = AGREE_LIBRARY__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__OWNED_COMMENT = AGREE_LIBRARY__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__NAME = AGREE_LIBRARY__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__QUALIFIED_NAME = AGREE_LIBRARY__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__OWNED_PROPERTY_ASSOCIATION = AGREE_LIBRARY__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Contract</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY__CONTRACT = AGREE_LIBRARY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Contract Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_LIBRARY_FEATURE_COUNT = AGREE_LIBRARY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractSubclauseImpl <em>Contract Subclause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractSubclauseImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContractSubclause()
   * @generated
   */
  int AGREE_CONTRACT_SUBCLAUSE = 33;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__OWNED_ELEMENT = AGREE_SUBCLAUSE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__OWNED_COMMENT = AGREE_SUBCLAUSE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__NAME = AGREE_SUBCLAUSE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__QUALIFIED_NAME = AGREE_SUBCLAUSE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__OWNED_PROPERTY_ASSOCIATION = AGREE_SUBCLAUSE__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>In Mode</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__IN_MODE = AGREE_SUBCLAUSE__IN_MODE;

  /**
   * The feature id for the '<em><b>Contract</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE__CONTRACT = AGREE_SUBCLAUSE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Contract Subclause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_SUBCLAUSE_FEATURE_COUNT = AGREE_SUBCLAUSE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractImpl <em>Contract</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContract()
   * @generated
   */
  int AGREE_CONTRACT = 34;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT__OWNED_ELEMENT = CONTRACT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT__OWNED_COMMENT = CONTRACT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Specs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT__SPECS = CONTRACT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Contract</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AGREE_CONTRACT_FEATURE_COUNT = CONTRACT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl <em>Assume Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssumeStatement()
   * @generated
   */
  int ASSUME_STATEMENT = 35;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__STR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__PATTERN = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Assume Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl <em>Guarantee Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getGuaranteeStatement()
   * @generated
   */
  int GUARANTEE_STATEMENT = 36;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__STR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__PATTERN = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Guarantee Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssertStatement()
   * @generated
   */
  int ASSERT_STATEMENT = 37;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__STR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__PATTERN = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Assert Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl <em>Initial Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getInitialStatement()
   * @generated
   */
  int INITIAL_STATEMENT = 38;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Initial Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ParamStatementImpl <em>Param Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ParamStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getParamStatement()
   * @generated
   */
  int PARAM_STATEMENT = 39;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_STATEMENT__TYPE = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Param Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl <em>Lemma Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLemmaStatement()
   * @generated
   */
  int LEMMA_STATEMENT = 40;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__STR = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__PATTERN = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Lemma Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl <em>Lift Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLiftStatement()
   * @generated
   */
  int LIFT_STATEMENT = 41;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFT_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFT_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Subcomp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFT_STATEMENT__SUBCOMP = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Lift Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFT_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ConnectionStatementImpl <em>Connection Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ConnectionStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getConnectionStatement()
   * @generated
   */
  int CONNECTION_STATEMENT = 42;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_STATEMENT__OWNED_ELEMENT = SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_STATEMENT__OWNED_COMMENT = SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Conn</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_STATEMENT__CONN = SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_STATEMENT__EXPR = SPEC_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Connection Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECTION_STATEMENT_FEATURE_COUNT = SPEC_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl <em>Always Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAlwaysStatement()
   * @generated
   */
  int ALWAYS_STATEMENT = 43;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALWAYS_STATEMENT__EXPR = PATTERN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Always Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALWAYS_STATEMENT_FEATURE_COUNT = PATTERN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenHoldsStatementImpl <em>When Holds Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WhenHoldsStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenHoldsStatement()
   * @generated
   */
  int WHEN_HOLDS_STATEMENT = 44;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT__CONDITION = WHEN_STATEMENT__CONDITION;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT__EVENT = WHEN_STATEMENT__EVENT;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT__EXCL = WHEN_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Condition Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT__CONDITION_INTERVAL = WHEN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Event Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT__EVENT_INTERVAL = WHEN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>When Holds Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_HOLDS_STATEMENT_FEATURE_COUNT = WHEN_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenOccursStatmentImpl <em>When Occurs Statment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WhenOccursStatmentImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenOccursStatment()
   * @generated
   */
  int WHEN_OCCURS_STATMENT = 45;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT__CONDITION = WHEN_STATEMENT__CONDITION;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT__EVENT = WHEN_STATEMENT__EVENT;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT__EXCL = WHEN_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Times</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT__TIMES = WHEN_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT__INTERVAL = WHEN_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>When Occurs Statment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_OCCURS_STATMENT_FEATURE_COUNT = WHEN_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverOccursStatementImpl <em>Whenever Occurs Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WheneverOccursStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverOccursStatement()
   * @generated
   */
  int WHENEVER_OCCURS_STATEMENT = 46;

  /**
   * The feature id for the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_OCCURS_STATEMENT__CAUSE = WHENEVER_STATEMENT__CAUSE;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_OCCURS_STATEMENT__EXCL = WHENEVER_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_OCCURS_STATEMENT__INTERVAL = WHENEVER_STATEMENT__INTERVAL;

  /**
   * The feature id for the '<em><b>Effect</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_OCCURS_STATEMENT__EFFECT = WHENEVER_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Whenever Occurs Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_OCCURS_STATEMENT_FEATURE_COUNT = WHENEVER_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverBecomesTrueStatementImpl <em>Whenever Becomes True Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WheneverBecomesTrueStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverBecomesTrueStatement()
   * @generated
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT = 47;

  /**
   * The feature id for the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT__CAUSE = WHENEVER_STATEMENT__CAUSE;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT__EXCL = WHENEVER_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT__INTERVAL = WHENEVER_STATEMENT__INTERVAL;

  /**
   * The feature id for the '<em><b>Effect</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT__EFFECT = WHENEVER_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Whenever Becomes True Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_BECOMES_TRUE_STATEMENT_FEATURE_COUNT = WHENEVER_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverHoldsStatementImpl <em>Whenever Holds Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WheneverHoldsStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverHoldsStatement()
   * @generated
   */
  int WHENEVER_HOLDS_STATEMENT = 48;

  /**
   * The feature id for the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_HOLDS_STATEMENT__CAUSE = WHENEVER_STATEMENT__CAUSE;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_HOLDS_STATEMENT__EXCL = WHENEVER_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_HOLDS_STATEMENT__INTERVAL = WHENEVER_STATEMENT__INTERVAL;

  /**
   * The feature id for the '<em><b>Effect</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_HOLDS_STATEMENT__EFFECT = WHENEVER_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Whenever Holds Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_HOLDS_STATEMENT_FEATURE_COUNT = WHENEVER_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverImpliesStatementImpl <em>Whenever Implies Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.WheneverImpliesStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverImpliesStatement()
   * @generated
   */
  int WHENEVER_IMPLIES_STATEMENT = 49;

  /**
   * The feature id for the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT__CAUSE = WHENEVER_STATEMENT__CAUSE;

  /**
   * The feature id for the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT__EXCL = WHENEVER_STATEMENT__EXCL;

  /**
   * The feature id for the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT__INTERVAL = WHENEVER_STATEMENT__INTERVAL;

  /**
   * The feature id for the '<em><b>Lhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT__LHS = WHENEVER_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT__RHS = WHENEVER_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Whenever Implies Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHENEVER_IMPLIES_STATEMENT_FEATURE_COUNT = WHENEVER_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl <em>Periodic Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPeriodicStatement()
   * @generated
   */
  int PERIODIC_STATEMENT = 50;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODIC_STATEMENT__EVENT = REAL_TIME_STATEMENT__EVENT;

  /**
   * The feature id for the '<em><b>Jitter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODIC_STATEMENT__JITTER = REAL_TIME_STATEMENT__JITTER;

  /**
   * The feature id for the '<em><b>Period</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODIC_STATEMENT__PERIOD = REAL_TIME_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Periodic Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODIC_STATEMENT_FEATURE_COUNT = REAL_TIME_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.SporadicStatementImpl <em>Sporadic Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.SporadicStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSporadicStatement()
   * @generated
   */
  int SPORADIC_STATEMENT = 51;

  /**
   * The feature id for the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPORADIC_STATEMENT__EVENT = REAL_TIME_STATEMENT__EVENT;

  /**
   * The feature id for the '<em><b>Jitter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPORADIC_STATEMENT__JITTER = REAL_TIME_STATEMENT__JITTER;

  /**
   * The feature id for the '<em><b>Iat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPORADIC_STATEMENT__IAT = REAL_TIME_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Sporadic Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPORADIC_STATEMENT_FEATURE_COUNT = REAL_TIME_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ClosedTimeIntervalImpl <em>Closed Time Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ClosedTimeIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getClosedTimeInterval()
   * @generated
   */
  int CLOSED_TIME_INTERVAL = 52;

  /**
   * The feature id for the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSED_TIME_INTERVAL__LOW = TIME_INTERVAL__LOW;

  /**
   * The feature id for the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSED_TIME_INTERVAL__HIGH = TIME_INTERVAL__HIGH;

  /**
   * The number of structural features of the '<em>Closed Time Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLOSED_TIME_INTERVAL_FEATURE_COUNT = TIME_INTERVAL_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenLeftTimeIntervalImpl <em>Open Left Time Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.OpenLeftTimeIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenLeftTimeInterval()
   * @generated
   */
  int OPEN_LEFT_TIME_INTERVAL = 53;

  /**
   * The feature id for the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_LEFT_TIME_INTERVAL__LOW = TIME_INTERVAL__LOW;

  /**
   * The feature id for the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_LEFT_TIME_INTERVAL__HIGH = TIME_INTERVAL__HIGH;

  /**
   * The number of structural features of the '<em>Open Left Time Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_LEFT_TIME_INTERVAL_FEATURE_COUNT = TIME_INTERVAL_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenRightTimeIntervalImpl <em>Open Right Time Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.OpenRightTimeIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenRightTimeInterval()
   * @generated
   */
  int OPEN_RIGHT_TIME_INTERVAL = 54;

  /**
   * The feature id for the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_RIGHT_TIME_INTERVAL__LOW = TIME_INTERVAL__LOW;

  /**
   * The feature id for the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_RIGHT_TIME_INTERVAL__HIGH = TIME_INTERVAL__HIGH;

  /**
   * The number of structural features of the '<em>Open Right Time Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_RIGHT_TIME_INTERVAL_FEATURE_COUNT = TIME_INTERVAL_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenTimeIntervalImpl <em>Open Time Interval</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.OpenTimeIntervalImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenTimeInterval()
   * @generated
   */
  int OPEN_TIME_INTERVAL = 55;

  /**
   * The feature id for the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_TIME_INTERVAL__LOW = TIME_INTERVAL__LOW;

  /**
   * The feature id for the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_TIME_INTERVAL__HIGH = TIME_INTERVAL__HIGH;

  /**
   * The number of structural features of the '<em>Open Time Interval</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPEN_TIME_INTERVAL_FEATURE_COUNT = TIME_INTERVAL_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl <em>MN Synch Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getMNSynchStatement()
   * @generated
   */
  int MN_SYNCH_STATEMENT = 56;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__OWNED_ELEMENT = SYNCH_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__OWNED_COMMENT = SYNCH_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__VAL = SYNCH_STATEMENT__VAL;

  /**
   * The feature id for the '<em><b>Val2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__VAL2 = SYNCH_STATEMENT__VAL2;

  /**
   * The feature id for the '<em><b>Sim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__SIM = SYNCH_STATEMENT__SIM;

  /**
   * The feature id for the '<em><b>Comp1</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__COMP1 = SYNCH_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Comp2</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__COMP2 = SYNCH_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__MAX = SYNCH_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT__MIN = SYNCH_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>MN Synch Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MN_SYNCH_STATEMENT_FEATURE_COUNT = SYNCH_STATEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.CalenStatementImpl <em>Calen Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.CalenStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCalenStatement()
   * @generated
   */
  int CALEN_STATEMENT = 57;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__OWNED_ELEMENT = SYNCH_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__OWNED_COMMENT = SYNCH_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__VAL = SYNCH_STATEMENT__VAL;

  /**
   * The feature id for the '<em><b>Val2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__VAL2 = SYNCH_STATEMENT__VAL2;

  /**
   * The feature id for the '<em><b>Sim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__SIM = SYNCH_STATEMENT__SIM;

  /**
   * The feature id for the '<em><b>Els</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT__ELS = SYNCH_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Calen Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALEN_STATEMENT_FEATURE_COUNT = SYNCH_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AsynchStatementImpl <em>Asynch Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AsynchStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAsynchStatement()
   * @generated
   */
  int ASYNCH_STATEMENT = 58;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT__OWNED_ELEMENT = SYNCH_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT__OWNED_COMMENT = SYNCH_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT__VAL = SYNCH_STATEMENT__VAL;

  /**
   * The feature id for the '<em><b>Val2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT__VAL2 = SYNCH_STATEMENT__VAL2;

  /**
   * The feature id for the '<em><b>Sim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT__SIM = SYNCH_STATEMENT__SIM;

  /**
   * The number of structural features of the '<em>Asynch Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASYNCH_STATEMENT_FEATURE_COUNT = SYNCH_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LatchedStatementImpl <em>Latched Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LatchedStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLatchedStatement()
   * @generated
   */
  int LATCHED_STATEMENT = 59;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT__OWNED_ELEMENT = SYNCH_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT__OWNED_COMMENT = SYNCH_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT__VAL = SYNCH_STATEMENT__VAL;

  /**
   * The feature id for the '<em><b>Val2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT__VAL2 = SYNCH_STATEMENT__VAL2;

  /**
   * The feature id for the '<em><b>Sim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT__SIM = SYNCH_STATEMENT__SIM;

  /**
   * The number of structural features of the '<em>Latched Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_STATEMENT_FEATURE_COUNT = SYNCH_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl <em>Node Eq</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeEq()
   * @generated
   */
  int NODE_EQ = 60;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_EQ__OWNED_ELEMENT = NODE_STMT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_EQ__OWNED_COMMENT = NODE_STMT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_EQ__EXPR = NODE_STMT__EXPR;

  /**
   * The feature id for the '<em><b>Lhs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_EQ__LHS = NODE_STMT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Node Eq</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_EQ_FEATURE_COUNT = NODE_STMT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeLemmaImpl <em>Node Lemma</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeLemmaImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeLemma()
   * @generated
   */
  int NODE_LEMMA = 61;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LEMMA__OWNED_ELEMENT = NODE_STMT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LEMMA__OWNED_COMMENT = NODE_STMT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LEMMA__EXPR = NODE_STMT__EXPR;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LEMMA__STR = NODE_STMT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Node Lemma</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LEMMA_FEATURE_COUNT = NODE_STMT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl <em>Prim Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrimType()
   * @generated
   */
  int PRIM_TYPE = 62;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__OWNED_ELEMENT = TYPE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__OWNED_COMMENT = TYPE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__STRING = TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Low Neg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__LOW_NEG = TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Range Low</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__RANGE_LOW = TYPE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>High Neg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__HIGH_NEG = TYPE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Range High</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__RANGE_HIGH = TYPE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Prim Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordTypeImpl <em>Record Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordTypeImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordType()
   * @generated
   */
  int RECORD_TYPE = 63;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_TYPE__OWNED_ELEMENT = TYPE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_TYPE__OWNED_COMMENT = TYPE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Record</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_TYPE__RECORD = TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Record Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl <em>Binary Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getBinaryExpr()
   * @generated
   */
  int BINARY_EXPR = 64;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR__LEFT = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR__OP = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR__RIGHT = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Binary Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BINARY_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.UnaryExprImpl <em>Unary Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.UnaryExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getUnaryExpr()
   * @generated
   */
  int UNARY_EXPR = 65;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPR__OP = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPR__EXPR = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Unary Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.IfThenElseExprImpl <em>If Then Else Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.IfThenElseExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIfThenElseExpr()
   * @generated
   */
  int IF_THEN_ELSE_EXPR = 66;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR__A = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>B</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR__B = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>C</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR__C = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Then Else Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_THEN_ELSE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl <em>Prev Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrevExpr()
   * @generated
   */
  int PREV_EXPR = 67;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREV_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREV_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Delay</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREV_EXPR__DELAY = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Init</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREV_EXPR__INIT = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Prev Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREV_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl <em>Get Property Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getGetPropertyExpr()
   * @generated
   */
  int GET_PROPERTY_EXPR = 68;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Component</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR__COMPONENT = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Prop</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR__PROP = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Get Property Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl <em>Record Update Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordUpdateExpr()
   * @generated
   */
  int RECORD_UPDATE_EXPR = 69;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Record</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__RECORD = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Args</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__ARGS = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Arg Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__ARG_EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Record Update Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl <em>Time Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeExpr()
   * @generated
   */
  int TIME_EXPR = 70;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The number of structural features of the '<em>Time Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl <em>Int Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIntLitExpr()
   * @generated
   */
  int INT_LIT_EXPR = 71;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIT_EXPR__VAL = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Int Lit Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INT_LIT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PreExprImpl <em>Pre Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PreExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPreExpr()
   * @generated
   */
  int PRE_EXPR = 72;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_EXPR__EXPR = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pre Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.EventExprImpl <em>Event Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.EventExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEventExpr()
   * @generated
   */
  int EVENT_EXPR = 73;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_EXPR__ID = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Event Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EVENT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LatchedExprImpl <em>Latched Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LatchedExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLatchedExpr()
   * @generated
   */
  int LATCHED_EXPR = 74;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_EXPR__EXPR = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Latched Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LATCHED_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeOfExprImpl <em>Time Of Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeOfExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeOfExpr()
   * @generated
   */
  int TIME_OF_EXPR = 75;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_OF_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_OF_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_OF_EXPR__ID = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Time Of Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_OF_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeRiseExprImpl <em>Time Rise Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeRiseExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeRiseExpr()
   * @generated
   */
  int TIME_RISE_EXPR = 76;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_RISE_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_RISE_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_RISE_EXPR__ID = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Time Rise Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_RISE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeFallExprImpl <em>Time Fall Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeFallExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeFallExpr()
   * @generated
   */
  int TIME_FALL_EXPR = 77;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_FALL_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_FALL_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_FALL_EXPR__ID = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Time Fall Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TIME_FALL_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealLitExprImpl <em>Real Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RealLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealLitExpr()
   * @generated
   */
  int REAL_LIT_EXPR = 78;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LIT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LIT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LIT_EXPR__VAL = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Real Lit Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LIT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.BoolLitExprImpl <em>Bool Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.BoolLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getBoolLitExpr()
   * @generated
   */
  int BOOL_LIT_EXPR = 79;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_LIT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_LIT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_LIT_EXPR__VAL = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Bool Lit Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOL_LIT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ThisExprImpl <em>This Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ThisExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getThisExpr()
   * @generated
   */
  int THIS_EXPR = 80;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Sub This</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_EXPR__SUB_THIS = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>This Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl <em>Floor Cast</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFloorCast()
   * @generated
   */
  int FLOOR_CAST = 81;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLOOR_CAST__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLOOR_CAST__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLOOR_CAST__EXPR = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Floor Cast</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLOOR_CAST_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealCastImpl <em>Real Cast</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RealCastImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealCast()
   * @generated
   */
  int REAL_CAST = 82;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_CAST__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_CAST__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_CAST__EXPR = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Real Cast</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_CAST_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AADLEnumeratorImpl <em>AADL Enumerator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AADLEnumeratorImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAADLEnumerator()
   * @generated
   */
  int AADL_ENUMERATOR = 83;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AADL_ENUMERATOR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AADL_ENUMERATOR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Enum Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AADL_ENUMERATOR__ENUM_TYPE = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AADL_ENUMERATOR__VALUE = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>AADL Enumerator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AADL_ENUMERATOR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl <em>Fn Call Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnCallExpr()
   * @generated
   */
  int FN_CALL_EXPR = 84;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_CALL_EXPR__OWNED_ELEMENT = COMPLEX_EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_CALL_EXPR__OWNED_COMMENT = COMPLEX_EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Fn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_CALL_EXPR__FN = COMPLEX_EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_CALL_EXPR__ARGS = COMPLEX_EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Fn Call Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_CALL_EXPR_FEATURE_COUNT = COMPLEX_EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl <em>Record Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordExpr()
   * @generated
   */
  int RECORD_EXPR = 85;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR__OWNED_ELEMENT = COMPLEX_EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR__OWNED_COMMENT = COMPLEX_EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Record</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR__RECORD = COMPLEX_EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Args</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR__ARGS = COMPLEX_EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Arg Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR__ARG_EXPR = COMPLEX_EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Record Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_EXPR_FEATURE_COUNT = COMPLEX_EXPR_FEATURE_COUNT + 3;


  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AgreeLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeLibrary
   * @generated
   */
  EClass getAgreeLibrary();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AgreeSubclause <em>Subclause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Subclause</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeSubclause
   * @generated
   */
  EClass getAgreeSubclause();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Contract <em>Contract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contract</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Contract
   * @generated
   */
  EClass getContract();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.SpecStatement <em>Spec Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Spec Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SpecStatement
   * @generated
   */
  EClass getSpecStatement();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PatternStatement <em>Pattern Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pattern Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PatternStatement
   * @generated
   */
  EClass getPatternStatement();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WhenStatement <em>When Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>When Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenStatement
   * @generated
   */
  EClass getWhenStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenStatement#getCondition()
   * @see #getWhenStatement()
   * @generated
   */
  EReference getWhenStatement_Condition();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getEvent <em>Event</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Event</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenStatement#getEvent()
   * @see #getWhenStatement()
   * @generated
   */
  EReference getWhenStatement_Event();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getExcl <em>Excl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Excl</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenStatement#getExcl()
   * @see #getWhenStatement()
   * @generated
   */
  EAttribute getWhenStatement_Excl();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement <em>Whenever Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Whenever Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverStatement
   * @generated
   */
  EClass getWheneverStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getCause <em>Cause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cause</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverStatement#getCause()
   * @see #getWheneverStatement()
   * @generated
   */
  EReference getWheneverStatement_Cause();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getExcl <em>Excl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Excl</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverStatement#getExcl()
   * @see #getWheneverStatement()
   * @generated
   */
  EAttribute getWheneverStatement_Excl();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getInterval <em>Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverStatement#getInterval()
   * @see #getWheneverStatement()
   * @generated
   */
  EReference getWheneverStatement_Interval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement <em>Real Time Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Time Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealTimeStatement
   * @generated
   */
  EClass getRealTimeStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getEvent <em>Event</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Event</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealTimeStatement#getEvent()
   * @see #getRealTimeStatement()
   * @generated
   */
  EReference getRealTimeStatement_Event();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getJitter <em>Jitter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Jitter</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealTimeStatement#getJitter()
   * @see #getRealTimeStatement()
   * @generated
   */
  EReference getRealTimeStatement_Jitter();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TimeInterval <em>Time Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeInterval
   * @generated
   */
  EClass getTimeInterval();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getLow <em>Low</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Low</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeInterval#getLow()
   * @see #getTimeInterval()
   * @generated
   */
  EReference getTimeInterval_Low();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getHigh <em>High</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>High</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeInterval#getHigh()
   * @see #getTimeInterval()
   * @generated
   */
  EReference getTimeInterval_High();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.SynchStatement <em>Synch Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Synch Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SynchStatement
   * @generated
   */
  EClass getSynchStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.SynchStatement#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SynchStatement#getVal()
   * @see #getSynchStatement()
   * @generated
   */
  EAttribute getSynchStatement_Val();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.SynchStatement#getVal2 <em>Val2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val2</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SynchStatement#getVal2()
   * @see #getSynchStatement()
   * @generated
   */
  EAttribute getSynchStatement_Val2();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.SynchStatement#getSim <em>Sim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sim</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SynchStatement#getSim()
   * @see #getSynchStatement()
   * @generated
   */
  EAttribute getSynchStatement_Sim();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.OrderStatement <em>Order Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Order Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.OrderStatement
   * @generated
   */
  EClass getOrderStatement();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.OrderStatement#getComps <em>Comps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Comps</em>'.
   * @see com.rockwellcollins.atc.agree.agree.OrderStatement#getComps()
   * @see #getOrderStatement()
   * @generated
   */
  EReference getOrderStatement_Comps();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.CallDef <em>Call Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Call Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CallDef
   * @generated
   */
  EClass getCallDef();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PropertyStatement <em>Property Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PropertyStatement
   * @generated
   */
  EClass getPropertyStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.PropertyStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PropertyStatement#getExpr()
   * @see #getPropertyStatement()
   * @generated
   */
  EReference getPropertyStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ConstStatement <em>Const Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Const Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConstStatement
   * @generated
   */
  EClass getConstStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ConstStatement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConstStatement#getType()
   * @see #getConstStatement()
   * @generated
   */
  EReference getConstStatement_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ConstStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConstStatement#getExpr()
   * @see #getConstStatement()
   * @generated
   */
  EReference getConstStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.EnumStatement <em>Enum Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EnumStatement
   * @generated
   */
  EClass getEnumStatement();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.EnumStatement#getEnums <em>Enums</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Enums</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EnumStatement#getEnums()
   * @see #getEnumStatement()
   * @generated
   */
  EReference getEnumStatement_Enums();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.EqStatement <em>Eq Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Eq Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EqStatement
   * @generated
   */
  EClass getEqStatement();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.EqStatement#getLhs <em>Lhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lhs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EqStatement#getLhs()
   * @see #getEqStatement()
   * @generated
   */
  EReference getEqStatement_Lhs();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.EqStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EqStatement#getExpr()
   * @see #getEqStatement()
   * @generated
   */
  EReference getEqStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.InputStatement <em>Input Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Input Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.InputStatement
   * @generated
   */
  EClass getInputStatement();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.InputStatement#getLhs <em>Lhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lhs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.InputStatement#getLhs()
   * @see #getInputStatement()
   * @generated
   */
  EReference getInputStatement_Lhs();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AssignStatement <em>Assign Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assign Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssignStatement
   * @generated
   */
  EClass getAssignStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssignStatement#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssignStatement#getId()
   * @see #getAssignStatement()
   * @generated
   */
  EReference getAssignStatement_Id();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssignStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssignStatement#getExpr()
   * @see #getAssignStatement()
   * @generated
   */
  EReference getAssignStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr <em>Fn Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fn Def Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDefExpr
   * @generated
   */
  EClass getFnDefExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDefExpr#getArgs()
   * @see #getFnDefExpr()
   * @generated
   */
  EReference getFnDefExpr_Args();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDefExpr#getType()
   * @see #getFnDefExpr()
   * @generated
   */
  EReference getFnDefExpr_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FnDefExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDefExpr#getExpr()
   * @see #getFnDefExpr()
   * @generated
   */
  EReference getFnDefExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr <em>Library Fn Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library Fn Def Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr
   * @generated
   */
  EClass getLibraryFnDefExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr#getArgs()
   * @see #getLibraryFnDefExpr()
   * @generated
   */
  EReference getLibraryFnDefExpr_Args();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr#getType()
   * @see #getLibraryFnDefExpr()
   * @generated
   */
  EReference getLibraryFnDefExpr_Type();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr <em>Linearization Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Linearization Def Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr
   * @generated
   */
  EClass getLinearizationDefExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getArgs()
   * @see #getLinearizationDefExpr()
   * @generated
   */
  EReference getLinearizationDefExpr_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getIntervals <em>Intervals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Intervals</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getIntervals()
   * @see #getLinearizationDefExpr()
   * @generated
   */
  EReference getLinearizationDefExpr_Intervals();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getPrecision <em>Precision</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Precision</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getPrecision()
   * @see #getLinearizationDefExpr()
   * @generated
   */
  EReference getLinearizationDefExpr_Precision();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getExprBody <em>Expr Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr Body</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getExprBody()
   * @see #getLinearizationDefExpr()
   * @generated
   */
  EReference getLinearizationDefExpr_ExprBody();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval <em>Linearization Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Linearization Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationInterval
   * @generated
   */
  EClass getLinearizationInterval();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Start</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationInterval#getStart()
   * @see #getLinearizationInterval()
   * @generated
   */
  EReference getLinearizationInterval_Start();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationInterval#getEnd()
   * @see #getLinearizationInterval()
   * @generated
   */
  EReference getLinearizationInterval_End();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr <em>Node Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Def Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDefExpr
   * @generated
   */
  EClass getNodeDefExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDefExpr#getArgs()
   * @see #getNodeDefExpr()
   * @generated
   */
  EReference getNodeDefExpr_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getRets <em>Rets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Rets</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDefExpr#getRets()
   * @see #getNodeDefExpr()
   * @generated
   */
  EReference getNodeDefExpr_Rets();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getNodeBody <em>Node Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Node Body</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDefExpr#getNodeBody()
   * @see #getNodeDefExpr()
   * @generated
   */
  EReference getNodeDefExpr_NodeBody();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr <em>Node Body Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Body Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeBodyExpr
   * @generated
   */
  EClass getNodeBodyExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getLocs <em>Locs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Locs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getLocs()
   * @see #getNodeBodyExpr()
   * @generated
   */
  EReference getNodeBodyExpr_Locs();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getStmts <em>Stmts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Stmts</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getStmts()
   * @see #getNodeBodyExpr()
   * @generated
   */
  EReference getNodeBodyExpr_Stmts();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeStmt <em>Node Stmt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Stmt</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeStmt
   * @generated
   */
  EClass getNodeStmt();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NodeStmt#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeStmt#getExpr()
   * @see #getNodeStmt()
   * @generated
   */
  EReference getNodeStmt_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Arg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Arg</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Arg
   * @generated
   */
  EClass getArg();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.Arg#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Arg#getType()
   * @see #getArg()
   * @generated
   */
  EReference getArg_Type();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Type
   * @generated
   */
  EClass getType();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordDefExpr <em>Record Def Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Def Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordDefExpr
   * @generated
   */
  EClass getRecordDefExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.RecordDefExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordDefExpr#getArgs()
   * @see #getRecordDefExpr()
   * @generated
   */
  EReference getRecordDefExpr_Args();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Expr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Expr
   * @generated
   */
  EClass getExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ComplexExpr <em>Complex Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ComplexExpr
   * @generated
   */
  EClass getComplexExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NestedDotID <em>Nested Dot ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nested Dot ID</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NestedDotID
   * @generated
   */
  EClass getNestedDotID();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getBase <em>Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Base</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NestedDotID#getBase()
   * @see #getNestedDotID()
   * @generated
   */
  EReference getNestedDotID_Base();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getTag <em>Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tag</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NestedDotID#getTag()
   * @see #getNestedDotID()
   * @generated
   */
  EAttribute getNestedDotID_Tag();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sub</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NestedDotID#getSub()
   * @see #getNestedDotID()
   * @generated
   */
  EReference getNestedDotID_Sub();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NamedID <em>Named ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named ID</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedID
   * @generated
   */
  EClass getNamedID();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AgreeContractLibrary <em>Contract Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contract Library</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContractLibrary
   * @generated
   */
  EClass getAgreeContractLibrary();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AgreeContractLibrary#getContract <em>Contract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Contract</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContractLibrary#getContract()
   * @see #getAgreeContractLibrary()
   * @generated
   */
  EReference getAgreeContractLibrary_Contract();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AgreeContractSubclause <em>Contract Subclause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contract Subclause</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContractSubclause
   * @generated
   */
  EClass getAgreeContractSubclause();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AgreeContractSubclause#getContract <em>Contract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Contract</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContractSubclause#getContract()
   * @see #getAgreeContractSubclause()
   * @generated
   */
  EReference getAgreeContractSubclause_Contract();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AgreeContract <em>Contract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contract</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContract
   * @generated
   */
  EClass getAgreeContract();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.AgreeContract#getSpecs <em>Specs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Specs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AgreeContract#getSpecs()
   * @see #getAgreeContract()
   * @generated
   */
  EReference getAgreeContract_Specs();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement <em>Assume Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assume Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssumeStatement
   * @generated
   */
  EClass getAssumeStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssumeStatement#getStr()
   * @see #getAssumeStatement()
   * @generated
   */
  EAttribute getAssumeStatement_Str();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssumeStatement#getExpr()
   * @see #getAssumeStatement()
   * @generated
   */
  EReference getAssumeStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssumeStatement#getPattern()
   * @see #getAssumeStatement()
   * @generated
   */
  EReference getAssumeStatement_Pattern();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.GuaranteeStatement <em>Guarantee Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Guarantee Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GuaranteeStatement
   * @generated
   */
  EClass getGuaranteeStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getStr()
   * @see #getGuaranteeStatement()
   * @generated
   */
  EAttribute getGuaranteeStatement_Str();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getExpr()
   * @see #getGuaranteeStatement()
   * @generated
   */
  EReference getGuaranteeStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GuaranteeStatement#getPattern()
   * @see #getGuaranteeStatement()
   * @generated
   */
  EReference getGuaranteeStatement_Pattern();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AssertStatement <em>Assert Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assert Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssertStatement
   * @generated
   */
  EClass getAssertStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.AssertStatement#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssertStatement#getStr()
   * @see #getAssertStatement()
   * @generated
   */
  EAttribute getAssertStatement_Str();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssertStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssertStatement#getExpr()
   * @see #getAssertStatement()
   * @generated
   */
  EReference getAssertStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AssertStatement#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssertStatement#getPattern()
   * @see #getAssertStatement()
   * @generated
   */
  EReference getAssertStatement_Pattern();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.InitialStatement <em>Initial Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Initial Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.InitialStatement
   * @generated
   */
  EClass getInitialStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.InitialStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.InitialStatement#getExpr()
   * @see #getInitialStatement()
   * @generated
   */
  EReference getInitialStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ParamStatement <em>Param Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Param Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ParamStatement
   * @generated
   */
  EClass getParamStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ParamStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ParamStatement#getExpr()
   * @see #getParamStatement()
   * @generated
   */
  EReference getParamStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ParamStatement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ParamStatement#getType()
   * @see #getParamStatement()
   * @generated
   */
  EReference getParamStatement_Type();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LemmaStatement <em>Lemma Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lemma Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LemmaStatement
   * @generated
   */
  EClass getLemmaStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.LemmaStatement#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LemmaStatement#getStr()
   * @see #getLemmaStatement()
   * @generated
   */
  EAttribute getLemmaStatement_Str();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LemmaStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LemmaStatement#getExpr()
   * @see #getLemmaStatement()
   * @generated
   */
  EReference getLemmaStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LemmaStatement#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LemmaStatement#getPattern()
   * @see #getLemmaStatement()
   * @generated
   */
  EReference getLemmaStatement_Pattern();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LiftStatement <em>Lift Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lift Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LiftStatement
   * @generated
   */
  EClass getLiftStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp <em>Subcomp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Subcomp</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp()
   * @see #getLiftStatement()
   * @generated
   */
  EReference getLiftStatement_Subcomp();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ConnectionStatement <em>Connection Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Connection Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConnectionStatement
   * @generated
   */
  EClass getConnectionStatement();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.ConnectionStatement#getConn <em>Conn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Conn</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConnectionStatement#getConn()
   * @see #getConnectionStatement()
   * @generated
   */
  EReference getConnectionStatement_Conn();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ConnectionStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ConnectionStatement#getExpr()
   * @see #getConnectionStatement()
   * @generated
   */
  EReference getConnectionStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AlwaysStatement <em>Always Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Always Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AlwaysStatement
   * @generated
   */
  EClass getAlwaysStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AlwaysStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AlwaysStatement#getExpr()
   * @see #getAlwaysStatement()
   * @generated
   */
  EReference getAlwaysStatement_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement <em>When Holds Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>When Holds Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenHoldsStatement
   * @generated
   */
  EClass getWhenHoldsStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getConditionInterval <em>Condition Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getConditionInterval()
   * @see #getWhenHoldsStatement()
   * @generated
   */
  EReference getWhenHoldsStatement_ConditionInterval();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getEventInterval <em>Event Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Event Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getEventInterval()
   * @see #getWhenHoldsStatement()
   * @generated
   */
  EReference getWhenHoldsStatement_EventInterval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment <em>When Occurs Statment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>When Occurs Statment</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenOccursStatment
   * @generated
   */
  EClass getWhenOccursStatment();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getTimes <em>Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Times</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getTimes()
   * @see #getWhenOccursStatment()
   * @generated
   */
  EReference getWhenOccursStatment_Times();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getInterval <em>Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getInterval()
   * @see #getWhenOccursStatment()
   * @generated
   */
  EReference getWhenOccursStatment_Interval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WheneverOccursStatement <em>Whenever Occurs Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Whenever Occurs Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverOccursStatement
   * @generated
   */
  EClass getWheneverOccursStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverOccursStatement#getEffect <em>Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Effect</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverOccursStatement#getEffect()
   * @see #getWheneverOccursStatement()
   * @generated
   */
  EReference getWheneverOccursStatement_Effect();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement <em>Whenever Becomes True Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Whenever Becomes True Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement
   * @generated
   */
  EClass getWheneverBecomesTrueStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement#getEffect <em>Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Effect</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement#getEffect()
   * @see #getWheneverBecomesTrueStatement()
   * @generated
   */
  EReference getWheneverBecomesTrueStatement_Effect();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement <em>Whenever Holds Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Whenever Holds Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement
   * @generated
   */
  EClass getWheneverHoldsStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement#getEffect <em>Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Effect</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverHoldsStatement#getEffect()
   * @see #getWheneverHoldsStatement()
   * @generated
   */
  EReference getWheneverHoldsStatement_Effect();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement <em>Whenever Implies Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Whenever Implies Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement
   * @generated
   */
  EClass getWheneverImpliesStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getLhs <em>Lhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lhs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getLhs()
   * @see #getWheneverImpliesStatement()
   * @generated
   */
  EReference getWheneverImpliesStatement_Lhs();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getRhs <em>Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rhs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getRhs()
   * @see #getWheneverImpliesStatement()
   * @generated
   */
  EReference getWheneverImpliesStatement_Rhs();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PeriodicStatement <em>Periodic Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Periodic Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PeriodicStatement
   * @generated
   */
  EClass getPeriodicStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.PeriodicStatement#getPeriod <em>Period</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Period</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PeriodicStatement#getPeriod()
   * @see #getPeriodicStatement()
   * @generated
   */
  EReference getPeriodicStatement_Period();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.SporadicStatement <em>Sporadic Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sporadic Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SporadicStatement
   * @generated
   */
  EClass getSporadicStatement();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.SporadicStatement#getIat <em>Iat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Iat</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SporadicStatement#getIat()
   * @see #getSporadicStatement()
   * @generated
   */
  EReference getSporadicStatement_Iat();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ClosedTimeInterval <em>Closed Time Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Closed Time Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ClosedTimeInterval
   * @generated
   */
  EClass getClosedTimeInterval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval <em>Open Left Time Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Open Left Time Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.OpenLeftTimeInterval
   * @generated
   */
  EClass getOpenLeftTimeInterval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval <em>Open Right Time Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Open Right Time Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.OpenRightTimeInterval
   * @generated
   */
  EClass getOpenRightTimeInterval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.OpenTimeInterval <em>Open Time Interval</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Open Time Interval</em>'.
   * @see com.rockwellcollins.atc.agree.agree.OpenTimeInterval
   * @generated
   */
  EClass getOpenTimeInterval();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement <em>MN Synch Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>MN Synch Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement
   * @generated
   */
  EClass getMNSynchStatement();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp1 <em>Comp1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Comp1</em>'.
   * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp1()
   * @see #getMNSynchStatement()
   * @generated
   */
  EReference getMNSynchStatement_Comp1();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp2 <em>Comp2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Comp2</em>'.
   * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp2()
   * @see #getMNSynchStatement()
   * @generated
   */
  EReference getMNSynchStatement_Comp2();

  /**
   * Returns the meta object for the attribute list '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Max</em>'.
   * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMax()
   * @see #getMNSynchStatement()
   * @generated
   */
  EAttribute getMNSynchStatement_Max();

  /**
   * Returns the meta object for the attribute list '{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Min</em>'.
   * @see com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMin()
   * @see #getMNSynchStatement()
   * @generated
   */
  EAttribute getMNSynchStatement_Min();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.CalenStatement <em>Calen Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Calen Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CalenStatement
   * @generated
   */
  EClass getCalenStatement();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.CalenStatement#getEls <em>Els</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Els</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CalenStatement#getEls()
   * @see #getCalenStatement()
   * @generated
   */
  EReference getCalenStatement_Els();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AsynchStatement <em>Asynch Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Asynch Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AsynchStatement
   * @generated
   */
  EClass getAsynchStatement();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LatchedStatement <em>Latched Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Latched Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LatchedStatement
   * @generated
   */
  EClass getLatchedStatement();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeEq <em>Node Eq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Eq</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeEq
   * @generated
   */
  EClass getNodeEq();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.NodeEq#getLhs <em>Lhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Lhs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeEq#getLhs()
   * @see #getNodeEq()
   * @generated
   */
  EReference getNodeEq_Lhs();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeLemma <em>Node Lemma</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Lemma</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeLemma
   * @generated
   */
  EClass getNodeLemma();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.NodeLemma#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeLemma#getStr()
   * @see #getNodeLemma()
   * @generated
   */
  EAttribute getNodeLemma_Str();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PrimType <em>Prim Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Prim Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType
   * @generated
   */
  EClass getPrimType();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getString()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_String();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getLowNeg <em>Low Neg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Low Neg</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getLowNeg()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_LowNeg();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getRangeLow <em>Range Low</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Range Low</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getRangeLow()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_RangeLow();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getHighNeg <em>High Neg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>High Neg</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getHighNeg()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_HighNeg();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getRangeHigh <em>Range High</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Range High</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getRangeHigh()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_RangeHigh();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordType <em>Record Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordType
   * @generated
   */
  EClass getRecordType();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RecordType#getRecord <em>Record</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordType#getRecord()
   * @see #getRecordType()
   * @generated
   */
  EReference getRecordType_Record();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.BinaryExpr <em>Binary Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Binary Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BinaryExpr
   * @generated
   */
  EClass getBinaryExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.BinaryExpr#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BinaryExpr#getLeft()
   * @see #getBinaryExpr()
   * @generated
   */
  EReference getBinaryExpr_Left();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.BinaryExpr#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BinaryExpr#getOp()
   * @see #getBinaryExpr()
   * @generated
   */
  EAttribute getBinaryExpr_Op();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.BinaryExpr#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BinaryExpr#getRight()
   * @see #getBinaryExpr()
   * @generated
   */
  EReference getBinaryExpr_Right();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.UnaryExpr <em>Unary Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.UnaryExpr
   * @generated
   */
  EClass getUnaryExpr();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.UnaryExpr#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see com.rockwellcollins.atc.agree.agree.UnaryExpr#getOp()
   * @see #getUnaryExpr()
   * @generated
   */
  EAttribute getUnaryExpr_Op();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.UnaryExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.UnaryExpr#getExpr()
   * @see #getUnaryExpr()
   * @generated
   */
  EReference getUnaryExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.IfThenElseExpr <em>If Then Else Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Then Else Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IfThenElseExpr
   * @generated
   */
  EClass getIfThenElseExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>A</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getA()
   * @see #getIfThenElseExpr()
   * @generated
   */
  EReference getIfThenElseExpr_A();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>B</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getB()
   * @see #getIfThenElseExpr()
   * @generated
   */
  EReference getIfThenElseExpr_B();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getC <em>C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>C</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IfThenElseExpr#getC()
   * @see #getIfThenElseExpr()
   * @generated
   */
  EReference getIfThenElseExpr_C();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PrevExpr <em>Prev Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Prev Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrevExpr
   * @generated
   */
  EClass getPrevExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getDelay <em>Delay</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Delay</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrevExpr#getDelay()
   * @see #getPrevExpr()
   * @generated
   */
  EReference getPrevExpr_Delay();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getInit <em>Init</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Init</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrevExpr#getInit()
   * @see #getPrevExpr()
   * @generated
   */
  EReference getPrevExpr_Init();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr <em>Get Property Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Get Property Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GetPropertyExpr
   * @generated
   */
  EClass getGetPropertyExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponent <em>Component</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponent()
   * @see #getGetPropertyExpr()
   * @generated
   */
  EReference getGetPropertyExpr_Component();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getProp <em>Prop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Prop</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getProp()
   * @see #getGetPropertyExpr()
   * @generated
   */
  EReference getGetPropertyExpr_Prop();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr <em>Record Update Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Update Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr
   * @generated
   */
  EClass getRecordUpdateExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getRecord <em>Record</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getRecord()
   * @see #getRecordUpdateExpr()
   * @generated
   */
  EReference getRecordUpdateExpr_Record();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgs()
   * @see #getRecordUpdateExpr()
   * @generated
   */
  EReference getRecordUpdateExpr_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgExpr <em>Arg Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arg Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgExpr()
   * @see #getRecordUpdateExpr()
   * @generated
   */
  EReference getRecordUpdateExpr_ArgExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TimeExpr <em>Time Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeExpr
   * @generated
   */
  EClass getTimeExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.IntLitExpr <em>Int Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Int Lit Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IntLitExpr
   * @generated
   */
  EClass getIntLitExpr();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.IntLitExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IntLitExpr#getVal()
   * @see #getIntLitExpr()
   * @generated
   */
  EAttribute getIntLitExpr_Val();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.PreExpr <em>Pre Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pre Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PreExpr
   * @generated
   */
  EClass getPreExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.PreExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PreExpr#getExpr()
   * @see #getPreExpr()
   * @generated
   */
  EReference getPreExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.EventExpr <em>Event Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Event Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EventExpr
   * @generated
   */
  EClass getEventExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.EventExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EventExpr#getId()
   * @see #getEventExpr()
   * @generated
   */
  EReference getEventExpr_Id();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LatchedExpr <em>Latched Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Latched Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LatchedExpr
   * @generated
   */
  EClass getLatchedExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LatchedExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LatchedExpr#getExpr()
   * @see #getLatchedExpr()
   * @generated
   */
  EReference getLatchedExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr <em>Time Of Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Of Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeOfExpr
   * @generated
   */
  EClass getTimeOfExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeOfExpr#getId()
   * @see #getTimeOfExpr()
   * @generated
   */
  EReference getTimeOfExpr_Id();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TimeRiseExpr <em>Time Rise Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Rise Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeRiseExpr
   * @generated
   */
  EClass getTimeRiseExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TimeRiseExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeRiseExpr#getId()
   * @see #getTimeRiseExpr()
   * @generated
   */
  EReference getTimeRiseExpr_Id();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TimeFallExpr <em>Time Fall Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Time Fall Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeFallExpr
   * @generated
   */
  EClass getTimeFallExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TimeFallExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TimeFallExpr#getId()
   * @see #getTimeFallExpr()
   * @generated
   */
  EReference getTimeFallExpr_Id();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RealLitExpr <em>Real Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Lit Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealLitExpr
   * @generated
   */
  EClass getRealLitExpr();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.RealLitExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealLitExpr#getVal()
   * @see #getRealLitExpr()
   * @generated
   */
  EAttribute getRealLitExpr_Val();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.BoolLitExpr <em>Bool Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bool Lit Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BoolLitExpr
   * @generated
   */
  EClass getBoolLitExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.BoolLitExpr#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see com.rockwellcollins.atc.agree.agree.BoolLitExpr#getVal()
   * @see #getBoolLitExpr()
   * @generated
   */
  EReference getBoolLitExpr_Val();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ThisExpr <em>This Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>This Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ThisExpr
   * @generated
   */
  EClass getThisExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ThisExpr#getSubThis <em>Sub This</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sub This</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ThisExpr#getSubThis()
   * @see #getThisExpr()
   * @generated
   */
  EReference getThisExpr_SubThis();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FloorCast <em>Floor Cast</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Floor Cast</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FloorCast
   * @generated
   */
  EClass getFloorCast();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FloorCast#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FloorCast#getExpr()
   * @see #getFloorCast()
   * @generated
   */
  EReference getFloorCast_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RealCast <em>Real Cast</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Cast</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealCast
   * @generated
   */
  EClass getRealCast();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RealCast#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RealCast#getExpr()
   * @see #getRealCast()
   * @generated
   */
  EReference getRealCast_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AADLEnumerator <em>AADL Enumerator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>AADL Enumerator</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AADLEnumerator
   * @generated
   */
  EClass getAADLEnumerator();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.AADLEnumerator#getEnumType <em>Enum Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Enum Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AADLEnumerator#getEnumType()
   * @see #getAADLEnumerator()
   * @generated
   */
  EReference getAADLEnumerator_EnumType();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.AADLEnumerator#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AADLEnumerator#getValue()
   * @see #getAADLEnumerator()
   * @generated
   */
  EAttribute getAADLEnumerator_Value();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr <em>Fn Call Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fn Call Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnCallExpr
   * @generated
   */
  EClass getFnCallExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr#getFn <em>Fn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fn</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnCallExpr#getFn()
   * @see #getFnCallExpr()
   * @generated
   */
  EReference getFnCallExpr_Fn();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnCallExpr#getArgs()
   * @see #getFnCallExpr()
   * @generated
   */
  EReference getFnCallExpr_Args();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordExpr <em>Record Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordExpr
   * @generated
   */
  EClass getRecordExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getRecord <em>Record</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordExpr#getRecord()
   * @see #getRecordExpr()
   * @generated
   */
  EReference getRecordExpr_Record();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordExpr#getArgs()
   * @see #getRecordExpr()
   * @generated
   */
  EReference getRecordExpr_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getArgExpr <em>Arg Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arg Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordExpr#getArgExpr()
   * @see #getRecordExpr()
   * @generated
   */
  EReference getRecordExpr_ArgExpr();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  AgreeFactory getAgreeFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeLibraryImpl <em>Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreeLibraryImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeLibrary()
     * @generated
     */
    EClass AGREE_LIBRARY = eINSTANCE.getAgreeLibrary();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeSubclauseImpl <em>Subclause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreeSubclauseImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeSubclause()
     * @generated
     */
    EClass AGREE_SUBCLAUSE = eINSTANCE.getAgreeSubclause();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ContractImpl <em>Contract</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ContractImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getContract()
     * @generated
     */
    EClass CONTRACT = eINSTANCE.getContract();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.SpecStatementImpl <em>Spec Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.SpecStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSpecStatement()
     * @generated
     */
    EClass SPEC_STATEMENT = eINSTANCE.getSpecStatement();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl <em>Pattern Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPatternStatement()
     * @generated
     */
    EClass PATTERN_STATEMENT = eINSTANCE.getPatternStatement();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenStatementImpl <em>When Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WhenStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenStatement()
     * @generated
     */
    EClass WHEN_STATEMENT = eINSTANCE.getWhenStatement();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__CONDITION = eINSTANCE.getWhenStatement_Condition();

    /**
     * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__EVENT = eINSTANCE.getWhenStatement_Event();

    /**
     * The meta object literal for the '<em><b>Excl</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WHEN_STATEMENT__EXCL = eINSTANCE.getWhenStatement_Excl();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl <em>Whenever Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverStatement()
     * @generated
     */
    EClass WHENEVER_STATEMENT = eINSTANCE.getWheneverStatement();

    /**
     * The meta object literal for the '<em><b>Cause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_STATEMENT__CAUSE = eINSTANCE.getWheneverStatement_Cause();

    /**
     * The meta object literal for the '<em><b>Excl</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WHENEVER_STATEMENT__EXCL = eINSTANCE.getWheneverStatement_Excl();

    /**
     * The meta object literal for the '<em><b>Interval</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_STATEMENT__INTERVAL = eINSTANCE.getWheneverStatement_Interval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealTimeStatementImpl <em>Real Time Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RealTimeStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealTimeStatement()
     * @generated
     */
    EClass REAL_TIME_STATEMENT = eINSTANCE.getRealTimeStatement();

    /**
     * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REAL_TIME_STATEMENT__EVENT = eINSTANCE.getRealTimeStatement_Event();

    /**
     * The meta object literal for the '<em><b>Jitter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REAL_TIME_STATEMENT__JITTER = eINSTANCE.getRealTimeStatement_Jitter();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeIntervalImpl <em>Time Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TimeIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeInterval()
     * @generated
     */
    EClass TIME_INTERVAL = eINSTANCE.getTimeInterval();

    /**
     * The meta object literal for the '<em><b>Low</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TIME_INTERVAL__LOW = eINSTANCE.getTimeInterval_Low();

    /**
     * The meta object literal for the '<em><b>High</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TIME_INTERVAL__HIGH = eINSTANCE.getTimeInterval_High();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.SynchStatementImpl <em>Synch Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.SynchStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSynchStatement()
     * @generated
     */
    EClass SYNCH_STATEMENT = eINSTANCE.getSynchStatement();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYNCH_STATEMENT__VAL = eINSTANCE.getSynchStatement_Val();

    /**
     * The meta object literal for the '<em><b>Val2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYNCH_STATEMENT__VAL2 = eINSTANCE.getSynchStatement_Val2();

    /**
     * The meta object literal for the '<em><b>Sim</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYNCH_STATEMENT__SIM = eINSTANCE.getSynchStatement_Sim();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.OrderStatementImpl <em>Order Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.OrderStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOrderStatement()
     * @generated
     */
    EClass ORDER_STATEMENT = eINSTANCE.getOrderStatement();

    /**
     * The meta object literal for the '<em><b>Comps</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORDER_STATEMENT__COMPS = eINSTANCE.getOrderStatement_Comps();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.CallDefImpl <em>Call Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.CallDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCallDef()
     * @generated
     */
    EClass CALL_DEF = eINSTANCE.getCallDef();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl <em>Property Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPropertyStatement()
     * @generated
     */
    EClass PROPERTY_STATEMENT = eINSTANCE.getPropertyStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_STATEMENT__EXPR = eINSTANCE.getPropertyStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ConstStatementImpl <em>Const Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ConstStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getConstStatement()
     * @generated
     */
    EClass CONST_STATEMENT = eINSTANCE.getConstStatement();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONST_STATEMENT__TYPE = eINSTANCE.getConstStatement_Type();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONST_STATEMENT__EXPR = eINSTANCE.getConstStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl <em>Enum Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEnumStatement()
     * @generated
     */
    EClass ENUM_STATEMENT = eINSTANCE.getEnumStatement();

    /**
     * The meta object literal for the '<em><b>Enums</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_STATEMENT__ENUMS = eINSTANCE.getEnumStatement_Enums();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.EqStatementImpl <em>Eq Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.EqStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEqStatement()
     * @generated
     */
    EClass EQ_STATEMENT = eINSTANCE.getEqStatement();

    /**
     * The meta object literal for the '<em><b>Lhs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQ_STATEMENT__LHS = eINSTANCE.getEqStatement_Lhs();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQ_STATEMENT__EXPR = eINSTANCE.getEqStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.InputStatementImpl <em>Input Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.InputStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getInputStatement()
     * @generated
     */
    EClass INPUT_STATEMENT = eINSTANCE.getInputStatement();

    /**
     * The meta object literal for the '<em><b>Lhs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INPUT_STATEMENT__LHS = eINSTANCE.getInputStatement_Lhs();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssignStatementImpl <em>Assign Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AssignStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssignStatement()
     * @generated
     */
    EClass ASSIGN_STATEMENT = eINSTANCE.getAssignStatement();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGN_STATEMENT__ID = eINSTANCE.getAssignStatement_Id();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGN_STATEMENT__EXPR = eINSTANCE.getAssignStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl <em>Fn Def Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FnDefExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnDefExpr()
     * @generated
     */
    EClass FN_DEF_EXPR = eINSTANCE.getFnDefExpr();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF_EXPR__ARGS = eINSTANCE.getFnDefExpr_Args();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF_EXPR__TYPE = eINSTANCE.getFnDefExpr_Type();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF_EXPR__EXPR = eINSTANCE.getFnDefExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefExprImpl <em>Library Fn Def Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLibraryFnDefExpr()
     * @generated
     */
    EClass LIBRARY_FN_DEF_EXPR = eINSTANCE.getLibraryFnDefExpr();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_FN_DEF_EXPR__ARGS = eINSTANCE.getLibraryFnDefExpr_Args();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_FN_DEF_EXPR__TYPE = eINSTANCE.getLibraryFnDefExpr_Type();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefExprImpl <em>Linearization Def Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationDefExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationDefExpr()
     * @generated
     */
    EClass LINEARIZATION_DEF_EXPR = eINSTANCE.getLinearizationDefExpr();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF_EXPR__ARGS = eINSTANCE.getLinearizationDefExpr_Args();

    /**
     * The meta object literal for the '<em><b>Intervals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF_EXPR__INTERVALS = eINSTANCE.getLinearizationDefExpr_Intervals();

    /**
     * The meta object literal for the '<em><b>Precision</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF_EXPR__PRECISION = eINSTANCE.getLinearizationDefExpr_Precision();

    /**
     * The meta object literal for the '<em><b>Expr Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF_EXPR__EXPR_BODY = eINSTANCE.getLinearizationDefExpr_ExprBody();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationIntervalImpl <em>Linearization Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationInterval()
     * @generated
     */
    EClass LINEARIZATION_INTERVAL = eINSTANCE.getLinearizationInterval();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_INTERVAL__START = eINSTANCE.getLinearizationInterval_Start();

    /**
     * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_INTERVAL__END = eINSTANCE.getLinearizationInterval_End();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefExprImpl <em>Node Def Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeDefExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeDefExpr()
     * @generated
     */
    EClass NODE_DEF_EXPR = eINSTANCE.getNodeDefExpr();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF_EXPR__ARGS = eINSTANCE.getNodeDefExpr_Args();

    /**
     * The meta object literal for the '<em><b>Rets</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF_EXPR__RETS = eINSTANCE.getNodeDefExpr_Rets();

    /**
     * The meta object literal for the '<em><b>Node Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF_EXPR__NODE_BODY = eINSTANCE.getNodeDefExpr_NodeBody();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeBodyExprImpl <em>Node Body Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeBodyExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeBodyExpr()
     * @generated
     */
    EClass NODE_BODY_EXPR = eINSTANCE.getNodeBodyExpr();

    /**
     * The meta object literal for the '<em><b>Locs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_BODY_EXPR__LOCS = eINSTANCE.getNodeBodyExpr_Locs();

    /**
     * The meta object literal for the '<em><b>Stmts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_BODY_EXPR__STMTS = eINSTANCE.getNodeBodyExpr_Stmts();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeStmtImpl <em>Node Stmt</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeStmtImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeStmt()
     * @generated
     */
    EClass NODE_STMT = eINSTANCE.getNodeStmt();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_STMT__EXPR = eINSTANCE.getNodeStmt_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArgImpl <em>Arg</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ArgImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArg()
     * @generated
     */
    EClass ARG = eINSTANCE.getArg();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARG__TYPE = eINSTANCE.getArg_Type();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TypeImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getType()
     * @generated
     */
    EClass TYPE = eINSTANCE.getType();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordDefExprImpl <em>Record Def Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordDefExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordDefExpr()
     * @generated
     */
    EClass RECORD_DEF_EXPR = eINSTANCE.getRecordDefExpr();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_DEF_EXPR__ARGS = eINSTANCE.getRecordDefExpr_Args();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExprImpl <em>Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExpr()
     * @generated
     */
    EClass EXPR = eINSTANCE.getExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ComplexExprImpl <em>Complex Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ComplexExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getComplexExpr()
     * @generated
     */
    EClass COMPLEX_EXPR = eINSTANCE.getComplexExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl <em>Nested Dot ID</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNestedDotID()
     * @generated
     */
    EClass NESTED_DOT_ID = eINSTANCE.getNestedDotID();

    /**
     * The meta object literal for the '<em><b>Base</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NESTED_DOT_ID__BASE = eINSTANCE.getNestedDotID_Base();

    /**
     * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NESTED_DOT_ID__TAG = eINSTANCE.getNestedDotID_Tag();

    /**
     * The meta object literal for the '<em><b>Sub</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NESTED_DOT_ID__SUB = eINSTANCE.getNestedDotID_Sub();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl <em>Named ID</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedID()
     * @generated
     */
    EClass NAMED_ID = eINSTANCE.getNamedID();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractLibraryImpl <em>Contract Library</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractLibraryImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContractLibrary()
     * @generated
     */
    EClass AGREE_CONTRACT_LIBRARY = eINSTANCE.getAgreeContractLibrary();

    /**
     * The meta object literal for the '<em><b>Contract</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGREE_CONTRACT_LIBRARY__CONTRACT = eINSTANCE.getAgreeContractLibrary_Contract();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractSubclauseImpl <em>Contract Subclause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractSubclauseImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContractSubclause()
     * @generated
     */
    EClass AGREE_CONTRACT_SUBCLAUSE = eINSTANCE.getAgreeContractSubclause();

    /**
     * The meta object literal for the '<em><b>Contract</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGREE_CONTRACT_SUBCLAUSE__CONTRACT = eINSTANCE.getAgreeContractSubclause_Contract();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AgreeContractImpl <em>Contract</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreeContractImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAgreeContract()
     * @generated
     */
    EClass AGREE_CONTRACT = eINSTANCE.getAgreeContract();

    /**
     * The meta object literal for the '<em><b>Specs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AGREE_CONTRACT__SPECS = eINSTANCE.getAgreeContract_Specs();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl <em>Assume Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssumeStatement()
     * @generated
     */
    EClass ASSUME_STATEMENT = eINSTANCE.getAssumeStatement();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSUME_STATEMENT__STR = eINSTANCE.getAssumeStatement_Str();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSUME_STATEMENT__EXPR = eINSTANCE.getAssumeStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSUME_STATEMENT__PATTERN = eINSTANCE.getAssumeStatement_Pattern();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl <em>Guarantee Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getGuaranteeStatement()
     * @generated
     */
    EClass GUARANTEE_STATEMENT = eINSTANCE.getGuaranteeStatement();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GUARANTEE_STATEMENT__STR = eINSTANCE.getGuaranteeStatement_Str();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GUARANTEE_STATEMENT__EXPR = eINSTANCE.getGuaranteeStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GUARANTEE_STATEMENT__PATTERN = eINSTANCE.getGuaranteeStatement_Pattern();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssertStatement()
     * @generated
     */
    EClass ASSERT_STATEMENT = eINSTANCE.getAssertStatement();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSERT_STATEMENT__STR = eINSTANCE.getAssertStatement_Str();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSERT_STATEMENT__EXPR = eINSTANCE.getAssertStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSERT_STATEMENT__PATTERN = eINSTANCE.getAssertStatement_Pattern();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl <em>Initial Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getInitialStatement()
     * @generated
     */
    EClass INITIAL_STATEMENT = eINSTANCE.getInitialStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INITIAL_STATEMENT__EXPR = eINSTANCE.getInitialStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ParamStatementImpl <em>Param Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ParamStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getParamStatement()
     * @generated
     */
    EClass PARAM_STATEMENT = eINSTANCE.getParamStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAM_STATEMENT__EXPR = eINSTANCE.getParamStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAM_STATEMENT__TYPE = eINSTANCE.getParamStatement_Type();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl <em>Lemma Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLemmaStatement()
     * @generated
     */
    EClass LEMMA_STATEMENT = eINSTANCE.getLemmaStatement();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEMMA_STATEMENT__STR = eINSTANCE.getLemmaStatement_Str();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEMMA_STATEMENT__EXPR = eINSTANCE.getLemmaStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEMMA_STATEMENT__PATTERN = eINSTANCE.getLemmaStatement_Pattern();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl <em>Lift Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLiftStatement()
     * @generated
     */
    EClass LIFT_STATEMENT = eINSTANCE.getLiftStatement();

    /**
     * The meta object literal for the '<em><b>Subcomp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIFT_STATEMENT__SUBCOMP = eINSTANCE.getLiftStatement_Subcomp();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ConnectionStatementImpl <em>Connection Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ConnectionStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getConnectionStatement()
     * @generated
     */
    EClass CONNECTION_STATEMENT = eINSTANCE.getConnectionStatement();

    /**
     * The meta object literal for the '<em><b>Conn</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECTION_STATEMENT__CONN = eINSTANCE.getConnectionStatement_Conn();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECTION_STATEMENT__EXPR = eINSTANCE.getConnectionStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl <em>Always Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAlwaysStatement()
     * @generated
     */
    EClass ALWAYS_STATEMENT = eINSTANCE.getAlwaysStatement();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALWAYS_STATEMENT__EXPR = eINSTANCE.getAlwaysStatement_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenHoldsStatementImpl <em>When Holds Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WhenHoldsStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenHoldsStatement()
     * @generated
     */
    EClass WHEN_HOLDS_STATEMENT = eINSTANCE.getWhenHoldsStatement();

    /**
     * The meta object literal for the '<em><b>Condition Interval</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_HOLDS_STATEMENT__CONDITION_INTERVAL = eINSTANCE.getWhenHoldsStatement_ConditionInterval();

    /**
     * The meta object literal for the '<em><b>Event Interval</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_HOLDS_STATEMENT__EVENT_INTERVAL = eINSTANCE.getWhenHoldsStatement_EventInterval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WhenOccursStatmentImpl <em>When Occurs Statment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WhenOccursStatmentImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWhenOccursStatment()
     * @generated
     */
    EClass WHEN_OCCURS_STATMENT = eINSTANCE.getWhenOccursStatment();

    /**
     * The meta object literal for the '<em><b>Times</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_OCCURS_STATMENT__TIMES = eINSTANCE.getWhenOccursStatment_Times();

    /**
     * The meta object literal for the '<em><b>Interval</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_OCCURS_STATMENT__INTERVAL = eINSTANCE.getWhenOccursStatment_Interval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverOccursStatementImpl <em>Whenever Occurs Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WheneverOccursStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverOccursStatement()
     * @generated
     */
    EClass WHENEVER_OCCURS_STATEMENT = eINSTANCE.getWheneverOccursStatement();

    /**
     * The meta object literal for the '<em><b>Effect</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_OCCURS_STATEMENT__EFFECT = eINSTANCE.getWheneverOccursStatement_Effect();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverBecomesTrueStatementImpl <em>Whenever Becomes True Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WheneverBecomesTrueStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverBecomesTrueStatement()
     * @generated
     */
    EClass WHENEVER_BECOMES_TRUE_STATEMENT = eINSTANCE.getWheneverBecomesTrueStatement();

    /**
     * The meta object literal for the '<em><b>Effect</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_BECOMES_TRUE_STATEMENT__EFFECT = eINSTANCE.getWheneverBecomesTrueStatement_Effect();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverHoldsStatementImpl <em>Whenever Holds Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WheneverHoldsStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverHoldsStatement()
     * @generated
     */
    EClass WHENEVER_HOLDS_STATEMENT = eINSTANCE.getWheneverHoldsStatement();

    /**
     * The meta object literal for the '<em><b>Effect</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_HOLDS_STATEMENT__EFFECT = eINSTANCE.getWheneverHoldsStatement_Effect();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.WheneverImpliesStatementImpl <em>Whenever Implies Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.WheneverImpliesStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getWheneverImpliesStatement()
     * @generated
     */
    EClass WHENEVER_IMPLIES_STATEMENT = eINSTANCE.getWheneverImpliesStatement();

    /**
     * The meta object literal for the '<em><b>Lhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_IMPLIES_STATEMENT__LHS = eINSTANCE.getWheneverImpliesStatement_Lhs();

    /**
     * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHENEVER_IMPLIES_STATEMENT__RHS = eINSTANCE.getWheneverImpliesStatement_Rhs();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl <em>Periodic Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPeriodicStatement()
     * @generated
     */
    EClass PERIODIC_STATEMENT = eINSTANCE.getPeriodicStatement();

    /**
     * The meta object literal for the '<em><b>Period</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PERIODIC_STATEMENT__PERIOD = eINSTANCE.getPeriodicStatement_Period();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.SporadicStatementImpl <em>Sporadic Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.SporadicStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSporadicStatement()
     * @generated
     */
    EClass SPORADIC_STATEMENT = eINSTANCE.getSporadicStatement();

    /**
     * The meta object literal for the '<em><b>Iat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SPORADIC_STATEMENT__IAT = eINSTANCE.getSporadicStatement_Iat();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ClosedTimeIntervalImpl <em>Closed Time Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ClosedTimeIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getClosedTimeInterval()
     * @generated
     */
    EClass CLOSED_TIME_INTERVAL = eINSTANCE.getClosedTimeInterval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenLeftTimeIntervalImpl <em>Open Left Time Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.OpenLeftTimeIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenLeftTimeInterval()
     * @generated
     */
    EClass OPEN_LEFT_TIME_INTERVAL = eINSTANCE.getOpenLeftTimeInterval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenRightTimeIntervalImpl <em>Open Right Time Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.OpenRightTimeIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenRightTimeInterval()
     * @generated
     */
    EClass OPEN_RIGHT_TIME_INTERVAL = eINSTANCE.getOpenRightTimeInterval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.OpenTimeIntervalImpl <em>Open Time Interval</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.OpenTimeIntervalImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getOpenTimeInterval()
     * @generated
     */
    EClass OPEN_TIME_INTERVAL = eINSTANCE.getOpenTimeInterval();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl <em>MN Synch Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getMNSynchStatement()
     * @generated
     */
    EClass MN_SYNCH_STATEMENT = eINSTANCE.getMNSynchStatement();

    /**
     * The meta object literal for the '<em><b>Comp1</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MN_SYNCH_STATEMENT__COMP1 = eINSTANCE.getMNSynchStatement_Comp1();

    /**
     * The meta object literal for the '<em><b>Comp2</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MN_SYNCH_STATEMENT__COMP2 = eINSTANCE.getMNSynchStatement_Comp2();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MN_SYNCH_STATEMENT__MAX = eINSTANCE.getMNSynchStatement_Max();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MN_SYNCH_STATEMENT__MIN = eINSTANCE.getMNSynchStatement_Min();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.CalenStatementImpl <em>Calen Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.CalenStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCalenStatement()
     * @generated
     */
    EClass CALEN_STATEMENT = eINSTANCE.getCalenStatement();

    /**
     * The meta object literal for the '<em><b>Els</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CALEN_STATEMENT__ELS = eINSTANCE.getCalenStatement_Els();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AsynchStatementImpl <em>Asynch Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AsynchStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAsynchStatement()
     * @generated
     */
    EClass ASYNCH_STATEMENT = eINSTANCE.getAsynchStatement();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LatchedStatementImpl <em>Latched Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LatchedStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLatchedStatement()
     * @generated
     */
    EClass LATCHED_STATEMENT = eINSTANCE.getLatchedStatement();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl <em>Node Eq</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeEq()
     * @generated
     */
    EClass NODE_EQ = eINSTANCE.getNodeEq();

    /**
     * The meta object literal for the '<em><b>Lhs</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_EQ__LHS = eINSTANCE.getNodeEq_Lhs();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeLemmaImpl <em>Node Lemma</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeLemmaImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeLemma()
     * @generated
     */
    EClass NODE_LEMMA = eINSTANCE.getNodeLemma();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_LEMMA__STR = eINSTANCE.getNodeLemma_Str();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl <em>Prim Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrimType()
     * @generated
     */
    EClass PRIM_TYPE = eINSTANCE.getPrimType();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__STRING = eINSTANCE.getPrimType_String();

    /**
     * The meta object literal for the '<em><b>Low Neg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__LOW_NEG = eINSTANCE.getPrimType_LowNeg();

    /**
     * The meta object literal for the '<em><b>Range Low</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__RANGE_LOW = eINSTANCE.getPrimType_RangeLow();

    /**
     * The meta object literal for the '<em><b>High Neg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__HIGH_NEG = eINSTANCE.getPrimType_HighNeg();

    /**
     * The meta object literal for the '<em><b>Range High</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__RANGE_HIGH = eINSTANCE.getPrimType_RangeHigh();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordTypeImpl <em>Record Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordTypeImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordType()
     * @generated
     */
    EClass RECORD_TYPE = eINSTANCE.getRecordType();

    /**
     * The meta object literal for the '<em><b>Record</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_TYPE__RECORD = eINSTANCE.getRecordType_Record();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl <em>Binary Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getBinaryExpr()
     * @generated
     */
    EClass BINARY_EXPR = eINSTANCE.getBinaryExpr();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BINARY_EXPR__LEFT = eINSTANCE.getBinaryExpr_Left();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BINARY_EXPR__OP = eINSTANCE.getBinaryExpr_Op();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BINARY_EXPR__RIGHT = eINSTANCE.getBinaryExpr_Right();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.UnaryExprImpl <em>Unary Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.UnaryExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getUnaryExpr()
     * @generated
     */
    EClass UNARY_EXPR = eINSTANCE.getUnaryExpr();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNARY_EXPR__OP = eINSTANCE.getUnaryExpr_Op();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPR__EXPR = eINSTANCE.getUnaryExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.IfThenElseExprImpl <em>If Then Else Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.IfThenElseExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIfThenElseExpr()
     * @generated
     */
    EClass IF_THEN_ELSE_EXPR = eINSTANCE.getIfThenElseExpr();

    /**
     * The meta object literal for the '<em><b>A</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_THEN_ELSE_EXPR__A = eINSTANCE.getIfThenElseExpr_A();

    /**
     * The meta object literal for the '<em><b>B</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_THEN_ELSE_EXPR__B = eINSTANCE.getIfThenElseExpr_B();

    /**
     * The meta object literal for the '<em><b>C</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_THEN_ELSE_EXPR__C = eINSTANCE.getIfThenElseExpr_C();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl <em>Prev Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrevExpr()
     * @generated
     */
    EClass PREV_EXPR = eINSTANCE.getPrevExpr();

    /**
     * The meta object literal for the '<em><b>Delay</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREV_EXPR__DELAY = eINSTANCE.getPrevExpr_Delay();

    /**
     * The meta object literal for the '<em><b>Init</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREV_EXPR__INIT = eINSTANCE.getPrevExpr_Init();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl <em>Get Property Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getGetPropertyExpr()
     * @generated
     */
    EClass GET_PROPERTY_EXPR = eINSTANCE.getGetPropertyExpr();

    /**
     * The meta object literal for the '<em><b>Component</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GET_PROPERTY_EXPR__COMPONENT = eINSTANCE.getGetPropertyExpr_Component();

    /**
     * The meta object literal for the '<em><b>Prop</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GET_PROPERTY_EXPR__PROP = eINSTANCE.getGetPropertyExpr_Prop();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl <em>Record Update Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordUpdateExpr()
     * @generated
     */
    EClass RECORD_UPDATE_EXPR = eINSTANCE.getRecordUpdateExpr();

    /**
     * The meta object literal for the '<em><b>Record</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_UPDATE_EXPR__RECORD = eINSTANCE.getRecordUpdateExpr_Record();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_UPDATE_EXPR__ARGS = eINSTANCE.getRecordUpdateExpr_Args();

    /**
     * The meta object literal for the '<em><b>Arg Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_UPDATE_EXPR__ARG_EXPR = eINSTANCE.getRecordUpdateExpr_ArgExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl <em>Time Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeExpr()
     * @generated
     */
    EClass TIME_EXPR = eINSTANCE.getTimeExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl <em>Int Lit Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIntLitExpr()
     * @generated
     */
    EClass INT_LIT_EXPR = eINSTANCE.getIntLitExpr();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INT_LIT_EXPR__VAL = eINSTANCE.getIntLitExpr_Val();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.PreExprImpl <em>Pre Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.PreExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPreExpr()
     * @generated
     */
    EClass PRE_EXPR = eINSTANCE.getPreExpr();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRE_EXPR__EXPR = eINSTANCE.getPreExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.EventExprImpl <em>Event Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.EventExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEventExpr()
     * @generated
     */
    EClass EVENT_EXPR = eINSTANCE.getEventExpr();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EVENT_EXPR__ID = eINSTANCE.getEventExpr_Id();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LatchedExprImpl <em>Latched Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LatchedExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLatchedExpr()
     * @generated
     */
    EClass LATCHED_EXPR = eINSTANCE.getLatchedExpr();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LATCHED_EXPR__EXPR = eINSTANCE.getLatchedExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeOfExprImpl <em>Time Of Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TimeOfExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeOfExpr()
     * @generated
     */
    EClass TIME_OF_EXPR = eINSTANCE.getTimeOfExpr();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TIME_OF_EXPR__ID = eINSTANCE.getTimeOfExpr_Id();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeRiseExprImpl <em>Time Rise Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TimeRiseExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeRiseExpr()
     * @generated
     */
    EClass TIME_RISE_EXPR = eINSTANCE.getTimeRiseExpr();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TIME_RISE_EXPR__ID = eINSTANCE.getTimeRiseExpr_Id();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeFallExprImpl <em>Time Fall Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TimeFallExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeFallExpr()
     * @generated
     */
    EClass TIME_FALL_EXPR = eINSTANCE.getTimeFallExpr();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TIME_FALL_EXPR__ID = eINSTANCE.getTimeFallExpr_Id();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealLitExprImpl <em>Real Lit Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RealLitExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealLitExpr()
     * @generated
     */
    EClass REAL_LIT_EXPR = eINSTANCE.getRealLitExpr();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REAL_LIT_EXPR__VAL = eINSTANCE.getRealLitExpr_Val();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.BoolLitExprImpl <em>Bool Lit Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.BoolLitExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getBoolLitExpr()
     * @generated
     */
    EClass BOOL_LIT_EXPR = eINSTANCE.getBoolLitExpr();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOL_LIT_EXPR__VAL = eINSTANCE.getBoolLitExpr_Val();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ThisExprImpl <em>This Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ThisExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getThisExpr()
     * @generated
     */
    EClass THIS_EXPR = eINSTANCE.getThisExpr();

    /**
     * The meta object literal for the '<em><b>Sub This</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference THIS_EXPR__SUB_THIS = eINSTANCE.getThisExpr_SubThis();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl <em>Floor Cast</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFloorCast()
     * @generated
     */
    EClass FLOOR_CAST = eINSTANCE.getFloorCast();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FLOOR_CAST__EXPR = eINSTANCE.getFloorCast_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RealCastImpl <em>Real Cast</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RealCastImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRealCast()
     * @generated
     */
    EClass REAL_CAST = eINSTANCE.getRealCast();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REAL_CAST__EXPR = eINSTANCE.getRealCast_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AADLEnumeratorImpl <em>AADL Enumerator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AADLEnumeratorImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAADLEnumerator()
     * @generated
     */
    EClass AADL_ENUMERATOR = eINSTANCE.getAADLEnumerator();

    /**
     * The meta object literal for the '<em><b>Enum Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AADL_ENUMERATOR__ENUM_TYPE = eINSTANCE.getAADLEnumerator_EnumType();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AADL_ENUMERATOR__VALUE = eINSTANCE.getAADLEnumerator_Value();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl <em>Fn Call Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnCallExpr()
     * @generated
     */
    EClass FN_CALL_EXPR = eINSTANCE.getFnCallExpr();

    /**
     * The meta object literal for the '<em><b>Fn</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_CALL_EXPR__FN = eINSTANCE.getFnCallExpr_Fn();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_CALL_EXPR__ARGS = eINSTANCE.getFnCallExpr_Args();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl <em>Record Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordExpr()
     * @generated
     */
    EClass RECORD_EXPR = eINSTANCE.getRecordExpr();

    /**
     * The meta object literal for the '<em><b>Record</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_EXPR__RECORD = eINSTANCE.getRecordExpr_Record();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_EXPR__ARGS = eINSTANCE.getRecordExpr_Args();

    /**
     * The meta object literal for the '<em><b>Arg Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_EXPR__ARG_EXPR = eINSTANCE.getRecordExpr_ArgExpr();

  }

} //AgreePackage
