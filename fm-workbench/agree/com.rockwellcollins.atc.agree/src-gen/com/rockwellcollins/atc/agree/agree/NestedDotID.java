/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nested Dot ID</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getChainID <em>Chain ID</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getTag <em>Tag</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID()
 * @model
 * @generated
 */
public interface NestedDotID extends ComplexExpr
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID_ChainID()
   * @model containment="true"
   * @generated
   */
  ChainID getChainID();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getChainID <em>Chain ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Chain ID</em>' containment reference.
   * @see #getChainID()
   * @generated
   */
  void setChainID(ChainID value);

  /**
   * Returns the value of the '<em><b>Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tag</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tag</em>' attribute.
   * @see #setTag(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNestedDotID_Tag()
   * @model
   * @generated
   */
  String getTag();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.NestedDotID#getTag <em>Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag</em>' attribute.
   * @see #getTag()
   * @generated
   */
  void setTag(String value);

} // NestedDotID
