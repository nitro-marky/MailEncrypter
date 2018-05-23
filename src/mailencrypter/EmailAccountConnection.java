/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.MailException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

/**
 *
 * This class manages the connection to the email server.
 */
public class EmailAccountConnection implements IEmailAccountConnection {
 
    private boolean _accountStatus;
    private Properties _properties;
    private Session _emailSession;
    
    
    /**
     * 
     * Connects to the Outlook email server via SMTP and returns the connected mail session.
     * @param email User email account
     * @param password User password
     * @return Current email session
     * @throws javax.mail.NoSuchProviderException
     * @throws Exceptions.MailException
     */
    @Override
    public Session connectAccount(String email, String password) throws NoSuchProviderException, MailException
    {
        //Sets the properties for the email session 
        _properties = new Properties(); 
        _properties.put("mail.smtp.auth", "true");
        _properties.put("mail.smtp.starttls.enable", "true");
        _properties.put("mail.smtp.host", "smtp.office365.com");
        _properties.put("mail.smtp.port", "587");
        
        //Creates the email session and autheticates the user's details
        _emailSession = Session.getInstance(_properties, new javax.mail.Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(email, password);
            }
        }
        );
        _emailSession.setDebug(true);
        Transport _transport = _emailSession.getTransport("smtp");
        try 
        {
            _transport.connect("smtp.office365.com", 587, email, password);
        }
        catch (MessagingException ex)
        {
             throw new MailException("CONNECTION_FAILURE");
        }
        
        try 
        {
            _transport.close();
        } 
        catch (MessagingException ex) 
        {
             throw new MailException("CONNECTION_FAILURE");
        }
        _accountStatus = true;
        
        return _emailSession;
    }
    
    /**
     * Returns the connection status
     * @return Boolean representing the connection status
     */
    @Override
    public boolean getConnectionStatus()
    {
        return _accountStatus;
    }
    
}
