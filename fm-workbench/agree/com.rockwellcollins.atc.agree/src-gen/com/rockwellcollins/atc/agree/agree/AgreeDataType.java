/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.AgreeDataType#getData <em>Data</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeDataType()
 * @model
 * @generated
 */
public interface AgreeDataType extends Type
{
  /**
   * Returns the value of the '<em><b>Data</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data</em>' containment reference.
   * @see #setData(NestedDotID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeDataType_Data()
   * @model containment="true"
   * @generated
   */
  NestedDotID getData();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.AgreeDataType#getData <em>Data</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data</em>' containment reference.
   * @see #getData()
   * @generated
   */
  void setData(NestedDotID value);

} // AgreeDataType
