/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;

import Exceptions.MailException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

/**
 *
 * The interface implemented by the email model. Enforces email specific functions.
 */
public interface IEmailModel {
    
    /**
     * 
     * @param email Address to connect to
     * @param password Password to authenticate account
     * @throws MessagingException
     * @throws MailException 
     */
    void setAccount(String email, String password) throws MessagingException, MailException;
    
    /**
     * 
     * @throws MessagingException
     * @throws MailException 
     */
    void checkMessages() throws MessagingException, MailException;
    
    /**
     * 
     * @param recipient Who the message is going to
     * @param message The body of the email
     * @param subject The email subject
     * @param keyID The ID of the key used to encrypt
     * @throws UnsupportedEncodingException
     * @throws MessagingException 
     */
    void sendMessage(String recipient, String message, String subject, String keyID) throws UnsupportedEncodingException, MessagingException;
    
    /**
     * 
     * @param index The location of the email in the list
     * @throws MessagingException
     * @throws IOException 
     */
    void openSelectedEmail(int index) throws MessagingException, IOException;
   
    /**
     * 
     * @param id The location of the email in the list
     * @throws MessagingException
     * @throws MailException 
     */
    void deleteMessage(int id) throws MessagingException, MailException;
    
}
