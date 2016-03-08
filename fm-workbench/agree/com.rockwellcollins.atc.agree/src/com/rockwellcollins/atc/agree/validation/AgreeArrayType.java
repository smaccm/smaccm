package com.rockwellcollins.atc.agree.validation;

public class AgreeArrayType extends AgreeType {

    private final AgreeType subType;
    public AgreeArrayType(AgreeType subType){
        super("["+subType+"]");
        this.subType = subType;
    }
    
    public int getNumDimensions(){
        if (subType instanceof AgreeArrayType) {
            AgreeArrayType subArray = (AgreeArrayType) subType;
            return subArray.getNumDimensions() + 1;
        }else{
            return 1;
        }
    }

    public AgreeType getBaseType(){
        if (subType instanceof AgreeArrayType) {
            AgreeArrayType subArray = (AgreeArrayType) subType;
            return subArray.getBaseType();
        }else{
            return subType;
        }
    }
    
    public static AgreeArrayType getArrayType(AgreeType baseType, int numDimensionts){
        AgreeArrayType type = new AgreeArrayType(baseType);
        for(int i = 1; i < numDimensionts; i++){
            type = new AgreeArrayType(type);
        }
        return type;
    }
    
    public AgreeType getSubType(){
        return this.subType;
    }
}
