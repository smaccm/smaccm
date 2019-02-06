/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Elm Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NamedElmExpr#getElm <em>Elm</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNamedElmExpr()
 * @model
 * @generated
 */
public interface NamedElmExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Elm</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elm</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elm</em>' reference.
   * @see #setElm(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNamedElmExpr_Elm()
   * @model
   * @generated
   */
  NamedElement getElm();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.NamedElmExpr#getElm <em>Elm</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Elm</em>' reference.
   * @see #getElm()
   * @generated
   */
  void setElm(NamedElement value);

} // NamedElmExpr
