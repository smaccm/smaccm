/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Prev Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getDelay <em>Delay</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getInit <em>Init</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getPrevExpr()
 * @model
 * @generated
 */
public interface PrevExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Delay</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Delay</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Delay</em>' containment reference.
   * @see #setDelay(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getPrevExpr_Delay()
   * @model containment="true"
   * @generated
   */
  Expr getDelay();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getDelay <em>Delay</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Delay</em>' containment reference.
   * @see #getDelay()
   * @generated
   */
  void setDelay(Expr value);

  /**
   * Returns the value of the '<em><b>Init</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Init</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Init</em>' containment reference.
   * @see #setInit(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getPrevExpr_Init()
   * @model containment="true"
   * @generated
   */
  Expr getInit();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.PrevExpr#getInit <em>Init</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Init</em>' containment reference.
   * @see #getInit()
   * @generated
   */
  void setInit(Expr value);

} // PrevExpr
