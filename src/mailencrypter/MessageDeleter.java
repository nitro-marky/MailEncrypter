/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.MailException;
import Model.EmailModel;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

/**
 *
 * This class deletes a message from the Outlook inbox.
 */
public class MessageDeleter implements IMessageDeleter {
    
    private Properties _inboxProps;
    private SearchTerm _search;
    
    /**
     * 
     * This method opens a new IMAP connection and looks for a message matching the subject provided.
     * Once found it is deleted from the inbox.
     * @param subject Of the message to be deleted
     * @param session Current mail session
     * @param email Email address
     * @param pass Email password
     * @throws javax.mail.MessagingException
     * @throws Exceptions.MailException
     */
    @Override
    public void deleteMessage(String subject,Session session, String email, String pass) throws MessagingException, MailException
    {
        //Create new search term
        createSearch(subject);
        try
        {
            _inboxProps = new Properties();
            _inboxProps.put("mail.imap.starttls.enable", true);
            _inboxProps.put("mail.imap.auth", true);
            _inboxProps.put("mail.imap.ssl.trust", "*");
            _inboxProps.put("mail.imap.ssl.enable", true);
            _inboxProps.put("mail.imap.auth.plain.disable", true);
            _inboxProps.put("mail.imap.auth.ntlm.disable", true);

            Session mailSession = javax.mail.Session.getInstance(_inboxProps);
            mailSession.setDebug(true);

            Store store = mailSession.getStore("imap");

            store.connect("outlook.office365.com", 993, email, pass);
 
            // Open the encryption inbox folder with read and write capabilities
            Folder folderInbox = store.getFolder("ENCRYPTED");
            folderInbox.open(Folder.READ_WRITE);      
            
            
            Message[] _messages;
            
            _messages = folderInbox.search(_search);
            
            //If the message is found the flag is set to deleted.
            for (int i = 0; i < _messages.length; i++) {
                _messages[i].setFlag(Flags.Flag.DELETED, true);
            }    
            
            folderInbox.close(true);

        }
        catch(MessagingException e)
        {
            throw new MailException("DELETE_ERROR");
        }
            
    }
       
    /**
     * 
     * @param name Name to be found in the search
     * Takes a name parameter which is incorporated into the search. If the message contains the parameter
     * it is flagged for deletion.
     */   
    private void createSearch(String name)
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
                else if(subject.contains(name))
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
    
    

