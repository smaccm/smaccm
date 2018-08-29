package com.rockwellcollins.atc.agree.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osate.aadl2.AadlPackage;
import com.google.inject.Inject;
import com.rockwellcollins.atc.agree.AgreeInjectorProvider;

@InjectWith(AgreeInjectorProvider.class)
@RunWith(XtextRunner.class)
class ParserTest {

	@Inject
	ParseHelper<AadlPackage> parser;

	@Test
	void simple() {
		assertTrue(true);
	}
}