/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr;
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
 * An implementation of the model object '<em><b>Array Update Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl#getArray <em>Array</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl#getIndices <em>Indices</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.ArrayUpdateExprImpl#getValueExprs <em>Value Exprs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayUpdateExprImpl extends ExprImpl implements ArrayUpdateExpr
{
  /**
   * The cached value of the '{@link #getArray() <em>Array</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArray()
   * @generated
   * @ordered
   */
  protected Expr array;

  /**
   * The cached value of the '{@link #getIndices() <em>Indices</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndices()
   * @generated
   * @ordered
   */
  protected EList<Expr> indices;

  /**
   * The cached value of the '{@link #getValueExprs() <em>Value Exprs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValueExprs()
   * @generated
   * @ordered
   */
  protected EList<Expr> valueExprs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ArrayUpdateExprImpl()
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
    return AgreePackage.Literals.ARRAY_UPDATE_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Expr getArray()
  {
    return array;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArray(Expr newArray, NotificationChain msgs)
  {
    Expr oldArray = array;
    array = newArray;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.ARRAY_UPDATE_EXPR__ARRAY, oldArray, newArray);
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
  public void setArray(Expr newArray)
  {
    if (newArray != array)
    {
      NotificationChain msgs = null;
      if (array != null)
        msgs = ((InternalEObject)array).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.ARRAY_UPDATE_EXPR__ARRAY, null, msgs);
      if (newArray != null)
        msgs = ((InternalEObject)newArray).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.ARRAY_UPDATE_EXPR__ARRAY, null, msgs);
      msgs = basicSetArray(newArray, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.ARRAY_UPDATE_EXPR__ARRAY, newArray, newArray));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Expr> getIndices()
  {
    if (indices == null)
    {
      indices = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.ARRAY_UPDATE_EXPR__INDICES);
    }
    return indices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Expr> getValueExprs()
  {
    if (valueExprs == null)
    {
      valueExprs = new EObjectContainmentEList<Expr>(Expr.class, this, AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS);
    }
    return valueExprs;
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
      case AgreePackage.ARRAY_UPDATE_EXPR__ARRAY:
        return basicSetArray(null, msgs);
      case AgreePackage.ARRAY_UPDATE_EXPR__INDICES:
        return ((InternalEList<?>)getIndices()).basicRemove(otherEnd, msgs);
      case AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS:
        return ((InternalEList<?>)getValueExprs()).basicRemove(otherEnd, msgs);
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
      case AgreePackage.ARRAY_UPDATE_EXPR__ARRAY:
        return getArray();
      case AgreePackage.ARRAY_UPDATE_EXPR__INDICES:
        return getIndices();
      case AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS:
        return getValueExprs();
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
      case AgreePackage.ARRAY_UPDATE_EXPR__ARRAY:
        setArray((Expr)newValue);
        return;
      case AgreePackage.ARRAY_UPDATE_EXPR__INDICES:
        getIndices().clear();
        getIndices().addAll((Collection<? extends Expr>)newValue);
        return;
      case AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS:
        getValueExprs().clear();
        getValueExprs().addAll((Collection<? extends Expr>)newValue);
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
      case AgreePackage.ARRAY_UPDATE_EXPR__ARRAY:
        setArray((Expr)null);
        return;
      case AgreePackage.ARRAY_UPDATE_EXPR__INDICES:
        getIndices().clear();
        return;
      case AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS:
        getValueExprs().clear();
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
      case AgreePackage.ARRAY_UPDATE_EXPR__ARRAY:
        return array != null;
      case AgreePackage.ARRAY_UPDATE_EXPR__INDICES:
        return indices != null && !indices.isEmpty();
      case AgreePackage.ARRAY_UPDATE_EXPR__VALUE_EXPRS:
        return valueExprs != null && !valueExprs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ArrayUpdateExprImpl
