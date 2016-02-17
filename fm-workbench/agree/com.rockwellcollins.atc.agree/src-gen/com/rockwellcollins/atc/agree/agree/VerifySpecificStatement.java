/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verify Specific Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.VerifySpecificStatement#getFirst <em>First</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.VerifySpecificStatement#getRest <em>Rest</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getVerifySpecificStatement()
 * @model
 * @generated
 */
public interface VerifySpecificStatement extends SpecStatement
{
  /**
   * Returns the value of the '<em><b>First</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>First</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>First</em>' attribute.
   * @see #setFirst(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getVerifySpecificStatement_First()
   * @model
   * @generated
   */
  String getFirst();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.VerifySpecificStatement#getFirst <em>First</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>First</em>' attribute.
   * @see #getFirst()
   * @generated
   */
  void setFirst(String value);

  /**
   * Returns the value of the '<em><b>Rest</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rest</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rest</em>' attribute list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getVerifySpecificStatement_Rest()
   * @model unique="false"
   * @generated
   */
  EList<String> getRest();

} // VerifySpecificStatement
