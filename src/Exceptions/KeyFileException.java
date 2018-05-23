/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Exceptions;

/**
 *
 * The KeyFileException is created when errors in key loading or saving occur
 */
public class KeyFileException extends Exception{
    
    private String _message;
    
    /**
     * Constructor for the KeyFileException. 
     * @param message The error description 
     */
    public KeyFileException(String message)
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
