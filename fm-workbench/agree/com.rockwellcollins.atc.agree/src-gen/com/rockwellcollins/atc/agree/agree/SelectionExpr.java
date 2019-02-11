/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selection Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getTarget <em>Target</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getField <em>Field</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getSelectionExpr()
 * @model
 * @generated
 */
public interface SelectionExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getSelectionExpr_Target()
   * @model containment="true"
   * @generated
   */
  Expr getTarget();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expr value);

  /**
   * Returns the value of the '<em><b>Field</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field</em>' reference.
   * @see #setField(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getSelectionExpr_Field()
   * @model
   * @generated
   */
  NamedElement getField();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.SelectionExpr#getField <em>Field</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field</em>' reference.
   * @see #getField()
   * @generated
   */
  void setField(NamedElement value);

} // SelectionExpr
