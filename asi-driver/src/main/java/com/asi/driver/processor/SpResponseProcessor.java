/**
 * 
 */
package com.asi.driver.processor;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class SpResponseProcessor
{

  /* *******************************************
   * 
   *  Private Class Members
   *  
   * *******************************************/
  
  @SuppressWarnings("unused")
  private static final Logger LOGGER = Logger.getLogger(SpResponseProcessor.class);
 
  /**
   * 
   */
  public SpResponseProcessor()
  {
  }

  @SuppressWarnings("unused")
  public String process(LinkedHashMap<String, Object> hmResponse)
  {
   
    String response = "";
    String error = "";
    
    error = (String) hmResponse.get("Pv_CodigoError");   
    response = (String) hmResponse.get("Pv_MsgReqRes");

    return response;
  }
}
