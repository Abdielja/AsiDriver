/**
 * 
 */
package com.asi.driver.encryption;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

/**
 * @author Abdiel Jaramillp Ojedis
 *
 */
public interface IEncryptionService
{

  public void keyGen(String algorithm) throws NoSuchAlgorithmException;
  
  public SecretKey getSecretKey(String algorithm, String fileName);

  public byte[] encrypt(String textToEncrypt);
  
  public String decrypt(byte[] textEncrypted) throws UnsupportedEncodingException;
  
}
