/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qual ID</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QualID#getAadlQual <em>Aadl Qual</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QualID#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQualID()
 * @model
 * @generated
 */
public interface QualID extends ComplexExpr
{
  /**
   * Returns the value of the '<em><b>Aadl Qual</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Aadl Qual</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Aadl Qual</em>' reference.
   * @see #setAadlQual(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQualID_AadlQual()
   * @model
   * @generated
   */
  NamedElement getAadlQual();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.QualID#getAadlQual <em>Aadl Qual</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Aadl Qual</em>' reference.
   * @see #getAadlQual()
   * @generated
   */
  void setAadlQual(NamedElement value);

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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQualID_Id()
   * @model containment="true"
   * @generated
   */
  NestedDotID getId();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.QualID#getId <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' containment reference.
   * @see #getId()
   * @generated
   */
  void setId(NestedDotID value);

} // QualID
