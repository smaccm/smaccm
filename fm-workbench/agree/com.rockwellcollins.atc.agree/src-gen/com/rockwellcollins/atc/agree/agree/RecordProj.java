/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Proj</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordProj#getChainID <em>Chain ID</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordProj()
 * @model
 * @generated
 */
public interface RecordProj extends ChainID
{
  /**
   * Returns the value of the '<em><b>Chain ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Chain ID</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Chain ID</em>' containment reference.
   * @see #setChainID(ChainID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordProj_ChainID()
   * @model containment="true"
   * @generated
   */
  ChainID getChainID();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RecordProj#getChainID <em>Chain ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Chain ID</em>' containment reference.
   * @see #getChainID()
   * @generated
   */
  void setChainID(ChainID value);

} // RecordProj
