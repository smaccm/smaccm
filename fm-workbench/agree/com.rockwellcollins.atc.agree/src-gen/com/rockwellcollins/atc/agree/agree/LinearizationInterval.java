/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linearization Interval</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getStart <em>Start</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationInterval()
 * @model
 * @generated
 */
public interface LinearizationInterval extends EObject, Element
{
  /**
   * Returns the value of the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' containment reference.
   * @see #setStart(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationInterval_Start()
   * @model containment="true"
   * @generated
   */
  Expr getStart();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getStart <em>Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' containment reference.
   * @see #getStart()
   * @generated
   */
  void setStart(Expr value);

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationInterval_End()
   * @model containment="true"
   * @generated
   */
  Expr getEnd();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LinearizationInterval#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Expr value);

} // LinearizationInterval
