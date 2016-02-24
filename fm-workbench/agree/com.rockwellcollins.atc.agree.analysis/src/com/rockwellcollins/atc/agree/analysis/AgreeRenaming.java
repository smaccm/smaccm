package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import jkind.JKindException;
import jkind.api.results.Renaming;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;

public class AgreeRenaming extends Renaming {

    private Map<String, String> explicitRenames = new HashMap<>();
    private Map<String, EObject> refMap;

    public AgreeRenaming(Map<String, EObject> refMap) {
        this.refMap = refMap;
    }

    public void addRenamings(AgreeRenaming renaming) {
        this.explicitRenames.putAll(renaming.explicitRenames);
        this.refMap.putAll(renaming.refMap);
    }

    public void addExplicitRename(String oldName, String newName) {
        this.explicitRenames.put(oldName, newName);
    }

    public String forceRename(String original) {

        // magic to remove the prefix
        String newName;
        newName = original.replaceAll("___Nod([^_]_?)*_", "");
        newName = newName.replace("~condact", "");
        newName = newName.replaceAll("~[0-9]*", "");
        //the following is special for kind 2 contracts
        newName = newName.replaceAll("guarantee\\[.*?\\]", "");
        newName = newName.replace("__", ".");

        return newName;

    }

    @Override
    public Property rename(Property property) {
        //another hack for kind2
       if(property.getName().matches("guarantee\\[.*?\\]")){
           return renameKind2Prop(property);
           //return property;
       }
       return super.rename(property);
    }
    
    private Property renameKind2Prop(Property property){
        if(property instanceof InvalidProperty){
            InvalidProperty renamedInvalid = (InvalidProperty)property;
            return new InvalidProperty(renamedInvalid.getName(), 
                    renamedInvalid.getSource(), 
                    rename(renamedInvalid.getCounterexample()), 
                    renamedInvalid.getConflicts(), 
                    renamedInvalid.getRuntime());
        }else if(property instanceof UnknownProperty){
            UnknownProperty renamedUnknown = (UnknownProperty)property;
            return new UnknownProperty(renamedUnknown.getName(), 
                    renamedUnknown.getTrueFor(), 
                    rename(renamedUnknown.getInductiveCounterexample()), 
                    renamedUnknown.getRuntime());
        }
        if(!(property instanceof ValidProperty)){
            throw new AgreeException("Unexpected property type");
        }
        return property;
    }
    
    @Override
    public String rename(String original) {

        String newName = this.explicitRenames.get(original);
        if (newName != null) {
            return newName;
        }
        newName = forceRename(original);

        if (findBestReference(original) == null) {
            if (original.equals("%REALIZABLE")) {
                return "Realizability Result";
            } else if (original.contains("__nodeLemma")) {
                return newName;
            } else if(newName.matches(".*\\[[0-9]*\\]")){
                //kind2 hacks
               newName = this.explicitRenames.get(newName);
//               if(newName == null){
//                   return original;
//               }
               return newName;
            }
            return null;
        }

        return newName;

    }

    private EObject findBestReference(String refStr) {

        EObject ref = null;
        refStr = refStr.replace(".", "__");
        while (ref == null && refStr != null && !refStr.equals("")) {
            ref = refMap.get(refStr);
            int index = refStr.lastIndexOf("__");
            if (index == -1) {
                break;
            }
            refStr = refStr.substring(0, index);
        }

        return ref;
    }

}
