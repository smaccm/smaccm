package com.rockwellcollins.atc.resolute;

/**
 * Initialization support for running Xtext languages without equinox extension
 * registry
 */
public class ResoluteStandaloneSetup extends ResoluteStandaloneSetupGenerated {

    public static void doSetup() {
        new ResoluteStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
