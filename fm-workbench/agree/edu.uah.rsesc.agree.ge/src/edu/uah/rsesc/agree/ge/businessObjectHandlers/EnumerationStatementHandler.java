package edu.uah.rsesc.agree.ge.businessObjectHandlers;

import javax.inject.Named;

import org.osate.aadl2.AadlPackage;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.GraphicalConfiguration;
import org.osate.ge.GraphicalConfigurationBuilder;
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
import org.osate.ge.graphics.StyleBuilder;
import org.osate.ge.operations.Operation;
import org.osate.ge.operations.OperationBuilderHelper;
import org.osate.ge.operations.StepResultBuilder;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.NamedID;

import edu.uah.rsesc.agree.ge.AgreeCategories;

public class EnumerationStatementHandler {
	private static final Graphic graphic = RectangleBuilder.create().build();

	@IsApplicable
	@CanDelete
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) EnumStatement bo) {
		return true;
	}

	@GetGraphicalConfiguration
	public GraphicalConfiguration getGraphicalConfiguration(
			final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc) {
		return GraphicalConfigurationBuilder.create().graphic(graphic).annotation("<enumeration>")
				.style(StyleBuilder
						.create()
						.labelsTop().labelsHorizontalCenter().build())
				.build();
	}

	@GetPaletteEntries
	public PaletteEntry[] getPaletteEntries(final @Named(Names.DIAGRAM_BO) Object diagramBo) {
		if (!AgreeBusinessObjectHandlerUtil.shouldContributePaletteEntries(diagramBo)) {
			return null;
		}

		return new PaletteEntry[] {
				PaletteEntryBuilder.create().label("Enumeration").category(AgreeCategories.AGREE).build() };
	}

	private static OperationBuilderHelper<AadlPackage> getClassifierOpBuilder() {
		return OperationBuilderHelper.packages().allowAnyMatchingClassifier();
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
						final EnumStatement newBo = AgreeFactory.eINSTANCE.createEnumStatement();
						newBo.setName(
								AgreeBusinessObjectHandlerUtil.buildUniqueIdentifier(agreeContract, "enumeration"));

						final NamedID enumId = AgreeFactory.eINSTANCE.createNamedID();
						enumId.setName(newBo.getName() + "_enumerator");
						newBo.getEnums().add(enumId);

						agreeContract.getSpecs().add(newBo);

						return StepResultBuilder.create(newBo).showNewBusinessObject(targetBoc, newBo).build();
					});
		});
	}

	@GetName
	public String getName(final @Named(Names.BUSINESS_OBJECT) EnumStatement bo) {
		return bo.getName();
	}

	@ValidateName
	public String validateName(final @Named(Names.BUSINESS_OBJECT) EnumStatement bo,
			final @Named(Names.NAME) String value) {
		return AgreeBusinessObjectHandlerUtil.validateName(bo, value);
	}

	@Rename
	public void setName(final @Named(Names.BUSINESS_OBJECT) EnumStatement bo,
			final @Named(Names.NAME) String value) {
		bo.setName(value);
	}
}
