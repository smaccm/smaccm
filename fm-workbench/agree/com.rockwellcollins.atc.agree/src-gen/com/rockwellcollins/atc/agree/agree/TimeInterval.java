/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Interval</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getLow <em>Low</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getHigh <em>High</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTimeInterval()
 * @model
 * @generated
 */
public interface TimeInterval extends EObject
{
  /**
   * Returns the value of the '<em><b>Low</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Low</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Low</em>' containment reference.
   * @see #setLow(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTimeInterval_Low()
   * @model containment="true"
   * @generated
   */
  Expr getLow();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getLow <em>Low</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Low</em>' containment reference.
   * @see #getLow()
   * @generated
   */
  void setLow(Expr value);

  /**
   * Returns the value of the '<em><b>High</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>High</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>High</em>' containment reference.
   * @see #setHigh(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTimeInterval_High()
   * @model containment="true"
   * @generated
   */
  Expr getHigh();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.TimeInterval#getHigh <em>High</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>High</em>' containment reference.
   * @see #getHigh()
   * @generated
   */
  void setHigh(Expr value);

} // TimeInterval
