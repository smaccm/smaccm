package edu.uah.rsesc.agree.ge.filtering;

import java.util.Objects;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Subcomponent;
import org.osate.ge.ContentFilter;

import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RecordDef;

enum SpecStatementFilter implements ContentFilter {
	// @formatter:off
	ASSERTION(AssertStatement.class, "assertions","Assertions"),
	ASSUMPTION(AssumeStatement.class, "assumptions", "Assumptions"),
	CONST(ConstStatement.class, "constants", "Constants"),
	ENUMERATION(EnumStatement.class, "enumerations", "Enumerations"),
	EQUATION(EqStatement.class, "equations", "Equations"),
	GUARANTEE(GuaranteeStatement.class, "guarantees", "Guarantees"),
	LEMMA(LemmaStatement.class, "lemmas", "Lemmas"),
	LINEARIZATION_DEFINITION(LinearizationDef.class, "linearizations", "Linearizations"),
	NODE_DEFINITION(NodeDef.class, "nodeDefinitions", "Node Definitions"),
	PROPERTY(PropertyStatement.class, "properties", "Properties"),
	RECORD_DEFINITION(RecordDef.class, "recordDefinitions", "Record Definitions");
	// @formatter:on

	public final Class<?> statementClass;
	public final String id;
	public final String name;

	SpecStatementFilter(final Class<?> statementClass, final String idSuffix, final String name) {
		this.statementClass = Objects.requireNonNull(statementClass, "statementClass must not be null");
		this.id = AgreeFilter.ID + "." + Objects.requireNonNull(idSuffix, "idSuffix must not be null");
		this.name = Objects.requireNonNull(name, "name must not be null");
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getParentId() {
		return AgreeFilter.ID;
	}

	@Override
	public boolean isApplicable(final Object bo) {
		// Lemmas and Assertions are only applicable to component implementations
		if (this == LEMMA || this == ASSERTION) {
			return bo instanceof ComponentImplementation || bo instanceof Subcomponent;
		}

		// Support expressions that are allowed in AGREE libraries
		if (this == CONST || this == LINEARIZATION_DEFINITION || this == NODE_DEFINITION || this == RECORD_DEFINITION) {
			if (bo instanceof AadlPackage) {
				return true;
			}
		}

		if (this == ENUMERATION) {
			return bo instanceof AadlPackage;
		}

		return bo instanceof ComponentClassifier || bo instanceof Subcomponent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean test(final Object bo) {
		return statementClass.isInstance(bo);
	}

}
