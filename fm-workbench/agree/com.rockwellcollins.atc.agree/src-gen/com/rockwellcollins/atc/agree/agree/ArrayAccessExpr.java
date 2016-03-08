/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Access Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayAccessExpr#getArray <em>Array</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayAccessExpr#getArg <em>Arg</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayAccessExpr()
 * @model
 * @generated
 */
public interface ArrayAccessExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array</em>' containment reference.
   * @see #setArray(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayAccessExpr_Array()
   * @model containment="true"
   * @generated
   */
  Expr getArray();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ArrayAccessExpr#getArray <em>Array</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' containment reference.
   * @see #getArray()
   * @generated
   */
  void setArray(Expr value);

  /**
   * Returns the value of the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg</em>' containment reference.
   * @see #setArg(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayAccessExpr_Arg()
   * @model containment="true"
   * @generated
   */
  Expr getArg();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ArrayAccessExpr#getArg <em>Arg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg</em>' containment reference.
   * @see #getArg()
   * @generated
   */
  void setArg(Expr value);

} // ArrayAccessExpr
