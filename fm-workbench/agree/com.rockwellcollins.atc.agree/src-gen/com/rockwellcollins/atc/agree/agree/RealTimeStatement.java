/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Real Time Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getEvent <em>Event</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getJitter <em>Jitter</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRealTimeStatement()
 * @model
 * @generated
 */
public interface RealTimeStatement extends PatternStatement
{
  /**
   * Returns the value of the '<em><b>Event</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Event</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Event</em>' containment reference.
   * @see #setEvent(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRealTimeStatement_Event()
   * @model containment="true"
   * @generated
   */
  Expr getEvent();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getEvent <em>Event</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Event</em>' containment reference.
   * @see #getEvent()
   * @generated
   */
  void setEvent(Expr value);

  /**
   * Returns the value of the '<em><b>Jitter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Jitter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Jitter</em>' containment reference.
   * @see #setJitter(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRealTimeStatement_Jitter()
   * @model containment="true"
   * @generated
   */
  Expr getJitter();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RealTimeStatement#getJitter <em>Jitter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Jitter</em>' containment reference.
   * @see #getJitter()
   * @generated
   */
  void setJitter(Expr value);

} // RealTimeStatement
