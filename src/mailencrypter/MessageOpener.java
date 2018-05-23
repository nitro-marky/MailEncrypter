/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import View.IEmailObserver;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 *
 * The message opener opens a selected email and returns the relevant information.
 */
public class MessageOpener implements IEmailOpener {
    
    private HashMap _mailAttributes;
    
    /**
     * Constructor for the MessageOpener. Initialises the hash map.
     */
    public MessageOpener()
    {
        _mailAttributes = new HashMap();
    }
    
    /**
     * 
     * The message with the corresponding index is found and the subject, message, date and sender are put into 
     * a hash map. This was chosen as it was easier to store multiple pieces of information which can easily be
     * retrieved.
     * @param index Position of the message from the JList
     * @param messages Current list of messages
     * @return Hashmap containing the selected email attributes such as message and date
     */
    @Override
    public HashMap openSelectedEmail(int index, List<Message> messages) throws MessagingException, IOException
    {   
        Message _tempMessage = messages.get(index);
           
        String _tempSubject = _tempMessage.getSubject();
        _mailAttributes.put("subject", _tempSubject);
        
        String _from = Arrays.toString(_tempMessage.getFrom());
        _mailAttributes.put("from", _from);
        
        String _date = _tempMessage.getReceivedDate().toString();
        _mailAttributes.put("date", _date);
        
        String _tempMessageText = "";
        Object content = _tempMessage.getContent();
         _tempMessageText = content.toString();
        _mailAttributes.put("message", _tempMessageText);
        
        return _mailAttributes;
    }
    
    
}
