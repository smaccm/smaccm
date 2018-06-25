/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.SubcomponentRef;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subcomponent Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.SubcomponentRefImpl#getNamedElm <em>Named Elm</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubcomponentRefImpl extends ComponentRefImpl implements SubcomponentRef
{
  /**
   * The cached value of the '{@link #getNamedElm() <em>Named Elm</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamedElm()
   * @generated
   * @ordered
   */
  protected NamedElement namedElm;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SubcomponentRefImpl()
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
    return AgreePackage.Literals.SUBCOMPONENT_REF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getNamedElm()
  {
    if (namedElm != null && ((EObject)namedElm).eIsProxy())
    {
      InternalEObject oldNamedElm = (InternalEObject)namedElm;
      namedElm = (NamedElement)eResolveProxy(oldNamedElm);
      if (namedElm != oldNamedElm)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.SUBCOMPONENT_REF__NAMED_ELM, oldNamedElm, namedElm));
      }
    }
    return namedElm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetNamedElm()
  {
    return namedElm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamedElm(NamedElement newNamedElm)
  {
    NamedElement oldNamedElm = namedElm;
    namedElm = newNamedElm;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.SUBCOMPONENT_REF__NAMED_ELM, oldNamedElm, namedElm));
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
      case AgreePackage.SUBCOMPONENT_REF__NAMED_ELM:
        if (resolve) return getNamedElm();
        return basicGetNamedElm();
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
      case AgreePackage.SUBCOMPONENT_REF__NAMED_ELM:
        setNamedElm((NamedElement)newValue);
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
      case AgreePackage.SUBCOMPONENT_REF__NAMED_ELM:
        setNamedElm((NamedElement)null);
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
      case AgreePackage.SUBCOMPONENT_REF__NAMED_ELM:
        return namedElm != null;
    }
    return super.eIsSet(featureID);
  }

} //SubcomponentRefImpl
