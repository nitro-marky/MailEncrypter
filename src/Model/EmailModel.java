/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;
import Exceptions.MailException;
import View.IEmailObserver;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import mailencrypter.EmailAccountConnection;
import mailencrypter.MessageOpener;
import mailencrypter.IEmailAccountConnection;
import mailencrypter.IEmailOpener;
import mailencrypter.IMessageChecker;
import mailencrypter.IMessageDeleter;
import mailencrypter.IMessageSender;
import mailencrypter.MessageChecker;
import mailencrypter.MessageDeleter;
import mailencrypter.MessageSender;

/**
 *
 * The email model contains all of the data relating to an email, this includes account set up, email sending, email checking and deletion.
 */
public class EmailModel implements IEmailModel, IEmailModelObservable {
    
    //The utilities used to carry out data manipulation
    private IMessageSender _sender;
    private IEmailAccountConnection _connecter;
    private IMessageChecker _checker;
    private IEmailOpener _opener;
    
    //Fields that contain email specific information such as email address, password and mail session.
    private String _emailAddress;
    private String _password;
    private Session _emailSession;
    private Session _mailSession;
    private Properties _properties;
    private List<Message> _messageList;
          
    //A list that contains all of the email related observers.
    private List<IEmailObserver> _emailObservers;
    
    //The helper modules and certain fields are intitialised in the constructor to avoid null pointer exceptions.
    public EmailModel()
    {
       _sender = new MessageSender();
       _connecter = new EmailAccountConnection();
       _checker = new MessageChecker();
       _opener = new MessageOpener();
       
       _properties = new Properties();
       _emailObservers = new ArrayList<>();
       _messageList = new ArrayList<>();
    }
    
    /**
     * 
     * SetAccount records the user input and creates a mail session by passing the email and password to 
     * the connector which returns the mail session. The observers are notified about whether the connection
     * was successful
     * @param email Users email address
     * @param password Users email password
     * @throws javax.mail.MessagingException
     * @throws Exceptions.MailException
     */
    @Override
    public void setAccount(String email, String password) throws MessagingException, MailException
    {
        _emailAddress = email;
        _password = password;
        _emailSession = _connecter.connectAccount(email, password);
        UpdateEmailStatus(_connecter.getConnectionStatus());
     }

    
    /**
     * 
     * Clears the current list of messages then retrieves the new list from the checker. The observers are notified with the 
     * new list of emails.
     * @throws javax.mail.MessagingException
     * @throws Exceptions.MailException
     */
    @Override
    public void checkMessages() throws MessagingException, MailException
    {
        _messageList.clear();
        _messageList = _checker.checkMessages(_emailAddress, _password);
        _mailSession = _checker.getMessageSession();
        UpdateEmailObservers();
    }
    
    /**
     * 
     * @param recipient Who the message is being sent to
     * @param message The body of the message
     * @param subject The subject title
     * @param keyID Reference to the key that encrypted the message
     * The send method first checks that there is a current valid email session, if so the email values are passed to the sender.
     */
    @Override
    public void sendMessage(String recipient, String message, String subject, String keyID) throws UnsupportedEncodingException, MessagingException
    {
        if(_emailSession != null)
        {
           boolean _sent =  _sender.sendMessage(_emailSession, _emailAddress, recipient, message, subject, keyID);
           UpdateEmailSentStatus(_sent);
        }
    }
    
    /**
     * 
     * @param index Position of the message in the JList.
     * The email opener returns a hash map which contains the requested email's details. The observers are then passes the 
     * email information for displaying in the view.
     */
    @Override
    public void openSelectedEmail(int index) throws MessagingException, IOException
    {           
        HashMap _contents = _opener.openSelectedEmail(index, _messageList);
         
        String _date = _contents.get("date").toString();
        String _from = _contents.get("from").toString();
        String _message = _contents.get("message").toString();
        String _subject = _contents.get("subject").toString();
         
        for (IEmailObserver observer : _emailObservers) 
        {
            observer.UpdateSelectedEmail(_message,_subject , _from, _date);      
        }
         
    }
    
    
    /**
     * 
     * @param id Message index from JList
     * The index of the message is collected from the JList and checked to see if is valid, if not an exception is thrown.
     * The index is used to retrieve the message subject which is sent to the deleter. The current deletion system is based on subjects
     * but could be expanded. Once the messages are deleted the check message method is called to update the list.
     * @throws javax.mail.MessagingException
     * @throws Exceptions.MailException
     */
    @Override
    public void deleteMessage(int id) throws MessagingException, MailException
    {
        if(id < 0 || id > _messageList.size())
        {
             throw new MailException("FOLDER_ERROR");
        }
        else
        {
            String subject = _messageList.get(id).getSubject();
            IMessageDeleter _delete = new MessageDeleter();
            _delete.deleteMessage(subject, _mailSession, _emailAddress, _password);
            try {
                checkMessages();
            } catch (MailException ex) 
            {
                throw new MailException("DELETE_ERROR");
            }
        }
    }
    
    /**
     * 
     * Adds an email observer to the list. 
     * @param o Email Observer
     */
    @Override
    public void AddEmailObservers(IEmailObserver o) 
    {
        _emailObservers.add(o);
    }

    /**
     * Removes an email observer from the list.
     * @param o Email Observer
     */
    @Override
    public void RemoveEmailObservers(IEmailObserver o) 
    {
        _emailObservers.remove(o);
    }
    
    
    /**
     * Updates the current email observers with the list of encrypted emails. 
     */
    private void UpdateEmailObservers() throws MessagingException
    { 
        for (IEmailObserver observer : _emailObservers) 
        {
            observer.UpdateEmails(_messageList);
        }
    }
    
    
    /**
     * 
     * @param connected Current amount connection status
     * Updates the view with whether the email connection process was successful.
     */
    private void UpdateEmailStatus(boolean connected)
    {
        for (IEmailObserver observer : _emailObservers) 
        {
            observer.UpdateStatus(connected);          
        } 
    }
    
    /**
     * Updates the email observers with a boolean denoting if the email was sucessfully sent.
     * @param isSent Boolean showing if email is sent.
     */
    private void UpdateEmailSentStatus(boolean isSent)
    {
        for (IEmailObserver observer : _emailObservers) 
        {
            observer.UpdateSendStatus(isSent);
        } 
    }
}

