/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;

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

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Record Lit Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl#getRecordType <em>Record Type</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordLitExprImpl#getArgExpr <em>Arg Expr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RecordLitExprImpl extends ExprImpl implements RecordLitExpr
{
  /**
   * The cached value of the '{@link #getRecordType() <em>Record Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRecordType()
   * @generated
   * @ordered
   */
  protected DoubleDotRef recordType;

  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected EList<NamedElement> args;

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
  protected RecordLitExprImpl()
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
    return AgreePackage.Literals.RECORD_LIT_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoubleDotRef getRecordType()
  {
    return recordType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRecordType(DoubleDotRef newRecordType, NotificationChain msgs)
  {
    DoubleDotRef oldRecordType = recordType;
    recordType = newRecordType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE, oldRecordType, newRecordType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecordType(DoubleDotRef newRecordType)
  {
    if (newRecordType != recordType)
    {
      NotificationChain msgs = null;
      if (recordType != null)
        msgs = ((InternalEObject)recordType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE, null, msgs);
      if (newRecordType != null)
        msgs = ((InternalEObject)newRecordType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE, null, msgs);
      msgs = basicSetRecordType(newRecordType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE, newRecordType, newRecordType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NamedElement> getArgs()
  {
    if (args == null)
    {
      args = new EObjectResolvingEList<NamedElement>(NamedElement.class, this, AgreePackage.RECORD_LIT_EXPR__ARGS);
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
      argExpr = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.RECORD_LIT_EXPR__ARG_EXPR);
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
      case AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE:
        return basicSetRecordType(null, msgs);
      case AgreePackage.RECORD_LIT_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE:
        return getRecordType();
      case AgreePackage.RECORD_LIT_EXPR__ARGS:
        return getArgs();
      case AgreePackage.RECORD_LIT_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE:
        setRecordType((DoubleDotRef)newValue);
        return;
      case AgreePackage.RECORD_LIT_EXPR__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends NamedElement>)newValue);
        return;
      case AgreePackage.RECORD_LIT_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE:
        setRecordType((DoubleDotRef)null);
        return;
      case AgreePackage.RECORD_LIT_EXPR__ARGS:
        getArgs().clear();
        return;
      case AgreePackage.RECORD_LIT_EXPR__ARG_EXPR:
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
      case AgreePackage.RECORD_LIT_EXPR__RECORD_TYPE:
        return recordType != null;
      case AgreePackage.RECORD_LIT_EXPR__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.RECORD_LIT_EXPR__ARG_EXPR:
        return argExpr != null && !argExpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RecordLitExprImpl
