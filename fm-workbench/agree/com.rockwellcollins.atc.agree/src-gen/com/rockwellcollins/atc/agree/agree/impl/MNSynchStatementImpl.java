/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.MNSynchStatement;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MN Synch Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl#getComp1 <em>Comp1</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl#getComp2 <em>Comp2</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl#getMax <em>Max</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.MNSynchStatementImpl#getMin <em>Min</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MNSynchStatementImpl extends SynchStatementImpl implements MNSynchStatement
{
  /**
   * The cached value of the '{@link #getComp1() <em>Comp1</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp1()
   * @generated
   * @ordered
   */
  protected EList<NamedElement> comp1;

  /**
   * The cached value of the '{@link #getComp2() <em>Comp2</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp2()
   * @generated
   * @ordered
   */
  protected EList<NamedElement> comp2;

  /**
   * The cached value of the '{@link #getMax() <em>Max</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected EList<String> max;

  /**
   * The cached value of the '{@link #getMin() <em>Min</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected EList<String> min;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MNSynchStatementImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AgreePackage.Literals.MN_SYNCH_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<NamedElement> getComp1()
  {
    if (comp1 == null)
    {
      comp1 = new EObjectResolvingEList<NamedElement>(NamedElement.class, this, AgreePackage.MN_SYNCH_STATEMENT__COMP1);
    }
    return comp1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<NamedElement> getComp2()
  {
    if (comp2 == null)
    {
      comp2 = new EObjectResolvingEList<NamedElement>(NamedElement.class, this, AgreePackage.MN_SYNCH_STATEMENT__COMP2);
    }
    return comp2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getMax()
  {
    if (max == null)
    {
      max = new EDataTypeEList<String>(String.class, this, AgreePackage.MN_SYNCH_STATEMENT__MAX);
    }
    return max;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getMin()
  {
    if (min == null)
    {
      min = new EDataTypeEList<String>(String.class, this, AgreePackage.MN_SYNCH_STATEMENT__MIN);
    }
    return min;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case AgreePackage.MN_SYNCH_STATEMENT__COMP1:
        return getComp1();
      case AgreePackage.MN_SYNCH_STATEMENT__COMP2:
        return getComp2();
      case AgreePackage.MN_SYNCH_STATEMENT__MAX:
        return getMax();
      case AgreePackage.MN_SYNCH_STATEMENT__MIN:
        return getMin();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AgreePackage.MN_SYNCH_STATEMENT__COMP1:
        getComp1().clear();
        getComp1().addAll((Collection<? extends NamedElement>)newValue);
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__COMP2:
        getComp2().clear();
        getComp2().addAll((Collection<? extends NamedElement>)newValue);
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__MAX:
        getMax().clear();
        getMax().addAll((Collection<? extends String>)newValue);
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__MIN:
        getMin().clear();
        getMin().addAll((Collection<? extends String>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case AgreePackage.MN_SYNCH_STATEMENT__COMP1:
        getComp1().clear();
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__COMP2:
        getComp2().clear();
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__MAX:
        getMax().clear();
        return;
      case AgreePackage.MN_SYNCH_STATEMENT__MIN:
        getMin().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case AgreePackage.MN_SYNCH_STATEMENT__COMP1:
        return comp1 != null && !comp1.isEmpty();
      case AgreePackage.MN_SYNCH_STATEMENT__COMP2:
        return comp2 != null && !comp2.isEmpty();
      case AgreePackage.MN_SYNCH_STATEMENT__MAX:
        return max != null && !max.isEmpty();
      case AgreePackage.MN_SYNCH_STATEMENT__MIN:
        return min != null && !min.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (max: ");
    result.append(max);
    result.append(", min: ");
    result.append(min);
    result.append(')');
    return result.toString();
  }

} //MNSynchStatementImpl
