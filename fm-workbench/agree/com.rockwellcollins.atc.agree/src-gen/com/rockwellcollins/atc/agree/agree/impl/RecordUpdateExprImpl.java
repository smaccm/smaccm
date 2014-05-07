/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;

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
 * An implementation of the model object '<em><b>Record Update Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordUpdateExprImpl#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RecordUpdateExprImpl extends ExprImpl implements RecordUpdateExpr
{
  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected Expr expr;

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
   * The cached value of the '{@link #getArgExpr() <em>Arg Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgExpr()
   * @generated
   * @ordered
   */
  protected EList<Expr> argExpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RecordUpdateExprImpl()
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
    return AgreePackage.Literals.RECORD_UPDATE_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expr getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(Expr newExpr, NotificationChain msgs)
  {
    Expr oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_UPDATE_EXPR__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(Expr newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_UPDATE_EXPR__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_UPDATE_EXPR__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_UPDATE_EXPR__EXPR, newExpr, newExpr));
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
      args = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.RECORD_UPDATE_EXPR__ARGS);
    }
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expr> getArgExpr()
  {
    if (argExpr == null)
    {
      argExpr = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR);
    }
    return argExpr;
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
      case AgreePackage.RECORD_UPDATE_EXPR__EXPR:
        return basicSetExpr(null, msgs);
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        return ((InternalEList<?>)getArgs()).basicRemove(otherEnd, msgs);
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        return ((InternalEList<?>)getArgExpr()).basicRemove(otherEnd, msgs);
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
      case AgreePackage.RECORD_UPDATE_EXPR__EXPR:
        return getExpr();
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        return getArgs();
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        return getArgExpr();
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
      case AgreePackage.RECORD_UPDATE_EXPR__EXPR:
        setExpr((Expr)newValue);
        return;
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Expr>)newValue);
        return;
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        getArgExpr().clear();
        getArgExpr().addAll((Collection<? extends Expr>)newValue);
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
      case AgreePackage.RECORD_UPDATE_EXPR__EXPR:
        setExpr((Expr)null);
        return;
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        getArgs().clear();
        return;
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        getArgExpr().clear();
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
      case AgreePackage.RECORD_UPDATE_EXPR__EXPR:
        return expr != null;
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        return argExpr != null && !argExpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RecordUpdateExprImpl
