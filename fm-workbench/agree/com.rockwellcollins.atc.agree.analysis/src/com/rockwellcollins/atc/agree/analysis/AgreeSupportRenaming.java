package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

public class AgreeSupportRenaming extends AgreeRenaming {

	public AgreeSupportRenaming() {
		 this.refMap = new HashMap<String, EObject>();
		// TODO Auto-generated constructor stub
	}
	
	  public void addToRefMap(String str, EObject ref) {
		    if (str != null) { 
		    	String renamedstr = rename(str);
	            if (renamedstr != null) {
	            	refMap.put(renamedstr, ref);
	            }
	        }
	    }
	  //Anitha: I added this since I dont want to rename for support strings when adding refernece.
	  public void addSupportToRefMap(String str, EObject ref) {
		    if (str != null) { 
	           refMap.put(str, ref);
	        }
	    }
	
	@Override
    public String rename(String original) {
		
		
		
		String renamed = super.explicitRenames.get(original);
		
        if (renamed != null) {        	
    		return renamed;
        }
       
        String newName = original.replaceAll("___Nod([^_]_?)*_", "");
        newName = newName.replaceAll("~condact", "");
        newName = newName.replaceAll("~[0-9]*", "");
        
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
        	return renamed;
        }
        
        newName = newName.replaceAll("_TOP__", "");
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
        	return renamed;
        }
        
        //this is done for variables
        if(newName.contains(".")) {
            String strippedName = newName.substring(newName.indexOf(".")+1, newName.length() );
	        renamed = this.explicitRenames.get(strippedName);
	        if (renamed != null) {
	        	return renamed;
	        }
        }
        
        if(newName.contains("__")) {
	        String strippedName = newName.substring(newName.indexOf("__")+2, newName.length() );
	        renamed = this.explicitRenames.get(strippedName);
	        if (renamed != null) {
	        	return renamed;
	        }
        }
        
        /*This piece of code helps identify if the variables of form: CONFIG__OP_CMD_IN.Data_Config  are renamed
         * Such variables reference will be CONFIG.OP_CMD_IN. 
         * We want to display the whole name, but when clicked it should go to its reference 
         * */
        EObject ref = null;
        String tempName = original.replace("__", ".");
        while (ref == null && tempName != null && !tempName.equals("")) {        	
            ref = refMap.get(tempName);
            if (!(ref == null)) {
            	renamed =  original.replace("__", ".");
               return renamed;
            }
    		int index = tempName.lastIndexOf(".");
            if (index == -1) {
                break;
            }
            tempName = tempName.substring(0, index);
           }
       
        //the following is special for kind 2 contracts
        newName = newName.replaceAll("guarantee\\[.*?\\]", "");
        newName = newName.replace("__", ".");
        renamed = this.explicitRenames.get(newName);
        if (renamed != null) {
            return renamed;
        }
        return renamed;
    }

}
