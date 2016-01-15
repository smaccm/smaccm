/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.PeriodicStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Periodic Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl#getPeriod <em>Period</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PeriodicStatementImpl#getJitter <em>Jitter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PeriodicStatementImpl extends RealTimeStatementImpl implements PeriodicStatement
{
  /**
   * The cached value of the '{@link #getEvent() <em>Event</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEvent()
   * @generated
   * @ordered
   */
  protected Expr event;

  /**
   * The cached value of the '{@link #getPeriod() <em>Period</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPeriod()
   * @generated
   * @ordered
   */
  protected Expr period;

  /**
   * The cached value of the '{@link #getJitter() <em>Jitter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJitter()
   * @generated
   * @ordered
   */
  protected Expr jitter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PeriodicStatementImpl()
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
    return AgreePackage.Literals.PERIODIC_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getEvent()
  {
    return event;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEvent(Expr newEvent, NotificationChain msgs)
  {
    Expr oldEvent = event;
    event = newEvent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__EVENT, oldEvent, newEvent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEvent(Expr newEvent)
  {
    if (newEvent != event)
    {
      NotificationChain msgs = null;
      if (event != null)
        msgs = ((InternalEObject)event).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__EVENT, null, msgs);
      if (newEvent != null)
        msgs = ((InternalEObject)newEvent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__EVENT, null, msgs);
      msgs = basicSetEvent(newEvent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__EVENT, newEvent, newEvent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getPeriod()
  {
    return period;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPeriod(Expr newPeriod, NotificationChain msgs)
  {
    Expr oldPeriod = period;
    period = newPeriod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__PERIOD, oldPeriod, newPeriod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPeriod(Expr newPeriod)
  {
    if (newPeriod != period)
    {
      NotificationChain msgs = null;
      if (period != null)
        msgs = ((InternalEObject)period).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__PERIOD, null, msgs);
      if (newPeriod != null)
        msgs = ((InternalEObject)newPeriod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__PERIOD, null, msgs);
      msgs = basicSetPeriod(newPeriod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__PERIOD, newPeriod, newPeriod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getJitter()
  {
    return jitter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJitter(Expr newJitter, NotificationChain msgs)
  {
    Expr oldJitter = jitter;
    jitter = newJitter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__JITTER, oldJitter, newJitter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJitter(Expr newJitter)
  {
    if (newJitter != jitter)
    {
      NotificationChain msgs = null;
      if (jitter != null)
        msgs = ((InternalEObject)jitter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__JITTER, null, msgs);
      if (newJitter != null)
        msgs = ((InternalEObject)newJitter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.PERIODIC_STATEMENT__JITTER, null, msgs);
      msgs = basicSetJitter(newJitter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PERIODIC_STATEMENT__JITTER, newJitter, newJitter));
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
      case AgreePackage.PERIODIC_STATEMENT__EVENT:
        return basicSetEvent(null, msgs);
      case AgreePackage.PERIODIC_STATEMENT__PERIOD:
        return basicSetPeriod(null, msgs);
      case AgreePackage.PERIODIC_STATEMENT__JITTER:
        return basicSetJitter(null, msgs);
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
      case AgreePackage.PERIODIC_STATEMENT__EVENT:
        return getEvent();
      case AgreePackage.PERIODIC_STATEMENT__PERIOD:
        return getPeriod();
      case AgreePackage.PERIODIC_STATEMENT__JITTER:
        return getJitter();
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
      case AgreePackage.PERIODIC_STATEMENT__EVENT:
        setEvent((Expr)newValue);
        return;
      case AgreePackage.PERIODIC_STATEMENT__PERIOD:
        setPeriod((Expr)newValue);
        return;
      case AgreePackage.PERIODIC_STATEMENT__JITTER:
        setJitter((Expr)newValue);
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
      case AgreePackage.PERIODIC_STATEMENT__EVENT:
        setEvent((Expr)null);
        return;
      case AgreePackage.PERIODIC_STATEMENT__PERIOD:
        setPeriod((Expr)null);
        return;
      case AgreePackage.PERIODIC_STATEMENT__JITTER:
        setJitter((Expr)null);
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
      case AgreePackage.PERIODIC_STATEMENT__EVENT:
        return event != null;
      case AgreePackage.PERIODIC_STATEMENT__PERIOD:
        return period != null;
      case AgreePackage.PERIODIC_STATEMENT__JITTER:
        return jitter != null;
    }
    return super.eIsSet(featureID);
  }

} //PeriodicStatementImpl
