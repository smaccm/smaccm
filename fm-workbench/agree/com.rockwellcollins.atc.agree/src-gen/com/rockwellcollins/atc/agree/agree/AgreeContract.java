/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contract</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.AgreeContract#getSpecs <em>Specs</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeContract()
 * @model
 * @generated
 */
public interface AgreeContract extends Contract
{
  /**
   * Returns the value of the '<em><b>Specs</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.SpecStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Specs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Specs</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeContract_Specs()
   * @model containment="true"
   * @generated
   */
  EList<SpecStatement> getSpecs();

} // AgreeContract
