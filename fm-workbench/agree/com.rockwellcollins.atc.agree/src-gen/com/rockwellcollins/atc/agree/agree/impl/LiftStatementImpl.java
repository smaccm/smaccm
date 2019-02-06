/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.LiftStatement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lift Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.LiftStatementImpl#getSubcomp <em>Subcomp</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LiftStatementImpl extends SpecStatementImpl implements LiftStatement
{
  /**
   * The cached value of the '{@link #getSubcomp() <em>Subcomp</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubcomp()
   * @generated
   * @ordered
   */
  protected NamedElement subcomp;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LiftStatementImpl()
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
    return AgreePackage.Literals.LIFT_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getSubcomp()
  {
    if (subcomp != null && ((EObject)subcomp).eIsProxy())
    {
      InternalEObject oldSubcomp = (InternalEObject)subcomp;
      subcomp = (NamedElement)eResolveProxy(oldSubcomp);
      if (subcomp != oldSubcomp)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.LIFT_STATEMENT__SUBCOMP, oldSubcomp, subcomp));
      }
    }
    return subcomp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetSubcomp()
  {
    return subcomp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubcomp(NamedElement newSubcomp)
  {
    NamedElement oldSubcomp = subcomp;
    subcomp = newSubcomp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.LIFT_STATEMENT__SUBCOMP, oldSubcomp, subcomp));
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
      case AgreePackage.LIFT_STATEMENT__SUBCOMP:
        if (resolve) return getSubcomp();
        return basicGetSubcomp();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AgreePackage.LIFT_STATEMENT__SUBCOMP:
        setSubcomp((NamedElement)newValue);
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
      case AgreePackage.LIFT_STATEMENT__SUBCOMP:
        setSubcomp((NamedElement)null);
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
      case AgreePackage.LIFT_STATEMENT__SUBCOMP:
        return subcomp != null;
    }
    return super.eIsSet(featureID);
  }

} //LiftStatementImpl
