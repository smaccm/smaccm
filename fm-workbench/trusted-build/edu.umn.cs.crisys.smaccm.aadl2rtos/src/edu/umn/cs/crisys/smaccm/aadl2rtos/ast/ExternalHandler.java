package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

public class ExternalHandler {

	private String handlerName;
	private String handlerFileName;

	public ExternalHandler(String entryPointFunctionName, String entryPointSourceText) {
		this.handlerName = entryPointFunctionName;
		this.handlerFileName = entryPointSourceText;
	}
	
	public String getHandlerName() {
	  return this.handlerName;
	}

  /**
   * @return the handlerFileName
   */
  public String getHandlerFileName() {
    return handlerFileName;
  }	
	
}
