/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.CustomType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.CustomTypeImpl#getStem <em>Stem</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.CustomTypeImpl#getLeaf <em>Leaf</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomTypeImpl extends TypeImpl implements CustomType
{
  /**
   * The cached value of the '{@link #getStem() <em>Stem</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStem()
   * @generated
   * @ordered
   */
  protected NamedElement stem;

  /**
   * The cached value of the '{@link #getLeaf() <em>Leaf</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeaf()
   * @generated
   * @ordered
   */
  protected NamedElement leaf;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CustomTypeImpl()
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
    return AgreePackage.Literals.CUSTOM_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getStem()
  {
    if (stem != null && ((EObject)stem).eIsProxy())
    {
      InternalEObject oldStem = (InternalEObject)stem;
      stem = (NamedElement)eResolveProxy(oldStem);
      if (stem != oldStem)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.CUSTOM_TYPE__STEM, oldStem, stem));
      }
    }
    return stem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetStem()
  {
    return stem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStem(NamedElement newStem)
  {
    NamedElement oldStem = stem;
    stem = newStem;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.CUSTOM_TYPE__STEM, oldStem, stem));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getLeaf()
  {
    if (leaf != null && ((EObject)leaf).eIsProxy())
    {
      InternalEObject oldLeaf = (InternalEObject)leaf;
      leaf = (NamedElement)eResolveProxy(oldLeaf);
      if (leaf != oldLeaf)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AgreePackage.CUSTOM_TYPE__LEAF, oldLeaf, leaf));
      }
    }
    return leaf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetLeaf()
  {
    return leaf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeaf(NamedElement newLeaf)
  {
    NamedElement oldLeaf = leaf;
    leaf = newLeaf;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.CUSTOM_TYPE__LEAF, oldLeaf, leaf));
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
      case AgreePackage.CUSTOM_TYPE__STEM:
        if (resolve) return getStem();
        return basicGetStem();
      case AgreePackage.CUSTOM_TYPE__LEAF:
        if (resolve) return getLeaf();
        return basicGetLeaf();
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
      case AgreePackage.CUSTOM_TYPE__STEM:
        setStem((NamedElement)newValue);
        return;
      case AgreePackage.CUSTOM_TYPE__LEAF:
        setLeaf((NamedElement)newValue);
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
      case AgreePackage.CUSTOM_TYPE__STEM:
        setStem((NamedElement)null);
        return;
      case AgreePackage.CUSTOM_TYPE__LEAF:
        setLeaf((NamedElement)null);
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
      case AgreePackage.CUSTOM_TYPE__STEM:
        return stem != null;
      case AgreePackage.CUSTOM_TYPE__LEAF:
        return leaf != null;
    }
    return super.eIsSet(featureID);
  }

} //CustomTypeImpl
