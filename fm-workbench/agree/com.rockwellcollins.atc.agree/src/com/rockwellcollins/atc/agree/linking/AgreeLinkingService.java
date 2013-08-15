package com.rockwellcollins.atc.agree.linking;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.Element;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

import com.rockwellcollins.atc.agree.agree.IdExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;

public class AgreeLinkingService extends PropertiesLinkingService {
    public AgreeLinkingService() {
        super();
    }

    @Override
    public List<EObject> getLinkedObjects(EObject context, EReference reference, INode node)
            throws IllegalNodeException {
        String name = getCrossRefNodeAsString(node);

        if (context instanceof PropertyValue) {
            return findUnitLiteralAsList((Element) context, name);
        }

        if (context instanceof IdExpr 
                || context instanceof NestedDotID
                || context instanceof NodeEq) {

            // int dotIndex = name.indexOf('.');
            // name = name.substring(0, dotIndex);

            // EObject e = findComponentClassifier((Element) context, name);

            EObject e = findClassifier(context, reference, name);
            if (e != null) {
                return Collections.singletonList(e);
            }

            // TODO: This is a blind lookup which is unaware of project
            // dependencies and such
            e = EMFIndexRetrieval.getEObjectOfType(context, reference.getEReferenceType(), name);
            if (e != null) {
                return Collections.singletonList(e);
            }
        }

        return super.getLinkedObjects(context, reference, node);
    }

    public static List<EObject> findUnitLiteralAsList(Element context, String name) {
        EObject e = findUnitLiteral(context, name);
        if (e == null) {
            return Collections.<EObject> emptyList();
        }
        return Collections.singletonList(e);
    }

    final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

    private static UnitLiteral findUnitLiteral(Element context, String name) {
        // TODO: Scope literals by type, but how to do we know the type of an
        // expression?
        for (IEObjectDescription desc : EMFIndexRetrieval
                .getAllEObjectsOfTypeInWorkspace(UNITS_TYPE)) {
            UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
            UnitLiteral literal = unitsType.findLiteral(name);
            if (literal != null) {
                return literal;
            }
        }

        return null;
    }

}
