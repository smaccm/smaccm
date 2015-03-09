/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.lang.reflect.InvocationTargetException;

import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;

/**
 * @author Whalen
 *
 */
public class CAmkESSTErrorListener implements STErrorListener {

  Logger log; 
  
  public String userCodeException(STMessage arg) {
    Throwable t = arg.cause;
    if (t instanceof STNoSuchPropertyException) {
      STNoSuchPropertyException exp = (STNoSuchPropertyException)t;
      if (exp.getCause() != null && exp.getCause() instanceof InvocationTargetException) {
        InvocationTargetException ite = (InvocationTargetException)exp.getCause();
        return "User Code Exception: " + ite.getCause();
      }
    }
    return "<Unknown>";
  }
  
  public CAmkESSTErrorListener(Logger log) {
    this.log = log;
  }
  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#IOError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void IOError(STMessage arg0) {
    // System.out.println(arg0); 
    log.error(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#compileTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void compileTimeError(STMessage arg0) {
    // TODO Auto-generated method stub
    // System.out.println(arg0);
    log.error(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#internalError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void internalError(STMessage arg0) {
    // TODO Auto-generated method stub
    // System.out.println(arg0);
    log.error(arg0);
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#runTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void runTimeError(STMessage arg0) {
    // TODO Auto-generated method stub
    // System.out.println(arg0);
    log.error("StringTemplate exception: " + arg0.cause + " due to: " + userCodeException(arg0));
    log.error("Error at: " + arg0.arg + ", " + arg0.arg2 + ", " + arg0.arg3);
  }

}
