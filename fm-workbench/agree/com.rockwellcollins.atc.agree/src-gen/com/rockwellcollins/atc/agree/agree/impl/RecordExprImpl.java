/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Record Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl#getRecord <em>Record</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordExprImpl#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RecordExprImpl extends ExprImpl implements RecordExpr
{
  /**
   * The cached value of the '{@link #getRecord() <em>Record</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRecord()
   * @generated
   * @ordered
   */
  protected RecordType record;

  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected EList<Arg> args;

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
  protected RecordExprImpl()
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
    return AgreePackage.Literals.RECORD_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RecordType getRecord()
  {
    if (record != null && record.eIsProxy())
    {
      InternalEObject oldRecord = (InternalEObject)record;
      record = (RecordType)eResolveProxy(oldRecord);
      if (record != oldRecord)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.RECORD_EXPR__RECORD, oldRecord, record));
      }
    }
    return record;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RecordType basicGetRecord()
  {
    return record;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecord(RecordType newRecord)
  {
    RecordType oldRecord = record;
    record = newRecord;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_EXPR__RECORD, oldRecord, record));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Arg> getArgs()
  {
    if (args == null)
    {
      args = new EObjectResolvingEList<Arg>(Arg.class, this, AgreePackage.RECORD_EXPR__ARGS);
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
      argExpr = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.RECORD_EXPR__ARG_EXPR);
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
      case AgreePackage.RECORD_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_EXPR__RECORD:
        if (resolve) return getRecord();
        return basicGetRecord();
      case AgreePackage.RECORD_EXPR__ARGS:
        return getArgs();
      case AgreePackage.RECORD_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_EXPR__RECORD:
        setRecord((RecordType)newValue);
        return;
      case AgreePackage.RECORD_EXPR__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Arg>)newValue);
        return;
      case AgreePackage.RECORD_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_EXPR__RECORD:
        setRecord((RecordType)null);
        return;
      case AgreePackage.RECORD_EXPR__ARGS:
        getArgs().clear();
        return;
      case AgreePackage.RECORD_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_EXPR__RECORD:
        return record != null;
      case AgreePackage.RECORD_EXPR__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.RECORD_EXPR__ARG_EXPR:
        return argExpr != null && !argExpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RecordExprImpl
