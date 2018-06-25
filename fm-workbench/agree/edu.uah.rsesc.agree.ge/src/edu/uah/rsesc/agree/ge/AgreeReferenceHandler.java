package edu.uah.rsesc.agree.ge;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.eclipse.xtext.util.Strings;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Element;
import org.osate.ge.di.BuildCanonicalReference;
import org.osate.ge.di.BuildRelativeReference;
import org.osate.ge.di.Names;
import org.osate.ge.di.ResolveCanonicalReference;
import org.osate.ge.services.ReferenceBuilderService;
import org.osate.ge.services.ReferenceResolutionService;

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
import com.rockwellcollins.atc.agree.agree.SpecStatement;

/**
 * This class builds unique references for AGREE statements. However, many AGREE statements do not have unique names. In such cases an index is used to differentiate between the elements.
 * This can result in problems when removing elements with the graphical editor.
 * For example, when deleting a model statement which have other statements with the same name, the incorrect graphical shape may be removed.
 * However, the correct model element will be modified.
 * Unfortunately, the use of indices is the best referencing scheme at the moment.
 *
 */
// NOTE: Performance. Need to get all specs to get proper references. This will likely have an affect on performance.
public class AgreeReferenceHandler {
	private final static String AGREE_REFERENCE_PREFIX = "agree.";

	enum ReferenceableType {
		// @formatter:off
		ASSERT_STATEMENT("assertion", AssertStatement.class, s -> s.getStr()),
		ASSUME_STATEMENT("assumption", AssumeStatement.class, s -> s.getStr()),
		CONST_STATEMENT("constant", ConstStatement.class, s -> s.getName()),
		ENUM_STATEMENT("enum", EnumStatement.class, s -> s.getName()),
		EQ_STATEMENT("eq", EqStatement.class, s -> s.getLhs().stream().map(a -> Strings.emptyIfNull(a.getName())).collect(Collectors.joining(" "))),
		GUARANTEE_STATEMENT("guarantee", GuaranteeStatement.class, s -> s.getStr()),
		LEMMA_STATEMENT("lemma", LemmaStatement.class, s -> s.getStr()),
		LINEARIZATION_DEFINITION("linearization", LinearizationDef.class, s -> s.getName()),
		NODE_DEF_EXPRESSION("node_def", NodeDef.class, s -> s.getName()),
		PROPERTY_STATEMENT("property", PropertyStatement.class, s -> s.getName()),
		RECORD_DEF_EXPRESSION("record_def", RecordDef.class, s -> s.getName());

		public final String id;
		public final Class<?> boClass;
		public final Function<?, String> nameProvider;

		// @formatter:on
		<T> ReferenceableType(final String typeSuffix, final Class<T> boClass, final Function<T, String> nameProvider) {
			this.id = AGREE_REFERENCE_PREFIX + typeSuffix;
			this.boClass = boClass;
			this.nameProvider = nameProvider;
		}
	}

	@BuildRelativeReference
	public String[] getRelativeReference(final @Named(Names.BUSINESS_OBJECT) Object bo) {
		return generateReference(bo, new SpecStatementBuilder() {
			@Override
			public <T extends SpecStatement> String[] generateReference(String type, Object bo, Class<T> boClass,
					Function<T, String> nameProvider) {
				return getRelativeReference(type, bo, boClass, nameProvider);
			}
		});
	}

	private <T extends SpecStatement> String[] getRelativeReference(final String type, final Object bo,
			final Class<T> boClass, final Function<T, String> nameProvider) {
		if (boClass.isInstance(bo)) {
			final T s = boClass.cast(bo);
			final String name = nameProvider.apply(s);

			final int index = getIndex(s, boClass, nameProvider);
			return new String[] { type, name, Integer.toString(index) };
		}

		return null;
	}

	@BuildCanonicalReference
	public String[] getCanonicalReference(final @Named(Names.BUSINESS_OBJECT) Object bo,
			final ReferenceBuilderService refBuilder) {
		return generateReference(bo, new SpecStatementBuilder() {
			@Override
			public <T extends SpecStatement> String[] generateReference(String type, Object bo, Class<T> boClass,
					Function<T, String> nameProvider) {
				return getCanonicalReference(type, bo, boClass, nameProvider, refBuilder);
			}
		});
	}

	private <T extends SpecStatement> String[] getCanonicalReference(final String type, final Object bo,
			final Class<T> boClass, final Function<T, String> nameProvider, final ReferenceBuilderService refBuilder) {
		if (boClass.isInstance(bo)) {
			final T s = boClass.cast(bo);
			final String name = nameProvider.apply(s);

			final int index = getIndex(s, boClass, nameProvider);
			return new String[] { type, refBuilder.getReference(AgreeGeUtil.getPackageOrClassifier(s)), name,
					Integer.toString(index) };
		}

		return null;
	}

	@ResolveCanonicalReference
	public Object getReferencedObject(final @Named(Names.REFERENCE) String[] ref,
			final ReferenceResolutionService refService) {
		Objects.requireNonNull(ref, "ref must not be null");

		// Handle references with exactly 4 segments
		if (ref.length != 4) {
			return null;
		}

		// Check that the type is an EMV2 type
		final String type = ref[0];
		if (!type.startsWith(AGREE_REFERENCE_PREFIX)) {
			return null;
		}

		// Retrieve the referenced object
		final Object ref1 = refService.getReferencedObject(ref[1]);
		if (!(ref1 instanceof Classifier)) {
			return null;
		}
		final Classifier classifier = (Classifier) ref1;

		final String[] relReference = { ref[0], ref[2], ref[3] };
		final Optional<SpecStatement> result = AgreeBusinessObjectProvider.getAllSpecStatements(classifier)
				.filter(s -> Arrays.equals(relReference, getRelativeReference(s))).findFirst();

		return result.orElse(null);
	}

	private interface SpecStatementBuilder {
		<T extends SpecStatement> String[] generateReference(final String type, final Object bo, final Class<T> boClass,
				final Function<T, String> nameProvider);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String[] generateReference(final Object bo, final SpecStatementBuilder specStatementRefBuilder) {
		String[] ref;

		for (final ReferenceableType rt : ReferenceableType.values()) {
			ref = specStatementRefBuilder.generateReference(rt.id, bo, (Class) rt.boClass,
					(Function) rt.nameProvider);
			if (ref != null) {
				return ref;
			}
		}

		return null;
	}

	private <T extends SpecStatement> int getIndex(final T bo, final Class<T> boClass,
			final Function<T, String> nameProvider) {
		final String name = nameProvider.apply(bo);
		if (name == null) {
			return 0;
		}

		int index = 0;
		if (name != null) {
			final Element owner = AgreeGeUtil.getPackageOrClassifier(bo);
			if (owner != null) {
				// Find the index for the element.
				for (final T ss : (Iterable<T>) AgreeBusinessObjectProvider.getAllSpecStatements(owner)
						.filter(boClass::isInstance)
						.map(boClass::cast)
						.filter(other -> other == bo || name.equals(nameProvider.apply(other)))::iterator) {
					if (bo == ss) {
						break;
					} else {
						index++;
					}
				}
			}
		}

		return index;
	}
}
