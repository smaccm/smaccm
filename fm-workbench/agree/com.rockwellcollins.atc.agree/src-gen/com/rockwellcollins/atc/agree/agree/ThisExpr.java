/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>This Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ThisExpr#getSubThis <em>Sub This</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getThisExpr()
 * @model
 * @generated
 */
public interface ThisExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Sub This</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub This</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub This</em>' containment reference.
   * @see #setSubThis(NestedDotID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getThisExpr_SubThis()
   * @model containment="true"
   * @generated
   */
  NestedDotID getSubThis();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ThisExpr#getSubThis <em>Sub This</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sub This</em>' containment reference.
   * @see #getSubThis()
   * @generated
   */
  void setSubThis(NestedDotID value);

} // ThisExpr
