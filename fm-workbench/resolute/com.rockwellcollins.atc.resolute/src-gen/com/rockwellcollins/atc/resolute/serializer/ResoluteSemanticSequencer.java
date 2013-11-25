package com.rockwellcollins.atc.resolute.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltinType;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ClaimString;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.ElementSets;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FuncBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.ResoluteLibrary;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;
import com.rockwellcollins.atc.resolute.resolute.SetType;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
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

@SuppressWarnings("all")
public class ResoluteSemanticSequencer extends PropertiesSemanticSequencer {

	@Inject
	private ResoluteGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == Aadl2Package.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case Aadl2Package.ARRAY_RANGE:
				if(context == grammarAccess.getArrayRangeRule()) {
					sequence_ArrayRange(context, (ArrayRange) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.BASIC_PROPERTY_ASSOCIATION:
				if(context == grammarAccess.getFieldPropertyAssociationRule()) {
					sequence_FieldPropertyAssociation(context, (BasicPropertyAssociation) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.BOOLEAN_LITERAL:
				if(context == grammarAccess.getBooleanLiteralRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_BooleanLiteral(context, (BooleanLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.CLASSIFIER_VALUE:
				if(context == grammarAccess.getComponentClassifierTermRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_ComponentClassifierTerm(context, (ClassifierValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.COMPUTED_VALUE:
				if(context == grammarAccess.getComputedTermRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_ComputedTerm(context, (ComputedValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.CONTAINED_NAMED_ELEMENT:
				if(context == grammarAccess.getContainmentPathRule()) {
					sequence_ContainmentPath(context, (ContainedNamedElement) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.CONTAINMENT_PATH_ELEMENT:
				if(context == grammarAccess.getContainmentPathElementRule()) {
					sequence_ContainmentPathElement(context, (ContainmentPathElement) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getQualifiedContainmentPathElementRule()) {
					sequence_QualifiedContainmentPathElement(context, (ContainmentPathElement) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.INTEGER_LITERAL:
				if(context == grammarAccess.getIntegerTermRule() ||
				   context == grammarAccess.getNumAltRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_IntegerTerm(context, (IntegerLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.LIST_VALUE:
				if(context == grammarAccess.getListTermRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_ListTerm(context, (ListValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.MODAL_PROPERTY_VALUE:
				if(context == grammarAccess.getModalPropertyValueRule()) {
					sequence_ModalPropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getOptionalModalPropertyValueRule()) {
					sequence_OptionalModalPropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPropertyValueRule()) {
					sequence_PropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.NAMED_VALUE:
				if(context == grammarAccess.getConstantValueRule() ||
				   context == grammarAccess.getNumAltRule()) {
					sequence_ConstantValue(context, (NamedValue) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getLiteralorReferenceTermRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_LiteralorReferenceTerm(context, (NamedValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.OPERATION:
				if(context == grammarAccess.getNumAltRule() ||
				   context == grammarAccess.getSignedConstantRule()) {
					sequence_SignedConstant(context, (Operation) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.PROPERTY_ASSOCIATION:
				if(context == grammarAccess.getBasicPropertyAssociationRule()) {
					sequence_BasicPropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getContainedPropertyAssociationRule() ||
				   context == grammarAccess.getPModelRule()) {
					sequence_ContainedPropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPropertyAssociationRule()) {
					sequence_PropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.RANGE_VALUE:
				if(context == grammarAccess.getNumericRangeTermRule() ||
				   context == grammarAccess.getPropertyExpressionRule()) {
					sequence_NumericRangeTerm(context, (RangeValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.REAL_LITERAL:
				if(context == grammarAccess.getNumAltRule() ||
				   context == grammarAccess.getPropertyExpressionRule() ||
				   context == grammarAccess.getRealTermRule()) {
					sequence_RealTerm(context, (RealLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.RECORD_VALUE:
				if(context == grammarAccess.getOldRecordTermRule()) {
					sequence_OldRecordTerm(context, (RecordValue) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPropertyExpressionRule() ||
				   context == grammarAccess.getRecordTermRule()) {
					sequence_RecordTerm(context, (RecordValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.REFERENCE_VALUE:
				if(context == grammarAccess.getPropertyExpressionRule() ||
				   context == grammarAccess.getReferenceTermRule()) {
					sequence_ReferenceTerm(context, (ReferenceValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.STRING_LITERAL:
				if(context == grammarAccess.getPropertyExpressionRule() ||
				   context == grammarAccess.getStringTermRule()) {
					sequence_StringTerm(context, (StringLiteral) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == ResolutePackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ResolutePackage.ARG:
				if(context == grammarAccess.getArgRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_Arg(context, (Arg) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BINARY_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AndExpr_ImpliesExpr_OrExpr_PlusExpr_RelationalExpr_SetOpExpr_TimesExpr(context, (BinaryExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BOOL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (BoolExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (BuiltInFuncCallExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BUILTIN_TYPE:
				if(context == grammarAccess.getBuiltinTypeRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getTypeRule()) {
					sequence_BuiltinType(context, (BuiltinType) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CLAIM_ARG:
				if(context == grammarAccess.getClaimStringRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_ClaimString(context, (ClaimArg) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CLAIM_BODY:
				if(context == grammarAccess.getDefinitionBodyRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_DefinitionBody(context, (ClaimBody) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CLAIM_STRING:
				if(context == grammarAccess.getClaimStringRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_ClaimString(context, (ClaimString) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CONSTANT_DEFINITION:
				if(context == grammarAccess.getConstantDefinitionRule() ||
				   context == grammarAccess.getDefinitionRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_ConstantDefinition(context, (ConstantDefinition) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.ELEMENT_SETS:
				if(context == grammarAccess.getElementSetsRule()) {
					sequence_ElementSets(context, (ElementSets) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FAIL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (FailExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FILTER_MAP_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (FilterMapExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FN_CALL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (FnCallExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FUNC_BODY:
				if(context == grammarAccess.getDefinitionBodyRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_DefinitionBody(context, (FuncBody) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FUNCTION_DEFINITION:
				if(context == grammarAccess.getDefinitionRule() ||
				   context == grammarAccess.getFunctionDefinitionRule() ||
				   context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getNamespaceRule()) {
					sequence_FunctionDefinition(context, (FunctionDefinition) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.ID_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (IdExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.IF_THEN_ELSE_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (IfThenElseExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.INT_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (IntExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.NESTED_DOT_ID:
				if(context == grammarAccess.getNestedDotIDRule()) {
					sequence_NestedDotID(context, (NestedDotID) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.PROVE_STATEMENT:
				if(context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getProveStatementRule()) {
					sequence_ProveStatement(context, (ProveStatement) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.QUANT_ARG:
				if(context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getQuantArgRule()) {
					sequence_QuantArg(context, (QuantArg) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.QUANTIFIED_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (QuantifiedExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.REAL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (RealExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.RESOLUTE_LIBRARY:
				if(context == grammarAccess.getAnnexLibraryRule() ||
				   context == grammarAccess.getResoluteLibraryRule()) {
					sequence_ResoluteLibrary(context, (ResoluteLibrary) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.RESOLUTE_SUBCLAUSE:
				if(context == grammarAccess.getAnnexSubclauseRule() ||
				   context == grammarAccess.getResoluteSubclauseRule()) {
					sequence_ResoluteSubclause(context, (ResoluteSubclause) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.SET_TYPE:
				if(context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getNamedElementRule() ||
				   context == grammarAccess.getTypeRule()) {
					sequence_Type(context, (SetType) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.STRING_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (StringExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.THIS_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (ThisExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.UNARY_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getSetOpExprRule() ||
				   context == grammarAccess.getSetOpExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_PrefixExpr(context, (UnaryExpr) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         (left=TimesExpr_BinaryExpr_1_0_0_0 (op='*' | op='/') right=SetOpExpr) | 
	 *         (left=SetOpExpr_BinaryExpr_1_0_0_0 (op='union' | op='intersect') right=PrefixExpr) | 
	 *         (left=PlusExpr_BinaryExpr_1_0_0_0 (op='+' | op='-') right=TimesExpr) | 
	 *         (left=RelationalExpr_BinaryExpr_1_0_0_0 op=RelationalOp right=PlusExpr) | 
	 *         (left=AndExpr_BinaryExpr_1_0_0_0 op='and' right=RelationalExpr) | 
	 *         (left=OrExpr_BinaryExpr_1_0_0_0 op='or' right=AndExpr) | 
	 *         (left=ImpliesExpr_BinaryExpr_1_0_0_0 op='=>' right=ImpliesExpr)
	 *     )
	 */
	protected void sequence_AndExpr_ImpliesExpr_OrExpr_PlusExpr_RelationalExpr_SetOpExpr_TimesExpr(EObject context, BinaryExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=Type)
	 */
	protected void sequence_Arg(EObject context, Arg semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val=BooleanLiteral
	 */
	protected void sequence_AtomicExpr(EObject context, BoolExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (fn=BuiltInFunc (args+=Expr args+=Expr*)?)
	 */
	protected void sequence_AtomicExpr(EObject context, BuiltInFuncCallExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (val=StringTerm?)
	 */
	protected void sequence_AtomicExpr(EObject context, FailExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (map=Expr args+=Arg+ filter=Expr?)
	 */
	protected void sequence_AtomicExpr(EObject context, FilterMapExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (fn=[FunctionDefinition|ID] (args+=Expr args+=Expr*)?)
	 */
	protected void sequence_AtomicExpr(EObject context, FnCallExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=[NamedElement|QCREF] subelements=ElementSets?)
	 */
	protected void sequence_AtomicExpr(EObject context, IdExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (cond=Expr then=Expr else=Expr)
	 */
	protected void sequence_AtomicExpr(EObject context, IfThenElseExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val=IntegerTerm
	 */
	protected void sequence_AtomicExpr(EObject context, IntExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((quant='forall' | quant='exists') args+=QuantArg+ expr=Expr)
	 */
	protected void sequence_AtomicExpr(EObject context, QuantifiedExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val=RealTerm
	 */
	protected void sequence_AtomicExpr(EObject context, RealExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     val=StringTerm
	 */
	protected void sequence_AtomicExpr(EObject context, StringExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (sub=NestedDotID?)
	 */
	protected void sequence_AtomicExpr(EObject context, ThisExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name='data' | 
	 *         name='thread' | 
	 *         name='thread_group' | 
	 *         name='process' | 
	 *         name='subprogram' | 
	 *         name='subprogram_group' | 
	 *         name='processor' | 
	 *         name='virtual_processor' | 
	 *         name='memory' | 
	 *         name='bus' | 
	 *         name='virtual_bus' | 
	 *         name='device' | 
	 *         name='system' | 
	 *         name='abstract' | 
	 *         name='connection' | 
	 *         name='component' | 
	 *         name='string' | 
	 *         name='int' | 
	 *         name='real' | 
	 *         name='bool'
	 *     )
	 */
	protected void sequence_BuiltinType(EObject context, BuiltinType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     arg=[Arg|ID]
	 */
	protected void sequence_ClaimString(EObject context, ClaimArg semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     str=STRING
	 */
	protected void sequence_ClaimString(EObject context, ClaimString semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=Type expr=Expr)
	 */
	protected void sequence_ConstantDefinition(EObject context, ConstantDefinition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (claim+=ClaimString* expr=Expr)
	 */
	protected void sequence_DefinitionBody(EObject context, ClaimBody semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=Type expr=Expr)
	 */
	protected void sequence_DefinitionBody(EObject context, FuncBody semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name='threads' | 
	 *         name='data' | 
	 *         name='memory' | 
	 *         name='memories' | 
	 *         name='thread_groups' | 
	 *         name='processes' | 
	 *         name='subprograms' | 
	 *         name='subprogram_groups' | 
	 *         name='processors' | 
	 *         name='virtual_processors' | 
	 *         name='buses' | 
	 *         name='virtual_buses' | 
	 *         name='devices' | 
	 *         name='systems' | 
	 *         name='abstracts' | 
	 *         name='connections' | 
	 *         name='components'
	 *     )
	 */
	protected void sequence_ElementSets(EObject context, ElementSets semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (args+=Arg args+=Arg*)? body=DefinitionBody)
	 */
	protected void sequence_FunctionDefinition(EObject context, FunctionDefinition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=UnsignedInt unit=[UnitLiteral|ID]?)
	 */
	protected void sequence_IntegerTerm(EObject context, IntegerLiteral semanticObject) {
		genericSequencer.createSequence(context, (EObject)semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (base=[NamedElement|ID] sub=NestedDotID?)
	 */
	protected void sequence_NestedDotID(EObject context, NestedDotID semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((op='-' | op='not') expr=PrefixExpr)
	 */
	protected void sequence_PrefixExpr(EObject context, UnaryExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expr=Expr (modes+=NestedDotID modes+=NestedDotID*)?)
	 */
	protected void sequence_ProveStatement(EObject context, ProveStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID expr=Expr)
	 */
	protected void sequence_QuantArg(EObject context, QuantArg semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=UnsignedReal unit=[UnitLiteral|ID]?)
	 */
	protected void sequence_RealTerm(EObject context, RealLiteral semanticObject) {
		genericSequencer.createSequence(context, (EObject)semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (definitions+=Definition*)
	 */
	protected void sequence_ResoluteLibrary(EObject context, ResoluteLibrary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (proves+=ProveStatement*)
	 */
	protected void sequence_ResoluteSubclause(EObject context, ResoluteSubclause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     type=Type
	 */
	protected void sequence_Type(EObject context, SetType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
