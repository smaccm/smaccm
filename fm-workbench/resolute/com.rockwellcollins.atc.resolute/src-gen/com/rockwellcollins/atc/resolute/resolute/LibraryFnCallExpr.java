/**
 */
package com.rockwellcollins.atc.resolute.resolute;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library Fn Call Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr#getLibName <em>Lib Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr#getFnName <em>Fn Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnCallExpr()
 * @model
 * @generated
 */
public interface LibraryFnCallExpr extends Expr
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
   * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnCallExpr_LibName()
   * @model
   * @generated
   */
  String getLibName();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr#getLibName <em>Lib Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lib Name</em>' attribute.
   * @see #getLibName()
   * @generated
   */
  void setLibName(String value);

  /**
   * Returns the value of the '<em><b>Fn Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fn Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fn Name</em>' attribute.
   * @see #setFnName(String)
   * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnCallExpr_FnName()
   * @model
   * @generated
   */
  String getFnName();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr#getFnName <em>Fn Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fn Name</em>' attribute.
   * @see #getFnName()
   * @generated
   */
  void setFnName(String value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.resolute.resolute.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage#getLibraryFnCallExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgs();

} // LibraryFnCallExpr
