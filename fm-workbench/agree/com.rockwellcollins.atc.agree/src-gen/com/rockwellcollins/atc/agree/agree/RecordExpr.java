/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getRecord <em>Record</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordExpr()
 * @model
 * @generated
 */
public interface RecordExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Record</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Record</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Record</em>' reference.
   * @see #setRecord(RecordType)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordExpr_Record()
   * @model
   * @generated
   */
  RecordType getRecord();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RecordExpr#getRecord <em>Record</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Record</em>' reference.
   * @see #getRecord()
   * @generated
   */
  void setRecord(RecordType value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordExpr_Args()
   * @model
   * @generated
   */
  EList<Arg> getArgs();

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordExpr_ArgExpr()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgExpr();

} // RecordExpr
