/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Nested Dot ID</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getName <em>Name
 * </em>}</li>
 * <li>{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getSubName <em>Sub
 * Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID()
 * @model
 * @generated
 */
public interface NestedDotID extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' reference.
     * @see #setName(NamedElement)
     * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID_Name()
     * @model
     * @generated
     */
    NamedElement getName();

    /**
     * Sets the value of the '
     * {@link com.rockwellcollins.atc.agree.agree.NestedDotID#getName
     * <em>Name</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' reference.
     * @see #getName()
     * @generated
     */
    void setName(NamedElement value);

    /**
     * Returns the value of the '<em><b>Sub Name</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Name</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Name</em>' containment reference.
     * @see #setSubName(NestedDotID)
     * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID_SubName()
     * @model containment="true"
     * @generated
     */
    NestedDotID getSubName();

    /**
     * Sets the value of the '
     * {@link com.rockwellcollins.atc.agree.agree.NestedDotID#getSubName
     * <em>Sub Name</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Sub Name</em>' containment
     *            reference.
     * @see #getSubName()
     * @generated
     */
    void setSubName(NestedDotID value);

} // NestedDotID
