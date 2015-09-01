
package com.asi.driver.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.Transformer;

import com.asi.driver.config.AppSettings;

public class EncryptionService implements IEncryptionService
{

  private static final Logger LOGGER = Logger.getLogger(EncryptionService.class);
   
  @Override
  public void keyGen(String algorithm) throws NoSuchAlgorithmException
  {
       
    try 
    {
      
      KeyGenerator kg = KeyGenerator.getInstance(algorithm);
      kg.init(new SecureRandom());
      SecretKey key = kg.generateKey();
    
      SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);

      if ("DES".equals(algorithm))
      {
        Class<?> spec = Class.forName("javax.crypto.spec.DESKeySpec");
        DESKeySpec ks = (DESKeySpec) skf.getKeySpec(key, spec);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("keyfile"));
        oos.writeObject(ks.getKey());
        oos.flush();
        oos.close();
      }
    
    } 
    catch (Exception e) 
    {
      LOGGER.error(e);
    }    
  }

  @Override
  public SecretKey getSecretKey(String algorithm, String fileName)
  {
    
    SecretKey key = null;
    
    try
    {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
      
      DESKeySpec ks;
      ks = new DESKeySpec((byte[]) ois.readObject());
      
      ois.close();
      
      SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
      key = skf.generateSecret(ks);
    } 
    catch (InvalidKeyException | ClassNotFoundException | IOException e)
    {
      LOGGER.error(e);
    } 
    catch (NoSuchAlgorithmException e)
    {
      LOGGER.error(e);
    } 
    catch (InvalidKeySpecException e)
    {
      LOGGER.error(e);
    }

    return key;
  }
  
  @Override
  @Transformer
  public byte[] encrypt(String stringToEncrypt)
  {

    byte[] textEncrypted = null;
    byte[] bytesToEncrypt = null;
    
    try
    {
      
      bytesToEncrypt = stringToEncrypt.getBytes(AppSettings.getCharset());
      
      // Get SecretKey
      SecretKey key = getSecretKey("DES", "keyFile");

      // Create the cipher
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

      // Initialize the cipher for encryption
      cipher.init(Cipher.ENCRYPT_MODE, key);

      // Encrypt the text
      textEncrypted = cipher.doFinal(bytesToEncrypt);

      //System.out.println("Text Encrypted : " + textEncrypted);

    }
    catch (NoSuchAlgorithmException e)
    {
      LOGGER.error(e);
    } 
    catch (NoSuchPaddingException e)
    {
      LOGGER.error(e);
    } 
    catch (InvalidKeyException e)
    {
      LOGGER.error(e);
    } 
    catch (IllegalBlockSizeException e)
    {
      LOGGER.error(e);
    } 
    catch (BadPaddingException e)
    {
      LOGGER.error(e);
    } catch (UnsupportedEncodingException e)
    {
      LOGGER.error(e);
    } 
    
    return textEncrypted;

  }
  
  @Override
  @Transformer
  public String decrypt(byte[] textEncrypted) throws UnsupportedEncodingException
  {
  
    byte[] textDecrypted = null;
    String stringDecrypted = null;
    
    try
    {
      
      // Get SecretKey
      SecretKey key = getSecretKey("DES", "keyFile");

      // Create the cipher
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

      // Initialize the same cipher for decryption
      cipher.init(Cipher.DECRYPT_MODE, key);
    
      // Decrypt the text
      textDecrypted = cipher.doFinal(textEncrypted);
    
      stringDecrypted = new String(textDecrypted, AppSettings.getCharset());
      //System.out.println("Text Decrypted : " + new String(textDecrypted, AppSettings.getCharset()));
  
    } 
    catch (NoSuchAlgorithmException e)
    {
      LOGGER.error(e);
    } 
    catch (NoSuchPaddingException e)
    {
      LOGGER.error(e);
    } 
    catch (InvalidKeyException e)
    {
      LOGGER.error(e);
    } 
    catch (IllegalBlockSizeException e)
    {
      LOGGER.error(e);
    } 
    catch (BadPaddingException e)
    {
      LOGGER.error(e);
    }
  
    return stringDecrypted;
    
  }
   
}