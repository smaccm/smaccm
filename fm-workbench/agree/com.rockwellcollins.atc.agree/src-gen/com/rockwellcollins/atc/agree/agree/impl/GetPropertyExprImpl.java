/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Property Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getProp <em>Prop</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GetPropertyExprImpl extends ExprImpl implements GetPropertyExpr
{
  /**
   * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent()
   * @generated
   * @ordered
   */
  protected Expr component;

  /**
   * The cached value of the '{@link #getProp() <em>Prop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProp()
   * @generated
   * @ordered
   */
  protected Expr prop;

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
  public Expr getComponent()
  {
    return component;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponent(Expr newComponent, NotificationChain msgs)
  {
    Expr oldComponent = component;
    component = newComponent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__COMPONENT, oldComponent, newComponent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponent(Expr newComponent)
  {
    if (newComponent != component)
    {
      NotificationChain msgs = null;
      if (component != null)
        msgs = ((InternalEObject)component).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__COMPONENT, null, msgs);
      if (newComponent != null)
        msgs = ((InternalEObject)newComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__COMPONENT, null, msgs);
      msgs = basicSetComponent(newComponent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__COMPONENT, newComponent, newComponent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getProp()
  {
    return prop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProp(Expr newProp, NotificationChain msgs)
  {
    Expr oldProp = prop;
    prop = newProp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__PROP, oldProp, newProp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProp(Expr newProp)
  {
    if (newProp != prop)
    {
      NotificationChain msgs = null;
      if (prop != null)
        msgs = ((InternalEObject)prop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__PROP, null, msgs);
      if (newProp != null)
        msgs = ((InternalEObject)newProp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.GET_PROPERTY_EXPR__PROP, null, msgs);
      msgs = basicSetProp(newProp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.GET_PROPERTY_EXPR__PROP, newProp, newProp));
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
        return basicSetComponent(null, msgs);
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        return basicSetProp(null, msgs);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
        return getComponent();
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        return getProp();
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
        setComponent((Expr)newValue);
        return;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        setProp((Expr)newValue);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
        setComponent((Expr)null);
        return;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        setProp((Expr)null);
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
      case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
        return component != null;
      case AgreePackage.GET_PROPERTY_EXPR__PROP:
        return prop != null;
    }
    return super.eIsSet(featureID);
  }

} //GetPropertyExprImpl
