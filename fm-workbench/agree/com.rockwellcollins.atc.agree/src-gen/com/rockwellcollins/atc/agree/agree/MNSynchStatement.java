/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MN Synch Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp1 <em>Comp1</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getComp2 <em>Comp2</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMax <em>Max</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.MNSynchStatement#getMin <em>Min</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getMNSynchStatement()
 * @model
 * @generated
 */
public interface MNSynchStatement extends SynchStatement
{
  /**
   * Returns the value of the '<em><b>Comp1</b></em>' reference list.
   * The list contents are of type {@link org.osate.aadl2.NamedElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp1</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp1</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getMNSynchStatement_Comp1()
   * @model
   * @generated
   */
  EList<NamedElement> getComp1();

  /**
   * Returns the value of the '<em><b>Comp2</b></em>' reference list.
   * The list contents are of type {@link org.osate.aadl2.NamedElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp2</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp2</em>' reference list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getMNSynchStatement_Comp2()
   * @model
   * @generated
   */
  EList<NamedElement> getComp2();

  /**
   * Returns the value of the '<em><b>Max</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max</em>' attribute list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getMNSynchStatement_Max()
   * @model unique="false"
   * @generated
   */
  EList<String> getMax();

  /**
   * Returns the value of the '<em><b>Min</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' attribute list.
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getMNSynchStatement_Min()
   * @model unique="false"
   * @generated
   */
  EList<String> getMin();

} // MNSynchStatement
