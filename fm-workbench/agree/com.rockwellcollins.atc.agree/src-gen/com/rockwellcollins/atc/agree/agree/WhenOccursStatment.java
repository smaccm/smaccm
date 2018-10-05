/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>When Occurs Statment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getTimes <em>Times</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getInterval <em>Interval</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenOccursStatment()
 * @model
 * @generated
 */
public interface WhenOccursStatment extends WhenStatement
{
  /**
   * Returns the value of the '<em><b>Times</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Times</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Times</em>' containment reference.
   * @see #setTimes(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenOccursStatment_Times()
   * @model containment="true"
   * @generated
   */
  Expr getTimes();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getTimes <em>Times</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Times</em>' containment reference.
   * @see #getTimes()
   * @generated
   */
  void setTimes(Expr value);

  /**
   * Returns the value of the '<em><b>Interval</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interval</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interval</em>' containment reference.
   * @see #setInterval(TimeInterval)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenOccursStatment_Interval()
   * @model containment="true"
   * @generated
   */
  TimeInterval getInterval();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenOccursStatment#getInterval <em>Interval</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interval</em>' containment reference.
   * @see #getInterval()
   * @generated
   */
  void setInterval(TimeInterval value);

} // WhenOccursStatment
