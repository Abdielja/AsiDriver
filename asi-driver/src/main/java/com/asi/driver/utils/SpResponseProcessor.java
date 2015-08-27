/**
 * 
 */
package com.asi.driver.utils;

import java.util.LinkedHashMap;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class SpResponseProcessor
{

  /**
   * 
   */
  public SpResponseProcessor()
  {
  }

  public String process(LinkedHashMap<String, Object> hmResponse)
  {
   
    String response = "";
    String error = "";
    
    error = (String) hmResponse.get("Pv_CodigoError");   
    response = (String) hmResponse.get("Pv_MsgReqRes");

    return response;
  }
}
