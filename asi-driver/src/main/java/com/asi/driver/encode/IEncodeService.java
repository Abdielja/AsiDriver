/**
 * 
 */
package com.asi.driver.encode;

/**
 * @author Abdiel
 *
 */
public interface IEncodeService
{

  public String encode(byte[] rawBytes); 

  public byte[] decode(byte[] rawBytes);
  
}
