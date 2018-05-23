/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Controller;

import Command.CheckEmailCommand;
import Command.EmailAccountCommand;
import Command.ICommand;
import Command.IEmailCommand;
import Command.KeyGenerateCommand;
import Exceptions.InvalidDecryptionError;
import Exceptions.KeyFileException;
import Exceptions.MailException;
import Model.EmailModel;
import Model.EncryptionModel;
import Model.IEmailModel;
import Model.IEmailModelObservable;
import Model.IEncryptionModel;
import Model.IEncryptionModelObservable;
import View.EmailView;
import View.MainView;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * The EncryptionController is the controller for the implemented MVC pattern. It coordinates input between the view and 
 * model. It also sets up the observers for the different models and creates the commands.
 */
public class Controller implements IEncryptionStrategy, IEmailStrategy, IKeyStrategy {

    //Singleton instance
    private static Controller _instance = null;
    
    //The models
    private final IEncryptionModel _encryptionModel;
    private final IEmailModel _emailModel;
    
    //The views
    private final MainView _view;
    private final EmailView _emailView;

    //Commands for input that does not pass data.
    private ICommand _emailAccountCommand;
    private ICommand _keyGenerateCommand;
    private IEmailCommand _checkMailCommand;

    
    /**
     * Returns singleton instance of the encryption controller
     * @return Controller instance
     */
    public static Controller getInstance()
    {
        if(_instance == null)
        {
            _instance = new Controller();
        }
        return _instance;
    }
    
    /**
     * Private constructor for singleton pattern
     */
    private Controller()
    {
        _encryptionModel = new EncryptionModel();  
        _emailModel = new EmailModel();
        _emailView = new EmailView(this);
        _view = new MainView(this);

        Initialise();

    }
    
    /**
     * Performs some of the initial setup for the Controller such as adding observers to the models
     * and creating and passing the commands.
     */
    private void Initialise()
    {
        //The main view is passed to the models as an observer. When data is changed the view is updated.
        IEncryptionModelObservable _encryptionObservable = (IEncryptionModelObservable)_encryptionModel;
        _encryptionObservable.AddEncryptionObservers(_view);
        IEmailModelObservable _emailObservable = (IEmailModelObservable)_emailModel;
        _emailObservable.AddEmailObservers(_view);

        
        //The commands are instantiated and passed the references needed to carry out the execute method.
        _emailAccountCommand = new EmailAccountCommand(_emailView);
        _keyGenerateCommand = new KeyGenerateCommand(_encryptionModel);
        _checkMailCommand = new CheckEmailCommand(_emailModel);

        //The commands are set within the view.
        _view.setCommand(_emailAccountCommand, 0);
        _view.setCommand(_keyGenerateCommand, 1);
        _view.setCommand(_checkMailCommand, 2);

        _view.setVisible(true);
    }
    
    /**
     * The encrypt method takes the input string from the model and passes it to the encryption model.
     * @param message String to be encrypted.
     * @param id The number of the encryption key selected.
     */
    @Override
    public void Encrypt(String message, int id) 
    {
       _encryptionModel.EncryptMessage(message, id);
    }

    /**
     * The decrypt method takes the input string from the model and passes it to the encryption model. 
     * @param message String to be decrypted
     * @param id The number of the selected decryption key.
     * @throws Exceptions.InvalidDecryptionError - Custom Exception
     */
    @Override
    public void Decrypt(String message, int id) throws InvalidDecryptionError
    {
        _encryptionModel.DecryptMessage(message, id);
    }

    /**
     * The email address and password are passed to the model in order to connect to the server. 
     * @param email String of user's email address
     * @param password String of user's password
     * @throws MailException - Custom Exception
     */
    @Override
    public void setEmailAttributes(String email, String password) throws MailException
    {
        try 
        {
            _emailModel.setAccount(email, password);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * The email attributes are sent to the email model for sending
     * @param recipient String of where the email is being sent
     * @param message String of the email message
     * @param subject String of the email subject
     * @param keyID String representing the key ID used to encrypt
     * @throws MailException - Custom Exception
     */
    @Override
    public void sendEmail(String recipient, String message, String subject, String keyID) throws MailException
    {
        //If the user has not entered a recipient address a custom exception is thrown.
        if(recipient.isEmpty())
        {
            throw new MailException("INVALID_MAIL_DETAILS");
        }
        else
        {
            try 
            {
                _emailModel.sendMessage(recipient, message, subject, keyID);
            }       
            catch (UnsupportedEncodingException ex) 
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (MessagingException ex) 
            {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Passes the index of the selected email from the JList to the email model.
     * @param index Index of the email within the JList
     */
    public void openSelectedEmail(int index)
    {
        try 
        {
            _emailModel.openSelectedEmail(index);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Takes the file name which includes the file path as a parameter for serialisation.
     * @param fileName What the file will be called along with the file path
     */
    @Override
    public void saveKeys(String fileName) 
    {
        _encryptionModel.saveKeys(fileName);
    }
    
    /**
     * Takes the file name which includes the file path as a parameter for deserialisation.
     * @param filePath The name and location of the file
     * @throws KeyFileException - Custom exception
     */
    @Override
    public void loadKeys(String filePath)throws KeyFileException
    {
        _encryptionModel.loadKeys(filePath);
    }

    /**
     * Takes the index of the email from the JList and passes it to the email model for 
     * deletion.
     * @param id Index of the message in the JList
     * @throws MailException - Custom Exception
     */
    @Override
    public void deleteMessage(int id) throws MailException 
    {
        try {
            _emailModel.deleteMessage(id);
        } catch (MessagingException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
