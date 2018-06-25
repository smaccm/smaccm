/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.CustomType#getStem <em>Stem</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.CustomType#getLeaf <em>Leaf</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCustomType()
 * @model
 * @generated
 */
public interface CustomType extends Type
{
  /**
   * Returns the value of the '<em><b>Stem</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stem</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stem</em>' reference.
   * @see #setStem(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCustomType_Stem()
   * @model
   * @generated
   */
  NamedElement getStem();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.CustomType#getStem <em>Stem</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stem</em>' reference.
   * @see #getStem()
   * @generated
   */
  void setStem(NamedElement value);

  /**
   * Returns the value of the '<em><b>Leaf</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Leaf</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Leaf</em>' reference.
   * @see #setLeaf(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getCustomType_Leaf()
   * @model
   * @generated
   */
  NamedElement getLeaf();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.CustomType#getLeaf <em>Leaf</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Leaf</em>' reference.
   * @see #getLeaf()
   * @generated
   */
  void setLeaf(NamedElement value);

} // CustomType
