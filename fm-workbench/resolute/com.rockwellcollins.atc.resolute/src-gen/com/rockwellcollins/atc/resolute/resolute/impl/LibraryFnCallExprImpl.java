/**
 */
package com.rockwellcollins.atc.resolute.resolute.impl;

import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

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
 * An implementation of the model object '<em><b>Library Fn Call Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.LibraryFnCallExprImpl#getLibName <em>Lib Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.LibraryFnCallExprImpl#getFnName <em>Fn Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.LibraryFnCallExprImpl#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryFnCallExprImpl extends ExprImpl implements LibraryFnCallExpr
{
  /**
   * The default value of the '{@link #getLibName() <em>Lib Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLibName()
   * @generated
   * @ordered
   */
  protected static final String LIB_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLibName() <em>Lib Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLibName()
   * @generated
   * @ordered
   */
  protected String libName = LIB_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getFnName() <em>Fn Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFnName()
   * @generated
   * @ordered
   */
  protected static final String FN_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFnName() <em>Fn Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFnName()
   * @generated
   * @ordered
   */
  protected String fnName = FN_NAME_EDEFAULT;

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
  protected LibraryFnCallExprImpl()
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
    return ResolutePackage.Literals.LIBRARY_FN_CALL_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLibName()
  {
    return libName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLibName(String newLibName)
  {
    String oldLibName = libName;
    libName = newLibName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.LIBRARY_FN_CALL_EXPR__LIB_NAME, oldLibName, libName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFnName()
  {
    return fnName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFnName(String newFnName)
  {
    String oldFnName = fnName;
    fnName = newFnName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.LIBRARY_FN_CALL_EXPR__FN_NAME, oldFnName, fnName));
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
      args = new EObjectContainmentEList<Expr>(Expr.class, this, ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS);
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
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS:
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
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__LIB_NAME:
        return getLibName();
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__FN_NAME:
        return getFnName();
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS:
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
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__LIB_NAME:
        setLibName((String)newValue);
        return;
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__FN_NAME:
        setFnName((String)newValue);
        return;
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS:
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
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__LIB_NAME:
        setLibName(LIB_NAME_EDEFAULT);
        return;
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__FN_NAME:
        setFnName(FN_NAME_EDEFAULT);
        return;
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS:
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
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__LIB_NAME:
        return LIB_NAME_EDEFAULT == null ? libName != null : !LIB_NAME_EDEFAULT.equals(libName);
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__FN_NAME:
        return FN_NAME_EDEFAULT == null ? fnName != null : !FN_NAME_EDEFAULT.equals(fnName);
      case ResolutePackage.LIBRARY_FN_CALL_EXPR__ARGS:
        return args != null && !args.isEmpty();
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (libName: ");
    result.append(libName);
    result.append(", fnName: ");
    result.append(fnName);
    result.append(')');
    return result.toString();
  }

} //LibraryFnCallExprImpl
