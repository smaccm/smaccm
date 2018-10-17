/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Of Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTimeOfExpr()
 * @model
 * @generated
 */
public interface TimeOfExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' containment reference.
   * @see #setId(NestedDotID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTimeOfExpr_Id()
   * @model containment="true"
   * @generated
   */
  NestedDotID getId();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.TimeOfExpr#getId <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' containment reference.
   * @see #getId()
   * @generated
   */
  void setId(NestedDotID value);

} // TimeOfExpr
