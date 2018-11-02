package com.rockwellcollins.atc.resolute.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.impl.Serializer;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.resolute.ResoluteLibrary;
import com.rockwellcollins.atc.resolute.resolute.ResoluteSubclause;
import com.rockwellcollins.atc.resolute.services.ResoluteGrammarAccess;

@SuppressWarnings("restriction")
public class ResoluteSerializer extends Serializer {

//    private static Injector injector = Guice
//            .createInjector(new com.rockwellcollins.atc.agree.AgreeRuntimeModule());
//
//    public static String serialize(EObject eobj) {
//        org.eclipse.xtext.serializer.impl.Serializer serializer = injector
//                .getInstance(org.eclipse.xtext.serializer.impl.Serializer.class);
//        String result = null;
//        try {
//            result = serializer.serialize(eobj);
//        } catch (Exception e) {
//            result = new String("caught exception serializing " + eobj.toString() + ": "
//                    + e.toString());
//            e.printStackTrace();
//            System.out.println(result);
//        }
//        return result;
//    }

	@Inject
	private ResoluteGrammarAccess grammarAccess;

	@Override
	protected ISerializationContext getIContext(EObject semanticObject) {
		ISerializationContext result = null;
		if (semanticObject instanceof ResoluteLibrary) {
			for (final ISerializationContext o : contextFinder.findByContents(semanticObject, null)) {
				if (o.getParserRule() == grammarAccess.getResoluteLibraryRule()) {
					result = o;
					break;
				}
			}
		} else if (semanticObject instanceof ResoluteSubclause) {
			for (final ISerializationContext o : contextFinder.findByContents(semanticObject, null)) {
				if (o.getParserRule() == grammarAccess.getResoluteSubclauseRule()) {
					result = o;
					break;
				}
			}
		} else {
			result = super.getIContext(semanticObject);
		}
		return result;
	}

}
