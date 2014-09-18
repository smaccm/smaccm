/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;

/**
 * @author Whalen
 *
 */
public class CAmkESSTErrorListener implements STErrorListener {

  Logger log; 
  
  public CAmkESSTErrorListener(Logger log) {
    this.log = log;
  }
  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#IOError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void IOError(STMessage arg0) {
    System.out.println(arg0); 
    log.info(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#compileTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void compileTimeError(STMessage arg0) {
    // TODO Auto-generated method stub
    System.out.println(arg0);
    log.info(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#internalError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void internalError(STMessage arg0) {
    // TODO Auto-generated method stub
    System.out.println(arg0);
    log.info(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#runTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void runTimeError(STMessage arg0) {
    // TODO Auto-generated method stub
    System.out.println(arg0);
    log.info(arg0);
  }

}
