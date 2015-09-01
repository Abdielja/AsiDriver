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
  private static String encryptionMode;
  private static String encryptionPadding;
  
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
  
  
  /**
   * @return the encryptionMode
   */
  public static String getEncryptionMode()
  {
    return encryptionMode;
  }


  /**
   * @param encryptionMode the encryptionMode to set
   */
  public static void setEncryptionMode(String encryptionMode)
  {
    AppSettings.encryptionMode = encryptionMode;
  }


  /**
   * @return the encryptionPadding
   */
  public static String getEncryptionPadding()
  {
    return encryptionPadding;
  }


  /**
   * @param encryptionPadding the encryptionPadding to set
   */
  public static void setEncryptionPadding(String encryptionPadding)
  {
    AppSettings.encryptionPadding = encryptionPadding;
  }


  public static void print()
  {
    
    StringBuilder sb = new StringBuilder();
    
    sb.append("\n");
    sb.append("Application Settings (asi-driver.properties):\n");
    sb.append("  General\n");
    sb.append("    Port:    " + AppSettings.getPort() + "\n");
    sb.append("  Encoding" + "\n");
    sb.append("    Charset: " + AppSettings.getCharset() + "\n");
    sb.append("  Encryption " + "\n");
    sb.append("    Algorithm: " + AppSettings.getEncryptionAlgorithm() + "\n");
    sb.append("    Mode:      " + AppSettings.getEncryptionMode() + "\n");
    sb.append("    Padding:   " + AppSettings.getEncryptionPadding() + "\n");
    
    System.out.println(sb.toString());
  }


}
