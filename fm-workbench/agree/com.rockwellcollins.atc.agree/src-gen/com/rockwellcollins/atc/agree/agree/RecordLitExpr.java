/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Lit Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordLitExpr()
 * @model
 * @generated
 */
public interface RecordLitExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Record Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Record Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Record Type</em>' containment reference.
   * @see #setRecordType(DoubleDotRef)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordLitExpr_RecordType()
   * @model containment="true"
   * @generated
   */
  DoubleDotRef getRecordType();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RecordLitExpr#getRecordType <em>Record Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Record Type</em>' containment reference.
   * @see #getRecordType()
   * @generated
   */
  void setRecordType(DoubleDotRef value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' reference list.
   * The list contents are of type {@link org.osate.aadl2.NamedElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordLitExpr_Args()
   * @model
   * @generated
   */
  EList<NamedElement> getArgs();

  /**
   * Returns the value of the '<em><b>Arg Expr</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg Expr</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordLitExpr_ArgExpr()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgExpr();

} // RecordLitExpr
