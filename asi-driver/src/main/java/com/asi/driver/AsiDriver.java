/**
 * 
 */
package com.asi.driver;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.MapPropertySource;

import com.asi.driver.config.AppSettings;
import com.asi.driver.encryption.EncryptionService;

/**
 * @author Abdiel Jaramillo Ojedis
 *
 */
public class AsiDriver
{
  
  private static final Logger LOGGER = Logger.getLogger(AsiDriver.class);
  private GenericXmlApplicationContext context;
  
  public AsiDriver()
  {
  }
  
  public void start()
  {
    LoadApplicationContext();
    InitDriver();
    InitDriverConsole();
  }
  
  public void LoadApplicationContext()
  {
    context = setupContext();  
    context.registerShutdownHook();

    AppSettings.print();
    
  }
  
  public void InitDriver()
  {
    
    System.out.println();
    
    if (LOGGER.isInfoEnabled())
    {
      LOGGER.info(
                    "\n========================================================="
                  + "\n                                                         "
                  + "\n          ASI-Driver started succesfully                 "
                  + "\n                                                         "
                  + "\n         Waiting for incoming messages...                "
                  + "\n                                                         "
                  + "\n========================================================="
                );
    }

    System.out.println();

  }
  
  public void InitDriverConsole()
  {
    
    final Scanner scanner = new Scanner(System.in);

    showHelp();
    
    System.out.println();
    System.out.println();

    while (true)
    {

      showPrompt();
      
      final String input = scanner.nextLine();
      
      if ("test".equals(input.trim()))
      {
        doTest();
      }
      else if ("q".equals(input.trim()))
      {
        break;
      }
      else if ("keygen".equals(input.trim()))
      {
        EncryptionService es = (EncryptionService) context.getBean(EncryptionService.class);
        try
        {
          es.keyGen("DES");
          System.out.println();
          LOGGER.info("Secret Key generated to file 'keyFile'.");
        } 
        catch (NoSuchAlgorithmException e)
        {
          LOGGER.error(e);
        }
      }
      else
      {
        System.out.println("Invalid Command.");        
      }
    }
    
    System.out.println("\nASI-Driver has been shut down.");
 
    context.close();
    
    scanner.close();
    
    System.exit(0);

  }
  
  private void doTest()
  {

      System.out.println("\tTest OK.");

  }

  public void showPrompt()
  {
    System.out.print("ASI-Driver>");          
  }
  
  public void showHelp()
  {
    
    System.out.println(
                        "\n========================================================="
                      + "\n                                                         "
                      + "\n    Please press 'q + Enter' to quit the application.    "
                      + "\n                                                         "
                      + "\n========================================================="
                    );

  }
  
  public GenericXmlApplicationContext setupContext() 
  {
  
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();

    LOGGER.info("Detect open server socket...");
    int availableServerSocket = 1963;

    final Map<String, Object> sockets = new HashMap<String, Object>();
    sockets.put("availableServerSocket", availableServerSocket);

    final MapPropertySource propertySource = new MapPropertySource("sockets", sockets);

    context.getEnvironment().getPropertySources().addLast(propertySource);

    LOGGER.info("using port " + context.getEnvironment().getProperty("availableServerSocket"));

    context.load("classpath:META-INF/spring/integration/spring-integration-context-tcp.xml");
    context.registerShutdownHook();
    context.refresh();

    return context;
  }
    
}
