/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Controller.Controller;

/**
 *
 * The main program which creates the controller.
 */
public class MailEncrypter {

    private static IEncryptionKey _testKey;
    
    /**
     * Gets the singleton instance of the controller
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Controller controller = Controller.getInstance(); 
    }
    
}
