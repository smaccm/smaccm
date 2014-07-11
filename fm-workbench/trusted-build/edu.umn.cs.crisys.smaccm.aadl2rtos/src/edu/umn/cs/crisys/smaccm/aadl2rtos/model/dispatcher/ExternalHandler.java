package edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher;

public class ExternalHandler {

	private String handlerName;
	private String optHandlerFileName;

	public ExternalHandler(String entryPointFunctionName, String entryPointSourceText) {
		this.handlerName = entryPointFunctionName;
		this.optHandlerFileName = entryPointSourceText;
	}
	
	public String getHandlerName() {
	  return this.handlerName;
	}

  /**
   * @return the optHandlerFileName
   */
  public String getOptHandlerFileName() {
    return optHandlerFileName;
  }	
	
}
