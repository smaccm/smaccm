package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.core.runtime.Adapters;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.ge.BusinessObjectContext;

class AgreePropertySectionUtil {
	public static NamedElement asPackageOrComponentClassifier(final Object obj) {
		NamedElement result = asPackage(obj);
		if (result == null) {
			result = asComponentClassifier(obj);
		}
		return result;
	}

	public static AadlPackage asPackage(final Object obj) {
		if (obj instanceof AadlPackage) {
			return (AadlPackage) obj;
		} else if (obj instanceof BusinessObjectContext) {
			return asPackage(((BusinessObjectContext) obj).getBusinessObject());
		} else if (obj != null) {
			return asPackage(Adapters.adapt(obj, BusinessObjectContext.class));
		} else {
			return null;
		}
	}

	public static ComponentClassifier asComponentClassifier(final Object obj) {
		if (obj instanceof ComponentClassifier) {
			return (ComponentClassifier) obj;
		} else if (obj instanceof Subcomponent) {
			return ((Subcomponent) obj).getClassifier();
		} else if (obj instanceof ComponentInstance) {
			return ((ComponentInstance) obj).getComponentClassifier();
		} else if (obj instanceof BusinessObjectContext) {
			return asComponentClassifier(((BusinessObjectContext) obj).getBusinessObject());
		} else if (obj != null) {
			return asComponentClassifier(Adapters.adapt(obj, BusinessObjectContext.class));
		} else {
			return null;
		}
	}

	public static ComponentType asComponentType(final Object obj) {
		if (obj instanceof ComponentType) {
			return (ComponentType) obj;
		} else if (obj instanceof ComponentImplementation) {
			return ((ComponentImplementation) obj).getType();
		} else if (obj instanceof Subcomponent) {
			return asComponentType(((Subcomponent) obj).getClassifier());
		} else if (obj instanceof ComponentInstance) {
			return asComponentType(((ComponentInstance) obj).getComponentClassifier());
		} else if (obj instanceof BusinessObjectContext) {
			return asComponentType(((BusinessObjectContext) obj).getBusinessObject());
		} else if (obj != null) {
			return asComponentType(Adapters.adapt(obj, BusinessObjectContext.class));
		} else {
			return null;
		}
	}

	public static ComponentImplementation asComponentImplementation(final Object obj) {
		if (obj instanceof ComponentImplementation) {
			return (ComponentImplementation) obj;
		} else if (obj instanceof Subcomponent) {
			return asComponentImplementation(((Subcomponent) obj).getClassifier());
		} else if (obj instanceof ComponentInstance) {
			return asComponentImplementation(((ComponentInstance) obj).getComponentClassifier());
		} else if (obj instanceof BusinessObjectContext) {
			return asComponentImplementation(((BusinessObjectContext) obj).getBusinessObject());
		} else if (obj != null) {
			return asComponentImplementation(Adapters.adapt(obj, BusinessObjectContext.class));
		} else {
			return null;
		}
	}
}
