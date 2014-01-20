package com.rockwellcollins.atc.resolute.analysis.results;

import java.util.ArrayList;
import java.util.List;

public class ResoluteDOTUtils {
        
    private static int nodeCount;
    
    public static String claimToDOTText(ClaimResult claim){
            
        StringBuilder dotStr = new StringBuilder();
        nodeCount = 0;
        
        dotStr.append("digraph assurance {\n");
        claimToDOTText_Helper(claim, dotStr);
        dotStr.append("}\n");
        
        return dotStr.toString();
    }
    
    private static List<Integer> claimToDOTText_Helper(ResoluteResult resResult, StringBuilder dotStr){
        
        int thisNodeNum = nodeCount;
        List<Integer> returnIds = new ArrayList<>();
        
        if(resResult instanceof ClaimResult){
            dotStr.append("n"+thisNodeNum+" [label=\""+((ClaimResult)resResult).getText()+"\"];\n");
            returnIds.add(thisNodeNum);
            nodeCount++;
        }
        
        for(ResoluteResult childResult : resResult.getChildren()){
            List<Integer> childIds = claimToDOTText_Helper(childResult, dotStr);
            if(resResult instanceof ClaimResult){
                for(int childId : childIds){
                    dotStr.append("n"+thisNodeNum+" -> n"+childId+";\n");
                }
            }else{
                returnIds.addAll(childIds);
            }
        }
        
        return returnIds;
    }
    
    
}
