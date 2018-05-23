/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.MailException;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * Interface implemented by the message checker.
 */
public interface IMessageChecker {
    
    /**
     * 
     * @param email Account to check
     * @param pass Authenticates the account
     * @return List of current messages
     * @throws MessagingException
     * @throws MailException 
     */
    List<Message> checkMessages(String email, String pass) throws MessagingException, MailException;
    
    /**
     * 
     * @return active email session 
     */
    Session getMessageSession();
}
