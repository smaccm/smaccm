/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Property Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getComponentRef <em>Component Ref</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getProp <em>Prop</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GetPropertyExprImpl extends ExprImpl implements GetPropertyExpr
{
  /**
   * The cached value of the '{@link #getComponentRef() <em>Component Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponentRef()
   * @generated
   * @ordered
   */
  protected ComponentRef componentRef;

  /**
   * The cached value of the '{@link #getProp() <em>Prop</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProp()
   * @generated
   * @ordered
   */
  protected NamedElement prop;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GetPropertyExprImpl()
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
    return AgreePackage.Literals.GET_PROPERTY_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentRef getComponentRef()
  {
    return componentRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponentRef(ComponentRef newComponentRef, NotificationChain msgs)
  {
    ComponentRef oldComponentRef = componentRef;
    componentRef = newComponentRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF, oldComponentRef, newComponentRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponentRef(ComponentRef newComponentRef)
  {
    if (newComponentRef != componentRef)
    {
      NotificationChain msgs = null;
      if (componentRef != null)
        msgs = ((InternalEObject)componentRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF, null, msgs);
      if (newComponentRef != null)
        msgs = ((InternalEObject)newComponentRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF, null, msgs);
      msgs = basicSetComponentRef(newComponentRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF, newComponentRef, newComponentRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getProp()
  {
    if (prop != null && ((EObject)prop).eIsProxy())
    {
      InternalEObject oldProp = (InternalEObject)prop;
      prop = (NamedElement)eResolveProxy(oldProp);
      if (prop != oldProp)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.GET_PROPERTY_EXPR__PROP, oldProp, prop));
      }
    }
    return prop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetProp()
  {
    return prop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProp(NamedElement newProp)
  {
    NamedElement oldProp = prop;
    prop = newProp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__PROP, oldProp, prop));
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF:
        return basicSetComponentRef(null, msgs);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF:
        return getComponentRef();
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        if (resolve) return getProp();
        return basicGetProp();
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF:
        setComponentRef((ComponentRef)newValue);
        return;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        setProp((NamedElement)newValue);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF:
        setComponentRef((ComponentRef)null);
        return;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        setProp((NamedElement)null);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT_REF:
        return componentRef != null;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        return prop != null;
    }
    return super.eIsSet(featureID);
  }

} //GetPropertyExprImpl
