/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.Session;

/**
 *
 * Interface implemented by the message sender.
 */
public interface IMessageSender {
    
    /**
     * 
     * @param session The active mail session to send with
     * @param emailAddress The account address
     * @param recipient Sending to
     * @param message The body of the email
     * @param subject The title of the email
     * @param keyID The encryption key identifier
     * @return boolean denoting if the message was sent
     * @throws UnsupportedEncodingException
     * @throws MessagingException 
     */
    boolean sendMessage(Session session,String emailAddress, String recipient, String message, String subject, String keyID)throws UnsupportedEncodingException, MessagingException;
}
