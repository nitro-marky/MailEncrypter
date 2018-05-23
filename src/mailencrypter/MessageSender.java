/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * This class is responsible for sending an email.
 */
public class MessageSender implements IMessageSender {
    
    
    /**
     * 
     * @param session The current email session
     * @param emailAddress The senders email
     * @param recipient Who its going to
     * @param message The message body
     * @param subject The message subject
     * @param keyID The key id used to encrypt
     * @return Boolean denoting if the message has been sent
     * The session used to connect to the server is passed through to allow the message to be sent. A new message is created and the 
     * supplied data to assigned to the relevant position. The key id is added to the subject so the recipient knows which key was used to 
     * encrypt. "Encrypted" is also appended to allow for message searching purposes.
     */
    @Override
    public boolean sendMessage(Session session,String emailAddress, String recipient, String message, String subject, String keyID) throws UnsupportedEncodingException, MessagingException
    {
        try
        {
            Message _message = new MimeMessage(session);
            _message.setFrom(new InternetAddress(emailAddress));
            _message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            String _newSubject = String.format("Encrypted: %s : %s",(keyID),subject);
            _message.setSubject(_newSubject);
            _message.setText(message);
            Transport.send(_message);
   
            return true;
        }
        catch(MessagingException e)
        {
            
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
        
    }
    
    
}
