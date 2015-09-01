/**
 * 
 */
package com.asi.driver.encode;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.Transformer;

import com.asi.driver.config.AppSettings;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class EncodeServiceHex implements IEncodeService
{

  private static final Logger LOGGER = Logger.getLogger(EncodeServiceHex.class);

  @Override
  @Transformer
  public String encode(byte[] rawBytes) 
  {    

    String encryptedMessageOutHex = null;

    // *** Convert encrypted byte array to Hex String ***
    encryptedMessageOutHex = DatatypeConverter.printHexBinary(rawBytes);
    //System.out.println("Text Encoded/Encrypted/Hex: " + encryptedMessageOutHex);    
       
    return encryptedMessageOutHex;
  } 

  @Override
  @Transformer
  public byte[] decode(byte[] rawBytes)
  {

    String hexString = null;
    
    try
    {
      hexString = new String(rawBytes, AppSettings.getCharset());
      if(hexString.charAt(hexString.length()-1) == '\r')
      {
        String tempString = hexString.substring(0, hexString.length()-1 );
        hexString = tempString;
      }
    } 
    catch (UnsupportedEncodingException e)
    {
      // TODO Auto-generated catch block
      LOGGER.error(e);
    }
    
    byte[] hexBytes = DatatypeConverter.parseHexBinary(hexString);
    
    return hexBytes;
    
  }

}
