/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>When Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getEvent <em>Event</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getExcl <em>Excl</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenStatement()
 * @model
 * @generated
 */
public interface WhenStatement extends PatternStatement
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenStatement_Condition()
   * @model containment="true"
   * @generated
   */
  Expr getCondition();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(Expr value);

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenStatement_Event()
   * @model containment="true"
   * @generated
   */
  Expr getEvent();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getEvent <em>Event</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Event</em>' containment reference.
   * @see #getEvent()
   * @generated
   */
  void setEvent(Expr value);

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWhenStatement_Excl()
   * @model
   * @generated
   */
  String getExcl();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WhenStatement#getExcl <em>Excl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Excl</em>' attribute.
   * @see #getExcl()
   * @generated
   */
  void setExcl(String value);

} // WhenStatement
