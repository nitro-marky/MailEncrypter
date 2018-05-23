/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.MailException;
import Model.EmailModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

/**
 *
 * The message checker searches the inbox and collects the messages in the Encrypted folder using an
 * IMAP connection which allows access to the messages on the server.
 */
public class MessageChecker implements IMessageChecker {
    
    private List<Message> _messageList;
    private Properties _inboxProps;
    private Message[] _messages;
    private SearchTerm _search;
    private Session _mailSession;
    
    /**
     * Constructor for the MessageChecker. Initialises the search term creation and 
     * the message array list.
     */
    public MessageChecker()
    {
        createSearch();
        _messageList = new ArrayList<>();
    }
    
    
    /**
     * Opens an IMAP connection which allows access to emails on the server without needing to download.
     * @param email The email address to connect with
     * @param pass The email password to authenticate with
     * @return List of current emails
     * @throws javax.mail.MessagingException
     * @throws Exceptions.MailException
     */
    @Override
    public List<Message> checkMessages(String email, String pass) throws MessagingException, MailException
    {
        
        try
        {
            //Sets the connection properties which use TLS and SSL authentication.
            _inboxProps = new Properties();
            _inboxProps.put("mail.imap.starttls.enable", true);
            _inboxProps.put("mail.imap.auth", true);
            _inboxProps.put("mail.imap.ssl.trust", "*");
            _inboxProps.put("mail.imap.ssl.enable", true);
            _inboxProps.put("mail.imap.auth.plain.disable", true);
            _inboxProps.put("mail.imap.auth.ntlm.disable", true);

            //Gets the instance of the mail session using the properties above
            Session _mailSession = javax.mail.Session.getInstance(_inboxProps);
            _mailSession.setDebug(true);

            Store store = _mailSession.getStore("imap");

            store.connect("outlook.office365.com", 993, email, pass);
 
            //Reads emails from the Encrypted folder in the inbox
            Folder folderInbox = store.getFolder("ENCRYPTED");
            folderInbox.open(Folder.READ_ONLY);      
            
            /**
             * 
             * Searches the inbox with a predefined search term and returns the relevant messages. It can only store messages
             * in an array.
             */
            _messages = folderInbox.search(_search);
            _messageList.clear();
 
            
            /**
             * The message array is iterated through and the each message is added to a list for 
             * additional functionality over an array.
             */
            for (int i = 0; i < _messages.length; i++) {
                Message message = _messages[i];
                _messageList.add(message);
            }
        }
        catch(MessagingException e)
        {
            throw new MailException("INBOX_ERROR");
        }
            
        return _messageList;
    }
    
    /**
     * Returns the current mail session.
     * @return Current Mail Session
     */
    @Override
    public Session getMessageSession()
    {
        return _mailSession;
    }
    
    
    /**
     * Creates a search term used to search the inbox. It is currently set up to look for messages
     * which include encrypted in the subject. The current software configuration appends encrypted to 
     * each message sent so it can be searched for here.
     */
    private void createSearch()
    {
        _search = new SearchTerm() 
        {

            @Override
            public boolean match(Message msg) 
            {
                String subject = null;
                try 
                {
                    subject = msg.getSubject();
                } 
                catch (MessagingException ex) 
                {
                    Logger.getLogger(EmailModel.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(subject == null)
                {
                    return false;
                }
                else if(subject.contains("Encrypted"))
                {
                    return true;
                }
                else
                {     
                    return false;
                }
            }   
        }; 
    }
    
}
