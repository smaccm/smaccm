/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Calen Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.CalenStatement#getEls <em>Els</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCalenStatement()
 * @model
 * @generated
 */
public interface CalenStatement extends SynchStatement
{
  /**
   * Returns the value of the '<em><b>Els</b></em>' reference list.
   * The list contents are of type {@link org.osate.aadl2.NamedElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Els</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Els</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCalenStatement_Els()
   * @model
   * @generated
   */
  EList<NamedElement> getEls();

} // CalenStatement
