package com.rockwellcollins.atc.resolute.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;

public class NullGenerator implements IGenerator {
    // @Override
    @Override
    public void doGenerate(Resource input, IFileSystemAccess fsa) {
        // Xtext complains if we don't have a generator,
        // but we don't want one so do nothing
        return;
    }
}
