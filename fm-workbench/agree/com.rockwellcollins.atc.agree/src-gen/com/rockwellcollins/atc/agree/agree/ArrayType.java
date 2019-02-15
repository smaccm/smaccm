/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayType#getStem <em>Stem</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.ArrayType#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayType()
 * @model
 * @generated
 */
public interface ArrayType extends Type
{
  /**
   * Returns the value of the '<em><b>Stem</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stem</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stem</em>' containment reference.
   * @see #setStem(Type)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayType_Stem()
   * @model containment="true"
   * @generated
   */
  Type getStem();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ArrayType#getStem <em>Stem</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stem</em>' containment reference.
   * @see #getStem()
   * @generated
   */
  void setStem(Type value);

  /**
   * Returns the value of the '<em><b>Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Size</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Size</em>' attribute.
   * @see #setSize(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getArrayType_Size()
   * @model
   * @generated
   */
  String getSize();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.ArrayType#getSize <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Size</em>' attribute.
   * @see #getSize()
   * @generated
   */
  void setSize(String value);

} // ArrayType
