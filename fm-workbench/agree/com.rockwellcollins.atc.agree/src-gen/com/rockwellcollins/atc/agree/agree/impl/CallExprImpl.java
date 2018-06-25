/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AbstractionRef;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.Expr;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.CallExprImpl#getAbstractionRef <em>Abstraction Ref</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.CallExprImpl#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CallExprImpl extends ExprImpl implements CallExpr
{
  /**
   * The cached value of the '{@link #getAbstractionRef() <em>Abstraction Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbstractionRef()
   * @generated
   * @ordered
   */
  protected AbstractionRef abstractionRef;

  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected EList<Expr> args;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CallExprImpl()
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
    return AgreePackage.Literals.CALL_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractionRef getAbstractionRef()
  {
    return abstractionRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAbstractionRef(AbstractionRef newAbstractionRef, NotificationChain msgs)
  {
    AbstractionRef oldAbstractionRef = abstractionRef;
    abstractionRef = newAbstractionRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.CALL_EXPR__ABSTRACTION_REF, oldAbstractionRef, newAbstractionRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbstractionRef(AbstractionRef newAbstractionRef)
  {
    if (newAbstractionRef != abstractionRef)
    {
      NotificationChain msgs = null;
      if (abstractionRef != null)
        msgs = ((InternalEObject)abstractionRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.CALL_EXPR__ABSTRACTION_REF, null, msgs);
      if (newAbstractionRef != null)
        msgs = ((InternalEObject)newAbstractionRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.CALL_EXPR__ABSTRACTION_REF, null, msgs);
      msgs = basicSetAbstractionRef(newAbstractionRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.CALL_EXPR__ABSTRACTION_REF, newAbstractionRef, newAbstractionRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expr> getArgs()
  {
    if (args == null)
    {
      args = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.CALL_EXPR__ARGS);
    }
    return args;
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
      case AgreePackage.CALL_EXPR__ABSTRACTION_REF:
        return basicSetAbstractionRef(null, msgs);
      case AgreePackage.CALL_EXPR__ARGS:
        return ((InternalEList<?>)getArgs()).basicRemove(otherEnd, msgs);
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
      case AgreePackage.CALL_EXPR__ABSTRACTION_REF:
        return getAbstractionRef();
      case AgreePackage.CALL_EXPR__ARGS:
        return getArgs();
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
      case AgreePackage.CALL_EXPR__ABSTRACTION_REF:
        setAbstractionRef((AbstractionRef)newValue);
        return;
      case AgreePackage.CALL_EXPR__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Expr>)newValue);
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
      case AgreePackage.CALL_EXPR__ABSTRACTION_REF:
        setAbstractionRef((AbstractionRef)null);
        return;
      case AgreePackage.CALL_EXPR__ARGS:
        getArgs().clear();
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
      case AgreePackage.CALL_EXPR__ABSTRACTION_REF:
        return abstractionRef != null;
      case AgreePackage.CALL_EXPR__ARGS:
        return args != null && !args.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //CallExprImpl
