package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;

import jkind.api.results.Renaming;

public class AgreeRenaming extends Renaming {

    protected Map<String, String> explicitRenames = new HashMap<>();
    protected Map<String, EObject> refMap;

    public AgreeRenaming() {
        this.refMap = new HashMap<String, EObject>();
    }

    public void addRenamings(AgreeRenaming renaming) {
        this.explicitRenames.putAll(renaming.explicitRenames);
        this.refMap.putAll(renaming.refMap);
    }

    public void addExplicitRename(String oldName, String newName) {
        this.explicitRenames.put(oldName, newName);
    }
    
    public void addToRefMap(String str, EObject ref) {
        if (str != null) {
            str = rename(str);
            if (str != null) {
                refMap.put(str, ref);
            }
        }
    }
    
    public Map<String, EObject> getRefMap(){
        return refMap;
    }

    public String forceRename(String original) {

        String newName;
        if(original.startsWith(LustreAstBuilder.LATCHED_VAR_PREFIX)){
            original = original.replaceAll(LustreAstBuilder.LATCHED_VAR_PREFIX, "");
            original += "__LATCHED";
        }
        newName = original.replaceAll("___Nod([^_]_?)*_", "");
        newName = newName.replace("~condact", "");
        newName = newName.replaceAll("~[0-9]*", "");
        newName = newName.replace("__", ".");

        return newName;

    }
    
    @Override
    public String rename(String original) {

        String newName = this.explicitRenames.get(original);
        if (newName != null) {
            return newName;
        }
        
        newName = forceRename(original);
        if (findBestReference(newName) == null) {
            if (original.equals("%REALIZABLE")) {
                return "Realizability Result";
            } else if (original.contains("__nodeLemma")) {
                return newName;
            }  else if (newName.matches("time")) {
                return "time";
            } else if (original.endsWith(AgreeASTBuilder.clockIDSuffix)) {
                return newName;
            } else if (original.endsWith(LustreAstBuilder.assumeHistSufix)) {
                return newName;
            }
            return null;
        }

        return newName;

    }

    private EObject findBestReference(String refStr) {

        EObject ref = null;
        while (ref == null && refStr != null && !refStr.equals("")) {
            ref = refMap.get(refStr);
            int index = refStr.lastIndexOf(".");
            if (index == -1) {
                break;
            }
            refStr = refStr.substring(0, index);
        }

        return ref;
    }

}
