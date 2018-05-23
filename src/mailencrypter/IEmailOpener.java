/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 *
 * Interface implemented by the message opener.
 */
public interface IEmailOpener {
    
    /**
     * 
     * @param index Position of the email in the list
     * @param messages The current email list
     * @return Hashmap containing the email information
     * @throws MessagingException
     * @throws IOException 
     */
    HashMap openSelectedEmail(int index, List<Message> messages) throws MessagingException, IOException;
}
