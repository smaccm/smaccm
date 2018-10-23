/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstraction Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.AbstractionRef#getNamedElm <em>Named Elm</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAbstractionRef()
 * @model
 * @generated
 */
public interface AbstractionRef extends EObject
{
  /**
   * Returns the value of the '<em><b>Named Elm</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Named Elm</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Named Elm</em>' reference.
   * @see #setNamedElm(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAbstractionRef_NamedElm()
   * @model
   * @generated
   */
  NamedElement getNamedElm();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.AbstractionRef#getNamedElm <em>Named Elm</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Named Elm</em>' reference.
   * @see #getNamedElm()
   * @generated
   */
  void setNamedElm(NamedElement value);

} // AbstractionRef
