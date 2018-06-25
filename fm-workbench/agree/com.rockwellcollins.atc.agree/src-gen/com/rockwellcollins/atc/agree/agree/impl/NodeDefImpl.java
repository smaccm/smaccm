/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl#getRets <em>Rets</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.NodeDefImpl#getNodeBody <em>Node Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeDefImpl extends NamedElementImpl implements NodeDef
{
  /**
   * The cached value of the '{@link #getArgs() <em>Args</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgs()
   * @generated
   * @ordered
   */
  protected EList<Arg> args;

  /**
   * The cached value of the '{@link #getRets() <em>Rets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRets()
   * @generated
   * @ordered
   */
  protected EList<Arg> rets;

  /**
   * The cached value of the '{@link #getNodeBody() <em>Node Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNodeBody()
   * @generated
   * @ordered
   */
  protected NodeBodyExpr nodeBody;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeDefImpl()
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
    return AgreePackage.Literals.NODE_DEF;
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
      args = new EObjectContainmentEList<Arg>(Arg.class, this, AgreePackage.NODE_DEF__ARGS);
    }
    return args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Arg> getRets()
  {
    if (rets == null)
    {
      rets = new EObjectContainmentEList<Arg>(Arg.class, this, AgreePackage.NODE_DEF__RETS);
    }
    return rets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeBodyExpr getNodeBody()
  {
    return nodeBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNodeBody(NodeBodyExpr newNodeBody, NotificationChain msgs)
  {
    NodeBodyExpr oldNodeBody = nodeBody;
    nodeBody = newNodeBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AgreePackage.NODE_DEF__NODE_BODY, oldNodeBody, newNodeBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNodeBody(NodeBodyExpr newNodeBody)
  {
    if (newNodeBody != nodeBody)
    {
      NotificationChain msgs = null;
      if (nodeBody != null)
        msgs = ((InternalEObject)nodeBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NODE_DEF__NODE_BODY, null, msgs);
      if (newNodeBody != null)
        msgs = ((InternalEObject)newNodeBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AgreePackage.NODE_DEF__NODE_BODY, null, msgs);
      msgs = basicSetNodeBody(newNodeBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NODE_DEF__NODE_BODY, newNodeBody, newNodeBody));
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
      case AgreePackage.NODE_DEF__ARGS:
        return ((InternalEList<?>)getArgs()).basicRemove(otherEnd, msgs);
      case AgreePackage.NODE_DEF__RETS:
        return ((InternalEList<?>)getRets()).basicRemove(otherEnd, msgs);
      case AgreePackage.NODE_DEF__NODE_BODY:
        return basicSetNodeBody(null, msgs);
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
      case AgreePackage.NODE_DEF__ARGS:
        return getArgs();
      case AgreePackage.NODE_DEF__RETS:
        return getRets();
      case AgreePackage.NODE_DEF__NODE_BODY:
        return getNodeBody();
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
      case AgreePackage.NODE_DEF__ARGS:
        getArgs().clear();
        getArgs().addAll((Collection<? extends Arg>)newValue);
        return;
      case AgreePackage.NODE_DEF__RETS:
        getRets().clear();
        getRets().addAll((Collection<? extends Arg>)newValue);
        return;
      case AgreePackage.NODE_DEF__NODE_BODY:
        setNodeBody((NodeBodyExpr)newValue);
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
      case AgreePackage.NODE_DEF__ARGS:
        getArgs().clear();
        return;
      case AgreePackage.NODE_DEF__RETS:
        getRets().clear();
        return;
      case AgreePackage.NODE_DEF__NODE_BODY:
        setNodeBody((NodeBodyExpr)null);
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
      case AgreePackage.NODE_DEF__ARGS:
        return args != null && !args.isEmpty();
      case AgreePackage.NODE_DEF__RETS:
        return rets != null && !rets.isEmpty();
      case AgreePackage.NODE_DEF__NODE_BODY:
        return nodeBody != null;
    }
    return super.eIsSet(featureID);
  }

} //NodeDefImpl
