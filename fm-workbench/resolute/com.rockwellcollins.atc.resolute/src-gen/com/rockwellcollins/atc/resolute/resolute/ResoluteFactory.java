/**
 */
package com.rockwellcollins.atc.resolute.resolute;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see com.rockwellcollins.atc.resolute.resolute.ResolutePackage
 * @generated
 */
public interface ResoluteFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ResoluteFactory eINSTANCE = com.rockwellcollins.atc.resolute.resolute.impl.ResoluteFactoryImpl
            .init();

    /**
     * Returns a new object of class '<em>Library</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Library</em>'.
     * @generated
     */
    ResoluteLibrary createResoluteLibrary();

    /**
     * Returns a new object of class '<em>Definition</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Definition</em>'.
     * @generated
     */
    Definition createDefinition();

    /**
     * Returns a new object of class '<em>Type</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Type</em>'.
     * @generated
     */
    Type createType();

    /**
     * Returns a new object of class '<em>Builtin Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Builtin Type</em>'.
     * @generated
     */
    BuiltinType createBuiltinType();

    /**
     * Returns a new object of class '<em>Arg</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Arg</em>'.
     * @generated
     */
    Arg createArg();

    /**
     * Returns a new object of class '<em>Constant Definition</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Constant Definition</em>'.
     * @generated
     */
    ConstantDefinition createConstantDefinition();

    /**
     * Returns a new object of class '<em>Function Definition</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Function Definition</em>'.
     * @generated
     */
    FunctionDefinition createFunctionDefinition();

    /**
     * Returns a new object of class '<em>Claim String</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Claim String</em>'.
     * @generated
     */
    ClaimString createClaimString();

    /**
     * Returns a new object of class '<em>Definition Body</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Definition Body</em>'.
     * @generated
     */
    DefinitionBody createDefinitionBody();

    /**
     * Returns a new object of class '<em>Expr</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Expr</em>'.
     * @generated
     */
    Expr createExpr();

    /**
     * Returns a new object of class '<em>Subclause</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Subclause</em>'.
     * @generated
     */
    ResoluteSubclause createResoluteSubclause();

    /**
     * Returns a new object of class '<em>Prove Statement</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Prove Statement</em>'.
     * @generated
     */
    ProveStatement createProveStatement();

    /**
     * Returns a new object of class '<em>Set Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Set Type</em>'.
     * @generated
     */
    SetType createSetType();

    /**
     * Returns a new object of class '<em>Claim Arg</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Claim Arg</em>'.
     * @generated
     */
    ClaimArg createClaimArg();

    /**
     * Returns a new object of class '<em>Func Body</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Func Body</em>'.
     * @generated
     */
    FuncBody createFuncBody();

    /**
     * Returns a new object of class '<em>Claim Body</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Claim Body</em>'.
     * @generated
     */
    ClaimBody createClaimBody();

    /**
     * Returns a new object of class '<em>Binary Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Binary Expr</em>'.
     * @generated
     */
    BinaryExpr createBinaryExpr();

    /**
     * Returns a new object of class '<em>Unary Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Unary Expr</em>'.
     * @generated
     */
    UnaryExpr createUnaryExpr();

    /**
     * Returns a new object of class '<em>Id Expr</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Id Expr</em>'.
     * @generated
     */
    IdExpr createIdExpr();

    /**
     * Returns a new object of class '<em>This Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>This Expr</em>'.
     * @generated
     */
    ThisExpr createThisExpr();

    /**
     * Returns a new object of class '<em>Fail Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Fail Expr</em>'.
     * @generated
     */
    FailExpr createFailExpr();

    /**
     * Returns a new object of class '<em>Int Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Int Expr</em>'.
     * @generated
     */
    IntExpr createIntExpr();

    /**
     * Returns a new object of class '<em>Real Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Real Expr</em>'.
     * @generated
     */
    RealExpr createRealExpr();

    /**
     * Returns a new object of class '<em>Bool Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Bool Expr</em>'.
     * @generated
     */
    BoolExpr createBoolExpr();

    /**
     * Returns a new object of class '<em>String Expr</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>String Expr</em>'.
     * @generated
     */
    StringExpr createStringExpr();

    /**
     * Returns a new object of class '<em>If Then Else Expr</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>If Then Else Expr</em>'.
     * @generated
     */
    IfThenElseExpr createIfThenElseExpr();

    /**
     * Returns a new object of class '<em>Quantified Expr</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Quantified Expr</em>'.
     * @generated
     */
    QuantifiedExpr createQuantifiedExpr();

    /**
     * Returns a new object of class '<em>Built In Func Call Expr</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Built In Func Call Expr</em>'.
     * @generated
     */
    BuiltInFuncCallExpr createBuiltInFuncCallExpr();

    /**
     * Returns a new object of class '<em>Fn Call Expr</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Fn Call Expr</em>'.
     * @generated
     */
    FnCallExpr createFnCallExpr();

    /**
     * Returns a new object of class '<em>Filter Map Expr</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Filter Map Expr</em>'.
     * @generated
     */
    FilterMapExpr createFilterMapExpr();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ResolutePackage getResolutePackage();

} // ResoluteFactory
