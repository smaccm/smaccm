/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.IdExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.NestIdExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NextExpr;
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.ParamStatement;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class AgreeFactoryImpl extends EFactoryImpl implements AgreeFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static AgreeFactory init() {
        try {
            AgreeFactory theAgreeFactory = (AgreeFactory) EPackage.Registry.INSTANCE
                    .getEFactory("http://www.rockwellcollins.com/atc/agree/Agree");
            if (theAgreeFactory != null) {
                return theAgreeFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new AgreeFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public AgreeFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case AgreePackage.AGREE_LIBRARY:
            return createAgreeLibrary();
        case AgreePackage.AGREE_SUBCLAUSE:
            return createAgreeSubclause();
        case AgreePackage.CONTRACT:
            return createContract();
        case AgreePackage.SPEC_STATEMENT:
            return createSpecStatement();
        case AgreePackage.CALL_DEF:
            return createCallDef();
        case AgreePackage.PROPERTY_STATEMENT:
            return createPropertyStatement();
        case AgreePackage.CONST_STATEMENT:
            return createConstStatement();
        case AgreePackage.EQ_STATEMENT:
            return createEqStatement();
        case AgreePackage.FN_DEF_EXPR:
            return createFnDefExpr();
        case AgreePackage.NODE_DEF_EXPR:
            return createNodeDefExpr();
        case AgreePackage.NODE_BODY_EXPR:
            return createNodeBodyExpr();
        case AgreePackage.NODE_EQ:
            return createNodeEq();
        case AgreePackage.ARG:
            return createArg();
        case AgreePackage.TYPE:
            return createType();
        case AgreePackage.EXPR:
            return createExpr();
        case AgreePackage.NESTED_DOT_ID:
            return createNestedDotID();
        case AgreePackage.AGREE_CONTRACT_LIBRARY:
            return createAgreeContractLibrary();
        case AgreePackage.AGREE_CONTRACT_SUBCLAUSE:
            return createAgreeContractSubclause();
        case AgreePackage.AGREE_CONTRACT:
            return createAgreeContract();
        case AgreePackage.ASSUME_STATEMENT:
            return createAssumeStatement();
        case AgreePackage.GUARANTEE_STATEMENT:
            return createGuaranteeStatement();
        case AgreePackage.ASSERT_STATEMENT:
            return createAssertStatement();
        case AgreePackage.PARAM_STATEMENT:
            return createParamStatement();
        case AgreePackage.BINARY_EXPR:
            return createBinaryExpr();
        case AgreePackage.UNARY_EXPR:
            return createUnaryExpr();
        case AgreePackage.IF_THEN_ELSE_EXPR:
            return createIfThenElseExpr();
        case AgreePackage.PREV_EXPR:
            return createPrevExpr();
        case AgreePackage.NEXT_EXPR:
            return createNextExpr();
        case AgreePackage.GET_PROPERTY_EXPR:
            return createGetPropertyExpr();
        case AgreePackage.ID_EXPR:
            return createIdExpr();
        case AgreePackage.NEST_ID_EXPR:
            return createNestIdExpr();
        case AgreePackage.INT_LIT_EXPR:
            return createIntLitExpr();
        case AgreePackage.PRE_EXPR:
            return createPreExpr();
        case AgreePackage.FN_CALL_EXPR:
            return createFnCallExpr();
        case AgreePackage.REAL_LIT_EXPR:
            return createRealLitExpr();
        case AgreePackage.BOOL_LIT_EXPR:
            return createBoolLitExpr();
        case AgreePackage.THIS_EXPR:
            return createThisExpr();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName()
                    + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreeLibrary createAgreeLibrary() {
        AgreeLibraryImpl agreeLibrary = new AgreeLibraryImpl();
        return agreeLibrary;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreeSubclause createAgreeSubclause() {
        AgreeSubclauseImpl agreeSubclause = new AgreeSubclauseImpl();
        return agreeSubclause;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Contract createContract() {
        ContractImpl contract = new ContractImpl();
        return contract;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SpecStatement createSpecStatement() {
        SpecStatementImpl specStatement = new SpecStatementImpl();
        return specStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CallDef createCallDef() {
        CallDefImpl callDef = new CallDefImpl();
        return callDef;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PropertyStatement createPropertyStatement() {
        PropertyStatementImpl propertyStatement = new PropertyStatementImpl();
        return propertyStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ConstStatement createConstStatement() {
        ConstStatementImpl constStatement = new ConstStatementImpl();
        return constStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EqStatement createEqStatement() {
        EqStatementImpl eqStatement = new EqStatementImpl();
        return eqStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FnDefExpr createFnDefExpr() {
        FnDefExprImpl fnDefExpr = new FnDefExprImpl();
        return fnDefExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NodeDefExpr createNodeDefExpr() {
        NodeDefExprImpl nodeDefExpr = new NodeDefExprImpl();
        return nodeDefExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NodeBodyExpr createNodeBodyExpr() {
        NodeBodyExprImpl nodeBodyExpr = new NodeBodyExprImpl();
        return nodeBodyExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NodeEq createNodeEq() {
        NodeEqImpl nodeEq = new NodeEqImpl();
        return nodeEq;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Arg createArg() {
        ArgImpl arg = new ArgImpl();
        return arg;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Type createType() {
        TypeImpl type = new TypeImpl();
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Expr createExpr() {
        ExprImpl expr = new ExprImpl();
        return expr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NestedDotID createNestedDotID() {
        NestedDotIDImpl nestedDotID = new NestedDotIDImpl();
        return nestedDotID;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreeContractLibrary createAgreeContractLibrary() {
        AgreeContractLibraryImpl agreeContractLibrary = new AgreeContractLibraryImpl();
        return agreeContractLibrary;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreeContractSubclause createAgreeContractSubclause() {
        AgreeContractSubclauseImpl agreeContractSubclause = new AgreeContractSubclauseImpl();
        return agreeContractSubclause;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreeContract createAgreeContract() {
        AgreeContractImpl agreeContract = new AgreeContractImpl();
        return agreeContract;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AssumeStatement createAssumeStatement() {
        AssumeStatementImpl assumeStatement = new AssumeStatementImpl();
        return assumeStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GuaranteeStatement createGuaranteeStatement() {
        GuaranteeStatementImpl guaranteeStatement = new GuaranteeStatementImpl();
        return guaranteeStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AssertStatement createAssertStatement() {
        AssertStatementImpl assertStatement = new AssertStatementImpl();
        return assertStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ParamStatement createParamStatement() {
        ParamStatementImpl paramStatement = new ParamStatementImpl();
        return paramStatement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BinaryExpr createBinaryExpr() {
        BinaryExprImpl binaryExpr = new BinaryExprImpl();
        return binaryExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public UnaryExpr createUnaryExpr() {
        UnaryExprImpl unaryExpr = new UnaryExprImpl();
        return unaryExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IfThenElseExpr createIfThenElseExpr() {
        IfThenElseExprImpl ifThenElseExpr = new IfThenElseExprImpl();
        return ifThenElseExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PrevExpr createPrevExpr() {
        PrevExprImpl prevExpr = new PrevExprImpl();
        return prevExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NextExpr createNextExpr() {
        NextExprImpl nextExpr = new NextExprImpl();
        return nextExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GetPropertyExpr createGetPropertyExpr() {
        GetPropertyExprImpl getPropertyExpr = new GetPropertyExprImpl();
        return getPropertyExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IdExpr createIdExpr() {
        IdExprImpl idExpr = new IdExprImpl();
        return idExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NestIdExpr createNestIdExpr() {
        NestIdExprImpl nestIdExpr = new NestIdExprImpl();
        return nestIdExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntLitExpr createIntLitExpr() {
        IntLitExprImpl intLitExpr = new IntLitExprImpl();
        return intLitExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PreExpr createPreExpr() {
        PreExprImpl preExpr = new PreExprImpl();
        return preExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FnCallExpr createFnCallExpr() {
        FnCallExprImpl fnCallExpr = new FnCallExprImpl();
        return fnCallExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RealLitExpr createRealLitExpr() {
        RealLitExprImpl realLitExpr = new RealLitExprImpl();
        return realLitExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BoolLitExpr createBoolLitExpr() {
        BoolLitExprImpl boolLitExpr = new BoolLitExprImpl();
        return boolLitExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ThisExpr createThisExpr() {
        ThisExprImpl thisExpr = new ThisExprImpl();
        return thisExpr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AgreePackage getAgreePackage() {
        return (AgreePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static AgreePackage getPackage() {
        return AgreePackage.eINSTANCE;
    }

} // AgreeFactoryImpl
