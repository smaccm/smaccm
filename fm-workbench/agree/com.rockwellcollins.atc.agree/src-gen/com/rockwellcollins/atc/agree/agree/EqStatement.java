/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Eq Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.EqStatement#getLhs <em>Lhs</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getEqStatement()
 * @model
 * @generated
 */
public interface EqStatement extends Element, SpecStatement
{
  /**
   * Returns the value of the '<em><b>Lhs</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lhs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lhs</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getEqStatement_Lhs()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getLhs();

} // EqStatement
