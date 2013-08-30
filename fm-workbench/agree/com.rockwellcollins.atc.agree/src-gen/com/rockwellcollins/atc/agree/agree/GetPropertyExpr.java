/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Property Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponent <em>Component</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getProp <em>Prop</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getGetPropertyExpr()
 * @model
 * @generated
 */
public interface GetPropertyExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Component</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component</em>' containment reference.
   * @see #setComponent(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getGetPropertyExpr_Component()
   * @model containment="true"
   * @generated
   */
  Expr getComponent();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getComponent <em>Component</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component</em>' containment reference.
   * @see #getComponent()
   * @generated
   */
  void setComponent(Expr value);

  /**
   * Returns the value of the '<em><b>Prop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prop</em>' containment reference.
   * @see #setProp(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getGetPropertyExpr_Prop()
   * @model containment="true"
   * @generated
   */
  Expr getProp();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.GetPropertyExpr#getProp <em>Prop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prop</em>' containment reference.
   * @see #getProp()
   * @generated
   */
  void setProp(Expr value);

} // GetPropertyExpr
