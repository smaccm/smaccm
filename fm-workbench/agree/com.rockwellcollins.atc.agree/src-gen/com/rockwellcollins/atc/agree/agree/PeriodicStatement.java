/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Periodic Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.PeriodicStatement#getPeriod <em>Period</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getPeriodicStatement()
 * @model
 * @generated
 */
public interface PeriodicStatement extends RealTimeStatement
{
  /**
   * Returns the value of the '<em><b>Period</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Period</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Period</em>' containment reference.
   * @see #setPeriod(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getPeriodicStatement_Period()
   * @model containment="true"
   * @generated
   */
  Expr getPeriod();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.PeriodicStatement#getPeriod <em>Period</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Period</em>' containment reference.
   * @see #getPeriod()
   * @generated
   */
  void setPeriod(Expr value);

} // PeriodicStatement
