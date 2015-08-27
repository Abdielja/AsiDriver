/**
 * 
 */
package com.asi.driver.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class TestService
{

  public TestService()
  {
    
  }
  
  public byte[] delay(byte[] message)
  {
    
//    Random rand = new Random();  
//    int _delay = rand.nextInt(5000) + 5000;

    int _delay = 10000;

/*    
    try
    {
      //System.out.println("Received message: (bytes)" + message);
      //System.out.println("Received message: (text)" + new String(message, "ISO-8859-15"));
    } 
    catch (UnsupportedEncodingException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
*/
    System.out.println("Processing thread " + Thread.currentThread().getId() + ". Please wait...");

    try
    {
      Thread.sleep(_delay);
    } catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    String textProcessed = new String(message) + " was delayed for " + _delay/1000 + " seconds";

    System.out.println("Thread " + Thread.currentThread().getId() + " processed.");
    //System.out.println(textProcessed);

    byte[] bytesProcessed = null;
    
    try
    {
      bytesProcessed = textProcessed.getBytes("ISO-8859-15");
    } 
    catch (UnsupportedEncodingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return bytesProcessed;
  }
  
}
