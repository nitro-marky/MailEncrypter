/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.MailException;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * Interface implemented by the message email connecter.
 */
public interface IEmailAccountConnection {
    
    /**
     * 
     * @param email User's email address to connect to
     * @param password For the email account
     * @return Active email session
     * @throws MessagingException
     * @throws MailException 
     */
    Session connectAccount(String email, String password) throws MessagingException, MailException ;
    
    /**
     * 
     * @return Boolean denoting connection status 
     */
    boolean getConnectionStatus();
    
}
