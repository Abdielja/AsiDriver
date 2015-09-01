/**
 * 
 */
package com.asi.driver.processor;

import java.util.LinkedHashMap;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public interface IResponseProcessor
{

  public String process(LinkedHashMap<String, Object> hmResponse);
  
}
