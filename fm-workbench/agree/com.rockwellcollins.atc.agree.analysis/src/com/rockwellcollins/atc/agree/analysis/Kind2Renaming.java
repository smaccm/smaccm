package com.rockwellcollins.atc.agree.analysis;

import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;

public class Kind2Renaming extends AgreeRenaming {
    
    @Override
    public String forceRename(String original) {

        String newName = super.forceRename(original);
        if(newName.startsWith("_TOP.")){
            newName = newName.replaceFirst("_TOP.", "");
        }
        //yes we need to do this twice because of how kind2 labels assumptions...
        if(newName.startsWith("_TOP.")){
            newName = newName.replaceFirst("_TOP.", "");
        }
        newName = newName.replaceAll("\\[l[0-9]*c[0-9]*\\]", "");
        //newName = newName.replaceAll(".guarantee", "");
        return newName;
    }

    
    @Override
    public String rename(String original) {
        String newName = super.rename(original);
        
        if(newName == null){
            newName = forceRename(original);
            if (newName.matches(".*\\[[0-9]*\\]")) {
                // kind2 and array hacks
                String renamed = this.explicitRenames.get(newName);
                if (renamed == null) {
                    // get the index of the array bound
                    int accessIndex = newName.indexOf("[");
                    String arrayName = newName.substring(0, accessIndex);
                    String arrayAccess = newName.substring(accessIndex);
                    renamed = rename(arrayName);
                    if (renamed != null) {
                        renamed += arrayAccess;
                    }
                }
                return renamed;
            }
        }
        return newName;
    }
    
    @Override
    public Property rename(Property property) {
       if(property.getName().matches("guarantee\\[.*?\\]")){
           return renameKind2Prop(property);
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
            UnknownProperty newProp =  new UnknownProperty(renamedUnknown.getName(), 
                    renamedUnknown.getTrueFor(), 
                    rename(renamedUnknown.getInductiveCounterexamples()), 
                    renamedUnknown.getRuntime());
            return newProp;
        }
        if(!(property instanceof ValidProperty)){
            throw new AgreeException("Unexpected property type");
        }
        return property;
    }
    
}
