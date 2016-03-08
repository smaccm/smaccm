package com.rockwellcollins.atc.agree.analysis;

import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ThreadType;

import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

public class Util {
    private static GlobalURIEditorOpener globalURIEditorOpener;

    public static GlobalURIEditorOpener getGlobalURIEditorOpener() {
        if (globalURIEditorOpener == null) {
            AgreeActivator activator = AgreeActivator.getInstance();
            String language = AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE;
            Injector injector = activator.getInjector(language);
            globalURIEditorOpener = injector.getInstance(GlobalURIEditorOpener.class);
        }
        return globalURIEditorOpener;
    }
    
    public static ComponentImplementation compImplFromType(ComponentType ct) {
        ComponentImplementation ci;
        if(ct instanceof ThreadType){
            ci = Aadl2Factory.eINSTANCE.createThreadImplementation();
        }else{
            ci = Aadl2Factory.eINSTANCE.createSystemImplementation();
        }
        ci.setName(ct.getName() + ".wrapper");
        ci.setType(ct);
        ct.eResource().getContents().add(ci);

        return ci;
    }
}
