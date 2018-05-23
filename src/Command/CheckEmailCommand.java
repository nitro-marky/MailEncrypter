/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import Exceptions.MailException;
import Model.IEmailModel;
import javax.mail.MessagingException;

/**
 *
 * The CheckEmailCommand executes the email fetching process when 'Check Emails' is 
 * clicked in the MainView.
 */
public class CheckEmailCommand implements IEmailCommand {

    private IEmailModel _model;
    
    /**
     * Constructor for the CheckEmailCommand.
     * @param model IEmailModel is needed for Execute();
     */
    public CheckEmailCommand(IEmailModel model)
    {
        _model = model;
    }
    
    /**
     * Execute calls the models checkMessage method. It throws a custom exception if
     * there is an MessagingException.
     * @throws Exceptions.MailException when MessageException is found
     */
    
    @Override
    public void Execute() throws MailException 
    {
        if(_model != null)
        {
            try 
            {
                _model.checkMessages();
            } 
            catch (MessagingException ex) 
            {
                throw new MailException("INBOX_ERROR");
            }
        }
    }

}
