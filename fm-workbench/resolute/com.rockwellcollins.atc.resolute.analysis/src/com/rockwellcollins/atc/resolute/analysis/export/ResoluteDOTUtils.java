package com.rockwellcollins.atc.resolute.analysis.export;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;

public class ResoluteDOTUtils {

    private static int nodeCount;

    public static String claimToDOTText(ClaimResult claim) {
        StringBuilder out = new StringBuilder();
        nodeCount = 0;

        out.append("digraph assurance {\n");
        walk(claim, out);
        out.append("}\n");

        return out.toString();
    }

    private static List<Integer> walk(ResoluteResult result, StringBuilder out) {
        int thisNodeNum = nodeCount;
        List<Integer> returnIds = new ArrayList<>();

        if (result instanceof ClaimResult) {
            out.append("n" + thisNodeNum + " [label=\"" + ((ClaimResult) result).getText()
                    + "\"];\n");
            returnIds.add(thisNodeNum);
            nodeCount++;
        }

        for (ResoluteResult childResult : result.getChildren()) {
            List<Integer> childIds = walk(childResult, out);
            if (result instanceof ClaimResult) {
                for (int childId : childIds) {
                    out.append("n" + thisNodeNum + " -> n" + childId + ";\n");
                }
            } else {
                returnIds.addAll(childIds);
            }
        }

        return returnIds;
    }
}
