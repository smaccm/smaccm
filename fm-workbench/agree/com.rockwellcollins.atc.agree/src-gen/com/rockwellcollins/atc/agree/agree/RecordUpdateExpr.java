/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Record Update Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.RecordUpdateExpr#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordUpdateExpr()
 * @model
 * @generated
 */
public interface RecordUpdateExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Args</b></em>' reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Args</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordUpdateExpr_Args()
   * @model
   * @generated
   */
  EList<Arg> getArgs();

  /**
   * Returns the value of the '<em><b>Arg Expr</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Expr}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg Expr</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getRecordUpdateExpr_ArgExpr()
   * @model containment="true"
   * @generated
   */
  EList<Expr> getArgExpr();

} // RecordUpdateExpr
