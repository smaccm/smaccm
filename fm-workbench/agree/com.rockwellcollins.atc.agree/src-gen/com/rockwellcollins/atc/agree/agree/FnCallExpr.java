/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fn Call Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FnCallExpr#getFn <em>Fn</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FnCallExpr#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFnCallExpr()
 * @model
 * @generated
 */
public interface FnCallExpr extends ComplexExpr
{
  /**
   * Returns the value of the '<em><b>Fn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fn</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fn</em>' containment reference.
   * @see #setFn(NestedDotID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFnCallExpr_Fn()
   * @model containment="true"
   * @generated
   */
  NestedDotID getFn();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FnCallExpr#getFn <em>Fn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fn</em>' containment reference.
   * @see #getFn()
   * @generated
   */
  void setFn(NestedDotID value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFnCallExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgs();

} // FnCallExpr
