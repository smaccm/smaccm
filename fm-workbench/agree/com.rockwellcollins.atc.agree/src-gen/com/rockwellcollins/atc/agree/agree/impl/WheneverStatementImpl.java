/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.TimeInterval;
import com.rockwellcollins.atc.agree.agree.WheneverStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Whenever Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl#getCause <em>Cause</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl#getExcl <em>Excl</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.WheneverStatementImpl#getInterval <em>Interval</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WheneverStatementImpl extends PatternStatementImpl implements WheneverStatement
{
  /**
   * The cached value of the '{@link #getCause() <em>Cause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCause()
   * @generated
   * @ordered
   */
  protected Expr cause;

  /**
   * The default value of the '{@link #getExcl() <em>Excl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExcl()
   * @generated
   * @ordered
   */
  protected static final String EXCL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExcl() <em>Excl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExcl()
   * @generated
   * @ordered
   */
  protected String excl = EXCL_EDEFAULT;

  /**
   * The cached value of the '{@link #getInterval() <em>Interval</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterval()
   * @generated
   * @ordered
   */
  protected TimeInterval interval;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WheneverStatementImpl()
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
    return AgreePackage.Literals.WHENEVER_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Expr getCause()
  {
    return cause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCause(Expr newCause, NotificationChain msgs)
  {
    Expr oldCause = cause;
    cause = newCause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.WHENEVER_STATEMENT__CAUSE, oldCause, newCause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCause(Expr newCause)
  {
    if (newCause != cause)
    {
      NotificationChain msgs = null;
      if (cause != null)
        msgs = ((InternalEObject)cause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.WHENEVER_STATEMENT__CAUSE, null, msgs);
      if (newCause != null)
        msgs = ((InternalEObject)newCause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.WHENEVER_STATEMENT__CAUSE, null, msgs);
      msgs = basicSetCause(newCause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.WHENEVER_STATEMENT__CAUSE, newCause, newCause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getExcl()
  {
    return excl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setExcl(String newExcl)
  {
    String oldExcl = excl;
    excl = newExcl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.WHENEVER_STATEMENT__EXCL, oldExcl, excl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TimeInterval getInterval()
  {
    return interval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInterval(TimeInterval newInterval, NotificationChain msgs)
  {
    TimeInterval oldInterval = interval;
    interval = newInterval;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.WHENEVER_STATEMENT__INTERVAL, oldInterval, newInterval);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setInterval(TimeInterval newInterval)
  {
    if (newInterval != interval)
    {
      NotificationChain msgs = null;
      if (interval != null)
        msgs = ((InternalEObject)interval).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.WHENEVER_STATEMENT__INTERVAL, null, msgs);
      if (newInterval != null)
        msgs = ((InternalEObject)newInterval).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.WHENEVER_STATEMENT__INTERVAL, null, msgs);
      msgs = basicSetInterval(newInterval, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.WHENEVER_STATEMENT__INTERVAL, newInterval, newInterval));
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
      case AgreePackage.WHENEVER_STATEMENT__CAUSE:
        return basicSetCause(null, msgs);
      case AgreePackage.WHENEVER_STATEMENT__INTERVAL:
        return basicSetInterval(null, msgs);
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
      case AgreePackage.WHENEVER_STATEMENT__CAUSE:
        return getCause();
      case AgreePackage.WHENEVER_STATEMENT__EXCL:
        return getExcl();
      case AgreePackage.WHENEVER_STATEMENT__INTERVAL:
        return getInterval();
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
      case AgreePackage.WHENEVER_STATEMENT__CAUSE:
        setCause((Expr)newValue);
        return;
      case AgreePackage.WHENEVER_STATEMENT__EXCL:
        setExcl((String)newValue);
        return;
      case AgreePackage.WHENEVER_STATEMENT__INTERVAL:
        setInterval((TimeInterval)newValue);
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
      case AgreePackage.WHENEVER_STATEMENT__CAUSE:
        setCause((Expr)null);
        return;
      case AgreePackage.WHENEVER_STATEMENT__EXCL:
        setExcl(EXCL_EDEFAULT);
        return;
      case AgreePackage.WHENEVER_STATEMENT__INTERVAL:
        setInterval((TimeInterval)null);
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
      case AgreePackage.WHENEVER_STATEMENT__CAUSE:
        return cause != null;
      case AgreePackage.WHENEVER_STATEMENT__EXCL:
        return EXCL_EDEFAULT == null ? excl != null : !EXCL_EDEFAULT.equals(excl);
      case AgreePackage.WHENEVER_STATEMENT__INTERVAL:
        return interval != null;
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
    result.append(" (excl: ");
    result.append(excl);
    result.append(')');
    return result.toString();
  }

} //WheneverStatementImpl
