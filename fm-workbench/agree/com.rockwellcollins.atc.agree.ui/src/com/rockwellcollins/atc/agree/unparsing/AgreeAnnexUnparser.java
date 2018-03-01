/**
 * Copyright (c) 2017, Rockwell Collins.
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

package com.rockwellcollins.atc.agree.unparsing;

import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializer;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.annexsupport.AnnexUnparser;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.serializer.AgreeSerializer;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

public class AgreeAnnexUnparser implements AnnexUnparser {
	@Inject private ISerializer serializer;

	protected ISerializer getSerializer() {
		if (serializer == null) {
			Injector injector = AgreeActivator.getInstance().getInjector(
					AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE);
			serializer = injector.getInstance(AgreeSerializer.class);
		}
		return serializer;
	}

	@Override
	public String unparseAnnexLibrary(AnnexLibrary library, String indent) {
		library.setName(null);
		return indent + getSerializer().serialize(library);
	}

	@Override
	public String unparseAnnexSubclause(AnnexSubclause subclause, String indent) {
		subclause.setName(null);
		return indent + getSerializer().serialize(subclause);
	}

	public String unparseContract(AgreeContract agreeContract, String indent) {
		return indent + getSerializer().serialize(agreeContract);
	}

}
