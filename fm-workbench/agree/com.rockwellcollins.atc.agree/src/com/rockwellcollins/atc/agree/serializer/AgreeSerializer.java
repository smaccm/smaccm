/**
 * Copyright (c) 2016, Rockwell Collins.
 *
 * Developed with the sponsorship of Defense Advanced Research Projects Agency
 * (DARPA).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this data, including any software or models in source or binary form, as
 * well as any drawings, specifications, and documentation (collectively
 * "the Data"), to deal in the Data without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Data, and to permit persons to whom the
 * Data is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Data.
 *
 * THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE
 * USE OR OTHER DEALINGS IN THE DATA.
 */

package com.rockwellcollins.atc.agree.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.impl.Serializer;

//import com.google.inject.Guice;
//import com.google.inject.Injector;
import com.google.inject.Inject;
//import com.google.inject.Provider;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;

@SuppressWarnings("restriction")
public class AgreeSerializer extends Serializer {

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
	private AgreeGrammarAccess grammarAccess;

	@Override
	protected ISerializationContext getIContext(EObject semanticObject) {
		ISerializationContext result = null;
		if (semanticObject instanceof AgreeContractLibrary) {
			for (final ISerializationContext o : contextFinder.findByContents(semanticObject, null)) {
				if (o.getParserRule() == grammarAccess.getAgreeLibraryRule()) {
					result = o;
					break;
				}
			}
		} else if (semanticObject instanceof AgreeContractSubclause) {
			for (final ISerializationContext o : contextFinder.findByContents(semanticObject, null)) {
				if (o.getParserRule() == grammarAccess.getAgreeSubclauseRule()) {
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
