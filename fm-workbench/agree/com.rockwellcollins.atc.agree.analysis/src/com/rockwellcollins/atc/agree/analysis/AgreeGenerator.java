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
import org.osate.aadl2.BusSubcomponent;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Contract;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class AgreeGenerator {
    
    private ComponentInstance compInst;
    private AgreeAnnexEmitter topEmitter;
    private String dotChar = "__";
	private FeatureToConnectionsMap featToConnMap;

    public AgreeGenerator(ComponentInstance compInst){
        this.compInst = compInst;
    }
    
    public Program evaluate(){
        
        ComponentImplementation compImpl = AgreeEmitterUtilities.getInstanceImplementation(compInst);
        ComponentType ct = AgreeEmitterUtilities.getInstanceType(compInst);
        AgreeLayout layout = new AgreeLayout();
        String topCategory = compInst.getName();
        
        AgreeAnnexEmitter topEmitter = new AgreeAnnexEmitter(
                compInst, layout, topCategory, topCategory + dotChar, topCategory + dotChar, true, true);
        
        this.topEmitter = topEmitter;
        
        boolean foundAnnex = false;
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(compImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                topEmitter.doSwitch(annex);
                foundAnnex = true;
                break;
            }
        }
        
        for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(ct, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
            if (annex instanceof AgreeContractSubclause) {
                topEmitter.doSwitch(annex);
                foundAnnex = true;
                break;
            }
        }
        
        List<AgreeAnnexEmitter> subEmitters = new ArrayList<>();
        for(Subcomponent subComp : compImpl.getAllSubcomponents()){
        	//don't check data subcomponents
        	if(subComp instanceof DataSubcomponent
        			|| subComp instanceof DataSubcomponentType
        			|| subComp instanceof BusSubcomponent){
        		continue;
        	}
        	
        	String category;
        	boolean foundSubAnnex = false;
            ComponentInstance subCompInst = compInst.findSubcomponentInstance(subComp);
            ct = AgreeEmitterUtilities.getInstanceType(subCompInst);
            ComponentImplementation subCompImpl = AgreeEmitterUtilities.getInstanceImplementation(subCompInst);
            category = subCompInst.getQualifiedName();
            AgreeAnnexEmitter subEmitter = new AgreeAnnexEmitter(
                    subCompInst, layout, category,
                    topCategory + dotChar,
                    topCategory + dotChar + subComp.getName() + dotChar, false, false);

            if(subCompImpl != null){
                for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(subCompImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
                    if (annex instanceof AgreeContractSubclause) {
                    	Contract contract = ((AgreeContractSubclause) annex).getContract();
                    	if(contract instanceof AgreeContract){
                    		for(SpecStatement spec :  ((AgreeContract) contract).getSpecs()){
                    			if(spec instanceof LiftStatement){
                    				subEmitter.doSwitch(spec);
                    			}
                    		}
                    	}
                        foundSubAnnex = foundAnnex = true;
                    }
                }
            }

            for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(ct, AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
                if (annex instanceof AgreeContractSubclause) {
                    subEmitter.doSwitch(annex);
                    foundSubAnnex = foundAnnex = true;
                }
            }

            if(foundSubAnnex){
            	subEmitters.add(subEmitter);
            }

        }
        if(!foundAnnex){
        	return null;
        }
        
        return topEmitter.getLustreWithCondacts(subEmitters);

    }

    public Map<String, EObject> getReferenceMap() {
        return topEmitter.refMap;
    }

    public Renaming getRenaming() {
//        return new MapRenaming(topEmitter.varRenaming, MapRenaming.Mode.NULL);
    	return topEmitter.agreeRename;
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
