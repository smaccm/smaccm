/**
 */
package com.rockwellcollins.atc.resolute.resolute.impl;

import com.rockwellcollins.atc.resolute.resolute.ElementSet;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Id Expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.IdExprImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.resolute.resolute.impl.IdExprImpl#getSubelements <em>Subelements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdExprImpl extends ExprImpl implements IdExpr
{
  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected NamedElement id;

  /**
   * The cached value of the '{@link #getSubelements() <em>Subelements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubelements()
   * @generated
   * @ordered
   */
  protected ElementSet subelements;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IdExprImpl()
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
    return ResolutePackage.Literals.ID_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getId()
  {
    if (id != null && ((EObject)id).eIsProxy())
    {
      InternalEObject oldId = (InternalEObject)id;
      id = (NamedElement)eResolveProxy(oldId);
      if (id != oldId)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResolutePackage.ID_EXPR__ID, oldId, id));
      }
    }
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(NamedElement newId)
  {
    NamedElement oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.ID_EXPR__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementSet getSubelements()
  {
    return subelements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubelements(ElementSet newSubelements, NotificationChain msgs)
  {
    ElementSet oldSubelements = subelements;
    subelements = newSubelements;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ResolutePackage.ID_EXPR__SUBELEMENTS, oldSubelements, newSubelements);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubelements(ElementSet newSubelements)
  {
    if (newSubelements != subelements)
    {
      NotificationChain msgs = null;
      if (subelements != null)
        msgs = ((InternalEObject)subelements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ResolutePackage.ID_EXPR__SUBELEMENTS, null, msgs);
      if (newSubelements != null)
        msgs = ((InternalEObject)newSubelements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ResolutePackage.ID_EXPR__SUBELEMENTS, null, msgs);
      msgs = basicSetSubelements(newSubelements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ResolutePackage.ID_EXPR__SUBELEMENTS, newSubelements, newSubelements));
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
      case ResolutePackage.ID_EXPR__SUBELEMENTS:
        return basicSetSubelements(null, msgs);
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
      case ResolutePackage.ID_EXPR__ID:
        if (resolve) return getId();
        return basicGetId();
      case ResolutePackage.ID_EXPR__SUBELEMENTS:
        return getSubelements();
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
      case ResolutePackage.ID_EXPR__ID:
        setId((NamedElement)newValue);
        return;
      case ResolutePackage.ID_EXPR__SUBELEMENTS:
        setSubelements((ElementSet)newValue);
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
      case ResolutePackage.ID_EXPR__ID:
        setId((NamedElement)null);
        return;
      case ResolutePackage.ID_EXPR__SUBELEMENTS:
        setSubelements((ElementSet)null);
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
      case ResolutePackage.ID_EXPR__ID:
        return id != null;
      case ResolutePackage.ID_EXPR__SUBELEMENTS:
        return subelements != null;
    }
    return super.eIsSet(featureID);
  }

} //IdExprImpl
