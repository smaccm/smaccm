package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.excel.Layout;
import jkind.lustre.Node;
import jkind.lustre.Program;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Subcomponent;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;

public class AgreeGenerator {
    
    private ComponentImplementation compImpl;
    private List<ComponentImplementation> modelParents;

    public AgreeGenerator(ComponentImplementation compImpl, List<ComponentImplementation> modelParents){
        this.compImpl = compImpl;
        this.modelParents = modelParents;
    }
    
    public Program evaluate(){
        
        ComponentType ct = compImpl.getType();
        AgreeLayout layout = new AgreeLayout();
        String category = compImpl.getName();
        
        AgreeAnnexEmitter topEmitter = new AgreeAnnexEmitter(
                compImpl, modelParents, null, layout, category);
        
        for (AnnexSubclause annex : compImpl.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                topEmitter.doSwitch(annex);
            }
        }

        for (AnnexSubclause annex : ct.getAllAnnexSubclauses()) {
            if (annex instanceof AgreeContractSubclause) {
                topEmitter.doSwitch(annex);
            }
        }
        
        List<AgreeAnnexEmitter> subEmitters = new ArrayList<>();
        for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
            ct = subComp.getComponentType();
            ComponentImplementation subCompImpl = subComp.getComponentImplementation();
            category = compImpl.getName() + subComp.getName();
            AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                    subCompImpl, modelParents, subComp, layout, category);

            for (AnnexSubclause annex : subCompImpl.getAllAnnexSubclauses()) {
                if (annex instanceof AgreeContractSubclause) {
                    subEmitter.doSwitch(annex);
                }
            }

            for (AnnexSubclause annex : ct.getAllAnnexSubclauses()) {
                if (annex instanceof AgreeContractSubclause) {
                    subEmitter.doSwitch(annex);
                }
            }
            
            subEmitters.add(subEmitter);
        }
        
        List<Node> nodes = topEmitter.getLustre(compImpl, subEmitters);
        return new Program(nodes);
        
    }
    
   // public Map<String, EObject> getReferenceMap() {
   //     return refMap;
   // }

   // public Renaming getRenaming() {
   //     return new MapRenaming(varRenaming, MapRenaming.Mode.NULL);
   // }

}
