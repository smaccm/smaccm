/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Update Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getArray <em>Array</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getIndices <em>Indices</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getValueExprs <em>Value Exprs</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayUpdateExpr()
 * @model
 * @generated
 */
public interface ArrayUpdateExpr extends Expr
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayUpdateExpr_Array()
   * @model containment="true"
   * @generated
   */
  Expr getArray();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr#getArray <em>Array</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' containment reference.
   * @see #getArray()
   * @generated
   */
  void setArray(Expr value);

  /**
   * Returns the value of the '<em><b>Indices</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Indices</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Indices</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayUpdateExpr_Indices()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getIndices();

  /**
   * Returns the value of the '<em><b>Value Exprs</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Exprs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Exprs</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayUpdateExpr_ValueExprs()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getValueExprs();

} // ArrayUpdateExpr
