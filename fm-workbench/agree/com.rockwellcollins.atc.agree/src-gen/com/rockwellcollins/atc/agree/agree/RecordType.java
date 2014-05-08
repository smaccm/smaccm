/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordType#getRecord <em>Record</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordType()
 * @model
 * @generated
 */
public interface RecordType extends Type
{
  /**
   * Returns the value of the '<em><b>Record</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Record</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Record</em>' reference.
   * @see #setRecord(NamedElement)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordType_Record()
   * @model
   * @generated
   */
  NamedElement getRecord();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RecordType#getRecord <em>Record</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Record</em>' reference.
   * @see #getRecord()
   * @generated
   */
  void setRecord(NamedElement value);

} // RecordType
