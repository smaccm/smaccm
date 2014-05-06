/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Record Update Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
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
  public EList<Arg> getArgs()
  {
    if (args == null)
    {
      args = new EObjectResolvingEList<Arg>(Arg.class, this, AgreePackage.RECORD_UPDATE_EXPR__ARGS);
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
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Arg>)newValue);
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
      case AgreePackage.RECORD_UPDATE_EXPR__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.RECORD_UPDATE_EXPR__ARG_EXPR:
        return argExpr != null && !argExpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //RecordUpdateExprImpl
