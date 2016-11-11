/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sporadic Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.SporadicStatement#getIat <em>Iat</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getSporadicStatement()
 * @model
 * @generated
 */
public interface SporadicStatement extends RealTimeStatement
{
  /**
   * Returns the value of the '<em><b>Iat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Iat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Iat</em>' containment reference.
   * @see #setIat(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getSporadicStatement_Iat()
   * @model containment="true"
   * @generated
   */
  Expr getIat();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.SporadicStatement#getIat <em>Iat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Iat</em>' containment reference.
   * @see #getIat()
   * @generated
   */
  void setIat(Expr value);

} // SporadicStatement
