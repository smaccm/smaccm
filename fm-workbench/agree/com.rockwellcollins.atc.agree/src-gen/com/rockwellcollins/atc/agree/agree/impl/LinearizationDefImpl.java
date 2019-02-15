/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.LinearizationInterval;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Linearization Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl#getIntervals <em>Intervals</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.LinearizationDefImpl#getExprBody <em>Expr Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinearizationDefImpl extends NamedElementImpl implements LinearizationDef
{
  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected EList<Arg> args;

  /**
   * The cached value of the '{@link #getIntervals() <em>Intervals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIntervals()
   * @generated
   * @ordered
   */
  protected EList<LinearizationInterval> intervals;

  /**
   * The cached value of the '{@link #getPrecision() <em>Precision</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrecision()
   * @generated
   * @ordered
   */
  protected Expr precision;

  /**
   * The cached value of the '{@link #getExprBody() <em>Expr Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExprBody()
   * @generated
   * @ordered
   */
  protected Expr exprBody;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinearizationDefImpl()
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
    return AgreePackage.Literals.LINEARIZATION_DEF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Arg> getArgs()
  {
    if (args == null)
    {
      args = new EObjectContainmentEList<Arg>(Arg.class, this, AgreePackage.LINEARIZATION_DEF__ARGS);
    }
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<LinearizationInterval> getIntervals()
  {
    if (intervals == null)
    {
      intervals = new EObjectContainmentEList<LinearizationInterval>(LinearizationInterval.class, this, AgreePackage.LINEARIZATION_DEF__INTERVALS);
    }
    return intervals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Expr getPrecision()
  {
    return precision;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPrecision(Expr newPrecision, NotificationChain msgs)
  {
    Expr oldPrecision = precision;
    precision = newPrecision;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.LINEARIZATION_DEF__PRECISION, oldPrecision, newPrecision);
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
  public void setPrecision(Expr newPrecision)
  {
    if (newPrecision != precision)
    {
      NotificationChain msgs = null;
      if (precision != null)
        msgs = ((InternalEObject)precision).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LINEARIZATION_DEF__PRECISION, null, msgs);
      if (newPrecision != null)
        msgs = ((InternalEObject)newPrecision).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LINEARIZATION_DEF__PRECISION, null, msgs);
      msgs = basicSetPrecision(newPrecision, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.LINEARIZATION_DEF__PRECISION, newPrecision, newPrecision));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Expr getExprBody()
  {
    return exprBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExprBody(Expr newExprBody, NotificationChain msgs)
  {
    Expr oldExprBody = exprBody;
    exprBody = newExprBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.LINEARIZATION_DEF__EXPR_BODY, oldExprBody, newExprBody);
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
  public void setExprBody(Expr newExprBody)
  {
    if (newExprBody != exprBody)
    {
      NotificationChain msgs = null;
      if (exprBody != null)
        msgs = ((InternalEObject)exprBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LINEARIZATION_DEF__EXPR_BODY, null, msgs);
      if (newExprBody != null)
        msgs = ((InternalEObject)newExprBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.LINEARIZATION_DEF__EXPR_BODY, null, msgs);
      msgs = basicSetExprBody(newExprBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.LINEARIZATION_DEF__EXPR_BODY, newExprBody, newExprBody));
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
      case AgreePackage.LINEARIZATION_DEF__ARGS:
        return ((InternalEList<?>)getArgs()).basicRemove(otherEnd, msgs);
      case AgreePackage.LINEARIZATION_DEF__INTERVALS:
        return ((InternalEList<?>)getIntervals()).basicRemove(otherEnd, msgs);
      case AgreePackage.LINEARIZATION_DEF__PRECISION:
        return basicSetPrecision(null, msgs);
      case AgreePackage.LINEARIZATION_DEF__EXPR_BODY:
        return basicSetExprBody(null, msgs);
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
      case AgreePackage.LINEARIZATION_DEF__ARGS:
        return getArgs();
      case AgreePackage.LINEARIZATION_DEF__INTERVALS:
        return getIntervals();
      case AgreePackage.LINEARIZATION_DEF__PRECISION:
        return getPrecision();
      case AgreePackage.LINEARIZATION_DEF__EXPR_BODY:
        return getExprBody();
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
      case AgreePackage.LINEARIZATION_DEF__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Arg>)newValue);
        return;
      case AgreePackage.LINEARIZATION_DEF__INTERVALS:
        getIntervals().clear();
        getIntervals().addAll((Collection<? extends LinearizationInterval>)newValue);
        return;
      case AgreePackage.LINEARIZATION_DEF__PRECISION:
        setPrecision((Expr)newValue);
        return;
      case AgreePackage.LINEARIZATION_DEF__EXPR_BODY:
        setExprBody((Expr)newValue);
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
      case AgreePackage.LINEARIZATION_DEF__ARGS:
        getArgs().clear();
        return;
      case AgreePackage.LINEARIZATION_DEF__INTERVALS:
        getIntervals().clear();
        return;
      case AgreePackage.LINEARIZATION_DEF__PRECISION:
        setPrecision((Expr)null);
        return;
      case AgreePackage.LINEARIZATION_DEF__EXPR_BODY:
        setExprBody((Expr)null);
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
      case AgreePackage.LINEARIZATION_DEF__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.LINEARIZATION_DEF__INTERVALS:
        return intervals != null && !intervals.isEmpty();
      case AgreePackage.LINEARIZATION_DEF__PRECISION:
        return precision != null;
      case AgreePackage.LINEARIZATION_DEF__EXPR_BODY:
        return exprBody != null;
    }
    return super.eIsSet(featureID);
  }

} //LinearizationDefImpl
