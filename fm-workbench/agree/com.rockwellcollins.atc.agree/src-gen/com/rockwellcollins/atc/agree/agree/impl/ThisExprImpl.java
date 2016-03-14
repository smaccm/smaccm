/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.ThisExpr;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>This Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.ThisExprImpl#getSubThis <em>Sub This</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ThisExprImpl extends ExprImpl implements ThisExpr
{
  /**
   * The cached value of the '{@link #getSubThis() <em>Sub This</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubThis()
   * @generated
   * @ordered
   */
  protected NestedDotID subThis;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ThisExprImpl()
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
    return AgreePackage.Literals.THIS_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NestedDotID getSubThis()
  {
    return subThis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubThis(NestedDotID newSubThis, NotificationChain msgs)
  {
    NestedDotID oldSubThis = subThis;
    subThis = newSubThis;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.THIS_EXPR__SUB_THIS, oldSubThis, newSubThis);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubThis(NestedDotID newSubThis)
  {
    if (newSubThis != subThis)
    {
      NotificationChain msgs = null;
      if (subThis != null)
        msgs = ((InternalEObject)subThis).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.THIS_EXPR__SUB_THIS, null, msgs);
      if (newSubThis != null)
        msgs = ((InternalEObject)newSubThis).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.THIS_EXPR__SUB_THIS, null, msgs);
      msgs = basicSetSubThis(newSubThis, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.THIS_EXPR__SUB_THIS, newSubThis, newSubThis));
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
      case AgreePackage.THIS_EXPR__SUB_THIS:
        return basicSetSubThis(null, msgs);
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
      case AgreePackage.THIS_EXPR__SUB_THIS:
        return getSubThis();
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
      case AgreePackage.THIS_EXPR__SUB_THIS:
        setSubThis((NestedDotID)newValue);
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
      case AgreePackage.THIS_EXPR__SUB_THIS:
        setSubThis((NestedDotID)null);
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
      case AgreePackage.THIS_EXPR__SUB_THIS:
        return subThis != null;
    }
    return super.eIsSet(featureID);
  }

} //ThisExprImpl
