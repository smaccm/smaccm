/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quant Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QuantExpr#getQuant <em>Quant</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QuantExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QuantExpr#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQuantExpr()
 * @model
 * @generated
 */
public interface QuantExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Quant</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quant</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quant</em>' attribute.
   * @see #setQuant(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQuantExpr_Quant()
   * @model
   * @generated
   */
  String getQuant();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.QuantExpr#getQuant <em>Quant</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Quant</em>' attribute.
   * @see #getQuant()
   * @generated
   */
  void setQuant(String value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQuantExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getArgs();

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQuantExpr_Expr()
   * @model containment="true"
   * @generated
   */
  Expr getExpr();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.QuantExpr#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Expr value);

} // QuantExpr
