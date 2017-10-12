package edu.umn.cs.crisys.tb.codegen.common.emitters;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkesVM;
//import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.MailBox.PortListEmitterMailBox;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortListEmitterRPC;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem.PortListEmitterSimpleVMDataport;

/****************************************************
 * 
 * @author whalen
 *
 * This is a holding place for the code that will be 
 * called at various times during .  
 * 
 ****************************************************/
public class EmitterListRegistry {
   
   static private List<PortListEmitter> portListEmitters;
   
   // static initializer...add your PortListEmitter here!
   static {
      portListEmitters = new ArrayList<>();
      portListEmitters.add(new PortListEmitterRPC());
//      portListEmitters.add(new PortListEmitterMailBox());
   }

   static public final List<PortListEmitter> getPortListEmitters() {
      return portListEmitters;
   }

   static private List<PortListEmitterCamkesVM> camkesPortListEmitters;
   static {
      camkesPortListEmitters = new ArrayList<>();
      camkesPortListEmitters.add(new PortListEmitterSimpleVMDataport());
   }
   
   static public final List<PortListEmitterCamkesVM> getVMPortListEmitters() {
      return camkesPortListEmitters;
   }
   
   static public void addPortListEmitter(PortListEmitter pe) {
      portListEmitters.add(pe);
   }
   
}
