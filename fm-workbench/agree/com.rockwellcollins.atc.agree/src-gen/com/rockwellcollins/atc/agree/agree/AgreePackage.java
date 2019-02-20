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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedSpecStatementImpl <em>Named Spec Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NamedSpecStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedSpecStatement()
   * @generated
   */
  int NAMED_SPEC_STATEMENT = 4;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__STR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__EXPR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT__PATTERN = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Named Spec Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_SPEC_STATEMENT_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl <em>Pattern Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PatternStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPatternStatement()
   * @generated
   */
  int PATTERN_STATEMENT = 5;

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
  int WHEN_STATEMENT = 6;

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
  int WHENEVER_STATEMENT = 7;

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
  int REAL_TIME_STATEMENT = 8;

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
  int TIME_INTERVAL = 9;

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
  int SYNCH_STATEMENT = 10;

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
  int ORDER_STATEMENT = 11;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AbstractionImpl <em>Abstraction</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AbstractionImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAbstraction()
   * @generated
   */
  int ABSTRACTION = 12;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The number of structural features of the '<em>Abstraction</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACTION_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl <em>Property Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PropertyStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPropertyStatement()
   * @generated
   */
  int PROPERTY_STATEMENT = 13;

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
  int CONST_STATEMENT = 14;

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
   * The feature id for the '<em><b>Id</b></em>' reference.
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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnDefImpl <em>Fn Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FnDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnDef()
   * @generated
   */
  int FN_DEF = 18;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF__EXPR = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Fn Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FN_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefImpl <em>Library Fn Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLibraryFnDef()
   * @generated
   */
  int LIBRARY_FN_DEF = 19;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Library Fn Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FN_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl <em>Linearization Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationDef()
   * @generated
   */
  int LINEARIZATION_DEF = 20;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Intervals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__INTERVALS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Precision</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__PRECISION = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Expr Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF__EXPR_BODY = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Linearization Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LINEARIZATION_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl <em>Node Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeDef()
   * @generated
   */
  int NODE_DEF = 22;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rets</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__RETS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Node Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF__NODE_BODY = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Node Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordDefImpl <em>Record Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordDefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordDef()
   * @generated
   */
  int RECORD_DEF = 27;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF__ARGS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Record Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_DEF_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl <em>Enum Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.EnumStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEnumStatement()
   * @generated
   */
  int ENUM_STATEMENT = 28;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExprImpl <em>Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExpr()
   * @generated
   */
  int EXPR = 29;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ComponentRefImpl <em>Component Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ComponentRefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getComponentRef()
   * @generated
   */
  int COMPONENT_REF = 30;

  /**
   * The number of structural features of the '<em>Component Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REF_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayLiteralExprImpl <em>Array Literal Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ArrayLiteralExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayLiteralExpr()
   * @generated
   */
  int ARRAY_LITERAL_EXPR = 31;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Elems</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL_EXPR__ELEMS = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Array Literal Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_LITERAL_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.DoubleDotRefImpl <em>Double Dot Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.DoubleDotRefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getDoubleDotRef()
   * @generated
   */
  int DOUBLE_DOT_REF = 32;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_DOT_REF__OWNED_ELEMENT = TYPE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_DOT_REF__OWNED_COMMENT = TYPE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Elm</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_DOT_REF__ELM = TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Double Dot Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_DOT_REF_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl <em>Named ID</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NamedIDImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedID()
   * @generated
   */
  int NAMED_ID = 33;

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
  int AGREE_CONTRACT_LIBRARY = 34;

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
  int AGREE_CONTRACT_SUBCLAUSE = 35;

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
  int AGREE_CONTRACT = 36;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl <em>Initial Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.InitialStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getInitialStatement()
   * @generated
   */
  int INITIAL_STATEMENT = 37;

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
  int PARAM_STATEMENT = 38;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl <em>Lift Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLiftStatement()
   * @generated
   */
  int LIFT_STATEMENT = 39;

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
   * The feature id for the '<em><b>Subcomp</b></em>' reference.
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
  int CONNECTION_STATEMENT = 40;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl <em>Assume Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssumeStatement()
   * @generated
   */
  int ASSUME_STATEMENT = 41;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__OWNED_ELEMENT = NAMED_SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__OWNED_COMMENT = NAMED_SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__NAME = NAMED_SPEC_STATEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__QUALIFIED_NAME = NAMED_SPEC_STATEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__OWNED_PROPERTY_ASSOCIATION = NAMED_SPEC_STATEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__STR = NAMED_SPEC_STATEMENT__STR;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__EXPR = NAMED_SPEC_STATEMENT__EXPR;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT__PATTERN = NAMED_SPEC_STATEMENT__PATTERN;

  /**
   * The number of structural features of the '<em>Assume Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSUME_STATEMENT_FEATURE_COUNT = NAMED_SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl <em>Guarantee Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getGuaranteeStatement()
   * @generated
   */
  int GUARANTEE_STATEMENT = 42;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__OWNED_ELEMENT = NAMED_SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__OWNED_COMMENT = NAMED_SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__NAME = NAMED_SPEC_STATEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__QUALIFIED_NAME = NAMED_SPEC_STATEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__OWNED_PROPERTY_ASSOCIATION = NAMED_SPEC_STATEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__STR = NAMED_SPEC_STATEMENT__STR;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__EXPR = NAMED_SPEC_STATEMENT__EXPR;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT__PATTERN = NAMED_SPEC_STATEMENT__PATTERN;

  /**
   * The number of structural features of the '<em>Guarantee Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GUARANTEE_STATEMENT_FEATURE_COUNT = NAMED_SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssertStatement()
   * @generated
   */
  int ASSERT_STATEMENT = 43;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__OWNED_ELEMENT = NAMED_SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__OWNED_COMMENT = NAMED_SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__NAME = NAMED_SPEC_STATEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__QUALIFIED_NAME = NAMED_SPEC_STATEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__OWNED_PROPERTY_ASSOCIATION = NAMED_SPEC_STATEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__STR = NAMED_SPEC_STATEMENT__STR;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__EXPR = NAMED_SPEC_STATEMENT__EXPR;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT__PATTERN = NAMED_SPEC_STATEMENT__PATTERN;

  /**
   * The number of structural features of the '<em>Assert Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSERT_STATEMENT_FEATURE_COUNT = NAMED_SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl <em>Lemma Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLemmaStatement()
   * @generated
   */
  int LEMMA_STATEMENT = 44;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__OWNED_ELEMENT = NAMED_SPEC_STATEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__OWNED_COMMENT = NAMED_SPEC_STATEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__NAME = NAMED_SPEC_STATEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__QUALIFIED_NAME = NAMED_SPEC_STATEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__OWNED_PROPERTY_ASSOCIATION = NAMED_SPEC_STATEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__STR = NAMED_SPEC_STATEMENT__STR;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__EXPR = NAMED_SPEC_STATEMENT__EXPR;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT__PATTERN = NAMED_SPEC_STATEMENT__PATTERN;

  /**
   * The number of structural features of the '<em>Lemma Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEMMA_STATEMENT_FEATURE_COUNT = NAMED_SPEC_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl <em>Always Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.AlwaysStatementImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAlwaysStatement()
   * @generated
   */
  int ALWAYS_STATEMENT = 45;

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
  int WHEN_HOLDS_STATEMENT = 46;

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
  int WHEN_OCCURS_STATMENT = 47;

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
  int WHENEVER_OCCURS_STATEMENT = 48;

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
  int WHENEVER_BECOMES_TRUE_STATEMENT = 49;

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
  int WHENEVER_HOLDS_STATEMENT = 50;

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
  int WHENEVER_IMPLIES_STATEMENT = 51;

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
  int PERIODIC_STATEMENT = 52;

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
  int SPORADIC_STATEMENT = 53;

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
  int CLOSED_TIME_INTERVAL = 54;

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
  int OPEN_LEFT_TIME_INTERVAL = 55;

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
  int OPEN_RIGHT_TIME_INTERVAL = 56;

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
  int OPEN_TIME_INTERVAL = 57;

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
  int MN_SYNCH_STATEMENT = 58;

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
  int CALEN_STATEMENT = 59;

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
  int ASYNCH_STATEMENT = 60;

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
  int LATCHED_STATEMENT = 61;

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
  int NODE_EQ = 62;

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
  int NODE_LEMMA = 63;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayTypeImpl <em>Array Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ArrayTypeImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayType()
   * @generated
   */
  int ARRAY_TYPE = 64;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_TYPE__OWNED_ELEMENT = TYPE__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_TYPE__OWNED_COMMENT = TYPE__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Stem</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_TYPE__STEM = TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_TYPE__SIZE = TYPE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Array Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl <em>Prim Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrimType()
   * @generated
   */
  int PRIM_TYPE = 65;

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
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIM_TYPE__NAME = TYPE_FEATURE_COUNT + 0;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ForallExprImpl <em>Forall Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ForallExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getForallExpr()
   * @generated
   */
  int FORALL_EXPR = 66;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR__BINDING = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR__ARRAY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR__EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Forall Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORALL_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExistsExprImpl <em>Exists Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ExistsExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExistsExpr()
   * @generated
   */
  int EXISTS_EXPR = 67;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR__BINDING = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR__ARRAY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR__EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Exists Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXISTS_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FlatmapExprImpl <em>Flatmap Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FlatmapExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFlatmapExpr()
   * @generated
   */
  int FLATMAP_EXPR = 68;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR__BINDING = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR__ARRAY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR__EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Flatmap Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FLATMAP_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FoldLeftExprImpl <em>Fold Left Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FoldLeftExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFoldLeftExpr()
   * @generated
   */
  int FOLD_LEFT_EXPR = 69;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__BINDING = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__ARRAY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Accumulator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__ACCUMULATOR = EXPR_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Initial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__INITIAL = EXPR_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR__EXPR = EXPR_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Fold Left Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_LEFT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FoldRightExprImpl <em>Fold Right Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FoldRightExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFoldRightExpr()
   * @generated
   */
  int FOLD_RIGHT_EXPR = 70;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__BINDING = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__ARRAY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Accumulator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__ACCUMULATOR = EXPR_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Initial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__INITIAL = EXPR_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR__EXPR = EXPR_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Fold Right Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOLD_RIGHT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl <em>Binary Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.BinaryExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getBinaryExpr()
   * @generated
   */
  int BINARY_EXPR = 71;

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
  int UNARY_EXPR = 72;

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
  int IF_THEN_ELSE_EXPR = 73;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ThisRefImpl <em>This Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ThisRefImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getThisRef()
   * @generated
   */
  int THIS_REF = 74;

  /**
   * The number of structural features of the '<em>This Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THIS_REF_FEATURE_COUNT = COMPONENT_REF_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl <em>Prev Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.PrevExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getPrevExpr()
   * @generated
   */
  int PREV_EXPR = 75;

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
  int GET_PROPERTY_EXPR = 76;

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
   * The feature id for the '<em><b>Component Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GET_PROPERTY_EXPR__COMPONENT_REF = EXPR_FEATURE_COUNT + 0;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl <em>Array Update Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayUpdateExpr()
   * @generated
   */
  int ARRAY_UPDATE_EXPR = 77;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR__ARRAY = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Indices</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR__INDICES = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Value Exprs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR__VALUE_EXPRS = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Array Update Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_UPDATE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl <em>Record Update Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordUpdateExpr()
   * @generated
   */
  int RECORD_UPDATE_EXPR = 78;

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
   * The feature id for the '<em><b>Key</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__KEY = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR__EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Record Update Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_UPDATE_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArraySubExprImpl <em>Array Sub Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.ArraySubExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArraySubExpr()
   * @generated
   */
  int ARRAY_SUB_EXPR = 79;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUB_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUB_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUB_EXPR__EXPR = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUB_EXPR__INDEX = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Array Sub Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUB_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TagExprImpl <em>Tag Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TagExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTagExpr()
   * @generated
   */
  int TAG_EXPR = 80;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAG_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAG_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Stem</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAG_EXPR__STEM = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAG_EXPR__TAG = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Tag Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TAG_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.SelectionExprImpl <em>Selection Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.SelectionExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSelectionExpr()
   * @generated
   */
  int SELECTION_EXPR = 81;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_EXPR__TARGET = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Field</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_EXPR__FIELD = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Selection Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTION_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedElmExprImpl <em>Named Elm Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.NamedElmExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedElmExpr()
   * @generated
   */
  int NAMED_ELM_EXPR = 82;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELM_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELM_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Elm</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELM_EXPR__ELM = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Named Elm Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELM_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl <em>Time Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.TimeExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTimeExpr()
   * @generated
   */
  int TIME_EXPR = 83;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.IndicesExprImpl <em>Indices Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.IndicesExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIndicesExpr()
   * @generated
   */
  int INDICES_EXPR = 84;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDICES_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDICES_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDICES_EXPR__ARRAY = EXPR_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Indices Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDICES_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.CallExprImpl <em>Call Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.CallExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCallExpr()
   * @generated
   */
  int CALL_EXPR = 85;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXPR__REF = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXPR__ARGS = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Call Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl <em>Record Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordLitExpr()
   * @generated
   */
  int RECORD_LIT_EXPR = 86;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Record Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR__RECORD_TYPE = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Args</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR__ARGS = EXPR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Arg Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR__ARG_EXPR = EXPR_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Record Lit Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RECORD_LIT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.EnumLitExprImpl <em>Enum Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.EnumLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEnumLitExpr()
   * @generated
   */
  int ENUM_LIT_EXPR = 87;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIT_EXPR__OWNED_ELEMENT = EXPR__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIT_EXPR__OWNED_COMMENT = EXPR__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Enum Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIT_EXPR__ENUM_TYPE = EXPR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIT_EXPR__VALUE = EXPR_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Enum Lit Expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIT_EXPR_FEATURE_COUNT = EXPR_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl <em>Int Lit Expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.IntLitExprImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIntLitExpr()
   * @generated
   */
  int INT_LIT_EXPR = 88;

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
  int PRE_EXPR = 89;

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
  int EVENT_EXPR = 90;

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
   * The feature id for the '<em><b>Id</b></em>' reference.
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
  int LATCHED_EXPR = 91;

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
  int TIME_OF_EXPR = 92;

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
   * The feature id for the '<em><b>Id</b></em>' reference.
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
  int TIME_RISE_EXPR = 93;

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
   * The feature id for the '<em><b>Id</b></em>' reference.
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
  int TIME_FALL_EXPR = 94;

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
   * The feature id for the '<em><b>Id</b></em>' reference.
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
  int REAL_LIT_EXPR = 95;

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
  int BOOL_LIT_EXPR = 96;

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
   * The meta object id for the '{@link com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl <em>Floor Cast</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.rockwellcollins.atc.agree.agree.impl.FloorCastImpl
   * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFloorCast()
   * @generated
   */
  int FLOOR_CAST = 97;

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
  int REAL_CAST = 98;

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NamedSpecStatement <em>Named Spec Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Spec Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedSpecStatement
   * @generated
   */
  EClass getNamedSpecStatement();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getStr()
   * @see #getNamedSpecStatement()
   * @generated
   */
  EAttribute getNamedSpecStatement_Str();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getExpr()
   * @see #getNamedSpecStatement()
   * @generated
   */
  EReference getNamedSpecStatement_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedSpecStatement#getPattern()
   * @see #getNamedSpecStatement()
   * @generated
   */
  EReference getNamedSpecStatement_Pattern();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Abstraction <em>Abstraction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstraction</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Abstraction
   * @generated
   */
  EClass getAbstraction();

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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.AssignStatement#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Id</em>'.
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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FnDef <em>Fn Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fn Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDef
   * @generated
   */
  EClass getFnDef();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.FnDef#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDef#getArgs()
   * @see #getFnDef()
   * @generated
   */
  EReference getFnDef_Args();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FnDef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDef#getType()
   * @see #getFnDef()
   * @generated
   */
  EReference getFnDef_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FnDef#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FnDef#getExpr()
   * @see #getFnDef()
   * @generated
   */
  EReference getFnDef_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDef <em>Library Fn Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library Fn Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDef
   * @generated
   */
  EClass getLibraryFnDef();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDef#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDef#getArgs()
   * @see #getLibraryFnDef()
   * @generated
   */
  EReference getLibraryFnDef_Args();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LibraryFnDef#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LibraryFnDef#getType()
   * @see #getLibraryFnDef()
   * @generated
   */
  EReference getLibraryFnDef_Type();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef <em>Linearization Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Linearization Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef
   * @generated
   */
  EClass getLinearizationDef();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef#getArgs()
   * @see #getLinearizationDef()
   * @generated
   */
  EReference getLinearizationDef_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef#getIntervals <em>Intervals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Intervals</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef#getIntervals()
   * @see #getLinearizationDef()
   * @generated
   */
  EReference getLinearizationDef_Intervals();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef#getPrecision <em>Precision</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Precision</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef#getPrecision()
   * @see #getLinearizationDef()
   * @generated
   */
  EReference getLinearizationDef_Precision();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.LinearizationDef#getExprBody <em>Expr Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr Body</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LinearizationDef#getExprBody()
   * @see #getLinearizationDef()
   * @generated
   */
  EReference getLinearizationDef_ExprBody();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NodeDef <em>Node Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDef
   * @generated
   */
  EClass getNodeDef();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeDef#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDef#getArgs()
   * @see #getNodeDef()
   * @generated
   */
  EReference getNodeDef_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.NodeDef#getRets <em>Rets</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Rets</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDef#getRets()
   * @see #getNodeDef()
   * @generated
   */
  EReference getNodeDef_Rets();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.NodeDef#getNodeBody <em>Node Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Node Body</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NodeDef#getNodeBody()
   * @see #getNodeDef()
   * @generated
   */
  EReference getNodeDef_NodeBody();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordDef <em>Record Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Def</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordDef
   * @generated
   */
  EClass getRecordDef();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.RecordDef#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordDef#getArgs()
   * @see #getRecordDef()
   * @generated
   */
  EReference getRecordDef_Args();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.Expr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.Expr
   * @generated
   */
  EClass getExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ComponentRef <em>Component Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Ref</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ComponentRef
   * @generated
   */
  EClass getComponentRef();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr <em>Array Literal Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Literal Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr
   * @generated
   */
  EClass getArrayLiteralExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr#getElems <em>Elems</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elems</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr#getElems()
   * @see #getArrayLiteralExpr()
   * @generated
   */
  EReference getArrayLiteralExpr_Elems();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.DoubleDotRef <em>Double Dot Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Double Dot Ref</em>'.
   * @see com.rockwellcollins.atc.agree.agree.DoubleDotRef
   * @generated
   */
  EClass getDoubleDotRef();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.DoubleDotRef#getElm <em>Elm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Elm</em>'.
   * @see com.rockwellcollins.atc.agree.agree.DoubleDotRef#getElm()
   * @see #getDoubleDotRef()
   * @generated
   */
  EReference getDoubleDotRef_Elm();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.LiftStatement <em>Lift Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lift Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.LiftStatement
   * @generated
   */
  EClass getLiftStatement();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp <em>Subcomp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Subcomp</em>'.
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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AssumeStatement <em>Assume Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assume Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssumeStatement
   * @generated
   */
  EClass getAssumeStatement();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.AssertStatement <em>Assert Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assert Statement</em>'.
   * @see com.rockwellcollins.atc.agree.agree.AssertStatement
   * @generated
   */
  EClass getAssertStatement();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ArrayType <em>Array Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayType
   * @generated
   */
  EClass getArrayType();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ArrayType#getStem <em>Stem</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Stem</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayType#getStem()
   * @see #getArrayType()
   * @generated
   */
  EReference getArrayType_Stem();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.ArrayType#getSize <em>Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Size</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayType#getSize()
   * @see #getArrayType()
   * @generated
   */
  EAttribute getArrayType_Size();

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
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.PrimType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.rockwellcollins.atc.agree.agree.PrimType#getName()
   * @see #getPrimType()
   * @generated
   */
  EAttribute getPrimType_Name();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ForallExpr <em>Forall Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Forall Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ForallExpr
   * @generated
   */
  EClass getForallExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ForallExpr#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binding</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ForallExpr#getBinding()
   * @see #getForallExpr()
   * @generated
   */
  EReference getForallExpr_Binding();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ForallExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ForallExpr#getArray()
   * @see #getForallExpr()
   * @generated
   */
  EReference getForallExpr_Array();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ForallExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ForallExpr#getExpr()
   * @see #getForallExpr()
   * @generated
   */
  EReference getForallExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ExistsExpr <em>Exists Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Exists Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ExistsExpr
   * @generated
   */
  EClass getExistsExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ExistsExpr#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binding</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ExistsExpr#getBinding()
   * @see #getExistsExpr()
   * @generated
   */
  EReference getExistsExpr_Binding();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ExistsExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ExistsExpr#getArray()
   * @see #getExistsExpr()
   * @generated
   */
  EReference getExistsExpr_Array();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ExistsExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ExistsExpr#getExpr()
   * @see #getExistsExpr()
   * @generated
   */
  EReference getExistsExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FlatmapExpr <em>Flatmap Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Flatmap Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FlatmapExpr
   * @generated
   */
  EClass getFlatmapExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FlatmapExpr#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binding</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FlatmapExpr#getBinding()
   * @see #getFlatmapExpr()
   * @generated
   */
  EReference getFlatmapExpr_Binding();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FlatmapExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FlatmapExpr#getArray()
   * @see #getFlatmapExpr()
   * @generated
   */
  EReference getFlatmapExpr_Array();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FlatmapExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FlatmapExpr#getExpr()
   * @see #getFlatmapExpr()
   * @generated
   */
  EReference getFlatmapExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr <em>Fold Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fold Left Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr
   * @generated
   */
  EClass getFoldLeftExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binding</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getBinding()
   * @see #getFoldLeftExpr()
   * @generated
   */
  EReference getFoldLeftExpr_Binding();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getArray()
   * @see #getFoldLeftExpr()
   * @generated
   */
  EReference getFoldLeftExpr_Array();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getAccumulator <em>Accumulator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Accumulator</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getAccumulator()
   * @see #getFoldLeftExpr()
   * @generated
   */
  EReference getFoldLeftExpr_Accumulator();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getInitial <em>Initial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getInitial()
   * @see #getFoldLeftExpr()
   * @generated
   */
  EReference getFoldLeftExpr_Initial();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldLeftExpr#getExpr()
   * @see #getFoldLeftExpr()
   * @generated
   */
  EReference getFoldLeftExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr <em>Fold Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fold Right Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr
   * @generated
   */
  EClass getFoldRightExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binding</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr#getBinding()
   * @see #getFoldRightExpr()
   * @generated
   */
  EReference getFoldRightExpr_Binding();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr#getArray()
   * @see #getFoldRightExpr()
   * @generated
   */
  EReference getFoldRightExpr_Array();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getAccumulator <em>Accumulator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Accumulator</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr#getAccumulator()
   * @see #getFoldRightExpr()
   * @generated
   */
  EReference getFoldRightExpr_Accumulator();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getInitial <em>Initial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr#getInitial()
   * @see #getFoldRightExpr()
   * @generated
   */
  EReference getFoldRightExpr_Initial();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.FoldRightExpr#getExpr()
   * @see #getFoldRightExpr()
   * @generated
   */
  EReference getFoldRightExpr_Expr();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ThisRef <em>This Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>This Ref</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ThisRef
   * @generated
   */
  EClass getThisRef();

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
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponentRef <em>Component Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component Ref</em>'.
   * @see com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponentRef()
   * @see #getGetPropertyExpr()
   * @generated
   */
  EReference getGetPropertyExpr_ComponentRef();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr <em>Array Update Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Update Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr
   * @generated
   */
  EClass getArrayUpdateExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getArray()
   * @see #getArrayUpdateExpr()
   * @generated
   */
  EReference getArrayUpdateExpr_Array();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getIndices <em>Indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Indices</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getIndices()
   * @see #getArrayUpdateExpr()
   * @generated
   */
  EReference getArrayUpdateExpr_Indices();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getValueExprs <em>Value Exprs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Value Exprs</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getValueExprs()
   * @see #getArrayUpdateExpr()
   * @generated
   */
  EReference getArrayUpdateExpr_ValueExprs();

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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Key</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getKey()
   * @see #getRecordUpdateExpr()
   * @generated
   */
  EReference getRecordUpdateExpr_Key();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getExpr()
   * @see #getRecordUpdateExpr()
   * @generated
   */
  EReference getRecordUpdateExpr_Expr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.ArraySubExpr <em>Array Sub Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Sub Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArraySubExpr
   * @generated
   */
  EClass getArraySubExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ArraySubExpr#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArraySubExpr#getExpr()
   * @see #getArraySubExpr()
   * @generated
   */
  EReference getArraySubExpr_Expr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.ArraySubExpr#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Index</em>'.
   * @see com.rockwellcollins.atc.agree.agree.ArraySubExpr#getIndex()
   * @see #getArraySubExpr()
   * @generated
   */
  EReference getArraySubExpr_Index();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.TagExpr <em>Tag Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tag Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TagExpr
   * @generated
   */
  EClass getTagExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.TagExpr#getStem <em>Stem</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Stem</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TagExpr#getStem()
   * @see #getTagExpr()
   * @generated
   */
  EReference getTagExpr_Stem();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.TagExpr#getTag <em>Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tag</em>'.
   * @see com.rockwellcollins.atc.agree.agree.TagExpr#getTag()
   * @see #getTagExpr()
   * @generated
   */
  EAttribute getTagExpr_Tag();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr <em>Selection Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Selection Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SelectionExpr
   * @generated
   */
  EClass getSelectionExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SelectionExpr#getTarget()
   * @see #getSelectionExpr()
   * @generated
   */
  EReference getSelectionExpr_Target();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getField <em>Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Field</em>'.
   * @see com.rockwellcollins.atc.agree.agree.SelectionExpr#getField()
   * @see #getSelectionExpr()
   * @generated
   */
  EReference getSelectionExpr_Field();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.NamedElmExpr <em>Named Elm Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Elm Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedElmExpr
   * @generated
   */
  EClass getNamedElmExpr();

  /**
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.NamedElmExpr#getElm <em>Elm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Elm</em>'.
   * @see com.rockwellcollins.atc.agree.agree.NamedElmExpr#getElm()
   * @see #getNamedElmExpr()
   * @generated
   */
  EReference getNamedElmExpr_Elm();

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
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.IndicesExpr <em>Indices Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Indices Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IndicesExpr
   * @generated
   */
  EClass getIndicesExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.IndicesExpr#getArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array</em>'.
   * @see com.rockwellcollins.atc.agree.agree.IndicesExpr#getArray()
   * @see #getIndicesExpr()
   * @generated
   */
  EReference getIndicesExpr_Array();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.CallExpr <em>Call Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Call Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CallExpr
   * @generated
   */
  EClass getCallExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.CallExpr#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ref</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CallExpr#getRef()
   * @see #getCallExpr()
   * @generated
   */
  EReference getCallExpr_Ref();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.CallExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.CallExpr#getArgs()
   * @see #getCallExpr()
   * @generated
   */
  EReference getCallExpr_Args();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr <em>Record Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Record Lit Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordLitExpr
   * @generated
   */
  EClass getRecordLitExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getRecordType <em>Record Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Record Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordLitExpr#getRecordType()
   * @see #getRecordLitExpr()
   * @generated
   */
  EReference getRecordLitExpr_RecordType();

  /**
   * Returns the meta object for the reference list '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Args</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgs()
   * @see #getRecordLitExpr()
   * @generated
   */
  EReference getRecordLitExpr_Args();

  /**
   * Returns the meta object for the containment reference list '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgExpr <em>Arg Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arg Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgExpr()
   * @see #getRecordLitExpr()
   * @generated
   */
  EReference getRecordLitExpr_ArgExpr();

  /**
   * Returns the meta object for class '{@link com.rockwellcollins.atc.agree.agree.EnumLitExpr <em>Enum Lit Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Lit Expr</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EnumLitExpr
   * @generated
   */
  EClass getEnumLitExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.rockwellcollins.atc.agree.agree.EnumLitExpr#getEnumType <em>Enum Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Enum Type</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EnumLitExpr#getEnumType()
   * @see #getEnumLitExpr()
   * @generated
   */
  EReference getEnumLitExpr_EnumType();

  /**
   * Returns the meta object for the attribute '{@link com.rockwellcollins.atc.agree.agree.EnumLitExpr#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.rockwellcollins.atc.agree.agree.EnumLitExpr#getValue()
   * @see #getEnumLitExpr()
   * @generated
   */
  EAttribute getEnumLitExpr_Value();

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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.EventExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Id</em>'.
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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Id</em>'.
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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.TimeRiseExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Id</em>'.
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
   * Returns the meta object for the reference '{@link com.rockwellcollins.atc.agree.agree.TimeFallExpr#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Id</em>'.
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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedSpecStatementImpl <em>Named Spec Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NamedSpecStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedSpecStatement()
     * @generated
     */
    EClass NAMED_SPEC_STATEMENT = eINSTANCE.getNamedSpecStatement();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_SPEC_STATEMENT__STR = eINSTANCE.getNamedSpecStatement_Str();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_SPEC_STATEMENT__EXPR = eINSTANCE.getNamedSpecStatement_Expr();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_SPEC_STATEMENT__PATTERN = eINSTANCE.getNamedSpecStatement_Pattern();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AbstractionImpl <em>Abstraction</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AbstractionImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAbstraction()
     * @generated
     */
    EClass ABSTRACTION = eINSTANCE.getAbstraction();

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
     * The meta object literal for the '<em><b>Id</b></em>' reference feature.
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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FnDefImpl <em>Fn Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FnDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFnDef()
     * @generated
     */
    EClass FN_DEF = eINSTANCE.getFnDef();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF__ARGS = eINSTANCE.getFnDef_Args();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF__TYPE = eINSTANCE.getFnDef_Type();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FN_DEF__EXPR = eINSTANCE.getFnDef_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefImpl <em>Library Fn Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LibraryFnDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLibraryFnDef()
     * @generated
     */
    EClass LIBRARY_FN_DEF = eINSTANCE.getLibraryFnDef();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_FN_DEF__ARGS = eINSTANCE.getLibraryFnDef_Args();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_FN_DEF__TYPE = eINSTANCE.getLibraryFnDef_Type();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl <em>Linearization Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLinearizationDef()
     * @generated
     */
    EClass LINEARIZATION_DEF = eINSTANCE.getLinearizationDef();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF__ARGS = eINSTANCE.getLinearizationDef_Args();

    /**
     * The meta object literal for the '<em><b>Intervals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF__INTERVALS = eINSTANCE.getLinearizationDef_Intervals();

    /**
     * The meta object literal for the '<em><b>Precision</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF__PRECISION = eINSTANCE.getLinearizationDef_Precision();

    /**
     * The meta object literal for the '<em><b>Expr Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LINEARIZATION_DEF__EXPR_BODY = eINSTANCE.getLinearizationDef_ExprBody();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl <em>Node Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNodeDef()
     * @generated
     */
    EClass NODE_DEF = eINSTANCE.getNodeDef();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF__ARGS = eINSTANCE.getNodeDef_Args();

    /**
     * The meta object literal for the '<em><b>Rets</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF__RETS = eINSTANCE.getNodeDef_Rets();

    /**
     * The meta object literal for the '<em><b>Node Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_DEF__NODE_BODY = eINSTANCE.getNodeDef_NodeBody();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordDefImpl <em>Record Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordDefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordDef()
     * @generated
     */
    EClass RECORD_DEF = eINSTANCE.getRecordDef();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_DEF__ARGS = eINSTANCE.getRecordDef_Args();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExprImpl <em>Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExpr()
     * @generated
     */
    EClass EXPR = eINSTANCE.getExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ComponentRefImpl <em>Component Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ComponentRefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getComponentRef()
     * @generated
     */
    EClass COMPONENT_REF = eINSTANCE.getComponentRef();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayLiteralExprImpl <em>Array Literal Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ArrayLiteralExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayLiteralExpr()
     * @generated
     */
    EClass ARRAY_LITERAL_EXPR = eINSTANCE.getArrayLiteralExpr();

    /**
     * The meta object literal for the '<em><b>Elems</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_LITERAL_EXPR__ELEMS = eINSTANCE.getArrayLiteralExpr_Elems();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.DoubleDotRefImpl <em>Double Dot Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.DoubleDotRefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getDoubleDotRef()
     * @generated
     */
    EClass DOUBLE_DOT_REF = eINSTANCE.getDoubleDotRef();

    /**
     * The meta object literal for the '<em><b>Elm</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOUBLE_DOT_REF__ELM = eINSTANCE.getDoubleDotRef_Elm();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl <em>Lift Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getLiftStatement()
     * @generated
     */
    EClass LIFT_STATEMENT = eINSTANCE.getLiftStatement();

    /**
     * The meta object literal for the '<em><b>Subcomp</b></em>' reference feature.
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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl <em>Assume Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssumeStatement()
     * @generated
     */
    EClass ASSUME_STATEMENT = eINSTANCE.getAssumeStatement();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getAssertStatement()
     * @generated
     */
    EClass ASSERT_STATEMENT = eINSTANCE.getAssertStatement();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayTypeImpl <em>Array Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ArrayTypeImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayType()
     * @generated
     */
    EClass ARRAY_TYPE = eINSTANCE.getArrayType();

    /**
     * The meta object literal for the '<em><b>Stem</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_TYPE__STEM = eINSTANCE.getArrayType_Stem();

    /**
     * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARRAY_TYPE__SIZE = eINSTANCE.getArrayType_Size();

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
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIM_TYPE__NAME = eINSTANCE.getPrimType_Name();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ForallExprImpl <em>Forall Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ForallExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getForallExpr()
     * @generated
     */
    EClass FORALL_EXPR = eINSTANCE.getForallExpr();

    /**
     * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORALL_EXPR__BINDING = eINSTANCE.getForallExpr_Binding();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORALL_EXPR__ARRAY = eINSTANCE.getForallExpr_Array();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FORALL_EXPR__EXPR = eINSTANCE.getForallExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ExistsExprImpl <em>Exists Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ExistsExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getExistsExpr()
     * @generated
     */
    EClass EXISTS_EXPR = eINSTANCE.getExistsExpr();

    /**
     * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXISTS_EXPR__BINDING = eINSTANCE.getExistsExpr_Binding();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXISTS_EXPR__ARRAY = eINSTANCE.getExistsExpr_Array();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXISTS_EXPR__EXPR = eINSTANCE.getExistsExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FlatmapExprImpl <em>Flatmap Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FlatmapExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFlatmapExpr()
     * @generated
     */
    EClass FLATMAP_EXPR = eINSTANCE.getFlatmapExpr();

    /**
     * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FLATMAP_EXPR__BINDING = eINSTANCE.getFlatmapExpr_Binding();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FLATMAP_EXPR__ARRAY = eINSTANCE.getFlatmapExpr_Array();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FLATMAP_EXPR__EXPR = eINSTANCE.getFlatmapExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FoldLeftExprImpl <em>Fold Left Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FoldLeftExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFoldLeftExpr()
     * @generated
     */
    EClass FOLD_LEFT_EXPR = eINSTANCE.getFoldLeftExpr();

    /**
     * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_LEFT_EXPR__BINDING = eINSTANCE.getFoldLeftExpr_Binding();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_LEFT_EXPR__ARRAY = eINSTANCE.getFoldLeftExpr_Array();

    /**
     * The meta object literal for the '<em><b>Accumulator</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_LEFT_EXPR__ACCUMULATOR = eINSTANCE.getFoldLeftExpr_Accumulator();

    /**
     * The meta object literal for the '<em><b>Initial</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_LEFT_EXPR__INITIAL = eINSTANCE.getFoldLeftExpr_Initial();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_LEFT_EXPR__EXPR = eINSTANCE.getFoldLeftExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.FoldRightExprImpl <em>Fold Right Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.FoldRightExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getFoldRightExpr()
     * @generated
     */
    EClass FOLD_RIGHT_EXPR = eINSTANCE.getFoldRightExpr();

    /**
     * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_RIGHT_EXPR__BINDING = eINSTANCE.getFoldRightExpr_Binding();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_RIGHT_EXPR__ARRAY = eINSTANCE.getFoldRightExpr_Array();

    /**
     * The meta object literal for the '<em><b>Accumulator</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_RIGHT_EXPR__ACCUMULATOR = eINSTANCE.getFoldRightExpr_Accumulator();

    /**
     * The meta object literal for the '<em><b>Initial</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_RIGHT_EXPR__INITIAL = eINSTANCE.getFoldRightExpr_Initial();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOLD_RIGHT_EXPR__EXPR = eINSTANCE.getFoldRightExpr_Expr();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ThisRefImpl <em>This Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ThisRefImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getThisRef()
     * @generated
     */
    EClass THIS_REF = eINSTANCE.getThisRef();

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
     * The meta object literal for the '<em><b>Component Ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GET_PROPERTY_EXPR__COMPONENT_REF = eINSTANCE.getGetPropertyExpr_ComponentRef();

    /**
     * The meta object literal for the '<em><b>Prop</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GET_PROPERTY_EXPR__PROP = eINSTANCE.getGetPropertyExpr_Prop();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl <em>Array Update Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArrayUpdateExpr()
     * @generated
     */
    EClass ARRAY_UPDATE_EXPR = eINSTANCE.getArrayUpdateExpr();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_UPDATE_EXPR__ARRAY = eINSTANCE.getArrayUpdateExpr_Array();

    /**
     * The meta object literal for the '<em><b>Indices</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_UPDATE_EXPR__INDICES = eINSTANCE.getArrayUpdateExpr_Indices();

    /**
     * The meta object literal for the '<em><b>Value Exprs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_UPDATE_EXPR__VALUE_EXPRS = eINSTANCE.getArrayUpdateExpr_ValueExprs();

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
     * The meta object literal for the '<em><b>Key</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_UPDATE_EXPR__KEY = eINSTANCE.getRecordUpdateExpr_Key();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_UPDATE_EXPR__EXPR = eINSTANCE.getRecordUpdateExpr_Expr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.ArraySubExprImpl <em>Array Sub Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.ArraySubExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getArraySubExpr()
     * @generated
     */
    EClass ARRAY_SUB_EXPR = eINSTANCE.getArraySubExpr();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_SUB_EXPR__EXPR = eINSTANCE.getArraySubExpr_Expr();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_SUB_EXPR__INDEX = eINSTANCE.getArraySubExpr_Index();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.TagExprImpl <em>Tag Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.TagExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getTagExpr()
     * @generated
     */
    EClass TAG_EXPR = eINSTANCE.getTagExpr();

    /**
     * The meta object literal for the '<em><b>Stem</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TAG_EXPR__STEM = eINSTANCE.getTagExpr_Stem();

    /**
     * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TAG_EXPR__TAG = eINSTANCE.getTagExpr_Tag();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.SelectionExprImpl <em>Selection Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.SelectionExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getSelectionExpr()
     * @generated
     */
    EClass SELECTION_EXPR = eINSTANCE.getSelectionExpr();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTION_EXPR__TARGET = eINSTANCE.getSelectionExpr_Target();

    /**
     * The meta object literal for the '<em><b>Field</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTION_EXPR__FIELD = eINSTANCE.getSelectionExpr_Field();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.NamedElmExprImpl <em>Named Elm Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.NamedElmExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getNamedElmExpr()
     * @generated
     */
    EClass NAMED_ELM_EXPR = eINSTANCE.getNamedElmExpr();

    /**
     * The meta object literal for the '<em><b>Elm</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELM_EXPR__ELM = eINSTANCE.getNamedElmExpr_Elm();

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
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.IndicesExprImpl <em>Indices Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.IndicesExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getIndicesExpr()
     * @generated
     */
    EClass INDICES_EXPR = eINSTANCE.getIndicesExpr();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INDICES_EXPR__ARRAY = eINSTANCE.getIndicesExpr_Array();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.CallExprImpl <em>Call Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.CallExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getCallExpr()
     * @generated
     */
    EClass CALL_EXPR = eINSTANCE.getCallExpr();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CALL_EXPR__REF = eINSTANCE.getCallExpr_Ref();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CALL_EXPR__ARGS = eINSTANCE.getCallExpr_Args();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl <em>Record Lit Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getRecordLitExpr()
     * @generated
     */
    EClass RECORD_LIT_EXPR = eINSTANCE.getRecordLitExpr();

    /**
     * The meta object literal for the '<em><b>Record Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_LIT_EXPR__RECORD_TYPE = eINSTANCE.getRecordLitExpr_RecordType();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_LIT_EXPR__ARGS = eINSTANCE.getRecordLitExpr_Args();

    /**
     * The meta object literal for the '<em><b>Arg Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RECORD_LIT_EXPR__ARG_EXPR = eINSTANCE.getRecordLitExpr_ArgExpr();

    /**
     * The meta object literal for the '{@link com.rockwellcollins.atc.agree.agree.impl.EnumLitExprImpl <em>Enum Lit Expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.rockwellcollins.atc.agree.agree.impl.EnumLitExprImpl
     * @see com.rockwellcollins.atc.agree.agree.impl.AgreePackageImpl#getEnumLitExpr()
     * @generated
     */
    EClass ENUM_LIT_EXPR = eINSTANCE.getEnumLitExpr();

    /**
     * The meta object literal for the '<em><b>Enum Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_LIT_EXPR__ENUM_TYPE = eINSTANCE.getEnumLitExpr_EnumType();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_LIT_EXPR__VALUE = eINSTANCE.getEnumLitExpr_Value();

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
     * The meta object literal for the '<em><b>Id</b></em>' reference feature.
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
     * The meta object literal for the '<em><b>Id</b></em>' reference feature.
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
     * The meta object literal for the '<em><b>Id</b></em>' reference feature.
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
     * The meta object literal for the '<em><b>Id</b></em>' reference feature.
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

  }

} //AgreePackage
