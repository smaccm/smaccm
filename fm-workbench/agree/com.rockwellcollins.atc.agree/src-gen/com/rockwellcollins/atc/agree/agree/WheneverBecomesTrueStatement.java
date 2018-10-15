/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Whenever Becomes True Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement#getEffect <em>Effect</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverBecomesTrueStatement()
 * @model
 * @generated
 */
public interface WheneverBecomesTrueStatement extends WheneverStatement
{
  /**
   * Returns the value of the '<em><b>Effect</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Effect</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Effect</em>' containment reference.
   * @see #setEffect(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverBecomesTrueStatement_Effect()
   * @model containment="true"
   * @generated
   */
  Expr getEffect();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverBecomesTrueStatement#getEffect <em>Effect</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Effect</em>' containment reference.
   * @see #getEffect()
   * @generated
   */
  void setEffect(Expr value);

} // WheneverBecomesTrueStatement
