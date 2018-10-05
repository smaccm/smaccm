/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Eq</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.NodeEq#getLhs <em>Lhs</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeEq()
 * @model
 * @generated
 */
public interface NodeEq extends NodeStmt
{
  /**
   * Returns the value of the '<em><b>Lhs</b></em>' reference list.
   * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lhs</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lhs</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getNodeEq_Lhs()
   * @model
   * @generated
   */
  EList<Arg> getLhs();

} // NodeEq
