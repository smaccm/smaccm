/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.CallExpr#getAbstractionRef <em>Abstraction Ref</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.CallExpr#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCallExpr()
 * @model
 * @generated
 */
public interface CallExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Abstraction Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Abstraction Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstraction Ref</em>' containment reference.
   * @see #setAbstractionRef(AbstractionRef)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCallExpr_AbstractionRef()
   * @model containment="true"
   * @generated
   */
  AbstractionRef getAbstractionRef();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.CallExpr#getAbstractionRef <em>Abstraction Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstraction Ref</em>' containment reference.
   * @see #getAbstractionRef()
   * @generated
   */
  void setAbstractionRef(AbstractionRef value);

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCallExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgs();

} // CallExpr
