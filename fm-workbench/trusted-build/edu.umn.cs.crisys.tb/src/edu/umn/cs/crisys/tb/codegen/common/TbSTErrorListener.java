/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common;

import java.lang.reflect.InvocationTargetException;

import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.misc.STMessage;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;

import edu.umn.cs.crisys.tb.Logger;

/**
 * @author Whalen
 *
 */
public class TbSTErrorListener implements STErrorListener {

  Logger log;
  boolean errorOccurred = false;
  
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
  
  public TbSTErrorListener(Logger log) {
    this.log = log;
  }
  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#IOError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void IOError(STMessage arg0) {
    log.error(arg0);
    errorOccurred = true;
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#compileTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void compileTimeError(STMessage arg0) {
    log.error(arg0);
    errorOccurred = true;
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#internalError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void internalError(STMessage arg0) {
    log.error(arg0);
    errorOccurred = true;
  }

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.STErrorListener#runTimeError(org.stringtemplate.v4.misc.STMessage)
   */
  @Override
  public void runTimeError(STMessage arg0) {
    log.error("StringTemplate exception: " + arg0.cause + " due to: " + userCodeException(arg0));
    log.error(arg0);
    errorOccurred = true;
  }

  public boolean isErrorOccurred() {
    return errorOccurred;
  }

  public void setErrorOccurred(boolean errorOccurred) {
    this.errorOccurred = errorOccurred;
  }

}
