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
 * Interface implemented by the message deleter
 */
public interface IMessageDeleter {
    
    /**
     * 
     * @param subject Of the message to be deleted
     * @param session Current email session
     * @param email Account to delete from
     * @param pass Authenticates the account
     * @throws MessagingException
     * @throws MailException 
     */
    void deleteMessage(String subject,Session session, String email, String pass) throws MessagingException, MailException;
}
