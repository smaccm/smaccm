/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contract Subclause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.AgreeContractSubclause#getContract <em>Contract</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeContractSubclause()
 * @model
 * @generated
 */
public interface AgreeContractSubclause extends AgreeSubclause
{
  /**
   * Returns the value of the '<em><b>Contract</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contract</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contract</em>' containment reference.
   * @see #setContract(Contract)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getAgreeContractSubclause_Contract()
   * @model containment="true"
   * @generated
   */
  Contract getContract();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.AgreeContractSubclause#getContract <em>Contract</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Contract</em>' containment reference.
   * @see #getContract()
   * @generated
   */
  void setContract(Contract value);

} // AgreeContractSubclause
