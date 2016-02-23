/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lift Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp <em>Subcomp</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLiftStatement()
 * @model
 * @generated
 */
public interface LiftStatement extends SpecStatement
{
  /**
   * Returns the value of the '<em><b>Subcomp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subcomp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subcomp</em>' containment reference.
   * @see #setSubcomp(NestedDotID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLiftStatement_Subcomp()
   * @model containment="true"
   * @generated
   */
  NestedDotID getSubcomp();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp <em>Subcomp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subcomp</em>' containment reference.
   * @see #getSubcomp()
   * @generated
   */
  void setSubcomp(NestedDotID value);

} // LiftStatement
