/**
 */
package com.rockwellcollins.atc.resolute.resolute;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Fn Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnType#getLibName <em>Lib Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnType#getFnType <em>Fn Type</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnType()
 * @model
 * @generated
 */
public interface LibraryFnType extends Type
{
  /**
   * Returns the value of the '<em><b>Lib Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lib Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lib Name</em>' attribute.
   * @see #setLibName(String)
   * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnType_LibName()
   * @model
   * @generated
   */
  String getLibName();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnType#getLibName <em>Lib Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lib Name</em>' attribute.
   * @see #getLibName()
   * @generated
   */
  void setLibName(String value);

  /**
   * Returns the value of the '<em><b>Fn Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fn Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fn Type</em>' attribute.
   * @see #setFnType(String)
   * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnType_FnType()
   * @model
   * @generated
   */
  String getFnType();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnType#getFnType <em>Fn Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fn Type</em>' attribute.
   * @see #getFnType()
   * @generated
   */
  void setFnType(String value);

} // LibraryFnType
