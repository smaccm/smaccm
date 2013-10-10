package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jkind.api.results.MapRenaming;
import jkind.api.results.Renaming;
import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.results.layout.Layout;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Subcomponent;

import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;

public class AgreeGenerator {
    
    private ComponentImplementation compImpl;
    private List<ComponentImplementation> modelParents;
    private AgreeAnnexEmitter topEmitter;
    private String dotChar = "__";


    public AgreeGenerator(ComponentImplementation compImpl, List<ComponentImplementation> modelParents){
        this.compImpl = compImpl;
        this.modelParents = modelParents;
    }
    
    public Program evaluate(Subcomponent subCompContext){
        
        ComponentType ct = compImpl.getType();
        AgreeLayout layout = new AgreeLayout();
        String category = "";
        
        AgreeAnnexEmitter topEmitter = new AgreeAnnexEmitter(
                compImpl, modelParents, subCompContext, layout, category, "", "");
        
        this.topEmitter = topEmitter;
        
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
            category = subComp.getName();
            AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                    subCompImpl, modelParents, subComp, layout, category,
                    subComp.getName() + dotChar,
                    subComp.getName() + ".");

            if(subCompImpl != null){
                for (AnnexSubclause annex : subCompImpl.getAllAnnexSubclauses()) {
                    if (annex instanceof AgreeContractSubclause) {
                        subEmitter.doSwitch(annex);
                    }
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

    public Map<String, EObject> getReferenceMap() {
        return topEmitter.refMap;
    }

    public Renaming getRenaming() {
        return new MapRenaming(topEmitter.varRenaming, MapRenaming.Mode.NULL);
    }

    public Layout getLayout() {
        return topEmitter.layout;
    }

    public String getLog() {
        return topEmitter.log.toString();
    }

    public List<String> getAssumeProps() {
        return topEmitter.assumProps;
    }

    public List<String> getConsistProps() {
        return topEmitter.consistProps;
    }

    public List<String> getGuarProps() {
        return topEmitter.guarProps;
    }


}
