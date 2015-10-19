/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Order Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.OrderStatement#getComps <em>Comps</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getOrderStatement()
 * @model
 * @generated
 */
public interface OrderStatement extends SpecStatement
{
  /**
   * Returns the value of the '<em><b>Comps</b></em>' reference list.
   * The list contents are of type {@link org.osate.aadl2.NamedElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comps</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comps</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getOrderStatement_Comps()
   * @model
   * @generated
   */
  EList<NamedElement> getComps();

} // OrderStatement
