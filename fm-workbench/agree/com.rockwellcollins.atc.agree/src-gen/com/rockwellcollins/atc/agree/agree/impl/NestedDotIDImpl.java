/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.NestedDotID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nested Dot ID</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl#getSubName <em>Sub Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NestedDotIDImpl extends MinimalEObjectImpl.Container implements NestedDotID
{
  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected NamedElement name;

  /**
   * The cached value of the '{@link #getSubName() <em>Sub Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubName()
   * @generated
   * @ordered
   */
  protected NestedDotID subName;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NestedDotIDImpl()
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
    return AgreePackage.Literals.NESTED_DOT_ID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getName()
  {
    if (name != null && ((EObject)name).eIsProxy())
    {
      InternalEObject oldName = (InternalEObject)name;
      name = (NamedElement)eResolveProxy(oldName);
      if (name != oldName)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.NESTED_DOT_ID__NAME, oldName, name));
      }
    }
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(NamedElement newName)
  {
    NamedElement oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NestedDotID getSubName()
  {
    return subName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubName(NestedDotID newSubName, NotificationChain msgs)
  {
    NestedDotID oldSubName = subName;
    subName = newSubName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__SUB_NAME, oldSubName, newSubName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubName(NestedDotID newSubName)
  {
    if (newSubName != subName)
    {
      NotificationChain msgs = null;
      if (subName != null)
        msgs = ((InternalEObject)subName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NESTED_DOT_ID__SUB_NAME, null, msgs);
      if (newSubName != null)
        msgs = ((InternalEObject)newSubName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NESTED_DOT_ID__SUB_NAME, null, msgs);
      msgs = basicSetSubName(newSubName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__SUB_NAME, newSubName, newSubName));
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
      case AgreePackage.NESTED_DOT_ID__SUB_NAME:
        return basicSetSubName(null, msgs);
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
      case AgreePackage.NESTED_DOT_ID__NAME:
        if (resolve) return getName();
        return basicGetName();
      case AgreePackage.NESTED_DOT_ID__SUB_NAME:
        return getSubName();
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
      case AgreePackage.NESTED_DOT_ID__NAME:
        setName((NamedElement)newValue);
        return;
      case AgreePackage.NESTED_DOT_ID__SUB_NAME:
        setSubName((NestedDotID)newValue);
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
      case AgreePackage.NESTED_DOT_ID__NAME:
        setName((NamedElement)null);
        return;
      case AgreePackage.NESTED_DOT_ID__SUB_NAME:
        setSubName((NestedDotID)null);
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
      case AgreePackage.NESTED_DOT_ID__NAME:
        return name != null;
      case AgreePackage.NESTED_DOT_ID__SUB_NAME:
        return subName != null;
    }
    return super.eIsSet(featureID);
  }

} //NestedDotIDImpl
