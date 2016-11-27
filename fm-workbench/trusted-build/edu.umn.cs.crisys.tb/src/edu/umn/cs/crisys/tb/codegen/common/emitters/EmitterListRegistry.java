package edu.umn.cs.crisys.tb.codegen.common.emitters;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC.PortListEmitterRPC;

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
   }

   static public final List<PortListEmitter> getPortListEmitters() {
      return portListEmitters;
   }
   
   static public void addPortListEmitter(PortListEmitter pe) {
      portListEmitters.add(pe);
   }
   
}
