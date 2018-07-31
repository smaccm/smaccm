/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.ChainID;
import com.rockwellcollins.atc.agree.agree.NestedDotID;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nested Dot ID</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl#getChainID <em>Chain ID</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NestedDotIDImpl#getTag <em>Tag</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NestedDotIDImpl extends ComplexExprImpl implements NestedDotID
{
  /**
   * The cached value of the '{@link #getChainID() <em>Chain ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChainID()
   * @generated
   * @ordered
   */
  protected ChainID chainID;

  /**
   * The default value of the '{@link #getTag() <em>Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTag()
   * @generated
   * @ordered
   */
  protected static final String TAG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTag()
   * @generated
   * @ordered
   */
  protected String tag = TAG_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NestedDotIDImpl()
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
    return AgreePackage.Literals.NESTED_DOT_ID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChainID getChainID()
  {
    return chainID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetChainID(ChainID newChainID, NotificationChain msgs)
  {
    ChainID oldChainID = chainID;
    chainID = newChainID;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__CHAIN_ID, oldChainID, newChainID);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setChainID(ChainID newChainID)
  {
    if (newChainID != chainID)
    {
      NotificationChain msgs = null;
      if (chainID != null)
        msgs = ((InternalEObject)chainID).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NESTED_DOT_ID__CHAIN_ID, null, msgs);
      if (newChainID != null)
        msgs = ((InternalEObject)newChainID).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NESTED_DOT_ID__CHAIN_ID, null, msgs);
      msgs = basicSetChainID(newChainID, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__CHAIN_ID, newChainID, newChainID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTag()
  {
    return tag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTag(String newTag)
  {
    String oldTag = tag;
    tag = newTag;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NESTED_DOT_ID__TAG, oldTag, tag));
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
      case AgreePackage.NESTED_DOT_ID__CHAIN_ID:
        return basicSetChainID(null, msgs);
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
      case AgreePackage.NESTED_DOT_ID__CHAIN_ID:
        return getChainID();
      case AgreePackage.NESTED_DOT_ID__TAG:
        return getTag();
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
      case AgreePackage.NESTED_DOT_ID__CHAIN_ID:
        setChainID((ChainID)newValue);
        return;
      case AgreePackage.NESTED_DOT_ID__TAG:
        setTag((String)newValue);
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
      case AgreePackage.NESTED_DOT_ID__CHAIN_ID:
        setChainID((ChainID)null);
        return;
      case AgreePackage.NESTED_DOT_ID__TAG:
        setTag(TAG_EDEFAULT);
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
      case AgreePackage.NESTED_DOT_ID__CHAIN_ID:
        return chainID != null;
      case AgreePackage.NESTED_DOT_ID__TAG:
        return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
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
    result.append(" (tag: ");
    result.append(tag);
    result.append(')');
    return result.toString();
  }

} //NestedDotIDImpl
