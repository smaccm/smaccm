/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Whenever Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getCause <em>Cause</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getExcl <em>Excl</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getInterval <em>Interval</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverStatement()
 * @model
 * @generated
 */
public interface WheneverStatement extends PatternStatement
{
  /**
   * Returns the value of the '<em><b>Cause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cause</em>' containment reference.
   * @see #setCause(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverStatement_Cause()
   * @model containment="true"
   * @generated
   */
  Expr getCause();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getCause <em>Cause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cause</em>' containment reference.
   * @see #getCause()
   * @generated
   */
  void setCause(Expr value);

  /**
   * Returns the value of the '<em><b>Excl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Excl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Excl</em>' attribute.
   * @see #setExcl(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverStatement_Excl()
   * @model
   * @generated
   */
  String getExcl();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getExcl <em>Excl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Excl</em>' attribute.
   * @see #getExcl()
   * @generated
   */
  void setExcl(String value);

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverStatement_Interval()
   * @model containment="true"
   * @generated
   */
  TimeInterval getInterval();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverStatement#getInterval <em>Interval</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interval</em>' containment reference.
   * @see #getInterval()
   * @generated
   */
  void setInterval(TimeInterval value);

} // WheneverStatement
