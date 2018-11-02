/*
 * generated by Xtext
 */
package com.rockwellcollins.atc.resolute;

import org.eclipse.xtext.generator.IGenerator;

import com.rockwellcollins.atc.resolute.generator.NullGenerator;

/**
 * Use this class to register components to be used at runtime / without the
 * Equinox extension registry.
 */
public class ResoluteRuntimeModule extends AbstractResoluteRuntimeModule {
	public Class<? extends IGenerator> bindIGenerator() {
		return NullGenerator.class;
	}

	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameConverter> bindIQualifiedNameConverter() {
		return com.rockwellcollins.atc.resolute.naming.ResoluteQualifiedNameConverter.class;
	}

	@SuppressWarnings("restriction")
	public Class<? extends org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer> bindICrossReferenceSerializer() {
		return com.rockwellcollins.atc.resolute.serializer.ResoluteCrossReferenceSerializer.class;
	}

	// shouldn't need to do this, it should appear in the srg-gen version...just testing
	@Override
	public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
		return com.rockwellcollins.atc.resolute.formatting.ResoluteFormatter.class;
	}
}
