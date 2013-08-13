package com.rockwellcollins.atc.resolute.schedule.analysis;

import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.instance.ComponentInstance;
import com.rockwellcollins.atc.resolute.analysis.ResoluteEvaluator;
import com.rockwellcollins.atc.resolute.analysis.ResoluteExternalAnalysis;
import com.rockwellcollins.atc.resolute.analysis.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.ResoluteQuantifiableAadlObjects;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class ResoluteScheduleAnalysis implements ResoluteExternalAnalysis {
	@Override
	public ResoluteValue run(ResoluteEvaluator resEval, List<ResoluteValue> args) {

		
		/*
		Set<NamedElement> procList = ResoluteQuantifiableAadlObjects.getAllComponentsOfType("processor");
		double clockPeriod = 0;
		if (procList.size() != 1) {
			throw new ResoluteFailException("The external 'schedule' analysis assumes that the"
					+ "instance model contains one and only one processor");
		}

	
		for (NamedElement el : procList) {
			assert (el instanceof ComponentInstance);
			ComponentInstance comp = (ComponentInstance) el;
			EList<PropertyExpression> prop = comp.getPropertyValues("Timing_Properties",
					"Clock_Period");

			if (prop.size() != 1) {
				throw new ResoluteFailException(
						"Processor '"
								+ comp.getName()
								+ "' must have a 'Clock_Period' property specified to run 'schedule' analysis");
			}
			PropertyExpression propExpr = prop.get(0);

			// if the property isn't specified it returns a "namedvalue"
			if (!(propExpr instanceof IntegerLiteral)) {
				throw new ResoluteFailException(
						"Processor '"
								+ comp.getName()
								+ "' must have a 'Clock_Period' property specified to run 'schedule' analysis");
			}

			IntegerLiteral intLit = (IntegerLiteral) propExpr;
			double value = intLit.getScaledValue(); // this is in pico seconds

			if (value == 0) {
				throw new ResoluteFailException("Processor '" + comp.getName()
						+ "' must have a non-zero 'Clock_Period' to run 'schedule' analysis");
			}

			clockPeriod = value;
		
		}

		*/
		
		// --- Add threads ---

		double sched_ratio = 0;
		Set<NamedElement> threadList = ResoluteQuantifiableAadlObjects.getAllComponentsOfType("thread");
		for (NamedElement el : threadList) {

			assert (el instanceof ComponentInstance);
			ComponentInstance comp = (ComponentInstance) el;
			EList<PropertyExpression> prop = comp.getPropertyValues("Timing_Properties", "Period");

			if (prop.size() != 1) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'Period' property specified to run 'schedule' analysis");
			}
			PropertyExpression propExpr = prop.get(0);

			if (!(propExpr instanceof IntegerLiteral)) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'Period' property specified to run 'schedule' analysis");
			}
			assert (propExpr instanceof IntegerLiteral);
			IntegerLiteral intLit = (IntegerLiteral) propExpr;
			double period_ps = intLit.getScaledValue(); // this is in pico
														// seconds

			prop = comp.getPropertyValues("Timing_Properties", "Deadline");

			if (prop.size() != 1) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'Deadline' property specified to run 'schedule' analysis");
			}

			propExpr = prop.get(0);

			if (!(propExpr instanceof IntegerLiteral)) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'Deadline' property specified to run 'schedule' analysis");
			}

			assert (propExpr instanceof IntegerLiteral);
			intLit = (IntegerLiteral) propExpr;
			double deadline_ps = intLit.getScaledValue(); // this is in pico
															// seconds

			prop = comp.getPropertyValues("SMACCM", "WC_Execution_Time");

			if (prop.size() != 1) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'WC_Execution_Time' property specified to run 'schedule' analysis");
			}

			propExpr = prop.get(0);

			if (!(propExpr instanceof IntegerLiteral)) {
				throw new ResoluteFailException("Thread '" + comp.getName()
						+ "' must have a 'WC_Execution_Time' property specified to run 'schedule' analysis");
			}

			assert (propExpr instanceof IntegerLiteral);
			intLit = (IntegerLiteral) propExpr;
			double execute_time_ps = intLit.getScaledValue(); // this is in pico
															// seconds
			
			
			sched_ratio += execute_time_ps / period_ps;
			
		}

	
		return new BoolValue(sched_ratio <= 1.0);
	}

}
