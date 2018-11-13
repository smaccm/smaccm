/**
 */
package com.rockwellcollins.atc.resolute.resolute.impl;

import com.rockwellcollins.atc.resolute.resolute.LibraryFnType;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library Fn Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.LibraryFnTypeImpl#getLibName <em>Lib Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.LibraryFnTypeImpl#getFnType <em>Fn Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryFnTypeImpl extends TypeImpl implements LibraryFnType
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
   * The default value of the '{@link #getFnType() <em>Fn Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFnType()
   * @generated
   * @ordered
   */
  protected static final String FN_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFnType() <em>Fn Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFnType()
   * @generated
   * @ordered
   */
  protected String fnType = FN_TYPE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LibraryFnTypeImpl()
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
    return ResolutePackage.Literals.LIBRARY_FN_TYPE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.LIBRARY_FN_TYPE__LIB_NAME, oldLibName, libName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFnType()
  {
    return fnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFnType(String newFnType)
  {
    String oldFnType = fnType;
    fnType = newFnType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.LIBRARY_FN_TYPE__FN_TYPE, oldFnType, fnType));
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
      case ResolutePackage.LIBRARY_FN_TYPE__LIB_NAME:
        return getLibName();
      case ResolutePackage.LIBRARY_FN_TYPE__FN_TYPE:
        return getFnType();
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
      case ResolutePackage.LIBRARY_FN_TYPE__LIB_NAME:
        setLibName((String)newValue);
        return;
      case ResolutePackage.LIBRARY_FN_TYPE__FN_TYPE:
        setFnType((String)newValue);
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
      case ResolutePackage.LIBRARY_FN_TYPE__LIB_NAME:
        setLibName(LIB_NAME_EDEFAULT);
        return;
      case ResolutePackage.LIBRARY_FN_TYPE__FN_TYPE:
        setFnType(FN_TYPE_EDEFAULT);
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
      case ResolutePackage.LIBRARY_FN_TYPE__LIB_NAME:
        return LIB_NAME_EDEFAULT == null ? libName != null : !LIB_NAME_EDEFAULT.equals(libName);
      case ResolutePackage.LIBRARY_FN_TYPE__FN_TYPE:
        return FN_TYPE_EDEFAULT == null ? fnType != null : !FN_TYPE_EDEFAULT.equals(fnType);
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
    result.append(", fnType: ");
    result.append(fnType);
    result.append(')');
    return result.toString();
  }

} //LibraryFnTypeImpl
