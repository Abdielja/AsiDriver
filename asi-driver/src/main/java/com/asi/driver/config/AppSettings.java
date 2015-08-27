package com.asi.driver.config;


/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class AppSettings
{
 
  private static String charset;
  private static String port;
  private static String encryptionAlgorithm;
  
  /**
   * @return the charset
   */
  public static String getCharset()
  {
    return charset;
  }

  
  /**
   * @param charset the charset to set
   */
  public static void setCharset(String _charset)
  {
    charset = _charset;
  }


  /**
   * @return the port
   */
  public static String getPort()
  {
    return port;
  }


  /**
   * @param port the port to set
   */
  public static void setPort(String port)
  {
    AppSettings.port = port;
  }
  
  /**
   * @return the encryptionAlgorithm
   */
  public static String getEncryptionAlgorithm()
  {
    return encryptionAlgorithm;
  }
 
  /**
   * @param encryptionAlgorithm the encryptionAlgorithm to set
   */
  public static void setEncryptionAlgorithm(String encryptionAlgorithm)
  {
    AppSettings.encryptionAlgorithm = encryptionAlgorithm;
  }
  
}
