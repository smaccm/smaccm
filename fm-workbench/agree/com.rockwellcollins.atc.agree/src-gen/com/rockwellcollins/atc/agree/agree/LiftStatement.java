/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

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
   * Returns the value of the '<em><b>Subcomp</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subcomp</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subcomp</em>' reference.
   * @see #setSubcomp(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLiftStatement_Subcomp()
   * @model
   * @generated
   */
  NamedElement getSubcomp();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LiftStatement#getSubcomp <em>Subcomp</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subcomp</em>' reference.
   * @see #getSubcomp()
   * @generated
   */
  void setSubcomp(NamedElement value);

} // LiftStatement
