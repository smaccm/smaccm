package com.rockwellcollins.atc.resolute.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BaseType;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.CastExpr;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ClaimString;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.InstanceOfExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.LetExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.ResoluteLibrary;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;
import com.rockwellcollins.atc.resolute.resolute.SetExpr;
import com.rockwellcollins.atc.resolute.resolute.SetType;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
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
				   context == grammarAccess.getClaimTextVarRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_Arg(context, (Arg) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BASE_TYPE:
				if(context == grammarAccess.getBaseTypeRule() ||
				   context == grammarAccess.getTypeRule()) {
					sequence_BaseType(context, (BaseType) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BINARY_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AndExpr_ImpliesExpr_OrExpr_PlusExpr_RelationalExpr_TimesExpr(context, (BinaryExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BOOL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (BoolExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.BUILT_IN_FN_CALL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (BuiltInFnCallExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CAST_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_PrefixExpr(context, (CastExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CLAIM_ARG:
				if(context == grammarAccess.getClaimTextRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_ClaimText(context, (ClaimArg) semanticObject); 
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
				if(context == grammarAccess.getClaimTextRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_ClaimText(context, (ClaimString) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.CONSTANT_DEFINITION:
				if(context == grammarAccess.getClaimTextVarRule() ||
				   context == grammarAccess.getConstantDefinitionRule() ||
				   context == grammarAccess.getDefinitionRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_ConstantDefinition(context, (ConstantDefinition) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FAIL_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (FnCallExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.FUNCTION_BODY:
				if(context == grammarAccess.getDefinitionBodyRule() ||
				   context == grammarAccess.getElementRule()) {
					sequence_DefinitionBody(context, (FunctionBody) semanticObject); 
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (IfThenElseExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.INSTANCE_OF_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_InstanceOfExpr(context, (InstanceOfExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.INT_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (IntExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.LET_BINDING:
				if(context == grammarAccess.getClaimTextVarRule() ||
				   context == grammarAccess.getLetBindingRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_LetBinding(context, (LetBinding) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.LET_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (LetExpr) semanticObject); 
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
				if(context == grammarAccess.getArgRule() ||
				   context == grammarAccess.getClaimTextVarRule() ||
				   context == grammarAccess.getNamedElementRule()) {
					sequence_Arg(context, (QuantArg) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.QUANTIFIED_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
			case ResolutePackage.SET_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getTimesExprRule() ||
				   context == grammarAccess.getTimesExprAccess().getBinaryExprLeftAction_1_0_0_0()) {
					sequence_AtomicExpr(context, (SetExpr) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.SET_TYPE:
				if(context == grammarAccess.getTypeRule()) {
					sequence_Type(context, (SetType) semanticObject); 
					return; 
				}
				else break;
			case ResolutePackage.STRING_EXPR:
				if(context == grammarAccess.getAndExprRule() ||
				   context == grammarAccess.getAndExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getAtomicExprRule() ||
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
				   context == grammarAccess.getAtomicExprAccess().getFilterMapExprMapAction_11_2_0_0() ||
				   context == grammarAccess.getAtomicExprAccess().getSetExprExprsAction_11_2_1_0() ||
				   context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExprRule() ||
				   context == grammarAccess.getImpliesExprRule() ||
				   context == grammarAccess.getImpliesExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getInstanceOfExprRule() ||
				   context == grammarAccess.getInstanceOfExprAccess().getInstanceOfExprExprAction_1_0_0_0() ||
				   context == grammarAccess.getOrExprRule() ||
				   context == grammarAccess.getOrExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPlusExprRule() ||
				   context == grammarAccess.getPlusExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
				   context == grammarAccess.getPrefixExprRule() ||
				   context == grammarAccess.getRelationalExprRule() ||
				   context == grammarAccess.getRelationalExprAccess().getBinaryExprLeftAction_1_0_0_0() ||
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
	 *         (left=PlusExpr_BinaryExpr_1_0_0_0 (op='+' | op='-') right=TimesExpr) | 
	 *         (left=TimesExpr_BinaryExpr_1_0_0_0 (op='*' | op='/') right=PrefixExpr) | 
	 *         (left=RelationalExpr_BinaryExpr_1_0_0_0 op=RelationalOp right=PlusExpr) | 
	 *         (left=AndExpr_BinaryExpr_1_0_0_0 (op='and' | op='andthen') right=InstanceOfExpr) | 
	 *         (left=OrExpr_BinaryExpr_1_0_0_0 op='or' right=AndExpr) | 
	 *         (left=ImpliesExpr_BinaryExpr_1_0_0_0 op='=>' right=ImpliesExpr)
	 *     )
	 */
	protected void sequence_AndExpr_ImpliesExpr_OrExpr_PlusExpr_RelationalExpr_TimesExpr(EObject context, BinaryExpr semanticObject) {
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
	 *     (name=ID expr=Expr)
	 */
	protected void sequence_Arg(EObject context, QuantArg semanticObject) {
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
	 *     (fn=BuiltInFn (args+=Expr args+=Expr*)?)
	 */
	protected void sequence_AtomicExpr(EObject context, BuiltInFnCallExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (val=Expr | failmsg+=ClaimText+)
	 */
	protected void sequence_AtomicExpr(EObject context, FailExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (map=AtomicExpr_FilterMapExpr_11_2_0_0 args+=Arg+ filter=Expr?)
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
	 *     id=[NamedElement|QCREF]
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
	 *     (binding=LetBinding expr=Expr)
	 */
	protected void sequence_AtomicExpr(EObject context, LetExpr semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((quant='forall' | quant='exists') args+=Arg+ expr=Expr)
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
	 *     ((exprs+=AtomicExpr_SetExpr_11_2_1_0 exprs+=Expr*) | exprs+=AtomicExpr_SetExpr_11_2_1_0)
	 */
	protected void sequence_AtomicExpr(EObject context, SetExpr semanticObject) {
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
	 *         type='int' | 
	 *         type='real' | 
	 *         type='string' | 
	 *         type='bool' | 
	 *         type='range' | 
	 *         type='aadl' | 
	 *         type='component' | 
	 *         type='abstract' | 
	 *         type='bus' | 
	 *         type='data' | 
	 *         type='device' | 
	 *         type='memory' | 
	 *         type='processor' | 
	 *         type='process' | 
	 *         type='subprogram_group' | 
	 *         type='subprogram' | 
	 *         type='system' | 
	 *         type='thread_group' | 
	 *         type='thread' | 
	 *         type='virtual_bus' | 
	 *         type='virtual_processor' | 
	 *         type='connection' | 
	 *         type='property' | 
	 *         type='feature' | 
	 *         type='port' | 
	 *         type='data_port' | 
	 *         type='event_port' | 
	 *         type='event_data_port' | 
	 *         type='feature_group' | 
	 *         type='access' | 
	 *         type='bus_access' | 
	 *         type='provides_bus_access' | 
	 *         type='requires_bus_access' | 
	 *         type='data_access' | 
	 *         type='provides_data_access' | 
	 *         type='requires_data_access' | 
	 *         type='subprogram_access' | 
	 *         type='provides_subprogram_access' | 
	 *         type='requires_subprogram_access' | 
	 *         type='subprogram_group_access' | 
	 *         type='provides_subprogram_group_access' | 
	 *         type='requires_subprogram_group_access'
	 *     )
	 */
	protected void sequence_BaseType(EObject context, BaseType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arg=[ClaimTextVar|ID] unit=[UnitLiteral|ID]?)
	 */
	protected void sequence_ClaimText(EObject context, ClaimArg semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     str=STRING
	 */
	protected void sequence_ClaimText(EObject context, ClaimString semanticObject) {
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
	 *     (claim+=ClaimText+ expr=Expr)
	 */
	protected void sequence_DefinitionBody(EObject context, ClaimBody semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=Type expr=Expr)
	 */
	protected void sequence_DefinitionBody(EObject context, FunctionBody semanticObject) {
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
	 *     (expr=InstanceOfExpr_InstanceOfExpr_1_0_0_0 type=BaseType)
	 */
	protected void sequence_InstanceOfExpr(EObject context, InstanceOfExpr semanticObject) {
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
	 *     (name=ID type=Type expr=Expr)
	 */
	protected void sequence_LetBinding(EObject context, LetBinding semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
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
	 *     (type=BaseType expr=PrefixExpr)
	 */
	protected void sequence_PrefixExpr(EObject context, CastExpr semanticObject) {
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
	 *     expr=Expr
	 */
	protected void sequence_ProveStatement(EObject context, ProveStatement semanticObject) {
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
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ResolutePackage.Literals.SET_TYPE__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ResolutePackage.Literals.SET_TYPE__TYPE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTypeAccess().getTypeTypeParserRuleCall_0_2_0(), semanticObject.getType());
		feeder.finish();
	}
}
