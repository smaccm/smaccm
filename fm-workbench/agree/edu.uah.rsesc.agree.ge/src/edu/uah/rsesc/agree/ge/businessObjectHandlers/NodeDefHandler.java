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
import com.rockwellcollins.atc.agree.agree.NodeBodyExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;

import edu.uah.rsesc.agree.ge.AgreeCategories;

public class NodeDefHandler {
	private static final Graphic graphic = RectangleBuilder.create().build();

	@IsApplicable
	@CanDelete
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) NodeDef bo) {
		return true;
	}

	@GetGraphicalConfiguration
	public GraphicalConfiguration getGraphicalConfiguration(
			final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc) {
		return GraphicalConfigurationBuilder.create().graphic(graphic).annotation("<node>")
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
				PaletteEntryBuilder.create().label("Node").category(AgreeCategories.AGREE).build() };
	}

	private static OperationBuilderHelper<NamedElement> getOperationBuilder() {
		return OperationBuilderHelper.packagesAndComponentClassifiers().allowAnyMatchingClassifier();
	}

	@CanCreate
	public boolean canCreate(final @Named(Names.TARGET_BO) Object bo) {
		return getOperationBuilder().canBuildOperation(bo);
	}

	@BuildCreateOperation
	public Operation buildCreateOperation(
			final @Named(Names.TARGET_BUSINESS_OBJECT_CONTEXT) BusinessObjectContext targetBoc) {
		return Operation.create(createOp -> {
			getOperationBuilder().buildOperation(createOp, targetBoc.getBusinessObject())
					.map(AgreeHandlerUtil.toBusinessObjectToModify()).modifyPreviousResult(modifyBo -> {
						final AgreeContract agreeContract = AgreeHandlerUtil.getOrCreateAgreeContract(modifyBo);

						final NodeDef newBo = AgreeFactory.eINSTANCE.createNodeDef();
						agreeContract.getSpecs().add(newBo);
						newBo.setName(AgreeBusinessObjectHandlerUtil.buildUniqueIdentifier(agreeContract, "node"));

						// Create return argument
						final Arg retArg = AgreeFactory.eINSTANCE.createArg();
						retArg.setName("result");
						newBo.getRets().add(retArg);

						// Set Type
						final PrimType realType = AgreeFactory.eINSTANCE.createPrimType();
						realType.setName("real");
						retArg.setType(realType);

						// Create the node body
						final NodeBodyExpr nodeBody = AgreeFactory.eINSTANCE.createNodeBodyExpr();
						newBo.setNodeBody(nodeBody);

						// Create a statement that assigns to the return value.
						NodeEq initialStatement = AgreeFactory.eINSTANCE.createNodeEq();
						nodeBody.getStmts().add(initialStatement);
						initialStatement.getLhs().add(retArg);

						final RealLitExpr zeroExpr = AgreeFactory.eINSTANCE.createRealLitExpr();
						zeroExpr.setVal("0.0");
						initialStatement.setExpr(zeroExpr);

						return StepResultBuilder.create(newBo).showNewBusinessObject(targetBoc, newBo).build();
					});
		});
	}

	@GetName
	public String getName(final @Named(Names.BUSINESS_OBJECT) NodeDef bo) {
		return bo.getName();
	}

	@ValidateName
	public String validateName(final @Named(Names.BUSINESS_OBJECT) NodeDef bo,
			final @Named(Names.NAME) String value) {
		return AgreeBusinessObjectHandlerUtil.validateName(bo, value);
	}

	@Rename
	public void setName(final @Named(Names.BUSINESS_OBJECT) NodeDef bo,
			final @Named(Names.NAME) String value) {
		bo.setName(value);
	}
}
