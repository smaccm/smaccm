/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.ChainID;
import com.rockwellcollins.atc.agree.agree.RecordProj;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Record Proj</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.RecordProjImpl#getChainID <em>Chain ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RecordProjImpl extends ChainIDImpl implements RecordProj
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RecordProjImpl()
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
    return AgreePackage.Literals.RECORD_PROJ;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_PROJ__CHAIN_ID, oldChainID, newChainID);
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
        msgs = ((InternalEObject)chainID).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_PROJ__CHAIN_ID, null, msgs);
      if (newChainID != null)
        msgs = ((InternalEObject)newChainID).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.RECORD_PROJ__CHAIN_ID, null, msgs);
      msgs = basicSetChainID(newChainID, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.RECORD_PROJ__CHAIN_ID, newChainID, newChainID));
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
      case AgreePackage.RECORD_PROJ__CHAIN_ID:
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
      case AgreePackage.RECORD_PROJ__CHAIN_ID:
        return getChainID();
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
      case AgreePackage.RECORD_PROJ__CHAIN_ID:
        setChainID((ChainID)newValue);
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
      case AgreePackage.RECORD_PROJ__CHAIN_ID:
        setChainID((ChainID)null);
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
      case AgreePackage.RECORD_PROJ__CHAIN_ID:
        return chainID != null;
    }
    return super.eIsSet(featureID);
  }

} //RecordProjImpl
