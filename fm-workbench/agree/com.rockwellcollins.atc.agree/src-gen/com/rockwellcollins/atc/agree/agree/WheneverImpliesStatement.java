/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Whenever Implies Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getLhs <em>Lhs</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getRhs <em>Rhs</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverImpliesStatement()
 * @model
 * @generated
 */
public interface WheneverImpliesStatement extends WheneverStatement
{
  /**
   * Returns the value of the '<em><b>Lhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lhs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lhs</em>' containment reference.
   * @see #setLhs(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverImpliesStatement_Lhs()
   * @model containment="true"
   * @generated
   */
  Expr getLhs();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getLhs <em>Lhs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lhs</em>' containment reference.
   * @see #getLhs()
   * @generated
   */
  void setLhs(Expr value);

  /**
   * Returns the value of the '<em><b>Rhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rhs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rhs</em>' containment reference.
   * @see #setRhs(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getWheneverImpliesStatement_Rhs()
   * @model containment="true"
   * @generated
   */
  Expr getRhs();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.WheneverImpliesStatement#getRhs <em>Rhs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rhs</em>' containment reference.
   * @see #getRhs()
   * @generated
   */
  void setRhs(Expr value);

} // WheneverImpliesStatement
