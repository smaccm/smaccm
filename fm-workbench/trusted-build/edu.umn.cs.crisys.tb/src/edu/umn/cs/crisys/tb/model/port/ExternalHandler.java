package edu.umn.cs.crisys.tb.model.port;

public class ExternalHandler {

	private String handlerName;
//	private String optHandlerFileName;

	public ExternalHandler(String entryPointFunctionName) {
		this.handlerName = entryPointFunctionName;
//		this.optHandlerFileName = entryPointSourceText;
	}
	
	public String getHandlerName() {
	  return this.handlerName;
	}

  /**
   * @return the optHandlerFileName
   */
//  public String getOptHandlerFileName() {
//    return optHandlerFileName;
//  }	

	public int hashCode() {
	  return 0;
	}
}
