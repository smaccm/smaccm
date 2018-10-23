/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NestedDotID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

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
   * The cached value of the '{@link #getSubcomp() <em>Subcomp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubcomp()
   * @generated
   * @ordered
   */
  protected NestedDotID subcomp;

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
  public NestedDotID getSubcomp()
  {
    return subcomp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubcomp(NestedDotID newSubcomp, NotificationChain msgs)
  {
    NestedDotID oldSubcomp = subcomp;
    subcomp = newSubcomp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.LIFT_STATEMENT__SUBCOMP, oldSubcomp, newSubcomp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubcomp(NestedDotID newSubcomp)
  {
    if (newSubcomp != subcomp)
    {
      NotificationChain msgs = null;
      if (subcomp != null)
        msgs = ((InternalEObject)subcomp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LIFT_STATEMENT__SUBCOMP, null, msgs);
      if (newSubcomp != null)
        msgs = ((InternalEObject)newSubcomp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LIFT_STATEMENT__SUBCOMP, null, msgs);
      msgs = basicSetSubcomp(newSubcomp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.LIFT_STATEMENT__SUBCOMP, newSubcomp, newSubcomp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case AgreePackage.LIFT_STATEMENT__SUBCOMP:
        return basicSetSubcomp(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
        return getSubcomp();
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
        setSubcomp((NestedDotID)newValue);
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
        setSubcomp((NestedDotID)null);
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
