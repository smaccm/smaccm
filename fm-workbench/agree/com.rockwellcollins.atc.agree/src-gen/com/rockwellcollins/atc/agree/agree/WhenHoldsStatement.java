/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>When Holds Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getConditionInterval <em>Condition Interval</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getEventInterval <em>Event Interval</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenHoldsStatement()
 * @model
 * @generated
 */
public interface WhenHoldsStatement extends WhenStatement
{
  /**
   * Returns the value of the '<em><b>Condition Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition Interval</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition Interval</em>' containment reference.
   * @see #setConditionInterval(TimeInterval)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenHoldsStatement_ConditionInterval()
   * @model containment="true"
   * @generated
   */
  TimeInterval getConditionInterval();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getConditionInterval <em>Condition Interval</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition Interval</em>' containment reference.
   * @see #getConditionInterval()
   * @generated
   */
  void setConditionInterval(TimeInterval value);

  /**
   * Returns the value of the '<em><b>Event Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Event Interval</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Event Interval</em>' containment reference.
   * @see #setEventInterval(TimeInterval)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenHoldsStatement_EventInterval()
   * @model containment="true"
   * @generated
   */
  TimeInterval getEventInterval();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenHoldsStatement#getEventInterval <em>Event Interval</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Event Interval</em>' containment reference.
   * @see #getEventInterval()
   * @generated
   */
  void setEventInterval(TimeInterval value);

} // WhenHoldsStatement
