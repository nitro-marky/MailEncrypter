/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Exceptions;

/**
 *
 * The MailException is created when errors in mail sending, checking or connecting occur
 */
public class MailException extends Exception {
    
    private String _message;
    
    /**
     * Constructor for MailException
     * @param message Description of the error
     */
    public MailException(String message)
    {
        _message = message;
    }
    
    /**
     * Returns the error message string
     * @return String containing the error description
     */
    @Override
    public String getMessage()
    {
        return _message;
    }
    
}
