/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Type Def Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordTypeDefExpr#getName <em>Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordTypeDefExpr#getArgs <em>Args</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordTypeDefExpr()
 * @model
 * @generated
 */
public interface RecordTypeDefExpr extends TypeDefExpr
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordTypeDefExpr_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.RecordTypeDefExpr#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordTypeDefExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getArgs();

} // RecordTypeDefExpr
