/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Controller;

import Exceptions.MailException;

/**
 *
 * The email strategy provides the view with methods it can call to pass certain email related information.
 */
public interface IEmailStrategy extends IStrategy {

    /**
     * 
     * @param email Address to connect to
     * @param password Password for authentication
     * @throws MailException 
     */
    void setEmailAttributes(String email, String password)throws MailException;
    
    /**
     * 
     * @param recipient Who the message is being sent to
     * @param message The body of the email
     * @param subject The subject of the email
     * @param keyID The ID of the key used to encrypt
     * @throws MailException 
     */
    void sendEmail(String recipient, String message, String subject, String keyID)throws MailException;
    
    /**
     * 
     * @param index Location of the email to open in the list
     */
    void openSelectedEmail(int index);
    
    /**
     * 
     * @param id Location of the email to delete in the list
     * @throws MailException 
     */
    void deleteMessage(int id) throws MailException;
}
