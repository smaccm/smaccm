/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.VerifySpecificStatement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Verify Specific Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.VerifySpecificStatementImpl#getFirst <em>First</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.VerifySpecificStatementImpl#getRest <em>Rest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VerifySpecificStatementImpl extends SpecStatementImpl implements VerifySpecificStatement
{
  /**
   * The default value of the '{@link #getFirst() <em>First</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirst()
   * @generated
   * @ordered
   */
  protected static final String FIRST_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFirst() <em>First</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirst()
   * @generated
   * @ordered
   */
  protected String first = FIRST_EDEFAULT;

  /**
   * The cached value of the '{@link #getRest() <em>Rest</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRest()
   * @generated
   * @ordered
   */
  protected EList<String> rest;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VerifySpecificStatementImpl()
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
    return AgreePackage.Literals.VERIFY_SPECIFIC_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFirst()
  {
    return first;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFirst(String newFirst)
  {
    String oldFirst = first;
    first = newFirst;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.VERIFY_SPECIFIC_STATEMENT__FIRST, oldFirst, first));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getRest()
  {
    if (rest == null)
    {
      rest = new EDataTypeEList<String>(String.class, this, AgreePackage.VERIFY_SPECIFIC_STATEMENT__REST);
    }
    return rest;
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
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__FIRST:
        return getFirst();
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__REST:
        return getRest();
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
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__FIRST:
        setFirst((String)newValue);
        return;
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__REST:
        getRest().clear();
        getRest().addAll((Collection<? extends String>)newValue);
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
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__FIRST:
        setFirst(FIRST_EDEFAULT);
        return;
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__REST:
        getRest().clear();
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
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__FIRST:
        return FIRST_EDEFAULT == null ? first != null : !FIRST_EDEFAULT.equals(first);
      case AgreePackage.VERIFY_SPECIFIC_STATEMENT__REST:
        return rest != null && !rest.isEmpty();
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
    result.append(" (first: ");
    result.append(first);
    result.append(", rest: ");
    result.append(rest);
    result.append(')');
    return result.toString();
  }

} //VerifySpecificStatementImpl
