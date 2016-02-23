/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Body Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getLocs <em>Locs</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeBodyExpr#getStmts <em>Stmts</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeBodyExpr()
 * @model
 * @generated
 */
public interface NodeBodyExpr extends EObject, Element
{
  /**
   * Returns the value of the '<em><b>Locs</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Locs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Locs</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeBodyExpr_Locs()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getLocs();

  /**
   * Returns the value of the '<em><b>Stmts</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.NodeStmt}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stmts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stmts</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeBodyExpr_Stmts()
   * @model containment="true"
   * @generated
   */
  EList<NodeStmt> getStmts();

} // NodeBodyExpr
