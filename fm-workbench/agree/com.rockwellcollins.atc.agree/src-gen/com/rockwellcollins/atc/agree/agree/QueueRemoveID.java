/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Queue Remove ID</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.QueueRemoveID#getComp <em>Comp</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQueueRemoveID()
 * @model
 * @generated
 */
public interface QueueRemoveID extends Expr
{
  /**
   * Returns the value of the '<em><b>Comp</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp</em>' reference.
   * @see #setComp(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getQueueRemoveID_Comp()
   * @model
   * @generated
   */
  NamedElement getComp();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.QueueRemoveID#getComp <em>Comp</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comp</em>' reference.
   * @see #getComp()
   * @generated
   */
  void setComp(NamedElement value);

} // QueueRemoveID
