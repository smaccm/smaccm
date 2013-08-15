package com.rockwellcollins.atc.agree.serializer;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.ArrayRange;
import org.osate.aadl2.BasicPropertyAssociation;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.ClassifierValue;
import org.osate.aadl2.ComputedValue;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.ContainmentPathElement;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Operation;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.RecordValue;
import org.osate.aadl2.ReferenceValue;
import org.osate.aadl2.StringLiteral;
import org.osate.xtext.aadl2.properties.serializer.PropertiesSemanticSequencer;

import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
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
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;

@SuppressWarnings("all")
public class AgreeSemanticSequencer extends PropertiesSemanticSequencer {

    @Inject
    private AgreeGrammarAccess grammarAccess;

    public void createSequence(EObject context, EObject semanticObject) {
        if (semanticObject.eClass().getEPackage() == Aadl2Package.eINSTANCE) {
            switch (semanticObject.eClass().getClassifierID()) {
            case Aadl2Package.ARRAY_RANGE:
                if (context == grammarAccess.getArrayRangeRule()) {
                    sequence_ArrayRange(context, (ArrayRange) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.BASIC_PROPERTY_ASSOCIATION:
                if (context == grammarAccess.getFieldPropertyAssociationRule()) {
                    sequence_FieldPropertyAssociation(context,
                            (BasicPropertyAssociation) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.BOOLEAN_LITERAL:
                if (context == grammarAccess.getBooleanLiteralRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_BooleanLiteral(context, (BooleanLiteral) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.CLASSIFIER_VALUE:
                if (context == grammarAccess.getComponentClassifierTermRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_ComponentClassifierTerm(context, (ClassifierValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.COMPUTED_VALUE:
                if (context == grammarAccess.getComputedTermRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_ComputedTerm(context, (ComputedValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.CONTAINED_NAMED_ELEMENT:
                if (context == grammarAccess.getContainmentPathRule()) {
                    sequence_ContainmentPath(context, (ContainedNamedElement) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.CONTAINMENT_PATH_ELEMENT:
                if (context == grammarAccess.getContainmentPathElementRule()) {
                    sequence_ContainmentPathElement(context,
                            (ContainmentPathElement) semanticObject);
                    return;
                } else if (context == grammarAccess.getQualifiedContainmentPathElementRule()) {
                    sequence_QualifiedContainmentPathElement(context,
                            (ContainmentPathElement) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.INTEGER_LITERAL:
                if (context == grammarAccess.getIntegerTermRule()
                        || context == grammarAccess.getNumAltRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_IntegerTerm(context, (IntegerLiteral) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.LIST_VALUE:
                if (context == grammarAccess.getListTermRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_ListTerm(context, (ListValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.MODAL_PROPERTY_VALUE:
                if (context == grammarAccess.getModalPropertyValueRule()) {
                    sequence_ModalPropertyValue(context, (ModalPropertyValue) semanticObject);
                    return;
                } else if (context == grammarAccess.getOptionalModalPropertyValueRule()) {
                    sequence_OptionalModalPropertyValue(context,
                            (ModalPropertyValue) semanticObject);
                    return;
                } else if (context == grammarAccess.getPropertyValueRule()) {
                    sequence_PropertyValue(context, (ModalPropertyValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.NAMED_VALUE:
                if (context == grammarAccess.getConstantValueRule()
                        || context == grammarAccess.getNumAltRule()) {
                    sequence_ConstantValue(context, (NamedValue) semanticObject);
                    return;
                } else if (context == grammarAccess.getLiteralorReferenceTermRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_LiteralorReferenceTerm(context, (NamedValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.OPERATION:
                if (context == grammarAccess.getNumAltRule()
                        || context == grammarAccess.getSignedConstantRule()) {
                    sequence_SignedConstant(context, (Operation) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.PROPERTY_ASSOCIATION:
                if (context == grammarAccess.getBasicPropertyAssociationRule()) {
                    sequence_BasicPropertyAssociation(context, (PropertyAssociation) semanticObject);
                    return;
                } else if (context == grammarAccess.getContainedPropertyAssociationRule()
                        || context == grammarAccess.getPModelRule()) {
                    sequence_ContainedPropertyAssociation(context,
                            (PropertyAssociation) semanticObject);
                    return;
                } else if (context == grammarAccess.getPropertyAssociationRule()) {
                    sequence_PropertyAssociation(context, (PropertyAssociation) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.RANGE_VALUE:
                if (context == grammarAccess.getNumericRangeTermRule()
                        || context == grammarAccess.getPropertyExpressionRule()) {
                    sequence_NumericRangeTerm(context, (RangeValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.REAL_LITERAL:
                if (context == grammarAccess.getNumAltRule()
                        || context == grammarAccess.getPropertyExpressionRule()
                        || context == grammarAccess.getRealTermRule()) {
                    sequence_RealTerm(context, (RealLiteral) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.RECORD_VALUE:
                if (context == grammarAccess.getOldRecordTermRule()) {
                    sequence_OldRecordTerm(context, (RecordValue) semanticObject);
                    return;
                } else if (context == grammarAccess.getPropertyExpressionRule()
                        || context == grammarAccess.getRecordTermRule()) {
                    sequence_RecordTerm(context, (RecordValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.REFERENCE_VALUE:
                if (context == grammarAccess.getPropertyExpressionRule()
                        || context == grammarAccess.getReferenceTermRule()) {
                    sequence_ReferenceTerm(context, (ReferenceValue) semanticObject);
                    return;
                } else {
                    break;
                }
            case Aadl2Package.STRING_LITERAL:
                if (context == grammarAccess.getPropertyExpressionRule()
                        || context == grammarAccess.getStringTermRule()) {
                    sequence_StringTerm(context, (StringLiteral) semanticObject);
                    return;
                } else {
                    break;
                }
            }
        } else if (semanticObject.eClass().getEPackage() == AgreePackage.eINSTANCE) {
            switch (semanticObject.eClass().getClassifierID()) {
            case AgreePackage.AGREE_CONTRACT:
                if (context == grammarAccess.getAgreeContractRule()
                        || context == grammarAccess.getElementRule()) {
                    sequence_AgreeContract(context, (AgreeContract) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.AGREE_CONTRACT_LIBRARY:
                if (context == grammarAccess.getAgreeLibraryRule()
                        || context == grammarAccess.getAnnexLibraryRule()) {
                    sequence_AgreeLibrary(context, (AgreeContractLibrary) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.AGREE_CONTRACT_SUBCLAUSE:
                if (context == grammarAccess.getAgreeSubclauseRule()
                        || context == grammarAccess.getAnnexSubclauseRule()) {
                    sequence_AgreeSubclause(context, (AgreeContractSubclause) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.ARG:
                if (context == grammarAccess.getArgRule()
                        || context == grammarAccess.getNamedElementRule()) {
                    sequence_Arg(context, (Arg) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.ASSERT_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_SpecStatement(context, (AssertStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.ASSUME_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_SpecStatement(context, (AssumeStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.BINARY_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_AddSubExpr_AndExpr_ArrowExpr_EquivExpr_ImpliesExpr_MultDivExpr_OrExpr_RelateExpr(
                            context, (BinaryExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.BOOL_LIT_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (BoolLitExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.CONST_STATEMENT:
                if (context == grammarAccess.getConstStatementRule()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_ConstStatement(context, (ConstStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.EQ_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEqStatementRule()
                        || context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_EqStatement(context, (EqStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.FN_CALL_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (FnCallExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.FN_DEF_EXPR:
                if (context == grammarAccess.getCallDefRule()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getFnDefExprRule()
                        || context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_FnDefExpr(context, (FnDefExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.GET_PROPERTY_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_PreDefFnExpr(context, (GetPropertyExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.GUARANTEE_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_SpecStatement(context, (GuaranteeStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.ID_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (IdExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.IF_THEN_ELSE_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_IfThenElseExpr(context, (IfThenElseExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.INT_LIT_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (IntLitExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NEST_ID_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (NestIdExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NESTED_DOT_ID:
                if (context == grammarAccess.getNestedDotIDRule()) {
                    sequence_NestedDotID(context, (NestedDotID) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NEXT_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_PreDefFnExpr(context, (NextExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NODE_BODY_EXPR:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getNodeBodyExprRule()) {
                    sequence_NodeBodyExpr(context, (NodeBodyExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NODE_DEF_EXPR:
                if (context == grammarAccess.getCallDefRule()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getNodeDefExprRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_NodeDefExpr(context, (NodeDefExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.NODE_EQ:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getNodeEqRule()) {
                    sequence_NodeEq(context, (NodeEq) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.PARAM_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_SpecStatement(context, (ParamStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.PRE_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (PreExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.PREV_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_PreDefFnExpr(context, (PrevExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.PROPERTY_STATEMENT:
                if (context == grammarAccess.getElementRule()
                        || context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getPropertyStatementRule()
                        || context == grammarAccess.getSpecStatementRule()) {
                    sequence_PropertyStatement(context, (PropertyStatement) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.REAL_LIT_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (RealLitExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.THIS_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_TermExpr(context, (ThisExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.TYPE:
                if (context == grammarAccess.getNamedElementRule()
                        || context == grammarAccess.getTypeRule()) {
                    sequence_Type(context, (Type) semanticObject);
                    return;
                } else {
                    break;
                }
            case AgreePackage.UNARY_EXPR:
                if (context == grammarAccess.getAddSubExprRule()
                        || context == grammarAccess.getAddSubExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getAndExprRule()
                        || context == grammarAccess.getAndExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getArrowExprRule()
                        || context == grammarAccess.getArrowExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getElementRule()
                        || context == grammarAccess.getEquivExprRule()
                        || context == grammarAccess.getEquivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getExprRule()
                        || context == grammarAccess.getIfThenElseExprRule()
                        || context == grammarAccess.getImpliesExprRule()
                        || context == grammarAccess.getImpliesExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getMultDivExprRule()
                        || context == grammarAccess.getMultDivExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getOrExprRule()
                        || context == grammarAccess.getOrExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getPreDefFnExprRule()
                        || context == grammarAccess.getRelateExprRule()
                        || context == grammarAccess.getRelateExprAccess()
                                .getBinaryExprLeftAction_1_0_0_0()
                        || context == grammarAccess.getTermExprRule()
                        || context == grammarAccess.getUnaryExprRule()) {
                    sequence_UnaryExpr(context, (UnaryExpr) semanticObject);
                    return;
                } else {
                    break;
                }
            }
        }
        if (errorAcceptor != null) {
            errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(
                    semanticObject, context));
        }
    }

    /**
     * Constraint: ( (left=AddSubExpr_BinaryExpr_1_0_0_0 (op='+' | op='-')
     * right=MultDivExpr) | (left=MultDivExpr_BinaryExpr_1_0_0_0 (op='*' |
     * op='/' | op='mod' | op='div') right=UnaryExpr) |
     * (left=RelateExpr_BinaryExpr_1_0_0_0 op=RelateOp right=AddSubExpr) |
     * (left=OrExpr_BinaryExpr_1_0_0_0 op='or' right=RelateExpr) |
     * (left=AndExpr_BinaryExpr_1_0_0_0 op='and' right=OrExpr) |
     * (left=EquivExpr_BinaryExpr_1_0_0_0 op='<=>' right=AndExpr) |
     * (left=ImpliesExpr_BinaryExpr_1_0_0_0 op='=>' right=EquivExpr) |
     * (left=ArrowExpr_BinaryExpr_1_0_0_0 op='->' right=ImpliesExpr) )
     */
    protected void sequence_AddSubExpr_AndExpr_ArrowExpr_EquivExpr_ImpliesExpr_MultDivExpr_OrExpr_RelateExpr(
            EObject context, BinaryExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: specs+=SpecStatement+
     */
    protected void sequence_AgreeContract(EObject context, AgreeContract semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: contract=AgreeContract
     */
    protected void sequence_AgreeLibrary(EObject context, AgreeContractLibrary semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: contract=AgreeContract
     */
    protected void sequence_AgreeSubclause(EObject context, AgreeContractSubclause semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=ID type=Type)
     */
    protected void sequence_Arg(EObject context, Arg semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=ID type=Type expr=Expr)
     */
    protected void sequence_ConstStatement(EObject context, ConstStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (args+=Arg args+=Arg* expr=Expr)
     */
    protected void sequence_EqStatement(EObject context, EqStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=ID args+=Arg args+=Arg* type=Type expr=Expr)
     */
    protected void sequence_FnDefExpr(EObject context, FnDefExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (a=Expr b=Expr c=Expr)
     */
    protected void sequence_IfThenElseExpr(EObject context, IfThenElseExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=[NamedElement|ID] subName=NestedDotID?)
     */
    protected void sequence_NestedDotID(EObject context, NestedDotID semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (locs+=Arg* eqs+=NodeEq+)
     */
    protected void sequence_NodeBodyExpr(EObject context, NodeBodyExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: ( name=ID args+=Arg args+=Arg* rets+=Arg rets+=Arg*
     * nodeBody=NodeBodyExpr )
     */
    protected void sequence_NodeDefExpr(EObject context, NodeDefExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (names+=[Arg|ID] names+=[Arg|ID]* expr=Expr)
     */
    protected void sequence_NodeEq(EObject context, NodeEq semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (component=Expr name=Expr)
     */
    protected void sequence_PreDefFnExpr(EObject context, GetPropertyExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: expr=Expr
     */
    protected void sequence_PreDefFnExpr(EObject context, NextExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (delay=Expr init=Expr)
     */
    protected void sequence_PreDefFnExpr(EObject context, PrevExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=ID expr=Expr)
     */
    protected void sequence_PropertyStatement(EObject context, PropertyStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: expr=Expr
     */
    protected void sequence_SpecStatement(EObject context, AssertStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: expr=Expr
     */
    protected void sequence_SpecStatement(EObject context, AssumeStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: expr=Expr
     */
    protected void sequence_SpecStatement(EObject context, GuaranteeStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (expr=Expr type=Type)
     */
    protected void sequence_SpecStatement(EObject context, ParamStatement semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: val=BooleanLiteral
     */
    protected void sequence_TermExpr(EObject context, BoolLitExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (fn=[CallDef|ID] args+=Expr args+=Expr*)
     */
    protected void sequence_TermExpr(EObject context, FnCallExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: id=[NamedElement|QID]
     */
    protected void sequence_TermExpr(EObject context, IdExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: val=INTEGER_LIT
     */
    protected void sequence_TermExpr(EObject context, IntLitExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: id=NestedDotID
     */
    protected void sequence_TermExpr(EObject context, NestIdExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: expr=Expr
     */
    protected void sequence_TermExpr(EObject context, PreExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: val=REAL_LIT
     */
    protected void sequence_TermExpr(EObject context, RealLitExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: {ThisExpr}
     */
    protected void sequence_TermExpr(EObject context, ThisExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name='real' | name='bool' | name='int')
     */
    protected void sequence_Type(EObject context, Type semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: ((op='-' | op='not') expr=UnaryExpr)
     */
    protected void sequence_UnaryExpr(EObject context, UnaryExpr semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }
}
