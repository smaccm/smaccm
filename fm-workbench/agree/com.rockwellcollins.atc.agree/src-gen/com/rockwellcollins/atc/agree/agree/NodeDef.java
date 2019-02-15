/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDef#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDef#getRets <em>Rets</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeDef#getNodeBody <em>Node Body</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDef()
 * @model
 * @generated
 */
public interface NodeDef extends NamedElement, SpecStatement, Abstraction
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDef_Args()
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDef_Rets()
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
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeDef_NodeBody()
   * @model containment="true"
   * @generated
   */
  NodeBodyExpr getNodeBody();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.NodeDef#getNodeBody <em>Node Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Node Body</em>' containment reference.
   * @see #getNodeBody()
   * @generated
   */
  void setNodeBody(NodeBodyExpr value);

} // NodeDef
