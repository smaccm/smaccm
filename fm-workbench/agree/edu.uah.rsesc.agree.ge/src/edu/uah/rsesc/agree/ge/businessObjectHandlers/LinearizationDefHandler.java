package edu.uah.rsesc.agree.ge.businessObjectHandlers;

import javax.inject.Named;

import org.osate.aadl2.NamedElement;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
import org.osate.ge.GraphicalExtensionUtil;
import org.osate.ge.PaletteEntry;
import org.osate.ge.PaletteEntryBuilder;
import org.osate.ge.di.BuildCreateOperation;
import org.osate.ge.di.CanCreate;
import org.osate.ge.di.CanDelete;
import org.osate.ge.di.GetGraphicalConfiguration;
import org.osate.ge.di.GetName;
import org.osate.ge.di.GetPaletteEntries;
import org.osate.ge.di.IsApplicable;
import org.osate.ge.di.Names;
import org.osate.ge.di.Rename;
import org.osate.ge.di.ValidateName;
import org.osate.ge.graphics.Graphic;
import org.osate.ge.graphics.RectangleBuilder;
import org.osate.ge.graphics.Style;
import org.osate.ge.graphics.StyleBuilder;
import org.osate.ge.operations.Operation;
import org.osate.ge.operations.OperationBuilderHelper;
import org.osate.ge.operations.StepResultBuilder;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.LinearizationInterval;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;

import edu.uah.rsesc.agree.ge.AgreeCategories;

public class LinearizationDefHandler {
	private static final Graphic graphic = RectangleBuilder.create().build();

	@IsApplicable
	@CanDelete
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) LinearizationDef bo) {
		return true;
	}

	@GetGraphicalConfiguration
	public GraphicalConfiguration getGraphicalConfiguration(
			final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc) {
		return GraphicalConfigurationBuilder.create().graphic(graphic).annotation("<linearization>")
				.style(StyleBuilder
						.create(GraphicalExtensionUtil.isInherited(boc) ? GraphicalExtensionUtil.STYLE_INHERITED_ELEMENT
								: Style.EMPTY)
						.labelsTop().labelsHorizontalCenter().build())
				.build();
	}

	@GetPaletteEntries
	public PaletteEntry[] getPaletteEntries(final @Named(Names.DIAGRAM_BO) Object diagramBo) {
		if (!AgreeBusinessObjectHandlerUtil.shouldContributePaletteEntries(diagramBo)) {
			return null;
		}

		return new PaletteEntry[] {
				PaletteEntryBuilder.create().label("Linearization").category(AgreeCategories.AGREE).build() };
	}

	private static OperationBuilderHelper<NamedElement> getClassifierOpBuilder() {
		return OperationBuilderHelper.packagesAndComponentClassifiers().allowAnyMatchingClassifier();
	}

	@CanCreate
	public boolean canCreate(final @Named(Names.TARGET_BO) Object bo) {
		return getClassifierOpBuilder().canBuildOperation(bo);
	}

	@BuildCreateOperation
	public Operation buildCreateOperation(
			final @Named(Names.TARGET_BUSINESS_OBJECT_CONTEXT) BusinessObjectContext targetBoc) {
		return Operation.create(createOp -> {
			getClassifierOpBuilder().buildOperation(createOp, targetBoc.getBusinessObject())
					.map(AgreeHandlerUtil.toBusinessObjectToModify())
					.modifyPreviousResult(modifyBo -> {
						final AgreeContract agreeContract = AgreeHandlerUtil.getOrCreateAgreeContract(modifyBo);
						final LinearizationDef newBo = AgreeFactory.eINSTANCE.createLinearizationDef();
						newBo.setName(AgreeBusinessObjectHandlerUtil.buildUniqueIdentifier(agreeContract,
								"linearization"));

						// Parameter
						final Arg newArg = AgreeFactory.eINSTANCE.createArg();
						newArg.setName("x");
						final PrimType type = AgreeFactory.eINSTANCE.createPrimType();
						type.setName("real");
						newArg.setType(type);
						newBo.getArgs().add(newArg);

						// Interval
						final LinearizationInterval interval = AgreeFactory.eINSTANCE.createLinearizationInterval();
						final RealLitExpr startExprReal = AgreeFactory.eINSTANCE.createRealLitExpr();
						startExprReal.setVal("1.0");

						final UnaryExpr startExpr = AgreeFactory.eINSTANCE.createUnaryExpr();
						startExpr.setOp("-");
						startExpr.setExpr(startExprReal);
						interval.setStart(startExpr);

						final RealLitExpr endExpr = AgreeFactory.eINSTANCE.createRealLitExpr();
						endExpr.setVal("1.0");
						interval.setEnd(endExpr);
						newBo.getIntervals().add(interval);

						// Precision
						final RealLitExpr precisionExpr = AgreeFactory.eINSTANCE.createRealLitExpr();
						precisionExpr.setVal("0.01");
						newBo.setPrecision(precisionExpr);

						// Body

						final NamedElmExpr body = AgreeFactory.eINSTANCE.createNamedElmExpr();
						body.setElm(newArg);
//=======
//						final NestedDotID body = AgreeFactory.eINSTANCE.createNestedDotID();
//						body.setBase(newArg);
//
//>>>>>>> origin/develop
						newBo.setExprBody(body);

						agreeContract.getSpecs().add(newBo);

						return StepResultBuilder.create(newBo).showNewBusinessObject(targetBoc, newBo).build();
					});
		});
	}

	@GetName
	public String getName(final @Named(Names.BUSINESS_OBJECT) LinearizationDef bo) {
		return bo.getName();
	}

	@ValidateName
	public String validateName(final @Named(Names.BUSINESS_OBJECT) LinearizationDef bo,
			final @Named(Names.NAME) String value) {
		return AgreeBusinessObjectHandlerUtil.validateName(bo, value);
	}

	@Rename
	public void setName(final @Named(Names.BUSINESS_OBJECT) LinearizationDef bo,
			final @Named(Names.NAME) String value) {
		bo.setName(value);
	}
}
