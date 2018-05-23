/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package View;

import java.util.List;
import javax.mail.Message;

/**
 *
 * The observer interface implemented by email observers.
 */
public interface IEmailObserver {
    
    /**
     * 
     * @param emails List of current emails
     */
    void UpdateEmails(List<Message> emails);
    
    /**
     * 
     * @param connected Current connection status
     */
    void UpdateStatus(boolean connected);
    
    /**
     * 
     * @param isSent Has the message been sent
     */
    void UpdateSendStatus(boolean isSent);
    
    /**
     * 
     * @param message The body of the message
     * @param subject The title of the message
     * @param from Who sent the message
     * @param date When the message was sent
     */
    void UpdateSelectedEmail(String message, String subject, String from, String date);
    
}
