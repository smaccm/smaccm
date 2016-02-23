/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Def Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getRets <em>Rets</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getNodeBody <em>Node Body</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDefExpr()
 * @model
 * @generated
 */
public interface NodeDefExpr extends NamedElement, SpecStatement, CallDef
{
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDefExpr_Args()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getArgs();

  /**
   * Returns the value of the '<em><b>Rets</b></em>' containment reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rets</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rets</em>' containment reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDefExpr_Rets()
   * @model containment="true"
   * @generated
   */
  EList<Arg> getRets();

  /**
   * Returns the value of the '<em><b>Node Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Node Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Node Body</em>' containment reference.
   * @see #setNodeBody(NodeBodyExpr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDefExpr_NodeBody()
   * @model containment="true"
   * @generated
   */
  NodeBodyExpr getNodeBody();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.NodeDefExpr#getNodeBody <em>Node Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node Body</em>' containment reference.
   * @see #getNodeBody()
   * @generated
   */
  void setNodeBody(NodeBodyExpr value);

} // NodeDefExpr
